package com.hisun.saas.zzb.app.console.gbrm;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.zzb.app.console.aset.vo.AppAsetA01Vo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/gbrm")
public class AppGbrmController extends BaseController{

    @RequestMapping(value = "/dashboard")
    public ModelAndView dashboard(){
        return new ModelAndView("saas/zzb/app/console/gbrm/dashboard");
    }
    //===============base
    @RequestMapping(value = "/list")
    public ModelAndView list(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbrm/list",map);
    }
    @RequestMapping(value = "/ry/list")
    public ModelAndView ryList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbrm/ryList",map);
    }
    @RequestMapping(value = "/ryManage")
    public ModelAndView ryManage(){
        return new ModelAndView("saas/zzb/app/console/gbrm/manage/ryManage");
    }

    @RequestMapping(value = "/ajax/base")
    public ModelAndView base(){
        return new ModelAndView("saas/zzb/app/console/gbrm/manage/base");
    }
    @RequestMapping(value = "/ajax/rmqk")
    public ModelAndView rmqk(){
        return new ModelAndView("saas/zzb/app/console/gbrm/manage/rmqk");
    }
    @RequestMapping(value = "/ajax/kcqk")
    public ModelAndView kcqk(){
        return new ModelAndView("saas/zzb/app/console/gbrm/manage/kcqk");
    }
    @RequestMapping(value = "/ajax/zqyj")
    public ModelAndView zqyj(){
        return new ModelAndView("saas/zzb/app/console/gbrm/manage/zqyj");
    }

    @RequestMapping(value = "/ybList")
    public ModelAndView ybList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbrm/yb/list",map);
    }
    @RequestMapping(value = "/ybry/list")
    public ModelAndView ybryList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbrm/yb/ryList",map);
    }
    @RequestMapping(value = "/shcl/list")
    public ModelAndView shcllist(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbrm/shcl/list",map);
    }
    @RequestMapping(value = "/shcl/ryList")
    public ModelAndView shclryList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbrm/shcl/ryList",map);
    }
    @RequestMapping(value = "/rmtz/rqgs/list")
    public ModelAndView rqgslist(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbrm/rmtz/rqgs/list",map);
    }
    @RequestMapping(value = "/rmtz/rqgs/ryList")
    public ModelAndView rqgsryList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbrm/rmtz/rqgs/ryList",map);
    }
    @RequestMapping(value = "/rmtz/tz/list")
    public ModelAndView tzlist(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbrm/rmtz/tz/list",map);
    }
    @RequestMapping(value = "/rmtz/tz/yrList")
    public ModelAndView tzRyList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbrm/rmtz/tz/ryList",map);
    }
    @RequestMapping(value = "/xrjs/list")
    public ModelAndView xrjslist(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbrm/xrjs/list",map);
    }
    @RequestMapping(value = "/rmtj/list")
    public ModelAndView rmtjList(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 10, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/gbrm/rmtj/list",map);
    }
}
