/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbmc.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name="APP_MC")
public class GbMc extends TenantEntity implements Serializable{

    public static int YML=0;//有目录
    public static int WML=1;//无目录


    private String id;

    private String mc;

    private int px;

    private int isMl = YML;//是否存在目录

    private String mb;//选择的模板


    private List<GbMcB01> gbMcB01s;


    private List<GbMcA01> gbMcA01s;
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
    @Column(name = "mc",length = 255)
    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }
    @Column(name = "px")
    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }
    @OneToMany(mappedBy="gbMc",fetch= FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<GbMcB01> getGbMcB01s() {
        return gbMcB01s;
    }

    public void setGbMcB01s(List<GbMcB01> gbMcB01s) {
        this.gbMcB01s = gbMcB01s;
    }
    @Column(name = "is_ml")
    public int getIsMl() {
        return isMl;
    }

    public void setIsMl(int isMl) {
        this.isMl = isMl;
    }
    @OneToMany(mappedBy = "gbMc", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    public List<GbMcA01> getGbMcA01s() {
        return gbMcA01s;
    }

    public void setGbMcA01s(List<GbMcA01> gbMcA01s) {
        this.gbMcA01s = gbMcA01s;
    }

    @Column(name = "mb")
    public String getMb() {
        return mb;
    }

    public void setMb(String mb) {
        this.mb = mb;
    }


    public void addGbMcA01(GbMcA01 gbMcA01){
        if(this.gbMcA01s==null){
            this.gbMcA01s = new ArrayList<>();
        }
        gbMcA01.setGbMc(this);
        this.gbMcA01s.add(gbMcA01);
    }

    public void addGbMcB01(GbMcB01 gbMcB01){
        if(this.gbMcB01s==null){
            this.gbMcB01s = new ArrayList<>();
        }
        gbMcB01.setGbMc(this);
        this.gbMcB01s.add(gbMcB01);
    }
}
