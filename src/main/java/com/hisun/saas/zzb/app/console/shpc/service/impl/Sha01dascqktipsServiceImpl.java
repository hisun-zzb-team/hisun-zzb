package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01dascqktipsDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01dascqktips;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01dascqktipsService;
import com.hisun.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class Sha01dascqktipsServiceImpl extends BaseServiceImpl<Sha01dascqktips, String> implements Sha01dascqktipsService {

    private Sha01dascqktipsDao sha01dascqktipsDao;

    @Autowired
    public void setBaseDao(BaseDao<Sha01dascqktips, String> sha01dascqktipsDao) {
        this.baseDao = sha01dascqktipsDao;
        this.sha01dascqktipsDao = (Sha01dascqktipsDao) sha01dascqktipsDao;
    }

    @Override
    public void deleteBySql(String id) {
        String sql = "delete from APP_SH_A01_DASCQK_TIPS where id = ?";
        List<Object> paramList = new ArrayList<Object>();
        paramList.add(id);
        sha01dascqktipsDao.executeNativeBulk(sql, paramList);
    }


    public String toSqliteInsertSql(Sha01dascqktips entity){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" APP_SH_A01_DASCQK_TIPS ");
        sb.append("(");
        sb.append("ID");
        sb.append(",APP_SH_A01_DASCQK_ID");
        sb.append(",TIP");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.trimNull2Empty(entity.getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getSha01dascqk().getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getTip())+"'");
        sb.append(")");
        return sb.toString();
    }
}