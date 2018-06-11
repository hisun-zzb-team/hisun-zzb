/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.a.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.a.dao.A01Dao;
import com.hisun.saas.zzb.a.entity.A01;
import com.hisun.saas.zzb.a.service.A01Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Marco {854476391@qq.com}
 */
@Service
public class A01ServiceImpl extends BaseServiceImpl<A01,String> implements A01Service {
    private A01Dao a01Dao;

    @Resource
    public void setBaseDao(BaseDao<A01, String> baseDao) {
        this.baseDao = baseDao;
        this.a01Dao = (A01Dao)baseDao;
    }
}
