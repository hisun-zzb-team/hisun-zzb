/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a38.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.admin.dictionary.service.DictionaryItemService;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.tenant.privilege.dao.TenantPrivilegeDao;
import com.hisun.saas.sys.tenant.privilege.entity.TenantPrivilege;
import com.hisun.saas.sys.tenant.privilege.service.TenantPrivilegeService;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a32.service.A32Service;
import com.hisun.saas.zzb.dzda.a32.vo.A32Vo;
import com.hisun.saas.zzb.dzda.a38.Constants;
import com.hisun.saas.zzb.dzda.a38.dao.A38Dao;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.exchange.A38ExcelExchange;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.a38.vo.A38ExcelVo;
import com.hisun.saas.zzb.dzda.a38.vo.A38Vo;
import com.hisun.saas.zzb.dzda.a38.vo.WrongExcelColumn;
import com.hisun.saas.zzb.dzda.a52.service.A52Service;
import com.hisun.saas.zzb.dzda.a52.vo.A52Vo;
import com.hisun.saas.zzb.dzda.dak.vo.DakVo;
import com.hisun.saas.zzb.dzda.e01z2.service.E01z2Service;
import com.hisun.saas.zzb.dzda.e01z2.vo.E01z2Vo;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.saas.zzb.dzda.mlcl.vo.E01Z1ExcelVo;
import com.hisun.saas.zzb.dzda.util.DaUtils;
import com.hisun.util.StringUtils;
import com.hisun.util.URLEncoderUtil;
import com.hisun.util.UUIDUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.*;

/**
 * @author liuzj {279421824@qq.com}
 */
