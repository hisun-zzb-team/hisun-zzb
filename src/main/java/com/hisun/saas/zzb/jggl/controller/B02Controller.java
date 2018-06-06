/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.jggl.controller;

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
import com.hisun.saas.zzb.b.entity.B02;
import com.hisun.saas.zzb.b.service.B01Service;
import com.hisun.saas.zzb.b.service.B02Service;
import com.hisun.saas.zzb.b.vo.B01Vo;
import com.hisun.saas.zzb.b.vo.B02Vo;
import com.hisun.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marco {854476391@qq.com}
 */
@Controller
@RequestMapping("/zzb/jggl/b02")
public class B02Controller extends BaseController {
    @Resource
    private B02Service b02Service;
    @Resource
    private B01Service b01Service;
    @RequestMapping(value = "/ajax/bzqk")
    public ModelAndView bzqk(String id) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            if(StringUtils.isNotBlank(id)){
                B02Vo vo = new B02Vo();
                CommonConditionQuery query = new CommonConditionQuery();
                query.add(CommonRestrictions.and(" b01.id = :id ", "id", id));
                List<B02> resultList = b02Service.list(query,null);
                if(resultList!=null && !resultList.isEmpty()){
                    BeanUtils.copyProperties(resultList.get(0),vo);
                }
                map.put("vo",vo);
              //  map.put("id",id);
            }
           // map.put("b01Id", b01Id);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/jggl/b01/bzqk", map);
    }
    @RequestMapping(value = "/updateOrSave")
    @ResponseBody
    public Map<String, Object> updateOrSave(B02Vo vo,String b01Id) {
        Map<String, Object> map = Maps.newHashMap();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        B02 entity = new B02();
        try {
            if (StringUtils.isNotBlank(vo.getB0200())) {
                entity = b02Service.getByPK(vo.getB0200());
                BeanUtils.copyProperties(vo,entity);
                EntityWrapper.wrapperUpdateBaseProperties(entity,details);
                b02Service.update(entity);
            }else {
                BeanUtils.copyProperties(vo,entity);
                EntityWrapper.wrapperSaveBaseProperties(entity,details);
                entity.setB01(b01Service.getByPK(b01Id));
                b02Service.save(entity);
            }
            map.put("code", 1);
            map.put("message","success");
        } catch (Exception e) {
            logger.error(e, e);
            map.put("code", 0);
        }
        return map;
    }

}
