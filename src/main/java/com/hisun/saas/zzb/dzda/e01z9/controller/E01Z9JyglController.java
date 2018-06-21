/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.e01z9.controller;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.log.LogOperateType;
import com.hisun.saas.sys.log.RequiresLog;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.e01z9.entity.E01Z9;
import com.hisun.saas.zzb.dzda.e01z9.service.E01Z9Service;
import com.hisun.saas.zzb.dzda.e01z9.vo.E01Z9Vo;
import com.hisun.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhout {605144321@qq.com}
 */
@Controller
@RequestMapping("/zzb/dzda/jygl")
public class E01Z9JyglController extends BaseController {

    @Resource
    private E01Z9Service e01Z9Service;
    
    @Resource
    private A38Service a38Service;


    @RequiresPermissions("jygl:*")
    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,@RequestParam(value = "pageSize",defaultValue = "10")int pageSize,HttpServletRequest request,
                             String e01Z9Damc,String e01Z907,String e01Z9Jyzt) throws UnsupportedEncodingException {
        Map<String,Object> model = new HashMap<String,Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        CommonOrderBy orderBy = new CommonOrderBy();
        if(StringUtils.isNotEmpty(e01Z9Damc)){
            query.add(CommonRestrictions.and(" e01Z9Damc like:e01Z9Damc ", "e01Z9Damc", "%"+e01Z9Damc+"%"));
        }
        if(StringUtils.isNotEmpty(e01Z907)){
            query.add(CommonRestrictions.and(" e01Z907 like:e01Z907 ", "e01Z907", "%"+e01Z907+"%"));
        }
        if(StringUtils.isNotEmpty(e01Z9Jyzt)){
            query.add(CommonRestrictions.and(" e01Z9Jyzt = :e01Z9Jyzt ", "e01Z9Jyzt", e01Z9Jyzt));
        }
        List<E01Z9> e01Z9s = this.e01Z9Service.list(query,orderBy);
        Long total = this.e01Z9Service.count(query);
        List<E01Z9Vo> a01Z9Vos = new ArrayList<E01Z9Vo>();
        E01Z9Vo vo = new E01Z9Vo();
        for(E01Z9 entity : e01Z9s){
            vo = new E01Z9Vo();
            BeanUtils.copyProperties(entity,vo);
            a01Z9Vos.add(vo);
        }

        PagerVo<E01Z9Vo> pager = new PagerVo<E01Z9Vo>(a01Z9Vos, total.intValue(), pageNum, pageSize);
        model.put("e01Z9Damc",e01Z9Damc);
        model.put("e01Z907",e01Z907);
        model.put("e01Z9Jyzt",e01Z9Jyzt);
        model.put("pager",pager);
        model.put("total",total);
        return new ModelAndView("saas/zzb/dzda/e01z9/jygl/list",model);
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Map<String, Object> update(String id,String e01Z9Jyzt) {
        Map<String, Object> map = Maps.newHashMap();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        try {
            E01Z9 entity = new E01Z9();
            entity = this.e01Z9Service.getByPK(id);
            entity.setE01Z9Jyzt(e01Z9Jyzt);
            if("2".equals(e01Z9Jyzt)){
                entity.setE01Z934(details.getRealname());
            }else {
                entity.setE01Z931(details.getRealname());
            }
            EntityWrapper.wrapperUpdateBaseProperties(entity,details);
            e01Z9Service.update(entity);
            map.put("code", 1);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("code", 0);
        }
        return map;
    }

    @RequiresPermissions("jygl:*")
    @RequestMapping(value = "/ajax/edit")
    public ModelAndView edit(String id,String flag) {
        Map<String, Object> map = Maps.newHashMap();
        E01Z9Vo vo = new E01Z9Vo();
        E01Z9 entity = this.e01Z9Service.getByPK(id);
        BeanUtils.copyProperties(entity,vo);
        map.put("vo",vo);
        map.put("flag",flag);
        return new ModelAndView("saas/zzb/dzda/e01z9/jygl/edit",map);
    }

    @RequiresLog(operateType = LogOperateType.DELETE,description = "删除阅档申请:${id}")
    @RequiresPermissions("jygl:*")
    @RequestMapping(value = "/del/{id}")
    public @ResponseBody Map<String, Object> del(
            @PathVariable("id") String id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(StringUtils.isEmpty(id)){
                return null;
            }
            E01Z9 entity = this.e01Z9Service.getByPK(id);
            this.e01Z9Service.delete(entity);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }
}