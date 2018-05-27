/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbmc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.util.GzjlUtil;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcA01gzjlDao;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01gzjl;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcA01gzjlService;

import com.hisun.util.StringUtils;
import com.hisun.util.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class GbMcA01gzjlServiceImpl extends BaseServiceImpl<GbMcA01gzjl,String> implements GbMcA01gzjlService {

    private GbMcA01gzjlDao GbMca01gzjlDao;

    @Autowired
    public void setBaseDao(BaseDao<GbMcA01gzjl, String> GbMca01gzjlDao) {
        this.baseDao = GbMca01gzjlDao;
        this.GbMca01gzjlDao = (GbMcA01gzjlDao) GbMca01gzjlDao;
    }



    public void saveFromWord(GbMcA01 gbMcA01, Map<String, String> dataMap){
        GbMcA01gzjl gzjl = null;
        String gzjlStr = null;
        Map<String,String> listDataMap = new HashMap<String,String>();
        for (Iterator<String> it = dataMap.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            //组合主表以"list"
            if(key.startsWith(WordUtil.listSign) && key.indexOf("APP_MC_A01_GZJL")>0) {
                gzjlStr= dataMap.get(key);
            }
        }
        //解析gzjlStr
        if(gzjlStr!=null){
            int px =1;
           List<String> list = GzjlUtil.matchGzjlStr(gzjlStr);
            for(String str : list){
                gzjl = new GbMcA01gzjl();
                gzjl.setGbMcA01(gbMcA01);
                gzjl.setTenant(gbMcA01.getTenant());
                gzjl.setJlsm(str);
                gzjl.setPx(px);
                px++;
                this.GbMca01gzjlDao.save(gzjl);
            }
        }

    }

    public String toSqliteInsertSql(GbMcA01gzjl entity){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" APP_MC_A01_GZJL ");
        sb.append("(");
        sb.append("ID");
        sb.append(",APP_MC_A01_ID");
        sb.append(",C_SJ");
        sb.append(",Z_SJ");
        sb.append(",JLSM");
        sb.append(",GZJL_PX");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.trimNull2Empty(entity.getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getGbMcA01().getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getCsj())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getZsj())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getJlsm())+"'");
        sb.append(","+ entity.getPx()+"");
        sb.append(")");
        return sb.toString();
    }

}