/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.aset.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name = "app_aset_a36")
public class AppAsetA36 extends TenantEntity implements Serializable{



    private String id;

    private AppAsetA01 appAsetA01;


    private String cw;

    private String xm;

    private String nl;

    private String csny;

    private String zzmm;

    private String gzdwjzw;

    private int px=0;


    public AppAsetA36(){}

    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="id",nullable=false,unique=true,length=32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name = "cw",length = 24)
    public String getCw() {
        return cw;
    }

    public void setCw(String cw) {
        this.cw = cw;
    }
    @Column(name = "xm",length = 24)
    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }
    @Column(name = "nl",length = 10)
    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }
    @Column(name = "zzmm",length = 24)
    public String getZzmm() {
        return zzmm;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm;
    }
    @Column(name = "gzdwjzw",length = 128)
    public String getGzdwjzw() {
        return gzdwjzw;
    }

    public void setGzdwjzw(String gzdwjzw) {
        this.gzdwjzw = gzdwjzw;
    }
    @Column(name = "shgx_px")
    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }
    @Column(name = "csny",length = 20)
    public String getCsny() {
        return csny;
    }

    public void setCsny(String csny) {
        this.csny = csny;
    }
    @ManyToOne(optional=true,fetch = FetchType.LAZY)
    @JoinColumn(name="a01_id")
    public AppAsetA01 getAppAsetA01() {
        return appAsetA01;
    }

    public void setAppAsetA01(AppAsetA01 appAsetA01) {
        this.appAsetA01 = appAsetA01;
    }


}
