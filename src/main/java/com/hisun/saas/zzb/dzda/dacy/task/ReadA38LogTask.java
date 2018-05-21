/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.task;

import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.saas.zzb.dzda.dacy.entity.*;
import com.hisun.saas.zzb.dzda.dacy.service.*;
import com.hisun.util.DateUtil;
import com.hisun.util.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Marco {854476391@qq.com}
 */
public class ReadA38LogTask {
    @Resource
    private EApplyE01Z8Service eApplyE01Z8Service;
    @Resource
    private EA38LogService ea38LogService;
    @Resource
    private EA38LogDetailService ea38LogDetailService;
    @Resource
    private EA38LogViewTimeService ea38LogViewTimeService;
    @Resource
    private ELogDetailViewTimeService eLogDetailViewTimeService;


    private Integer avgViewTime = new Integer(5);
    protected final Logger logger = Logger.getLogger(getClass());

    public void updateReadStatus(){
        if(logger.isDebugEnabled()){
            logger.debug("开始执行 updateReadStatus 》》》》》");
        }
        CommonConditionQuery query = new CommonConditionQuery();
        //已审
        query.add(CommonRestrictions.and("auditingState = :auditingState ", "auditingState","1" ));
        //  query.add(CommonRestrictions.and("readState = :readState ", "readState","0"));
        List<EApplyE01Z8> eApplyE01Z8s = eApplyE01Z8Service.list(query,null);
        if(eApplyE01Z8s !=null && !eApplyE01Z8s.isEmpty()){
            for (EApplyE01Z8 eApplyE01Z8 :eApplyE01Z8s){
                //未查阅
                if(eApplyE01Z8.getReadState() == null ||"0".equals(eApplyE01Z8.getReadState())
                        || "".equals(eApplyE01Z8.getReadState())){
                    Date accreditDate = DateUtil.parseTimesTampDate(eApplyE01Z8.getAccreditDate());
                    //授权后7天内没阅档自动结束
                    if(DateUtil.diffDate(new Date(),accreditDate) >=7){
                        //结束阅档
                        eApplyE01Z8.setAuditingState("4");
                        if(logger.isDebugEnabled()){
                            logger.debug("授权后7天内没阅档 自动结束阅档 》》》》》");
                        }
                        eApplyE01Z8Service.update(eApplyE01Z8);
                    }
                }else if ("1".equals(eApplyE01Z8.getReadState())){
                    Date readDate = DateUtil.parseTimesTampDate(eApplyE01Z8.getReadDate());
                    if(DateUtil.diffDate(new Date(),readDate) >=1){
                        eApplyE01Z8.setAuditingState("4");
                        if(logger.isDebugEnabled()){
                            logger.debug("开始阅档后1天内没阅档完 自动结束阅档 》》》》》");
                        }
                        eApplyE01Z8Service.update(eApplyE01Z8);
                    }
                }
            }
        }
        if(logger.isDebugEnabled()){
            logger.debug("结束执行 updateReadStatus 》》》》》");
        }
    }

    public void updateLogViewTime(){
        if(logger.isDebugEnabled())
            logger.debug("updateLogViewTime is star >>>>>>>>>");
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and("ydzt = :ydzt ", "ydzt",1 ));
        List<EA38Log> ea38Logs = ea38LogService.list(query,null);
        if(ea38Logs !=null && !ea38Logs.isEmpty()){
            for(EA38Log ea38Log:ea38Logs){
                if(DateUtil.diffDateTime(new Date(),ea38Log.getZzcysj())>10){
                    if(logger.isDebugEnabled())
                        logger.debug("阅档时浏览器异常关闭等特殊情况处理 >>>>>>>>>");
                    //申请阅档浏览处理
                    if(ea38Log.getApplyE01Z8()!=null ){
                        EApplyE01Z8 eApplyE01Z8 = ea38Log.getApplyE01Z8();
                        eApplyE01Z8.setEndReadDate(DateUtil.formatTimesTampDate(new Date()));
                        if(StringUtils.isNotBlank(eApplyE01Z8.getAlreadyReadTime())){
                            Integer viewTime = Integer.valueOf(eApplyE01Z8.getAlreadyReadTime());
                            viewTime = viewTime+avgViewTime;
                            eApplyE01Z8.setAlreadyReadTime(String.valueOf(viewTime));
                        }
                        eApplyE01Z8Service.update(eApplyE01Z8);
                    }
                    CommonConditionQuery query1 = new CommonConditionQuery();
                    query.add(CommonRestrictions.and("a38Log.id = :a38LogId ", "a38LogId",ea38Log.getId()));
                    query.add(CommonRestrictions.and("endTime is :endTime ", "endTime","null"));
                    List<EA38LogViewTime> ea38LogViewTimes = ea38LogViewTimeService.list(query,null);
                    Long eA38LogCYSJ = avgViewTime.longValue();
                    if(ea38LogViewTimes != null && !ea38LogViewTimes.isEmpty()){
                        EA38LogViewTime eA38LogViewTime = ea38LogViewTimes.get(0);
                        eA38LogViewTime.setEndTime(new Date());
                        eA38LogCYSJ = DateUtil.diffDateTime(new Date(),eA38LogViewTime.getStareTime());
                        eA38LogViewTime.setViewTime(String.valueOf(eA38LogCYSJ));
                        ea38LogViewTimeService.update(eA38LogViewTime);
                    }
                    if(StringUtils.isNotBlank(ea38Log.getViewTime())){
                        ea38Log.setViewTime(String.valueOf(Integer.valueOf(ea38Log.getViewTime())+eA38LogCYSJ));
                    }else {
                        ea38Log.setViewTime(String.valueOf(eA38LogCYSJ));
                    }
                    ea38Log.setYdzt(0);
                    ea38LogService.update(ea38Log);

                    CommonConditionQuery query2 = new CommonConditionQuery();
                    query.add(CommonRestrictions.and("endTime is :endTime ", "endTime","null"));
                    query.add(CommonRestrictions.and("a38LogDetails.a38Log.id = :a38LogId ", "a38LogId",ea38Log.getId()));
                    List<ELogDetailViewTime> eLogDetailViewTimes = eLogDetailViewTimeService.list(query,null);
                    if(eLogDetailViewTimes !=null && !eLogDetailViewTimes.isEmpty()){
                        ELogDetailViewTime eLogDetailViewTime = eLogDetailViewTimes.get(0);
                        eLogDetailViewTime.setEndTime(new Date());
                        Long viewTime = DateUtil.diffDateTime(new Date(),eLogDetailViewTime.getStareTime());
                        eLogDetailViewTime.setViewTime(viewTime.toString());
                        eLogDetailViewTimeService.update(eLogDetailViewTime);
                        //
                        EA38LogDetail ea38LogDetail = eLogDetailViewTime.getA38LogDetails();
                        if(StringUtils.isNotBlank(ea38LogDetail.getCysj())){
                            ea38LogDetail.setCysj(String.valueOf(Integer.valueOf(ea38LogDetail.getCysj())+viewTime));
                        }else {
                            ea38LogDetail.setCysj(viewTime.toString());
                        }
                        ea38LogDetail.setJscysj(new Date());
                        ea38LogDetailService.update(ea38LogDetail);
                    }
                }
            }
        }
        if(logger.isDebugEnabled())
            logger.debug("updateLogViewTime is end >>>>>>>>>");
    }
}
