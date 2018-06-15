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
import com.hisun.saas.zzb.a.entity.A14Z2;
import com.hisun.saas.zzb.a.entity.A14Z3;
import com.hisun.saas.zzb.a.entity.A15;
import com.hisun.saas.zzb.a.service.A01Service;
import com.hisun.saas.zzb.a.service.A14Z2Service;
import com.hisun.saas.zzb.a.service.A14Z3Service;
import com.hisun.saas.zzb.a.service.A15Service;
import com.hisun.saas.zzb.a.vo.A01Vo;
import com.hisun.saas.zzb.a.vo.A14Z2Vo;
import com.hisun.saas.zzb.a.vo.A14Z3Vo;
import com.hisun.saas.zzb.a.vo.A15Vo;
import com.hisun.saas.zzb.b.vo.B09Vo;
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
@RequestMapping("/zzb/gbgl/a14")
public class A14Controller extends BaseController {
    @Resource
    private A14Z2Service a14Z2Service;
    @Resource
    private A14Z3Service a14Z3Service;
    @Resource
    private A15Service a15Service;
    @Resource
    private A01Service a01Service;

    @RequestMapping(value = "/ajax/jckh")
    public @ResponseBody ModelAndView list(HttpServletRequest request,String a01Id,Boolean isShow,
                                               @RequestParam(value="pageNum",defaultValue="1")int pageNum,
                                               @RequestParam(value="pageSize",defaultValue="10") int pageSize) throws GenericException {
        Map<String, Object> map = Maps.newHashMap();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            CommonOrderBy orderBy = new CommonOrderBy();
            query.add(CommonRestrictions.and(" a01.id = :id ", "id", a01Id));
            List<A14Z2> a14Z2s = this.a14Z2Service.list(query,orderBy);
            String jlqkData = this.a14Z2Service.getData(a14Z2s);
            map.put("jlqkData", jlqkData);
            List<A14Z3> a14Z3s = this.a14Z3Service.list(query,orderBy);
            String cjqkData = this.a14Z3Service.getData(a14Z3s);
            map.put("cjqkData", cjqkData);
            List<A15> a15s = this.a15Service.list(query,orderBy);
            String ndkhData = this.a15Service.getData(a15s);
            map.put("ndkhData", ndkhData);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            throw new GenericException(e);
        }
        map.put("a01Id", a01Id);
        return new ModelAndView("saas/zzb/gbgl/a14/jckh",map);
    }

    @RequestMapping(value = "/ajax/edit")
    public @ResponseBody ModelAndView edit(HttpServletRequest request,String type,String a01Id,String a14Z2Id,
                                                    @RequestParam(value="pageNum",defaultValue="1")int pageNum,
                                                    @RequestParam(value="pageSize",defaultValue="10") int pageSize){
        Map<String, Object> map = Maps.newHashMap();

        String url = "";
        A01Vo a01Vo = new A01Vo();
        CommonConditionQuery query = new CommonConditionQuery();
        CommonOrderBy orderBy = new CommonOrderBy();
        query.add(CommonRestrictions.and(" a01.id = :id ", "id", a01Id));
        try {
            if("j".equals(type)){
                url = "saas/zzb/gbgl/a14/jlqkList";
                List<A14Z2> a14Z2s = this.a14Z2Service.list(query,orderBy);
                List<A14Z2Vo> vos = new ArrayList<>();
                if(a14Z2s!=null){
                    A14Z2Vo vo;
                    for(A14Z2 entity : a14Z2s){
                        vo = new A14Z2Vo();
                        BeanUtils.copyProperties(vo,entity);
                        vos.add(vo);
                    }
                }
                Long total = this.a14Z2Service.count(query);
                PagerVo<A14Z2Vo> pager = new PagerVo<A14Z2Vo>(vos, total.intValue(), pageNum, pageSize);
                map.put("pager",pager);
                map.put("total",total);
                A14Z2Vo vo = new A14Z2Vo();
                if(StringUtils.isNotEmpty(a14Z2Id)){
                    A14Z2 a14Z2 = this.a14Z2Service.getByPK(a14Z2Id);
                    BeanUtils.copyProperties(vo,a14Z2);
                }
                map.put("vo",vo);
            }
            if("c".equals(type)){
                url = "saas/zzb/gbgl/a14/cjqkList";
                List<A14Z3> a14Z3s = this.a14Z3Service.list(query,orderBy);
                List<A14Z3Vo> vos = new ArrayList<>();
                if(a14Z3s!=null){
                    A14Z3Vo vo;
                    for(A14Z3 entity : a14Z3s){
                        vo = new A14Z3Vo();
                        BeanUtils.copyProperties(vo,entity);
                        vos.add(vo);
                    }
                }
                Long total = this.a14Z2Service.count(query);
                PagerVo<A14Z3Vo> pager = new PagerVo<A14Z3Vo>(vos, total.intValue(), pageNum, pageSize);
                map.put("pager",pager);
                map.put("total",total);
                A14Z3Vo vo = new A14Z3Vo();
                if(StringUtils.isNotEmpty(a14Z2Id)){
                    A14Z3 a14Z3 = this.a14Z3Service.getByPK(a14Z2Id);
                    BeanUtils.copyProperties(vo,a14Z3);
                }
                map.put("vo",vo);
            }
            if("n".equals(type)){
                url = "saas/zzb/gbgl/a15/ndkhList";
                List<A15> a14Z3s = this.a15Service.list(query,orderBy);
                List<A15Vo> vos = new ArrayList<>();
                if(a14Z3s!=null){
                    A15Vo vo;
                    for(A15 entity : a14Z3s){
                        vo = new A15Vo();
                        BeanUtils.copyProperties(vo,entity);
                        vos.add(vo);
                    }
                }
                Long total = this.a14Z2Service.count(query);
                PagerVo<A15Vo> pager = new PagerVo<A15Vo>(vos, total.intValue(), pageNum, pageSize);
                map.put("pager",pager);
                map.put("total",total);
                A15Vo vo = new A15Vo();
                if(StringUtils.isNotEmpty(a14Z2Id)){
                    A15 a15 = this.a15Service.getByPK(a14Z2Id);
                    BeanUtils.copyProperties(vo,a15);
                }
                map.put("vo",vo);
            }
//            BeanUtils.copyProperties(vo, a14Z2);
//            BeanUtils.copyProperties(a01Vo, a14Z2.getA01());
//            vo.setA01Vo(a01Vo);
        } catch (IllegalAccessException e) {
              e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        map.put("a01Id",a01Id);
        return new ModelAndView(url,map);
    }

////    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加材料:${vo.e01Z111}")
////    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/saveOrUpdateJlqk", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> saveOrUpdateJlqk(A14Z2Vo vo,HttpServletRequest request,String a01Id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            if(StringUtils.isEmpty(vo.getA14Z200())){
                A01 a01 = a01Service.getByPK(a01Id);
                A14Z2 a14Z2 = new A14Z2();
                BeanUtils.copyProperties(a14Z2, vo);
                a14Z2.setA01(a01);
                EntityWrapper.wrapperSaveBaseProperties(a14Z2,userLoginDetails);
                this.a14Z2Service.save(a14Z2);
            }else {
                A14Z2 a14Z2 = this.a14Z2Service.getByPK(vo.getA14Z200());
                BeanUtils.copyProperties(a14Z2, vo);
                EntityWrapper.wrapperSaveBaseProperties(a14Z2,userLoginDetails);
                a14Z2Service.update(a14Z2);
            }
            map.put("code", 1);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }

    @RequestMapping(value = "/saveOrUpdateCjqk", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> saveOrUpdateCjqk(A14Z3Vo vo,HttpServletRequest request,String a01Id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            if(StringUtils.isEmpty(vo.getA14Z300())){
                A01 a01 = a01Service.getByPK(a01Id);
                A14Z3 a14Z3 = new A14Z3();
                BeanUtils.copyProperties(a14Z3, vo);
                a14Z3.setA01(a01);
                EntityWrapper.wrapperSaveBaseProperties(a14Z3,userLoginDetails);
                this.a14Z3Service.save(a14Z3);
            }else {
                A14Z3 a14Z3 = this.a14Z3Service.getByPK(vo.getA14Z300());
                BeanUtils.copyProperties(a14Z3, vo);
                EntityWrapper.wrapperSaveBaseProperties(a14Z3,userLoginDetails);
                a14Z3Service.update(a14Z3);
            }
            map.put("code", 1);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }

    @RequestMapping(value = "/saveOrUpdateNdkh", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> saveOrUpdateNdkh(A15Vo vo,HttpServletRequest request,String a01Id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            if(StringUtils.isEmpty(vo.getA1500())){
                A01 a01 = a01Service.getByPK(a01Id);
                A15 a15 = new A15();
                BeanUtils.copyProperties(a15, vo);
                a15.setA01(a01);
                EntityWrapper.wrapperSaveBaseProperties(a15,userLoginDetails);
                this.a15Service.save(a15);
            }else {
                A15 a15 = this.a15Service.getByPK(vo.getA1500());
                BeanUtils.copyProperties(a15, vo);
                EntityWrapper.wrapperSaveBaseProperties(a15,userLoginDetails);
                a15Service.update(a15);
            }
            map.put("code", 1);
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
            @PathVariable("id") String id,String type) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(StringUtils.isEmpty(id)){
                return null;
            }
            if("j".equals(type)){
                A14Z2 a14Z2 = this.a14Z2Service.getByPK(id);
                this.a14Z2Service.delete(a14Z2);
            }
            if("c".equals(type)){
                A14Z3 a14Z3 = this.a14Z3Service.getByPK(id);
                this.a14Z3Service.delete(a14Z3);
            }
            if("n".equals(type)){
                A15 a15 = this.a15Service.getByPK(id);
                this.a15Service.delete(a15);
            }
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }
}
