/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.a.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Marco {854476391@qq.com}
 */
public class A01Vo {
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

    /** 人事关系所在单位级别：ZB03-2006/DWJB */
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
    private String jrshzwqk;

    /** 当选会议代表情况 */
    private String dxdbqk;

    /** 出国境情况 */
    private String cgjqk;

    /** 奖励情况 */
    private String aJlqk;

    /** 惩戒情况 */
    private String aCjqk;

    /** 年度考核情况 */
    private String aNdkhqk;

    /** 培训情况 */
    private String aPxqk;

    /** 科研成果、著作发明 */
    private String kycgqk;

    /** 职称情况 */
    private String zcqk;

    /* */
    //private List<A02Vo> a02Vos;


    public String getA0141A() {
        return a0141A;
    }

    public void setA0141A(String a0141A) {
        this.a0141A = a0141A;
    }

    public String getA0100() {
        return a0100;
    }

    public String getA0161A() {
        return a0161A;
    }

    public void setA0161A(String a0161A) {
        this.a0161A = a0161A;
    }

    public String getA0164A() {
        return a0164A;
    }

    public void setA0164A(String a0164A) {
        this.a0164A = a0164A;
    }

    public String getA0167A() {
        return a0167A;
    }

    public void setA0167A(String a0167A) {
        this.a0167A = a0167A;
    }

    public String getA0171A() {
        return a0171A;
    }

    public void setA0171A(String a0171A) {
        this.a0171A = a0171A;
    }

    public String getA0174A() {
        return a0174A;
    }

    public void setA0174A(String a0174A) {
        this.a0174A = a0174A;
    }

    public String getA0177A() {
        return a0177A;
    }

    public void setA0177A(String a0177A) {
        this.a0177A = a0177A;
    }

    public void setA0100(String a0100) {
        this.a0100 = a0100;
    }

    public String getA0101() {
        return a0101;
    }

    public void setA0101(String a0101) {
        this.a0101 = a0101;
    }

    public String getA0102() {
        return a0102;
    }

    public void setA0102(String a0102) {
        this.a0102 = a0102;
    }

    public String getA0104() {
        return a0104;
    }

    public void setA0104(String a0104) {
        this.a0104 = a0104;
    }

    public String getA0104A() {
        return a0104A;
    }

    public void setA0104A(String a0104A) {
        this.a0104A = a0104A;
    }

    public String getA0107() {
        return a0107;
    }

    public void setA0107(String a0107) {
        this.a0107 = a0107;
    }

    public String getA0111A() {
        return a0111A;
    }

    public void setA0111A(String a0111A) {
        this.a0111A = a0111A;
    }

    public String getA0111B() {
        return a0111B;
    }

    public void setA0111B(String a0111B) {
        this.a0111B = a0111B;
    }

    public String getA0114A() {
        return a0114A;
    }

    public void setA0114A(String a0114A) {
        this.a0114A = a0114A;
    }

    public String getA0114B() {
        return a0114B;
    }

    public void setA0114B(String a0114B) {
        this.a0114B = a0114B;
    }

    public String getA0115A() {
        return a0115A;
    }

    public void setA0115A(String a0115A) {
        this.a0115A = a0115A;
    }

    public String getA0115B() {
        return a0115B;
    }

    public void setA0115B(String a0115B) {
        this.a0115B = a0115B;
    }

    public String getA0117() {
        return a0117;
    }

    public void setA0117(String a0117) {
        this.a0117 = a0117;
    }

    public String getA0117A() {
        return a0117A;
    }

    public void setA0117A(String a0117A) {
        this.a0117A = a0117A;
    }

    public String getA0127() {
        return a0127;
    }

    public void setA0127(String a0127) {
        this.a0127 = a0127;
    }

    public String getA0127A() {
        return a0127A;
    }

    public void setA0127A(String a0127A) {
        this.a0127A = a0127A;
    }

    public String getA0128() {
        return a0128;
    }

    public void setA0128(String a0128) {
        this.a0128 = a0128;
    }

