/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.bset.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.bset.dao.AppBsetFl2B01Dao;
import com.hisun.saas.zzb.app.console.bset.dao.AppBsetFlDao;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetFl;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetFl2B01;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetFlService;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.util.StringUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.ConnectionEvent;
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
public class AppBsetFlServiceImpl extends BaseServiceImpl<AppBsetFl,String> implements AppBsetFlService {

    private AppBsetFlDao appBsetFlDao;

    @Autowired
    private AppBsetFl2B01Dao appBsetFl2B01Dao;

    @Autowired
    public void setBaseDao(BaseDao<AppBsetFl, String> appBsetFlDao) {
        this.baseDao = appBsetFlDao;
        this.appBsetFlDao = (AppBsetFlDao) appBsetFlDao;
    }


    public int saveFromYw(DataSource dataSource)throws Exception{
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        //处理了多少条
        int order = 0;
        Connection conn = dataSource.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = " select * from sm_unitTreeNode  where sm_unitTreeNode.parentNodeId ='0' ";
        List<Map<String, Object>> list = queryRunner.query(conn, sql, new MapListHandler(), (Object[]) null);

        String sql2 = " select * from sm_unitTreeNode  where sm_unitTreeNode.parentNodeId in (select nodeId from sm_unitTreeNode) ";
        List<Map<String, Object>> list2 = queryRunner.query(conn, sql2, new MapListHandler(), (Object[]) null);
        list.addAll(list2);
        for (Iterator<Map<String, Object>> li = list.iterator(); li.hasNext(); ) {
            Map<String, Object> m = li.next();

            StringBuffer fields = new StringBuffer();
            fields.append("insert into app_bset_fl (");
            fields.append(" tombstone,tenant_id,create_user_id,create_user_name ");
            StringBuffer values = new StringBuffer();
            values.append(") values (");
            values.append(" 0 ");
            values.append(",'").append(userLoginDetails.getTenant().getId()).append("'")
                    .append(",'").append(userLoginDetails.getUser().getId()).append("'")
                    .append(",'").append(userLoginDetails.getUsername()).append("'");

            for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext(); ) {
                Map.Entry<String, Object> e = mi.next();
                String key = e.getKey();
                Object value = e.getValue() == null ? "" : e.getValue();
                if (key.equalsIgnoreCase("nodeID")) {
                    fields.append(",id");
                    values.append(",'" + value + "'");
                } else if (key.equalsIgnoreCase("parentNodeID")) {
                    //特殊处理顶层节点
                    if (value.toString() == null || value.toString().equals("0")) {
                    } else {
                        fields.append(",parent_id");
                        values.append(",'" + value + "'");
                    }

                } else if (key.equalsIgnoreCase("nodeName")) {
                    fields.append(",fl");
                    values.append(",'" + value + "'");
                } else if (key.equalsIgnoreCase("sortID")) {
                    fields.append(",px");
                    values.append(",'" + value + "'");
                }

            }
            values.append(")");
            List<Object> paramList = new ArrayList<Object>();
            this.appBsetFlDao.executeNativeBulk(fields.append(values).toString(), paramList);
            order++;
        }

        DbUtils.close(conn);
        return order;
    }

    public void deleteAllData() throws Exception{
        this.appBsetFl2B01Dao.deleteBatch(null);
        this.appBsetFlDao.deleteBatch(null);
    }

    public  int saveFromZdwx(DataSource dataSource)throws Exception{
        //自动生成隐藏分类及关联关系
        List<AppBsetFl> appBsetFls =  this.appBsetFlDao.find("from AppBsetFl fl where fl.parentFl.id is null",null,null);
        if(appBsetFls==null || appBsetFls.size()==0) {
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            AppBsetFl fl = new AppBsetFl();
            fl.setFl("隐藏分类");
            fl.setPx(1);
            fl.setIsHidden(AppBsetFl.HIDDEN);
            EntityWrapper.wrapperSaveBaseProperties(fl, userLoginDetails);
            this.appBsetFlDao.save(fl);
        }
        return 1;
    }


    public AppBsetFl getTopFl(){
        List<AppBsetFl> appBsetFls =  this.appBsetFlDao.find("from AppBsetFl fl where fl.parentFl.id is null",null,null);
        if(appBsetFls!=null && appBsetFls.size()>0){
            return appBsetFls.get(0);
        }
        return null;
    }

    public String toSqliteInsertSql(AppBsetFl entity){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" app_bset_fl ");
        sb.append("(");
        sb.append("id");
        sb.append(",fl");
        if(entity.getParentFl()!=null) {
            sb.append(",parent_id");
        }
        sb.append(",px");
        sb.append(",is_hidden");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.trimNull2Empty(entity.getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getFl())+"'");
        if(entity.getParentFl()!=null){
            sb.append(",'" + StringUtils.trimNull2Empty(entity.getParentFl().getId()) + "'");
        }
        sb.append(","+entity.getPx());
        sb.append(","+entity.getIsHidden());
        sb.append(")");
        return sb.toString();
    }

}
