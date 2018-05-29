/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.b.dao.impl;

import com.hisun.saas.sys.tenant.base.dao.imp.TenantBaseDaoImpl;
import com.hisun.saas.zzb.b.dao.B01Dao;
import com.hisun.saas.zzb.b.entity.B01;
import org.springframework.stereotype.Repository;

/**
 * @author liuzj {279421824@qq.com}
 */
@Repository
public class B01DaoImpl extends TenantBaseDaoImpl<B01,String> implements B01Dao {
}
