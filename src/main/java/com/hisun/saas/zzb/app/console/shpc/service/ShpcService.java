/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.shpc.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.saas.zzb.app.console.shpc.vo.Sha01Vo;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by zhouying on 2017/9/15.
 */
public interface ShpcService extends BaseService<Shpc,String> {
    String ATTS_PATH = File.separator+"shpc"+ File.separator;
    String APP_ATTS_PATH = "shpc/";

    List<Sha01Vo> getShpcById(String shpcId) throws Exception;
    void exportExcel(String title, List<Sha01Vo> Sha01Vos, OutputStream out);
    Integer getMaxPx() ;
    void updatePx(int oldPx, int newPx);
    String toSqliteInsertSql(Shpc entity);
    void saveAsSqlite(String shpcId, String sqlite, String imgdir, String attsdir) throws Exception;
}
