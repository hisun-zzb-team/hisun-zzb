/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.dzda.dacy.dao.EPopedomE01Z1RelationDao;
import com.hisun.saas.zzb.dzda.dacy.entity.EPopedomE01Z1Relation;
import com.hisun.saas.zzb.dzda.dacy.service.EPopedomE01Z1RelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Marco {854476391@qq.com}
 */
@Service
public class EPopedomE01Z1RelationServiceImpl extends BaseServiceImpl<EPopedomE01Z1Relation,String> implements EPopedomE01Z1RelationService {
    private EPopedomE01Z1RelationDao ePopedomE01Z1RelationDao;

    @Resource
    public void setBaseDao(BaseDao<EPopedomE01Z1Relation, String> baseDao) {
        this.baseDao = baseDao;
        this.ePopedomE01Z1RelationDao = (EPopedomE01Z1RelationDao)baseDao;
    }
}
