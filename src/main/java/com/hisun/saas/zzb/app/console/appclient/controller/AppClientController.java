/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.appclient.controller;

import com.google.common.collect.Lists;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.appclient.entity.AppClient;
import com.hisun.saas.zzb.app.console.appclient.service.AppClientService;
import com.hisun.saas.zzb.app.console.appclient.vo.AppClientVo;
import com.hisun.saas.sys.util.BeanTrans;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/appClient")
public class AppClientController extends BaseController {
    @Resource
    private AppClientService appClientService;

    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping("/")
    public ModelAndView list(HttpServletRequest req,String identificationQuery,String statusQuery,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));

            if(identificationQuery!=null && !identificationQuery.equals("")){
                query.add(CommonRestrictions.and(" identification like:identificationQuery", "identificationQuery", "%"+ identificationQuery+ "%"));
            }
            if(statusQuery!=null && !statusQuery.equals("") && !statusQuery.equals("-1")){
                query.add(CommonRestrictions.and(" status = :statusQuery", "statusQuery", Integer.valueOf(statusQuery)));
            }
            CommonOrderBy orderBy = new CommonOrderBy();
//            orderBy.add(CommonOrder.asc("px"));

            Long total = this.appClientService.count(query);
            List<AppClient> appClients = this.appClientService.list(query, orderBy, pageNum,
                    pageSize);
            List<AppClientVo> appClientVos = new ArrayList<AppClientVo>();
            if (appClients != null) {
                for (AppClient appClient : appClients) {
                    AppClientVo vo = new AppClientVo();
                    BeanUtils.copyProperties(vo, appClient);
                    appClientVos.add(vo);
                }
            }
            PagerVo<AppClientVo> pager = new PagerVo<AppClientVo>(appClientVos, total.intValue(),
                    pageNum, pageSize);
            map.put("pager", pager);
            map.put("identificationQuery", identificationQuery);
            map.put("statusQuery", statusQuery);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/appClient/list", map);
    }

    @RequestMapping(value ="/changeStatus/{id}")
    public @ResponseBody Map<String, Object> changeStatus(@PathVariable("id")String id) {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            String statustdValue = "";
            String statusBtnValue = "";
            AppClient appClient = this.appClientService.getByPK(id);
            if(appClient.getStatus()==AppClient.OFF){
                appClient.setStatus(AppClient.ON);
                statustdValue = "正常";
                statusBtnValue = "停用该设备";
            }else{
                appClient.setStatus(AppClient.OFF);
                statustdValue = "停用";
                statusBtnValue = "启用该设备";
            }
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            BeanTrans.setBaseProperties(appClient, userLoginDetails, "update");
            this.appClientService.update(appClient);
            map.put("success", true);
            map.put("statustdValue", statustdValue);
            map.put("statusBtnValue", statusBtnValue);
        } catch (Exception e) {
            map.put("success", false);
            throw new GenericException(e);
        }
        return map;
    }

}
