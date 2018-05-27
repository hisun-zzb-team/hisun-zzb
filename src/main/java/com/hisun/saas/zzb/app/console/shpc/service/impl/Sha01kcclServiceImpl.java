package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.gendata.entity.Gendata;
import com.hisun.saas.zzb.app.console.gendata.service.GendataService;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01kcclDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01kccl;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01kcclService;
import com.hisun.util.FileUtil;
import com.hisun.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class Sha01kcclServiceImpl extends BaseServiceImpl<Sha01kccl,String> implements Sha01kcclService {

    private Sha01kcclDao sha01kcclDao;

    @Autowired
    public void setBaseDao(BaseDao<Sha01kccl, String> sha01kcclDao) {
        this.baseDao = sha01kcclDao;
        this.sha01kcclDao = (Sha01kcclDao) sha01kcclDao;
    }



    public String toSqliteInsertSql(Sha01kccl kccl){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" APP_SH_A01_KCCL ");
        sb.append("(");
        sb.append("ID");
        sb.append(",APP_SH_A01_ID");
        sb.append(",PATH");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.trimNull2Empty(kccl.getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(kccl.getSha01().getId())+"'");
        if (StringUtils.isEmpty(kccl.getFile2imgPath())){
            sb.append(",''");
        }else{
            String attsPath = GendataService.APP_ATTS_PATH+Sha01kcclService.APP_ATTS_PATH
                    + FileUtil.getFileName(kccl.getFile2imgPath());
            sb.append(",'"+attsPath+"'");

        }
        sb.append(")");
        return sb.toString();
    }
}