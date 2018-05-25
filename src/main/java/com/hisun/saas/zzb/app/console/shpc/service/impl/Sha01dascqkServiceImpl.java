package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.gendata.service.GendataService;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01dascqkDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01dascqk;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01dascqkService;
import com.hisun.util.FileUtil;
import com.hisun.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class Sha01dascqkServiceImpl extends BaseServiceImpl<Sha01dascqk,String> implements Sha01dascqkService {

    private Sha01dascqkDao sha01dascqkDao;

    @Autowired
    public void setBaseDao(BaseDao<Sha01dascqk, String> sha01dascqkDao) {
        this.baseDao = sha01dascqkDao;
        this.sha01dascqkDao = (Sha01dascqkDao) sha01dascqkDao;
    }


    public String toSqliteInsertSql(Sha01dascqk entity){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" APP_SH_A01_DASCQK ");
        sb.append("(");
        sb.append("ID");
        sb.append(",APP_SH_A01_ID");
        sb.append(",PATH");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.trimNull2Empty(entity.getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getSha01().getId())+"'");
        if (StringUtils.isEmpty(entity.getFile2imgPath())){
            sb.append(",''");
        }else{
            String attsPath = GendataService.APP_ATTS_PATH+Sha01dascqkService.APP_ATTS_PATH
                    + FileUtil.getFileName(entity.getFile2imgPath());
            sb.append(",'"+attsPath+"'");

        }
        sb.append(")");
        return sb.toString();
    }

}