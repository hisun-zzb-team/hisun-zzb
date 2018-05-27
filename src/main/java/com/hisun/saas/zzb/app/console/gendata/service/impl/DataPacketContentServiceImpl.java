/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gendata.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.apiregister.entity.ApiRegister;
import com.hisun.saas.zzb.app.console.apiregister.service.ApiRegisterService;
import com.hisun.saas.zzb.app.console.gbcx.service.GbcxService;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcService;
import com.hisun.saas.zzb.app.console.gbtj.service.GbtjService;
import com.hisun.saas.zzb.app.console.gendata.dao.DataPacketContentDao;
import com.hisun.saas.zzb.app.console.gendata.entity.DataPacketContent;
import com.hisun.saas.zzb.app.console.gendata.service.DataPacketContentService;
import com.hisun.saas.zzb.app.console.shpc.service.ShpcService;
import com.hisun.saas.sys.util.BeanTrans;
import com.hisun.util.CompressUtil;
import com.hisun.util.Md5Util;
import com.hisun.util.SqliteDBUtil;
import com.hisun.util.UUIDUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Service
public class DataPacketContentServiceImpl extends BaseServiceImpl<DataPacketContent, String> implements DataPacketContentService {
    

    private DataPacketContentDao dataPacketContentDao;

    @Autowired
    public void setBaseDao(BaseDao<DataPacketContent, String> dataPacketContentDao) {
        this.baseDao = dataPacketContentDao;
        this.dataPacketContentDao = (DataPacketContentDao) dataPacketContentDao;
    }

    

}
