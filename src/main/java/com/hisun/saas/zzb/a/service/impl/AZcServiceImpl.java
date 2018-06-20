/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.a.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.a.dao.AZcDao;
import com.hisun.saas.zzb.a.entity.AZc;
import com.hisun.saas.zzb.a.service.AZcService;
import com.hisun.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuzj {279421824@qq.com}
 */
@Service
public class AZcServiceImpl extends BaseServiceImpl<AZc,String> implements AZcService {
    private AZcDao aZcDao;

    @Resource
    public void setBaseDao(BaseDao<AZc, String> baseDao) {
        this.baseDao = baseDao;
        this.aZcDao = (AZcDao)baseDao;
    }

    @Override
    public String getData(List<AZc> aZcs){
        String strData = "";
        for(AZc aZc:aZcs){
            if(StringUtils.isEmpty(aZc.getaZca())){
                aZc.setaZca("");
            }
            if(StringUtils.isEmpty(aZc.getaQdsj())){
                aZc.setaQdsj("");
            }
            if(StringUtils.isEmpty(aZc.getaZcjbb())){
                aZc.setaZcjbb("");
            }
            if(StringUtils.isEmpty(aZc.getaZcztb())){
                aZc.setaZcztb("");
            }
            strData = strData +aZc.getaQdsj()+ " " +aZc.getaZca() + " （" +aZc.getaZcjbb() + "）" +aZc.getaZcztb() + "\n";
        }
        return strData;
    }

}
