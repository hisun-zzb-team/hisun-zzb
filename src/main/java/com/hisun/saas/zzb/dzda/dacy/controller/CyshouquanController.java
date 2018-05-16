/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.controller;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.a38.vo.A38Vo;
import com.hisun.saas.zzb.dzda.dacy.entity.EApplyE01Z8;
import com.hisun.saas.zzb.dzda.dacy.entity.EPopedomE01Z1Relation;
import com.hisun.saas.zzb.dzda.dacy.service.EApplyE01Z8Service;
import com.hisun.saas.zzb.dzda.dacy.service.EPopedomE01Z1RelationService;
import com.hisun.saas.zzb.dzda.dacy.vo.EApplyE01Z8Vo;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.util.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("/zzb/dzda/cyshouquan")
public class CyshouquanController extends BaseController {
    @Resource
    private EApplyE01Z8Service eApplyE01Z8Service;
    @Resource
    private A38Service a38Service;
    @Resource
    private E01Z1Service e01Z1Service;
    @Resource
    private EPopedomE01Z1RelationService ePopedomE01Z1RelationService;

    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                             @RequestParam(value = "userName",required = false)String userName,
                             @RequestParam(value = "readContent",required = false)String readContent,
                             @RequestParam(value = "e01Z807Name",required = false)String e01Z807Name,
                             @RequestParam(value = "auditingState",required = false)String auditingState
    ) throws UnsupportedEncodingException {
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String,Object> model = new HashMap<String,Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        if(StringUtils.isNotBlank(userName)){
            query.add(CommonRestrictions.and("a0101 like :a0101 ", "a0101","%"+ userName+"%"));
        }
        if(StringUtils.isNotBlank(readContent)){
            query.add(CommonRestrictions.and("readContent like :readContent ", "readContent", "%"+readContent+"%"));
        }
        if(StringUtils.isNotBlank(e01Z807Name)){
            query.add(CommonRestrictions.and("e01Z807Name = :e01Z807Name ", "e01Z807Name", e01Z807Name));
        }
        if(StringUtils.isNotBlank(auditingState)){
            query.add(CommonRestrictions.and("auditingState = :auditingState ", "auditingState", auditingState));
        }
        Long total = eApplyE01Z8Service.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.asc("auditingState"));
        orderBy.add(CommonOrder.asc("createDate"));
        List<EApplyE01Z8> resultList = eApplyE01Z8Service.list(query,orderBy,pageNum,pageSize);
        PagerVo<EApplyE01Z8> pager = new PagerVo<EApplyE01Z8>(resultList, total.intValue(), pageNum, pageSize);
        model.put("pager",pager);
        model.put("userName",userName);
        model.put("readContent",readContent);
        model.put("e01Z807Name",e01Z807Name);
        model.put("auditingState",auditingState);
        return new ModelAndView("saas/zzb/dzda/dasq/list",model);
    }
    @RequestMapping(value = "/toShouquan")
    public ModelAndView toShouquan(String id){
        Map<String,Object> model = Maps.newHashMap();
        try{
            EApplyE01Z8 entity  = eApplyE01Z8Service.getByPK(id);
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and("sjzt = :sjzt","sjzt","1"));
            query.add(CommonRestrictions.and("a0101 = :a0101","a0101",entity.getA0101()));
            List<A38> a38s =  a38Service.list(query,null);
            String a38Id = "";
            if(a38s.size()==1){
                a38Id = a38s.get(0).getId();
            }
            model.put("entity",entity);
            model.put("a38s",a38s);
            model.put("a38Id",a38Id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("saas/zzb/dzda/dasq/shouquan",model);
    }
    @RequestMapping(value = "/ajax/tobfShouquan")
    public ModelAndView tobfShouquan(String a38Id){
        Map<String,Object> model = Maps.newHashMap();
        try{
            model.put("a38Id",a38Id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("saas/zzb/dzda/dasq/bfshouquan",model);
    }


    @RequestMapping(value = "/shouhuiQx/{id}")
    public @ResponseBody  Map<String,Object> shouhuiQx(@PathVariable("id")String id){
        Map<String,Object> model = Maps.newHashMap();
        try{
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            EApplyE01Z8 entity = eApplyE01Z8Service.getByPK(id);
            //收回权限
            entity.setAuditingState("3");
            EntityWrapper.wrapperUpdateBaseProperties(entity,details);
            eApplyE01Z8Service.update(entity);
            model.put("success",true);
        }catch (Exception e){
            model.put("success",false);
            model.put("message","授权失败");
            e.printStackTrace();
        }
        return model;
    }

    /**
     * 授权或者拒绝
     * @param vo
     * @return
     */
    @RequestMapping(value = "/shouquan")
    public @ResponseBody Map<String,Object> shouquanOrJujue(EApplyE01Z8Vo vo){
        Map<java.lang.String, java.lang.Object> model = Maps.newHashMap();
        try{
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            EApplyE01Z8 entity =  eApplyE01Z8Service.getByPK(vo.getId());
            entity.setSqdwpzld(details.getUsername());
            BeanUtils.copyProperties(entity,vo);
            //全部授权
            if(vo.getAuditingState().equals("1")) entity.setPopedomStuffType("0");
            entity.setSqdwpzld(details.getUsername());
            EntityWrapper.wrapperUpdateBaseProperties(entity,details);
            entity.setA38(a38Service.getByPK(vo.getA38Id()));
            eApplyE01Z8Service.update(entity);
            model.put("success",true);
        }catch (Exception e){
            model.put("success",false);
            e.printStackTrace();
        }
        return model;
    }
    @RequestMapping(value = "/bfshouquan")
    public @ResponseBody Map<String,Object> bfShouquan(EApplyE01Z8Vo vo){
        Map<java.lang.String, java.lang.Object> model = Maps.newHashMap();
        try{
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            String e01z1IdContent = vo.getE01z1IdContent();
            String[] e01z1Ids = e01z1IdContent.split(",");
            EApplyE01Z8 entity =  eApplyE01Z8Service.getByPK(vo.getId());
            for (String id : e01z1Ids){
                if(id.equals("")) continue;
                EPopedomE01Z1Relation ep = new EPopedomE01Z1Relation();
                E01Z1 e01Z1 = e01Z1Service.getByPK(id);
                ep.setApplyE01Z8(entity);
                ep.setE01z1(e01Z1);
                ePopedomE01Z1RelationService.save(ep);
            }
            entity.setSqdwpzld(details.getUsername());
            BeanUtils.copyProperties(entity,vo);
            //已审
            entity.setAuditingState("1");
            //部分授权
            entity.setPopedomStuffType("1");
            entity.setSqdwpzld(details.getUsername());
            EntityWrapper.wrapperUpdateBaseProperties(entity,details);
            entity.setA38(a38Service.getByPK(vo.getA38Id()));
            eApplyE01Z8Service.update(entity);
            model.put("success",true);
        }catch (Exception e){
            model.put("success",false);
            e.printStackTrace();
        }
        return model;
    }
    @RequestMapping(value = "/getA0101")
    public @ResponseBody Map<String,Object> getA0101(@RequestParam(value = "a0101",required = true) String a0101){
        Map<String,Object> model = Maps.newHashMap();
        try{
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and("sjzt = :sjzt","sjzt","1"));
            query.add(CommonRestrictions.and("a0101 = :a0101","a0101",a0101));
            List<A38> a38 =  a38Service.list(query,null);
            PagerVo<A38> pager = new PagerVo<A38>(a38, a38.size(), 1, 20);
            model.put("pager",pager);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return model;
    }
}
