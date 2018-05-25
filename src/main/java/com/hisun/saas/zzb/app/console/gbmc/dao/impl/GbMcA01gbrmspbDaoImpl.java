/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbmc.dao.impl;

import com.hisun.saas.sys.tenant.base.dao.imp.TenantBaseDaoImpl;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcA01gbrmspbDao;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01gbrmspb;
import com.hisun.util.UUIDUtil;
import com.hisun.util.WordUtil;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by zhouying on 2017/9/12.
 */
@Repository
public class GbMcA01gbrmspbDaoImpl extends TenantBaseDaoImpl<GbMcA01gbrmspb, String> implements GbMcA01gbrmspbDao {

    public String saveFromWord(GbMcA01gbrmspb gbrmspb , Map<String, String> wordDataMap) {
        String idValue = UUIDUtil.getUUID();
        StringBuffer insertSql = new StringBuffer("INSERT INTO ");
        insertSql.append("APP_MC_A01_GBRMSPB");
        insertSql.append(" (");
        //生成字段sql,值sql
        StringBuffer fieldSql = new StringBuffer();
        fieldSql.append("ID,APP_MC_A01_ID,FILE2IMG_PATH,FILE_PATH");
        StringBuffer valueSql = new StringBuffer();

        valueSql.append("(");
        valueSql.append("'").append(idValue).append("'");
        valueSql.append(",'").append(gbrmspb.getGbMcA01().getId()).append("'");
        valueSql.append(",'").append(gbrmspb.getFile2imgPath()).append("'");
        valueSql.append(",'").append(gbrmspb.getFilepath()).append("'");

        Map<String,String> listDataMap = new HashMap<String,String>();
        for (Iterator<String> it = wordDataMap.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            String value = wordDataMap.get(key);
            //组合主表以"["或"#image"开头的
            if(key.startsWith(WordUtil.dataPrefix)) {
                fieldSql.append(",");
                fieldSql.append(WordUtil.getSqlField(key));
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

        return idValue;

    }

}
