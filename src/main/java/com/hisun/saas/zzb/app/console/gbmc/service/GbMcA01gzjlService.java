package com.hisun.saas.zzb.app.console.gbmc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01gzjl;

import java.util.Map;

/**
 * Created by zhouying on 2017/9/15.
 */
public interface GbMcA01gzjlService extends BaseService<GbMcA01gzjl,String> {


    public void saveFromWord(GbMcA01 gbMcA01, Map<String, String> dataMap);
    String toSqliteInsertSql(GbMcA01gzjl entity);

}
