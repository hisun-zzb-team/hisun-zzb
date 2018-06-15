package com.hisun.saas.zzb.a.entity;

import com.hisun.base.entity.TombstoneEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "a15")
public class A15 extends TombstoneEntity implements Serializable {
    /** null */
    private String a1500;

    /** null */
    private A01 a01;

    /** 考核类别字典代码:ZB17-2006/KHLB */
    private String a1501;

    /** 考核类别显示内容 */
    private String a1501A;

    /** 考核起始日期 */
    private String a1504;

    /** 考核截止日期 */
    private String a1505;

    /** 考核组织名称 */
    private String a1507;

    /** 考核意见 */
    private String a1511;

    /** 考核人姓名 */
    private String a1514;

    /** 考核结论类别字典代码:ZB18-2006/KHJL */
    private String a1517;

    /** 考核结论类别显示内容 */
    private String a1517A;

    /** 考核年度 */
    private String aKhnd;

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "a1500", nullable = false, length = 64)
    public String getA1500() {
        return a1500;
    }

    public void setA1500(String a1500) {
        this.a1500 = a1500;
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
    @Column(name = "a1501", nullable = true, length = 128)
    public String getA1501() {
        return a1501;
    }

    public void setA1501(String a1501) {
        this.a1501 = a1501;
    }

    @Basic
    @Column(name = "a1501a", nullable = true, length = 128)
    public String getA1501A() {
        return a1501A;
    }

    public void setA1501A(String a1501A) {
        this.a1501A = a1501A;
    }

    @Basic
    @Column(name = "a1504", nullable = true, length = 8)
    public String getA1504() {
        return a1504;
    }

    public void setA1504(String a1504) {
        this.a1504 = a1504;
    }

    @Basic
    @Column(name = "a1505", nullable = true, length = 8)
    public String getA1505() {
        return a1505;
    }

    public void setA1505(String a1505) {
        this.a1505 = a1505;
    }

    @Basic
    @Column(name = "a1507", nullable = true, length = 64)
    public String getA1507() {
        return a1507;
    }

    public void setA1507(String a1507) {
        this.a1507 = a1507;
    }

    @Basic
    @Column(name = "a1511", nullable = true, length = -1)
    public String getA1511() {
        return a1511;
    }

    public void setA1511(String a1511) {
        this.a1511 = a1511;
    }

    @Basic
    @Column(name = "a1514", nullable = true, length = 100)
    public String getA1514() {
        return a1514;
    }

    public void setA1514(String a1514) {
        this.a1514 = a1514;
    }

    @Basic
    @Column(name = "a1517", nullable = true, length = 128)
    public String getA1517() {
        return a1517;
    }

    public void setA1517(String a1517) {
        this.a1517 = a1517;
    }

    @Basic
    @Column(name = "a1517a", nullable = true, length = 32)
    public String getA1517A() {
        return a1517A;
    }

    public void setA1517A(String a1517A) {
        this.a1517A = a1517A;
    }

    @Basic
    @Column(name = "a_khnd", nullable = true, length = 8)
    public String getaKhnd() {
        return aKhnd;
    }

    public void setaKhnd(String aKhnd) {
        this.aKhnd = aKhnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A15 a15 = (A15) o;
        return Objects.equals(a1500, a15.a1500) &&
                Objects.equals(a1501, a15.a1501) &&
                Objects.equals(a1501A, a15.a1501A) &&
                Objects.equals(a1504, a15.a1504) &&
                Objects.equals(a1505, a15.a1505) &&
                Objects.equals(a1507, a15.a1507) &&
                Objects.equals(a1511, a15.a1511) &&
                Objects.equals(a1514, a15.a1514) &&
                Objects.equals(a1517, a15.a1517) &&
                Objects.equals(a1517A, a15.a1517A) &&
                Objects.equals(aKhnd, a15.aKhnd);
    }

    @Override
    public int hashCode() {

        return Objects.hash(a1500, a1501, a1501A, a1504, a1505, a1507, a1511, a1514, a1517, a1517A, aKhnd);
    }
}
