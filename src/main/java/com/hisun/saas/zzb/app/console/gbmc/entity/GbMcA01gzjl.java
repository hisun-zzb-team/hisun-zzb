/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbmc.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name = "app_mc_a01_gzjl")
public class GbMcA01gzjl extends TenantEntity implements Serializable {


    private String id;

    private GbMcA01 gbMcA01;

    private String csj;

    private String zsj;

    private String jlsm;

    private int px;

    public GbMcA01gzjl(){

    }

    public GbMcA01gzjl(String gzjl,int px){
        this.jlsm= gzjl;
        this.px=px;
    }

    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="ID",nullable=false,unique=true,length=32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Column(name = "C_SJ",length = 24)
    public String getCsj() {
        return csj;
    }

    public void setCsj(String csj) {
        this.csj = csj;
    }
    @Column(name = "Z_SJ",length = 24)
    public String getZsj() {
        return zsj;
    }

    public void setZsj(String zsj) {
        this.zsj = zsj;
    }
    @Column(name = "JLSM",length = 255)//经历说明
    public String getJlsm() {
        return jlsm;
    }

    public void setJlsm(String jlsm) {
        this.jlsm = jlsm;
    }
    @Column(name = "GZJL_PX")
    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }
    @ManyToOne(optional=true,fetch = FetchType.LAZY)
    @JoinColumn(name="APP_MC_A01_ID")
    public GbMcA01 getGbMcA01() {
        return gbMcA01;
    }

    public void setGbMcA01(GbMcA01 gbMcA01) {
        this.gbMcA01 = gbMcA01;
    }


}
