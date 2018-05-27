package com.hisun.saas.zzb.app.console.shpc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01grzdsx;

import java.io.File;

/**
 * Created by zhouying on 2017/9/15.
 */
public interface Sha01grzdsxService extends BaseService<Sha01grzdsx,String> {

    String ATTS_PATH = Sha01Service.ATTS_PATH+"grzdsx"+ File.separator;
    String APP_ATTS_PATH = Sha01Service.APP_ATTS_PATH + "grzdsx/";

    String toSqliteInsertSql(Sha01grzdsx entity);
}
