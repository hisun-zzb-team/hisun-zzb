/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.gbgl.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.a.entity.A01;
import com.hisun.saas.zzb.a.service.A01Service;
import com.hisun.saas.zzb.a.vo.A01Vo;
import com.hisun.saas.zzb.a.vo.QueryModel;
import com.hisun.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Marco {854476391@qq.com}
 */
@Controller
@RequestMapping("/zzb/gbgl/a01")
public class A01Controller extends BaseController {
    @Resource
    private A01Service a01Service;

    @Value("${sys.upload.absolute.path}")
    private String uploadBasePath;
    @RequestMapping(value = "/index")
    public
    @ResponseBody
    ModelAndView index(HttpServletRequest request) {
        Map<String, Object> map = Maps.newHashMap();
        return new ModelAndView("saas/zzb/gbgl/a01/index", map);
    }

    @RequestMapping(value = "/ajax/list")
    public ModelAndView getList(String b01Id, String b0101,QueryModel queryModel,
                                @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            List<A01Vo> vos = Lists.newArrayList();
            CommonConditionQuery query = new CommonConditionQuery();
            CommonOrderBy orderBy = new CommonOrderBy();
            Long total = 0L;
            if(StringUtils.isNotBlank(queryModel.getA0101Query())){
                query.add(CommonRestrictions.and(" a0101 like :a0101 ", "a0101", "%"+queryModel.getA0101Query() + "%"));
            }
           /* if(StringUtils.isNotBlank(queryModel.getParentIdQuery())){
                query.add(CommonRestrictions.and(" a0101 like :a0101 ", "a0101", "%"+queryModel.getA0101Query() + "%"));
            }*/
            if(StringUtils.isNotBlank(queryModel.getA0157BQuery())){
                query.add(CommonRestrictions.and(" a0157B = :a0157B ", "a0157B", queryModel.getA0157BQuery()));
            }
            if(StringUtils.isNotBlank(queryModel.getaGbztbQuery())){
                query.add(CommonRestrictions.and(" aGbztb = :aGbztb ", "aGbztb", queryModel.getaGbztbQuery()));
            }
            List<A01> list = a01Service.list(query,orderBy,pageNum,pageSize);
            total = a01Service.count(query);
            for(A01 entity :list){
                A01Vo vo = new A01Vo();
                BeanUtils.copyProperties(entity,vo);
                vos.add(vo);
            }
            PagerVo<A01Vo> pager = new PagerVo<A01Vo>(vos, total.intValue(), pageNum, pageSize);
            map.put("queryModel",queryModel);
            map.put("pager", pager);
            map.put("b01Id", b01Id);
            map.put("b0101", b0101);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/gbgl/a01/rightList", map);

    }

    @RequestMapping(value = "/ajax/manage")
    public ModelAndView mangePage(String a01Id,String b01Id) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            map.put("a01Id", a01Id);
            map.put("b01Id", b01Id);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/gbgl/a01/manage", map);
    }
    @RequestMapping(value = "/ajax/jbxx")
    public ModelAndView jbxx(String a01Id,String b01Id) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            A01Vo vo = new A01Vo();
            if(StringUtils.isNotBlank(a01Id)){
                A01 a01 = a01Service.getByPK(a01Id);
                BeanUtils.copyProperties(a01,vo);
            }
            map.put("b01Id",b01Id);
            map.put("vo", vo);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/gbgl/a01/jbxx", map);
    }

    @RequestMapping(value = "/updateOrSave")
    @ResponseBody
    public Map<String, Object> updateOrSave(A01Vo vo) {
        Map<String, Object> map = Maps.newHashMap();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        try {
            A01 entity = new A01();
            String a01Id = "";
            if (StringUtils.isNotBlank(vo.getA0100())) {
                a01Id = vo.getA0100();
                entity = a01Service.getByPK(vo.getA0100());
                BeanUtils.copyProperties(vo,entity);
                a01Service.update(entity);
            } else {
                BeanUtils.copyProperties(vo,entity);
                a01Id = a01Service.save(entity);
            }
            map.put("a01Id",a01Id);
            map.put("code", 1);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("code", 0);
        }
        return map;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> delete(String id) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            a01Service.deleteByPK(id);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            map.put("message", "删除失败");
            map.put("success", false);
        }
        return map;
    }
    
}
