/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.b.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.b.dao.B04Dao;
import com.hisun.saas.zzb.b.entity.B04;
import com.hisun.saas.zzb.b.service.B04Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Marco {854476391@qq.com}
 */
@Service
public class B04ServiceImpl extends BaseServiceImpl<B04,String> implements B04Service {
    private B04Dao b04Dao;

    @Resource
    public void setBaseDao(BaseDao<B04, String> baseDao) {
        this.baseDao = baseDao;
        this.b04Dao = (B04Dao)baseDao;
    }
}
