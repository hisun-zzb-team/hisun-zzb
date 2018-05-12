/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a32.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.dzda.a32.entity.A32;

/**
 * @author Marco {854476391@qq.com}
 */
public interface A32Service extends BaseService<A32,String> {
    public Integer getMaxSort(String a38Id);
    void updatePx(int oldPx,int newPx,String a38Id);
}
