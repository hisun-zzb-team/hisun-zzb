/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.dzda.dacy.dao.EA38LogDao;
import com.hisun.saas.zzb.dzda.dacy.entity.EA38Log;
import com.hisun.saas.zzb.dzda.dacy.service.EA38LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Marco {854476391@qq.com}
 */
@Service
public class EA38LogServiceImpl extends BaseServiceImpl<EA38Log,String>implements EA38LogService {
    private EA38LogDao eA38LogDao;

    @Resource
    public void setBaseDao(BaseDao<EA38Log, String> baseDao) {
        this.baseDao = baseDao;
        this.eA38LogDao = (EA38LogDao)baseDao;
    }
    public List<EA38Log> getA38LogListBySql(String sql, Map<String,Object> paramMap){
        return this.eA38LogDao.getA38LogListBySql(sql,paramMap);
    }
}
