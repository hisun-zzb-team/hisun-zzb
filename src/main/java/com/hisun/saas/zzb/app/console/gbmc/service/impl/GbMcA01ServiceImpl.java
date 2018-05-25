/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbmc.service.impl;

import com.aspose.words.*;
import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcA01Dao;
import com.hisun.saas.zzb.app.console.gbmc.entity.*;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcA01Service;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcA01gbrmspbService;
import com.hisun.saas.zzb.app.console.gbmc.vo.GbMcA01gbrmspbVo;
import com.hisun.saas.zzb.app.console.gendata.service.GendataService;
import com.hisun.saas.sys.util.GzjlUtil;
import com.hisun.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class GbMcA01ServiceImpl extends BaseServiceImpl<GbMcA01, String> implements GbMcA01Service {

    private GbMcA01Dao gbMcA01Dao;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    @Autowired
    public void setBaseDao(BaseDao<GbMcA01, String> gbMcA01Dao) {
        this.baseDao = gbMcA01Dao;
        this.gbMcA01Dao = (GbMcA01Dao) gbMcA01Dao;
    }


    public void saveFromWordDataMap(Map<String, String> dataMap, GbMcB01 gbMcB01) {
        this.gbMcA01Dao.saveFromWordDataMap(dataMap, gbMcB01);
    }


    public void updateA01FromYwJson(String gbmcId, String ywJsonPath, String photoPath) throws Exception {
        //JSON及照片临时目录
        File jsonFiles = new File(ywJsonPath);
        File photos = new File(photoPath);
        if (jsonFiles != null) {
            for (File jsonFile : jsonFiles.listFiles()) {
                if (jsonFile.isDirectory()
                        || jsonFile.getName().toLowerCase().endsWith("json") == false) {
                    continue;
                }
                String json = FileUtils.readFileToString(jsonFile);
                JacksonUtil util = new JacksonUtil();
                List<Map<String, String>> gbrmsbpDataList = util.fromJson(json, List.class);
                for (Map<String, String> dataMap : gbrmsbpDataList) {
                    String xm = StringUtils.trimBlankCharacter2Empty(dataMap.get("name"));
                    if (StringUtils.isEmpty(xm)==false) {
                        //在A01表中找到对应的记录
                        CommonConditionQuery query = new CommonConditionQuery();
                        query.add(CommonRestrictions.and(" xm like :xmQuery", "xmQuery", "%" + xm + "%"));
                        query.add(CommonRestrictions.and(" gbMc.id = :gbmcId", "gbmcId", gbmcId));
                        query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
                        List<GbMcA01> a01s = this.list(query, null);
                        if (a01s != null) {
                            for (GbMcA01 a01 : a01s) {
                                if (a01.getGbMca01gbrmspbs() == null ||a01.getGbMca01gbrmspbs().size()==0) {
                                    //处理干部任免审批表数据
                                    GbMcA01gbrmspb gbMcA01gbrmspb = new GbMcA01gbrmspb(dataMap);
                                    a01.addGbrmspb(gbMcA01gbrmspb);

                                    //处理照片
                                    String photoFileName = dataMap.get("photos");
                                    if (photoFileName != null && StringUtils.isEmpty(photoFileName) == false) {
                                        String photoStorePath = GbMcA01Service.IMG_PATH + UUIDUtil.getUUID() + ".jpg";
                                        String photoRealStorePath = uploadAbsolutePath + photoStorePath;
                                        if (photos != null) {
                                            for (File photo : photos.listFiles()) {
                                                if (photo.isDirectory()
                                                        || photo.getName().toLowerCase().endsWith("jpg") == false) {
                                                    continue;
                                                }
                                                if (photo.getName().equals(photoFileName)) {
                                                    FileUtils.copyFile(photo, new File(photoRealStorePath));
                                                    a01.setZppath(photoStorePath);
                                                    gbMcA01gbrmspb.setZppath(photoStorePath);
                                                    break;
                                                }
                                            }

                                        }

                                    }
                                }
                                //处理工作经历数据
                                if (a01.getGbMca01gzjls() == null||a01.getGbMca01gzjls().size()==0) {
                                    Object obj = dataMap.get("intro");
                                    if (obj instanceof String) {
                                        List<String> gzjlList = GzjlUtil.matchGzjlStr((String) obj);
                                        int i = 1;
                                        for (String gzjl : gzjlList) {
                                            GbMcA01gzjl gbMcA01gzjl = new GbMcA01gzjl(gzjl, i);
                                            a01.addGzjl(gbMcA01gzjl);
                                            i++;
                                        }
                                    }
                                }
                                //处理社会关系数据,最多10条数据
                                if (a01.getGbMcA01shgxes() == null || a01.getGbMcA01shgxes().size()==0) {
                                    for (int i = 0; i < 10; i++) {
                                        GbMcA01shgx gbMcA01shgx = new GbMcA01shgx(dataMap, i);
                                        if (gbMcA01shgx.getXm() != null) {
                                            a01.addShgx(gbMcA01shgx);
                                        }
                                    }
                                }

                                //生成Word并转换成pdf存入gbrmspb
                                this.buildWordAndUpateGbrmspb(a01);
                                this.update(a01);
                            }
                        }
                    }
                }
            }
        }
        FileUtils.deleteDirectory(jsonFiles);
        FileUtils.deleteDirectory(photos);

    }

    private void buildWordAndUpateGbrmspb(GbMcA01 gbMcA01) throws Exception {
        if (gbMcA01.getGbMca01gbrmspbs().size() > 0) {
            WordUtil wordUtil = WordUtil.newInstance();
            Document gbrmspbTemplateDoc = wordUtil.read(uploadAbsolutePath + GbMcA01gbrmspbService.GBRMSPB_DOC_TEMPLATE);
            DocumentBuilder builder = new DocumentBuilder(gbrmspbTemplateDoc);
            GbMcA01gbrmspb gbMcA01gbrmspb = gbMcA01.getGbMca01gbrmspbs().get(0);
            GbMcA01gbrmspbVo gbMcA01gbrmspbVo = new GbMcA01gbrmspbVo();
            BeanUtils.copyProperties(gbMcA01gbrmspbVo,gbMcA01gbrmspb);
            Map<String,Object> gbrmspbFieldMap = ReflectionVoUtil.map(gbMcA01gbrmspbVo);

            NodeCollection tables = gbrmspbTemplateDoc.getChildNodes(NodeType.TABLE, true);
            int tableIndex = 0;
            for (Iterator<Table> iterator = tables.iterator(); iterator.hasNext(); ) {
                Table table = iterator.next();
                NodeCollection rows = table.getChildNodes(NodeType.ROW, true);
                int rowIndex = 0;
                for (Iterator<Row> rowIterator = rows.iterator(); rowIterator.hasNext(); ) {
                    Row row = rowIterator.next();
                    NodeCollection cells = row.getChildNodes(NodeType.CELL, true);
                    int colIndex = 0;
                    for (Iterator<Cell> cellIterator = cells.iterator(); cellIterator.hasNext(); ) {
                        Cell cell = cellIterator.next();
                        String trimText = wordUtil.trim(cell.getText());
                        builder.moveToCell(tableIndex, rowIndex, colIndex, 0);
                        if (trimText.startsWith(WordUtil.dataPrefix)) {
                            String field = wordUtil.getSqlField(trimText);
                            String value = gbrmspbFieldMap.get(field)==null?"":gbrmspbFieldMap.get(field).toString();
                            builder.write(value);
                            gbrmspbTemplateDoc.getRange().replace(trimText, "",
                                    new FindReplaceOptions(FindReplaceDirection.FORWARD));
                        } else if (trimText.startsWith(WordUtil.imageSign)) {
                            if (StringUtils.isEmpty(gbMcA01gbrmspb.getZppath()) == false) {
                                builder.insertImage(uploadAbsolutePath + gbMcA01gbrmspb.getZppath(), 94, 122);
                            }
                            gbrmspbTemplateDoc.getRange().replace(trimText, "",
                                    new FindReplaceOptions(FindReplaceDirection.FORWARD));
                        } else if (trimText.startsWith(WordUtil.rangeSign)) {
                            if (gbMcA01.getGbMcA01shgxes() != null && gbMcA01.getGbMcA01shgxes().size() > 0) {
                                String field = wordUtil.getSqlField(trimText);
                                for (int i = 0; i < gbMcA01.getGbMcA01shgxes().size(); i++) {
                                    if (i > 9) {
                                        break;
                                    }//最多取10条数据
                                    builder.moveToCell(tableIndex, rowIndex + i, colIndex, 0);
                                    Map<String, Object> shgxFieldMap = ReflectionVoUtil.map(gbMcA01.getGbMcA01shgxes().get(i));
                                    String value = shgxFieldMap.get(field)==null?"":shgxFieldMap.get(field).toString();
                                    builder.write(value);
                                }
                            }
                            gbrmspbTemplateDoc.getRange().replace(trimText, "",
                                    new FindReplaceOptions(FindReplaceDirection.FORWARD));
                        }
                        colIndex++;
                    }
                    rowIndex++;
                }
                tableIndex++;
            }
            String saveWordPath = GbMcA01gbrmspbService.ATTS_PATH + UUIDUtil.getUUID() + ".docx";
            gbrmspbTemplateDoc.save(uploadAbsolutePath + saveWordPath);
            gbMcA01gbrmspb.setFilepath(saveWordPath);

            String pdfPath = GbMcA01gbrmspbService.ATTS_PATH + UUIDUtil.getUUID() + ".pdf";
            String pdfRealPath = uploadAbsolutePath + pdfPath;
            WordConvertUtil.newInstance().convert(uploadAbsolutePath + saveWordPath, pdfRealPath, WordConvertUtil.PDF);
            gbMcA01gbrmspb.setFile2imgPath(pdfPath);
        }
    }



    public String toSqliteInsertSql(GbMcA01 entity) {
        StringBuffer sb = new StringBuffer("");
        sb.append(" insert into ");
        sb.append(" app_mc_a01 ");
        sb.append("(");
        sb.append("id");
        sb.append(",b01_id");
        sb.append(",xm");
        sb.append(",mz");
        sb.append(",zw");
        sb.append(",csd");
        sb.append(",jg");
        sb.append(",csny");
        sb.append(",cjgzsj");
        sb.append(",rdsj");
        sb.append(",qrzxlxwjzy");
        sb.append(",zzxlxwjzy");
        sb.append(",zyjszw");
        sb.append(",xrzwsj");
        sb.append(",xrzjsj");
        sb.append(",zp_path");
        sb.append(",a01_px");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'" + StringUtils.trimNull2Empty(entity.getId()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getGbMcB01().getId()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getXm()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getMz()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getZw()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getCsd()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getJg()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getCsny()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getCjgzsj()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getRdsj()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getQrzxlxwjzy()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getZzxlxwjzy()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getZyjszw()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getXrzwsj()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getXrzjsj()) + "'");
        if (StringUtils.isEmpty(entity.getZppath())) {
            sb.append(",''");
        } else {
            String filepath = GendataService.APP_IMG_PATH+GbMcA01Service.APP_IMG_PATH
                    + FileUtil.getFileName(entity.getZppath());
            sb.append(",'" + filepath + "'");

        }
        sb.append("," + entity.getPx() + "");
        sb.append(")");
        return sb.toString();
    }

}
