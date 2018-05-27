package com.hisun.saas.zzb.app.console.zscx.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.zscx.entity.AppZscxZs;

import java.io.File;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface AppZscxZsService extends BaseService<AppZscxZs,String> {
    public static String ATTS_PATH = File.separator+"zscx"+ File.separator;
}
