package com.hisun.saas.zzb.a.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "a37")
public class A37 implements Serializable {
    /** null */
    private String a3700;

    /** null */
    private A01 a01;

    /** 工作单位通信地址 */
    private String a3701;

    /** 工作单位邮编 */
    private String a3704;

    /** 办公电话 */
    private String a3707A;

    /** 住宅电话 */
    private String a3707B;

    /** 移动电话 */
    private String a3707C;

    /** 电子邮箱 */
    private String a3708;

    /** 家庭地址 */
    private String a3711;

    /** 家庭地址邮编 */
    private String a3714;
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "a3700", nullable = false, length = 100)
    public String getA3700() {
        return a3700;
    }

    public void setA3700(String a3700) {
        this.a3700 = a3700;
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
    @Column(name = "a3701", nullable = true, length = 60)
    public String getA3701() {
        return a3701;
    }

    public void setA3701(String a3701) {
        this.a3701 = a3701;
    }

    @Basic
    @Column(name = "a3704", nullable = true, length = 6)
    public String getA3704() {
        return a3704;
    }

    public void setA3704(String a3704) {
        this.a3704 = a3704;
    }

    @Basic
    @Column(name = "a3707a", nullable = true, length = 20)
    public String getA3707A() {
        return a3707A;
    }

    public void setA3707A(String a3707A) {
        this.a3707A = a3707A;
    }

    @Basic
    @Column(name = "a3707b", nullable = true, length = 20)
    public String getA3707B() {
        return a3707B;
    }

    public void setA3707B(String a3707B) {
        this.a3707B = a3707B;
    }

    @Basic
    @Column(name = "a3707c", nullable = true, length = 20)
    public String getA3707C() {
        return a3707C;
    }

    public void setA3707C(String a3707C) {
        this.a3707C = a3707C;
    }

    @Basic
    @Column(name = "a3708", nullable = true, length = 40)
    public String getA3708() {
        return a3708;
    }

    public void setA3708(String a3708) {
        this.a3708 = a3708;
    }

    @Basic
    @Column(name = "a3711", nullable = true, length = 60)
    public String getA3711() {
        return a3711;
    }

    public void setA3711(String a3711) {
        this.a3711 = a3711;
    }

    @Basic
    @Column(name = "a3714", nullable = true, length = 6)
    public String getA3714() {
        return a3714;
    }

    public void setA3714(String a3714) {
        this.a3714 = a3714;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A37 a37 = (A37) o;
        return Objects.equals(a3700, a37.a3700) &&
                Objects.equals(a3701, a37.a3701) &&
                Objects.equals(a3704, a37.a3704) &&
                Objects.equals(a3707A, a37.a3707A) &&
                Objects.equals(a3707B, a37.a3707B) &&
                Objects.equals(a3707C, a37.a3707C) &&
                Objects.equals(a3708, a37.a3708) &&
                Objects.equals(a3711, a37.a3711) &&
                Objects.equals(a3714, a37.a3714);
    }

    @Override
    public int hashCode() {

        return Objects.hash(a3700, a3701, a3704, a3707A, a3707B, a3707C, a3708, a3711, a3714);
    }
}
