package com.hisun.saas.zzb.a.entity;

import com.hisun.base.entity.TombstoneEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "a17")
public class A17 extends TombstoneEntity implements Serializable {
    /** null */
    private String a1700;

    /** null */
    private A01 a01;

    /** 简历 */
    private String a1701;

    /** 起始时间 */
    private String aQssj;

    /** 截止日期 */
    private String aJzsj;

    /** 工作经历描述 */
    private String aJlms;

    /** 单位职务 */
    private String aDwzw;

    /** 行政级别字典代码 */
    private String aZwjbb;

    /** 行政级别字典内容/行政级别显示内容 */
    private String aZwjba;

    /** 是否当前单位.1:是;0:否 */
    private String aSfdqdw;

    /** 进入单位时间 */
    private String aJrdqdwsj;

    /** 本条累计时长 */
    private Integer aLjsj;

    /** 备注 */
    private String aBz;

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "a1700", nullable = false, length = 64)
    public String getA1700() {
        return a1700;
    }

    public void setA1700(String a1700) {
        this.a1700 = a1700;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "a0100")
    public A01 getA01() {
        return a01;
    }

    public void setA01(A01 a01) {
        this.a01 = a01;
    }

    @Basic
    @Column(name = "a1701", nullable = true, length = -1)
    public String getA1701() {
        return a1701;
    }

    public void setA1701(String a1701) {
        this.a1701 = a1701;
    }

    @Basic
    @Column(name = "a_qssj", nullable = true, length = 8)
    public String getaQssj() {
        return aQssj;
    }

    public void setaQssj(String aQssj) {
        this.aQssj = aQssj;
    }

    @Basic
    @Column(name = "a_jzsj", nullable = true, length = 8)
    public String getaJzsj() {
        return aJzsj;
    }

    public void setaJzsj(String aJzsj) {
        this.aJzsj = aJzsj;
    }

    @Basic
    @Column(name = "a_jlms", nullable = true, length = 512)
    public String getaJlms() {
        return aJlms;
    }

    public void setaJlms(String aJlms) {
        this.aJlms = aJlms;
    }

    @Basic
    @Column(name = "a_dwzw", nullable = true, length = 512)
    public String getaDwzw() {
        return aDwzw;
    }

    public void setaDwzw(String aDwzw) {
        this.aDwzw = aDwzw;
    }

    @Basic
    @Column(name = "a_zwjbb", nullable = true, length = 128)
    public String getaZwjbb() {
        return aZwjbb;
    }

    public void setaZwjbb(String aZwjbb) {
        this.aZwjbb = aZwjbb;
    }

    @Basic
    @Column(name = "a_zwjba", nullable = true, length = 32)
    public String getaZwjba() {
        return aZwjba;
    }

    public void setaZwjba(String aZwjba) {
        this.aZwjba = aZwjba;
    }

    @Basic
    @Column(name = "a_sfdqdw", nullable = true, length = 1)
    public String getaSfdqdw() {
        return aSfdqdw;
    }

    public void setaSfdqdw(String aSfdqdw) {
        this.aSfdqdw = aSfdqdw;
    }

    @Basic
    @Column(name = "a_jrdqdwsj", nullable = true, length = 8)
    public String getaJrdqdwsj() {
        return aJrdqdwsj;
    }

    public void setaJrdqdwsj(String aJrdqdwsj) {
        this.aJrdqdwsj = aJrdqdwsj;
    }

    @Basic
    @Column(name = "a_ljsj", nullable = true)
    public Integer getaLjsj() {
        return aLjsj;
    }

    public void setaLjsj(Integer aLjsj) {
        this.aLjsj = aLjsj;
    }

    @Basic
    @Column(name = "a_bz", nullable = true, length = 512)
    public String getaBz() {
        return aBz;
    }

    public void setaBz(String aBz) {
        this.aBz = aBz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A17 a17 = (A17) o;
        return Objects.equals(a1700, a17.a1700) &&
                Objects.equals(a1701, a17.a1701) &&
                Objects.equals(aQssj, a17.aQssj) &&
                Objects.equals(aJzsj, a17.aJzsj) &&
                Objects.equals(aJlms, a17.aJlms) &&
                Objects.equals(aDwzw, a17.aDwzw) &&
                Objects.equals(aZwjbb, a17.aZwjbb) &&
                Objects.equals(aZwjba, a17.aZwjba) &&
                Objects.equals(aSfdqdw, a17.aSfdqdw) &&
                Objects.equals(aJrdqdwsj, a17.aJrdqdwsj) &&
                Objects.equals(aLjsj, a17.aLjsj) &&
                Objects.equals(aBz, a17.aBz);
    }

    @Override
    public int hashCode() {

        return Objects.hash(a1700, a1701, aQssj, aJzsj, aJlms, aDwzw, aZwjbb, aZwjba, aSfdqdw, aJrdqdwsj, aLjsj, aBz);
    }
}
