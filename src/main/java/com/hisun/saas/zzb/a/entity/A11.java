package com.hisun.saas.zzb.a.entity;

import com.hisun.base.entity.TombstoneEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "a11")
public class A11 extends TombstoneEntity implements Serializable {
    /** 培训情况主键 */
    private String a1100;

    /** 人员基本信息主键 */
    private A01 a01;

    /** 培训类别字典代码:ZB29-2006/PXLB */
    private String a1101;

    /** 培训类别显示内容 */
    private String a1101A;

    /** 培训离岗状态:ZB30-2006/LGZT */
    private String a1104;

    /** 培训起始日期 */
    private String a1107;

    /** 培训结束日期 */
    private String a1111;

    /** 培训主办单位名称 */
    private String a1114A;

    /** 培训主办单位代码:ZB02-2006/JGMC */
    private String a1114B;

    /** 培训主办单位级别:ZB03-1994/DWJB */
    private String a1117;

    /** 培训承办单位名称 */
    private String a1118A;

    /** 培训承办单位代码:ZB02-2006/JGMC */
    private String a1118B;

    /** 国外合作单位 */
    private String a1119;

    /** 从学单位名称 */
    private String a1121A;

    /** 从学单位代码:ZB02-2006/JGMC */
    private String a1121B;

    /** 从学单位所在政区:ZB01-2006/GQMC */
    private String a1124;

    /** 从学单位类别:ZB27-2006/CXDW */
    private String a1127;

    /** 培训班名称 */
    private String a1131;

    /** 培训班主键 */
    private String a1131B;

    /** 出国培训主题 */
    private String a1132;

    /** 培训班类别:ZB31-2006/PXLB */
    private String a1134;

    /** 培训专业名称 */
    private String a1137;

    /** 培训专业类别:GB/T16835-1997 */
    private String a1141;

    /** 培训完成情况代码:ZB28-1994/XXWC */
    private String a1144;

    /** 培训完成情况字典内容 */
    private String a1144A;

    /** 培训时所在单位及职务 */
    private String a1147;

    /** 出国培训标识,1-是，0-否 */
    private String a1151;

    /** 从学单位所在国别:ZB01-2006/GQMC */
    private String a1154;

    /** 培训说明 */
    private String aPxsm;

    /** 培训时长 */
    private Integer aPxsc;

    /** 培训时长计算标准。1：按年计算；2：按月计算；3：按周计算；4：按天计算；5：按小时计算 */
    private String aPxscdw;

    /** 培训层次字典内容/培训层次显示内容 */
    private String aPxcca;

    /** 培训层次代码 */
    private String aPxccb;

    /** 培训学分 */
    private Integer aPxxf;

    /** 培训学分类别代码（1：一类;2:二类） */
    private String aPxxflb;

    /** 培训地点,1内地,3港澳台,2国外 */
    private String aPxdd;

    /** 培训地址 */
    private String aPxjtdz;

