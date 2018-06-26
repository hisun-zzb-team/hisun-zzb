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
                             String e01Z9Damc,String e01Z907,String e01Z9Jyzt,String e01z9Yh) throws UnsupportedEncodingException {
        Map<String,Object> model = new HashMap<String,Object>();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        CommonConditionQuery query = new CommonConditionQuery();
        CommonOrderBy orderBy = new CommonOrderBy();
        if(StringUtils.isNotEmpty(e01Z9Damc)){
            query.add(CommonRestrictions.and(" e01Z9Damc like:e01Z9Damc ", "e01Z9Damc", "%"+e01Z9Damc+"%"));
        }
        if(StringUtils.isNotEmpty(e01Z907)){
            query.add(CommonRestrictions.and(" e01Z907 like:e01Z907 ", "e01Z907", "%"+e01Z907+"%"));
        }
        if(StringUtils.isNotEmpty(e01Z9Jyzt)&&!"x".equals(e01Z9Jyzt)){
            query.add(CommonRestrictions.and(" e01Z9Jyzt = :e01Z9Jyzt ", "e01Z9Jyzt", e01Z9Jyzt));
        }
        if(StringUtils.isEmpty(e01Z9Jyzt)){
            e01Z9Jyzt = "0";
            query.add(CommonRestrictions.and(" e01Z9Jyzt = :e01Z9Jyzt ", "e01Z9Jyzt", e01Z9Jyzt));
        }
        int total = 0;
        List<E01Z9> e01Z9s = new ArrayList<>();
        Map<String,Object> resultMap = new HashMap<>();
        if(StringUtils.isNotEmpty(e01z9Yh)){
            resultMap = this.e01Z9Service.getYqE01Z9("",details.getTenantId(),pageNum,pageSize,e01Z9Damc,e01Z907,"x".equals(e01Z9Jyzt)?"":e01Z9Jyzt,e01z9Yh);
            e01Z9s = ( List<E01Z9>) resultMap.get("e01Z9s");
            total = (int) resultMap.get("total");
        }else {
            e01Z9s = this.e01Z9Service.list(query,orderBy);
            Long totalL = this.e01Z9Service.count(query);
            total = totalL.intValue();
        }
        List<E01Z9Vo> a01Z9Vos = new ArrayList<E01Z9Vo>();
        E01Z9Vo vo = new E01Z9Vo();
        for(E01Z9 entity : e01Z9s){
            vo = new E01Z9Vo();
            BeanUtils.copyProperties(entity,vo);
            a01Z9Vos.add(vo);
        }

        PagerVo<E01Z9Vo> pager = new PagerVo<E01Z9Vo>(a01Z9Vos, total, pageNum, pageSize);
        model.put("e01Z9Damc",e01Z9Damc);
        model.put("e01Z907",e01Z907);
        model.put("e01Z9Jyzt",e01Z9Jyzt);
        model.put("e01z9Yh",e01z9Yh);
        model.put("pager",pager);
        model.put("total",total);
        return new ModelAndView("saas/zzb/dzda/e01z9/jygl/list",model);
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Map<String, Object> update(E01Z9Vo vo) {
        Map<String, Object> map = Maps.newHashMap();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        try {
            E01Z9 entity = new E01Z9();
            entity = this.e01Z9Service.getByPK(vo.getId());
            entity.setE01Z9Jyzt(vo.getE01Z9Jyzt());
            entity.setE01Z931(vo.getE01Z931());
            entity.setE01Z9Shsj(vo.getE01Z9Shsj());
            entity.setE01Z941(vo.getE01Z941());
            if("1".equals(vo.getE01Z9Jyzt())){
                entity.setE01z9Yhsj(vo.getE01z9Yhsj());
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

    @RequestMapping(value = "/gh")
    @ResponseBody
    public Map<String, Object> gh(String id,String e01Z9Jyzt) {
        Map<String, Object> map = Maps.newHashMap();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        try {
            E01Z9 entity = new E01Z9();
            entity = this.e01Z9Service.getByPK(id);
            entity.setE01Z9Jyzt(e01Z9Jyzt);
            entity.setE01Z934(details.getRealname());
            entity.setE01Z927(date);
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
    @RequestMapping(value = "/edit")
    public ModelAndView edit(String id,String e01Z9Jyzt) {
        Map<String, Object> map = Maps.newHashMap();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String e01Z9Shsj = sdf.format(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE,5);
        date = cal.getTime();
        String e01z9Yhsj = sdf.format(date);

        E01Z9Vo vo = new E01Z9Vo();
        E01Z9 entity = this.e01Z9Service.getByPK(id);
        BeanUtils.copyProperties(entity,vo);
        vo.setE01Z931(details.getRealname());
        vo.setE01Z9Shsj(e01Z9Shsj);
        vo.setE01z9Yhsj(e01z9Yhsj);
        map.put("vo",vo);
        map.put("e01Z9Jyzt",e01Z9Jyzt);
        return new ModelAndView("saas/zzb/dzda/e01z9/jygl/edit",map);
    }

//    @RequiresPermissions("jygl:*")
//    @RequestMapping(value = "/edit/gh")
//    public ModelAndView editGh(String id) {
//        Map<String, Object> map = Maps.newHashMap();
//        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String date = sdf.format(new Date());
//        E01Z9Vo vo = new E01Z9Vo();
//        E01Z9 entity = this.e01Z9Service.getByPK(id);
//        BeanUtils.copyProperties(entity,vo);
//        vo.setE01Z934(details.getRealname());
//        vo.setE01Z927(date);
//        map.put("vo",vo);
//        return new ModelAndView("saas/zzb/dzda/e01z9/jygl/gh",map);
//    }

}