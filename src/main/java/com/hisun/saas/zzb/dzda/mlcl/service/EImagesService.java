/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.mlcl.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;
import com.hisun.saas.zzb.dzda.mlcl.entity.EImages;

import java.io.File;

/**
 * @author zhout {605144321@qq.com}
 */
public interface EImagesService extends BaseService<EImages,String>{

    void saveEImagesByJztp(A38 a38, File storePathFile)throws Exception;
    void saveEImagesByJztp(E01Z1 e01Z1,File storePathFile)throws Exception;
    void deleteEImagesByA38(A38 a38);
    void deleteEImagesAndFileByA38(A38 a38) throws Exception;
    void updateEImagesImgNo(EImages eImages,int oldImgNo)throws Exception;
    void deleteEImages(EImages images)throws Exception;
    Integer getMaxImgNo(String e01z1Id);
}
