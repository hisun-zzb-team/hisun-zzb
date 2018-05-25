package com.hisun.saas.zzb.app.console.apiregister.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.apiregister.dao.ApiRegisterDao;
import com.hisun.saas.zzb.app.console.apiregister.entity.ApiRegister;
import com.hisun.saas.zzb.app.console.apiregister.service.ApiRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhouying on 2017/10/12.
 */
@Service
public class ApiRegisterServiceImpl extends BaseServiceImpl<ApiRegister,String> implements ApiRegisterService {

    private ApiRegisterDao apiRegisterDao;

    @Autowired
    public void setBaseDao(BaseDao<ApiRegister, String> apiRegisterDao) {
        this.baseDao = apiRegisterDao;
        this.apiRegisterDao = (ApiRegisterDao)apiRegisterDao;
    }
}
