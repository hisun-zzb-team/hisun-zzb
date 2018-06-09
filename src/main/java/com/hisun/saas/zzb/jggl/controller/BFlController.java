package com.hisun.saas.zzb.jggl.controller;

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
import com.hisun.saas.sys.taglib.treeTag.TreeNode;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.b.entity.B01;
import com.hisun.saas.zzb.b.entity.BFl;
import com.hisun.saas.zzb.b.entity.BFl2B01;
import com.hisun.saas.zzb.b.service.B01Service;
import com.hisun.saas.zzb.b.service.BFl2B01Service;
import com.hisun.saas.zzb.b.service.BFlService;
import com.hisun.saas.zzb.b.vo.B01TreeNode;
import com.hisun.saas.zzb.b.vo.B01Vo;
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
@RequestMapping("/zzb/jggl/fl")
public class BFlController  extends BaseController {
    @Resource
    private BFlService bFlService;
    @Resource
    private B01Service b01Service;
    @Resource
    private BFl2B01Service bFl2B01Service;

    @RequestMapping(value = "/flManage")
    public @ResponseBody
    ModelAndView index(HttpServletRequest request,String parentBFlId,String key,String bflId,String fl){
        Map<String, Object> map = Maps.newHashMap();
        map.put("bflId",bflId);
        map.put("fl",fl);
        map.put("parentBFlId",parentBFlId);
        map.put("key",key);
        return new ModelAndView("saas/zzb/jggl/fl/index",map);
    }

    @RequestMapping(value = "/ajax/list")
    public @ResponseBody ModelAndView list(HttpServletRequest request,String parentBFlId,String key,String bflId,String flQuery,String b0101Query,
                                               @RequestParam(value="pageNum",defaultValue="1")int pageNum,
                                               @RequestParam(value="pageSize",defaultValue="10") int pageSize) throws GenericException {
        Map<String, Object> map = Maps.newHashMap();
        String url;
        if("1".equals(key)){
            url = "saas/zzb/jggl/fl/flglList";
        }else {
            url = "saas/zzb/jggl/fl/jgglList";
        }
        try{
            Long total = 0l;
            CommonConditionQuery query = new CommonConditionQuery();
            CommonOrderBy orderBy = new CommonOrderBy();
            if("1".equals(key)){
                if(StringUtils.isNotEmpty(flQuery)){
                    query.add(CommonRestrictions.and(" fl like :flQuery ", "flQuery", "%" + flQuery + "%") );
                }
                if(StringUtils.isEmpty(parentBFlId)){
                    query.add(CommonRestrictions.and(" parentBFl.id is null ",null,null));
                }else {
                    query.add(CommonRestrictions.and(" parentBFl.id = :id ", "id", bflId));
                }
                orderBy.add(CommonOrder.asc("px"));
                total = this.bFlService.count(query);
                List<BFl> bFlList = this.bFlService.list(query,orderBy);
                List<BFlVo> vos = new ArrayList<>();
                if(bFlList!=null){
                    BFlVo vo;
                    for(BFl entity : bFlList){
                        vo = new BFlVo();
                        BeanUtils.copyProperties(vo,entity);
                        vos.add(vo);
                    }
                }
                PagerVo<BFlVo> pager = new PagerVo<BFlVo>(vos, total.intValue(), pageNum, pageSize);
                map.put("pager", pager);
                map.put("flQuery", flQuery);
                map.put("total",total);
            }else {
                if(StringUtils.isNotEmpty(b0101Query)){
                    query.add(CommonRestrictions.and(" b01.b0101 like :b0101Query ", "b0101Query", "%" + b0101Query + "%") );
                }
                query.add(CommonRestrictions.and(" bfl.id = :id ", "id", bflId));
                orderBy.add(CommonOrder.asc("px"));
                total = this.bFl2B01Service.count(query);
                List<BFl2B01> bFl2B01List = this.bFl2B01Service.list(query,orderBy);
                List<BFl2B01Vo> vos = new ArrayList<>();
                if(bFl2B01List!=null){
                    BFl2B01Vo vo;
                    for(BFl2B01 entity : bFl2B01List){
                        vo = new BFl2B01Vo();
                        BeanUtils.copyProperties(vo,entity);
                        B01 b01=entity.getB01();
                        B01Vo b01Vo = new B01Vo();
                        BeanUtils.copyProperties(b01Vo,b01);
                        vo.setB01Vo(b01Vo);
                        BFl bFl = entity.getBfl();
                        BFlVo bFlVo = new BFlVo();
                        BeanUtils.copyProperties(bFlVo,bFl);
                        vo.setBflVo(bFlVo);
                        vos.add(vo);
                    }
                }
                PagerVo<BFl2B01Vo> pager = new PagerVo<BFl2B01Vo>(vos, total.intValue(), pageNum, pageSize);
                map.put("pager", pager);
                map.put("b0101Query", b0101Query);
                map.put("total",total);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
            throw new GenericException(e);
        }
        return new ModelAndView(url,map);
    }

    @RequestMapping(value = "/ajax/addFl")
    public @ResponseBody ModelAndView ajax(HttpServletRequest request,String bflId){
        Map<String, Object> map = Maps.newHashMap();

        BFlVo vo = new BFlVo();

        int sort = this.bFlService.getMaxSort();

        map.put("bflId",bflId);
        map.put("px",sort);
        map.put("vo",vo);
        return new ModelAndView("saas/zzb/jggl/fl/addFlgl",map);
    }

//    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加材料:${vo.e01Z111}")
//    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/saveFl", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> saveFl(BFlVo vo,HttpServletRequest request) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        String bflId = StringUtils.trimNull2Empty(request.getParameter("bflId"));
        try {
            int sort = this.bFlService.getMaxSort();
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            BFl bFl = new BFl();
            BeanUtils.copyProperties(bFl, vo);
            bFl.setParentBFl(this.bFlService.getByPK(bflId));
            EntityWrapper.wrapperSaveBaseProperties(bFl,userLoginDetails);
            int newSort = bFl.getPx();
//            if(newSort<sort){
//                bFlService.updateSortBeforSave(bFl,sort);
//            }
            bFlService.save(bFl);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            throw new GenericException(e);
        }

        return map;
    }

