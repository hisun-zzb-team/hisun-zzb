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
import com.hisun.saas.zzb.dzda.a38.controller.A38Controller;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.a38.vo.WrongExcelColumn;
import com.hisun.saas.zzb.dzda.mlcl.entity.EListRowCount;
import com.hisun.saas.zzb.dzda.mlcl.exchange.MlxxExcelExchange;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.saas.zzb.dzda.mlcl.service.RowCountService;
import com.hisun.saas.zzb.dzda.mlcl.vo.E01Z1ExcelVo;
import com.hisun.saas.zzb.dzda.mlcl.vo.E01Z1Vo;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;

import com.hisun.saas.zzb.dzda.mlcl.Constants;
import com.hisun.saas.zzb.dzda.mlcl.vo.EListRowCountVo;
import com.hisun.saas.zzb.dzda.mlcl.vo.MlclTreeNode;
import com.hisun.saas.zzb.dzda.util.DaUtils;
import com.hisun.util.StringUtils;
import com.hisun.util.URLEncoderUtil;
import com.hisun.util.UUIDUtil;
import com.hisun.util.WebUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
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
    @Resource
    private RowCountService rowCountService;

    @Resource
    MlxxExcelExchange mlxxExcelExchange;

    List<WrongExcelColumn> wrongExcelColumns;

    @Value("${sys.upload.absolute.path}")
    private String uploadBasePath;

    @RequestMapping(value = "/ajax/mlxxManage")
    public @ResponseBody ModelAndView mlxxManage(HttpServletRequest request){
        Map<String, Object> map = Maps.newHashMap();
        String a38Id = StringUtils.trimNull2Empty(request.getParameter("a38Id"));
        map.put("a38Id", a38Id);
        map.put("isAll", 2);
        return new ModelAndView("saas/zzb/dzda/mlcl/mlclManage",map);
    }

    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/ajax/mlxxList")
    public @ResponseBody ModelAndView mlxxList(HttpServletRequest request,
                                               @RequestParam(value="pageNum",defaultValue="1")int pageNum,
                                               @RequestParam(value="pageSize",defaultValue="10") int pageSize) throws GenericException {
        Map<String, Object> map = Maps.newHashMap();
        String eCatalogTypeTreeId = StringUtils.trimNull2Empty(request.getParameter("eCatalogTypeTreeId"));
        String eCatalogTypeTreeName = StringUtils.trimNull2Empty(request.getParameter("eCatalogTypeTreeName"));
        String eCatalogTypeTreeCode = StringUtils.trimNull2Empty(request.getParameter("eCatalogTypeTreeCode"));
        String eCatalogTypeTreeParentId = StringUtils.trimNull2Empty(request.getParameter("eCatalogTypeTreeParentId"));
        String a38Id = StringUtils.trimNull2Empty(request.getParameter("a38Id"));
        String url = "saas/zzb/dzda/mlcl/mlclList";
        String smxh = "";
        String a0101 = "";
        String jztpTitle = "";
        try{
            A38 a38 = this.a38Service.getByPK(a38Id);
            smxh = a38.getSmxh();
            a0101 = a38.getA0101();
            jztpTitle = a0101;
            if(smxh!=null && !smxh.equals("")){
                jztpTitle = a0101 +"("+smxh+")";
            }
            CommonConditionQuery query = new CommonConditionQuery();

            CommonOrderBy orderBy = new CommonOrderBy();
            if(StringUtils.isEmpty(eCatalogTypeTreeId)){
                orderBy.add(CommonOrder.asc("e01z101b"));
                orderBy.add(CommonOrder.asc("e01Z104"));
                eCatalogTypeTreeName="所有材料";
                url = "saas/zzb/dzda/mlcl/allMlclList";
            }else {
                ECatalogTypeInfo eCatalogTypeInfo = new ECatalogTypeInfo();
                eCatalogTypeInfo=eCatalogTypeService.getByPK(eCatalogTypeTreeId);
                eCatalogTypeTreeName = eCatalogTypeInfo.getCatalogValue();
                orderBy.add(CommonOrder.asc("e01Z104"));
                if(StringUtils.isEmpty(eCatalogTypeTreeCode)&&StringUtils.isNotEmpty(eCatalogTypeTreeId)){
                    eCatalogTypeTreeCode=eCatalogTypeInfo.getCatalogCode();
                }
            }

            CommonConditionQuery eCatalogTypeQuery = new CommonConditionQuery();
            eCatalogTypeQuery.add(CommonRestrictions.and(" parent.id = :id ", "id", eCatalogTypeTreeId));
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
                if(StringUtils.isEmpty(eCatalogTypeTreeId)){
                    query.add(CommonRestrictions.and(" a38.id = :id ", "id", a38Id));
                }else {
                    query.add(CommonRestrictions.and(" a38.id = :id ", "id", a38Id));
                    query.add(CommonRestrictions.and(" e01z101b = :e01z101b ", "e01z101b", eCatalogTypeTreeCode));
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
            map.put("eCatalogTypeTreeId",eCatalogTypeTreeId);
            map.put("eCatalogTypeTreeCode",eCatalogTypeTreeCode);
            map.put("eCatalogTypeTreeName",eCatalogTypeTreeName);
            map.put("eCatalogTypeTreeParentId",eCatalogTypeTreeParentId);
            map.put("a38Id",a38Id);
            map.put("smxh",smxh);
            map.put("a0101",a38.getA0101());
            map.put("jztpTitle",jztpTitle);

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
        String eCatalogTypeTreeId = StringUtils.trimNull2Empty(request.getParameter("eCatalogTypeTreeId"));
        String eCatalogTypeTreeCode = StringUtils.trimNull2Empty(request.getParameter("eCatalogTypeTreeCode"));
        String eCatalogTypeTreeName = StringUtils.trimNull2Empty(request.getParameter("eCatalogTypeTreeName"));
        String eCatalogTypeTreeParentId = StringUtils.trimNull2Empty(request.getParameter("eCatalogTypeTreeParentId"));
        String a38Id = StringUtils.trimNull2Empty(request.getParameter("a38Id"));

        E01Z1Vo vo = new E01Z1Vo();
        vo.setE01Z101B(eCatalogTypeTreeCode);
        vo.setE01Z101A(eCatalogTypeTreeName);

        int sort = this.e01Z1Service.getMaxSort(a38Id,eCatalogTypeTreeCode);

        vo.setE01Z104(sort);
        vo.setE01Z124(1);
        ECatalogTypeInfo eCatalogTypeInfo = new ECatalogTypeInfo();
        eCatalogTypeInfo=eCatalogTypeService.getByPK(eCatalogTypeTreeId);
        eCatalogTypeTreeName = eCatalogTypeInfo.getCatalogValue();
        map.put("eCatalogTypeTreeId",eCatalogTypeTreeId);
        map.put("eCatalogTypeTreeCode",eCatalogTypeTreeCode);
        map.put("eCatalogTypeTreeName",eCatalogTypeTreeName);
        map.put("eCatalogTypeTreeParentId",eCatalogTypeTreeParentId);
        map.put("a38Id",a38Id);
        map.put("vo",vo);
        return new ModelAndView("saas/zzb/dzda/mlcl/addMlcl",map);
    }

    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加材料:${vo.e01Z111}")
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> save(E01Z1Vo vo,HttpServletRequest request) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        String eCatalogTypeTreeId = StringUtils.trimNull2Empty(request.getParameter("eCatalogTypeTreeId"));
        String eCatalogTypeTreeCode = StringUtils.trimNull2Empty(request.getParameter("eCatalogTypeTreeCode"));
        String eCatalogTypeTreeName = StringUtils.trimNull2Empty(request.getParameter("eCatalogTypeTreeName"));
        String a38Id = StringUtils.trimNull2Empty(request.getParameter("a38Id"));
        ECatalogTypeInfo eCatalogTypeInfo = new ECatalogTypeInfo();
        eCatalogTypeInfo=eCatalogTypeService.getByPK(eCatalogTypeTreeId);
        eCatalogTypeTreeName = eCatalogTypeInfo.getCatalogValue();
        try {
            int sort = this.e01Z1Service.getMaxSort(a38Id,eCatalogTypeTreeCode);
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            E01Z1 e01Z1 = new E01Z1();
            BeanUtils.copyProperties(e01Z1, vo);
            e01Z1.setE01Z101B(eCatalogTypeTreeCode);
            e01Z1.setE01Z101A(eCatalogTypeTreeName);
            e01Z1.setECatalogTypeId(eCatalogTypeTreeId);
            e01Z1.setYjztps(0);
            if(StringUtils.isNotBlank(a38Id)){
                e01Z1.setA38(this.a38Service.getByPK(a38Id));
            }
            EntityWrapper.wrapperSaveBaseProperties(e01Z1,userLoginDetails);
            int newSort = e01Z1.getE01Z104();
            if(newSort<sort){
                e01Z1Service.updateSortBeforSave(e01Z1,sort);
            }
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
        String eCatalogTypeTreeParentId = StringUtils.trimNull2Empty(request.getParameter("eCatalogTypeTreeParentId"));
        String a38Id = StringUtils.trimNull2Empty(request.getParameter("a38Id"));
        map.put("eCatalogTypeTreeEditId",e01Z1.getECatalogTypeId());
        map.put("eCatalogTypeTreeEditCode",e01Z1.getE01Z101B());
        map.put("eCatalogTypeTreeEditName",e01Z1.getE01Z101A());
        map.put("catalogTypeEditName",e01Z1.getE01Z101A());
        map.put("yjztps",e01Z1.getYjztps());
        map.put("eCatalogTypeTreeParentEditId",eCatalogTypeTreeParentId);
        map.put("a38Id",a38Id);
        map.put("vo",e01Z1);
        return new ModelAndView("saas/zzb/dzda/mlcl/editMlcl",map);
    }

    @RequiresLog(operateType = LogOperateType.UPDATE,description = "修改材料目录:${vo.e01Z111}")
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/update")
    public @ResponseBody Map<String, Object> update(E01Z1Vo vo,HttpServletRequest request) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        String eCatalogTypeTreeId = StringUtils.trimNull2Empty(request.getParameter("eCatalogTypeTreeEditId"));
        String eCatalogTypeTreeCode = StringUtils.trimNull2Empty(request.getParameter("eCatalogTypeTreeEditCode"));
        String eCatalogTypeTreeParentId = StringUtils.trimNull2Empty(request.getParameter("eCatalogTypeTreeParentEditId"));
        ECatalogTypeInfo eCatalogTypeInfo = new ECatalogTypeInfo();
        String id = StringUtils.trimNull2Empty(request.getParameter("id"));
        try {

            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            E01Z1 e01Z1 = this.e01Z1Service.getByPK(id);
            if(StringUtils.isEmpty(eCatalogTypeTreeParentId)){
                eCatalogTypeTreeCode = e01Z1.getE01Z101B();
            }
            eCatalogTypeInfo=eCatalogTypeService.getByPK(eCatalogTypeTreeId);
            String eCatalogTypeTreeName = eCatalogTypeInfo.getCatalogValue();
            vo.setE01Z101B(eCatalogTypeTreeCode);
            vo.setE01Z101A(eCatalogTypeTreeName);
            vo.setECatalogTypeId(eCatalogTypeTreeId);
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
            if(StringUtils.isEmpty(id)){
                return null;
            }
            E01Z1 e01z1 = this.e01Z1Service.getByPK(id);
            this.e01Z1Service.deleteE01Z1(e01z1);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            throw new GenericException(e);
        }
        return map;
    }

    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/plAddMlcl")
    public ModelAndView plAddMlcl(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,@RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        Map<String, Object> map = Maps.newHashMap();
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" sjzt = :sjzt ", "sjzt", "1"));
        Long total = a38Service.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.desc("smxh"));
        orderBy.add(CommonOrder.asc("a0101"));
        List<A38> resultList = a38Service.list(query,orderBy,pageNum,pageSize);
        PagerVo<A38> pager = new PagerVo<A38>(resultList, total.intValue(), pageNum, pageSize);
        map.put("pager",pager);

        return new ModelAndView("saas/zzb/dzda/mlcl/plAddMlcl",map);
    }

    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/ajax/plGetA38List")
    public ModelAndView plGetA38List(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,@RequestParam(value = "pageSize",defaultValue = "10")int pageSize
            ,@RequestParam String a0101Query,@RequestParam String gbztCodeQuery,@RequestParam String gbztContentQuery,@RequestParam String daztCodeQuery,@RequestParam String daztContentQuery){
        Map<String, Object> map = Maps.newHashMap();
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" sjzt = :sjzt ", "sjzt", "1"));
        if(StringUtils.isNotEmpty(a0101Query)){
            query.add(CommonRestrictions.and(" a0101 like :a0101 ", "a0101", "%" + a0101Query + "%") );
        }
        if(StringUtils.isNotEmpty(gbztCodeQuery)){
            query.add(CommonRestrictions.and(" gbztCode = :gbztCode ", "gbztCode", gbztCodeQuery));
        }
        if(StringUtils.isNotEmpty(gbztContentQuery)){
            query.add(CommonRestrictions.and(" gbztContent = :gbztContent ", "gbztContent", gbztContentQuery));
        }
        if(StringUtils.isNotEmpty(daztCodeQuery)){
            query.add(CommonRestrictions.and(" daztCode = :daztCode ", "daztCode", daztCodeQuery));
        }
        if(StringUtils.isNotEmpty(daztContentQuery)){
            query.add(CommonRestrictions.and(" daztContent = :daztContent ", "daztContent", daztContentQuery));
        }
        Long total = a38Service.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.desc("smxh"));
        orderBy.add(CommonOrder.asc("a0101"));
        List<A38> resultList = a38Service.list(query,orderBy,pageNum,pageSize);
        PagerVo<A38> pager = new PagerVo<A38>(resultList, total.intValue(), pageNum, pageSize);
        map.put("a0101Query",a0101Query);
        map.put("gbztCodeQuery",gbztCodeQuery);
        map.put("daztCodeQuery",daztCodeQuery);
        map.put("gbztContentQuery",gbztContentQuery);
        map.put("daztContentQuery",daztContentQuery);
        map.put("pager",pager);
        map.put("list",resultList);

        return new ModelAndView("saas/zzb/dzda/mlcl/plAddMlclTable",map);
    }

    @RequiresLog(operateType = LogOperateType.SAVE,description = "批量增加材料:${vo.a38Ids}")
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/plSaveMlcl", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> plSaveMlcl(E01Z1Vo vo, HttpServletRequest request) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        String a38IdStr = com.hisun.util.StringUtils.trimNull2Empty(request.getParameter("a38Ids"));
        String[] a38Ids=a38IdStr.split(",");
        StringBuffer errorId = new StringBuffer();

        for(String a38Id : a38Ids){
            try {
                UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
                int sort = this.e01Z1Service.getMaxSort(a38Id,vo.getE01Z101B());
//                int smSort = this.e01Z1Service.getMaxSmSort(a38Id,vo.getE01Z101B());
                vo.setE01Z104(sort);
//                vo.setE01Z107(smSort);

                E01Z1 e01Z1 = new E01Z1();
                org.apache.commons.beanutils.BeanUtils.copyProperties(e01Z1, vo);
                if(com.hisun.util.StringUtils.isNotBlank(a38Id)){
                    e01Z1.setA38(this.a38Service.getByPK(a38Id));
                }
                e01Z1.setYjztps(0);
                EntityWrapper.wrapperSaveBaseProperties(e01Z1,userLoginDetails);
                e01Z1Service.save(e01Z1);

            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e);
                errorId.append(a38Id).append(",");
                throw new GenericException(e);
            }
        }
        map.put("success", true);
        return map;
    }

    @RequestMapping(value = "/ajax/setRowCount")
    public ModelAndView setRowCount(){
        Map<String, Object> map = new HashMap<String, Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        List<EListRowCount> eListRowCounts = rowCountService.list(query, null);
        EListRowCountVo vo = new EListRowCountVo();
        try {
            if(eListRowCounts.size()>0){
                BeanUtils.copyProperties(vo, eListRowCounts.get(0));
                map.put("vo",vo);
            }else {
                vo.setBigTypeCount("6");
                vo.setSmallTypeCount("2");
                map.put("vo",vo);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return new ModelAndView("saas/zzb/dzda/mlcl/eListRowCount/eListRowCount",map);
    }

    @RequiresLog(operateType = LogOperateType.SAVE,description = "保存行距")
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/rowCount/save", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> rowCountSave(EListRowCountVo vo, HttpServletRequest request) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            if(StringUtils.isNotEmpty(vo.getId())){
                EListRowCount eListRowCount = this.rowCountService.getByPK(vo.getId());
                EntityWrapper.wrapperUpdateBaseProperties(eListRowCount,userLoginDetails);
                BeanUtils.copyProperties(eListRowCount, vo);
                rowCountService.update(eListRowCount);
            }else{
                EListRowCount eListRowCount = new EListRowCount();
                BeanUtils.copyProperties(eListRowCount, vo);
                EntityWrapper.wrapperSaveBaseProperties(eListRowCount,userLoginDetails);
                this.rowCountService.save(eListRowCount);
            }
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
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

    @RequiresPermissions("a38:*")
    @RequestMapping("/download/{a38Id}")
    public void download(@PathVariable("a38Id") String a38Id, HttpServletResponse resp,
                         @RequestParam(value="xml",defaultValue="2")int xml,
                         @RequestParam(value="dml",defaultValue="6") int dml){
        CommonConditionQuery query = new CommonConditionQuery();
        CommonOrderBy orderBy = new CommonOrderBy();

        List<EListRowCount> eListRowCounts = rowCountService.list(query, null);//获得设置空余行数值
        if(eListRowCounts.size()>0){
            xml = Integer.parseInt(eListRowCounts.get(0).getSmallTypeCount());
            dml = Integer.parseInt(eListRowCounts.get(0).getBigTypeCount());
        }

        orderBy.add(CommonOrder.asc("sort"));
        List<ECatalogTypeInfo> eCatalogTypeInfos = eCatalogTypeService.list(query, orderBy);
        E01Z1ExcelVo e01Z1ExcelVo = new E01Z1ExcelVo();
        E01Z1Vo vo;
        String filePath="";
        Map<String,Object> map = new HashMap();
        try {

            for(ECatalogTypeInfo eCatalogTypeInfo : eCatalogTypeInfos){
                query=new CommonConditionQuery();
                orderBy = new CommonOrderBy();
                query.add(CommonRestrictions.and(" a38_id = :a38Id ", "a38Id", a38Id));
                query.add(CommonRestrictions.and(" e01z101b = :e01z101b ","e01z101b",eCatalogTypeInfo.getCatalogCode()));
                orderBy.add(CommonOrder.asc("e01Z104"));
                List<E01Z1> resultList = e01Z1Service.list(query,orderBy);
                List<E01Z1Vo> e01Z1Vos=new ArrayList<>();
                for(E01Z1 e01Z1:resultList){
                    vo = new E01Z1Vo();
                    BeanUtils.copyProperties(vo,e01Z1);
                    String date = vo.getE01Z117();
                    if(date.length()>=4){
                        vo.setYear(date.substring(0,4));
                        if(date.length()>=6){
                            vo.setMonth(date.substring(4,6));
                            if(date.length()>6){
                                vo.setDay(date.substring(6,8));
                            }
                        }
                    }else {
                        vo.setYear(date);
                    }
                    e01Z1Vos.add(vo);
                }
                if("010".equals(eCatalogTypeInfo.getCatalogCode())){
                    map.put("jlcl",e01Z1Vos.size());
                    e01Z1ExcelVo.setJlcl(e01Z1Vos);
                }else if("020".equals(eCatalogTypeInfo.getCatalogCode())){
                    map.put("zzcl",e01Z1Vos.size());
                    e01Z1ExcelVo.setZzcl(e01Z1Vos);
                }else if("030".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setJdcl(e01Z1Vos);
                    map.put("jdcl",e01Z1Vos.size());
                }else if("041".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setXlxw(e01Z1Vos);
                    map.put("xlxw",e01Z1Vos.size());
                }else if("042".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setZyzg(e01Z1Vos);
                    map.put("zyzg",e01Z1Vos.size());
                }else if("043".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setKysp(e01Z1Vos);
                    map.put("kysp",e01Z1Vos.size());
                }else if("044".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setPxcl(e01Z1Vos);
                    map.put("pxcl",e01Z1Vos.size());
                }else if("050".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setZscl(e01Z1Vos);
                    map.put("zscl",e01Z1Vos.size());
                }else if("060".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setDtcl(e01Z1Vos);
                    map.put("dtcl",e01Z1Vos.size());
                }else if("070".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setJlicl(e01Z1Vos);
                    map.put("jlicl",e01Z1Vos.size());
                }else if("080".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setCfcl(e01Z1Vos);
                    map.put("cfcl",e01Z1Vos.size());
                }else if("091".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setGzcl(e01Z1Vos);
                    map.put("gzcl",e01Z1Vos.size());
                }else if("092".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setRmcl(e01Z1Vos);
                    map.put("rmcl",e01Z1Vos.size());
                }else if("093".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setCgcl(e01Z1Vos);
                    map.put("cgcl",e01Z1Vos.size());
                }else if("094".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setDbdh(e01Z1Vos);
                    map.put("dbdh",e01Z1Vos.size());
                }else if("100".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setQtcl(e01Z1Vos);
                    map.put("qtcl",e01Z1Vos.size());
                }
            }

            File storePathFile = new File(Constants.DATP_STORE_PATH_UPLOAD);
            if(!storePathFile.exists()){
                storePathFile.mkdirs();
            }

            filePath = uploadBasePath+Constants.DATP_STORE_PATH_UPLOAD+ UUIDUtil.getUUID()+".xlsx";
            mlxxExcelExchange.setLines(e01Z1ExcelVo, uploadBasePath+Constants.DATPMB_STORE_PATH,filePath,xml, dml,map);
            resp.setContentType("multipart/form-data");
            resp.setHeader("Content-Disposition", "attachment;fileName="+ URLEncoderUtil.encode("干部档案目录表.xlsx"));
            OutputStream output = resp.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            output.flush();
            output.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }finally {
            File file = new File(filePath);
            if(file.exists()){
                file.delete();
            }
        }
    }

    @RequiresPermissions("a38:*")
    @RequestMapping("/downloadMB/{a38Id}")
    public void downloadMB(@PathVariable("a38Id") String a38Id, HttpServletResponse resp,
                         @RequestParam(value="xml",defaultValue="2")int xml,
                         @RequestParam(value="dml",defaultValue="6") int dml){
        CommonConditionQuery query = new CommonConditionQuery();
        CommonOrderBy orderBy = new CommonOrderBy();

        List<EListRowCount> eListRowCounts = rowCountService.list(query, null);//获得设置空余行数值
        if(eListRowCounts.size()>0){
            xml = Integer.parseInt(eListRowCounts.get(0).getSmallTypeCount());
            dml = Integer.parseInt(eListRowCounts.get(0).getBigTypeCount());
        }

        orderBy.add(CommonOrder.asc("sort"));
        List<ECatalogTypeInfo> eCatalogTypeInfos = eCatalogTypeService.list(query, orderBy);
        E01Z1ExcelVo e01Z1ExcelVo = new E01Z1ExcelVo();
        E01Z1Vo vo;
        String filePath="";
        Map<String,Object> map = new HashMap();
        try {

            for(ECatalogTypeInfo eCatalogTypeInfo : eCatalogTypeInfos){
                query=new CommonConditionQuery();
                orderBy = new CommonOrderBy();
                query.add(CommonRestrictions.and(" a38_id = :a38Id ", "a38Id", a38Id));
                query.add(CommonRestrictions.and(" e01z101b = :e01z101b ","e01z101b",eCatalogTypeInfo.getCatalogCode()));
                orderBy.add(CommonOrder.asc("e01Z104"));
                List<E01Z1> resultList = e01Z1Service.list(query,orderBy);
                List<E01Z1Vo> e01Z1Vos=new ArrayList<>();

                if("010".equals(eCatalogTypeInfo.getCatalogCode())){
                    map.put("jlcl",e01Z1Vos.size());
                    e01Z1ExcelVo.setJlcl(e01Z1Vos);
                }else if("020".equals(eCatalogTypeInfo.getCatalogCode())){
                    map.put("zzcl",e01Z1Vos.size());
                    e01Z1ExcelVo.setZzcl(e01Z1Vos);
                }else if("030".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setJdcl(e01Z1Vos);
                    map.put("jdcl",e01Z1Vos.size());
                }else if("041".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setXlxw(e01Z1Vos);
                    map.put("xlxw",e01Z1Vos.size());
                }else if("042".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setZyzg(e01Z1Vos);
                    map.put("zyzg",e01Z1Vos.size());
                }else if("043".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setKysp(e01Z1Vos);
                    map.put("kysp",e01Z1Vos.size());
                }else if("044".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setPxcl(e01Z1Vos);
                    map.put("pxcl",e01Z1Vos.size());
                }else if("050".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setZscl(e01Z1Vos);
                    map.put("zscl",e01Z1Vos.size());
                }else if("060".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setDtcl(e01Z1Vos);
                    map.put("dtcl",e01Z1Vos.size());
                }else if("070".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setJlicl(e01Z1Vos);
                    map.put("jlicl",e01Z1Vos.size());
                }else if("080".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setCfcl(e01Z1Vos);
                    map.put("cfcl",e01Z1Vos.size());
                }else if("091".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setGzcl(e01Z1Vos);
                    map.put("gzcl",e01Z1Vos.size());
                }else if("092".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setRmcl(e01Z1Vos);
                    map.put("rmcl",e01Z1Vos.size());
                }else if("093".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setCgcl(e01Z1Vos);
                    map.put("cgcl",e01Z1Vos.size());
                }else if("094".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setDbdh(e01Z1Vos);
                    map.put("dbdh",e01Z1Vos.size());
                }else if("100".equals(eCatalogTypeInfo.getCatalogCode())){
                    e01Z1ExcelVo.setQtcl(e01Z1Vos);
                    map.put("qtcl",e01Z1Vos.size());
                }
            }

            File storePathFile = new File(Constants.DATP_STORE_PATH_UPLOAD);
            if(!storePathFile.exists()){
                storePathFile.mkdirs();
            }

            filePath = uploadBasePath+Constants.DATP_STORE_PATH_UPLOAD+ UUIDUtil.getUUID()+".xlsx";
            mlxxExcelExchange.setLines(e01Z1ExcelVo, uploadBasePath+Constants.DATPMB_STORE_PATH,filePath,xml, dml,map);
            resp.setContentType("multipart/form-data");
            resp.setHeader("Content-Disposition", "attachment;fileName="+URLEncoderUtil.encode("档案目录模板.xlsx"));
            OutputStream output = resp.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            output.flush();
            output.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }finally {
            File file = new File(filePath);
            if(file.exists()){
                file.delete();
            }
        }
    }

    @RequiresPermissions("a38:*")
    @RequestMapping("/uploadFile")
    public @ResponseBody Map<String,Object> uploadFile (String a38Id , @RequestParam(value="mlxxFile",required = false) MultipartFile mlxxFile , HttpServletResponse resp) throws IOException {
        Map<String,Object> map = new HashMap<>();
        boolean isRight = false;
        List<WrongExcelColumn> wrongExcelColumns = new ArrayList<>();
        Map<String,Object> returnMap = new HashMap<>();
        String filePath = "";
        File storePathFile = new File(Constants.DATP_STORE_PATH_UPLOAD);
        if(!storePathFile.exists()) storePathFile.mkdirs();
        filePath = uploadBasePath+Constants.DATP_STORE_PATH_UPLOAD+ UUIDUtil.getUUID()+".xlsx";
        File file = new File(filePath);
        InputStream inputStream = null;
        OutputStream output = null;
        try {
            inputStream = mlxxFile.getInputStream();
            output = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                inputStream.close();
            }
            if(output!=null){
                output.close();
            }
        }
        String tempFile = uploadBasePath+Constants.DATPMB_STORE_PATH;
        E01Z1ExcelVo e01Z1ExcelVo;

        returnMap.put("isRight",false);
        returnMap.put("wrongExcelColumns",new ArrayList<WrongExcelColumn>());
        try {
            //解析excel 获得返回数据
            e01Z1ExcelVo = (E01Z1ExcelVo) mlxxExcelExchange.fromExcel(E01Z1ExcelVo.class,tempFile,filePath);
            //根据返回数据新增材料
            if(e01Z1ExcelVo!=null){

                returnMap = e01Z1Service.checkE01Z1ExcelVo(e01Z1ExcelVo);
                if(!(boolean) returnMap.get("isRight")) {
                    A38 a38 = a38Service.getByPK(a38Id);
                    e01Z1Service.saveE01Z1S(e01Z1ExcelVo,a38);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            file.delete();
        }
        isRight= (boolean) returnMap.get("isRight");
        wrongExcelColumns = (List<WrongExcelColumn>) returnMap.get("wrongExcelColumns");
        map.put("success",true);
        if(isRight){
            this.wrongExcelColumns = wrongExcelColumns;
            map.put("isWrong",true);
        }else {
            map.put("isWrong",false);
        }
        return map;
    }

    @RequestMapping(value = "/ajax/cwjl")
    public ModelAndView loadGjcx(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("datas",this.wrongExcelColumns);
        return new ModelAndView("saas/zzb/dzda/a32/wrongList",map);
    }

    /**
     * 申请查阅 部分授权使用
     * @param a38Id
     * @return
     * @throws GenericException
     */

    @RequestMapping("/ajax/tree/{a38Id}")
    public
    @ResponseBody
    Map<String, Object> tree(@PathVariable(value = "a38Id") String a38Id) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();

            CommonConditionQuery query = new CommonConditionQuery();
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("sort"));
            List<ECatalogTypeInfo> eCatalogTypeInfos = eCatalogTypeService.list(query, orderBy);
            A38 a38 = a38Service.getByPK(a38Id);
            CommonConditionQuery query1 = new CommonConditionQuery();
            query1.add(CommonRestrictions.and("a38.id=:a38Id", "a38Id", a38Id));
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
                childTreeNode.setName(eCatalogTypeInfo.getCatalogCode() + "." + eCatalogTypeInfo.getCatalogValue());
                childTreeNode.setKey(eCatalogTypeInfo.getCatalogCode());
                childTreeNode.setOpen(true);
                childTreeNode.setNodeType("dir");
                if (eCatalogTypeInfo.getParent() == null) {
                    childTreeNode.setpId(a38.getId());
                } else {
                    childTreeNode.setpId(eCatalogTypeInfo.getParent().getId());
                }
                treeNodes.add(childTreeNode);
            }
            for (E01Z1 e01Z1 : e01Z1s) {
                String text = e01Z1.getE01Z111();
                String e01z117 = StringUtils.trimNull2Empty(e01Z1.getE01Z117());//制成时间
                int imagesCount = e01Z1.getE01Z114()==null?0:e01Z1.getE01Z114();
                String title = e01Z1.getE01Z111();

                if (!e01z117.equals("")) {
                    text = text + "," + e01z117;
                    title = title + " 制成时间：" + e01z117;
                }
                if (imagesCount != 0) {
                    text = text + "," + imagesCount;
                    title = title + " 材料页数：" + imagesCount;
                }
                childTreeNode = new MlclTreeNode();
                childTreeNode.setId(e01Z1.getId());
                childTreeNode.setName(text);
                childTreeNode.setDescription(title);
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