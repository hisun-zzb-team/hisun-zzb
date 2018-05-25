package com.hisun.saas.zzb.app.console.gbmc.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.zzb.app.console.gbmc.dao.GbMcA01gbrmspbDao;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01gbrmspb;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01gzjl;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcA01Service;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcA01gbrmspbService;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcA01gzjlService;
import com.hisun.saas.zzb.app.console.gendata.service.GendataService;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01Service;
import com.hisun.util.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/15.
 */
@Service
public class GbMcA01gbrmspbServiceImpl extends BaseServiceImpl<GbMcA01gbrmspb, String> implements GbMcA01gbrmspbService {

    private GbMcA01gbrmspbDao gbMcA01gbrmspbDao;
    @Resource
    private GbMcA01gzjlService gbMcA01gzjlService;
    @Resource
    private GbMcA01Service gbMcA01Service;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    @Autowired
    public void setBaseDao(BaseDao<GbMcA01gbrmspb, String> gbMcA01gbrmspbDao) {
        this.baseDao = gbMcA01gbrmspbDao;
        this.gbMcA01gbrmspbDao = (GbMcA01gbrmspbDao) gbMcA01gbrmspbDao;
    }


    public String saveFromWord(GbMcA01gbrmspb gbrmspb, String wordsourcePath, String templatePath) throws Exception {
        Map<String, String> dataMap = WordUtil.newInstance().convertMapByTemplate(wordsourcePath, templatePath);
        this.dealAtts(gbrmspb,wordsourcePath);
        this.dealPhoto(gbrmspb,wordsourcePath);
        //先删除原有的干部任免审批表
        GbMcA01 gbMcA01 = gbrmspb.getGbMcA01();
        if(gbMcA01.getGbMca01gbrmspbs()!=null && gbMcA01.getGbMca01gbrmspbs().size()>0){
            List<GbMcA01gbrmspb> tempGbMca01gbrmspbs = new ArrayList<GbMcA01gbrmspb>();
            for(GbMcA01gbrmspb gb :gbMcA01.getGbMca01gbrmspbs()){
                tempGbMca01gbrmspbs.add(gb);
            }
            for(GbMcA01gbrmspb gb :tempGbMca01gbrmspbs){
                gbMcA01.getGbMca01gbrmspbs().remove(gb);
                gb.setGbMcA01(null);
                this.gbMcA01gbrmspbDao.delete(gb);
            }
            //由于工作经历也是导入的,所以一并删除
            List<GbMcA01gzjl> tempGbMca01gzjls = new ArrayList<GbMcA01gzjl>();
            for(GbMcA01gzjl gzjl :gbMcA01.getGbMca01gzjls()){
                tempGbMca01gzjls.add(gzjl);
            }
            for(GbMcA01gzjl gzjl :tempGbMca01gzjls){
                gbMcA01.getGbMca01gzjls().remove(gzjl);
                gzjl.setGbMcA01(null);
                this.gbMcA01gzjlService.delete(gzjl);
            }
        }
        String id = this.gbMcA01gbrmspbDao.saveFromWord(gbrmspb,dataMap);
        this.gbMcA01gzjlService.saveFromWord(gbrmspb.getGbMcA01(),dataMap);
        return id;
    }

    private void dealPhoto(GbMcA01gbrmspb gbrmspb, String wordsourcePath) throws Exception{
        //临时方式处理照片(取得当前文档中所有照片)
        List<byte[]> imgs = WordUtil.newInstance().extractImages(wordsourcePath);
        if(imgs!=null&&imgs.size()>0){
            File file = new File(uploadAbsolutePath+GbMcA01Service.ATTS_PATH);
            if(file.exists()==false){
                file.mkdirs();
            }
            String photoPath = GbMcA01Service.ATTS_PATH+ UUIDUtil.getUUID()+".jpg";
            String photoRealPath = uploadAbsolutePath+photoPath;
            FileOutputStream photofos = new FileOutputStream(new File(photoRealPath));
            photofos.write(imgs.get(0));
            photofos.flush();
            photofos.close();
            GbMcA01 gbMcA01 = gbrmspb.getGbMcA01();
            gbMcA01.setZppath(photoPath);
            this.gbMcA01Service.update(gbMcA01);
        }
    }

    private void dealAtts(GbMcA01gbrmspb gbrmspb, String wordsourcePath)throws Exception{
        //处理附件
        String pdfPath = GbMcA01gbrmspbService.ATTS_PATH+UUIDUtil.getUUID()+".pdf";
        String pdfRealPath = uploadAbsolutePath+pdfPath;
        WordConvertUtil.newInstance().convert(wordsourcePath,pdfRealPath,WordConvertUtil.PDF);
        gbrmspb.setFile2imgPath(pdfPath);
    }

    public String toSqliteInsertSql(GbMcA01gbrmspb entity){
        StringBuffer sb = new StringBuffer("");
        sb.append("INSERT INTO ");
        sb.append("APP_MC_A01_GBRMSPB ");
        sb.append("(");
        sb.append("ID");
        sb.append(",APP_MC_A01_ID");
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
        sb.append("'"+ StringUtils.trimNull2Empty(entity.getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getGbMcA01().getId())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getXm())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getXb())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getCsny())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getNl())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getMz())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getJg())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getCsd())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getRdsj())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getCjgzsj())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getJkzk())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getZyjszw())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getZytc())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getXlqrz())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getXwqrz())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getXlzz())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getXwzz())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getQrz_byyx())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getZz_byyx())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getXrzw())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getNrzw())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getNmzw())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getRmly())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getCbdwyj())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getSpjgyj())+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(entity.getXzjgrmyj())+"'");
        if (StringUtils.isEmpty(entity.getFile2imgPath())){
            sb.append(",''");
        }else{
            String attsPath = GendataService.APP_ATTS_PATH+GbMcA01gbrmspbService.APP_ATTS_PATH
                    +FileUtil.getFileName(entity.getFile2imgPath());
            sb.append(",'"+attsPath+"'");

        }
        sb.append(")");
        return sb.toString();
    }

}