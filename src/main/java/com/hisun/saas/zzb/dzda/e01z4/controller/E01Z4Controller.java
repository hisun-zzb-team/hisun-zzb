/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.e01z4.controller;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.admin.dictionary.entity.DictionaryItem;
import com.hisun.saas.sys.admin.dictionary.entity.DictionaryType;
import com.hisun.saas.sys.admin.dictionary.service.DictionaryItemService;
import com.hisun.saas.sys.admin.dictionary.service.DictionaryTypeService;
import com.hisun.saas.sys.admin.dzda.entity.ECatalogTypeInfo;
import com.hisun.saas.sys.admin.dzda.service.ECatalogTypeService;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.log.LogOperateType;
import com.hisun.saas.sys.log.RequiresLog;
import com.hisun.saas.sys.taglib.selectTag.SelectNode;
import com.hisun.saas.sys.taglib.treeTag.TreeNode;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.e01z4.entity.E01Z4;
import com.hisun.saas.zzb.dzda.e01z4.service.E01Z4Service;
import com.hisun.saas.zzb.dzda.e01z4.vo.E01Z4Vo;
import com.hisun.util.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author zhout {605144321@qq.com}
*/
@Controller
@RequestMapping("/zzb/dzda/e01z4")
public class E01Z4Controller extends BaseController {

    @Resource
    private DictionaryTypeService dictionaryTypeService;

    @Resource
    private DictionaryItemService dictionaryItemService;

    @Resource
    private ECatalogTypeService eCatalogTypeService;
    @Resource
    private A38Service a38Service;
    @Resource
    private E01Z4Service e01Z4Service;

    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/ajax/list")
    public @ResponseBody ModelAndView mlxxList(HttpServletRequest request,
                                               @RequestParam(value="pageNum",defaultValue="1")int pageNum,
                                               @RequestParam(value="pageSize",defaultValue="10") int pageSize) throws GenericException {
        Map<String, Object> map = Maps.newHashMap();
        String a38Id = StringUtils.trimNull2Empty(request.getParameter("a38Id"));

        try{

            CommonConditionQuery query = new CommonConditionQuery();

            query.add(CommonRestrictions.and(" a38.id = :id ", "id", a38Id));

            Long total = this.e01Z4Service.count(query);
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("px"));
            List<E01Z4> entities = this.e01Z4Service.list(query, orderBy, pageNum, pageSize);
            List<E01Z4Vo> vos = new ArrayList<>();
            E01Z4Vo vo = null;
            if(entities!=null){
                for(E01Z4 entity : entities){
                    vo = new E01Z4Vo();
                    BeanUtils.copyProperties(vo,entity);
                    vos.add(vo);
                }
            }
            PagerVo<E01Z4Vo> pager = new PagerVo<E01Z4Vo>(vos, total.intValue(), pageNum, pageSize);
            map.put("pager", pager);
            map.put("a38Id",a38Id);
            map.put("total",total);
        }catch(Exception e){
            logger.error(e);
            throw new GenericException(e);
        }

