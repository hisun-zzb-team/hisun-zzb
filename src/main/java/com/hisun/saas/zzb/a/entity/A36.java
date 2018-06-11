package com.hisun.saas.zzb.a.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "a36")
public class A36 implements Serializable {
    /** 主键 */
    private String a3600;

    /** 人员基本信息主键 */
    private A01 a01;

    /** 姓名 */
    private String a3601;

    /** 人员与该人关系名称(称谓) */
    private String a3604A;

    /** 人员与该人的关系代码：GB4761-1984 */
    private String a3604B;

    /** 出生日期 */
    private String a3607;

    /** 工作单位及职务 */
    private String a3611;

    /** 人员国籍:ZB01-2006/GQMC */
    private String a3617;

    /** 人员民族：GB3304-1991 */
    private String a3621;

    /** 民族字典内容/民族显示内容 */
    private String a3621A;

    /** 人员学历：ZB64-2006/XLDM */
    private String a3624;

    /** 学历字典内容 */
    private String a3624A;

    /** 政治面貌字典代码:GB762-1984 */
    private String a3627;

    /** 政治面貌显示内容 */
    private String a3627A;

    /** 人员身份 */
    private String a3631;

    /** 人员职级:ZB09-2006/ZWJB */
    private String a3637;

    /** 人员现状:ZB56-2006/CYXZ */
    private String a3641;

    /** 人员备注 */
    private String a3644;

    /** 人员排序号 */
    private Integer a3647;

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "a3600", nullable = false, length = 64)
    public String getA3600() {
        return a3600;
    }

    public void setA3600(String a3600) {
        this.a3600 = a3600;
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
    @Column(name = "a3601", nullable = true, length = 18)
    public String getA3601() {
        return a3601;
    }

    public void setA3601(String a3601) {
        this.a3601 = a3601;
    }

    @Basic
    @Column(name = "a3604a", nullable = true, length = 6)
    public String getA3604A() {
        return a3604A;
    }

    public void setA3604A(String a3604A) {
        this.a3604A = a3604A;
    }

    @Basic
    @Column(name = "a3604b", nullable = true, length = 2)
    public String getA3604B() {
        return a3604B;
    }

    public void setA3604B(String a3604B) {
        this.a3604B = a3604B;
    }

    @Basic
    @Column(name = "a3607", nullable = true, length = 8)
    public String getA3607() {
        return a3607;
    }

    public void setA3607(String a3607) {
        this.a3607 = a3607;
    }

    @Basic
    @Column(name = "a3611", nullable = true, length = 128)
    public String getA3611() {
        return a3611;
    }

    public void setA3611(String a3611) {
        this.a3611 = a3611;
    }

    @Basic
    @Column(name = "a3617", nullable = true, length = 6)
    public String getA3617() {
        return a3617;
    }

    public void setA3617(String a3617) {
        this.a3617 = a3617;
    }

    @Basic
    @Column(name = "a3621", nullable = true, length = 2)
    public String getA3621() {
        return a3621;
    }

    public void setA3621(String a3621) {
        this.a3621 = a3621;
    }

    @Basic
    @Column(name = "a3621a", nullable = true, length = 64)
    public String getA3621A() {
        return a3621A;
    }

    public void setA3621A(String a3621A) {
        this.a3621A = a3621A;
    }

    @Basic
    @Column(name = "a3624", nullable = true, length = 2)
    public String getA3624() {
        return a3624;
    }

    public void setA3624(String a3624) {
        this.a3624 = a3624;
    }

    @Basic
    @Column(name = "a3624a", nullable = true, length = 64)
    public String getA3624A() {
        return a3624A;
    }

    public void setA3624A(String a3624A) {
        this.a3624A = a3624A;
    }

    @Basic
    @Column(name = "a3627", nullable = true, length = 2)
    public String getA3627() {
        return a3627;
    }

    public void setA3627(String a3627) {
        this.a3627 = a3627;
    }

    @Basic
    @Column(name = "a3627a", nullable = true, length = 64)
    public String getA3627A() {
        return a3627A;
    }

    public void setA3627A(String a3627A) {
        this.a3627A = a3627A;
    }

    @Basic
    @Column(name = "a3631", nullable = true, length = 2)
    public String getA3631() {
        return a3631;
    }

    public void setA3631(String a3631) {
        this.a3631 = a3631;
    }

    @Basic
    @Column(name = "a3637", nullable = true, length = 4)
    public String getA3637() {
        return a3637;
    }

    public void setA3637(String a3637) {
        this.a3637 = a3637;
    }

    @Basic
    @Column(name = "a3641", nullable = true, length = 1)
    public String getA3641() {
        return a3641;
    }

    public void setA3641(String a3641) {
        this.a3641 = a3641;
    }

    @Basic
    @Column(name = "a3644", nullable = true, length = -1)
    public String getA3644() {
        return a3644;
    }

    public void setA3644(String a3644) {
        this.a3644 = a3644;
    }

    @Basic
    @Column(name = "a3647", nullable = true, precision = 2)
    public Integer getA3647() {
        return a3647;
    }

    public void setA3647(Integer a3647) {
        this.a3647 = a3647;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A36 a36 = (A36) o;
        return Objects.equals(a3600, a36.a3600) &&
                Objects.equals(a3601, a36.a3601) &&
                Objects.equals(a3604A, a36.a3604A) &&
                Objects.equals(a3604B, a36.a3604B) &&
                Objects.equals(a3607, a36.a3607) &&
                Objects.equals(a3611, a36.a3611) &&
                Objects.equals(a3617, a36.a3617) &&
                Objects.equals(a3621, a36.a3621) &&
                Objects.equals(a3621A, a36.a3621A) &&
                Objects.equals(a3624, a36.a3624) &&
                Objects.equals(a3624A, a36.a3624A) &&
                Objects.equals(a3627, a36.a3627) &&
                Objects.equals(a3627A, a36.a3627A) &&
                Objects.equals(a3631, a36.a3631) &&
                Objects.equals(a3637, a36.a3637) &&
                Objects.equals(a3641, a36.a3641) &&
                Objects.equals(a3644, a36.a3644) &&
                Objects.equals(a3647, a36.a3647);
    }

    @Override
    public int hashCode() {

        return Objects.hash(a3600, a3601, a3604A, a3604B, a3607, a3611, a3617, a3621, a3621A, a3624, a3624A, a3627, a3627A, a3631, a3637, a3641, a3644, a3647);
    }
}
