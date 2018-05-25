package com.hisun.saas.zzb.app.console.shpc.service;

import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.service.BaseService;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA01;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.saas.zzb.app.console.shpc.vo.Sha01Vo;

import java.io.File;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/15.
 */
public interface Sha01Service extends BaseService<Sha01,String> {

    String ATTS_PATH = ShpcService.ATTS_PATH+"sha01"+ File.separator;
    String APP_ATTS_PATH = ShpcService.APP_ATTS_PATH+"sha01/";
    String IMG_PATH=ATTS_PATH+"photo"+File.separator;
    String APP_IMG_PATH = APP_ATTS_PATH+"photo/";
    String IMPORT_DOC_TEMPLATE = ATTS_PATH +"sha01.docx";


    void saveFromWordDataMap(Tenant tenant, Map<String, String> dataMap, String pcId);
    PagerVo<Sha01Vo> getSha01VoS(int pageSize, int pageNum, String shpcId, String xmQuery, String noFileQuert);
    String toSqliteInsertSql(Sha01 sha01);
    void matchQueryCondition(CommonConditionQuery query, String uploadMatchingMode, String split, String filename);
    void saveAsGbrmspb(Sha01 sha01)throws Exception;
}
