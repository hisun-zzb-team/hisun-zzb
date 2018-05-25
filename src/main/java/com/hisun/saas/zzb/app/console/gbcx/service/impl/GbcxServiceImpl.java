package com.hisun.saas.zzb.app.console.gbcx.service.impl;

import com.google.common.collect.Lists;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA01;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA02;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA01Service;
import com.hisun.saas.zzb.app.console.aset.service.AppAsetA02Service;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetB01;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetFl;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetFl2B01;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetB01Service;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetFl2B01Service;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetFlService;
import com.hisun.saas.zzb.app.console.gbcx.service.GbcxService;
import com.hisun.saas.zzb.app.console.gendata.service.GendataService;
import com.hisun.util.FileUtil;
import com.hisun.util.SqliteDBUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhouying on 2018/1/3.
 */
@Service
public class GbcxServiceImpl implements GbcxService {


    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    @Resource
    private AppBsetB01Service appBsetB01Service;
    @Resource
    private AppBsetFlService appBsetFlService;
    @Resource
    private AppBsetFl2B01Service appBsetFl2B01Service;
    @Resource
    private AppAsetA01Service appAsetA01Service;
    @Resource
    private AppAsetA02Service appAsetA02Service;

    public void saveAsSqlite(String sqlite,String imgdir,String attsdir) throws Exception {
        SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
        //分类及机构
        List<AppBsetFl> appBsetFls = this.appBsetFlService.list();
        for (AppBsetFl appBsetFl : appBsetFls) {
            sqliteDBUtil.insert(sqlite, this.appBsetFlService.toSqliteInsertSql(appBsetFl));
        }
        List<AppBsetB01> appBsetB01s = this.appBsetB01Service.list();
        for (AppBsetB01 b01 : appBsetB01s) {
            sqliteDBUtil.insert(sqlite,this.appBsetB01Service.toSqliteInsertSql(b01));
            List<AppBsetFl2B01> appBsetFl2B01s = b01.getAppBsetFl2B01s();
            for (AppBsetFl2B01 appBsetFl2B01 : appBsetFl2B01s) {
                sqliteDBUtil.insert(sqlite, this.appBsetFl2B01Service.toSqliteInsertSql(appBsetFl2B01));
            }
        }
        //干部信息

        AppBsetFl appBsetFl = this.appBsetFlService.getTopFl();
        List<Object> paramList = Lists.newArrayList();
        String hql = " from AppAsetA01 a01  inner join a01.appAsetA02s a02  "
                + " inner join a02.appBsetB01 b01 ";
        if(appBsetFl.getIsHidden()== AppBsetFl.DISPLAY){
            hql +="inner join b01.appBsetFl2B01s fltob01 ";
        }
        hql+= " where a01.tombstone =?";
        if(appBsetFl.getIsHidden()== AppBsetFl.DISPLAY) {
            hql = hql + " order by fltob01.px,b01.px,a02.jtlPx ";
        }else{
            hql = hql + " order by b01.queryCode,a02.jtlPx ";
        }
        paramList.add(0);
        int total = this.appAsetA01Service.count("select  count(distinct a01.id) " + hql, paramList);
        int dealCount = total/200;
        for(int i=1;i<=dealCount+1;i++) {
            List<AppAsetA01> appAsetA01s = this.appAsetA01Service.list("select  DISTINCT(a01) " + hql, paramList,i,200);
            for(AppAsetA01 appAsetA01: appAsetA01s){
                sqliteDBUtil.insert(sqlite, this.appAsetA01Service.toSqliteInsertSql(appAsetA01));
//            if(appAsetA01.getZpPath()!=null
//                    && appAsetA01.getZpPath().isEmpty()==false){
//                this.copyFile(appAsetA01.getZpPath(),imgDir);
//            }
                if(appAsetA01.getFile2ImgPath()!=null
                        &&appAsetA01.getFile2ImgPath().isEmpty()==false){
                    FileUtil.copyFile(uploadAbsolutePath+appAsetA01.getFile2ImgPath(),
                            attsdir+AppAsetA01Service.GBRMSPB_PATH);
                }
                List<AppAsetA02> appAsetA02s = appAsetA01.getAppAsetA02s();
                for(AppAsetA02 appAsetA02 : appAsetA02s){
                    sqliteDBUtil.insert(sqlite, this.appAsetA02Service.toSqliteInsertSql(appAsetA02));
                }
            }
        }


    }
}
