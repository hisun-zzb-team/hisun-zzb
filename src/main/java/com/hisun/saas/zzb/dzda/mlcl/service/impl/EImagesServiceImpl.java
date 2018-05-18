/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.mlcl.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.admin.dzda.entity.ECatalogTypeInfo;
import com.hisun.saas.sys.admin.dzda.service.ECatalogTypeService;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.mlcl.Constants;
import com.hisun.saas.zzb.dzda.mlcl.dao.E01Z1Dao;
import com.hisun.saas.zzb.dzda.mlcl.dao.EImagesDao;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;
import com.hisun.saas.zzb.dzda.mlcl.entity.EImages;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.saas.zzb.dzda.mlcl.service.EImagesService;
import com.hisun.util.DESUtil;
import com.hisun.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhout {605144321@qq.com}
 */
@Service
public class EImagesServiceImpl extends BaseServiceImpl<EImages, String>
        implements EImagesService {

    @Resource
    private E01Z1Service e01Z1Service;
    @Resource
    private ECatalogTypeService eCatalogTypeService;
    @Resource
    private E01Z1Dao e01Z1Dao;
    @Value("${sys.upload.absolute.path}")
    private String uploadBasePath;

    private EImagesDao eImagesDao;

    @Resource
    public void setBaseDao(BaseDao<EImages, String> baseDao) {
        this.baseDao = baseDao;
        this.eImagesDao = (EImagesDao) baseDao;
    }


    public void saveEImagesByJztp(A38 a38, File storePathFile) throws Exception {
        //在加载图片之前,先清除原有的已加载的图片数据
        this.deleteEImagesByA38(a38);
        List<File> files = FileUtil.listFilesOrderByName(storePathFile);
        Map<E01Z1, Integer> yjzTpMaps = new HashMap<>();
        for (File file : files) {
            if (Arrays.asList(Constants.EXCLUDE_FILE_AND_DIR).contains(file.getName())) continue;
            //取得当前目录下的材料
            String e01Z101B = file.getName().substring(0, file.getName().indexOf("."));
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and("e01Z101B=:e01Z101B", "e01Z101B", e01Z101B));
            query.add(CommonRestrictions.and("a38.id=:a38Id", "a38Id", a38.getId()));
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("e01z107"));
            List<E01Z1> e01z1s = this.e01Z1Service.list(query, orderBy);
            //循环已上传图片文件,为对应的材料加载图片
            if (e01z1s.size() > 0) {
                List<File> tpFiles = FileUtil.listFilesOrderByName(file);
                for (File tpFile : tpFiles) {
                    boolean isNeedDelete = true;
                    if (Arrays.asList(Constants.EXCLUDE_FILE_AND_DIR).contains(tpFile.getName())) {
                        continue;
                    }
                    for (E01Z1 e01Z1 : e01z1s) {
                        DecimalFormat decimalFormat = new DecimalFormat("00");
                        String nameCode = decimalFormat.format(e01Z1.getE01Z104());//当前材料对应文件编号

                        String tpNameCode = "";
                        if (tpFile.getName().lastIndexOf(".") != -1) {
                            tpNameCode = tpFile.getName().substring(0, tpFile.getName().lastIndexOf(".")).substring(0, 2);//上传图片文件名编号
                        }
                        if (tpNameCode.equals(nameCode)) {
                            isNeedDelete = false;
                            EImages eImages = new EImages();
                            eImages.setE01z1(e01Z1);
                            String encryptFilePath = tpFile.getPath().substring(0, tpFile.getPath().lastIndexOf("."));
                            DESUtil.getInstance(Constants.DATP_KEY).encrypt(tpFile, new File(encryptFilePath));
                            eImages.setImgFilePath(encryptFilePath.substring(uploadBasePath.length(), encryptFilePath.length()));
                            FileUtils.deleteQuietly(tpFile);
                            eImages.setImgNo(Integer.getInteger(tpFile.getName().substring(0, tpFile.getName().lastIndexOf(".")).substring(2)));
                            this.save(eImages);
                            //记录已加载图片数
                            if (yjzTpMaps.get(e01Z1) != null) {
                                Integer count = yjzTpMaps.get(e01Z1) + 1;
                                yjzTpMaps.put(e01Z1, count);
                            } else {
                                yjzTpMaps.put(e01Z1, 1);
                            }
                        }
                    }
                    //如果没有对应的材料则删除已上传的图片
                    if (isNeedDelete) {
                        if (tpFile.isDirectory()) {
                            FileUtils.deleteDirectory(tpFile);
                        } else {
                            FileUtils.deleteQuietly(tpFile);
                        }
                    }
                }
            } else {
                //如果没有对应的材料,则为多余的材料目录进行删除
                if (file.isDirectory()) {
                    FileUtils.deleteDirectory(file);
                } else {
                    FileUtils.deleteQuietly(file);
                }
            }
        }

        //处理已加载图片数
        for (E01Z1 e01Z1 : yjzTpMaps.keySet()) {
            e01Z1.setYjztps(yjzTpMaps.get(e01Z1));
            this.e01Z1Service.update(e01Z1);
        }
    }


    public void saveEImagesByJztp(E01Z1 e01Z1, File storePathFile) throws Exception {
        this.deleteEImagesByE01ez1(e01Z1);
        ECatalogTypeInfo eCatalogTypeInfo = this.eCatalogTypeService.getByPK(e01Z1.getECatalogTypeId());
        String realStorePath = uploadBasePath + getTpStorePath(e01Z1.getA38().getId()) + eCatalogTypeInfo.getCatalogCode()
                + "." + eCatalogTypeInfo.getCatalogValue() + File.separator;
        File realStorePathFile = new File(realStorePath);
        if (!realStorePathFile.exists()) {
            realStorePathFile.mkdirs();
        }
        List<File> files = FileUtil.listFilesOrderByName(storePathFile);
        int yjz = 0;
        for (File file : files) {
            if (Arrays.asList(Constants.EXCLUDE_FILE_AND_DIR).contains(file.getName())) continue;
            List<File> tpFiles = FileUtil.listFilesOrderByName(file);
            for (File tpFile : tpFiles) {
                boolean isNeedDelete = true;
                if (Arrays.asList(Constants.EXCLUDE_FILE_AND_DIR).contains(tpFile.getName())) {
                    continue;
                }

                DecimalFormat decimalFormat = new DecimalFormat("00");
                String nameCode = decimalFormat.format(e01Z1.getE01Z104());//当前材料对应文件编号
                String tpNameCode = "";
                if (tpFile.getName().lastIndexOf(".") != -1) {
                    tpNameCode = tpFile.getName().substring(0, tpFile.getName().lastIndexOf(".")).substring(0, 2);//上传图片文件名编号
                }
                if (tpNameCode.equals(nameCode)) {
                    isNeedDelete = false;
                    EImages eImages = new EImages();
                    eImages.setE01z1(e01Z1);
                    String encryptFilePath = tpFile.getPath().substring(0, tpFile.getPath().lastIndexOf("."));
                    File encryptFile = new File(encryptFilePath);
                    DESUtil.getInstance(Constants.DATP_KEY).encrypt(tpFile, encryptFile);
                    FileUtils.moveFileToDirectory(encryptFile, realStorePathFile,false);
                    eImages.setImgFilePath(realStorePath.substring(uploadBasePath.length(), realStorePath.length()) + encryptFile.getName());
                    FileUtils.deleteQuietly(tpFile);
                    eImages.setImgNo(Integer.getInteger(tpFile.getName().substring(0, tpFile.getName().lastIndexOf(".")).substring(2)));
                    this.save(eImages);
                    yjz++;
                }
                //如果没有对应的材料则删除已上传的图片
                if (isNeedDelete) {
                    if (tpFile.isDirectory()) {
                        FileUtils.deleteDirectory(tpFile);
                    } else {
                        FileUtils.deleteQuietly(tpFile);
                    }
                }
            }
        }
        e01Z1.setYjztps(yjz);
        this.e01Z1Service.update(e01Z1);

    }

    public String getTpStorePath(String a38Id) {
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        return Constants.DATP_STORE_PATH
                + userLoginDetails.getTenantId() + File.separator
                + a38Id.substring(a38Id.length() - 1, a38Id.length()) + File.separator
                + a38Id + File.separator;
    }

    public void deleteEImagesByA38(A38 a38) {
        List<E01Z1> e01Z1s = a38.getE01z1s();
        for (E01Z1 e01Z1 : e01Z1s) {
            this.deleteEImagesByE01ez1(e01Z1);
            e01Z1.setYjztps(0);
            e01Z1.setA38(a38);
            this.e01Z1Dao.update(e01Z1);
        }
    }

    public void deleteEImagesByE01ez1(E01Z1 e01Z1) {
        StringBuffer deleteEImages = new StringBuffer();
        deleteEImages.append(" delete from EImages EImages where EImages.e01z1.id = :id");
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and("", "id", e01Z1.getId()));
        this.eImagesDao.executeBulk(deleteEImages.toString(), query);
    }


    public void deleteEImagesAndFileByA38(A38 a38) throws Exception {
        this.deleteEImagesByA38(a38);
        File tpStorePathFile = new File(uploadBasePath + getTpStorePath(a38.getId()));
        if (tpStorePathFile.exists()) {
            FileUtils.deleteDirectory(tpStorePathFile);
        }
    }

    /**
     * 调整图片顺序
     * @param eImages
     * @param oldImgNo
     * @throws Exception
     */
    public void updateEImagesImgNo(EImages eImages,int oldImgNo)throws Exception{
        if(eImages.getImgNo()!=oldImgNo) {
            List<EImages> images = this.getNeedUpdateEImages(eImages,oldImgNo);
            if(images!=null && images.size()>0){
                this.updateImagesSort(images,eImages.getE01z1().getA38().getId(),oldImgNo,eImages);
            }
            this.eImagesDao.update(eImages);
        }
    }

    /**
     *
     * @param eImages
     * @param uploadType 上传方式 frist表示插入首页，up表示插入上一页 down表示下一页 end表示尾页
     * @throws Exception
     */
    public void saveEImages(EImages eImages,String uploadType,int oldImgNo,File imgFile,String encryptFilePath)throws Exception{

        if(!uploadType.equals("end")) {
            List<EImages> images = this.getNeedUpdateEImages(eImages, oldImgNo);
            if (images != null && images.size() > 0) {
                this.updateImagesSort(images, eImages.getE01z1().getA38().getId(), oldImgNo, eImages);
            }
        }
        DESUtil.getInstance(Constants.DATP_KEY).encrypt(imgFile, new File(encryptFilePath));

        FileUtils.deleteQuietly(imgFile);
        this.eImagesDao.save(eImages);

        E01Z1 e01Z1 = eImages.getE01z1();
        e01Z1.setYjztps(e01Z1.getYjztps()+1);
        this.e01Z1Dao.update(e01Z1);
    }

    /**
     * 调整图片排序及调整存储服务图片的名称
     * @param eImages 需要调整的图片集合
     * @param oldSort  修改材料的旧排序
     */
    private void updateImagesSort(List<EImages> eImages,String a38Id,int oldSort,EImages updateImage) throws Exception{
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf=new   SimpleDateFormat("yyyyMMddHHmmss");//定义日期格式
        String nowDate = sdf.format(date);//系统当前日期
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        String myDirName = details.getUserid()+nowDate+"TMP";
        String path = "";//文件存放路径

        String dirPath = uploadBasePath+this.getTpStorePath(a38Id)+myDirName;//	取得临时存放图片的路径
        File storeDir = new File(dirPath);
        if (storeDir.exists() == false) {
            storeDir.mkdirs();
        }
        String newE01z104 ="";
        int newSort = updateImage.getImgNo();
        //如果有需要重新按照材料序号修改的图片则先按照材料序号修改名字后移动到临时文件
        if (eImages != null && eImages.size() > 0) {
            for (EImages image : eImages) {
                int imgNo = image.getImgNo();//图片序号
                int e01z104 = image.getE01z1().getE01Z104();

                if (image.getId() != updateImage.getId()) {
                    if (imgNo < oldSort) {
                        imgNo = imgNo + 1;
                    } else {
                        imgNo = imgNo - 1;
                    }
                }
                if (path.equals("")) {
                    int lastIndex = 0;
                    if (image.getImgFilePath().lastIndexOf("/") != -1) {
                        lastIndex = image.getImgFilePath().lastIndexOf("/");
                    } else {
                        lastIndex = image.getImgFilePath().lastIndexOf("\\");
                    }
                    path = image.getImgFilePath().substring(0, lastIndex);
                }
                String imgPath = uploadBasePath + image.getImgFilePath();
                File file = new File(imgPath);
                FileInputStream fileinputstream = null;
                FileOutputStream fileoutputStream = null;
                if (e01z104 < 10) {
                    newE01z104 = "0" + e01z104;
                } else {
                    newE01z104 = e01z104 + "";
                }
                String newFileName = newE01z104 + imgNo;
                if (file.exists()) {
                    fileinputstream = new FileInputStream(file);
                    long len = file.length();
                    String newFilePath = dirPath + File.separator + newFileName;
                    byte abyte0[] = new byte[Integer.parseInt(Long.toString(len))];
                    File newFile = new File(newFilePath);
                    if (!newFile.exists()) {
                        newFile.createNewFile();
                    }
                    fileoutputStream = new FileOutputStream(newFile);
                    fileinputstream.read(abyte0, 0, Integer.parseInt(Long.toString(len)));
                    fileoutputStream.write(abyte0);
                    if (fileinputstream != null) {
                        fileinputstream.close();
                    }
                    if (fileoutputStream != null) {
                        fileoutputStream.close();
                    }
                    file.delete();
                }
                image.setImgFilePath(path + File.separator + newFileName);
                image.setImgNo(imgNo);
                if (image.getId() != updateImage.getId()) {
                    this.eImagesDao.update(image);
                }
            }
        }

        if(!dirPath.equals("")){
            File desfile = new File(dirPath);
            if(desfile.exists()){
                File[] files = desfile.listFiles();
                for(int i=0;i<files.length;i++){
                    String fileName = files[i].getName();
                    File file =  files[i];
                    FileInputStream fileinputstream = null;
                    FileOutputStream fileoutputStream = null;
                    fileinputstream = new FileInputStream(file);
                    long len = file.length();
                    String newFilePath = uploadBasePath+path+File.separator+fileName;
                    byte abyte0[] = new byte[Integer.parseInt(Long.toString(len))];
                    File newFile = new File(newFilePath);
                    if(!newFile.exists()){
                        newFile.createNewFile();
                    }

                    fileoutputStream = new FileOutputStream(newFile);

                    fileinputstream.read(abyte0, 0, Integer.parseInt(Long.toString(len)));

                    fileoutputStream.write(abyte0);

                    if(fileinputstream != null ){
                        fileinputstream.close();
                    }
                    if(fileoutputStream != null ){
                        fileoutputStream.close();
                    }
                    file.delete();
                }
                desfile.delete();
            }
        }
    }

    //得到在刷新排序时 需要调整排序的图片
    private List<EImages> getNeedUpdateEImages(EImages images, Integer oldSort){
        Integer newSort = images.getImgNo();
        List<EImages> eImages = new ArrayList<EImages>();
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" e01z1.id = :id ", "id", images.getE01z1().getId()));
        if(newSort>oldSort){
            query.add(CommonRestrictions.and(" imgNo <= :newSort ", "newSort", newSort));
            query.add(CommonRestrictions.and(" imgNo > :oldSort ", "oldSort", oldSort));
        }else{
            query.add(CommonRestrictions.and(" imgNo < :oldSort ", "oldSort", oldSort));
            query.add(CommonRestrictions.and(" imgNo >= :newSort ", "newSort", newSort));
        }

        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.asc("imgNo"));
        eImages = this.eImagesDao.list(query,orderBy);
        return eImages;
    }
    //删除单张图片
    public void deleteEImages(EImages images)throws Exception{
        int maxSort = this.getMaxImgNo(images.getE01z1().getId())+1;
        Integer oldSort = images.getImgNo();//以前的排序号
        if(oldSort < maxSort){//如果新的排序号比以前的排序号小
            images.setImgNo(maxSort);
            List<EImages> eImages = this.getNeedUpdateEImages(images,oldSort);
            if(eImages!=null && eImages.size()>0){
                this.updateImagesSort(eImages,images.getE01z1().getA38().getId(),oldSort,images);
            }
        }
        FileUtils.deleteQuietly(new File(uploadBasePath+images.getImgFilePath()));
        this.eImagesDao.delete(images);

        E01Z1 e01Z1 = images.getE01z1();
        e01Z1.setYjztps(e01Z1.getYjztps()-1);
        this.e01Z1Dao.update(e01Z1);
    }

    public Integer getMaxImgNo(String e01z1Id) {
        Map<String, Object> map=new HashMap<String, Object>();
        String hql = "select max(e.imgNo)+1 as imgNo from EImages e ";
        if(e01z1Id!=null && !e01z1Id.equals("")) {
            hql = hql+"where e.e01z1.id =:e01z1Id";
            map.put("e01z1Id", e01z1Id);
        }
        List<Map> maxSorts = this.e01Z1Dao.list(hql, map);
        if(maxSorts.get(0).get("imgNo")==null){
            return 0;
        }else{
            Integer maxSort = ((Number) maxSorts.get(0).get("imgNo")).intValue();
            return maxSort;
        }
    }
}
