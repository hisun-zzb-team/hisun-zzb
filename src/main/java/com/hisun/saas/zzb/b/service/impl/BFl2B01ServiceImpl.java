/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.b.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.b.dao.BFl2B01Dao;
import com.hisun.saas.zzb.b.dao.BFlDao;
import com.hisun.saas.zzb.b.entity.BFl;
import com.hisun.saas.zzb.b.entity.BFl2B01;
import com.hisun.saas.zzb.b.service.BFl2B01Service;
import com.hisun.saas.zzb.b.service.BFlService;
import com.hisun.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuzj {279421824@qq.com}
 */
@Service
public class BFl2B01ServiceImpl extends BaseServiceImpl<BFl2B01,String> implements BFl2B01Service {
    private BFl2B01Dao bFl2B01Dao;

    @Resource
    public void setBaseDao(BaseDao<BFl2B01, String> baseDao) {
        this.baseDao = baseDao;
        this.bFl2B01Dao = (BFl2B01Dao)baseDao;
    }

    /**
     * 排序处理（首先跟最大的排序号比较，如果大于最大排序号则不处理；
     *        如果小于，就一个个比较，当比较到大于它的就把后面大于它的全部+1；）
     * @param
     */
    @Override
    public void updatePx(int oldPx,int newPx,String bFl00){
        String sql = "UPDATE b_fl_2_b01 f SET ";
        if(newPx > oldPx) {
            sql = sql + "f.px=f.px-1";
        } else {
            sql = sql + "f.px=f.px+1";
        }

        sql = sql + " where f.fl_id =(:bFlId) ";

        if(newPx > oldPx) {
            sql = sql + " and f.px<=" + newPx + " and f.px >" + oldPx;
        } else if(newPx == oldPx) {
            sql = sql + " and 1<>1";
        } else {
            sql = sql + " and f.px<" + oldPx + " and f.px>=" + newPx;
        }
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("bFlId", bFl00);
        this.bFl2B01Dao.update(sql, paramMap);
    }
    @Override
    public Integer getMaxSort() {
        Map<String, Object> map = new HashMap<String, Object>();
        String hql = "select max(f.px)+1 as sort from BFl2B01 f ";
        List<Map> maxSorts = this.bFl2B01Dao.list(hql, map);
        if (maxSorts.get(0).get("sort") == null) {
            return 1;
        } else {
            Integer maxSort = ((Number) maxSorts.get(0).get("sort")).intValue();
            return maxSort;
        }
    }

    @Override
    public void delBFl2B01(BFl2B01 bFl2B01){
//        int oldPx = bFl2B01.getPx();
//        int newPx = getMaxSort();
//        updatePx(oldPx,newPx,bFl2B01.getBfl().getId());
        this.bFl2B01Dao.delete(bFl2B01);
    }
}
