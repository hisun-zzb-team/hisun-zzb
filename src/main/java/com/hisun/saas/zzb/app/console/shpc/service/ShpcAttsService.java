package com.hisun.saas.zzb.app.console.shpc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.shpc.entity.ShpcAtts;

import java.io.File;

/**
 * Created by zhouying on 2017/11/15.
 */
public interface ShpcAttsService extends BaseService<ShpcAtts,String> {

    String ATTS_PATH = ShpcService.ATTS_PATH+"shpcAtts"+ File.separator;
    String APP_ATTS_PATH = ShpcService.APP_ATTS_PATH+"shpcAtts/";

    String toSqliteInsertSql(ShpcAtts entity);
}
