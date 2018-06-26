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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author Rocky {rockwithyou@126.com}
 */

@Controller
@RequestMapping("/zzb/dzda/mlcl/tpcl")
public class DatpDealController extends BaseController {

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

    @RequestMapping(value = "/ajax/index/{a38Id}")
    public ModelAndView index(@PathVariable(value = "a38Id") String a38Id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a38Id", a38Id);
        return new ModelAndView("saas/zzb/dzda/mlcl/jztp/index", map);
    }


    @RequestMapping("/ajax/tree/{a38Id}")
    public
    @ResponseBody
    Map<String, Object> tree(@PathVariable(value = "a38Id") String a38Id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();

            CommonConditionQuery query = new CommonConditionQuery();
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("sort"));
            List<ECatalogTypeInfo> eCatalogTypeInfos = eCatalogTypeService.list(query, orderBy);
            A38 a38 = a38Service.getByPK(a38Id);
            CommonConditionQuery query1 = new CommonConditionQuery();
            query1.add(CommonRestrictions.and("a38.id=:a38Id", "a38Id", a38Id));
            CommonOrderBy orderBy1 = new CommonOrderBy();
            orderBy1.add(CommonOrder.asc("e01z104"));
            List<E01Z1> e01Z1s = e01Z1Service.list(query1, orderBy1);
            List<MlclTreeNode> treeNodes = new ArrayList<MlclTreeNode>();
            MlclTreeNode treeNode = new MlclTreeNode();
            treeNode.setId(a38.getId());
            treeNode.setName(a38.getA0101());
            treeNode.setNodeType("a38");
            treeNode.setOpen(true);
            treeNodes.add(treeNode);
            MlclTreeNode childTreeNode = null;
            for (ECatalogTypeInfo eCatalogTypeInfo : eCatalogTypeInfos) {
                childTreeNode = new MlclTreeNode();
                childTreeNode.setId(eCatalogTypeInfo.getId());
                childTreeNode.setName(eCatalogTypeInfo.getCatalogCode() + "." + eCatalogTypeInfo.getCatalogValue());
                childTreeNode.setKey(eCatalogTypeInfo.getCatalogCode());
                childTreeNode.setOpen(true);
                childTreeNode.setNodeType("dir");
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
                int imagesCount = e01Z1.getE01Z114()==null?0:e01Z1.getE01Z114();
                String title = e01Z1.getE01Z111();

                if (!e01z117.equals("")) {
                    text = text + "," + e01z117;
                    title = title + " 制成时间：" + e01z117;
                }
                if (imagesCount != 0) {
                    text = text + "," + imagesCount;
                    title = title + " 材料页数：" + imagesCount;
                }
                childTreeNode = new MlclTreeNode();
                childTreeNode.setId(e01Z1.getId());
                childTreeNode.setName(text);
                childTreeNode.setDescription(title);

                DecimalFormat decimalFormat = new DecimalFormat("00");
                childTreeNode.setKey(decimalFormat.format(e01Z1.getE01Z104()));
                childTreeNode.setpId(this.eCatalogTypeService.getECatalogTypeInfoByCatalogCode(e01Z1.getE01Z101B()).getId());
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


    @RequestMapping("/mlclAggregate/{a38Id}")
    public
    @ResponseBody
    Map<String, Object> mlclAggregate(@PathVariable(value = "a38Id") String a38Id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            A38 a38 = a38Service.getByPK(a38Id);
            CommonConditionQuery query1 = new CommonConditionQuery();
            query1.add(CommonRestrictions.and("a38.id=:a38Id", "a38Id", a38Id));
            CommonOrderBy orderBy1 = new CommonOrderBy();
            orderBy1.add(CommonOrder.asc("e01z104"));
            List<E01Z1> e01Z1s = e01Z1Service.list(query1, orderBy1);
            List<MlclAggregateVo> mlclAggregateVos = new ArrayList<>();
            MlclAggregateVo mlclAggregateVo = null;
            for (E01Z1 e01Z1 : e01Z1s) {
                mlclAggregateVo = new MlclAggregateVo();
                mlclAggregateVo.setDirCode(e01Z1.getE01Z101B());
                DecimalFormat decimalFormat = new DecimalFormat("00");
                mlclAggregateVo.setNameCode(decimalFormat.format(e01Z1.getE01Z104()));
                mlclAggregateVo.setCount(e01Z1.getE01Z114());
                mlclAggregateVo.setFileName(e01Z1.getE01Z111());
                mlclAggregateVos.add(mlclAggregateVo);
            }

            map.put("success", true);
            map.put("mlclAggregateJson", JacksonUtil.nonEmptyMapper().toJson(mlclAggregateVos));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e, e);
            map.put("success", false);
        }
        return map;
    }

    @RequestMapping("/mlclAggregateInit/{a38Id}")
    public
    @ResponseBody
    Map<String, Object> mlclAggregateInit(@PathVariable(value = "a38Id") String a38Id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            A38 a38 = a38Service.getByPK(a38Id);
            CommonConditionQuery query1 = new CommonConditionQuery();
            query1.add(CommonRestrictions.and("a38.id=:a38Id", "a38Id", a38Id));
            CommonOrderBy orderBy1 = new CommonOrderBy();
            orderBy1.add(CommonOrder.asc("e01z104"));
            List<E01Z1> e01Z1s = e01Z1Service.list(query1, orderBy1);
            MlclAggregateVo mlclAggregateVo = null;
            int imgCount=0;
            for (E01Z1 e01Z1 : e01Z1s) {
                imgCount = imgCount+e01Z1.getE01Z114();
            }

            map.put("success", true);
            map.put("imgCount", imgCount);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e, e);
            map.put("success", false);
        }
        return map;
    }



    @RequestMapping("/e01z1/mlclAggregate/{e01z1Id}")
    public
    @ResponseBody
    Map<String, Object> mlclAggregate4E01z1(@PathVariable(value = "e01z1Id") String e01z1Id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            E01Z1 e01Z1 = this.e01Z1Service.getByPK(e01z1Id);
            List<MlclAggregateVo> mlclAggregateVos = new ArrayList<>();
            MlclAggregateVo mlclAggregateVo = new MlclAggregateVo();
            mlclAggregateVo.setDirCode(e01Z1.getE01Z101B());
            DecimalFormat decimalFormat = new DecimalFormat("00");
            mlclAggregateVo.setNameCode(decimalFormat.format(e01Z1.getE01Z104()));
            mlclAggregateVo.setCount(e01Z1.getE01Z114());
            mlclAggregateVo.setFileName(e01Z1.getE01Z111());
            mlclAggregateVos.add(mlclAggregateVo);
            map.put("success", true);
            map.put("mlclAggregateJson", JacksonUtil.nonEmptyMapper().toJson(mlclAggregateVos));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e, e);
            map.put("success", false);
        }
        return map;
    }


    @RequestMapping("/ajax/list/{a38Id}")
    public ModelAndView list(@PathVariable(value = "a38Id") String a38Id, HttpServletRequest request) throws GenericException {
        Map<String, Object> map = Maps.newHashMap();
        String currentNodeId = StringUtils.trimNull2Empty(request.getParameter("currentNodeId"));
        String currentNodeName = StringUtils.trimNull2Empty(request.getParameter("currentNodeName"));
        String currentNodeParentId = StringUtils.trimNull2Empty(request.getParameter("currentNodeParentId"));//取得当前树节点的父ID属性
        try {
            A38 a38 = a38Service.getByPK(a38Id);

            map.put("maxFileSize", Constants.DATP_MAX_FILE_SIZE);
            map.put("currentNodeId", currentNodeId);
            map.put("currentNodeName", currentNodeName);
            map.put("currentNodeParentId", currentNodeParentId);
            map.put("a38Id", a38Id);
            map.put("a0101", a38.getA0101());
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
        }
        return new ModelAndView("saas/zzb/dzda/mlcl/jztp/listZipfile", map);
    }

    @RequestMapping("/ajax/init/list/{a38Id}")
    public ModelAndView initList(@PathVariable(value = "a38Id") String a38Id, HttpServletRequest request) throws GenericException {
        Map<String, Object> map = Maps.newHashMap();
//        String currentNodeId = StringUtils.trimNull2Empty(request.getParameter("currentNodeId"));
//        String currentNodeName = StringUtils.trimNull2Empty(request.getParameter("currentNodeName"));
//        String currentNodeParentId = StringUtils.trimNull2Empty(request.getParameter("currentNodeParentId"));//取得当前树节点的父ID属性
        try {
            A38 a38 = a38Service.getByPK(a38Id);

            map.put("maxFileSize", Constants.DATP_MAX_FILE_SIZE);
            map.put("currentNodeId", a38Id);
            map.put("currentNodeName", "");
            map.put("currentNodeParentId", "");
            map.put("a38Id", a38Id);
            map.put("a0101", a38.getA0101());
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
        }
        return new ModelAndView("saas/zzb/dzda/mlcl/jztp/listZipfileInit", map);
    }

    @RequestMapping("/ajax/list/e01z1/{e01z1Id}")
    public ModelAndView list4E01ez1(@PathVariable(value = "e01z1Id") String e01z1Id, HttpServletRequest request) throws GenericException {
        Map<String, Object> map = Maps.newHashMap();
        String currentNodeId = StringUtils.trimNull2Empty(request.getParameter("currentNodeId"));
        String currentNodeName = StringUtils.trimNull2Empty(request.getParameter("currentNodeName"));
        String currentNodeParentId = StringUtils.trimNull2Empty(request.getParameter("currentNodeParentId"));//取得当前树节点的父ID属性
        try {
            E01Z1 e01Z1 = e01Z1Service.getByPK(e01z1Id);

            map.put("maxFileSize", Constants.DATP_MAX_FILE_SIZE);
            map.put("currentNodeId", currentNodeId);
            map.put("currentNodeName", currentNodeName);
            map.put("currentNodeParentId", currentNodeParentId);
            map.put("e01z1Id", e01z1Id);
            map.put("e01Z111", e01Z1.getE01Z111());
            map.put("a0101", e01Z1.getA38().getA0101());
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
        }
        return new ModelAndView("saas/zzb/dzda/mlcl/jztp/listZipfile4E01z1", map);
    }


    @RequiresLog(operateType = LogOperateType.SAVE, description = "加载图片:${a0101}")
    @RequestMapping(value = "/save/{a38Id}", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> save(@RequestParam(value = "zipfile", required = false) MultipartFile file,
                             @PathVariable(value = "a38Id") String a38Id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        String storeTmpRealPath = uploadBasePath + getTpStoreTmpPath(a38Id);//临时目录
        String storeRealPath = uploadBasePath + getTpStorePath(a38Id);//正式目录
        try {
            A38 a38 = this.a38Service.getByPK(a38Id);
            File storeRealPathFile = new File(storeRealPath);
            if (storeRealPathFile.exists() == false) {
                storeRealPathFile.mkdirs();
            } else {
                //如果存在,现将其移到临时目录下
                FileUtils.moveDirectory(storeRealPathFile, new File(storeTmpRealPath));
                //重新创建存储目录
                storeRealPathFile.mkdirs();
            }
            //先将zip文件存入目录
            String zipStoreRealPath = storeRealPath + UUIDUtil.getUUID() + ".zip";
            File zipFile = new File(zipStoreRealPath);
            FileOutputStream zipfos = new FileOutputStream(zipFile);
            InputStream zipis = file.getInputStream();
            byte[] buffer = new byte[8192];
            int length;
            while ((length = zipis.read(buffer)) != -1) {
                zipfos.write(buffer, 0, length);
            }
            zipfos.flush();
            zipfos.close();
            zipis.close();
            //解压zip
            CompressUtil.unzip(zipStoreRealPath, storeRealPath);
            FileUtils.deleteQuietly(zipFile);
            //写入eimages
            List<File> files = FileUtil.listFilesOrderByName(storeRealPathFile);
            if (checkTpDirByCataolog(files)) {
                eImagesService.saveEImagesByJztp(a38, storeRealPathFile);
                //删除临时文件
                File storeTmpRealPathFile = new File(storeTmpRealPath);
                if (storeTmpRealPathFile.exists()) {
                    FileUtils.deleteDirectory(storeTmpRealPathFile);
                }
                map.put("success", true);
                map.put("message", "保存成功!");
            } else {
                //删除已上传文件
                FileUtils.deleteDirectory(storeRealPathFile);
                //还原正式文件
                File storeTmpRealPathFile = new File(storeTmpRealPath);
                if (storeTmpRealPathFile.exists()) {
                    //将临时文件还原至正式目录
                    FileUtils.moveDirectory(storeTmpRealPathFile, storeRealPathFile);
                }
                map.put("success", false);
                map.put("message", "目录结构错误!");
            }
        } catch (Exception e) {
            try {
                //将正式目录数据清除
                File storeRealPathFile = new File(storeRealPath);
                if (storeRealPathFile.exists()) {
                    FileUtils.deleteDirectory(storeRealPathFile);
                }
                //将临时文件还原至正式目录
                File storeTmpRealPathFile = new File(storeTmpRealPath);
                if (storeTmpRealPathFile.exists()) {
                    FileUtils.moveDirectory(storeTmpRealPathFile, storeRealPathFile);
                }
            } catch (Exception e1) {

            }
            logger.error(e);
            throw new GenericException(e);
        }finally {

        }
        return map;
    }

    @RequiresLog(operateType = LogOperateType.SAVE, description = "加载图片:${a0101}")
    @RequestMapping(value = "/saveInit/{a38Id}", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> saveInit(@RequestParam(value = "zipfile", required = false) MultipartFile file,
                             @PathVariable(value = "a38Id") String a38Id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        String storeTmpRealPath = uploadBasePath + getTpStoreTmpPath(a38Id);//临时目录
        String storeRealPath = uploadBasePath + getTpStorePath(a38Id);//正式目录
        try {
            A38 a38 = this.a38Service.getByPK(a38Id);
            File storeRealPathFile = new File(storeRealPath);
            if (storeRealPathFile.exists() == false) {
                storeRealPathFile.mkdirs();
            } else {
                //如果存在,现将其移到临时目录下
                FileUtils.moveDirectory(storeRealPathFile, new File(storeTmpRealPath));
                //重新创建存储目录
                storeRealPathFile.mkdirs();
            }
            //先将zip文件存入目录
            String zipStoreRealPath = storeRealPath + UUIDUtil.getUUID() + ".zip";
            File zipFile = new File(zipStoreRealPath);
            FileOutputStream zipfos = new FileOutputStream(zipFile);
            InputStream zipis = file.getInputStream();
            byte[] buffer = new byte[8192];
            int length;
            while ((length = zipis.read(buffer)) != -1) {
                zipfos.write(buffer, 0, length);
            }
            zipfos.flush();
            zipfos.close();
            zipis.close();
            //解压zip
            CompressUtil.unzip(zipStoreRealPath, storeRealPath);
            FileUtils.deleteQuietly(zipFile);
            //写入eimages
            eImagesService.saveEImagesByJztpInit(a38, storeRealPathFile);
            //删除临时文件
            File storeTmpRealPathFile = new File(storeTmpRealPath);
            if (storeTmpRealPathFile.exists()) {
                FileUtils.deleteDirectory(storeTmpRealPathFile);
            }
            map.put("success", true);
            map.put("message", "保存成功!");
        } catch (Exception e) {
            try {
                //将正式目录数据清除
                File storeRealPathFile = new File(storeRealPath);
                if (storeRealPathFile.exists()) {
                    FileUtils.deleteDirectory(storeRealPathFile);
                }
                //将临时文件还原至正式目录
                File storeTmpRealPathFile = new File(storeTmpRealPath);
                if (storeTmpRealPathFile.exists()) {
                    FileUtils.moveDirectory(storeTmpRealPathFile, storeRealPathFile);
                }
            } catch (Exception e1) {

            }
            logger.error(e);
            throw new GenericException(e);
        }finally {

        }
        return map;
    }


    @RequiresLog(operateType = LogOperateType.SAVE, description = "加载图片：a0101,e01Z111")
    @RequestMapping(value = "/e01z1/save/{e01z1Id}", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> saveE01z1Tp(@RequestParam(value = "zipfile", required = false) MultipartFile file,
                                    @PathVariable(value = "e01z1Id") String e01z1Id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        E01Z1 e01Z1 = this.e01Z1Service.getByPK(e01z1Id);
        String storeTmpRealPath = uploadBasePath + getTpStorePath(e01Z1.getA38().getId()) + UUIDUtil.getUUID() + File.separator;//原文件临时目录
        String storeZipRealPath = uploadBasePath + getTpStorePath(e01Z1.getA38().getId()) + UUIDUtil.getUUID() + File.separator;//上传zip文件临时目录
        ECatalogTypeInfo eCatalogTypeInfo = this.eCatalogTypeService.getECatalogTypeInfoByCatalogCode(e01Z1.getE01Z101B());
        String realStorePath = uploadBasePath + getTpStorePath(e01Z1.getA38().getId()) + eCatalogTypeInfo.getCatalogCode()
                + "." + eCatalogTypeInfo.getCatalogValue() + File.separator;
        try {
            File storeZipRealPathFile = new File(storeZipRealPath);
            storeZipRealPathFile.mkdirs();
            File storeTmpRealPathFile = new File(storeTmpRealPath);
            storeTmpRealPathFile.mkdirs();
            File realStorePathFile = new File(realStorePath);
            if(!realStorePathFile.exists()){
                realStorePathFile.mkdirs();
            }
            //先将zip文件存入目录
            String zipStoreRealPath = storeZipRealPath + UUIDUtil.getUUID() + ".zip";
            File zipFile = new File(zipStoreRealPath);
            FileOutputStream zipfos = new FileOutputStream(zipFile);
            InputStream zipis = file.getInputStream();
            byte[] buffer = new byte[8192];
            int length;
            while ((length = zipis.read(buffer)) != -1) {
                zipfos.write(buffer, 0, length);
            }
            zipfos.flush();
            zipfos.close();
            zipis.close();
            //解压zip
            CompressUtil.unzip(zipStoreRealPath, storeZipRealPath);
            FileUtils.deleteQuietly(zipFile);
            //将原有图片存放到临时目录
            List<EImages> eImages = e01Z1.getImages();
            if(eImages!=null){
                for (EImages eImage : eImages) {
                    File image = new File(uploadBasePath + eImage.getImgFilePath());
                    FileUtils.moveFileToDirectory(image, storeTmpRealPathFile, false);
                }
            }
            //写入新的Images
            List<File> tpDirFiles = FileUtil.listFilesOrderByName(storeZipRealPathFile);
            if (checkTpDirByCataolog(tpDirFiles)) {
                eImagesService.saveEImagesByJztp(e01Z1, storeZipRealPathFile);
                map.put("success", true);
                map.put("message", "保存成功!");
            } else {
                //将临时文件还原至正式目录
                if (storeTmpRealPathFile.exists()) {
                    List<File> tmpFiles = FileUtil.listFilesOrderByName(storeTmpRealPathFile);
                    for(File tmpFile :tmpFiles){
                        if (Arrays.asList(Constants.EXCLUDE_FILE_AND_DIR).contains(tmpFile.getName())) {
                            continue;
                        }
                        FileUtils.copyFileToDirectory(tmpFile,new File(realStorePath));
                    }
                }
                map.put("success", false);
                map.put("message", "目录结构错误!");
            }
        } catch (Exception e) {
            try {
                //将临时文件还原至正式目录
                File storeTmpRealPathFile = new File(storeTmpRealPath);
                if (storeTmpRealPathFile.exists()) {
                    List<File> tmpFiles = FileUtil.listFilesOrderByName(storeTmpRealPathFile);
                    for(File tmpFile :tmpFiles){
                        if (Arrays.asList(Constants.EXCLUDE_FILE_AND_DIR).contains(tmpFile.getName())) {
                            continue;
                        }
                        FileUtils.copyFileToDirectory(tmpFile,new File(realStorePath));
                    }
                }
            } catch (Exception e1) {

            }
            logger.error(e);
            throw new GenericException(e);
        } finally {
            try {
                File storeTmpRealPathFile = new File(storeTmpRealPath);
                if (storeTmpRealPathFile.exists()) {
                    FileUtils.deleteDirectory(storeTmpRealPathFile);
                }
                File storeZipRealPathFile = new File (storeZipRealPath);
                if (storeZipRealPathFile.exists()) {
                    FileUtils.deleteDirectory(storeZipRealPathFile);
                }
            } catch (Exception ex) {

            }
        }
        return map;
    }


    private String getTpStorePath(String a38Id) {
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        return Constants.DATP_STORE_PATH
                + userLoginDetails.getTenantId() + File.separator
                + a38Id.substring(a38Id.length() - 1, a38Id.length()) + File.separator
                + a38Id + File.separator;
    }

    private String getTpStoreTmpPath(String a38Id) {
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        return Constants.DATP_STORE_PATH
                + userLoginDetails.getTenantId() + File.separator
                + a38Id.substring(a38Id.length() - 1, a38Id.length()) + File.separator
                + UUIDUtil.getUUID() + File.separator;
    }

    private String getTpDownStore() {
        return Constants.DATP_STORE_PATH + File.separator+"down"+ File.separator
                + UUIDUtil.getUUID() + File.separator;
    }

    private boolean checkTpDirByCataolog(List<File> files) {
        boolean ispass = true;
        CommonConditionQuery query = new CommonConditionQuery();
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.asc("sort"));
        List<ECatalogTypeInfo> eCatalogTypeInfos = this.eCatalogTypeService.list(query, orderBy);
        for (File file : files) {
            //排除一些系统自带生成的目录及文件
            if (Arrays.asList(Constants.EXCLUDE_FILE_AND_DIR).contains(file.getName())) continue;
            boolean isExist = false;
            for (ECatalogTypeInfo eCatalogTypeInfo : eCatalogTypeInfos) {
                if (file.getName().indexOf(".") != -1) {
                    if (file.getName().substring(0, file.getName().indexOf(".")).equals(eCatalogTypeInfo.getCatalogCode())) {
                        isExist = true;
                    }
                }
            }
            if (!isExist) {
                ispass = false;
                break;
            }
        }
        return ispass;
    }

    @RequiresLog(operateType = LogOperateType.DELETE, description = "卸载图片：a0101")
    @RequestMapping(value = "/delete/{a38Id}")
    public
    @ResponseBody
    Map<String, Object> delete(@PathVariable(value = "a38Id") String a38Id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        try {
            A38 a38 = this.a38Service.getByPK(a38Id);
            this.eImagesService.deleteEImagesAndFileByA38(a38);
            map.put("success", true);
            map.put("message", "图片已卸载!");
        } catch (Exception e) {
            map.put("success", false);
            map.put("message", "图片卸载错误!");
        }
        return map;
    }

    @RequiresLog(operateType = LogOperateType.DOWNLOAD, description = "下载图片：a0101")
    @RequestMapping(value = "/download/{a38Id}")
    public void zipDown(@PathVariable(value = "a38Id") String a38Id, HttpServletResponse resp) {
        String srcPath = uploadBasePath + getTpStorePath(a38Id);
        String destPath = uploadBasePath + getTpDownStore();
        String zipPath = uploadBasePath + getTpDownStore();
        try {
            File tpStorePathFile = new File(srcPath);
            A38 a38 = this.a38Service.getByPK(a38Id);
            if (tpStorePathFile.exists()) {
                File destPathFile = new File(destPath);
                FileUtils.copyDirectory(tpStorePathFile, destPathFile);
                List<File> files = FileUtil.listFilesOrderByName(destPathFile);
                for (File file : files) {
                    if (Arrays.asList(Constants.EXCLUDE_FILE_AND_DIR).contains(file.getName())) {
                        continue;
                    }
                    List<File> tpFiles = FileUtil.listFilesOrderByName(file);
                    for (File tpFile : tpFiles) {
                        if (Arrays.asList(Constants.EXCLUDE_FILE_AND_DIR).contains(tpFile.getName())) {
                            continue;
                        }

                        File newDestFile = new File(tpFile.getPath() + ".jpg");

                        String imgCode = getImgCode(tpFile);

                        DESUtil.getInstance(Constants.DATP_KEY).decrypt(tpFile, newDestFile,imgCode,100000,1);
                        FileUtils.deleteQuietly(tpFile);
                    }
                }
                new File(zipPath).mkdirs();
                String zipRealStorePath = zipPath + UUIDUtil.getUUID() + ".zip";
                CompressUtil.zip(zipRealStorePath, destPath, a38.getA0101());
                resp.setContentType("multipart/form-data");
                resp.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoderUtil.encode(a38.getA0101() + ".zip"));
                OutputStream output = resp.getOutputStream();
                FileInputStream fileInputStream = new FileInputStream(new File(zipRealStorePath));
                byte[] buffer = new byte[8192];
                int length;
                while ((length = fileInputStream.read(buffer)) != -1) {
                    output.write(buffer, 0, length);
                }
                output.flush();
                output.close();
                fileInputStream.close();
            }
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e.getMessage());
        } finally {
            try {
                File destPathFile = new File(destPath);
                File zipPathFile = new File(zipPath);
                if (destPathFile.exists()) FileUtils.deleteDirectory(destPathFile);
                if (zipPathFile.exists()) FileUtils.deleteDirectory(zipPathFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getImgCode(File file){
        String str = "";
        String[] filePaths = file.getPath().split("\\\\");
        int index = 0;
        for(int i = 0;i<filePaths.length;i++){
            if(filePaths[i].length()==32){
                String docStr = filePaths[i+1];
                String[] names = docStr.split("\\.");
                if(names!=null&&names.length>0){
                    Integer code = Integer.parseInt(names[0]);
                    int x = code/10;
                    int y = code%10;
                    str +=x;
                    if(y>0){
                        str += "-" + y;
                    }
                    Integer fileCode = Integer.parseInt(filePaths[i+2]);
                    int fileX = fileCode/10;
                    int fileY = fileCode%10;
                    if(fileY==1){
                        str +="-" + fileX;
                    }else {
                        str="";
                    }
                }
                break;
            }
        }
        return str;
    }

}
