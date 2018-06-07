/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.b.entity;

import javax.persistence.*;

/**
 * @author Marco {854476391@qq.com}
 */
@Entity
@Table(name = "b_b09_bh_jr_info")
public class BB09BhJrInfo {
    private String id;
    private String b0900;
    private String b0100;
    private int sfbdwzw;
    private String glB0900;
    private String b0901A;
    private Integer lx;
    private Integer zs;

    @Id
    @Column(name = "id", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "b0900", nullable = false, length = 32)
    public String getB0900() {
        return b0900;
    }

    public void setB0900(String b0900) {
        this.b0900 = b0900;
    }

    @Basic
    @Column(name = "b0100", nullable = false, length = 32)
    public String getB0100() {
        return b0100;
    }

    public void setB0100(String b0100) {
        this.b0100 = b0100;
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
        if (b0900 != null ? !b0900.equals(that.b0900) : that.b0900 != null) return false;
        if (b0100 != null ? !b0100.equals(that.b0100) : that.b0100 != null) return false;
        if (glB0900 != null ? !glB0900.equals(that.glB0900) : that.glB0900 != null) return false;
        if (b0901A != null ? !b0901A.equals(that.b0901A) : that.b0901A != null) return false;
        if (lx != null ? !lx.equals(that.lx) : that.lx != null) return false;
        if (zs != null ? !zs.equals(that.zs) : that.zs != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (b0900 != null ? b0900.hashCode() : 0);
        result = 31 * result + (b0100 != null ? b0100.hashCode() : 0);
        result = 31 * result + sfbdwzw;
        result = 31 * result + (glB0900 != null ? glB0900.hashCode() : 0);
        result = 31 * result + (b0901A != null ? b0901A.hashCode() : 0);
        result = 31 * result + (lx != null ? lx.hashCode() : 0);
        result = 31 * result + (zs != null ? zs.hashCode() : 0);
        return result;
    }
}
