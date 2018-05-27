/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.aset.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.saas.zzb.app.console.zscx.entity.AppZscxZsA01;
import com.hisun.util.DateUtil;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouying on 2017/11/28.
 */
@Entity
@Table(name = "app_aset_a01")
public class AppAsetA01 extends TenantEntity implements Serializable {

    private String id;

    private String xm; //姓名

    private String xb;//性别

    private String csny;//出生年月

    private String nl;//年龄

    private String mz;//名族

    private String zw;//职务


    private String csd;//出生地

    private String jg;//籍贯

    private String jkzk;//健康状态


    private String cjgzsj;//参加工作时间

    private String rdsj;//入党时间

    private String qrzxl;//全日制学历

    private String qrzxw;//全日制学位

    private String qrzByyx;//全日制毕业院校

    private String qrzZy;//全日制专业

    private String zzxl;//在职学历

    private String zzxw;//在职学位

    private String zzByyx;//在职毕业院校

    private String zzZy;//在职专业



    private String zyjszw;//专业技术职务

    private String zytc;//专业特长


    private String xrzj;//现任职级

    private String xrzj_code;//现任职级代码

    private String xrzjsj;//现任职级时间



    private String xrzwsj;//现任职务时间


    private String xrzw;//现任职务

    private String nrzw;//拟任职务

    private String nmzw;//拟免职务

    private String filepath;

    private String file2ImgPath;

    private String gzjlStr;//工作经历

    private String jcqkStr;//奖惩情况

    private String khjgStr;//考核结果


    private String dp;//党派

    private int a01Px;

    private String zpPath;


    private List<AppAsetA02> appAsetA02s;


    private List<AppAsetA36> appAsetA36s;


    private List<AppZscxZsA01> appZscxZsA01s;


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

    @Column(name = "xm")
    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    @Column(name = "xb")
    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    @Column(name = "mz")
    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    @Column(name = "zw")
    public String getZw() {
        return zw;
    }

    public void setZw(String zw) {
        this.zw = zw;
    }

    @Column(name = "csd")
    public String getCsd() {
        return csd;
    }

    public void setCsd(String csd) {
        this.csd = csd;
    }

    @Column(name = "jg")
    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    @Column(name = "csny")
    public String getCsny() {
        return csny;
    }

    public void setCsny(String csny) {
        this.csny = csny;
    }

    @Column(name = "cjgzsj")
    public String getCjgzsj() {
        return cjgzsj;
    }

    public void setCjgzsj(String cjgzsj) {
        this.cjgzsj = cjgzsj;
    }

    @Column(name = "rdsj")
    public String getRdsj() {
        return rdsj;
    }

    public void setRdsj(String rdsj) {
        this.rdsj = rdsj;
    }
    @Column(name = "zyjszw")
    public String getZyjszw() {
        return zyjszw;
    }

    public void setZyjszw(String zyjszw) {
        this.zyjszw = zyjszw;
    }

    @Column(name = "xrzwsj")
    public String getXrzwsj() {
        return xrzwsj;
    }

    public void setXrzwsj(String xrzwsj) {
        this.xrzwsj = xrzwsj;
    }

    @Column(name = "xrzjsj")
    public String getXrzjsj() {
        return xrzjsj;
    }

    public void setXrzjsj(String xrzjsj) {
        this.xrzjsj = xrzjsj;
    }

    @Column(name = "a01_px")
    public int getA01Px() {
        return a01Px;
    }

    public void setA01Px(int a01Px) {
        this.a01Px = a01Px;
    }

    @Column(name = "zp_path")
    public String getZpPath() {
        return zpPath;
    }

    public void setZpPath(String zpPath) {
        this.zpPath = zpPath;
    }
    @Column(name = "qrzxl")
    public String getQrzxl() {
        return qrzxl;
    }

    public void setQrzxl(String qrzxl) {
        this.qrzxl = qrzxl;
    }
    @Column(name = "qrzxw")
    public String getQrzxw() {
        return qrzxw;
    }

    public void setQrzxw(String qrzxw) {
        this.qrzxw = qrzxw;
    }
    @Column(name = "zzxl")
    public String getZzxl() {
        return zzxl;
    }

