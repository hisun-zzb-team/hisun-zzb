package com.hisun.saas.zzb.app.console.gbtj.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.gbtj.dao.GbtjDao;
import com.hisun.saas.zzb.app.console.gbtj.entity.Gbtj;
import com.hisun.saas.zzb.app.console.gbtj.service.GbtjService;
import com.hisun.util.SqliteDBUtil;
import com.hisun.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class GbtjServiceImpl extends BaseServiceImpl<Gbtj,String> implements GbtjService{

    private GbtjDao gbtjDao;

    @Autowired
    public void setBaseDao(BaseDao<Gbtj, String> gbtjDao) {
        this.baseDao = gbtjDao;
        this.gbtjDao = (GbtjDao) gbtjDao;
    }

    public String toSqliteInsertSql(Gbtj gbtj){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" APP_DWJG_TJ ");
        sb.append("(");
        sb.append("ID");
        sb.append(",TJ_MC");
        sb.append(",TJ_JSON_DATA");
        sb.append(",TBLX");
        sb.append(",TJ_PX");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.trimNull2Empty(gbtj.getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(gbtj.getTjmc())+"'");
        sb.append(",'"+gbtj.getTjjsondata()+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(gbtj.getTblx())+"'");
        sb.append(","+ gbtj.getPx()+"");
        sb.append(")");
        return sb.toString();
    }


    public void saveAsSqlite(String gbtjId, String sqlite,String imgdir,String attsdir) throws ClassNotFoundException,SQLException{
        SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
        Gbtj gbtj = this.getByPK(gbtjId);
        sqliteDBUtil.insert(sqlite, this.toSqliteInsertSql(gbtj));
    }

}