    /** 培训形式,0为非网络培训,1为网络培训 */
    private Integer aPxxs;

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "a1100", nullable = false, length = 64)
    public String getA1100() {
        return a1100;
    }

    public void setA1100(String a1100) {
        this.a1100 = a1100;
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
    @Column(name = "a1101", nullable = true, length = 1000)
    public String getA1101() {
        return a1101;
    }

    public void setA1101(String a1101) {
        this.a1101 = a1101;
    }

    @Basic
    @Column(name = "a1101a", nullable = true, length = 256)
    public String getA1101A() {
        return a1101A;
    }

    public void setA1101A(String a1101A) {
        this.a1101A = a1101A;
    }

    @Basic
    @Column(name = "a1104", nullable = true, length = 1)
    public String getA1104() {
        return a1104;
    }

    public void setA1104(String a1104) {
        this.a1104 = a1104;
    }

    @Basic
    @Column(name = "a1107", nullable = true, length = 8)
    public String getA1107() {
        return a1107;
    }

    public void setA1107(String a1107) {
        this.a1107 = a1107;
    }

    @Basic
    @Column(name = "a1111", nullable = true, length = 8)
    public String getA1111() {
        return a1111;
    }

    public void setA1111(String a1111) {
        this.a1111 = a1111;
    }

    @Basic
    @Column(name = "a1114a", nullable = true, length = 60)
    public String getA1114A() {
        return a1114A;
    }

    public void setA1114A(String a1114A) {
        this.a1114A = a1114A;
    }

    @Basic
    @Column(name = "a1114b", nullable = true, length = 15)
    public String getA1114B() {
        return a1114B;
    }

    public void setA1114B(String a1114B) {
        this.a1114B = a1114B;
    }

    @Basic
    @Column(name = "a1117", nullable = true, length = 3)
    public String getA1117() {
        return a1117;
    }

    public void setA1117(String a1117) {
        this.a1117 = a1117;
    }

    @Basic
    @Column(name = "a1118a", nullable = true, length = 60)
    public String getA1118A() {
        return a1118A;
    }

    public void setA1118A(String a1118A) {
        this.a1118A = a1118A;
    }

    @Basic
    @Column(name = "a1118b", nullable = true, length = 15)
    public String getA1118B() {
        return a1118B;
    }

    public void setA1118B(String a1118B) {
        this.a1118B = a1118B;
    }

    @Basic
    @Column(name = "a1119", nullable = true, length = 120)
    public String getA1119() {
        return a1119;
    }

    public void setA1119(String a1119) {
        this.a1119 = a1119;
    }

    @Basic
    @Column(name = "a1121a", nullable = true, length = 60)
    public String getA1121A() {
        return a1121A;
    }

    public void setA1121A(String a1121A) {
        this.a1121A = a1121A;
    }

    @Basic
    @Column(name = "a1121b", nullable = true, length = 15)
    public String getA1121B() {
        return a1121B;
    }

    public void setA1121B(String a1121B) {
        this.a1121B = a1121B;
    }

    @Basic
    @Column(name = "a1124", nullable = true, length = 6)
    public String getA1124() {
        return a1124;
    }

    public void setA1124(String a1124) {
        this.a1124 = a1124;
    }

    @Basic
    @Column(name = "a1127", nullable = true, length = 2)
    public String getA1127() {
        return a1127;
    }

    public void setA1127(String a1127) {
        this.a1127 = a1127;
    }

    @Basic
    @Column(name = "a1131", nullable = true, length = 512)
    public String getA1131() {
        return a1131;
    }

    public void setA1131(String a1131) {
        this.a1131 = a1131;
    }

    @Basic
    @Column(name = "a1131b", nullable = true, length = 128)
    public String getA1131B() {
        return a1131B;
    }

    public void setA1131B(String a1131B) {
        this.a1131B = a1131B;
    }

    @Basic
    @Column(name = "a1132", nullable = true, length = 30)
    public String getA1132() {
        return a1132;
    }

    public void setA1132(String a1132) {
        this.a1132 = a1132;
    }

    @Basic
    @Column(name = "a1134", nullable = true, length = 1)
    public String getA1134() {
        return a1134;
    }

    public void setA1134(String a1134) {
        this.a1134 = a1134;
    }

    @Basic
    @Column(name = "a1137", nullable = true, length = 40)
    public String getA1137() {
        return a1137;
    }

    public void setA1137(String a1137) {
        this.a1137 = a1137;
    }

    @Basic
    @Column(name = "a1141", nullable = true, length = 6)
    public String getA1141() {
        return a1141;
    }

    public void setA1141(String a1141) {
        this.a1141 = a1141;
    }

    @Basic
    @Column(name = "a1144", nullable = true, length = 128)
    public String getA1144() {
        return a1144;
    }

    public void setA1144(String a1144) {
        this.a1144 = a1144;
    }

    @Basic
    @Column(name = "a1144a", nullable = true, length = 32)
    public String getA1144A() {
        return a1144A;
    }

    public void setA1144A(String a1144A) {
        this.a1144A = a1144A;
    }

    @Basic
    @Column(name = "a1147", nullable = true, length = 60)
    public String getA1147() {
        return a1147;
    }

    public void setA1147(String a1147) {
        this.a1147 = a1147;
    }

    @Basic
    @Column(name = "a1151", nullable = true, length = 1)
    public String getA1151() {
        return a1151;
    }

    public void setA1151(String a1151) {
        this.a1151 = a1151;
    }

    @Basic
    @Column(name = "a1154", nullable = true, length = 6)
    public String getA1154() {
        return a1154;
    }

    public void setA1154(String a1154) {
        this.a1154 = a1154;
    }

    @Basic
    @Column(name = "a_pxsm", nullable = true, length = 1024)
    public String getaPxsm() {
        return aPxsm;
    }

    public void setaPxsm(String aPxsm) {
        this.aPxsm = aPxsm;
    }

    @Basic
    @Column(name = "a_pxsc", nullable = true, precision = 2)
    public Integer getaPxsc() {
        return aPxsc;
    }

    public void setaPxsc(Integer aPxsc) {
        this.aPxsc = aPxsc;
    }

    @Basic
    @Column(name = "a_pxscdw", nullable = true, length = 1)
    public String getaPxscdw() {
        return aPxscdw;
    }

    public void setaPxscdw(String aPxscdw) {
        this.aPxscdw = aPxscdw;
    }

    @Basic
    @Column(name = "a_pxcca", nullable = true, length = 12)
    public String getaPxcca() {
        return aPxcca;
    }

    public void setaPxcca(String aPxcca) {
        this.aPxcca = aPxcca;
    }

    @Basic
    @Column(name = "a_pxccb", nullable = true, length = 2)
    public String getaPxccb() {
        return aPxccb;
    }

    public void setaPxccb(String aPxccb) {
        this.aPxccb = aPxccb;
    }

    @Basic
    @Column(name = "a_pxxf", nullable = true, precision = 2)
    public Integer getaPxxf() {
        return aPxxf;
    }

    public void setaPxxf(Integer aPxxf) {
        this.aPxxf = aPxxf;
    }

    @Basic
    @Column(name = "a_pxxflb", nullable = true, length = 1)
    public String getaPxxflb() {
        return aPxxflb;
    }

    public void setaPxxflb(String aPxxflb) {
        this.aPxxflb = aPxxflb;
    }

    @Basic
    @Column(name = "a_pxdd", nullable = true, length = 1)
    public String getaPxdd() {
        return aPxdd;
    }

    public void setaPxdd(String aPxdd) {
        this.aPxdd = aPxdd;
    }

    @Basic
    @Column(name = "a_pxjtdz", nullable = true, length = 512)
    public String getaPxjtdz() {
        return aPxjtdz;
    }

    public void setaPxjtdz(String aPxjtdz) {
        this.aPxjtdz = aPxjtdz;
    }

    @Basic
    @Column(name = "a_pxxs", nullable = true, precision = 0)
    public Integer getaPxxs() {
        return aPxxs;
    }

    public void setaPxxs(Integer aPxxs) {
        this.aPxxs = aPxxs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A11 a11 = (A11) o;
        return Objects.equals(a1100, a11.a1100) &&
                Objects.equals(a1101, a11.a1101) &&
                Objects.equals(a1101A, a11.a1101A) &&
                Objects.equals(a1104, a11.a1104) &&
                Objects.equals(a1107, a11.a1107) &&
                Objects.equals(a1111, a11.a1111) &&
                Objects.equals(a1114A, a11.a1114A) &&
                Objects.equals(a1114B, a11.a1114B) &&
                Objects.equals(a1117, a11.a1117) &&
                Objects.equals(a1118A, a11.a1118A) &&
                Objects.equals(a1118B, a11.a1118B) &&
                Objects.equals(a1119, a11.a1119) &&
                Objects.equals(a1121A, a11.a1121A) &&
                Objects.equals(a1121B, a11.a1121B) &&
                Objects.equals(a1124, a11.a1124) &&
                Objects.equals(a1127, a11.a1127) &&
                Objects.equals(a1131, a11.a1131) &&
                Objects.equals(a1131B, a11.a1131B) &&
                Objects.equals(a1132, a11.a1132) &&
                Objects.equals(a1134, a11.a1134) &&
                Objects.equals(a1137, a11.a1137) &&
                Objects.equals(a1141, a11.a1141) &&
                Objects.equals(a1144, a11.a1144) &&
                Objects.equals(a1144A, a11.a1144A) &&
                Objects.equals(a1147, a11.a1147) &&
                Objects.equals(a1151, a11.a1151) &&
                Objects.equals(a1154, a11.a1154) &&
                Objects.equals(aPxsm, a11.aPxsm) &&
                Objects.equals(aPxsc, a11.aPxsc) &&
                Objects.equals(aPxscdw, a11.aPxscdw) &&
                Objects.equals(aPxcca, a11.aPxcca) &&
                Objects.equals(aPxccb, a11.aPxccb) &&
                Objects.equals(aPxxf, a11.aPxxf) &&
                Objects.equals(aPxxflb, a11.aPxxflb) &&
                Objects.equals(aPxdd, a11.aPxdd) &&
                Objects.equals(aPxjtdz, a11.aPxjtdz) &&
                Objects.equals(aPxxs, a11.aPxxs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(a1100, a1101, a1101A, a1104, a1107, a1111, a1114A, a1114B, a1117, a1118A, a1118B, a1119, a1121A, a1121B, a1124, a1127, a1131, a1131B, a1132, a1134, a1137, a1141, a1144, a1144A, a1147, a1151, a1154, aPxsm, aPxsc, aPxscdw, aPxcca, aPxccb, aPxxf, aPxxflb, aPxdd, aPxjtdz, aPxxs);
    }
}
