/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a38.entity;

import com.hisun.base.entity.TombstoneEntity;
import com.hisun.saas.zzb.dzda.a32.entity.A32;
import com.hisun.saas.zzb.dzda.a52.entity.A52;
import com.hisun.saas.zzb.dzda.dacy.entity.EA38Log;
import com.hisun.saas.zzb.dzda.e01z4.entity.E01Z4;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;
import com.hisun.saas.zzb.dzda.zrzc.entity.E01Z5;
import com.hisun.saas.zzb.dzda.zrzc.entity.E01Z7;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author liuzj {279421824@qq.com}
 */
@Entity
@Table(name = "a38")
public class A38 extends TombstoneEntity implements Serializable {
    private String id;
    private String a01Id;//人员主键
    private String a0101;//姓名
    private String smxh;//扫描序号
    private String dabh;//档案编号
    private String a0104;//性别字典代码
    private String a0104Content;//性别字典内容
    private String a0107;//出生日期
    private String a0111B;//籍贯字典代码
    private String a0111A;//籍贯内容
    private String gbztCode;//干部状态字典代码
    private String gbztContent;//干部状态字典内容
    private String daztCode;//档案状态字典代码
    private String daztContent;//档案状态字典内容
    private String a0157;//单位与职务
    private String a3801;//档案接收日期
    private String jsr;//接收人，默认当前用户
    private String a3804B;//档案来处id
    private String a3804A;//档案来处名称（转入单位名称）
    private String a3807B;//转出单位id，即档案管理单位id
    private String a3807A;//转出单位名称，即档案管理单位名称
    private String a3814;//档案版本类别
    private String a3834;//档案位置
    private String swrq;//死亡日期
    private String a3824;//档案备注
    private String a3817;//档案转出日期
    private String a3821;//转去单位
    private String zcyy;//转出原因
    private String jbrId;//档案转递时的经办人id
    private String jbrXm;//档案转递时的经办人
    private String rcbz;//档案转出信息备注
    private String sjzt;//数据状态。0：待审核(未审核)；1：已审核(正式数据)
    private String zryy;//转入原因
    private String zcTenantId;//转出单位id
    private String zcTenantName;//转出单位名称
    private String dasjly;//数据来源：add：从电子档案系统中新增；frompi：来源于干部系统；fromdr:导入产生
    private String xzjsj;//现职级时间
    private String surnameCode;//姓氏编码
    private Integer nameWordcount;//姓名字数
    private String personalnameCode;//名编码
    private String personalnameNonecode;//无编码汉字
    private String dutyLevelCode;//职务级别code
    private String dutyLevelValue;//职务级别
    private String dutyLevelTimeBase;//职级时间

    private List<A32> a32s;
    private List<A52> a52s;

    private List<E01Z4> e01z4s;
    private List<E01Z1> e01z1s;
    private List<E01Z5> e01z5s;
    private List<E01Z7> e01z7s;
    private List<EA38Log> a38Logs;
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false, unique = true, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @javax.persistence.Column(name = "a01_id")
    public String getA01Id() {
        return a01Id;
    }

    public void setA01Id(String a01Id) {
        this.a01Id = a01Id;
    }

    @Basic
    @javax.persistence.Column(name = "a0101")
    public String getA0101() {
        return a0101;
    }

    public void setA0101(String a0101) {
        this.a0101 = a0101;
    }

    @Basic
    @javax.persistence.Column(name = "smxh")
    public String getSmxh() {
        return smxh;
    }

    public void setSmxh(String smxh) {
        this.smxh = smxh;
    }

    @Basic
    @javax.persistence.Column(name = "dabh")
    public String getDabh() {
        return dabh;
    }
    public void setDabh(String dabh) {
        this.dabh = dabh;
    }
    @Basic
    @javax.persistence.Column(name = "a0104")
    public String getA0104() {
        return a0104;
    }

    public void setA0104(String a0104) {
        this.a0104 = a0104;
    }
    @Basic
    @javax.persistence.Column(name = "a0104_content")
    public String getA0104Content() {
        return a0104Content;
    }

    public void setA0104Content(String a0104Content) {
        this.a0104Content = a0104Content;
    }
    @Basic
    @javax.persistence.Column(name = "a0107")
    public String getA0107() {
        return a0107;
    }

    public void setA0107(String a0107) {
        this.a0107 = a0107;
    }
    @Basic
    @javax.persistence.Column(name = "a0111b")
    public String getA0111B() {
        return a0111B;
    }

