/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.entity;

import com.hisun.base.entity.TombstoneEntity;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author liuzj {279421824@qq.com}
 */
@Entity
@Table(name = "e_a38_log")
public class EA38Log extends TombstoneEntity implements Serializable {
    private String id;
    private A38 a38;
    private Date cysj;
    private String cyrId;
    private String cyrName;
    private String a0101;
    private EApplyE01Z8 applyE01Z8;
    private String readTenantId;
    private String readTenantName;
    private String viewTime;
    private Integer ydzt;
    private Date zzcysj;
    private List<EA38LogDetail> a38LogDetails;
    private List<EA38LogViewTime> a38LogViewTimes;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_e01z8_id")
    public EApplyE01Z8 getApplyE01Z8() {
        return applyE01Z8;
    }

    public void setA38LogDetails(List<EA38LogDetail> a38LogDetails) {
        this.a38LogDetails = a38LogDetails;
    }
    @OneToMany(mappedBy = "a38Log", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<EA38LogViewTime> getA38LogViewTimes() {
        return a38LogViewTimes;
    }

    public void setA38LogViewTimes(List<EA38LogViewTime> a38LogViewTimes) {
        this.a38LogViewTimes = a38LogViewTimes;
    }

    public void setApplyE01Z8(EApplyE01Z8 applyE01Z8) {
        this.applyE01Z8 = applyE01Z8;
    }
    @OneToMany(mappedBy = "a38Log", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<EA38LogDetail> getA38LogDetails() {
        return a38LogDetails;
    }

    public void setA38LogDetail(List<EA38LogDetail> a38LogDetails) {
        this.a38LogDetails = a38LogDetails;
    }

    @Basic
    @Column(name = "cysj")
    public Date getCysj() {
        return cysj;
    }

    public void setCysj(Date cysj) {
        this.cysj = cysj;
    }

    @Basic
    @Column(name = "cyr_id")
    public String getCyrId() {
        return cyrId;
    }

    public void setCyrId(String cyrId) {
        this.cyrId = cyrId;
    }

    @Basic
    @Column(name = "cyr_name")
    public String getCyrName() {
        return cyrName;
    }

    public void setCyrName(String cyrName) {
        this.cyrName = cyrName;
    }

    @Basic
    @Column(name = "a0101")
    public String getA0101() {
        return a0101;
    }

    public void setA0101(String a0101) {
        this.a0101 = a0101;
    }



    @Basic
    @Column(name = "read_tenant_id")
    public String getReadTenantId() {
        return readTenantId;
    }

    public void setReadTenantId(String readTenantId) {
        this.readTenantId = readTenantId;
    }

    @Basic
    @Column(name = "read_tenant_name")
    public String getReadTenantName() {
        return readTenantName;
    }

    public void setReadTenantName(String readTenantName) {
        this.readTenantName = readTenantName;
    }

    @Basic
    @Column(name = "view_time")
    public String getViewTime() {
        return viewTime;
    }

    public void setViewTime(String viewTime) {
        this.viewTime = viewTime;
    }

    @Basic
    @Column(name = "ydzt")
    public Integer getYdzt() {
        return ydzt;
    }

    public void setYdzt(Integer ydzt) {
        this.ydzt = ydzt;
    }

    @Basic
    @Column(name = "zzcysj")
    public Date getZzcysj() {
        return zzcysj;
    }

    public void setZzcysj(Date zzcysj) {
        this.zzcysj = zzcysj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EA38Log ea38Log = (EA38Log) o;

        if (id != null ? !id.equals(ea38Log.id) : ea38Log.id != null) return false;
        if (cysj != null ? !cysj.equals(ea38Log.cysj) : ea38Log.cysj != null) return false;
        if (cyrId != null ? !cyrId.equals(ea38Log.cyrId) : ea38Log.cyrId != null) return false;
        if (cyrName != null ? !cyrName.equals(ea38Log.cyrName) : ea38Log.cyrName != null) return false;
        if (a0101 != null ? !a0101.equals(ea38Log.a0101) : ea38Log.a0101 != null) return false;
        if (readTenantId != null ? !readTenantId.equals(ea38Log.readTenantId) : ea38Log.readTenantId != null)
            return false;
        if (readTenantName != null ? !readTenantName.equals(ea38Log.readTenantName) : ea38Log.readTenantName != null)
            return false;
        if (viewTime != null ? !viewTime.equals(ea38Log.viewTime) : ea38Log.viewTime != null) return false;
        if (ydzt != null ? !ydzt.equals(ea38Log.ydzt) : ea38Log.ydzt != null) return false;
        if (zzcysj != null ? !zzcysj.equals(ea38Log.zzcysj) : ea38Log.zzcysj != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cysj != null ? cysj.hashCode() : 0);
        result = 31 * result + (cyrId != null ? cyrId.hashCode() : 0);
        result = 31 * result + (cyrName != null ? cyrName.hashCode() : 0);
        result = 31 * result + (a0101 != null ? a0101.hashCode() : 0);
        result = 31 * result + (readTenantId != null ? readTenantId.hashCode() : 0);
        result = 31 * result + (readTenantName != null ? readTenantName.hashCode() : 0);
        result = 31 * result + (viewTime != null ? viewTime.hashCode() : 0);
        result = 31 * result + (ydzt != null ? ydzt.hashCode() : 0);
        result = 31 * result + (zzcysj != null ? zzcysj.hashCode() : 0);
        return result;
    }
}
