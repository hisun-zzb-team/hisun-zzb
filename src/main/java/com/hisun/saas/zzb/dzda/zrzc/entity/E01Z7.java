/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.zrzc.entity;

import com.hisun.base.entity.TombstoneEntity;
import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author liuzj {279421824@qq.com}
 */
@Entity
@Table(name = "e01z7")
public class E01Z7  extends TenantEntity implements Serializable {
    private String id;
    private A38 a38;
    private String name;//'档案名称'
    private Date e01Z701;//'转递日期'
    private String e01Z704A;//'转往单位名称'
    private String e01Z704B;//'转往单位Id'
    private Integer e01Z711;//'转出正本数'
    private Integer e01Z714;//'转出副本数'
    private String e01Z717;//'经办人'
    private String e01Z721;//'转递原因'
    private String e01Z724;//'回执人'
    private Date e01Z727;//'回执日期'
    private String e01Z731;//'备注'
    private String filePath;//上传文件路径
    private String fileName; //上传文件名称

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

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "fileName")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "filePath")
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    @Basic
    @Column(name = "e01z701")
    public Date getE01Z701() {
        return e01Z701;
    }

    public void setE01Z701(Date e01Z701) {
        this.e01Z701 = e01Z701;
    }

    @Basic
    @Column(name = "e01z704a")
    public String getE01Z704A() {
        return e01Z704A;
    }

    public void setE01Z704A(String e01Z704A) {
        this.e01Z704A = e01Z704A;
    }

    @Basic
    @Column(name = "e01z704b")
    public String getE01Z704B() {
        return e01Z704B;
    }

    public void setE01Z704B(String e01Z704B) {
        this.e01Z704B = e01Z704B;
    }

    @Basic
    @Column(name = "e01z711")
    public Integer getE01Z711() {
        return e01Z711;
    }

    public void setE01Z711(Integer e01Z711) {
        this.e01Z711 = e01Z711;
    }

    @Basic
    @Column(name = "e01z714")
    public Integer getE01Z714() {
        return e01Z714;
    }

    public void setE01Z714(Integer e01Z714) {
        this.e01Z714 = e01Z714;
    }

    @Basic
    @Column(name = "e01z717")
    public String getE01Z717() {
        return e01Z717;
    }

    public void setE01Z717(String e01Z717) {
        this.e01Z717 = e01Z717;
    }

    @Basic
    @Column(name = "e01z721")
    public String getE01Z721() {
        return e01Z721;
    }

    public void setE01Z721(String e01Z721) {
        this.e01Z721 = e01Z721;
    }

    @Basic
    @Column(name = "e01z724")
    public String getE01Z724() {
        return e01Z724;
    }

    public void setE01Z724(String e01Z724) {
        this.e01Z724 = e01Z724;
    }

    @Basic
    @Column(name = "e01z727")
    public Date getE01Z727() {
        return e01Z727;
    }

    public void setE01Z727(Date e01Z727) {
        this.e01Z727 = e01Z727;
    }

    @Basic
    @Column(name = "e01z731")
    public String getE01Z731() {
        return e01Z731;
    }

    public void setE01Z731(String e01Z731) {
        this.e01Z731 = e01Z731;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        E01Z7 e01Z7 = (E01Z7) o;

        if (id != null ? !id.equals(e01Z7.id) : e01Z7.id != null) return false;
        if (name != null ? !name.equals(e01Z7.name) : e01Z7.name != null) return false;
        if (e01Z701 != null ? !e01Z701.equals(e01Z7.e01Z701) : e01Z7.e01Z701 != null) return false;
        if (e01Z704A != null ? !e01Z704A.equals(e01Z7.e01Z704A) : e01Z7.e01Z704A != null) return false;
        if (e01Z704B != null ? !e01Z704B.equals(e01Z7.e01Z704B) : e01Z7.e01Z704B != null) return false;
        if (e01Z711 != null ? !e01Z711.equals(e01Z7.e01Z711) : e01Z7.e01Z711 != null) return false;
        if (e01Z714 != null ? !e01Z714.equals(e01Z7.e01Z714) : e01Z7.e01Z714 != null) return false;
        if (e01Z717 != null ? !e01Z717.equals(e01Z7.e01Z717) : e01Z7.e01Z717 != null) return false;
        if (e01Z721 != null ? !e01Z721.equals(e01Z7.e01Z721) : e01Z7.e01Z721 != null) return false;
        if (e01Z724 != null ? !e01Z724.equals(e01Z7.e01Z724) : e01Z7.e01Z724 != null) return false;
        if (e01Z727 != null ? !e01Z727.equals(e01Z7.e01Z727) : e01Z7.e01Z727 != null) return false;
        if (e01Z731 != null ? !e01Z731.equals(e01Z7.e01Z731) : e01Z7.e01Z731 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (e01Z701 != null ? e01Z701.hashCode() : 0);
        result = 31 * result + (e01Z704A != null ? e01Z704A.hashCode() : 0);
        result = 31 * result + (e01Z704B != null ? e01Z704B.hashCode() : 0);
        result = 31 * result + (e01Z711 != null ? e01Z711.hashCode() : 0);
        result = 31 * result + (e01Z714 != null ? e01Z714.hashCode() : 0);
        result = 31 * result + (e01Z717 != null ? e01Z717.hashCode() : 0);
        result = 31 * result + (e01Z721 != null ? e01Z721.hashCode() : 0);
        result = 31 * result + (e01Z724 != null ? e01Z724.hashCode() : 0);
        result = 31 * result + (e01Z727 != null ? e01Z727.hashCode() : 0);
        result = 31 * result + (e01Z731 != null ? e01Z731.hashCode() : 0);
        return result;
    }
}
