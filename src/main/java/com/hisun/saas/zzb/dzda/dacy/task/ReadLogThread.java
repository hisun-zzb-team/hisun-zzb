/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.task;

import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.saas.zzb.dzda.dacy.entity.EA38Log;
import com.hisun.saas.zzb.dzda.dacy.entity.EA38LogDetail;
import com.hisun.saas.zzb.dzda.dacy.entity.EA38LogViewTime;
import com.hisun.saas.zzb.dzda.dacy.entity.EApplyE01Z8;
import com.hisun.saas.zzb.dzda.dacy.service.EA38LogDetailService;
import com.hisun.saas.zzb.dzda.dacy.service.EA38LogService;
import com.hisun.saas.zzb.dzda.dacy.service.EA38LogViewTimeService;
import com.hisun.saas.zzb.dzda.dacy.service.EApplyE01Z8Service;
import com.hisun.util.DateUtil;
import com.hisun.util.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author Marco {854476391@qq.com}
 */
public class ReadLogThread implements InitializingBean,Runnable {
    @Autowired
    private EA38LogService ea38LogService;
    @Autowired
    private EApplyE01Z8Service eApplyE01Z8Service;
    @Autowired
    private EA38LogDetailService ea38LogDetailService;
    @Autowired
    private EA38LogViewTimeService ea38LogViewTimeService;

    private final Logger logger = Logger.getLogger(getClass());

    @Override
    public void afterPropertiesSet() throws Exception {
        ReadLogThread myTread =  new ReadLogThread();
        new Thread(myTread).start();
    }

    @Override
    public void run() {
        if(logger.isDebugEnabled())
            logger.debug("My readLogThread is star >>>>>>>>>");
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and("ydzt = :ydzt ", "ydzt","1" ));
        List<EA38Log> ea38Logs = ea38LogService.list(query,null);
        if(ea38Logs !=null && !ea38Logs.isEmpty()){
            for(EA38Log ea38Log:ea38Logs){
                if(DateUtil.diffDateTime(new Date(),ea38Log.getZzcysj())>10){
                    //申请阅档浏览处理
                    if(ea38Log.getApplyE01Z8()!=null ){
                        EApplyE01Z8 eApplyE01Z8 = ea38Log.getApplyE01Z8();
                        eApplyE01Z8.setEndReadDate(DateUtil.formatTimesTampDate(new Date()));
                        if(StringUtils.isNotBlank(eApplyE01Z8.getAlreadyReadTime())){
                            Integer viewTime = Integer.valueOf(eApplyE01Z8.getAlreadyReadTime());
                            viewTime = viewTime+5;
                            eApplyE01Z8.setAlreadyReadTime(String.valueOf(viewTime));
                        }
                        eApplyE01Z8Service.update(eApplyE01Z8);
                    }
                    CommonConditionQuery query1 = new CommonConditionQuery();
                    query.add(CommonRestrictions.and("a38Log.id = :a38LogId ", "a38LogId",ea38Log.getId()));
                    query.add(CommonRestrictions.and("endTime = :endTime ", "endTime",null));
                    List<EA38LogViewTime> ea38LogViewTimes = ea38LogViewTimeService.list(query,null);
                    if(ea38LogViewTimes != null && !ea38LogViewTimes.isEmpty()){
                        EA38LogViewTime eA38LogViewTime = ea38LogViewTimes.get(0);
                        eA38LogViewTime.setEndTime(new Date());
                        eA38LogViewTime.setViewTime(String.valueOf(DateUtil.diffDateTime(new Date(),eA38LogViewTime.getStareTime())));
                        ea38LogViewTimeService.update(eA38LogViewTime);
                    }

                    ea38Log.setYdzt(0);
                    ea38LogService.update(ea38Log);
                }

            }
        }

    }
}
