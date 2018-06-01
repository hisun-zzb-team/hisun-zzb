/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a52.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.vo.WrongExcelColumn;
import com.hisun.saas.zzb.dzda.a52.dao.A52Dao;
import com.hisun.saas.zzb.dzda.a52.entity.A52;
import com.hisun.saas.zzb.dzda.a52.service.A52Service;
import com.hisun.saas.zzb.dzda.a52.vo.A52Vo;
import com.hisun.saas.zzb.dzda.util.DaUtils;
import com.hisun.util.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marco {854476391@qq.com}
 */
@Service
public class A52ServiceImpl extends BaseServiceImpl<A52,String> implements A52Service {

    private A52Dao a52Dao;

    @Resource
    public void setBaseDao(BaseDao<A52, String> baseDao) {
        this.baseDao = baseDao;
        this.a52Dao = (A52Dao)baseDao;
    }

    /**
     * 排序处理（首先跟最大的排序号比较，如果大于最大排序号则不处理；
     *        如果小于，就一个个比较，当比较到大于它的就把后面大于它的全部+1；）
     * @param
     */
    public void updatePx(int oldPx,int newPx,String a38Id){
        String sql = "UPDATE a52 t SET ";
        if(newPx > oldPx) {
            sql = sql + "t.px=t.px-1";
        } else {
            sql = sql + "t.px=t.px+1";
        }
        sql = sql + " where t.a38_id=(:a38Id) and t.tenant_id=(:tenantId)";
        if(newPx > oldPx) {
            sql = sql + " and t.px<=" + newPx + " and t.px >" + oldPx;
        } else if(newPx == oldPx) {
            sql = sql + " and 1<>1";
        } else {
            sql = sql + " and t.px<" + oldPx + " and t.px>=" + newPx;
        }
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("tenantId", UserLoginDetailsUtil.getUserLoginDetails().getTenantId());
        paramMap.put("a38Id", a38Id);
        this.a52Dao.update(sql, paramMap);
    }
    @Override
    public Integer getMaxSort(String a38Id) {
        Map<String, Object> map = new HashMap<String, Object>();
        String hql = "select max(t.px)+1 as sort from a52 t ";
        if (a38Id != null && !a38Id.equals("")) {
            hql = hql + "where t.a38_id =:a38Id";
            map.put("a38Id", a38Id);
        } else {
            hql = hql + "where t.a38_id is null";
        }
        List<Map> maxSorts = this.a52Dao.nativeList(hql, map);
        if (maxSorts.get(0).get("sort") == null) {
            return 1;
        } else {
            Integer maxSort = ((Number) maxSorts.get(0).get("sort")).intValue();
            return maxSort;
        }
    }

    @Override
    public Map<String,Object> checkA52Vos(List<A52Vo> a52Vos){
        boolean isRight = false;
        int emptySum = 0;
        List<WrongExcelColumn> wrongExcelColumns = new ArrayList<>();
        Map<String,Object> returnMap = new HashMap<>();
        WrongExcelColumn wrongExcelColumn;
        for(int i=0;i<a52Vos.size();i++) {
            int sum = 0;
            boolean flag = false;//判断是否存在非法数据
            boolean flag1 = false;//判断必填数据是否全为空
            A52Vo a52Vo = a52Vos.get(i);
            if (StringUtils.isEmpty(a52Vo.getA5204())) {
                wrongExcelColumn = new WrongExcelColumn();
                wrongExcelColumn.setLines("C" + a52Vo.getRow());
                wrongExcelColumn.setReason("部门名称不能为空");
                wrongExcelColumn.setWrongExcel("职务变动登记表");
                wrongExcelColumns.add(wrongExcelColumn);
                flag = true;
                sum++;

            }
            if (DaUtils.isNotDate(a52Vo.getA5227In())) {
                wrongExcelColumn = new WrongExcelColumn();
                wrongExcelColumn.setLines("A" + a52Vo.getRow());
                wrongExcelColumn.setReason("任职时间格式错误");
                wrongExcelColumn.setWrongExcel("职务变动登记表");
                wrongExcelColumns.add(wrongExcelColumn);
                flag = true;
                sum++;
            }
            if (DaUtils.isNotDate(a52Vo.getA5227Out())) {
                wrongExcelColumn = new WrongExcelColumn();
                wrongExcelColumn.setLines("B" + a52Vo.getRow());
                wrongExcelColumn.setReason("免职时间格式错误");
                wrongExcelColumn.setWrongExcel("职务变动登记表");
                wrongExcelColumns.add(wrongExcelColumn);
                flag = true;
                sum++;
            }
            if (StringUtils.isEmpty(a52Vo.getA5204()) && StringUtils.isEmpty(a52Vo.getA5227In()) && StringUtils.isEmpty(a52Vo.getA5227Out())) {
                flag1 = true;
            }

            if (flag) {
                if (flag1) {
                    for (int j = 0; j < sum; j++) {
                        wrongExcelColumns.remove(wrongExcelColumns.size() - 1);
                    }
                    emptySum++;
                }else {
                    isRight = true;
                }
                continue;
            }
        }
        if(a52Vos.size()==emptySum){
            returnMap.put("zwbdIsEmpty",true);
        }else {
            returnMap.put("zwbdIsEmpty",false);
        }
        returnMap.put("isRight",isRight);
        returnMap.put("wrongExcelColumns",wrongExcelColumns);
        return returnMap;
    }

    @Override
    public void saveA52S(List<A52Vo> a52Vos, A38 a38, UserLoginDetails details){
        for (int i = 0; i < a52Vos.size(); i++) {
            boolean flag1 = true;
            Integer oldPxInteger = getMaxSort(a38.getId());
            A52 a52 = new A52();
            A52Vo a52Vo = a52Vos.get(i);
            try {
                BeanUtils.copyProperties(a52, a52Vo);
                a52.setA38(a38);
                a52.setPx(oldPxInteger);
                EntityWrapper.wrapperSaveBaseProperties(a52, details);
                if (StringUtils.isEmpty(a52Vo.getA5204()) && StringUtils.isEmpty(a52Vo.getA5227In()) && StringUtils.isEmpty(a52Vo.getA5227Out())) {
                    flag1 = false;
                }
                if(flag1){
                    save(a52);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
