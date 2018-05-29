/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacx.controller;

import com.google.common.collect.Lists;
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
import com.hisun.saas.zzb.dzda.dacx.vo.DzdaQueryInfoVo;
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
public class DzdaQueryInfoController extends BaseController {
    @Resource
    private DzdaQueryInfoService dzdaQueryInfoService;
    @Resource
    private A38Service a38Service;

    @RequestMapping(value = "/index")
    public ModelAndView index() {
        return new ModelAndView("saas/zzb/dzda/dacx/index");
    }

    @RequiresPermissions("dzda:*")
    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Map<String, Object> model = new HashMap<String, Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.asc("px"));
        query.add(CommonRestrictions.and(" queryStatus = :queryStatus ", "queryStatus", "0"));
        List<DzdaQueryInfo> resultList = dzdaQueryInfoService.list(query, orderBy, pageNum, pageSize);
        Long total = dzdaQueryInfoService.count(query);
        PagerVo<DzdaQueryInfo> pager = new PagerVo<DzdaQueryInfo>(resultList, total.intValue(), pageNum, pageSize);
        model.put("pager", pager);
        return new ModelAndView("saas/zzb/dzda/dacx/list", model);
    }

    @RequiresPermissions("dzda:*")
    @RequestMapping("/delete/{id}")
    public
    @ResponseBody
    Map<String, Object> delete(@PathVariable("id") String id) throws GenericException {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
            dzdaQueryInfoService.deleteByPK(id);
            returnMap.put("code", 1);
        } catch (Exception e) {
            logger.error(e, e);
            throw new GenericException(e.getMessage());
        }

        return returnMap;
    }

    @RequiresPermissions("dzda:*")
    @RequestMapping(value = "/gjcx")
    public ModelAndView loadGjcx(HttpServletRequest request,
                                 @RequestParam(value = "appQueryId", required = false) String appQueryId,
                                 @RequestParam(value = "editFlag", required = false) String editFlag) {
        //  HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<String, Object>();
        //  DakVo queryDakVo = (DakVo)session.getAttribute("queryDakVo");
        if (StringUtils.isNotBlank(appQueryId)) {
            DzdaQueryInfo dzdaQueryInfo = dzdaQueryInfoService.getByPK(appQueryId);
            DakVo queryDakVo = JacksonUtil.nonDefaultMapper().fromJson(dzdaQueryInfo.getQueryModel(), DakVo.class);
            map.put("vo", queryDakVo);
            map.put("appQueryId", appQueryId);
        }
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        int sort = dzdaQueryInfoService.getMaxSort(details.getTenantId());
        map.put("editFlag", editFlag);
        map.put("sort", sort);
        return new ModelAndView("saas/zzb/dzda/dacx/gjcx", map);
    }

    @RequiresPermissions("dzda:*")
    @RequestMapping("/save")
    @ResponseBody
    public Map<String, Object> save(@ModelAttribute DakVo queryVo, HttpServletRequest request,
                                    @RequestParam(value = "queryName", required = true) String queryName,
                                    @RequestParam(value = "description", required = false) String description,
                                    @RequestParam(value = "queryType", required = false) String queryType,
                                    @RequestParam(value = "px", required = true) Integer px) {
        Map<String, Object> model = Maps.newHashMap();
        DzdaQueryInfo entity = new DzdaQueryInfo();
        entity.setQueryStatus("0");
        entity.setQueryName(queryName);
        entity.setQueryType(queryType);
        entity.setPx(px);
        entity.setDescription(description);
        String id = this.saveModel(queryVo, entity);
        model.put("code", 1);
        model.put("appQueryId", id);
        return model;
    }

    @RequiresPermissions("dzda:*")
    @RequestMapping("/saveById")
    @ResponseBody
    public Map<String, Object> saveById(@RequestParam(value = "appQueryId", required = true) String appQueryId,
                                        @RequestParam(value = "queryName", required = true) String queryName,
                                        @RequestParam(value = "description", required = false) String description,
                                        @RequestParam(value = "queryType", required = false) String queryType,
                                        @RequestParam(value = "px", required = true) Integer px) {
        Map<String, Object> model = Maps.newHashMap();
        DzdaQueryInfo dzdaQueryInfo = dzdaQueryInfoService.getByPK(appQueryId);
        dzdaQueryInfo.setQueryName(queryName);
        dzdaQueryInfo.setDescription(description);
        dzdaQueryInfo.setQueryType(queryType);
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        int newPx = px;
        int oldPx = dzdaQueryInfo.getPx();
        if (oldPx != newPx) {
            this.dzdaQueryInfoService.updatePx(oldPx, newPx,details.getTenantId());
        }
        dzdaQueryInfo.setPx(px);
        dzdaQueryInfo.setQueryStatus("0");
        dzdaQueryInfoService.update(dzdaQueryInfo);
        model.put("code", 1);
        return model;
    }

    @RequiresPermissions("dzda:*")
    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestParam(value = "appQueryId", required = true) String appQueryId,
                                      @RequestParam(value = "queryName", required = true) String queryName,
                                      @RequestParam(value = "description", required = false) String description,
                                      @ModelAttribute DakVo queryVo, HttpServletRequest request) {
        Map<String, Object> model = Maps.newHashMap();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        DzdaQueryInfo dzdaQueryInfo = dzdaQueryInfoService.getByPK(appQueryId);
        String queryModel = JacksonUtil.nonDefaultMapper().toJson(queryVo);
        if (StringUtils.isNotBlank(queryName)) {
            dzdaQueryInfo.setQueryName(queryName);
        }
        if (StringUtils.isNotBlank(description)) {
            dzdaQueryInfo.setDescription(description);
        }
        dzdaQueryInfo.setQueryModel(queryModel);
        dzdaQueryInfo.setQueryStatus("0");
        EntityWrapper.wrapperUpdateBaseProperties(dzdaQueryInfo, details);
        dzdaQueryInfoService.update(dzdaQueryInfo);
        model.put("code", 1);
        return model;
    }

    @RequiresPermissions("dzda:*")
    @RequestMapping("/ajax/updateQueryType")
    @ResponseBody
    public Map<String, Object> updateQueryType(@RequestParam(value = "appQueryId", required = true) String appQueryId
                                      ) {
        Map<String, Object> model = Maps.newHashMap();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        DzdaQueryInfo dzdaQueryInfo = dzdaQueryInfoService.getByPK(appQueryId);
        if("0".equals(dzdaQueryInfo.getQueryType())){
            dzdaQueryInfo.setQueryType("1");
        }else {
            dzdaQueryInfo.setQueryType("0");
        }
        EntityWrapper.wrapperUpdateBaseProperties(dzdaQueryInfo, details);
        dzdaQueryInfoService.update(dzdaQueryInfo);
        model.put("code", 1);
        return model;
    }

    @RequiresPermissions("dzda:*")
    @RequestMapping("/ajax/getById")
    @ResponseBody
    public Map<String,Object> getById(String appQueryId){
        Map<String, Object> model = Maps.newHashMap();
        DzdaQueryInfo entity = dzdaQueryInfoService.getByPK(appQueryId);
        DzdaQueryInfoVo vo = new DzdaQueryInfoVo();
        BeanUtils.copyProperties(entity,vo);
        model.put("queryVo",vo);
        model.put("code", 1);
        return model;
    }

    private String saveModel(DakVo queryVo, DzdaQueryInfo entity) {
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        String queryModel = JacksonUtil.nonDefaultMapper().toJson(queryVo);
        entity.setQueryModel(queryModel);
        EntityWrapper.wrapperSaveBaseProperties(entity, details);
        return dzdaQueryInfoService.save(entity);
    }
    @RequiresPermissions("dzda:*")
    @RequestMapping("/ajax/toBaocun")
    public ModelAndView toBaocun(@ModelAttribute DakVo queryVo,
                                 @RequestParam(value = "appQueryId", required = false) String appQueryId,
                                 @RequestParam(value = "editQuery", required = false) String editQuery){
        Map<String, Object> model = new HashMap<String, Object>();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        if(StringUtils.isNotBlank(appQueryId)){
            DzdaQueryInfo dzdaQueryInfo = dzdaQueryInfoService.getByPK(appQueryId);
            DzdaQueryInfoVo vo = new DzdaQueryInfoVo();
            BeanUtils.copyProperties(dzdaQueryInfo,vo);
            if(dzdaQueryInfo.getPx()==0){
                int sort = dzdaQueryInfoService.getMaxSort(details.getTenantId());
                vo.setPx(sort);
            }
            model.put("vo",vo);
        }else {
            int sort = dzdaQueryInfoService.getMaxSort(details.getTenantId());
            model.put("add", "add");
            model.put("sort", sort);
           // model.put("queryVo",queryVo);
        }
        model.put("editQuery",editQuery);
        return new ModelAndView("saas/zzb/dzda/dacx/baocun",model);
    }

    @RequiresPermissions("dzda:*")
    @RequestMapping("/bdwdalist")
    public ModelAndView bdwdalist(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                  @ModelAttribute DakVo queryVo, String a0101Query, String gbztCodeQuery, String daztCodeQuery, String gbztContentQuery,
                                  String daztContentQuery, String queryType,
                                  @RequestParam(value = "appQueryId", required = false) String appQueryId,
                                  @RequestParam(value = "ercichaxun", required = false) String ercichaxun) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(appQueryId)) {
            DzdaQueryInfo dzdaQueryInfo = dzdaQueryInfoService.getByPK(appQueryId);
            queryVo = JacksonUtil.nonDefaultMapper().fromJson(dzdaQueryInfo.getQueryModel(), DakVo.class);
            a0101Query = queryVo.getA0101();
            gbztCodeQuery = queryVo.getGbztCodes();
            gbztContentQuery = queryVo.getGbztContents();
            daztCodeQuery = queryVo.getDaztCodes();
            daztContentQuery = queryVo.getDaztContents();
            model.put("appQueryId", appQueryId);
            model.put("fanhui", "1");
        } else if (queryType != null && queryType.equals("listQuery")) {//listQuery
            DakVo queryDakVo = new DakVo();
            if (queryDakVo == null) {
                queryDakVo = new DakVo();
            }
            queryDakVo.setA0101(a0101Query);
            queryDakVo.setGbztCodes(gbztCodeQuery);
            queryDakVo.setGbztContents(gbztContentQuery);
            queryDakVo.setDaztCodes(daztCodeQuery);
            queryDakVo.setDaztContents(daztContentQuery);
            queryVo = queryDakVo;
            if(StringUtils.isNotBlank(ercichaxun))
                model.put("appQueryId",ercichaxun );

            model.put("fanhui", "1");
        } else if (queryType != null && queryType.equals("gaojichaxun")) {
            DzdaQueryInfo dzdaQueryInfo = new DzdaQueryInfo();
            dzdaQueryInfo.setQueryStatus("1");
            model.put("gaojichaxun", "gaojichaxun");
            model.put("appQueryId", this.saveModel(queryVo, dzdaQueryInfo));
            a0101Query = queryVo.getA0101();
            gbztCodeQuery = queryVo.getGbztCodes();
            gbztContentQuery = queryVo.getGbztContents();
            daztCodeQuery = queryVo.getDaztCodes();
            daztContentQuery = queryVo.getDaztContents();
            model.put("fanhui", "1");
        }else {
            queryVo= new DakVo();
            model.put("wutiaojian","wutiaojian");
        }

        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        List<A38> resultList = a38Service.list(this.a38Service.getGjcxHql(queryVo, userLoginDetails), new ArrayList<Object>(), pageNum, pageSize);
        int total = a38Service.list(this.a38Service.getGjcxHql(queryVo, userLoginDetails), new ArrayList<Object>(), 1, 100000).size();
        List<A38Vo> a38Vos = new ArrayList<A38Vo>();
        A38Vo vo = new A38Vo();
        for (A38 entity : resultList) {
            vo = new A38Vo();
            BeanUtils.copyProperties(entity, vo);
            vo.setUpdateDateByShow(entity.getUpdateDate());
            vo.setUpdateUserNameByShow(entity.getUpdateUserName());
            a38Vos.add(vo);
        }
        PagerVo<A38Vo> pager = new PagerVo<A38Vo>(a38Vos, total, pageNum, pageSize);
        //常用查询
        CommonConditionQuery query = new CommonConditionQuery();
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.asc("px"));
        query.add(CommonRestrictions.and(" queryType = :queryType ", "queryType", "1"));
        List<DzdaQueryInfo> queryList = dzdaQueryInfoService.list(query, orderBy, 1, 10);
        List<DzdaQueryInfoVo> dzdaQueryInfoVos = Lists.newArrayList();
        for (DzdaQueryInfo i : queryList) {
            DzdaQueryInfoVo vo1 = new DzdaQueryInfoVo();
            BeanUtils.copyProperties(i, vo1);
            dzdaQueryInfoVos.add(vo1);
        }
        //查询queryName用
        String id = (String)model.get("appQueryId");
        if(StringUtils.isNotBlank(id)){
            model.put("queryName",dzdaQueryInfoService.getByPK(id).getQueryName());
        }

        model.put("a0101Query", a0101Query);
        model.put("gbztCodeQuery", gbztCodeQuery);
        model.put("daztCodeQuery", daztCodeQuery);
        model.put("gbztContentQuery", gbztContentQuery);
        model.put("daztContentQuery", daztContentQuery);
        model.put("queryVo", dzdaQueryInfoVos);
        model.put("pager", pager);
        return new ModelAndView("saas/zzb/dzda/dacx/bdwdalist", model);
    }

    @RequiresPermissions("dzda:*")
    @RequestMapping("/bdwdalistById")
    public ModelAndView bdwdalistById(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                      @RequestParam(value = "appQueryId", required = true) String appQueryId) throws UnsupportedEncodingException {
        Map<String, Object> model = new HashMap<String, Object>();
        DzdaQueryInfo dzdaQueryInfo = dzdaQueryInfoService.getByPK(appQueryId);
        String queryName = dzdaQueryInfo.getQueryName();
        DakVo queryDakVo = JacksonUtil.nonDefaultMapper().fromJson(dzdaQueryInfo.getQueryModel(), DakVo.class);
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        List<A38> resultList = a38Service.list(this.a38Service.getGjcxHql(queryDakVo, userLoginDetails), new ArrayList<Object>(), pageNum, pageSize);
        int total = a38Service.list(this.a38Service.getGjcxHql(queryDakVo, userLoginDetails), new ArrayList<Object>(), 1, 100000).size();
        List<A38Vo> a38Vos = new ArrayList<A38Vo>();
        A38Vo vo = new A38Vo();
        for (A38 entity : resultList) {
            vo = new A38Vo();
            BeanUtils.copyProperties(entity, vo);
            vo.setUpdateDateByShow(entity.getUpdateDate());
            vo.setUpdateUserNameByShow(entity.getUpdateUserName());
            a38Vos.add(vo);
        }
        PagerVo<A38Vo> pager = new PagerVo<A38Vo>(a38Vos, total, pageNum, pageSize);
        if ("0".equals(dzdaQueryInfo.getQueryStatus())) {
            model.put("idQuery", "idQuery");
        }
        model.put("pager", pager);
        model.put("appQueryId", appQueryId);
        model.put("queryName", queryName);
        return new ModelAndView("saas/zzb/dzda/dacx/bdwdalist", model);
    }

}
