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
import com.hisun.saas.zzb.dzda.zrzc.entity.E01Z5;
import com.hisun.saas.zzb.dzda.zrzc.service.E01Z5Service;
import com.hisun.saas.zzb.dzda.zrzc.vo.E01Z5ResVo;
import com.hisun.saas.zzb.dzda.zrzc.vo.E01Z5Vo;
import com.hisun.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/zzb/dzda/dajs")
public class E01Z5Controller extends BaseController {

    @Resource
    private E01Z5Service e01z5Service;

    @Value("${sys.upload.absolute.path}")
    private String uploadAbsolutePath;


    @RequiresPermissions("dajs:*")
    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "15")int pageSize,
                             @RequestParam(value="name",required = false)String name,
                             @RequestParam(value="e01Z507A",required = false)String e01Z507A,
                             @RequestParam(value="starTime",required = false)String starTime,
                             @RequestParam(value="endTime",required = false)String endTime,
                             @RequestParam(value="e01Z517",required = false)String e01Z517,
                             @RequestParam(value="e01Z527",required = false)String e01Z527
    ) throws UnsupportedEncodingException, InvocationTargetException, IllegalAccessException {
        Map<String,Object> model = new HashMap<String,Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        if(StringUtils.isNotBlank(name)){
            query.add(CommonRestrictions.and(" name = :name ", "name", name));
        }
        if(StringUtils.isNotBlank(e01Z507A)){
            query.add(CommonRestrictions.and(" e01Z507A = :e01Z507A ", "e01Z507A", e01Z507A));
        }
        if(StringUtils.isNotBlank(starTime)){
            query.add(CommonRestrictions.and(" e01Z501 >= :starTime ", "starTime", new DateTime(starTime).toDate()));
        }
        if(StringUtils.isNotBlank(endTime)){
            query.add(CommonRestrictions.and(" e01Z501 <= :endTime ", "endTime", new DateTime(endTime).toDate()));
        }
        if(StringUtils.isNotBlank(e01Z517)){
            query.add(CommonRestrictions.and(" e01Z517 = :e01Z517 ", "e01Z517", e01Z517));
        }
        if(StringUtils.isNotBlank(e01Z527)){
            query.add(CommonRestrictions.and(" e01Z527 = :e01Z527 ", "e01Z527", e01Z527));
        }

//        query.add(CommonRestrictions.and(" pId = :pId ", "pId", pId));
        Long total = e01z5Service.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
//        orderBy.add(CommonOrder.asc("sort"));
        List<E01Z5> resultList = e01z5Service.list(query,orderBy,pageNum,pageSize);
        List<E01Z5ResVo> e01z5Vos = new ArrayList<E01Z5ResVo>();
        E01Z5ResVo vo = new E01Z5ResVo();
        for(E01Z5 entity : resultList){
            vo = new E01Z5ResVo();
            BeanUtils.copyProperties(vo,entity);
            e01z5Vos.add(vo);
        }
        PagerVo<E01Z5ResVo> pager = new PagerVo<E01Z5ResVo>(e01z5Vos, total.intValue(), pageNum, pageSize);
        model.put("pager",pager);
        model.put("name",name);
        model.put("e01Z507A",e01Z507A);
        model.put("starTime",starTime);
        model.put("endTime",endTime);
        model.put("e01Z517",e01Z517);
        model.put("e01Z527",e01Z527);

        return new ModelAndView("saas/zzb/dzda/zrzc/e01z5/list",model);
    }

    @RequiresPermissions("dajs:*")
    @RequestMapping("/add")
    public ModelAndView add(){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        returnMap.put("","");
        return new ModelAndView("saas/zzb/dzda/zrzc/e01z5/add",returnMap);
    }

    // @RequiresPermissions("dajs:*")
    @RequestMapping("/save")
    public @ResponseBody Map<String,Object> save(E01Z5Vo vo,HttpServletRequest req,
                                                 @RequestParam(value = "clFile", required = false) MultipartFile clFile){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Map<String,String> vMap = ValidateUtil.validAll(vo);
        if(vMap.size()>0){
            returnMap.put("message","数据验证不通过");
            returnMap.put("code",-1);
            return returnMap;
        }
        try{
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
            E01Z5 entity = new  E01Z5();
            A38 a38 = new A38();
            //// TODO: 2018/5/23
            a38.setId("ass" + System.currentTimeMillis());
            ConvertUtils.register(new DateConverter(null), java.util.Date.class);
            BeanUtils.copyProperties(entity, vo);
            entity.setE01Z501(com.hisun.util.StringUtils.isNotBlank(vo.getE01Z501())? DateUtil.parseDefaultDate(vo.getE01Z501()) :null );
            entity.setE01Z524(com.hisun.util.StringUtils.isNotBlank(vo.getE01Z524())? DateUtil.parseDefaultDate(vo.getE01Z524()) :null );
            entity.setE01Z531(com.hisun.util.StringUtils.isNotBlank(vo.getE01Z531())? DateUtil.parseDefaultDate(vo.getE01Z531()) :null );
            entity.setE01Z534(com.hisun.util.StringUtils.isNotBlank(vo.getE01Z534())? DateUtil.parseDefaultDate(vo.getE01Z534()) :null );
                entity.setA38(a38);
            entity.setFileName(fileName);
            entity.setFilePath(savePath);
            entity.setId(null);
            entity.setE01Z517(details.getRealname());
            EntityWrapper.wrapperSaveBaseProperties(entity,details);
            e01z5Service.save(entity);
            returnMap.put("code",1);
        }catch (GenericException e){
            returnMap.put("code",-1);
            returnMap.put("message", e.getMessage());
        }catch (Exception e){
            logger.error(e,e);
            returnMap.put("code",-1);
            returnMap.put("message", "系统错误，请联系管理员");
        }

        return returnMap;
    }

    @RequiresPermissions("dajs:*")
    @RequestMapping("/ajax/edit/{id}")
    public ModelAndView edit(@PathVariable("id")String id){
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", id);
        return new ModelAndView("saas/zzb/dzda/zrzc/e01z5/edit", model);
    }

    @RequiresPermissions("dajs:*")
    @RequestMapping("/view/{id}")
    public ModelAndView view(@PathVariable("id") String id){
        Map<String,Object> model = new HashMap<String,Object>();
        try{
            E01Z5 e01Z5 = e01z5Service.getByPK(id);
            E01Z5Vo vo = new E01Z5Vo();
            ConvertUtils.register(new DateConverter(null), java.util.Date.class);
            BeanUtils.copyProperties(vo,e01Z5);
            vo.setE01Z501(e01Z5.getE01Z501()==null?null:DateUtil.formatDefaultDate(e01Z5.getE01Z501()));
            vo.setE01Z524(e01Z5.getE01Z524()==null?null:DateUtil.formatDefaultDate(e01Z5.getE01Z524()));
            vo.setE01Z531(e01Z5.getE01Z531()==null?null:DateUtil.formatDefaultDate(e01Z5.getE01Z531()));
            vo.setE01Z534(e01Z5.getE01Z534()==null?null:DateUtil.formatDefaultDate(e01Z5.getE01Z534()));
            model.put("vo",vo);
        }catch (Exception e){
            logger.error(e);
        }

        return new ModelAndView("saas/zzb/dzda/zrzc/e01z5/view", model);
    }
    @RequiresPermissions("dajs:*")
    @RequestMapping("/updateHzrq")
    public @ResponseBody Map<String,Object> updateHzrq(@RequestParam(value = "id",required = true) String id,
                                         @RequestParam(value = "hzrq",required = true) String hzrq){
        Map<String,Object> model = new HashMap<String,Object>();
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date date = sdf.parse(hzrq);
            E01Z5 e01Z5 = e01z5Service.getByPK(id);
            e01Z5.setE01Z531(sdf.parse(hzrq));
            e01z5Service.update(e01Z5);
            model.put("code","1");
        }catch (Exception e){
            model.put("code","0");
            logger.error(e);
        }
        return model;
    }


    @RequestMapping(value = "/ajax/down")
    @RequiresPermissions("cysq:*")
    public void templateDown(String id, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        E01Z5 entity = e01z5Service.getByPK(id);
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


    @RequiresPermissions("dajs:*")
    @RequestMapping("/update")
    public @ResponseBody Map<String,Object> update(@ModelAttribute E01Z5Vo vo, HttpServletRequest request) throws GenericException{
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Map<String,String> vMap = ValidateUtil.validAll(vo);
        if(StringUtils.isBlank(vo.getName()) || vo.getName().length()<1 || vo.getName().length()>15){
            returnMap.put("message","数据验证不通过");
            returnMap.put("code",-1);
            return returnMap;
        }
        E01Z5 entity = e01z5Service.getByPK(vo.getId());
        if(entity==null){
            returnMap.put("message","数据不存在");
            returnMap.put("code",-1);
            return returnMap;
        }
        try{
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            BeanUtils.copyProperties(vo, entity);
            EntityWrapper.wrapperUpdateBaseProperties(entity,details);
            e01z5Service.update(entity);
            returnMap.put("code",1);
        }catch (Exception e){
            logger.error(e,e);
            returnMap.put("code",-1);
        }
        return returnMap;
    }

    /**
     * 租户注销
     * @param id
     * @return
     */
    @RequiresPermissions("dajs:*")
    @RequestMapping("/delete/{id}")
    public @ResponseBody Map<String,Object> delete(@PathVariable("id") String id) throws GenericException{
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            e01z5Service.deleteByPK(id);
            returnMap.put("code",1);
        }catch (Exception e){
            logger.error(e,e);
            returnMap.put("code",-1);
        }

        return returnMap;
    }
}