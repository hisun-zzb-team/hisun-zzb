/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.mlcl.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.saas.zzb.dzda.mlcl.dao.E01Z1Dao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuzj {279421824@qq.com}
 */
@Service
public class E01Z1ServiceImpl extends BaseServiceImpl<E01Z1,String>
        implements E01Z1Service {

    private E01Z1Dao e01Z1Dao;

    @Resource
    public void setBaseDao(BaseDao<E01Z1, String> baseDao) {
        this.baseDao = baseDao;
        this.e01Z1Dao = (E01Z1Dao)baseDao;
    }

    public Integer getMaxSort(String a38Id,String e01Z101B) {
        Map<String, Object> map=new HashMap<String, Object>();
        String hql = "select max(e.e01Z104)+1 as sort from e01z1 e ";
        if(a38Id!=null && !a38Id.equals("")) {
            hql = hql+"where e.a38.id =:a38Id";
            map.put("a38Id", a38Id);
        }else{
            hql = hql+"where e.a38 is null";
        }

        if(e01Z101B!=null && !e01Z101B.equals("")) {
            hql = hql+" and e.e01Z101B =:e01Z101B";
            map.put("e01Z101B", e01Z101B);
        }else{
            hql = hql+" and e.e01Z101B is null";
        }

        List<Map> maxSorts = this.e01Z1Dao.list(hql, map);
        if(maxSorts.get(0).get("sort")==null){
            return 1;
        }else{
            Integer maxSort = ((Number) maxSorts.get(0).get("sort")).intValue();
            return maxSort;
        }
    }

    public void updateE01Z1(E01Z1 e01z1, String oldPid, Integer oldSort){
        String newParentId = "";
        if(e01z1.getA38()!=null){
            newParentId = e01z1.getA38().getId();
        }
        this.updateSort(e01z1, oldSort);
        this.e01Z1Dao.update(e01z1);
    }

    private void updateSort(E01Z1 e01z1, Integer oldSort)  {
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        CommonConditionQuery query = new CommonConditionQuery();
        Integer newSort = e01z1.getE01Z104();
        String sql="update e01z1 t set ";
        if(newSort>oldSort){
            sql+="t.e01z104=t.e01z104-1";
        }else{
            sql+="t.e01z104=t.e01z104+1";
        }

        sql +=" where t.tenant_id = '" + details.getTenantId()+"'";

        if(newSort>oldSort){
            sql+=" and t.e01z104<="+newSort+" and t.e01z104 >"+oldSort;
        }else{
            if(newSort==oldSort){
                sql+=" and t.e01z104 = -100";
            }else{
                sql+=" and t.e01z104<"+oldSort+" and t.e01z104>="+newSort;
            }
        }
        this.e01Z1Dao.executeNativeBulk(sql,query);
    }
}
