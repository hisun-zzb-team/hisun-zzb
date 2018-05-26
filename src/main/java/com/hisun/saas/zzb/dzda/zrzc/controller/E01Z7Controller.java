/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.zrzc.controller;

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
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.zrzc.entity.E01Z5;
import com.hisun.saas.zzb.dzda.zrzc.entity.E01Z7;
import com.hisun.saas.zzb.dzda.zrzc.service.E01Z7Service;
import com.hisun.saas.zzb.dzda.zrzc.vo.E01Z7ResVo;
import com.hisun.saas.zzb.dzda.zrzc.vo.E01Z7Vo;
import com.hisun.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Resource
    private A38Service a38Service;


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
            String a38IdString = vo.getNameContent();
            String[] a38ids = a38IdString.split(",");
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            E01Z7 entity = new E01Z7();
            for (String a38Id:a38ids){
                entity = new E01Z7();
                A38 a38 = a38Service.getByPK(a38Id);
                ConvertUtils.register(new DateConverter(null), java.util.Date.class);
                BeanUtils.copyProperties(entity, vo);
                entity.setE01Z701(StringUtils.isNotBlank(vo.getE01Z701())? DateUtil.parseDefaultDate(vo.getE01Z701()) :null );
                entity.setE01Z727(StringUtils.isNotBlank(vo.getE01Z727())? DateUtil.parseDefaultDate(vo.getE01Z727()) :null );
                entity.setA38(a38);
                entity.setName(a38.getA0101());
                entity.setE01Z717(details.getRealname());
                entity.setFileName(fileName);
                entity.setFilePath(savePath);
                entity.setId(null);
                EntityWrapper.wrapperSaveBaseProperties(entity, details);
                e01z7Service.save(entity);
            }
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
    @RequestMapping("/view/{id}")
    public ModelAndView view(@PathVariable("id") String id){
        Map<String,Object> model = new HashMap<String,Object>();
        try{
            E01Z7 e01Z7 = e01z7Service.getByPK(id);
            E01Z7Vo vo = new E01Z7Vo();
            ConvertUtils.register(new DateConverter(null), java.util.Date.class);
            BeanUtils.copyProperties(vo,e01Z7);
            vo.setE01Z701(e01Z7.getE01Z701()==null?null:DateUtil.formatDefaultDate(e01Z7.getE01Z701()));
            vo.setE01Z727(e01Z7.getE01Z727()==null?null:DateUtil.formatDefaultDate(e01Z7.getE01Z727()));
            model.put("vo",vo);
        }catch (Exception e){
            logger.error(e);
        }
        return new ModelAndView("saas/zzb/dzda/zrzc/e01z7/view", model);
    }

    @RequiresPermissions("dazd:*")
    @RequestMapping("/ajax/edit/{id}")
    public ModelAndView edit(@PathVariable("id")String id){
        Map<String,Object> model = new HashMap<String,Object>();
        E01Z7 e01Z7 = e01z7Service.getByPK(id);
        model.put("hzUserName",e01Z7.getE01Z724());
        model.put("hzrq",DateUtil.formatDefaultDate(e01Z7.getE01Z727()));
        model.put("id", id);
        return new ModelAndView("saas/zzb/dzda/zrzc/e01z7/edit", model);
    }

    @RequiresPermissions("dazd:*")
    @RequestMapping("/updateHzrq")
    public @ResponseBody Map<String,Object> updateHzrq(@RequestParam(value = "id",required = true) String id,
                                                       @RequestParam(value = "hzUserName",required = true) String hzUserName,
                                                       @RequestParam(value = "hzrq",required = true) String hzrq){
        Map<String,Object> model = new HashMap<String,Object>();
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date date = sdf.parse(hzrq);
            E01Z7 e01Z7 = e01z7Service.getByPK(id);
            e01Z7.setE01Z724(hzUserName);
            e01Z7.setE01Z727(sdf.parse(hzrq));
            e01z7Service.update(e01Z7);
            model.put("code","1");
        }catch (Exception e){
            model.put("code","0");
            logger.error(e);
        }
        return model;
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
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/ajax/xzgb")
    public ModelAndView plAddMlcl(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,@RequestParam(value = "pageSize",defaultValue = "5")int pageSize){
        Map<String, Object> map = Maps.newHashMap();
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" sjzt = :sjzt ", "sjzt", "1"));
        Long total = a38Service.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.desc("smxh"));
       // orderBy.add(CommonOrder.asc("a0101"));
        List<A38> resultList = a38Service.list(query,orderBy,pageNum,pageSize);
        PagerVo<A38> pager = new PagerVo<A38>(resultList, total.intValue(), pageNum, pageSize);
        map.put("pager",pager);

        return new ModelAndView("saas/zzb/dzda/zrzc/e01z7/xzgb",map);
    }

    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/ajax/getA38List")
    public ModelAndView plGetA38List(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,@RequestParam(value = "pageSize",defaultValue = "5")int pageSize){
        Map<String, Object> map = Maps.newHashMap();
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" sjzt = :sjzt ", "sjzt", "1"));
        Long total = a38Service.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.desc("smxh"));
        //orderBy.add(CommonOrder.asc("a0101"));
        List<A38> resultList = a38Service.list(query,orderBy,pageNum,pageSize);
        PagerVo<A38> pager = new PagerVo<A38>(resultList, total.intValue(), pageNum, pageSize);
        map.put("pager",pager);
        map.put("list",resultList);

        return new ModelAndView("saas/zzb/dzda/zrzc/e01z7/xzgbTable",map);
    }

    @RequestMapping(value = "/ajax/down")
    @RequiresPermissions("cysq:*")
    public void templateDown(String id, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        E01Z7 entity = e01z7Service.getByPK(id);
        if (entity.getFileName() != null && !entity.getFilePath().equals("")) {
            String fileRealPath = entity.getFilePath();
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