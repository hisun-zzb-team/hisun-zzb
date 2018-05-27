/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbtj.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/9/15.
 */
@Entity
@Table(name = "APP_DWJG_TJ")
public class Gbtj extends TenantEntity implements Serializable{


    private String id;

    private String tjmc;


    private String tjjsondata;


    private String tblx;//1 饼图 2柱状图 3折线图


    private int px;
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false, unique = true, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name = "TJ_MC",length = 128)
    public String getTjmc() {
        return tjmc;
    }

    public void setTjmc(String tjmc) {
        this.tjmc = tjmc;
    }
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "TJ_JSON_DATA",columnDefinition = "TEXT",nullable = true)
    public String getTjjsondata() {
        return tjjsondata;
    }

    public void setTjjsondata(String tjjsondata) {
        this.tjjsondata = tjjsondata;
    }
    @Column(name = "TJ_PX")
    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }
    @Column(name = "TBLX")
    public String getTblx() {
        return tblx;
    }

    public void setTblx(String tblx) {
        this.tblx = tblx;
    }

}
