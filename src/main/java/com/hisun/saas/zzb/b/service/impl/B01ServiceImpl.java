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
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.entity.TombstoneEntity;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.b.dao.B01Dao;
import com.hisun.saas.zzb.b.entity.B01;
import com.hisun.saas.zzb.b.service.B01Service;
import com.hisun.saas.zzb.b.vo.B01TreeNode;
import com.hisun.saas.zzb.b.vo.B01Vo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.*;

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
        String hql = "select max(t.bPx)+1 as sort from B01 t ";
        if (parentId != null && !parentId.equals("")) {
            hql = hql + "where t.parentB01.id =:parentId";
            map.put("parentId", parentId);
        } else {
            hql = hql + "where t.parentB01.id is null";
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
    public List<B01TreeNode> getB01TreeVoList(String id,String param,String defaultkeys,String noLoadNsjg)throws Exception{
        CommonConditionQuery query = new CommonConditionQuery();
        if (id == null || id.equals("")) {
            if(param!=null && !param.equals("")){
                query.add(CommonRestrictions.and(" b0101 like:param ", "param", "%"+param+"%"));
                query.add(CommonRestrictions.and(" tombstone=:tombstone ", "tombstone", TombstoneEntity.TOMBSTONE_FALSE));
            }else {
                query.add(CommonRestrictions.and(" b_sjjg='' or b_sjjg is null and tombstone=:tombstone ", "tombstone", TombstoneEntity.TOMBSTONE_FALSE));
            }
        } else {
            query.add(CommonRestrictions.and(" parentB01.b0100=:parentNodeId ", "parentNodeId", id));
            query.add(CommonRestrictions.and(" tombstone=:tombstone ", "tombstone", TombstoneEntity.TOMBSTONE_FALSE));
        }
        if(noLoadNsjg!=null && noLoadNsjg.equals("true")){
            query.add(CommonRestrictions.and(" bSjlx<>:bSjlx ", "bSjlx", 1));
        }
       //加载默认值的节点及其父节点
        if(defaultkeys!=null && !defaultkeys.equals("")) {
            List defaultkeysList = this.getB01sByDefaultkeys(defaultkeys);
            query.add(CommonRestrictions.or(" b0100 in (:defaultkeysList) ", "defaultkeysList",defaultkeysList));
        }
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.asc("bCxbm"));
        List<B01> appBsetB01s = this.b01Dao.list(query, orderBy);
        List<B01TreeNode> b01TreeVoList = Lists.newArrayList();
        if(appBsetB01s != null) {
            for (B01 b01 : appBsetB01s) {
                B01TreeNode b01TreeNode = new B01TreeNode();
                b01TreeNode.setId(b01.getB0100());
                if(param==null || param.equals("")) {
                    if (b01.getChildB01s() != null && b01.getChildB01s().size() > 0) {
                        b01TreeNode.setIsParent(true);
                    }
                }
                b01TreeNode.setName(b01.getB0101());
                b01TreeNode.setbSjlx(b01.getbSjlx());
                b01TreeNode.setKey(b01.getB0100());
                if(defaultkeys!=null && !defaultkeys.equals("")) {
                    String str[] = defaultkeys.split(",");
                    for(String key :str){
                        if(key.equals(b01.getB0100())){
                            b01TreeNode.setChecked(true);
                        }
                    }
                }
                if(b01.getParentB01()!= null&&b01.getParentB01().getB0100()!=null&&!b01.getParentB01().getB0100().equals("")) {
                    b01TreeNode.setpId(b01.getParentB01().getB0100());
                }else{
                    b01TreeNode.setpId(null);
//                    b01TreeNode.setOpen(true);
                }
                b01TreeVoList.add(b01TreeNode);
            }
        }
        return b01TreeVoList;
    }

    /**
     * 根据默认值得到及节点及其父节点
     * @param defaultkeys
     * @return
     */
    private List<String> getB01sByDefaultkeys(String defaultkeys){
        List<String> b01Ids = new ArrayList<String>();
        if(defaultkeys!=null && !defaultkeys.equals("")){
            String str[] = defaultkeys.split(",");
            List idList =  Arrays.asList(str);

            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" b0100 in (:idList) ", "idList",idList));
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("bCxbm"));
            List<B01> b01s = b01Dao.list(query, orderBy);//得到默认值的对象
            for(B01 b01 : b01s){
                this.getParentB01(b01Ids,b01);
            }
        }
        return b01Ids;
    }

    //递归取得所有的父节点及兄弟节点（如果不取得兄弟节点则不会再加载）
    private void getParentB01(List<String> b01Ids ,B01 b01){
        B01 parentB01 = b01.getParentB01();
        if(parentB01!=null&&parentB01.getB0100()!=null&&!parentB01.getB0100().equals("")){
            System.out.println("parentB01.getB0100()=="+parentB01.getB0100());
            System.out.println("parentB01.getB0101()=="+parentB01.getB0101());
            b01Ids.add(b01.getB0100());
            List<B01> xdB01s = parentB01.getChildB01s();
            for(B01 xdB01 : xdB01s){
                b01Ids.add(xdB01.getB0100());
            }
            this.getParentB01(b01Ids,parentB01);
        }else{
            b01Ids.add(b01.getB0100());
            return;
        }
    }

    @Override
    public String saveB01(B01Vo vo) throws Exception {
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        Integer oldPx = this.getMaxSort(vo.getParentId());
        Integer newPx = vo.getbPx();
        int retval = 0;
        if(oldPx!=null){
            retval = newPx.compareTo(oldPx);
        }

        if(retval>0){
            newPx = oldPx;
        }
        String cxbm = "";
        B01 parentB01 = this.getByPK(vo.getParentId());
        DecimalFormat decimalFormat = new DecimalFormat("000");
        if(!StringUtils.equals("1", vo.getParentId())){
            if(parentB01!=null){
                cxbm = parentB01.getbCxbm()+decimalFormat.format(newPx);
            }else{
                cxbm = decimalFormat.format(newPx);
            }
        }else{
            cxbm = decimalFormat.format(newPx);
        }
        B01 entity = new B01();
        BeanUtils.copyProperties(vo,entity);
        entity.setB0100(null);
        entity.setParentB01(parentB01);
        entity.setbCxbm(cxbm);
        entity.setbPx(newPx);
        this.updatePxAndCxbm(entity,parentB01,oldPx);

        EntityWrapper.wrapperSaveBaseProperties(entity,details);
        return b01Dao.save(entity);
    }

    @Override
    public void updateB01(B01 b01, B01 oldB01,String oldPid) throws Exception {
        //B01 oldB01 = new B01();
        //oldB01 = b01Dao.getByPK(b01.getB0100());
        String parentId = b01.getParentB01().getB0100();
        int newSort = b01.getbPx();
        int maxSort = this.getMaxSort(parentId);
        if(newSort>maxSort){
            newSort = maxSort;
        }

        String cxbm = "";
        DecimalFormat decimalFormat = new DecimalFormat("000");
        if(!StringUtils.equals("1", parentId)){
            B01 parentB01 = b01.getParentB01();
            cxbm = parentB01.getbCxbm()+decimalFormat.format(newSort);
        }else{
            cxbm = decimalFormat.format(newSort);
        }
        b01.setbCxbm(cxbm);
        b01.setbPx(newSort);
        if(StringUtils.isNotBlank(oldB01.getbCxbm())){
            this.refreshCxbmToTmp(oldB01.getbCxbm());
        }
        if(StringUtils.equals(parentId, oldPid)){
            this.updatePxAndCxbm(b01, b01.getParentB01(),oldB01.getbPx());
        }else{
            this.updatePxAndCxbm(b01,b01.getParentB01(), maxSort);
        }
        if(StringUtils.isNotBlank(oldB01.getbCxbm())){
            this.refreshCxbmToFormal(oldB01.getbCxbm(), cxbm);
        }
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        EntityWrapper.wrapperUpdateBaseProperties(b01,details);
        this.update(b01);
    }

    @Override
    public void updatePxAndCxbm(B01 b01, B01 parentB01,Integer oldSort) throws Exception {
        Map<String, Object> map = Collections.emptyMap();
        Integer newSort = b01.getbPx();
        String newCxbm = b01.getbCxbm();

        DecimalFormat decimalFormat = new DecimalFormat("000");
        String oldCxbm;
        if(parentB01==null){
            oldCxbm = decimalFormat.format(oldSort);
        }else{
            oldCxbm = parentB01.getbCxbm()+decimalFormat.format(oldSort);
        }

        String sql="UPDATE b01 t SET ";
        if(newSort>oldSort){
            sql+="t.b_px=t.b_px-1";
        }else{
            sql+="t.b_px=t.b_px+1";
        }
        sql+=" where t.b_sjjg='"+parentB01.getB0100()+"' ";
        if(newSort>oldSort){
            sql+=" and t.b_px<="+newSort+" and t.b_px >"+oldSort;
        }else{
            if(newSort==oldSort){
                sql+=" and t.b_px = -100";
            }else{
                sql+=" and t.b_px<"+oldSort+" and t.b_px>="+newSort;
            }
        }
        b01Dao.update(sql,map);

        if(newSort>oldSort){
            this.refreshQueryCodeForUp(oldCxbm, newCxbm);
        }else{
            if(newSort==oldSort){
            }else{
                this.refreshQueryCodeForDown(newCxbm, oldCxbm);
            }
        }
    }

    private void
    refreshQueryCodeForUp(String startCode, String endCode)
            throws Exception {
        Map<String, Object> map = Collections.emptyMap();
        int i = startCode.length();
        String updateSql = "UPDATE b01 r SET r.b_cxbm = concat(substring(r.b_cxbm, 1, "+i+"-3),right(concat('000',substring(r.b_cxbm,"+i+"-3+1,3)-1),3),substring(r.b_cxbm,"+i+"+1,length(r.b_cxbm)))"+
                "WHERE substr(r.b_cxbm,1,"+i+") >= '"+startCode+"' and substr(r.b_cxbm,1,"+i+") <='"+endCode+"'" ;
        this.b01Dao.update(updateSql,map);
    }

    private void refreshQueryCodeForDown(String startCode, String endCode)
            throws Exception {
        Map<String, Object> map = Collections.emptyMap();
        int i = startCode.length();
        String updateSql = "UPDATE b01 r SET r.b_cxbm = concat(substring(r.b_cxbm, 1, "+i+"-3),right(concat('000',substring(r.b_cxbm,"+i+"-3+1,3)+1),3),substring(r.b_cxbm,"+i+"+1,length(r.b_cxbm)))"+
                "WHERE substr(r.b_cxbm,1,"+i+") >= '"+startCode+"' and substr(r.b_cxbm,1,"+i+") <='"+endCode+"' ";
        this.b01Dao.update(updateSql,map);
    }

    private void refreshCxbmToTmp(String oldCxbm) throws Exception {
        Map<String, Object> map = Collections.emptyMap();
        int i = oldCxbm.length();
        String tmpCode = oldCxbm.substring(0,oldCxbm.length()-3)+"tmp";
        String updateSql = "UPDATE b01 b set b.b_cxbm = concat('"+tmpCode+"',substr(b.b_cxbm,"+i+"+1,length(b.b_cxbm))) where substr(b.b_cxbm,1,"+i+") like '"+oldCxbm+"%' ";
        this.b01Dao.update(updateSql, map);
    }

    private void refreshCxbmToFormal(String oldCxbm,String newCxbm)  {
        Map<String, Object> map = Collections.emptyMap();
        int i = oldCxbm.length();
        String tmpCode = oldCxbm.substring(0,oldCxbm.length()-3)+"tmp";
        String updateSql="update b01 b set b.b_cxbm = concat('"+newCxbm+"',substr(b.b_cxbm,"+(i+1)+",length(b.b_cxbm))) where substr(b.b_cxbm,1,"+i+") like'"+tmpCode+"%' ";
        this.b01Dao.update(updateSql, map);
    }
}
















