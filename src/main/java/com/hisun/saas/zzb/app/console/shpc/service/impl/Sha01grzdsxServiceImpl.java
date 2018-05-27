/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.gendata.service.GendataService;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01grzdsxDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01grzdsx;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01grzdsxService;
import com.hisun.util.FileUtil;
import com.hisun.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class Sha01grzdsxServiceImpl extends BaseServiceImpl<Sha01grzdsx,String> implements Sha01grzdsxService {

    private Sha01grzdsxDao sha01grzdsxDao;

    @Autowired
    public void setBaseDao(BaseDao<Sha01grzdsx, String> sha01grzdsxDao) {
        this.baseDao = sha01grzdsxDao;
        this.sha01grzdsxDao = (Sha01grzdsxDao) sha01grzdsxDao;
    }


    public String toSqliteInsertSql(Sha01grzdsx entity){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" APP_SH_A01_GRZDSX ");
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
            String attsPath = GendataService.APP_ATTS_PATH+ Sha01grzdsxService.APP_ATTS_PATH
                    +FileUtil.getFileName(entity.getFile2imgPath());
            sb.append(",'"+attsPath+"'");

        }
        sb.append(")");
        return sb.toString();
    }

}
