/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.shpc.dao;

import com.hisun.base.dao.BaseDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01gbrmspb;

import java.util.Map;

/**
 * Created by zhouying on 2017/9/12.
 */
public interface Sha01gbrmspbDao extends BaseDao<Sha01gbrmspb,String> {

    public String saveFromWord(Sha01gbrmspb gbrmspb, Map<String, String> wordDataMap) throws Exception;

}
