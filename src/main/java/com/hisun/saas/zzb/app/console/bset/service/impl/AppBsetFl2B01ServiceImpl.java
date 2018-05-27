/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.bset.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA01;
import com.hisun.saas.zzb.app.console.bset.dao.AppBsetB01Dao;
import com.hisun.saas.zzb.app.console.bset.dao.AppBsetFl2B01Dao;
import com.hisun.saas.zzb.app.console.bset.dao.AppBsetFlDao;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetB01;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetFl;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetFl2B01;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetFl2B01Service;
import com.hisun.util.StringUtils;
import com.hisun.util.UUIDUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class AppBsetFl2B01ServiceImpl extends BaseServiceImpl<AppBsetFl2B01,String> implements AppBsetFl2B01Service {

    private AppBsetFl2B01Dao appBsetFl2B01Dao;
    @Autowired
    private AppBsetFlDao appBsetFlDao;
    @Autowired
    private AppBsetB01Dao appBsetB01Dao;

    @Autowired
    public void setBaseDao(BaseDao<AppBsetFl2B01, String> appBsetFl2B01Dao) {
        this.baseDao = appBsetFl2B01Dao;
        this.appBsetFl2B01Dao = (AppBsetFl2B01Dao) appBsetFl2B01Dao;
    }



    public int saveFromYw(DataSource dataSource)throws Exception{
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        //处理了多少条
        int order = 0;
        Connection conn = dataSource.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        String sql = "select top 600 * from sm_unitCatalog ";
        List<Map<String, Object>> list = queryRunner.query(conn, sql, new MapListHandler(),(Object[]) null);
        for (Iterator<Map<String, Object>> li = list.iterator(); li.hasNext();) {
            Map<String, Object> m = li.next();

            StringBuffer fields = new StringBuffer();
            fields.append("insert into app_bset_fl_2_b01 (");
            fields.append(" tombstone,id,tenant_id,create_user_id,create_user_name ");
            StringBuffer values = new StringBuffer();
            values.append(") values (");
            values.append(" 0,'"+ UUIDUtil.getUUID()+"'");
            values.append(",'").append(userLoginDetails.getTenant().getId()).append("'")
                    .append(",'").append(userLoginDetails.getUser().getId()).append("'")
                    .append(",'").append(userLoginDetails.getUsername()).append("'");

            String b01Id="b01Id";
            String flId="flId";
            for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                Map.Entry<String, Object> e = mi.next();
                String key = e.getKey();
                Object value = e.getValue()==null?"":e.getValue();
                if(key.equalsIgnoreCase("nodeID")){
                    fields.append(",fl_id");
                    values.append(",'"+value+"'");
                    flId = value.toString();
                }else if(key.equalsIgnoreCase("unitCodeNo")){
                    fields.append(",b01_id");
                    values.append(",'"+value+"'");
                    b01Id = value.toString();
                }else if(key.equalsIgnoreCase("sortID")){
                    fields.append(",px");
                    values.append(",'"+value+"'");
                }

            }
            values.append(")");

            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" id = :b01Id", "b01Id", b01Id));
            List<AppBsetB01> appBsetB01s = this.appBsetB01Dao.list(query,null);

            CommonConditionQuery flQuery = new CommonConditionQuery();
            flQuery.add(CommonRestrictions.and(" id = :flId", "flId", flId));
            List<AppBsetFl> appBsetFls =  this.appBsetFlDao.list(flQuery,null);

            if(appBsetB01s!=null && appBsetB01s.size()>0
                    && appBsetFls!=null && appBsetFls.size()>0) {
                List<Object> paramList = new ArrayList<Object>();
                this.appBsetFl2B01Dao.executeNativeBulk(fields.append(values).toString(), paramList);
                order++;
            }
        }

        DbUtils.close(conn);
        return order;
    }

    public  int saveFromZdwx(DataSource dataSource)throws Exception{
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        List<AppBsetB01> appBsetB01s = this.appBsetB01Dao.find("from AppBsetB01 b01 where b01.parentB01.id is null",null,null);
        List<AppBsetFl> appBsetFls =  this.appBsetFlDao.find("from AppBsetFl fl where fl.parentFl.id is null",null,null);
        AppBsetFl2B01 appBsetFl2B01 = new AppBsetFl2B01();
        appBsetFl2B01.setPx(1);
        appBsetFl2B01.setAppBsetB01(appBsetB01s.get(0));
        appBsetFl2B01.setAppBsetFl(appBsetFls.get(0));
        EntityWrapper.wrapperSaveBaseProperties(appBsetFl2B01,userLoginDetails);
        this.appBsetFl2B01Dao.save(appBsetFl2B01);
        return 1;
    }

    public String toSqliteInsertSql(AppBsetFl2B01 entity){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" app_bset_fl_2_b01 ");
        sb.append("(");
        sb.append("id");
        sb.append(",fl_id");
        sb.append(",b01_id");
        sb.append(",px");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.trimNull2Empty(entity.getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getAppBsetFl().getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getAppBsetB01().getId())+"'");
        sb.append(","+entity.getPx());
        sb.append(")");
        return sb.toString();
    }

}
