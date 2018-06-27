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
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.*;

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

    /**
     * 检查a52Vo数据是否合法
     * @param a52Vos
     * @return
     */
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

    /**
     * 保存A52
     * @param a52Vos
     * @param a38
     * @param details
     */
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
    public int saveFromGzslws(DataSource dataSource,String a3807B)throws Exception{
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        //处理了多少条
        int order = 0;
        Connection conn = dataSource.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        int count =0;
        List<Map<String, Object>> countList = queryRunner.query(conn,
                "select count(*) as count from a52 where PK_A38 in(" +
                        "select a38.PK_A38 from a38 where a38.A_STATE = '1' and a38.A_IS_DESTROY = '0' and a38.a3807b='"+a3807B+"')" , new MapListHandler(),(Object[]) null);
        for (Iterator<Map<String, Object>> li = countList.iterator(); li.hasNext();) {
            Map<String, Object> m = li.next();
            for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                Map.Entry<String, Object> e = mi.next();
                Object value = e.getValue();
                count = ((BigDecimal)value).intValue();
            }
        }

        Map<String,Object> attMaps = getSavaAttMaps();

        //每次处理400条
        int dealCount = count/400;
        for(int i=0;i<=dealCount;i++){
            int num1 = i*400;
            int num2 = (i+1)*400;
            String sql = "select * from (select a52.*,rownum rn from a52 where PK_A38 in(" +
                    "select a38.PK_A38 from a38 where a38.A_STATE = '1' and a38.A_IS_DESTROY = '0' and a38.a3807b='"+a3807B+"') " +
                    "order by a52.PK_A52) where rn >"+num1+" and rn<="+num2+" ";

            List<Map<String, Object>> list = queryRunner.query(conn, sql, new MapListHandler(),(Object[]) null);
            for (Iterator<Map<String, Object>> li = list.iterator(); li.hasNext();) {
                Map<String, Object> m = li.next();
                StringBuffer fields = new StringBuffer();
                fields.append("insert into a52 (");
                fields.append(" tombstone,tenant_id,create_user_id,create_user_name,create_date ");
                StringBuffer values = new StringBuffer();
                values.append(") values (");
                values.append(" 0 ");
                values.append(",'").append(userLoginDetails.getTenant().getId()).append("'")
                        .append(",'").append(userLoginDetails.getUser().getId()).append("'")
                        .append(",'").append(userLoginDetails.getUsername()).append("'")
                        .append(",").append("now()").append("");

                for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                    Map.Entry<String, Object> e = mi.next();
                    String key = e.getKey();
                    Object value = e.getValue()==null?"":e.getValue();

                    Iterator it = attMaps.entrySet().iterator();
                    boo:while (it.hasNext()) {
                        Map.Entry entry = (Map.Entry) it.next();
                        Object attKey = entry.getKey();
                        Object attValue = entry.getValue();
                        if(key.equalsIgnoreCase(attKey.toString())){
                            if("A52_ORDER".equalsIgnoreCase(attKey.toString())){
                                fields.append("," + attValue);
                                values.append("," + value);
                            }else{
                                fields.append("," + attValue);
                                values.append(",'" + value + "'");
                            }
                            break boo;
                        }
                    }
                }
                values.append(")");
                List<Object> paramList = new ArrayList<Object>();
                this.a52Dao.executeNativeBulk(fields.append(values).toString(),paramList);
                order++;
            }
        }

        DbUtils.close(conn);
        return order;
    }

    public int updateFromGzslws(DataSource dataSource)throws Exception{
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        //处理了多少条
        int order = 0;
        Connection conn = dataSource.getConnection();
        QueryRunner queryRunner = new QueryRunner();


        Map<String,Object> attMaps = getSavaAttMaps();
        List<A52> a52s = this.a52Dao.list();
        //每次处理400条

            String sql = "select a52.* from a52 where a52.PK_A38 in(" +
                    "select a38.PK_A38 from a38 where a38.A_STATE = '1' and a38.A_IS_DESTROY = '0' and a38.a3807b='GZZZB3002143') " +
                    "order by a52.PK_A52 ";

            List<Map<String, Object>> list = queryRunner.query(conn, sql, new MapListHandler(),(Object[]) null);
            for (Iterator<Map<String, Object>> li = list.iterator(); li.hasNext();) {
                Map<String, Object> m = li.next();
                String a52Id = "";
                StringBuffer fields = new StringBuffer();
                fields.append("insert into a52 (");
                fields.append(" tombstone,tenant_id,create_user_id,create_user_name,create_date ");
                StringBuffer values = new StringBuffer();
                values.append(") values (");
                values.append(" 0 ");
                values.append(",'").append(userLoginDetails.getTenant().getId()).append("'")
                        .append(",'").append(userLoginDetails.getUser().getId()).append("'")
                        .append(",'").append(userLoginDetails.getUsername()).append("'")
                        .append(",").append("now()").append("");

                for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                    Map.Entry<String, Object> e = mi.next();
                    String key = e.getKey();
                    Object value = e.getValue()==null?"":e.getValue();
                    if ("PK_A52".equalsIgnoreCase(key)) {
                        a52Id = value.toString();
                    }
                    Iterator it = attMaps.entrySet().iterator();
                    boo:while (it.hasNext()) {
                        Map.Entry entry = (Map.Entry) it.next();
                        Object attKey = entry.getKey();
                        Object attValue = entry.getValue();
                        if(key.equalsIgnoreCase(attKey.toString())){

                            if("A52_ORDER".equalsIgnoreCase(attKey.toString())){
                                fields.append("," + attValue);
                                values.append("," + value);
                            }else{
                                fields.append("," + attValue);
                                values.append(",'" + value + "'");
                            }
                            break boo;
                        }
                    }
                }
                values.append(")");
                boolean isAdd = true;
                boo:for(A52 a52 : a52s){
                    if(a52.getId().equals(a52Id)){
                        isAdd = false;
                        break boo;
                    }
                }
                if(isAdd == true) {
                    List<Object> paramList = new ArrayList<Object>();
                    this.a52Dao.executeNativeBulk(fields.append(values).toString(), paramList);
                    order++;
                }
            }

        DbUtils.close(conn);
        return order;
    }

    private Map<String,Object> getSavaAttMaps(){
        Map<String,Object> attMaps = new HashMap<String,Object>();
        attMaps.put("PK_A52","id");
        attMaps.put("PK_A38","a38_id");//外键，人员档案主健
        attMaps.put("A5204","a5204");//任职机构
        attMaps.put("A5211","a5211");//职务名称
        attMaps.put("A5227_IN","a5227_in");//任职时间
        attMaps.put("A5227_OUT","a5227_Out");//免职时间
        attMaps.put("A0245","a0245");//任职文号
        attMaps.put("A0267","a0267");//免职文号
        attMaps.put("A52_ORDER","px");//职务变动排序
        return attMaps;
    }
}
