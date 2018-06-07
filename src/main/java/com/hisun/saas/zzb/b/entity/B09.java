/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.b.entity;

import com.hisun.base.entity.TombstoneEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author liuzj {279421824@qq.com}
 */
@Entity
@Table(name = "b09")
public class B09 extends TombstoneEntity implements Serializable {
    /** 主键 */
    private String b0900;

    /** 查询职务名称 */
    private String b0901A;

    /** 查询职务名称代码ZB08-2006/ZWMC */
    private String b0901B;

    /** 职务类别ZB42-2006/ZWLB */
    private String b0904;

    /** 职务类别名称 */
    private String b0904A;

    /** 职级ZB09-2006/ZWJB */
    private String b0907;

    /** 职务级别名称 */
    private String b0907A;

    /** 职数 */
    private Integer b0911;

    /** 现配职数 */
    private Integer b0914;

    /** 超编原因 */
    private String b0917;

    /** 缺编原因 */
    private String b0921;

    /** 职务排序 */
    private Integer bPx;

    /** 单位职务名称 */
    private String bDwzwmc;

    /** 职数管理名称 */
    private String bZsglmc;

    /** 是否存在兼任，1－存在，0－不存在 */
    private String bSfjr;

    /** 是否能存在包含，1－存在，0－不存在 */
    private String bSfbh;

    /** 是否占编制，1－占用，0－不占用 */
    private String bSfzb;

    /** 是否领导职务，1－是，0－否,默认1 */
    private String bSfldzw;

    /** 是否班子成员，1－是，0－否  */
    private String bSfbzcy;

    /** 是否需要试用 */
    private String bSfsy;

    /** 试用时长 */
    private Integer bSysc;

    /** 试用时长单位,1－月，2－年 */
    private String bSyscdw;

    /** 退休年龄,格式(60,60) */
    private String bTxnl;

    /** 该职务是否存在多职级，1－是，0－否  */
    private String bSfdzj;

    /** 是否在本单位名册中显示此职务，1－是，0－否。默认为是 */
    private String bSfmcxs;

    /** 管理类别，单位管理类别:01－中央管理，02－省级党委管理，03－市级党委管理，04－县级党委管理，05－其他 */
    private String bGllbB;

    /** 管理类别说明，单位管理类别:01－中央管理，02－省级党委管理，03－市级党委管理，04－县级党委管理，05－其他 */
    private String bGllbA;

    /** 是否统计职数:0-不统计,1-统计 */
    private String bSftjzs;

    /** 编制性质ZB61-2006/BZXZ */
    private String bBzxzB;

    /** 所属编制性质名字 */
    private String bBzxzA;

    /** 排序参照职务id */
    private String bCzpxzwB;

    /** 排序参照职务名称 */
    private String bCzpxzwA;

    /** 职务状态1-在编，0-撤销，默认1 */
    private String bZwztB;

    /** 职务状态内容 */
    private String bZwztA;

    private B01 b01;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b0100")
    public B01 getB01() {
        return b01;
    }

