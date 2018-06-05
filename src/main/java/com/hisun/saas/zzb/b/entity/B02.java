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
@Table(name = "b02")
public class B02 extends TombstoneEntity implements Serializable {
    /** 编制情况信息id */
    private String b0200;

    /** 编制批准日期 */
    private String b0201;

    /** 编制批准文号 */
    private String b0204;

    /** 编制文号,关联文件库 */
    private String b0204A;

    /** 编制批准机关名称 */
    private String b0207A;

    /** 编制批准机关代码ZB02-2006/JGMC */
    private String b0207B;

    /** 内设单位数 */
    private Integer b0211;

    /** 内设单位名称 */
    private String b0214;

    /** 内设单位级别 */
    private String b0217;

    /** 编制性质ZB61-2006/BZXZ */
    private String b0221;

    /** 编制性质名称 */
    private String b0221A;

    /** 批准编制总数 */
    private Integer b0224;

    /** 行政编制数 */
    private Integer b0227;

    /** 事业编制数 */
    private Integer b0231;

    /** 企业编制数 */
    private Integer b0234;

    /** 社团编制数 */
    private Integer b0237;

    /** 单位领导职数 */
    private Integer b0241;

    /** 领导同级非领导职务职数 */
    private Integer b0244;

    /** 内设第一级单位领导职数 */
    private Integer b0247;

    /** 内设第一级单位领导同级非领导职数 */
    private Integer b0251;

    /** 业务人员编制数 */
    private Integer b0254;

    /** 工勤人员编制数 */
    private Integer b0257;

    /** 使用全额拨款人数 */
    private Integer b0261;

    /** 使用差额拨款人数 */
    private Integer b0264;

    /** 使用自筹资金人数 */
    private Integer b0267;

    /** 编制摘要说明 */
    private String bBzzysm;
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
    @Column(name = "b0200", nullable = false, unique = true, length = 32)
    public String getB0200() {
        return b0200;
    }

    public void setB0200(String b0200) {
        this.b0200 = b0200;
    }


    @Basic
    @javax.persistence.Column(name = "b0201", nullable = true, length = 8)
    public String getB0201() {
        return b0201;
    }

    public void setB0201(String b0201) {
        this.b0201 = b0201;
    }


    @Basic
    @javax.persistence.Column(name = "b0204", nullable = true, length = 500)
    public String getB0204() {
        return b0204;
    }

    public void setB0204(String b0204) {
        this.b0204 = b0204;
    }


    @Basic
    @javax.persistence.Column(name = "b0204a", nullable = true, length = 320)
    public String getB0204A() {
        return b0204A;
    }

    public void setB0204A(String b0204A) {
        this.b0204A = b0204A;
    }


    @Basic
    @javax.persistence.Column(name = "b0207a", nullable = true, length = 240)
    public String getB0207A() {
        return b0207A;
    }

    public void setB0207A(String b0207A) {
        this.b0207A = b0207A;
    }


    @Basic
    @javax.persistence.Column(name = "b0207b", nullable = true, length = 64)
    public String getB0207B() {
        return b0207B;
    }

    public void setB0207B(String b0207B) {
        this.b0207B = b0207B;
    }


    @Basic
    @javax.persistence.Column(name = "b0211", nullable = true, precision = 0)
    public Integer getB0211() {
        return b0211;
    }

    public void setB0211(Integer b0211) {
        this.b0211 = b0211;
    }


    @Basic
    @javax.persistence.Column(name = "b0214", nullable = true, length = 4000)
    public String getB0214() {
        return b0214;
    }

    public void setB0214(String b0214) {
        this.b0214 = b0214;
    }


    @Basic
    @javax.persistence.Column(name = "b0217", nullable = true, length = 3)
    public String getB0217() {
        return b0217;
    }

    public void setB0217(String b0217) {
        this.b0217 = b0217;
    }


    @Basic
    @javax.persistence.Column(name = "b0221", nullable = true, length = 128)
    public String getB0221() {
        return b0221;
    }

    public void setB0221(String b0221) {
        this.b0221 = b0221;
    }


    @Basic
    @javax.persistence.Column(name = "b0221a", nullable = true, length = 256)
    public String getB0221A() {
        return b0221A;
    }

