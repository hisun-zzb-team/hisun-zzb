/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a38.controller;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.tenant.privilege.entity.TenantPrivilege;
import com.hisun.saas.sys.tenant.privilege.service.TenantPrivilegeService;
import com.hisun.saas.sys.tenant.privilege.vo.TenantPrivilegeVo;
import com.hisun.saas.sys.tenant.tenant.service.TenantService;
import com.hisun.saas.sys.tenant.user.service.TenantUserService;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.util.ValidateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author liuzj {279421824@qq.com}
*/
@Controller
@RequestMapping("/zzb/dzda/a38")
public class A38Controller extends BaseController {

    @Resource
    private A38Service a38Service;


    @RequiresPermissions("a38:*")
    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,@RequestParam(value = "pageSize",defaultValue = "10")int pageSize) throws UnsupportedEncodingException {
        Map<String,Object> model = new HashMap<String,Object>();
        CommonConditionQuery query = new CommonConditionQuery();
//        query.add(CommonRestrictions.and(" pId = :pId ", "pId", pId));
        Long total = a38Service.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
//        orderBy.add(CommonOrder.asc("sort"));
        List<A38> resultList = a38Service.list(query,orderBy,pageNum,pageSize);
        PagerVo<A38> pager = new PagerVo<A38>(resultList, total.intValue(), pageNum, pageSize);
        model.put("pager",pager);
        return new ModelAndView("saas/zzb/dzda/a38/list",model);
    }

    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/editManage")
    public ModelAndView editManage(String id){
        Map<String, Object> map = Maps.newHashMap();
        map.put("id",id);
        return new ModelAndView("saas/zzb/dzda/a38/manage",map);
    }

    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/add")
    public ModelAndView add(){
        Map<String, Object> map = Maps.newHashMap();
        return new ModelAndView("saas/zzb/dzda/a38/addDaBase",map);
    }
}