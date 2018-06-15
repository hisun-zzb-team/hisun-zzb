/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.a.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.a.dao.A36Dao;
import com.hisun.saas.zzb.a.entity.A36;
import com.hisun.saas.zzb.a.service.A36Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuzj {279421824@qq.com}
 */
@Service
public class A36ServiceImpl extends BaseServiceImpl<A36,String> implements A36Service {
    private A36Dao a36Dao;

    @Resource
    public void setBaseDao(BaseDao<A36, String> baseDao) {
        this.baseDao = baseDao;
        this.a36Dao = (A36Dao)baseDao;
    }

    /**
     * 排序处理（首先跟最大的排序号比较，如果大于最大排序号则不处理；
     *        如果小于，就一个个比较，当比较到大于它的就把后面大于它的全部+1；）
     * @param
     */
    public void updatePx(int oldPx,int newPx,String a01Id){
        String sql = "UPDATE a36 a SET ";
        if(newPx > oldPx) {
            sql = sql + "a.a3647=a.a3647-1";
        } else {
            sql = sql + "a.a3647=a.a3647+1";
        }
        sql = sql + " where a.a0100 =(:a01Id) ";
        if(newPx > oldPx) {
            sql = sql + " and a.a3647<=" + newPx + " and a.a3647 >" + oldPx;
        } else if(newPx == oldPx) {
            sql = sql + " and 1<>1";
        } else {
            sql = sql + " and a.a3647<" + oldPx + " and a.a3647>=" + newPx;
        }
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("a01Id",a01Id);
        this.a36Dao.update(sql, paramMap);
    }
    @Override
    public Integer getMaxSort() {
        Map<String, Object> map = new HashMap<String, Object>();
        String hql = "select max(a.a3647)+1 as sort from A36 a ";
        List<Map> maxSorts = this.a36Dao.list(hql, map);
        if (maxSorts.get(0).get("sort") == null) {
            return 1;
        } else {
            Integer maxSort = ((Number) maxSorts.get(0).get("sort")).intValue();
            return maxSort;
        }
    }
}
