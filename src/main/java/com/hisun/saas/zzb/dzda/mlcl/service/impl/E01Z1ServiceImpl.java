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
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.dzda.mlcl.Constants;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;
import com.hisun.saas.zzb.dzda.mlcl.entity.EImages;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.saas.zzb.dzda.mlcl.dao.E01Z1Dao;
import com.hisun.saas.zzb.dzda.mlcl.service.EImagesService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhout {605144321@qq.com}
 */
@Service
public class E01Z1ServiceImpl extends BaseServiceImpl<E01Z1,String>
        implements E01Z1Service {

    @Value("${sys.upload.absolute.path}")
    private String uploadBasePath;
    private E01Z1Dao e01Z1Dao;
    @Resource
    private EImagesService eImagesService;

    @Resource
    public void setBaseDao(BaseDao<E01Z1, String> baseDao) {
        this.baseDao = baseDao;
        this.e01Z1Dao = (E01Z1Dao)baseDao;
    }

    public Integer getMaxSort(String a38Id,String e01Z101B) {
        Map<String, Object> map=new HashMap<String, Object>();
        String hql = "select max(e.e01Z104)+1 as sort from E01Z1 e ";
        if(a38Id!=null && !a38Id.equals("")) {
            hql = hql+"where e.a38.id =:a38Id";
            map.put("a38Id", a38Id);
        }else{
            hql = hql+"where e.a38 is null";
        }

        if(e01Z101B!=null && !e01Z101B.equals("")) {
            hql = hql+" and e.e01Z101B =:e01Z101B";
            map.put("e01Z101B", e01Z101B);
        }else{
            hql = hql+" and e.e01Z101B is null";
        }

        List<Map> maxSorts = this.e01Z1Dao.list(hql, map);
        if(maxSorts.get(0).get("sort")==null){
            return 1;
        }else{
            Integer maxSort = ((Number) maxSorts.get(0).get("sort")).intValue();
            return maxSort;
        }
    }

//    public Integer getMaxSmSort(String a38Id,String e01Z101B) {
//        Map<String, Object> map=new HashMap<String, Object>();
//        String hql = "select max(e.e01Z107)+1 as sort from E01Z1 e ";
//        if(a38Id!=null && !a38Id.equals("")) {
//            hql = hql+"where e.a38.id =:a38Id";
//            map.put("a38Id", a38Id);
//        }else{
//            hql = hql+"where e.a38 is null";
//        }
//
//        if(e01Z101B!=null && !e01Z101B.equals("")) {
//            hql = hql+" and e.e01Z101B =:e01Z101B";
//            map.put("e01Z101B", e01Z101B);
//        }else{
//            hql = hql+" and e.e01Z101B is null";
//        }
//
//        List<Map> maxSorts = this.e01Z1Dao.list(hql, map);
//        if(maxSorts.get(0).get("sort")==null){
//            return 1;
//        }else{
//            Integer maxSort = ((Number) maxSorts.get(0).get("sort")).intValue();
//            return maxSort;
//        }
//    }

    public void updateE01Z1(E01Z1 e01z1, Integer oldSort)throws Exception {
        if(e01z1.getE01Z104()!=oldSort) {
            List<E01Z1> e01Z1s = this.getNeedUpdateSortE01z1(e01z1, oldSort);
            if (e01Z1s != null && e01Z1s.size() > 0) {
                this.updateImagesSort(e01Z1s,oldSort,e01z1.getA38().getId(),e01z1);
            }
//            this.updateSort(e01z1, oldSort);
        }
        this.e01Z1Dao.update(e01z1);
    }

    //用于修改材料排序刷新
    private void updateSort(E01Z1 e01z1, Integer oldSort)  {
        CommonConditionQuery query = new CommonConditionQuery();
        Integer newSort = e01z1.getE01Z104();
        String sql = "update e01z1 t set ";
        if (newSort > oldSort) {
            sql += "t.e01z104=t.e01z104-1";
        } else {
            sql += "t.e01z104=t.e01z104+1";
        }
        sql +=" where  t.a38_id = '" + e01z1.getA38().getId() + "'"
                + " and t.e01z101b = '" + e01z1.getE01Z101B() + "'";
        if (newSort > oldSort) {
            sql += " and t.e01z104<=" + newSort + " and t.e01z104 >"
                    + oldSort;
        } else {
            sql += " and t.e01z104<" + oldSort + " and t.e01z104>="
                    + newSort;
        }
        this.e01Z1Dao.executeNativeBulk(sql,query);
    }

    //用于添加材料排序刷新
    public void updateSortBeforSave(E01Z1 e01z1, Integer oldSort) throws Exception {
        List<E01Z1> e01Z1s = this.getNeedUpdateSortE01z1(e01z1,oldSort);
        if(e01Z1s!=null && e01Z1s.size()>0){
            this.updateImagesSort(e01Z1s,oldSort,e01z1.getA38().getId(),e01z1);
        }

//        CommonConditionQuery query = new CommonConditionQuery();
//        Integer newSort = e01z1.getE01Z104();
//        String sql="update e01z1 t set ";
//        sql+="t.e01z104=t.e01z104+1";
//
//        sql +=" where t.a38_id = '" + e01z1.getA38().getId() + "'"
//                + " and t.e01z101b = '" + e01z1.getE01Z101B() + "'";
//
//        sql+=" and t.e01z104<"+oldSort+" and t.e01z104>="+newSort;
//
//        this.e01Z1Dao.executeNativeBulk(sql,query);


    }


    public void deleteE01Z1(E01Z1 e01Z1) throws Exception{
        int maxSort = this.getMaxSort(e01Z1.getA38().getId(),e01Z1.getE01Z101B());
        Integer oldSort = e01Z1.getE01Z104();//以前的排序号
        if(oldSort < maxSort){//如果新的排序号比以前的排序号小
            e01Z1.setE01Z104(maxSort);
            List<E01Z1> e01Z1s = this.getNeedUpdateSortE01z1(e01Z1,oldSort);
            if(e01Z1s!=null && e01Z1s.size()>0){
                this.updateImagesSort(e01Z1s,oldSort,e01Z1.getA38().getId(),e01Z1);
            }
        }
        List<EImages> eImages = e01Z1.getImages();
        for(EImages eImage : eImages){
            FileUtils.deleteQuietly(new File(uploadBasePath+eImage.getImgFilePath()));
        }
        this.e01Z1Dao.delete(e01Z1);
    }

    //得到在刷新排序时 需要调整排序的材料
    private List<E01Z1> getNeedUpdateSortE01z1(E01Z1 e01z1, Integer oldSort){
        Integer newSort = e01z1.getE01Z104();
        List<E01Z1> e01Z1s = new ArrayList<E01Z1>();
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" a38.id = :id ", "id", e01z1.getA38().getId()));
        query.add(CommonRestrictions.and(" e01z101b = :e01z101b ", "e01z101b", e01z1.getE01Z101B()));
        if(newSort>oldSort){
            query.add(CommonRestrictions.and(" e01z104 <= :newSort ", "newSort", newSort));
            query.add(CommonRestrictions.and(" e01z104 > :oldSort ", "oldSort", oldSort));
        }else{
            query.add(CommonRestrictions.and(" e01z104 < :oldSort ", "oldSort", oldSort));
            query.add(CommonRestrictions.and(" e01z104 >= :newSort ", "newSort", newSort));
        }

        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.asc("e01Z104"));
        e01Z1s = this.e01Z1Dao.list(query,orderBy);
        return e01Z1s;
    }

    /**
     * 调整图片排序及调整存储服务图片的名称
     * @param e01Z1s 需要调整图片的材料集合
     * @param oldSort  修改材料的旧排序
     */
    private void updateImagesSort( List<E01Z1> e01Z1s,int oldSort,String a38Id,E01Z1 updateE01z1) throws Exception{
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

        String newE01z104 = "";
        for(E01Z1 e01z1 : e01Z1s) {
            int e01z104 = e01z1.getE01Z104();//材料序号
            if (e01z1.getId() != updateE01z1.getId()) {
                if (e01z104 < oldSort) {
                    e01z104 = e01z104 + 1;
                } else {
                    e01z104 = e01z104 - 1;
                }
            }
            if (e01z1.getImages() != null && e01z1.getImages().size() > 0) {//e01z107!=e01z104 &&
                List<EImages> eImages = e01z1.getImages();
                //如果有需要重新按照材料序号修改的图片则先按照材料序号修改名字后移动到临时文件
                if (eImages != null && eImages.size() > 0) {
                    for (EImages image : eImages) {
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
                        int imgNo = image.getImgNo();
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
                        if (e01z1.getId() != updateE01z1.getId()) {
                            this.eImagesService.update(image);
                        }
                    }
                }
            }
            if (e01z1.getId() != updateE01z1.getId()){
                e01z1.setE01Z104(e01z104);
                this.e01Z1Dao.update(e01z1);
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
    private String getTpStorePath(String a38Id) {
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        return Constants.DATP_STORE_PATH
                + userLoginDetails.getTenantId() + File.separator
                + a38Id.substring(a38Id.length() - 1, a38Id.length()) + File.separator
                + a38Id + File.separator;
    }
}
