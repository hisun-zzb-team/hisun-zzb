/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.dacy.entity.EA38Log;
import com.hisun.saas.zzb.dzda.dacy.exchange.CyjlExcelExchange;
import com.hisun.saas.zzb.dzda.dacy.service.*;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marco {854476391@qq.com}
 */
@Controller
@RequestMapping("/zzb/dzda/ydrz")
public class YdrzController extends BaseController {
    @Resource
    private EApplyE01Z8Service eApplyE01Z8Service;
    @Resource
    private A38Service a38Service;
    @Resource
    private E01Z1Service e01Z1Service;
    @Resource
    private EPopedomE01Z1RelationService ePopedomE01Z1RelationService;
    @Resource
    private EA38LogService eA38LogService;
    @Resource
    private EA38LogViewTimeService eA38LogViewTimeService;
    @Resource
    private EA38LogDetailService eA38LogDetailService;
    @Resource
    private ELogDetailViewTimeService eLogDetailViewTimeService;

    @Resource
    CyjlExcelExchange cyjlExcelExchange;

    @Value("${sys.upload.absolute.path}")
    private String uploadBasePath;


    @RequiresPermissions("ydrz:*")
    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                             @RequestParam(value = "starttime",required = false)String starttime,
                             @RequestParam(value = "endtime",required = false)String endtime,
                             @RequestParam(value = "cyrName",required = false)String cyrName,
                             @RequestParam(value = "a0101",required = false)String a0101,
                             @RequestParam(value = "ydlx",required = false)String ydlx
    ) throws UnsupportedEncodingException {
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String,Object> model = new HashMap<String,Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        this.buildParam(query,starttime,endtime,cyrName,a0101,ydlx);
        Long total = eA38LogService.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.desc("cysj"));
        List<EA38Log> resultList = eA38LogService.list(query,orderBy,pageNum,pageSize);
        PagerVo<EA38Log> pager = new PagerVo<EA38Log>(resultList, total.intValue(), pageNum, pageSize);
        model.put("pager",pager);
        model.put("starttime",starttime);
        model.put("a0101",a0101);
        model.put("cyrName",cyrName);
        model.put("ydlx",ydlx);
        model.put("endtime",endtime);
        return new ModelAndView("saas/zzb/dzda/ydrz/list",model);
    }

    private void buildParam(CommonConditionQuery query,String starttime,String endtime,String cyrName,String a0101,String ydlx){
        if(StringUtils.isNotBlank(cyrName)){
            query.add(CommonRestrictions.and("cyrName = :e01Z807Name ", "e01Z807Name", cyrName));
        }
        if(StringUtils.isNotBlank(a0101)){
            query.add(CommonRestrictions.and("a0101 like :a0101 ", "a0101", "%"+a0101+"%"));
        }
        if(StringUtils.isNotBlank(starttime)){
            query.add(CommonRestrictions.and("cysj >= :starttime ", "starttime", new DateTime(starttime).toDate()));
        }
        if(StringUtils.isNotBlank(endtime)){
            query.add(CommonRestrictions.and("cysj <= :endtime ", "endtime", new DateTime(endtime).toDate()));
        }
        if(StringUtils.isNotBlank(ydlx)){
            if("0".equals(ydlx)){
                query.add(CommonRestrictions.and("(applyE01Z8 is not null or applyE01Z8.id <>'') and 1=:applyE01Z8", "applyE01Z8", 1));
            }else {
                query.add(CommonRestrictions.and("(applyE01Z8 is  null or applyE01Z8.id ='') and 1=:applyE01Z8", "applyE01Z8", 1));
            }
        }
    }

}
