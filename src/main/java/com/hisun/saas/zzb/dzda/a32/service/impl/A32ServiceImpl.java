/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a32.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a32.dao.A32Dao;
import com.hisun.saas.zzb.dzda.a32.entity.A32;
import com.hisun.saas.zzb.dzda.a32.service.A32Service;
import com.hisun.saas.zzb.dzda.a32.vo.A32Vo;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.vo.WrongExcelColumn;
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
public class A32ServiceImpl extends BaseServiceImpl<A32,String> implements A32Service {
    private A32Dao a32Dao;

    @Resource
    public void setBaseDao(BaseDao<A32, String> baseDao) {
        this.baseDao = baseDao;
        this.a32Dao = (A32Dao)baseDao;
    }

    /**
     * 排序处理（首先跟最大的排序号比较，如果大于最大排序号则不处理；
     *        如果小于，就一个个比较，当比较到大于它的就把后面大于它的全部+1；）
     * @param
     */
    public void updatePx(int oldPx,int newPx,String a38Id){
        String sql = "UPDATE a32 t SET ";
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
        this.a32Dao.update(sql, paramMap);
    }
    @Override
    public Integer getMaxSort(String a38Id) {
        Map<String, Object> map = new HashMap<String, Object>();
        String hql = "select max(t.px)+1 as sort from A32 t ";
        if (a38Id != null && !a38Id.equals("")) {
            hql = hql + "where t.a38.id =:a38Id";
            map.put("a38Id", a38Id);
        } else {
            hql = hql + "where t.a38.id is null";
        }
        List<Map> maxSorts = this.a32Dao.list(hql, map);
        if (maxSorts.get(0).get("sort") == null) {
            return 1;
        } else {
            Integer maxSort = ((Number) maxSorts.get(0).get("sort")).intValue();
            return maxSort;
        }
    }

    @Override
    public Map<String,Object> checkA32Vos(List<A32Vo> a32Vos){
        boolean isRight = false;
        int emptySum = 0;
        List<WrongExcelColumn> wrongExcelColumns = new ArrayList<>();
        Map<String,Object> returnMap = new HashMap<>();
        WrongExcelColumn wrongExcelColumn;
        for(int i=0;i<a32Vos.size();i++) {
            int sum = 0;
            boolean flag = false;//判断是否存在非法数据
            boolean flag1 = false;//判断是否存在非法数据
            A32Vo a32Vo = (A32Vo) a32Vos.get(i);

            if (StringUtils.isEmpty(a32Vo.getGzbm())) {
                wrongExcelColumn = new WrongExcelColumn();
                wrongExcelColumn.setLines("A" + a32Vo.getRow());
                wrongExcelColumn.setReason("工作部门不能为空");
                wrongExcelColumn.setWrongExcel("工资变动登记表");
                wrongExcelColumns.add(wrongExcelColumn);
                flag = true;
                sum++;
            }
            if (DaUtils.isNotDate(a32Vo.getA3207())) {
                wrongExcelColumn = new WrongExcelColumn();
                wrongExcelColumn.setLines("E" + a32Vo.getRow());
                wrongExcelColumn.setReason("日期格式错误");
                wrongExcelColumn.setWrongExcel("工资变动登记表");
                wrongExcelColumns.add(wrongExcelColumn);
                flag = true;
                sum++;
            }

            if (StringUtils.isEmpty(a32Vo.getGzbm()) && StringUtils.isEmpty(a32Vo.getA3207())) {
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
            }
        }
        if(a32Vos.size()==emptySum){
            returnMap.put("gzbdIsEmpty",true);
        }else {
            returnMap.put("gzbdIsEmpty",false);
        }
        returnMap.put("isRight",isRight);
        returnMap.put("wrongExcelColumns",wrongExcelColumns);
        return returnMap;
    }

    public void saveA32S(List<A32Vo> a32Vos,A38 a38,UserLoginDetails details){
        for (int i = 0; i < a32Vos.size(); i++) {
            Integer oldPxInteger = getMaxSort(a38.getId());
            A32 a32 = new A32();
            A32Vo a32Vo = a32Vos.get(i);
            try {
                BeanUtils.copyProperties(a32, a32Vo);
                a32.setA38(a38);
                a32.setPx(oldPxInteger);
                EntityWrapper.wrapperSaveBaseProperties(a32, details);
                save(a32);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
