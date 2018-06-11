package com.hisun.saas.zzb.a.entity;

import com.hisun.base.entity.TombstoneEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "a08")
public class A08 extends TombstoneEntity implements Serializable {
    /** 学历学位主键 */
    private String a0800;

    /** null */
    private A01 a01;

    /** 学历字典名称/学历显示名称 */
    private String a0801A;

    /** 学历字典代码:ZB64-2006/XLDM */
    private String a0801B;

    /** 学位字典名称/学位显示名称 */
    private String a0901A;

    /** 学位字典代码:GB/T6864-2003 */
    private String a0901B;

    /** 入学日期 */
    private String a0804;

    /** 毕业(肄)日期 */
    private String a0807;

    /** 学位授予日期 */
    private String a0904;

    /** 学制 */
    private String a0811;

    /** 学校（单位）名称 */
    private String a0814;

    /** 学校所在政区:ZB01-2006/GQMC */
    private String a0817;

    /** 从学单位类别字典代码:ZB27-2006/CXDW */
    private String a0821;

    /** 从学单位类别字典内容 */
    private String a0821A;

    /** 所学专业名称 */
    private String a0824;

    /** 所学专业类别代码:GB/T1635-1997 */
    private String a0827;

    /** 所学专业类别字典内容 */
    private String a0827A;

    /** 学习完成情况：ZB28-2994/XXWC */
    private String a0831;

    /** 学习完成情况字典内容 */
    private String a0831A;

    /** 最高学历标识，1：最高；0：否 */
    private String a0834;

    /** 教育类别。1-全日制；2-在职 */
    private String a0837;

    /** 最高学位标识。1：最高；0：否 */
    private String a0914;

    /** 学历证书编号 */
    private String aXlzsbh;

    /** 学位证书编号 */
    private String aXwzsbh;

    /** 学历证书发证时间 */
    private String aXlzssj;

