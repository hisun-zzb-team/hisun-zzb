/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.zscx.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.zscx.entity.AppZscxZsA01;

import java.io.File;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface AppZscxZsA01Service extends BaseService<AppZscxZsA01,String> {

    Integer getMaxPx(String b01Id) ;
    void updatePx(String b01Id, int oldPx, int newPx);
}
