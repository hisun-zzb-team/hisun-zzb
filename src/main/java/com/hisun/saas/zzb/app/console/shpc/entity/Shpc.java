/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.shpc.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.saas.zzb.app.console.shtp.entity.Shtp;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name="APP_SH_PC")
public class Shpc extends TenantEntity implements Serializable{

    public static String SHLX_BWH="1";
    public static String SHLX_CWH="2";
    public static String SJLX_GB="1";//干部数据
    public static String SJLX_CL="2";//材料数据
    public static int YSH=1;
    public static int WSH=0;


    private String id;

    private String pcmc;

    private String shlx=SHLX_BWH;

    private Date pcsj;

    private String filePath;

    private int shZt=WSH;

    private String sjlx=SJLX_GB;

    private int px;


    private String mb;


    private List<Sha01> sha01s;


    private List<Shtp> shtps;


    private List<ShpcAtts> shpcAttses;

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
    @Column(name = "PC_MC",length = 255)
    public String getPcmc() {
        return pcmc;
    }

    public void setPcmc(String pcmc) {
        this.pcmc = pcmc;
    }
    @Column(name = "SHLX",length = 1)
    public String getShlx() {
        return shlx;
    }

    public void setShlx(String shlx) {
        this.shlx = shlx;
    }
    @Column(name = "PC_SJ")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getPcsj() {
        return pcsj;
    }

    public void setPcsj(Date pcsj) {
        this.pcsj = pcsj;
    }

    @OneToMany(mappedBy="shpc",fetch= FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<Sha01> getSha01s() {
        return sha01s;
    }

    public void setSha01s(List<Sha01> sha01s) {
        this.sha01s = sha01s;
    }
    @Column(name = "file_path")
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    @OneToMany(mappedBy="shpc",fetch= FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<Shtp> getShtps() {
        return shtps;
    }

    public void setShtps(List<Shtp> shtps) {
        this.shtps = shtps;
    }
    @Column(name = "sh_zt")//0-未上会，1-已上会
    public int getShZt() {
        return shZt;
    }

    public void setShZt(int shZt) {
        this.shZt = shZt;
    }
    @Column(name = "SJLX",length = 1)
    public String getSjlx() {
        return sjlx;
    }

    public void setSjlx(String sjlx) {
        this.sjlx = sjlx;
    }
    @OneToMany(mappedBy="shpc",fetch= FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<ShpcAtts> getShpcAttses() {
        return shpcAttses;
    }

    public void setShpcAttses(List<ShpcAtts> shpcAttses) {
        this.shpcAttses = shpcAttses;
    }
    @Column(name = "PC_PX")//排序
    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }
    @Column(name = "mb")
    public String getMb() {
        return mb;
    }

    public void setMb(String mb) {
        this.mb = mb;
    }

}
