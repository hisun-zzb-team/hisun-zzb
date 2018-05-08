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

/**
 * @author liuzj {279421824@qq.com}
 */
@Entity
@Table(name = "e_a38_log_view_time")
public class EA38LogViewTime  extends TombstoneEntity implements Serializable {
    private String id;
    private EA38Log a38Log;
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
    @JoinColumn(name = "a38_log_id")
    public EA38Log getA38Log() {
        return a38Log;
    }

    public void setA38Log(EA38Log a38Log) {
        this.a38Log = a38Log;
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

        EA38LogViewTime that = (EA38LogViewTime) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (viewTime != null ? !viewTime.equals(that.viewTime) : that.viewTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (viewTime != null ? viewTime.hashCode() : 0);
        return result;
    }
}
