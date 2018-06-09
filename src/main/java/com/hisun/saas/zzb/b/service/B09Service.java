/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.b.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.b.entity.B09;
import com.hisun.saas.zzb.b.entity.BB09BhJrInfo;
import com.hisun.saas.zzb.b.entity.BFl2B01;

import java.util.List;

/**
 * @author liuzj {279421824@qq.com}
 */
public interface B09Service extends BaseService<B09,String> {
   Integer getMaxSort();
   void updateB09(B09 b09, Integer oldSort, List<BB09BhJrInfo> bb09BhJrInfoList);
   void updatePx(int oldPx,int newPx,String b01Id);
}
