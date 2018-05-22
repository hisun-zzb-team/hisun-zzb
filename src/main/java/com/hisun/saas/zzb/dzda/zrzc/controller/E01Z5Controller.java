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
import com.hisun.saas.zzb.dzda.zrzc.entity.E01Z5;
import com.hisun.saas.zzb.dzda.zrzc.service.E01Z5Service;
import com.hisun.saas.zzb.dzda.zrzc.vo.E01Z5Vo;
import com.hisun.util.ValidateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author liuzj {279421824@qq.com}
*/
@Controller
@RequestMapping("/zzb/dzda/dajs")
public class E01Z5Controller extends BaseController {

    @Resource
    private E01Z5Service e01z5Service;
   

    @RequiresPermissions("dajs:*")
    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,@RequestParam(value = "pageSize",defaultValue = "15")int pageSize) throws UnsupportedEncodingException {
        Map<String,Object> model = new HashMap<String,Object>();
        CommonConditionQuery query = new CommonConditionQuery();
//        query.add(CommonRestrictions.and(" pId = :pId ", "pId", pId));
        Long total = e01z5Service.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
//        orderBy.add(CommonOrder.asc("sort"));
        List<E01Z5> resultList = e01z5Service.list(query,orderBy,pageNum,pageSize);
        List<E01Z5Vo> e01z5Vos = new ArrayList<E01Z5Vo>();
        E01Z5Vo vo = new E01Z5Vo();
        for(E01Z5 entity : resultList){
            vo = new E01Z5Vo();
            BeanUtils.copyProperties(entity,vo);
            e01z5Vos.add(vo);
        }
        PagerVo<E01Z5Vo> pager = new PagerVo<E01Z5Vo>(e01z5Vos, total.intValue(), pageNum, pageSize);
        model.put("pager",pager);
        return new ModelAndView("saas/zzb/dzda/zrzc/e01z5/list",model);
    }

    @RequiresPermissions("dajs:*")
    @RequestMapping("/add")
    public ModelAndView add(){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        return new ModelAndView("saas/zzb/dzda/zrzc/e01z5/add",returnMap);
    }

    @RequiresPermissions("dajs:*")
    @RequestMapping("/save")
    public @ResponseBody Map<String,Object> save(@ModelAttribute E01Z5Vo vo) throws GenericException {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Map<String,String> vMap = ValidateUtil.validAll(vo);
        if(vMap.size()>0){
            returnMap.put("message","数据验证不通过");
            returnMap.put("code",-1);
            return returnMap;
        }
        try{
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
             E01Z5 entity = new  E01Z5();
            BeanUtils.copyProperties(vo, entity);
            entity.setId(null);
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
    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        Map<String,Object> model = new HashMap<String,Object>();
         E01Z5 tenant = e01z5Service.getByPK(id);
        E01Z5Vo vo = new E01Z5Vo();
        BeanUtils.copyProperties(tenant,vo);
        model.put("entity", vo);
        return new ModelAndView("saas/zzb/dzda/zrzc/e01z5/edit", model);
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