package com.hisun.saas.zzb.app.console.shpc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01kccl;

import java.io.File;

/**
 * Created by zhouying on 2017/9/15.
 */
public interface Sha01kcclService extends BaseService<Sha01kccl,String> {

    String ATTS_PATH = Sha01Service.ATTS_PATH+"kccl"+ File.separator;
    String APP_ATTS_PATH = Sha01Service.APP_ATTS_PATH+"kccl/";
    String toSqliteInsertSql(Sha01kccl kccl);
}
