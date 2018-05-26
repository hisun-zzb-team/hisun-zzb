/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacx.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.dzda.dacx.dao.AppQueryInfoDao;
import com.hisun.saas.zzb.dzda.dacx.entity.AppQueryInfo;
import com.hisun.saas.zzb.dzda.dacx.service.AppQueryInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marco {854476391@qq.com}
 */
@Service
public class AppQueryInfoServiceImpl extends BaseServiceImpl<AppQueryInfo,String> implements AppQueryInfoService {
    private AppQueryInfoDao appQueryInfoDao;

    @Resource
    public void setBaseDao(BaseDao<AppQueryInfo, String> baseDao) {
        this.baseDao = baseDao;
        this.appQueryInfoDao = (AppQueryInfoDao)baseDao;
    }

    /**
     * 排序处理（首先跟最大的排序号比较，如果大于最大排序号则不处理；
     *        如果小于，就一个个比较，当比较到大于它的就把后面大于它的全部+1；）
     * @param
     */
    public void updatePx(int oldPx,int newPx,String tenantId){
        String sql = "UPDATE app_query_info t SET ";
        if(newPx > oldPx) {
            sql = sql + "t.px=t.px-1";
        } else {
            sql = sql + "t.px=t.px+1";
        }
        sql = sql + " where t.tenant_id=(:tenantId)";
        if(newPx > oldPx) {
            sql = sql + " and t.px<=" + newPx + " and t.px >" + oldPx;
        } else if(newPx == oldPx) {
            sql = sql + " and 1<>1";
        } else {
            sql = sql + " and t.px<" + oldPx + " and t.px>=" + newPx;
        }
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("tenantId", tenantId);
        this.appQueryInfoDao.update(sql, paramMap);
    }
    @Override
    public Integer getMaxSort(String tenantId) {
        Map<String, Object> map = new HashMap<String, Object>();
        String hql = "select max(t.px)+1 as sort from app_query_info t ";
        if (tenantId != null && !tenantId.equals("")) {
            hql = hql + "where t.tenant_id =:tenantId";
            map.put("tenantId", tenantId);
        }
        List<Map> maxSorts = this.appQueryInfoDao.nativeList(hql, map);
        if (maxSorts.get(0).get("sort") == null) {
            return 1;
        } else {
            Integer maxSort = ((Number) maxSorts.get(0).get("sort")).intValue();
            return maxSort;
        }
    }
}
