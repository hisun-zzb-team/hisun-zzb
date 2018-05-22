/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.dao;

import com.hisun.base.dao.BaseDao;
import com.hisun.saas.zzb.dzda.dacy.entity.EA38Log;

import java.util.List;
import java.util.Map;

/**
 * @author Marco {854476391@qq.com}
 */
public interface EA38LogDao extends BaseDao<EA38Log,String> {
    List<EA38Log> getA38LogListBySql(String sql, Map<String,Object> paramMap);
}
