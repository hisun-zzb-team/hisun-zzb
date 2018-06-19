package com.hisun.saas.zzb.a.entity;

import com.hisun.base.entity.TombstoneEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "a12")
public class A12 extends TombstoneEntity implements Serializable {
    /** 主键 */
    private String a1200;

    /** 人员基本信息主键 */
    private A01 a01;

    /** 出国地点 */
    private String a1201;

    /** 所至国家（地区）数 */
    private Integer a1212;

    /** 出国(境)目的代码：GB10301-1988 */
    private String a1214;

    /** 出国(境)目的 */
    private String a1214A;

    /** 组织该人此次出国境的单位名称 */
    private String a1217;

    /** 该人出国境期间的个人身份：ZB33-1994/CGSF */
    private String a1234;

    /** 在团内身份 */
    private String a1236;

    /** 起始日期 */
    private String a1237;

    /** 截止日期 */
    private String a1241;

    /** 出国天数 */
    private String aCgts;

    /** 批准时间 */
    private String aPzsj;

    /** 单位职务 */
    private String aDwzw;

    /** 随行人员 */
    private String aSxry;

    /** 出国情况 */
    private String aCgqk;

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "a1200", nullable = false, length = 64)
    public String getA1200() {
        return a1200;
    }

    public void setA1200(String a1200) {
        this.a1200 = a1200;
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
    @Column(name = "a1201", nullable = true, length = 128)
    public String getA1201() {
        return a1201;
    }

    public void setA1201(String a1201) {
        this.a1201 = a1201;
    }

    @Basic
    @Column(name = "a1212", nullable = true, precision = 0)
    public Integer getA1212() {
        return a1212;
    }

    public void setA1212(Integer a1212) {
        this.a1212 = a1212;
    }

    @Basic
    @Column(name = "a1214", nullable = true, length = 2)
    public String getA1214() {
        return a1214;
    }

    public void setA1214(String a1214) {
        this.a1214 = a1214;
    }

    @Basic
    @Column(name = "a1214a", nullable = true, length = 32)
    public String getA1214A() {
        return a1214A;
    }

    public void setA1214A(String a1214A) {
        this.a1214A = a1214A;
    }

    @Basic
    @Column(name = "a1217", nullable = true, length = 64)
    public String getA1217() {
        return a1217;
    }

    public void setA1217(String a1217) {
        this.a1217 = a1217;
    }

    @Basic
    @Column(name = "a1234", nullable = true, length = 2)
    public String getA1234() {
        return a1234;
    }

    public void setA1234(String a1234) {
        this.a1234 = a1234;
    }

    @Basic
    @Column(name = "a1236", nullable = true, length = 40)
    public String getA1236() {
        return a1236;
    }

    public void setA1236(String a1236) {
        this.a1236 = a1236;
    }

    @Basic
    @Column(name = "a1237", nullable = true, length = 8)
    public String getA1237() {
        return a1237;
    }

    public void setA1237(String a1237) {
        this.a1237 = a1237;
    }

    @Basic
    @Column(name = "a1241", nullable = true, length = 8)
    public String getA1241() {
        return a1241;
    }

    public void setA1241(String a1241) {
        this.a1241 = a1241;
    }

    @Basic
    @Column(name = "a_cgts", nullable = true, length = 32)
    public String getaCgts() {
        return aCgts;
    }

    public void setaCgts(String aCgts) {
        this.aCgts = aCgts;
    }

    @Basic
    @Column(name = "a_pzsj", nullable = true, length = 8)
    public String getaPzsj() {
        return aPzsj;
    }

    public void setaPzsj(String aPzsj) {
        this.aPzsj = aPzsj;
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
    @Column(name = "a_sxry", nullable = true, length = 512)
    public String getaSxry() {
        return aSxry;
    }

    public void setaSxry(String aSxry) {
        this.aSxry = aSxry;
    }

    @Basic
    @Column(name = "a_cgqk", nullable = true, length = 1024)
    public String getaCgqk() {
        return aCgqk;
    }

    public void setaCgqk(String aCgqk) {
        this.aCgqk = aCgqk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A12 a12 = (A12) o;
        return Objects.equals(a1200, a12.a1200) &&
                Objects.equals(a1201, a12.a1201) &&
                Objects.equals(a1212, a12.a1212) &&
                Objects.equals(a1214, a12.a1214) &&
                Objects.equals(a1214A, a12.a1214A) &&
                Objects.equals(a1217, a12.a1217) &&
                Objects.equals(a1234, a12.a1234) &&
                Objects.equals(a1236, a12.a1236) &&
                Objects.equals(a1237, a12.a1237) &&
                Objects.equals(a1241, a12.a1241) &&
                Objects.equals(aCgts, a12.aCgts) &&
                Objects.equals(aPzsj, a12.aPzsj) &&
                Objects.equals(aDwzw, a12.aDwzw) &&
                Objects.equals(aSxry, a12.aSxry) &&
                Objects.equals(aCgqk, a12.aCgqk);
    }

    @Override
    public int hashCode() {

        return Objects.hash(a1200, a1201, a1212, a1214, a1214A, a1217, a1234, a1236, a1237, a1241, aCgts, aPzsj, aDwzw, aSxry, aCgqk);
    }
}
