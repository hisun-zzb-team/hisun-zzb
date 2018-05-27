package com.hisun.saas.zzb.app.console.aset.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.aset.dao.AppAsetA02Dao;
import com.hisun.saas.zzb.app.console.aset.dao.AppAsetA36Dao;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA01;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA36;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA01Service;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA36Service;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetB01;
import com.hisun.util.DateUtil;
import com.hisun.util.UUIDUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.*;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class AppAsetA36ServiceImpl extends BaseServiceImpl<AppAsetA36,String> implements AppAsetA36Service {

    private AppAsetA36Dao appAsetA36Dao;

    @Resource
    private AppAsetA01Service appAsetA01Service;


    @Autowired
    public void setBaseDao(BaseDao<AppAsetA36, String> appAsetA36Dao) {
        this.baseDao = appAsetA36Dao;
        this.appAsetA36Dao = (AppAsetA36Dao) appAsetA36Dao;
    }


    public int saveFromYw(DataSource dataSource)throws Exception{
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();

        //处理了多少条
        int order = 0;
        Connection conn = dataSource.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        int count =0;
        List<Map<String, Object>> countList = queryRunner.query(conn,
                " select count(*) as count from a035  ", new MapListHandler(),(Object[]) null);
        for (Iterator<Map<String, Object>> li = countList.iterator(); li.hasNext();) {
            Map<String, Object> m = li.next();
            for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                Map.Entry<String, Object> e = mi.next();
                Object value = e.getValue();
                count = ((Integer)value).intValue();
            }
        }
        //每次处理400条
        int dealCount = count/400;
        for(int i=0;i<=dealCount;i++){
            int num = i*400;
            String sql = " SELECT top 400 * FROM a035 where a035.id not in "
                    + "(select top "+num+" a035.id from a035)";
            List<Map<String, Object>> list = queryRunner.query(conn, sql, new MapListHandler(),(Object[]) null);
            for (Iterator<Map<String, Object>> li = list.iterator(); li.hasNext();) {
                Map<String, Object> m = li.next();
                StringBuffer fields = new StringBuffer();

                fields.append("insert into app_aset_a36 (");
                fields.append("tombstone,tenant_id,create_user_id,create_user_name");
                StringBuffer values = new StringBuffer();
                values.append(") values (");
                values.append(" 0 ");
                values.append(",'").append(userLoginDetails.getTenant().getId()).append("'")
                        .append(",'").append(userLoginDetails.getUser().getId()).append("'")
                        .append(",'").append(userLoginDetails.getUsername()).append("'");
                String personCode = "personCode";
                for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                    Map.Entry<String, Object> e = mi.next();
                    String key = e.getKey();
                    Object value = e.getValue()==null?"":e.getValue();
                    if(key.equalsIgnoreCase("PERSONCODE")){
                        fields.append(",a01_id");
                        values.append(",'"+value+"'");
                        personCode = value.toString();
                    }else if(key.equalsIgnoreCase("ID")){
                        fields.append(",id");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A035_A3601")){
                        fields.append(",xm");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A035_A3604B_SHOW")){
                        fields.append(",cw");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A035_A0108")){
                        fields.append(",nl");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A035_A3627_SHOW")){
                        fields.append(",zzmm");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A035_A3611A")){
                        fields.append(",gzdwjzw");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A035_A3607")){
                        fields.append(",csny");
                        if(value.toString().equals("")==false){
                            values.append(",'"+ DateUtil.formatDateByFormat((Date) value,DateUtil.NOCHAR_PATTERN2)+"'");
                        }else{
                            values.append(",''");
                        }
                    }
                }
                fields.append(",shgx_px");
                values.append(","+order);
                values.append(")");
                List<Object> paramList = new ArrayList<Object>();
                this.appAsetA36Dao.executeNativeBulk(fields.append(values).toString(), paramList);
                order++;
                //判断干部是否存在
//                CommonConditionQuery query = new CommonConditionQuery();
//                query.add(CommonRestrictions.and(" id = :personCode", "personCode", personCode));
//                List<AppAsetA01> appAsetA01s =  this.appAsetA01Service.list(query,null);
//
//                if(appAsetA01s!=null && appAsetA01s.size()>0) {
//                    List<Object> paramList = new ArrayList<Object>();
//                    this.appAsetA36Dao.executeNativeBulk(fields.append(values).toString(), paramList);
//                    order++;
//                }
            }
        }

        DbUtils.close(conn);
        return order;
    }



    public int saveFromZdwx(DataSource dataSource)throws Exception{
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();

        //处理了多少条
        int order = 0;
        Connection conn = dataSource.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        int count =0;
        List<Map<String, Object>> countList = queryRunner.query(conn,
                " select count(*) as count from a36  ", new MapListHandler(),(Object[]) null);
        for (Iterator<Map<String, Object>> li = countList.iterator(); li.hasNext();) {
            Map<String, Object> m = li.next();
            for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                Map.Entry<String, Object> e = mi.next();
                Object value = e.getValue();
                count = ((Long)value).intValue();
            }
        }
        //每次处理400条
        int dealCount = count/400;
        for(int i=0;i<=dealCount;i++){
            int num = i*400;
            String sql = " SELECT * FROM a36  limit " + num + ",400";
            List<Map<String, Object>> list = queryRunner.query(conn, sql, new MapListHandler(),(Object[]) null);
            for (Iterator<Map<String, Object>> li = list.iterator(); li.hasNext();) {
                Map<String, Object> m = li.next();
                StringBuffer fields = new StringBuffer();

                fields.append("insert into app_aset_a36 (");
                fields.append("tombstone,tenant_id,create_user_id,create_user_name");
                StringBuffer values = new StringBuffer();
                values.append(") values (");
                values.append(" 0 ");
                values.append(",'").append(userLoginDetails.getTenant().getId()).append("'")
                        .append(",'").append(userLoginDetails.getUser().getId()).append("'")
                        .append(",'").append(userLoginDetails.getUsername()).append("'");
                String personCode = "personCode";
                for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                    Map.Entry<String, Object> e = mi.next();
                    String key = e.getKey();
                    Object value = e.getValue()==null?"":e.getValue();
                    if(key.equalsIgnoreCase("a0000")){
                        fields.append(",a01_id");
                        values.append(",'"+value+"'");
                        personCode = value.toString();
                    }else if(key.equalsIgnoreCase("a3600")){
                        fields.append(",id");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A3601")){
                        fields.append(",xm");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A3604A")){
                        fields.append(",cw");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A3607")){
                        fields.append(",csny");
                        if (value.toString().equals("") == false) {
                            values.append(",'" + value + "'");
                            Calendar calendar = Calendar.getInstance();
                            int currentYear = calendar.get(Calendar.YEAR);
                            calendar.setTime(DateUtil.parseDate(value.toString().substring(0,4), "YYYY"));
                            int csn = calendar.get(Calendar.YEAR);
                            fields.append(",nl");
                            values.append(",'" + (currentYear - csn - 1) + "'");
                        } else {
                            values.append(",''");
                        }
                    }else if(key.equalsIgnoreCase("A3627")){
                        fields.append(",zzmm");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A3611")){
                        fields.append(",gzdwjzw");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("sortid")){
                        fields.append(",shgx_px");
                        values.append(",'"+value+"'");
                    }
                }

                values.append(")");
                List<Object> paramList = new ArrayList<Object>();
                this.appAsetA36Dao.executeNativeBulk(fields.append(values).toString(), paramList);
                order++;
                //判断干部是否存在
//                CommonConditionQuery query = new CommonConditionQuery();
//                query.add(CommonRestrictions.and(" id = :personCode", "personCode", personCode));
//                List<AppAsetA01> appAsetA01s =  this.appAsetA01Service.list(query,null);
//
//                if(appAsetA01s!=null && appAsetA01s.size()>0) {
//                    List<Object> paramList = new ArrayList<Object>();
//                    this.appAsetA36Dao.executeNativeBulk(fields.append(values).toString(), paramList);
//                    order++;
//                }
            }
        }

        DbUtils.close(conn);
        return order;
    }


}
