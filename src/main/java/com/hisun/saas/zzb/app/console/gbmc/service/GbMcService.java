/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbmc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMc;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcB01;
import com.hisun.saas.zzb.app.console.gbtj.entity.Gbtj;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface GbMcService extends BaseService<GbMc,String> {

    String ATTS_PATH = File.separator+"gbmc"+ File.separator;
    String APP_ATTS_PATH = "gbmc/";
    int getA01Count(String id) throws Exception;
    void saveFromWordDataMap(GbMc gbMc, List<Map<String, String>> dataList);
    String toSqliteInsertSql(GbMc entity);
    void saveAsSqlite(String id, String sqlite, String imgdir, String attsdir) throws Exception;
}
