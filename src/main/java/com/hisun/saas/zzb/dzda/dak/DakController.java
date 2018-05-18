/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dak;

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
import com.hisun.saas.zzb.dzda.dacy.entity.EApplyE01Z8;
import com.hisun.saas.zzb.dzda.dacy.service.EApplyE01Z8Service;
import com.hisun.saas.zzb.dzda.dak.vo.DakVo;
import org.antlr.stringtemplate.CommonGroupLoader;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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

    private DakVo dakVos;

    @RequiresPermissions("a38:dakList ")
    @RequestMapping(value = "/manage")
    public ModelAndView manage(String id,String listType){
        dakVos = new DakVo();
        return new ModelAndView("saas/zzb/dzda/dak/manage");
    }

    @RequiresPermissions("a38:dakList ")
    @RequestMapping("/ajax/bdwdalist")
    public ModelAndView bdwdalist(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,@RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
    String dabhQuery,String smxhQuery,String a0101Query,String gbztCodeQuery,String daztCodeQuery,String gbztContentQuery,String daztContentQuery) throws UnsupportedEncodingException {
        Map<String,Object> model = new HashMap<String,Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" sjzt = :sjzt ", "sjzt", "1"));
        if(StringUtils.isNotEmpty(dabhQuery)){
            query.add(CommonRestrictions.and(" dabh like :dabhQuery ", "dabhQuery","%"+dabhQuery+"%"));
        }
        if(StringUtils.isNotEmpty(smxhQuery)){
            query.add(CommonRestrictions.and(" smxh like :smxhQuery ", "smxhQuery","%"+smxhQuery+"%"));
        }
        if(StringUtils.isNotEmpty(a0101Query)){
            query.add(CommonRestrictions.and(" a0101 like :a0101Query ", "a0101Query","%"+a0101Query+"%"));
        }
        if(StringUtils.isNotEmpty(gbztCodeQuery)){
            String str[] = gbztCodeQuery.split(",");
            List gbztCodeList =  Arrays.asList(str);
            query.add(CommonRestrictions.and(" gbztCode in (:gbztCodeList)", "gbztCodeList",gbztCodeList));
        }
        if(StringUtils.isNotEmpty(daztCodeQuery)){
            String str[] = daztCodeQuery.split(",");
            List daztCodeList =  Arrays.asList(str);
            query.add(CommonRestrictions.and(" daztCode in (:daztCodeList) ", "daztCodeList",daztCodeList));
        }
        Long total = a38Service.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.desc("smxh"));
        orderBy.add(CommonOrder.asc("a0101"));
        List<A38> resultList = a38Service.list(query,orderBy,pageNum,pageSize);
        PagerVo<A38> pager = new PagerVo<A38>(resultList, total.intValue(), pageNum, pageSize);
        model.put("dabhQuery",dabhQuery);
        model.put("smxhQuery",smxhQuery);
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

    /**
     *
     * @param a38Id 档案主键
     * @param a0101 档案姓名
     * @param archiveId 档案材料字典ID
     * @param e01z1Id 档案材料ID
     * @param showType  浏览的位置  如果为refer则是   如果是viewApply则为查阅列表进入
     * @return
     * @throws GenericException
     */
    @RequiresLog(operateType = LogOperateType.QUERY,description = "查看档案:${a0101}")
    @RequestMapping("/ajax/viewMain/{a38Id}")
    public
    @ResponseBody ModelAndView viewMain(@PathVariable(value = "a38Id") String a38Id,String a0101,String archiveId,String e01z1Id,String showType,String myDirName) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();

        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        java.util.Date date = new java.util.Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        String nowDate = df.format(date);
        if(myDirName==null || myDirName.equals("")) {
            myDirName = userLoginDetails.getUserid() + nowDate;
        }
        map.put("a38Id", a38Id);
        map.put("archiveId", archiveId);
        map.put("e01z1Id", e01z1Id);
        map.put("myDirName", myDirName);
        map.put("a0101", a0101);
        return new ModelAndView("saas/zzb/dzda/mlcl/viewImg/viewImgManage",map);
    }

    @RequestMapping(value = "/ajax/sqcydaAdd")
    public ModelAndView add(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sqcydazw","sqcydazw");
        return new ModelAndView("saas/zzb/dzda/dak/sqcydaAdd");
    }

    @RequiresPermissions("a38:dakList ")
    @RequestMapping(value = "/ajax/gjcx")
    public ModelAndView gjcx(String id,String listType){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("vo",dakVos);
        return new ModelAndView("saas/zzb/dzda/dak/gjcxPage",map);
    }

    @RequiresPermissions("a38:dakList ")
    @RequestMapping("/ajax/gjcxBdwdalist")
    public ModelAndView GjcxBdwdalist(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,@RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                                      @ModelAttribute DakVo vo) throws UnsupportedEncodingException {
        Map<String,Object> model = new HashMap<String,Object>();
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        List<A38> resultList = a38Service.gjcxList(vo,userLoginDetails);
        int total =  resultList.size();
        PagerVo<A38> pager = new PagerVo<A38>(resultList, total, pageNum, pageSize);
        model.put("pager",pager);
        dakVos=vo;
        return new ModelAndView("saas/zzb/dzda/dak/bdwdalist",model);
    }

    @RequestMapping(value = "/select")
    public @ResponseBody Map<String,Object> getSelectNodes(String typeCode,String tenant2ResourceId,String privilegeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        String typeId = "";
        try {
            CommonConditionQuery dictionaryTypeQuery = new CommonConditionQuery();
            dictionaryTypeQuery.add(CommonRestrictions.and("(code like :searchName)", "searchName", "%" + "SFBS" + "%"));
            CommonOrderBy dictionaryTypeOrderBy = new CommonOrderBy();
            List<DictionaryType> queryList = this.dictionaryTypeService.list(dictionaryTypeQuery, dictionaryTypeOrderBy);
            if(queryList!=null && queryList.size()>0){
                typeId=queryList.get(0).getId();
            }

            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" dictionaryType.id=:typeId ", "typeId", typeId));
            CommonOrderBy orderBy = new CommonOrderBy();
//            orderBy.add(CommonOrder.asc("sort"));
//            orderBy.add(CommonOrder.asc("code"));
            List<DictionaryItem> dictionaryItems = this.dictionaryItemService.list(query, orderBy);
            List<SelectNode> nodes = new ArrayList<>();
            SelectNode node=null;
            node = new SelectNode();
            node.setOptionKey("");
            node.setOptionValue("");
            nodes.add(node);
            for (DictionaryItem dictionaryItem : dictionaryItems) {
                node = new SelectNode();
                node.setOptionKey(dictionaryItem.getCode());
                node.setOptionValue(dictionaryItem.getName());
                nodes.add(node);
            }
            map.put("success", true);
            map.put("data", nodes);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        }
        return map;
    }
}