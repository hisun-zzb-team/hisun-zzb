/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.exchange.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.appclient.entity.AppClient;
import com.hisun.saas.zzb.app.console.exchange.dao.ExchangeActuatorDao;
import com.hisun.saas.zzb.app.console.exchange.entity.ExchangeActuator;
import com.hisun.saas.zzb.app.console.exchange.service.ExchangeActuatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class ExchangeActuatorImpl extends BaseServiceImpl<ExchangeActuator,String> implements ExchangeActuatorService {

    private ExchangeActuatorDao exchangeActuatorDao;

    @Autowired
    public void setBaseDao(BaseDao<ExchangeActuator, String> exchangeActuatorDao) {
        this.baseDao = exchangeActuatorDao;
        this.exchangeActuatorDao = (ExchangeActuatorDao) exchangeActuatorDao;
    }
    public Integer getMaxPx(){
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String, Object> arg=new HashMap<String, Object>();
        String hql = "select max(t.sort) as px from app_exchange_actuator t where t.tombstone=(:tombstone) and t.tenant_id=(:tenant_id) ";
        hql = hql+ " order by  t.sort asc";
        arg.put("tombstone", "0");
        arg.put("tenant_id", userLoginDetails.getTenantId());
        List<Map> maxSorts = this.exchangeActuatorDao.nativeList(hql, arg);
        Integer maxPx = (Integer) maxSorts.get(0).get("px");
        return maxPx;
    }
}
