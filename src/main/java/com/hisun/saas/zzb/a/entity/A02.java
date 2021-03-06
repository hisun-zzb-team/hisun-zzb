package com.hisun.saas.zzb.a.entity;

import com.hisun.base.entity.TombstoneEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "a02")
public class A02 extends TombstoneEntity implements Serializable {
    /** 现任职务主键 */
    private String a0200;

    /** 人员基本信息主键 */
    private A01 a01;

    /** 单位名称 */
    private String a0201A;

    /** 单位ID */
    private String a0201B;

    /** 任职机构所在政区 */
    private String a0203;

    /** 任职机构隶属关系 */
    private String a0205;

    /** 任职机构级别 */
    private String a0207;

    /** 任职机构性质类别 */
    private String a0209;

    /** 任职机构所属行业 */
    private String a0211;

    /** 任职机构统计标识 */
    private String a0213;

    /** 职务名称 */
    private String a0215A;

    /** 职务代码 */
    private String a0215B;

    /** 职务说明 */
    private String a0217;

    /** 职务类别 */
    private String a0219;

    /** 职务级别代码 */
    private String a0221;

    /** 多职务主次序号 */
    private Integer a0223;

    /** 集体内排序 */
    private Integer a0225;

    /** 分管（从事）工作 */
    private String a0229;

    /** 从事专业 */
    private String a0233;

    /** 提名任职的机关名称 */
    private String a0235A;

    /** 提名任职的机关代码 */
    private String a0235B;

    /** 提名任职的日期 */
    private String a0237;

    /** 提名任职的文号 */
    private String a0239;

    /** 决定或批准任职的机关名称 */
    private String a0241A;

    /** 决定或批准任职的机关代码 */
    private String a0241B;

    /** 决定或批准任职的日期 */
    private String a0243;

    /** 决定或批准任职的文号 */
    private String a0245;

    /** 任职方式：ZB11-1994/RZFS */
    private String a0247;

    /** 任职原因类别字典代码:ZB12-2006/RZYY */
    private String a0249;

    /** 任职原因类别 */
    private String a0249A;

    /** 任职变动类别:ZB13-2006/ZWBD */
    private String a0251;

    /** 任职变动类别内容 */
    private String a0251A;

    /** 连续任该职起始日期 */
    private String a0253;

    /** 任职状态字典代码 : ZB14-1994/RZZT */
    private String a0255;

    /** 任职状态字典内容/任职状态显示内容 */
    private String aA0255A;

    /** 提名免职的机关名称 */
    private String a0257A;

    /** 提名免职的机关代码 */
    private String a0257B;

    /** 提名免职的日期 */
    private String a0259;

    /** 提名免职的文号 */
    private String a0261;

    /** 决定或批准免职的名称 */
    private String a0263A;

    /** 决定或批准免职的机关代码 */
    private String a0263B;

    /** 决定或批准免职的日期 */
    private String a0265;

    /** 决定或批准免职的文号 */
    private String a0267;

    /** 免职方式:ZB15-1994/MZFS */
    private String a0269;

    /** 免职原因字典代码:ZB16-2006/MZYY */
    private String a0271;

    /** 免职原因显示内容 */
    private String aA0271A;

    /** 免职变动类别:ZB13-2006/ZWBD */
    private String a0273;

    /** 免职变动类别内容 */
    private String aA0273A;

    /** 该职务是否参与统计 1－参加统计 0－不参加 */
    private String a0275;

    /** 任职单位说明 */
    private String aRzdwsm;

    /** 职务名称ID */
    private String b0900;

    /** 单位职务名称 */
    private String bDwzwmc;

    /** 职务名称说明 */
    private String aZwmcsm;

    /** 管理类别 */
    private String bGllbB;

    /** 管理类别说明，单位管理类别:01－中央管理，02－省级党委管理，03－市级党委管理，04－县级党委管理，05－其他 */
    private String bGllbA;

    /** 是否占编制，1－占用，0－不占用 */
    private String bSfzb;

