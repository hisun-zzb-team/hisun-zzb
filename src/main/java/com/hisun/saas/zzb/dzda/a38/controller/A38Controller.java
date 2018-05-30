/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a38.controller;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.entity.TombstoneEntity;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.admin.dictionary.entity.DictionaryItem;
import com.hisun.saas.sys.admin.dictionary.service.DictionaryItemService;
import com.hisun.saas.sys.admin.dzda.entity.ECatalogTypeInfo;
import com.hisun.saas.sys.admin.dzda.service.ECatalogTypeService;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a32.entity.A32;
import com.hisun.saas.zzb.dzda.a32.service.A32Service;
import com.hisun.saas.zzb.dzda.a32.vo.A32Vo;
import com.hisun.saas.zzb.dzda.a38.Constants;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.exchange.A38ExcelExchange;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.a38.vo.A38ExcelVo;
import com.hisun.saas.zzb.dzda.a38.vo.A38Vo;
import com.hisun.saas.zzb.dzda.a38.vo.WrongExcelColumn;
import com.hisun.saas.zzb.dzda.a52.entity.A52;
import com.hisun.saas.zzb.dzda.a52.service.A52Service;
import com.hisun.saas.zzb.dzda.a52.vo.A52Vo;
import com.hisun.saas.zzb.dzda.dak.vo.DakVo;
import com.hisun.saas.zzb.dzda.e01z2.entity.E01Z2;
import com.hisun.saas.zzb.dzda.e01z2.service.E01z2Service;
import com.hisun.saas.zzb.dzda.e01z2.vo.E01z2Vo;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.saas.zzb.dzda.mlcl.service.EImagesService;
import com.hisun.saas.zzb.dzda.mlcl.vo.E01Z1ExcelVo;
import com.hisun.saas.zzb.dzda.mlcl.vo.E01Z1Vo;
import com.hisun.util.StringUtils;
import com.hisun.util.URLEncoderUtil;
import com.hisun.util.UUIDUtil;
import com.hisun.util.ValidateUtil;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author liuzj {279421824@qq.com}
 */
@Controller
@RequestMapping("/zzb/dzda/a38")
public class A38Controller extends BaseController {

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
    private DictionaryItemService dictionaryItemService;

    @Resource
    A38ExcelExchange a38ExcelExchange;

    List<WrongExcelColumn> wrongExcelColumns;

    @Resource
    private EImagesService eImagesService;

    @Value("${sys.upload.absolute.path}")
    private String uploadBasePath;

