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
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.a38.vo.WrongExcelColumn;
import com.hisun.saas.zzb.dzda.mlcl.Constants;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;
import com.hisun.saas.zzb.dzda.mlcl.entity.EImages;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.saas.zzb.dzda.mlcl.dao.E01Z1Dao;
import com.hisun.saas.zzb.dzda.mlcl.service.EImagesService;
import com.hisun.saas.zzb.dzda.mlcl.vo.E01Z1ExcelVo;
import com.hisun.saas.zzb.dzda.mlcl.vo.E01Z1Vo;
import com.hisun.saas.zzb.dzda.util.DaUtils;
import com.hisun.util.StringUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private ECatalogTypeService eCatalogTypeService;
    @Resource
    private A38Service a38Service;

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

    /**
     * 检查目录信息数据是否合法
     * @param e01Z1ExcelVo
     * @return
     */
    @Override
    public Map<String,Object> checkE01Z1ExcelVo(E01Z1ExcelVo e01Z1ExcelVo){
        Map<String,Object> returnMap = new HashMap<>();
        List<E01Z1Vo> e01Z1Vos = new ArrayList<>();
        e01Z1Vos.addAll(e01Z1ExcelVo.getJlcl());
        e01Z1Vos.addAll(e01Z1ExcelVo.getZzcl());
        e01Z1Vos.addAll(e01Z1ExcelVo.getJdcl());
        e01Z1Vos.addAll(e01Z1ExcelVo.getXlxw());
        e01Z1Vos.addAll(e01Z1ExcelVo.getZyzg());
        e01Z1Vos.addAll(e01Z1ExcelVo.getKysp());
        e01Z1Vos.addAll(e01Z1ExcelVo.getPxcl());
        e01Z1Vos.addAll(e01Z1ExcelVo.getZscl());
        e01Z1Vos.addAll(e01Z1ExcelVo.getDtcl());
        e01Z1Vos.addAll(e01Z1ExcelVo.getJlicl());
        e01Z1Vos.addAll(e01Z1ExcelVo.getCfcl());
        e01Z1Vos.addAll(e01Z1ExcelVo.getGzcl());
        e01Z1Vos.addAll(e01Z1ExcelVo.getRmcl());
        e01Z1Vos.addAll(e01Z1ExcelVo.getCgcl());
        e01Z1Vos.addAll(e01Z1ExcelVo.getDbdh());
        e01Z1Vos.addAll(e01Z1ExcelVo.getQtcl());
        return checkE01z1(e01Z1Vos,returnMap);
    }

    /**
     * 检查E01Z1Vo的数据是否合法
     * @param e01Z1Vos
     * @param returnMap
     * @return
     */
    public static Map<String,Object> checkE01z1(List<E01Z1Vo> e01Z1Vos, Map<String,Object> returnMap){
        List<WrongExcelColumn> wrongExcelColumns = new ArrayList<>();
        returnMap.put("isRight",false);
        if(e01Z1Vos.size()>0){
            WrongExcelColumn wrongExcelColumn;

            try {

                for(E01Z1Vo e01Z1Vo:e01Z1Vos) {
                    int sum = 0;
                    boolean flag = false;//判断是否存在非法数据
                    boolean flag1 = false;//判断必填数据是否全为空
                    if (e01Z1Vo != null) {

                        //判断必填材料是否为空
                        if(e01Z1Vo.getE01Z114()==null||e01Z1Vo.getE01Z114() == 0){
                            wrongExcelColumn = new WrongExcelColumn();
                            wrongExcelColumn.setLines("F"+e01Z1Vo.getRow());
                            wrongExcelColumn.setReason("页数不能为空且大于0");
                            wrongExcelColumn.setWrongExcel("干部档案目录表");
                            wrongExcelColumns.add(wrongExcelColumn);
                            flag = true;
                            sum++;
                        }
                        if(StringUtils.isEmpty(e01Z1Vo.getE01Z111())){
                            wrongExcelColumn = new WrongExcelColumn();
                            wrongExcelColumn.setLines("B"+e01Z1Vo.getRow());
                            wrongExcelColumn.setReason("材料名称不能为空");
                            wrongExcelColumn.setWrongExcel("干部档案目录表");
                            wrongExcelColumns.add(wrongExcelColumn);
                            flag = true;
                            sum++;
                        }

                        //拼接日期
                        String e01Z117 = "";
                        if(StringUtils.isNotEmpty(e01Z1Vo.getYear())){
                            e01Z117 = getDate(e01Z1Vo);
                            if(DaUtils.isNotDate(e01Z117)){
                                wrongExcelColumn = new WrongExcelColumn();
                                wrongExcelColumn.setLines("C/D/E"+e01Z1Vo.getRow());
                                wrongExcelColumn.setReason("日期格式错误");
                                wrongExcelColumn.setWrongExcel("干部档案目录表");
                                wrongExcelColumns.add(wrongExcelColumn);
                                flag = true;
                                sum++;
                            }
                        }

                        if((e01Z1Vo.getE01Z104()==null||e01Z1Vo.getE01Z104() == 0)
                                &&(e01Z1Vo.getE01Z114()==null||e01Z1Vo.getE01Z114() == 0)
                                &&StringUtils.isEmpty(e01Z1Vo.getE01Z111())){
                            flag1 = true;
                        }

                        if(flag){
                            if(flag1){
                                for(int j=0;j<sum;j++){
                                    wrongExcelColumns.remove(wrongExcelColumns.size()-1);
                                }
                            }else {
                                returnMap.put("isRight",true);
                            }
                            continue;
                        }
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        returnMap.put("wrongExcelColumns",wrongExcelColumns);
        return returnMap;
    }

    /**
     * 分类保存目录信息
     * @param e01Z1ExcelVo
     * @param a38
     */
    @Override
    public void saveE01Z1S(E01Z1ExcelVo e01Z1ExcelVo, A38 a38) {
        addE01z1(e01Z1ExcelVo.getJlcl(), "jlcl", a38.getId());
        addE01z1(e01Z1ExcelVo.getZzcl(), "zzcl", a38.getId());
        addE01z1(e01Z1ExcelVo.getJdcl(), "jdcl", a38.getId());
        addE01z1(e01Z1ExcelVo.getXlxw(), "xlxw", a38.getId());
        addE01z1(e01Z1ExcelVo.getZyzg(), "zyzg", a38.getId());
        addE01z1(e01Z1ExcelVo.getKysp(), "kysp", a38.getId());
        addE01z1(e01Z1ExcelVo.getPxcl(), "pxcl", a38.getId());
        addE01z1(e01Z1ExcelVo.getZscl(), "zscl", a38.getId());
        addE01z1(e01Z1ExcelVo.getDtcl(), "dtcl", a38.getId());
        addE01z1(e01Z1ExcelVo.getJlicl(), "jlicl", a38.getId());
        addE01z1(e01Z1ExcelVo.getCfcl(), "cfcl", a38.getId());
        addE01z1(e01Z1ExcelVo.getGzcl(), "gzcl", a38.getId());
        addE01z1(e01Z1ExcelVo.getRmcl(), "rmcl", a38.getId());
        addE01z1(e01Z1ExcelVo.getCgcl(), "cgcl", a38.getId());
        addE01z1(e01Z1ExcelVo.getDbdh(), "dbdh", a38.getId());
        addE01z1(e01Z1ExcelVo.getQtcl(), "qtcl", a38.getId());
    }

    /**
     * 新增材料
     * @param e01Z1Vos
     * @param listStr
     * @param a38Id
     * @return
     */
    public void addE01z1(List<E01Z1Vo> e01Z1Vos, String listStr, String a38Id){
        if(e01Z1Vos.size()>0){
            //获得材料类别
            String catalogCode = DaUtils.getCatalogCode(listStr);//获取材料类别Code
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" catalogCode = :catalogCode ", "catalogCode", catalogCode));
            CommonOrderBy orderBy = new CommonOrderBy();
            List<ECatalogTypeInfo> entities = eCatalogTypeService.list(query, orderBy);
            ECatalogTypeInfo eCatalogTypeInfo = new ECatalogTypeInfo();
            if(entities.size()>0){
                eCatalogTypeInfo=entities.get(0);
            }

            try {

                for(E01Z1Vo e01Z1Vo:e01Z1Vos) {
                    boolean flag1 = true;
                    if((e01Z1Vo.getE01Z104()==null||e01Z1Vo.getE01Z104() == 0)
                            &&(e01Z1Vo.getE01Z114()==null||e01Z1Vo.getE01Z114() == 0)
                            &&StringUtils.isEmpty(e01Z1Vo.getE01Z111())){
                        flag1 = false;
                    }

                    if (e01Z1Vo != null) {
                        if(flag1){

                            //拼接日期
                            String e01Z117 = getDate(e01Z1Vo);
                            e01Z1Vo.setE01Z117(e01Z117);

                            int sort = getMaxSort(a38Id, eCatalogTypeInfo.getCatalogCode());
                            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
                            E01Z1 e01Z1 = new E01Z1();
                            org.springframework.beans.BeanUtils.copyProperties(e01Z1Vo, e01Z1);
                            e01Z1.setE01Z101B(eCatalogTypeInfo.getCatalogCode());
                            e01Z1.setE01Z101A(eCatalogTypeInfo.getCatalogValue());
                            e01Z1.setECatalogTypeId(eCatalogTypeInfo.getId());
                            e01Z1.setYjztps(0);
                            e01Z1.setE01Z104(sort);
                            if (com.hisun.util.StringUtils.isNotBlank(a38Id)) {
                                e01Z1.setA38(a38Service.getByPK(a38Id));
                            }
                            EntityWrapper.wrapperSaveBaseProperties(e01Z1, userLoginDetails);
                            save(e01Z1);
                        }
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getDate(E01Z1Vo e01Z1Vo){
        String e01Z117 = "";
        if(StringUtils.isNotEmpty(e01Z1Vo.getYear())){
            e01Z117 = e01Z1Vo.getYear();
            if(StringUtils.isNotEmpty(e01Z1Vo.getMonth())){
                if(e01Z1Vo.getMonth().length()==1&&Integer.parseInt(e01Z1Vo.getMonth())>0){
                    e01Z117 += "0"+e01Z1Vo.getMonth();
                }else {
                    e01Z117 += e01Z1Vo.getMonth();
                }
                if(StringUtils.isNotEmpty(e01Z1Vo.getDay())){
                    if(e01Z1Vo.getDay().length()==1&&Integer.parseInt(e01Z1Vo.getDay())>0){
                        e01Z117 += "0"+e01Z1Vo.getDay();
                    }else {
                        e01Z117 += e01Z1Vo.getDay();
                    }
                }
            }
        }
        return e01Z117;
    }


    public int saveFromGzslws(DataSource dataSource)throws Exception{
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        //处理了多少条
        int order = 0;
        Connection conn = dataSource.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        int count =0;
        List<Map<String, Object>> countList = queryRunner.query(conn,
                "select count(*) as count from e01z1 where e01z1.A_IS_DESTROY='0' and e01z1.PK_A38 in(" +
                        "select a38.PK_A38 from a38 where a38.A_STATE = '1' and a38.A_IS_DESTROY = '0' and a38.a3807b='001') " , new MapListHandler(),(Object[]) null);
        for (Iterator<Map<String, Object>> li = countList.iterator(); li.hasNext();) {
            Map<String, Object> m = li.next();
            for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                Map.Entry<String, Object> e = mi.next();
                Object value = e.getValue();
                count = ((BigDecimal)value).intValue();
            }
        }

        Map<String,Object> attMaps = getSavaAttMaps();

        //每次处理400条
        int dealCount = count/400;
        for(int i=0;i<=dealCount;i++){
            int num1 = i*400;
            int num2 = (i+1)*400;
            String sql = "select * from (select e01z1.*,rownum rn from e01z1 where e01z1.A_IS_DESTROY = '0' and e01z1.PK_A38 in(" +
                    "select a38.PK_A38 from a38 where a38.A_STATE = '1' and a38.A_IS_DESTROY = '0' and a38.a3807b='001') " +
                    "order by e01z1.PK_E01Z1) where rn >"+num1+" and rn<"+num2+" ";

            List<Map<String, Object>> list = queryRunner.query(conn, sql, new MapListHandler(),(Object[]) null);
            for (Iterator<Map<String, Object>> li = list.iterator(); li.hasNext();) {
                Map<String, Object> m = li.next();
                StringBuffer fields = new StringBuffer();
                fields.append("insert into e01z1 (");
                fields.append(" tombstone,tenant_id,create_user_id,create_user_name,create_date ");
                StringBuffer values = new StringBuffer();
                values.append(") values (");
                values.append(" 0 ");
                values.append(",'").append(userLoginDetails.getTenant().getId()).append("'")
                        .append(",'").append(userLoginDetails.getUser().getId()).append("'")
                        .append(",'").append(userLoginDetails.getUsername()).append("'")
                        .append(",").append("now()").append("");

                for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                    Map.Entry<String, Object> e = mi.next();
                    String key = e.getKey();
                    Object value = e.getValue()==null?"":e.getValue();

                    Iterator it = attMaps.entrySet().iterator();
                    boo:while (it.hasNext()) {
                        Map.Entry entry = (Map.Entry) it.next();
                        Object attKey = entry.getKey();
                        Object attValue = entry.getValue();
                        if(key.equalsIgnoreCase(attKey.toString())){
                            if("NAME_WORDCOUNT".equalsIgnoreCase(attKey.toString())){
                                fields.append("," + attValue);
                                values.append("," + value);
                            }else{
                                fields.append("," + attValue);
                                values.append(",'" + value + "'");
                            }
                            break boo;
                        }
                    }
                }
                values.append(")");
                List<Object> paramList = new ArrayList<Object>();
                this.e01Z1Dao.executeNativeBulk(fields.append(values).toString(),paramList);
                order++;
            }
        }

        DbUtils.close(conn);
        return order;
    }

    private Map<String,Object> getSavaAttMaps(){
        Map<String,Object> attMaps = new HashMap<String,Object>();
        attMaps.put("PK_E01Z1","id");			//材料主键
        attMaps.put("PK_A38","a38_id");              //外键，档案基本信息主键
        attMaps.put("E01Z111","e01z111");              //材料名称'
        attMaps.put("E01Z111_REMARK","e01z111_remark");         //材料名称备注'
        attMaps.put("E01Z117","e01z117");              //材料制成时间'
        attMaps.put("E01Z107","e01z107");              //扫描序号（单份材料的扫描序号）'
        attMaps.put("E01Z101B","e01z101b");            //材料类别字典代码'
        attMaps.put("E01Z101A"," e01z101a");             //材料类别字典内容
        attMaps.put("E01Z114","e01z114");              //材料页数'
        attMaps.put("SCAN_PAGES","smys");			     //扫描页数
        attMaps.put("E01Z124","e01z124");              //材料份数
        attMaps.put("E01Z207","e01z207");              //接收人姓名
        attMaps.put("E01Z204","e01z204");              //材料来处
        attMaps.put("E01Z201","e01z201");              //材料接收时间
        attMaps.put("E01Z231","e01z231");              //备注
        attMaps.put("UP_PAGES","yjztps");               //已加载图片数
        attMaps.put("E01Z104","e01z104");              //排序号

        return attMaps;
    }
}
