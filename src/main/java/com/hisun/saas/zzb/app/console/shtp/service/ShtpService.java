package com.hisun.saas.zzb.app.console.shtp.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.shtp.entity.Shtp;

/**
 * Created by zhouying on 2017/9/15.
 */
public interface ShtpService extends BaseService<Shtp,String> {

    public String saveByRestApi(Shtp shtp);

}
