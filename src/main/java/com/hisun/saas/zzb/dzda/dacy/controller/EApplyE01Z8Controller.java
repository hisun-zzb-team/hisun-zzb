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
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.log.LogOperateType;
import com.hisun.saas.sys.log.RequiresLog;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a32.entity.A32;
import com.hisun.saas.zzb.dzda.a32.vo.A32Vo;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.dacy.entity.EApplyE01Z8;
import com.hisun.saas.zzb.dzda.dacy.service.EApplyE01Z8Service;
import com.hisun.saas.zzb.dzda.dacy.vo.EApplyE01Z8Vo;
import com.hisun.util.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping("/zzb/dzda/cysq")
public class EApplyE01Z8Controller extends BaseController {
    @Resource
    private EApplyE01Z8Service eApplyE01Z8Service;
    @Resource
    private A38Service a38Service;
    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                             @RequestParam(value = "userName",required = false)String userName,
                             @RequestParam(value = "readContent",required = false)String readContent
        ) throws UnsupportedEncodingException {
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String,Object> model = new HashMap<String,Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and("isShowToA0101 = :isShowToA0101 ", "isShowToA0101", "0"));
        if(StringUtils.isNotBlank(userName)){
            query.add(CommonRestrictions.and("e01Z807Name = :e01Z807Name ", "e01Z807Name", userName));
        }
        if(StringUtils.isNotBlank(readContent)){
            query.add(CommonRestrictions.and("readContent = :readContent ", "readContent", readContent));
        }
        Long total = eApplyE01Z8Service.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
      //  orderBy.add(CommonOrder.asc("px"));
        List<EApplyE01Z8> resultList = eApplyE01Z8Service.list(query,orderBy,pageNum,pageSize);
        PagerVo<EApplyE01Z8> pager = new PagerVo<EApplyE01Z8>(resultList, total.intValue(), pageNum, pageSize);
        model.put("pager",pager);
        return new ModelAndView("saas/zzb/dzda/dacy/list",model);
    }
    @RequestMapping(value = "/ajax/add")
    public ModelAndView addApplyDa(){
        return new ModelAndView("saas/zzb/dzda/dacy/add");
    }

    @RequestMapping(value = "/ajax/getDaxx")
    @ResponseBody
    public Map<String,Object> getDaxx(@RequestParam(value = "param",required = true) String param){
        Map<String,Object> map = Maps.newHashMap();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and("a0101 = :a0101 ", "a0101", param));
            List<A38> resultList = a38Service.list(query,null);
            if(resultList != null && !resultList.isEmpty()){
                map.put("success", true);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            map.put("success", false);
        }
        return  map;
    }
    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加档案申请查阅记录:${vo.a0101}")
    @RequestMapping(value = "/save")
    public @ResponseBody Map<String, Object> save(EApplyE01Z8Vo vo){
        Map<String,Object> map = Maps.newHashMap();
        try {
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            String a0a01s = vo.getA0101Content();
            String [] names = a0a01s.split(",");
            //一次申请查询多人档案
            for (String name : names){
                EApplyE01Z8 entity = new EApplyE01Z8();
                BeanUtils.copyProperties(entity,vo);
                entity.setA0101(name);
                EntityWrapper.wrapperSaveBaseProperties(entity,details);
                entity.setIsShowToA0101("0");
                entity.setAuditingState("0");
                entity.setApplyUserId(details.getUserid());
                entity.setApplyUserName(details.getUsername());
                eApplyE01Z8Service.save(entity);
            }

            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            map.put("success", false);
        }
        return map;
    }

    /**
     * 改变状态 逻辑删除
     * @param id
     * @return
     * @throws GenericException
     */
    @RequestMapping("/delete/{id}")
    public @ResponseBody Map<String,Object> delete(@PathVariable("id") String id) throws GenericException {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            EApplyE01Z8 entity = eApplyE01Z8Service.getByPK(id);
            if (entity != null){
                entity.setIsShowToA0101("1");
                eApplyE01Z8Service.update(entity);
                returnMap.put("code",1);
            }
        }catch (Exception e){
            returnMap.put("code",0);
            returnMap.put("message","删除失败");
            logger.error(e,e);
            throw new GenericException(e.getMessage());
        }
        return returnMap;
    }


}
