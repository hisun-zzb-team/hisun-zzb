package com.hisun.saas.zzb.a.entity;

import com.hisun.base.entity.TombstoneEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "a05")
public class A05 extends TombstoneEntity implements Serializable {
    /** 职级主键 */
    private String a0500;

    /** 人员基本信息主键 */
    private A01 a01;

    /** 职级字典内容 */
    private String a0501A;

    /** 职级字典代码:ZB09-2006/ZWJB */
    private String a0501B;

    /** 职级批准文号 */
    private String a0511;

    /** 职级终止日期 */
    private String a0517;

    /** 任职级时间 */
    private String a0504;

    /** 职级批准机关名称 */
    private String a0507A;

    /** 职级批准机关代码 */
    private String a0507B;

    /** 职级批准时所在单位及职务 */
    private String a0521;

    /** 现行职级标识,1-现行（在任），0-非现行（已免） */
    private String a0524;

    /** 累积时间,列如：5年 */
    private String aLjsj;

    /** 职级说明 */
    private String aZjsm;

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "a0500", nullable = false, length = 64)
    public String getA0500() {
        return a0500;
    }

    public void setA0500(String a0500) {
        this.a0500 = a0500;
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
    @Column(name = "a0501a", nullable = true, length = 32)
    public String getA0501A() {
        return a0501A;
    }

    public void setA0501A(String a0501A) {
        this.a0501A = a0501A;
    }

    @Basic
    @Column(name = "a0501b", nullable = true, length = 128)
    public String getA0501B() {
        return a0501B;
    }

    public void setA0501B(String a0501B) {
        this.a0501B = a0501B;
    }

    @Basic
    @Column(name = "a0511", nullable = true, length = 24)
    public String getA0511() {
        return a0511;
    }

    public void setA0511(String a0511) {
        this.a0511 = a0511;
    }

    @Basic
    @Column(name = "a0517", nullable = true, length = 8)
    public String getA0517() {
        return a0517;
    }

    public void setA0517(String a0517) {
        this.a0517 = a0517;
    }

    @Basic
    @Column(name = "a0504", nullable = true, length = 8)
    public String getA0504() {
        return a0504;
    }

    public void setA0504(String a0504) {
        this.a0504 = a0504;
    }

    @Basic
    @Column(name = "a0507a", nullable = true, length = 60)
    public String getA0507A() {
        return a0507A;
    }

    public void setA0507A(String a0507A) {
        this.a0507A = a0507A;
    }

    @Basic
    @Column(name = "a0507b", nullable = true, length = 15)
    public String getA0507B() {
        return a0507B;
    }

    public void setA0507B(String a0507B) {
        this.a0507B = a0507B;
    }

    @Basic
    @Column(name = "a0521", nullable = true, length = 60)
    public String getA0521() {
        return a0521;
    }

    public void setA0521(String a0521) {
        this.a0521 = a0521;
    }

    @Basic
    @Column(name = "a0524", nullable = true, length = 1)
    public String getA0524() {
        return a0524;
    }

    public void setA0524(String a0524) {
        this.a0524 = a0524;
    }

    @Basic
    @Column(name = "a_ljsj", nullable = true, length = 8)
    public String getaLjsj() {
        return aLjsj;
    }

    public void setaLjsj(String aLjsj) {
        this.aLjsj = aLjsj;
    }

    @Basic
    @Column(name = "a_zjsm", nullable = true, length = 64)
    public String getaZjsm() {
        return aZjsm;
    }

    public void setaZjsm(String aZjsm) {
        this.aZjsm = aZjsm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A05 a05 = (A05) o;
        return Objects.equals(a0500, a05.a0500) &&
                Objects.equals(a0501A, a05.a0501A) &&
                Objects.equals(a0501B, a05.a0501B) &&
                Objects.equals(a0511, a05.a0511) &&
                Objects.equals(a0517, a05.a0517) &&
                Objects.equals(a0504, a05.a0504) &&
                Objects.equals(a0507A, a05.a0507A) &&
                Objects.equals(a0507B, a05.a0507B) &&
                Objects.equals(a0521, a05.a0521) &&
                Objects.equals(a0524, a05.a0524) &&
                Objects.equals(aLjsj, a05.aLjsj) &&
                Objects.equals(aZjsm, a05.aZjsm);
    }

    @Override
    public int hashCode() {

        return Objects.hash(a0500, a0501A, a0501B, a0511, a0517, a0504, a0507A, a0507B, a0521, a0524, aLjsj, aZjsm);
    }
}
