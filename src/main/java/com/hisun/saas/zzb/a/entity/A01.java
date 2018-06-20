package com.hisun.saas.zzb.a.entity;

import com.hisun.base.entity.TombstoneEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "a01")
public class A01 extends TombstoneEntity implements Serializable {
    /** 人员基本信息主键 */
    private String a0100;

    /** 姓名 */
    private String a0101;

    /** 姓名拼音缩写 */
    private String a0102;

    /** 性别字典代码:GB/T2261.1-2003 */
    private String a0104;

    /** 性别字典内容/性别显示内容 */
    private String a0104A;

    /** 出生日期 */
    private String a0107;

    /** 籍贯名称 */
    private String a0111A;

    /** 籍贯字典代码:ZB01-2006/GQMC */
    private String a0111B;

    /** 出生地字典内容 */
    private String a0114A;

    /** 出生地代码:ZB01-2006/GQMC */
    private String a0114B;

    /** 成长地显示内容 */
    private String a0115A;

    /** 成长地字典代码:ZB01-2006/GQMC */
    private String a0115B;

    /** 民族字典代码:GB3304-1991 */
    private String a0117;

    /** 民族字典内容/民族显示内容 */
    private String a0117A;

    /** 健康状况字典代码 */
    private String a0127;

    /** 健康状况显示内容:GB/T2261.3-2003 */
    private String a0127A;

    /** 健康状况描述 */
    private String a0128;

    /** 婚姻状况字典代码:GB/T2261.2-2003 */
    private String a0131;

    /** 婚姻状况字典内容/婚姻状况显示内容 */
    private String a0131A;

    /** 参加工作单位 */
    private String aCjgzdw;

    /** 参加工作日期 */
    private String a0134;

    /** 工龄计算较值 */
    private java.math.BigDecimal a0137;

    /** 政治面貌：GB4762-1984 */
    private String a0141;

    /** 政治面貌内容*/
    private String a0141A;

    /** 参加组织日期 */
    private String a0144;

    /** 个人身份：GB/T2261.4-2003 */
    private String a0151;

    /** 现身份起始日期 */
    private String a0154;

    /** 人事关系所在单位名称 */
    private String a0157A;

    /** 人事关系所在单位代码：ZB02-2006/JGMC */
    private String a0157B;

    /** 人事关系所在单位所在政区：ZB01-2006/GQMC */
    private String a0161;

    /** 人事关系所在单位所在政区名称 */
    private String a0161A;

    /** 人事关系所在单位隶属关系：ZB87-2006/DWLS */
    private String a0164;

    /** 人事关系所在单位隶属关系*/
    private String a0164A;

    /** 人事关系所在单位级别ZB03-2006/DWJB */
    private String a0167;

    /** 人事关系所在单位级别 */
    private String a0167A;

    /** 人事关系所在单位性质：ZB04-2006/DWXZ */
    private String a0171;

    /** 人事关系所在单位性质*/
    private String a0171A;

    /** 人事关系所在单位所属行业：GB/T4754-2002 */
    private String a0174;

    /** 人事关系所在单位所属行业 */
    private String a0174A;

    /** 户口性质：ZB07-2006/HKXZ */
    private String a0177;

    /** 户口性质*/
    private String a0177A;

    /** 户籍所在地：ZB01-2006/GQMC */
    private String a0181A;

    /** 户籍所在地代码 */
    private String a0181B;

    /** 有效证件类别：ZB76-2006/YXZJ */
    private String a0183;

    /** 证件号码 */
    private String a0184;

    /** 专长 */
    private String a0187A;

    /** 爱好 */
    private String a0187B;

    /** 声像信息标识 */
    private String a0191;

    /** 照片格式 */
    private String aZpgs;

    /** 照片大小 */
    private java.math.BigDecimal aZpdx;

    /** 照片路径 */
    private String aZplj;

    /** 现职级 */
    private String a0501A;

    /** 现职级代码：ZB09-2006/ZWJB  */
    private String a0501B;

    /** 现职级时间 */
    private String a0504;

    /** 现职级说明 */
    private String aZjsm;

    /** 现职务层次 */
    private String axzwcca;

    /** 现职务层次代码 */
    private String aXzwccb;

    /** 现职级时间 */
    private String aXzwccsj;

    /** 是否公务员，是-1，否-0 */
    private String aSfgwy;

    /** 公务员登记时间 */
    private String aGwydjsj;

    /** 干部状态代码 */
    private String aGbztb;

    /** 干部状态显示内容 */
    private String aGbzta;

    /** 进入当前单位时间 */
    private String aJrdqdwsj;

    /** 干部来源显示内容 */
    private String aGblya;

    /** 干部来源字典代码 */
    private String aGblyb;

    /** 管理类别代码 */
    private String aGllbB;