    @RequestMapping(value = "/ajax/editFl")
    public @ResponseBody ModelAndView editFl(HttpServletRequest request,String id){
        Map<String, Object> map = Maps.newHashMap();

        BFlVo vo = new BFlVo();
        BFl bFl = bFlService.getByPK(id);
        try {
            BeanUtils.copyProperties(vo, bFl);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        map.put("bflId",id);
        map.put("vo",vo);
        return new ModelAndView("saas/zzb/jggl/fl/editFlgl",map);
    }

//    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加材料:${vo.e01Z111}")
//    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/updateFl", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> updateFl(BFlVo vo,HttpServletRequest request) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        String id = StringUtils.trimNull2Empty(request.getParameter("id"));
        try {

            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            BFl bFl = this.bFlService.getByPK(id);
            int sort = bFl.getPx();
            BeanUtils.copyProperties(bFl, vo);

            EntityWrapper.wrapperSaveBaseProperties(bFl,userLoginDetails);
            int newSort = bFl.getPx();
            String a;
            if(bFl.getParentBFl()==null){
                a= null;
            }else {
                a = bFl.getParentBFl().getId();
            }
            bFlService.updateBFl(bFl,sort,a);
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
    @RequestMapping(value = "/delFl/{id}")
    public @ResponseBody Map<String, Object> delFl(
            @PathVariable("id") String id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(StringUtils.isEmpty(id)){
                return null;
            }
            BFl bFl = this.bFlService.getByPK(id);
            List<BFl> bFlList = new ArrayList<>();
            bFlList.add(bFl);
            this.bFlService.deleteList(bFlList);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }


    @RequestMapping(value = "/ajax/addJg")
    public @ResponseBody ModelAndView addJg(HttpServletRequest request,String bflId,String fl){
        Map<String, Object> map = Maps.newHashMap();
        CommonConditionQuery query = new CommonConditionQuery();
        CommonOrderBy orderBy = new CommonOrderBy();
        query.add(CommonRestrictions.and(" bfl.id = :id ", "id", bflId));
        orderBy.add(CommonOrder.asc("px"));
        List<BFl2B01> bFl2B01List = this.bFl2B01Service.list(query,orderBy);
        BFlVo vo = new BFlVo();
        String b01Ids = "";
        String b01Names = "";

        if(bFl2B01List.size()>0){
            for(int i=0;i<bFl2B01List.size();i++){
                if(i==bFl2B01List.size()-1){
                    b01Ids+=bFl2B01List.get(i).getB01().getB0100();
                    b01Names+=bFl2B01List.get(i).getB01().getB0101();
                }else {
                    b01Ids+=bFl2B01List.get(i).getB01().getB0100()+",";
                    b01Names+=bFl2B01List.get(i).getB01().getB0101()+",";
                }
            }
        }
        int sort = this.bFlService.getMaxSort();

        map.put("bflId",bflId);
        map.put("fl",fl);
        map.put("jgQuery",b01Ids);
        map.put("jgNameQuery",b01Names);
        return new ModelAndView("saas/zzb/jggl/fl/addJggl",map);
    }

//    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加材料:${vo.e01Z111}")
//    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/saveJg", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> saveJg(HttpServletRequest request,String idString,String jgQuery
            ,String jgNameQuery,String bflId) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        String [] ids = idString.split(",");
        String [] addIds = new String[ids.length];
        CommonConditionQuery query = new CommonConditionQuery();
        CommonOrderBy orderBy = new CommonOrderBy();
        try {
            BFl bFl = this.bFlService.getByPK(bflId);

            query.add(CommonRestrictions.and(" bfl.id = :id ", "id", bflId));
            orderBy.add(CommonOrder.asc("px"));
            List<BFl2B01> bFl2B01List = this.bFl2B01Service.list(query,orderBy);
            String [] delIds = new String[bFl2B01List.size()];
            int s = 0;
            boolean flag;
            for(int i=0;i<ids.length;i++){
                flag=true;
                for(int j=0;j<bFl2B01List.size();j++){
                    if(ids[i].equals(bFl2B01List.get(j).getB01().getB0100())){
                        flag=false;
                        break;
                    }
                    if(j==bFl2B01List.size()-1){
                        addIds[s] = ids[i];
                        s++;
                        flag=false;
                    }
                }
                if(flag){
                    addIds[s] = ids[i];
                    s++;
                }
            }

            s = 0;
            for(int i=0;i<bFl2B01List.size();i++){
                for(int j=0;j<ids.length;j++){
                    if(ids[j].equals(bFl2B01List.get(i).getB01().getB0100())){
                        break;
                    }
                    if(j==ids.length-1){
                        delIds[s] = bFl2B01List.get(i).getB01().getB0100();
                        s++;
                    }
                }
            }

            if(delIds.length>0){
                for(String id:delIds){
                    if(StringUtils.isEmpty(id)){
                        break;
                    }
                    query = new CommonConditionQuery();
                    query.add(CommonRestrictions.and(" bfl.id = :bflId ", "bflId", bflId));
                    query.add(CommonRestrictions.and(" b01.b0100 = :b01Id ", "b01Id", id));
                    List<BFl2B01> bFl2B01ListDel = this.bFl2B01Service.list(query,orderBy);
                    if(bFl2B01ListDel.size()>0){
                        this.bFl2B01Service.deleteList(bFl2B01ListDel);
                    }
                }
            }

            if(addIds.length>0){
                for(String id:addIds){
                    if(StringUtils.isEmpty(id)){
                        break;
                    }
                    B01 b01 = this.b01Service.getByPK(id);
                    BFl2B01 bFl2B01 = new BFl2B01();
                    bFl2B01.setB01(b01);
                    bFl2B01.setBfl(bFl);
                    this.bFl2B01Service.save(bFl2B01);
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

    //    @RequiresLog(operateType = LogOperateType.DELETE,description = "删除材料:${id}")
//    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/delJg/{id}")
    public @ResponseBody Map<String, Object> delJg(
            @PathVariable("id") String id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(StringUtils.isEmpty(id)){
                return null;
            }
            BFl2B01 bFl2B01 = this.bFl2B01Service.getByPK(id);
            this.bFl2B01Service.delete(bFl2B01);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }

    @RequestMapping("/tree")
    public @ResponseBody
    Map<String, Object> tree() throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("px"));
            List<BFl> bFlList = this.bFlService.list(query,orderBy);
            List<B01TreeNode> nodes = this.b01Service.getB01TreeVoList(null,null,null,null);
            List<TreeNode> treeNodes = new ArrayList<TreeNode>();
            TreeNode treeNode = new TreeNode();
            String Pid = "";
            if(nodes!=null&&nodes.size()>0){
                for(B01TreeNode b01TreeNode:nodes){
                    if(b01TreeNode.getIsParent()){
                        treeNode = new TreeNode();
                        treeNode.setId(b01TreeNode.getId());
                        treeNode.setName(b01TreeNode.getName());
                        treeNode.setpId(b01TreeNode.getpId());
                        treeNode.setKey("1");
                        treeNodes.add(treeNode);
                        Pid=b01TreeNode.getId();
                        break;
                    }
                }
            }

            TreeNode childTreeNode=null;
            for (BFl bFl : bFlList) {
                childTreeNode = new TreeNode();
                childTreeNode.setId(bFl.getId());
                childTreeNode.setName(bFl.getFl());
                childTreeNode.setKey("2");
                childTreeNode.setpId(Pid);
                treeNodes.add(childTreeNode);
            }
            map.put("success", true);
            map.put("data", treeNodes);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        }
        return map;
    }
}
