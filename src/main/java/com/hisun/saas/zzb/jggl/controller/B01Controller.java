/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.jggl.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.b.entity.B01;
import com.hisun.saas.zzb.b.service.B01Service;
import com.hisun.saas.zzb.b.vo.B01Vo;
import com.hisun.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rocky {rockwithyou@126.com}
 */
@Controller
@RequestMapping("/zzb/jggl/b01")
public class B01Controller extends BaseController {
    @Resource
    private B01Service b01Service;

    @RequestMapping(value = "/index")
    public
    @ResponseBody
    ModelAndView index(HttpServletRequest request) {
        Map<String, Object> map = Maps.newHashMap();
        return new ModelAndView("saas/zzb/jggl/b01/index", map);
    }

    @RequestMapping(value = "/ajax/list")
    public ModelAndView getList(String parentB01Id, String b01Id,String b0101,
                                @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                @RequestParam(value = "b0101Query", required = false)String b0101Query,
                                @RequestParam(value = "parentIdQuery", required = false)String parentIdQuery,
                                @RequestParam(value = "parentNameQuery", required = false)String parentNameQuery,
                                @RequestParam(value = "b0127Query", required = false)String b0127Query,
                                @RequestParam(value = "b0127AQuery", required = false)String b0127AQuery,
                                @RequestParam(value = "bGllbBQuery", required = false)String bGllbBQuery,
                                @RequestParam(value = "bGllbAQuery", required = false)String bGllbAQuery
                                ) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            List<B01> resultList = Lists.newArrayList();
            CommonOrderBy orderBy = new CommonOrderBy();
            Long total =0L;
            B01 b01 = new B01();
            if(StringUtils.isBlank(b0101Query) && StringUtils.isBlank(parentIdQuery) && StringUtils.isBlank(b0127Query) &&
                    StringUtils.isBlank(bGllbBQuery)){
                 b01 = this.b01Service.getByPK(b01Id);
                b0101 = b01.getB0101();
                query.add(CommonRestrictions.and(" bCxbm like :bCxbm ", "bCxbm",  b01.getbCxbm()+"%" ));

            }else {
                if(StringUtils.isNotBlank(b0101Query)){
                    query.add(CommonRestrictions.and(" b0101 like :b0101 ", "b0101", "%"+b0101Query+"%" ));
                }
                if(StringUtils.isNotBlank(parentIdQuery)){
                    b01 = this.b01Service.getByPK(parentIdQuery);
                    query.add(CommonRestrictions.and(" bCxbm like :bCxbm ", "bCxbm",  b01.getbCxbm()+"%" ));
                }
                if(StringUtils.isNotBlank(b0127Query)){
                    query.add(CommonRestrictions.and(" b0127 = :b0127 ", "b0127",  b0127Query ));
                }
                if(StringUtils.isNotBlank(bGllbBQuery)){
                    query.add(CommonRestrictions.and(" bGllbB = :bGllbB ", "bGllbB",  bGllbBQuery ));
                }
            }
            total = b01Service.count(query);
            orderBy.add(CommonOrder.asc("bCxbm"));
            resultList = b01Service.list(query, orderBy, pageNum, pageSize);
            List<B01Vo> b01Vos = new ArrayList<B01Vo>();
            B01Vo vo = new B01Vo();
            for (B01 entity : resultList) {
                vo = new B01Vo();
                BeanUtils.copyProperties(entity, vo);
                b01Vos.add(vo);
            }
            PagerVo<B01Vo> pager = new PagerVo<B01Vo>(b01Vos, total.intValue(), pageNum, pageSize);
            map.put("b0101Query",b0101Query);
            map.put("parentIdQuery",parentIdQuery);
            map.put("parentNameQuery",parentNameQuery);
            map.put("b0127Query",b0127Query);
            map.put("b0127AQuery",b0127AQuery);
            map.put("bGllbBQuery",bGllbBQuery);
            map.put("bGllbAQuery",bGllbAQuery);
            map.put("pager", pager);
            map.put("b01Id", b01Id);
            map.put("parentB01Id", parentB01Id);
            map.put("b0101", b0101);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/jggl/b01/rightList", map);

    }
    @RequestMapping(value = "/updateOrSave")
    @ResponseBody
    public Map<String, Object> updateOrSave(B01Vo vo) {
        Map<String, Object> map = Maps.newHashMap();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        B01 entity = new B01();
        B01 oldB01 = new B01();
        String bSjlx = vo.getbSjlx();
        try {
            //法人机构
                if (StringUtils.isNotBlank(vo.getB0100())) {
                    entity = b01Service.getByPK(vo.getB0100());
                    String oldPid = entity.getB0100();
                    BeanUtils.copyProperties(entity,oldB01);
                    BeanUtils.copyProperties(vo,entity);
                    entity.setParentB01(b01Service.getByPK(vo.getParentId()));
                    this.b01Service.updateB01(entity,oldB01,oldPid);
                }else {
                    String currentId = "";
                    currentId = b01Service.saveB01(vo);
                    map.put("currentId",currentId);
                }
            map.put("code", 1);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("code", 0);
        }
        return map;
    }

    @RequestMapping(value = "/manage")
    public ModelAndView mangePage(String bSjlx, String b01Id,String currentId,String isAdd) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            map.put("bSjlx", bSjlx);
            map.put("b01Id", b01Id);
            map.put("currentId", currentId);
            map.put("isAdd",isAdd);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/jggl/b01/manage", map);
    }

    @RequestMapping(value = "/ajax/jbxx")
    public ModelAndView jbxx(String b01Id,String b0101,String bSjlx,String currentId,String isAdd) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            B01Vo vo = new B01Vo();
            vo.setbSjlx(bSjlx);
            vo.setB01Id(b01Id);
            if(StringUtils.isNotBlank(currentId)){
                B01 entity = b01Service.getByPK(currentId);
                BeanUtils.copyProperties(entity,vo);
                vo.setParentId(entity.getParentB01().getB0100());
                vo.setParentName(entity.getParentB01().getB0101());
            }else{
                vo.setParentId(b01Id);
                vo.setParentName( b01Service.getByPK(b01Id).getB0101());
            }
            map.put("vo",vo);
            Integer sort = b01Service.getMaxSort(b01Id);

            map.put("isAdd",isAdd);
            map.put("sort",sort);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/jggl/b01/jbxx", map);
    }
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String,Object> delete(String id){
        Map<String, Object> map = Maps.newHashMap();
        try{
            b01Service.deleteByPK(id);
            map.put("success", true);
        }catch (Exception e){
            logger.error(e);
            map.put("message","删除失败");
            map.put("success", false);
        }
        return map;
    }

}
