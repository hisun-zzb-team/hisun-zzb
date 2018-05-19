/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a38.vo;

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
import java.util.Date;
import java.util.List;

/**
 * @author liuzj {279421824@qq.com}
 */
public class A38Vo  {
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
    private String updateUserNameByShow;
    private Date updateDateByShow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getA01Id() {
        return a01Id;
    }

    public void setA01Id(String a01Id) {
        this.a01Id = a01Id;
    }

    public String getA0101() {
        return a0101;
    }

    public void setA0101(String a0101) {
        this.a0101 = a0101;
    }

    public String getSmxh() {
        return smxh;
    }

    public void setSmxh(String smxh) {
        this.smxh = smxh;
    }

    public String getDabh() {
        return dabh;
    }

    public void setDabh(String dabh) {
        this.dabh = dabh;
    }

    public String getA0104() {
        return a0104;
    }

    public void setA0104(String a0104) {
        this.a0104 = a0104;
    }

    public String getA0104Content() {
        return a0104Content;
    }

    public void setA0104Content(String a0104Content) {
        this.a0104Content = a0104Content;
    }

    public String getA0107() {
        return a0107;
    }

    public void setA0107(String a0107) {
        this.a0107 = a0107;
    }

    public String getA0111B() {
        return a0111B;
    }

    public void setA0111B(String a0111B) {
        this.a0111B = a0111B;
    }

    public String getA0111A() {
        return a0111A;
    }

    public void setA0111A(String a0111A) {
        this.a0111A = a0111A;
    }

    public String getGbztCode() {
        return gbztCode;
    }

    public void setGbztCode(String gbztCode) {
        this.gbztCode = gbztCode;
    }

    public String getGbztContent() {
        return gbztContent;
    }

    public void setGbztContent(String gbztContent) {
        this.gbztContent = gbztContent;
    }

    public String getDaztCode() {
        return daztCode;
    }

    public void setDaztCode(String daztCode) {
        this.daztCode = daztCode;
    }

    public String getDaztContent() {
        return daztContent;
    }

    public void setDaztContent(String daztContent) {
        this.daztContent = daztContent;
    }

    public String getA0157() {
        return a0157;
    }

    public void setA0157(String a0157) {
        this.a0157 = a0157;
    }

    public String getA3801() {
        return a3801;
    }

    public void setA3801(String a3801) {
        this.a3801 = a3801;
    }

    public String getJsr() {
        return jsr;
    }

    public void setJsr(String jsr) {
        this.jsr = jsr;
    }

    public String getA3804B() {
        return a3804B;
    }

    public void setA3804B(String a3804B) {
        this.a3804B = a3804B;
    }

    public String getA3804A() {
        return a3804A;
    }

    public void setA3804A(String a3804A) {
        this.a3804A = a3804A;
    }

    public String getA3807B() {
        return a3807B;
    }

    public void setA3807B(String a3807B) {
        this.a3807B = a3807B;
    }

    public String getA3807A() {
        return a3807A;
    }

    public void setA3807A(String a3807A) {
        this.a3807A = a3807A;
    }

    public String getA3814() {
        return a3814;
    }

    public void setA3814(String a3814) {
        this.a3814 = a3814;
    }

    public String getA3834() {
        return a3834;
    }

    public void setA3834(String a3834) {
        this.a3834 = a3834;
    }

    public String getSwrq() {
        return swrq;
    }

    public void setSwrq(String swrq) {
        this.swrq = swrq;
    }

    public String getA3824() {
        return a3824;
    }

    public void setA3824(String a3824) {
        this.a3824 = a3824;
    }

    public String getA3817() {
        return a3817;
    }

    public void setA3817(String a3817) {
        this.a3817 = a3817;
    }

    public String getA3821() {
        return a3821;
    }

    public void setA3821(String a3821) {
        this.a3821 = a3821;
    }

    public String getZcyy() {
        return zcyy;
    }

    public void setZcyy(String zcyy) {
        this.zcyy = zcyy;
    }

    public String getJbrId() {
        return jbrId;
    }

    public void setJbrId(String jbrId) {
        this.jbrId = jbrId;
    }

    public String getJbrXm() {
        return jbrXm;
    }

    public void setJbrXm(String jbrXm) {
        this.jbrXm = jbrXm;
    }

    public String getRcbz() {
        return rcbz;
    }

    public void setRcbz(String rcbz) {
        this.rcbz = rcbz;
    }

    public String getSjzt() {
        return sjzt;
    }

    public void setSjzt(String sjzt) {
        this.sjzt = sjzt;
    }

    public String getZryy() {
        return zryy;
    }

    public void setZryy(String zryy) {
        this.zryy = zryy;
    }

    public String getZcTenantId() {
        return zcTenantId;
    }

    public void setZcTenantId(String zcTenantId) {
        this.zcTenantId = zcTenantId;
    }

    public String getZcTenantName() {
        return zcTenantName;
    }

    public void setZcTenantName(String zcTenantName) {
        this.zcTenantName = zcTenantName;
    }

    public String getDasjly() {
        return dasjly;
    }

    public void setDasjly(String dasjly) {
        this.dasjly = dasjly;
    }

    public String getXzjsj() {
        return xzjsj;
    }

    public void setXzjsj(String xzjsj) {
        this.xzjsj = xzjsj;
    }

    public String getSurnameCode() {
        return surnameCode;
    }

    public void setSurnameCode(String surnameCode) {
        this.surnameCode = surnameCode;
    }

    public Integer getNameWordcount() {
        return nameWordcount;
    }

    public void setNameWordcount(Integer nameWordcount) {
        this.nameWordcount = nameWordcount;
    }

    public String getPersonalnameCode() {
        return personalnameCode;
    }

    public void setPersonalnameCode(String personalnameCode) {
        this.personalnameCode = personalnameCode;
    }

    public String getPersonalnameNonecode() {
        return personalnameNonecode;
    }

    public void setPersonalnameNonecode(String personalnameNonecode) {
        this.personalnameNonecode = personalnameNonecode;
    }

    public String getDutyLevelCode() {
        return dutyLevelCode;
    }

    public void setDutyLevelCode(String dutyLevelCode) {
        this.dutyLevelCode = dutyLevelCode;
    }

    public String getDutyLevelValue() {
        return dutyLevelValue;
    }

    public void setDutyLevelValue(String dutyLevelValue) {
        this.dutyLevelValue = dutyLevelValue;
    }

    public String getDutyLevelTimeBase() {
        return dutyLevelTimeBase;
    }

    public void setDutyLevelTimeBase(String dutyLevelTimeBase) {
        this.dutyLevelTimeBase = dutyLevelTimeBase;
    }

    public String getUpdateUserNameByShow() {
        return updateUserNameByShow;
    }

    public void setUpdateUserNameByShow(String updateUserNameByShow) {
        this.updateUserNameByShow = updateUserNameByShow;
    }

    public Date getUpdateDateByShow() {
        return updateDateByShow;
    }

    public void setUpdateDateByShow(Date updateDateByShow) {
        this.updateDateByShow = updateDateByShow;
    }
}
