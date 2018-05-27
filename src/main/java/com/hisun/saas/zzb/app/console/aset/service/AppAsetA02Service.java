package com.hisun.saas.zzb.app.console.aset.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA02;

import javax.sql.DataSource;
import java.io.File;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface AppAsetA02Service extends BaseService<AppAsetA02,String> {

    String ATTS_PATH = File.separator+"aset"+ File.separator+"a02"+File.separator;

    int saveFromYw(DataSource dataSource)throws Exception;
    int saveFromZdwx(DataSource dataSource)throws Exception;
    String toSqliteInsertSql(AppAsetA02 entity);
}
