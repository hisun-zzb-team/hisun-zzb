package com.hisun.saas.zzb.app.console.appversion.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.appversion.dao.AppVersionDao;
import com.hisun.saas.zzb.app.console.appversion.entity.AppVersion;
import com.hisun.saas.zzb.app.console.appversion.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class AppVersionServiceImpl extends BaseServiceImpl<AppVersion,String> implements AppVersionService {

    private AppVersionDao appVersionDao;

    @Autowired
    public void setBaseDao(BaseDao<AppVersion, String> appVersionDao) {
        this.baseDao = appVersionDao;
        this.appVersionDao = (AppVersionDao) appVersionDao;
    }}
