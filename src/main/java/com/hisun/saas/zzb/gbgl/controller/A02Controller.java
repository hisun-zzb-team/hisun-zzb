package com.hisun.saas.zzb.gbgl.controller;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.a.entity.A01;
import com.hisun.saas.zzb.a.entity.A02;
import com.hisun.saas.zzb.a.service.A01Service;
import com.hisun.saas.zzb.a.service.A02Service;
import com.hisun.saas.zzb.a.vo.A01Vo;
import com.hisun.saas.zzb.a.vo.A02Vo;
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
@RequestMapping("/zzb/gbgl/a02")
public class A02Controller extends BaseController {
    @Resource
    private A02Service a02Service;
    @Resource
    private A01Service a01Service;

    @RequestMapping(value = "/ajax/xrzw")
    public @ResponseBody ModelAndView list(HttpServletRequest request,String a01Id,
                                               @RequestParam(value="pageNum",defaultValue="1")int pageNum,
                                               @RequestParam(value="pageSize",defaultValue="10") int pageSize) throws GenericException {
        Map<String, Object> map = Maps.newHashMap();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            CommonOrderBy orderBy = new CommonOrderBy();
            query.add(CommonRestrictions.and(" a01.id = :id ", "id", a01Id));
            query.add(CommonRestrictions.and(" a0255 != :a0255 ", "a0255", "3"));
            List<A02> a02s = this.a02Service.list(query,orderBy);
            List<A02Vo> vos = new ArrayList<>();
            if(a02s!=null){
                A02Vo vo;
                for(A02 entity : a02s){
                    vo = new A02Vo();
                    BeanUtils.copyProperties(vo,entity);
                    vos.add(vo);
                }
            }
            map.put("a01Id", a01Id);
            map.put("xrDatas",vos);
            query= new CommonConditionQuery();
            query.add(CommonRestrictions.and(" a01.id = :id ", "id", a01Id));
            query.add(CommonRestrictions.and(" a0255 = :a0255 ", "a0255", "3"));
            a02s = this.a02Service.list(query,orderBy);
            vos = new ArrayList<>();
            if(a02s!=null){
                A02Vo vo;
                for(A02 entity : a02s){
                    vo = new A02Vo();
                    BeanUtils.copyProperties(vo,entity);
                    vos.add(vo);
                }
            }
            map.put("rzjlDatas",vos);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            throw new GenericException(e);
        }

        return new ModelAndView("saas/zzb/gbgl/a02/xrzwList",map);
    }

    @RequestMapping(value = "/ajax/addXrzw")
    public @ResponseBody ModelAndView ajax(HttpServletRequest request,String a01Id,String xrOrJl){
        Map<String, Object> map = Maps.newHashMap();
        int sort = this.a02Service.getMaxSort();
        if("1".equals(xrOrJl)){
            map.put("a0225","1");
            map.put("a0255A","已任");
            map.put("title","添加现任职务");
        }else {
            map.put("a0225","3");
            map.put("a0255A","已免");
            map.put("title","添加任职经历");
        }
        map.put("a01Id",a01Id);
        map.put("a0225",sort);
        return new ModelAndView("saas/zzb/gbgl/a02/addXrzw",map);
    }

//    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加材料:${vo.e01Z111}")
//    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> save(A02Vo vo,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String a01Id = StringUtils.trimNull2Empty(request.getParameter("a01Id"));
        try {
            A01 a01 = this.a01Service.getByPK(a01Id);
            int sort = this.a02Service.getMaxSort();
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            A02 a02 = new A02();

            BeanUtils.copyProperties(a02, vo);

            a02.setA01(a01);
            EntityWrapper.wrapperSaveBaseProperties(a02,userLoginDetails);
            int newSort = a02.getA0225();
            if(newSort<sort){
                a02Service.updatePx(sort,newSort,a01Id);
            }
            a02Service.save(a02);
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

        A02Vo vo = new A02Vo();
        A02 a02 = a02Service.getByPK(id);
        A01Vo a01Vo = new A01Vo();
        try {
            BeanUtils.copyProperties(vo, a02);
            BeanUtils.copyProperties(a01Vo, a02.getA01());
            vo.setA01Vo(a01Vo);
        } catch (IllegalAccessException e) {
              e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        map.put("vo",vo);
        map.put("a01Id",a01Id);
        return new ModelAndView("saas/zzb/gbgl/a02/editXrzw",map);
    }

////    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加材料:${vo.e01Z111}")
////    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> update(A02Vo vo,HttpServletRequest request) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            A02 a02 = this.a02Service.getByPK(vo.getA0200());
            int sort = a02.getA0225();
            BeanUtils.copyProperties(a02, vo);
            EntityWrapper.wrapperSaveBaseProperties(a02,userLoginDetails);
            a02Service.update(a02);
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
            A02 a02 = this.a02Service.getByPK(id);
            this.a02Service.delete(a02);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }
}
