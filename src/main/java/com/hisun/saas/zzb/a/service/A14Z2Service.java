/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.a.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.a.entity.A14Z2;

import java.util.List;

/**
 * @author zhout {6051144321@qq.com}
 */
public interface A14Z2Service extends BaseService<A14Z2,String> {
    String getData(List<A14Z2> a14Z2s);
}
