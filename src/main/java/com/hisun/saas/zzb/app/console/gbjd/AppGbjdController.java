/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbjd;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA01;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA01Service;
import com.hisun.saas.zzb.app.console.aset.vo.AppAsetA01Vo;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetB01;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetFl;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetB01Service;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetFlService;
import com.hisun.saas.sys.util.GzjlUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/gbjd")
public class AppGbjdController extends BaseController{
    @Resource
    private AppAsetA01Service appAsetA01Service;
    @Resource
    private AppBsetB01Service appBsetB01Service;
    @Resource
    private AppBsetFlService appBsetFlService;

    @RequestMapping(value = "/dashboard")
    public ModelAndView dashboard(){
        return new ModelAndView("saas/zzb/app/console/gbjd/dashboard");
    }

    @RequestMapping(value = "/base")
    public ModelAndView base(){
        return new ModelAndView("saas/zzb/app/console/gbjd/base/a01/a01QueryManage");
    }
    @RequestMapping(value = "/ajax/list")
    public ModelAndView getList(String b01Id, String xmQuery,
                                @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Map<String, Object> map = Maps.newHashMap();
        boolean isHiddenFl = false;
        try {
            AppBsetFl appBsetFl = this.appBsetFlService.getTopFl();
            List<Object> paramList = Lists.newArrayList();
            String hql = "from AppAsetA01 a01  "
                    +"inner join a01.appAsetA02s a02 "
                    +"inner join a02.appBsetB01 b01 ";
            if(appBsetFl.getIsHidden()== AppBsetFl.DISPLAY){
                hql +="inner join b01.appBsetFl2B01s fltob01 ";
            }
            hql+= "where a01.tombstone =? ";
            paramList.add(0);
            String b0101 = "";
            if (StringUtils.isNotBlank(b01Id)) {
                if (!b01Id.equals("allA01")) {
                    paramList.add(b01Id);
                    hql = hql + " and b01.id = ?";
                    AppBsetB01 appBsetB01 = this.appBsetB01Service.getByPK(b01Id);
                    b0101 = appBsetB01.getB0101();
                }

            }

            if (xmQuery != null && !xmQuery.equals("")) {
                paramList.add("%" + xmQuery + "%");
                hql = hql + " and a01.xm like ?";
            }
            if(appBsetFl.getIsHidden()== AppBsetFl.DISPLAY) {
                hql = hql + " order by fltob01.px,b01.px,a02.jtlPx ";
            }else{
                hql = hql + " order by b01.queryCode,a02.jtlPx ";
            }

            int total = this.appAsetA01Service.count("select  count(distinct a01.id) " + hql, paramList);
            List<AppAsetA01> appAsetA01s = this.appAsetA01Service.list("select  DISTINCT(a01) " + hql, paramList, pageNum,
                    pageSize);
            List<AppAsetA01Vo> appAsetA01Vos = new ArrayList<AppAsetA01Vo>();
            for (AppAsetA01 a01 : appAsetA01s) {
                AppAsetA01Vo vo = new AppAsetA01Vo();
                BeanUtils.copyProperties(vo, a01);
                appAsetA01Vos.add(vo);
            }
            PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(appAsetA01Vos, total, pageNum, pageSize);

            map.put("pager", pagerVo);
            map.put("b01Id", b01Id);
            map.put("xmQuery", xmQuery);
            map.put("b0101", b0101);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/app/console/gbjd/base/a01/list", map);

    }

    @RequestMapping(value = "/addManage")
    public ModelAndView addManage(String b01Id) throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        return new ModelAndView("/saas/zzb/app/console/gbjd/base/addManage", map);
    }
    @RequestMapping(value = "/ajax/view")
    public ModelAndView view(@RequestParam(value = "id") String id, @RequestParam(value = "b01Id") String b01Id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            AppAsetA01 a01 = this.appAsetA01Service.getByPK(id);
            AppAsetA01Vo a01Vo = new AppAsetA01Vo();
            if (a01 == null) {
                logger.error("数据不存在");
                throw new GenericException("数据不存在");
            }
            BeanUtils.copyProperties(a01Vo, a01);
            if (a01.getGzjlStr() != null && !a01.getGzjlStr().equals("")) {
                a01Vo.setGzjlStrs(GzjlUtil.matchGzjlStr(a01.getGzjlStr()));
            }
            map.put("a01Vo", a01Vo);
            map.put("b01Id", b01Id);
        } catch (Exception e) {
            map.put("success", false);
            map.put("msg", "查看失败！");
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/gbjd/base/a01/view", map);
    }
    @RequestMapping(value = "/view2")
    public ModelAndView view2(String b01Id) throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        return new ModelAndView("/saas/zzb/app/console/gbjd/base/view2", map);
    }
    @RequestMapping(value = "/view3")
    public ModelAndView view3(String b01Id) throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        return new ModelAndView("/saas/zzb/app/console/gbjd/base/view3", map);
    }
    @RequestMapping(value = "/ajax/editBase")
    public ModelAndView editBase(){
        return new ModelAndView("saas/zzb/app/console/gbjd/base/addBase");
    }

    @RequestMapping(value = "/ajax/grsxchGrList")
    public ModelAndView grsxchGrList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/base/sjccgbGrList",map);
    }

    @RequestMapping(value = "/ajax/sjgbGrList")
    public ModelAndView sjgbGrList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/base/sjgbGrList",map);
    }
    @RequestMapping(value = "/ajax/xfjbGrList")
    public ModelAndView xfjbGrList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/base/xfjbGrList",map);
    }

    @RequestMapping(value = "/ajax/ssyjList")
    public ModelAndView ssyjList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/ssyj/list",map);
    }
    @RequestMapping(value = "/ajax/zrzjGrList")
    public ModelAndView zrzjGrList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/base/zrzjGrList",map);
    }
    @RequestMapping(value = "/ajax/wzGrList")
    public ModelAndView wzGrList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/base/wzGrList",map);
    }
    //=========================

    @RequestMapping(value = "/grsxch/sjcc")
    public ModelAndView sjcc(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/grsxch/sjcc/list", map);
    }

    @RequestMapping(value = "/grsxch/sjccgbList")
    public ModelAndView sjccgbList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/grsxch/sjcc/ccgbList",map);
    }
    @RequestMapping(value = "/grsxch/sjccgbAdd")
    public ModelAndView sjccgbAdd(){
        return new ModelAndView("saas/zzb/app/console/gbjd/grsxch/sjcc/sjccgbAdd");
    }
    @RequestMapping(value = "/grsxch/zdch")
    public ModelAndView zdch(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/grsxch/zdch/list",map);
    }
    @RequestMapping(value = "/grsxch/zdchgbList")
    public ModelAndView zdchgbList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/grsxch/sjcc/zdchgbList",map);
    }
    @RequestMapping(value = "/grsxch/zdchgbAdd")
    public ModelAndView zdchgbAdd(){
        return new ModelAndView("saas/zzb/app/console/gbjd/grsxch/sjcc/zdchgbAdd");
    }

    @RequestMapping(value = "/jjzrsj")
    public ModelAndView jjzrsj(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/jjzrsj/list",map);
    }
    @RequestMapping(value = "/jjzrsj/sjgbList")
    public ModelAndView sjgbList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/jjzrsj/sjgbList",map);
    }

    @RequestMapping(value = "/jjzrsj/edit")
    public ModelAndView jjzrsjEdit(){
        return new ModelAndView("saas/zzb/app/console/gbjd/jjzrsj/edit");
    }


    @RequestMapping(value = "/xfjb")
    public ModelAndView xfjb(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/xfjb/list",map);
    }

    @RequestMapping(value = "/xfjb/edit")
    public ModelAndView xfjbEdit(){
        return new ModelAndView("saas/zzb/app/console/gbjd/xfjb/edit");
    }

    @RequestMapping(value = "/ybglpy")
    public ModelAndView ybglpy(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/ybglpy/list",map);
    }
    @RequestMapping(value = "/ybglpy/pyjgList")
    public ModelAndView pyjgList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/ybglpy/pyjgList",map);
    }
    @RequestMapping(value = "/ybglpy/edit")
    public ModelAndView ybglpyEdit(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/ybglpy/edit",map);
    }
    @RequestMapping(value = "/zrzj")
    public ModelAndView zrzj(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/zrzj/list",map);
    }
    @RequestMapping(value = "/zrzj/edit")
    public ModelAndView zrzjEdit(){
        return new ModelAndView("saas/zzb/app/console/gbjd/zrzj/edit");
    }

    @RequestMapping(value = "/wz/dzzwz")
    public ModelAndView dzzwz(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/wz/dzzwz/list",map);
    }
    @RequestMapping(value = "/wz/ldgbwz")
    public ModelAndView wz(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbjd/wz/ldgbwz/list",map);
    }
    @RequestMapping(value = "/wz/dzzwz/edit")
    public ModelAndView dzzwzEdit(){
        return new ModelAndView("saas/zzb/app/console/gbjd/wz/dzzwz/edit");
    }
    @RequestMapping(value = "/wz/ldgbwz/edit")
    public ModelAndView ldgbwzEdit(){
        return new ModelAndView("saas/zzb/app/console/gbjd/wz/ldgbwz/edit");
    }
    @RequestMapping(value = "/ajax/viewGbjd")
    public ModelAndView viewGbjd(){
        return new ModelAndView("saas/zzb/app/console/gbjd/viewGbjd");
    }
}
