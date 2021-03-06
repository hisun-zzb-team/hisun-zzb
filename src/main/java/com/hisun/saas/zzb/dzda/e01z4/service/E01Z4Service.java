/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.e01z4.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.dzda.e01z4.entity.E01Z4;

import javax.sql.DataSource;

/**
 * @author zhout {605144321@qq.com}
 */
public interface E01Z4Service extends BaseService<E01Z4,String>{
    Integer getMaxSort(String ad8Id);
    void updateE01Z4(E01Z4 e01z4, Integer oldSort);
    void updateSortBeforSave(E01Z4 e01z4, Integer oldSort);
    /**
     * 从广州三零系统导入数据
     * @param dataSource
     * @return
     */
    int saveFromGzslws(DataSource dataSource,String a3807B)throws Exception;
}
