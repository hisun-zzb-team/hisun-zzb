package com.hisun.saas.zzb.jggl.controller;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.admin.dictionary.service.DictionaryItemService;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.taglib.selectTag.SelectNode;
import com.hisun.saas.sys.taglib.treeTag.TreeNode;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.b.entity.*;
import com.hisun.saas.zzb.b.service.*;
import com.hisun.saas.zzb.b.vo.B01Vo;
import com.hisun.saas.zzb.b.vo.B09Vo;
import com.hisun.saas.zzb.b.vo.BFl2B01Vo;
import com.hisun.saas.zzb.b.vo.BFlVo;
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
 * Created by 60514 on 2018/6/4.
 */
@Controller
@RequestMapping("/zzb/jggl/b09")
public class B09Controller extends BaseController {
    @Resource
    private B09Service b09Service;
    @Resource
    private B01Service b01Service;
    @Resource
    private BB09BhJrInfoService bB09BhJrInfoService;
    @Resource
    private DictionaryItemService dictionaryItemService;

    @RequestMapping(value = "/ajax/zwgl")
    public @ResponseBody ModelAndView list(HttpServletRequest request,String b01Id,
                                               @RequestParam(value="pageNum",defaultValue="1")int pageNum,
                                               @RequestParam(value="pageSize",defaultValue="10") int pageSize) throws GenericException {
        Map<String, Object> map = Maps.newHashMap();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            CommonOrderBy orderBy = new CommonOrderBy();
            query.add(CommonRestrictions.and(" b01.id = :id ", "id", b01Id));
            Long total = this.b09Service.count(query);
            List<B09> b09s = this.b09Service.list(query,orderBy);
            List<B09Vo> vos = new ArrayList<>();
            if(b09s!=null){
                B09Vo vo;
                for(B09 entity : b09s){
                    vo = new B09Vo();
                    BeanUtils.copyProperties(vo,entity);
                    if(StringUtils.isNotEmpty(entity.getUpdateUserName())||entity.getUpdateDate()!=null){
                        vo.setUpdateData(entity.getUpdateUserName()+"\n"+entity.getUpdateDate());
                    }
                    vos.add(vo);
                }
            }
            PagerVo<B09Vo> pager = new PagerVo<B09Vo>(vos, total.intValue(), pageNum, pageSize);
            map.put("b01Id", b01Id);
            map.put("pager", pager);
            map.put("total",total);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            throw new GenericException(e);
        }

        return new ModelAndView("saas/zzb/jggl/b09/zwglList",map);
    }

    @RequestMapping(value = "/ajax/addZw")
    public @ResponseBody ModelAndView ajax(HttpServletRequest request,String b01Id){
        Map<String, Object> map = Maps.newHashMap();

        BFlVo vo = new BFlVo();
        B01 b01 = this.b01Service.getByPK(b01Id);
        int sort = this.b09Service.getMaxSort();
        if(b01!=null){
            map.put("b0101",b01.getB0101());
        }else{
            map.put("b0101","");
        }
        map.put("b01Id",b01Id);
        map.put("bPx",sort);
        return new ModelAndView("saas/zzb/jggl/b09/addZwgl",map);
    }

