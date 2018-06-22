/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Marco {854476391@qq.com}
 */
@Entity
@Table(name = "e_cysq")
public class ECysq extends TenantEntity implements Serializable {
    private String id;
    private EApplyE01Z8 applyE01Z8;//外键
    private String sqzt;//授权状态0：拒绝1：同意
    private String sqcysc;//授权查阅时长（分钟）
    private String sqr;//授权人
    private String sfyxxz;//是否允许下载0：否1：是
    private String sfyxdy;//是否允许打印0：否1：是
    private String sqcyml;//授权查阅目录
    private String sqclfw;//授权材料范围类型。0：授权所有档案材料；1：授权指定的档案材料
    private String sqbz;//授权备注
    private List<EPopedomE01Z1Relation> popedomE01Z1Relations;

    @OneToMany(mappedBy = "eCysq", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<EPopedomE01Z1Relation> getPopedomE01Z1Relations() {
        return popedomE01Z1Relations;
    }

    public void setPopedomE01Z1Relations(List<EPopedomE01Z1Relation> popedomE01Z1Relations) {
        this.popedomE01Z1Relations = popedomE01Z1Relations;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "e01z8_id")
    public EApplyE01Z8 getApplyE01Z8() {
        return applyE01Z8;
    }

    public void setApplyE01Z8(EApplyE01Z8 applyE01Z8) {
        this.applyE01Z8 = applyE01Z8;
    }


    @Basic
    @Column(name = "sqzt", nullable = true, length = 8)
    public String getSqzt() {
        return sqzt;
    }

    public void setSqzt(String sqzt) {
        this.sqzt = sqzt;
    }

    @Basic
    @Column(name = "sqcysc", nullable = true, length = 64)
    public String getSqcysc() {
        return sqcysc;
    }

    public void setSqcysc(String sqcysc) {
        this.sqcysc = sqcysc;
    }

    @Basic
    @Column(name = "sqr", nullable = true, length = 60)
    public String getSqr() {
        return sqr;
    }

    public void setSqr(String sqr) {
        this.sqr = sqr;
    }

    @Basic
    @Column(name = "sfyxxz", nullable = true, length = 8)
    public String getSfyxxz() {
        return sfyxxz;
    }

    public void setSfyxxz(String sfyxxz) {
        this.sfyxxz = sfyxxz;
    }

    @Basic
    @Column(name = "sfyxdy", nullable = true, length = 8)
    public String getSfyxdy() {
        return sfyxdy;
    }

    public void setSfyxdy(String sfyxdy) {
        this.sfyxdy = sfyxdy;
    }

    @Basic
    @Column(name = "sqcyml", nullable = true, length = 1024)
    public String getSqcyml() {
        return sqcyml;
    }

    public void setSqcyml(String sqcyml) {
        this.sqcyml = sqcyml;
    }

    @Basic
    @Column(name = "sqclfw", nullable = true, length = 128)
    public String getSqclfw() {
        return sqclfw;
    }

    public void setSqclfw(String sqclfw) {
        this.sqclfw = sqclfw;
    }

    @Basic
    @Column(name = "sqbz", nullable = true, length = 1024)
    public String getSqbz() {
        return sqbz;
    }

    public void setSqbz(String sqbz) {
        this.sqbz = sqbz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ECysq eCysq = (ECysq) o;

        if (id != null ? !id.equals(eCysq.id) : eCysq.id != null) return false;
        if (sqzt != null ? !sqzt.equals(eCysq.sqzt) : eCysq.sqzt != null) return false;
        if (sqcysc != null ? !sqcysc.equals(eCysq.sqcysc) : eCysq.sqcysc != null) return false;
        if (sqr != null ? !sqr.equals(eCysq.sqr) : eCysq.sqr != null) return false;
        if (sfyxxz != null ? !sfyxxz.equals(eCysq.sfyxxz) : eCysq.sfyxxz != null) return false;
        if (sqcyml != null ? !sqcyml.equals(eCysq.sqcyml) : eCysq.sqcyml != null) return false;
        if (sqclfw != null ? !sqclfw.equals(eCysq.sqclfw) : eCysq.sqclfw != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sqzt != null ? sqzt.hashCode() : 0);
        result = 31 * result + (sqcysc != null ? sqcysc.hashCode() : 0);
        result = 31 * result + (sqr != null ? sqr.hashCode() : 0);
        result = 31 * result + (sfyxxz != null ? sfyxxz.hashCode() : 0);
        result = 31 * result + (sqcyml != null ? sqcyml.hashCode() : 0);
        result = 31 * result + (sqclfw != null ? sqclfw.hashCode() : 0);
        return result;
    }
}
