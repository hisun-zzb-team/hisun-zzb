/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.e01z2.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.dzda.e01z2.dao.E01z2Dao;
import com.hisun.saas.zzb.dzda.e01z2.entity.E01Z2;
import com.hisun.saas.zzb.dzda.e01z2.service.E01z2Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marco {854476391@qq.com}
 */
@Service
public class E01z2ServiceImpl extends BaseServiceImpl<E01Z2,String> implements E01z2Service{
    private E01z2Dao e01z2Dao;

    @Resource
    public void setBaseDao(BaseDao<E01Z2, String> baseDao) {
        this.baseDao = baseDao;
        this.e01z2Dao = (E01z2Dao)baseDao;
    }
    /**
     * 排序处理（首先跟最大的排序号比较，如果大于最大排序号则不处理；
     *        如果小于，就一个个比较，当比较到大于它的就把后面大于它的全部+1；）
     * @param
     */
    public void updatePx(int oldPx,int newPx,String a38Id){
        String sql = "UPDATE e01z2 t SET ";
        if(newPx > oldPx) {
            sql = sql + "t.e01z214=t.e01z214-1";
        } else {
            sql = sql + "t.e01z214=t.e01z214+1";
        }
        sql = sql + " where t.a38_id=(:a38Id)";
        if(newPx > oldPx) {
            sql = sql + " and t.e01z214<=" + newPx + " and t.e01z214 >" + oldPx;
        } else if(newPx == oldPx) {
            sql = sql + " and 1<>1";
        } else {
            sql = sql + " and t.e01z214<" + oldPx + " and t.e01z214>=" + newPx;
        }
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("a38Id", a38Id);
        this.e01z2Dao.update(sql, paramMap);
    }
    @Override
    public Integer getMaxSort(String a38Id) {
        Map<String, Object> map = new HashMap<String, Object>();
        String hql = "select max(t.e01z214)+1 as sort from e01z2 t ";
        if (a38Id != null && !a38Id.equals("")) {
            hql = hql + "where t.a38_id =:a38Id";
            map.put("a38Id", a38Id);
        } else {
            hql = hql + "where t.a38_id is null";
        }
        List<Map> maxSorts = this.e01z2Dao.nativeList(hql, map);
        if (maxSorts.get(0).get("sort") == null) {
            return 1;
        } else {
            Integer maxSort = ((Number) maxSorts.get(0).get("sort")).intValue();
            return maxSort;
        }
    }
}
