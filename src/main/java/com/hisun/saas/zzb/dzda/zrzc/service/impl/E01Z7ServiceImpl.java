/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.zrzc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.dzda.zrzc.dao.E01Z5Dao;
import com.hisun.saas.zzb.dzda.zrzc.dao.E01Z7Dao;
import com.hisun.saas.zzb.dzda.zrzc.entity.E01Z5;
import com.hisun.saas.zzb.dzda.zrzc.entity.E01Z7;
import com.hisun.saas.zzb.dzda.zrzc.service.E01Z5Service;
import com.hisun.saas.zzb.dzda.zrzc.service.E01Z7Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liuzj {279421824@qq.com}
 */
@Service
public class E01Z7ServiceImpl extends BaseServiceImpl<E01Z7,String> implements E01Z7Service {
    private E01Z7Dao e01z7Dao;

    @Resource
    public void setBaseDao(BaseDao<E01Z7, String> baseDao) {
        this.baseDao = baseDao;
        this.e01z7Dao = (E01Z7Dao)baseDao;
    }

}
