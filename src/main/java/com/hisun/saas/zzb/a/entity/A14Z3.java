package com.hisun.saas.zzb.a.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "a14z3")
public class A14Z3 {
    /** null */
    private String a14Z300;

    /** 人员基本信息主键 */
    private A01 a01;

    /** 惩戒类别:ZB19-2006/JCLB */
    private String a14Z301;

    /** 惩戒名称字典内容/惩戒名称显示内容 */
    private String a14Z304A;

    /** 惩戒名称代码：ZB67-2006/JLCF */
    private String a14Z304B;

    /** 惩戒批准日期 */
    private String a14Z307;

    /** 惩戒批准机关名称 */
    private String a14Z311A;

    /** 惩戒批准机关代码:ZB02-2006/JGMC */
    private String a14Z311B;

    /** 批准惩戒机关级别:ZB03-1994/DWJB */
    private String a14Z314;

    /** 惩戒原因：ZB20-2006/JCYY */
    private String a14Z317;

    /** 惩戒说明 */
    private String a14Z321;

    /** 惩戒撤销日期 */
    private String a14Z324;

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "a14z300", nullable = false, length = 64)
    public String getA14Z300() {
        return a14Z300;
    }

    public void setA14Z300(String a14Z300) {
        this.a14Z300 = a14Z300;
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
    @Column(name = "a14z301", nullable = true, length = 2)
    public String getA14Z301() {
        return a14Z301;
    }

    public void setA14Z301(String a14Z301) {
        this.a14Z301 = a14Z301;
    }

    @Basic
    @Column(name = "a14z304a", nullable = true, length = 128)
    public String getA14Z304A() {
        return a14Z304A;
    }

    public void setA14Z304A(String a14Z304A) {
        this.a14Z304A = a14Z304A;
    }

    @Basic
    @Column(name = "a14z304b", nullable = true, length = 128)
    public String getA14Z304B() {
        return a14Z304B;
    }

    public void setA14Z304B(String a14Z304B) {
        this.a14Z304B = a14Z304B;
    }

    @Basic
    @Column(name = "a14z307", nullable = true, length = 8)
    public String getA14Z307() {
        return a14Z307;
    }

    public void setA14Z307(String a14Z307) {
        this.a14Z307 = a14Z307;
    }

    @Basic
    @Column(name = "a14z311a", nullable = true, length = 64)
    public String getA14Z311A() {
        return a14Z311A;
    }

    public void setA14Z311A(String a14Z311A) {
        this.a14Z311A = a14Z311A;
    }

    @Basic
    @Column(name = "a14z311b", nullable = true, length = 15)
    public String getA14Z311B() {
        return a14Z311B;
    }

    public void setA14Z311B(String a14Z311B) {
        this.a14Z311B = a14Z311B;
    }

    @Basic
    @Column(name = "a14z314", nullable = true, length = 3)
    public String getA14Z314() {
        return a14Z314;
    }

    public void setA14Z314(String a14Z314) {
        this.a14Z314 = a14Z314;
    }

    @Basic
    @Column(name = "a14z317", nullable = true, length = 4)
    public String getA14Z317() {
        return a14Z317;
    }

    public void setA14Z317(String a14Z317) {
        this.a14Z317 = a14Z317;
    }

    @Basic
    @Column(name = "a14z321", nullable = true, length = -1)
    public String getA14Z321() {
        return a14Z321;
    }

    public void setA14Z321(String a14Z321) {
        this.a14Z321 = a14Z321;
    }

    @Basic
    @Column(name = "a14z324", nullable = true, length = 8)
    public String getA14Z324() {
        return a14Z324;
    }

    public void setA14Z324(String a14Z324) {
        this.a14Z324 = a14Z324;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A14Z3 that = (A14Z3) o;
        return Objects.equals(a14Z300, that.a14Z300) &&
                Objects.equals(a14Z301, that.a14Z301) &&
                Objects.equals(a14Z304A, that.a14Z304A) &&
                Objects.equals(a14Z304B, that.a14Z304B) &&
                Objects.equals(a14Z307, that.a14Z307) &&
                Objects.equals(a14Z311A, that.a14Z311A) &&
                Objects.equals(a14Z311B, that.a14Z311B) &&
                Objects.equals(a14Z314, that.a14Z314) &&
                Objects.equals(a14Z317, that.a14Z317) &&
                Objects.equals(a14Z321, that.a14Z321) &&
                Objects.equals(a14Z324, that.a14Z324);
    }

    @Override
    public int hashCode() {

        return Objects.hash(a14Z300, a14Z301, a14Z304A, a14Z304B, a14Z307, a14Z311A, a14Z311B, a14Z314, a14Z317, a14Z321, a14Z324);
    }
}
