/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.shpc.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name = "APP_SH_A01_GBRMSPB")
public class Sha01gbrmspb extends TenantEntity implements Serializable{


    private String id;

    private Sha01 sha01;

    private String xm;

    private String xb;

    private String csny;

    private String nl;

    private String zppath;

    private String mz;

    private String jg;

    private String csd;



    private String rdsj;

    private String cjgzsj;

    private String jkzk;

    private String zyjszw;

    private String zytc;

    private String xlqrz;

    private String xwqrz;

    private String xlzz;



    private String xwzz;

    private String  qrz_byyx;

    private String zz_byyx;

    private String xrzw;

    private String nrzw;


    private String nmzw;


    private String rmly;

    private String cbdwyj;

    private String spjgyj;

    private String xzjgrmyj;

    private String filepath;

    private String file2imgPath;


    private String gzjlStr;

    private String jcqkStr;

    private String khjgStr;

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
    @ManyToOne(optional=true,fetch = FetchType.LAZY)
    @JoinColumn(name="APP_SH_A01_ID")
    public Sha01 getSha01() {
        return sha01;
    }

    public void setSha01(Sha01 sha01) {
        this.sha01 = sha01;
    }
    @Column(name = "XM",length = 20)
    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }
    @Column(name = "XB",length = 10)
    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }
    @Column(name = "CSNY",length = 24)
    public String getCsny() {
        return csny;
    }

    public void setCsny(String csny) {
        this.csny = csny;
    }
    @Column(name = "NL",length = 10)
    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }
    @Column(name = "ZP_PATH",length = 128)
    public String getZppath() {
        return zppath;
    }

    public void setZppath(String zppath) {
        this.zppath = zppath;
    }
    @Column(name = "MZ",length = 24)
    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }
    @Column(name = "JG",length = 24)
    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }
    @Column(name = "CSD",length = 24)
    public String getCsd() {
        return csd;
    }

    public void setCsd(String csd) {
        this.csd = csd;
    }
    @Column(name = "RDSJ",length = 10)
    public String getRdsj() {
        return rdsj;
    }

    public void setRdsj(String rdsj) {
        this.rdsj = rdsj;
    }
    @Column(name = "CJGZSJ",length = 10)
    public String getCjgzsj() {
        return cjgzsj;
    }

    public void setCjgzsj(String cjgzsj) {
        this.cjgzsj = cjgzsj;
    }
    @Column(name = "JKZK",length = 24)
    public String getJkzk() {
        return jkzk;
    }

    public void setJkzk(String jkzk) {
        this.jkzk = jkzk;
    }
    @Column(name = "ZYJSZW",length = 60)
    public String getZyjszw() {
        return zyjszw;
    }

    public void setZyjszw(String zyjszw) {
        this.zyjszw = zyjszw;
    }
    @Column(name = "ZYTC",length = 60)
    public String getZytc() {
        return zytc;
    }

    public void setZytc(String zytc) {
        this.zytc = zytc;
    }
    @Column(name = "XL_QRZ",length = 24)
    public String getXlqrz() {
        return xlqrz;
    }

    public void setXlqrz(String xlqrz) {
        this.xlqrz = xlqrz;
    }
    @Column(name = "XW_QRZ",length = 24)
    public String getXwqrz() {
        return xwqrz;
    }

    public void setXwqrz(String xwqrz) {
        this.xwqrz = xwqrz;
    }
    @Column(name = "XL_ZZ",length = 24)
    public String getXlzz() {
        return xlzz;
    }

    public void setXlzz(String xlzz) {
        this.xlzz = xlzz;
    }
    @Column(name = "XW_ZZ",length = 24)
    public String getXwzz() {
        return xwzz;
    }

    public void setXwzz(String xwzz) {
        this.xwzz = xwzz;
    }
    @Column(name = "QRZ_BYYX",length = 128)
    public String getQrz_byyx() {
        return qrz_byyx;
    }

    public void setQrz_byyx(String qrz_byyx) {
        this.qrz_byyx = qrz_byyx;
    }
    @Column(name = "ZZ_BYYX",length = 128)
    public String getZz_byyx() {
        return zz_byyx;
    }

    public void setZz_byyx(String zz_byyx) {
        this.zz_byyx = zz_byyx;
    }
    @Column(name = "XRZW",length = 128)
    public String getXrzw() {
        return xrzw;
    }

    public void setXrzw(String xrzw) {
        this.xrzw = xrzw;
    }
    @Column(name = "NRZW",length = 128)
    public String getNrzw() {
        return nrzw;
    }

    public void setNrzw(String nrzw) {
        this.nrzw = nrzw;
    }
    @Column(name = "RMLY",length = 255)
    public String getRmly() {
        return rmly;
    }

    public void setRmly(String rmly) {
        this.rmly = rmly;
    }
    @Column(name = "CBDWYJ",length = 255)
    public String getCbdwyj() {
        return cbdwyj;
    }

    public void setCbdwyj(String cbdwyj) {
        this.cbdwyj = cbdwyj;
    }
    @Column(name = "SPJGYJ",length = 24)
    public String getSpjgyj() {
        return spjgyj;
    }

    public void setSpjgyj(String spjgyj) {
        this.spjgyj = spjgyj;
    }
    @Column(name = "XZJGRMYJ",length = 24)
    public String getXzjgrmyj() {
        return xzjgrmyj;
    }

    public void setXzjgrmyj(String xzjgrmyj) {
        this.xzjgrmyj = xzjgrmyj;
    }
    @Column(name = "NMZW",length = 128)
    public String getNmzw() {
        return nmzw;
    }

    public void setNmzw(String nmzw) {
        this.nmzw = nmzw;
    }
    @Column(name = "file_path",length = 128)
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        //在Windows环境下,采用原生SQL操作数据库,注意文件路径
        this.filepath = filepath.replaceAll("\\\\", "\\\\\\\\");
    }

    @Column(name = "FILE2IMG_PATH",length = 128)
    public String getFile2imgPath() {
        return file2imgPath;
    }

    public void setFile2imgPath(String file2imgPath) {
        this.file2imgPath = file2imgPath.replaceAll("\\\\", "\\\\\\\\");
    }
    @Type(type="text")
    @Column(name = "gzjl_str")
    public String getGzjlStr() {
        return gzjlStr;
    }

    public void setGzjlStr(String gzjlStr) {
        this.gzjlStr = gzjlStr;
    }
    @Type(type="text")
    @Column(name = "jcqk_str")
    public String getJcqkStr() {
        return jcqkStr;
    }

    public void setJcqkStr(String jcqkStr) {
        this.jcqkStr = jcqkStr;
    }
    @Type(type="text")
    @Column(name = "khjg_str")
    public String getKhjgStr() {
        return khjgStr;
    }

    public void setKhjgStr(String khjgStr) {
        this.khjgStr = khjgStr;
    }


}
