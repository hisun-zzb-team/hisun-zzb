package com.hisun.saas.zzb.app.console.appclient.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.appclient.dao.AppClientDao;
import com.hisun.saas.zzb.app.console.appclient.entity.AppClient;
import com.hisun.saas.zzb.app.console.appclient.service.AppClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class AppClientServiceImpl extends BaseServiceImpl<AppClient,String> implements AppClientService {

    private AppClientDao appClientDao;

    @Autowired
    public void setBaseDao(BaseDao<AppClient, String> appClientDao) {
        this.baseDao = appClientDao;
        this.appClientDao = (AppClientDao) appClientDao;
    }}
