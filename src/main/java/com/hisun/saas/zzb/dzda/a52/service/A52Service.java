/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a52.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a52.entity.A52;
import com.hisun.saas.zzb.dzda.a52.vo.A52Vo;

import java.util.List;
import java.util.Map;

/**
 * @author Marco {854476391@qq.com}
 */
public interface A52Service extends BaseService<A52,String> {
    Integer getMaxSort(String a38Id);
    void updatePx(int oldPx,int newPx,String a38Id);
    Map<String,Object> checkA52Vos(List<A52Vo> a52Vos);
    void saveA52S(List<A52Vo> a52Vos, A38 a38, UserLoginDetails details);
}
