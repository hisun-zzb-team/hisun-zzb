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
import com.hisun.saas.zzb.dzda.mlcl.Constants;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;
import com.hisun.saas.zzb.dzda.mlcl.entity.EImages;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.saas.zzb.dzda.mlcl.service.EImagesService;
import com.hisun.saas.zzb.dzda.mlcl.vo.MlclAggregateVo;
import com.hisun.saas.zzb.dzda.mlcl.vo.MlclTreeNode;
import com.hisun.util.*;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

    private final static String DEFAULT_PHOTO = "/WEB-INF/images/tempNoPic.bmp";
    /**
     *
     * @param a38Id 档案主键
     * @param a0101 档案姓名
     * @param archiveId 档案材料字典ID
     * @param e01z1Id 档案材料ID
     * @param showType  浏览的位置  如果为refer则是   如果是viewApply则为查阅列表进入
     * @return
     * @throws GenericException
     */
    @RequiresLog(operateType = LogOperateType.QUERY,description = "查看档案:${a0101}")
    @RequestMapping("/ajax/viewMain/{a38Id}")
    public
    @ResponseBody ModelAndView viewMain(@PathVariable(value = "a38Id") String a38Id,String a0101,String archiveId,String e01z1Id,String showType,String myDirName) throws GenericException {
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
    public ModelAndView viewImg(String a38Id,String e01z1Id,String myDirName) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String storePath = getTpStorePath(a38Id);
        List<String> images = new ArrayList<String>();
        int imagesSize = 0;
        if(!e01z1Id.equals("")){
            E01Z1 e01z1 = new E01Z1();
            e01z1 = this.e01Z1Service.getByPK(e01z1Id);

            List<EImages> eImages = new ArrayList<EImages>();
            eImages = e01z1.getImages();

            for(EImages image : eImages){
                String jiamfilePath = image.getImgFilePath();//加密的图片路径
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
                images.add(jiamfilePath);
            }
         imagesSize = images.size();
        }

        map.put("imagesSize", imagesSize);
        map.put("images", images);
        map.put("a38Id", a38Id);
        map.put("e01z1Id", e01z1Id);
        map.put("myDirName", myDirName);
        return new ModelAndView("saas/zzb/dzda/mlcl/viewImg/viewImg",map);
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

    @RequestMapping("/ajax/typeAndE01z1Tree/{a38Id}")
    public
    @ResponseBody
    Map<String, Object> typeAndE01z1Tree(@PathVariable(value = "a38Id") String a38Id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
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
                String text = e01Z1.getE01Z111();
                String e01z117 = StringUtils.trimNull2Empty(e01Z1.getE01Z117());//制成时间
                int imagesCount = e01Z1.getYjztps();
                String title=e01Z1.getE01Z111();

                if(!e01z117.equals("")){
                    text = text +","+ e01z117;
                    title =title+"  制成时间："+e01z117;
                }
                if(imagesCount != 0){
                    text = text +","+ imagesCount;
                    title =title+"  图片数："+imagesCount;
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
    public HttpEntity<byte[]> showImages(String imgPath,String a38Id,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (StringUtils.isEmpty(imgPath)==false) {
            String zpRealPath = uploadBasePath +imgPath;
            File file = new File(zpRealPath);
            if (file.exists()) {
                DESUtil.getInstance(Constants.DATP_KEY).decrypt(file,response.getOutputStream());
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
}
