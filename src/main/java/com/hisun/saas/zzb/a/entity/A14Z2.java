package com.hisun.saas.zzb.a.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "a14z2")
public class A14Z2 {
    /** 奖励情况主键 */
    private String a14Z200;

    /** 人员基本信息主键 */
    private A01 a01;

    /** 奖励类别:ZB19-2006/JCLB */
    private String a14Z201;

    /** 奖励名称字典内容/奖励名称显示内容 */
    private String a14Z204A;

    /** 奖励名称字典代码:ZB65-2006/JLDM及ZB66-2006/CHJZ */
    private String a14Z204B;

    /** 授予荣誉称号级别代码:ZB66-2006/CHJZ第1部分 */
    private String a14Z207;

    /** 授予荣誉称号级别字典内容/授予荣誉称号级别显示内容 */
    private String aA14Z207A;

    /** 奖励日期 */
    private String a14Z211;

    /** 奖励批准机关名称 */
    private String a14Z214A;

    /** 奖励批准机关代码：ZB02-2006/JGMC */
    private String a14Z214B;

    /** 批准奖励机关级别:ZB03-1994/DWJB */
    private String a14Z217;

    /** 奖励原因 */
    private String a14Z221;

    /** 奖励说明 */
    private String a14Z224;

    /** 奖励撤销日期 */
    private String a14Z227;

    /** 奖励撤消原因 */
    private String a14Z231;

    /** 奖励荣誉级别字典代码  */
    private String aJljbb;

    /** 奖励荣誉级别字典内容  */
    private String aJljba;

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "a14z200", nullable = false, length = 64)
    public String getA14Z200() {
        return a14Z200;
    }

    public void setA14Z200(String a14Z200) {
        this.a14Z200 = a14Z200;
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
    @Column(name = "a14z201", nullable = true, length = 2)
    public String getA14Z201() {
        return a14Z201;
    }

    public void setA14Z201(String a14Z201) {
        this.a14Z201 = a14Z201;
    }

    @Basic
    @Column(name = "a14z204a", nullable = true, length = 128)
    public String getA14Z204A() {
        return a14Z204A;
    }

    public void setA14Z204A(String a14Z204A) {
        this.a14Z204A = a14Z204A;
    }

    @Basic
    @Column(name = "a14z204b", nullable = true, length = 128)
    public String getA14Z204B() {
        return a14Z204B;
    }

    public void setA14Z204B(String a14Z204B) {
        this.a14Z204B = a14Z204B;
    }

    @Basic
    @Column(name = "a14z207", nullable = true, length = 1)
    public String getA14Z207() {
        return a14Z207;
    }

    public void setA14Z207(String a14Z207) {
        this.a14Z207 = a14Z207;
    }

    @Basic
    @Column(name = "a_a14z207a", nullable = true, length = 128)
    public String getaA14Z207A() {
        return aA14Z207A;
    }

    public void setaA14Z207A(String aA14Z207A) {
        this.aA14Z207A = aA14Z207A;
    }

    @Basic
    @Column(name = "a14z211", nullable = true, length = 8)
    public String getA14Z211() {
        return a14Z211;
    }

    public void setA14Z211(String a14Z211) {
        this.a14Z211 = a14Z211;
    }

    @Basic
    @Column(name = "a14z214a", nullable = true, length = 60)
    public String getA14Z214A() {
        return a14Z214A;
    }

    public void setA14Z214A(String a14Z214A) {
        this.a14Z214A = a14Z214A;
    }

    @Basic
    @Column(name = "a14z214b", nullable = true, length = 15)
    public String getA14Z214B() {
        return a14Z214B;
    }

    public void setA14Z214B(String a14Z214B) {
        this.a14Z214B = a14Z214B;
    }

    @Basic
    @Column(name = "a14z217", nullable = true, length = 3)
    public String getA14Z217() {
        return a14Z217;
    }

    public void setA14Z217(String a14Z217) {
        this.a14Z217 = a14Z217;
    }

    @Basic
    @Column(name = "a14z221", nullable = true, length = 4)
    public String getA14Z221() {
        return a14Z221;
    }

    public void setA14Z221(String a14Z221) {
        this.a14Z221 = a14Z221;
    }

    @Basic
    @Column(name = "a14z224", nullable = true, length = -1)
    public String getA14Z224() {
        return a14Z224;
    }

    public void setA14Z224(String a14Z224) {
        this.a14Z224 = a14Z224;
    }

    @Basic
    @Column(name = "a14z227", nullable = true, length = 8)
    public String getA14Z227() {
        return a14Z227;
    }

    public void setA14Z227(String a14Z227) {
        this.a14Z227 = a14Z227;
    }

    @Basic
    @Column(name = "a14z231", nullable = true, length = 1)
    public String getA14Z231() {
        return a14Z231;
    }

    public void setA14Z231(String a14Z231) {
        this.a14Z231 = a14Z231;
    }

    @Basic
    @Column(name = "a_jljbb", nullable = true, length = 128)
    public String getaJljbb() {
        return aJljbb;
    }

    public void setaJljbb(String aJljbb) {
        this.aJljbb = aJljbb;
    }

    @Basic
    @Column(name = "a_jljba", nullable = true, length = 256)
    public String getaJljba() {
        return aJljba;
    }

    public void setaJljba(String aJljba) {
        this.aJljba = aJljba;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A14Z2 that = (A14Z2) o;
        return Objects.equals(a14Z200, that.a14Z200) &&
                Objects.equals(a14Z201, that.a14Z201) &&
                Objects.equals(a14Z204A, that.a14Z204A) &&
                Objects.equals(a14Z204B, that.a14Z204B) &&
                Objects.equals(a14Z207, that.a14Z207) &&
                Objects.equals(aA14Z207A, that.aA14Z207A) &&
                Objects.equals(a14Z211, that.a14Z211) &&
                Objects.equals(a14Z214A, that.a14Z214A) &&
                Objects.equals(a14Z214B, that.a14Z214B) &&
                Objects.equals(a14Z217, that.a14Z217) &&
                Objects.equals(a14Z221, that.a14Z221) &&
                Objects.equals(a14Z224, that.a14Z224) &&
                Objects.equals(a14Z227, that.a14Z227) &&
                Objects.equals(a14Z231, that.a14Z231) &&
                Objects.equals(aJljbb, that.aJljbb) &&
                Objects.equals(aJljba, that.aJljba);
    }

    @Override
    public int hashCode() {

        return Objects.hash(a14Z200, a14Z201, a14Z204A, a14Z204B, a14Z207, aA14Z207A, a14Z211, a14Z214A, a14Z214B, a14Z217, a14Z221, a14Z224, a14Z227, a14Z231, aJljbb, aJljba);
    }
}
