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
import com.hisun.saas.zzb.a.entity.A36;
import com.hisun.saas.zzb.a.service.A01Service;
import com.hisun.saas.zzb.a.service.A36Service;
import com.hisun.saas.zzb.a.vo.A01Vo;
import com.hisun.saas.zzb.a.vo.A36Vo;
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
@RequestMapping("/zzb/gbgl/a36")
public class A36Controller extends BaseController {
    @Resource
    private A36Service a36Service;
    @Resource
    private A01Service a01Service;

    @RequestMapping(value = "/ajax/shgx")
    public @ResponseBody ModelAndView list(HttpServletRequest request,String a01Id,
                                               @RequestParam(value="pageNum",defaultValue="1")int pageNum,
                                               @RequestParam(value="pageSize",defaultValue="10") int pageSize) throws GenericException {
        Map<String, Object> map = Maps.newHashMap();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            CommonOrderBy orderBy = new CommonOrderBy();
            query.add(CommonRestrictions.and(" a01.id = :id ", "id", a01Id));
            query.add(CommonRestrictions.and(" a3604b = :a3604b ", "a3604b", "10"));
            List<A36> a36s = this.a36Service.list(query,orderBy);
            List<A36Vo> vos = new ArrayList<>();
            if(a36s!=null){
                A36Vo vo;
                for(A36 entity : a36s){
                    vo = new A36Vo();
                    BeanUtils.copyProperties(vo,entity);
                    vos.add(vo);
                }
            }
            map.put("a01Id", a01Id);
            map.put("poxxDatas",vos);
            query= new CommonConditionQuery();
            query.add(CommonRestrictions.and(" a01.id = :id ", "id", a01Id));
            query.add(CommonRestrictions.and(" a3604b != :a3604b ", "a3604b", "10"));
            a36s = this.a36Service.list(query,orderBy);
            vos = new ArrayList<>();
            if(a36s!=null){
                A36Vo vo;
                for(A36 entity : a36s){
                    vo = new A36Vo();
                    BeanUtils.copyProperties(vo,entity);
                    vos.add(vo);
                }
            }
            map.put("qtgxDatas",vos);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            throw new GenericException(e);
        }

        return new ModelAndView("saas/zzb/gbgl/a36/shgxList",map);
    }

    @RequestMapping(value = "/ajax/addQtgx")
    public @ResponseBody ModelAndView addQtgx(HttpServletRequest request,String a01Id,String xrOrJl){
        Map<String, Object> map = Maps.newHashMap();
        int sort = this.a36Service.getMaxSort();
        map.put("a01Id",a01Id);
        map.put("a3647",sort);
        return new ModelAndView("saas/zzb/gbgl/a36/addQtgx",map);
    }

    @RequestMapping(value = "/ajax/addPoxx")
    public @ResponseBody ModelAndView addPoxx(HttpServletRequest request,String a01Id,String xrOrJl){
        Map<String, Object> map = Maps.newHashMap();
        map.put("a01Id",a01Id);
        return new ModelAndView("saas/zzb/gbgl/a36/addPoxx",map);
    }

//    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加材料:${vo.e01Z111}")
//    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/savePoxx", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> savePoxx(A36Vo vo,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String a01Id = StringUtils.trimNull2Empty(request.getParameter("a01Id"));
        try {
            A01 a01 = this.a01Service.getByPK(a01Id);
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            A36 a36 = new A36();

            BeanUtils.copyProperties(a36, vo);

            a36.setA01(a01);
            a36.setA3604A("配偶");
            a36.setA3604B("10");
            EntityWrapper.wrapperSaveBaseProperties(a36,userLoginDetails);
            a36Service.save(a36);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }

//    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加材料:${vo.e01Z111}")
//    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/saveQtgx", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> saveQtgx(A36Vo vo,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String a01Id = StringUtils.trimNull2Empty(request.getParameter("a01Id"));
        try {
            A01 a01 = this.a01Service.getByPK(a01Id);
            int sort = this.a36Service.getMaxSort();
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            A36 a36 = new A36();

            BeanUtils.copyProperties(a36, vo);

            a36.setA01(a01);
            EntityWrapper.wrapperSaveBaseProperties(a36,userLoginDetails);
            int newSort = a36.getA3647();
            if(newSort<sort){
                a36Service.updatePx(sort,newSort,a01Id);
            }
            a36Service.save(a36);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }

    @RequestMapping(value = "/ajax/edit")
    public @ResponseBody ModelAndView edit(HttpServletRequest request,String id,String a01Id,String flag){
        Map<String, Object> map = Maps.newHashMap();
        String url = "";
        if("1".equals(flag)){
            url="saas/zzb/gbgl/a36/editPoxx";
        }
        if("2".equals(flag)){
            url="saas/zzb/gbgl/a36/editQtgx";
        }
        A36Vo vo = new A36Vo();
        A36 a36 = a36Service.getByPK(id);
        A01Vo a01Vo = new A01Vo();
        try {
            BeanUtils.copyProperties(vo, a36);
            BeanUtils.copyProperties(a01Vo, a36.getA01());
            vo.setA01Vo(a01Vo);
        } catch (IllegalAccessException e) {
              e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        map.put("vo",vo);
        map.put("a01Id",a01Id);
        return new ModelAndView(url,map);
    }

////    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加材料:${vo.e01Z111}")
////    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> updatePoxx(A36Vo vo,HttpServletRequest request,String flag) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            A36 a36 = this.a36Service.getByPK(vo.getA3600());
            int oldSort = 0;
            int newSort = 0;
            if("2".equals(flag)){
                oldSort = a36.getA3647();
                newSort = vo.getA3647();
            }
            BeanUtils.copyProperties(a36, vo);
            EntityWrapper.wrapperSaveBaseProperties(a36,userLoginDetails);
            if("2".equals(flag)){
                a36Service.updatePx(oldSort,newSort,a36.getA01().getA0100());
            }
            a36Service.update(a36);
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
            A36 a36 = this.a36Service.getByPK(id);
            this.a36Service.delete(a36);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }
}
