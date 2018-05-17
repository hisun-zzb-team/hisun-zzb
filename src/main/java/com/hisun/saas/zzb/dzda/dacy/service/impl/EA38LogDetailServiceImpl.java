/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.dzda.dacy.dao.EA38LogDao;
import com.hisun.saas.zzb.dzda.dacy.dao.EA38LogDetailDao;
import com.hisun.saas.zzb.dzda.dacy.entity.EA38LogDetail;
import com.hisun.saas.zzb.dzda.dacy.service.EA38LogDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Marco {854476391@qq.com}
 */
@Service
public class EA38LogDetailServiceImpl extends BaseServiceImpl<EA38LogDetail,String> implements EA38LogDetailService {
    private EA38LogDetailDao eA38LogDetailDao;

    @Resource
    public void setBaseDao(BaseDao<EA38LogDetail, String> baseDao) {
        this.baseDao = baseDao;
        this.eA38LogDetailDao = (EA38LogDetailDao)baseDao;
    }
}
