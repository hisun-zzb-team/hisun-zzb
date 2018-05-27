package com.hisun.saas.zzb.app.console.bset.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetFl;

import javax.sql.DataSource;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface AppBsetFlService extends BaseService<AppBsetFl,String> {

    int saveFromYw(DataSource dataSource)throws Exception;
    int saveFromZdwx(DataSource dataSource)throws Exception;
    void deleteAllData()throws Exception;
    String toSqliteInsertSql(AppBsetFl entity);
    AppBsetFl getTopFl();
}
