/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.entity;

import com.hisun.base.entity.TombstoneEntity;
import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
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
@Table(name = "e_a38_log_detail")
public class EA38LogDetail  extends TenantEntity implements Serializable {
    private String id;
    private EA38Log a38Log;
    private String e01Z1Id;//材料主键
    private String e01Z111;//材料名称
    private Date cyTime;//查阅时间
    private String e01Z101A;//材料类别字典内容
    private String e01Z101B;//材料类别字典代码
    private String cysj;//查阅时长
    private Date jscysj;// 结束查阅时间
    private List<ELogDetailViewTime> logDetailViewTimes;

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
    @JoinColumn(name = "a38_log_id")
    public EA38Log getA38Log() {
        return a38Log;
    }

    public void setA38Log(EA38Log a38Log) {
        this.a38Log = a38Log;
    }

    @OneToMany(mappedBy = "a38LogDetails", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<ELogDetailViewTime> getLogDetailViewTimes() {
        return logDetailViewTimes;
    }

    public void setLogDetailViewTimes(List<ELogDetailViewTime> logDetailViewTimes) {
        this.logDetailViewTimes = logDetailViewTimes;
    }

    @Basic
    @Column(name = "e01z1_id")
    public String getE01Z1Id() {
        return e01Z1Id;
    }

    public void setE01Z1Id(String e01Z1Id) {
        this.e01Z1Id = e01Z1Id;
    }

    @Basic
    @Column(name = "e01z111")
    public String getE01Z111() {
        return e01Z111;
    }

    public void setE01Z111(String e01Z111) {
        this.e01Z111 = e01Z111;
    }

    @Basic
    @Column(name = "cy_time")
    public Date getCyTime() {
        return cyTime;
    }

    public void setCyTime(Date cyTime) {
        this.cyTime = cyTime;
    }

    @Basic
    @Column(name = "e01z101a")
    public String getE01Z101A() {
        return e01Z101A;
    }

    public void setE01Z101A(String e01Z101A) {
        this.e01Z101A = e01Z101A;
    }

    @Basic
    @Column(name = "e01z101b")
    public String getE01Z101B() {
        return e01Z101B;
    }

    public void setE01Z101B(String e01Z101B) {
        this.e01Z101B = e01Z101B;
    }

    @Basic
    @Column(name = "cysj")
    public String getCysj() {
        return cysj;
    }

    public void setCysj(String cysj) {
        this.cysj = cysj;
    }

    @Basic
    @Column(name = "jscysj")
    public Date getJscysj() {
        return jscysj;
    }

    public void setJscysj(Date jscysj) {
        this.jscysj = jscysj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EA38LogDetail that = (EA38LogDetail) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (e01Z1Id != null ? !e01Z1Id.equals(that.e01Z1Id) : that.e01Z1Id != null) return false;
        if (e01Z111 != null ? !e01Z111.equals(that.e01Z111) : that.e01Z111 != null) return false;
        if (cyTime != null ? !cyTime.equals(that.cyTime) : that.cyTime != null) return false;
        if (e01Z101A != null ? !e01Z101A.equals(that.e01Z101A) : that.e01Z101A != null) return false;
        if (e01Z101B != null ? !e01Z101B.equals(that.e01Z101B) : that.e01Z101B != null) return false;
        if (cysj != null ? !cysj.equals(that.cysj) : that.cysj != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (e01Z1Id != null ? e01Z1Id.hashCode() : 0);
        result = 31 * result + (e01Z111 != null ? e01Z111.hashCode() : 0);
        result = 31 * result + (cyTime != null ? cyTime.hashCode() : 0);
        result = 31 * result + (e01Z101A != null ? e01Z101A.hashCode() : 0);
        result = 31 * result + (e01Z101B != null ? e01Z101B.hashCode() : 0);
        result = 31 * result + (cysj != null ? cysj.hashCode() : 0);
        return result;
    }
}
