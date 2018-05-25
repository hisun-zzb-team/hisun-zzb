package com.hisun.saas.zzb.app.console.bigdata;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.exception.GenericException;
import com.hisun.saas.zzb.app.console.bset.vo.B01TreeVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/bigdata")
public class BigdataController extends BaseController{

    @RequestMapping(value = "/dashboard")
    public ModelAndView list(){
        return new ModelAndView("saas/zzb/app/console/bigdata/dashboard");
    }

}
