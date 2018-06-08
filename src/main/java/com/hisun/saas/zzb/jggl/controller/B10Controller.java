/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.jggl.controller;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
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
@RequestMapping("/zzb/jggl/b10")
public class B10Controller extends BaseController {
    @Resource
    private B10Service b10Service;
    @Resource
    private B01Service b01Service;
    @RequestMapping(value = "/ajax/hjxx")
    public ModelAndView txxx(String currentId) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            if(StringUtils.isNotBlank(currentId)){
                B10Vo vo = new B10Vo();
                CommonConditionQuery query = new CommonConditionQuery();
                query.add(CommonRestrictions.and(" b01.id = :currentId ", "currentId", currentId));
                List<B10> resultList = b10Service.list(query,null);
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
        return new ModelAndView("saas/zzb/jggl/b01/hjxx", map);
    }

    @RequestMapping(value = "/updateOrSave")
    @ResponseBody
    public Map<String, Object> updateOrSave(B10Vo vo,String currentId) {
        Map<String, Object> map = Maps.newHashMap();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        B10 entity = new B10();
        try {
            if (StringUtils.isNotBlank(vo.getB1000())) {
                entity = b10Service.getByPK(vo.getB1000());
                BeanUtils.copyProperties(vo,entity);
                EntityWrapper.wrapperUpdateBaseProperties(entity,details);
                b10Service.update(entity);
            }else {
                BeanUtils.copyProperties(vo,entity);
                EntityWrapper.wrapperSaveBaseProperties(entity,details);
                entity.setB01(b01Service.getByPK(currentId));
                b10Service.save(entity);
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
