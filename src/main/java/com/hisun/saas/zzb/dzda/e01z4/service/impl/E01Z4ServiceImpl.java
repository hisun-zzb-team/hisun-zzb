/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.e01z4.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.dzda.e01z4.dao.E01Z4Dao;
import com.hisun.saas.zzb.dzda.e01z4.entity.E01Z4;
import com.hisun.saas.zzb.dzda.e01z4.service.E01Z4Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhout {605144321@qq.com}
 */
@Service
public class E01Z4ServiceImpl extends BaseServiceImpl<E01Z4,String>
        implements E01Z4Service {

    private E01Z4Dao e01Z4Dao;

    @Resource
    public void setBaseDao(BaseDao<E01Z4, String> baseDao) {
        this.baseDao = baseDao;
        this.e01Z4Dao = (E01Z4Dao)baseDao;
    }

    public Integer getMaxSort(String a38Id) {
        Map<String, Object> map=new HashMap<String, Object>();
        String hql = "select max(e.px)+1 as sort from E01Z4 e ";
        if(a38Id!=null && !a38Id.equals("")) {
            hql = hql+"where e.a38.id =:a38Id";
            map.put("a38Id", a38Id);
        }else{
            hql = hql+"where e.a38 is null";
        }

        List<Map> maxSorts = this.e01Z4Dao.list(hql, map);
        if(maxSorts.get(0).get("sort")==null){
            return 1;
        }else{
            Integer maxSort = ((Number) maxSorts.get(0).get("sort")).intValue();
            return maxSort;
        }
    }

    public void updateE01Z4(E01Z4 e01z4, Integer oldSort){
        this.updateSort(e01z4, oldSort);
        this.e01Z4Dao.update(e01z4);
    }

    private void updateSort(E01Z4 e01z1, Integer oldSort)  {
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        CommonConditionQuery query = new CommonConditionQuery();
        Integer newSort = e01z1.getPx();
        String sql="update e01z4 t set ";
        if(newSort>oldSort){
            sql+="t.px=t.px-1";
        }else{
            sql+="t.px=t.px+1";
        }

        sql +=" where t.a38_id = '" + e01z1.getA38().getId() + "'";

        if(newSort>oldSort){
            sql+=" and t.px<="+newSort+" and t.px >"+oldSort;
        }else{
            if(newSort==oldSort){
                sql+=" and t.px = -100";
            }else{
                sql+=" and t.px<"+oldSort+" and t.px>="+newSort;
            }
        }
        this.e01Z4Dao.executeNativeBulk(sql,query);
    }
}
