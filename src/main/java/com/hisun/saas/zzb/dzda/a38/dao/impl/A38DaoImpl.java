/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a38.dao.impl;

import com.hisun.base.dao.impl.BaseDaoImpl;
import com.hisun.saas.sys.tenant.base.dao.imp.TenantBaseDaoImpl;
import com.hisun.saas.sys.tenant.privilege.dao.TenantPrivilegeDao;
import com.hisun.saas.sys.tenant.privilege.entity.TenantPrivilege;
import com.hisun.saas.zzb.dzda.a38.dao.A38Dao;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import org.springframework.stereotype.Repository;

/**
 * @author liuzj {279421824@qq.com}
 */
@Repository
public class A38DaoImpl extends TenantBaseDaoImpl<A38,String>
       implements A38Dao {


}
