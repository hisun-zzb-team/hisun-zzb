/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.controller;

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
import com.hisun.saas.sys.log.LogOperateType;
import com.hisun.saas.sys.log.RequiresLog;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.a38.vo.A38Vo;
import com.hisun.saas.zzb.dzda.dacy.Constants;
import com.hisun.saas.zzb.dzda.dacy.entity.*;
import com.hisun.saas.zzb.dzda.dacy.exchange.CyjlExcelExchange;
import com.hisun.saas.zzb.dzda.dacy.service.*;
import com.hisun.saas.zzb.dzda.dacy.vo.EApplyE01Z8Vo;
import com.hisun.saas.zzb.dzda.dacy.vo.ECysqVo;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.util.DateUtil;
import com.hisun.util.StringUtils;
import com.hisun.util.URLEncoderUtil;
import com.hisun.util.UUIDUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Marco {854476391@qq.com}
 */
@Controller
@RequestMapping("/zzb/dzda/cyshouquan")
public class CyshouquanController extends BaseController {
    @Resource
    private EApplyE01Z8Service eApplyE01Z8Service;
    @Resource
    private A38Service a38Service;
    @Resource
    private E01Z1Service e01Z1Service;
    @Resource
    private EPopedomE01Z1RelationService ePopedomE01Z1RelationService;
    @Resource
    private ECysqService eCysqService;

    @Resource
    CyjlExcelExchange cyjlExcelExchange;

    @Value("${sys.upload.absolute.path}")
    private String uploadBasePath;

    @Resource
    private EA38LogService eA38LogService;

    @Resource
    private EA38LogDetailService eA38LogDetailService;