    /** 所占职务ID */
    private String aZbB0900;

    /** 所占职位字典名称/所占职位显示名称 */
    private String aZbDwzwmc;

    /** 所占职位机构id */
    private String aZbB0100;

    /** 所占职位机构 */
    private String aZbB0101;

    /** 编制性质字典内容 */
    private String aZbzwbzlba;

    /** 编制性质字典 */
    private String aZbzwbzlbb;

    /** 是否领导职务，1－是，0－否,默认1 */
    private String bSfldzw;

    /** 是否班子成员，1－是，0－否  */
    private String bSfbzcy;

    /** 是否有试用期,1是，0否 */
    private String aSfsy;

    /** 试用期时长 */
    private Integer aSyq;

    /** 试用期时长单位.1－日，2－月，3－年 */
    private String aSyqdw;

    /** 试用期满预警时间 */
    private String aSyqyjsj;

    /** 挂职期满时长 */
    private Integer aGzq;

    /** 挂职期满时长时间单位,1：日；2：月；3；年；4：周 */
    private String aGzqdw;

    /** 挂职期满预警时间 */
    private String aGzqyjsj;

    /** 任职文号对应的文件id */
    private String a0245B;

    /** 任职文号对应的文件名 */
    private String a0245A;

    /** 免职文号对应的文件id */
    private String a0267B;

