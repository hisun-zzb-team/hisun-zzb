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
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.dacy.Constants;
import com.hisun.saas.zzb.dzda.dacy.entity.*;
import com.hisun.saas.zzb.dzda.dacy.exchange.CyjlExcelExchange;
import com.hisun.saas.zzb.dzda.dacy.service.*;
import com.hisun.saas.zzb.dzda.dacy.vo.EA38LogVo;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.util.DateUtil;
import com.hisun.util.StringUtils;
import com.hisun.util.URLEncoderUtil;
import com.hisun.util.UUIDUtil;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marco {854476391@qq.com}
 */
@Controller
@RequestMapping("/zzb/dzda/dacyjl")
public class CyjlControllelr extends BaseController {
    @Resource
    private EApplyE01Z8Service eApplyE01Z8Service;
    @Resource
    private A38Service a38Service;
    @Resource
    private E01Z1Service e01Z1Service;
    @Resource
    private EPopedomE01Z1RelationService ePopedomE01Z1RelationService;
    @Resource
    private EA38LogService eA38LogService;
    @Resource
    private EA38LogViewTimeService eA38LogViewTimeService;
    @Resource
    private EA38LogDetailService eA38LogDetailService;
    @Resource
    private ELogDetailViewTimeService eLogDetailViewTimeService;

    @Resource
    CyjlExcelExchange cyjlExcelExchange;

    @Value("${sys.upload.absolute.path}")
    private String uploadBasePath;

