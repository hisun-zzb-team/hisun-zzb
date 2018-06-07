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
@Table(name = "b_fl_2_b01")
public class BFl2B01 implements Serializable {
    private String id;//主键
    private Integer px;//排序
    private BFl bfl;//分类id
    private B01 b01;//机构id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b0100")
    public B01 getB01() {
        return b01;
    }

    public void setB01(B01 b01) {
        this.b01 = b01;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fl_id")
    public BFl getBfl() {
        return bfl;
    }

    public void setBfl(BFl bfl) {
        this.bfl = bfl;
    }

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




    @Basic
    @Column(name = "px", nullable = true)
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

        BFl2B01 bFl2B01 = (BFl2B01) o;

        if (id != null ? !id.equals(bFl2B01.id) : bFl2B01.id != null) return false;
        if (px != null ? !px.equals(bFl2B01.px) : bFl2B01.px != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (px != null ? px.hashCode() : 0);
        return result;
    }
}
