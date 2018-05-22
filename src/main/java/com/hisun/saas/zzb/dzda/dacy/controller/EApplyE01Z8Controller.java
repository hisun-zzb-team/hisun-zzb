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
import com.hisun.saas.zzb.dzda.dacy.entity.*;
import com.hisun.saas.zzb.dzda.dacy.service.*;
import com.hisun.saas.zzb.dzda.dacy.vo.EApplyE01Z8Vo;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
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
    @Resource
    private E01Z1Service e01Z1Service;

    @Value("${sys.upload.absolute.path}")
    private String uploadAbsolutePath;

    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                             @RequestParam(value = "userName", required = false) String userName,
                             @RequestParam(value = "readContent", required = false) String readContent,
                             @RequestParam(value = "auditingState", required = false) String auditingState
    ) throws UnsupportedEncodingException {
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String, Object> model = new HashMap<String, Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and("isShowToA0101 = :isShowToA0101 ", "isShowToA0101", "0"));
        if (StringUtils.isNotBlank(userName)) {
            query.add(CommonRestrictions.and("a0101 like :a0101 ", "a0101", "%" + userName + "%"));
        }
        if (StringUtils.isNotBlank(readContent)) {
            query.add(CommonRestrictions.and("readContent like :readContent ", "readContent", "%" + readContent + "%"));
        }
        if (StringUtils.isNotBlank(auditingState)) {
            query.add(CommonRestrictions.and("auditingState = :auditingState ", "auditingState", auditingState));
        }
        Long total = eApplyE01Z8Service.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.desc("createDate"));
        List<EApplyE01Z8> resultList = eApplyE01Z8Service.list(query, orderBy, pageNum, pageSize);
        PagerVo<EApplyE01Z8> pager = new PagerVo<EApplyE01Z8>(resultList, total.intValue(), pageNum, pageSize);
        model.put("pager", pager);
        model.put("userName", userName);
        model.put("readContent", readContent);
        model.put("auditingState", auditingState);
        return new ModelAndView("saas/zzb/dzda/dacy/list", model);
    }

    @RequestMapping(value = "/ajax/add")
    public ModelAndView add() {
        return new ModelAndView("saas/zzb/dzda/dacy/add");
    }


    @RequestMapping(value = "/ajax/edit")
    public ModelAndView edit(String id) {
        Map<String, Object> map = Maps.newHashMap();
        EApplyE01Z8 entity = eApplyE01Z8Service.getByPK(id);
        map.put("entity", entity);
        return new ModelAndView("saas/zzb/dzda/dacy/edit", map);
    }

    /**
     * 删除材料
     *
     * @param id
     * @return
     * @throws GenericException
     */
    @RequestMapping("/deleteFile/{id}")
    @RequiresPermissions("cysq:*")
    public
    @ResponseBody
    Map<String, Object> deleteFile(@PathVariable("id") String id) throws GenericException {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
            EApplyE01Z8 entity = eApplyE01Z8Service.getByPK(id);
            entity.setApplyFilePath(null);
            entity.setApplyFileName(null);
            eApplyE01Z8Service.update(entity);
            returnMap.put("code", 1);
        } catch (Exception e) {
            logger.error(e, e);
            throw new GenericException(e.getMessage());
        }
        return returnMap;
    }

    @RequestMapping(value = "/ajax/getDaxx")
    @ResponseBody
    public Map<String, Object> getDaxx(@RequestParam(value = "param", required = true) String param) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and("a0101 = :a0101 ", "a0101", param));
            List<A38> resultList = a38Service.list(query, null);
            if (resultList != null && !resultList.isEmpty()) {
                map.put("success", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            map.put("success", false);
        }
        return map;
    }

    @RequiresLog(operateType = LogOperateType.SAVE, description = "增加档案申请查阅记录:${vo.a0101}")
    @RequestMapping(value = "/save")
    @RequiresPermissions("cysq:*")
    public
    @ResponseBody
    Map<String, Object> save(EApplyE01Z8Vo vo, HttpServletRequest req,
                             @RequestParam(value = "clFile", required = false) MultipartFile clFile) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            String fileName = "";
            String savePath = "";
            if (clFile != null && !clFile.isEmpty()) {
                fileName = clFile.getOriginalFilename();
                if (fileName.endsWith(".doc") || fileName.endsWith(".DOC") || fileName.endsWith(".docx") || fileName.endsWith(".DOCX")) {
                    String fileDir = uploadAbsolutePath + "/e01z8";
                    File _fileDir = new File(fileDir);
                    if (_fileDir.exists() == false) {
                        _fileDir.mkdirs();
                    }
                    savePath = fileDir + File.separator + UUIDUtil.getUUID() + "." + FileUtil.getExtend(fileName);
                    ;
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
            if (StringUtils.isNotBlank(vo.getId())) {
                vo.setId(null);
                a0a01s = vo.getA0101();
            } else {
                a0a01s = vo.getA0101Content();
            }
            String[] names = a0a01s.split(",");
            //一次申请查询多人档案
            for (String name : names) {
                EApplyE01Z8 entity = new EApplyE01Z8();
                BeanUtils.copyProperties(entity, vo);
                entity.setA0101(name);
                EntityWrapper.wrapperSaveBaseProperties(entity, details);
                entity.setIsShowToA0101("0");
                entity.setAuditingState("0");
                entity.setApplyType("0");
                entity.setAccreditType("0");
                entity.setApplyFileName(fileName);
                entity.setE01Z807Name(details.getUsername());
                entity.setApplyFilePath(savePath);
                entity.setApplyUserId(details.getUserid());
                entity.setApplyUserName(details.getUsername());
                EntityWrapper.wrapperSaveBaseProperties(entity, details);
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

    @RequiresLog(operateType = LogOperateType.UPDATE, description = "增加档案申请查阅记录:${vo.a0101}")
    @RequestMapping(value = "/update")
    @RequiresPermissions("cysq:*")
    public
    @ResponseBody
    Map<String, Object> update(EApplyE01Z8Vo vo, HttpServletRequest req,
                               @RequestParam(value = "clFile", required = false) MultipartFile clFile) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            String fileName = "";
            String savePath = "";
            EApplyE01Z8 entity = eApplyE01Z8Service.getByPK(vo.getId());
            if (clFile != null && !clFile.isEmpty()) {
                fileName = clFile.getOriginalFilename();
                if (fileName.equals(entity.getApplyFileName())) {
                    FileUtils.deleteQuietly(new File(entity.getApplyFilePath()));
                }
                if (fileName.endsWith(".doc") || fileName.endsWith(".DOC") || fileName.endsWith(".docx") || fileName.endsWith(".DOCX")) {
                    String fileDir = uploadAbsolutePath + "/e01z8";
                    File _fileDir = new File(fileDir);
                    if (_fileDir.exists() == false) {
                        _fileDir.mkdirs();
                    }
                    savePath = fileDir + File.separator + UUIDUtil.getUUID() + "." + FileUtil.getExtend(fileName);
                    ;
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
            BeanUtils.copyProperties(entity, vo);
            EntityWrapper.wrapperUpdateBaseProperties(entity, details);
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
     *
     * @param id
     * @return
     * @throws GenericException
     */
    @RequestMapping("/deleteSq/{id}")
    @RequiresPermissions("cysq:*")
    public
    @ResponseBody
    Map<String, Object> deleteSq(@PathVariable("id") String id) throws GenericException {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
            eApplyE01Z8Service.deleteByPK(id);
            returnMap.put("code", 1);
        } catch (Exception e) {
            returnMap.put("code", 0);
            returnMap.put("message", "删除失败");
            logger.error(e, e);
            throw new GenericException(e.getMessage());
        }
        return returnMap;
    }


    /**
     * 改变状态 逻辑删除
     *
     * @param id
     * @return
     * @throws GenericException
     */
    @RequestMapping("/delete/{id}")
    @RequiresPermissions("cysq:*")
    public
    @ResponseBody
    Map<String, Object> delete(@PathVariable("id") String id) throws GenericException {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
            EApplyE01Z8 entity = eApplyE01Z8Service.getByPK(id);
            if (entity != null) {
                entity.setIsShowToA0101("1");
                eApplyE01Z8Service.update(entity);
                returnMap.put("code", 1);
            }
        } catch (Exception e) {
            returnMap.put("code", 0);
            returnMap.put("message", "删除失败");
            logger.error(e, e);
            throw new GenericException(e.getMessage());
        }
        return returnMap;
    }

    /**
     * 浏览时记录日志
     *
     * @param a38Id
     * @param eApplyE01Z8Id
     * @return
     */
    @RequestMapping(value = "/ajax/liulanLog")
    public
    @ResponseBody
    Map<String, Object> liulanLog(@RequestParam(value = "a38Id", required = true) String a38Id,
                                  @RequestParam(value = "eApplyE01Z8Id", required = false) String eApplyE01Z8Id,//eApplyE01Z8Id
                                  // @RequestParam(value = "a0101",required = false) String a0101,
                                  @RequestParam(value = "isManage", required = false) String isManage) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String nowMinDate = DateUtil.formatDefaultDate(new Date()) + " 00:00:00";
        String nowMaxDate = DateUtil.formatDefaultDate(new Date()) + " 23:59:59";
        try {
            EApplyE01Z8 eApplyE01Z8 = new EApplyE01Z8();
            //管理员 浏览管理档案 不做记录
            if (StringUtils.isNotBlank(isManage)) {
                if ("true".equals(isManage)) {
                    return returnMap;
                }
            }
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and("a38.id = :a38Id ", "a38Id", a38Id));
            query.add(CommonRestrictions.and("cysj >= :nowMinDate ", "nowMinDate", sdf.parse(nowMinDate)));
            query.add(CommonRestrictions.and("cysj <= :nowMaxDate ", "nowMaxDate", sdf.parse(nowMaxDate)));
            //eApplyE01Z8Id 不为空 为申请浏览阅档
            if (StringUtils.isNotBlank(eApplyE01Z8Id)) {
                query.add(CommonRestrictions.and("applyE01Z8.id = :eApplyE01Z8Id ", "eApplyE01Z8Id", eApplyE01Z8Id));
                eApplyE01Z8 = eApplyE01Z8Service.getByPK(eApplyE01Z8Id);
                if (eApplyE01Z8.getReadDate() == null || "".equals(eApplyE01Z8.getReadDate())) {
                    eApplyE01Z8.setReadDate(sdf.format(new Date()));
                    eApplyE01Z8.setReadState("1");
                    eApplyE01Z8Service.update(eApplyE01Z8);
                }
                //可阅读时间
                String readTime = eApplyE01Z8.getReadTime();
                //已阅读时间
                String alreadyReadTime = eApplyE01Z8.getAlreadyReadTime();
                //剩余可阅读时间
                String syReadTime = "syReadTime";
                if (alreadyReadTime == null || alreadyReadTime.equals("")) {
                    returnMap.put(syReadTime, Integer.valueOf(readTime) * 60);
                } else {
                    returnMap.put(syReadTime, ((Integer.valueOf(readTime) * 60) - Integer.valueOf(alreadyReadTime)));
                }
            }
            List<EA38Log> eA38Logs = eA38LogService.list(query, new CommonOrderBy());
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            String eA38LogPK;
            if (eA38Logs == null || eA38Logs.isEmpty()) {
                EA38Log ea38Log = new EA38Log();
                ea38Log.setApplyE01Z8(eApplyE01Z8);
                ea38Log.setCyrId(details.getUserid());
                ea38Log.setCyrName(details.getUsername());
                ea38Log.setA38(a38Service.getByPK(a38Id));
                ea38Log.setA0101(a38Service.getByPK(a38Id).getA0101());
                ea38Log.setReadTenantId(details.getTenantId());
                ea38Log.setCysj(new Date());
                ea38Log.setZzcysj(new Date());
                ea38Log.setReadTenantName(details.getTenantName());
                ea38Log.setYdzt(1);
                BeanUtils.copyProperties(ea38Log, details);
                eA38LogPK = eA38LogService.save(ea38Log);
            } else {
                eA38LogPK = eA38Logs.get(0).getId();
                eA38Logs.get(0).setYdzt(1);
                eA38LogService.update(eA38Logs.get(0));
            }
            EA38LogViewTime ea38LogViewTime = new EA38LogViewTime();
            ea38LogViewTime.setA38Log(eA38LogService.getByPK(eA38LogPK));
            ea38LogViewTime.setStareTime(new Date());
            BeanUtils.copyProperties(ea38LogViewTime, details);
            String a38LogViewTimeId = eA38LogViewTimeService.save(ea38LogViewTime);
            returnMap.put("a38LogViewTimeId", a38LogViewTimeId);
            returnMap.put("eApplyE01Z8Id", eApplyE01Z8Id);
            returnMap.put("a38LogId", eA38LogPK);
            returnMap.put("code", 1);
        } catch (Exception e) {
            returnMap.put("code", 0);
            logger.error(e, e);
            throw new GenericException(e.getMessage());
        }
        return returnMap;
    }

    /**
     * 更新 阅档时间 累加
     *
     * @param a38LogId
     * @param eApplyE01Z8Id
     * @param time
     */
    @RequestMapping(value = "/ajax/updateViewTime")
    public
    @ResponseBody
    Map<String, Object> updateViewTime(@RequestParam(value = "a38LogId", required = true) String a38LogId,
                                       @RequestParam(value = "eApplyE01Z8Id", required = false) String eApplyE01Z8Id,
                                       @RequestParam(value = "time", required = true) Integer time) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(eApplyE01Z8Id)) {
            EApplyE01Z8 eApplyE01Z8 = eApplyE01Z8Service.getByPK(eApplyE01Z8Id);
            if (eApplyE01Z8.getAlreadyReadTime() == null || "".equals(eApplyE01Z8.getAlreadyReadTime())) {
                eApplyE01Z8.setAlreadyReadTime(time.toString());
            } else {
                Integer viewTime = Integer.valueOf(eApplyE01Z8.getAlreadyReadTime());
                eApplyE01Z8.setAlreadyReadTime(String.valueOf(viewTime + time));
            }
            eApplyE01Z8Service.update(eApplyE01Z8);
            if(!eApplyE01Z8.getAuditingState().equals("1")){
                returnMap.put("code","2");//权限被收回等
            }
        }
       /* EA38Log ea38Log = eA38LogService.getByPK(a38LogId);
        if (ea38Log.getViewTime() == null || "".equals(ea38Log.getViewTime())) {
            ea38Log.setViewTime(String.valueOf(time));
        } else {
            Integer viewTime = Integer.valueOf(ea38Log.getViewTime());
            ea38Log.setViewTime(String.valueOf(viewTime + time));
        }
        ea38Log.setZzcysj(new Date());
        eA38LogService.update(ea38Log);*/
        return returnMap;
    }

    /**
     * 浏览材料详细日志
     *
     * @param a38LogId
     * @param e01z1Id
     * @param e01z111
     */
    @RequestMapping(value = "/ajax/detailViewTime")
    public
    @ResponseBody
    Map<String, Object> detailViewTime(@RequestParam(value = "a38LogId", required = true) String a38LogId,
                                       @RequestParam(value = "e01z1Id", required = true) String e01z1Id,
                                       @RequestParam(value = "lseLogDetailViewTimeId", required = false) String lseLogDetailViewTimeId,//上次浏览的id
                                       @RequestParam(value = "e01z111", required = true) String e01z111,
                                       @RequestParam(value = "lsEa38LogDetailId", required = false) String lsEa38LogDetailId,
                                       @RequestParam(value = "isManage", required = false) String isManage) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String nowMinDate = DateUtil.formatDefaultDate(new Date()) + " 00:00:00";
        String nowMaxDate = DateUtil.formatDefaultDate(new Date()) + " 23:59:59";
        try {
            if (StringUtils.isNotBlank(isManage)) {
                if ("true".equals(isManage)) {
                    return returnMap;
                }
            }
            if (StringUtils.isNotBlank(lsEa38LogDetailId)) {

            }
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and("a38Log.id = :a38Id ", "a38Id", a38LogId));
            query.add(CommonRestrictions.and("e01Z1Id = :e01z1Id ", "e01z1Id", e01z1Id));
            query.add(CommonRestrictions.and("cyTime >= :nowMinDate ", "nowMinDate", sdf.parse(nowMinDate)));
            query.add(CommonRestrictions.and("cyTime <= :nowMaxDate ", "nowMaxDate", sdf.parse(nowMaxDate)));
            EA38Log ea38Log = eA38LogService.getByPK(a38LogId);
            if (StringUtils.isNotBlank(lseLogDetailViewTimeId)) {
                ELogDetailViewTime eLogDetailViewTime = eLogDetailViewTimeService.getByPK(lseLogDetailViewTimeId);
                EA38LogDetail ea38LogDetail = eA38LogDetailService.getByPK(eLogDetailViewTime.getA38LogDetails().getId());
                eLogDetailViewTime.setEndTime(new Date());
                Long viewTime = (new Date()).getTime() - eLogDetailViewTime.getStareTime().getTime();
                eLogDetailViewTime.setViewTime(String.valueOf(viewTime / 1000));
                eLogDetailViewTimeService.update(eLogDetailViewTime);
                String ea38LogViewTime = ea38LogDetail.getCysj();
                if (ea38LogViewTime == null || "".equals(ea38LogViewTime)) {
                    ea38LogDetail.setCysj(String.valueOf(viewTime / 1000));
                } else {
                    ea38LogDetail.setCysj(String.valueOf(Integer.valueOf(ea38Log.getViewTime()) + (viewTime / 1000)));
                }
                ea38LogDetail.setJscysj(new Date());
                eA38LogDetailService.update(ea38LogDetail);
            }

            //  List<EA38LogDetail> logDetails = eA38LogDetailService.list(query, null);
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            //阅档申请信息
            //EApplyE01Z8 eApplyE01Z8 = eApplyE01Z8Service.getByPK(eApplyE01Z8Id);
            //档案材料信息
            E01Z1 e01Z1 = e01Z1Service.getByPK(e01z1Id);
            List<EA38LogDetail> ea38LogDetails = eA38LogDetailService.list(query, null);
            String ea38LogDetailId;
            if (ea38LogDetails == null || ea38LogDetails.isEmpty()) {
                EA38LogDetail ea38LogDetail = new EA38LogDetail();
                ea38LogDetail.setA38Log(ea38Log);
                ea38LogDetail.setCyTime(new Date());
                ea38LogDetail.setE01Z1Id(e01z1Id);
                ea38LogDetail.setE01Z101A(e01Z1.getE01Z101A());
                ea38LogDetail.setE01Z101B(e01Z1.getE01Z101B());
                ea38LogDetail.setE01Z111(e01z111);
                BeanUtils.copyProperties(ea38LogDetail, details);
                ea38LogDetailId = eA38LogDetailService.save(ea38LogDetail);
            } else {
                ea38LogDetailId = ea38LogDetails.get(0).getId();
            }
            ELogDetailViewTime eLogDetailViewTime = new ELogDetailViewTime();
            eLogDetailViewTime.setA38LogDetails(eA38LogDetailService.getByPK(ea38LogDetailId));
            eLogDetailViewTime.setStareTime(new Date());
            BeanUtils.copyProperties(eLogDetailViewTime, details);
            String eLogDetailViewTimeId = eLogDetailViewTimeService.save(eLogDetailViewTime);
            returnMap.put("eLogDetailViewTimeId", eLogDetailViewTimeId);
            returnMap.put("ea38LogDetailId", ea38LogDetailId);
            returnMap.put("code", 1);
        } catch (Exception e) {
            returnMap.put("code", 0);
            logger.error(e, e);
            throw new GenericException(e.getMessage());
        }
        return returnMap;
    }

    /**
     * 关闭或者结束阅档  时间处理
     * @param a38LogId
     * @param a38LogViewTimeId
     * @param ea38LogDetailId
     * @param lseLogDetailViewTimeId
     */
    private void dealEndTime(String a38LogId, String a38LogViewTimeId, String ea38LogDetailId, String lseLogDetailViewTimeId) {
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();

        if (StringUtils.isNotBlank(lseLogDetailViewTimeId)) {
            ELogDetailViewTime eLogDetailViewTime = eLogDetailViewTimeService.getByPK(lseLogDetailViewTimeId);
            eLogDetailViewTime.setEndTime(new Date());
            eLogDetailViewTime.setViewTime(String.valueOf((new Date().getTime() - eLogDetailViewTime.getStareTime().getTime()) / 1000));
            eLogDetailViewTimeService.update(eLogDetailViewTime);

            EA38LogDetail ea38LogDetail = eA38LogDetailService.getByPK(ea38LogDetailId);
            ea38LogDetail.setJscysj(new Date());
            if (ea38LogDetail.getCysj() == null || "".equals(ea38LogDetail.getCysj())) {
                ea38LogDetail.setCysj(String.valueOf((new Date().getTime() - eLogDetailViewTime.getStareTime().getTime()) / 1000));
            } else {
                Long viewtTiem = Long.valueOf(ea38LogDetail.getCysj());
                viewtTiem = viewtTiem + ((new Date().getTime() - eLogDetailViewTime.getStareTime().getTime()) / 1000);
                ea38LogDetail.setCysj(String.valueOf(viewtTiem));
            }
            eA38LogDetailService.update(ea38LogDetail);
        }

        EA38Log ea38Log = eA38LogService.getByPK(a38LogId);
        EA38LogViewTime ea38LogViewTime = eA38LogViewTimeService.getByPK(a38LogViewTimeId);
        ea38LogViewTime.setEndTime(new Date());
        ea38LogViewTime.setViewTime(String.valueOf((new Date().getTime() - ea38LogViewTime.getStareTime().getTime()) / 1000));
        eA38LogViewTimeService.update(ea38LogViewTime);
       /* if (StringUtils.isNotBlank(ea38LogDetailId)) {

        }*/
        if (ea38Log.getViewTime() == null || "".equals(ea38Log.getViewTime())) {
            ea38Log.setViewTime(String.valueOf((new Date().getTime() - ea38LogViewTime.getStareTime().getTime()) / 1000));
        } else {
            Long viewtTiem = Long.valueOf(ea38Log.getViewTime());
            viewtTiem = viewtTiem + ((new Date().getTime() - ea38LogViewTime.getStareTime().getTime()) / 1000);
            ea38Log.setViewTime(String.valueOf(viewtTiem));
        }
        //不在阅档状态
        ea38Log.setYdzt(0);
        eA38LogService.update(ea38Log);
    }

    /**
     * 关闭按钮 结束阅档
     *
     * @param a38LogId
     * @param a38LogViewTimeId
     * @param ea38LogDetailId
     * @param lseLogDetailViewTimeId
     */
    @RequestMapping(value = "/ajax/guanbiOrjieshu")
    public
    @ResponseBody
    Map<String, Object> guanbi(@RequestParam(value = "a38LogId", required = true) String a38LogId,
                               @RequestParam(value = "a38LogViewTimeId", required = true) String a38LogViewTimeId,
                               @RequestParam(value = "ea38LogDetailId", required = false) String ea38LogDetailId,
                               @RequestParam(value = "lseLogDetailViewTimeId", required = false) String lseLogDetailViewTimeId,
                               @RequestParam(value = "eApplyE01Z8Id", required = false) String eApplyE01Z8Id,
                               @RequestParam(value = "ydsjzt", required = false) String ydsjzt) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(eApplyE01Z8Id) && StringUtils.isNotBlank(ydsjzt)) {
            EApplyE01Z8 eApplyE01Z8 = eApplyE01Z8Service.getByPK(eApplyE01Z8Id);
            //结束阅档
            eApplyE01Z8.setAuditingState("4");
            eApplyE01Z8.setEndReadDate(DateUtil.formatTimesTampDate(new Date()));
            eApplyE01Z8Service.update(eApplyE01Z8);
            //时间到期
            returnMap.put("code", "5");
        }
        this.dealEndTime(a38LogId, a38LogViewTimeId, ea38LogDetailId, lseLogDetailViewTimeId);
        return returnMap;
    }

    @RequestMapping(value = "/ajax/down")
    @RequiresPermissions("cysq:*")
    public void templateDown(String id, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        EApplyE01Z8 entity = eApplyE01Z8Service.getByPK(id);
        if (entity.getApplyFilePath() != null && !entity.getApplyFilePath().equals("")) {
            String fileRealPath = entity.getApplyFilePath();
            resp.setContentType("multipart/form-data");
            //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
            resp.setHeader("Content-Disposition", "attachment;fileName="
                    + encode(fileRealPath.substring(fileRealPath.lastIndexOf(File.separator) + 1)));
            OutputStream output = resp.getOutputStream();
            byte[] b = FileUtils.readFileToByteArray(new File(fileRealPath));
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
