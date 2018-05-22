/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.dao.impl;

import com.hisun.saas.sys.tenant.base.dao.imp.TenantBaseDaoImpl;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.dacy.dao.EA38LogDao;
import com.hisun.saas.zzb.dzda.dacy.entity.EA38Log;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Marco {854476391@qq.com}
 */
@Repository
public class EA38LogDaoImpl extends TenantBaseDaoImpl<EA38Log,String> implements EA38LogDao {
    public List<EA38Log> getA38LogListBySql(String sql, Map<String,Object> paramMap){
        return nativeList(EA38Log.class, sql.toString(), paramMap);
    }
}
