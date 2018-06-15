/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.a.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.a.dao.A15Dao;
import com.hisun.saas.zzb.a.entity.A15;
import com.hisun.saas.zzb.a.service.A15Service;
import com.hisun.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuzj {279421824@qq.com}
 */
@Service
public class A15ServiceImpl extends BaseServiceImpl<A15,String> implements A15Service {
    private A15Dao a15Dao;

    @Resource
    public void setBaseDao(BaseDao<A15, String> baseDao) {
        this.baseDao = baseDao;
        this.a15Dao = (A15Dao)baseDao;
    }

    @Override
    public String getData(List<A15> a15s) {
        String strData = "";
        for (A15 a15 : a15s) {
            String khsj = "";
            if("1".equals(a15.getA1501())){
                if(StringUtils.isEmpty(a15.getaKhnd())){
                    a15.setaKhnd("");
                }
                khsj=a15.getaKhnd();
            }else if("2".equals(a15.getA1501())){
                int flag = 0;
                if(StringUtils.isEmpty(a15.getA1504())){
                    a15.setA1504("");
                    flag++;
                }
                if(StringUtils.isEmpty(a15.getA1505())){
                    a15.setA1505("");
                    flag++;
                }
                khsj = a15.getA1504() + (flag==0?"-":"") + a15.getA1505();
            }
            strData = strData +khsj+ " " +a15.getA1517A() + "。\n";
        }
        return strData;
    }
}
