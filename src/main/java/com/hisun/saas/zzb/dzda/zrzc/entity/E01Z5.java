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
@Table(name = "e01z5")
public class E01Z5  extends TenantEntity implements Serializable {
    private String id;
    private A38 a38;
    private String name;//档案名称
    private Date e01Z501;//接收日期
    private String e01Z507A;//来件单位名称
    private String e01Z507B;//来件单位ID
    private Integer e01Z511;//正本卷数
    private Integer e01Z514;//副本卷数'
    private String e01Z517;//经办人'
    private String e01Z521;//审核人'
    private Date e01Z524;//审核日期'
    private String e01Z527;//案卷质量 0-不合格；1-合格；2-优秀
    private Date e01Z531;//回执日期
    private Date e01Z534;//入库日期
    private String e01Z537;//入库审批人
    private String e01Z541;//档案位置
    private String e01Z544;//备注
    private String filePath;//上传文件路径
    private String fileName; //上传文件名称
    private String sjly;//数据来源
    private String jsr;//接收人
    private String yjr;//移交人
    private String dadwzw;//档案单位职务

    @Basic
    @Column(name = "jsr")
    public String getJsr() {
        return jsr;
    }

    public void setJsr(String jsr) {
        this.jsr = jsr;
    }

    @Basic
    @Column(name = "yjr")
    public String getYjr() {
        return yjr;
    }

    public void setYjr(String yjr) {
        this.yjr = yjr;
    }

    @Basic
    @Column(name = "dadwzw")
    public String getDadwzw() {
        return dadwzw;
    }

    public void setDadwzw(String dadwzw) {
        this.dadwzw = dadwzw;
    }

