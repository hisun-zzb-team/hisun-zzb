/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.mlcl.controller;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.saas.sys.admin.dzda.entity.ECatalogTypeInfo;
import com.hisun.saas.sys.admin.dzda.service.ECatalogTypeService;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.log.LogOperateType;
import com.hisun.saas.sys.log.RequiresLog;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.dacy.entity.EApplyE01Z8;
import com.hisun.saas.zzb.dzda.dacy.entity.ECysq;
import com.hisun.saas.zzb.dzda.dacy.entity.EPopedomE01Z1Relation;
import com.hisun.saas.zzb.dzda.dacy.service.EApplyE01Z8Service;
import com.hisun.saas.zzb.dzda.dacy.service.ECysqService;
import com.hisun.saas.zzb.dzda.dacy.vo.EApplyE01Z8Vo;
import com.hisun.saas.zzb.dzda.mlcl.Constants;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;
import com.hisun.saas.zzb.dzda.mlcl.entity.EImages;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.saas.zzb.dzda.mlcl.service.EImagesService;
import com.hisun.saas.zzb.dzda.mlcl.vo.MlclAggregateVo;
import com.hisun.saas.zzb.dzda.mlcl.vo.MlclTreeNode;
import com.hisun.saas.zzb.dzda.util.PrintImage;
import com.hisun.util.*;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Rocky {rockwithyou@126.com}
 */

@Controller
@RequestMapping("/zzb/dzda/mlcl/images")
public class EImagesController extends BaseController {

    @Value("${sys.upload.absolute.path}")
    private String uploadBasePath;

    @Resource
    private ECatalogTypeService eCatalogTypeService;
    @Resource
    private A38Service a38Service;
    @Resource
    private E01Z1Service e01Z1Service;
    @Resource
    private EImagesService eImagesService;

    @Resource
    private EApplyE01Z8Service eApplyE01Z8Service;

    private final static String DEFAULT_PHOTO = "/WEB-INF/images/tempNoPic.bmp";
    /**
     *
     * @param a38Id 档案主键
     * @param a0101 档案姓名
     * @param archiveId 档案材料字典ID
     * @param e01z1Id 档案材料ID
     * @param showType  浏览的位置  如果为refer则是   如果是viewApply则为查阅列表进入
     * @param isManage  是否进入管理图片 true为进入管理界面
     * @return
     * @throws GenericException
     */
    @RequiresLog(operateType = LogOperateType.QUERY,description = "查看档案:${a0101}")
    @RequestMapping("/ajax/viewMain/{a38Id}")
    public
    @ResponseBody ModelAndView viewMain(@PathVariable(value = "a38Id") String a38Id,String a0101,String archiveId,String e01z1Id,String showType,String myDirName,String isManage,String isAddLog,String eApplyE01Z8Id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();

        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        java.util.Date date = new java.util.Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        String nowDate = df.format(date);
        if(myDirName==null || myDirName.equals("")) {
            myDirName = userLoginDetails.getUserid() + nowDate;
        }
        map.put("a38Id", a38Id);
        map.put("archiveId", archiveId);
        map.put("e01z1Id", e01z1Id);
        map.put("myDirName", myDirName);
        map.put("a0101", a0101);
        map.put("isManage", isManage);
        map.put("isAddLog",isAddLog);
        EApplyE01Z8 eApplyE01Z8 = new EApplyE01Z8();
        if(StringUtils.isNotBlank(eApplyE01Z8Id)){
             eApplyE01Z8 = eApplyE01Z8Service.getByPK(eApplyE01Z8Id);
        }
        EApplyE01Z8Vo vo = new EApplyE01Z8Vo();
        BeanUtils.copyProperties(eApplyE01Z8,vo);
        map.put("vo", vo);
        map.put("eApplyE01Z8Id",eApplyE01Z8Id);
        return new ModelAndView("saas/zzb/dzda/mlcl/viewImg/viewImgManage",map);
    }
    private String getTpStorePath(String a38Id) {
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        return Constants.DATP_STORE_PATH
                + userLoginDetails.getTenantId() + File.separator
                + a38Id.substring(a38Id.length() - 1, a38Id.length()) + File.separator
                + a38Id + File.separator;
    }
    @RequestMapping(value = "/ajax/viewImg")
    public ModelAndView viewImg(String a38Id,String e01z1Id,String myDirName,String isManage,String imageIndex) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String storePath = getTpStorePath(a38Id);
        List<EImages> images = new ArrayList<EImages>();
        int imagesSize = 0;
        List<EImages> eImages = new ArrayList<EImages>();
        if(imageIndex==null|| imageIndex.equals("")){
            imageIndex = "1";
        }
        if(!e01z1Id.equals("")){
            E01Z1 e01z1 = new E01Z1();
            e01z1 = this.e01Z1Service.getByPK(e01z1Id);


            eImages = e01z1.getImages();

            for(EImages image : eImages) {

//                String jiamfilePath = image.getImgFilePath();//加密的图片路径
//                String jianmfilePath = "";//解密的图片路径
//                String dirPath = uploadBasePath +storePath+myDirName;
//                File storeDir = new File(dirPath);
//                if (storeDir.exists() == false) {
//                    storeDir.mkdirs();
//                }
//                jiamfilePath = uploadBasePath+jiamfilePath;
//                String fileName ="";
//                if(image.getImgFilePath().lastIndexOf("\\")!=-1){
//                    fileName = image.getImgFilePath().substring(image.getImgFilePath().lastIndexOf("\\")+1);
//                }else{
//                    fileName = image.getImgFilePath().substring(image.getImgFilePath().lastIndexOf("/")+1);
//                }
//                fileName = image.getE01z1().getE01Z101B()+fileName;//在入库的图片名前加入分类的编码
//                String showFilePath = myDirName+ File.separator+fileName+".jpg";
//                jianmfilePath =dirPath + File.separator+fileName+".jpg";

//                File jianmfile = new File(jianmfilePath);//解密图片 检查是否已经解密，如果已经解密则不进行解密
//                if(jianmfile.exists()== false){
//                    DESUtil.decrypt(new File(jiamfilePath),new File(jianmfilePath));
//                }
                if (image.getImgNo().toString().equals(imageIndex)){
//                    images.add(image.getId() + ";" + image.getImgNo());
                    images.add(image);
                }
            }
            imagesSize = eImages.size();
        }