    public void setZzxl(String zzxl) {
        this.zzxl = zzxl;
    }
    @Column(name = "zzxw")
    public String getZzxw() {
        return zzxw;
    }

    public void setZzxw(String zzxw) {
        this.zzxw = zzxw;
    }
    @OneToMany(mappedBy = "appAsetA01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OrderBy("px asc ")
    public List<AppAsetA02> getAppAsetA02s() {
        return appAsetA02s;
    }

    public void setAppAsetA02s(List<AppAsetA02> appAsetA02s) {
        this.appAsetA02s = appAsetA02s;
    }

    @Column(name = "nl")
    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }
    @Column(name = "jkzk")
    public String getJkzk() {
        return jkzk;
    }

    public void setJkzk(String jkzk) {
        this.jkzk = jkzk;
    }
    @Column(name = "qrz_byyx")
    public String getQrzByyx() {
        return qrzByyx;
    }

    public void setQrzByyx(String qrzByyx) {
        this.qrzByyx = qrzByyx;
    }
    @Column(name = "qrz_zy")
    public String getQrzZy() {
        return qrzZy;
    }

    public void setQrzZy(String qrzZy) {
        this.qrzZy = qrzZy;
    }
    @Column(name = "zz_byyx")
    public String getZzByyx() {
        return zzByyx;
    }

    public void setZzByyx(String zzByyx) {
        this.zzByyx = zzByyx;
    }
    @Column(name = "zz_zy")
    public String getZzZy() {
        return zzZy;
    }

    public void setZzZy(String zzZy) {
        this.zzZy = zzZy;
    }
    @Column(name = "zytc")
    public String getZytc() {
        return zytc;
    }

    public void setZytc(String zytc) {
        this.zytc = zytc;
    }
    @Column(name = "xrzw")
    public String getXrzw() {
        return xrzw;
    }

    public void setXrzw(String xrzw) {
        this.xrzw = xrzw;
    }
    @Column(name = "nrzw")
    public String getNrzw() {
        return nrzw;
    }

    public void setNrzw(String nrzw) {
        this.nrzw = nrzw;
    }
    @Column(name = "nmzw")
    public String getNmzw() {
        return nmzw;
    }

    public void setNmzw(String nmzw) {
        this.nmzw = nmzw;
    }
    @Column(name = "file_path")
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
    @Column(name = "file2img_path")
    public String getFile2ImgPath() {
        return file2ImgPath;
    }

    public void setFile2ImgPath(String file2ImgPath) {
        this.file2ImgPath = file2ImgPath;
    }
    @Column(name = "gzjl_str")
    public String getGzjlStr() {
        return gzjlStr;
    }

    public void setGzjlStr(String gzjlStr) {
        this.gzjlStr = gzjlStr;
    }
    @Column(name = "jcqk_str")
    public String getJcqkStr() {
        return jcqkStr;
    }

    public void setJcqkStr(String jcqkStr) {
        this.jcqkStr = jcqkStr;
    }
    @Column(name = "khjg_str")
    public String getKhjgStr() {
        return khjgStr;
    }

    public void setKhjgStr(String khjgStr) {
        this.khjgStr = khjgStr;
    }

    @OneToMany(mappedBy = "appAsetA01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OrderBy("shgx_px asc ")
    public List<AppAsetA36> getAppAsetA36s() {
        return appAsetA36s;
    }

    public void setAppAsetA36s(List<AppAsetA36> appAsetA36s) {
        this.appAsetA36s = appAsetA36s;
    }

    @OneToMany(mappedBy = "appAsetA01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<AppZscxZsA01> getAppZscxZsA01s() {
        return appZscxZsA01s;
    }

    public void setAppZscxZsA01s(List<AppZscxZsA01> appZscxZsA01s) {
        this.appZscxZsA01s = appZscxZsA01s;
    }

    @Column(name = "xrzj")
    public String getXrzj() {
        return xrzj;
    }

    public void setXrzj(String xrzj) {
        this.xrzj = xrzj;
    }
    @Column(name = "xrzj_code")
    public String getXrzj_code() {
        return xrzj_code;
    }

    public void setXrzj_code(String xrzj_code) {
        this.xrzj_code = xrzj_code;
    }
    @Column(name = "dp")
    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }
}