    @RequiresPermissions("a38:*")
    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,@RequestParam(value = "pageSize",defaultValue = "10")int pageSize,HttpServletRequest request,
                             String dabhQuery,String smxhQuery,String a0101Query,String gbztCodeQuery,String daztCodeQuery,String gbztContentQuery,String daztContentQuery,@ModelAttribute DakVo queryVo,String queryType) throws UnsupportedEncodingException {
        Map<String,Object> model = new HashMap<String,Object>();
        HttpSession session = request.getSession();

        if(queryType!=null && queryType.equals("gaojichaxun")){//高级查询
            a0101Query =  queryVo.getA0101();
            gbztCodeQuery = queryVo.getGbztCodes();
            gbztContentQuery =  queryVo.getGbztContents();
            daztCodeQuery= queryVo.getDaztCodes();
            daztContentQuery = queryVo.getDaztContents();
            session.setAttribute("queryA38Vo",queryVo);
        } else if(queryType!=null && queryType.equals("listQuery")){//listQuery
            DakVo queryA38Vo = (DakVo)session.getAttribute("queryA38Vo");
            if(queryA38Vo ==null){
                queryA38Vo = new DakVo();
            }
            queryA38Vo.setDabh(dabhQuery);
            queryA38Vo.setSmxh(smxhQuery);
            queryA38Vo.setA0101(a0101Query);
            queryA38Vo.setGbztCodes(gbztCodeQuery);
            queryA38Vo.setGbztContents(gbztContentQuery);
            queryA38Vo.setDaztCodes(daztCodeQuery);
            queryA38Vo.setDaztContents(daztContentQuery);
            queryVo = queryA38Vo;
            session.setAttribute("queryA38Vo",queryVo);
        }else{
            DakVo queryA38Vo = (DakVo)session.getAttribute("queryA38Vo");
            if(queryA38Vo ==null){
                queryA38Vo = new DakVo();
            }
            dabhQuery = queryA38Vo.getDabh();
            smxhQuery =queryA38Vo.getSmxh();
            a0101Query =queryA38Vo.getA0101();
            gbztCodeQuery =queryA38Vo.getGbztCodes();
            gbztContentQuery =queryA38Vo.getGbztContents();
            daztCodeQuery = queryA38Vo.getDaztCodes();
            daztContentQuery =queryA38Vo.getDaztContents();
            queryVo = queryA38Vo;
        }

        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        List<A38> resultList = a38Service.list(this.a38Service.getGjcxHql(queryVo,userLoginDetails), new ArrayList<Object>(), pageNum, pageSize);
        int total =   a38Service.list(this.a38Service.getGjcxHql(queryVo,userLoginDetails), new ArrayList<Object>(),1,100000).size();
        List<A38Vo> a38Vos = new ArrayList<A38Vo>();
        A38Vo vo = new A38Vo();
        for(A38 entity : resultList){
            vo = new A38Vo();
            BeanUtils.copyProperties(entity,vo);
            if(entity.getUpdateDate()==null || entity.getUpdateDate().equals("")){
                vo.setUpdateDateByShow(entity.getCreateDate());
                vo.setUpdateUserNameByShow(entity.getCreateUserName());
            }else {
                vo.setUpdateDateByShow(entity.getUpdateDate());
                vo.setUpdateUserNameByShow(entity.getUpdateUserName());
            }
            a38Vos.add(vo);
        }
        PagerVo<A38Vo> pager = new PagerVo<A38Vo>(a38Vos, total, pageNum, pageSize);
        model.put("dabhQuery",dabhQuery);
        model.put("smxhQuery",smxhQuery);
        model.put("a0101Query",a0101Query);
        model.put("gbztCodeQuery",gbztCodeQuery);
        model.put("daztCodeQuery",daztCodeQuery);
        model.put("gbztContentQuery",gbztContentQuery);
        model.put("daztContentQuery",daztContentQuery);
        model.put("pager",pager);
        return new ModelAndView("saas/zzb/dzda/a38/list",model);
    }

    @RequiresPermissions("a38:*")
    @RequestMapping("/shList")
    public ModelAndView shList(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,@RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                               String dabhQuery,String smxhQuery,String a0101Query,String gbztCodeQuery,String daztCodeQuery,String gbztContentQuery,String daztContentQuery) throws UnsupportedEncodingException {
        Map<String,Object> model = new HashMap<String,Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" sjzt = :sjzt ", "sjzt", "0"));
        if(StringUtils.isNotEmpty(dabhQuery)){
            query.add(CommonRestrictions.and(" dabh like :dabhQuery ", "dabhQuery","%"+dabhQuery+"%"));
        }
        if(StringUtils.isNotEmpty(smxhQuery)){
            query.add(CommonRestrictions.and(" smxh like :smxhQuery ", "smxhQuery","%"+smxhQuery+"%"));
        }
        if(StringUtils.isNotEmpty(a0101Query)){
            query.add(CommonRestrictions.and(" a0101 like :a0101Query ", "a0101Query","%"+a0101Query+"%"));
        }
        if(StringUtils.isNotEmpty(gbztCodeQuery)){
            String str[] = gbztCodeQuery.split(",");
            List gbztCodeList =  Arrays.asList(str);
            query.add(CommonRestrictions.and(" gbztCode in (:gbztCodeList)", "gbztCodeList",gbztCodeList));
        }
        if(StringUtils.isNotEmpty(daztCodeQuery)){
            String str[] = daztCodeQuery.split(",");
            List daztCodeList =  Arrays.asList(str);
            query.add(CommonRestrictions.and(" daztCode in (:daztCodeList) ", "daztCodeList",daztCodeList));
        }
        Long total = a38Service.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.desc("smxh"));
        orderBy.add(CommonOrder.asc("a0101"));
        List<A38> resultList = a38Service.list(query,orderBy,pageNum,pageSize);
        List<A38Vo> a38Vos = new ArrayList<A38Vo>();
        A38Vo vo = new A38Vo();
        for(A38 entity : resultList){
            vo = new A38Vo();
            BeanUtils.copyProperties(entity,vo);
            vo.setUpdateDateByShow(entity.getUpdateDate());
            vo.setUpdateUserNameByShow(entity.getUpdateUserName());
            a38Vos.add(vo);
        }
        PagerVo<A38Vo> pager = new PagerVo<A38Vo>(a38Vos, total.intValue(), pageNum, pageSize);
        model.put("dabhQuery",dabhQuery);
        model.put("smxhQuery",smxhQuery);
        model.put("a0101Query",a0101Query);
        model.put("gbztCodeQuery",gbztCodeQuery);
        model.put("daztCodeQuery",daztCodeQuery);
        model.put("gbztContentQuery",gbztContentQuery);
        model.put("daztContentQuery",daztContentQuery);
        model.put("pager",pager);
        return new ModelAndView("saas/zzb/dzda/a38/shList",model);
    }
    /**
     *
     * @param listType shList为审核档案
     * @return
     */
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/editManage")
    public ModelAndView editManage(String id,String listType){
        Map<String, Object> map = Maps.newHashMap();
        A38 a38 = a38Service.getByPK(id);
        map.put("id",id);
        map.put("a0101",a38.getA0101());
        map.put("listType",listType);
        return new ModelAndView("saas/zzb/dzda/a38/manage",map);
    }

    /**
     *
     * @param listType shList为审核档案
     * @return
     */
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/add")
    public ModelAndView add(String listType){
        Map<String, Object> map = Maps.newHashMap();
        map.put("listType",listType);
        return new ModelAndView("saas/zzb/dzda/a38/add",map);
    }

    @RequestMapping(value = "/smxh/check")
    public @ResponseBody Map<String, Object> smxhCheck(
            @RequestParam("smxh") String smxh,@RequestParam(value="id",required=false)String id) throws GenericException {
        Map<String, Object> map = Maps.newHashMap();
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" smxh = :smxh ", "smxh", smxh));
        if(StringUtils.isNotBlank(id)){
            A38 a38 = a38Service.getByPK(id);
            if(StringUtils.equalsIgnoreCase(a38.getSmxh(), smxh)){
                map.put("success", true);
            }else{
                Long total = a38Service.count(query);
                if(total>=1){
                    map.put("success", false);
                }else{
                    map.put("success", true);
                }
            }
        }else{
            Long total = a38Service.count(query);
            if(total>=1){
                map.put("success", false);
            }else{
                map.put("success", true);
            }
        }
        return map;
    }
    @RequiresPermissions("a38:*")
    @RequestMapping("/save")
    public @ResponseBody Map<String,Object> save(@ModelAttribute A38Vo vo) throws GenericException {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Map<String,String> vMap = ValidateUtil.validAll(vo);
        if(vMap.size()>0){
            returnMap.put("message","数据验证不通过");
            returnMap.put("code",-1);
            return returnMap;
        }
        try{
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            A38 entity = new A38();
            BeanUtils.copyProperties(vo, entity);
            entity.setId(null);
//            entity.setSjzt("1");
            EntityWrapper.wrapperSaveBaseProperties(entity,details);
            a38Service.save(entity);
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

    @RequiresPermissions("a38:*")
    @RequestMapping("/ajax/edit")
    public ModelAndView edit(@RequestParam(value="id",required=true)String id){
        Map<String,Object> model = new HashMap<String,Object>();
        A38 a38 = a38Service.getByPK(id);
        A38Vo vo = new A38Vo();
        BeanUtils.copyProperties(a38,vo);
        model.put("vo", vo);
        return new ModelAndView("saas/zzb/dzda/a38/edit", model);
    }

    @RequiresPermissions("a38:*")
    @RequestMapping("/update")
    public @ResponseBody Map<String,Object> update(@ModelAttribute A38Vo vo, HttpServletRequest request) throws GenericException{
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Map<String,String> vMap = ValidateUtil.validAll(vo);
        if(StringUtils.isBlank(vo.getA0101()) || vo.getA0101().length()<1 || vo.getA0101().length()>15){
            returnMap.put("message","数据验证不通过");
            returnMap.put("code",-1);
            return returnMap;
        }
        A38 entity = a38Service.getByPK(vo.getId());
        if(entity==null){
            returnMap.put("message","数据不存在");
            returnMap.put("code",-1);
            return returnMap;
        }
        try{
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            BeanUtils.copyProperties(vo, entity);
            EntityWrapper.wrapperUpdateBaseProperties(entity,details);
            a38Service.update(entity);
            returnMap.put("code",1);
        }catch (Exception e){
            logger.error(e,e);
            returnMap.put("code",-1);
        }
        return returnMap;
    }

    @RequiresPermissions("a38:*")
    @RequestMapping("/update/Sjzt")
    public @ResponseBody Map<String,Object> updateSjzt(String a38Ids,String sjzt) throws GenericException{
        Map<String,Object> returnMap = new HashMap<String,Object>();
        if(StringUtils.isBlank(a38Ids)){
            returnMap.put("message","主键为空");
            returnMap.put("code",-1);
            return returnMap;
        }
        try{
            String[] a38IdArr = a38Ids.split(",");
            for(String id : a38IdArr) {
                A38 entity = a38Service.getByPK(id);
                if (entity == null) {
                    returnMap.put("message", "数据不存在");
                    returnMap.put("code", -1);
                    return returnMap;
                }

                UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
                EntityWrapper.wrapperUpdateBaseProperties(entity, details);
                entity.setSjzt(sjzt);
                a38Service.update(entity);
            }
            returnMap.put("code",1);
        }catch (Exception e){
            logger.error(e,e);
            returnMap.put("code",-1);
        }
        return returnMap;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequiresPermissions("a38:*")
    @RequestMapping("/delete/{id}")
    public @ResponseBody Map<String,Object> delete(@PathVariable("id") String id) throws GenericException{
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            A38 a38 = this.a38Service.getByPK(id);
            this.eImagesService.deleteEImagesAndFileByA38(a38);
            a38Service.delete(a38);
            returnMap.put("code",1);
        }catch (Exception e){
            logger.error(e,e);
            returnMap.put("code",-1);
        }

        return returnMap;
    }

    @RequiresPermissions("a38:dakList ")
    @RequestMapping(value = "/ajax/gjcx")
    public ModelAndView gjcx(HttpServletRequest request,String id,String listType){
        HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<String, Object>();
        DakVo queryA38Vo = (DakVo)session.getAttribute("queryA38Vo");
        map.put("vo",queryA38Vo);
        map.put("queryType","a38List");
        return new ModelAndView("saas/zzb/dzda/dak/gjcxPage",map);
    }

    @RequiresPermissions("a38:*")
    @RequestMapping("/download/{id}")
    public void download(@PathVariable("id") String id, HttpServletResponse resp){
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
            BeanUtils.copyProperties(a38,jbxxVo);
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

            File storePathFile = new File(Constants.JBXX_STORE_PATH);
            if(!storePathFile.exists()){
                storePathFile.mkdirs();
            }
            filePath = uploadBasePath+Constants.JBXX_STORE_PATH+ UUIDUtil.getUUID()+".xlsx";
            a38ExcelExchange.toExcel(a38ExcelVo, uploadBasePath+ Constants.JBXXMB_STORE_PATH,filePath,xml, dml,map);
            resp.setContentType("multipart/form-data");
            resp.setHeader("Content-Disposition", "attachment;fileName="+URLEncoderUtil.encode(a38.getA0101()+".xlsx"));
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
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }finally {
            File file = new File(filePath);
            if(file.exists()){
                file.delete();
            }
        }
    }

    @RequiresPermissions("a38:*")
    @RequestMapping("/uploadFile")
    public @ResponseBody Map<String,Object> uploadFile (String id , @RequestParam(value="zbdaFile",required = false) MultipartFile zbdaFile , HttpServletResponse resp) throws IOException {
        Map<String,Object> map = new HashMap<>();
        boolean isRight = false;
        List<WrongExcelColumn> wrongExcelColumns = new ArrayList<>();
        Map<String,Object> returnMap = new HashMap<>();
        String filePath = "";
        File storePathFile = new File(Constants.JBXX_STORE_PATH);
        if(!storePathFile.exists()) storePathFile.mkdirs();
        filePath = uploadBasePath+Constants.JBXX_STORE_PATH+ UUIDUtil.getUUID()+".xlsx";
        File file = new File(filePath);
        InputStream inputStream = null;
        OutputStream output = null;
        try {
            inputStream = zbdaFile.getInputStream();
            output = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                inputStream.close();
            }
            if(output!=null){
                output.close();
            }
        }
        String tempFile = uploadBasePath+Constants.JBXXMB_STORE_PATH;
        A38ExcelVo a38ExcelVo = new A38ExcelVo();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        try {
            a38ExcelVo = (A38ExcelVo) a38ExcelExchange.fromExcel(A38ExcelVo.class,tempFile,filePath);
            WrongExcelColumn wrongExcelColumn;
            boolean a38Flag = false;//判断是否存在非法数据
            if(a38ExcelVo!=null){
                int sum = 0;
                boolean flag = false;//判断是否存在非法数据
                boolean flag1 = false;//判断必填数据是否全为空
                //新增档案
                A38Vo jbxxA38Vo = a38ExcelVo.getJbxxA38Vo();
                A38 a38 = new A38();

                if(StringUtils.isEmpty(jbxxA38Vo.getA0101())){
                    wrongExcelColumn = new WrongExcelColumn();
                    wrongExcelColumn.setLines("C"+jbxxA38Vo.getRow());
                    wrongExcelColumn.setReason("姓名不能为空");
                    wrongExcelColumn.setWrongExcel("基本信息");
                    wrongExcelColumns.add(wrongExcelColumn);
                    a38Flag = true;
                    sum++;
                    a38Flag = true;
                }

                if (isNotDate(jbxxA38Vo.getA0107())) {
                    wrongExcelColumn = new WrongExcelColumn();
                    wrongExcelColumn.setLines("F"+jbxxA38Vo.getRow());
                    wrongExcelColumn.setReason("生日日期格式错误");
                    wrongExcelColumn.setWrongExcel("基本信息");
                    wrongExcelColumns.add(wrongExcelColumn);
                    a38Flag = true;
                    sum++;
                    a38Flag = true;
                }

                if(!a38Flag){

                    String a0104Content = jbxxA38Vo.getA0104Content();
                    jbxxA38Vo.setA0104(getDictionaryItem(a0104Content,"GB/T2261.1-2003"));
                    String gbztContent = jbxxA38Vo.getGbztContent();
                    jbxxA38Vo.setGbztCode(getDictionaryItem(gbztContent,"SAN_GBZT"));

                    BeanUtils.copyProperties(jbxxA38Vo, a38);
                    a38.setId(null);
                    a38.setSjzt("1");
                    EntityWrapper.wrapperSaveBaseProperties(a38,details);
                    id = a38Service.save(a38);
                    if(StringUtils.isNotEmpty(id)){
                        a38.setId(id);
                        //新增职务变动
                        A38Vo a38VoForA52 = a38ExcelVo.getZwbdA38Vo();
                        if(a38VoForA52!=null&&a38VoForA52.getA52Vos().size()>0){
                            List<A52Vo> a52Vos = a38VoForA52.getA52Vos();
                            for(int i=0;i<a52Vos.size();i++){
                                flag = false;//判断是否存在非法数据
                                sum=0;
                                A52 a52 = new A52();
                                A52Vo a52Vo = a52Vos.get(i);
                                Integer oldPxInteger=a52Service.getMaxSort(a52Vo.getId());
                                if(StringUtils.isEmpty(a52Vo.getA5204())){
                                    wrongExcelColumn = new WrongExcelColumn();
                                    wrongExcelColumn.setLines("C"+a52Vo.getRow());
                                    wrongExcelColumn.setReason("部门名称不能为空");
                                    wrongExcelColumn.setWrongExcel("职务变动登记表");
                                    wrongExcelColumns.add(wrongExcelColumn);
                                    flag = true;
                                    sum++;

                                }
                                if(A38Controller.isNotDate(a52Vo.getA5227In())){
                                    wrongExcelColumn = new WrongExcelColumn();
                                    wrongExcelColumn.setLines("A"+a52Vo.getRow());
                                    wrongExcelColumn.setReason("任职时间格式错误");
                                    wrongExcelColumn.setWrongExcel("职务变动登记表");
                                    wrongExcelColumns.add(wrongExcelColumn);
                                    flag = true;
                                    sum++;
                                }
                                if(A38Controller.isNotDate(a52Vo.getA5227Out())){
                                    wrongExcelColumn = new WrongExcelColumn();
                                    wrongExcelColumn.setLines("B"+a52Vo.getRow());
                                    wrongExcelColumn.setReason("免职时间格式错误");
                                    wrongExcelColumn.setWrongExcel("职务变动登记表");
                                    wrongExcelColumns.add(wrongExcelColumn);
                                    flag = true;
                                    sum++;
                                }
                                if(StringUtils.isEmpty(a52Vo.getA5204())&&StringUtils.isEmpty(a52Vo.getA5227In())&&StringUtils.isEmpty(a52Vo.getA5227Out())){
                                    flag1 = true;
                                }

                                if(flag){
                                    continue;
                                }
                                BeanUtils.copyProperties(a52Vo,a52);
                                a52.setA38(a38);
                                a52.setPx(oldPxInteger);
                                EntityWrapper.wrapperSaveBaseProperties(a52,details);
                                a52Service.save(a52);
                            }
                        }

                        //添加工资变动记录
                        List<A32Vo> gzbdList = a38ExcelVo.getGzbdList();
                        if(gzbdList.size()>0){
                            for(int i=0;i<gzbdList.size();i++){
                                flag = false;//判断是否存在非法数据
                                sum=0;
                                A32 a32 = new A32();
                                A32Vo a32Vo = gzbdList.get(i);
                                Integer oldPxInteger=a32Service.getMaxSort(a32Vo.getId());

                                if(StringUtils.isEmpty(a32Vo.getGzbm())){
                                    wrongExcelColumn = new WrongExcelColumn();
                                    wrongExcelColumn.setLines("A"+a32Vo.getRow());
                                    wrongExcelColumn.setReason("工作部门不能为空");
                                    wrongExcelColumn.setWrongExcel("工资变动登记表");
                                    wrongExcelColumns.add(wrongExcelColumn);
                                    flag = true;
                                    sum++;
                                }
                                if(A38Controller.isNotDate(a32Vo.getA3207())){
                                    wrongExcelColumn = new WrongExcelColumn();
                                    wrongExcelColumn.setLines("E"+a32Vo.getRow());
                                    wrongExcelColumn.setReason("日期格式错误");
                                    wrongExcelColumn.setWrongExcel("工资变动登记表");
                                    wrongExcelColumns.add(wrongExcelColumn);
                                    flag = true;
                                    sum++;
                                }

                                if(StringUtils.isEmpty(a32Vo.getGzbm())){
                                    flag1 = true;
                                }

                                if(flag){
                                    if(flag1){
                                        for(int j=0;j<sum;j++){
                                            wrongExcelColumns.remove(wrongExcelColumns.size()-1);
                                        }
                                    }
                                    isRight = true;
                                    continue;
                                }

                                BeanUtils.copyProperties(a32Vo,a32);
                                a32.setA38(a38);
                                a32.setPx(oldPxInteger);
                                EntityWrapper.wrapperSaveBaseProperties(a32,details);
                                a32Service.save(a32);
                            }
                        }

                        //添加材料接收记录
                        List<E01z2Vo> e01z2Vos = a38ExcelVo.getE01z2Vos();
                        if(e01z2Vos.size()>0){
                            for(int i=0;i<e01z2Vos.size();i++){
                                flag = false;//判断是否存在非法数据
                                sum=0;
                                E01Z2 e01z2 = new E01Z2();
                                E01z2Vo e01z2Vo = e01z2Vos.get(i);
                                Integer oldPxInteger=e01z2Service.getMaxSort(e01z2Vo.getId());

                                if(StringUtils.isEmpty(e01z2Vo.getE01Z204A())){
                                    wrongExcelColumn = new WrongExcelColumn();
                                    wrongExcelColumn.setLines("A"+e01z2Vo.getRow());
                                    wrongExcelColumn.setReason("来件单位不能为空");
                                    wrongExcelColumn.setWrongExcel("材料接收表");
                                    wrongExcelColumns.add(wrongExcelColumn);
                                    flag = true;
                                    sum++;
                                }
                                if(StringUtils.isEmpty(e01z2Vo.getE01Z221A())){
                                    wrongExcelColumn = new WrongExcelColumn();
                                    wrongExcelColumn.setLines("E"+e01z2Vo.getRow());
                                    wrongExcelColumn.setReason("材料名称不能为空");
                                    wrongExcelColumn.setWrongExcel("材料接收表");
                                    wrongExcelColumns.add(wrongExcelColumn);
                                    flag = true;
                                    sum++;
                                }
                                if(A38Controller.isNotDate(e01z2Vo.getE01Z201())){
                                    wrongExcelColumn = new WrongExcelColumn();
                                    wrongExcelColumn.setLines("B"+e01z2Vo.getRow());
                                    wrongExcelColumn.setReason("收件日期格式错误");
                                    wrongExcelColumn.setWrongExcel("材料接收表");
                                    wrongExcelColumns.add(wrongExcelColumn);
                                    flag = true;
                                    sum++;
                                }
                                if(A38Controller.isNotDate(e01z2Vo.getE01Z227())){
                                    wrongExcelColumn = new WrongExcelColumn();
                                    wrongExcelColumn.setLines("G"+e01z2Vo.getRow());
                                    wrongExcelColumn.setReason("材料制成日期格式错误");
                                    wrongExcelColumn.setWrongExcel("材料接收表");
                                    wrongExcelColumns.add(wrongExcelColumn);
                                    flag = true;
                                    sum++;
                                }

                                if(StringUtils.isEmpty(e01z2Vo.getE01Z204A())&&StringUtils.isEmpty(e01z2Vo.getE01Z221A())
                                        &&StringUtils.isEmpty(e01z2Vo.getE01Z201())&&StringUtils.isEmpty(e01z2Vo.getE01Z227())){
                                    flag1 = true;
                                }

                                if(flag){
                                    if(flag1){
                                        for(int j=0;j<sum;j++){
                                            wrongExcelColumns.remove(wrongExcelColumns.size()-1);
                                        }
                                    }
                                    isRight = true;
                                    continue;
                                }

                                String e01Z237Content = e01z2Vo.getE01Z237Content();
                                e01z2Vo.setE01Z237(getDictionaryItem(e01Z237Content,"CLCLBS-2018"));
                                String e01Z244Content = e01z2Vo.getE01Z244Content();
                                e01z2Vo.setE01Z244(getDictionaryItem(e01Z244Content,"SFBS-2018"));

                                BeanUtils.copyProperties(e01z2Vo,e01z2);
                                e01z2.setA38(a38);
                                e01z2.setE01Z214(oldPxInteger);
                                EntityWrapper.wrapperSaveBaseProperties(e01z2,details);
                                e01z2Service.save(e01z2);
                            }
                        }

                        returnMap.put("isRight",isRight);
                        returnMap.put("wrongExcelColumns",wrongExcelColumns);
                        //添加目录信息及材料
                        E01Z1ExcelVo e01Z1ExcelVo = a38ExcelVo.getE01Z1ExcelVo();
                        if(e01Z1ExcelVo!=null){
                            returnMap = addE01z1(e01Z1ExcelVo.getJlcl(),"jlcl",id,returnMap);
                            returnMap = addE01z1(e01Z1ExcelVo.getZzcl(),"zzcl",id,returnMap);
                            returnMap = addE01z1(e01Z1ExcelVo.getJdcl(),"jdcl",id,returnMap);
                            returnMap = addE01z1(e01Z1ExcelVo.getXlxw(),"xlxw",id,returnMap);
                            returnMap = addE01z1(e01Z1ExcelVo.getZyzg(),"zyzg",id,returnMap);
                            returnMap = addE01z1(e01Z1ExcelVo.getKysp(),"kysp",id,returnMap);
                            returnMap = addE01z1(e01Z1ExcelVo.getPxcl(),"pxcl",id,returnMap);
                            returnMap = addE01z1(e01Z1ExcelVo.getZscl(),"zscl",id,returnMap);
                            returnMap = addE01z1(e01Z1ExcelVo.getDtcl(),"dtcl",id,returnMap);
                            returnMap = addE01z1(e01Z1ExcelVo.getJlicl(),"jlicl",id,returnMap);
                            returnMap = addE01z1(e01Z1ExcelVo.getCfcl(),"cfcl",id,returnMap);
                            returnMap = addE01z1(e01Z1ExcelVo.getGzcl(),"gzcl",id,returnMap);
                            returnMap = addE01z1(e01Z1ExcelVo.getRmcl(),"rmcl",id,returnMap);
                            returnMap = addE01z1(e01Z1ExcelVo.getCgcl(),"cgcl",id,returnMap);
                            returnMap = addE01z1(e01Z1ExcelVo.getDbdh(),"dbdh",id,returnMap);
                            returnMap = addE01z1(e01Z1ExcelVo.getQtcl(),"qtcl",id,returnMap);
                        }
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            file.delete();
        }
        isRight= (boolean) returnMap.get("isRight");
        wrongExcelColumns = (List<WrongExcelColumn>) returnMap.get("wrongExcelColumns");
        map.put("success",true);
        if(isRight){
            this.wrongExcelColumns = wrongExcelColumns;
            map.put("isWrong",true);
        }else {
            map.put("isWrong",false);
        }
        return map;
    }

    @RequiresPermissions("a38:*")
    @RequestMapping("/dagl/download")
    public void download( HttpServletResponse resp){
        CommonConditionQuery query = new CommonConditionQuery();
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.desc("smxh"));
        orderBy.add(CommonOrder.asc("a0101"));
        List<A38> resultList = a38Service.list(query,orderBy);
        A38Vo vo;
        List<A38Vo> a38Vos=new ArrayList<>();
        String filePath = "";
        try {
            for(A38 a38:resultList){
                vo = new A38Vo();
                org.apache.commons.beanutils.BeanUtils.copyProperties(vo,a38);
                a38Vos.add(vo);
            }
            File storePathFile = new File(Constants.DAGL_STORE_PATH);
            if(!storePathFile.exists()) storePathFile.mkdirs();
            filePath = uploadBasePath+Constants.DAGL_STORE_PATH+ UUIDUtil.getUUID()+".xlsx";
            a38ExcelExchange.toExcelByManyPojo(a38Vos, uploadBasePath+Constants.DAGLMB_STORE_PATH,filePath);
            resp.setContentType("multipart/form-data");
            resp.setHeader("Content-Disposition", "attachment;fileName="+ URLEncoderUtil.encode("档案管理表.xlsx"));
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
            FileUtils.deleteQuietly(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }finally {
            File file = new File(filePath);
            if(file.exists()){
                file.delete();
            }
        }
    }

    @RequestMapping(value = "/ajax/cwjl")
    public ModelAndView loadGjcx(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("datas",this.wrongExcelColumns);
        return new ModelAndView("saas/zzb/dzda/a32/a32WrongList",map);
    }

    public Map<String,Object> addE01z1(List<E01Z1Vo> e01Z1Vos,String listStr,String a38Id, Map<String,Object> returnMap){
        if(e01Z1Vos.size()>0){
            //获得材料类别
            String catalogCode = getCatalogCode(listStr);//获取材料类别Code
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" catalogCode = :catalogCode ", "catalogCode", catalogCode));
            CommonOrderBy orderBy = new CommonOrderBy();
            List<ECatalogTypeInfo> entities = this.eCatalogTypeService.list(query, orderBy);
            ECatalogTypeInfo eCatalogTypeInfo = new ECatalogTypeInfo();
            if(entities.size()>0){
                eCatalogTypeInfo=entities.get(0);
            }
            List<WrongExcelColumn> wrongExcelColumns = (List<WrongExcelColumn>) returnMap.get("wrongExcelColumns");
            WrongExcelColumn wrongExcelColumn;

            try {

                for(E01Z1Vo e01Z1Vo:e01Z1Vos) {
                    int sum = 0;
                    boolean flag = false;//判断是否存在非法数据
                    boolean flag1 = false;//判断必填数据是否全为空
                    if (e01Z1Vo != null) {

                        //判断必填材料是否为空
                        if(e01Z1Vo.getE01Z104()==null||e01Z1Vo.getE01Z104() == 0){
                            wrongExcelColumn = new WrongExcelColumn();
                            wrongExcelColumn.setLines("A"+e01Z1Vo.getRow());
                            wrongExcelColumn.setReason("序号不能为空且大于0");
                            wrongExcelColumn.setWrongExcel("干部档案目录表");
                            wrongExcelColumns.add(wrongExcelColumn);
                            flag = true;
                            sum++;
                        }
                        if(e01Z1Vo.getE01Z114()==null||e01Z1Vo.getE01Z114() == 0){
                            wrongExcelColumn = new WrongExcelColumn();
                            wrongExcelColumn.setLines("F"+e01Z1Vo.getRow());
                            wrongExcelColumn.setReason("页数不能为空且大于0");
                            wrongExcelColumn.setWrongExcel("干部档案目录表");
                            wrongExcelColumns.add(wrongExcelColumn);
                            flag = true;
                            sum++;
                        }
                        if(StringUtils.isEmpty(e01Z1Vo.getE01Z111())){
                            wrongExcelColumn = new WrongExcelColumn();
                            wrongExcelColumn.setLines("B"+e01Z1Vo.getRow());
                            wrongExcelColumn.setReason("材料名称不能为空");
                            wrongExcelColumn.setWrongExcel("干部档案目录表");
                            wrongExcelColumns.add(wrongExcelColumn);
                            flag = true;
                            sum++;
                        }

                        //拼接日期
                        String e01Z117 = "";
                        if(StringUtils.isNotEmpty(e01Z1Vo.getYear())){
                            e01Z117 = e01Z1Vo.getYear();
                            if(StringUtils.isNotEmpty(e01Z1Vo.getMonth())){
                                e01Z117 += e01Z1Vo.getMonth();
                                if(StringUtils.isNotEmpty(e01Z1Vo.getDay())){
                                    e01Z117 += e01Z1Vo.getDay();
                                }
                            }
                            if(A38Controller.isNotDate(e01Z117)){
                                flag = true;
                                wrongExcelColumn = new WrongExcelColumn();
                                wrongExcelColumn.setLines("C/D/E"+e01Z1Vo.getRow());
                                wrongExcelColumn.setReason("日期格式错误");
                                wrongExcelColumn.setWrongExcel("干部档案目录表");
                                wrongExcelColumns.add(wrongExcelColumn);
                                flag = true;
                                sum++;
                            }
                        }

                        if((e01Z1Vo.getE01Z104()==null||e01Z1Vo.getE01Z104() == 0)
                                &&(e01Z1Vo.getE01Z114()==null||e01Z1Vo.getE01Z114() == 0)
                                &&StringUtils.isEmpty(e01Z1Vo.getE01Z111())){
                            flag1 = true;
                        }

                        if(flag){
                            if(flag1){
                                for(int j=0;j<sum;j++){
                                    wrongExcelColumns.remove(wrongExcelColumns.size()-1);
                                }
                            }
                            returnMap.put("isRight",true);
                            continue;
                        }

                        e01Z1Vo.setE01Z117(e01Z117);

                        int sort = this.e01Z1Service.getMaxSort(a38Id, eCatalogTypeInfo.getCatalogCode());
                        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
                        E01Z1 e01Z1 = new E01Z1();
                        BeanUtils.copyProperties(e01Z1Vo, e01Z1);
                        e01Z1.setE01Z101B(eCatalogTypeInfo.getCatalogCode());
                        e01Z1.setE01Z101A(eCatalogTypeInfo.getCatalogValue());
                        e01Z1.setECatalogTypeId(eCatalogTypeInfo.getId());
                        e01Z1.setYjztps(0);
                        if (com.hisun.util.StringUtils.isNotBlank(a38Id)) {
                            e01Z1.setA38(this.a38Service.getByPK(a38Id));
                        }
                        EntityWrapper.wrapperSaveBaseProperties(e01Z1, userLoginDetails);
                        int newSort = e01Z1.getE01Z104();
                        if (newSort < sort) {
                            e01Z1Service.updateSortBeforSave(e01Z1, sort);
                        }
                        e01Z1Service.save(e01Z1);
                    }
                }
            }catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        returnMap.put("wrongExcelColumns",wrongExcelColumns);
        return returnMap;
    }

    public String getCatalogCode(String listStr){
        String catalogCode = "";
        if("jlcl".equals(listStr)){
            catalogCode = "010";
        }else if("zzcl".equals(listStr)){
            catalogCode = "020";
        }else if("jdcl".equals(listStr)){
            catalogCode = "030";
        }else if("xlxw".equals(listStr)){
            catalogCode = "041";
        }else if("zyzg".equals(listStr)){
            catalogCode = "042";
        }else if("kysp".equals(listStr)){
            catalogCode = "043";
        }else if("pxcl".equals(listStr)){
            catalogCode = "044";
        }else if("zscl".equals(listStr)){
            catalogCode = "050";
        }else if("dtcl".equals(listStr)){
            catalogCode = "060";
        }else if("jlicl".equals(listStr)){
            catalogCode = "070";
        }else if("cfcl".equals(listStr)){
            catalogCode = "080";
        }else if("gzcl".equals(listStr)){
            catalogCode = "091";
        }else if("rmcl".equals(listStr)){
            catalogCode = "092";
        }else if("cgcl".equals(listStr)){
            catalogCode = "093";
        }else if("dbdh".equals(listStr)){
            catalogCode = "094";
        }else if("qtcl".equals(listStr)){
            catalogCode = "100";
        }
        return catalogCode;
    }


    /**
     * 反向查询获取字典项
     * @param name
     * @return
     */
    public String getDictionaryItem(String name,String Code){
        List<DictionaryItem> dictionaryItems;
        if(StringUtils.isEmpty(name)){
            return "";
        }
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" dictionaryType.code=:typeCode ", "typeCode", Code));
        query.add(CommonRestrictions.and(" tombstone=:tombstone ", "tombstone", TombstoneEntity.TOMBSTONE_FALSE));
        query.add(CommonRestrictions.and(" display=:display ", "display", DictionaryItem.DISPLAY));
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.asc("sort"));
        dictionaryItems = dictionaryItemService.list(query, orderBy);
        for(DictionaryItem dictionaryItem:dictionaryItems){
            if(name.equals(dictionaryItem.getName())){
                return dictionaryItem.getCode();
            }
        }
        return "";
    }

    public static boolean isNotDate(String dateStr){
        if(StringUtils.isNotEmpty(dateStr)) {
            int lengh = dateStr.length();
            if (lengh == 4 || lengh == 6 || lengh == 8) {
                if (StringUtils.isNumeric(dateStr)) {
                    if(lengh==6){
                        int mouth = Integer.parseInt(dateStr.substring(4,6));
                        if(mouth>=1&&mouth<=12){
                            return false;
                        }
                        return true;
                    }else if(lengh==8){
                        int mouth = Integer.parseInt(dateStr.substring(4,6));
                        int day = Integer.parseInt(dateStr.substring(6,8));
                        if(mouth>=1&&mouth<=12&&day>=1&&day<=31){
                            return false;
                        }
                        return true;
                    }
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}