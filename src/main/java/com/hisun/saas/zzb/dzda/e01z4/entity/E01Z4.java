/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.e01z4.entity;

import com.hisun.base.entity.TombstoneEntity;
import com.hisun.saas.zzb.dzda.a38.entity.A38;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author liuzj {279421824@qq.com}
 */
@Entity
@Table(name = "e01z4")
public class E01Z4   extends TombstoneEntity implements Serializable {
    private String id;
    private A38 a38;
    private String e01Z401;
    private String e01Z401B;
    private String e01Z401A;
    private Integer e01Z407;
    private String fileTime;
    private String fileTypeCode;
    private String fileTypeName;
    private Integer px;
    private String remark;

    @Id
    @Column(name = "id")
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

    @Basic
    @Column(name = "e01z401")
    public String getE01Z401() {
        return e01Z401;
    }

    public void setE01Z401(String e01Z401) {
        this.e01Z401 = e01Z401;
    }

    @Basic
    @Column(name = "e01z401b")
    public String getE01Z401B() {
        return e01Z401B;
    }

    public void setE01Z401B(String e01Z401B) {
        this.e01Z401B = e01Z401B;
    }

    @Basic
    @Column(name = "e01z401a")
    public String getE01Z401A() {
        return e01Z401A;
    }

    public void setE01Z401A(String e01Z401A) {
        this.e01Z401A = e01Z401A;
    }

    @Basic
    @Column(name = "e01z407")
    public Integer getE01Z407() {
        return e01Z407;
    }

    public void setE01Z407(Integer e01Z407) {
        this.e01Z407 = e01Z407;
    }

    @Basic
    @Column(name = "file_time")
    public String getFileTime() {
        return fileTime;
    }

    public void setFileTime(String fileTime) {
        this.fileTime = fileTime;
    }

    @Basic
    @Column(name = "file_type_code")
    public String getFileTypeCode() {
        return fileTypeCode;
    }

    public void setFileTypeCode(String fileTypeCode) {
        this.fileTypeCode = fileTypeCode;
    }

    @Basic
    @Column(name = "file_type_name")
    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    @Basic
    @Column(name = "px")
    public Integer getPx() {
        return px;
    }

    public void setPx(Integer px) {
        this.px = px;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        E01Z4 e01Z4 = (E01Z4) o;

        if (id != null ? !id.equals(e01Z4.id) : e01Z4.id != null) return false;
        if (e01Z401 != null ? !e01Z401.equals(e01Z4.e01Z401) : e01Z4.e01Z401 != null) return false;
        if (e01Z401B != null ? !e01Z401B.equals(e01Z4.e01Z401B) : e01Z4.e01Z401B != null) return false;
        if (e01Z401A != null ? !e01Z401A.equals(e01Z4.e01Z401A) : e01Z4.e01Z401A != null) return false;
        if (e01Z407 != null ? !e01Z407.equals(e01Z4.e01Z407) : e01Z4.e01Z407 != null) return false;
        if (fileTime != null ? !fileTime.equals(e01Z4.fileTime) : e01Z4.fileTime != null) return false;
        if (fileTypeCode != null ? !fileTypeCode.equals(e01Z4.fileTypeCode) : e01Z4.fileTypeCode != null) return false;
        if (fileTypeName != null ? !fileTypeName.equals(e01Z4.fileTypeName) : e01Z4.fileTypeName != null) return false;
        if (px != null ? !px.equals(e01Z4.px) : e01Z4.px != null) return false;
        if (remark != null ? !remark.equals(e01Z4.remark) : e01Z4.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (e01Z401 != null ? e01Z401.hashCode() : 0);
        result = 31 * result + (e01Z401B != null ? e01Z401B.hashCode() : 0);
        result = 31 * result + (e01Z401A != null ? e01Z401A.hashCode() : 0);
        result = 31 * result + (e01Z407 != null ? e01Z407.hashCode() : 0);
        result = 31 * result + (fileTime != null ? fileTime.hashCode() : 0);
        result = 31 * result + (fileTypeCode != null ? fileTypeCode.hashCode() : 0);
        result = 31 * result + (fileTypeName != null ? fileTypeName.hashCode() : 0);
        result = 31 * result + (px != null ? px.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
