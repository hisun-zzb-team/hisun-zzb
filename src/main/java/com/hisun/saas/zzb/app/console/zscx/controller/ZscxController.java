package com.hisun.saas.zzb.app.console.zscx.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetB01;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetB01Service;
import com.hisun.saas.zzb.app.console.zscx.entity.AppZscxZs;
import com.hisun.saas.zzb.app.console.zscx.vo.AppZscxZsVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/zscx")
public class ZscxController extends BaseController {

    @Resource
    private AppBsetB01Service appBsetB01Service;
    @RequestMapping(value = "/")
    public ModelAndView list(){

        return new ModelAndView("saas/zzb/app/console/zscx/zscxManage");
    }

    /**
     * 机构管理下的职数查询
     * @return
     */
    @RequestMapping(value = "/zscxManage")
    public ModelAndView zscxManage(){

        return new ModelAndView("saas/zzb/app/console/zscxTmp/zscxManage");
    }

    @RequestMapping(value="/ajax/list")
    public ModelAndView getList(String b01Id,String xmQuery,
                                @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        Map<String, Object> map = Maps.newHashMap();
        try {

            String b0101 = "";
            if (StringUtils.isNotBlank(b01Id)) {
                if (!b01Id.equals("allZs")) {
                    AppBsetB01 appBsetB01 = this.appBsetB01Service.getByPK(b01Id);
                    b0101 = appBsetB01.getB0101();
                }
            }
            if(b01Id.equals("001.001")){
                b01Id = "allZs";
            }
            map.put("b01Id", b01Id);
            map.put("b0101", b0101);
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/app/console/zscxTmp/list", map);

    }
}
