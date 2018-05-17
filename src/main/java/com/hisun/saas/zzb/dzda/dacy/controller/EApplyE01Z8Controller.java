/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.controller;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.log.LogOperateType;
import com.hisun.saas.sys.log.RequiresLog;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a32.entity.A32;
import com.hisun.saas.zzb.dzda.a32.vo.A32Vo;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.dacy.entity.EA38Log;
import com.hisun.saas.zzb.dzda.dacy.entity.EA38LogViewTime;
import com.hisun.saas.zzb.dzda.dacy.entity.EApplyE01Z8;
import com.hisun.saas.zzb.dzda.dacy.service.*;
import com.hisun.saas.zzb.dzda.dacy.vo.EApplyE01Z8Vo;
import com.hisun.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marco {854476391@qq.com}
 */
@Controller
@RequestMapping("/zzb/dzda/cysq")
public class EApplyE01Z8Controller extends BaseController {
    @Resource
    private EApplyE01Z8Service eApplyE01Z8Service;
    @Resource
    private A38Service a38Service;
    @Resource
    private EA38LogService eA38LogService;
    @Resource
    private EA38LogViewTimeService eA38LogViewTimeService;
    @Resource
    private EA38LogDetailService eA38LogDetailService;
    @Resource
    private ELogDetailViewTimeService eLogDetailViewTimeService;

