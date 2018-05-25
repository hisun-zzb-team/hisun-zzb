package com.hisun.saas.zzb.app.console.gbmc.dao;

import com.hisun.base.dao.BaseDao;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01gbrmspb;

import java.util.Map;

/**
 * Created by zhouying on 2017/9/12.
 */
public interface GbMcA01gbrmspbDao extends BaseDao<GbMcA01gbrmspb,String> {

    public String saveFromWord(GbMcA01gbrmspb gbrmspb, Map<String, String> wordDataMap) throws Exception;

}
