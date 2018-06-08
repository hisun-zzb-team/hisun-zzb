/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.b.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Marco {854476391@qq.com}
 */
@Entity
@Table(name = "b_b09_bh_jr_info")
public class BB09BhJrInfo implements Serializable {
    private String id;
    private B09 b09;//当前职务
    private B01 b01;//当前机构
    private int sfbdwzw;//是否本单位职务 1：是 0：否
    private String b0901A;//关联的职务名称
    private String glB0900;//关联的职务id
    private Integer lx;//类型 1：兼任 2：包含
    private Integer zs;//职数

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
    @JoinColumn(name = "b0900")
    public B09 getB09() {
        return b09;
    }

    public void setB09(B09 b09) {
        this.b09 = b09;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b0100")
    public B01 getB01() {
        return b01;
    }

    public void setB01(B01 b01) {
        this.b01 = b01;
    }

    @Basic
    @Column(name = "sfbdwzw", nullable = false)
    public int getSfbdwzw() {
        return sfbdwzw;
    }

    public void setSfbdwzw(int sfbdwzw) {
        this.sfbdwzw = sfbdwzw;
    }

    @Basic
    @Column(name = "gl_b0900", nullable = false, length = 32)
    public String getGlB0900() {
        return glB0900;
    }

    public void setGlB0900(String glB0900) {
        this.glB0900 = glB0900;
    }

    @Basic
    @Column(name = "b0901a", nullable = true, length = 256)
    public String getB0901A() {
        return b0901A;
    }

    public void setB0901A(String b0901A) {
        this.b0901A = b0901A;
    }

    @Basic
    @Column(name = "lx", nullable = true)
    public Integer getLx() {
        return lx;
    }

    public void setLx(Integer lx) {
        this.lx = lx;
    }

    @Basic
    @Column(name = "zs", nullable = true)
    public Integer getZs() {
        return zs;
    }

    public void setZs(Integer zs) {
        this.zs = zs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BB09BhJrInfo that = (BB09BhJrInfo) o;

        if (sfbdwzw != that.sfbdwzw) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (b09 != null ? !b09.equals(that.b09) : that.b09 != null) return false;
        if (b01 != null ? !b01.equals(that.b01) : that.b01 != null) return false;
        if (glB0900 != null ? !glB0900.equals(that.glB0900) : that.glB0900 != null) return false;
        if (b0901A != null ? !b0901A.equals(that.b0901A) : that.b0901A != null) return false;
        if (lx != null ? !lx.equals(that.lx) : that.lx != null) return false;
        if (zs != null ? !zs.equals(that.zs) : that.zs != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (b09 != null ? b09.hashCode() : 0);
        result = 31 * result + (b01 != null ? b01.hashCode() : 0);
        result = 31 * result + sfbdwzw;
        result = 31 * result + (glB0900 != null ? glB0900.hashCode() : 0);
        result = 31 * result + (b0901A != null ? b0901A.hashCode() : 0);
        result = 31 * result + (lx != null ? lx.hashCode() : 0);
        result = 31 * result + (zs != null ? zs.hashCode() : 0);
        return result;
    }
}
