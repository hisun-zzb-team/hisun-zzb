/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacx.controller;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.a38.vo.A38Vo;
import com.hisun.saas.zzb.dzda.dacx.entity.AppQueryInfo;
import com.hisun.saas.zzb.dzda.dacx.service.AppQueryInfoService;
import com.hisun.saas.zzb.dzda.dak.vo.DakVo;
import com.hisun.util.JacksonUtil;
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
public class AppQueryInfoController extends BaseController{
    @Resource
    private AppQueryInfoService appQueryInfoService;
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
        List<AppQueryInfo> resultList= appQueryInfoService.list();
        Long total = appQueryInfoService.count();
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.asc("px"));
        PagerVo<AppQueryInfo> pager = new PagerVo<AppQueryInfo>(resultList, total.intValue(), pageNum, pageSize);
        model.put("pager",pager);
        return  new ModelAndView("saas/zzb/dzda/dacx/list",model);
    }
    @RequiresPermissions("dzda:*")
    @RequestMapping("/delete/{id}")
    public @ResponseBody
    Map<String,Object> delete(@PathVariable("id") String id) throws GenericException {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            appQueryInfoService.deleteByPK(id);
            returnMap.put("code",1);
        }catch (Exception e){
            logger.error(e,e);
            throw new GenericException(e.getMessage());
        }

        return returnMap;
    }
    @RequiresPermissions("dzda:*")
    @RequestMapping(value = "/ajax/gjcx")
    public ModelAndView loadGjcx(HttpServletRequest request){
        HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<String, Object>();
        DakVo queryDakVo = (DakVo)session.getAttribute("queryDakVo");
        map.put("vo",queryDakVo);
        return new ModelAndView("saas/zzb/dzda/dacx/gjcx",map);
    }

    @RequiresPermissions("dzda:*")
    @RequestMapping("/save")
    @ResponseBody
    public Map<String,Object> save(@ModelAttribute DakVo queryVo){
        Map<String,Object> model = Maps.newHashMap();
        AppQueryInfo entity = new AppQueryInfo();
        String queryModel = JacksonUtil.nonDefaultMapper().toJson(queryVo);
        entity.setQueryModel(queryModel);

        return  model;
    }

    private String saveModel(DakVo queryVo){
        AppQueryInfo entity = new AppQueryInfo();
        String queryModel = JacksonUtil.nonDefaultMapper().toJson(queryVo);
        entity.setQueryModel(queryModel);
        return  appQueryInfoService.save(entity);
    }

    @RequiresPermissions("dzda:*")
    @RequestMapping("/ajax/bdwdalist")
    public ModelAndView bdwdalist(@RequestParam(value="pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                                  HttpServletRequest request, String a0101Query, String gbztCodeQuery, String daztCodeQuery, String gbztContentQuery,
                                  String daztContentQuery, @ModelAttribute DakVo queryVo, String queryType) throws UnsupportedEncodingException {
        Map<String,Object> model = new HashMap<String,Object>();
        HttpSession session = request.getSession();
        if(queryType!=null && queryType.equals("gaojichaxun")){//高级查询
            a0101Query =  queryVo.getA0101();
            gbztCodeQuery = queryVo.getGbztCodes();
            gbztContentQuery =  queryVo.getGbztContents();
            daztCodeQuery= queryVo.getDaztCodes();
            daztContentQuery = queryVo.getDaztContents();
            session.setAttribute("queryDakVo",queryVo);
        } else if(queryType!=null && queryType.equals("listQuery")){//listQuery
            DakVo queryDakVo = (DakVo)session.getAttribute("queryDakVo");
            if(queryDakVo ==null){
                queryDakVo = new DakVo();
            }
            queryDakVo.setA0101(a0101Query);
            queryDakVo.setGbztCodes(gbztCodeQuery);
            queryDakVo.setGbztContents(gbztContentQuery);
            queryDakVo.setDaztCodes(daztCodeQuery);
            queryDakVo.setDaztContents(daztContentQuery);
            queryVo = queryDakVo;
            session.setAttribute("queryDakVo",queryVo);
        }else{
            DakVo queryDakVo = (DakVo)session.getAttribute("queryDakVo");
            if(queryDakVo ==null){
                queryDakVo = new DakVo();
            }
            a0101Query =queryDakVo.getA0101();
            gbztCodeQuery =queryDakVo.getGbztCodes();
            gbztContentQuery =queryDakVo.getGbztContents();
            daztCodeQuery = queryDakVo.getDaztCodes();
            daztContentQuery =queryDakVo.getDaztContents();
            queryVo = queryDakVo;
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


        model.put("pager",pager);
        model.put("a0101Query",a0101Query);
        model.put("gbztCodeQuery",gbztCodeQuery);
        model.put("daztCodeQuery",daztCodeQuery);
        model.put("gbztContentQuery",gbztContentQuery);
        model.put("daztContentQuery",daztContentQuery);
        model.put("pager",pager);
        return new ModelAndView("saas/zzb/dzda/dacx/bdwdalist",model);
    }

}