    public void setA0111B(String a0111B) {
        this.a0111B = a0111B;
    }
    @Basic
    @javax.persistence.Column(name = "a0111a")
    public String getA0111A() {
        return a0111A;
    }

    public void setA0111A(String a0111A) {
        this.a0111A = a0111A;
    }
    @Basic
    @javax.persistence.Column(name = "gbzt_code")
    public String getGbztCode() {
        return gbztCode;
    }

    public void setGbztCode(String gbztCode) {
        this.gbztCode = gbztCode;
    }
    @Basic
    @javax.persistence.Column(name = "gbzt_content")
    public String getGbztContent() {
        return gbztContent;
    }

    public void setGbztContent(String gbztContent) {
        this.gbztContent = gbztContent;
    }
    @Basic
    @javax.persistence.Column(name = "dazt_code")
    public String getDaztCode() {
        return daztCode;
    }

    public void setDaztCode(String daztCode) {
        this.daztCode = daztCode;
    }
    @Basic
    @javax.persistence.Column(name = "dazt_content")
    public String getDaztContent() {
        return daztContent;
    }

    public void setDaztContent(String daztContent) {
        this.daztContent = daztContent;
    }
    @Basic
    @javax.persistence.Column(name = "a0157")
    public String getA0157() {
        return a0157;
    }

    public void setA0157(String a0157) {
        this.a0157 = a0157;
    }
    @Basic
    @javax.persistence.Column(name = "a3801")
    public String getA3801() {
        return a3801;
    }

    public void setA3801(String a3801) {
        this.a3801 = a3801;
    }
    @Basic
    @javax.persistence.Column(name = "jsr")
    public String getJsr() {
        return jsr;
    }

    public void setJsr(String jsr) {
        this.jsr = jsr;
    }
    @Basic
    @javax.persistence.Column(name = "a3804b")
    public String getA3804B() {
        return a3804B;
    }

    public void setA3804B(String a3804B) {
        this.a3804B = a3804B;
    }
    @Basic
    @javax.persistence.Column(name = "a3804a")
    public String getA3804A() {
        return a3804A;
    }

    public void setA3804A(String a3804A) {
        this.a3804A = a3804A;
    }
    @Basic
    @javax.persistence.Column(name = "a3807b")
    public String getA3807B() {
        return a3807B;
    }

    public void setA3807B(String a3807B) {
        this.a3807B = a3807B;
    }
    @Basic
    @javax.persistence.Column(name = "a3807a")
    public String getA3807A() {
        return a3807A;
    }

    public void setA3807A(String a3807A) {
        this.a3807A = a3807A;
    }
    @Basic
    @javax.persistence.Column(name = "a3814")
    public String getA3814() {
        return a3814;
    }

    public void setA3814(String a3814) {
        this.a3814 = a3814;
    }
    @Basic
    @javax.persistence.Column(name = "a3834")
    public String getA3834() {
        return a3834;
    }

    public void setA3834(String a3834) {
        this.a3834 = a3834;
    }
    @Basic
    @javax.persistence.Column(name = "swrq")
    public String getSwrq() {
        return swrq;
    }

    public void setSwrq(String swrq) {
        this.swrq = swrq;
    }
    @Basic
    @javax.persistence.Column(name = "a3824")
    public String getA3824() {
        return a3824;
    }

    public void setA3824(String a3824) {
        this.a3824 = a3824;
    }
    @Basic
    @javax.persistence.Column(name = "a3817")
    public String getA3817() {
        return a3817;
    }

    public void setA3817(String a3817) {
        this.a3817 = a3817;
    }

    @Basic
    @javax.persistence.Column(name = "a3821")
    public String getA3821() {
        return a3821;
    }

    public void setA3821(String a3821) {
        this.a3821 = a3821;
    }

    @Basic
    @javax.persistence.Column(name = "zcyy")
    public String getZcyy() {
        return zcyy;
    }

    public void setZcyy(String zcyy) {
        this.zcyy = zcyy;
    }

    @Basic
    @javax.persistence.Column(name = "jbr_id")
    public String getJbrId() {
        return jbrId;
    }

    public void setJbrId(String jbrId) {
        this.jbrId = jbrId;
    }

    @Basic
    @javax.persistence.Column(name = "jbr_xm")
    public String getJbrXm() {
        return jbrXm;
    }

    public void setJbrXm(String jbrXm) {
        this.jbrXm = jbrXm;
    }

    @Basic
    @javax.persistence.Column(name = "rcbz")
    public String getRcbz() {
        return rcbz;
    }

