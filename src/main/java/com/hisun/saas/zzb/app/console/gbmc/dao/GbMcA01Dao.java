/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbmc.dao;

import com.hisun.base.dao.BaseDao;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMc;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcB01;

import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface GbMcA01Dao extends BaseDao<GbMcA01,String> {


    public void saveFromWordDataMap(Map<String, String> dataMap, GbMcB01 gbMcB01);

}
