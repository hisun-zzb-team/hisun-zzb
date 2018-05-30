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
import com.hisun.saas.sys.admin.dzda.entity.ECatalogTypeInfo;
import com.hisun.saas.sys.admin.dzda.service.ECatalogTypeService;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a32.entity.A32;
import com.hisun.saas.zzb.dzda.a32.service.A32Service;
import com.hisun.saas.zzb.dzda.a32.vo.A32Vo;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.exchange.A38ExcelExchange;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.a38.vo.A38ExcelVo;
import com.hisun.saas.zzb.dzda.a38.vo.A38Vo;
import com.hisun.saas.zzb.dzda.a52.entity.A52;
import com.hisun.saas.zzb.dzda.a52.service.A52Service;
import com.hisun.saas.zzb.dzda.a52.vo.A52Vo;
import com.hisun.saas.zzb.dzda.e01z2.entity.E01Z2;
import com.hisun.saas.zzb.dzda.e01z2.service.E01z2Service;
import com.hisun.saas.zzb.dzda.e01z2.vo.E01z2Vo;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.saas.zzb.dzda.mlcl.vo.E01Z1ExcelVo;
import com.hisun.saas.zzb.dzda.mlcl.vo.E01Z1Vo;
import com.hisun.saas.zzb.dzda.zrzc.Constants;
import com.hisun.saas.zzb.dzda.zrzc.entity.E01Z5;
import com.hisun.saas.zzb.dzda.zrzc.entity.E01Z7;
import com.hisun.saas.zzb.dzda.zrzc.exchange.DacdExcelExchange;
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
import java.io.*;
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

    @Resource
    private E01Z1Service e01Z1Service;

    @Resource
    private A32Service a32Service;

    @Resource
    private A52Service a52Service;

    @Resource
    private E01z2Service e01z2Service;

    @Resource
    private ECatalogTypeService eCatalogTypeService;

    @Resource
    DacdExcelExchange dacdExcelExchange;

    @Resource
    A38ExcelExchange a38ExcelExchange;

    @Value("${sys.upload.absolute.path}")
    private String uploadBasePath;


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
    Map<String, Object> save(@ModelAttribute E01Z7Vo vo, HttpServletRequest req, HttpServletResponse resp
                             ) throws GenericException {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Map<String, String> vMap = ValidateUtil.validAll(vo);
        if (vMap.size() > 0) {
            returnMap.put("message", "数据验证不通过");
            returnMap.put("code", -1);
            return returnMap;
        }
        try {
            String a38IdString = vo.getNameContent();
            String[] a38ids = a38IdString.split(",");
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            E01Z7 entity = new E01Z7();
            String destPath = uploadBasePath + Constants.DACD_STORE_PATH + "e" + File.separator + UUIDUtil.getUUID() + File.separator;//excel存放路径
            new File(destPath).mkdirs();
            String filePath = "";
            String fileName = "";
            String zipFileName = vo.getE01Z704A();
            if(StringUtils.isNotEmpty(vo.getE01Z701())){
                zipFileName = zipFileName+" "+vo.getE01Z701();
            }

            for (String a38Id:a38ids){
                filePath = getFilePath(a38Id,destPath);
                entity = new E01Z7();
                A38 a38 = a38Service.getByPK(a38Id);
                a38.setA3821(vo.getE01Z704A());
                a38.setZcyy(vo.getE01Z731());
                a38.setJbrId(details.getUserid());
                a38.setJbrXm(details.getRealname());
                a38.setA3817(DateUtil.formatDefaultDate(new Date()));
                a38Service.update(a38);
                fileName=a38.getA0101();
                if(StringUtils.isNotEmpty(a38.getA0107())){
                    fileName=fileName+"("+a38.getA0107()+")";
                }
                ConvertUtils.register(new DateConverter(null), java.util.Date.class);
                BeanUtils.copyProperties(entity, vo);
                entity.setE01Z701(StringUtils.isNotBlank(vo.getE01Z701())? DateUtil.parseDefaultDate(vo.getE01Z701()) :null );
                entity.setE01Z727(StringUtils.isNotBlank(vo.getE01Z727())? DateUtil.parseDefaultDate(vo.getE01Z727()) :null );
                entity.setA38(a38);
                entity.setName(a38.getA0101());
                entity.setE01Z717(details.getRealname());
                entity.setFileName(fileName);
                entity.setFilePath(filePath);
                entity.setId(null);
                EntityWrapper.wrapperSaveBaseProperties(entity, details);
                e01z7Service.save(entity);
            }

            if(a38ids.length>1){
                returnMap.put("destPath", destPath);
                returnMap.put("zipFileName", zipFileName);
            }else if(a38ids.length==1){
                returnMap.put("filePath", filePath);
                returnMap.put("fileName", fileName);
            }
            returnMap.put("a38IdsLength", a38ids.length);
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
    @RequestMapping("/download")
    public void download(
            @RequestParam(value="filePath",required = true) String filePath
            ,@RequestParam(value="a38IdsLength",required = true) int a38IdsLength
            ,@RequestParam(value="destPath",required = true) String destPath
            ,@RequestParam(value="fileName",required = true) String fileName
            ,@RequestParam(value="zipFileName",required = true) String zipFileName
            , HttpServletResponse resp){
        String zipFilePath = uploadBasePath+Constants.DACD_STORE_PATH+ UUIDUtil.getUUID()+".zip";
        try {
            if(a38IdsLength>1){

                CompressUtil.zip(zipFilePath, destPath, "转递文件");
                resp.setContentType("multipart/form-data");
                resp.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoderUtil.encode("转递文件" +"("+zipFileName+")" + ".zip"));
                OutputStream output = resp.getOutputStream();
                FileInputStream fileInputStream = new FileInputStream(new File(zipFilePath));
                byte[] buffer = new byte[8192];
                int length;
                while ((length = fileInputStream.read(buffer)) != -1) {
                    output.write(buffer, 0, length);
                }
                output.flush();
                output.close();
                fileInputStream.close();
            }else if(a38IdsLength==1){
                resp.setContentType("multipart/form-data");
                resp.setHeader("Content-Disposition", "attachment;fileName="+URLEncoderUtil.encode(fileName+".xlsx"));
                OutputStream output = resp.getOutputStream();
                FileInputStream fileInputStream = new FileInputStream(new File(filePath));
                byte[] buffer = new byte[8192];
                int length;
                while ((length = fileInputStream.read(buffer)) != -1) {
                    output.write(buffer, 0, length);
                }
                output.flush();
                output.close();
                fileInputStream.close();
            }
        }catch (Exception e) {
            logger.error(e, e);
        }finally {
            if(a38IdsLength>1){
                File file = new File(filePath);
                if(file.exists()){
                    file.delete();
                }
            }
        }
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
    public ModelAndView plAddMlcl(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,@RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
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
    public ModelAndView plGetA38List(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,@RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                                     String a0101Query, String gbztCodeQuery, String daztCodeQuery, String gbztContentQuery,
                                     String daztContentQuery){

        Map<String, Object> map = Maps.newHashMap();
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" sjzt = :sjzt ", "sjzt", "1"));
        if(StringUtils.isNotBlank(a0101Query)){
            query.add(CommonRestrictions.and(" a0101 like :a0101 ", "a0101", "%"+a0101Query+"%"));
        }
        if(StringUtils.isNotBlank(gbztCodeQuery)){
            query.add(CommonRestrictions.and(" gbztCode = :gbztCode ", "gbztCode", gbztCodeQuery));
            query.add(CommonRestrictions.and(" gbztContent = :gbztContent ", "gbztContent", gbztContentQuery));
        }

        if(StringUtils.isNotBlank(daztCodeQuery)){
            query.add(CommonRestrictions.and(" daztCode = :daztCode ", "daztCode", daztCodeQuery));
            query.add(CommonRestrictions.and(" daztContent = :daztContent ", "daztContent", daztContentQuery));
        }

        Long total = a38Service.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.desc("smxh"));
        //orderBy.add(CommonOrder.asc("a0101"));
        List<A38> resultList = a38Service.list(query,orderBy,pageNum,pageSize);
        PagerVo<A38> pager = new PagerVo<A38>(resultList, total.intValue(), pageNum, pageSize);
        map.put("pager",pager);
      //  map.put("list",resultList);

        return new ModelAndView("saas/zzb/dzda/zrzc/e01z7/xzgbTable",map);
    }

    @RequestMapping(value = "/down")
    @RequiresPermissions("cysq:*")
    public void templateDown(String id, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        E01Z7 entity = e01z7Service.getByPK(id);
        try {
            resp.setContentType("multipart/form-data");
            resp.setHeader("Content-Disposition", "attachment;fileName="+URLEncoderUtil.encode(entity.getFileName()+".xlsx"));
            OutputStream output = resp.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(new File(entity.getFilePath()));
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            output.flush();
            output.close();
            fileInputStream.close();
        }catch (Exception e) {
            logger.error(e, e);
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

    public String getFilePath(String id, String destPath){
        CommonConditionQuery query = new CommonConditionQuery();
        CommonOrderBy orderBy = new CommonOrderBy();
        String filePath = "";
        A38ExcelVo a38ExcelVo = new A38ExcelVo();
        Map<String,Object> map = new HashMap();
        int xml = 2;
        int dml = 6;
        try {
            //基本信息
            A38 a38 = a38Service.getByPK(id);
            A38Vo jbxxVo = new A38Vo();
            org.springframework.beans.BeanUtils.copyProperties(a38,jbxxVo);
            a38ExcelVo.setJbxxA38Vo(jbxxVo);


            //目录信息
            orderBy.add(CommonOrder.asc("sort"));
            List<ECatalogTypeInfo> eCatalogTypeInfos = eCatalogTypeService.list(query, orderBy);
            E01Z1ExcelVo e01Z1ExcelVo = new E01Z1ExcelVo();
            E01Z1Vo e01Z1Vo;

            for(ECatalogTypeInfo eCatalogTypeInfo : eCatalogTypeInfos){
                query=new CommonConditionQuery();
                orderBy = new CommonOrderBy();
                query.add(CommonRestrictions.and(" a38_id = :a38Id ", "a38Id", id));
                query.add(CommonRestrictions.and(" e01z101b = :e01z101b ","e01z101b",eCatalogTypeInfo.getCatalogCode()));
                orderBy.add(CommonOrder.asc("e01Z104"));
                List<E01Z1> resultList = e01Z1Service.list(query,orderBy);
                List<E01Z1Vo> e01Z1Vos=new ArrayList<>();
                for(E01Z1 e01Z1:resultList){
                    e01Z1Vo = new E01Z1Vo();
                    org.apache.commons.beanutils.BeanUtils.copyProperties(e01Z1Vo,e01Z1);
                    String date = e01Z1Vo.getE01Z117();
                    if(date.length()>=4){
                        e01Z1Vo.setYear(date.substring(date.length()-4,date.length()));
                        if(date.length()>=6){
                            e01Z1Vo.setMonth(date.substring(date.length()-6,date.length()-4));
                            if(date.length()>6){
                                e01Z1Vo.setDay(date.substring(date.length()-8,date.length()-6));
                            }
                        }
                    }else {
                        e01Z1Vo.setYear(date);
                    }
                    e01Z1Vos.add(e01Z1Vo);
                }
                if("010".equals(eCatalogTypeInfo.getCatalogCode())){
                    map.put("jlcl",e01Z1Vos.size());
                    e01Z1ExcelVo.setJlcl(e01Z1Vos);
                }else if("020".equals(eCatalogTypeInfo.getCatalogCode())){
                    map.put("zzcl",e01Z1Vos.size());
                    e01Z1ExcelVo.setZzcl(e01Z1Vos);
                }else if("030".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setJdcl(e01Z1Vos);
                    map.put("jdcl",e01Z1Vos.size());
                }else if("041".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setXlxw(e01Z1Vos);
                    map.put("xlxw",e01Z1Vos.size());
                }else if("042".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setZyzg(e01Z1Vos);
                    map.put("zyzg",e01Z1Vos.size());
                }else if("043".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setKysp(e01Z1Vos);
                    map.put("kysp",e01Z1Vos.size());
                }else if("044".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setPxcl(e01Z1Vos);
                    map.put("pxcl",e01Z1Vos.size());
                }else if("050".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setZscl(e01Z1Vos);
                    map.put("zscl",e01Z1Vos.size());
                }else if("060".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setDtcl(e01Z1Vos);
                    map.put("dtcl",e01Z1Vos.size());
                }else if("070".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setJlicl(e01Z1Vos);
                    map.put("jlicl",e01Z1Vos.size());
                }else if("080".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setCfcl(e01Z1Vos);
                    map.put("cfcl",e01Z1Vos.size());
                }else if("091".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setGzcl(e01Z1Vos);
                    map.put("gzcl",e01Z1Vos.size());
                }else if("092".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setRmcl(e01Z1Vos);
                    map.put("rmcl",e01Z1Vos.size());
                }else if("093".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setCgcl(e01Z1Vos);
                    map.put("cgcl",e01Z1Vos.size());
                }else if("094".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setDbdh(e01Z1Vos);
                    map.put("dbdh",e01Z1Vos.size());
                }else if("100".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setQtcl(e01Z1Vos);
                    map.put("qtcl",e01Z1Vos.size());
                }
            }
            a38ExcelVo.setE01Z1ExcelVo(e01Z1ExcelVo);



            //职务变动
            query=new CommonConditionQuery();
            orderBy = new CommonOrderBy();
            query.add(CommonRestrictions.and(" a38_id = :a38Id ", "a38Id", id));
            List<A52> a52List = a52Service.list(query,null);
            A52Vo a52Vo;
            A38Vo a38Vo = new A38Vo();
            List<A52Vo> a52Vos=new ArrayList<>();
            for(A52 a52:a52List){
                a52Vo = new A52Vo();
                org.apache.commons.beanutils.BeanUtils.copyProperties(a52Vo,a52);
                a52Vos.add(a52Vo);
            }
            if(a52List!=null && a52List.size()>0){
                A52 a52=a52List.get(0);
                a38Vo.setA0101(a52.getA38().getA0101());
                a38Vo.setA0104Content(a52.getA38().getA0104Content());
                a38Vo.setA0107(a52.getA38().getA0107());
                a38Vo.setA0111A(a52.getA38().getA0111A());
                a38Vo.setA52Vos(a52Vos);
            }
            a38ExcelVo.setZwbdA38Vo(a38Vo);

            //工资变动
            query=new CommonConditionQuery();
            orderBy = new CommonOrderBy();
            query.add(CommonRestrictions.and(" a38_id = :a38Id ", "a38Id", id));
            orderBy.add(CommonOrder.asc("px"));
            List<A32> a32List = a32Service.list(query,orderBy);
            A32Vo a32Vo;
            List<A32Vo> a32Vos=new ArrayList<>();
            for(A32 a32:a32List){
                a32Vo = new A32Vo();
                org.apache.commons.beanutils.BeanUtils.copyProperties(a32Vo,a32);
                a32Vos.add(a32Vo);
            }
            a38ExcelVo.setGzbdList(a32Vos);

            //材料接收
            query=new CommonConditionQuery();
            query.add(CommonRestrictions.and(" a38_id = :a38Id ", "a38Id", id));
            orderBy = new CommonOrderBy();
            List<E01Z2> cljsList = e01z2Service.list(query,null);
            E01z2Vo e01z2Vo;
            List<E01z2Vo> e01z2Vos=new ArrayList<>();
            for(E01Z2 e01z2:cljsList){
                e01z2Vo = new E01z2Vo();
                org.apache.commons.beanutils.BeanUtils.copyProperties(e01z2Vo,e01z2);
                e01z2Vos.add(e01z2Vo);
            }
            a38ExcelVo.setE01z2Vos(e01z2Vos);

            File storePathFile = new File(Constants.DACD_STORE_PATH);
            if(!storePathFile.exists()){
                storePathFile.mkdirs();
            }
            String fileName = a38.getA0101();
            if(StringUtils.isNotEmpty(a38.getA0107())){
                fileName=fileName+"("+a38.getA0107()+")";
            }
            filePath = destPath+ fileName+".xlsx";
            a38ExcelExchange.toExcel(a38ExcelVo, uploadBasePath+ Constants.DACDMB_STORE_PATH,filePath,xml, dml,map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return filePath;
    }
}