/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dak.controller;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.admin.dictionary.entity.DictionaryItem;
import com.hisun.saas.sys.admin.dictionary.entity.DictionaryType;
import com.hisun.saas.sys.admin.dictionary.service.DictionaryItemService;
import com.hisun.saas.sys.admin.dictionary.service.DictionaryTypeService;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.log.LogOperateType;
import com.hisun.saas.sys.log.RequiresLog;
import com.hisun.saas.sys.taglib.selectTag.SelectNode;
import com.hisun.saas.sys.taglib.treeTag.TreeNode;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.a38.vo.A38Vo;
import com.hisun.saas.zzb.dzda.dacy.entity.EApplyE01Z8;
import com.hisun.saas.zzb.dzda.dacy.service.EApplyE01Z8Service;
import com.hisun.saas.zzb.dzda.dak.vo.DakVo;
import org.antlr.stringtemplate.CommonGroupLoader;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* @author liuzj {279421824@qq.com}
*/
@Controller
@RequestMapping("/zzb/dzda/dak")
public class DakController extends BaseController {

    @Resource
    private A38Service a38Service;

    @Resource
    private EApplyE01Z8Service eApplyE01Z8Service;

    @Resource
    private DictionaryItemService dictionaryItemService;

    @Resource
    private DictionaryTypeService dictionaryTypeService;

    @RequiresPermissions("a38:dakList ")
    @RequestMapping(value = "/manage")
    public ModelAndView manage(String id,String listType){
        return new ModelAndView("saas/zzb/dzda/dak/manage");
    }

    @RequiresPermissions("a38:dakList ")
    @RequestMapping("/ajax/bdwdalist")
    public ModelAndView bdwdalist(@RequestParam(value="pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
             HttpServletRequest request, String a0101Query, String gbztCodeQuery, String daztCodeQuery, String gbztContentQuery,
                                  String daztContentQuery, @ModelAttribute DakVo queryVo, String queryType) throws UnsupportedEncodingException {
        Map<String,Object> model = new HashMap<String,Object>();
        HttpSession session = request.getSession();
        if(queryType!=null && queryType.equals("gaojichaxun")){//高级查询
            a0101Query =  queryVo.getA0101();
            gbztCodeQuery = queryVo.getGbztCodes();
            gbztContentQuery =  queryVo.getGbztContents();
            daztCodeQuery= queryVo.getDaztCodes();
            daztContentQuery = queryVo.getDaztContents();
            session.setAttribute("queryDakVo",queryVo);
        } else if(queryType!=null && queryType.equals("listQuery")){//listQuery
            DakVo queryDakVo = (DakVo)session.getAttribute("queryDakVo");
            if(queryDakVo ==null){
                queryDakVo = new DakVo();
            }
            queryDakVo.setA0101(a0101Query);
            queryDakVo.setGbztCodes(gbztCodeQuery);
            queryDakVo.setGbztContents(gbztContentQuery);
            queryDakVo.setDaztCodes(daztCodeQuery);
            queryDakVo.setDaztContents(daztContentQuery);
            queryVo = queryDakVo;
            session.setAttribute("queryDakVo",queryVo);
        }else{
            DakVo queryDakVo = (DakVo)session.getAttribute("queryDakVo");
            if(queryDakVo ==null){
                queryDakVo = new DakVo();
            }
            a0101Query =queryDakVo.getA0101();
            gbztCodeQuery =queryDakVo.getGbztCodes();
            gbztContentQuery =queryDakVo.getGbztContents();
            daztCodeQuery = queryDakVo.getDaztCodes();
            daztContentQuery =queryDakVo.getDaztContents();
            queryVo = queryDakVo;
        }

        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        List<A38> resultList = a38Service.list(this.a38Service.getGjcxHql(queryVo,userLoginDetails), new ArrayList<Object>(), pageNum, pageSize);
        int total =   a38Service.list(this.a38Service.getGjcxHql(queryVo,userLoginDetails), new ArrayList<Object>(),1,100000).size();
        List<A38Vo> a38Vos = new ArrayList<A38Vo>();
        A38Vo vo = new A38Vo();
        for(A38 entity : resultList){
            vo = new A38Vo();
            BeanUtils.copyProperties(entity,vo);
            vo.setUpdateDateByShow(entity.getUpdateDate());
            vo.setUpdateUserNameByShow(entity.getUpdateUserName());
            a38Vos.add(vo);
        }
        PagerVo<A38Vo> pager = new PagerVo<A38Vo>(a38Vos, total, pageNum, pageSize);


        model.put("pager",pager);
        model.put("a0101Query",a0101Query);
        model.put("gbztCodeQuery",gbztCodeQuery);
        model.put("daztCodeQuery",daztCodeQuery);
        model.put("gbztContentQuery",gbztContentQuery);
        model.put("daztContentQuery",daztContentQuery);
        model.put("pager",pager);
        return new ModelAndView("saas/zzb/dzda/dak/bdwdalist",model);
    }

    @RequestMapping(value = "/ajax/sqcydalist")
    public ModelAndView sqcydalist(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                             @RequestParam(value = "userName",required = false)String userName,
                             @RequestParam(value = "readContent",required = false)String readContent
    ) throws UnsupportedEncodingException {
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String,Object> model = new HashMap<String,Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and("isShowToA0101 = :isShowToA0101 ", "isShowToA0101", "0"));
        if(com.hisun.util.StringUtils.isNotBlank(userName)){
            query.add(CommonRestrictions.and("a0101 like :a0101 ", "a0101","%"+ userName+"%"));
        }
        if(com.hisun.util.StringUtils.isNotBlank(readContent)){
            query.add(CommonRestrictions.and("readContent like :readContent ", "readContent", "%"+readContent+"%"));
        }
        query.add(CommonRestrictions.and("auditingState = :auditingState ", "auditingState", "1"));
        Long total = eApplyE01Z8Service.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.desc("createDate"));
        List<EApplyE01Z8> resultList = eApplyE01Z8Service.list(query,orderBy,pageNum,pageSize);
        PagerVo<EApplyE01Z8> pager = new PagerVo<EApplyE01Z8>(resultList, total.intValue(), pageNum, pageSize);
        model.put("pager",pager);
        model.put("userName",userName);
        model.put("readContent",readContent);
        return new ModelAndView("saas/zzb/dzda/dak/sqcydalist",model);
    }

    @RequestMapping(value = "/ajax/sqcydaAdd")
    public ModelAndView add(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sqcydazw","sqcydazw");
        return new ModelAndView("saas/zzb/dzda/dak/sqcydaAdd");
    }

    @RequestMapping(value = "/ajax/gjcx")
    public ModelAndView loadGjcx(HttpServletRequest request){
        HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<String, Object>();
        DakVo queryDakVo = (DakVo)session.getAttribute("queryDakVo");
        map.put("vo",queryDakVo);
        return new ModelAndView("saas/zzb/dzda/dak/gjcxPage",map);
    }


}