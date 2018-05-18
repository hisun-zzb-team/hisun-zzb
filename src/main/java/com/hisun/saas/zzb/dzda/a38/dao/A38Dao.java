/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a38.dao;

import com.hisun.base.dao.BaseDao;
import com.hisun.saas.sys.tenant.privilege.entity.TenantPrivilege;
import com.hisun.saas.zzb.dzda.a38.entity.A38;

import java.util.List;
import java.util.Map;

/**
 * @author liuzj {279421824@qq.com}
 */
public interface A38Dao extends BaseDao<A38,String> {
    List<A38> gjcxList(String sql, Map<String,Object> paramMap);
}
