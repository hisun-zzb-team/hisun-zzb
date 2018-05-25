/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.bset.controller;

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
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA01;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA36;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA01Service;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA02Service;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA36Service;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetB01;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetFl;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetFl2B01;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetB01Service;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetFl2B01Service;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetFlService;
import com.hisun.saas.zzb.app.console.bset.vo.AppBsetB01Vo;
import com.hisun.saas.zzb.app.console.bset.vo.B01TreeVo;
import com.hisun.saas.zzb.app.console.bset.vo.ImportParameterVo;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01;
import com.hisun.saas.sys.util.BeanTrans;
import com.hisun.util.C3p0Util;
import com.hisun.util.WebUtil;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/bset")
public class AppBsetB01Controller extends BaseController{

    @Resource
    private AppBsetB01Service appBsetB01Service;
    @Resource
    private AppBsetFlService appBsetFlService;
    @Resource
    private AppBsetFl2B01Service appBsetFl2B01Service;
    @Resource
    private AppAsetA01Service appAsetA01Service;
    @Resource
    private AppAsetA02Service appAsetA02Service;
    @Resource
    private AppAsetA36Service  appAsetA36Service;

    @RequestMapping(value = "/")
    public ModelAndView list(){

        return new ModelAndView("saas/zzb/app/console/bsetB01/b01Manage");



    }
    @RequestMapping(value="/ajax/load/tree")
    public @ResponseBody
    Map<String,Object> loadTree(HttpServletRequest request)throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String dataSourceTyle = "b01";//用于记录机构树组合方式 如果分类的顶级节点为隐藏则由b01直接组成
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" parentFl.id is null and  tombstone = :tombstone", "tombstone", 0));
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("parentFl.id"));
            orderBy.add(CommonOrder.asc("px"));
            List<AppBsetFl> topAppBsetFls = this.appBsetFlService.list(query, orderBy);
            if(topAppBsetFls!=null && topAppBsetFls.size()>0){
                if(topAppBsetFls.get(0).getIsHidden()==AppBsetFl.DISPLAY){
                    dataSourceTyle = "fl";
                }
            }
            List<B01TreeVo> b01TreeVoList = this.appBsetB01Service.getB01TreeVoList(dataSourceTyle);
            // 添加未分组节点
            map.put("customTree", b01TreeVoList);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e,e);
            map.put("success", false);
            map.put("message", "服务器忙，请稍后再试！");
        }
        return map;
    }

    @RequestMapping(value="/ajax/list")
    public ModelAndView getList(String queryId,String b0101Query,
                                @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        Map<String, Object> map = Maps.newHashMap();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            String flmc = "";

            List<Object> paramList = Lists.newArrayList();
            String hql = " from AppBsetB01 b01 left join b01.appBsetFl2B01s fl2b01 where ";
            if(StringUtils.isNotBlank(queryId)) {
                CommonConditionQuery query1 = new CommonConditionQuery();
                query1.add(CommonRestrictions.and(" id =:id", "id",queryId));
                query1.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
                CommonOrderBy orderBy = new CommonOrderBy();
                List<AppBsetFl> appBsetFls = this.appBsetFlService.list(query1, orderBy);
                if(appBsetFls!=null && appBsetFls.size()>0) {
                    paramList.add(queryId);
                    hql = hql + " fl2b01.appBsetFl.id = ?";

                    flmc = appBsetFls.get(0).getFl();
                }else{
                    paramList.add(queryId);
                    hql = hql + " b01.parentB01.id = ?";
                    AppBsetB01 appBsetB01 = this.appBsetB01Service.getByPK(queryId);
                    flmc = appBsetB01.getB0101();
                }
            }

            if(b0101Query!=null && !b0101Query.equals("")){
                paramList.add("%"+ b0101Query+ "%");
                hql = hql+" and b01.b0101 like ?";
            }
            hql = hql+" and b01.tombstone =? order by b01.px";
            paramList.add(0);
            int total = this.appBsetB01Service.count("select  count(distinct b01.id) "+hql,paramList);
            List<AppBsetB01> appBsetB01s = this.appBsetB01Service.list("select  DISTINCT(b01) "+hql,paramList, pageNum,
                    pageSize);
//            Long total = appBsetB01Service.count(query);
//            CommonOrderBy orderBy = new CommonOrderBy();
////            orderBy.add(CommonOrder.desc("lastUpdateTime"));
////            orderBy.add(CommonOrder.desc("name"));
//            List<AppBsetB01> b01List = appBsetB01Service.list(query, orderBy, pageNum, pageSize);
            PagerVo<AppBsetB01> pagerVo = new PagerVo<AppBsetB01>(appBsetB01s, total, pageNum, pageSize);
            map.put("pager", pagerVo);
            map.put("queryId", queryId);
//            map.put("zsId", zsId);
            map.put("b0101Query", b0101Query);
            map.put("flmc", flmc);

            map.put("success", true);
        } catch (Exception e) {
            logger.error(e,e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/app/console/bsetB01/rightList", map);

    }


    /**
     * 调转到新增页面
     * @return
     */
    @RequestMapping(value = "/addOrEditManage")
    public ModelAndView addOrEditManage(@RequestParam(value="dataType",required=false)String dataType,@RequestParam(value="parentId",required=false)String parentId,@RequestParam(value="id",required=false)String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        AppBsetB01Vo vo = new AppBsetB01Vo();
        try {
            if(id==null || id.equals("")) {
                Integer maxPx = appBsetB01Service.getMaxPx(parentId);
                if (maxPx != null) {
                    vo.setPx(maxPx + 1);
                } else {
                    vo.setPx(1);
                }
                vo.setDataType(Integer.parseInt(dataType));
                vo.setParentId(parentId);
            }else{
                AppBsetB01 appGbcxB01 = this.appBsetB01Service.getByPK(id);
                if (appGbcxB01 == null) {
                    logger.error("数据不存在");
                    throw new GenericException("数据不存在");
                }
                BeanUtils.copyProperties(vo, appGbcxB01);
                if (appGbcxB01.getParentB01() != null) {
                    parentId = appGbcxB01.getParentB01().getId();
                }
                vo.setParentId(parentId);
            }
            map.put("vo", vo);
            map.put("dataType", dataType);
            map.put("parentId", parentId);
            map.put("id", id);

        } catch(Exception e) {
            map.put("success", false);
            map.put("msg", "修改失败！");
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/bsetB01/addAndEditB01Manage",map);
    }

    @RequestMapping(value="/ajax/addOrEdit")
    public ModelAndView addOrEdit(@RequestParam(value="dataType",required=false)String dataType,@RequestParam(value="parentId",required=false)String parentId,@RequestParam(value="id",required=false)String id){
    Map<String, Object> map = new HashMap<String, Object>();
        AppBsetB01Vo vo = new AppBsetB01Vo();
        try {
            if(id==null || id.equals("")) {
//                Integer maxPx = appBsetB01Service.getMaxPx(parentId);
//                if (maxPx != null) {
//                    vo.setPx(maxPx + 1);
//                } else {
//                    vo.setPx(1);
//                }
//                vo.setDataType(Integer.parseInt(dataType));
//                vo.setParentId(parentId);
            }else{
                AppBsetB01 appGbcxB01 = this.appBsetB01Service.getByPK(id);
                if (appGbcxB01 == null) {
                    logger.error("数据不存在");
                    throw new GenericException("数据不存在");
                }
                BeanUtils.copyProperties(vo, appGbcxB01);
                if (appGbcxB01.getParentB01() != null) {
                    parentId = appGbcxB01.getParentB01().getId();
                }
                vo.setParentId(parentId);
            }
            map.put("vo", vo);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/bsetB01/addAndEditB01",map);
    }



    /**
     * 调转到新增页面
     * @return
     */
    @RequestMapping(value = "/ajax/importParamSet")
    public ModelAndView importParamSet(@RequestParam(value="importType",required=false)String importType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("importType",importType);
        return new ModelAndView("/saas/zzb/app/console/bsetB01/importParameter",map);
    }
    /**
     * 保存部务会信息
     * @return
     */
    @RequestMapping(value = "/save")
    public @ResponseBody Map<String, Object> save(@ModelAttribute AppBsetB01Vo appGbcxB01Vo, HttpServletRequest req) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        AppBsetB01 appGbcxB01 = null;
        int newPx = appGbcxB01Vo.getPx();
        int oldPx = 0;
        Integer oldPxInteger = this.appBsetB01Service.getMaxPx(appGbcxB01Vo.getParentId());
        if(oldPxInteger != null){
            oldPx = oldPxInteger.intValue();
        }else{
            oldPx = 1;
        }

        try {
            if (appGbcxB01Vo != null) {
                String id = appGbcxB01Vo.getId();
                if (id != null && id.length() > 0) {//修改
                    appGbcxB01 = this.appBsetB01Service.getByPK(id);
                    oldPx = appGbcxB01.getPx();
                } else {//新增
                    appGbcxB01 = new AppBsetB01();
                }
                UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
                BeanUtils.copyProperties(appGbcxB01, appGbcxB01Vo);
                String parentId = appGbcxB01Vo.getParentId();
                AppBsetB01 parentAppGbcxB01 = null;
                if(parentId!=null && !parentId.equals("")){
                    parentAppGbcxB01 = this.appBsetB01Service.getByPK(parentId);
                    appGbcxB01.setParentB01(parentAppGbcxB01);
                }
                appGbcxB01.setTenant(userLoginDetails.getTenant());
               

                if(oldPx!=newPx) {
                    this.appBsetB01Service.updatePx(appGbcxB01Vo.getParentId(),oldPx,newPx);
                }
                if (id != null && id.length() > 0) {
                    BeanTrans.setBaseProperties(appGbcxB01, userLoginDetails, "update");
                    this.appBsetB01Service.update(appGbcxB01);
                } else {
                    BeanTrans.setBaseProperties(appGbcxB01, userLoginDetails, "save");
                    this.appBsetB01Service.save(appGbcxB01);
                }
                map.put("success", true);
            }
        } catch (Exception e) {
            map.put("success", false);
            map.put("message", "保存出错");
            throw new GenericException(e);
        }
        return map;
    }

    @RequestMapping(value="/delete/{id}")
    public @ResponseBody Map<String,Object> delete(@PathVariable("id") String id,String dataType)throws GenericException{
        Map<String,Object> map = Maps.newHashMap();
        try {
            if (StringUtils.isNoneBlank(id)) {
                if(dataType.equals("fl")){
                    this.appBsetFlService.deleteByPK(id);
                }else {
                    appBsetB01Service.deleteByPK(id);
                }
                map.put("success", true);
            } else {
                map.put("success", false);
                map.put("message", "删除失败");
            }
        }catch(Exception e){
            logger.error(e, e);
        }
        return map;
    }

    @RequestMapping(value = "/import")
    public @ResponseBody Map<String, Object> importData(@ModelAttribute ImportParameterVo importParameterVo, HttpServletRequest req) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
       // importType;//gwyglxt从公务员管理系统(浙大网新) zzzhywpt从组织综合业务平台 gbglxt从干部管理系统

        try {
             map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            map.put("message", "保存出错");
            throw new GenericException(e);
        }
        return map;
    }

}
