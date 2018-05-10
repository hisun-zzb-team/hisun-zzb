/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a52.dao.impl;

import com.hisun.base.dao.impl.BaseDaoImpl;
import com.hisun.saas.sys.tenant.base.dao.imp.TenantBaseDaoImpl;
import com.hisun.saas.zzb.dzda.a52.dao.A52Dao;
import com.hisun.saas.zzb.dzda.a52.entity.A52;
import org.springframework.stereotype.Repository;

/**
 * @author Marco {854476391@qq.com}
 */
@Repository
public class A52DaoImpl extends TenantBaseDaoImpl<A52,String> implements A52Dao{

}
