package com.hisun.saas.zzb.app.console.gbmc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcB01Dao;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcDao;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMc;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcB01;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcB01Service;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcService;
import com.hisun.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class GbMcB01ServiceImpl extends BaseServiceImpl<GbMcB01,String> implements GbMcB01Service {

    private GbMcB01Dao gbMcB01Dao;

    @Autowired
    public void setBaseDao(BaseDao<GbMcB01, String> gbMcB01Dao) {
        this.baseDao = gbMcB01Dao;
        this.gbMcB01Dao = (GbMcB01Dao) gbMcB01Dao;
    }

    public String toSqliteInsertSql(GbMcB01 entity){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" app_mc_b01 ");
        sb.append("(");
        sb.append("id");
        sb.append(",b0101");
        sb.append(",mc_id");
        sb.append(",px");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.trimNull2Empty(entity.getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getB0101())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getGbMc().getId())+"'");
        sb.append(","+entity.getPx());
        sb.append(")");
        return sb.toString();
    }
}
