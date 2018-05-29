/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy;

import java.io.File;

/**
 * @author Rocky {rockwithyou@126.com}
 */
public class Constants {


    //查阅记录表存储路径
    public static final String CYJL_STORE_PATH = File.separator+"eA38Log"+File.separator;

    //查阅记录表模板存储路径
    public static final String CYJLMB_STORE_PATH = CYJL_STORE_PATH+"cyjl.xlsx";

    //查阅管理表存储路径
    public static final String CYGL_STORE_PATH = File.separator+"eA38Log"+File.separator;

    //查阅管理表模板存储路径
    public static final String CYGLMB_STORE_PATH = CYGL_STORE_PATH+"cygl.xlsx";

}
