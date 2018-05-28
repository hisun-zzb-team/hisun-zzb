/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.zrzc;

import java.io.File;

/**
 * @author zhout {605144321@qq.com}
 */
public class Constants {


    //档案传递文件存储路径
    public static final String DACD_STORE_PATH = File.separator+"e01z7"+File.separator;
    //档案传递文件模板存储路径
    public static final String DACDMB_STORE_PATH = DACD_STORE_PATH+"zbda.xlsx";

    //档案接收文件存储路径
    public static final String DAJS_STORE_PATH = File.separator+"e01z5"+File.separator;
    //档案接收文件模板存储路径
    public static final String DAJSMB_STORE_PATH = DAJS_STORE_PATH+"zbda.xlsx";

}
