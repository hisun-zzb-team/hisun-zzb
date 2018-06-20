/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.e01z9.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.dzda.e01z9.dao.E01Z9Dao;
import com.hisun.saas.zzb.dzda.e01z9.entity.E01Z9;
import com.hisun.saas.zzb.dzda.e01z9.service.E01Z9Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhout {605144321@qq.com}
 */
@Service
public class E01Z9ServiceImpl extends BaseServiceImpl<E01Z9,String>
        implements E01Z9Service {

    private E01Z9Dao e01Z9Dao;

    @Resource
    public void setBaseDao(BaseDao<E01Z9, String> baseDao) {
        this.baseDao = baseDao;
        this.e01Z9Dao = (E01Z9Dao)baseDao;
    }

}
