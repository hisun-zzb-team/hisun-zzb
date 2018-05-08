/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a52.entity;

import com.hisun.base.entity.TombstoneEntity;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author liuzj {279421824@qq.com}
 */
@Entity
@Table(name = "a52")
public class A52 extends TombstoneEntity implements Serializable {
    private String id;
    private A38 a38;//外键，人员档案主健
    private String a5204;//任职机构
    private String a5211;//职务名称
    private String a5227In;//任职时间
    private String a5227Out;//免职时间
    private String a0245;//任职文号
    private String a0267;//免职文号
    private Integer px;//职务变动排序

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
    @Column(name = "a5204")
    public String getA5204() {
        return a5204;
    }

    public void setA5204(String a5204) {
        this.a5204 = a5204;
    }

    @Basic
    @Column(name = "a5211")
    public String getA5211() {
        return a5211;
    }

    public void setA5211(String a5211) {
        this.a5211 = a5211;
    }

    @Basic
    @Column(name = "a5227_in")
    public String getA5227In() {
        return a5227In;
    }

    public void setA5227In(String a5227In) {
        this.a5227In = a5227In;
    }

    @Basic
    @Column(name = "a5227_out")
    public String getA5227Out() {
        return a5227Out;
    }

    public void setA5227Out(String a5227Out) {
        this.a5227Out = a5227Out;
    }

    @Basic
    @Column(name = "a0245")
    public String getA0245() {
        return a0245;
    }

    public void setA0245(String a0245) {
        this.a0245 = a0245;
    }

    @Basic
    @Column(name = "a0267")
    public String getA0267() {
        return a0267;
    }

    public void setA0267(String a0267) {
        this.a0267 = a0267;
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

        A52 a52 = (A52) o;

        if (id != null ? !id.equals(a52.id) : a52.id != null) return false;
        if (a5204 != null ? !a5204.equals(a52.a5204) : a52.a5204 != null) return false;
        if (a5211 != null ? !a5211.equals(a52.a5211) : a52.a5211 != null) return false;
        if (a5227In != null ? !a5227In.equals(a52.a5227In) : a52.a5227In != null) return false;
        if (a5227Out != null ? !a5227Out.equals(a52.a5227Out) : a52.a5227Out != null) return false;
        if (a0245 != null ? !a0245.equals(a52.a0245) : a52.a0245 != null) return false;
        if (a0267 != null ? !a0267.equals(a52.a0267) : a52.a0267 != null) return false;
        if (px != null ? !px.equals(a52.px) : a52.px != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (a5204 != null ? a5204.hashCode() : 0);
        result = 31 * result + (a5211 != null ? a5211.hashCode() : 0);
        result = 31 * result + (a5227In != null ? a5227In.hashCode() : 0);
        result = 31 * result + (a5227Out != null ? a5227Out.hashCode() : 0);
        result = 31 * result + (a0245 != null ? a0245.hashCode() : 0);
        result = 31 * result + (a0267 != null ? a0267.hashCode() : 0);
        result = 31 * result + (px != null ? px.hashCode() : 0);
        return result;
    }
}
