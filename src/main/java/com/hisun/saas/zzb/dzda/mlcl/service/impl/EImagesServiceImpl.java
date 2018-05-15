/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.mlcl.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.auth.session.mgt.quartz.QuartzSessionValidationScheduler;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.mlcl.Constants;
import com.hisun.saas.zzb.dzda.mlcl.dao.EImagesDao;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;
import com.hisun.saas.zzb.dzda.mlcl.entity.EImages;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.saas.zzb.dzda.mlcl.service.EImagesService;
import com.hisun.util.DESUtil;
import com.hisun.util.FileUtil;
import com.hisun.util.UUIDUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhout {605144321@qq.com}
 */
@Service
public class EImagesServiceImpl extends BaseServiceImpl<EImages, String>
        implements EImagesService {

    @Resource
    private E01Z1Service e01Z1Service;
    @Value("${sys.upload.absolute.path}")
    private String uploadBasePath;

    private EImagesDao eImagesDao;

    @Resource
    public void setBaseDao(BaseDao<EImages, String> baseDao) {
        this.baseDao = baseDao;
        this.eImagesDao = (EImagesDao) baseDao;
    }


    public void saveEImagesByJztp(A38 a38, File storePathFile) throws Exception {
        //在加载图片之前,先清除原有的已加载的图片
        this.deleteEImagesByA38(a38);
        List<File> files = FileUtil.listFilesOrderByName(storePathFile);
        Map<E01Z1 ,Integer> yjzTpMaps  = new HashMap<>();
        for (File file : files) {
            if (Arrays.asList(Constants.EXCLUDE_FILE_AND_DIR).contains(file.getName())) continue;
            //取得当前目录下的材料
            String e01Z101B = file.getName().substring(0, file.getName().indexOf("."));
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and("e01Z101B=:e01Z101B", "e01Z101B", e01Z101B));
            query.add(CommonRestrictions.and("a38.id=:a38Id", "a38Id", a38.getId()));
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("e01z107"));
            List<E01Z1> e01z1s = this.e01Z1Service.list(query, orderBy);
            //循环已上传图片文件,为对应的材料加载图片
            if (e01z1s.size() > 0) {
                List<File> tpFiles = FileUtil.listFilesOrderByName(file);
                for (File tpFile : tpFiles) {
                    boolean isNeedDelete = true;
                    if (Arrays.asList(Constants.EXCLUDE_FILE_AND_DIR).contains(tpFile.getName())) {
                        continue;
                    }
                    for (E01Z1 e01Z1 : e01z1s) {
                        DecimalFormat decimalFormat = new DecimalFormat("00");
                        String nameCode = decimalFormat.format(e01Z1.getE01Z107());//当前材料对应文件编号
                        String tpNameCode = tpFile.getName().substring(0,tpFile.getName().lastIndexOf(".")).substring(0, 2);//上传图片文件名编号
                        if (tpNameCode.equals(nameCode)) {
                            isNeedDelete = false;
                            EImages eImages = new EImages();
                            eImages.setE01z1(e01Z1);
                            String encryptFilePath = tpFile.getPath().substring(0,tpFile.getPath().lastIndexOf("."));
                            DESUtil.getInstance("a38Images").encrypt(tpFile,new File(encryptFilePath));
                            eImages.setImgFilePath(encryptFilePath.substring(uploadBasePath.length(),encryptFilePath.length()));
                            FileUtils.deleteQuietly(tpFile);
                            eImages.setImgNo(tpFile.getName().substring(0,tpFile.getName().lastIndexOf(".")).substring(2));
                            this.save(eImages);
                            //记录已加载图片数
                            if(yjzTpMaps.get(e01Z1)!=null){
                                Integer count = yjzTpMaps.get(e01Z1)+1;
                                yjzTpMaps.put(e01Z1,count);
                            }else{
                                yjzTpMaps.put(e01Z1,1);
                            }
                        }
                    }
                    //如果没有对应的材料则删除已上传的图片
                    if (isNeedDelete) {
                        if (tpFile.isDirectory()) {
                            FileUtils.deleteDirectory(tpFile);
                        } else {
                            FileUtils.deleteQuietly(tpFile);
                        }
                    }
                }
            } else {
                //如果没有对应的材料,则为多余的材料目录进行删除
                if(file.isDirectory()){
                    FileUtils.deleteDirectory(file);
                }else {
                    FileUtils.deleteQuietly(file);
                }
            }
        }

        //处理已加载图片数
        for(E01Z1 e01Z1 :yjzTpMaps.keySet()){
            e01Z1.setYjztps(yjzTpMaps.get(e01Z1));
            this.e01Z1Service.update(e01Z1);
        }
    }

    public String getTpStorePath(String a38Id) {
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        return Constants.DATP_STORE_PATH
                + userLoginDetails.getTenantId() + File.separator
                + a38Id.substring(a38Id.length() - 1, a38Id.length()) + File.separator
                + a38Id+ File.separator;
    }

    public void deleteEImagesByA38(A38 a38) throws Exception {
        StringBuffer hqlsb = new StringBuffer();
        hqlsb.append(" delete from EImages EImages where EImages.e01z1.id = :id");
        List<E01Z1> e01Z1s = a38.getE01z1s();
        for(E01Z1 e01Z1 : e01Z1s){
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and("","id",e01Z1.getId()));
            this.eImagesDao.executeBulk(hqlsb.toString(),query);
        }
       // FileUtils.deleteDirectory(new File(getTpStorePath(a38.getId())));
    }

}
