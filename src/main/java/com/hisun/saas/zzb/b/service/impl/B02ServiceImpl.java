/*
 * Copyright (c) 2028. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.b.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.b.dao.B02Dao;
import com.hisun.saas.zzb.b.entity.B02;
import com.hisun.saas.zzb.b.service.B02Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Marco {854476391@qq.com}
 */
@Service
public class B02ServiceImpl extends BaseServiceImpl<B02,String> implements B02Service {
    private B02Dao b02Dao;

    @Resource
    public void setBaseDao(BaseDao<B02, String> baseDao) {
        this.baseDao = baseDao;
        this.b02Dao = (B02Dao)baseDao;
    }

}