    /** 免职文号对应的文件名 */
    private String a0267A;

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "a0200", nullable = false, length = 64)
    public String getA0200() {
        return a0200;
    }

    public void setA0200(String a0200) {
        this.a0200 = a0200;
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
    @Column(name = "a0201a", nullable = true, length = 256)
    public String getA0201A() {
        return a0201A;
    }

    public void setA0201A(String a0201A) {
        this.a0201A = a0201A;
    }

    @Basic
    @Column(name = "a0201b", nullable = true, length = 128)
    public String getA0201B() {
        return a0201B;
    }

    public void setA0201B(String a0201B) {
        this.a0201B = a0201B;
    }

    @Basic
    @Column(name = "a0203", nullable = true, length = 6)
    public String getA0203() {
        return a0203;
    }

    public void setA0203(String a0203) {
        this.a0203 = a0203;
    }

    @Basic
    @Column(name = "a0205", nullable = true, length = 2)
    public String getA0205() {
        return a0205;
    }

    public void setA0205(String a0205) {
        this.a0205 = a0205;
    }

    @Basic
    @Column(name = "a0207", nullable = true, length = 3)
    public String getA0207() {
        return a0207;
    }

    public void setA0207(String a0207) {
        this.a0207 = a0207;
    }

    @Basic
    @Column(name = "a0209", nullable = true, length = 3)
    public String getA0209() {
        return a0209;
    }

    public void setA0209(String a0209) {
        this.a0209 = a0209;
    }

    @Basic
    @Column(name = "a0211", nullable = true, length = 5)
    public String getA0211() {
        return a0211;
    }

    public void setA0211(String a0211) {
        this.a0211 = a0211;
    }

    @Basic
    @Column(name = "a0213", nullable = true, length = 1)
    public String getA0213() {
        return a0213;
    }

    public void setA0213(String a0213) {
        this.a0213 = a0213;
    }

    @Basic
    @Column(name = "a0215a", nullable = true, length = 128)
    public String getA0215A() {
        return a0215A;
    }

    public void setA0215A(String a0215A) {
        this.a0215A = a0215A;
    }

    @Basic
    @Column(name = "a0215b", nullable = true, length = 64)
    public String getA0215B() {
        return a0215B;
    }

    public void setA0215B(String a0215B) {
        this.a0215B = a0215B;
    }

    @Basic
    @Column(name = "a0217", nullable = true, length = 256)
    public String getA0217() {
        return a0217;
    }

    public void setA0217(String a0217) {
        this.a0217 = a0217;
    }

    @Basic
    @Column(name = "a0219", nullable = true, length = 1)
    public String getA0219() {
        return a0219;
    }

    public void setA0219(String a0219) {
        this.a0219 = a0219;
    }

    @Basic
    @Column(name = "a0221", nullable = true, length = 128)
    public String getA0221() {
        return a0221;
    }

    public void setA0221(String a0221) {
        this.a0221 = a0221;
    }

    @Basic
    @Column(name = "a0223", nullable = true, precision = 2)
    public Integer getA0223() {
        return a0223;
    }

    public void setA0223(Integer a0223) {
        this.a0223 = a0223;
    }

    @Basic
    @Column(name = "a0225", nullable = true, precision = 2)
    public Integer getA0225() {
        return a0225;
    }

    public void setA0225(Integer a0225) {
        this.a0225 = a0225;
    }

    @Basic
    @Column(name = "a0229", nullable = true, length = 60)
    public String getA0229() {
        return a0229;
    }

    public void setA0229(String a0229) {
        this.a0229 = a0229;
    }

    @Basic
    @Column(name = "a0233", nullable = true, length = 6)
    public String getA0233() {
        return a0233;
    }

    public void setA0233(String a0233) {
        this.a0233 = a0233;
    }

    @Basic
    @Column(name = "a0235a", nullable = true, length = 60)
    public String getA0235A() {
        return a0235A;
    }

    public void setA0235A(String a0235A) {
        this.a0235A = a0235A;
    }

    @Basic
    @Column(name = "a0235b", nullable = true, length = 15)
    public String getA0235B() {
        return a0235B;
    }

    public void setA0235B(String a0235B) {
        this.a0235B = a0235B;
    }

    @Basic
    @Column(name = "a0237", nullable = true, length = 8)
    public String getA0237() {
        return a0237;
    }

    public void setA0237(String a0237) {
        this.a0237 = a0237;
    }

    @Basic
    @Column(name = "a0239", nullable = true, length = 128)
    public String getA0239() {
        return a0239;
    }

    public void setA0239(String a0239) {
        this.a0239 = a0239;
    }

    @Basic
    @Column(name = "a0241a", nullable = true, length = 60)
    public String getA0241A() {
        return a0241A;
    }

    public void setA0241A(String a0241A) {
        this.a0241A = a0241A;
    }

    @Basic
    @Column(name = "a0241b", nullable = true, length = 15)
    public String getA0241B() {
        return a0241B;
    }

    public void setA0241B(String a0241B) {
        this.a0241B = a0241B;
    }

    @Basic
    @Column(name = "a0243", nullable = true, length = 8)
    public String getA0243() {
        return a0243;
    }

    public void setA0243(String a0243) {
        this.a0243 = a0243;
    }

    @Basic
    @Column(name = "a0245", nullable = true, length = 256)
    public String getA0245() {
        return a0245;
    }

    public void setA0245(String a0245) {
        this.a0245 = a0245;
    }

    @Basic
    @Column(name = "a0247", nullable = true, length = 1)
    public String getA0247() {
        return a0247;
    }

    public void setA0247(String a0247) {
        this.a0247 = a0247;
    }

    @Basic
    @Column(name = "a0249", nullable = true, length = 16)
    public String getA0249() {
        return a0249;
    }

    public void setA0249(String a0249) {
        this.a0249 = a0249;
    }

    @Basic
    @Column(name = "a0249a", nullable = true, length = 64)
    public String getA0249A() {
        return a0249A;
    }

    public void setA0249A(String a0249A) {
        this.a0249A = a0249A;
    }

    @Basic
    @Column(name = "a0251", nullable = true, length = 2)
    public String getA0251() {
        return a0251;
    }

    public void setA0251(String a0251) {
        this.a0251 = a0251;
    }

    @Basic
    @Column(name = "a0251a", nullable = true, length = 32)
    public String getA0251A() {
        return a0251A;
    }

    public void setA0251A(String a0251A) {
        this.a0251A = a0251A;
    }

    @Basic
    @Column(name = "a0253", nullable = true, length = 8)
    public String getA0253() {
        return a0253;
    }

    public void setA0253(String a0253) {
        this.a0253 = a0253;
    }

    @Basic
    @Column(name = "a0255", nullable = true, length = 128)
    public String getA0255() {
        return a0255;
    }

    public void setA0255(String a0255) {
        this.a0255 = a0255;
    }

    @Basic
    @Column(name = "a_a0255a", nullable = true, length = 32)
    public String getaA0255A() {
        return aA0255A;
    }

    public void setaA0255A(String aA0255A) {
        this.aA0255A = aA0255A;
    }

    @Basic
    @Column(name = "a0257a", nullable = true, length = 60)
    public String getA0257A() {
        return a0257A;
    }

    public void setA0257A(String a0257A) {
        this.a0257A = a0257A;
    }

    @Basic
    @Column(name = "a0257b", nullable = true, length = 15)
    public String getA0257B() {
        return a0257B;
    }

    public void setA0257B(String a0257B) {
        this.a0257B = a0257B;
    }

    @Basic
    @Column(name = "a0259", nullable = true, length = 8)
    public String getA0259() {
        return a0259;
    }

    public void setA0259(String a0259) {
        this.a0259 = a0259;
    }

    @Basic
    @Column(name = "a0261", nullable = true, length = 128)
    public String getA0261() {
        return a0261;
    }

    public void setA0261(String a0261) {
        this.a0261 = a0261;
    }

    @Basic
    @Column(name = "a0263a", nullable = true, length = 60)
    public String getA0263A() {
        return a0263A;
    }

    public void setA0263A(String a0263A) {
        this.a0263A = a0263A;
    }

    @Basic
    @Column(name = "a0263b", nullable = true, length = 15)
    public String getA0263B() {
        return a0263B;
    }

    public void setA0263B(String a0263B) {
        this.a0263B = a0263B;
    }

    @Basic
    @Column(name = "a0265", nullable = true, length = 8)
    public String getA0265() {
        return a0265;
    }

    public void setA0265(String a0265) {
        this.a0265 = a0265;
    }

    @Basic
    @Column(name = "a0267", nullable = true, length = 24)
    public String getA0267() {
        return a0267;
    }

    public void setA0267(String a0267) {
        this.a0267 = a0267;
    }

    @Basic
    @Column(name = "a0269", nullable = true, length = 1)
    public String getA0269() {
        return a0269;
    }

    public void setA0269(String a0269) {
        this.a0269 = a0269;
    }

    @Basic
    @Column(name = "a0271", nullable = true, length = 2)
    public String getA0271() {
        return a0271;
    }

    public void setA0271(String a0271) {
        this.a0271 = a0271;
    }

    @Basic
    @Column(name = "a_a0271a", nullable = true, length = 128)
    public String getaA0271A() {
        return aA0271A;
    }

    public void setaA0271A(String aA0271A) {
        this.aA0271A = aA0271A;
    }

    @Basic
    @Column(name = "a0273", nullable = true, length = 2)
    public String getA0273() {
        return a0273;
    }

    public void setA0273(String a0273) {
        this.a0273 = a0273;
    }

    @Basic
    @Column(name = "a_a0273a", nullable = true, length = 128)
    public String getaA0273A() {
        return aA0273A;
    }

    public void setaA0273A(String aA0273A) {
        this.aA0273A = aA0273A;
    }

    @Basic
    @Column(name = "a0275", nullable = true, length = 1)
    public String getA0275() {
        return a0275;
    }

    public void setA0275(String a0275) {
        this.a0275 = a0275;
    }

    @Basic
    @Column(name = "a_rzdwsm", nullable = true, length = 256)
    public String getaRzdwsm() {
        return aRzdwsm;
    }

    public void setaRzdwsm(String aRzdwsm) {
        this.aRzdwsm = aRzdwsm;
    }

    @Basic
    @Column(name = "b0900", nullable = true, length = 64)
    public String getB0900() {
        return b0900;
    }

    public void setB0900(String b0900) {
        this.b0900 = b0900;
    }

    @Basic
    @Column(name = "b_dwzwmc", nullable = true, length = 128)
    public String getbDwzwmc() {
        return bDwzwmc;
    }

    public void setbDwzwmc(String bDwzwmc) {
        this.bDwzwmc = bDwzwmc;
    }

    @Basic
    @Column(name = "a_zwmcsm", nullable = true, length = 128)
    public String getaZwmcsm() {
        return aZwmcsm;
    }

    public void setaZwmcsm(String aZwmcsm) {
        this.aZwmcsm = aZwmcsm;
    }

    @Basic
    @Column(name = "b_gllb_b", nullable = true, length = 128)
    public String getbGllbB() {
        return bGllbB;
    }

    public void setbGllbB(String bGllbB) {
        this.bGllbB = bGllbB;
    }

    @Basic
    @Column(name = "b_gllb_a", nullable = true, length = 128)
    public String getbGllbA() {
        return bGllbA;
    }

    public void setbGllbA(String bGllbA) {
        this.bGllbA = bGllbA;
    }

    @Basic
    @Column(name = "b_sfzb", nullable = true, length = 1)
    public String getbSfzb() {
        return bSfzb;
    }

    public void setbSfzb(String bSfzb) {
        this.bSfzb = bSfzb;
    }

    @Basic
    @Column(name = "a_zb_b0900", nullable = true, length = 128)
    public String getaZbB0900() {
        return aZbB0900;
    }

    public void setaZbB0900(String aZbB0900) {
        this.aZbB0900 = aZbB0900;
    }

    @Basic
    @Column(name = "a_zb_dwzwmc", nullable = true, length = 256)
    public String getaZbDwzwmc() {
        return aZbDwzwmc;
    }

    public void setaZbDwzwmc(String aZbDwzwmc) {
        this.aZbDwzwmc = aZbDwzwmc;
    }

    @Basic
    @Column(name = "a_zb_b0100", nullable = true, length = 128)
    public String getaZbB0100() {
        return aZbB0100;
    }

    public void setaZbB0100(String aZbB0100) {
        this.aZbB0100 = aZbB0100;
    }

    @Basic
    @Column(name = "a_zb_b0101", nullable = true, length = 512)
    public String getaZbB0101() {
        return aZbB0101;
    }

    public void setaZbB0101(String aZbB0101) {
        this.aZbB0101 = aZbB0101;
    }

    @Basic
    @Column(name = "a_zbzwbzlba", nullable = true, length = 64)
    public String getaZbzwbzlba() {
        return aZbzwbzlba;
    }

    public void setaZbzwbzlba(String aZbzwbzlba) {
        this.aZbzwbzlba = aZbzwbzlba;
    }

    @Basic
    @Column(name = "a_zbzwbzlbb", nullable = true, length = 32)
    public String getaZbzwbzlbb() {
        return aZbzwbzlbb;
    }

    public void setaZbzwbzlbb(String aZbzwbzlbb) {
        this.aZbzwbzlbb = aZbzwbzlbb;
    }

    @Basic
    @Column(name = "b_sfldzw", nullable = true, length = 2)
    public String getbSfldzw() {
        return bSfldzw;
    }

    public void setbSfldzw(String bSfldzw) {
        this.bSfldzw = bSfldzw;
    }

    @Basic
    @Column(name = "b_sfbzcy", nullable = true, length = 1)
    public String getbSfbzcy() {
        return bSfbzcy;
    }

    public void setbSfbzcy(String bSfbzcy) {
        this.bSfbzcy = bSfbzcy;
    }

    @Basic
    @Column(name = "a_sfsy", nullable = true, length = 1)
    public String getaSfsy() {
        return aSfsy;
    }

    public void setaSfsy(String aSfsy) {
        this.aSfsy = aSfsy;
    }

    @Basic
    @Column(name = "a_syq", nullable = true, precision = 2)
    public Integer getaSyq() {
        return aSyq;
    }

    public void setaSyq(Integer aSyq) {
        this.aSyq = aSyq;
    }

    @Basic
    @Column(name = "a_syqdw", nullable = true, length = 1)
    public String getaSyqdw() {
        return aSyqdw;
    }

    public void setaSyqdw(String aSyqdw) {
        this.aSyqdw = aSyqdw;
    }

    @Basic
    @Column(name = "a_syqyjsj", nullable = true, length = 32)
    public String getaSyqyjsj() {
        return aSyqyjsj;
    }

    public void setaSyqyjsj(String aSyqyjsj) {
        this.aSyqyjsj = aSyqyjsj;
    }

    @Basic
    @Column(name = "a_gzq", nullable = true, precision = 2)
    public Integer getaGzq() {
        return aGzq;
    }

    public void setaGzq(Integer aGzq) {
        this.aGzq = aGzq;
    }

    @Basic
    @Column(name = "a_gzqdw", nullable = true, length = 1)
    public String getaGzqdw() {
        return aGzqdw;
    }

    public void setaGzqdw(String aGzqdw) {
        this.aGzqdw = aGzqdw;
    }

    @Basic
    @Column(name = "a_gzqyjsj", nullable = true, length = 32)
    public String getaGzqyjsj() {
        return aGzqyjsj;
    }

    public void setaGzqyjsj(String aGzqyjsj) {
        this.aGzqyjsj = aGzqyjsj;
    }

    @Basic
    @Column(name = "a0245_b", nullable = true, length = 2048)
    public String getA0245B() {
        return a0245B;
    }

    public void setA0245B(String a0245B) {
        this.a0245B = a0245B;
    }

    @Basic
    @Column(name = "a0245_a", nullable = true, length = 2048)
    public String getA0245A() {
        return a0245A;
    }

    public void setA0245A(String a0245A) {
        this.a0245A = a0245A;
    }

    @Basic
    @Column(name = "a0267_b", nullable = true, length = 2048)
    public String getA0267B() {
        return a0267B;
    }

    public void setA0267B(String a0267B) {
        this.a0267B = a0267B;
    }

    @Basic
    @Column(name = "a0267_a", nullable = true, length = 2048)
    public String getA0267A() {
        return a0267A;
    }

    public void setA0267A(String a0267A) {
        this.a0267A = a0267A;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A02 a02 = (A02) o;
        return Objects.equals(a0200, a02.a0200) &&
                Objects.equals(a0201A, a02.a0201A) &&
                Objects.equals(a0201B, a02.a0201B) &&
                Objects.equals(a0203, a02.a0203) &&
                Objects.equals(a0205, a02.a0205) &&
                Objects.equals(a0207, a02.a0207) &&
                Objects.equals(a0209, a02.a0209) &&
                Objects.equals(a0211, a02.a0211) &&
                Objects.equals(a0213, a02.a0213) &&
                Objects.equals(a0215A, a02.a0215A) &&
                Objects.equals(a0215B, a02.a0215B) &&
                Objects.equals(a0217, a02.a0217) &&
                Objects.equals(a0219, a02.a0219) &&
                Objects.equals(a0221, a02.a0221) &&
                Objects.equals(a0223, a02.a0223) &&
                Objects.equals(a0225, a02.a0225) &&
                Objects.equals(a0229, a02.a0229) &&
                Objects.equals(a0233, a02.a0233) &&
                Objects.equals(a0235A, a02.a0235A) &&
                Objects.equals(a0235B, a02.a0235B) &&
                Objects.equals(a0237, a02.a0237) &&
                Objects.equals(a0239, a02.a0239) &&
                Objects.equals(a0241A, a02.a0241A) &&
                Objects.equals(a0241B, a02.a0241B) &&
                Objects.equals(a0243, a02.a0243) &&
                Objects.equals(a0245, a02.a0245) &&
                Objects.equals(a0247, a02.a0247) &&
                Objects.equals(a0249, a02.a0249) &&
                Objects.equals(a0249A, a02.a0249A) &&
                Objects.equals(a0251, a02.a0251) &&
                Objects.equals(a0251A, a02.a0251A) &&
                Objects.equals(a0253, a02.a0253) &&
                Objects.equals(a0255, a02.a0255) &&
                Objects.equals(aA0255A, a02.aA0255A) &&
                Objects.equals(a0257A, a02.a0257A) &&
                Objects.equals(a0257B, a02.a0257B) &&
                Objects.equals(a0259, a02.a0259) &&
                Objects.equals(a0261, a02.a0261) &&
                Objects.equals(a0263A, a02.a0263A) &&
                Objects.equals(a0263B, a02.a0263B) &&
                Objects.equals(a0265, a02.a0265) &&
                Objects.equals(a0267, a02.a0267) &&
                Objects.equals(a0269, a02.a0269) &&
                Objects.equals(a0271, a02.a0271) &&
                Objects.equals(aA0271A, a02.aA0271A) &&
                Objects.equals(a0273, a02.a0273) &&
                Objects.equals(aA0273A, a02.aA0273A) &&
                Objects.equals(a0275, a02.a0275) &&
                Objects.equals(aRzdwsm, a02.aRzdwsm) &&
                Objects.equals(b0900, a02.b0900) &&
                Objects.equals(bDwzwmc, a02.bDwzwmc) &&
                Objects.equals(aZwmcsm, a02.aZwmcsm) &&
                Objects.equals(bGllbB, a02.bGllbB) &&
                Objects.equals(bGllbA, a02.bGllbA) &&
                Objects.equals(bSfzb, a02.bSfzb) &&
                Objects.equals(aZbB0900, a02.aZbB0900) &&
                Objects.equals(aZbDwzwmc, a02.aZbDwzwmc) &&
                Objects.equals(aZbB0100, a02.aZbB0100) &&
                Objects.equals(aZbB0101, a02.aZbB0101) &&
                Objects.equals(aZbzwbzlba, a02.aZbzwbzlba) &&
                Objects.equals(aZbzwbzlbb, a02.aZbzwbzlbb) &&
                Objects.equals(bSfldzw, a02.bSfldzw) &&
                Objects.equals(bSfbzcy, a02.bSfbzcy) &&
                Objects.equals(aSfsy, a02.aSfsy) &&
                Objects.equals(aSyq, a02.aSyq) &&
                Objects.equals(aSyqdw, a02.aSyqdw) &&
                Objects.equals(aSyqyjsj, a02.aSyqyjsj) &&
                Objects.equals(aGzq, a02.aGzq) &&
                Objects.equals(aGzqdw, a02.aGzqdw) &&
                Objects.equals(aGzqyjsj, a02.aGzqyjsj) &&
                Objects.equals(a0245B, a02.a0245B) &&
                Objects.equals(a0245A, a02.a0245A) &&
                Objects.equals(a0267B, a02.a0267B) &&
                Objects.equals(a0267A, a02.a0267A);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a0200, a0201A, a0201B, a0203, a0205, a0207, a0209, a0211, a0213, a0215A, a0215B, a0217, a0219, a0221, a0223, a0225, a0229, a0233, a0235A, a0235B, a0237, a0239, a0241A, a0241B, a0243, a0245, a0247, a0249, a0249A, a0251, a0251A, a0253, a0255, aA0255A, a0257A, a0257B, a0259, a0261, a0263A, a0263B, a0265, a0267, a0269, a0271, aA0271A, a0273, aA0273A, a0275, aRzdwsm, b0900, bDwzwmc, aZwmcsm, bGllbB, bGllbA, bSfzb, aZbB0900, aZbDwzwmc, aZbB0100, aZbB0101, aZbzwbzlba, aZbzwbzlbb, bSfldzw, bSfbzcy, aSfsy, aSyq, aSyqdw, aSyqyjsj, aGzq, aGzqdw, aGzqyjsj, a0245B, a0245A, a0267B, a0267A);
    }
}
