/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.e01z9.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.e01z9.dao.E01Z9Dao;
import com.hisun.saas.zzb.dzda.e01z9.entity.E01Z9;
import com.hisun.saas.zzb.dzda.e01z9.service.E01Z9Service;
import com.hisun.util.StringUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhout {605144321@qq.com}
 */
@Service
public class E01Z9ServiceImpl extends BaseServiceImpl<E01Z9,String>
        implements E01Z9Service {

    @Resource
    private A38Service a38Service;

    private E01Z9Dao e01Z9Dao;

    @Resource
    public void setBaseDao(BaseDao<E01Z9, String> baseDao) {
        this.baseDao = baseDao;
        this.e01Z9Dao = (E01Z9Dao)baseDao;
    }

    @Override
    public Map<String,Object> getYqE01Z9(String userId,String tenantId,int pageNum,int pageSize,String e01Z9Damc,String e01Z907,String e01Z9Jyzt,String e01z9Yh){
        Map<String,Object> paramMap = new HashMap<>();
        int start = (pageNum-1)*pageSize;
        int end = pageNum*pageSize;
        List<E01Z9> e01Z9s = new ArrayList<>();
        String sql = "";
//        e01Z907 = " %"+e01Z907+"% ";
        paramMap.put("start",start);
        paramMap.put("end",end);
        paramMap.put("userId",userId);
        paramMap.put("tenantId",tenantId);
        paramMap.put("e01Z9Damc",e01Z9Damc);
        paramMap.put("e01Z907",e01Z907);
        paramMap.put("e01Z9Jyzt",e01Z9Jyzt);
        if(StringUtils.isNotEmpty(userId)){
            sql = "SELECT `id` ,\n" +
                    "  `a38_id`,\n" +
                    "  `a0101` ,\n" +
                    "  `e01z9_damc`,\n" +
                    "  `e01z901` ,\n" +
                    "  `e01z904b`,\n" +
                    "  `e01z904a`,\n" +
                    "  `e01z907`,\n" +
                    "  `e01z911`,\n" +
                    "  `e01z914` ,\n" +
                    "  `e01z917`,\n" +
                    "  `e01z927` ,\n" +
                    "  `e01z9_yhsj`,\n" +
                    "  `e01z9_jyzt`,\n" +
                    "  `e01z931`,\n" +
                    "  `e01z9_shsj`,\n" +
                    "  `e01z934`,\n" +
                    "  `e01z941` ,\n" +
                    "  `is_del` ,\n" +
                    "  `tenant_id` ,\n" +
                    "  `create_user_id` ,\n" +
                    "  `create_user_name` ,\n" +
                    "  `create_date` ,\n" +
                    "  `update_user_id` ,\n" +
                    "  `update_user_name` ,\n" +
                    "  `update_date`,\n" +
                    "  `tombstone` FROM `e01z9` where tenant_id = :tenantId " +
                    ("1".equals(e01z9Yh)?" and  e01z9_yhsj+0<date_format(SYSDATE(),'%Y%m%d')+0 ":" and e01z9_yhsj+0>date_format(SYSDATE(),'%Y%m%d')+0 ") +
//                    "  `tombstone` FROM `e01z9` where e01z9_yhsj+0<date_format(SYSDATE(),'%Y%m%d')+0 " +
                    "  and create_user_id = :userId " +
                    (StringUtils.isNotEmpty(e01Z9Damc)?"  and e01Z9_damc like '%"+e01Z9Damc+"%' " : "") +
                    (StringUtils.isNotEmpty(e01Z907)?"  and e01Z907 like '%"+e01Z907+"%' " : "") +
                    (StringUtils.isNotEmpty(e01Z9Jyzt)?"  and e01Z9_jyzt = :e01Z9Jyzt " : "  and e01z9_jyzt = '1' ") +
                    "  limit :start , :end";
        }else {
            sql = "SELECT `id` ,\n" +
                    "  `a38_id`,\n" +
                    "  `a0101` ,\n" +
                    "  `e01z9_damc`,\n" +
                    "  `e01z901` ,\n" +
                    "  `e01z904b`,\n" +
                    "  `e01z904a`,\n" +
                    "  `e01z907`,\n" +
                    "  `e01z911`,\n" +
                    "  `e01z914` ,\n" +
                    "  `e01z917`,\n" +
                    "  `e01z927` ,\n" +
                    "  `e01z9_yhsj`,\n" +
                    "  `e01z9_jyzt`,\n" +
                    "  `e01z931`,\n" +
                    "  `e01z9_shsj`,\n" +
                    "  `e01z934`,\n" +
                    "  `e01z941` ,\n" +
                    "  `is_del` ,\n" +
                    "  `tenant_id` ,\n" +
                    "  `create_user_id` ,\n" +
                    "  `create_user_name` ,\n" +
                    "  `create_date` ,\n" +
                    "  `update_user_id` ,\n" +
                    "  `update_user_name` ,\n" +
                    "  `update_date`,\n" +
                    "  `tombstone` FROM `e01z9` where tenant_id = :tenantId " +
                    ("1".equals(e01z9Yh)?" and e01z9_yhsj+0<date_format(SYSDATE(),'%Y%m%d')+0 ":" and e01z9_yhsj+0>date_format(SYSDATE(),'%Y%m%d')+0 ") +
//                    "  `tombstone` FROM `e01z9` where e01z9_yhsj+0<date_format(SYSDATE(),'%Y%m%d')+0 " +
                    (StringUtils.isNotEmpty(e01Z9Damc)?"  and e01Z9_damc like '%"+e01Z9Damc+"%' " : "") +
                    (StringUtils.isNotEmpty(e01Z907)?"  and e01Z907 like '%"+e01Z907+"%' " : "") +
                    (StringUtils.isNotEmpty(e01Z9Jyzt)?"  and e01Z9_jyzt = :e01Z9Jyzt " : "  and e01z9_jyzt = '1' ") +
                    "  limit :start , :end";
        }
        List<Map> mapList = this.e01Z9Dao.nativeList(sql,paramMap);
        e01Z9s = mapToE01Z9(mapList);
        sql = "SELECT `id` ,\n" +
                "  `a38_id`,\n" +
                "  `a0101` ,\n" +
                "  `e01z9_damc`,\n" +
                "  `e01z901` ,\n" +
                "  `e01z904b`,\n" +
                "  `e01z904a`,\n" +
                "  `e01z907`,\n" +
                "  `e01z911`,\n" +
                "  `e01z914` ,\n" +
                "  `e01z917`,\n" +
                "  `e01z927` ,\n" +
                "  `e01z9_yhsj`,\n" +
                "  `e01z931`,\n" +
                "  `e01z9_shsj`,\n" +
                "  `e01z934`,\n" +
                "  `e01z941` ,\n" +
                "  `is_del` ,\n" +
                "  `tenant_id` ,\n" +
                "  `create_user_id` ,\n" +
                "  `create_user_name` ,\n" +
                "  `create_date` ,\n" +
                "  `update_user_id` ,\n" +
                "  `update_user_name` ,\n" +
                "  `update_date`,\n" +
                "  `tombstone` FROM `e01z9` where tenant_id = :tenantId " +
                ("1".equals(e01z9Yh)?" and  e01z9_yhsj+0<date_format(SYSDATE(),'%Y%m%d')+0 ":" and e01z9_yhsj+0>date_format(SYSDATE(),'%Y%m%d')+0 ") +
//                "  `tombstone` FROM `e01z9` where e01z9_yhsj+0<date_format(SYSDATE(),'%Y%m%d')+0 " +
                (StringUtils.isNotEmpty(userId)?"  and create_user_id = :userId " : "") +
                (StringUtils.isNotEmpty(e01Z9Damc)?"  and e01Z9_damc like '%"+e01Z9Damc+"%' " : "") +
                (StringUtils.isNotEmpty(e01Z907)?"  and e01Z907 like '%"+e01Z907+"%' " : "") +
                (StringUtils.isNotEmpty(e01Z9Jyzt)?"  and e01Z9_jyzt = :e01Z9Jyzt " : "  and e01z9_jyzt = '1' ")
                ;
        List<Map> mapListTotal = this.e01Z9Dao.nativeList(sql,paramMap);
        paramMap = new HashMap<>();
        paramMap.put("e01Z9s",e01Z9s);
        paramMap.put("total",mapListTotal.size());
        return paramMap;
    }

    public List<E01Z9> mapToE01Z9(List<Map> mapList){
        List<E01Z9> e01Z9List = new ArrayList<>();
        for(Map map : mapList){
            E01Z9 e01Z9 = new E01Z9();
            e01Z9.setId((String) map.get("id"));
//            e01Z9.setA38(a38Service.getByPK((String) map.get("a38_id")));
            e01Z9.setA0101((String) map.get("a0101"));
            e01Z9.setE01Z9Damc((String) map.get("e01z9_damc"));
            e01Z9.setE01Z901((String) map.get("e01z901"));
            e01Z9.setE01Z904B((String) map.get("e01z904b"));
            e01Z9.setE01Z904A((String) map.get("e01z904a"));
            e01Z9.setE01Z907((String) map.get("e01z907"));
            e01Z9.setE01Z911((String) map.get("e01z911"));
            e01Z9.setE01Z914((String) map.get("e01z914"));
            e01Z9.setE01Z917((String) map.get("e01z917"));
            e01Z9.setE01Z927((String) map.get("e01z927"));
            e01Z9.setE01z9Yhsj((String) map.get("e01z9_yhsj"));
            e01Z9.setE01Z931((String) map.get("e01z931"));
            e01Z9.setE01Z9Shsj((String) map.get("e01z9_shsj"));
            e01Z9.setE01Z934((String) map.get("e01z934"));
            e01Z9.setE01Z941((String) map.get("e01z941"));
            e01Z9.setIsDel((String) map.get("is_del"));
            e01Z9.setE01Z9Jyzt((String) map.get("e01z9_jyzt"));
            e01Z9List.add(e01Z9);
        }

        return e01Z9List;
    }

}
