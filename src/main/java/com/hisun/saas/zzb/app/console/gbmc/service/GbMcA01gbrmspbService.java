package com.hisun.saas.zzb.app.console.gbmc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01gbrmspb;

import java.io.File;

/**
 * Created by zhouying on 2017/9/15.
 */
public interface GbMcA01gbrmspbService extends BaseService<GbMcA01gbrmspb,String> {

    String ATTS_PATH = GbMcA01Service.ATTS_PATH+"gbrmspb"+ File.separator;
    String APP_ATTS_PATH=GbMcA01Service.APP_ATTS_PATH+"gbrmspb/";
    String GBRMSPB_DOC_TEMPLATE = ATTS_PATH+"gbrmspb.docx";


    public String saveFromWord(GbMcA01gbrmspb gbrmspb, String wordsourcePath, String templatePath) throws Exception;
    String toSqliteInsertSql(GbMcA01gbrmspb entity);
}
