/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a38.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.tenant.privilege.dao.TenantPrivilegeDao;
import com.hisun.saas.sys.tenant.privilege.entity.TenantPrivilege;
import com.hisun.saas.sys.tenant.privilege.service.TenantPrivilegeService;
import com.hisun.saas.zzb.dzda.a38.dao.A38Dao;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.dak.vo.DakVo;
import com.hisun.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuzj {279421824@qq.com}
 */
@Service
public class A38ServiceImpl extends BaseServiceImpl<A38,String>
        implements A38Service {

    private A38Dao a38Dao;

    @Resource
    public void setBaseDao(BaseDao<A38, String> baseDao) {
        this.baseDao = baseDao;
        this.a38Dao = (A38Dao)baseDao;
    }

    public List<A38> gjcxList(DakVo dakVo,UserLoginDetails userLoginDetails){
        StringBuffer a38Hql = new StringBuffer();
        StringBuffer e01z1Hql = new StringBuffer();

        a38Hql.append(" where a38.sjzt = 1 and a38.tenant.id = '" + userLoginDetails.getTenantId() + "'");
        if(StringUtils.isNotEmpty(dakVo.getDabh())){
            a38Hql.append(" and a38.dabh like '%"+dakVo.getDabh()+"%'");
        }
        if(StringUtils.isNotEmpty(dakVo.getSmxh())){
            a38Hql.append(" and a38.smxh like '%"+dakVo.getSmxh()+"%'");
        }
        if(StringUtils.isNotEmpty(dakVo.getA0101())){//姓名
            a38Hql.append(" and a38.a0101 like '%" + dakVo.getA0101() + "%'");
        }
        if(StringUtils.isNotEmpty(dakVo.getA0157())){//单位职务
            a38Hql.append(" and a38.a0157 like '%" + dakVo.getA0157() + "%'");
        }

        if(StringUtils.isNotEmpty(dakVo.getGbztCodes())){//干部状态
            String str[] = dakVo.getGbztCodes().split(",");
            String queryCode ="";
            for(int i=0;i<str.length;i++){
                if(queryCode.equals("")){
                    queryCode = "'"+str[i]+"'";
                }else{
                    queryCode =queryCode+ ",'"+str[i]+"'";
                }
            }
            a38Hql.append(" and a38.gbztCode in ("+queryCode+")");
        }

        if(StringUtils.isNotEmpty(dakVo.getDaztCodes())){//档案状态
            String str[] = dakVo.getDaztCodes().split(",");
            String queryCode ="";
            for(int i=0;i<str.length;i++){
                if(queryCode.equals("")){
                    queryCode = "'"+str[i]+"'";
                }else{
                    queryCode =queryCode+ ",'"+str[i]+"'";
                }
            }
            a38Hql.append(" and a38.daztCode in ("+queryCode+")");
        }

        if(StringUtils.isNotEmpty(dakVo.getDutyLevelValue())){//现职级
            String str[] = dakVo.getDutyLevelValue().split(",");
            String queryCode ="";
            for(int i=0;i<str.length;i++){
                if(queryCode.equals("")){
                    queryCode = "'"+str[i]+"'";
                }else{
                    queryCode =queryCode+ ",'"+str[i]+"'";
                }
            }
            a38Hql.append(" and a38.dutyLevelCode in ("+queryCode+")");
        }
        String listTmp= dakVo.getDutyLevelTimeBase();
        if(listTmp!=null && !listTmp.equals(",")) {
            String[] arrTmp = dakVo.getDutyLevelTimeBase().split(",");
            if(arrTmp.length>0) {
                dakVo.setDutyLevelTimeBaseStart(arrTmp[0]);
            }
            if(arrTmp.length>1) {
                dakVo.setDutyLevelTimeBaseEnd(arrTmp[1]);
            }
            if (StringUtils.isNotEmpty(dakVo.getDutyLevelTimeBaseStart())) {//职级时间
                a38Hql.append(" and a38.dutyLevelTimeBase >=  '" + dakVo.getDutyLevelTimeBaseStart() + "'");
            }
            if (StringUtils.isNotEmpty(dakVo.getDutyLevelTimeBaseEnd())) {//职级时间
                a38Hql.append(" and a38.dutyLevelTimeBase <=  '" + dakVo.getDutyLevelTimeBaseEnd() + "'");
            }
        }

        if(StringUtils.isNotEmpty(dakVo.getA3804A())){//档案来处
            a38Hql.append(" and a38.a3804A like '%" + dakVo.getA3804A() + "%'");
        }
        if(StringUtils.isNotEmpty(dakVo.getA3821())){//专去单位
            a38Hql.append(" and a38.a3821 like '%" + dakVo.getA3821() + "%'");
        }
        listTmp= dakVo.getA3801();
        if(listTmp!=null && !listTmp.equals(",")) {//接收时间
            String[] arrTmp = dakVo.getA3801().split(",");
            if(arrTmp.length>0) {
                dakVo.setA3801Start(arrTmp[0]);
            }
            if(arrTmp.length>1) {
                dakVo.setA3801End(arrTmp[1]);
            }
            if (StringUtils.isNotEmpty(dakVo.getA3801Start()) && StringUtils.isEmpty(dakVo.getA3801End())) {
                a38Hql.append(" and a38.a3801 >= '" + dakVo.getA3801Start() + "'");
            } else if (StringUtils.isEmpty(dakVo.getA3801Start()) && StringUtils.isNotEmpty(dakVo.getA3801End())) {
                a38Hql.append(" and a38.a3801 <= '" + dakVo.getA3801End() + "'");
            } else if (StringUtils.isNotEmpty(dakVo.getA3801Start()) && StringUtils.isNotEmpty(dakVo.getA3801End())) {
                a38Hql.append(" and ((a38.a3801 >= '" + dakVo.getA3801Start() + "' and a38.a3801 <= '%" + dakVo.getA3801End() + "%') or a38.a3801 like '%" + dakVo.getA3801End() + "%')");
            }
        }

       listTmp= dakVo.getA3817();
      if(listTmp!=null && !listTmp.equals(",")) {
            String[] arrTmp = dakVo.getA3817().split(",");
            if(arrTmp.length>0) {
                dakVo.setA3817Start(arrTmp[0]);
            }
            if(arrTmp.length>1) {
                 dakVo.setA3817End(arrTmp[1]);
            }
          if (StringUtils.isNotEmpty(dakVo.getA3817Start()) && StringUtils.isEmpty(dakVo.getA3817End())) {
              a38Hql.append(" and a38.a3817 >= '" + dakVo.getA3817Start() + "'");
          } else if (StringUtils.isEmpty(dakVo.getA3817Start()) && StringUtils.isNotEmpty(dakVo.getA3817End())) {
              a38Hql.append(" and a38.a3817 <= '" + dakVo.getA3817End() + "'");
          } else if (StringUtils.isNotEmpty(dakVo.getA3817Start()) && StringUtils.isNotEmpty(dakVo.getA3817End())) {
              a38Hql.append(" and ((a38.a3817 >= '" + dakVo.getA3817Start() + "' and a38.a3817 <= '%" + dakVo.getA3817End() + "%') or a38.a3817 like '%" + dakVo.getA3817End() + "%')");
          }

        }

        if(StringUtils.isNotEmpty(dakVo.getE01Z111())){//材料名称
            e01z1Hql.append(" and e.e01Z111 like '%" + dakVo.getE01Z111() + "%'");
        }
        if(StringUtils.isNotEmpty(dakVo.getE01Z101B())){//材料类型
            String str[] = dakVo.getE01Z101B().split(",");
            String queryCode ="";
            for(int i=0;i<str.length;i++){
                if(queryCode.equals("")){
                    queryCode = "'"+str[i]+"'";
                }else{
                    queryCode =queryCode+ ",'"+str[i]+"'";
                }
            }
            e01z1Hql.append(" and e.e01Z101B in ("+queryCode+")");
        }

        if(StringUtils.isNotEmpty(dakVo.getE01Z207())){//接收人
            e01z1Hql.append(" and e.e01Z207 like '%" + dakVo.getE01Z207() + "%'");
        }
        listTmp= dakVo.getE01Z201();
      if(listTmp!=null && !listTmp.equals(",")) {
          String[] arrTmp = dakVo.getE01Z201().split(",");
          if(arrTmp.length>0) {
              dakVo.setE01Z201Start(arrTmp[0]);
          }
          if(arrTmp.length>1) {
              dakVo.setE01Z201End(arrTmp[1]);
          }
          if (StringUtils.isNotEmpty(dakVo.getE01Z201Start()) && StringUtils.isEmpty(dakVo.getE01Z201End())) {
              e01z1Hql.append(" and e.e01Z201 >= '" + dakVo.getE01Z201Start() + "'");
          } else if (StringUtils.isEmpty(dakVo.getE01Z201Start()) && StringUtils.isNotEmpty(dakVo.getE01Z201End())) {
              e01z1Hql.append(" and e.e01Z201 <= '" + dakVo.getE01Z201End() + "'");
          } else if (StringUtils.isNotEmpty(dakVo.getE01Z201Start()) && StringUtils.isNotEmpty(dakVo.getE01Z201End())) {
              e01z1Hql.append(" and ((e.e01Z201 >= '" + dakVo.getE01Z201Start() + "' and e.e01Z201 <= '%" + dakVo.getE01Z201End() + "%') or e.e01Z201 like '%" + dakVo.getE01Z201End() + "%')");
          }
        }

        if(StringUtils.isNotEmpty(dakVo.getE01Z204())){//材料来处
            e01z1Hql.append(" and e.e01Z204 like '%" + dakVo.getE01Z204() + "%'");
        }
        listTmp= dakVo.getE01z1CreatedTime();
      if(listTmp!=null && !listTmp.equals(",")) {
          String[] arrTmp = dakVo.getE01z1CreatedTime().split(",");
          if(arrTmp.length>0) {
              dakVo.setE01z1CreatedTimeStart(arrTmp[0]);
          }
          if(arrTmp.length>1) {
              dakVo.setE01z1CreatedTimeEnd(arrTmp[1]);
          }
          if (StringUtils.isNotEmpty(dakVo.getE01z1CreatedTimeStart()) && StringUtils.isEmpty(dakVo.getE01z1CreatedTimeEnd())) {
              e01z1Hql.append(" and e.createDate >= '" + dakVo.getE01z1CreatedTimeStart() + "'");
          } else if (StringUtils.isEmpty(dakVo.getE01z1CreatedTimeStart()) && StringUtils.isNotEmpty(dakVo.getE01z1CreatedTimeEnd())) {
              e01z1Hql.append(" and e.createDate <= '" + dakVo.getE01z1CreatedTimeEnd() + "'");
          } else if (StringUtils.isNotEmpty(dakVo.getE01z1CreatedTimeStart()) && StringUtils.isNotEmpty(dakVo.getE01z1CreatedTimeEnd())) {
              e01z1Hql.append(" and ((e.createDate >= '" + dakVo.getE01z1CreatedTimeStart() + "' and e.createDate <= '%" + dakVo.getE01z1CreatedTimeEnd() + "%') or e.createDate like '%" + dakVo.getE01z1CreatedTimeEnd() + "%')");
          }

        }
        listTmp= dakVo.getE01Z117();
      if(listTmp!=null && !listTmp.equals(",")) {
          String[] arrTmp = dakVo.getE01Z117().split(",");
          if(arrTmp.length>0) {
              dakVo.setE01Z117Start(arrTmp[0]);
          }
          if(arrTmp.length>1) {
              dakVo.setE01Z117End(arrTmp[1]);
          }
          if (StringUtils.isNotEmpty(dakVo.getE01Z117Start()) && StringUtils.isEmpty(dakVo.getE01Z117End())) {
              e01z1Hql.append(" and e.e01Z117 >= '" + dakVo.getE01z1CreatedTimeStart() + "'");
          } else if (StringUtils.isEmpty(dakVo.getE01Z117Start()) && StringUtils.isNotEmpty(dakVo.getE01Z117End())) {
              e01z1Hql.append(" and e.e01Z117 <= '" + dakVo.getE01Z117End() + "'");
          } else if (StringUtils.isNotEmpty(dakVo.getE01Z117Start()) && StringUtils.isNotEmpty(dakVo.getE01z1CreatedTimeEnd())) {
              e01z1Hql.append(" and ((e.e01Z117 >= '" + dakVo.getE01z1CreatedTimeStart() + "' and e.e01Z117 <= '%" + dakVo.getE01Z117End() + "%') or e.e01Z117 like '%" + dakVo.getE01Z117End() + "%')");
          }
        }
        if(StringUtils.isNotEmpty(dakVo.getYjztps())){//是否加载图片
            if("1".equals(dakVo.getYjztps())){
                e01z1Hql.append(" and e.yjztps >0 ");
            }else {
                e01z1Hql.append(" and ( e.yjztps <=0 or e.yjztps is null )");
            }
        }
        StringBuffer queryHql = new StringBuffer();
        queryHql.append("select DISTINCT(a38) from A38 a38 ");
        if(e01z1Hql.length()>0){
            queryHql.append("  left join a38.e01z1s e");
        }
        queryHql.append(a38Hql);
        queryHql.append(e01z1Hql);
        queryHql.append(" order by a38.smxh desc , a38.a0101 asc ");

        Map<String,Object> paramMap = new HashMap<>();
        List<A38> a38List = a38Dao.list(queryHql.toString(),null,null);
        return a38List;
    }

}