    public String getA0131() {
        return a0131;
    }

    public void setA0131(String a0131) {
        this.a0131 = a0131;
    }

    public String getA0131A() {
        return a0131A;
    }

    public void setA0131A(String a0131A) {
        this.a0131A = a0131A;
    }

    public String getaCjgzdw() {
        return aCjgzdw;
    }

    public void setaCjgzdw(String aCjgzdw) {
        this.aCjgzdw = aCjgzdw;
    }

    public String getA0134() {
        return a0134;
    }

    public void setA0134(String a0134) {
        this.a0134 = a0134;
    }

    public BigDecimal getA0137() {
        return a0137;
    }

    public void setA0137(BigDecimal a0137) {
        this.a0137 = a0137;
    }

    public String getA0141() {
        return a0141;
    }

    public void setA0141(String a0141) {
        this.a0141 = a0141;
    }

    public String getA0144() {
        return a0144;
    }

    public void setA0144(String a0144) {
        this.a0144 = a0144;
    }

    public String getA0151() {
        return a0151;
    }

    public void setA0151(String a0151) {
        this.a0151 = a0151;
    }

    public String getA0154() {
        return a0154;
    }

    public void setA0154(String a0154) {
        this.a0154 = a0154;
    }

    public String getA0157A() {
        return a0157A;
    }

    public void setA0157A(String a0157A) {
        this.a0157A = a0157A;
    }

    public String getA0157B() {
        return a0157B;
    }

    public void setA0157B(String a0157B) {
        this.a0157B = a0157B;
    }

    public String getA0161() {
        return a0161;
    }

    public void setA0161(String a0161) {
        this.a0161 = a0161;
    }

    public String getA0164() {
        return a0164;
    }

    public void setA0164(String a0164) {
        this.a0164 = a0164;
    }

    public String getA0167() {
        return a0167;
    }

    public void setA0167(String a0167) {
        this.a0167 = a0167;
    }

    public String getA0171() {
        return a0171;
    }

    public void setA0171(String a0171) {
        this.a0171 = a0171;
    }

    public String getA0174() {
        return a0174;
    }

    public void setA0174(String a0174) {
        this.a0174 = a0174;
    }

    public String getA0177() {
        return a0177;
    }

    public void setA0177(String a0177) {
        this.a0177 = a0177;
    }

    public String getA0181A() {
        return a0181A;
    }

    public void setA0181A(String a0181A) {
        this.a0181A = a0181A;
    }

    public String getA0181B() {
        return a0181B;
    }

    public void setA0181B(String a0181B) {
        this.a0181B = a0181B;
    }

    public String getA0183() {
        return a0183;
    }

    public void setA0183(String a0183) {
        this.a0183 = a0183;
    }

    public String getA0184() {
        return a0184;
    }

    public void setA0184(String a0184) {
        this.a0184 = a0184;
    }

    public String getA0187A() {
        return a0187A;
    }

    public void setA0187A(String a0187A) {
        this.a0187A = a0187A;
    }

    public String getA0187B() {
        return a0187B;
    }

    public void setA0187B(String a0187B) {
        this.a0187B = a0187B;
    }

    public String getA0191() {
        return a0191;
    }

    public void setA0191(String a0191) {
        this.a0191 = a0191;
    }

    public String getaZpgs() {
        return aZpgs;
    }

    public void setaZpgs(String aZpgs) {
        this.aZpgs = aZpgs;
    }

    public BigDecimal getaZpdx() {
        return aZpdx;
    }

    public void setaZpdx(BigDecimal aZpdx) {
        this.aZpdx = aZpdx;
    }

    public String getaZplj() {
        return aZplj;
    }

    public void setaZplj(String aZplj) {
        this.aZplj = aZplj;
    }

    public String getA0501A() {
        return a0501A;
    }

    public void setA0501A(String a0501A) {
        this.a0501A = a0501A;
    }

    public String getA0501B() {
        return a0501B;
    }

    public void setA0501B(String a0501B) {
        this.a0501B = a0501B;
    }

