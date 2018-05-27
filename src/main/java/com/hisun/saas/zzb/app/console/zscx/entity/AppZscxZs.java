/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.zscx.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetB01;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouying on 2017/11/28.
 */
@Entity
@Table(name = "app_zscx_zs")
public class AppZscxZs extends TenantEntity implements Serializable {

    private String id;

    private String zwmc;//职务名称

    private int xp;//现配

    private int sp;//实配

    private int cqb;//超缺编


    private AppBsetB01 appBsetB01;


    private List<AppZscxZsA01> appZscxZsA01s;

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


    @Column(name = "zwmc")
    public String getZwmc() {
        return zwmc;
    }

    public void setZwmc(String zwmc) {
        this.zwmc = zwmc;
    }


    @Column(name = "xp")
    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }


    @Column(name = "sp")
    public int getSp() {
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }


    @Column(name = "cqb")
    public int getCqb() {
        return cqb;
    }

    public void setCqb(int cqb) {
        this.cqb = cqb;
    }
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "b01_id")
    public AppBsetB01 getAppBsetB01() {
        return appBsetB01;
    }

    public void setAppBsetB01(AppBsetB01 appBsetB01) {
        this.appBsetB01 = appBsetB01;
    }
    @OneToMany(mappedBy = "appZscxZs",fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<AppZscxZsA01> getAppZscxZsA01s() {
        return appZscxZsA01s;
    }

    public void setAppZscxZsA01s(List<AppZscxZsA01> appZscxZsA01s) {
        this.appZscxZsA01s = appZscxZsA01s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppZscxZs appZscxZs = (AppZscxZs) o;

        if (xp != appZscxZs.xp) return false;
        if (sp != appZscxZs.sp) return false;
        if (cqb != appZscxZs.cqb) return false;
        if (id != null ? !id.equals(appZscxZs.id) : appZscxZs.id != null) return false;
        if (zwmc != null ? !zwmc.equals(appZscxZs.zwmc) : appZscxZs.zwmc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (zwmc != null ? zwmc.hashCode() : 0);
        result = 31 * result + xp;
        result = 31 * result + sp;
        result = 31 * result + cqb;
        return result;
    }
}
