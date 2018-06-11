/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.gbgl.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.zzb.a.entity.A01;
import com.hisun.saas.zzb.a.service.A01Service;
import com.hisun.saas.zzb.a.vo.A01Vo;
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
    public ModelAndView getList(String b01Id, String b0101,
                                @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            List<A01Vo> vos = Lists.newArrayList();
            List<A01> list = a01Service.list();
            Long total = a01Service.count();
            for(A01 entity :list){
                A01Vo vo = new A01Vo();
                BeanUtils.copyProperties(entity,vo);
                vos.add(vo);
            }
            PagerVo<A01Vo> pager = new PagerVo<A01Vo>(vos, total.intValue(), pageNum, pageSize);
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
    public ModelAndView mangePage(String b01Id) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            map.put("b01Id", b01Id);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/gbgl/a01/manage", map);
    }
}
