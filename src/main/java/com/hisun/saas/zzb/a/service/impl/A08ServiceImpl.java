/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.a.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.a.dao.A08Dao;
import com.hisun.saas.zzb.a.entity.A08;
import com.hisun.saas.zzb.a.service.A08Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liuzj {279421824@qq.com}
 */
@Service
public class A08ServiceImpl extends BaseServiceImpl<A08,String> implements A08Service {
    private A08Dao a08Dao;

    @Resource
    public void setBaseDao(BaseDao<A08, String> baseDao) {
        this.baseDao = baseDao;
        this.a08Dao = (A08Dao)baseDao;
    }
}
