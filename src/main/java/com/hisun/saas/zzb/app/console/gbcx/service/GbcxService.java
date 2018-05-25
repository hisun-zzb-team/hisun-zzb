/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbcx.service;

/**
 * Created by zhouying on 2018/1/3.
 */
public interface GbcxService {

    void saveAsSqlite(String sqlite, String imgdir, String attsdir)throws Exception;
}
