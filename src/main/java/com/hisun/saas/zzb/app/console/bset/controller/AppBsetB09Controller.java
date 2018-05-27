package com.hisun.saas.zzb.app.console.bset.controller;

import com.hisun.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/bset/b09")
public class AppBsetB09Controller extends BaseController{



    @RequestMapping(value = "/ajax/list")
    public ModelAndView list(){
        return new ModelAndView("saas/zzb/app/console/bsetB01/b09/list");
    }

    @RequestMapping(value="/ajax/add")
    public ModelAndView addOrEdit(){
     Map<String, Object> map = new HashMap<String, Object>();
        return new ModelAndView("/saas/zzb/app/console/bsetB01/b09/addB09",map);
    }
}
