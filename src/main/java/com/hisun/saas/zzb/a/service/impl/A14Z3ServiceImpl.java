/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.a.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.a.dao.A14Z3Dao;
import com.hisun.saas.zzb.a.entity.A14Z3;
import com.hisun.saas.zzb.a.service.A14Z3Service;
import com.hisun.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuzj {279421824@qq.com}
 */
@Service
public class A14Z3ServiceImpl extends BaseServiceImpl<A14Z3,String> implements A14Z3Service {
    private A14Z3Dao a14Z3Dao;

    @Resource
    public void setBaseDao(BaseDao<A14Z3, String> baseDao) {
        this.baseDao = baseDao;
        this.a14Z3Dao = (A14Z3Dao)baseDao;
    }

    @Override
    public String getData(List<A14Z3> a14Z3s){
        String strData = "";
        for(A14Z3 a14Z3:a14Z3s){
            if(StringUtils.isEmpty(a14Z3.getA14Z321())){
                a14Z3.setA14Z321("");
            }
            if(StringUtils.isEmpty(a14Z3.getA14Z307())){
                a14Z3.setA14Z307("");
            }
            strData = strData +a14Z3.getA14Z321()+ " " +a14Z3.getA14Z307() + "。\n";
        }
        return strData;
    }
}
