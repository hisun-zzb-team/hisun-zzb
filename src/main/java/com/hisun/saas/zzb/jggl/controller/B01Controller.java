/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.jggl.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.b.entity.B01;
import com.hisun.saas.zzb.b.service.B01Service;
import com.hisun.saas.zzb.b.vo.B01QueryModel;
import com.hisun.saas.zzb.b.vo.B01Vo;
import com.hisun.saas.zzb.jggl.Constants;
import com.hisun.saas.zzb.jggl.exchange.JgglExcelExchange;
import com.hisun.util.StringUtils;
import com.hisun.util.URLEncoderUtil;
import com.hisun.util.UUIDUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rocky {rockwithyou@126.com}
 * @author Marco
 */
@Controller
@RequestMapping("/zzb/jggl/b01")
public class B01Controller extends BaseController {
    @Resource
    private B01Service b01Service;

    @Value("${sys.upload.absolute.path}")
    private String uploadBasePath;

    @Resource
    private JgglExcelExchange jgglExcelExchange;
    @RequestMapping(value = "/index")
    public
    @ResponseBody
    ModelAndView index(HttpServletRequest request) {
        Map<String, Object> map = Maps.newHashMap();
        return new ModelAndView("saas/zzb/jggl/b01/index", map);
    }

    @RequestMapping(value = "/ajax/list")
    public ModelAndView getList(String b01Id, String b0101,
                                @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                B01QueryModel queryModel
    ) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            List<B01> resultList = Lists.newArrayList();
            CommonOrderBy orderBy = new CommonOrderBy();
            Long total = 0L;
            B01 b01 = new B01();
            b0101 = buildParam(b01Id, b0101, queryModel, query);
            total = b01Service.count(query);
            orderBy.add(CommonOrder.asc("bCxbm"));
            resultList = b01Service.list(query, orderBy, pageNum, pageSize);
            List<B01Vo> b01Vos = new ArrayList<B01Vo>();
            B01Vo vo = new B01Vo();
            for (B01 entity : resultList) {
                vo = new B01Vo();
                BeanUtils.copyProperties(entity, vo);
                b01Vos.add(vo);
            }
            PagerVo<B01Vo> pager = new PagerVo<B01Vo>(b01Vos, total.intValue(), pageNum, pageSize);
            map.put("queryModel",queryModel);
            map.put("pager", pager);
            map.put("b01Id", b01Id);
            map.put("b0101", b0101);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/jggl/b01/rightList", map);

    }

    private String buildParam(String b01Id, String b0101, B01QueryModel queryModel, CommonConditionQuery query) {
        B01 b01;
        if (StringUtils.isBlank(queryModel.getB0101Query()) && StringUtils.isBlank(queryModel.getParentIdQuery()) && StringUtils.isBlank(queryModel.getB0127Query()) &&
                StringUtils.isBlank(queryModel.getbGllbBQuery())) {
            if(StringUtils.isNotBlank(b01Id)){
                b01 = this.b01Service.getByPK(b01Id);
                b0101 = b01.getB0101();
                query.add(CommonRestrictions.and(" bCxbm like :bCxbm ", "bCxbm", b01.getbCxbm() + "%"));
            }

        } else {
            if (StringUtils.isNotBlank(queryModel.getB0101Query())) {
                query.add(CommonRestrictions.and(" b0101 like :b0101 ", "b0101", "%" + queryModel.getB0101Query() + "%"));
            }
            if (StringUtils.isNotBlank(queryModel.getParentIdQuery())) {
                b01 = this.b01Service.getByPK(queryModel.getParentIdQuery());
                query.add(CommonRestrictions.and(" bCxbm like :bCxbm ", "bCxbm", b01.getbCxbm() + "%"));
            }
            if (StringUtils.isNotBlank(queryModel.getB0127Query())) {
                query.add(CommonRestrictions.and(" b0127 = :b0127 ", "b0127", queryModel.getB0127Query()));
            }
            if (StringUtils.isNotBlank(queryModel.getbGllbBQuery())) {
                query.add(CommonRestrictions.and(" bGllbB = :bGllbB ", "bGllbB", queryModel.getbGllbBQuery()));
            }
        }
        return b0101;
    }

    @RequestMapping(value = "/updateOrSave")
    @ResponseBody
    public Map<String, Object> updateOrSave(B01Vo vo) {
        Map<String, Object> map = Maps.newHashMap();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        B01 entity = new B01();
        B01 oldB01 = new B01();
        String bSjlx = vo.getbSjlx();
        try {
            if (StringUtils.isNotBlank(vo.getB0100())) {
                entity = b01Service.getByPK(vo.getB0100());
                String oldPid = entity.getB0100();
                BeanUtils.copyProperties(entity, oldB01);
                BeanUtils.copyProperties(vo, entity);
                if("001".equals(entity.getbCxbm())){
                    EntityWrapper.wrapperUpdateBaseProperties(entity,details);
                    b01Service.update(entity);
                    map.put("code", 1);
                    return  map;
                }
                entity.setParentB01(b01Service.getByPK(vo.getParentId()));
                this.b01Service.updateB01(entity, oldB01, oldPid);
            } else {
                //第一個節點
                if (!StringUtils.isNotBlank(vo.getB01Id())) {
                    BeanUtils.copyProperties(vo, entity);
                    entity.setbCxbm("001");
                    String b01Id = b01Service.save(entity);
                    map.put("oneNodeId",b01Id);
                    map.put("oneNodeName",vo.getB0101());
                    map.put("code", 1);
                    return map;
                }
                String currentId = "";
                currentId = b01Service.saveB01(vo);
                map.put("currentId", currentId);
            }
            map.put("code", 1);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("code", 0);
        }
        return map;
    }

    @RequestMapping(value = "/ajax/manage")
    public ModelAndView mangePage(String bSjlx, String b01Id, String currentId, String isAdd, String isAddOne) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            if(StringUtils.isNotBlank(currentId)){
                B01 b01 = b01Service.getByPK(currentId);
                bSjlx = b01.getbSjlx();
            }
            map.put("bSjlx", bSjlx);
            map.put("b01Id", b01Id);
            map.put("currentId", currentId);
            map.put("isAdd", isAdd);
            map.put("isAddOne", isAddOne);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/jggl/b01/manage", map);
    }

    @RequestMapping(value = "/ajax/jbxx")
    public ModelAndView jbxx(String b01Id, String b0101, String bSjlx, String currentId, String isAdd, String isAddOne) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            B01Vo vo = new B01Vo();
            vo.setbSjlx(bSjlx);
            vo.setB01Id(b01Id);
            if (StringUtils.isNotBlank(currentId)) {
                B01 entity = b01Service.getByPK(currentId);
                BeanUtils.copyProperties(entity, vo);
                if(entity.getParentB01()!=null && StringUtils.isNotBlank(entity.getParentB01().getB0100())){
                    vo.setParentId(entity.getParentB01().getB0100());
                    vo.setParentName(entity.getParentB01().getB0101());
                }
            } else {
                vo.setParentId(b01Id);
                if (StringUtils.isNotBlank(b01Id))
                    vo.setParentName(b01Service.getByPK(b01Id).getB0101());
            }
            map.put("vo", vo);
            Integer sort = 1;
            //第一个节点
            if (StringUtils.isNotBlank(b01Id))
                sort = b01Service.getMaxSort(b01Id);
            map.put("isAdd", isAdd);
            map.put("isAddOne", isAddOne);
            map.put("sort", sort);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e, e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/jggl/b01/jbxx", map);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> delete(String id) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            b01Service.deleteByPK(id);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            map.put("message", "删除失败");
            map.put("success", false);
        }
        return map;
    }

    @RequestMapping(value = "/updatePx")
    @ResponseBody
    public Map<String, Object> updatePx(String parentId, String b0100) {
        Map<String, Object> map = Maps.newHashMap();
        try {
            if (StringUtils.isNotBlank(b0100)) {
                CommonConditionQuery query = new CommonConditionQuery();
                B01 b01 = this.b01Service.getByPK(b0100);
                query.add(CommonRestrictions.and(" bCxbm like :bCxbm ", "bCxbm", b01.getbCxbm() + "%"));
                List<B01> resultList = b01Service.list(query, null);
                for (B01 b : resultList) {
                    if (parentId.equals(b.getB0100())) {
                        map.put("checkValue",false);
                        map.put("oldParentId",b01.getParentB01().getB0100());
                        map.put("oldParentName",b01.getParentB01().getB0101());
                        map.put("success", true);
                        map.put("message", "不能选择本身及其以下节点");
                        return map;
                    }
                }
            }
            Integer sort = b01Service.getMaxSort(parentId);
            map.put("checkValue",true);
            map.put("px", sort);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            map.put("message", "加载失败");
            map.put("success", false);
        }
        return map;
    }
    @RequestMapping(value = "/ajax/toGjcx")
    public ModelAndView toGjcx() {
        Map<String, Object> map = Maps.newHashMap();
        try {

        } catch (Exception e) {
            logger.error(e, e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/jggl/b01/gjcx", map);
    }

    @RequestMapping(value = "/getB01List")
    @ResponseBody
    public Map<String, Object> getB01List() {
        Map<String, Object> map = Maps.newHashMap();
        try {
            boolean exist = false;
            List<B01> list = b01Service.list();
            if(list !=null && !list.isEmpty()){
                exist = true;
            }
            map.put("exist",exist);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e);
            map.put("message", "查询失败");
            map.put("success", false);
        }
        return map;
    }

    @RequestMapping(value = "/download")
    public void download(HttpServletResponse res,String b01Id,B01QueryModel queryModel){
        CommonConditionQuery query = new CommonConditionQuery();
        List<B01> resultList = Lists.newArrayList();
        CommonOrderBy orderBy = new CommonOrderBy();
        String b0101="";
        this.buildParam(b01Id,b0101,queryModel,query);
        resultList = b01Service.list(query,null);
        List<B01Vo> b01Vos = new ArrayList<B01Vo>();
        B01Vo vo = new B01Vo();
        for (B01 entity : resultList) {
            vo = new B01Vo();
            BeanUtils.copyProperties(entity, vo);
            b01Vos.add(vo);
        }
        String filePath="";
        try{
            File storePathFile = new File(Constants.JBXX_STORE_PATH);
            if(!storePathFile.exists()) storePathFile.mkdirs();
            filePath = uploadBasePath+Constants.JBXX_STORE_PATH+ UUIDUtil.getUUID()+".xlsx";
            jgglExcelExchange.toExcelByManyPojo(b01Vos, uploadBasePath+Constants.JBXXMB_STORE_PATH,filePath);
            res.setContentType("multipart/form-data");
            res.setHeader("Content-Disposition", "attachment;fileName="+ URLEncoderUtil.encode("机构管理信息表.xlsx"));
            OutputStream output = res.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            output.flush();
            output.close();
            fileInputStream.close();
            FileUtils.deleteQuietly(new File(filePath));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