    @RequiresPermissions("dacyjl:*")
    @RequestMapping(value = "/list")
    public ModelAndView list(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                             @RequestParam(value = "starttime",required = false)String starttime,
                             @RequestParam(value = "endtime",required = false)String endtime,
                             @RequestParam(value = "cyrName",required = false)String cyrName,
                             @RequestParam(value = "a0101",required = false)String a0101
    ) throws UnsupportedEncodingException {
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String,Object> model = new HashMap<String,Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and("(applyE01Z8 is not null or applyE01Z8.id <>'') and 1=:applyE01Z8", "applyE01Z8", 1));
        this.buildParam(query,starttime,endtime,cyrName,a0101);
        Long total = eA38LogService.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.desc("cysj"));
        List<EA38Log> resultList = eA38LogService.list(query,orderBy,pageNum,pageSize);
        for (EA38Log ea38Log :resultList){
            EApplyE01Z8 eApplyE01Z8 = ea38Log.getApplyE01Z8();
        }
        PagerVo<EA38Log> pager = new PagerVo<EA38Log>(resultList, total.intValue(), pageNum, pageSize);
        model.put("pager",pager);
        model.put("starttime",starttime);
        model.put("a0101",a0101);
        model.put("cyrName",cyrName);
        model.put("endtime",endtime);
        return new ModelAndView("saas/zzb/dzda/ydjl/list",model);
    }
    @RequiresPermissions("dacyjl:*")
    @RequestMapping(value = "/ajax/toScxq")
    public ModelAndView toScxq(String a38LogId){
        Map<String,Object> model = Maps.newHashMap();
        try{
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and("a38Log.id = :a38LogId ", "a38LogId", a38LogId));
            Long total = eA38LogViewTimeService.count(query);
            CommonOrderBy orderBy = new CommonOrderBy();
           // orderBy.add(CommonOrder.desc("cysj"));
            List<EA38LogViewTime> resultList = eA38LogViewTimeService.list(query,orderBy);
            EA38Log ea38Log = eA38LogService.getByPK(a38LogId);
            model.put("cyrName",ea38Log.getCyrName());
            model.put("a0101",ea38Log.getA0101());
            model.put("resultList",resultList);
            model.put("total",total);
            model.put("success",true);

        }catch (Exception e){
            model.put("success",false);
            e.printStackTrace();
        }
        return new ModelAndView("saas/zzb/dzda/ydjl/scxq",model);
    }
    @RequiresPermissions("dacyjl:*")
    @RequestMapping(value = "/ajax/toNrxq")
    public ModelAndView toNrxq(String a38LogId){
        Map<String,Object> model = Maps.newHashMap();
        try{
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and("a38Log.id = :a38LogId ", "a38LogId", a38LogId));
            Long total = eA38LogDetailService.count(query);
            CommonOrderBy orderBy = new CommonOrderBy();
            // orderBy.add(CommonOrder.desc("cysj"));
            List<EA38LogDetail> resultList = eA38LogDetailService.list(query,orderBy);
            List<ELogDetailViewTime> info = resultList.get(0).getLogDetailViewTimes();
            PagerVo<EA38LogDetail> pager = new PagerVo<EA38LogDetail>(resultList, total.intValue(), 1, 100);
            EA38Log ea38Log = eA38LogService.getByPK(a38LogId);
            model.put("cyrName",ea38Log.getCyrName());
            model.put("a0101",ea38Log.getA0101());
            model.put("pager",pager);
            model.put("success",true);
        }catch (Exception e){
            model.put("success",false);
            e.printStackTrace();
        }
        return new ModelAndView("saas/zzb/dzda/ydjl/nrxq",model);
    }
    @RequiresPermissions("dacyjl:*")
    @RequestMapping(value = "/ajax/toNrxqViewTime")
    public ModelAndView toNrxqViewTime(String a38LogDetailId){
        Map<String,Object> model = Maps.newHashMap();
        try{
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and("a38LogDetails.id = :a38LogDetailId ", "a38LogDetailId", a38LogDetailId));
            Long total = eLogDetailViewTimeService.count(query);
            CommonOrderBy orderBy = new CommonOrderBy();
            // orderBy.add(CommonOrder.desc("cysj"));
            List<ELogDetailViewTime> resultList = eLogDetailViewTimeService.list(query,orderBy);
            PagerVo<ELogDetailViewTime> pager = new PagerVo<ELogDetailViewTime>(resultList, total.intValue(), 1, 100);
            model.put("pager",pager);
            model.put("success",true);
        }catch (Exception e){
            model.put("success",false);
            e.printStackTrace();
        }
        return new ModelAndView("saas/zzb/dzda/ydjl/nrxqViewTime",model);
    }

    private void buildParam(CommonConditionQuery query,String starttime,String endtime,String cyrName,String a0101){
        if(StringUtils.isNotBlank(cyrName)){
            query.add(CommonRestrictions.and("cyrName = :e01Z807Name ", "e01Z807Name", cyrName));
        }
        if(StringUtils.isNotBlank(a0101)){
            query.add(CommonRestrictions.and("a0101 like :a0101 ", "a0101", "%"+a0101+"%"));
        }
        if(StringUtils.isNotBlank(starttime)){
            query.add(CommonRestrictions.and("cysj >= :starttime ", "starttime", new DateTime(starttime).toDate()));
        }
        if(StringUtils.isNotBlank(endtime)){
            query.add(CommonRestrictions.and("cysj <= :endtime ", "endtime", new DateTime(endtime).toDate()));
        }
    }

    @RequiresPermissions("dacyjl:*")
    @RequestMapping("/download")
    public void download( HttpServletResponse resp,
                          @RequestParam(value = "starttime",required = false)String starttime,
                          @RequestParam(value = "endtime",required = false)String endtime,
                          @RequestParam(value = "cyrName",required = false)String cyrName,
                          @RequestParam(value = "a0101",required = false)String a0101){
        CommonConditionQuery query = new CommonConditionQuery();
        this.buildParam(query,starttime,endtime,cyrName,a0101);
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.desc("cysj"));
        List<EA38Log> resultList = eA38LogService.list(query,orderBy);
        EA38LogVo vo;
        List<EA38LogVo> eA38LogVos=new ArrayList<>();
        String filePath = "";
        try {
            for(EA38Log eA38Log:resultList){
                vo = new EA38LogVo();
                org.apache.commons.beanutils.BeanUtils.copyProperties(vo,eA38Log);
                vo.setCysjString(DateUtil.formatTimesTampDate(eA38Log.getCysj()));
                List<EA38LogDetail> a38LogDetails = eA38Log.getA38LogDetails();
                if(a38LogDetails.size()>0){
                    StringBuffer strB = new StringBuffer();
                    for(EA38LogDetail eA38LogDetail:a38LogDetails){
                        strB.append(eA38LogDetail.getE01Z111());
                    }
                    vo.setReadContent(strB.toString());
                }
                eA38LogVos.add(vo);
            }
            File storePathFile = new File(Constants.CYJL_STORE_PATH);
            if(!storePathFile.exists()) storePathFile.mkdirs();
            filePath = uploadBasePath+Constants.CYJL_STORE_PATH+ UUIDUtil.getUUID()+".xlsx";
            cyjlExcelExchange.toExcelByManyPojo(eA38LogVos, uploadBasePath+Constants.CYJLMB_STORE_PATH,filePath);
            resp.setContentType("multipart/form-data");
            resp.setHeader("Content-Disposition", "attachment;fileName="+ URLEncoderUtil.encode("阅档记录名册表.xlsx"));
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
