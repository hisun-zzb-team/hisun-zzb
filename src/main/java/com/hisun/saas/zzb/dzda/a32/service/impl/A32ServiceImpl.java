/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a32.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.dzda.a32.dao.A32Dao;
import com.hisun.saas.zzb.dzda.a32.entity.A32;
import com.hisun.saas.zzb.dzda.a32.service.A32Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marco {854476391@qq.com}
 */
@Service
public class A32ServiceImpl extends BaseServiceImpl<A32,String> implements A32Service {
    private A32Dao a32Dao;

    @Resource
    public void setBaseDao(BaseDao<A32, String> baseDao) {
        this.baseDao = baseDao;
        this.a32Dao = (A32Dao)baseDao;
    }

    /**
     * 排序处理（首先跟最大的排序号比较，如果大于最大排序号则不处理；
     *        如果小于，就一个个比较，当比较到大于它的就把后面大于它的全部+1；）
     * @param
     */
    public void updatePx(int oldPx,int newPx,String a38Id){
        String sql = "UPDATE a32 t SET ";
        if(newPx > oldPx) {
            sql = sql + "t.px=t.px-1";
        } else {
            sql = sql + "t.px=t.px+1";
        }
        sql = sql + " where t.a38_id=(:a38Id)";
        if(newPx > oldPx) {
            sql = sql + " and t.px<=" + newPx + " and t.px >" + oldPx;
        } else if(newPx == oldPx) {
            sql = sql + " and 1<>1";
        } else {
            sql = sql + " and t.px<" + oldPx + " and t.px>=" + newPx;
        }
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("a38Id", a38Id);
        this.a32Dao.update(sql, paramMap);
    }
    @Override
    public Integer getMaxSort(String a38Id) {
        Map<String, Object> map = new HashMap<String, Object>();
        String hql = "select max(t.px)+1 as sort from a32 t ";
        if (a38Id != null && !a38Id.equals("")) {
            hql = hql + "where t.a38_id =:a38Id";
            map.put("a38Id", a38Id);
        } else {
            hql = hql + "where t.a38_id is null";
        }
        List<Map> maxSorts = this.a32Dao.nativeList(hql, map);
        if (maxSorts.get(0).get("sort") == null) {
            return 1;
        } else {
            Integer maxSort = ((Number) maxSorts.get(0).get("sort")).intValue();
            return maxSort;
        }
    }
}
