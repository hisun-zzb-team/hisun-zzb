package com.hisun.saas.zzb.app.console.shpc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01dascqk;

import java.io.File;

/**
 * Created by zhouying on 2017/9/15.
 */
public interface Sha01dascqkService extends BaseService<Sha01dascqk,String> {

    String ATTS_PATH = Sha01Service.ATTS_PATH+"dascqk"+ File.separator;
    String APP_ATTS_PATH = Sha01Service.APP_ATTS_PATH+"dascqk/";

    String toSqliteInsertSql(Sha01dascqk entity);
}
