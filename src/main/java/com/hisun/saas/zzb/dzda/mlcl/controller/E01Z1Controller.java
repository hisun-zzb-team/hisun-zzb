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
import com.hisun.saas.zzb.dzda.mlcl.exchange.MlxxExcelExchange;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.saas.zzb.dzda.mlcl.vo.E01Z1ExcelVo;
import com.hisun.saas.zzb.dzda.mlcl.vo.E01Z1Vo;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;

import com.hisun.saas.zzb.dzda.mlcl.Constants;
import com.hisun.saas.zzb.dzda.mlcl.vo.MlclTreeNode;
import com.hisun.util.StringUtils;
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
    MlxxExcelExchange mlxxExcelExchange;

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

        try{
            A38 a38 = this.a38Service.getByPK(a38Id);

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
    public ModelAndView plGetA38List(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,@RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
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
                        vo.setYear(date.substring(date.length()-4,date.length()));
                        if(date.length()>=6){
                            vo.setMonth(date.substring(date.length()-6,date.length()-4));
                            if(date.length()>6){
                                vo.setDay(date.substring(date.length()-8,date.length()-6));
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
            resp.setHeader("Content-Disposition", "attachment;fileName="+encode("mlxx.xlsx"));
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

    private String encode(String filename) throws UnsupportedEncodingException {
        if (WebUtil.getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            filename = URLEncoder.encode(filename, "UTF-8");
        } else {
            filename = new String(filename.getBytes("UTF-8"), "GBK");
        }
        return filename;
    }

    @RequiresPermissions("a38:*")
    @RequestMapping("/uploadFile")
    public void uploadFile (String a38Id , @RequestParam(value="mlxxFile",required = false) MultipartFile mlxxFile , HttpServletResponse resp) throws IOException {
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
        E01Z1ExcelVo e01Z1ExcelVo = new E01Z1ExcelVo();
        try {
            //解析excel 获得返回数据
            e01Z1ExcelVo = (E01Z1ExcelVo) mlxxExcelExchange.fromExcel(E01Z1ExcelVo.class,tempFile,filePath);
            //根据返回数据新增材料
            addE01z1(e01Z1ExcelVo.getJlcl(),"jlcl",a38Id);
            addE01z1(e01Z1ExcelVo.getZzcl(),"zzcl",a38Id);
            addE01z1(e01Z1ExcelVo.getJdcl(),"jdcl",a38Id);
            addE01z1(e01Z1ExcelVo.getXlxw(),"xlxw",a38Id);
            addE01z1(e01Z1ExcelVo.getZyzg(),"zyzg",a38Id);
            addE01z1(e01Z1ExcelVo.getKysp(),"kysp",a38Id);
            addE01z1(e01Z1ExcelVo.getPxcl(),"pxcl",a38Id);
            addE01z1(e01Z1ExcelVo.getZscl(),"zscl",a38Id);
            addE01z1(e01Z1ExcelVo.getDtcl(),"dtcl",a38Id);
            addE01z1(e01Z1ExcelVo.getJlicl(),"jlicl",a38Id);
            addE01z1(e01Z1ExcelVo.getCfcl(),"cfcl",a38Id);
            addE01z1(e01Z1ExcelVo.getGzcl(),"gzcl",a38Id);
            addE01z1(e01Z1ExcelVo.getRmcl(),"rmcl",a38Id);
            addE01z1(e01Z1ExcelVo.getCgcl(),"cgcl",a38Id);
            addE01z1(e01Z1ExcelVo.getDbdh(),"dbdh",a38Id);
            addE01z1(e01Z1ExcelVo.getQtcl(),"qtcl",a38Id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            file.delete();
        }
    }

    public void addE01z1(List<E01Z1Vo> e01Z1Vos,String listStr,String a38Id){
        if(e01Z1Vos.size()>0){
            //获得材料类别
            String catalogCode = getCatalogCode(listStr);//获取材料类别Code
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" catalogCode = :catalogCode ", "catalogCode", catalogCode));
            CommonOrderBy orderBy = new CommonOrderBy();
            List<ECatalogTypeInfo> entities = this.eCatalogTypeService.list(query, orderBy);
            ECatalogTypeInfo eCatalogTypeInfo = new ECatalogTypeInfo();
            if(entities.size()>0){
                eCatalogTypeInfo=entities.get(0);
            }

            try {

                for(E01Z1Vo e01Z1Vo:e01Z1Vos){
                    boolean e01z104IsEmpty= false;
                    boolean e01z114IsEmpty= false;
                    boolean e01z111IsEmpty= false;
                    if(e01Z1Vo!=null){

                        //判断必填材料是否为空
                        if(e01Z1Vo.getE01Z104()==null||e01Z1Vo.getE01Z104() == 0){
                            e01z104IsEmpty = true;
                        }
                        if(e01Z1Vo.getE01Z114()==null||e01Z1Vo.getE01Z114() == 0){
                            e01z114IsEmpty = true;
                        }
                        if(StringUtils.isEmpty(e01Z1Vo.getE01Z111())){
                            e01z111IsEmpty = true;
                        }

                        //如果必填材料全不为空，新增材料
                        if(!e01z104IsEmpty&&!e01z111IsEmpty&&!e01z114IsEmpty){
                            //拼接日期
                            String e01Z117 = "";
                            if(StringUtils.isNotEmpty(e01Z1Vo.getYear())){
                                e01Z117 = e01Z1Vo.getYear();
                                if(StringUtils.isNotEmpty(e01Z1Vo.getMonth())){
                                    e01Z117 += e01Z1Vo.getMonth();
                                    if(StringUtils.isNotEmpty(e01Z1Vo.getDay())){
                                        e01Z117 += e01Z1Vo.getDay();
                                    }
                                }
                            }
                            e01Z1Vo.setE01Z117(e01Z117);

                            int sort = this.e01Z1Service.getMaxSort(a38Id,eCatalogTypeInfo.getCatalogCode());
                            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
                            E01Z1 e01Z1 = new E01Z1();
                            BeanUtils.copyProperties(e01Z1, e01Z1Vo);
                            e01Z1.setE01Z101B(eCatalogTypeInfo.getCatalogCode());
                            e01Z1.setE01Z101A(eCatalogTypeInfo.getCatalogValue());
                            e01Z1.setECatalogTypeId(eCatalogTypeInfo.getId());
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
                        }
                    }
                }
            }catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getCatalogCode(String listStr){
        String catalogCode = "";
        if("jlcl".equals(listStr)){
            catalogCode = "010";
        }else if("zzcl".equals(listStr)){
            catalogCode = "020";
        }else if("jdcl".equals(listStr)){
            catalogCode = "030";
        }else if("xlxw".equals(listStr)){
            catalogCode = "041";
        }else if("zyzg".equals(listStr)){
            catalogCode = "042";
        }else if("kysp".equals(listStr)){
            catalogCode = "043";
        }else if("pxcl".equals(listStr)){
            catalogCode = "044";
        }else if("zscl".equals(listStr)){
            catalogCode = "050";
        }else if("dtcl".equals(listStr)){
            catalogCode = "060";
        }else if("jlicl".equals(listStr)){
            catalogCode = "070";
        }else if("cfcl".equals(listStr)){
            catalogCode = "080";
        }else if("gzcl".equals(listStr)){
            catalogCode = "091";
        }else if("rmcl".equals(listStr)){
            catalogCode = "092";
        }else if("cgcl".equals(listStr)){
            catalogCode = "093";
        }else if("dbdh".equals(listStr)){
            catalogCode = "094";
        }else if("qtcl".equals(listStr)){
            catalogCode = "100";
        }
        return catalogCode;
    }
}