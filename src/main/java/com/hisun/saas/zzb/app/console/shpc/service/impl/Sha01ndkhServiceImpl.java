package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01ndkhDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01ndkh;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01ndkhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class Sha01ndkhServiceImpl extends BaseServiceImpl<Sha01ndkh,String> implements Sha01ndkhService {

    private Sha01ndkhDao sha01ndkhDao;

    @Autowired
    public void setBaseDao(BaseDao<Sha01ndkh, String> sha01ndkhDao) {
        this.baseDao = sha01ndkhDao;
        this.sha01ndkhDao = (Sha01ndkhDao) sha01ndkhDao;
    }

}