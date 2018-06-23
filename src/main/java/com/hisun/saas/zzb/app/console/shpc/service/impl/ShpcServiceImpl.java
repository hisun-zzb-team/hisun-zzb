/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.google.common.collect.Lists;
import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.gendata.service.GendataService;
import com.hisun.saas.zzb.app.console.shpc.dao.ShpcDao;
import com.hisun.saas.zzb.app.console.shpc.entity.*;
import com.hisun.saas.zzb.app.console.shpc.service.*;
import com.hisun.saas.zzb.app.console.shpc.vo.Sha01Vo;
import com.hisun.saas.zzb.app.console.shtp.entity.Shtpsj;
import com.hisun.saas.zzb.app.console.shtp.service.ShtpsjService;
import com.hisun.util.FileUtil;
import com.hisun.util.SqliteDBUtil;
import com.hisun.util.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class ShpcServiceImpl extends BaseServiceImpl<Shpc,String> implements ShpcService{

    private ShpcDao shpcDao;
    @Resource
    private Sha01Service sha01Service;
    @Resource
    private Sha01gbrmspbService sha01gbrmspbService;
    @Resource
    private Sha01gzjlService sha01gzjlService;
    @Resource
    private Sha01dascqkService sha01dascqkService;
    @Resource
    private Sha01dascqktipsService sha01dascqktipsService;
    @Resource
    private Sha01kcclService sha01kcclService;
    @Resource
    private Sha01grzdsxService sha01grzdsxService;
    @Resource
    private ShpcAttsService shpcAttsService;
    @Resource
    private ShtpsjService shtpsjService;

    @Value("${sys.upload.absolute.path}")
    private String uploadAbsolutePath;

    @Autowired
    public void setBaseDao(BaseDao<Shpc, String> shpcDao) {
        this.baseDao = shpcDao;
        this.shpcDao = (ShpcDao) shpcDao;
    }

    @Override
    public List<Sha01Vo> getShpcById(String shpcId) throws Exception{
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" shpc.id = :shpcId", "shpcId", shpcId));
        query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.asc("px"));

        //取得当前批次下的人员列表
        Long total = this.sha01Service.count(query);
        List<Sha01> sha01s = this.sha01Service.list(query,orderBy);
        List<Sha01Vo> sha01Vos = new ArrayList<Sha01Vo>();
        //查询该批次下的投票数据
        query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" shtp.shpc.id = :shpcId", "shpcId", shpcId));
        query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
        List<Shtpsj> shtpsjs = this.shtpsjService.list(query, null);

        if (sha01s != null) {// entity ==> vo
            for (Sha01 sha01 : sha01s) {
                Sha01Vo vo = new Sha01Vo();
                int tyCount = 0;//同意票数
                int btyCount = 0;//不同意票数
                int qqCount = 0;//弃权票数
                String dplCount = "0";//得票率

                if(shtpsjs!=null){
                    for(Shtpsj shtpsj : shtpsjs){
                        if(shtpsj.getSha01().getId().equals(sha01.getId())){
                            if(shtpsj.getTp()==1){
                                tyCount++;
                            }else  if(shtpsj.getTp()==2){
                                btyCount++;
                            }else  if(shtpsj.getTp()==3){
                                qqCount++;
                            }
                        }
                    }
                }
                float num= (float)tyCount/(tyCount+btyCount+qqCount)*100;
                if(Float.isNaN(num)){
                    dplCount="NaN";
                }else {
                    DecimalFormat df = new DecimalFormat("0.00");//格式化小数
                    dplCount = df.format(num);//返回的是String类型
                }
                vo.setXm(sha01.getXm());
                vo.setTyCount(tyCount);
                vo.setBtyCount(btyCount);
                vo.setQqCount(qqCount);
                vo.setDplCount(dplCount);

                sha01Vos.add(vo);
            }
        }
        return sha01Vos;
    }

    public void exportExcel(String title, List<Sha01Vo> Sha01Vos, OutputStream out) {
        Workbook workbook=new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(title);
        // 声明一个画图的顶级管理器
        Drawing patriarch = sheet.createDrawingPatriarch();
        // 产生表格标题行
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        CellStyle titleCellStyle = workbook.createCellStyle(); // 标题样式对象
        Font font = workbook.createFont();//设置字体
        font.setFontHeightInPoints((short) 13);//设置字体大小
        font.setBold(true);//加粗
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
        titleCellStyle.setFont(font);
        titleCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cell.setCellValue(title);
        cell.setCellStyle(titleCellStyle);



        CellStyle cellStyle1 = workbook.createCellStyle(); // title样式对象
        font = workbook.createFont();//设置字体
        font.setFontHeightInPoints((short) 10);//设置字体大小
        font.setBold(true);//加粗
        cellStyle1.setFont(font);
        cellStyle1.setAlignment(CellStyle.ALIGN_CENTER);


        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 3));
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 4, 4));
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("姓名");
        cell.setCellStyle(cellStyle1);

        cell = row.createCell(1);
        cell.setCellValue("票决结果");
        cell.setCellStyle(cellStyle1);

        cell = row.createCell(4);
        cell.setCellValue("得票率");
        cell.setCellStyle(cellStyle1);


        sheet.setColumnWidth(0,3000);
        sheet.setColumnWidth(1,3000);
        sheet.setColumnWidth(2,3000);
        sheet.setColumnWidth(3,3000);
        sheet.setColumnWidth(4,3000);

        row = sheet.createRow(2);
        List<String> headers= Lists.newArrayList();
        headers.add("同意(票数）");
        headers.add("不同意(票数）");
        headers.add("弃权(票数）");
        String name = "";
        for (short i = 0; i < headers.size(); i++) {

            name=headers.get(i);
            cell = row.createCell(i+1);
            cell.setCellStyle(cellStyle1);
            cell.setCellValue(name);

        }
        CellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setAlignment(CellStyle.ALIGN_CENTER);

        if(Sha01Vos!=null){
            int index = 2;
            for(Sha01Vo Sha01Vo : Sha01Vos) {
                index++;
                row = sheet.createRow(index);
                cell = row.createCell(0);
                cell.setCellValue(Sha01Vo.getXm());

                cell = row.createCell(1);
                cell.setCellValue(Sha01Vo.getTyCount());
				cell.setCellStyle(cellStyle2);

                cell = row.createCell(2);
                cell.setCellValue(Sha01Vo.getBtyCount());
				cell.setCellStyle(cellStyle2);

                cell = row.createCell(3);
                cell.setCellValue(Sha01Vo.getQqCount());
				cell.setCellStyle(cellStyle2);

                cell = row.createCell(4);
                cell.setCellValue(Sha01Vo.getDplCount()+"%");
				cell.setCellStyle(cellStyle2);

            }
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   public Integer getMaxPx(){
       UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
       Map<String, Object> arg=new HashMap<String, Object>();
       String hql = "select max(t.PC_PX) as px from APP_SH_PC t where t.tombstone=(:tombstone) and t.tenant_id=(:tenant_id) order by  t.PC_PX asc";
       arg.put("tombstone", "0");
       arg.put("tenant_id", userLoginDetails.getTenantId());
       List<Map> maxSorts = this.shpcDao.nativeList(hql, arg);
       Integer maxPx = (Integer) maxSorts.get(0).get("px");
       return maxPx;
    }

    /**
     * 排序处理（首先跟最大的排序号比较，如果大于最大排序号则不处理；
     *        如果小于，就一个个比较，当比较到大于它的就把后面大于它的全部+1；）
     * @param
     */
    public void updatePx(int oldPx,int newPx){
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        String sql = "UPDATE APP_SH_PC t SET ";
        if(newPx > oldPx) {
            sql = sql + "t.PC_PX=t.PC_PX-1";
        } else {
            sql = sql + "t.PC_PX=t.PC_PX+1";
        }

        sql = sql + " where t.tombstone=(:tombstone) and t.tenant_id=(:tenant_id)";
        if(newPx > oldPx) {
            sql = sql + " and t.PC_PX<=" + newPx + " and t.PC_PX >" + oldPx;
        } else if(newPx == oldPx) {
            sql = sql + " and 1<>1";
        } else {
            sql = sql + " and t.PC_PX<" + oldPx + " and t.PC_PX>=" + newPx;
        }
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("tombstone", "0");
        paramMap.put("tenant_id", userLoginDetails.getTenantId());
        this.shpcDao.update(sql, paramMap);
    }


    public String toSqliteInsertSql(Shpc entity){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" APP_SH_PC ");
        sb.append("(");
        sb.append("ID");
        sb.append(",PC_MC");
        sb.append(",SHLX");
        sb.append(",PC_SJ");
        sb.append(",SJLX");
        sb.append(",PATH");
        sb.append(",PC_PX");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.trimNull2Empty(entity.getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getPcmc())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getShlx())+"'");
        if(entity.getPcsj()!=null){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            sb.append(",'"+ df.format(entity.getPcsj())+"'");
        }else{
            sb.append(",''");
        }
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getSjlx())+"'");
        if (StringUtils.isEmpty(entity.getFilePath())){
            sb.append(",''");
        }else{
            String attsPath =GendataService.APP_ATTS_PATH+ShpcService.APP_ATTS_PATH
                    +FileUtil.getFileName(entity.getFilePath());
            sb.append(",'"+attsPath+"'");

        }
        sb.append(",'"+entity.getPx()+"'");
        sb.append(")");
        return sb.toString();
    }


    public void saveAsSqlite(String shpcId,String sqlite,String imgdir,String attsdir) throws Exception{
        Shpc shpc = this.shpcDao.getPK(shpcId);
        if (shpc != null) {
            SqliteDBUtil sqliteDBUtil = SqliteDBUtil.newInstance();
            sqliteDBUtil.insert(sqlite, this.toSqliteInsertSql(shpc));
            if (StringUtils.isEmpty(shpc.getFilePath()) == false) {
                FileUtil.copyFile(uploadAbsolutePath+shpc.getFilePath(),
                        attsdir+ShpcService.ATTS_PATH);
            }
            //上会批次附件
            List<ShpcAtts> shpcAttses = shpc.getShpcAttses();
            if (shpcAttses != null) {
                for (ShpcAtts shpcAtts : shpcAttses) {
                    //初始化结构数据
                    sqliteDBUtil.insert(sqlite,this.shpcAttsService.toSqliteInsertSql(shpcAtts));
                    if (StringUtils.isEmpty(shpcAtts.getFilepath()) == false) {
                        FileUtil.copyFile(uploadAbsolutePath+shpcAtts.getFilepath(),
                                attsdir+ShpcAttsService.ATTS_PATH);
                    }
                }
            }
            //干部
            List<Sha01> sha01s = shpc.getSha01s();
            if (sha01s != null) {
                for (Sha01 sha01 : sha01s) {
                    //初始化结构数据
                    sqliteDBUtil.insert(sqlite, this.sha01Service.toSqliteInsertSql(sha01));
                    //初始化非机构化数据
                    if (sha01.getZppath() != null) {
                        FileUtil.copyFile(uploadAbsolutePath+sha01.getZppath(),
                                imgdir+Sha01Service.IMG_PATH);
                    }
                    //工作经历
                    List<Sha01gzjl> gzjls = sha01.getGzjls();
                    if (gzjls != null) {
                        for (Sha01gzjl sha01gzjl : gzjls) {
                            sqliteDBUtil.insert(sqlite,this.sha01gzjlService.toSqliteInsertSql(sha01gzjl));
                        }
                    }
                    //干部任免审批表
                    List<Sha01gbrmspb> sha01gbrmspbs = sha01.getGbrmspbs();
                    if (sha01gbrmspbs != null) {
                        for (Sha01gbrmspb gbrmspb : sha01gbrmspbs) {
                            sqliteDBUtil.insert(sqlite, this.sha01gbrmspbService.toSqliteInsertSql(gbrmspb));
                            if (gbrmspb.getFile2imgPath() != null) {
                                FileUtil.copyFile(uploadAbsolutePath+gbrmspb.getFile2imgPath(),
                                        attsdir+Sha01gbrmspbService.ATTS_PATH);
                            }
                        }
                    }
                    //考察材料
                    List<Sha01kccl> sha01kccls = sha01.getKccls();
                    if (sha01kccls != null) {
                        for (Sha01kccl kccl : sha01kccls) {
                            sqliteDBUtil.insert(sqlite, this.sha01kcclService.toSqliteInsertSql(kccl));
                            if (kccl.getFile2imgPath() != null) {
                                FileUtil.copyFile(uploadAbsolutePath+kccl.getFile2imgPath(),
                                        attsdir+Sha01kcclService.ATTS_PATH);
                            }
                        }
                    }
                    //档案任前审核表
                    List<Sha01dascqk> sha01dascqks = sha01.getDascqks();
                    if (sha01dascqks != null) {
                        for (Sha01dascqk sha01dascqk : sha01dascqks) {
                            sqliteDBUtil.insert(sqlite, this.sha01dascqkService.toSqliteInsertSql(sha01dascqk));
                            if (sha01dascqk.getFile2imgPath() != null) {
                                FileUtil.copyFile(uploadAbsolutePath+sha01dascqk.getFile2imgPath(),
                                        attsdir+Sha01dascqkService.ATTS_PATH);
                            }
                            //档案审查表提示表
                            List<Sha01dascqktips> sha01dascqktipses = sha01dascqk.getSha01dascqktips();
                            if (sha01dascqktipses != null) {
                                for (Sha01dascqktips tip : sha01dascqktipses) {
                                    sqliteDBUtil.insert(sqlite,this.sha01dascqktipsService.toSqliteInsertSql(tip));
                                }
                            }
                        }
                    }
                    //个人重大事项
                    List<Sha01grzdsx> sha01grzdsxes = sha01.getGrzdsxes();
                    if (sha01grzdsxes != null) {
                        for (Sha01grzdsx sha01grzdsx : sha01grzdsxes) {
                            sqliteDBUtil.insert(sqlite, this.sha01grzdsxService.toSqliteInsertSql(sha01grzdsx));
                            if (sha01grzdsx.getFile2imgPath() != null) {
                                FileUtil.copyFile(uploadAbsolutePath+sha01grzdsx.getFile2imgPath(),
                                        attsdir+Sha01grzdsxService.ATTS_PATH);
                            }
                        }
                    }
                }
            }
        }
    }

}
