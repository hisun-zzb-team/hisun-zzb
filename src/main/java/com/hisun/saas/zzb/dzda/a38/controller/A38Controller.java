/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a38.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.admin.dzda.entity.ECatalogTypeInfo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.log.LogOperateType;
import com.hisun.saas.sys.log.RequiresLog;
import com.hisun.saas.sys.tenant.tenant.service.TenantService;
import com.hisun.saas.sys.tenant.user.service.TenantUserService;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.a38.vo.A38Vo;
import com.hisun.saas.zzb.dzda.dak.vo.DakVo;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.saas.zzb.dzda.mlcl.vo.E01Z1Vo;
import com.hisun.util.ValidateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
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
            vo.setUpdateDateByShow(entity.getUpdateDate());
            vo.setUpdateUserNameByShow(entity.getUpdateUserName());
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
            a38Service.deleteByPK(id);
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

}