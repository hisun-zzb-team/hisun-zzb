package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01jcDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01jc;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01jcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class Sha01jcServiceImpl extends BaseServiceImpl<Sha01jc,String> implements Sha01jcService {

    private Sha01jcDao sha01jcDao;

    @Autowired
    public void setBaseDao(BaseDao<Sha01jc, String> sha01jcDao) {
        this.baseDao = sha01jcDao;
        this.sha01jcDao = (Sha01jcDao) sha01jcDao;
    }

}