/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.b.service.impl;

import com.google.common.collect.Lists;
import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.taglib.treeTag.TreeNode;
import com.hisun.saas.zzb.b.dao.B01Dao;
import com.hisun.saas.zzb.b.entity.B01;
import com.hisun.saas.zzb.b.service.B01Service;
import com.hisun.saas.zzb.b.vo.B01TreeNode;
import com.hisun.saas.zzb.dzda.a32.dao.A32Dao;
import com.hisun.saas.zzb.dzda.a32.entity.A32;
import com.hisun.saas.zzb.dzda.a32.service.A32Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuzj {279421824@qq.com}
 */
@Service
public class B01ServiceImpl extends BaseServiceImpl<B01,String> implements B01Service {
    private B01Dao b01Dao;

    @Resource
    public void setBaseDao(BaseDao<B01, String> baseDao) {
        this.baseDao = baseDao;
        this.b01Dao = (B01Dao)baseDao;
    }

    /**
     * 排序处理（首先跟最大的排序号比较，如果大于最大排序号则不处理；
     *        如果小于，就一个个比较，当比较到大于它的就把后面大于它的全部+1；）
     * @param
     */
    public void updatePx(int oldPx,int newPx,String parentId){
        String sql = "UPDATE b01 t SET ";
        if(newPx > oldPx) {
            sql = sql + "t.b_px=t.b_px-1";
        } else {
            sql = sql + "t.b_px=t.b_px+1";
        }
        sql = sql + " where t.b_sjjg=(:parentId)";
        if(newPx > oldPx) {
            sql = sql + " and t.b_px<=" + newPx + " and t.b_px >" + oldPx;
        } else if(newPx == oldPx) {
            sql = sql + " and 1<>1";
        } else {
            sql = sql + " and t.b_px<" + oldPx + " and t.b_px>=" + newPx;
        }
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("tenantId", UserLoginDetailsUtil.getUserLoginDetails().getTenantId());
        paramMap.put("parentId", parentId);
        this.b01Dao.update(sql, paramMap);
    }
    @Override
    public Integer getMaxSort(String parentId) {
        Map<String, Object> map = new HashMap<String, Object>();
        String hql = "select max(t.b_px)+1 as sort from B01 t ";
        if (parentId != null && !parentId.equals("")) {
            hql = hql + "where t.b_sjjg =:parentId";
            map.put("a38Id", parentId);
        } else {
            hql = hql + "where t.b_sjjg is null";
        }
        List<Map> maxSorts = this.b01Dao.list(hql, map);
        if (maxSorts.get(0).get("sort") == null) {
            return 1;
        } else {
            Integer maxSort = ((Number) maxSorts.get(0).get("sort")).intValue();
            return maxSort;
        }
    }
    @Override
    public List<B01TreeNode> getB01TreeVoList()throws Exception{
        CommonConditionQuery query = new CommonConditionQuery();
        CommonOrderBy orderBy = new CommonOrderBy();

        orderBy.add(CommonOrder.asc("bCxbm"));
//        orderBy.add(CommonOrder.asc("parentB01.id"));
//        orderBy.add(CommonOrder.asc("px"));
        List<B01> appBsetB01s = this.b01Dao.list(query, orderBy);
        List<B01TreeNode> b01TreeVoList = Lists.newArrayList();
        if(appBsetB01s != null) {
            for (B01 b01 : appBsetB01s) {
                B01TreeNode b01TreeNode = new B01TreeNode();
                b01TreeNode.setId(b01.getB0100());
                if(b01.getParentB01()!= null) {
                    b01TreeNode.setpId(b01.getParentB01().getB0100());
                    b01TreeNode.setName(b01.getB0101());
                    b01TreeNode.setbSjlx(b01.getbSjlx());
                    b01TreeNode.setKey(b01.getB0100());
                }else{
                    b01TreeNode.setpId("");
                    b01TreeNode.setName(b01.getB0101());
                    b01TreeNode.setbSjlx(b01.getbSjlx());
                    b01TreeNode.setOpen(true);
                    b01TreeNode.setKey(b01.getB0100());
                }
                b01TreeVoList.add(b01TreeNode);
            }
        }
        return b01TreeVoList;
    }
}
