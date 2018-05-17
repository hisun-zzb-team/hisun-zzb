/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.dzda.dacy.dao.ELogDetailViewTimeDao;
import com.hisun.saas.zzb.dzda.dacy.entity.ELogDetailViewTime;
import com.hisun.saas.zzb.dzda.dacy.service.ELogDetailViewTimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Marco {854476391@qq.com}
 */
@Service
public class ELogDetailViewTimeServiceImpl extends BaseServiceImpl<ELogDetailViewTime,String> implements ELogDetailViewTimeService {
    private ELogDetailViewTimeDao eLogDetailViewTimeDao;

    @Resource
    public void setBaseDao(BaseDao<ELogDetailViewTime, String> baseDao) {
        this.baseDao = baseDao;
        this.eLogDetailViewTimeDao = (ELogDetailViewTimeDao)baseDao;
    }
}