    public String getA0504() {
        return a0504;
    }

    public void setA0504(String a0504) {
        this.a0504 = a0504;
    }

    public String getaZjsm() {
        return aZjsm;
    }

    public void setaZjsm(String aZjsm) {
        this.aZjsm = aZjsm;
    }

    public String getAxzwcca() {
        return axzwcca;
    }

    public void setAxzwcca(String axzwcca) {
        this.axzwcca = axzwcca;
    }

    public String getaXzwccb() {
        return aXzwccb;
    }

    public void setaXzwccb(String aXzwccb) {
        this.aXzwccb = aXzwccb;
    }

    public String getaXzwccsj() {
        return aXzwccsj;
    }

    public void setaXzwccsj(String aXzwccsj) {
        this.aXzwccsj = aXzwccsj;
    }

    public String getaSfgwy() {
        return aSfgwy;
    }

    public void setaSfgwy(String aSfgwy) {
        this.aSfgwy = aSfgwy;
    }

    public String getaGwydjsj() {
        return aGwydjsj;
    }

    public void setaGwydjsj(String aGwydjsj) {
        this.aGwydjsj = aGwydjsj;
    }

    public String getaGbztb() {
        return aGbztb;
    }

    public void setaGbztb(String aGbztb) {
        this.aGbztb = aGbztb;
    }

    public String getaGbzta() {
        return aGbzta;
    }

    public void setaGbzta(String aGbzta) {
        this.aGbzta = aGbzta;
    }

    public String getaJrdqdwsj() {
        return aJrdqdwsj;
    }

    public void setaJrdqdwsj(String aJrdqdwsj) {
        this.aJrdqdwsj = aJrdqdwsj;
    }

    public String getaGblya() {
        return aGblya;
    }

    public void setaGblya(String aGblya) {
        this.aGblya = aGblya;
    }

    public String getaGblyb() {
        return aGblyb;
    }

    public void setaGblyb(String aGblyb) {
        this.aGblyb = aGblyb;
    }

    public String getaGllbB() {
        return aGllbB;
    }

    public void setaGllbB(String aGllbB) {
        this.aGllbB = aGllbB;
    }

    public String getaGllbA() {
        return aGllbA;
    }

    public void setaGllbA(String aGllbA) {
        this.aGllbA = aGllbA;
    }

    public String getaSfydzda() {
        return aSfydzda;
    }

    public void setaSfydzda(String aSfydzda) {
        this.aSfydzda = aSfydzda;
    }

    public String getaSfysh() {
        return aSfysh;
    }

    public void setaSfysh(String aSfysh) {
        this.aSfysh = aSfysh;
    }

    public String getaSfhbgb() {
        return aSfhbgb;
    }

    public void setaSfhbgb(String aSfhbgb) {
        this.aSfhbgb = aSfhbgb;
    }

    public String getaSfyshxlxw() {
        return aSfyshxlxw;
    }

    public void setaSfyshxlxw(String aSfyshxlxw) {
        this.aSfyshxlxw = aSfyshxlxw;
    }

    public String getaSfzgdy() {
        return aSfzgdy;
    }

    public void setaSfzgdy(String aSfzgdy) {
        this.aSfzgdy = aSfzgdy;
    }

    public String getaZzxlb() {
        return aZzxlb;
    }

    public void setaZzxlb(String aZzxlb) {
        this.aZzxlb = aZzxlb;
    }

    public String getaZzxla() {
        return aZzxla;
    }

    public void setaZzxla(String aZzxla) {
        this.aZzxla = aZzxla;
    }

    public String getaZzxwb() {
        return aZzxwb;
    }

    public void setaZzxwb(String aZzxwb) {
        this.aZzxwb = aZzxwb;
    }

    public String getaZzxwa() {
        return aZzxwa;
    }

    public void setaZzxwa(String aZzxwa) {
        this.aZzxwa = aZzxwa;
    }

    public String getaZzbyyxjzy() {
        return aZzbyyxjzy;
    }

