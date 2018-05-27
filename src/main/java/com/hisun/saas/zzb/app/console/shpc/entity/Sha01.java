/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.shpc.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.saas.zzb.app.console.shtp.entity.Shtpsj;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name = "APP_SH_A01")
public class Sha01 extends TenantEntity implements Serializable {

    private String id;

    private Shpc shpc;

    private String xm;

    private String xb;

    private String mz;

    private String jg;

    private String csny;

    private String cjgzsj;

    private String rdsj;

    private String whcd;

    private String rxjbsj;

    private String mztjqk;

    private String ywfpjl;

    private String xgzdwjzw;

    private String ntzpbyj;

    private String shyj;

    private int px = 0;

    private String zppath;

    private String jsbs;


    private List<Sha01dascqk> dascqks;

    private List<Sha01gbrmspb> gbrmspbs;

    private List<Sha01grzdsx> grzdsxes;

    private List<Sha01gzjl> gzjls;

    private List<Sha01jc> jcs;

    private List<Sha01kccl> kccls;

    private List<Sha01ndkh> ndkhs;

    private List<Sha01shgx> shgxes;


    private List<Shtpsj> shtpsjs;


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
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "APP_SH_PC_ID")
    public Shpc getShpc() {
        return shpc;
    }

    public void setShpc(Shpc shpc) {
        this.shpc = shpc;
    }
    @Column(name = "XM", length = 20)
    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }
    @Column(name = "XB", length = 10)
    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }
    @Column(name = "MZ", length = 20)
    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }
    @Column(name = "JG", length = 20)
    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }
    @Column(name = "CSNY", length = 24)
    public String getCsny() {
        return csny;
    }

    public void setCsny(String csny) {
        this.csny = csny;
    }
    @Column(name = "RDSJ", length = 24)
    public String getRdsj() {
        return rdsj;
    }

    public void setRdsj(String rdsj) {
        this.rdsj = rdsj;
    }
    @Column(name = "WHCD", length = 40)
    public String getWhcd() {
        return whcd;
    }

    public void setWhcd(String whcd) {
        this.whcd = whcd;
    }
    @Column(name = "RXJBSJ", length = 24)
    public String getRxjbsj() {
        return rxjbsj;
    }

    public void setRxjbsj(String rxjbsj) {
        this.rxjbsj = rxjbsj;
    }
    @Column(name = "MZTJQK", length = 80)
    public String getMztjqk() {
        return mztjqk;
    }

    public void setMztjqk(String mztjqk) {
        this.mztjqk = mztjqk;
    }
    @Column(name = "YWFPJL", length = 10)
    public String getYwfpjl() {
        return ywfpjl;
    }

    public void setYwfpjl(String ywfpjl) {
        this.ywfpjl = ywfpjl;
    }
    @Column(name = "NTZPBYJ", length = 24)
    public String getNtzpbyj() {
        return ntzpbyj;
    }

    public void setNtzpbyj(String ntzpbyj) {
        this.ntzpbyj = ntzpbyj;
    }
    @Column(name = "SHYJ", length = 24)
    public String getShyj() {
        return shyj;
    }

    public void setShyj(String shyj) {
        this.shyj = shyj;
    }
    @Column(name = "A01_PX")
    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }
    @Column(name = "ZP_PATH",length = 128)
    public String getZppath() {
        return zppath;
    }

    public void setZppath(String zppath) {
        this.zppath = zppath;
    }
    @OneToMany(mappedBy = "sha01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<Sha01dascqk> getDascqks() {
        return dascqks;
    }

    public void setDascqks(List<Sha01dascqk> dascqks) {
        this.dascqks = dascqks;
    }
    @OneToMany(mappedBy = "sha01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<Sha01gzjl> getGzjls() {
        return gzjls;
    }

    public void setGzjls(List<Sha01gzjl> gzjls) {
        this.gzjls = gzjls;
    }
    @OneToMany(mappedBy = "sha01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<Sha01jc> getJcs() {
        return jcs;
    }

    public void setJcs(List<Sha01jc> jcs) {
        this.jcs = jcs;
    }
    @OneToMany(mappedBy = "sha01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<Sha01kccl> getKccls() {
        return kccls;
    }

    public void setKccls(List<Sha01kccl> kccls) {
        this.kccls = kccls;
    }
    @OneToMany(mappedBy = "sha01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<Sha01ndkh> getNdkhs() {
        return ndkhs;
    }

    public void setNdkhs(List<Sha01ndkh> ndkhs) {
        this.ndkhs = ndkhs;
    }
    @OneToMany(mappedBy = "sha01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<Sha01shgx> getShgxes() {
        return shgxes;
    }

    public void setShgxes(List<Sha01shgx> shgxes) {
        this.shgxes = shgxes;
    }
    @OneToMany(mappedBy = "sha01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<Sha01gbrmspb> getGbrmspbs() {
        return gbrmspbs;
    }

    public void setGbrmspbs(List<Sha01gbrmspb> gbrmspbs) {
        this.gbrmspbs = gbrmspbs;
    }
    @OneToMany(mappedBy = "sha01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<Sha01grzdsx> getGrzdsxes() {
        return grzdsxes;
    }

    public void setGrzdsxes(List<Sha01grzdsx> grzdsxes) {
        this.grzdsxes = grzdsxes;
    }
    @OneToMany(mappedBy = "sha01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<Shtpsj> getShtpsjs() {
        return shtpsjs;
    }

    public void setShtpsjs(List<Shtpsj> shtpsjs) {
        this.shtpsjs = shtpsjs;
    }
    @Column(name = "CJGZSJ", length = 24)
    public String getCjgzsj() {
        return cjgzsj;
    }

    public void setCjgzsj(String cjgzsj) {
        this.cjgzsj = cjgzsj;
    }
    @Column(name = "XGZDWJZW", length = 10)
    public String getXgzdwjzw() {
        return xgzdwjzw;
    }

    public void setXgzdwjzw(String xgzdwjzw) {
        this.xgzdwjzw = xgzdwjzw;
    }
    @Column(name = "jsbs")
    public String getJsbs() {
        return jsbs;
    }

    public void setJsbs(String jsbs) {
        this.jsbs = jsbs;
    }


    public void addGbrmspb(Sha01gbrmspb sha01gbrmspb){
        if(this.gbrmspbs==null){
            this.gbrmspbs = new ArrayList<>();
        }
        sha01gbrmspb.setSha01(this);
        this.gbrmspbs.add(sha01gbrmspb);
    }

    public void addShgx(Sha01shgx sha01shgx){
        if(this.shgxes==null){
            this.shgxes = new ArrayList<>();
        }
        sha01shgx.setSha01(this);
        this.shgxes.add(sha01shgx);
    }

    public void addGzjl(Sha01gzjl sha01gzjl){
        if(this.gzjls==null){
            this.gzjls = new ArrayList<>();
        }
        sha01gzjl.setSha01(this);
        this.gzjls.add(sha01gzjl);
    }
}
