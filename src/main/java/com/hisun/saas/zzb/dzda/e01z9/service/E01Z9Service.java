/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.e01z9.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.dzda.e01z9.entity.E01Z9;

import java.util.List;
import java.util.Map;

/**
 * @author zhout {605144321@qq.com}
 */
public interface E01Z9Service extends BaseService<E01Z9,String>{
    Map<String,Object> getYqE01Z9(String userId,String tenantId, int pageNum, int pageSize,String e01Z9Damc,String e01Z907,String e01Z9Jyzt,String e01z9Yh);
}