    public void setB0221A(String b0221A) {
        this.b0221A = b0221A;
    }


    @Basic
    @javax.persistence.Column(name = "b0224", nullable = true, precision = 0)
    public Integer getB0224() {
        return b0224;
    }

    public void setB0224(Integer b0224) {
        this.b0224 = b0224;
    }


    @Basic
    @javax.persistence.Column(name = "b0227", nullable = true, precision = 0)
    public Integer getB0227() {
        return b0227;
    }

    public void setB0227(Integer b0227) {
        this.b0227 = b0227;
    }


    @Basic
    @javax.persistence.Column(name = "b0231", nullable = true, precision = 0)
    public Integer getB0231() {
        return b0231;
    }

    public void setB0231(Integer b0231) {
        this.b0231 = b0231;
    }


    @Basic
    @javax.persistence.Column(name = "b0234", nullable = true, precision = 0)
    public Integer getB0234() {
        return b0234;
    }

    public void setB0234(Integer b0234) {
        this.b0234 = b0234;
    }


    @Basic
    @javax.persistence.Column(name = "b0237", nullable = true, precision = 0)
    public Integer getB0237() {
        return b0237;
    }

    public void setB0237(Integer b0237) {
        this.b0237 = b0237;
    }


    @Basic
    @javax.persistence.Column(name = "b0241", nullable = true, precision = 0)
    public Integer getB0241() {
        return b0241;
    }

    public void setB0241(Integer b0241) {
        this.b0241 = b0241;
    }


    @Basic
    @javax.persistence.Column(name = "b0244", nullable = true, precision = 0)
    public Integer getB0244() {
        return b0244;
    }

    public void setB0244(Integer b0244) {
        this.b0244 = b0244;
    }


    @Basic
    @javax.persistence.Column(name = "b0247", nullable = true, precision = 0)
    public Integer getB0247() {
        return b0247;
    }

    public void setB0247(Integer b0247) {
        this.b0247 = b0247;
    }


    @Basic
    @javax.persistence.Column(name = "b0251", nullable = true, precision = 0)
    public Integer getB0251() {
        return b0251;
    }

    public void setB0251(Integer b0251) {
        this.b0251 = b0251;
    }


    @Basic
    @javax.persistence.Column(name = "b0254", nullable = true, precision = 0)
    public Integer getB0254() {
        return b0254;
    }

    public void setB0254(Integer b0254) {
        this.b0254 = b0254;
    }


    @Basic
    @javax.persistence.Column(name = "b0257", nullable = true, precision = 0)
    public Integer getB0257() {
        return b0257;
    }

    public void setB0257(Integer b0257) {
        this.b0257 = b0257;
    }


    @Basic
    @javax.persistence.Column(name = "b0261", nullable = true, precision = 0)
    public Integer getB0261() {
        return b0261;
    }

    public void setB0261(Integer b0261) {
        this.b0261 = b0261;
    }


    @Basic
    @javax.persistence.Column(name = "b0264", nullable = true, precision = 0)
    public Integer getB0264() {
        return b0264;
    }

    public void setB0264(Integer b0264) {
        this.b0264 = b0264;
    }


    @Basic
    @javax.persistence.Column(name = "b0267", nullable = true, precision = 0)
    public Integer getB0267() {
        return b0267;
    }

    public void setB0267(Integer b0267) {
        this.b0267 = b0267;
    }

    @Basic
    @javax.persistence.Column(name = "b_bzzysm", nullable = true, length = 2000)
    public String getbBzzysm() {
        return bBzzysm;
    }