    public void setB01(B01 b01) {
        this.b01 = b01;
    }
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "b0900", nullable = false, unique = true, length = 32)
    public String getB0900() {
        return b0900;
    }

    public void setB0900(String b0900) {
        this.b0900 = b0900;
    }



    @Basic
    @javax.persistence.Column(name = "b0901a", nullable = true, length = 256)
    public String getB0901A() {
        return b0901A;
    }

    public void setB0901A(String b0901A) {
        this.b0901A = b0901A;
    }


    @Basic
    @javax.persistence.Column(name = "b0901b", nullable = true, length = 128)
    public String getB0901B() {
        return b0901B;
    }

    public void setB0901B(String b0901B) {
        this.b0901B = b0901B;
    }


    @Basic
    @javax.persistence.Column(name = "b0904", nullable = true, length = 128)
    public String getB0904() {
        return b0904;
    }

    public void setB0904(String b0904) {
        this.b0904 = b0904;
    }


    @Basic
    @javax.persistence.Column(name = "b0904a", nullable = true, length = 256)
    public String getB0904A() {
        return b0904A;
    }

    public void setB0904A(String b0904A) {
        this.b0904A = b0904A;
    }


    @Basic
    @javax.persistence.Column(name = "b0907", nullable = true, length = 128)
    public String getB0907() {
        return b0907;
    }

    public void setB0907(String b0907) {
        this.b0907 = b0907;
    }


    @Basic
    @javax.persistence.Column(name = "b0907a", nullable = true, length = 256)
    public String getB0907A() {
        return b0907A;
    }

    public void setB0907A(String b0907A) {
        this.b0907A = b0907A;
    }


    @Basic
    @javax.persistence.Column(name = "b0911", nullable = true, precision = 0)
    public Integer getB0911() {
        return b0911;
    }

    public void setB0911(Integer b0911) {
        this.b0911 = b0911;
    }


    @Basic
    @javax.persistence.Column(name = "b0914", nullable = true, precision = 0)
    public Integer getB0914() {
        return b0914;
    }

    public void setB0914(Integer b0914) {
        this.b0914 = b0914;
    }


    @Basic
    @javax.persistence.Column(name = "b0917", nullable = true, length = 4000)
    public String getB0917() {
        return b0917;
    }

    public void setB0917(String b0917) {
        this.b0917 = b0917;
    }


    @Basic
    @javax.persistence.Column(name = "b0921", nullable = true, length = 4000)
    public String getB0921() {
        return b0921;
    }

    public void setB0921(String b0921) {
        this.b0921 = b0921;
    }

    @Basic
    @javax.persistence.Column(name = "b_px", nullable = true, precision = 0)
    public Integer getbPx() {
        return bPx;
    }

    public void setbPx(Integer bPx) {
        this.bPx = bPx;
    }


    @Basic
    @javax.persistence.Column(name = "b_dwzwmc", nullable = true, length = 256)
    public String getbDwzwmc() {
        return bDwzwmc;
    }

    public void setbDwzwmc(String bDwzwmc) {
        this.bDwzwmc = bDwzwmc;
    }


    @Basic
    @javax.persistence.Column(name = "b_zsglmc", nullable = true, length = 256)
    public String getbZsglmc() {
        return bZsglmc;
    }

    public void setbZsglmc(String bZsglmc) {
        this.bZsglmc = bZsglmc;
    }


    @Basic
    @javax.persistence.Column(name = "b_sfjr", nullable = true, length = 1)
    public String getbSfjr() {
        return bSfjr;
    }

    public void setbSfjr(String bSfjr) {
        this.bSfjr = bSfjr;
    }


    @Basic
    @javax.persistence.Column(name = "b_sfbh", nullable = true, length = 1)
    public String getbSfbh() {
        return bSfbh;
    }

    public void setbSfbh(String bSfbh) {
        this.bSfbh = bSfbh;
    }


    @Basic
    @javax.persistence.Column(name = "b_sfzb", nullable = true, length = 1)
    public String getbSfzb() {
        return bSfzb;
    }

    public void setbSfzb(String bSfzb) {
        this.bSfzb = bSfzb;
    }


    @Basic
    @javax.persistence.Column(name = "b_sfldzw", nullable = true, length = 2)
    public String getbSfldzw() {
        return bSfldzw;
    }

    public void setbSfldzw(String bSfldzw) {
        this.bSfldzw = bSfldzw;
    }


    @Basic
    @javax.persistence.Column(name = "b_sfbzcy", nullable = true, length = 1)
    public String getbSfbzcy() {
        return bSfbzcy;
    }

    public void setbSfbzcy(String bSfbzcy) {
        this.bSfbzcy = bSfbzcy;
    }


    @Basic
    @javax.persistence.Column(name = "b_sfsy", nullable = true, length = 1)
    public String getbSfsy() {
        return bSfsy;
    }

    public void setbSfsy(String bSfsy) {
        this.bSfsy = bSfsy;
    }


    @Basic
    @javax.persistence.Column(name = "b_sysc", nullable = true, precision = 0)
    public Integer getbSysc() {
        return bSysc;
    }

    public void setbSysc(Integer bSysc) {
        this.bSysc = bSysc;
    }


    @Basic
    @javax.persistence.Column(name = "b_syscdw", nullable = true, length = 1)
    public String getbSyscdw() {
        return bSyscdw;
    }

    public void setbSyscdw(String bSyscdw) {
        this.bSyscdw = bSyscdw;
    }


    @Basic
    @javax.persistence.Column(name = "b_txnl", nullable = true, length = 10)
    public String getbTxnl() {
        return bTxnl;
    }

    public void setbTxnl(String bTxnl) {
        this.bTxnl = bTxnl;
    }


    @Basic
    @javax.persistence.Column(name = "b_sfdzj", nullable = true, length = 1)
    public String getbSfdzj() {
        return bSfdzj;
    }

    public void setbSfdzj(String bSfdzj) {
        this.bSfdzj = bSfdzj;
    }


    @Basic
    @javax.persistence.Column(name = "b_sfmcxs", nullable = true, length = 1)
    public String getbSfmcxs() {
        return bSfmcxs;
    }

    public void setbSfmcxs(String bSfmcxs) {
        this.bSfmcxs = bSfmcxs;
    }


    @Basic
    @javax.persistence.Column(name = "b_gllb_b", nullable = true, length = 128)
    public String getbGllbB() {
        return bGllbB;
    }

    public void setbGllbB(String bGllbB) {
        this.bGllbB = bGllbB;
    }


    @Basic
    @javax.persistence.Column(name = "b_gllb_a", nullable = true, length = 128)
    public String getbGllbA() {
        return bGllbA;
    }

    public void setbGllbA(String bGllbA) {
        this.bGllbA = bGllbA;
    }


    @Basic
    @javax.persistence.Column(name = "b_sftjzs", nullable = true, length = 1)
    public String getbSftjzs() {
        return bSftjzs;
    }

    public void setbSftjzs(String bSftjzs) {
        this.bSftjzs = bSftjzs;
    }


    @Basic
    @javax.persistence.Column(name = "b_bzxz_b", nullable = true, length = 128)
    public String getbBzxzB() {
        return bBzxzB;
    }

    public void setbBzxzB(String bBzxzB) {
        this.bBzxzB = bBzxzB;
    }


    @Basic
    @javax.persistence.Column(name = "b_bzxz_a", nullable = true, length = 256)
    public String getbBzxzA() {
        return bBzxzA;
    }

    public void setbBzxzA(String bBzxzA) {
        this.bBzxzA = bBzxzA;
    }


    @Basic
    @javax.persistence.Column(name = "b_czpxzw_b", nullable = true, length = 1024)
    public String getbCzpxzwB() {
        return bCzpxzwB;
    }

    public void setbCzpxzwB(String bCzpxzwB) {
        this.bCzpxzwB = bCzpxzwB;
    }


    @Basic
    @javax.persistence.Column(name = "b_czpxzw_a", nullable = true, length = 1024)
    public String getbCzpxzwA() {
        return bCzpxzwA;
    }

    public void setbCzpxzwA(String bCzpxzwA) {
        this.bCzpxzwA = bCzpxzwA;
    }


    @Basic
    @javax.persistence.Column(name = "b_zwzt_b", nullable = true, length = 64)
    public String getbZwztB() {
        return bZwztB;
    }

    public void setbZwztB(String bZwztB) {
        this.bZwztB = bZwztB;
    }


    @Basic
    @javax.persistence.Column(name = "b_zwzt_a", nullable = true, length = 128)
    public String getbZwztA() {
        return bZwztA;
    }

    public void setbZwztA(String bZwztA) {
        this.bZwztA = bZwztA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        B09 b09 = (B09) o;

        if (b0900 != null ? !b0900.equals(b09.b0900) : b09.b0900 != null) return false;
        if (b0901A != null ? !b0901A.equals(b09.b0901A) : b09.b0901A != null) return false;
        if (b0901B != null ? !b0901B.equals(b09.b0901B) : b09.b0901B != null) return false;
        if (b0904 != null ? !b0904.equals(b09.b0904) : b09.b0904 != null) return false;
        if (b0904A != null ? !b0904A.equals(b09.b0904A) : b09.b0904A != null) return false;
        if (b0907 != null ? !b0907.equals(b09.b0907) : b09.b0907 != null) return false;
        if (b0907A != null ? !b0907A.equals(b09.b0907A) : b09.b0907A != null) return false;
        if (b0911 != null ? !b0911.equals(b09.b0911) : b09.b0911 != null) return false;
        if (b0914 != null ? !b0914.equals(b09.b0914) : b09.b0914 != null) return false;
        if (b0917 != null ? !b0917.equals(b09.b0917) : b09.b0917 != null) return false;
        if (b0921 != null ? !b0921.equals(b09.b0921) : b09.b0921 != null) return false;
        if (bPx != null ? !bPx.equals(b09.bPx) : b09.bPx != null) return false;
        if (bDwzwmc != null ? !bDwzwmc.equals(b09.bDwzwmc) : b09.bDwzwmc != null) return false;
        if (bZsglmc != null ? !bZsglmc.equals(b09.bZsglmc) : b09.bZsglmc != null) return false;
        if (bSfjr != null ? !bSfjr.equals(b09.bSfjr) : b09.bSfjr != null) return false;
        if (bSfbh != null ? !bSfbh.equals(b09.bSfbh) : b09.bSfbh != null) return false;
        if (bSfzb != null ? !bSfzb.equals(b09.bSfzb) : b09.bSfzb != null) return false;
        if (bSfldzw != null ? !bSfldzw.equals(b09.bSfldzw) : b09.bSfldzw != null) return false;
        if (bSfbzcy != null ? !bSfbzcy.equals(b09.bSfbzcy) : b09.bSfbzcy != null) return false;
        if (bSfsy != null ? !bSfsy.equals(b09.bSfsy) : b09.bSfsy != null) return false;
        if (bSysc != null ? !bSysc.equals(b09.bSysc) : b09.bSysc != null) return false;
        if (bSyscdw != null ? !bSyscdw.equals(b09.bSyscdw) : b09.bSyscdw != null) return false;
        if (bTxnl != null ? !bTxnl.equals(b09.bTxnl) : b09.bTxnl != null) return false;
        if (bSfdzj != null ? !bSfdzj.equals(b09.bSfdzj) : b09.bSfdzj != null) return false;
        if (bSfmcxs != null ? !bSfmcxs.equals(b09.bSfmcxs) : b09.bSfmcxs != null) return false;
        if (bGllbB != null ? !bGllbB.equals(b09.bGllbB) : b09.bGllbB != null) return false;
        if (bGllbA != null ? !bGllbA.equals(b09.bGllbA) : b09.bGllbA != null) return false;
        if (bSftjzs != null ? !bSftjzs.equals(b09.bSftjzs) : b09.bSftjzs != null) return false;
        if (bBzxzB != null ? !bBzxzB.equals(b09.bBzxzB) : b09.bBzxzB != null) return false;
        if (bBzxzA != null ? !bBzxzA.equals(b09.bBzxzA) : b09.bBzxzA != null) return false;
        if (bCzpxzwB != null ? !bCzpxzwB.equals(b09.bCzpxzwB) : b09.bCzpxzwB != null) return false;
        if (bCzpxzwA != null ? !bCzpxzwA.equals(b09.bCzpxzwA) : b09.bCzpxzwA != null) return false;
        if (bZwztB != null ? !bZwztB.equals(b09.bZwztB) : b09.bZwztB != null) return false;
        if (bZwztA != null ? !bZwztA.equals(b09.bZwztA) : b09.bZwztA != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = b0900 != null ? b0900.hashCode() : 0;
        result = 31 * result + (b0901A != null ? b0901A.hashCode() : 0);
        result = 31 * result + (b0901B != null ? b0901B.hashCode() : 0);
        result = 31 * result + (b0904 != null ? b0904.hashCode() : 0);
        result = 31 * result + (b0904A != null ? b0904A.hashCode() : 0);
        result = 31 * result + (b0907 != null ? b0907.hashCode() : 0);
        result = 31 * result + (b0907A != null ? b0907A.hashCode() : 0);
        result = 31 * result + (b0911 != null ? b0911.hashCode() : 0);
        result = 31 * result + (b0914 != null ? b0914.hashCode() : 0);
        result = 31 * result + (b0917 != null ? b0917.hashCode() : 0);
        result = 31 * result + (b0921 != null ? b0921.hashCode() : 0);
        result = 31 * result + (bPx != null ? bPx.hashCode() : 0);
        result = 31 * result + (bDwzwmc != null ? bDwzwmc.hashCode() : 0);
        result = 31 * result + (bZsglmc != null ? bZsglmc.hashCode() : 0);
        result = 31 * result + (bSfjr != null ? bSfjr.hashCode() : 0);
        result = 31 * result + (bSfbh != null ? bSfbh.hashCode() : 0);
        result = 31 * result + (bSfzb != null ? bSfzb.hashCode() : 0);
        result = 31 * result + (bSfldzw != null ? bSfldzw.hashCode() : 0);
        result = 31 * result + (bSfbzcy != null ? bSfbzcy.hashCode() : 0);
        result = 31 * result + (bSfsy != null ? bSfsy.hashCode() : 0);
        result = 31 * result + (bSysc != null ? bSysc.hashCode() : 0);
        result = 31 * result + (bSyscdw != null ? bSyscdw.hashCode() : 0);
        result = 31 * result + (bTxnl != null ? bTxnl.hashCode() : 0);
        result = 31 * result + (bSfdzj != null ? bSfdzj.hashCode() : 0);
        result = 31 * result + (bSfmcxs != null ? bSfmcxs.hashCode() : 0);
        result = 31 * result + (bGllbB != null ? bGllbB.hashCode() : 0);
        result = 31 * result + (bGllbA != null ? bGllbA.hashCode() : 0);
        result = 31 * result + (bSftjzs != null ? bSftjzs.hashCode() : 0);
        result = 31 * result + (bBzxzB != null ? bBzxzB.hashCode() : 0);
        result = 31 * result + (bBzxzA != null ? bBzxzA.hashCode() : 0);
        result = 31 * result + (bCzpxzwB != null ? bCzpxzwB.hashCode() : 0);
        result = 31 * result + (bCzpxzwA != null ? bCzpxzwA.hashCode() : 0);
        result = 31 * result + (bZwztB != null ? bZwztB.hashCode() : 0);
        result = 31 * result + (bZwztA != null ? bZwztA.hashCode() : 0);
        return result;
    }
}
