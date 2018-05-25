/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.zscx.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.zscx.dao.AppZscxZsA01Dao;
import com.hisun.saas.zzb.app.console.zscx.entity.AppZscxZsA01;
import com.hisun.saas.zzb.app.console.zscx.service.AppZscxZsA01Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class AppZscxZsA01ServiceImpl extends BaseServiceImpl<AppZscxZsA01,String> implements AppZscxZsA01Service {

    private AppZscxZsA01Dao appZscxZsA01Dao;

    @Autowired
    public void setBaseDao(BaseDao<AppZscxZsA01, String> appZscxZsA01Dao) {
        this.baseDao = appZscxZsA01Dao;
        this.appZscxZsA01Dao = (AppZscxZsA01Dao) appZscxZsA01Dao;
    }
    public Integer getMaxPx(String b01Id){
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String, Object> arg=new HashMap<String, Object>();
        String hql = "select max(t.a01_px) as px from app_zscx_zs_a01 t where t.tombstone=(:tombstone) and t.tenant_id=(:tenant_id) and t.b01_id=(:b01_id) order by  t.a01_px asc";
        arg.put("tombstone", "0");
        arg.put("tenant_id", userLoginDetails.getTenantId());
        arg.put("b01_id", b01Id);
        List<Map> maxSorts = this.appZscxZsA01Dao.nativeList(hql, arg);
        Integer maxPx = (Integer) maxSorts.get(0).get("px");
        return maxPx;
    }

    /**
     * 排序处理（首先跟最大的排序号比较，如果大于最大排序号则不处理；
     *        如果小于，就一个个比较，当比较到大于它的就把后面大于它的全部+1；）
     * @param
     */
    public void updatePx(String b01Id,int oldPx,int newPx){
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        String sql = "UPDATE app_zscx_zs_a01 t SET ";
        if(newPx > oldPx) {
            sql = sql + "t.a01_px=t.a01_px-1";
        } else {
            sql = sql + "t.a01_px=t.a01_px+1";
        }

        sql = sql + " where t.tombstone=(:tombstone) and t.tenant_id=(:tenant_id) and t.b01_id=(:b01_id)";
        if(newPx > oldPx) {
            sql = sql + " and t.a01_px<=" + newPx + " and t.a01_px >" + oldPx;
        } else if(newPx == oldPx) {
            sql = sql + " and 1<>1";
        } else {
            sql = sql + " and t.a01_px<" + oldPx + " and t.a01_px>=" + newPx;
        }
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("tombstone", "0");
        paramMap.put("tenant_id", userLoginDetails.getTenantId());
        paramMap.put("b01_id", b01Id);
        this.appZscxZsA01Dao.update(sql, paramMap);
    }
}
