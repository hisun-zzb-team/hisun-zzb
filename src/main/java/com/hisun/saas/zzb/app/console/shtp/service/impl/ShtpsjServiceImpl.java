package com.hisun.saas.zzb.app.console.shtp.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.shtp.dao.ShtpsjDao;
import com.hisun.saas.zzb.app.console.shtp.entity.Shtpsj;
import com.hisun.saas.zzb.app.console.shtp.service.ShtpsjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class ShtpsjServiceImpl extends BaseServiceImpl<Shtpsj,String> implements ShtpsjService {

    private ShtpsjDao shtpsjDao;

    @Autowired
    public void setBaseDao(BaseDao<Shtpsj, String> shtpsjDao) {
        this.baseDao = shtpsjDao;
        this.shtpsjDao = (ShtpsjDao) shtpsjDao;
    }

}