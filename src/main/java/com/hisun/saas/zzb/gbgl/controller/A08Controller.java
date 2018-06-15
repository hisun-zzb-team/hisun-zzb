package com.hisun.saas.zzb.gbgl.controller;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.a.entity.A01;
import com.hisun.saas.zzb.a.entity.A08;
import com.hisun.saas.zzb.a.service.A01Service;
import com.hisun.saas.zzb.a.service.A08Service;
import com.hisun.saas.zzb.a.vo.A01Vo;
import com.hisun.saas.zzb.a.vo.A08Vo;
import com.hisun.util.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 60514 on 2018/6/11.
 */
@Controller
@RequestMapping("/zzb/gbgl/a08")
public class A08Controller extends BaseController {
    @Resource
    private A08Service a08Service;
    @Resource
    private A01Service a01Service;

    @RequestMapping(value = "/ajax/xxjl")
    public @ResponseBody ModelAndView list(HttpServletRequest request,String a01Id,
                                               @RequestParam(value="pageNum",defaultValue="1")int pageNum,
                                               @RequestParam(value="pageSize",defaultValue="10") int pageSize) throws GenericException {
        Map<String, Object> map = Maps.newHashMap();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            CommonOrderBy orderBy = new CommonOrderBy();
            query.add(CommonRestrictions.and(" a01.id = :id ", "id", a01Id));
            List<A08> a08s = this.a08Service.list(query,orderBy);
            List<A08Vo> vos = new ArrayList<>();
            if(a08s!=null){
                A08Vo vo;
                for(A08 entity : a08s){
                    vo = new A08Vo();
                    BeanUtils.copyProperties(vo,entity);
                    vos.add(vo);
                }
            }
            map.put("a01Id", a01Id);
            map.put("datas",vos);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            throw new GenericException(e);
        }

        return new ModelAndView("saas/zzb/gbgl/a08/xxjlList",map);
    }

    @RequestMapping(value = "/ajax/add")
    public @ResponseBody ModelAndView ajax(HttpServletRequest request,String a01Id,String xrOrJl){
        Map<String, Object> map = Maps.newHashMap();
        map.put("a01Id",a01Id);
        return new ModelAndView("saas/zzb/gbgl/a08/addXxjl",map);
    }

//    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加材料:${vo.e01Z111}")
//    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> save(A08Vo vo,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String a01Id = StringUtils.trimNull2Empty(request.getParameter("a01Id"));
        try {
            A01 a01 = this.a01Service.getByPK(a01Id);
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            A08 a08 = new A08();

            BeanUtils.copyProperties(a08, vo);

            a08.setA01(a01);
            a08.setA0834("0");
            a08.setA0914("0");
            EntityWrapper.wrapperSaveBaseProperties(a08,userLoginDetails);
            a08Service.save(a08);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }

    @RequestMapping(value = "/ajax/edit")
    public @ResponseBody ModelAndView edit(HttpServletRequest request,String id,String a01Id){
        Map<String, Object> map = Maps.newHashMap();

        A08Vo vo = new A08Vo();
        A08 a08 = a08Service.getByPK(id);
        A01Vo a01Vo = new A01Vo();
        try {
            BeanUtils.copyProperties(vo, a08);
            BeanUtils.copyProperties(a01Vo, a08.getA01());
            vo.setA01Vo(a01Vo);
            vo.setLastUpdateBy(a08.getUpdateUserName());
        } catch (IllegalAccessException e) {
              e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        map.put("vo",vo);
        map.put("a01Id",a01Id);
        return new ModelAndView("saas/zzb/gbgl/a08/editXxjl",map);
    }

////    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加材料:${vo.e01Z111}")
////    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> update(A08Vo vo,HttpServletRequest request) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            A08 a08 = this.a08Service.getByPK(vo.getA0800());
            BeanUtils.copyProperties(a08, vo);
            EntityWrapper.wrapperSaveBaseProperties(a08,userLoginDetails);
            a08.setA0834("0");
            a08.setA0914("0");
            a08Service.update(a08);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }

//    @RequiresLog(operateType = LogOperateType.DELETE,description = "删除材料:${id}")
//    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/del/{id}")
    public @ResponseBody Map<String, Object> del(
            @PathVariable("id") String id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(StringUtils.isEmpty(id)){
                return null;
            }
            A08 a08 = this.a08Service.getByPK(id);
            this.a08Service.delete(a08);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }
}
