package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01shgxDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01shgx;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01shgxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class Sha01shgxServiceImpl extends BaseServiceImpl<Sha01shgx,String> implements Sha01shgxService {

    private Sha01shgxDao sha01shgxDao;

    @Autowired
    public void setBaseDao(BaseDao<Sha01shgx, String> sha01shgxDao) {
        this.baseDao = sha01shgxDao;
        this.sha01shgxDao = (Sha01shgxDao) sha01shgxDao;
    }

}