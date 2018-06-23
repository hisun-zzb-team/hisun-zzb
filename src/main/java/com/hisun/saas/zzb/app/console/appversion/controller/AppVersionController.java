/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.appversion.controller;

import com.google.common.collect.Lists;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.appversion.entity.AppVersion;
import com.hisun.saas.zzb.app.console.appversion.service.AppVersionService;
import com.hisun.saas.zzb.app.console.appversion.vo.AppVersionVo;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.saas.zzb.app.console.shpc.service.ShpcService;
import com.hisun.saas.sys.util.BeanTrans;
import com.hisun.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/appVersion")
public class AppVersionController extends BaseController {
    @Resource
    private AppVersionService appVersionService;

    @Autowired
    private SessionFactory sessionFactory;


    @Value("${sys.upload.absolute.path}")
    private String uploadAbsolutePath;

    @RequestMapping("/")
    public ModelAndView list(HttpServletRequest req,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.desc("createDate"));

            Long total = this.appVersionService.count(query);
            List<AppVersion> appVersions = this.appVersionService.list(query, orderBy, pageNum,
                    pageSize);
            List<AppVersionVo> appVersionVos = new ArrayList<AppVersionVo>();
            if (appVersions != null) {
                for (AppVersion appVersion : appVersions) {
                    AppVersionVo vo = new AppVersionVo();
                    BeanUtils.copyProperties(vo, appVersion);
                    appVersionVos.add(vo);
                }
            }
            PagerVo<AppVersionVo> pager = new PagerVo<AppVersionVo>(appVersionVos, total.intValue(),
                    pageNum, pageSize);
            map.put("pager", pager);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/appVersion/list", map);
    }


    @RequestMapping(value = "/add")
    public ModelAndView add() {
        AppVersionVo vo = new AppVersionVo();
        return new ModelAndView("/saas/zzb/app/console/appVersion/add","appVersion",vo);

    }


    @RequestMapping(value = "/edit")
    public ModelAndView edit(@RequestParam(value="id")String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            AppVersion appVersion = this.appVersionService.getByPK(id);
            AppVersionVo appVersionVo = new AppVersionVo();
            if (appVersion == null) {
                logger.error("数据不存在");
                throw new GenericException("数据不存在");
            }
            BeanUtils.copyProperties(appVersionVo, appVersion);
            map.put("appVersionVo", appVersionVo);
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "修改失败！");
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/appVersion/edit", map);
    }


    /**
     * 调转到修改页面
     * @return
     */
//    @RequiresPermissions("admin-assetStatus:delete")
    @RequestMapping(value = "/delete/{id}")
    public @ResponseBody
    Map<String, Object> delete(@PathVariable("id")String AssetStatusId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            AppVersion appVersion = this.appVersionService.getByPK(AssetStatusId);
            if(appVersion != null){
                this.appVersionService.delete(appVersion);
            }
            map.put("success", true);
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "删除失败！");
            throw new GenericException(e);
        }
        return map;

    }

    /**
     * 保存部务会信息
     * @return
     */
    @RequestMapping(value = "/save")
   public @ResponseBody Map<String, Object> save(@ModelAttribute AppVersionVo appVersionVo, HttpServletRequest req, @RequestParam(value="appFile",required = false) MultipartFile appFile) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        AppVersion appVersion = null;
        try {
            if (appVersionVo != null) {
                String id = appVersionVo.getId();
                if (id != null && id.length() > 0) {//修改
                    appVersion = this.appVersionService.getByPK(id);
                } else {//新增
                    appVersion = new AppVersion();
                }
                UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
                BeanUtils.copyProperties(appVersion, appVersionVo);
                appVersion.setTenant(userLoginDetails.getTenant());

                if(appFile!=null && !appFile.isEmpty()) {
                    String fileName = appFile.getOriginalFilename();

                    if (fileName.endsWith(".apk") || fileName.endsWith(".ipa")) {
                        String fileDir = uploadAbsolutePath + AppVersionService.APP_PATH;

                        File _fileDir = new File(fileDir);
                        if (_fileDir.exists() == false) {
                            _fileDir.mkdirs();
                        }
                        //附件存储路径
                        String savePath = AppVersionService.APP_PATH + UUIDUtil.getUUID() + "_" + fileName;
                        String refSavePath =  uploadAbsolutePath + savePath;
                        try {

                            FileOutputStream fos = new FileOutputStream(new File(refSavePath));
                            fos.write(appFile.getBytes());
                            fos.flush();
                            fos.close();

                            FileInputStream inputStream = new FileInputStream(new File(refSavePath));
                            appVersion.setAppMd5(DigestUtils.md5Hex(IOUtils.toByteArray(inputStream)));
                            appVersion.setAppSize(Long.toString(appFile.getSize()));
                            appVersion.setAppStorePath(savePath);
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new GenericException(e);
                        }
                    }
                }

                if (id != null && id.length() > 0) {
                    BeanTrans.setBaseProperties(appVersion, userLoginDetails, "update");
                    this.appVersionService.update(appVersion);
                } else {
                    BeanTrans.setBaseProperties(appVersion, userLoginDetails, "save");
                    this.appVersionService.save(appVersion);
                }
                map.put("success", true);
            }
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return map;
    }
    @RequestMapping(value="/ajax/down")
    public void templateDown(String id,HttpServletRequest req, HttpServletResponse resp) throws Exception{
        AppVersion appVersion = this.appVersionService.getByPK(id);
        if(appVersion.getAppStorePath()!=null &&!appVersion.getAppStorePath().equals("")){
            String fileRealPath =uploadAbsolutePath+appVersion.getAppStorePath();
            resp.setContentType("multipart/form-data");
            //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
            resp.setHeader("Content-Disposition", "attachment;fileName="
                    +encode(fileRealPath.substring(fileRealPath.lastIndexOf(File.separator)+1)));
            OutputStream output=resp.getOutputStream();
            byte[] b= FileUtils.readFileToByteArray(new File(fileRealPath));
            output.write(b);
            output.flush();
            output.close();
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
}
