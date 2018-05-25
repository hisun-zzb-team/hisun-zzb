package com.hisun.saas.zzb.app.console.shpc.dao;

import com.hisun.base.dao.BaseDao;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;

import java.util.Map;

/**
 * Created by zhouying on 2017/9/13.
 */
public interface Sha01Dao extends BaseDao<Sha01,String> {

    public void saveFromWordDataMap(Tenant tenant, Map<String, String> dataMap, String pcId);

}