        map.put("imagesSize", imagesSize);
//        map.put("eImages", eImages);
        map.put("images", images);
        map.put("a38Id", a38Id);
        map.put("e01z1Id", e01z1Id);
        map.put("myDirName", myDirName);
        map.put("isManage", isManage);
        return new ModelAndView("saas/zzb/dzda/mlcl/viewImg/viewImg",map);
    }

    @RequestMapping(value="/ajax/downImg")
    @RequiresLog(operateType = LogOperateType.DOWNLOAD,description = "下载:${damc}的档案:${clmc}的第:${imgNo}张图片")
    public void downImg(String imgId,HttpServletRequest req, HttpServletResponse resp,String imgNo,String clmc,String damc) throws Exception{
        imgId = StringUtils.trim(imgId);
        EImages img = this.eImagesService.getByPK(imgId);

        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and("e01z1.id=:e01z1Id","e01z1Id",img.getE01z1().getId()));
        query.add(CommonRestrictions.and("imgNo<:imgNo","imgNo",img.getImgNo()));
        List<EImages> eImages = eImagesService.list(query,null);
        String imgCode = "";
        if(eImages.size()==0){
            imgCode = getImgCode(img.getE01z1());
        }
        String imgPath = img.getImgFilePath();
        if (StringUtils.isEmpty(imgPath)==false) {
            String zpRealPath = uploadBasePath +imgPath;
            File file = new File(zpRealPath);
            if (file.exists()) {
                OutputStream output=resp.getOutputStream();
                String fileName = file.getPath().substring(file.getPath().lastIndexOf(File.separator)+1)+".jpg";
                resp.setContentType("multipart/form-data");
                //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
                resp.setHeader("Content-Disposition", "attachment;fileName="+encode(fileName));
                DESUtil.getInstance(Constants.DATP_KEY).decrypt(file,output,imgCode,100000,1);
            }
        }
    }
    private String encode(String filename) throws UnsupportedEncodingException {
        if (WebUtil.getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            filename = URLEncoder.encode(filename, "UTF-8");
        } else {
            filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
        }
        return filename;
    }


    @RequestMapping(value="/ajax/printImg")
    @RequiresLog(operateType = LogOperateType.PRINT,description = "打印:${damc}的档案:${clmc}的第:${imgNo}张图片")
    public @ResponseBody Map<String, Object> printImg(String imgId,String imgNo,String clmc,String damc) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            imgId = StringUtils.trim(imgId);
            EImages img = this.eImagesService.getByPK(imgId);

            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and("e01z1.id=:e01z1Id","e01z1Id",img.getE01z1().getId()));
            query.add(CommonRestrictions.and("imgNo<:imgNo","imgNo",img.getImgNo()));
            List<EImages> eImages = eImagesService.list(query,null);
            String imgCode = "";
            if(eImages.size()==0){
                imgCode = getImgCode(img.getE01z1());
            }
            String imgPath = img.getImgFilePath();
            if (StringUtils.isEmpty(imgPath)==false) {
                String zpRealPath = uploadBasePath +imgPath;
                File file = new File(zpRealPath);
                if (file.exists()) {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    String fileName = file.getPath().substring(file.getPath().lastIndexOf(File.separator)+1)+".jpg";
                    DESUtil.getInstance(Constants.DATP_KEY).decrypt(file,outputStream,imgCode,100000,1);
//                    ByteArrayOutputStream  baos=(ByteArrayOutputStream) output;
                    ByteArrayInputStream swapStream = new ByteArrayInputStream(outputStream.toByteArray());

                    new PrintImage().drawImage("001.jpg", 1,swapStream);
                }
            }
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }
    //删除临时查看的解密文件
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/delete/jmImages")
    public @ResponseBody Map<String, Object> delJmImages(
           String a38Id,String myDirName) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String path = uploadBasePath+getTpStorePath(a38Id)+myDirName;
            File file = new File(path);
            File newFile = null;
            //删除图片
            if(file.exists()){
                File[] files = file.listFiles();
                for(int i=0;i<files.length;i++){
                    String fileName = files[i].getName();
                    if(!files[i].isDirectory() && fileName.lastIndexOf(".jpg") != -1){
                        newFile = new File(path+"/"+fileName);
                        newFile.delete();
                    }
                }
            }else{
                path=uploadBasePath+File.separator+a38Id+"/"+myDirName;
                file = new File(path);
                newFile = null;
                //删除图片
                if(file.exists()){
                    File[] files = file.listFiles();
                    for(int i=0;i<files.length;i++){
                        String fileName = files[i].getName();
                        if(!files[i].isDirectory() && fileName.lastIndexOf(".jpg") != -1){
                            newFile = new File(path+File.separator+fileName);
                            newFile.delete();
                        }
                    }
                }
            }
            file.delete();
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }

    @Resource
    private ECysqService eCysqService;

    @RequestMapping("/ajax/typeAndE01z1Tree/{a38Id}")
    public
    @ResponseBody
    Map<String, Object> typeAndE01z1Tree(@PathVariable(value = "a38Id") String a38Id,String eApplyE01Z8Id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            boolean isFilter = false;
            List<EPopedomE01Z1Relation> popedomE01Z1Relations = null;
            if(eApplyE01Z8Id!=null && !eApplyE01Z8Id.equals("")){
                CommonConditionQuery query = new CommonConditionQuery();
                query.add(CommonRestrictions.and("applyE01Z8.id = :applyE01Z8 ", "applyE01Z8", eApplyE01Z8Id));
                ECysq eCysq = eCysqService.list(query,null).get(0);
               // EApplyE01Z8 entity = eApplyE01Z8Service.getByPK(eApplyE01Z8Id);
                if(eCysq.getSqclfw().equals("1")){
                    isFilter = true;
                    popedomE01Z1Relations = eCysq.getPopedomE01Z1Relations();
                }
            }
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            CommonConditionQuery query = new CommonConditionQuery();
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("sort"));
            List<ECatalogTypeInfo> eCatalogTypeInfos = eCatalogTypeService.list(query, orderBy);
            A38 a38 = a38Service.getByPK(a38Id);
            CommonConditionQuery query1 = new CommonConditionQuery();
            query1.add(CommonRestrictions.and("a38.id=:a38Id","a38Id",a38Id));
            CommonOrderBy orderBy1 = new CommonOrderBy();
            orderBy1.add(CommonOrder.asc("e01z104"));
            List<E01Z1> e01Z1s = e01Z1Service.list(query1, orderBy1);
            List<MlclTreeNode> treeNodes = new ArrayList<MlclTreeNode>();
            MlclTreeNode childTreeNode = null;
            for (ECatalogTypeInfo eCatalogTypeInfo : eCatalogTypeInfos) {
                childTreeNode = new MlclTreeNode();
                childTreeNode.setId(eCatalogTypeInfo.getId());
                childTreeNode.setName(eCatalogTypeInfo.getCatalogValue());
                childTreeNode.setKey(eCatalogTypeInfo.getCatalogCode());
                childTreeNode.setNodeType("dir");
                childTreeNode.setOpen(true);
                if (eCatalogTypeInfo.getParent() == null) {
                    childTreeNode.setpId(a38.getId());
                } else {
                    childTreeNode.setpId(eCatalogTypeInfo.getParent().getId());
                }
                treeNodes.add(childTreeNode);
            }

            for (E01Z1 e01Z1 : e01Z1s) {
                boolean isAdd = false;
                if(isFilter == true){
                    if(popedomE01Z1Relations!=null && popedomE01Z1Relations.size()>0){
                        boo:for(EPopedomE01Z1Relation popedomE01Z1Relation : popedomE01Z1Relations){
                            if(popedomE01Z1Relation.getE01z1Id().equals(e01Z1.getId())){
                                isAdd = true;
                                break boo;
                            }
                        }
                    }
                }else{
                    isAdd = true;
                }
                if(isAdd == true) {
                    String text = getImgCode(e01Z1)+"、"+e01Z1.getE01Z111();
                    String e01z117 = StringUtils.trimNull2Empty(e01Z1.getE01Z117());//制成时间
                    int imagesCount = e01Z1.getYjztps();
                    String title = e01Z1.getE01Z111();

                    if (!e01z117.equals("")) {
                        text = text + "," + e01z117;
                        title = title + "  制成时间：" + e01z117;
                    }
                    if (imagesCount != 0) {
                        text = text + "," + imagesCount;
                        title = title + "  图片数：" + imagesCount;
                    }
                    childTreeNode = new MlclTreeNode();
                    childTreeNode.setId(e01Z1.getId());
                    childTreeNode.setName(text);
                    childTreeNode.setDescription(title);
                    DecimalFormat decimalFormat = new DecimalFormat("00");
                    childTreeNode.setKey(decimalFormat.format(e01Z1.getE01Z104()));
                    childTreeNode.setpId(e01Z1.getECatalogTypeId());
                    childTreeNode.setNodeType("cl");

                    treeNodes.add(childTreeNode);
                }
            }
            map.put("success", true);
            map.put("data", treeNodes);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e, e);
            map.put("success", false);
        }
        return map;
    }

    @RequestMapping("/showImages")
    public HttpEntity<byte[]> showImages(String imgId,String a38Id,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        imgId = StringUtils.trim(imgId);
        if(imgId==null || imgId.equals("")){
            return null;
        }
        CommonConditionQuery query = new CommonConditionQuery();
        EImages img = this.eImagesService.getByPK(imgId);
        query.add(CommonRestrictions.and("e01z1.id=:e01z1Id","e01z1Id",img.getE01z1().getId()));
        query.add(CommonRestrictions.and("imgNo<:imgNo","imgNo",img.getImgNo()));
        List<EImages> eImages = eImagesService.list(query,null);
        String imgCode = "";
        if(eImages.size()==0){
            imgCode = getImgCode(img.getE01z1());
        }
        String imgPath = img.getImgFilePath();
        if (StringUtils.isEmpty(imgPath)==false) {
            String zpRealPath = uploadBasePath +imgPath;
            File file = new File(zpRealPath);
            if (file.exists()) {
                DESUtil.getInstance(Constants.DATP_KEY).decrypt(file,response.getOutputStream(),imgCode,100000,1);
                response.setContentType(MediaType.IMAGE_JPEG_VALUE);
                return new HttpEntity(HttpStatus.OK);
            } else {
                //为空或者没有返回默认图片
                File defaultfile = new File(request.getServletContext().getRealPath(DEFAULT_PHOTO));
                FileInputStream fis = new FileInputStream(defaultfile);
                StreamUtils.copy(fis, response.getOutputStream());
                response.setContentType(MediaType.IMAGE_PNG_VALUE);
                return new HttpEntity(HttpStatus.OK);
            }
        } else {
            //为空或者没有返回默认图片
            File defaultfile = new File(request.getServletContext().getRealPath(DEFAULT_PHOTO));
            FileInputStream fis = new FileInputStream(defaultfile);
            StreamUtils.copy(fis, response.getOutputStream());
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            return new HttpEntity(HttpStatus.OK);
        }
    }

    public String getImgCode (E01Z1 e01Z1){
        String str = "";
        String e01Z101B = e01Z1.getE01Z101B();
        Integer code = Integer.parseInt(e01Z101B);
        int x = code/10;
        int y = code%10;
        str +=x;
        if(y>0){
            str += "-" + y;
        }
        str += "-" + e01Z1.getE01Z104();
        return str;
    }

    @RequiresLog(operateType = LogOperateType.DELETE,description = "删除图片:${id}")
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/delete/{id}")
    public @ResponseBody Map<String, Object> delete(
            @PathVariable("id") String id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            EImages eImages = this.eImagesService.getByPK(id);
            this.eImagesService.deleteEImages(eImages);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }

    @RequiresLog(operateType = LogOperateType.DELETE,description = "调整图片顺序:${id}")
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/updateImgNo/{id}")
    public @ResponseBody Map<String, Object> updateImgNo(
            @PathVariable("id") String id,String newImgNo) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            EImages eImages = this.eImagesService.getByPK(id);
            int oldImgNo = eImages.getImgNo();
            eImages.setImgNo(Integer.parseInt(newImgNo));
            this.eImagesService.updateEImagesImgNo(eImages,oldImgNo);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }

    /**
     * uploadType 上传方式 frist表示插入首页，up表示插入上一页 down表示下一页 end表示尾页
     * @param e01z1Id
     * @param curImgNo 操作的图片排序
     * @param uploadType uploadType 上传方式 frist表示插入首页，up表示插入上一页 down表示下一页 end表示尾页
     * @param tpFile
     * @return
     * @throws GenericException
     */
    @RequiresLog(operateType = LogOperateType.DELETE,description = "上传单张图片:${id}")
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/uploadImg")
    public @ResponseBody Map<String, Object> uploadImg(
            String e01z1Id,String curImgNo,String uploadType,@RequestParam(value="tpFile",required = false) MultipartFile tpFile) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            int maxImgNo = this.eImagesService.getMaxImgNo(e01z1Id);
            int insertImgNo = maxImgNo;
            if(uploadType.equals("frist")){
                insertImgNo = 1;
            }else if(uploadType.equals("up")){
                insertImgNo = Integer.parseInt(curImgNo);
            }else if(uploadType.equals("down")){
                insertImgNo = Integer.parseInt(curImgNo)+1;
            }else if(uploadType.equals("end")){
                insertImgNo =maxImgNo;
            }
            E01Z1 e01Z1 = this.e01Z1Service.getByPK(e01z1Id);
            String a38Id = e01Z1.getA38().getId();
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            String storeRealPath = uploadBasePath + getTpStorePath(a38Id);//正式目录
            File storeRealPathFile = new File(storeRealPath);
            if (storeRealPathFile.exists() == false) {
                storeRealPathFile.mkdirs();
            }
            int  e01z104 = e01Z1.getE01Z104();
            String newE01z104 ="";
            if (e01z104 < 10) {
                newE01z104 = "0" + e01z104;
            } else {
                newE01z104 = e01z104 + "";
            }
            String imgFilePath = "";
            if (tpFile != null && !tpFile.isEmpty()) {
                imgFilePath = storeRealPath+e01Z1.getE01Z101B()+"."+e01Z1.getE01Z101A()+File.separator+newE01z104+maxImgNo+tpFile.getOriginalFilename().toLowerCase();
                String encryptFilePath = storeRealPath+e01Z1.getE01Z101B()+"."+e01Z1.getE01Z101A()+File.separator+newE01z104+insertImgNo;
                File imgFile = new File(imgFilePath);
                FileOutputStream fosGbrmspbZip = new FileOutputStream(imgFile);
                fosGbrmspbZip.write(tpFile.getBytes());
                fosGbrmspbZip.flush();
                fosGbrmspbZip.close();

                EImages eImages = new EImages();
                eImages.setImgFilePath(encryptFilePath.substring(uploadBasePath.length(), encryptFilePath.length()));
                eImages.setE01z1(e01Z1);
                eImages.setImgNo(insertImgNo);
                this.eImagesService.saveEImages(eImages,uploadType,maxImgNo,imgFile,encryptFilePath);
            }
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }


}