    /** 管理类别说明，单位管理类别:01－中央管理，02－省级党委管理，03－市级党委管理，04－县级党委管理，05－其他 */
    private String aGllbA;

    /** 是否有电子档案，0-否，1-是，默认0 */
    private String aSfydzda;

    /** 是否已审核。0：未审核；1：已审核 */
    private String aSfysh;

    /** 是否后备干部 0-否，1-是，默认0 */
    private String aSfhbgb;

    /** 是否需要审核学历学位 1：需审核，0：不需审核 */
    private String aSfyshxlxw;

    /** 是否共产党员。 是：1；否：0  */
    private String aSfzgdy;

    /** 最高在职学历code */
    private String aZzxlb;

    /** 最高在职学历文本 */
    private String aZzxla;

    /** 最高在职学位code */
    private String aZzxwb;

    /** 最高在职学位文本 */
    private String aZzxwa;

    /** 在职毕业院校及专业 */
    private String aZzbyyxjzy;

    /** 最高全日制学历code */
    private String aQrzxlb;

    /** 最高全日制学历文本 */
    private String aQrzxla;

    /** 最高全日制学位code */
    private String aQrzxwb;

    /** 最高全日制学位文本 */
    private String aQrzxwa;

    /** 全日制毕业院校及专业 */
    private String aQrzbyyxjzy;

    /** 最高学历code */
    private String aZgxlb;

    /** 最高学历文本 */
    private String aZgxla;

    /** 最高学位code */
    private String aZgxwb;

    /** 最高学位文本 */
    private String aZgxwa;

    /** 最高学历学位毕业院校及专业 */
    private String aZgbyyxjzy;

    /** 单位及职务描述（用于名册） */
    private String aDwjzwMc;

    /** 单位及职务描述（用于干部任免审批表） */
    private String aDwjzwSpb;

    /** 简历情况 */
    private String aJl;

    /** 兼任社会职务情况 */
    private String aJrshzwqk;

    /** 当选会议代表情况 */
    private String aDxdbqk;

    /** 出国境情况 */
    private String aCgjqk;

    /** 奖励情况 */
    private String aJlqk;

    /** 惩戒情况 */
    private String aCjqk;

    /** 年度考核情况 */
    private String aNdkhqk;

    /** 培训情况 */
    private String aPxqk;

    /** 科研成果、著作发明 */
    private String aKycgqk;

    /** 职称情况 */
    private String aZcqk;

    //职务信息
    private List<A02> a02s;

    @OneToMany(mappedBy = "a01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<A02> getA02s() {
        return a02s;
    }

