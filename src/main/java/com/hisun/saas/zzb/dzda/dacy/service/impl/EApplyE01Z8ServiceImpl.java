/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.dzda.dacy.dao.EApplyE01Z8Dao;
import com.hisun.saas.zzb.dzda.dacy.entity.EApplyE01Z8;
import com.hisun.saas.zzb.dzda.dacy.service.EApplyE01Z8Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Marco {854476391@qq.com}
 */
@Service
public class EApplyE01Z8ServiceImpl extends BaseServiceImpl<EApplyE01Z8,String> implements EApplyE01Z8Service {
    private EApplyE01Z8Dao eApplyE01Z8Dao;

    @Resource
    public void setBaseDao(BaseDao<EApplyE01Z8, String> baseDao) {
        this.baseDao = baseDao;
        this.eApplyE01Z8Dao = (EApplyE01Z8Dao)baseDao;
    }
}
