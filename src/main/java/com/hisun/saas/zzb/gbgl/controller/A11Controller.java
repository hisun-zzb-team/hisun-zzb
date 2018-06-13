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
import com.hisun.saas.zzb.a.entity.A11;
import com.hisun.saas.zzb.a.service.A01Service;
import com.hisun.saas.zzb.a.service.A11Service;
import com.hisun.saas.zzb.a.vo.A11Vo;
import com.hisun.util.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/zzb/gbgl/a11")
public class A11Controller extends BaseController {

    @Resource
    private A01Service a01Service;

    @Resource
    private A11Service a11Service;

    @RequestMapping(value = "/ajax/list")
    public ModelAndView getList(String a01Id,
                                @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        Map<String, Object> map = Maps.newHashMap();
        List<A11Vo> vos = Lists.newArrayList();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" a01.a0100 = :a01Id ", "a01Id",a01Id));
            CommonOrderBy orderBy = new CommonOrderBy();
            Long total = 0L;
            List<A11> list = a11Service.list(query,orderBy,pageNum,pageSize);
            total = a11Service.count(query);
            for(A11 entity :list){
                A11Vo vo = new A11Vo();
                BeanUtils.copyProperties(entity,vo);
                vos.add(vo);
            }
            PagerVo<A11Vo> pager = new PagerVo<A11Vo>(vos, total.intValue(), pageNum, pageSize);
            map.put("a01Id",a01Id);
            map.put("pager", pager);
            map.put("success", true);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/gbgl/a11/list", map);
    }

    @RequestMapping(value = "/updateOrSave")
    @ResponseBody
    public Map<String, Object> updateOrSave(A11Vo vo) {
        Map<String, Object> map = Maps.newHashMap();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        try {
            A11 entity = new A11();
            if (StringUtils.isNotBlank(vo.getA1100())) {
                entity = a11Service.getByPK(vo.getA1100());
                org.springframework.beans.BeanUtils.copyProperties(vo,entity);
                a11Service.update(entity);
            } else {
                org.springframework.beans.BeanUtils.copyProperties(vo,entity);
                entity.setA01(a01Service.getByPK(vo.getA01Id()));
                a11Service.save(entity);
            }
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
            a11Service.deleteByPK(id);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            map.put("message", "删除失败");
            map.put("success", false);
        }
        return map;
    }
}
