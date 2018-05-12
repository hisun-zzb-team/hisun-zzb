/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.mlcl.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;

/**
 * @author liuzj {279421824@qq.com}
 */
public interface E01Z1Service extends BaseService<E01Z1,String>{
    Integer getMaxSort(String ad8Id,String e01Z101B);
    void updateE01Z1(E01Z1 e01z1, String oldPid, Integer oldSort);
}
