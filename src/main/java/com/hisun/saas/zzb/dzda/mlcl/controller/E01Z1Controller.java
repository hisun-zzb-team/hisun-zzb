/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.mlcl.controller;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.admin.dzda.entity.ECatalogTypeInfo;
import com.hisun.saas.sys.admin.dzda.service.ECatalogTypeService;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.log.LogOperateType;
import com.hisun.saas.sys.log.RequiresLog;
import com.hisun.saas.sys.taglib.treeTag.TreeNode;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.saas.zzb.dzda.mlcl.vo.E01Z1Vo;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;
import com.hisun.saas.zzb.dzda.mlcl.vo.MlclTreeNode;
import com.hisun.util.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author zhout {605144321@qq.com}
*/
@Controller
@RequestMapping("/zzb/dzda/e01z1")
public class E01Z1Controller extends BaseController {

    @Resource
    private ECatalogTypeService eCatalogTypeService;
    @Resource
    private A38Service a38Service;
    @Resource
    private E01Z1Service e01Z1Service;

    @RequestMapping(value = "/ajax/mlxxManage")
    public @ResponseBody ModelAndView mlxxManage(HttpServletRequest request){
        Map<String, Object> map = Maps.newHashMap();
        String a38Id = StringUtils.trimNull2Empty(request.getParameter("a38Id"));
        map.put("a38Id", a38Id);
        return new ModelAndView("saas/zzb/dzda/mlcl/mlclManage",map);
    }

    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/ajax/mlxxList")
    public @ResponseBody ModelAndView mlxxList(HttpServletRequest request,
                                               @RequestParam(value="pageNum",defaultValue="1")int pageNum,
                                               @RequestParam(value="pageSize",defaultValue="10") int pageSize) throws GenericException {
        Map<String, Object> map = Maps.newHashMap();
        String currentNodeId = StringUtils.trimNull2Empty(request.getParameter("currentNodeId"));
        String currentNodeName = StringUtils.trimNull2Empty(request.getParameter("currentNodeName"));
        String currentNodeCode = StringUtils.trimNull2Empty(request.getParameter("currentNodeCode"));
        String currentNodeParentId = StringUtils.trimNull2Empty(request.getParameter("currentNodeParentId"));
        String a38Id = StringUtils.trimNull2Empty(request.getParameter("a38Id"));
        String url = "saas/zzb/dzda/mlcl/mlclList";

        try{
            A38 a38 = this.a38Service.getByPK(a38Id);

            CommonConditionQuery query = new CommonConditionQuery();

            CommonOrderBy orderBy = new CommonOrderBy();
            if(StringUtils.isEmpty(currentNodeId)){
                orderBy.add(CommonOrder.asc("e01z101b"));
                currentNodeName="所有材料";
                url = "saas/zzb/dzda/mlcl/allMlclList";
            }else {
                ECatalogTypeInfo eCatalogTypeInfo = new ECatalogTypeInfo();
                eCatalogTypeInfo=eCatalogTypeService.getByPK(currentNodeId);
                currentNodeName = eCatalogTypeInfo.getCatalogValue();
                orderBy.add(CommonOrder.asc("e01Z104"));
            }

            CommonConditionQuery eCatalogTypeQuery = new CommonConditionQuery();
            eCatalogTypeQuery.add(CommonRestrictions.and(" parent.id = :id ", "id", currentNodeId));
            List<E01Z1> entities = new ArrayList<>();
            Long eCatalogTypetotal = this.eCatalogTypeService.count(eCatalogTypeQuery);

            Long total = 0l;
            if(eCatalogTypetotal>0){
                CommonOrderBy eCatalogTypeOrderBy = new CommonOrderBy();
                List<ECatalogTypeInfo> eCatalogTypeEntities = this.eCatalogTypeService.list(eCatalogTypeQuery, eCatalogTypeOrderBy);
                for(ECatalogTypeInfo eCatalogType:eCatalogTypeEntities){
                    String catalogCode = eCatalogType.getCatalogCode();
                    query.add(CommonRestrictions.and(" a38.id = :id ", "id", a38Id));
                    query.add(CommonRestrictions.and(" e01z101b = :e01z101b ", "e01z101b", catalogCode));

                    Long totals=this.e01Z1Service.count(query);
                    total+=totals;

                    entities.addAll(this.e01Z1Service.list(query, orderBy, pageNum, pageSize));
                }
                url = "saas/zzb/dzda/mlcl/allMlclList";
            }else  {
                if(StringUtils.isEmpty(currentNodeId)){
                    query.add(CommonRestrictions.and(" a38.id = :id ", "id", a38Id));
                }else {
                    query.add(CommonRestrictions.and(" a38.id = :id ", "id", a38Id));
                    query.add(CommonRestrictions.and(" e01z101b = :e01z101b ", "e01z101b", currentNodeCode));
                }
                total=this.e01Z1Service.count(query);
                entities = this.e01Z1Service.list(query, orderBy, pageNum, pageSize);
            }

            List<E01Z1Vo> vos = new ArrayList<>();
            E01Z1Vo vo = null;
            if(entities!=null){
                for(E01Z1 entity : entities){
                    vo = new E01Z1Vo();
                    BeanUtils.copyProperties(vo,entity);
                    vos.add(vo);
                }
            }
            PagerVo<E01Z1Vo> pager = new PagerVo<E01Z1Vo>(vos, total.intValue(), pageNum, pageSize);
            map.put("pager", pager);
            map.put("currentNodeId",currentNodeId);
            map.put("currentNodeCode",currentNodeCode);
            map.put("currentNodeName",currentNodeName);
            map.put("currentNodeParentId",currentNodeParentId);
            map.put("a38Id",a38Id);
            map.put("a0101",a38.getA0101());
            map.put("total",total);
        }catch(Exception e){
            logger.error(e);
            throw new GenericException(e);
        }

        return new ModelAndView(url,map);
    }