    @Basic
    @Column(name = "sjly")
    public String getSjly() {
        return sjly;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
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
    @Column(name = "e01z501")
    public Date getE01Z501() {
        return e01Z501;
    }

    public void setE01Z501(Date e01Z501) {
        this.e01Z501 = e01Z501;
    }

    @Basic
    @Column(name = "e01z507a")
    public String getE01Z507A() {
        return e01Z507A;
    }

    public void setE01Z507A(String e01Z507A) {
        this.e01Z507A = e01Z507A;
    }

    @Basic
    @Column(name = "e01z507b")
    public String getE01Z507B() {
        return e01Z507B;
    }

    public void setE01Z507B(String e01Z507B) {
        this.e01Z507B = e01Z507B;
    }

    @Basic
    @Column(name = "e01z511")
    public Integer getE01Z511() {
        return e01Z511;
    }

    public void setE01Z511(Integer e01Z511) {
        this.e01Z511 = e01Z511;
    }

    @Basic
    @Column(name = "e01z514")
    public Integer getE01Z514() {
        return e01Z514;
    }

    public void setE01Z514(Integer e01Z514) {
        this.e01Z514 = e01Z514;
    }

    @Basic
    @Column(name = "e01z517")
    public String getE01Z517() {
        return e01Z517;
    }

    public void setE01Z517(String e01Z517) {
        this.e01Z517 = e01Z517;
    }

    @Basic
    @Column(name = "e01z521")
    public String getE01Z521() {
        return e01Z521;
    }

    public void setE01Z521(String e01Z521) {
        this.e01Z521 = e01Z521;
    }

    @Basic
    @Column(name = "e01z524")
    public Date getE01Z524() {
        return e01Z524;
    }

    public void setE01Z524(Date e01Z524) {
        this.e01Z524 = e01Z524;
    }

    @Basic
    @Column(name = "e01z527")
    public String getE01Z527() {
        return e01Z527;
    }

    public void setE01Z527(String e01Z527) {
        this.e01Z527 = e01Z527;
    }

    @Basic
    @Column(name = "e01z531")
    public Date getE01Z531() {
        return e01Z531;
    }

    public void setE01Z531(Date e01Z531) {
        this.e01Z531 = e01Z531;
    }

    @Basic
    @Column(name = "e01z534")
    public Date getE01Z534() {
        return e01Z534;
    }

    public void setE01Z534(Date e01Z534) {
        this.e01Z534 = e01Z534;
    }

    @Basic
    @Column(name = "e01z537")
    public String getE01Z537() {
        return e01Z537;
    }

    public void setE01Z537(String e01Z537) {
        this.e01Z537 = e01Z537;
    }

    @Basic
    @Column(name = "e01z541")
    public String getE01Z541() {
        return e01Z541;
    }

    public void setE01Z541(String e01Z541) {
        this.e01Z541 = e01Z541;
    }

    @Basic
    @Column(name = "e01z544")
    public String getE01Z544() {
        return e01Z544;
    }

    public void setE01Z544(String e01Z544) {
        this.e01Z544 = e01Z544;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        E01Z5 e01Z5 = (E01Z5) o;

        if (id != null ? !id.equals(e01Z5.id) : e01Z5.id != null) return false;
        if (name != null ? !name.equals(e01Z5.name) : e01Z5.name != null) return false;
        if (e01Z501 != null ? !e01Z501.equals(e01Z5.e01Z501) : e01Z5.e01Z501 != null) return false;
        if (e01Z507A != null ? !e01Z507A.equals(e01Z5.e01Z507A) : e01Z5.e01Z507A != null) return false;
        if (e01Z507B != null ? !e01Z507B.equals(e01Z5.e01Z507B) : e01Z5.e01Z507B != null) return false;
        if (e01Z511 != null ? !e01Z511.equals(e01Z5.e01Z511) : e01Z5.e01Z511 != null) return false;
        if (e01Z514 != null ? !e01Z514.equals(e01Z5.e01Z514) : e01Z5.e01Z514 != null) return false;
        if (e01Z517 != null ? !e01Z517.equals(e01Z5.e01Z517) : e01Z5.e01Z517 != null) return false;
        if (e01Z521 != null ? !e01Z521.equals(e01Z5.e01Z521) : e01Z5.e01Z521 != null) return false;
        if (e01Z524 != null ? !e01Z524.equals(e01Z5.e01Z524) : e01Z5.e01Z524 != null) return false;
        if (e01Z527 != null ? !e01Z527.equals(e01Z5.e01Z527) : e01Z5.e01Z527 != null) return false;
        if (e01Z531 != null ? !e01Z531.equals(e01Z5.e01Z531) : e01Z5.e01Z531 != null) return false;
        if (e01Z534 != null ? !e01Z534.equals(e01Z5.e01Z534) : e01Z5.e01Z534 != null) return false;
        if (e01Z537 != null ? !e01Z537.equals(e01Z5.e01Z537) : e01Z5.e01Z537 != null) return false;
        if (e01Z541 != null ? !e01Z541.equals(e01Z5.e01Z541) : e01Z5.e01Z541 != null) return false;
        if (e01Z544 != null ? !e01Z544.equals(e01Z5.e01Z544) : e01Z5.e01Z544 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (e01Z501 != null ? e01Z501.hashCode() : 0);
        result = 31 * result + (e01Z507A != null ? e01Z507A.hashCode() : 0);
        result = 31 * result + (e01Z507B != null ? e01Z507B.hashCode() : 0);
        result = 31 * result + (e01Z511 != null ? e01Z511.hashCode() : 0);
        result = 31 * result + (e01Z514 != null ? e01Z514.hashCode() : 0);
        result = 31 * result + (e01Z517 != null ? e01Z517.hashCode() : 0);
        result = 31 * result + (e01Z521 != null ? e01Z521.hashCode() : 0);
        result = 31 * result + (e01Z524 != null ? e01Z524.hashCode() : 0);
        result = 31 * result + (e01Z527 != null ? e01Z527.hashCode() : 0);
        result = 31 * result + (e01Z531 != null ? e01Z531.hashCode() : 0);
        result = 31 * result + (e01Z534 != null ? e01Z534.hashCode() : 0);
        result = 31 * result + (e01Z537 != null ? e01Z537.hashCode() : 0);
        result = 31 * result + (e01Z541 != null ? e01Z541.hashCode() : 0);
        result = 31 * result + (e01Z544 != null ? e01Z544.hashCode() : 0);
        return result;
    }
}
