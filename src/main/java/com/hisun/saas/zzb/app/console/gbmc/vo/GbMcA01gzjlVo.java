/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbmc.vo;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.saas.zzb.app.console.shpc.vo.Sha01Vo;

import java.io.Serializable;

/**
 * Created by zhouying on 2017/9/8.
 */
public class GbMcA01gzjlVo extends TenantEntity implements Serializable {

    private String id;
    private GbMcA01Vo gbMcA01Vo;
    private String csj;
    private String zsj;
    private String jlsm;
    private int px;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GbMcA01Vo getGbMcA01Vo() {
        return gbMcA01Vo;
    }

    public void setGbMcA01Vo(GbMcA01Vo gbMcA01Vo) {
        this.gbMcA01Vo = gbMcA01Vo;
    }

    public String getCsj() {
        return csj;
    }

    public void setCsj(String csj) {
        this.csj = csj;
    }

    public String getZsj() {
        return zsj;
    }

    public void setZsj(String zsj) {
        this.zsj = zsj;
    }

    public String getJlsm() {
        return jlsm;
    }

    public void setJlsm(String jlsm) {
        this.jlsm = jlsm;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }
}
