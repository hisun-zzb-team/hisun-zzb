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
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.b.entity.B10;
import com.hisun.saas.zzb.b.service.B01Service;
import com.hisun.saas.zzb.b.service.B10Service;
import com.hisun.saas.zzb.b.vo.B10Vo;
import com.hisun.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Marco {854476391@qq.com}
 */
@Controller
@RequestMapping("/zzb/jggl/b10")
public class B10Controller extends BaseController {
    @Resource
    private B10Service b10Service;
    @Resource
    private B01Service b01Service;

    @RequestMapping(value = "/ajax/hjxx")
    public ModelAndView txxx(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, String currentId) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            List<B10Vo> voList = Lists.newArrayList();
            Long total = 0l;
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" b01.id = :currentId ", "currentId", currentId));
            List<B10> resultList = b10Service.list(query, null);
            total = b10Service.count(query);
            B10Vo vo = new B10Vo();
            if (resultList != null && !resultList.isEmpty()) {
                for (B10 b01 : resultList) {
                    vo = new B10Vo();
                    BeanUtils.copyProperties(b01, vo);
                    voList.add(vo);
                }
            }
            PagerVo<B10Vo> pager = new PagerVo<B10Vo>(voList, total.intValue(), pageNum, pageSize);
            map.put("pager",pager);
            map.put("success", true);
            map.put("currentId", currentId);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/jggl/b01/hjxx", map);
    }

    @RequestMapping(value = "/updateOrSave")
    @ResponseBody
    public Map<String, Object> updateOrSave(B10Vo vo, String currentId) {
        Map<String, Object> map = Maps.newHashMap();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        B10 entity = new B10();
        try {
            if (StringUtils.isNotBlank(vo.getB1000())) {
                entity = b10Service.getByPK(vo.getB1000());
                BeanUtils.copyProperties(vo, entity);
                EntityWrapper.wrapperUpdateBaseProperties(entity, details);
                b10Service.update(entity);
            } else {
                BeanUtils.copyProperties(vo, entity);
                EntityWrapper.wrapperSaveBaseProperties(entity, details);
                entity.setB01(b01Service.getByPK(currentId));
                b10Service.save(entity);
            }
            map.put("code", 1);
            map.put("message", "success");
        } catch (Exception e) {
            logger.error(e, e);
            map.put("code", 0);
        }
        return map;
    }
    @RequestMapping(value = "/ajax/add")
    public ModelAndView addB10(String b01Id){
        Map<String,Object> map = Maps.newHashMap();
        map.put("b01Id",b01Id);
        return new ModelAndView("saas/zzb/jggl/b10/add",map);
    }

    @RequestMapping(value = "/ajax/edit")
    public ModelAndView edit(String b1000,String b01Id){
        Map<String,Object> map = Maps.newHashMap();
        B10 b10 = b10Service.getByPK(b1000);
        B10Vo vo = new B10Vo();
        BeanUtils.copyProperties(b10,vo);
        map.put("b01Id",b01Id);
        map.put("vo",vo);
        return new ModelAndView("saas/zzb/jggl/b10/edit",map);
    }

    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id")String id) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            b10Service.deleteByPK(id);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            map.put("message", "删除失败");
            map.put("success", false);
        }
        return map;
    }

}
