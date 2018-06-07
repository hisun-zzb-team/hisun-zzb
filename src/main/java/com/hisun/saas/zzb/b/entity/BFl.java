/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.b.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author liuzj {279421824@qq.com}
 */
@Entity
@Table(name = "b_fl")
public class BFl extends TenantEntity implements Serializable {
    private String id;//主键
    private String fl;//分类名称
    private Integer px;//排序
    private BFl parentBFl;//上级分类
    private List<BFl> childFls;
    private List<BFl2B01> bFl2B01s;
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
    @OneToMany(mappedBy = "parentBFl", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<BFl> getChildFls() {
        return childFls;
    }

    public void setChildFls(List<BFl> childFls) {
        this.childFls = childFls;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    public BFl getParentBFl() {
        return parentBFl;
    }

    public void setParentBFl(BFl parentBFl) {
        this.parentBFl = parentBFl;
    }
    @OneToMany(mappedBy = "bfl", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<BFl2B01> getbFl2B01s() {
        return bFl2B01s;
    }

    public void setbFl2B01s(List<BFl2B01> bFl2B01s) {
        this.bFl2B01s = bFl2B01s;
    }

    @Basic
    @Column(name = "fl", nullable = false, length = 32)
    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
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

        BFl bFl = (BFl) o;

        if (id != null ? !id.equals(bFl.id) : bFl.id != null) return false;
        if (fl != null ? !fl.equals(bFl.fl) : bFl.fl != null) return false;
        if (px != null ? !px.equals(bFl.px) : bFl.px != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fl != null ? fl.hashCode() : 0);
        result = 31 * result + (px != null ? px.hashCode() : 0);
        return result;
    }
}
