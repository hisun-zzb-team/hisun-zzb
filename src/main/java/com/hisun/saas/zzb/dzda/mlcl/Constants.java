/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.mlcl;

import java.io.File;

/**
 * @author Rocky {rockwithyou@126.com}
 */
public class Constants {

    //允许图片最大大小2M
    public static final Integer DATP_MAX_FILE_SIZE =2*1024*1024;

    //档案存储路径
    public static final String DATP_STORE_PATH = File.separator+"a38"+File.separator;
    public static final String DATP_STORE_PATH_UPLOAD = File.separator+"e01z1"+File.separator;
    //档案模板存储路径
    public static final String DATPMB_STORE_PATH = DATP_STORE_PATH_UPLOAD+"mlxx.xlsx";
    public static final String[] EXCLUDE_FILE_AND_DIR={"__MACOSX",".DS_Store"};

    //档案图片秘钥
    public static final String DATP_KEY="a38Images";

    public static final String DATP_ZIP_NAME = "档案图片";

}
