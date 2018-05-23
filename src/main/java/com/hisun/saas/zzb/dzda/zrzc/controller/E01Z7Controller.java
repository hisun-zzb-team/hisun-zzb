/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.zrzc.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.zrzc.entity.E01Z5;
import com.hisun.saas.zzb.dzda.zrzc.entity.E01Z7;
import com.hisun.saas.zzb.dzda.zrzc.service.E01Z7Service;
import com.hisun.saas.zzb.dzda.zrzc.vo.E01Z7ResVo;
import com.hisun.saas.zzb.dzda.zrzc.vo.E01Z7Vo;
import com.hisun.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuzj {279421824@qq.com}
 */
@Controller
@RequestMapping("/zzb/dzda/dazd")
public class E01Z7Controller extends BaseController {

    @Resource
    private E01Z7Service e01z7Service;

    @Value("${sys.upload.absolute.path}")
    private String uploadAbsolutePath;


    @RequiresPermissions("dazd:*")
    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                             @RequestParam(value="name",required = false)String name,
                             @RequestParam(value="e01Z507A",required = false)String e01Z704A,
                             @RequestParam(value="starTime",required = false)String starTime,
                             @RequestParam(value="endTime",required = false)String endTime,
                             @RequestParam(value="e01Z517",required = false)String e01Z717,
                             @RequestParam(value="e01Z527",required = false)String e01Z724) throws UnsupportedEncodingException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> model = new HashMap<String, Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        if(StringUtils.isNotBlank(name)){
            query.add(CommonRestrictions.and(" name = :name ", "name", name));
        }
        if(StringUtils.isNotBlank(e01Z704A)){
            query.add(CommonRestrictions.and(" e01Z704A = :e01Z704A ", "e01Z704A", e01Z704A));
        }
        if(StringUtils.isNotBlank(starTime)){
            query.add(CommonRestrictions.and(" e01Z701 >= :starTime ", "starTime", new DateTime(starTime).toDate()));
        }
        if(StringUtils.isNotBlank(endTime)){
            query.add(CommonRestrictions.and(" e01Z701 <= :endTime ", "endTime", new DateTime(endTime).toDate()));
        }
        if(StringUtils.isNotBlank(e01Z717)){
            query.add(CommonRestrictions.and(" e01Z717 = :e01Z717 ", "e01Z717", e01Z717));
        }
        if(StringUtils.isNotBlank(e01Z724)){
            query.add(CommonRestrictions.and(" e01Z724 = :e01Z724 ", "e01Z724", e01Z724));
        }
        Long total = e01z7Service.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
