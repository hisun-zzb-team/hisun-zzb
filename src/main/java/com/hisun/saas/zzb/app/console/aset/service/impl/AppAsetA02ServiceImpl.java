package com.hisun.saas.zzb.app.console.aset.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.aset.dao.AppAsetA02Dao;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA01;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA02;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA01Service;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA02Service;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetB01;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetB01Service;
import com.hisun.util.DateUtil;
import com.hisun.util.StringUtils;
import com.hisun.util.UUIDUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.*;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class AppAsetA02ServiceImpl extends BaseServiceImpl<AppAsetA02,String> implements AppAsetA02Service {

    private AppAsetA02Dao appAsetA02Dao;

    @Resource
    private AppAsetA01Service appAsetA01Service;
    @Resource
    private AppBsetB01Service appBsetB01Service;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    @Autowired
    public void setBaseDao(BaseDao<AppAsetA02, String> appAsetA02Dao) {
        this.baseDao = appAsetA02Dao;
        this.appAsetA02Dao = (AppAsetA02Dao) appAsetA02Dao;
    }


    public int saveFromYw(DataSource dataSource)throws Exception{
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();

        //处理了多少条
        int order = 0;
        Connection conn = dataSource.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        int count =0;
        List<Map<String, Object>> countList = queryRunner.query(conn,
                " select count(*) as count from a001 where a001.a001_a0255 = '1' ", new MapListHandler(),(Object[]) null);
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
            String sql = " SELECT top 400 * FROM A001 where a001.a001_a0255 = '1' AND A001.id not in "
                    + "(select top "+num+" A001.id from A001 WHERE a001.a001_a0255 = '1')";
            List<Map<String, Object>> list = queryRunner.query(conn, sql, new MapListHandler(),(Object[]) null);
            for (Iterator<Map<String, Object>> li = list.iterator(); li.hasNext();) {
                Map<String, Object> m = li.next();
                StringBuffer fields = new StringBuffer();

                fields.append("insert into app_aset_a02 (");
                fields.append(" id,tombstone,tenant_id,create_user_id,create_user_name");
                StringBuffer values = new StringBuffer();
                values.append(") values (");
                values.append(" '"+ UUIDUtil.getUUID()+"' ,");
                values.append(" 0 ");
                values.append(",'").append(userLoginDetails.getTenant().getId()).append("'")
                        .append(",'").append(userLoginDetails.getUser().getId()).append("'")
                        .append(",'").append(userLoginDetails.getUsername()).append("'");
                String personCode = "personCode";
                String b01Id="b01Id";
                for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                    Map.Entry<String, Object> e = mi.next();
                    String key = e.getKey();
                    Object value = e.getValue()==null?"":e.getValue();
                    if(key.equalsIgnoreCase("PERSONCODE")){
                        fields.append(",a01_id");
                        values.append(",'"+value+"'");
                        personCode = value.toString();
                    }else if(key.equalsIgnoreCase("A001_A0225")){
                        fields.append(",jtl_px");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A001_A0225A")){
                        fields.append(",px");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A001_A0201B")){
                        fields.append(",b01_id");
                        values.append(",'"+value+"'");
                        b01Id = value.toString();
                    }else if(key.equalsIgnoreCase("A001_A0215_SHOW")){
                        fields.append(",zwmc");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A001_A0253A")){
                        fields.append(",rzsj");
                        if(value.toString().equals("")==false){
                            values.append(",'"+ DateUtil.formatDateByFormat((Date) value,DateUtil.NOCHAR_PATTERN2)+"'");
                        }else{
                            values.append(",''");
                        }
                    }
                }
                values.append(")");
                //判断干部是否存在
                CommonConditionQuery query = new CommonConditionQuery();
                query.add(CommonRestrictions.and(" id = :personCode", "personCode", personCode));
                List<AppAsetA01> appAsetA01s =  this.appAsetA01Service.list(query,null);

                CommonConditionQuery b01Query = new CommonConditionQuery();
                b01Query.add(CommonRestrictions.and(" id = :b01Id", "b01Id", b01Id));
                List<AppBsetB01> appBsetB01s =  this.appBsetB01Service.list(b01Query,null);

                if(appAsetA01s!=null && appAsetA01s.size()>0
                        && appBsetB01s!=null && appBsetB01s.size()>0) {
                    List<Object> paramList = new ArrayList<Object>();
                    this.appAsetA02Dao.executeNativeBulk(fields.append(values).toString(), paramList);
                    order++;
                }
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
                " select count(*) as count from a02 where a02.a0255 = '1' and a02.a0201B <>'-1' ", new MapListHandler(),(Object[]) null);
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
            String sql = " SELECT  * FROM a02 where a02.a0255 = '1' and a02.a0201B <>'-1' limit " + num + ",400";
            List<Map<String, Object>> list = queryRunner.query(conn, sql, new MapListHandler(),(Object[]) null);
            for (Iterator<Map<String, Object>> li = list.iterator(); li.hasNext();) {
                Map<String, Object> m = li.next();
                StringBuffer fields = new StringBuffer();

                fields.append("insert into app_aset_a02 (");
                fields.append(" id,tombstone,tenant_id,create_user_id,create_user_name");
                StringBuffer values = new StringBuffer();
                values.append(") values (");
                values.append(" '"+ UUIDUtil.getUUID()+"' ,");
                values.append(" 0 ");
                values.append(",'").append(userLoginDetails.getTenant().getId()).append("'")
                        .append(",'").append(userLoginDetails.getUser().getId()).append("'")
                        .append(",'").append(userLoginDetails.getUsername()).append("'");
                String personCode = "personCode";
                String b01Id="b01Id";
                for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                    Map.Entry<String, Object> e = mi.next();
                    String key = e.getKey();
                    Object value = e.getValue()==null?"":e.getValue();
                    if(key.equalsIgnoreCase("a0000")){
                        fields.append(",a01_id");
                        values.append(",'"+value+"'");
                        personCode = value.toString();
                    }else if(key.equalsIgnoreCase("A0225")){
                        if(StringUtils.isEmpty(value.toString())==false){
                            fields.append(",jtl_px");
                            values.append(",'"+value+"'");
                            fields.append(",px");
                            values.append(",'"+value+"'");
                        }else {
                            fields.append(",jtl_px");
                            values.append(",'"+99+"'");
                            fields.append(",px");
                            values.append(",'"+99+"'");
                        }

                    }else if(key.equalsIgnoreCase("A0201B")){
                        fields.append(",b01_id");
                        values.append(",'"+value+"'");
                        b01Id = value.toString();
                    }else if(key.equalsIgnoreCase("A0215A")){
                        fields.append(",zwmc");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("A001_A0243")){
                        fields.append(",rzsj");
                        values.append(",'"+value+"'");
                    }
                }
                values.append(")");
                //判断干部是否存在
                CommonConditionQuery query = new CommonConditionQuery();
                query.add(CommonRestrictions.and(" id = :personCode", "personCode", personCode));
                List<AppAsetA01> appAsetA01s =  this.appAsetA01Service.list(query,null);

                CommonConditionQuery b01Query = new CommonConditionQuery();
                b01Query.add(CommonRestrictions.and(" id = :b01Id", "b01Id", b01Id));
                List<AppBsetB01> appBsetB01s =  this.appBsetB01Service.list(b01Query,null);

                if(appAsetA01s!=null && appAsetA01s.size()>0
                        && appBsetB01s!=null && appBsetB01s.size()>0) {
                    List<Object> paramList = new ArrayList<Object>();
                    this.appAsetA02Dao.executeNativeBulk(fields.append(values).toString(), paramList);
                    order++;
                }

               // List<Object> paramList = new ArrayList<Object>();
               // this.appAsetA02Dao.executeNativeBulk(fields.append(values).toString(), paramList);
               // order++;
            }
        }

        DbUtils.close(conn);
        return order;
    }


    public String toSqliteInsertSql(AppAsetA02 entity){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" app_aset_a02 ");
        sb.append("(");
        sb.append("id");
        sb.append(",a01_id");
        sb.append(",b01_id");
        sb.append(",zwmc");
        sb.append(",rzsj");
        sb.append(",px");
        sb.append(",jtl_px");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.trimNull2Empty(entity.getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getAppAsetA01().getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getAppBsetB01().getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getZwmc())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getRzsj())+"'");
        sb.append(","+entity.getPx());
        sb.append(","+entity.getJtlPx());
        sb.append(")");
        return sb.toString();
    }

}
