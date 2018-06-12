/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.a.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.a.dao.A02Dao;
import com.hisun.saas.zzb.a.entity.A02;
import com.hisun.saas.zzb.a.service.A02Service;
import com.hisun.saas.zzb.b.service.BB09BhJrInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liuzj {279421824@qq.com}
 */
@Service
public class A02ServiceImpl extends BaseServiceImpl<A02,String> implements A02Service {
    private A02Dao a02Dao;

    @Resource
    private BB09BhJrInfoService bB09BhJrInfoService;

    @Resource
    public void setBaseDao(BaseDao<A02, String> baseDao) {
        this.baseDao = baseDao;
        this.a02Dao = (A02Dao)baseDao;
    }

//    @Override
//    public void updateB09(B09 b09, Integer oldSort, List<BB09BhJrInfo> bb09BhJrInfoList){
//        if(b09.getbPx()!=oldSort) {
//            this.updatePx(oldSort,b09.getbPx(),b09.getB01().getB0100());
//        }
//        this.b09Dao.update(b09);
//        if("1".equals(b09.getbSfjr())&&bb09BhJrInfoList.size()>0){
//            for(BB09BhJrInfo bB09BhJrInfo : bb09BhJrInfoList){
//                this.bB09BhJrInfoService.save(bB09BhJrInfo);
//            }
//        }
//    }
//
//    /**
//     * 排序处理（首先跟最大的排序号比较，如果大于最大排序号则不处理；
//     *        如果小于，就一个个比较，当比较到大于它的就把后面大于它的全部+1；）
//     * @param
//     */
//    public void updatePx(int oldPx,int newPx,String b01Id){
//        String sql = "UPDATE b09 f SET ";
//        if(newPx > oldPx) {
//            sql = sql + "f.b_px=f.b_px-1";
//        } else {
//            sql = sql + "f.b_px=f.b_px+1";
//        }
//        sql = sql + " where f.b0100 =(:b01Id) ";
//        if(newPx > oldPx) {
//            sql = sql + " and f.b_px<=" + newPx + " and f.b_px >" + oldPx;
//        } else if(newPx == oldPx) {
//            sql = sql + " and 1<>1";
//        } else {
//            sql = sql + " and f.b_px<" + oldPx + " and f.b_px>=" + newPx;
//        }
//        Map<String, Object> paramMap=new HashMap<String, Object>();
//        paramMap.put("tenantId", UserLoginDetailsUtil.getUserLoginDetails().getTenantId());
//        paramMap.put("b01Id",b01Id);
////        if(StringUtils.isNotEmpty(parentId)){
////            paramMap.put("parentId", parentId);
////        }
//        this.b09Dao.update(sql, paramMap);
//    }
//    @Override
//    public Integer getMaxSort() {
//        Map<String, Object> map = new HashMap<String, Object>();
//        String hql = "select max(b.bPx)+1 as sort from B09 b ";
//        List<Map> maxSorts = this.b09Dao.list(hql, map);
//        if (maxSorts.get(0).get("sort") == null) {
//            return 1;
//        } else {
//            Integer maxSort = ((Number) maxSorts.get(0).get("sort")).intValue();
//            return maxSort;
//        }
//    }
}
