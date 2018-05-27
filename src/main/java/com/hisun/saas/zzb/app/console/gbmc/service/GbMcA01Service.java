/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbmc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMc;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcB01;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface GbMcA01Service extends BaseService<GbMcA01,String> {

    String ATTS_PATH = GbMcService.ATTS_PATH+"mca01"+ File.separator;
    String APP_ATTS_PATH = GbMcService.APP_ATTS_PATH+"mca01/";
    String IMG_PATH = ATTS_PATH+"photo"+File.separator;
    String APP_IMG_PATH=APP_ATTS_PATH+"photo/";
    String IMPORT_DOC_TEMPLATE = ATTS_PATH +"gbmca01.docx";


    void saveFromWordDataMap(Map<String, String> dataMap, GbMcB01 gbMcB01) throws Exception;
    void updateA01FromYwJson(String gbmcId, String ywJsonPath, String photoPath) throws Exception;
    String toSqliteInsertSql(GbMcA01 entity);
}
