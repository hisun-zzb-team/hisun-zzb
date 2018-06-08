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
@Table(name = "b10")
public class B10 extends TombstoneEntity implements Serializable {
    private String b1000;//换届信息集id
    private Integer b1001;//届次
    private String b1004;//换届日期
    private String b1007;//换届原因
    private Integer bHjnx;//换届年限
    private B01 b01;//单位信息id
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
    @Column(name = "b1000", nullable = false, unique = true, length = 32)
    public String getB1000() {
        return b1000;
    }

    public void setB1000(String b1000) {
        this.b1000 = b1000;
    }



    @Basic
    @Column(name = "b1001", nullable = true, precision = 0)
    public Integer getB1001() {
        return b1001;
    }

    public void setB1001(Integer b1001) {
        this.b1001 = b1001;
    }

    @Basic
    @Column(name = "b1004", nullable = true, length = 8)
    public String getB1004() {
        return b1004;
    }

    public void setB1004(String b1004) {
        this.b1004 = b1004;
    }

    @Basic
    @Column(name = "b1007", nullable = true, length = 4000)
    public String getB1007() {
        return b1007;
    }

    public void setB1007(String b1007) {
        this.b1007 = b1007;
    }

    @Basic
    @Column(name = "b_hjnx", nullable = true, precision = 0)
    public Integer getbHjnx() {
        return bHjnx;
    }

    public void setbHjnx(Integer bHjnx) {
        this.bHjnx = bHjnx;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        B10 b10 = (B10) o;

        if (b1000 != null ? !b1000.equals(b10.b1000) : b10.b1000 != null) return false;
        if (b1001 != null ? !b1001.equals(b10.b1001) : b10.b1001 != null) return false;
        if (b1004 != null ? !b1004.equals(b10.b1004) : b10.b1004 != null) return false;
        if (b1007 != null ? !b1007.equals(b10.b1007) : b10.b1007 != null) return false;
        if (bHjnx != null ? !bHjnx.equals(b10.bHjnx) : b10.bHjnx != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = b1000 != null ? b1000.hashCode() : 0;
        result = 31 * result + (b1001 != null ? b1001.hashCode() : 0);
        result = 31 * result + (b1004 != null ? b1004.hashCode() : 0);
        result = 31 * result + (b1007 != null ? b1007.hashCode() : 0);
        result = 31 * result + (bHjnx != null ? bHjnx.hashCode() : 0);
        return result;
    }
}
