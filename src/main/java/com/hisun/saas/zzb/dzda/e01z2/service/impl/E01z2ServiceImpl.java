/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.e01z2.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.admin.dictionary.service.DictionaryItemService;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.vo.WrongExcelColumn;
import com.hisun.saas.zzb.dzda.e01z2.dao.E01z2Dao;
import com.hisun.saas.zzb.dzda.e01z2.entity.E01Z2;
import com.hisun.saas.zzb.dzda.e01z2.service.E01z2Service;
import com.hisun.saas.zzb.dzda.e01z2.vo.E01z2Vo;
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
public class E01z2ServiceImpl extends BaseServiceImpl<E01Z2,String> implements E01z2Service{
    private E01z2Dao e01z2Dao;

    @Resource
    private DictionaryItemService dictionaryItemService;

    @Resource
    public void setBaseDao(BaseDao<E01Z2, String> baseDao) {
        this.baseDao = baseDao;
        this.e01z2Dao = (E01z2Dao)baseDao;
    }
    /**
     * 排序处理（首先跟最大的排序号比较，如果大于最大排序号则不处理；
     *        如果小于，就一个个比较，当比较到大于它的就把后面大于它的全部+1；）
     * @param
     */
    public void updatePx(int oldPx,int newPx,String a38Id){
        String sql = "UPDATE e01z2 t SET ";
        if(newPx > oldPx) {
            sql = sql + "t.e01z214=t.e01z214-1";
        } else {
            sql = sql + "t.e01z214=t.e01z214+1";
        }
        sql = sql + " where t.a38_id=(:a38Id)";
        if(newPx > oldPx) {
            sql = sql + " and t.e01z214<=" + newPx + " and t.e01z214 >" + oldPx;
        } else if(newPx == oldPx) {
            sql = sql + " and 1<>1";
        } else {
            sql = sql + " and t.e01z214<" + oldPx + " and t.e01z214>=" + newPx;
        }
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("a38Id", a38Id);
        this.e01z2Dao.update(sql, paramMap);
    }
    @Override
    public Integer getMaxSort(String a38Id) {
        Map<String, Object> map = new HashMap<String, Object>();
        String hql = "select max(t.e01z214)+1 as sort from e01z2 t ";
        if (a38Id != null && !a38Id.equals("")) {
            hql = hql + "where t.a38_id =:a38Id";
            map.put("a38Id", a38Id);
        } else {
            hql = hql + "where t.a38_id is null";
        }
        List<Map> maxSorts = this.e01z2Dao.nativeList(hql, map);
        if (maxSorts.get(0).get("sort") == null) {
            return 1;
        } else {
            Integer maxSort = ((Number) maxSorts.get(0).get("sort")).intValue();
            return maxSort;
        }
    }

    @Override
    public Map<String,Object> checkE01z2Vos(List<E01z2Vo> e01z2Vos){
        boolean isRight = false;
        int emptySum = 0;
        List<WrongExcelColumn> wrongExcelColumns = new ArrayList<>();
        Map<String,Object> returnMap = new HashMap<>();
        WrongExcelColumn wrongExcelColumn;
        boolean flag;//判断是否存在非法数据
        boolean flag1;//判断是否存在非法数据
        for(int i=0;i<e01z2Vos.size();i++) {
            flag = false;//判断是否存在非法数据
            flag1 = false;//判断是否存在非法数据
            int sum = 0;
            E01z2Vo e01z2Vo = e01z2Vos.get(i);
            if (StringUtils.isEmpty(e01z2Vo.getE01Z204A())) {
                wrongExcelColumn = new WrongExcelColumn();
                wrongExcelColumn.setLines("A" + e01z2Vo.getRow());
                wrongExcelColumn.setReason("来件单位不能为空");
                wrongExcelColumn.setWrongExcel("材料接收表");
                wrongExcelColumns.add(wrongExcelColumn);
                flag = true;
                sum++;
            }
            if (StringUtils.isEmpty(e01z2Vo.getE01Z221A())) {
                wrongExcelColumn = new WrongExcelColumn();
                wrongExcelColumn.setLines("E" + e01z2Vo.getRow());
                wrongExcelColumn.setReason("材料名称不能为空");
                wrongExcelColumn.setWrongExcel("材料接收表");
                wrongExcelColumns.add(wrongExcelColumn);
                flag = true;
                sum++;
            }
            if (DaUtils.isNotDate(e01z2Vo.getE01Z201())) {
                wrongExcelColumn = new WrongExcelColumn();
                wrongExcelColumn.setLines("B" + e01z2Vo.getRow());
                wrongExcelColumn.setReason("收件日期格式错误");
                wrongExcelColumn.setWrongExcel("材料接收表");
                wrongExcelColumns.add(wrongExcelColumn);
                flag = true;
                sum++;
            }
            if (DaUtils.isNotDate(e01z2Vo.getE01Z227())) {
                wrongExcelColumn = new WrongExcelColumn();
                wrongExcelColumn.setLines("G" + e01z2Vo.getRow());
                wrongExcelColumn.setReason("材料制成日期格式错误");
                wrongExcelColumn.setWrongExcel("材料接收表");
                wrongExcelColumns.add(wrongExcelColumn);
                flag = true;
                sum++;
            }

            if (StringUtils.isEmpty(e01z2Vo.getE01Z204A()) && StringUtils.isEmpty(e01z2Vo.getE01Z221A())
                    && StringUtils.isEmpty(e01z2Vo.getE01Z201()) && StringUtils.isEmpty(e01z2Vo.getE01Z227())) {
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
        if(e01z2Vos.size()==emptySum){
            returnMap.put("cljsIsEmpty",true);
        }else {
            returnMap.put("cljsIsEmpty",false);
        }
        returnMap.put("isRight",isRight);
        returnMap.put("wrongExcelColumns",wrongExcelColumns);
        return returnMap;
    }

    @Override
    public void saveE01z2Vos(List<E01z2Vo> e01z2Vos, A38 a38, UserLoginDetails details){
        for(int i=0;i<e01z2Vos.size();i++){
            Integer oldPxInteger = getMaxSort(a38.getId());
            E01Z2 e01z2 = new E01Z2();
            try {
                E01z2Vo e01z2Vo = e01z2Vos.get(i);
                String e01Z237Content = e01z2Vo.getE01Z237Content();
                e01z2Vo.setE01Z237(dictionaryItemService.getDictionaryItem(e01Z237Content,"CLCLBS-2018"));
                String e01Z244Content = e01z2Vo.getE01Z244Content();
                e01z2Vo.setE01Z244(dictionaryItemService.getDictionaryItem(e01Z244Content,"SFBS-2018"));
                BeanUtils.copyProperties(e01z2,e01z2Vo);
                e01z2.setA38(a38);
                e01z2.setE01Z214(oldPxInteger);
                EntityWrapper.wrapperSaveBaseProperties(e01z2,details);
                save(e01z2);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