    public void setaZzbyyxjzy(String aZzbyyxjzy) {
        this.aZzbyyxjzy = aZzbyyxjzy;
    }

    public String getaQrzxlb() {
        return aQrzxlb;
    }

    public void setaQrzxlb(String aQrzxlb) {
        this.aQrzxlb = aQrzxlb;
    }

    public String getaQrzxla() {
        return aQrzxla;
    }

    public void setaQrzxla(String aQrzxla) {
        this.aQrzxla = aQrzxla;
    }

    public String getaQrzxwb() {
        return aQrzxwb;
    }

    public void setaQrzxwb(String aQrzxwb) {
        this.aQrzxwb = aQrzxwb;
    }

    public String getaQrzxwa() {
        return aQrzxwa;
    }

    public void setaQrzxwa(String aQrzxwa) {
        this.aQrzxwa = aQrzxwa;
    }

    public String getaQrzbyyxjzy() {
        return aQrzbyyxjzy;
    }

    public void setaQrzbyyxjzy(String aQrzbyyxjzy) {
        this.aQrzbyyxjzy = aQrzbyyxjzy;
    }

    public String getaZgxlb() {
        return aZgxlb;
    }

    public void setaZgxlb(String aZgxlb) {
        this.aZgxlb = aZgxlb;
    }

    public String getaZgxla() {
        return aZgxla;
    }

    public void setaZgxla(String aZgxla) {
        this.aZgxla = aZgxla;
    }

    public String getaZgxwb() {
        return aZgxwb;
    }

    public void setaZgxwb(String aZgxwb) {
        this.aZgxwb = aZgxwb;
    }

    public String getaZgxwa() {
        return aZgxwa;
    }

    public void setaZgxwa(String aZgxwa) {
        this.aZgxwa = aZgxwa;
    }

    public String getaZgbyyxjzy() {
        return aZgbyyxjzy;
    }

    public void setaZgbyyxjzy(String aZgbyyxjzy) {
        this.aZgbyyxjzy = aZgbyyxjzy;
    }

    public String getaDwjzwMc() {
        return aDwjzwMc;
    }

    public void setaDwjzwMc(String aDwjzwMc) {
        this.aDwjzwMc = aDwjzwMc;
    }

    public String getaDwjzwSpb() {
        return aDwjzwSpb;
    }

    public void setaDwjzwSpb(String aDwjzwSpb) {
        this.aDwjzwSpb = aDwjzwSpb;
    }

    public String getaJl() {
        return aJl;
    }

    public void setaJl(String aJl) {
        this.aJl = aJl;
    }

    public String getaJlqk() {
        return aJlqk;
    }

    public void setaJlqk(String aJlqk) {
        this.aJlqk = aJlqk;
    }

    public String getaCjqk() {
        return aCjqk;
    }

    public void setaCjqk(String aCjqk) {
        this.aCjqk = aCjqk;
    }

    public String getaNdkhqk() {
        return aNdkhqk;
    }

    public void setaNdkhqk(String aNdkhqk) {
        this.aNdkhqk = aNdkhqk;
    }

    public String getaPxqk() {
        return aPxqk;
    }

    public void setaPxqk(String aPxqk) {
        this.aPxqk = aPxqk;
    }

    public String getJrshzwqk() {
        return jrshzwqk;
    }

    public void setJrshzwqk(String jrshzwqk) {
        this.jrshzwqk = jrshzwqk;
    }

    public String getDxdbqk() {
        return dxdbqk;
    }

    public void setDxdbqk(String dxdbqk) {
        this.dxdbqk = dxdbqk;
    }

    public String getCgjqk() {
        return cgjqk;
    }

    public void setCgjqk(String cgjqk) {
        this.cgjqk = cgjqk;
    }

    public String getKycgqk() {
        return kycgqk;
    }

    public void setKycgqk(String kycgqk) {
        this.kycgqk = kycgqk;
    }

    public String getZcqk() {
        return zcqk;
    }

    public void setZcqk(String zcqk) {
        this.zcqk = zcqk;
    }
}
