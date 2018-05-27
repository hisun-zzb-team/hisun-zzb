/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.shpc.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name = "APP_SH_A01_DASCQK_TIPS")
public class Sha01dascqktips extends TenantEntity implements Serializable {


    private String id;

    private Sha01dascqk sha01dascqk;

    private String tip;
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
    @ManyToOne(optional=true,fetch=FetchType.LAZY)
    @JoinColumn(name="APP_SH_A01_DASCQK_ID")
    public Sha01dascqk getSha01dascqk() {
        return sha01dascqk;
    }

    public void setSha01dascqk(Sha01dascqk sha01dascqk) {
        this.sha01dascqk = sha01dascqk;
    }
    @Column(name = "TIP",length = 255)
    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }


}
