/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.a.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.a.dao.A14Z2Dao;
import com.hisun.saas.zzb.a.entity.A14Z2;
import com.hisun.saas.zzb.a.service.A14Z2Service;
import com.hisun.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuzj {279421824@qq.com}
 */
@Service
public class A14Z2ServiceImpl extends BaseServiceImpl<A14Z2,String> implements A14Z2Service {
    private A14Z2Dao a14Z2Dao;

    @Resource
    public void setBaseDao(BaseDao<A14Z2, String> baseDao) {
        this.baseDao = baseDao;
        this.a14Z2Dao = (A14Z2Dao)baseDao;
    }

    @Override
    public String getData(List<A14Z2> a14Z2s){
        String strData = "";
        for(A14Z2 a14Z2:a14Z2s){
            if(StringUtils.isEmpty(a14Z2.getA14Z211())){
                a14Z2.setA14Z211("");
            }
            if(StringUtils.isEmpty(a14Z2.getA14Z204A())){
                a14Z2.setA14Z204A("");
            }
            strData = strData +a14Z2.getA14Z211()+ " " +a14Z2.getA14Z204A() + "。\n";
        }
        return strData;
    }
}
