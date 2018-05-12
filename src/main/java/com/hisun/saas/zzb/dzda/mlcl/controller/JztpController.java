/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.mlcl.controller;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.exception.GenericException;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.log.LogOperateType;
import com.hisun.saas.sys.log.RequiresLog;
import com.hisun.saas.zzb.dzda.mlcl.Constants;
import com.hisun.util.CompressUtil;
import com.hisun.util.StringUtils;
import com.hisun.util.UUIDUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rocky {rockwithyou@126.com}
 */

@Controller
@RequestMapping("/zzb/dzda/mlcl/jztp")
public class JztpController extends BaseController {

    @Value("${sys.upload.absolute.path}")
    private String uploadBasePath;

    @RequestMapping(value = "/index")
    public ModelAndView index() {
        Map<String, Object> map = new HashMap<String, Object>();

        return new ModelAndView("saas/zzb/dzda/mlcl/jztp/index", map);
    }

    @RequestMapping("/ajax/list")
    public ModelAndView list(HttpServletRequest request) throws GenericException {
        Map<String, Object> map = Maps.newHashMap();
        String currentNodeId = StringUtils.trimNull2Empty(request.getParameter("currentNodeId"));
        String currentNodeName = StringUtils.trimNull2Empty(request.getParameter("currentNodeName"));
        String currentNodeParentId = StringUtils.trimNull2Empty(request.getParameter("currentNodeParentId"));//取得当前树节点的父ID属性
        try {


            map.put("maxFileSize", Constants.DATP_MAX_FILE_SIZE);
            map.put("currentNodeId", currentNodeId);
            map.put("currentNodeName", currentNodeName);
            map.put("currentNodeParentId", currentNodeParentId);
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
        }
        return new ModelAndView("saas/zzb/dzda/mlcl/jztp/listZipfile", map);
    }


    @RequiresLog(operateType = LogOperateType.SAVE, description = "保存材料目录")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> save(@RequestParam(value = "zipfile", required = false) MultipartFile file, HttpServletRequest request) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        try {

            String fileName = file.getOriginalFilename();
            String storePath = uploadBasePath + Constants.DATP_STORE_PATH + userLoginDetails.getTenantId() + File.separator;
            File storePathFile = new File(storePath);
            if (storePathFile.exists() == false) {
                storePathFile.mkdirs();
            }
            //先将zip文件存入目录
            String zipStoreRealPath = storePath + UUIDUtil.getUUID() + ".zip";
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
            //解压zip文件到临时目录
            String unzipTmpPath =  storePath + UUIDUtil.getUUID()+File.separator;
            CompressUtil.unzip(zipStoreRealPath, unzipTmpPath);
            //将文件COPY存储到对应的目录

            //删除临时文件
            FileUtils.deleteQuietly(zipFile);
            FileUtils.deleteDirectory(new File(unzipTmpPath));
            map.put("success", true);
            map.put("message", "保存成功!");
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }

}
