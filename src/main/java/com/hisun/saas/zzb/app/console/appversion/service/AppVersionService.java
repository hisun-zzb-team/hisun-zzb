package com.hisun.saas.zzb.app.console.appversion.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.appversion.entity.AppVersion;
import com.hisun.saas.zzb.app.console.gbtj.entity.Gbtj;

import java.io.File;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface AppVersionService extends BaseService<AppVersion,String> {
    public static String APP_PATH = File.separator+"APP"+ File.separator;
}
