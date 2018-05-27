package com.hisun.saas.zzb.app.console.shpc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01dascqktips;

/**
 * Created by zhouying on 2017/9/15.
 */
public interface Sha01dascqktipsService extends BaseService<Sha01dascqktips,String> {
    void deleteBySql(String id) ;
    String toSqliteInsertSql(Sha01dascqktips entity);
}