    @RequestMapping(value = "/ajax/addMlcl")
    public @ResponseBody ModelAndView addMlcl(HttpServletRequest request){
        Map<String, Object> map = Maps.newHashMap();
        String currentNodeId = StringUtils.trimNull2Empty(request.getParameter("currentNodeId"));
        String currentNodeCode = StringUtils.trimNull2Empty(request.getParameter("currentNodeCode"));
        String currentNodeName = StringUtils.trimNull2Empty(request.getParameter("currentNodeName"));
        String currentNodeParentId = StringUtils.trimNull2Empty(request.getParameter("currentNodeParentId"));
        String a38Id = StringUtils.trimNull2Empty(request.getParameter("a38Id"));

        E01Z1Vo vo = new E01Z1Vo();
        vo.setE01Z101B(currentNodeCode);
        vo.setE01Z101A(currentNodeName);

        int sort = this.e01Z1Service.getMaxSort(a38Id,currentNodeCode);
        int smSort = this.e01Z1Service.getMaxSmSort(a38Id,currentNodeCode);

        vo.setE01Z104(sort);
        vo.setE01Z107(smSort);
        ECatalogTypeInfo eCatalogTypeInfo = new ECatalogTypeInfo();
        eCatalogTypeInfo=eCatalogTypeService.getByPK(currentNodeId);
        currentNodeName = eCatalogTypeInfo.getCatalogValue();
        map.put("currentNodeId",currentNodeId);
        map.put("currentNodeCode",currentNodeCode);
        map.put("currentNodeName",currentNodeName);
        map.put("currentNodeParentId",currentNodeParentId);
        map.put("a38Id",a38Id);
        map.put("vo",vo);
        return new ModelAndView("saas/zzb/dzda/mlcl/addMlcl",map);
    }

    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加材料:${vo.e01Z111}")
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> save(E01Z1Vo vo,HttpServletRequest request) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        String currentNodeId = StringUtils.trimNull2Empty(request.getParameter("currentNodeId"));
        String currentNodeCode = StringUtils.trimNull2Empty(request.getParameter("currentNodeCode"));
        String currentNodeName = StringUtils.trimNull2Empty(request.getParameter("currentNodeName"));
        String a38Id = StringUtils.trimNull2Empty(request.getParameter("a38Id"));
        ECatalogTypeInfo eCatalogTypeInfo = new ECatalogTypeInfo();
        eCatalogTypeInfo=eCatalogTypeService.getByPK(currentNodeId);
        currentNodeName = eCatalogTypeInfo.getCatalogValue();
        try {
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            E01Z1 e01Z1 = new E01Z1();
            BeanUtils.copyProperties(e01Z1, vo);
            e01Z1.setE01Z101B(currentNodeCode);
            e01Z1.setE01Z101A(currentNodeName);
            e01Z1.setECatalogTypeId(currentNodeId);
            if(StringUtils.isNotBlank(a38Id)){
                e01Z1.setA38(this.a38Service.getByPK(a38Id));
            }
            EntityWrapper.wrapperSaveBaseProperties(e01Z1,userLoginDetails);
            e01Z1Service.save(e01Z1);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            throw new GenericException(e);
        }