    @Value("${sys.upload.absolute.path}")
    private String uploadAbsolutePath;

    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                             @RequestParam(value = "userName",required = false)String userName,
                             @RequestParam(value = "readContent",required = false)String readContent,
                             @RequestParam(value = "auditingState",required = false)String auditingState
        ) throws UnsupportedEncodingException {
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String,Object> model = new HashMap<String,Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and("isShowToA0101 = :isShowToA0101 ", "isShowToA0101", "0"));
        if(StringUtils.isNotBlank(userName)){
            query.add(CommonRestrictions.and("a0101 like :a0101 ", "a0101","%"+ userName+"%"));
        }
        if(StringUtils.isNotBlank(readContent)){
            query.add(CommonRestrictions.and("readContent like :readContent ", "readContent", "%"+readContent+"%"));
        }
        if(StringUtils.isNotBlank(auditingState)){
            query.add(CommonRestrictions.and("auditingState = :auditingState ", "auditingState", auditingState));
        }
        Long total = eApplyE01Z8Service.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.desc("createDate"));
        List<EApplyE01Z8> resultList = eApplyE01Z8Service.list(query,orderBy,pageNum,pageSize);
        PagerVo<EApplyE01Z8> pager = new PagerVo<EApplyE01Z8>(resultList, total.intValue(), pageNum, pageSize);
        model.put("pager",pager);
        model.put("userName",userName);
        model.put("readContent",readContent);
        model.put("auditingState",auditingState);
        return new ModelAndView("saas/zzb/dzda/dacy/list",model);
    }
    @RequestMapping(value = "/ajax/add")
    public ModelAndView add(){
        return new ModelAndView("saas/zzb/dzda/dacy/add");
    }



    @RequestMapping(value = "/ajax/edit")
    public ModelAndView edit(String id){
        Map<String,Object> map = Maps.newHashMap();
        EApplyE01Z8 entity = eApplyE01Z8Service.getByPK(id);
        map.put("entity",entity);
        return new ModelAndView("saas/zzb/dzda/dacy/edit",map);
    }

    /**
     * 删除材料
     * @param id
     * @return
     * @throws GenericException
     */
    @RequestMapping("/deleteFile/{id}")
    @RequiresPermissions("cysq:*")
    public @ResponseBody Map<String,Object> deleteFile(@PathVariable("id") String id) throws GenericException {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            EApplyE01Z8 entity = eApplyE01Z8Service.getByPK(id);
            entity.setApplyFilePath(null);
            entity.setApplyFileName(null);
            eApplyE01Z8Service.update(entity);
            returnMap.put("code",1);
        }catch (Exception e){
            logger.error(e,e);
            throw new GenericException(e.getMessage());
        }
        return returnMap;
    }

    @RequestMapping(value = "/ajax/getDaxx")
    @ResponseBody
    public Map<String,Object> getDaxx(@RequestParam(value = "param",required = true) String param){
        Map<String,Object> map = Maps.newHashMap();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and("a0101 = :a0101 ", "a0101", param));
            List<A38> resultList = a38Service.list(query,null);
            if(resultList != null && !resultList.isEmpty()){
                map.put("success", true);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            map.put("success", false);
        }
        return  map;
    }
    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加档案申请查阅记录:${vo.a0101}")
    @RequestMapping(value = "/save")
    @RequiresPermissions("cysq:*")
    public @ResponseBody Map<String, Object> save(EApplyE01Z8Vo vo,HttpServletRequest req,
                                                  @RequestParam(value="clFile",required = false) MultipartFile clFile){
        Map<String,Object> map = Maps.newHashMap();
        try {
            String fileName = "";
            String savePath = "";
            if (clFile != null && !clFile.isEmpty()) {
                    fileName = clFile.getOriginalFilename();
                    if(fileName.endsWith(".doc") ||fileName.endsWith(".DOC") ||fileName.endsWith(".docx") ||fileName.endsWith(".DOCX") ) {
                        String fileDir = uploadAbsolutePath + "/e01z8";
                        File _fileDir = new File(fileDir);
                        if (_fileDir.exists() == false) {
                            _fileDir.mkdirs();
                        }
                        savePath = fileDir + File.separator + UUIDUtil.getUUID() +"."+ FileUtil.getExtend(fileName);;
                       // savePath =fileDir;
                        try {
                            FileOutputStream fos = new FileOutputStream(new File(savePath));
                            fos.write(clFile.getBytes());
                            fos.flush();
                            fos.close();
                        } catch (Exception e) {
                             e.printStackTrace();
                            throw new GenericException(e);
                        }
                }
            }
            String a0a01s;
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            if(StringUtils.isNotBlank(vo.getId())){
                vo.setId(null);
                a0a01s=vo.getA0101();
            }else {
                a0a01s = vo.getA0101Content();
            }
            String [] names = a0a01s.split(",");
            //一次申请查询多人档案
            for (String name : names){
                EApplyE01Z8 entity = new EApplyE01Z8();
                BeanUtils.copyProperties(entity,vo);
                entity.setA0101(name);
                EntityWrapper.wrapperSaveBaseProperties(entity,details);
                entity.setIsShowToA0101("0");
                entity.setAuditingState("0");
                entity.setApplyType("0");
                entity.setAccreditType("0");
                entity.setApplyFileName(fileName);
                entity.setE01Z807Name(details.getUsername());
                entity.setApplyFilePath(savePath);
                entity.setApplyUserId(details.getUserid());
                entity.setApplyUserName(details.getUsername());
                EntityWrapper.wrapperSaveBaseProperties(entity,details);
                eApplyE01Z8Service.save(entity);
            }
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            map.put("success", false);
        }
        return map;
    }

    @RequiresLog(operateType = LogOperateType.UPDATE,description = "增加档案申请查阅记录:${vo.a0101}")
    @RequestMapping(value = "/update")
    @RequiresPermissions("cysq:*")
    public @ResponseBody Map<String, Object> update(EApplyE01Z8Vo vo,HttpServletRequest req,
                                                  @RequestParam(value="clFile",required = false) MultipartFile clFile){
        Map<String,Object> map = Maps.newHashMap();
        try {
            String fileName = "";
            String savePath = "";
            EApplyE01Z8 entity = eApplyE01Z8Service.getByPK(vo.getId());
            if (clFile != null && !clFile.isEmpty()) {
                fileName = clFile.getOriginalFilename();
                if(fileName.equals(entity.getApplyFileName())){
                    FileUtils.deleteQuietly(new File(entity.getApplyFilePath()));
                }
                if(fileName.endsWith(".doc") ||fileName.endsWith(".DOC") ||fileName.endsWith(".docx") ||fileName.endsWith(".DOCX") ) {
                    String fileDir = uploadAbsolutePath + "/e01z8";
                    File _fileDir = new File(fileDir);
                    if (_fileDir.exists() == false) {
                        _fileDir.mkdirs();
                    }
                    savePath = fileDir + File.separator + UUIDUtil.getUUID() +"."+ FileUtil.getExtend(fileName);;
                    try {
                        FileOutputStream fos = new FileOutputStream(new File(savePath));
                        fos.write(clFile.getBytes());
                        fos.flush();
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new GenericException(e);
                    }
                    vo.setApplyFileName(fileName);
                    vo.setApplyFilePath(savePath);
                }
            }
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            BeanUtils.copyProperties(entity,vo);
            EntityWrapper.wrapperUpdateBaseProperties(entity,details);
            eApplyE01Z8Service.update(entity);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            map.put("success", false);
        }
        return map;
    }

    /**
     * 撤销申请 物理删除
     * @param id
     * @return
     * @throws GenericException
     */
    @RequestMapping("/deleteSq/{id}")
    @RequiresPermissions("cysq:*")
    public @ResponseBody Map<String,Object> deleteSq(@PathVariable("id") String id) throws GenericException {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            eApplyE01Z8Service.deleteByPK(id);
            returnMap.put("code",1);
        }catch (Exception e){
            returnMap.put("code",0);
            returnMap.put("message","删除失败");
            logger.error(e,e);
            throw new GenericException(e.getMessage());
        }
        return returnMap;
    }


    /**
     * 改变状态 逻辑删除
     * @param id
     * @return
     * @throws GenericException
     */
    @RequestMapping("/delete/{id}")
    @RequiresPermissions("cysq:*")
    public @ResponseBody Map<String,Object> delete(@PathVariable("id") String id) throws GenericException {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            EApplyE01Z8 entity = eApplyE01Z8Service.getByPK(id);
            if (entity != null){
                entity.setIsShowToA0101("1");
                eApplyE01Z8Service.update(entity);
                returnMap.put("code",1);
            }
        }catch (Exception e){
            returnMap.put("code",0);
            returnMap.put("message","删除失败");
            logger.error(e,e);
            throw new GenericException(e.getMessage());
        }
        return returnMap;
    }

    /**
     * 浏览时记录日志
     * @param a38Id
     * @param id
     * @param a0101
     * @return
     */
    @RequestMapping(value="/ajax/liulanLog")
    public @ResponseBody Map<String,Object> liulanLog(@RequestParam(value = "a38Id",required = true) String a38Id,
                                                      @RequestParam(value = "id",required = true) String id,
                                                      @RequestParam(value = "a0101",required = true) String a0101){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String nowMinDate =DateUtil.formatDefaultDate(new Date())+" 00:00:00";
        String nowMaxDate =DateUtil.formatDefaultDate(new Date())+" 23:59:59";
        try{
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and("a38.id = :a38Id ", "a38Id", a38Id));
            query.add(CommonRestrictions.and("cysj >= :nowMinDate ", "nowMinDate", sdf.parse(nowMinDate)));
            query.add(CommonRestrictions.and("cysj <= :nowMaxDate ", "nowMaxDate", sdf.parse(nowMaxDate)));
            List<EA38Log> eA38Logs = eA38LogService.list(query,new CommonOrderBy());
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            EApplyE01Z8 eApplyE01Z8 = eApplyE01Z8Service.getByPK(id);
            String eA38LogPK;
            EA38Log ea38Log = new EA38Log();
            if(eA38Logs ==null || eA38Logs.isEmpty()){
                ea38Log.setApplyE01Z8(eApplyE01Z8);
                ea38Log.setCyrId(details.getUserid());
                ea38Log.setCyrName(details.getUsername());
                ea38Log.setA38(a38Service.getByPK(a38Id));
                ea38Log.setA0101(a0101);
                ea38Log.setReadTenantId(details.getTenantId());
                ea38Log.setCysj(new Date());
                ea38Log.setReadTenantName(details.getTenantName());
                BeanUtils.copyProperties(ea38Log,details);
                eA38LogPK = eA38LogService.save(ea38Log);
            }else {
                eA38LogPK = eA38Logs.get(0).getId();
            }
            EA38LogViewTime ea38LogViewTime = new EA38LogViewTime();
            ea38LogViewTime.setA38Log(eA38LogService.getByPK(eA38LogPK));
            ea38LogViewTime.setStareTime(new Date());
            BeanUtils.copyProperties(ea38LogViewTime,details);
            eA38LogViewTimeService.save(ea38LogViewTime);
            //可阅读时间
            String readTime= eApplyE01Z8.getReadTime();
            //已阅读时间
            String alreadyReadTime = eApplyE01Z8.getReadTime();
            //剩余可阅读时间
            String syReadTime = "syReadTime";
            if(alreadyReadTime == null || alreadyReadTime.equals("")){
                returnMap.put(syReadTime,readTime);
            }else {
                returnMap.put(syReadTime,((Integer.valueOf(readTime) * 60) - Integer.valueOf(alreadyReadTime)));
            }
            returnMap.put("code",1);
        }catch (Exception e){
            returnMap.put("code",0);
            logger.error(e,e);
            throw new GenericException(e.getMessage());
        }
        return returnMap;
    }


    @RequestMapping(value="/ajax/down")
    @RequiresPermissions("cysq:*")
    public void templateDown(String id,HttpServletRequest req, HttpServletResponse resp) throws Exception{
        EApplyE01Z8 entity = eApplyE01Z8Service.getByPK(id);
        if(entity.getApplyFilePath()!=null &&!entity.getApplyFilePath().equals("")){
            String fileRealPath =entity.getApplyFilePath();
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
