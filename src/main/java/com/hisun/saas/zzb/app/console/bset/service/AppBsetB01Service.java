/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.bset.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA02;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetB01;
import com.hisun.saas.zzb.app.console.bset.vo.B01TreeVo;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by zhouying on 2017/9/16.
 */
public interface AppBsetB01Service extends BaseService<AppBsetB01,String> {

    Integer getMaxPx(String parentId) ;
    void updatePx(String parentId, int oldPx, int newPx);
    int saveFromYw(DataSource dataSource)throws Exception;
    int saveFromZdwx(DataSource dataSource)throws Exception;

    //得到树的集合 dataType数据组合类型 fl为先读取分类，找分类下的机构 b01表示直接找机构组成树结构
    List<B01TreeVo> getB01TreeVoList(String dataSourceTyle)throws Exception;
    void deleteAllData() throws Exception;
    String toSqliteInsertSql(AppBsetB01 entity);
}
