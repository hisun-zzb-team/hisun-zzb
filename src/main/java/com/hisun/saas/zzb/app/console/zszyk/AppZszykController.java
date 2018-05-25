package com.hisun.saas.zzb.app.console.zszyk;

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
@RequestMapping("/zzb/app/console/zszyk")
public class AppZszykController extends BaseController{


    //===============base
    @RequestMapping(value = "/list")
    public ModelAndView base(){
        Map<String, Object> map = Maps.newHashMap();
        PagerVo<AppAsetA01Vo> pagerVo = new PagerVo<AppAsetA01Vo>(null, 1, 1, 10);
        map.put("pager", pagerVo);
        return new ModelAndView("saas/zzb/app/console/zszyk/list",map);
    }

}
