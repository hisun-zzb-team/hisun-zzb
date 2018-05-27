/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.bset.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.saas.zzb.app.console.aset.entity.AppAsetA02;
import com.hisun.saas.zzb.app.console.zscx.entity.AppZscxZs;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouying on 2017/11/28.
 */
@Entity
@Table(name = "app_bset_b01")
public class AppBsetB01 extends TenantEntity implements Serializable {

    private String id;

    private String b0101;

    private int px;

    private int dataType;//类型 0--机构 1--分类

    private AppBsetB01 parentB01;

    private List<AppBsetB01> childrenB01s;


    private List<AppAsetA02> appAsetA02s;


    private List<AppBsetFl2B01> appBsetFl2B01s;


    private List<AppZscxZs> appZscxZses;


    private String queryCode;

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false, unique = true, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "b0101")
    public String getB0101() {
        return b0101;
    }

    public void setB0101(String b0101) {
        this.b0101 = b0101;
    }

    @Column(name = "data_type")
    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    @Column(name = "px")
    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public AppBsetB01 getParentB01() {
        return parentB01;
    }

    public void setParentB01(AppBsetB01 parentB01) {
        this.parentB01 = parentB01;
    }

    @OneToMany(mappedBy = "parentB01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<AppBsetB01> getChildrenB01s() {
        return childrenB01s;
    }

    public void setChildrenB01s(List<AppBsetB01> childrenB01s) {
        this.childrenB01s = childrenB01s;
    }
    @OneToMany(mappedBy = "appBsetB01",fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<AppAsetA02> getAppAsetA02s() {
        return appAsetA02s;
    }

    public void setAppAsetA02s(List<AppAsetA02> appAsetA02s) {
        this.appAsetA02s = appAsetA02s;
    }
    @OneToMany(mappedBy = "appBsetB01", fetch = FetchType.LAZY)
    public List<AppBsetFl2B01> getAppBsetFl2B01s() {
        return appBsetFl2B01s;
    }

    public void setAppBsetFl2B01s(List<AppBsetFl2B01> appBsetFl2B01s) {
        this.appBsetFl2B01s = appBsetFl2B01s;
    }
    @Column(name = "query_code")
    public String getQueryCode() {
        return queryCode;
    }

    public void setQueryCode(String queryCode) {
        this.queryCode = queryCode;
    }
    @OneToMany(mappedBy = "appBsetB01",fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<AppZscxZs> getAppZscxZses() {
        return appZscxZses;
    }

    public void setAppZscxZses(List<AppZscxZs> appZscxZses) {
        this.appZscxZses = appZscxZses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppBsetB01 that = (AppBsetB01) o;

        if (px != that.px) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (b0101 != null ? !b0101.equals(that.b0101) : that.b0101 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (b0101 != null ? b0101.hashCode() : 0);
        result = 31 * result + px;
        return result;
    }


}