    public void setRcbz(String rcbz) {
        this.rcbz = rcbz;
    }

    @Basic
    @javax.persistence.Column(name = "sjzt")
    public String getSjzt() {
        return sjzt;
    }

    public void setSjzt(String sjzt) {
        this.sjzt = sjzt;
    }
    @Basic
    @javax.persistence.Column(name = "zryy")
    public String getZryy() {
        return zryy;
    }

    public void setZryy(String zryy) {
        this.zryy = zryy;
    }

    @Basic
    @javax.persistence.Column(name = "zc_tenant_id")
    public String getZcTenantId() {
        return zcTenantId;
    }

    public void setZcTenantId(String zcTenantId) {
        this.zcTenantId = zcTenantId;
    }

    @Basic
    @javax.persistence.Column(name = "zc_tenant_name")
    public String getZcTenantName() {
        return zcTenantName;
    }

    public void setZcTenantName(String zcTenantName) {
        this.zcTenantName = zcTenantName;
    }

    @Basic
    @javax.persistence.Column(name = "dasjly")
    public String getDasjly() {
        return dasjly;
    }

    public void setDasjly(String dasjly) {
        this.dasjly = dasjly;
    }

    @Basic
    @javax.persistence.Column(name = "xzjsj")
    public String getXzjsj() {
        return xzjsj;
    }

    public void setXzjsj(String xzjsj) {
        this.xzjsj = xzjsj;
    }

    @Basic
    @javax.persistence.Column(name = "surname_code")
    public String getSurnameCode() {
        return surnameCode;
    }

    public void setSurnameCode(String surnameCode) {
        this.surnameCode = surnameCode;
    }
    @Basic
    @javax.persistence.Column(name = "name_wordcount")
    public Integer getNameWordcount() {
        return nameWordcount;
    }
    public void setNameWordcount(Integer nameWordcount) {
        this.nameWordcount = nameWordcount;
    }

    @Basic
    @javax.persistence.Column(name = "personalname_code")
    public String getPersonalnameCode() {
        return personalnameCode;
    }

    public void setPersonalnameCode(String personalnameCode) {
        this.personalnameCode = personalnameCode;
    }
    @Basic
    @javax.persistence.Column(name = "personalname_nonecode")
    public String getPersonalnameNonecode() {
        return personalnameNonecode;
    }
    public void setPersonalnameNonecode(String personalnameNonecode) {
        this.personalnameNonecode = personalnameNonecode;
    }

    @Basic
    @javax.persistence.Column(name = "duty_level_code")
    public String getDutyLevelCode() {
        return dutyLevelCode;
    }
    public void setDutyLevelCode(String dutyLevelCode) {
        this.dutyLevelCode = dutyLevelCode;
    }

    @Basic
    @javax.persistence.Column(name = "duty_level_value")
    public String getDutyLevelValue() {
        return dutyLevelValue;
    }

    public void setDutyLevelValue(String dutyLevelValue) {
        this.dutyLevelValue = dutyLevelValue;
    }

    @Basic
    @javax.persistence.Column(name = "duty_level_time_base")
    public String getDutyLevelTimeBase() {
        return dutyLevelTimeBase;
    }

