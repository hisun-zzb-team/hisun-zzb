/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbmc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcA01Dao;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcB01Dao;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcDao;
import com.hisun.saas.zzb.app.console.gbmc.entity.*;
import com.hisun.saas.zzb.app.console.gbmc.service.*;
import com.hisun.saas.zzb.app.console.gbtj.dao.GbtjDao;
import com.hisun.saas.zzb.app.console.gbtj.entity.Gbtj;
import com.hisun.saas.zzb.app.console.gendata.service.GendataService;
import com.hisun.saas.sys.util.GzjlUtil;
import com.hisun.util.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class GbMcServiceImpl extends BaseServiceImpl<GbMc,String> implements GbMcService {

    private GbMcDao gbMcDao;
    @Resource
    private GbMcB01Dao gbMcB01Dao;
    @Resource
    private GbMcA01Dao gbMcA01Dao;

    @Resource
    private GbMcB01Service gbMcB01Service;
    @Resource
    private GbMcA01Service gbMcA01Service;
    @Resource
    private GbMcA01gbrmspbService gbMcA01gbrmspbService;
    @Resource
    private GbMcA01gzjlService gbMcA01gzjlService;

    @Value("${sys.upload.absolute.path}")
    private String uploadAbsolutePath;

    @Autowired
    public void setBaseDao(BaseDao<GbMc, String> gbMcDao) {
        this.baseDao = gbMcDao;
        this.gbMcDao = (GbMcDao) gbMcDao;
    }


    @Override
    public int getA01Count(String id) throws Exception {
        int a01Count = 0;
        GbMc gbmc = this.getByPK(id);
//        List<GbMcB01> mcBo1s = gbmc.getGbMcB01s();
//        if(mcBo1s!=null && mcBo1s.size()>0){
//            for(GbMcB01 gbMcB01 : mcBo1s){
//                a01Count = a01Count+gbMcB01.getGbMcA01s().size();
//            }
//        }
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
        query.add(CommonRestrictions.and(" gbMc.id = :mcid", "mcid", id));
        return ((Long)gbMcA01Dao.count(query)).intValue();
    }


    public void saveFromWordDataMap(GbMc gbMc, List<Map<String, String>> dataList){
        this.gbMcDao.save(gbMc);
        //判断是否有目录
        int px =1;
        if(gbMc.getIsMl()==GbMc.YML){
            for(Map<String,String> dataMap : dataList){
                String b0101=this.getB0101(dataMap);
                GbMcB01 b01= new GbMcB01();
                b01.setB0101(b0101);
                b01.setGbMc(gbMc);
                b01.setIsDisplay(GbMcB01.DISPLAY);
                b01.setPx(px);
                b01.setTenant(gbMc.getTenant());
                this.gbMcB01Dao.save(b01);
                this.gbMcB01Dao.flush();//执行sql,不然原生SQL认不到b01.id
                this.gbMcA01Dao.saveFromWordDataMap(dataMap,b01);
                px++;
            }
        }else{
            GbMcB01 b01= new GbMcB01();
            b01.setB0101("隐藏目录");
            b01.setGbMc(gbMc);
            b01.setIsDisplay(GbMcB01.HIDDEN);
            b01.setPx(1);
            b01.setTenant(gbMc.getTenant());
            this.gbMcB01Dao.save(b01);
            this.gbMcB01Dao.flush();
            for(Map<String,String> dataMap : dataList){
                this.gbMcA01Dao.saveFromWordDataMap(dataMap,b01);
                px++;
            }

        }
    }

    private String getB0101(Map<String,String> dataMap){
        String s ="";
        String key = "";
        for(Iterator<String> it = dataMap.keySet().iterator(); it.hasNext();){
            key = it.next();
            String value = dataMap.get(key);
            if(key.toLowerCase().indexOf("b0101")>-1){
                s= value;
                break;
            }
        }
        dataMap.remove(key);
        return s;
    }

    public String toSqliteInsertSql(GbMc entity){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" app_mc ");
        sb.append("(");
        sb.append("id");
        sb.append(",mc");
        sb.append(",px");
        sb.append(",is_ml");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.trimNull2Empty(entity.getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getMc())+"'");
        sb.append(","+entity.getPx());
        sb.append(",'"+entity.getIsMl()+"'");
        sb.append(")");
        return sb.toString();
    }


    public void saveAsSqlite(String mcId, String sqlite,String imgdir,String attsdir) throws Exception {
        GbMc gbMc = this.getPK(mcId);
        if (gbMc != null) {
            SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
            sqliteDBUtil.insert(sqlite, toSqliteInsertSql(gbMc));
            //单位目录
            List<GbMcB01> gbMcB01s = gbMc.getGbMcB01s();
            if (gbMcB01s != null) {
                for (GbMcB01 gbMcB01 : gbMcB01s) {
                    sqliteDBUtil.insert(sqlite, this.gbMcB01Service.toSqliteInsertSql(gbMcB01));
                    List<GbMcA01> gbMcA01s = gbMcB01.getGbMcA01s();
                    for (GbMcA01 gbMcA01 : gbMcA01s) {
                        //初始化结构数据
                        sqliteDBUtil.insert(sqlite, this.gbMcA01Service.toSqliteInsertSql(gbMcA01));
                        //初始化非机构化数据
//                        if (gbMcA01.getZppath() != null) {
//                            FileUtil.copyFile(uploadAbsolutePath+gbMcA01.getZppath(),
//                                    GendataService.APP_IMG_PATH+GbMcA01Service.APP_IMG_PATH);
//                        }
                        //工作经历
                        List<GbMcA01gzjl> gbMcA01gzjls = gbMcA01.getGbMca01gzjls();
                        if (gbMcA01gzjls != null) {
                            for (GbMcA01gzjl gbMcA01gzjl : gbMcA01gzjls) {
                                sqliteDBUtil.insert(sqlite, this.gbMcA01gzjlService.toSqliteInsertSql(gbMcA01gzjl));
                            }
                        }
                        //干部任免审批表
                        List<GbMcA01gbrmspb> gbMcA01gbrmspbs = gbMcA01.getGbMca01gbrmspbs();
                        if (gbMcA01gbrmspbs != null) {
                            for (GbMcA01gbrmspb gbrmspb : gbMcA01gbrmspbs) {
                                sqliteDBUtil.insert(sqlite, this.gbMcA01gbrmspbService.toSqliteInsertSql(gbrmspb));
                                if (gbrmspb.getFile2imgPath() != null) {
                                    FileUtil.copyFile(uploadAbsolutePath+gbrmspb.getFile2imgPath(),
                                            attsdir+GbMcA01gbrmspbService.ATTS_PATH);
                                }
                            }
                        }

                    }
                }
            }
        }
    }



}
