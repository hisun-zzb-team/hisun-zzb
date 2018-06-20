/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.a.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.a.dao.A12Dao;
import com.hisun.saas.zzb.a.entity.A12;
import com.hisun.saas.zzb.a.service.A12Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Marco {854476391@qq.com}
 */
@Service
public class A12ServiceImpl extends BaseServiceImpl<A12,String> implements A12Service {
    private A12Dao a12Dao;

    @Resource
    public void setBaseDao(BaseDao<A12, String> baseDao) {
        this.baseDao = baseDao;
        this.a12Dao = (A12Dao)baseDao;
    }
}