//    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加材料:${vo.e01Z111}")
//    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> save(B09Vo vo,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String b01Id = StringUtils.trimNull2Empty(request.getParameter("b01Id"));
        try {
            B01 b01 = this.b01Service.getByPK(b01Id);
            int sort = this.b09Service.getMaxSort();
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            B09 b09 = new B09();

            BeanUtils.copyProperties(b09, vo);

            b09.setB01(b01);
            EntityWrapper.wrapperSaveBaseProperties(b09,userLoginDetails);
            int newSort = b09.getbPx();
            if(newSort<sort){
                b09Service.updatePx(sort,newSort,b01Id);
            }
            if("0".equals(b09.getbSfsy())){
                b09.setbSysc(0);
                b09.setbSyscdw(null);
            }
            String b0900=b09Service.save(b09);
            if("1".equals(b09.getbSfjr())){
                List<BB09BhJrInfo> bb09BhJrInfos = getkJr(b01,vo,b0900);
                if(bb09BhJrInfos!=null&&bb09BhJrInfos.size()>0){
                    for(BB09BhJrInfo bb09BhJrInfo : bb09BhJrInfos){
                        bB09BhJrInfoService.save(bb09BhJrInfo);
                    }
                }
            }
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            throw new GenericException(e);
        }

        return map;
    }

    public List<BB09BhJrInfo> getkJr (B01 b01,B09Vo vo,String b0900){
        List<BB09BhJrInfo> bb09BhJrInfoList = new ArrayList<>();
        BB09BhJrInfo bb09BhJrInfo = new BB09BhJrInfo();
        if(StringUtils.isNotEmpty(vo.getJrB0901a1())&&StringUtils.isNotEmpty(vo.getJrGlB09001())){
            bb09BhJrInfo.setB09(b09Service.getByPK(b0900));
            bb09BhJrInfo.setB01(b01);
            bb09BhJrInfo.setB0901A(vo.getJrB0901a1());
            bb09BhJrInfo.setGlB0900(vo.getJrGlB09001());
            bb09BhJrInfo.setLx(1);
            bb09BhJrInfo.setZs(vo.getZs1());
            bb09BhJrInfoList.add(bb09BhJrInfo);
//            bB09BhJrInfoService.save(bb09BhJrInfo);
        }
        if(vo.getIsZs2()==1){
            bb09BhJrInfo = new BB09BhJrInfo();
            bb09BhJrInfo.setB09(b09Service.getByPK(b0900));
            bb09BhJrInfo.setB01(b01);
            bb09BhJrInfo.setB0901A(vo.getJrB0901a2());
            bb09BhJrInfo.setGlB0900(vo.getJrGlB09002());
            bb09BhJrInfo.setLx(1);
            bb09BhJrInfo.setZs(vo.getZs2());
            bb09BhJrInfoList.add(bb09BhJrInfo);
        }
        if(vo.getIsZs3()==1){
            bb09BhJrInfo = new BB09BhJrInfo();
            bb09BhJrInfo.setB09(b09Service.getByPK(b0900));
            bb09BhJrInfo.setB01(b01);
            bb09BhJrInfo.setB0901A(vo.getJrB0901a3());
            bb09BhJrInfo.setGlB0900(vo.getJrGlB09003());
            bb09BhJrInfo.setLx(1);
            bb09BhJrInfo.setZs(vo.getZs3());
            bb09BhJrInfoList.add(bb09BhJrInfo);
        }
        return bb09BhJrInfoList;
    }

    @RequestMapping(value = "/ajax/edit")
    public @ResponseBody ModelAndView edit(HttpServletRequest request,String id){
        Map<String, Object> map = Maps.newHashMap();

        B09Vo vo = new B09Vo();
        B09 b09 = b09Service.getByPK(id);
        B01Vo b01Vo = new B01Vo();
        try {
            if("1".equals(b09.getbSfjr())){
                vo.setbSfjr("1");
                int sum=0;
                CommonConditionQuery query = new CommonConditionQuery();
                query.add(CommonRestrictions.and(" b09.b0900 = :b09Id ", "b09Id", b09.getB0900()));
                List<BB09BhJrInfo> bb09BhJrInfos = this.bB09BhJrInfoService.list(query,null);
                if(bb09BhJrInfos!=null&&bb09BhJrInfos.size()>0){
                    vo.setJrB0901a1(bb09BhJrInfos.get(0).getB0901A());
                    vo.setJrGlB09001(bb09BhJrInfos.get(0).getGlB0900());
                    vo.setZs1(bb09BhJrInfos.get(0).getZs());
                }
                if(bb09BhJrInfos.size()>1){
                    vo.setJrB0901a2(bb09BhJrInfos.get(1).getB0901A());
                    vo.setJrGlB09002(bb09BhJrInfos.get(1).getGlB0900());
                    vo.setZs2(bb09BhJrInfos.get(1).getZs());
                    vo.setIsZs2(1);
                    sum++;
                }
                if(bb09BhJrInfos.size()>2){
                    vo.setJrB0901a3(bb09BhJrInfos.get(2).getB0901A());
                    vo.setJrGlB09003(bb09BhJrInfos.get(2).getGlB0900());
                    vo.setZs3(bb09BhJrInfos.get(2).getZs());
                    vo.setIsZs3(1);
                    sum++;
                }

                if(sum==2){
                    vo.setIsGd(1);
                }
            }
            BeanUtils.copyProperties(vo, b09);
            BeanUtils.copyProperties(b01Vo, b09.getB01());
            vo.setB01Vo(b01Vo);
            vo.setbSfldzwB(dictionaryItemService.getDictionaryItem(vo.getbSfldzw(),"SFBS-2018"));
        } catch (IllegalAccessException e) {
              e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        map.put("vo",vo);
        return new ModelAndView("saas/zzb/jggl/b09/editZwgl",map);
    }

//    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加材料:${vo.e01Z111}")
//    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/updateZw", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> updateZw(B09Vo vo,HttpServletRequest request) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BB09BhJrInfo bb09BhJrInfo = new BB09BhJrInfo();
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            B09 b09 = this.b09Service.getByPK(vo.getB0900());
            int sort = b09.getbPx();
            List<BB09BhJrInfo> bb09BhJrInfoList = new ArrayList<>();
            if("1".equals(b09.getbSfjr())){
                CommonConditionQuery query = new CommonConditionQuery();
                query.add(CommonRestrictions.and(" b09.b0900 = :b09Id ", "b09Id", b09.getB0900()));
                List<BB09BhJrInfo> bb09BhJrInfos = this.bB09BhJrInfoService.list(query,null);
                if(bb09BhJrInfos!=null&&bb09BhJrInfos.size()>0){
                    this.bB09BhJrInfoService.deleteList(bb09BhJrInfos);
                }
                bb09BhJrInfoList=getkJr(b09.getB01(),vo,b09.getB0900());
            }
            BeanUtils.copyProperties(b09, vo);
            if("0".equals(b09.getbSfsy())){
                b09.setbSysc(0);
                b09.setbSyscdw(null);
            }
            EntityWrapper.wrapperSaveBaseProperties(b09,userLoginDetails);
            b09Service.updateB09(b09,sort,bb09BhJrInfoList);
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
    @RequestMapping(value = "/delZw/{id}")
    public @ResponseBody Map<String, Object> delZw(
            @PathVariable("id") String id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(StringUtils.isEmpty(id)){
                return null;
            }
            B09 b09 = this.b09Service.getByPK(id);
            if("1".equals(b09.getbSfjr())){
                CommonConditionQuery query = new CommonConditionQuery();
                query.add(CommonRestrictions.and(" b09.b0900 = :b09Id ", "b09Id", b09.getB0900()));
                List<BB09BhJrInfo> bb09BhJrInfos = this.bB09BhJrInfoService.list(query,null);
                if(bb09BhJrInfos!=null&&bb09BhJrInfos.size()>0){
                    this.bB09BhJrInfoService.delete(bb09BhJrInfos.get(0));
                }
            }
            this.b09Service.delete(b09);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }

    @RequestMapping("/select")
    public @ResponseBody
    Map<String, Object> select(String typeCode,String b01Id,String b09Id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        if("pxczzw".equals(typeCode)){
            CommonConditionQuery query = new CommonConditionQuery();
            CommonOrderBy orderBy = new CommonOrderBy();
            query.add(CommonRestrictions.and(" b01.id = :id ", "id", b01Id));
            List<B09> b09s = this.b09Service.list(query,orderBy);
            List<SelectNode> nodes = new ArrayList<SelectNode>();
            SelectNode node = new SelectNode();
            if(b09s!=null&&b09s.size()>0){
                for(B09 b09:b09s){
                    if(b09Id.equals(b09.getB0900())){
                        continue;
                    }
                    node = new SelectNode();
                    node.setOptionKey(b09.getB0900());
                    node.setOptionValue(b09.getbDwzwmc());
                    nodes.add(node);
                }
            }
            map.put("success", true);
            map.put("data", nodes);
        }else{

        }
        return map;
    }
}
