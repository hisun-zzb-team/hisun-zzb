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
import com.hisun.saas.zzb.a.entity.*;
import com.hisun.saas.zzb.a.service.*;
import com.hisun.saas.zzb.a.vo.*;
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
@RequestMapping("/zzb/gbgl/aZc")
public class AZcController extends BaseController {
    @Resource
    private A14Z2Service a14Z2Service;
    @Resource
    private A14Z3Service a14Z3Service;
    @Resource
    private A15Service a15Service;
    @Resource
    private A01Service a01Service;
    @Resource
    private AZcService aZcService;

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
            List<AZc> aZcs = this.aZcService.list(query,orderBy);
            String zcData = this.aZcService.getData(aZcs);
            map.put("zcData", zcData);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            throw new GenericException(e);
        }
        map.put("a01Id", a01Id);
        return new ModelAndView("saas/zzb/gbgl/a14/jckh",map);
    }

    @RequestMapping(value = "/ajax/edit")
    public @ResponseBody ModelAndView edit(HttpServletRequest request,String a01Id,String id,
                                                    @RequestParam(value="pageNum",defaultValue="1")int pageNum,
                                                    @RequestParam(value="pageSize",defaultValue="10") int pageSize){
        Map<String, Object> map = Maps.newHashMap();

        String url = "";
        A01Vo a01Vo = new A01Vo();
        CommonConditionQuery query = new CommonConditionQuery();
        CommonOrderBy orderBy = new CommonOrderBy();
        query.add(CommonRestrictions.and(" a01.id = :id ", "id", a01Id));
        try {
            url = "saas/zzb/gbgl/azc/zcList";
            List<AZc> aZcs = this.aZcService.list(query,orderBy);
            List<AZcVo> vos = new ArrayList<>();
            if(aZcs!=null){
                AZcVo vo;
                for(AZc entity : aZcs){
                    vo = new AZcVo();
                    BeanUtils.copyProperties(vo,entity);
                    vos.add(vo);
                }
            }
            Long total = this.a14Z2Service.count(query);
            PagerVo<AZcVo> pager = new PagerVo<AZcVo>(vos, total.intValue(), pageNum, pageSize);
            map.put("pager",pager);
            map.put("total",total);
            AZcVo vo = new AZcVo();
            if(StringUtils.isNotEmpty(id)){
                AZc aZc = this.aZcService.getByPK(id);
                BeanUtils.copyProperties(vo,aZc);
            }
            map.put("vo",vo);
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
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> save(AZcVo vo,HttpServletRequest request,String a01Id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            if(StringUtils.isEmpty(vo.getId())){
                A01 a01 = a01Service.getByPK(a01Id);
                AZc aZc = new AZc();
                BeanUtils.copyProperties(aZc, vo);
                aZc.setA01(a01);
                EntityWrapper.wrapperSaveBaseProperties(aZc,userLoginDetails);
                this.aZcService.save(aZc);
            }else {
                AZc aZc = this.aZcService.getByPK(vo.getId());
                BeanUtils.copyProperties(aZc, vo);
                EntityWrapper.wrapperSaveBaseProperties(aZc,userLoginDetails);
                aZcService.update(aZc);
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
            @PathVariable("id") String id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(StringUtils.isEmpty(id)){
                return null;
            }
            AZc aZc = this.aZcService.getByPK(id);
            this.aZcService.delete(aZc);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }
}
