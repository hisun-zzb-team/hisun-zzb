/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.bset.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouying on 2017/12/16.
 */
@Entity
@Table(name = "app_bset_fl")
public class AppBsetFl extends TenantEntity implements Serializable {

    public static int DISPLAY=0;
    public static int HIDDEN=1;


    private String id;

    private String fl;

    private int px;

    private AppBsetFl parentFl;

    private List<AppBsetFl> children;

    private List<AppBsetFl2B01> appBsetFl2B01s;

    private int isHidden =DISPLAY;


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
    @Column(name = "fl")
    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
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
    public AppBsetFl getParentFl() {
        return parentFl;
    }

    public void setParentFl(AppBsetFl parentFl) {
        this.parentFl = parentFl;
    }
    @OneToMany(mappedBy = "parentFl", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<AppBsetFl> getChildren() {
        return children;
    }

    public void setChildren(List<AppBsetFl> children) {
        this.children = children;
    }
    @OneToMany(mappedBy = "appBsetFl", fetch = FetchType.LAZY)
    @OrderBy("px asc ")
    public List<AppBsetFl2B01> getAppBsetFl2B01s() {
        return appBsetFl2B01s;
    }

    public void setAppBsetFl2B01s(List<AppBsetFl2B01> appBsetFl2B01s) {
        this.appBsetFl2B01s = appBsetFl2B01s;
    }
    @Column(name = "is_hidden")
    public int getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(int isHidden) {
        this.isHidden = isHidden;
    }

}