    @RequiresPermissions("cyshouquan:*")
    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                             @RequestParam(value = "userName",required = false)String userName,
                             @RequestParam(value = "readContent",required = false)String readContent,
                             @RequestParam(value = "e01Z807Name",required = false)String e01Z807Name,
                             @RequestParam(value = "auditingState",required = false,defaultValue = "0")String auditingState,
                             @RequestParam(value = "starttime",required = false)String starttime,
                             @RequestParam(value = "endtime",required = false)String endtime
    ) throws UnsupportedEncodingException {
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String,Object> model = new HashMap<String,Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        if(StringUtils.isNotBlank(userName)){
            query.add(CommonRestrictions.and("a0101 like :a0101 ", "a0101","%"+ userName+"%"));
        }
        if(StringUtils.isNotBlank(readContent)){
            query.add(CommonRestrictions.and("readContent like :readContent ", "readContent", "%"+readContent+"%"));
        }
        if(StringUtils.isNotBlank(e01Z807Name)){
            query.add(CommonRestrictions.and("e01Z807Name = :e01Z807Name ", "e01Z807Name", e01Z807Name));
        }
        //-1表示查询全部
        if(StringUtils.isNotBlank(auditingState) && !"-1".equals(auditingState)){
            query.add(CommonRestrictions.and("auditingState = :auditingState ", "auditingState", auditingState));
        }
        if(StringUtils.isNotBlank(starttime)){
            query.add(CommonRestrictions.and("createDate >= :starttime ", "starttime", new DateTime(starttime).toDate()));
        }
        if(StringUtils.isNotBlank(endtime)){
            query.add(CommonRestrictions.and("createDate <= :endtime ", "endtime", new DateTime(endtime).toDate()));
        }
        Long total = eApplyE01Z8Service.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.asc("auditingState"));
        orderBy.add(CommonOrder.asc("createDate"));
        List<EApplyE01Z8> resultList = eApplyE01Z8Service.list(query,orderBy,pageNum,pageSize);
        PagerVo<EApplyE01Z8> pager = new PagerVo<EApplyE01Z8>(resultList, total.intValue(), pageNum, pageSize);
        model.put("pager",pager);
        model.put("userName",userName);
        model.put("readContent",readContent);
        model.put("e01Z807Name",e01Z807Name);
        model.put("auditingState",auditingState);
        model.put("starttime",starttime);
        model.put("endtime",endtime);
        return new ModelAndView("saas/zzb/dzda/dasq/list",model);
    }
    @RequestMapping(value = "/toShouquan")
    public ModelAndView toShouquan(String id,String zcsqbs){
        Map<String,Object> model = Maps.newHashMap();
        try{
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            EApplyE01Z8 entity  = eApplyE01Z8Service.getByPK(id);
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and("sjzt = :sjzt","sjzt","1"));
            query.add(CommonRestrictions.and("a0101 = :a0101","a0101",entity.getA0101()));
            List<A38> a38s =  a38Service.list(query,null);
            String a38Id = "";
            if(a38s.size()==1){
                a38Id = a38s.get(0).getId();
            }
            model.put("sqr",details.getRealname());
            model.put("zcsqbs",zcsqbs);
            model.put("entity",entity);
            model.put("a38s",a38s);
            model.put("a38Id",a38Id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("saas/zzb/dzda/dasq/shouquan",model);
    }

    @RequiresPermissions("cyshouquan:*")
    @RequiresLog(operateType = LogOperateType.UPDATE,description = "收回${applyUserName}申请查阅${a0101}的档案权限")
    @RequestMapping(value = "/shouhuiQx/{id}")
    public @ResponseBody  Map<String,Object> shouhuiQx(@PathVariable("id")String id,String a0101,String applyUserName){
        Map<String,Object> model = Maps.newHashMap();
        try{
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            EApplyE01Z8 entity = eApplyE01Z8Service.getByPK(id);
            //收回权限
           // entity.setAuditingState("3");
            entity.setReadState("2");
            EntityWrapper.wrapperUpdateBaseProperties(entity,details);
            eApplyE01Z8Service.update(entity);
            model.put("success",true);
        }catch (Exception e){
            model.put("success",false);
            model.put("message","授权失败");
            e.printStackTrace();
        }
        return model;
    }
    @RequiresPermissions("cyshouquan:*")
    @RequiresLog(operateType = LogOperateType.UPDATE,description = "${vo.sqztContent} ${vo.cysqr}申请查阅:${vo.cydadxmc}的档案")
    @RequestMapping(value = "/shouquan")
    public @ResponseBody Map<String,Object> shouquan(ECysqVo vo){
        Map<java.lang.String, java.lang.Object> model = Maps.newHashMap();
        try{
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            EApplyE01Z8 entity =  eApplyE01Z8Service.getByPK(vo.getE01z8Id());
            entity.setSqdwpzld(vo.getSqr());
            entity.setAuditingState(vo.getSqzt());
            entity.setA38(a38Service.getByPK(vo.getA38Id()));
            entity.setIsPrint(vo.getSfyxdy());
            entity.setIsDownload(vo.getSfyxxz());
            entity.setPopedomStuffType(vo.getSqclfw());//是否部分授权
            entity.setReadState("0");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            entity.setAccreditDate(sdf.format(new Date()));
            eApplyE01Z8Service.update(entity);
            //授权表
            ECysq eCysq = new ECysq();
            org.springframework.beans.BeanUtils.copyProperties(vo,eCysq);
            eCysq.setApplyE01Z8(entity);
            EntityWrapper.wrapperSaveBaseProperties(eCysq,details);
            String pk = eCysqService.save(eCysq);
            //部分授权
            ECysq saveEcysq = eCysqService.getByPK(pk);
            String e01z1IdContent = vo.getSqcymlIds();
            String[] e01z1Ids = e01z1IdContent.split(",");
            if("1".equals(vo.getSqclfw())){
                for (String id : e01z1Ids){
                    if(id.equals("")) continue;
                    EPopedomE01Z1Relation ep = new EPopedomE01Z1Relation();
                    E01Z1 e01Z1 = e01Z1Service.getByPK(id);
                    ep.seteCysq(saveEcysq);
                    ep.setE01z1Id(id);
                    ePopedomE01Z1RelationService.save(ep);
                }
            }
            model.put("success",true);
        }catch (Exception e){
            model.put("success",false);
            e.printStackTrace();
        }
        return model;
    }
    @RequestMapping("/view")
    @RequiresPermissions("cyshouquan:*")
    public
    ModelAndView view(String id) throws GenericException {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and("applyE01Z8.id = :id ", "id", id));
            List<ECysq> list = eCysqService.list(query,null);
            ECysqVo vo = new ECysqVo();
            BeanUtils.copyProperties(vo,list.get(0));
            EApplyE01Z8Vo eApplyE01Z8Vo = new EApplyE01Z8Vo();
            BeanUtils.copyProperties(eApplyE01Z8Vo,list.get(0).getApplyE01Z8());
            eApplyE01Z8Vo.setSqsj(DateUtil.formatTimesTampDate(list.get(0).getApplyE01Z8().getCreateDate()));
            vo.seteApplyE01Z8Vo(eApplyE01Z8Vo);
            returnMap.put("vo",vo);
            returnMap.put("code", 1);
        } catch (Exception e) {
            returnMap.put("code", 0);
            returnMap.put("message", "删除失败");
            logger.error(e, e);
            throw new GenericException(e.getMessage());
        }
        return new ModelAndView("saas/zzb/dzda/dasq/view",returnMap);
    }


    @RequiresPermissions("cyshouquan:*")
    @RequestMapping(value = "/getA0101")
    public @ResponseBody Map<String,Object> getA0101(@RequestParam(value = "a0101",required = true) String a0101){
        Map<String,Object> model = Maps.newHashMap();
        try{
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and("sjzt = :sjzt","sjzt","1"));
            query.add(CommonRestrictions.and("a0101 = :a0101","a0101",a0101));
            List<A38> a38 =  a38Service.list(query,null);
            PagerVo<A38> pager = new PagerVo<A38>(a38, a38.size(), 1, 20);
            model.put("pager",pager);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return model;
    }

    private void buildParam(CommonConditionQuery query ,String userName,String readContent,String e01Z807Name,String auditingState){
        if(StringUtils.isNotBlank(userName)){
            query.add(CommonRestrictions.and("a0101 like :a0101 ", "a0101","%"+ userName+"%"));
        }
        if(StringUtils.isNotBlank(readContent)){
            query.add(CommonRestrictions.and("readContent like :readContent ", "readContent", "%"+readContent+"%"));
        }
        if(StringUtils.isNotBlank(e01Z807Name)){
            query.add(CommonRestrictions.and("e01Z807Name = :e01Z807Name ", "e01Z807Name", e01Z807Name));
        }
        if(StringUtils.isNotBlank(auditingState)){
            query.add(CommonRestrictions.and("auditingState = :auditingState ", "auditingState", auditingState));
        }
    }

    @RequiresPermissions("cyshouquan:*")
    @RequestMapping("/download")
    public void download( HttpServletResponse resp,  @RequestParam(value = "userName",required = false)String userName,
                          @RequestParam(value = "readContent",required = false)String readContent,
                          @RequestParam(value = "e01Z807Name",required = false)String e01Z807Name,
                          @RequestParam(value = "auditingState",required = false)String auditingState){
        CommonConditionQuery query = new CommonConditionQuery();
        this.buildParam(query,userName,readContent,e01Z807Name,auditingState);
        CommonOrderBy orderBy = new CommonOrderBy();
        List<EApplyE01Z8> resultList = eApplyE01Z8Service.list(query,orderBy);
        EApplyE01Z8Vo vo;
        List<EApplyE01Z8Vo> eA38LogVos=new ArrayList<>();
        String filePath = "";
        try {
            for(EApplyE01Z8 eApplyE01Z8:resultList){
                vo = new EApplyE01Z8Vo();
                //query.add(CommonRestrictions.and("applyE01Z8.id = :eApplyE01Z8Id ", "eApplyE01Z8Id", eApplyE01Z8.getId()));
                List<EA38Log> ea38Logs = eApplyE01Z8.getA38Logs();
                        //eA38LogService.list(query,null);
                org.apache.commons.beanutils.BeanUtils.copyProperties(vo,eApplyE01Z8);
                vo.setContent("");
                vo.setSqsj(DateUtil.formatTimesTampDate(eApplyE01Z8.getCreateDate()));
                if(ea38Logs!=null && !ea38Logs.isEmpty()){
                    List<EA38LogDetail> a38LogDetails = ea38Logs.get(0).getA38LogDetails();
                    if(a38LogDetails.size()>0){
                        StringBuffer strB = new StringBuffer();
                        for(EA38LogDetail eA38LogDetail:a38LogDetails){
                            strB.append(eA38LogDetail.getE01Z111());
                        }
                        vo.setContent(strB.toString());
                    }
                }
                eA38LogVos.add(vo);
            }
            File storePathFile = new File(Constants.CYGL_STORE_PATH);
            if(!storePathFile.exists()) storePathFile.mkdirs();
            filePath = uploadBasePath+Constants.CYGL_STORE_PATH+ UUIDUtil.getUUID()+".xlsx";
            cyjlExcelExchange.toExcelByManyPojo(eA38LogVos, uploadBasePath+Constants.CYGLMB_STORE_PATH,filePath);
            resp.setContentType("multipart/form-data");
            resp.setHeader("Content-Disposition", "attachment;fileName="+ URLEncoderUtil.encode("授权查阅记录表.xlsx"));
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
            FileUtils.deleteQuietly(new File(filePath));
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
}
