/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.shpc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01gzjl;

import java.util.Map;

/**
 * Created by zhouying on 2017/9/15.
 */
public interface Sha01gzjlService extends BaseService<Sha01gzjl,String> {


     void saveFromWord(Sha01 sha01, Map<String, String> dataMap);
     void saveGzjls(Sha01 sha01, String gzjlStr);
     String toSqliteInsertSql(Sha01gzjl entity);

}
