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
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.saas.zzb.dzda.mlcl.service.EImagesService;
import com.hisun.saas.zzb.dzda.mlcl.vo.MlclAggregateVo;
import com.hisun.saas.zzb.dzda.mlcl.vo.MlclTreeNode;
import com.hisun.util.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author Rocky {rockwithyou@126.com}
 */

@Controller
@RequestMapping("/zzb/dzda/mlcl/jztp")
public class JztpController extends BaseController {

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
                childTreeNode.setName(eCatalogTypeInfo.getCatalogCode()+"."+eCatalogTypeInfo.getCatalogValue());
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
                int imagesCount = e01Z1.getE01Z114();
                String title=e01Z1.getE01Z111();

                if(!e01z117.equals("")){
                    text = text +","+ e01z117;
                    title =title+" 制成时间："+e01z117;
                }
                if(imagesCount != 0){
                    text = text +","+ imagesCount;
                    title =title+" 材料页数："+imagesCount;
                }
                childTreeNode = new MlclTreeNode();
                childTreeNode.setId(e01Z1.getId());
                childTreeNode.setName(text);
                childTreeNode.setDescription(title);

                DecimalFormat decimalFormat = new DecimalFormat("00");
                childTreeNode.setKey(decimalFormat.format(e01Z1.getE01Z107()));
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
                mlclAggregateVo.setNameCode(decimalFormat.format(e01Z1.getE01Z107()));
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


    @RequestMapping("/ajax/list/{a38Id}")
    public ModelAndView list(@PathVariable(value = "a38Id") String a38Id, HttpServletRequest request) throws GenericException {
        Map<String, Object> map = Maps.newHashMap();
        String currentNodeId = StringUtils.trimNull2Empty(request.getParameter("currentNodeId"));
        String currentNodeName = StringUtils.trimNull2Empty(request.getParameter("currentNodeName"));
        String currentNodeParentId = StringUtils.trimNull2Empty(request.getParameter("currentNodeParentId"));//取得当前树节点的父ID属性
        try {


            map.put("maxFileSize", Constants.DATP_MAX_FILE_SIZE);
            map.put("currentNodeId", currentNodeId);
            map.put("currentNodeName", currentNodeName);
            map.put("currentNodeParentId", currentNodeParentId);
            map.put("a38Id", a38Id);
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
        }
        return new ModelAndView("saas/zzb/dzda/mlcl/jztp/listZipfile", map);
    }


    @RequiresLog(operateType = LogOperateType.SAVE, description = "加载图片")
    @RequestMapping(value = "/save/{a38Id}", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> save(@RequestParam(value = "zipfile", required = false) MultipartFile file,
                             @PathVariable(value = "a38Id") String a38Id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        try {
            A38 a38 = this.a38Service.getByPK(a38Id);
            String fileName = file.getOriginalFilename();
            //取A38ID的最后一位作为目录
            String storePath = getTpStorePath(a38Id);
            String storeRealPath = uploadBasePath + getTpStorePath(a38Id);
            File storeRealPathFile = new File(storeRealPath);
            if (storeRealPathFile.exists() == false) {
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
            if(checkTpDirByCataolog(files)){
                eImagesService.saveEImagesByJztp(a38,storeRealPathFile);
                map.put("success", true);
                map.put("message", "保存成功!");
            }else{
                FileUtils.deleteDirectory(storeRealPathFile);
                map.put("success", false);
                map.put("message", "目录结构错误!");
            }
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
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


    private boolean checkTpDirByCataolog(List<File> files) {
        boolean ispass = true;
        CommonConditionQuery query = new CommonConditionQuery();
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.asc("sort"));
        List<ECatalogTypeInfo> eCatalogTypeInfos = this.eCatalogTypeService.list(query, orderBy);
        for (File file : files) {
            //排除一些系统自带生成的目录及文件
            if(Arrays.asList(Constants.EXCLUDE_FILE_AND_DIR).contains(file.getName())) continue;
            boolean isExist = false;
            for (ECatalogTypeInfo eCatalogTypeInfo : eCatalogTypeInfos) {
                if(file.getName().indexOf(".")!=-1){
                    if (file.getName().substring(0, file.getName().indexOf(".")).equals(eCatalogTypeInfo.getCatalogCode())) {
                        isExist = true;
                    }
                }
            }
            if(!isExist){
                ispass = false;
                break;
            }
        }
        return ispass;
    }
}
