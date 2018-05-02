/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.a.a01.privilege;

import com.hisun.saas.sys.privilege.PrivilegeRowExpress;
import com.hisun.saas.sys.privilege.PrivilegeUtil;
import com.hisun.saas.sys.tenant.tenant.service.Tenant2ResourcePrivilegeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Rocky {rockwithyou@126.com}
 */
@Component
public class A01ZylbPrivilegeRowExpress implements PrivilegeRowExpress {


    @Resource
    private Tenant2ResourcePrivilegeService tenant2ResourcePrivilegeService;

    @Override
    public String getHqlFilterExpress(String selectedValues) {
        String[] values = selectedValues.split(",");
        StringBuffer sb = new StringBuffer();
        sb.append(" a01.zylb in ");
        sb.append(PrivilegeUtil.toSqlInParameterExpress(values));
        return sb.toString();
    }

    @Override
    public String getSqlFilterExpress(String selectedValues) {
        String[] values = selectedValues.split(",");
        StringBuffer sb = new StringBuffer();
        sb.append(" a01.ext_zylb in ");
        sb.append(PrivilegeUtil.toSqlInParameterExpress(values));
        return sb.toString();
    }

    @Override
    public String getHqlFilterExpressInRole(String selectedValues, String tenant2ResourcePrivilegeId) {
        String tenantHqlFilterExpress = this.tenant2ResourcePrivilegeService.getByPK(tenant2ResourcePrivilegeId).getHqlFilterExpress();
        String[] values = selectedValues.split(",");
        StringBuffer sb = new StringBuffer();
        sb.append(" a01.zylb in ");
        sb.append(PrivilegeUtil.toSqlInParameterExpress(values));
        sb.append(" and ");
        sb.append(tenantHqlFilterExpress);
        return sb.toString();
    }

    @Override
    public String getSqlFilterExpressInRole(String selectedValues, String tenant2ResourcePrivilegeId) {
        String tenantHqlFilterExpress = this.tenant2ResourcePrivilegeService.getByPK(tenant2ResourcePrivilegeId).getHqlFilterExpress();
        String[] values = selectedValues.split(",");
        StringBuffer sb = new StringBuffer();
        sb.append(" a01.ext_zylb in ");
        sb.append(PrivilegeUtil.toSqlInParameterExpress(values));
        sb.append(" and ");
        sb.append(tenantHqlFilterExpress);
        return sb.toString();
    }
}
