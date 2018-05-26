/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.mlcl.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.dzda.e01z4.dao.E01Z4Dao;
import com.hisun.saas.zzb.dzda.e01z4.entity.E01Z4;
import com.hisun.saas.zzb.dzda.e01z4.service.E01Z4Service;
import com.hisun.saas.zzb.dzda.mlcl.dao.RowCountDao;
import com.hisun.saas.zzb.dzda.mlcl.entity.EListRowCount;
import com.hisun.saas.zzb.dzda.mlcl.service.RowCountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhout {605144321@qq.com}
 */
@Service
public class RowCountServiceImpl extends BaseServiceImpl<EListRowCount,String>
        implements RowCountService {

    private RowCountDao rowCountDao;

    @Resource
    public void setBaseDao(BaseDao<EListRowCount, String> baseDao) {
        this.baseDao = baseDao;
        this.rowCountDao = (RowCountDao)baseDao;
    }

}