        return map;
    }

    @RequestMapping(value = "/ajax/editMlcl")
    public @ResponseBody ModelAndView editMlcl(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        String id = StringUtils.trimNull2Empty(request.getParameter("id"));
        E01Z1 e01Z1 = e01Z1Service.getByPK(id);
        String currentNodeId = StringUtils.trimNull2Empty(request.getParameter("currentNodeId"));
        String currentNodeCode = StringUtils.trimNull2Empty(request.getParameter("currentNodeCode"));
        String currentNodeName = StringUtils.trimNull2Empty(request.getParameter("currentNodeName"));
        String currentNodeParentId = StringUtils.trimNull2Empty(request.getParameter("currentNodeParentId"));
        String a38Id = StringUtils.trimNull2Empty(request.getParameter("a38Id"));
        map.put("currentNodeId",currentNodeId);
        map.put("currentNodeCode",e01Z1.getE01Z101B());
        map.put("currentNodeName",currentNodeName);
        map.put("catalogTypeName",e01Z1.getE01Z101A());
        map.put("yjztps",e01Z1.getYjztps());
        map.put("currentNodeParentId",currentNodeParentId);
        map.put("a38Id",a38Id);
        map.put("vo",e01Z1);
        return new ModelAndView("saas/zzb/dzda/mlcl/editMlcl",map);
    }

    @RequiresLog(operateType = LogOperateType.UPDATE,description = "修改材料目录:${vo.e01Z111}")
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/update")
    public @ResponseBody Map<String, Object> update(E01Z1Vo vo,HttpServletRequest request) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        String currentNodeId = StringUtils.trimNull2Empty(request.getParameter("currentNodeId"));
        String currentNodeCode = StringUtils.trimNull2Empty(request.getParameter("currentNodeCode"));
        String currentNodeParentId = StringUtils.trimNull2Empty(request.getParameter("currentNodeParentId"));
        ECatalogTypeInfo eCatalogTypeInfo = new ECatalogTypeInfo();
        String id = StringUtils.trimNull2Empty(request.getParameter("id"));
        try {

            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            E01Z1 e01Z1 = this.e01Z1Service.getByPK(id);
            if(StringUtils.isEmpty(currentNodeParentId)){
                currentNodeCode = e01Z1.getE01Z101B();
            }
            eCatalogTypeInfo=eCatalogTypeService.getByPK(currentNodeId);
            String currentNodeName = eCatalogTypeInfo.getCatalogValue();
            vo.setE01Z101B(currentNodeCode);
            vo.setE01Z101A(currentNodeName);
            vo.setECatalogTypeId(currentNodeId);
            int oldSort = e01Z1.getE01Z104();

            BeanUtils.copyProperties(e01Z1, vo);
            e01Z1.setA38(this.a38Service.getByPK(e01Z1.getA38().getId()));
            EntityWrapper.wrapperUpdateBaseProperties(e01Z1,userLoginDetails);
            this.e01Z1Service.updateE01Z1(e01Z1, oldSort);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            throw new GenericException(e.getMessage());
        }
        return map;
    }

    @RequiresLog(operateType = LogOperateType.DELETE,description = "删除材料:${id}")
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/delete/{id}")
    public @ResponseBody Map<String, Object> delete(
            @PathVariable("id") String id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            this.e01Z1Service.deleteByPK(id);
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
            orderBy.add(CommonOrder.asc("sort"));
            List<ECatalogTypeInfo> eCatalogTypeInfos = eCatalogTypeService.list(query, orderBy);
            List<TreeNode> treeNodes = new ArrayList<TreeNode>();
            TreeNode treeNode = new TreeNode();
            TreeNode childTreeNode=null;
            for (ECatalogTypeInfo eCatalogTypeInfo : eCatalogTypeInfos) {
                childTreeNode = new TreeNode();
                childTreeNode.setId(eCatalogTypeInfo.getId());
                childTreeNode.setName(eCatalogTypeInfo.getSort()+"."+eCatalogTypeInfo.getCatalogValue());
                childTreeNode.setKey(eCatalogTypeInfo.getCatalogCode());
                if(eCatalogTypeInfo.getParent()==null){
                    childTreeNode.setpId(treeNode.getId());
                }else{
                    childTreeNode.setpId(eCatalogTypeInfo.getParent().getId());
                }
                treeNodes.add(childTreeNode);
            }
            map.put("success", true);
            map.put("data", treeNodes);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e, e);
            map.put("success", false);
        }
        return map;
    }

    /**
     *
     * @param a38Id 档案主键
     * @param a0101 档案姓名
     * @param archiveId 档案材料字典ID
     * @param e01z1Id 档案材料ID
     * @param showType  浏览的位置  如果为refer则是   如果是viewApply则为查阅列表进入
     * @return
     * @throws GenericException
     */
    @RequiresLog(operateType = LogOperateType.QUERY,description = "查看档案:${a0101}")
    @RequestMapping("/ajax/viewMain/{a38Id}")
    public
    @ResponseBody ModelAndView viewMain(@PathVariable(value = "a38Id") String a38Id,String a0101,String archiveId,String e01z1Id,String showType) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();

        String myDirName = "";
        map.put("a38Id", a38Id);
        map.put("archiveId", archiveId);
        map.put("e01z1Id", e01z1Id);
        map.put("myDirName", myDirName);
        map.put("a0101", a0101);
        return new ModelAndView("saas/zzb/dzda/mlcl/viewImg/viewImgManage",map);
    }

    @RequestMapping(value = "/ajax/viewImg")
    public ModelAndView viewImg(@PathVariable(value = "a38Id") String a38Id,String archiveId,String myDirName){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a38Id", a38Id);
        map.put("archiveId", archiveId);
        map.put("myDirName", myDirName);
        return new ModelAndView("saas/zzb/dzda/mlcl/viewImg/viewImg",map);
    }

    @RequestMapping("/ajax/typeAndE01z1Tree/{a38Id}")
    public
    @ResponseBody
    Map<String, Object> typeAndE01z1Tree(@PathVariable(value = "a38Id") String a38Id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            CommonConditionQuery query = new CommonConditionQuery();
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("sort"));
            List<ECatalogTypeInfo> eCatalogTypeInfos = eCatalogTypeService.list(query, orderBy);
            A38 a38 = a38Service.getByPK(a38Id);
            CommonConditionQuery query1 = new CommonConditionQuery();
            query1.add(CommonRestrictions.and("a38.id=:a38Id","a38Id",a38Id));
            CommonOrderBy orderBy1 = new CommonOrderBy();
            orderBy1.add(CommonOrder.asc("e01z104"));
            List<E01Z1> e01Z1s = e01Z1Service.list(query1, orderBy1);
            List<MlclTreeNode> treeNodes = new ArrayList<MlclTreeNode>();
            MlclTreeNode treeNode = new MlclTreeNode();
            treeNode.setId(a38.getId());
            treeNode.setName(a38.getA0101());
            treeNode.setNodeType("a38");
            treeNode.setOpen(true);
            treeNodes.add(treeNode);
            MlclTreeNode childTreeNode = null;
            for (ECatalogTypeInfo eCatalogTypeInfo : eCatalogTypeInfos) {
                childTreeNode = new MlclTreeNode();
                childTreeNode.setId(eCatalogTypeInfo.getId());
                childTreeNode.setName(eCatalogTypeInfo.getCatalogValue());
                childTreeNode.setKey(eCatalogTypeInfo.getCatalogCode());
                childTreeNode.setNodeType("dir");
                if (eCatalogTypeInfo.getParent() == null) {
                    childTreeNode.setpId(a38.getId());
                } else {
                    childTreeNode.setpId(eCatalogTypeInfo.getParent().getId());
                }
                treeNodes.add(childTreeNode);
            }
            for (E01Z1 e01Z1 : e01Z1s) {
                childTreeNode = new MlclTreeNode();
                childTreeNode.setId(e01Z1.getId());
                childTreeNode.setName(e01Z1.getE01Z111());
                DecimalFormat decimalFormat = new DecimalFormat("00");
                childTreeNode.setKey(decimalFormat.format(e01Z1.getE01Z107()));
                childTreeNode.setpId(e01Z1.getECatalogTypeId());
                childTreeNode.setNodeType("cl");
                treeNodes.add(childTreeNode);
            }
            map.put("success", true);
            map.put("data", treeNodes);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e, e);
            map.put("success", false);
        }
        return map;
    }
}