//        orderBy.add(CommonOrder.asc("sort"));
        List<E01Z7> resultList = e01z7Service.list(query, orderBy, pageNum, pageSize);
        List<Integer> userCountList = new ArrayList<Integer>();
        List<E01Z7ResVo> e01z7Vos = new ArrayList<E01Z7ResVo>();
        E01Z7ResVo vo = new E01Z7ResVo();
        for (E01Z7 entity : resultList) {
            vo = new E01Z7ResVo();
            BeanUtils.copyProperties(vo, entity);
            e01z7Vos.add(vo);
        }
        PagerVo<E01Z7ResVo> pager = new PagerVo<E01Z7ResVo>(e01z7Vos, total.intValue(), pageNum, pageSize);
        model.put("pager", pager);
        model.put("pager",pager);
        model.put("name",name);
        model.put("e01Z704A",e01Z704A);
        model.put("starTime",starTime);
        model.put("endTime",endTime);
        model.put("e01Z717",e01Z717);
        model.put("e01Z724",e01Z724);
        return new ModelAndView("saas/zzb/dzda/zrzc/e01z7/list", model);
    }

    @RequiresPermissions("dazd:*")
    @RequestMapping("/add")
    public ModelAndView add() {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        return new ModelAndView("saas/zzb/dzda/zrzc/e01z7/add", returnMap);
    }

    @RequiresPermissions("dazd:*")
    @RequestMapping("/save")
    public
    @ResponseBody
    Map<String, Object> save(@ModelAttribute E01Z7Vo vo, HttpServletRequest req,
                             @RequestParam(value = "clFile", required = false) MultipartFile clFile) throws GenericException {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Map<String, String> vMap = ValidateUtil.validAll(vo);
        if (vMap.size() > 0) {
            returnMap.put("message", "数据验证不通过");
            returnMap.put("code", -1);
            return returnMap;
        }
        try {
            String fileName = "";
            String savePath = "";
            if (clFile != null && !clFile.isEmpty()) {
                fileName = clFile.getOriginalFilename();
                if (fileName.endsWith(".doc") || fileName.endsWith(".DOC") || fileName.endsWith(".docx") || fileName.endsWith(".DOCX")
                        || fileName.endsWith(".xlsx")) {
                    String fileDir = uploadAbsolutePath + "/e01z5";
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
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            E01Z7 entity = new E01Z7();
            A38 a38 = new A38();
            //// TODO: 2018/5/23
            a38.setId("ass" + System.currentTimeMillis());
            ConvertUtils.register(new DateConverter(null), java.util.Date.class);
            BeanUtils.copyProperties(entity, vo);
            entity.setE01Z701(StringUtils.isNotBlank(vo.getE01Z701())? DateUtil.parseDefaultDate(vo.getE01Z701()) :null );
            entity.setE01Z727(StringUtils.isNotBlank(vo.getE01Z727())? DateUtil.parseDefaultDate(vo.getE01Z727()) :null );
            entity.setA38(a38);
            entity.setE01Z717(details.getRealname());
            entity.setFileName(fileName);
            entity.setFilePath(savePath);
            entity.setId(null);
            EntityWrapper.wrapperSaveBaseProperties(entity, details);
            e01z7Service.save(entity);
            returnMap.put("code", 1);
        } catch (GenericException e) {
            returnMap.put("code", -1);
            returnMap.put("message", e.getMessage());
        } catch (Exception e) {
            logger.error(e, e);
            returnMap.put("code", -1);
            returnMap.put("message", "系统错误，请联系管理员");
        }

        return returnMap;
    }

    @RequiresPermissions("dazd:*")
    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id) throws InvocationTargetException, IllegalAccessException {
        Map<String, Object> model = new HashMap<String, Object>();
        E01Z7 tenant = e01z7Service.getByPK(id);
        E01Z7Vo vo = new E01Z7Vo();
        BeanUtils.copyProperties(tenant, vo);
        model.put("entity", vo);
        return new ModelAndView("saas/zzb/dzda/zrzc/e01z7/edit", model);
    }

    @RequiresPermissions("dazd:*")
    @RequestMapping("/update")
    public
    @ResponseBody
    Map<String, Object> update(@ModelAttribute E01Z7Vo vo, HttpServletRequest request) throws GenericException {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Map<String, String> vMap = ValidateUtil.validAll(vo);
        if (StringUtils.isBlank(vo.getName()) || vo.getName().length() < 1 || vo.getName().length() > 15) {
            returnMap.put("message", "数据验证不通过");
            returnMap.put("code", -1);
            return returnMap;
        }
        E01Z7 entity = e01z7Service.getByPK(vo.getId());
        if (entity == null) {
            returnMap.put("message", "数据不存在");
            returnMap.put("code", -1);
            return returnMap;
        }
        try {
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            BeanUtils.copyProperties(vo, entity);
            EntityWrapper.wrapperUpdateBaseProperties(entity, details);
            e01z7Service.update(entity);
            returnMap.put("code", 1);
        } catch (Exception e) {
            logger.error(e, e);
            returnMap.put("code", -1);
        }
        return returnMap;
    }

    /**
     * 租户注销
     *
     * @param id
     * @return
     */
    @RequiresPermissions("dazd:*")
    @RequestMapping("/delete/{id}")
    public
    @ResponseBody
    Map<String, Object> delete(@PathVariable("id") String id) throws GenericException {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
            e01z7Service.deleteByPK(id);
            returnMap.put("code", 1);
        } catch (Exception e) {
            logger.error(e, e);
            returnMap.put("code", -1);
        }

        return returnMap;
    }
}