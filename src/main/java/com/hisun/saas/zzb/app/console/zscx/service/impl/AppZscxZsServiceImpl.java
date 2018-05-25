package com.hisun.saas.zzb.app.console.zscx.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.zscx.dao.AppZscxZsDao;
import com.hisun.saas.zzb.app.console.zscx.entity.AppZscxZs;
import com.hisun.saas.zzb.app.console.zscx.service.AppZscxZsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class AppZscxZsServiceImpl extends BaseServiceImpl<AppZscxZs,String> implements AppZscxZsService {

    private AppZscxZsDao appZscxZsDao;

    @Autowired
    public void setBaseDao(BaseDao<AppZscxZs, String> appZscxZsDao) {
        this.baseDao = appZscxZsDao;
        this.appZscxZsDao = (AppZscxZsDao) appZscxZsDao;
    }}