    public void setbBzzysm(String bBzzysm) {
        this.bBzzysm = bBzzysm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        B02 b02 = (B02) o;

        if (b0200 != null ? !b0200.equals(b02.b0200) : b02.b0200 != null) return false;

        if (b0201 != null ? !b0201.equals(b02.b0201) : b02.b0201 != null) return false;
        if (b0204 != null ? !b0204.equals(b02.b0204) : b02.b0204 != null) return false;
        if (b0204A != null ? !b0204A.equals(b02.b0204A) : b02.b0204A != null) return false;
        if (b0207A != null ? !b0207A.equals(b02.b0207A) : b02.b0207A != null) return false;
        if (b0207B != null ? !b0207B.equals(b02.b0207B) : b02.b0207B != null) return false;
        if (b0211 != null ? !b0211.equals(b02.b0211) : b02.b0211 != null) return false;
        if (b0214 != null ? !b0214.equals(b02.b0214) : b02.b0214 != null) return false;
        if (b0217 != null ? !b0217.equals(b02.b0217) : b02.b0217 != null) return false;
        if (b0221 != null ? !b0221.equals(b02.b0221) : b02.b0221 != null) return false;
        if (b0221A != null ? !b0221A.equals(b02.b0221A) : b02.b0221A != null) return false;
        if (b0224 != null ? !b0224.equals(b02.b0224) : b02.b0224 != null) return false;
        if (b0227 != null ? !b0227.equals(b02.b0227) : b02.b0227 != null) return false;
        if (b0231 != null ? !b0231.equals(b02.b0231) : b02.b0231 != null) return false;
        if (b0234 != null ? !b0234.equals(b02.b0234) : b02.b0234 != null) return false;
        if (b0237 != null ? !b0237.equals(b02.b0237) : b02.b0237 != null) return false;
        if (b0241 != null ? !b0241.equals(b02.b0241) : b02.b0241 != null) return false;
        if (b0244 != null ? !b0244.equals(b02.b0244) : b02.b0244 != null) return false;
        if (b0247 != null ? !b0247.equals(b02.b0247) : b02.b0247 != null) return false;
        if (b0251 != null ? !b0251.equals(b02.b0251) : b02.b0251 != null) return false;
        if (b0254 != null ? !b0254.equals(b02.b0254) : b02.b0254 != null) return false;
        if (b0257 != null ? !b0257.equals(b02.b0257) : b02.b0257 != null) return false;
        if (b0261 != null ? !b0261.equals(b02.b0261) : b02.b0261 != null) return false;
        if (b0264 != null ? !b0264.equals(b02.b0264) : b02.b0264 != null) return false;
        if (b0267 != null ? !b0267.equals(b02.b0267) : b02.b0267 != null) return false;
        if (bBzzysm != null ? !bBzzysm.equals(b02.bBzzysm) : b02.bBzzysm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = b0200 != null ? b0200.hashCode() : 0;

        result = 31 * result + (b0201 != null ? b0201.hashCode() : 0);
        result = 31 * result + (b0204 != null ? b0204.hashCode() : 0);
        result = 31 * result + (b0204A != null ? b0204A.hashCode() : 0);
        result = 31 * result + (b0207A != null ? b0207A.hashCode() : 0);
        result = 31 * result + (b0207B != null ? b0207B.hashCode() : 0);
        result = 31 * result + (b0211 != null ? b0211.hashCode() : 0);
        result = 31 * result + (b0214 != null ? b0214.hashCode() : 0);
        result = 31 * result + (b0217 != null ? b0217.hashCode() : 0);
        result = 31 * result + (b0221 != null ? b0221.hashCode() : 0);
        result = 31 * result + (b0221A != null ? b0221A.hashCode() : 0);
        result = 31 * result + (b0224 != null ? b0224.hashCode() : 0);
        result = 31 * result + (b0227 != null ? b0227.hashCode() : 0);
        result = 31 * result + (b0231 != null ? b0231.hashCode() : 0);
        result = 31 * result + (b0234 != null ? b0234.hashCode() : 0);
        result = 31 * result + (b0237 != null ? b0237.hashCode() : 0);
        result = 31 * result + (b0241 != null ? b0241.hashCode() : 0);
        result = 31 * result + (b0244 != null ? b0244.hashCode() : 0);
        result = 31 * result + (b0247 != null ? b0247.hashCode() : 0);
        result = 31 * result + (b0251 != null ? b0251.hashCode() : 0);
        result = 31 * result + (b0254 != null ? b0254.hashCode() : 0);
        result = 31 * result + (b0257 != null ? b0257.hashCode() : 0);
        result = 31 * result + (b0261 != null ? b0261.hashCode() : 0);
        result = 31 * result + (b0264 != null ? b0264.hashCode() : 0);
        result = 31 * result + (b0267 != null ? b0267.hashCode() : 0);
        result = 31 * result + (bBzzysm != null ? bBzzysm.hashCode() : 0);
        return result;
    }
}
