/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.b.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.b.entity.BFl;
import com.hisun.saas.zzb.b.entity.BFl2B01;

/**
 * @author liuzj {279421824@qq.com}
 */
public interface BFl2B01Service extends BaseService<BFl2B01,String> {
    Integer getMaxSort();
    void delBFl2B01(BFl2B01 bFl2B01);
    void updatePx(int oldPx,int newPx,String bFl00);
}
