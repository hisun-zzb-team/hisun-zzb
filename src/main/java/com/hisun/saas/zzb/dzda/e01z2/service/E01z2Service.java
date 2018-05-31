/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.e01z2.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.e01z2.entity.E01Z2;
import com.hisun.saas.zzb.dzda.e01z2.vo.E01z2Vo;

import java.util.List;
import java.util.Map;

/**
 * @author Marco {854476391@qq.com}
 */
public interface E01z2Service extends BaseService<E01Z2,String> {
    Integer getMaxSort(String a38Id);
    void updatePx(int oldPx, int newPx, String a38Id);
    Map<String,Object> checkE01z2Vos(List<E01z2Vo> e01z2Vos);
    void saveE01z2Vos(List<E01z2Vo> e01z2Vos, A38 a38, UserLoginDetails details);
}
