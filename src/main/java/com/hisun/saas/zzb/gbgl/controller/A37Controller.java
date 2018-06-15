/*
 * Copyright (c) 2378. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.gbgl.controller;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.a.entity.A37;
import com.hisun.saas.zzb.a.service.A01Service;
import com.hisun.saas.zzb.a.service.A37Service;
import com.hisun.saas.zzb.a.vo.A37Vo;
import com.hisun.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Marco {854476391@qq.com}
 */
@Controller
@RequestMapping("/zzb/gbgl/a37")
public class A37Controller extends BaseController {
    @Resource
    private A01Service a01Service;

    @Resource
    private A37Service a37Service;

    @RequestMapping(value = "/ajax/lxfs")
    public ModelAndView lxfs(String a01Id) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" a01.a0100 = :a01Id ", "a01Id",a01Id));
            A37Vo vo = new A37Vo();
            CommonOrderBy orderBy = new CommonOrderBy();
            List<A37> a37s = a37Service.list(query,orderBy);
            if(a37s!=null && !a37s.isEmpty()){
                BeanUtils.copyProperties(a37s.get(0),vo);
            }
            map.put("a01Id",a01Id);
            map.put("vo", vo);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/gbgl/a37/lxfs", map);
    }

    @RequestMapping(value = "/saveOrUpdate")
    @ResponseBody
    public Map<String, Object> saveOrUpdate(A37Vo vo,String a01Id) {
        Map<String, Object> map = Maps.newHashMap();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        try {
            A37 entity = new A37();
            if (StringUtils.isNotBlank(vo.getA3700())) {
                entity = a37Service.getByPK(vo.getA3700());
                BeanUtils.copyProperties(vo,entity);
                EntityWrapper.wrapperUpdateBaseProperties(entity,details);
                a37Service.update(entity);
            } else {
                BeanUtils.copyProperties(vo,entity);
                entity.setA01(a01Service.getByPK(a01Id));
                EntityWrapper.wrapperSaveBaseProperties(entity,details);
                a37Service.save(entity);
            }
            map.put("a01Id",a01Id);
            map.put("code", 1);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("code", 0);
        }
        return map;
    }
}
