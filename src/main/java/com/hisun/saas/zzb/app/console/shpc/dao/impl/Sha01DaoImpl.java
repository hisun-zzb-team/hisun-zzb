/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.shpc.dao.impl;

import com.hisun.saas.sys.tenant.base.dao.imp.TenantBaseDaoImpl;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01Dao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.util.UUIDUtil;
import com.hisun.util.WordUtil;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by zhouying on 2017/9/13.
 */
@Repository
public class Sha01DaoImpl extends TenantBaseDaoImpl<Sha01, String> implements Sha01Dao {

    public void saveFromWordDataMap(Tenant tenant, Map<String, String> dataMap, String pcId) {

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        int maxRow = this.getMaxRowFromWordDataMap(dataMap);

        for (int i = 0; i < maxRow; i++) {
            Map<String, String> rowMap = new HashMap<String, String>();
            //找出每一行的Map
            for (Iterator<String> it = dataMap.keySet().iterator(); it.hasNext(); ) {
                String key = it.next();
                String value = dataMap.get(key);
                if (i == getRowIndex(key)) {
                    rowMap.put(key, value);
                }
            }
            if (rowMap.size() > 0) {
                list.add(rowMap);
            }
            if (rowMap.size() == 0) {
                break;
            }
        }

        int px = 1;
        for (Map<String, String> map : list) {
            //构造每一行的insert语句
            String idValue = UUIDUtil.getUUID();
            StringBuffer insertSql = new StringBuffer("INSERT INTO ");
            insertSql.append("APP_SH_A01");
            insertSql.append(" (");
            //生成字段sql,值sql
            StringBuffer fieldSql = new StringBuffer();
            fieldSql.append(" ID,APP_SH_PC_ID,A01_PX,tenant_id ");
            StringBuffer valueSql = new StringBuffer();


            valueSql.append("(");
            valueSql.append("'").append(idValue).append("'");
            valueSql.append(",'").append(pcId).append("'");
            valueSql.append(",").append(px).append("");
            valueSql.append(",'").append(tenant.getId()).append("'");

            for (Iterator<String> it = map.keySet().iterator(); it.hasNext(); ) {
                String key = it.next();
                String value = dataMap.get(key);
                fieldSql.append(",");
                fieldSql.append(WordUtil.getSqlField(key));
                if(key.toLowerCase().indexOf("app_sh_a01.xm")>-1){
                    if(value.indexOf("△")>-1){
                        valueSql.append(",");
                        valueSql.append("'").append(value.substring(1,value.length())).append("'");

                        fieldSql.append(",");
                        fieldSql.append("jsbs");
                        valueSql.append(",'△'");
                    }else{
                        valueSql.append(",");
                        valueSql.append("'").append(value).append("'");
                    }
                }else {
                    valueSql.append(",");
                    valueSql.append("'").append(value).append("'");
                }

            }
            insertSql.append(fieldSql);
            insertSql.append(" )");

            insertSql.append(" VALUES ");
            insertSql.append(valueSql);
            insertSql.append(")");

            List<Object> paramList = new ArrayList<Object>();
            this.executeNativeBulk(insertSql.toString(),paramList);
            px++;
        }


    }




    private int getMaxRowFromWordDataMap(Map<String, String> dataMap) {
        int i = 0;
        if (dataMap != null) {
            for (Iterator<String> it = dataMap.keySet().iterator(); it.hasNext(); ) {
                String key = it.next();
                i = WordUtil.getRowCount(key);
                break;
            }
        }
        return i;
    }

    private int getRowIndex(String key) {
        return Integer.valueOf(key.substring(key.lastIndexOf(WordUtil.dot) + 1)).intValue();
    }


}
