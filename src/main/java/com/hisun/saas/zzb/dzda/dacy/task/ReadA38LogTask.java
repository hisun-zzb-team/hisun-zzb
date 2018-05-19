/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.task;

import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.saas.zzb.dzda.dacy.entity.EApplyE01Z8;
import com.hisun.saas.zzb.dzda.dacy.service.EA38LogService;
import com.hisun.saas.zzb.dzda.dacy.service.EApplyE01Z8Service;
import com.hisun.util.DateUtil;

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

    private void updateReadStatus(){
        CommonConditionQuery query = new CommonConditionQuery();
        //已审
        query.add(CommonRestrictions.and("auditingState = :auditingState ", "auditingState","1" ));
      //  query.add(CommonRestrictions.and("readState = :readState ", "readState","0"));
        List<EApplyE01Z8> eApplyE01Z8s = eApplyE01Z8Service.list(query,null);
        if(eApplyE01Z8s !=null && eApplyE01Z8s.isEmpty()){
            for (EApplyE01Z8 eApplyE01Z8 :eApplyE01Z8s){
                //未查阅
                if(eApplyE01Z8.getReadState() == null ||"0".equals(eApplyE01Z8.getReadState())
                        || "".equals(eApplyE01Z8.getReadState())){
                    Date accreditDate = DateUtil.parseTimesTampDate(eApplyE01Z8.getAccreditDate());
                    //授权后7天内没阅档自动结束
                    if(DateUtil.diffDate(new Date(),accreditDate) >=7){
                        //结束阅档
                        eApplyE01Z8.setAuditingState("4");
                        eApplyE01Z8Service.update(eApplyE01Z8);
                    }
                }else if ("1".equals(eApplyE01Z8.getReadState())){
                    Date readDate = DateUtil.parseTimesTampDate(eApplyE01Z8.getReadDate());
                    if(DateUtil.diffDate(new Date(),readDate) >=1){
                        eApplyE01Z8.setAuditingState("4");
                        eApplyE01Z8Service.update(eApplyE01Z8);
                    }
                }
            }
        }
    }
}
