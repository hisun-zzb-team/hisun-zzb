/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.a.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.a.dao.A17Dao;
import com.hisun.saas.zzb.a.entity.A17;
import com.hisun.saas.zzb.a.service.A17Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Marco {854476391@qq.com}
 */
@Service
public class A17ServiceImpl  extends BaseServiceImpl<A17,String> implements A17Service {
    private A17Dao a17Dao;

    @Resource
    public void setBaseDao(BaseDao<A17, String> baseDao) {
        this.baseDao = baseDao;
        this.a17Dao = (A17Dao)baseDao;
    }
}
