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

    /**
     * 检查获得的a32Vo数据是否合法
     * @param a32Vos
     * @return
     */
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

    /**
     * 根据a32Vo保存a32
     * @param a32Vos
     * @param a38
     * @param details
     */
    @Override
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

    public int saveFromGzslws(DataSource dataSource,String a3807B)throws Exception{
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        //处理了多少条
        int order = 0;
        Connection conn = dataSource.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        int count =0;
        List<Map<String, Object>> countList = queryRunner.query(conn,
                "select count(*) as count from a32 where PK_A38 in(" +
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
            String sql = "select * from (select a32.*,rownum rn from a32 where PK_A38 in(" +
                    "select a38.PK_A38 from a38 where a38.A_STATE = '1' and a38.A_IS_DESTROY = '0' and a38.a3807b='"+a3807B+"')" +
                    " order by a32.PK_A32) where rn >"+num1+" and rn<="+num2+" ";

            List<Map<String, Object>> list = queryRunner.query(conn, sql, new MapListHandler(),(Object[]) null);
            for (Iterator<Map<String, Object>> li = list.iterator(); li.hasNext();) {
                Map<String, Object> m = li.next();
                StringBuffer fields = new StringBuffer();
                fields.append("insert into a32 (");
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
                            if("A32_ORDER".equalsIgnoreCase(attKey.toString())){
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
                this.a32Dao.executeNativeBulk(fields.append(values).toString(),paramList);
                order++;
            }
        }

        DbUtils.close(conn);
        return order;
    }

    private Map<String,Object> getSavaAttMaps(){
        Map<String,Object> attMaps = new HashMap<String,Object>();
        attMaps.put("PK_A32","id");                  //工资变动id',
        attMaps.put("PK_A38","a38_id");              //外键，人员档案主健'
        attMaps.put("UNIT","gzbm");                 //工作部门'
        attMaps.put("DUTY","zwmc");                 //职务名称'
        attMaps.put("A3224","a3224");                //执行的职务工资标准'
        attMaps.put("A3234","a3234");                //职务工资额'
        attMaps.put("A3207","a3207");                //批准日期'
        attMaps.put("A3204","a3204");                //批准机关'
        attMaps.put("A3211","a3211");                //批准文电号'
        attMaps.put("A32_ORDER","px");                //工资变动顺序号'
        return attMaps;
    }
}
