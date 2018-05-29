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
@Table(name = "b04")
public class B04 extends TombstoneEntity implements Serializable {
    private String b0400;
    private String b0401;
    private String b0404;
    private String b0407;
    private String b0411;
    private String b0417;
    private String b0421;
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
    @Column(name = "b0400", nullable = false, unique = true, length = 32)
    public String getB0400() {
        return b0400;
    }

    public void setB0400(String b0400) {
        this.b0400 = b0400;
    }

    @Basic
    @Column(name = "b0401", nullable = true, length = 12)
    public String getB0401() {
        return b0401;
    }

    public void setB0401(String b0401) {
        this.b0401 = b0401;
    }

    @Basic
    @Column(name = "b0404", nullable = true, length = 512)
    public String getB0404() {
        return b0404;
    }

    public void setB0404(String b0404) {
        this.b0404 = b0404;
    }

    @Basic
    @Column(name = "b0407", nullable = true, length = 2000)
    public String getB0407() {
        return b0407;
    }

    public void setB0407(String b0407) {
        this.b0407 = b0407;
    }

    @Basic
    @Column(name = "b0411", nullable = true, length = 2000)
    public String getB0411() {
        return b0411;
    }

    public void setB0411(String b0411) {
        this.b0411 = b0411;
    }

    @Basic
    @Column(name = "b0417", nullable = true, length = 2000)
    public String getB0417() {
        return b0417;
    }

    public void setB0417(String b0417) {
        this.b0417 = b0417;
    }

    @Basic
    @Column(name = "b0421", nullable = true, length = 2000)
    public String getB0421() {
        return b0421;
    }

    public void setB0421(String b0421) {
        this.b0421 = b0421;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        B04 b04 = (B04) o;

        if (b0400 != null ? !b0400.equals(b04.b0400) : b04.b0400 != null) return false;
        if (b0401 != null ? !b0401.equals(b04.b0401) : b04.b0401 != null) return false;
        if (b0404 != null ? !b0404.equals(b04.b0404) : b04.b0404 != null) return false;
        if (b0407 != null ? !b0407.equals(b04.b0407) : b04.b0407 != null) return false;
        if (b0411 != null ? !b0411.equals(b04.b0411) : b04.b0411 != null) return false;
        if (b0417 != null ? !b0417.equals(b04.b0417) : b04.b0417 != null) return false;
        if (b0421 != null ? !b0421.equals(b04.b0421) : b04.b0421 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = b0400 != null ? b0400.hashCode() : 0;
        result = 31 * result + (b0401 != null ? b0401.hashCode() : 0);
        result = 31 * result + (b0404 != null ? b0404.hashCode() : 0);
        result = 31 * result + (b0407 != null ? b0407.hashCode() : 0);
        result = 31 * result + (b0411 != null ? b0411.hashCode() : 0);
        result = 31 * result + (b0417 != null ? b0417.hashCode() : 0);
        result = 31 * result + (b0421 != null ? b0421.hashCode() : 0);
        return result;
    }
}