    /** 学位证书发证时间 */
    private String aXwzssj;

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "a0800", nullable = false, length = 64)
    public String getA0800() {
        return a0800;
    }

    public void setA0800(String a0800) {
        this.a0800 = a0800;
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
    @Column(name = "a0801a", nullable = true, length = 32)
    public String getA0801A() {
        return a0801A;
    }

    public void setA0801A(String a0801A) {
        this.a0801A = a0801A;
    }

    @Basic
    @Column(name = "a0801b", nullable = true, length = 128)
    public String getA0801B() {
        return a0801B;
    }

    public void setA0801B(String a0801B) {
        this.a0801B = a0801B;
    }

    @Basic
    @Column(name = "a0901a", nullable = true, length = 64)
    public String getA0901A() {
        return a0901A;
    }

    public void setA0901A(String a0901A) {
        this.a0901A = a0901A;
    }

    @Basic
    @Column(name = "a0901b", nullable = true, length = 128)
    public String getA0901B() {
        return a0901B;
    }

    public void setA0901B(String a0901B) {
        this.a0901B = a0901B;
    }

    @Basic
    @Column(name = "a0804", nullable = true, length = 8)
    public String getA0804() {
        return a0804;
    }

    public void setA0804(String a0804) {
        this.a0804 = a0804;
    }

    @Basic
    @Column(name = "a0807", nullable = true, length = 8)
    public String getA0807() {
        return a0807;
    }

    public void setA0807(String a0807) {
        this.a0807 = a0807;
    }

    @Basic
    @Column(name = "a0904", nullable = true, length = 8)
    public String getA0904() {
        return a0904;
    }

    public void setA0904(String a0904) {
        this.a0904 = a0904;
    }

    @Basic
    @Column(name = "a0811", nullable = true, length = 64)
    public String getA0811() {
        return a0811;
    }

    public void setA0811(String a0811) {
        this.a0811 = a0811;
    }

    @Basic
    @Column(name = "a0814", nullable = true, length = 128)
    public String getA0814() {
        return a0814;
    }

    public void setA0814(String a0814) {
        this.a0814 = a0814;
    }

    @Basic
    @Column(name = "a0817", nullable = true, length = 6)
    public String getA0817() {
        return a0817;
    }

    public void setA0817(String a0817) {
        this.a0817 = a0817;
    }

    @Basic
    @Column(name = "a0821", nullable = true, length = 6)
    public String getA0821() {
        return a0821;
    }

    public void setA0821(String a0821) {
        this.a0821 = a0821;
    }

    @Basic
    @Column(name = "a0821a", nullable = true, length = 64)
    public String getA0821A() {
        return a0821A;
    }

    public void setA0821A(String a0821A) {
        this.a0821A = a0821A;
    }

    @Basic
    @Column(name = "a0824", nullable = true, length = 20)
    public String getA0824() {
        return a0824;
    }

    public void setA0824(String a0824) {
        this.a0824 = a0824;
    }

    @Basic
    @Column(name = "a0827", nullable = true, length = 6)
    public String getA0827() {
        return a0827;
    }

    public void setA0827(String a0827) {
        this.a0827 = a0827;
    }

    @Basic
    @Column(name = "a0827a", nullable = true, length = 64)
    public String getA0827A() {
        return a0827A;
    }

    public void setA0827A(String a0827A) {
        this.a0827A = a0827A;
    }

    @Basic
    @Column(name = "a0831", nullable = true, length = 128)
    public String getA0831() {
        return a0831;
    }

    public void setA0831(String a0831) {
        this.a0831 = a0831;
    }

    @Basic
    @Column(name = "a0831a", nullable = true, length = 64)
    public String getA0831A() {
        return a0831A;
    }

    public void setA0831A(String a0831A) {
        this.a0831A = a0831A;
    }

    @Basic
    @Column(name = "a0834", nullable = false, length = 1)
    public String getA0834() {
        return a0834;
    }

    public void setA0834(String a0834) {
        this.a0834 = a0834;
    }

    @Basic
    @Column(name = "a0837", nullable = true, length = 1)
    public String getA0837() {
        return a0837;
    }

    public void setA0837(String a0837) {
        this.a0837 = a0837;
    }

    @Basic
    @Column(name = "a0914", nullable = false, length = 1)
    public String getA0914() {
        return a0914;
    }

    public void setA0914(String a0914) {
        this.a0914 = a0914;
    }

    @Basic
    @Column(name = "a_xlzsbh", nullable = true, length = 128)
    public String getaXlzsbh() {
        return aXlzsbh;
    }

    public void setaXlzsbh(String aXlzsbh) {
        this.aXlzsbh = aXlzsbh;
    }

    @Basic
    @Column(name = "a_xwzsbh", nullable = true, length = 128)
    public String getaXwzsbh() {
        return aXwzsbh;
    }

    public void setaXwzsbh(String aXwzsbh) {
        this.aXwzsbh = aXwzsbh;
    }

    @Basic
    @Column(name = "a_xlzssj", nullable = true, length = 8)
    public String getaXlzssj() {
        return aXlzssj;
    }

    public void setaXlzssj(String aXlzssj) {
        this.aXlzssj = aXlzssj;
    }

    @Basic
    @Column(name = "a_xwzssj", nullable = true, length = 8)
    public String getaXwzssj() {
        return aXwzssj;
    }

    public void setaXwzssj(String aXwzssj) {
        this.aXwzssj = aXwzssj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A08 a08 = (A08) o;
        return Objects.equals(a0800, a08.a0800) &&
                Objects.equals(a0801A, a08.a0801A) &&
                Objects.equals(a0801B, a08.a0801B) &&
                Objects.equals(a0901A, a08.a0901A) &&
                Objects.equals(a0901B, a08.a0901B) &&
                Objects.equals(a0804, a08.a0804) &&
                Objects.equals(a0807, a08.a0807) &&
                Objects.equals(a0904, a08.a0904) &&
                Objects.equals(a0811, a08.a0811) &&
                Objects.equals(a0814, a08.a0814) &&
                Objects.equals(a0817, a08.a0817) &&
                Objects.equals(a0821, a08.a0821) &&
                Objects.equals(a0821A, a08.a0821A) &&
                Objects.equals(a0824, a08.a0824) &&
                Objects.equals(a0827, a08.a0827) &&
                Objects.equals(a0827A, a08.a0827A) &&
                Objects.equals(a0831, a08.a0831) &&
                Objects.equals(a0831A, a08.a0831A) &&
                Objects.equals(a0834, a08.a0834) &&
                Objects.equals(a0837, a08.a0837) &&
                Objects.equals(a0914, a08.a0914) &&
                Objects.equals(aXlzsbh, a08.aXlzsbh) &&
                Objects.equals(aXwzsbh, a08.aXwzsbh) &&
                Objects.equals(aXlzssj, a08.aXlzssj) &&
                Objects.equals(aXwzssj, a08.aXwzssj);
    }

    @Override
    public int hashCode() {

        return Objects.hash(a0800, a0801A, a0801B, a0901A, a0901B, a0804, a0807, a0904, a0811, a0814, a0817, a0821, a0821A, a0824, a0827, a0827A, a0831, a0831A, a0834, a0837, a0914, aXlzsbh, aXwzsbh, aXlzssj, aXwzssj);
    }
}
