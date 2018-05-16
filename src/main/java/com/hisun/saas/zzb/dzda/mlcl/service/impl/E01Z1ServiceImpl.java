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
import com.hisun.saas.zzb.dzda.mlcl.entity.EImages;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.saas.zzb.dzda.mlcl.dao.E01Z1Dao;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhout {605144321@qq.com}
 */
@Service
public class E01Z1ServiceImpl extends BaseServiceImpl<E01Z1,String>
        implements E01Z1Service {

    @Value("${sys.upload.absolute.path}")
    private String uploadBasePath;
    private E01Z1Dao e01Z1Dao;

    @Resource
    public void setBaseDao(BaseDao<E01Z1, String> baseDao) {
        this.baseDao = baseDao;
        this.e01Z1Dao = (E01Z1Dao)baseDao;
    }

    public Integer getMaxSort(String a38Id,String e01Z101B) {
        Map<String, Object> map=new HashMap<String, Object>();
        String hql = "select max(e.e01Z104)+1 as sort from E01Z1 e ";
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

//    public Integer getMaxSmSort(String a38Id,String e01Z101B) {
//        Map<String, Object> map=new HashMap<String, Object>();
//        String hql = "select max(e.e01Z107)+1 as sort from E01Z1 e ";
//        if(a38Id!=null && !a38Id.equals("")) {
//            hql = hql+"where e.a38.id =:a38Id";
//            map.put("a38Id", a38Id);
//        }else{
//            hql = hql+"where e.a38 is null";
//        }
//
//        if(e01Z101B!=null && !e01Z101B.equals("")) {
//            hql = hql+" and e.e01Z101B =:e01Z101B";
//            map.put("e01Z101B", e01Z101B);
//        }else{
//            hql = hql+" and e.e01Z101B is null";
//        }
//
//        List<Map> maxSorts = this.e01Z1Dao.list(hql, map);
//        if(maxSorts.get(0).get("sort")==null){
//            return 1;
//        }else{
//            Integer maxSort = ((Number) maxSorts.get(0).get("sort")).intValue();
//            return maxSort;
//        }
//    }

    public void updateE01Z1(E01Z1 e01z1, Integer oldSort){
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

        sql +=" where t.tenant_id = '" + details.getTenantId() + "'"
                + " and t.a38_id = '" + e01z1.getA38().getId() + "'"
                + " and t.e01z101b = '" + e01z1.getE01Z101B() + "'";

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

    public void updateSortBeforSave(E01Z1 e01z1, Integer oldSort)  {
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        CommonConditionQuery query = new CommonConditionQuery();
        Integer newSort = e01z1.getE01Z104();
        String sql="update e01z1 t set ";
        sql+="t.e01z104=t.e01z104+1";

        sql +=" where t.tenant_id = '" + details.getTenantId() + "'"
                + " and t.a38_id = '" + e01z1.getA38().getId() + "'"
                + " and t.e01z101b = '" + e01z1.getE01Z101B() + "'";

        sql+=" and t.e01z104<"+oldSort+" and t.e01z104>="+newSort;
        this.e01Z1Dao.executeNativeBulk(sql,query);
    }

    @Override
    public void delete(E01Z1 e01Z1){
        List<EImages> eImages = e01Z1.getImages();
        for(EImages eImage : eImages){
            FileUtils.deleteQuietly(new File(uploadBasePath+eImage.getImgFilePath()));
        }
        this.e01Z1Dao.delete(e01Z1);

    }
}
