/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.shpc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.gendata.service.GendataService;
import com.hisun.saas.zzb.app.console.shpc.dao.Sha01gbrmspbDao;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01gbrmspb;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01gzjl;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01shgx;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01Service;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01gbrmspbService;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01gzjlService;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01shgxService;
import com.hisun.saas.zzb.app.console.shpc.vo.Sha01gbrmspbVo;
import com.hisun.saas.zzb.app.console.shpc.vo.Sha01shgxVo;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.sys.util.GzjlUtil;
import com.hisun.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class Sha01gbrmspbServiceImpl extends BaseServiceImpl<Sha01gbrmspb, String> implements Sha01gbrmspbService {

    private Sha01gbrmspbDao sha01gbrmspbDao;
    @Resource
    private Sha01gzjlService sha01gzjlService;
    @Resource
    private Sha01Service sha01Service;
    @Resource
    private Sha01shgxService sha01shgxService;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    @Autowired
    public void setBaseDao(BaseDao<Sha01gbrmspb, String> sha01gbrmspbDao) {
        this.baseDao = sha01gbrmspbDao;
        this.sha01gbrmspbDao = (Sha01gbrmspbDao) sha01gbrmspbDao;
    }


    public String saveFromWord(String sha01Id, String wordsourcePath, String templatePath) throws Exception {
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String, String> dataMap = WordUtil.newInstance().convertMapByTemplate(wordsourcePath, templatePath);
        //this.dealAtts(gbrmspb,wordsourcePath);
        // this.dealPhoto(gbrmspb,wordsourcePath);
        //先删除原有的干部任免审批表
        Sha01 sha01 = this.sha01Service.getByPK(sha01Id);
        if (sha01.getGbrmspbs() != null && sha01.getGbrmspbs().size() > 0) {
            List<Sha01gbrmspb> tempSha01gbrmspbs = new ArrayList<Sha01gbrmspb>();
            for (Sha01gbrmspb gb : sha01.getGbrmspbs()) {
                tempSha01gbrmspbs.add(gb);
            }
            for (Sha01gbrmspb gb : tempSha01gbrmspbs) {
                sha01.getGbrmspbs().remove(gb);
                gb.setSha01(null);
                this.sha01gbrmspbDao.delete(gb);
            }
            //由于工作经历也是导入的,所以一并删除
            List<Sha01gzjl> tempSha01gzjls = new ArrayList<Sha01gzjl>();
            for (Sha01gzjl gzjl : sha01.getGzjls()) {
                tempSha01gzjls.add(gzjl);
            }
            for (Sha01gzjl gzjl : tempSha01gzjls) {
                sha01.getGzjls().remove(gzjl);
                gzjl.setSha01(null);
                this.sha01gzjlService.delete(gzjl);
            }
            //青春掉社会关系
            List<Sha01shgx> tempSha01shgxes = new ArrayList<>();
            for(Sha01shgx sha01shgx: sha01.getShgxes()){
                tempSha01shgxes.add(sha01shgx);
            }
            for(Sha01shgx sha01shgx : tempSha01shgxes){
                sha01.getShgxes().remove(sha01shgx);
                sha01shgx.setSha01(null);
                this.sha01shgxService.delete(sha01shgx);
            }
        }

        //获取Sha01gbrmspbVo
        Sha01gbrmspb sha01gbrmspb = new Sha01gbrmspb();
        Sha01gbrmspbVo sha01gbrmspbVo = (Sha01gbrmspbVo) ReflectionVoUtil.vo(Sha01gbrmspbVo.class, this.getGbrmspbVoDataMap(dataMap));
        BeanUtils.copyProperties(sha01gbrmspb, sha01gbrmspbVo);
        sha01gbrmspb.setFilepath(wordsourcePath.substring(uploadAbsolutePath.length()));
        EntityWrapper.wrapperSaveBaseProperties(sha01gbrmspb, userLoginDetails);
        sha01.addGbrmspb(sha01gbrmspb);
        int j = 1;
        for (Map<String, Object> sha01shgxVoMap : this.getShgxVoDataMap(dataMap)) {
            Sha01shgx sha01shgx = new Sha01shgx();
            Sha01shgxVo sha01shgxVo = (Sha01shgxVo) ReflectionVoUtil.vo(Sha01shgxVo.class, sha01shgxVoMap);
            BeanUtils.copyProperties(sha01shgx, sha01shgxVo);
            EntityWrapper.wrapperSaveBaseProperties(sha01shgx, userLoginDetails);
            sha01shgx.setPx(j);
            sha01.addShgx(sha01shgx);
            j++;
        }
        StringBuffer newGzjlStr = new StringBuffer();
        if(sha01gbrmspb.getGzjlStr()!=null){
            List<String> list = GzjlUtil.matchGzjlStr(sha01gbrmspb.getGzjlStr());
            int k = 1;
            for (String str : list) {
                Sha01gzjl gzjl = new Sha01gzjl();
                gzjl.setSha01(sha01);
                gzjl.setTenant(sha01.getTenant());
                gzjl.setJlsm(str);
                gzjl.setPx(k);
                k++;
                EntityWrapper.wrapperSaveBaseProperties(gzjl, userLoginDetails);
                sha01.addGzjl(gzjl);
               // newGzjlStr.append(str).append("\n");
            }
        }
       // sha01gbrmspb.setGzjlStr(newGzjlStr.toString());
        this.dealPhoto(sha01gbrmspb, wordsourcePath);
        //this.dealAtts(sha01gbrmspb,wordsourcePath);
        this.sha01Service.update(sha01);
        this.sha01Service.saveAsGbrmspb(sha01);

//        String id = this.sha01gbrmspbDao.saveFromWord(gbrmspb,dataMap);
//        gbrmspb = this.sha01gbrmspbDao.getByPK(id);
//        this.sha01gzjlService.saveGzjls(gbrmspb.getSha01(),gbrmspb.getGzjlStr());
        return sha01gbrmspb.getId();
    }


    private Map<String, Object> getGbrmspbVoDataMap(Map<String, String> dataMap) {
        Map<String, Object> resultMap = new HashMap<>();
        for (Iterator<String> it = dataMap.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            String value = dataMap.get(key);
            if (key.contains("Sha01gbrmspbVo")) {
                resultMap.put(WordUtil.getSqlField(key), value);
            }
        }
        return resultMap;
    }

    private List<Map<String, Object>> getShgxVoDataMap(Map<String, String> dataMap) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, String> sha01shgxVoMap = new HashMap<>();
        for (Iterator<String> it = dataMap.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            String value = dataMap.get(key);
            if (key.contains("Sha01shgxVo")) {
                sha01shgxVoMap.put(key, value);
            }
        }
        for (int i = 0; i < 9; i++) {
            Map<String, Object> rowMap = new HashMap<>();
            //找出每一行的Map
            for (Iterator<String> it = sha01shgxVoMap.keySet().iterator(); it.hasNext(); ) {
                String key = it.next();
                String value = dataMap.get(key);
                if (i == getRowIndex(key)) {
                    rowMap.put(WordUtil.getSqlField(key), value);
                }
            }
            if (rowMap.size() > 0) {
                resultList.add(rowMap);
            }
            if (rowMap.size() == 0) {
                break;
            }
        }

        return resultList;
    }

    private int getRowIndex(String key) {
        return Integer.valueOf(key.substring(key.lastIndexOf(WordUtil.dot) + 1)).intValue();
    }


    private void dealPhoto(Sha01gbrmspb gbrmspb, String wordsourcePath) throws Exception {
        //临时方式处理照片(取得当前文档中所有照片)
        List<byte[]> imgs = WordUtil.newInstance().extractImages(wordsourcePath);
        if (imgs != null && imgs.size() > 0) {
            File file = new File(uploadAbsolutePath + Sha01Service.IMG_PATH);
            if (file.exists() == false) {
                file.mkdirs();
            }
            String photoPath = Sha01Service.IMG_PATH + UUIDUtil.getUUID() + ".jpg";
            String photoRealPath = uploadAbsolutePath + photoPath;
            FileOutputStream photofos = new FileOutputStream(new File(photoRealPath));
            photofos.write(imgs.get(0));
            photofos.flush();
            photofos.close();
            gbrmspb.setZppath(photoPath);
            Sha01 sha01 = gbrmspb.getSha01();
            sha01.setZppath(photoPath);
            //this.sha01Service.update(sha01);
        }
    }

    private void dealAtts(Sha01gbrmspb gbrmspb, String wordsourcePath) throws Exception {
        String pdfPath = Sha01gbrmspbService.ATTS_PATH + UUIDUtil.getUUID() + ".pdf";
        String pdfRealPath = uploadAbsolutePath + pdfPath;
        //客户上传的word中某些字体服务器中可能不存在,有可能导致生成的PDF变形
        WordConvertUtil.newInstance().convert(wordsourcePath, pdfRealPath, WordConvertUtil.PDF);
        gbrmspb.setFile2imgPath(pdfPath);
    }


    public String toSqliteInsertSql(Sha01gbrmspb entity) {
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" APP_SH_A01_GBRMSPB ");
        sb.append("(");
        sb.append("ID");
        sb.append(",APP_SH_A01_ID");
        sb.append(",XM");
        sb.append(",XB");
        sb.append(",CSNY");
        sb.append(",NL");
        sb.append(",MZ");
        sb.append(",JG");
        sb.append(",CSD");
        sb.append(",RDSJ");
        sb.append(",CJGZSJ");
        sb.append(",JKZK");
        sb.append(",ZYJSZW");
        sb.append(",ZYTC");
        sb.append(",XL_QRZ");
        sb.append(",XW_QRZ");
        sb.append(",XL_ZZ");
        sb.append(",XW_ZZ");
        sb.append(",QRZ_BYYX");
        sb.append(",ZZ_BYYX");
        sb.append(",XRZW");
        sb.append(",NRZW");
        sb.append(",NMZW");
        sb.append(",RMLY");
        sb.append(",CBDWYJ");
        sb.append(",SPJGYJ");
        sb.append(",XZJGRMYJ");
        sb.append(",PATH");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'" + StringUtils.trimNull2Empty(entity.getId()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getSha01().getId()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getXm()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getXb()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getCsny()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getNl()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getMz()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getJg()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getCsd()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getRdsj()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getCjgzsj()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getJkzk()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getZyjszw()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getZytc()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getXlqrz()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getXwqrz()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getXlzz()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getXwzz()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getQrz_byyx()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getZz_byyx()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getXrzw()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getNrzw()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getNmzw()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getRmly()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getCbdwyj()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getSpjgyj()) + "'");
        sb.append(",'" + StringUtils.trimNull2Empty(entity.getXzjgrmyj()) + "'");
        if (StringUtils.isEmpty(entity.getFile2imgPath())) {
            sb.append(",''");
        } else {
            String attsPath = GendataService.APP_ATTS_PATH + Sha01gbrmspbService.APP_ATTS_PATH
                    + FileUtil.getFileName(entity.getFile2imgPath());
            sb.append(",'" + attsPath + "'");

        }
        sb.append(")");
        return sb.toString();
    }

}