@Service
public class A38ServiceImpl extends BaseServiceImpl<A38,String>
        implements A38Service {

    @Value("${sys.upload.absolute.path}")
    private String uploadBasePath;

    @Resource
    A38ExcelExchange a38ExcelExchange;

    private A38Dao a38Dao;

    @Resource
    private E01Z1Service e01Z1Service;

    @Resource
    private A32Service a32Service;

    @Resource
    private A52Service a52Service;

    @Resource
    private E01z2Service e01z2Service;

    @Resource
    private DictionaryItemService dictionaryItemService;

    @Resource
    public void setBaseDao(BaseDao<A38, String> baseDao) {
        this.baseDao = baseDao;
        this.a38Dao = (A38Dao)baseDao;
    }

    public String getGjcxHql(DakVo dakVo,UserLoginDetails userLoginDetails){
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

//        Map<String,Object> paramMap = new HashMap<>();
//        List<A38> a38List = a38Dao.list(queryHql.toString(),paramMap,pageNum, pageSize);
        return queryHql.toString();
    }


    /**
     * 检查a38Vo数据是否合法
     * @param a38Vo
     * @return
     */
    public Map<String,Object> checkA38(A38Vo a38Vo){
        boolean isRight = false;
        List<WrongExcelColumn> wrongExcelColumns = new ArrayList<>();
        Map<String,Object> returnMap = new HashMap<>();
        WrongExcelColumn wrongExcelColumn;
        boolean flag = false;//判断是否存在非法数据
        if(StringUtils.isEmpty(a38Vo.getA0101())){
            wrongExcelColumn = new WrongExcelColumn();
            wrongExcelColumn.setLines("B2");
            wrongExcelColumn.setReason("姓名不能为空");
            wrongExcelColumn.setWrongExcel("基本信息");
            wrongExcelColumns.add(wrongExcelColumn);
            flag = true;
        }

        if (DaUtils.isNotDate(a38Vo.getA0107())) {
            wrongExcelColumn = new WrongExcelColumn();
            wrongExcelColumn.setLines("F2");
            wrongExcelColumn.setReason("生日日期格式错误");
            wrongExcelColumn.setWrongExcel("基本信息");
            wrongExcelColumns.add(wrongExcelColumn);
            flag = true;
        }
        if (flag) {
            isRight = true;
        }
        returnMap.put("isRight",isRight);
        returnMap.put("wrongExcelColumns",wrongExcelColumns);
        return returnMap;
    }

    /**
     * 检查传入Excel的所有数据 将结果以map格式返回
     * @param a38ExcelVo
     * @param returnMap
     * @return
     */
    public Map<String,Object> checkA38ExcelData(A38ExcelVo a38ExcelVo,Map<String,Object> returnMap){
        Map<String,Object> checkMap;
        boolean isRight = false;
        boolean gzbdIsEmpty = false;
        boolean cljsIsEmpty = false;
        boolean checkIsRight;
        List<WrongExcelColumn> wrongExcelColumns=new ArrayList<>();
        if(a38ExcelVo!=null){
            //检查档案
            A38Vo jbxxA38Vo = a38ExcelVo.getJbxxA38Vo();

            returnMap = checkA38(jbxxA38Vo);
            checkIsRight= (boolean) returnMap.get("isRight");
            isRight=checkIsRight;
            if(isRight){
                wrongExcelColumns.addAll((List<WrongExcelColumn>) returnMap.get("wrongExcelColumns"));
            }

            //检查职务变动
            A38Vo a38VoForA52 = a38ExcelVo.getZwbdA38Vo();
            if(a38VoForA52!=null&&a38VoForA52.getA52Vos().size()>0){
                List<A52Vo> a52Vos = a38VoForA52.getA52Vos();
                checkMap=a52Service.checkA52Vos(a52Vos);
                checkIsRight= (boolean) checkMap.get("isRight");
                if(checkIsRight) {
                    isRight=checkIsRight;
                    wrongExcelColumns.addAll((List<WrongExcelColumn>) checkMap.get("wrongExcelColumns"));
                }
            }

            //检查工资变动记录
            List<A32Vo> gzbdList = a38ExcelVo.getGzbdList();
            if(gzbdList.size()>0){
                checkMap = a32Service.checkA32Vos(gzbdList);
                checkIsRight= (boolean) checkMap.get("isRight");
                gzbdIsEmpty= (boolean) checkMap.get("gzbdIsEmpty");
                if(checkIsRight) {
                    isRight=checkIsRight;
                    wrongExcelColumns.addAll((List<WrongExcelColumn>) checkMap.get("wrongExcelColumns"));
                }
            }

            //检查材料接收记录
            List<E01z2Vo> e01z2Vos = a38ExcelVo.getE01z2Vos();
            if(e01z2Vos.size()>0){
                checkMap = e01z2Service.checkE01z2Vos(e01z2Vos);
                checkIsRight= (boolean) checkMap.get("isRight");
                cljsIsEmpty= (boolean) checkMap.get("cljsIsEmpty");
                if(checkIsRight){
                    isRight=checkIsRight;
                    wrongExcelColumns.addAll((List<WrongExcelColumn>) checkMap.get("wrongExcelColumns"));
                }
            }

            //检查目录信息及材料
            E01Z1ExcelVo e01Z1ExcelVo = a38ExcelVo.getE01Z1ExcelVo();
            if(e01Z1ExcelVo!=null){
                checkMap = e01Z1Service.checkE01Z1ExcelVo(e01Z1ExcelVo);
                checkIsRight= (boolean) checkMap.get("isRight");
                if(checkIsRight) {
                    isRight=checkIsRight;
                    wrongExcelColumns.addAll((List<WrongExcelColumn>) checkMap.get("wrongExcelColumns"));
                }
            }

        }
        returnMap.put("isRight",isRight);
        returnMap.put("gzbdIsEmpty",gzbdIsEmpty);
        returnMap.put("cljsIsEmpty",cljsIsEmpty);
        returnMap.put("wrongExcelColumns",wrongExcelColumns);

        return returnMap;
    }

    /**
     * 将a38Vo保存为a38
     * @param a38Vo
     * @param details
     * @return
     */
    public String saveA38(A38Vo a38Vo,UserLoginDetails details){
        String id;
        A38 a38 = new A38();

        String a0104Content = a38Vo.getA0104Content();
        a38Vo.setA0104(dictionaryItemService.getDictionaryItem(a0104Content,"GB/T2261.1-2003"));
        String gbztContent = a38Vo.getGbztContent();
        a38Vo.setGbztCode(dictionaryItemService.getDictionaryItem(gbztContent,"SAN_GBZT"));

        BeanUtils.copyProperties(a38Vo, a38);
        a38.setId(null);
        a38.setSjzt("0");
        EntityWrapper.wrapperSaveBaseProperties(a38,details);
        id = save(a38);
        return id;
    }

    /**
     * 保存所有数据
     * @param a38ExcelVo
     * @param details
     * @param gzbdIsEmpty
     * @param cljsIsEmpty
     * @return
     */
    @Override
    public String saveA38ExcelData(A38ExcelVo a38ExcelVo,UserLoginDetails details,boolean gzbdIsEmpty,boolean cljsIsEmpty){
        String id = "";
        if(a38ExcelVo!=null){
            //新增档案
            A38Vo jbxxA38Vo = a38ExcelVo.getJbxxA38Vo();
            id = saveA38(jbxxA38Vo,details);
            A38 a38 = getByPK(id);
            if(StringUtils.isNotEmpty(id)){
                //新增职务变动
                A38Vo a38VoForA52 = a38ExcelVo.getZwbdA38Vo();
                if(a38VoForA52!=null&&a38VoForA52.getA52Vos().size()>0){
                    List<A52Vo> a52Vos = a38VoForA52.getA52Vos();
                    a52Service.saveA52S(a52Vos,a38,details);
                }

                //添加工资变动记录
                List<A32Vo> gzbdList = a38ExcelVo.getGzbdList();
                if(gzbdList.size()>0&&!gzbdIsEmpty){
                    a32Service.saveA32S(gzbdList,a38,details);
                }

                //添加材料接收记录
                List<E01z2Vo> e01z2Vos = a38ExcelVo.getE01z2Vos();
                if(e01z2Vos.size()>0&&!cljsIsEmpty){
                    e01z2Service.saveE01z2Vos(e01z2Vos,a38,details);
                }
                //添加目录信息及材料
                E01Z1ExcelVo e01Z1ExcelVo = a38ExcelVo.getE01Z1ExcelVo();
                if(e01Z1ExcelVo!=null){
                    e01Z1Service.saveE01Z1S(e01Z1ExcelVo,a38);
                }
            }
        }
        return id;
    }
    @Override
    public String download(HttpServletResponse resp, List<A38> resultList) {
        String filePath = "";
        try{
            A38Vo vo;
            List<A38Vo> a38Vos=new ArrayList<>();
            for(A38 a38:resultList){
                vo = new A38Vo();
                org.apache.commons.beanutils.BeanUtils.copyProperties(vo,a38);
                a38Vos.add(vo);
            }
            File storePathFile = new File(Constants.DAGL_STORE_PATH);
            if(!storePathFile.exists()) storePathFile.mkdirs();
            filePath = uploadBasePath+Constants.DAGL_STORE_PATH+ UUIDUtil.getUUID()+".xlsx";
            a38ExcelExchange.toExcelByManyPojo(a38Vos, uploadBasePath+Constants.DAGLMB_STORE_PATH,filePath);
            resp.setContentType("multipart/form-data");
            resp.setHeader("Content-Disposition", "attachment;fileName="+ URLEncoderUtil.encode("档案信息表.xlsx"));
            OutputStream output = resp.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            output.flush();
            output.close();
            fileInputStream.close();
            FileUtils.deleteQuietly(new File(filePath));
        }catch (Exception e){
            e.printStackTrace();
        }
        return  filePath;
    }

    public int saveFromGzslws(DataSource dataSource)throws Exception{
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        //处理了多少条
        int order = 0;
        Connection conn = dataSource.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        int count =0;
        List<Map<String, Object>> countList = queryRunner.query(conn,
                "select count(*) as count from a38 where a38.A_STATE = '1' and a38.A_IS_DESTROY = '0' and a38.a3807b='001'  " , new MapListHandler(),(Object[]) null);
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
            String sql = "select * from (select a38.*,rownum rn from a38 where a38.A_STATE = '1' and a38.A_IS_DESTROY = '0' and a38.a3807b='001'\n" +
                    "               order by nvl(a38.A_SCAN_CODE,'-9999') desc,a38.a0101 asc,a38.pk_a38) where rn >"+num1+" and rn<"+num2+" ";

            List<Map<String, Object>> list = queryRunner.query(conn, sql, new MapListHandler(),(Object[]) null);
            for (Iterator<Map<String, Object>> li = list.iterator(); li.hasNext();) {
                Map<String, Object> m = li.next();
                StringBuffer fields = new StringBuffer();
                fields.append("insert into a38 (");
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
                            if("NAME_WORDCOUNT".equalsIgnoreCase(attKey.toString())){
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
                this.a38Dao.executeNativeBulk(fields.append(values).toString(),paramList);
                order++;
            }
        }

        DbUtils.close(conn);
        return order;
    }

    private Map<String,Object> getSavaAttMaps(){
        Map<String,Object> attMaps = new HashMap<String,Object>();
        attMaps.put("PK_A38","id");
        attMaps.put("PK_A01","a01_id");//人员主键
        attMaps.put("A0101","a0101");//姓名
        attMaps.put("A_SCAN_CODE","smxh");//扫描序号
        attMaps.put("A_ARCHIVES_CODE","dabh");//档案编号
        attMaps.put("A0104","a0104");//性别字典代码
        attMaps.put("A0104_CONTENT","a0104_content");//性别字典内容
        attMaps.put("A0107","a0107");//出生日期
        attMaps.put("A0111B","a0111A");//籍贯字典代码
//        attMaps.put("A0111A","a0111A");//籍贯内容
        attMaps.put("A_CADRE_STATE_CODE","gbzt_code");//干部状态字典代码
        attMaps.put("A_CADRE_STATE_CONTENT","gbzt_content");//干部状态字典内容
        attMaps.put("A_ARCHIVES_STATE_CODE","dazt_code");//档案状态字典代码
        attMaps.put("A_ARCHIVES_STATE_CONTENT","dazt_content");//档案状态字典内容
        attMaps.put("A0157","a0157");//单位与职务
        attMaps.put("A3801","a3801");//档案接收日期
        attMaps.put("RECEIVER","jsr");//接收人，默认当前用户
        attMaps.put("A3804B","a3804B");//档案来处id
        attMaps.put("A3804A","a3804A");//档案来处名称（转入单位名称）
        attMaps.put("A3807B","a3807B");//转出单位id，即档案管理单位id
        attMaps.put("A3807A","a3807A");//转出单位名称，即档案管理单位名称
        attMaps.put("A3814","a3814");//档案版本类别
        attMaps.put("A3834","a3834");//档案位置
        attMaps.put("A_DEATH_DATE","swrq");//死亡日期
        attMaps.put("A3824","a3824");//档案备注
        attMaps.put("A3817","a3817");//档案转出日期
        attMaps.put("A3821","a3821");//转去单位
        attMaps.put("A_OUT_REASON","zcyy");//转出原因
        attMaps.put("A_HANDLEOR_ID","jbr_id");//档案转递时的经办人id
        attMaps.put("A_HANDLEOR","jbr_xm");//档案转递时的经办人
        attMaps.put("A_OUT_REMARK","rcbz");//档案转出信息备注
        attMaps.put("A_STATE","sjzt");//数据状态。0：待审核(未审核)；1：已审核(正式数据)
        attMaps.put("A_COME_REASON","zryy");//转入原因
        attMaps.put("OUT_TO_UNIT_ID","zc_tenant_id");//转出单位id
        attMaps.put("OUT_TO_UNIT_NAME","zc_tenant_name");//转出单位名称
        attMaps.put("DATA_FROM","dasjly");//数据来源：add：从电子档案系统中新增；frompi：来源于干部系统；fromdr:导入产生
        attMaps.put("DUTY_LEVEL_TIME","xzjsj");//现职级时间
        attMaps.put("SURNAME_CODE","surname_code");//姓氏编码
        attMaps.put("NAME_WORDCOUNT","name_wordcount");//姓名字数
        attMaps.put("PERSONALNAME_CODE","personalname_code");//名编码
        attMaps.put("PERSONALNAME_NONECODE","personalname_nonecode");//无编码汉字
        attMaps.put("DUTY_LEVEL_CODE","duty_level_code");//职务级别code
        attMaps.put("DUTY_LEVEL_VALUE","duty_level_value");//职务级别
        attMaps.put("DUTY_LEVEL_TIME_BASE","duty_level_time_base");//职级时间
        return attMaps;
    }
}