    public void setDutyLevelTimeBase(String dutyLevelTimeBase) {
        this.dutyLevelTimeBase = dutyLevelTimeBase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        A38 a38 = (A38) o;

        if (id != null ? !id.equals(a38.id) : a38.id != null) return false;
        if (a01Id != null ? !a01Id.equals(a38.a01Id) : a38.a01Id != null) return false;
        if (a0101 != null ? !a0101.equals(a38.a0101) : a38.a0101 != null) return false;
        if (smxh != null ? !smxh.equals(a38.smxh) : a38.smxh != null) return false;
        if (dabh != null ? !dabh.equals(a38.dabh) : a38.dabh != null) return false;
        if (a0104 != null ? !a0104.equals(a38.a0104) : a38.a0104 != null) return false;
        if (a0104Content != null ? !a0104Content.equals(a38.a0104Content) : a38.a0104Content != null) return false;
        if (a0107 != null ? !a0107.equals(a38.a0107) : a38.a0107 != null) return false;
        if (a0111B != null ? !a0111B.equals(a38.a0111B) : a38.a0111B != null) return false;
        if (a0111A != null ? !a0111A.equals(a38.a0111A) : a38.a0111A != null) return false;
        if (gbztCode != null ? !gbztCode.equals(a38.gbztCode) : a38.gbztCode != null) return false;
        if (gbztContent != null ? !gbztContent.equals(a38.gbztContent) : a38.gbztContent != null) return false;
        if (daztCode != null ? !daztCode.equals(a38.daztCode) : a38.daztCode != null) return false;
        if (daztContent != null ? !daztContent.equals(a38.daztContent) : a38.daztContent != null) return false;
        if (a0157 != null ? !a0157.equals(a38.a0157) : a38.a0157 != null) return false;
        if (a3801 != null ? !a3801.equals(a38.a3801) : a38.a3801 != null) return false;
        if (jsr != null ? !jsr.equals(a38.jsr) : a38.jsr != null) return false;
        if (a3804B != null ? !a3804B.equals(a38.a3804B) : a38.a3804B != null) return false;
        if (a3804A != null ? !a3804A.equals(a38.a3804A) : a38.a3804A != null) return false;
        if (a3807B != null ? !a3807B.equals(a38.a3807B) : a38.a3807B != null) return false;
        if (a3807A != null ? !a3807A.equals(a38.a3807A) : a38.a3807A != null) return false;
        if (a3814 != null ? !a3814.equals(a38.a3814) : a38.a3814 != null) return false;
        if (a3834 != null ? !a3834.equals(a38.a3834) : a38.a3834 != null) return false;
        if (swrq != null ? !swrq.equals(a38.swrq) : a38.swrq != null) return false;
        if (a3824 != null ? !a3824.equals(a38.a3824) : a38.a3824 != null) return false;
        if (a3817 != null ? !a3817.equals(a38.a3817) : a38.a3817 != null) return false;
        if (a3821 != null ? !a3821.equals(a38.a3821) : a38.a3821 != null) return false;
        if (zcyy != null ? !zcyy.equals(a38.zcyy) : a38.zcyy != null) return false;
        if (jbrId != null ? !jbrId.equals(a38.jbrId) : a38.jbrId != null) return false;
        if (jbrXm != null ? !jbrXm.equals(a38.jbrXm) : a38.jbrXm != null) return false;
        if (rcbz != null ? !rcbz.equals(a38.rcbz) : a38.rcbz != null) return false;
        if (sjzt != null ? !sjzt.equals(a38.sjzt) : a38.sjzt != null) return false;
        if (zryy != null ? !zryy.equals(a38.zryy) : a38.zryy != null) return false;
        if (zcTenantId != null ? !zcTenantId.equals(a38.zcTenantId) : a38.zcTenantId != null) return false;
        if (zcTenantName != null ? !zcTenantName.equals(a38.zcTenantName) : a38.zcTenantName != null) return false;
        if (dasjly != null ? !dasjly.equals(a38.dasjly) : a38.dasjly != null) return false;
        if (xzjsj != null ? !xzjsj.equals(a38.xzjsj) : a38.xzjsj != null) return false;
        if (surnameCode != null ? !surnameCode.equals(a38.surnameCode) : a38.surnameCode != null) return false;
        if (nameWordcount != null ? !nameWordcount.equals(a38.nameWordcount) : a38.nameWordcount != null) return false;
        if (personalnameCode != null ? !personalnameCode.equals(a38.personalnameCode) : a38.personalnameCode != null)
            return false;
        if (personalnameNonecode != null ? !personalnameNonecode.equals(a38.personalnameNonecode) : a38.personalnameNonecode != null)
            return false;
        if (dutyLevelCode != null ? !dutyLevelCode.equals(a38.dutyLevelCode) : a38.dutyLevelCode != null) return false;
        if (dutyLevelValue != null ? !dutyLevelValue.equals(a38.dutyLevelValue) : a38.dutyLevelValue != null)
            return false;
        if (dutyLevelTimeBase != null ? !dutyLevelTimeBase.equals(a38.dutyLevelTimeBase) : a38.dutyLevelTimeBase != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (a01Id != null ? a01Id.hashCode() : 0);
        result = 31 * result + (a0101 != null ? a0101.hashCode() : 0);
        result = 31 * result + (smxh != null ? smxh.hashCode() : 0);
        result = 31 * result + (dabh != null ? dabh.hashCode() : 0);
        result = 31 * result + (a0104 != null ? a0104.hashCode() : 0);
        result = 31 * result + (a0104Content != null ? a0104Content.hashCode() : 0);
        result = 31 * result + (a0107 != null ? a0107.hashCode() : 0);
        result = 31 * result + (a0111B != null ? a0111B.hashCode() : 0);
        result = 31 * result + (a0111A != null ? a0111A.hashCode() : 0);
        result = 31 * result + (gbztCode != null ? gbztCode.hashCode() : 0);
        result = 31 * result + (gbztContent != null ? gbztContent.hashCode() : 0);
        result = 31 * result + (daztCode != null ? daztCode.hashCode() : 0);
        result = 31 * result + (daztContent != null ? daztContent.hashCode() : 0);
        result = 31 * result + (a0157 != null ? a0157.hashCode() : 0);
        result = 31 * result + (a3801 != null ? a3801.hashCode() : 0);
        result = 31 * result + (jsr != null ? jsr.hashCode() : 0);
        result = 31 * result + (a3804B != null ? a3804B.hashCode() : 0);
        result = 31 * result + (a3804A != null ? a3804A.hashCode() : 0);
        result = 31 * result + (a3807B != null ? a3807B.hashCode() : 0);
        result = 31 * result + (a3807A != null ? a3807A.hashCode() : 0);
        result = 31 * result + (a3814 != null ? a3814.hashCode() : 0);
        result = 31 * result + (a3834 != null ? a3834.hashCode() : 0);
        result = 31 * result + (swrq != null ? swrq.hashCode() : 0);
        result = 31 * result + (a3824 != null ? a3824.hashCode() : 0);
        result = 31 * result + (a3817 != null ? a3817.hashCode() : 0);
        result = 31 * result + (a3821 != null ? a3821.hashCode() : 0);
        result = 31 * result + (zcyy != null ? zcyy.hashCode() : 0);
        result = 31 * result + (jbrId != null ? jbrId.hashCode() : 0);
        result = 31 * result + (jbrXm != null ? jbrXm.hashCode() : 0);
        result = 31 * result + (rcbz != null ? rcbz.hashCode() : 0);
        result = 31 * result + (sjzt != null ? sjzt.hashCode() : 0);
        result = 31 * result + (zryy != null ? zryy.hashCode() : 0);
        result = 31 * result + (zcTenantId != null ? zcTenantId.hashCode() : 0);
        result = 31 * result + (zcTenantName != null ? zcTenantName.hashCode() : 0);
        result = 31 * result + (dasjly != null ? dasjly.hashCode() : 0);
        result = 31 * result + (xzjsj != null ? xzjsj.hashCode() : 0);
        result = 31 * result + (surnameCode != null ? surnameCode.hashCode() : 0);
        result = 31 * result + (nameWordcount != null ? nameWordcount.hashCode() : 0);
        result = 31 * result + (personalnameCode != null ? personalnameCode.hashCode() : 0);
        result = 31 * result + (personalnameNonecode != null ? personalnameNonecode.hashCode() : 0);
        result = 31 * result + (dutyLevelCode != null ? dutyLevelCode.hashCode() : 0);
        result = 31 * result + (dutyLevelValue != null ? dutyLevelValue.hashCode() : 0);
        result = 31 * result + (dutyLevelTimeBase != null ? dutyLevelTimeBase.hashCode() : 0);
        return result;
    }
    @OneToMany(mappedBy = "a38", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<A32> getA32s() {
        return a32s;
    }

    public void setA32s(List<A32> a32s) {
        this.a32s = a32s;
    }
    @OneToMany(mappedBy = "a38", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<A52> getA52s() {
        return a52s;
    }

    public void setA52s(List<A52> a52s) {
        this.a52s = a52s;
    }
    @OneToMany(mappedBy = "a38", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<E01Z4> getE01z4s() {
        return e01z4s;
    }

    public void setE01z4s(List<E01Z4> e01z4s) {
        this.e01z4s = e01z4s;
    }
    @OneToMany(mappedBy = "a38", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<E01Z1> getE01z1s() {
        return e01z1s;
    }

    public void setE01z1s(List<E01Z1> e01z1s) {
        this.e01z1s = e01z1s;
    }
    @OneToMany(mappedBy = "a38", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<E01Z5> getE01z5s() {
        return e01z5s;
    }

    public void setE01z5s(List<E01Z5> e01z5s) {
        this.e01z5s = e01z5s;
    }
    @OneToMany(mappedBy = "a38", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<E01Z7> getE01z7s() {
        return e01z7s;
    }

    public void setE01z7s(List<E01Z7> e01z7s) {
        this.e01z7s = e01z7s;
    }

    @OneToMany(mappedBy = "a38", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<EA38Log> getA38Logs() {
        return a38Logs;
    }

    public void setA38Logs(List<EA38Log> a38Logs) {
        this.a38Logs = a38Logs;
    }
}
