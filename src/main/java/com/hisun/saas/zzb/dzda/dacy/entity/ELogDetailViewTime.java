/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.entity;

import com.hisun.base.entity.TombstoneEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author liuzj {279421824@qq.com}
 */
@Entity
@Table(name = "e_log_detail_view_time")
public class ELogDetailViewTime  extends TombstoneEntity implements Serializable {
    private String id;
    private EA38LogDetail a38LogDetails;
    private Date stareTime;
    private Date endTime;
    private String viewTime;

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
    @JoinColumn(name = "e_a38_log_detail_id")
    public EA38LogDetail getA38LogDetails() {
        return a38LogDetails;
    }

    public void setA38LogDetails(EA38LogDetail a38LogDetails) {
        this.a38LogDetails = a38LogDetails;
    }

    @Basic
    @Column(name = "stare_time")
    public Date getStareTime() {
        return stareTime;
    }

    public void setStareTime(Date stareTime) {
        this.stareTime = stareTime;
    }

    @Basic
    @Column(name = "end_time")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "view_time")
    public String getViewTime() {
        return viewTime;
    }

    public void setViewTime(String viewTime) {
        this.viewTime = viewTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ELogDetailViewTime that = (ELogDetailViewTime) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (stareTime != null ? !stareTime.equals(that.stareTime) : that.stareTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (viewTime != null ? !viewTime.equals(that.viewTime) : that.viewTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (stareTime != null ? stareTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (viewTime != null ? viewTime.hashCode() : 0);
        return result;
    }
}
