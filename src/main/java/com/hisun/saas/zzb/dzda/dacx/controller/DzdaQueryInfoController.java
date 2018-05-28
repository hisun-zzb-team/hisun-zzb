/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacx.controller;

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
import com.hisun.saas.zzb.dzda.a38.vo.A38Vo;
import com.hisun.saas.zzb.dzda.dacx.entity.DzdaQueryInfo;
import com.hisun.saas.zzb.dzda.dacx.service.DzdaQueryInfoService;
import com.hisun.saas.zzb.dzda.dak.vo.DakVo;
import com.hisun.util.JacksonUtil;
import com.hisun.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marco {854476391@qq.com}
 */
@Controller
@RequestMapping("/zzb/dzda/dacx")
public class DzdaQueryInfoController extends BaseController{
    @Resource
    private DzdaQueryInfoService dzdaQueryInfoService;
    @Resource
    private A38Service a38Service;

    @RequestMapping(value = "/index")
    public ModelAndView index(){
        return  new ModelAndView("saas/zzb/dzda/dacx/index");
    }

    @RequiresPermissions("dzda:*")
    @RequestMapping(value = "/ajax/list")
    public ModelAndView list(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        Map<String,Object> model = new HashMap<String,Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.asc("px"));
        query.add(CommonRestrictions.and(" queryStatus = :queryStatus ", "queryStatus", "0"));
        List<DzdaQueryInfo> resultList= dzdaQueryInfoService.list(query,orderBy,pageNum,pageSize);
        Long total = dzdaQueryInfoService.count(query);
        PagerVo<DzdaQueryInfo> pager = new PagerVo<DzdaQueryInfo>(resultList, total.intValue(), pageNum, pageSize);
        model.put("pager",pager);
        return  new ModelAndView("saas/zzb/dzda/dacx/list",model);
    }
    @RequiresPermissions("dzda:*")
    @RequestMapping("/delete/{id}")
    public @ResponseBody
    Map<String,Object> delete(@PathVariable("id") String id) throws GenericException {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            dzdaQueryInfoService.deleteByPK(id);
            returnMap.put("code",1);
        }catch (Exception e){
            logger.error(e,e);
            throw new GenericException(e.getMessage());
        }

        return returnMap;
    }
    @RequiresPermissions("dzda:*")
    @RequestMapping(value = "/ajax/gjcx")
    public ModelAndView loadGjcx(HttpServletRequest request,
                                 @RequestParam(value = "appQueryId",required = false) String appQueryId,
                                 @RequestParam(value = "editFlag",required = false)String editFlag){
      //  HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<String, Object>();
      //  DakVo queryDakVo = (DakVo)session.getAttribute("queryDakVo");
        if(StringUtils.isNotBlank(appQueryId)){
            DzdaQueryInfo dzdaQueryInfo = dzdaQueryInfoService.getByPK(appQueryId);
            DakVo queryDakVo = JacksonUtil.nonDefaultMapper().fromJson(dzdaQueryInfo.getQueryModel(), DakVo.class);
            map.put("vo",queryDakVo);
            map.put("appQueryId",appQueryId);
            map.put("queryName", dzdaQueryInfo.getQueryName());
            map.put("description", dzdaQueryInfo.getDescription());
        }
        map.put("editFlag",editFlag);
        return new ModelAndView("saas/zzb/dzda/dacx/gjcx",map);
    }

    @RequiresPermissions("dzda:*")
    @RequestMapping("/save")
    @ResponseBody
    public Map<String,Object> save(@ModelAttribute DakVo queryVo,HttpServletRequest request,
                                   @RequestParam(value = "queryName" ,required = true) String queryName,
                                   @RequestParam(value = "description" ,required = false) String description){
        Map<String,Object> model = Maps.newHashMap();
        DzdaQueryInfo entity = new DzdaQueryInfo();
        entity.setQueryStatus("0");
        entity.setQueryName(queryName);
        entity.setDescription(description);
        String id = this.saveModel(queryVo,entity);
        model.put("code",1);
        model.put("appQueryId",id);
        return  model;
    }

    @RequiresPermissions("dzda:*")
    @RequestMapping("/saveById")
    @ResponseBody
    public Map<String,Object> saveById(@RequestParam(value = "appQueryId" ,required = true)String appQueryId,
                                   @RequestParam(value = "queryName" ,required = true) String queryName,
                                   @RequestParam(value = "description" ,required = false) String description){
        Map<String,Object> model = Maps.newHashMap();
        DzdaQueryInfo dzdaQueryInfo = dzdaQueryInfoService.getByPK(appQueryId);
        dzdaQueryInfo.setQueryName(queryName);
        dzdaQueryInfo.setDescription(description);
        dzdaQueryInfo.setQueryStatus("0");
        dzdaQueryInfoService.update(dzdaQueryInfo);
        model.put("code",1);
        return  model;
    }

    @RequiresPermissions("dzda:*")
    @RequestMapping("/update")
    @ResponseBody
    public Map<String,Object> update(@RequestParam(value = "appQueryId" ,required = true)String appQueryId,
                                       @RequestParam(value = "queryName" ,required = true) String queryName,
                                       @RequestParam(value = "description" ,required = false) String description,
                                       @ModelAttribute DakVo queryVo,HttpServletRequest request){
        Map<String,Object> model = Maps.newHashMap();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        DzdaQueryInfo dzdaQueryInfo = dzdaQueryInfoService.getByPK(appQueryId);
        String queryModel = JacksonUtil.nonDefaultMapper().toJson(queryVo);
        if(StringUtils.isNotBlank(queryName)){
            dzdaQueryInfo.setQueryName(queryName);
        }
        if(StringUtils.isNotBlank(description)){
            dzdaQueryInfo.setDescription(description);
        }
        dzdaQueryInfo.setQueryModel(queryModel);
        dzdaQueryInfo.setQueryStatus("0");
        EntityWrapper.wrapperUpdateBaseProperties(dzdaQueryInfo, details);
        dzdaQueryInfoService.update(dzdaQueryInfo);
        model.put("code",1);
        return  model;
    }

    private String saveModel(DakVo queryVo,DzdaQueryInfo entity){
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        String queryModel = JacksonUtil.nonDefaultMapper().toJson(queryVo);
        entity.setQueryModel(queryModel);
        EntityWrapper.wrapperSaveBaseProperties(entity, details);
        return  dzdaQueryInfoService.save(entity);
    }

    @RequiresPermissions("dzda:*")
    @RequestMapping("/ajax/bdwdalist")
    public ModelAndView bdwdalist(@RequestParam(value="pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                                   @ModelAttribute DakVo queryVo) throws UnsupportedEncodingException {
        Map<String,Object> model = new HashMap<String,Object>();
        DzdaQueryInfo dzdaQueryInfo = new DzdaQueryInfo();
        dzdaQueryInfo.setQueryStatus("1");
        model.put("appQueryId",this.saveModel(queryVo,dzdaQueryInfo));
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
        model.put("pager",pager);
        return new ModelAndView("saas/zzb/dzda/dacx/bdwdalist",model);
    }
    @RequiresPermissions("dzda:*")
    @RequestMapping("/ajax/bdwdalistById")
    public ModelAndView bdwdalistById(@RequestParam(value="pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                                      @RequestParam(value = "appQueryId" ,required = true)String appQueryId) throws UnsupportedEncodingException {
        Map<String,Object> model = new HashMap<String,Object>();
        DzdaQueryInfo dzdaQueryInfo = dzdaQueryInfoService.getByPK(appQueryId);
        String queryName = dzdaQueryInfo.getQueryName();
        DakVo queryDakVo = JacksonUtil.nonDefaultMapper().fromJson(dzdaQueryInfo.getQueryModel(), DakVo.class);
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        List<A38> resultList = a38Service.list(this.a38Service.getGjcxHql(queryDakVo,userLoginDetails), new ArrayList<Object>(), pageNum, pageSize);
        int total =   a38Service.list(this.a38Service.getGjcxHql(queryDakVo,userLoginDetails), new ArrayList<Object>(),1,100000).size();
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
        if("0".equals(dzdaQueryInfo.getQueryStatus())){
            model.put("idQuery","idQuery");
        }
        model.put("pager",pager);
        model.put("appQueryId",appQueryId);
        model.put("queryName",queryName);
        return new ModelAndView("saas/zzb/dzda/dacx/bdwdalist",model);
    }

}
