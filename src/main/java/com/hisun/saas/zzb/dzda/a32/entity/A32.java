/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a32.entity;

import com.hisun.base.entity.TombstoneEntity;
import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author liuzj {279421824@qq.com}
 */
@Entity
@Table(name = "a32")
public class A32 extends TenantEntity implements Serializable {
    private String id;//工资变动id
    private A38 a38;//外键，人员档案主健
    private String gzbm;//工作部门
    private String zwmc;//职务名称
    private String a3224;//执行的职务工资标准
    private String a3234;//职务工资额
    private String a3207;//批准日期
    private String a3204;//批准机关
    private String a3211;//批准文电号
    private Integer px;//工资变动顺序号

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false, unique = true, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "a38_id")
    public A38 getA38() {
        return a38;
    }

    public void setA38(A38 a38) {
        this.a38 = a38;
    }

    @Basic
    @Column(name = "gzbm")
    public String getGzbm() {
        return gzbm;
    }

    public void setGzbm(String gzbm) {
        this.gzbm = gzbm;
    }

    @Basic
    @Column(name = "zwmc")
    public String getZwmc() {
        return zwmc;
    }

    public void setZwmc(String zwmc) {
        this.zwmc = zwmc;
    }

    @Basic
    @Column(name = "a3224")
    public String getA3224() {
        return a3224;
    }

    public void setA3224(String a3224) {
        this.a3224 = a3224;
    }

    @Basic
    @Column(name = "a3234")
    public String getA3234() {
        return a3234;
    }

    public void setA3234(String a3234) {
        this.a3234 = a3234;
    }

    @Basic
    @Column(name = "a3207")
    public String getA3207() {
        return a3207;
    }

    public void setA3207(String a3207) {
        this.a3207 = a3207;
    }

    @Basic
    @Column(name = "a3204")
    public String getA3204() {
        return a3204;
    }

    public void setA3204(String a3204) {
        this.a3204 = a3204;
    }

    @Basic
    @Column(name = "a3211")
    public String getA3211() {
        return a3211;
    }

    public void setA3211(String a3211) {
        this.a3211 = a3211;
    }

    @Basic
    @Column(name = "px")
    public Integer getPx() {
        return px;
    }

    public void setPx(Integer px) {
        this.px = px;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        A32 a32 = (A32) o;

        if (id != null ? !id.equals(a32.id) : a32.id != null) return false;
        if (gzbm != null ? !gzbm.equals(a32.gzbm) : a32.gzbm != null) return false;
        if (zwmc != null ? !zwmc.equals(a32.zwmc) : a32.zwmc != null) return false;
        if (a3224 != null ? !a3224.equals(a32.a3224) : a32.a3224 != null) return false;
        if (a3234 != null ? !a3234.equals(a32.a3234) : a32.a3234 != null) return false;
        if (a3207 != null ? !a3207.equals(a32.a3207) : a32.a3207 != null) return false;
        if (a3204 != null ? !a3204.equals(a32.a3204) : a32.a3204 != null) return false;
        if (a3211 != null ? !a3211.equals(a32.a3211) : a32.a3211 != null) return false;
        if (px != null ? !px.equals(a32.px) : a32.px != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (gzbm != null ? gzbm.hashCode() : 0);
        result = 31 * result + (zwmc != null ? zwmc.hashCode() : 0);
        result = 31 * result + (a3224 != null ? a3224.hashCode() : 0);
        result = 31 * result + (a3234 != null ? a3234.hashCode() : 0);
        result = 31 * result + (a3207 != null ? a3207.hashCode() : 0);
        result = 31 * result + (a3204 != null ? a3204.hashCode() : 0);
        result = 31 * result + (a3211 != null ? a3211.hashCode() : 0);
        result = 31 * result + (px != null ? px.hashCode() : 0);
        return result;
    }
}
