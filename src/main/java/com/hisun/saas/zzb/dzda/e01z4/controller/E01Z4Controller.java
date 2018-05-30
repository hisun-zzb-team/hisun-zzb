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
import com.hisun.saas.zzb.dzda.a38.controller.A38Controller;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.a38.vo.A38Vo;
import com.hisun.saas.zzb.dzda.e01z4.Constants;
import com.hisun.saas.zzb.dzda.e01z4.entity.E01Z4;
import com.hisun.saas.zzb.dzda.e01z4.exchange.QQclExcelExchange;
import com.hisun.saas.zzb.dzda.e01z4.service.E01Z4Service;
import com.hisun.saas.zzb.dzda.e01z4.vo.E01Z4Vo;
import com.hisun.saas.zzb.dzda.util.DaUtils;
import com.hisun.util.StringUtils;
import com.hisun.util.URLEncoderUtil;
import com.hisun.util.UUIDUtil;
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

    @Resource
    QQclExcelExchange qqclExcelExchange;

    @Value("${sys.upload.absolute.path}")
    private String uploadBasePath;

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

    @RequiresPermissions("a38:*")
    @RequestMapping("/download/{a38Id}")
    public void download(@PathVariable("a38Id") String a38Id, HttpServletResponse resp){
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" a38_id = :a38Id ", "a38Id", a38Id));
        List<E01Z4> resultList = e01Z4Service.list(query,null);
        E01Z4Vo vo;
        A38Vo a38Vo = new A38Vo();
        List<E01Z4Vo> e01Z4Vos=new ArrayList<>();
        String filePath="";
        try {

            for(E01Z4 e01Z4:resultList){
                vo = new E01Z4Vo();
                BeanUtils.copyProperties(vo,e01Z4);
                e01Z4Vos.add(vo);
            }
            if(resultList!=null && resultList.size()>0){
                E01Z4 a52=resultList.get(0);
                a38Vo.setA0101(a52.getA38().getA0101());
                a38Vo.setA0157(a52.getA38().getA0157());
                a38Vo.setA0111A(a52.getA38().getA0111A());
                a38Vo.setE01Z4Vos(e01Z4Vos);
            }

            File storePathFile = new File(Constants.QQCL_STORE_PATH);
            if(!storePathFile.exists()){
                storePathFile.mkdirs();
            }

            filePath = uploadBasePath+Constants.QQCL_STORE_PATH+ UUIDUtil.getUUID()+".xlsx";
            qqclExcelExchange.toExcel(a38Vo, uploadBasePath+Constants.QQCLMB_STORE_PATH,filePath);
            resp.setContentType("multipart/form-data");
            resp.setHeader("Content-Disposition", "attachment;fileName="+ URLEncoderUtil.encode("干部档案个人欠缺材料情况表.xlsx"));
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
    public void uploadFile (String a38Id , @RequestParam(value="qqclFile",required = false) MultipartFile qqclFile , HttpServletResponse resp) throws IOException {
        String filePath = "";
        File storePathFile = new File(Constants.QQCL_STORE_PATH);
        if(!storePathFile.exists()) storePathFile.mkdirs();
        filePath = uploadBasePath+Constants.QQCL_STORE_PATH+ UUIDUtil.getUUID()+".xlsx";
        File file = new File(filePath);
        InputStream inputStream = null;
        OutputStream output = null;
        try {
            inputStream = qqclFile.getInputStream();
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
        String tempFile = uploadBasePath+Constants.QQCLMB_STORE_PATH;
        A38Vo a38Vo = new A38Vo();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        try {
            a38Vo = (A38Vo) qqclExcelExchange.fromExcel(A38Vo.class,tempFile,filePath);
            if(a38Vo!=null&&a38Vo.getE01Z4Vos().size()>0){
                List<E01Z4Vo> e01Z4Vos = a38Vo.getE01Z4Vos();
                for(int i=0;i<10;i++){
                    Integer oldPxInteger=e01Z4Service.getMaxSort(a38Id);
                    boolean flag = false;//判断是否存在非法数据
                    E01Z4 e01Z4 = new E01Z4();
                    E01Z4Vo e01Z4Vo = e01Z4Vos.get(i);
                    if(StringUtils.isEmpty(e01Z4Vo.getE01Z401())){
                        flag = true;
                    }

                    if(flag&&StringUtils.isEmpty(e01Z4Vo.getFileTime())&&StringUtils.isEmpty(e01Z4Vo.getRemark())){
                        break;
                    }

                    if(DaUtils.isNotDate(e01Z4Vo.getFileTime())){
                        flag = true;
                    }

                    if(flag){
                        continue;
                    }

                    BeanUtils.copyProperties(e01Z4,e01Z4Vo);
                    A38 a38 = this.a38Service.getByPK(a38Id);
                    e01Z4.setA38(a38);
                    e01Z4.setPx(oldPxInteger);
                    EntityWrapper.wrapperSaveBaseProperties(e01Z4,details);
                    e01Z4Service.save(e01Z4);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            file.delete();
        }
    }

}