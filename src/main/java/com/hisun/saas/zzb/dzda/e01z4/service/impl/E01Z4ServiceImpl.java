/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.e01z4.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.admin.dzda.service.ECatalogTypeService;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.e01z4.dao.E01Z4Dao;
import com.hisun.saas.zzb.dzda.e01z4.entity.E01Z4;
import com.hisun.saas.zzb.dzda.e01z4.service.E01Z4Service;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.*;

/**
 * @author zhout {605144321@qq.com}
 */
@Service
public class E01Z4ServiceImpl extends BaseServiceImpl<E01Z4,String>
        implements E01Z4Service {

    private E01Z4Dao e01Z4Dao;

    @Resource
    private ECatalogTypeService eCatalogTypeService;

    @Resource
    public void setBaseDao(BaseDao<E01Z4, String> baseDao) {
        this.baseDao = baseDao;
        this.e01Z4Dao = (E01Z4Dao)baseDao;
    }

    public Integer getMaxSort(String a38Id) {
        Map<String, Object> map=new HashMap<String, Object>();
        String hql = "select max(e.px)+1 as sort from E01Z4 e ";
        if(a38Id!=null && !a38Id.equals("")) {
            hql = hql+"where e.a38.id =:a38Id";
            map.put("a38Id", a38Id);
        }else{
            hql = hql+"where e.a38 is null";
        }

        List<Map> maxSorts = this.e01Z4Dao.list(hql, map);
        if(maxSorts.get(0).get("sort")==null){
            return 1;
        }else{
            Integer maxSort = ((Number) maxSorts.get(0).get("sort")).intValue();
            return maxSort;
        }
    }

    public void updateE01Z4(E01Z4 e01z4, Integer oldSort){
        this.updateSort(e01z4, oldSort);
        this.e01Z4Dao.update(e01z4);
    }

    private void updateSort(E01Z4 e01z4, Integer oldSort)  {
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        CommonConditionQuery query = new CommonConditionQuery();
        Integer newSort = e01z4.getPx();
        String sql="update e01z4 t set ";
        if(newSort>oldSort){
            sql+="t.px=t.px-1";
        }else{
            sql+="t.px=t.px+1";
        }

        sql +=" where t.a38_id = '" + e01z4.getA38().getId() + "'";

        if(newSort>oldSort){
            sql+=" and t.px<="+newSort+" and t.px >"+oldSort;
        }else{
            if(newSort==oldSort){
                sql+=" and t.px = -100";
            }else{
                sql+=" and t.px<"+oldSort+" and t.px>="+newSort;
            }
        }
        this.e01Z4Dao.executeNativeBulk(sql,query);
    }

    public void updateSortBeforSave(E01Z4 e01z4, Integer oldSort)  {
        CommonConditionQuery query = new CommonConditionQuery();
        Integer newSort = e01z4.getPx();
        String sql="update e01z4 t set ";
            sql+="t.px=t.px+1";

        sql +=" where t.a38_id = '" + e01z4.getA38().getId() + "'";

        sql+=" and t.px<"+oldSort+" and t.px>="+newSort;
        this.e01Z4Dao.executeNativeBulk(sql,query);
    }
    public int saveFromGzslws(DataSource dataSource)throws Exception{
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        //处理了多少条
        int order = 0;
        Connection conn = dataSource.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        int count =0;
        List<Map<String, Object>> countList = queryRunner.query(conn,
                "select count(*) as count from E_SHORT_ARCHIVES where PK_A38 in(" +
                        "select a38.PK_A38 from a38 where a38.A_STATE = '1' and a38.A_IS_DESTROY = '0' and a38.a3807b='001')" , new MapListHandler(),(Object[]) null);
        for (Iterator<Map<String, Object>> li = countList.iterator(); li.hasNext();) {
            Map<String, Object> m = li.next();
            for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                Map.Entry<String, Object> e = mi.next();
                Object value = e.getValue();
                count = ((BigDecimal)value).intValue();
            }
        }
        Map<String,Integer> pxMap = new HashMap<String,Integer>();//接收排序
        Map<String,Object> attMaps = getSavaAttMaps();
        //每次处理400条
        int dealCount = count/400;
        for(int i=0;i<=dealCount;i++){
            int num1 = i*400;
            int num2 = (i+1)*400;
            String sql = "select * from (select E_SHORT_ARCHIVES.*,rownum rn from E_SHORT_ARCHIVES where PK_A38 in(" +
                    "select a38.PK_A38 from a38 where a38.A_STATE = '1' and a38.A_IS_DESTROY = '0' and a38.a3807b='001') " +
                    " order by PK_A38,PK_E_SHORT_ARCHIVES) where rn >"+num1+" and rn<"+num2+" ";
            List<Map<String, Object>> list = queryRunner.query(conn, sql, new MapListHandler(),(Object[]) null);
            for (Iterator<Map<String, Object>> li = list.iterator(); li.hasNext();) {
                Map<String, Object> m = li.next();
                StringBuffer fields = new StringBuffer();
                fields.append("insert into e01z4 (");
                fields.append(" tombstone,tenant_id,create_user_id,create_user_name,create_date ");
                StringBuffer values = new StringBuffer();
                values.append(") values (");
                values.append(" 0 ");
                values.append(",'").append(userLoginDetails.getTenant().getId()).append("'")
                        .append(",'").append(userLoginDetails.getUser().getId()).append("'")
                        .append(",'").append(userLoginDetails.getUsername()).append("'")
                        .append(",").append("now()").append("");
                String a38Id = "";
                int maxPx = 1;
                for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                    Map.Entry<String, Object> e = mi.next();
                    String key = e.getKey();
                    Object value = e.getValue()==null?"":e.getValue();
                    if("PK_A38".equalsIgnoreCase(key.toString())){
                        a38Id = value.toString();
                    }
                    Iterator it = attMaps.entrySet().iterator();
                    boo:while (it.hasNext()) {
                        Map.Entry entry = (Map.Entry) it.next();
                        Object attKey = entry.getKey();
                        Object attValue = entry.getValue();

                        if(key.equalsIgnoreCase(attKey.toString())){
                            fields.append("," + attValue);
                            values.append(",'" + value + "'");
                            break boo;
                        }

                    }
                }
                boolean isHave = false;
                Iterator it = pxMap.entrySet().iterator();
                boo1:while (it.hasNext()) {
                    Map.Entry entry1 = (Map.Entry) it.next();
                    Object pxA38Id = entry1.getKey();
                    Object px = entry1.getValue();
                    if (a38Id.equalsIgnoreCase(pxA38Id.toString())) {
                        isHave = true;
                        maxPx = Integer.parseInt(px.toString())+1;
                        entry1.setValue(maxPx);
                        break boo1;
                    }
                }
                if(isHave==false){
                    pxMap.put(a38Id,1);
                }
                fields.append(" ,px ");

                values.append(",'").append(maxPx).append("'");
                values.append(")");
                List<Object> paramList = new ArrayList<Object>();
                this.e01Z4Dao.executeNativeBulk(fields.append(values).toString(),paramList);
                order++;
            }
        }

        DbUtils.close(conn);
        return order;
    }

    private Map<String,Object> getSavaAttMaps(){
        Map<String,Object> attMaps = new HashMap<String,Object>();
        attMaps.put("PK_E_SHORT_ARCHIVES","id");//欠缺材料主键
        attMaps.put("PK_A38","a38_id");//外键，人员档案主键
        attMaps.put("FILE_NAME","e01Z401");//欠缺材料名称

        //attMaps.put("PK_E01Z1","e_catalog_type_id");//材料类型主键
        //attMaps.put("PK_E01Z1","px");//欠缺材料排序号

        attMaps.put("SHORT_TYPE_CODE","file_type_code");//欠缺材料类型字典代码
        attMaps.put("SHORT_TYPE_CONTENT","file_type_name");//欠缺材料类型字典内容
        attMaps.put("FILE_TIME","file_time");//材料时间
        attMaps.put("FILE_TYPE_CODE","e01Z401B");//材料大类代码
        attMaps.put("FILE_TYPE_NAME","e01Z401A");//材料大类内容



        attMaps.put("REMARK","remark");//备注

        return attMaps;
    }
}