        return new ModelAndView("saas/zzb/dzda/e01z4/list",map);
    }

    @RequestMapping(value = "/ajax/add")
    public ModelAndView add(String a38Id){
        Map<String, Object> map = Maps.newHashMap();
        int sort = this.e01Z4Service.getMaxSort(a38Id);
        map.put("a38Id",a38Id);
        map.put("px",sort);
        return new ModelAndView("saas/zzb/dzda/e01z4/addQQcl",map);
    }

    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加欠缺材料:${vo.e01Z401}")
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> save(E01Z4Vo vo,HttpServletRequest request) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        String a38Id = StringUtils.trimNull2Empty(request.getParameter("a38Id"));
        try {
            int sort = this.e01Z4Service.getMaxSort(a38Id);
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            E01Z4 e01Z4 = new E01Z4();
            BeanUtils.copyProperties(e01Z4, vo);
            e01Z4.setECatalogTypeId(vo.geteCatalogTypeId());
            if(StringUtils.isNotBlank(a38Id)){
                e01Z4.setA38(this.a38Service.getByPK(a38Id));
            }
            EntityWrapper.wrapperSaveBaseProperties(e01Z4,userLoginDetails);

            int newSort = e01Z4.getPx();
            if(newSort<sort){
                e01Z4Service.updateSortBeforSave(e01Z4,sort);
            }

            e01Z4Service.save(e01Z4);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            throw new GenericException(e);
        }

        return map;
    }

    @RequestMapping(value = "/ajax/edit")
    public @ResponseBody ModelAndView edit(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        String id = StringUtils.trimNull2Empty(request.getParameter("id"));
        E01Z4 e01Z4 = e01Z4Service.getByPK(id);
        String a38Id = StringUtils.trimNull2Empty(request.getParameter("a38Id"));
        map.put("a38Id",a38Id);
        map.put("vo",e01Z4);
        map.put("eCatalogTypeId",e01Z4.getECatalogTypeId());
        return new ModelAndView("saas/zzb/dzda/e01z4/editQQcl",map);
    }

    @RequiresLog(operateType = LogOperateType.UPDATE,description = "修改材料目录:${vo.e01Z411}")
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/update")
    public @ResponseBody Map<String, Object> update(E01Z4Vo vo,HttpServletRequest request) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        String id = StringUtils.trimNull2Empty(request.getParameter("id"));
        try {

            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            E01Z4 e01Z4 = this.e01Z4Service.getByPK(id);
            int oldSort = e01Z4.getPx();

            BeanUtils.copyProperties(e01Z4, vo);
            e01Z4.setA38(this.a38Service.getByPK(e01Z4.getA38().getId()));
            EntityWrapper.wrapperUpdateBaseProperties(e01Z4,userLoginDetails);
            this.e01Z4Service.updateE01Z4(e01Z4, oldSort);
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
            this.e01Z4Service.deleteByPK(id);
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
            TreeNode childTreeNode=null;
            for (ECatalogTypeInfo eCatalogTypeInfo : eCatalogTypeInfos) {
                childTreeNode = new TreeNode();
                childTreeNode.setId(eCatalogTypeInfo.getId());
                childTreeNode.setName(eCatalogTypeInfo.getCatalogValue());
                childTreeNode.setKey(eCatalogTypeInfo.getCatalogCode());
                if(eCatalogTypeInfo.getParent()==null){
                    childTreeNode.setpId("");
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

    @RequestMapping(value = "/select")
    public @ResponseBody Map<String,Object> getSelectNodes(String typeCode,String tenant2ResourceId,String privilegeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        String typeId = "";
        try {
            CommonConditionQuery dictionaryTypeQuery = new CommonConditionQuery();
            dictionaryTypeQuery.add(CommonRestrictions.and("(code like :searchName)", "searchName", "%" + "SAN_DAXQCL" + "%"));
            CommonOrderBy dictionaryTypeOrderBy = new CommonOrderBy();
            List<DictionaryType> queryList = this.dictionaryTypeService.list(dictionaryTypeQuery, dictionaryTypeOrderBy);
            if(queryList!=null && queryList.size()>0){
                typeId=queryList.get(0).getId();
            }

            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" dictionaryType.id=:typeId ", "typeId", typeId));
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("sort"));
            orderBy.add(CommonOrder.asc("code"));
            List<DictionaryItem> dictionaryItems = this.dictionaryItemService.list(query, orderBy);
            List<SelectNode> nodes = new ArrayList<>();
            SelectNode node=null;
            for (DictionaryItem dictionaryItem : dictionaryItems) {
                node = new SelectNode();
                node.setOptionKey(dictionaryItem.getCode());
                node.setOptionValue(dictionaryItem.getName());
                nodes.add(node);
            }
            map.put("success", true);
            map.put("data", nodes);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        }
        return map;
    }

}