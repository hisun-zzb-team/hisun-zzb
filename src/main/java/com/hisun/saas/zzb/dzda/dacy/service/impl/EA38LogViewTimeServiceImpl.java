/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.dzda.dacy.dao.EA38LogViewTimeDao;
import com.hisun.saas.zzb.dzda.dacy.entity.EA38LogViewTime;
import com.hisun.saas.zzb.dzda.dacy.service.EA38LogViewTimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Marco {854476391@qq.com}
 */
@Service
public class EA38LogViewTimeServiceImpl extends BaseServiceImpl<EA38LogViewTime,String> implements EA38LogViewTimeService {
    private EA38LogViewTimeDao eA38LogViewTimeDao;

    @Resource
    public void setBaseDao(BaseDao<EA38LogViewTime, String> baseDao) {
        this.baseDao = baseDao;
        this.eA38LogViewTimeDao = (EA38LogViewTimeDao)baseDao;
    }
}
