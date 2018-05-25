package com.hisun.saas.zzb.app.console.bset.service.impl;

import com.google.common.collect.Lists;
import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.bset.dao.AppBsetB01Dao;
import com.hisun.saas.zzb.app.console.bset.dao.AppBsetFl2B01Dao;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetB01;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetFl;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetFl2B01;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetB01Service;
import com.hisun.saas.zzb.app.console.bset.service.AppBsetFlService;
import com.hisun.saas.zzb.app.console.bset.vo.B01TreeVo;

import com.hisun.util.StringUtils;
import com.hisun.util.WebUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.*;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class AppBsetB01ServiceImpl extends BaseServiceImpl<AppBsetB01,String> implements AppBsetB01Service {

    private AppBsetB01Dao appBsetB01Dao;
    @Autowired
    private AppBsetFlService appBsetFlService;
    @Autowired
    private AppBsetFl2B01Dao appBsetFl2B01Dao;

    @Autowired
    public void setBaseDao(BaseDao<AppBsetB01, String> appBsetB01Dao) {
        this.baseDao = appBsetB01Dao;
        this.appBsetB01Dao = (AppBsetB01Dao) appBsetB01Dao;
    }
    public Integer getMaxPx(String parentId){
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String, Object> arg=new HashMap<String, Object>();
        String hql = "select max(t.px) as px from app_bset_b01 t where t.tombstone=(:tombstone) and t.tenant_id=(:tenant_id) ";
        if(parentId!=null && !parentId.equals("1")) {
            hql = hql+ " and t.parent_id=(:parentId)";
        }else{
            hql = hql+ " and t.parent_id is null";
        }
        hql = hql+ " order by  t.px asc";
        arg.put("tombstone", "0");
        arg.put("tenant_id", userLoginDetails.getTenantId());
        if(parentId!=null && !parentId.equals("1")) {
            arg.put("parentId", parentId);
        }
        List<Map> maxSorts = this.appBsetB01Dao.nativeList(hql, arg);
        Integer maxPx = (Integer) maxSorts.get(0).get("px");
        return maxPx;
    }

    /**
     * 排序处理（首先跟最大的排序号比较，如果大于最大排序号则不处理；
     *        如果小于，就一个个比较，当比较到大于它的就把后面大于它的全部+1；）
     * @param
     */
    public void updatePx(String parentId,int oldPx,int newPx){
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        String sql = "UPDATE app_bset_b01 t SET ";
        if(newPx > oldPx) {
            sql = sql + "t.px=t.px-1";
        } else {
            sql = sql + "t.px=t.px+1";
        }

        sql = sql + " where t.tombstone=(:tombstone) and t.tenant_id=(:tenant_id) " ;
        if(parentId!=null && !parentId.equals("")) {
            sql = sql+ " and t.parent_id=(:parentId)";
        }else{
            sql = sql+ " and t.parent_id is null";
        }
        if(newPx > oldPx) {
            sql = sql + " and t.px<=" + newPx + " and t.px >" + oldPx;
        } else if(newPx == oldPx) {
            sql = sql + " and 1<>1";
        } else {
            sql = sql + " and t.px<" + oldPx + " and t.px>=" + newPx;
        }
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("tombstone", "0");
        if(parentId!=null && !parentId.equals("")) {
            paramMap.put("parentId", parentId);
        }
        paramMap.put("tenant_id", userLoginDetails.getTenantId());
        this.appBsetB01Dao.update(sql, paramMap);
    }


    public int saveFromYw(DataSource dataSource)throws Exception{
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        //处理了多少条
        int order = 0;
        Connection conn = dataSource.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        int count =0;
        List<Map<String, Object>> countList = queryRunner.query(conn,
                "select count(*) as count from b000  where b000.B000_B0111 is not null " , new MapListHandler(),(Object[]) null);
        for (Iterator<Map<String, Object>> li = countList.iterator(); li.hasNext();) {
            Map<String, Object> m = li.next();
            for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                Map.Entry<String, Object> e = mi.next();
                Object value = e.getValue();
                count = ((Integer)value).intValue();
            }
        }
        //每次处理400条
        int dealCount = count/400;
        for(int i=0;i<=dealCount;i++){
            int num = i*400;
            String sql = "select top 400 * from b000  where b000.B000_B0111 is not null and b000.B000_B0111 not in"
                    +"(select top "+num+" b000.B000_B0111 from b000)"
                    +"order by  case when b000.B000_DWSX is null then 1 else 0 end ,b000.B000_DWSX ";
            List<Map<String, Object>> list = queryRunner.query(conn, sql, new MapListHandler(),(Object[]) null);
            for (Iterator<Map<String, Object>> li = list.iterator(); li.hasNext();) {
                Map<String, Object> m = li.next();
                StringBuffer fields = new StringBuffer();
                fields.append("insert into app_bset_b01 (");
                fields.append(" tombstone,tenant_id,create_user_id,create_user_name ");
                StringBuffer values = new StringBuffer();
                values.append(") values (");
                values.append(" 0 ");
                values.append(",'").append(userLoginDetails.getTenant().getId()).append("'")
                        .append(",'").append(userLoginDetails.getUser().getId()).append("'")
                        .append(",'").append(userLoginDetails.getUsername()).append("'");

                for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                    Map.Entry<String, Object> e = mi.next();
                    String key = e.getKey();
                    Object value = e.getValue()==null?"":e.getValue();
                    if(key.equalsIgnoreCase("B000_B0111")){
                        fields.append(",id");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("B000_B0101A")){
                        fields.append(",b0101");
                        values.append(",'"+value+"'");
                    }
                 }
                fields.append(",px");
                values.append(","+order+"");
                values.append(")");
                List<Object> paramList = new ArrayList<Object>();
                this.appBsetB01Dao.executeNativeBulk(fields.append(values).toString(),paramList);
                order++;

            }
        }

        DbUtils.close(conn);
        return order;
    }
    //得到树的集合 dataSourceTyle fl为先读取分类，找分类下的机构 b01表示直接找机构组成树结构
    public List<B01TreeVo> getB01TreeVoList(String dataSourceTyle)throws Exception{
        if(dataSourceTyle.equals("fl")){
            return getB01TreeVoListByFl();
        }else{
            return getB01TreeVoListByB01();
        }
    }
    private List<B01TreeVo> getB01TreeVoListByFl()throws Exception{
        CommonConditionQuery query = new CommonConditionQuery();

        CommonOrderBy orderBy = new CommonOrderBy();
        //orderBy.add(CommonOrder.asc("parentFl.px"));
        orderBy.add(CommonOrder.asc("px"));
        List<AppBsetFl> sppBsetFls = this.appBsetFlService.list(query, orderBy);
        List<B01TreeVo> b01TreeVoList = Lists.newArrayList();
        if(sppBsetFls != null){
            for (AppBsetFl fl : sppBsetFls) {
                B01TreeVo b01TreeVo = new B01TreeVo();
                b01TreeVo.setId(fl.getId());
                if(fl.getParentFl()!=null){//由于部分节点找不到parent 故做此处理
                    try {
                        fl.getParentFl().getId();
                    }catch(Exception e) {
                        b01TreeVo.setpId("0");
                        b01TreeVo.setName(fl.getFl());
                        b01TreeVo.setIsParent(true);
                        b01TreeVo.setDropInner(true);
                        b01TreeVo.setOpen(true);
                        b01TreeVo.setDataType("fl");
                        b01TreeVoList.add(b01TreeVo);
                        continue;
                    }
                }
                if(fl.getParentFl() != null) {
                    b01TreeVo.setpId(fl.getParentFl().getId());
                    b01TreeVo.setName(fl.getFl());
                    b01TreeVo.setHref(fl.getId());
                    b01TreeVo.setNoR(false);
                    b01TreeVo.setDropInner(true);
                }else{
                    b01TreeVo.setpId("0");
                    b01TreeVo.setName(fl.getFl());
                    b01TreeVo.setIsParent(true);
                    b01TreeVo.setDropInner(true);
                    b01TreeVo.setOpen(true);
                }
                b01TreeVo.setDataType("fl");
//                        if(StringUtils.isNotBlank(inst.getMonitor().getIcon())){
//                            b01TreeVo.setIcon(contextPath + "/monitor/ajax/icon/" + inst.getMonitor().getId() + ".jpg?OWASP_CSRFTOKEN=" + request.getSession().getAttribute("OWASP_CSRFTOKEN"));
//                        } else {
//                            b01TreeVo.setIcon(contextPath + MonitorVo.DEFAULT_PATH);
//                        }
                b01TreeVoList.add(b01TreeVo);
                if(fl.getAppBsetFl2B01s()!=null){//将机构增加到树
                    for(AppBsetFl2B01 fl2b01 : fl.getAppBsetFl2B01s()){
                        try {
                            fl2b01.getAppBsetB01().getId();
                        }catch(Exception e) {
                            continue;
                        }
                        AppBsetB01 appBsetB01 = fl2b01.getAppBsetB01();
                        if(appBsetB01!=null) {
                            b01TreeVo = new B01TreeVo();
                            b01TreeVo.setpId(fl.getId());
                            b01TreeVo.setId(appBsetB01.getId());
                            b01TreeVo.setName(appBsetB01.getB0101());
                            b01TreeVo.setHref(appBsetB01.getId());
                            b01TreeVo.setDataType("b01");
                            b01TreeVo.setNoR(false);
                            b01TreeVo.setDropInner(true);
                            b01TreeVoList.add(b01TreeVo);
                        }
                    }
                }
            }
        }
        return b01TreeVoList;
    }


    private List<B01TreeVo> getB01TreeVoListByB01()throws Exception{
        CommonConditionQuery query = new CommonConditionQuery();
        CommonOrderBy orderBy = new CommonOrderBy();

        orderBy.add(CommonOrder.asc("queryCode"));
        orderBy.add(CommonOrder.asc("parentB01.id"));
        orderBy.add(CommonOrder.asc("px"));
        List<AppBsetB01> appBsetB01s = this.appBsetB01Dao.list(query, orderBy);
        List<B01TreeVo> b01TreeVoList = Lists.newArrayList();
        if(appBsetB01s != null) {
            for (AppBsetB01 b01 : appBsetB01s) {
                B01TreeVo b01TreeVo = new B01TreeVo();
                b01TreeVo.setId(b01.getId());
                if(b01.getParentB01()!= null) {
                    b01TreeVo.setpId(b01.getParentB01().getId());
                    b01TreeVo.setName(b01.getB0101());
                    b01TreeVo.setHref(b01.getId());
                    b01TreeVo.setNoR(false);
                    b01TreeVo.setDataType("b01");
                    b01TreeVo.setDropInner(true);
                }else{
                    b01TreeVo.setpId("0");
                    b01TreeVo.setName(b01.getB0101());
                    b01TreeVo.setIsParent(true);
                    b01TreeVo.setDropInner(true);
                    b01TreeVo.setDataType("b01");
                    b01TreeVo.setOpen(true);
                }
                b01TreeVoList.add(b01TreeVo);
            }
        }
        return b01TreeVoList;
    }


    public void deleteAllData() throws Exception{
        this.appBsetFl2B01Dao.deleteBatch(null);
        this.appBsetB01Dao.deleteBatch(null);
    }


    public int saveFromZdwx(DataSource dataSource)throws Exception{

        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        //处理了多少条
        int order = 0;
        Connection conn = dataSource.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        int count =0;
        List<Map<String, Object>> countList = queryRunner.query(conn,
                "select count(*) as count from b01  where b01.b0111 <> '-1' " , new MapListHandler(),(Object[]) null);
        for (Iterator<Map<String, Object>> li = countList.iterator(); li.hasNext();) {
            Map<String, Object> m = li.next();
            for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                Map.Entry<String, Object> e = mi.next();
                Object value = e.getValue();
                count = ((Long)value).intValue();
            }
        }
        //每次处理400条
        int dealCount = count/400;
        for(int i=0;i<=dealCount;i++){
            int num = i*400;
            String sql = "select * from b01  where b01.b0111 <> '-1' "
                    +"order by b01.b0111 limit "+num+",400";
            List<Map<String, Object>> list = queryRunner.query(conn, sql, new MapListHandler(),(Object[]) null);
            for (Iterator<Map<String, Object>> li = list.iterator(); li.hasNext();) {
                Map<String, Object> m = li.next();
                StringBuffer fields = new StringBuffer();
                fields.append("insert into app_bset_b01 (");
                fields.append(" tombstone,tenant_id,create_user_id,create_user_name ");
                StringBuffer values = new StringBuffer();
                values.append(") values (");
                values.append(" 0 ");
                values.append(",'").append(userLoginDetails.getTenant().getId()).append("'")
                        .append(",'").append(userLoginDetails.getUser().getId()).append("'")
                        .append(",'").append(userLoginDetails.getUsername()).append("'");

                for (Iterator<Map.Entry<String, Object>> mi = m.entrySet().iterator(); mi.hasNext();) {
                    Map.Entry<String, Object> e = mi.next();
                    String key = e.getKey();
                    Object value = e.getValue()==null?"":e.getValue();
                    if(key.equalsIgnoreCase("b0111")){
                        fields.append(",id");
                        values.append(",'"+value+"'");
                        fields.append(",query_code");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("b0101")){
                        fields.append(",b0101");
                        values.append(",'"+value+"'");
                    }else if(key.equalsIgnoreCase("b0121")){
                        //-1的父机构相当于最顶层
                        if((value.toString().equals("-1")==false)){
                            fields.append(",parent_id");
                            values.append(",'"+value+"'");
                        }
                    }else if(key.equalsIgnoreCase("sortid")){
                        fields.append(",px");
                        values.append(",'"+value+"'");
                    }
                }
                values.append(")");
                List<Object> paramList = new ArrayList<Object>();
                this.appBsetB01Dao.executeNativeBulk(fields.append(values).toString(),paramList);
                order++;

            }
        }

        DbUtils.close(conn);
        return order;
    }


    public String toSqliteInsertSql(AppBsetB01 entity){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" app_bset_b01 ");
        sb.append("(");
        sb.append("id");
        sb.append(",b0101");
        if(entity.getParentB01()!= null) {
            sb.append(",parent_id");
        }
        sb.append(",px");
        sb.append(",query_code");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.trimNull2Empty(entity.getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getB0101())+"'");
        if(entity.getParentB01()!=null) {
            sb.append(",'" + StringUtils.trimNull2Empty(entity.getParentB01().getId()) + "'");
        }
        sb.append(","+entity.getPx());
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getQueryCode())+"'");
        sb.append(")");
        return sb.toString();
    }
}