    public void setA02s(List<A02> a02s) {
        this.a02s = a02s;
    }

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "a0100", nullable = false, length = 64)
    public String getA0100() {
        return a0100;
    }

    public void setA0100(String a0100) {
        this.a0100 = a0100;
    }

    @Basic
    @Column(name = "a0101", nullable = true, length = 100)
    public String getA0101() {
        return a0101;
    }

    public void setA0101(String a0101) {
        this.a0101 = a0101;
    }

    @Basic
    @Column(name = "a0102", nullable = true, length = 6)
    public String getA0102() {
        return a0102;
    }

    public void setA0102(String a0102) {
        this.a0102 = a0102;
    }

    @Basic
    @Column(name = "a0104", nullable = true, length = 128)
    public String getA0104() {
        return a0104;
    }

    public void setA0104(String a0104) {
        this.a0104 = a0104;
    }

    @Basic
    @Column(name = "a0104a", nullable = true, length = 4)
    public String getA0104A() {
        return a0104A;
    }

    public void setA0104A(String a0104A) {
        this.a0104A = a0104A;
    }

    @Basic
    @Column(name = "a0107", nullable = true, length = 8)
    public String getA0107() {
        return a0107;
    }

    public void setA0107(String a0107) {
        this.a0107 = a0107;
    }

    @Basic
    @Column(name = "a0111a", nullable = true, length = 64)
    public String getA0111A() {
        return a0111A;
    }

    public void setA0111A(String a0111A) {
        this.a0111A = a0111A;
    }

    @Basic
    @Column(name = "a0111b", nullable = true, length = 128)
    public String getA0111B() {
        return a0111B;
    }

    public void setA0111B(String a0111B) {
        this.a0111B = a0111B;
    }

    @Basic
    @Column(name = "a0114a", nullable = true, length = 128)
    public String getA0114A() {
        return a0114A;
    }

    public void setA0114A(String a0114A) {
        this.a0114A = a0114A;
    }

    @Basic
    @Column(name = "a0114b", nullable = true, length = 128)
    public String getA0114B() {
        return a0114B;
    }

    public void setA0114B(String a0114B) {
        this.a0114B = a0114B;
    }

    @Basic
    @Column(name = "a0115a", nullable = true, length = 128)
    public String getA0115A() {
        return a0115A;
    }

    public void setA0115A(String a0115A) {
        this.a0115A = a0115A;
    }

    @Basic
    @Column(name = "a0115b", nullable = true, length = 128)
    public String getA0115B() {
        return a0115B;
    }

    public void setA0115B(String a0115B) {
        this.a0115B = a0115B;
    }

    @Basic
    @Column(name = "a0117", nullable = true, length = 128)
    public String getA0117() {
        return a0117;
    }

    public void setA0117(String a0117) {
        this.a0117 = a0117;
    }

    @Basic
    @Column(name = "a0117a", nullable = true, length = 32)
    public String getA0117A() {
        return a0117A;
    }

    public void setA0117A(String a0117A) {
        this.a0117A = a0117A;
    }

    @Basic
    @Column(name = "a0127", nullable = true, length = 128)
    public String getA0127() {
        return a0127;
    }

    public void setA0127(String a0127) {
        this.a0127 = a0127;
    }

    @Basic
    @Column(name = "a0127a", nullable = true, length = 256)
    public String getA0127A() {
        return a0127A;
    }

    public void setA0127A(String a0127A) {
        this.a0127A = a0127A;
    }

    @Basic
    @Column(name = "a0128", nullable = true, length = 60)
    public String getA0128() {
        return a0128;
    }

    public void setA0128(String a0128) {
        this.a0128 = a0128;
    }

    @Basic
    @Column(name = "a0131", nullable = true, length = 128)
    public String getA0131() {
        return a0131;
    }

    public void setA0131(String a0131) {
        this.a0131 = a0131;
    }

    @Basic
    @Column(name = "a0131a", nullable = true, length = 16)
    public String getA0131A() {
        return a0131A;
    }

    public void setA0131A(String a0131A) {
        this.a0131A = a0131A;
    }

    @Basic
    @Column(name = "a_cjgzdw", nullable = true, length = 512)
    public String getaCjgzdw() {
        return aCjgzdw;
    }

    public void setaCjgzdw(String aCjgzdw) {
        this.aCjgzdw = aCjgzdw;
    }

    @Basic
    @Column(name = "a0134", nullable = true, length = 8)
    public String getA0134() {
        return a0134;
    }

    public void setA0134(String a0134) {
        this.a0134 = a0134;
    }

    @Basic
    @Column(name = "a0137", nullable = true, precision = 2)
    public BigDecimal getA0137() {
        return a0137;
    }

    public void setA0137(BigDecimal a0137) {
        this.a0137 = a0137;
    }

    @Basic
    @Column(name = "a0141", nullable = true, length = 2)
    public String getA0141() {
        return a0141;
    }

    public void setA0141(String a0141) {
        this.a0141 = a0141;
    }

    @Basic
    @Column(name = "a0141a", nullable = true, length = 256)
    public String getA0141A() {
        return a0141A;
    }

    public void setA0141A(String a0141A) {
        this.a0141A = a0141A;
    }

    @Basic
    @Column(name = "a0144", nullable = true, length = 8)
    public String getA0144() {
        return a0144;
    }

    public void setA0144(String a0144) {
        this.a0144 = a0144;
    }

    @Basic
    @Column(name = "a0151", nullable = true, length = 2)
    public String getA0151() {
        return a0151;
    }

    public void setA0151(String a0151) {
        this.a0151 = a0151;
    }

    @Basic
    @Column(name = "a0154", nullable = true, length = 8)
    public String getA0154() {
        return a0154;
    }

    public void setA0154(String a0154) {
        this.a0154 = a0154;
    }

    @Basic
    @Column(name = "a0157a", nullable = true, length = 60)
    public String getA0157A() {
        return a0157A;
    }

    public void setA0157A(String a0157A) {
        this.a0157A = a0157A;
    }

    @Basic
    @Column(name = "a0157b", nullable = true, length = 15)
    public String getA0157B() {
        return a0157B;
    }

    public void setA0157B(String a0157B) {
        this.a0157B = a0157B;
    }

    @Basic
    @Column(name = "a0161", nullable = true, length = 6)
    public String getA0161() {
        return a0161;
    }

    public void setA0161(String a0161) {
        this.a0161 = a0161;
    }

    @Basic
    @Column(name = "a0164", nullable = true, length = 2)
    public String getA0164() {
        return a0164;
    }

    public void setA0164(String a0164) {
        this.a0164 = a0164;
    }

    @Basic
    @Column(name = "a0167", nullable = true, length = 3)
    public String getA0167() {
        return a0167;
    }

    public void setA0167(String a0167) {
        this.a0167 = a0167;
    }

    @Basic
    @Column(name = "a0171", nullable = true, length = 3)
    public String getA0171() {
        return a0171;
    }

    public void setA0171(String a0171) {
        this.a0171 = a0171;
    }

    @Basic
    @Column(name = "a0174", nullable = true, length = 5)
    public String getA0174() {
        return a0174;
    }

    public void setA0174(String a0174) {
        this.a0174 = a0174;
    }

    @Basic
    @Column(name = "a0177", nullable = true, length = 1)
    public String getA0177() {
        return a0177;
    }

    public void setA0177(String a0177) {
        this.a0177 = a0177;
    }
    @Basic
    @Column(name = "a0161a", nullable = true, length = 256)
    public String getA0161A() {
        return a0161A;
    }

    public void setA0161A(String a0161A) {
        this.a0161A = a0161A;
    }

    @Basic
    @Column(name = "a0164a", nullable = true, length = 256)
    public String getA0164A() {
        return a0164A;
    }

    public void setA0164A(String a0164A) {
        this.a0164A = a0164A;
    }

    @Basic
    @Column(name = "a0167a", nullable = true, length = 256)
    public String getA0167A() {
        return a0167A;
    }

    public void setA0167A(String a0167A) {
        this.a0167A = a0167A;
    }

    @Basic
    @Column(name = "a0171a", nullable = true, length = 256)
    public String getA0171A() {
        return a0171A;
    }

    public void setA0171A(String a0171A) {
        this.a0171A = a0171A;
    }

    @Basic
    @Column(name = "a0174a", nullable = true, length = 256)
    public String getA0174A() {
        return a0174A;
    }

    public void setA0174A(String a0174A) {
        this.a0174A = a0174A;
    }

    @Basic
    @Column(name = "a0177a", nullable = true, length = 256)
    public String getA0177A() {
        return a0177A;
    }

    public void setA0177A(String a0177A) {
        this.a0177A = a0177A;
    }

    @Basic
    @Column(name = "a0181a", nullable = true, length = 6)
    public String getA0181A() {
        return a0181A;
    }

    public void setA0181A(String a0181A) {
        this.a0181A = a0181A;
    }

    @Basic
    @Column(name = "a0181b", nullable = true, length = 15)
    public String getA0181B() {
        return a0181B;
    }

    public void setA0181B(String a0181B) {
        this.a0181B = a0181B;
    }

    @Basic
    @Column(name = "a0183", nullable = true, length = 1)
    public String getA0183() {
        return a0183;
    }

    public void setA0183(String a0183) {
        this.a0183 = a0183;
    }

    @Basic
    @Column(name = "a0184", nullable = true, length = 30)
    public String getA0184() {
        return a0184;
    }

    public void setA0184(String a0184) {
        this.a0184 = a0184;
    }

    @Basic
    @Column(name = "a0187a", nullable = true, length = 256)
    public String getA0187A() {
        return a0187A;
    }

    public void setA0187A(String a0187A) {
        this.a0187A = a0187A;
    }

    @Basic
    @Column(name = "a0187b", nullable = true, length = 1024)
    public String getA0187B() {
        return a0187B;
    }

    public void setA0187B(String a0187B) {
        this.a0187B = a0187B;
    }

    @Basic
    @Column(name = "a0191", nullable = true, length = 1)
    public String getA0191() {
        return a0191;
    }

    public void setA0191(String a0191) {
        this.a0191 = a0191;
    }

    @Basic
    @Column(name = "a_zpgs", nullable = true, length = 32)
    public String getaZpgs() {
        return aZpgs;
    }

    public void setaZpgs(String aZpgs) {
        this.aZpgs = aZpgs;
    }

    @Basic
    @Column(name = "a_zpdx", nullable = true, precision = 2)
    public BigDecimal getaZpdx() {
        return aZpdx;
    }

    public void setaZpdx(BigDecimal aZpdx) {
        this.aZpdx = aZpdx;
    }

    @Basic
    @Column(name = "a_zplj", nullable = true, length = 256)
    public String getaZplj() {
        return aZplj;
    }

    public void setaZplj(String aZplj) {
        this.aZplj = aZplj;
    }

    @Basic
    @Column(name = "a0501a", nullable = true, length = 30)
    public String getA0501A() {
        return a0501A;
    }

    public void setA0501A(String a0501A) {
        this.a0501A = a0501A;
    }

    @Basic
    @Column(name = "a0501b", nullable = true, length = 4)
    public String getA0501B() {
        return a0501B;
    }

    public void setA0501B(String a0501B) {
        this.a0501B = a0501B;
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
    @Column(name = "a_zjsm", nullable = true, length = 128)
    public String getaZjsm() {
        return aZjsm;
    }

    public void setaZjsm(String aZjsm) {
        this.aZjsm = aZjsm;
    }

    @Basic
    @Column(name = "axzwcca", nullable = true, length = 30)
    public String getAxzwcca() {
        return axzwcca;
    }

    public void setAxzwcca(String axzwcca) {
        this.axzwcca = axzwcca;
    }

    @Basic
    @Column(name = "a_xzwccb", nullable = true, length = 4)
    public String getaXzwccb() {
        return aXzwccb;
    }

    public void setaXzwccb(String aXzwccb) {
        this.aXzwccb = aXzwccb;
    }

    @Basic
    @Column(name = "a_xzwccsj", nullable = true, length = 8)
    public String getaXzwccsj() {
        return aXzwccsj;
    }

    public void setaXzwccsj(String aXzwccsj) {
        this.aXzwccsj = aXzwccsj;
    }

    @Basic
    @Column(name = "a_sfgwy", nullable = true, length = 1)
    public String getaSfgwy() {
        return aSfgwy;
    }

    public void setaSfgwy(String aSfgwy) {
        this.aSfgwy = aSfgwy;
    }

    @Basic
    @Column(name = "a_gwydjsj", nullable = true, length = 8)
    public String getaGwydjsj() {
        return aGwydjsj;
    }

    public void setaGwydjsj(String aGwydjsj) {
        this.aGwydjsj = aGwydjsj;
    }

    @Basic
    @Column(name = "a_gbztb", nullable = true, length = 8)
    public String getaGbztb() {
        return aGbztb;
    }

    public void setaGbztb(String aGbztb) {
        this.aGbztb = aGbztb;
    }

    @Basic
    @Column(name = "a_gbzta", nullable = true, length = 256)
    public String getaGbzta() {
        return aGbzta;
    }

    public void setaGbzta(String aGbzta) {
        this.aGbzta = aGbzta;
    }

    @Basic
    @Column(name = "a_jrdqdwsj", nullable = true, length = 8)
    public String getaJrdqdwsj() {
        return aJrdqdwsj;
    }

    public void setaJrdqdwsj(String aJrdqdwsj) {
        this.aJrdqdwsj = aJrdqdwsj;
    }

    @Basic
    @Column(name = "a_gblya", nullable = true, length = 512)
    public String getaGblya() {
        return aGblya;
    }

    public void setaGblya(String aGblya) {
        this.aGblya = aGblya;
    }

    @Basic
    @Column(name = "a_gblyb", nullable = true, length = 128)
    public String getaGblyb() {
        return aGblyb;
    }

    public void setaGblyb(String aGblyb) {
        this.aGblyb = aGblyb;
    }

    @Basic
    @Column(name = "a_gllb_b", nullable = true, length = 128)
    public String getaGllbB() {
        return aGllbB;
    }

    public void setaGllbB(String aGllbB) {
        this.aGllbB = aGllbB;
    }

    @Basic
    @Column(name = "a_gllb_a", nullable = true, length = 128)
    public String getaGllbA() {
        return aGllbA;
    }

    public void setaGllbA(String aGllbA) {
        this.aGllbA = aGllbA;
    }

    @Basic
    @Column(name = "a_sfydzda", nullable = true, length = 128)
    public String getaSfydzda() {
        return aSfydzda;
    }

    public void setaSfydzda(String aSfydzda) {
        this.aSfydzda = aSfydzda;
    }

    @Basic
    @Column(name = "a_sfysh", nullable = true, length = 1)
    public String getaSfysh() {
        return aSfysh;
    }

    public void setaSfysh(String aSfysh) {
        this.aSfysh = aSfysh;
    }

    @Basic
    @Column(name = "a_sfhbgb", nullable = true, length = 1)
    public String getaSfhbgb() {
        return aSfhbgb;
    }

    public void setaSfhbgb(String aSfhbgb) {
        this.aSfhbgb = aSfhbgb;
    }

    @Basic
    @Column(name = "a_sfyshxlxw", nullable = true, length = 2)
    public String getaSfyshxlxw() {
        return aSfyshxlxw;
    }

    public void setaSfyshxlxw(String aSfyshxlxw) {
        this.aSfyshxlxw = aSfyshxlxw;
    }

    @Basic
    @Column(name = "a_sfzgdy", nullable = false, length = 4)
    public String getaSfzgdy() {
        return aSfzgdy;
    }

    public void setaSfzgdy(String aSfzgdy) {
        this.aSfzgdy = aSfzgdy;
    }

    @Basic
    @Column(name = "a_zzxlb", nullable = true, length = 20)
    public String getaZzxlb() {
        return aZzxlb;
    }

    public void setaZzxlb(String aZzxlb) {
        this.aZzxlb = aZzxlb;
    }

    @Basic
    @Column(name = "a_zzxla", nullable = true, length = 64)
    public String getaZzxla() {
        return aZzxla;
    }

    public void setaZzxla(String aZzxla) {
        this.aZzxla = aZzxla;
    }

    @Basic
    @Column(name = "a_zzxwb", nullable = true, length = 20)
    public String getaZzxwb() {
        return aZzxwb;
    }

    public void setaZzxwb(String aZzxwb) {
        this.aZzxwb = aZzxwb;
    }

    @Basic
    @Column(name = "a_zzxwa", nullable = true, length = 64)
    public String getaZzxwa() {
        return aZzxwa;
    }

    public void setaZzxwa(String aZzxwa) {
        this.aZzxwa = aZzxwa;
    }

    @Basic
    @Column(name = "a_zzbyyxjzy", nullable = true, length = 256)
    public String getaZzbyyxjzy() {
        return aZzbyyxjzy;
    }

    public void setaZzbyyxjzy(String aZzbyyxjzy) {
        this.aZzbyyxjzy = aZzbyyxjzy;
    }

    @Basic
    @Column(name = "a_qrzxlb", nullable = true, length = 20)
    public String getaQrzxlb() {
        return aQrzxlb;
    }

    public void setaQrzxlb(String aQrzxlb) {
        this.aQrzxlb = aQrzxlb;
    }

    @Basic
    @Column(name = "a_qrzxla", nullable = true, length = 64)
    public String getaQrzxla() {
        return aQrzxla;
    }

    public void setaQrzxla(String aQrzxla) {
        this.aQrzxla = aQrzxla;
    }

    @Basic
    @Column(name = "a_qrzxwb", nullable = true, length = 20)
    public String getaQrzxwb() {
        return aQrzxwb;
    }

    public void setaQrzxwb(String aQrzxwb) {
        this.aQrzxwb = aQrzxwb;
    }

    @Basic
    @Column(name = "a_qrzxwa", nullable = true, length = 64)
    public String getaQrzxwa() {
        return aQrzxwa;
    }

    public void setaQrzxwa(String aQrzxwa) {
        this.aQrzxwa = aQrzxwa;
    }

    @Basic
    @Column(name = "a_qrzbyyxjzy", nullable = true, length = 256)
    public String getaQrzbyyxjzy() {
        return aQrzbyyxjzy;
    }

    public void setaQrzbyyxjzy(String aQrzbyyxjzy) {
        this.aQrzbyyxjzy = aQrzbyyxjzy;
    }

    @Basic
    @Column(name = "a_zgxlb", nullable = true, length = 20)
    public String getaZgxlb() {
        return aZgxlb;
    }

    public void setaZgxlb(String aZgxlb) {
        this.aZgxlb = aZgxlb;
    }

    @Basic
    @Column(name = "a_zgxla", nullable = true, length = 64)
    public String getaZgxla() {
        return aZgxla;
    }

    public void setaZgxla(String aZgxla) {
        this.aZgxla = aZgxla;
    }

    @Basic
    @Column(name = "a_zgxwb", nullable = true, length = 20)
    public String getaZgxwb() {
        return aZgxwb;
    }

    public void setaZgxwb(String aZgxwb) {
        this.aZgxwb = aZgxwb;
    }

    @Basic
    @Column(name = "a_zgxwa", nullable = true, length = 64)
    public String getaZgxwa() {
        return aZgxwa;
    }

    public void setaZgxwa(String aZgxwa) {
        this.aZgxwa = aZgxwa;
    }

    @Basic
    @Column(name = "a_zgbyyxjzy", nullable = true, length = 256)
    public String getaZgbyyxjzy() {
        return aZgbyyxjzy;
    }

    public void setaZgbyyxjzy(String aZgbyyxjzy) {
        this.aZgbyyxjzy = aZgbyyxjzy;
    }

    @Basic
    @Column(name = "a_dwjzw_mc", nullable = true, length = -1)
    public String getaDwjzwMc() {
        return aDwjzwMc;
    }

    public void setaDwjzwMc(String aDwjzwMc) {
        this.aDwjzwMc = aDwjzwMc;
    }

    @Basic
    @Column(name = "a_dwjzw_spb", nullable = true, length = -1)
    public String getaDwjzwSpb() {
        return aDwjzwSpb;
    }

    public void setaDwjzwSpb(String aDwjzwSpb) {
        this.aDwjzwSpb = aDwjzwSpb;
    }

    @Basic
    @Column(name = "a_jl", nullable = true, length = -1)
    public String getaJl() {
        return aJl;
    }

    public void setaJl(String aJl) {
        this.aJl = aJl;
    }

    @Basic
    @Column(name = "a_jrshzwqk", nullable = true, length = -1)
    public String getaJrshzwqk() {
        return aJrshzwqk;
    }

    public void setaJrshzwqk(String aJrshzwqk) {
        this.aJrshzwqk = aJrshzwqk;
    }

    @Basic
    @Column(name = "a_dxdbqk", nullable = true, length = -1)
    public String getaDxdbqk() {
        return aDxdbqk;
    }

    public void setaDxdbqk(String aDxdbqk) {
        this.aDxdbqk = aDxdbqk;
    }

    @Basic
    @Column(name = "a_cgjqk", nullable = true, length = -1)
    public String getaCgjqk() {
        return aCgjqk;
    }

    public void setaCgjqk(String aCgjqk) {
        this.aCgjqk = aCgjqk;
    }

    @Basic
    @Column(name = "a_jlqk", nullable = true, length = -1)
    public String getaJlqk() {
        return aJlqk;
    }

    public void setaJlqk(String aJlqk) {
        this.aJlqk = aJlqk;
    }

    @Basic
    @Column(name = "a_cjqk", nullable = true, length = -1)
    public String getaCjqk() {
        return aCjqk;
    }

    public void setaCjqk(String aCjqk) {
        this.aCjqk = aCjqk;
    }

    @Basic
    @Column(name = "a_ndkhqk", nullable = true, length = -1)
    public String getaNdkhqk() {
        return aNdkhqk;
    }

    public void setaNdkhqk(String aNdkhqk) {
        this.aNdkhqk = aNdkhqk;
    }

    @Basic
    @Column(name = "a_pxqk", nullable = true, length = -1)
    public String getaPxqk() {
        return aPxqk;
    }

    public void setaPxqk(String aPxqk) {
        this.aPxqk = aPxqk;
    }

    @Basic
    @Column(name = "a_kycgqk", nullable = true, length = -1)
    public String getaKycgqk() {
        return aKycgqk;
    }

    public void setaKycgqk(String aKycgqk) {
        this.aKycgqk = aKycgqk;
    }

    @Basic
    @Column(name = "a_zcqk", nullable = true, length = -1)
    public String getaZcqk() {
        return aZcqk;
    }

    public void setaZcqk(String aZcqk) {
        this.aZcqk = aZcqk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A01 a01 = (A01) o;
        return Objects.equals(a0100, a01.a0100) &&
                Objects.equals(a0101, a01.a0101) &&
                Objects.equals(a0102, a01.a0102) &&
                Objects.equals(a0104, a01.a0104) &&
                Objects.equals(a0104A, a01.a0104A) &&
                Objects.equals(a0107, a01.a0107) &&
                Objects.equals(a0111A, a01.a0111A) &&
                Objects.equals(a0111B, a01.a0111B) &&
                Objects.equals(a0114A, a01.a0114A) &&
                Objects.equals(a0114B, a01.a0114B) &&
                Objects.equals(a0115A, a01.a0115A) &&
                Objects.equals(a0115B, a01.a0115B) &&
                Objects.equals(a0117, a01.a0117) &&
                Objects.equals(a0117A, a01.a0117A) &&
                Objects.equals(a0127, a01.a0127) &&
                Objects.equals(a0127A, a01.a0127A) &&
                Objects.equals(a0128, a01.a0128) &&
                Objects.equals(a0131, a01.a0131) &&
                Objects.equals(a0131A, a01.a0131A) &&
                Objects.equals(aCjgzdw, a01.aCjgzdw) &&
                Objects.equals(a0134, a01.a0134) &&
                Objects.equals(a0137, a01.a0137) &&
                Objects.equals(a0141, a01.a0141) &&
                Objects.equals(a0144, a01.a0144) &&
                Objects.equals(a0151, a01.a0151) &&
                Objects.equals(a0154, a01.a0154) &&
                Objects.equals(a0157A, a01.a0157A) &&
                Objects.equals(a0157B, a01.a0157B) &&
                Objects.equals(a0161, a01.a0161) &&
                Objects.equals(a0164, a01.a0164) &&
                Objects.equals(a0167, a01.a0167) &&
                Objects.equals(a0171, a01.a0171) &&
                Objects.equals(a0174, a01.a0174) &&
                Objects.equals(a0177, a01.a0177) &&
                Objects.equals(a0181A, a01.a0181A) &&
                Objects.equals(a0181B, a01.a0181B) &&
                Objects.equals(a0183, a01.a0183) &&
                Objects.equals(a0184, a01.a0184) &&
                Objects.equals(a0187A, a01.a0187A) &&
                Objects.equals(a0187B, a01.a0187B) &&
                Objects.equals(a0191, a01.a0191) &&
                Objects.equals(aZpgs, a01.aZpgs) &&
                Objects.equals(aZpdx, a01.aZpdx) &&
                Objects.equals(aZplj, a01.aZplj) &&
                Objects.equals(a0501A, a01.a0501A) &&
                Objects.equals(a0501B, a01.a0501B) &&
                Objects.equals(a0504, a01.a0504) &&
                Objects.equals(aZjsm, a01.aZjsm) &&
                Objects.equals(axzwcca, a01.axzwcca) &&
                Objects.equals(aXzwccb, a01.aXzwccb) &&
                Objects.equals(aXzwccsj, a01.aXzwccsj) &&
                Objects.equals(aSfgwy, a01.aSfgwy) &&
                Objects.equals(aGwydjsj, a01.aGwydjsj) &&
                Objects.equals(aGbztb, a01.aGbztb) &&
                Objects.equals(aGbzta, a01.aGbzta) &&
                Objects.equals(aJrdqdwsj, a01.aJrdqdwsj) &&
                Objects.equals(aGblya, a01.aGblya) &&
                Objects.equals(aGblyb, a01.aGblyb) &&
                Objects.equals(aGllbB, a01.aGllbB) &&
                Objects.equals(aGllbA, a01.aGllbA) &&
                Objects.equals(aSfydzda, a01.aSfydzda) &&
                Objects.equals(aSfysh, a01.aSfysh) &&
                Objects.equals(aSfhbgb, a01.aSfhbgb) &&
                Objects.equals(aSfyshxlxw, a01.aSfyshxlxw) &&
                Objects.equals(aSfzgdy, a01.aSfzgdy) &&
                Objects.equals(aZzxlb, a01.aZzxlb) &&
                Objects.equals(aZzxla, a01.aZzxla) &&
                Objects.equals(aZzxwb, a01.aZzxwb) &&
                Objects.equals(aZzxwa, a01.aZzxwa) &&
                Objects.equals(aZzbyyxjzy, a01.aZzbyyxjzy) &&
                Objects.equals(aQrzxlb, a01.aQrzxlb) &&
                Objects.equals(aQrzxla, a01.aQrzxla) &&
                Objects.equals(aQrzxwb, a01.aQrzxwb) &&
                Objects.equals(aQrzxwa, a01.aQrzxwa) &&
                Objects.equals(aQrzbyyxjzy, a01.aQrzbyyxjzy) &&
                Objects.equals(aZgxlb, a01.aZgxlb) &&
                Objects.equals(aZgxla, a01.aZgxla) &&
                Objects.equals(aZgxwb, a01.aZgxwb) &&
                Objects.equals(aZgxwa, a01.aZgxwa) &&
                Objects.equals(aZgbyyxjzy, a01.aZgbyyxjzy) &&
                Objects.equals(aDwjzwMc, a01.aDwjzwMc) &&
                Objects.equals(aDwjzwSpb, a01.aDwjzwSpb) &&
                Objects.equals(aJl, a01.aJl) &&
                Objects.equals(aJrshzwqk, a01.aJrshzwqk) &&
                Objects.equals(aDxdbqk, a01.aDxdbqk) &&
                Objects.equals(aCgjqk, a01.aCgjqk) &&
                Objects.equals(aJlqk, a01.aJlqk) &&
                Objects.equals(aCjqk, a01.aCjqk) &&
                Objects.equals(aNdkhqk, a01.aNdkhqk) &&
                Objects.equals(aZcqk, a01.aZcqk) &&
                Objects.equals(aPxqk, a01.aPxqk) &&
                Objects.equals(aKycgqk, a01.aKycgqk);
    }

    @Override
    public int hashCode() {

        return Objects.hash(a0100, a0101, a0102, a0104, a0104A, a0107, a0111A, a0111B, a0114A, a0114B, a0115A, a0115B, a0117, a0117A, a0127, a0127A, a0128, a0131, a0131A, aCjgzdw, a0134, a0137, a0141, a0144, a0151, a0154, a0157A, a0157B, a0161, a0164, a0167, a0171, a0174, a0177, a0181A, a0181B, a0183, a0184, a0187A, a0187B, a0191, aZpgs, aZpdx, aZplj, a0501A, a0501B, a0504, aZjsm, axzwcca, aXzwccb, aXzwccsj, aSfgwy, aGwydjsj, aGbztb, aGbzta, aJrdqdwsj, aGblya, aGblyb, aGllbB, aGllbA, aSfydzda, aSfysh, aSfhbgb, aSfyshxlxw, aSfzgdy, aZzxlb, aZzxla, aZzxwb, aZzxwa, aZzbyyxjzy, aQrzxlb, aQrzxla, aQrzxwb, aQrzxwa, aQrzbyyxjzy, aZgxlb, aZgxla, aZgxwb, aZgxwa, aZgbyyxjzy, aDwjzwMc, aDwjzwSpb, aJl, aJrshzwqk, aDxdbqk, aCgjqk, aJlqk, aCjqk, aNdkhqk, aZcqk, aPxqk, aKycgqk);
    }
}
