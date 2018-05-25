package com.hisun.saas.zzb.app.console.exchange.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.appclient.entity.AppClient;
import com.hisun.saas.zzb.app.console.exchange.entity.ExchangeActuator;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface ExchangeActuatorService extends BaseService<ExchangeActuator,String> {
    Integer getMaxPx() ;
}
