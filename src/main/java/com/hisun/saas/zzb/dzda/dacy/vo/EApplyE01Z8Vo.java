/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.vo;

import java.io.Serializable;

/**
 * @author Marco {854476391@qq.com}
 */
public class EApplyE01Z8Vo implements Serializable{
    private String id;
    private String a0101;//档案所属人姓名
    private String a0101Content;//档案所属人姓名拼接字符串
    private String manageTenantName;//档案管理单位名称，即申请单位名称
    private String sqcydazw;//申请查阅时填写的档案所属人单位职务
    private String readContent;//查阅内容
    private String e01Z824A;//查阅单位名称
    private String readTime;//查阅时长
    private String applyFileName;//上传附件名称
    private String phone;//联系电话
    private String applyRemark;//申请备注。申请阅档时由申请用户填写
    private String auditingRemark;//审核备注。管理员审核申请时填写
    private String accreditType;//授权类型。0：申请查阅授权；1：管理员主动授权查看记录
    private String popedomStuffType;//授权材料范围类型。0：授权所有档案材料；1：授权指定的档案材料
    private String refuseReason;//拒绝原因
    private String e01Z807Name;//查阅人姓名
    private String applyFilePath;//上传附件路径
    private String applyUserName;//申请查阅人名字
    private String isDownload;//是否能下载图片 1表示能下载 0表示不能下载
    private String sqdwpzld;//申请单位批准领导
    private String a38Id;
    private String auditingState;//审核状态。0：待审；1：已审；2：拒绝授权 3:收回权限

    public String getAuditingState() {
        return auditingState;
    }

    public void setAuditingState(String auditingState) {
        this.auditingState = auditingState;
    }

    public String getA38Id() {
        return a38Id;
    }

    public void setA38Id(String a38Id) {
        this.a38Id = a38Id;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(String isDownload) {
        this.isDownload = isDownload;
    }

    public String getSqdwpzld() {
        return sqdwpzld;
    }

    public void setSqdwpzld(String sqdwpzld) {
        this.sqdwpzld = sqdwpzld;
    }

    public String getApplyFilePath() {
        return applyFilePath;
    }

    public void setApplyFilePath(String applyFilePath) {
        this.applyFilePath = applyFilePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getE01Z807Name() {
        return e01Z807Name;
    }

    public void setE01Z807Name(String e01Z807Name) {
        this.e01Z807Name = e01Z807Name;
    }

    public String getSqcydazw() {
        return sqcydazw;
    }

    public void setSqcydazw(String sqcydazw) {
        this.sqcydazw = sqcydazw;
    }

    public String getA0101() {
        return a0101;
    }

    public void setA0101(String a0101) {
        this.a0101 = a0101;
    }

    public String getA0101Content() {
        return a0101Content;
    }

    public void setA0101Content(String a0101Content) {
        this.a0101Content = a0101Content;
    }

    public String getAccreditType() {
        return accreditType;
    }

    public void setAccreditType(String accreditType) {
        this.accreditType = accreditType;
    }

    public String getApplyFileName() {
        return applyFileName;
    }

    public void setApplyFileName(String applyFileName) {
        this.applyFileName = applyFileName;
    }

    public String getApplyRemark() {
        return applyRemark;
    }

    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark;
    }

    public String getAuditingRemark() {
        return auditingRemark;
    }

    public void setAuditingRemark(String auditingRemark) {
        this.auditingRemark = auditingRemark;
    }

    public String getE01Z824A() {
        return e01Z824A;
    }

    public void setE01Z824A(String e01Z824A) {
        this.e01Z824A = e01Z824A;
    }

    public String getManageTenantName() {
        return manageTenantName;
    }

    public void setManageTenantName(String manageTenantName) {
        this.manageTenantName = manageTenantName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPopedomStuffType() {
        return popedomStuffType;
    }

    public void setPopedomStuffType(String popedomStuffType) {
        this.popedomStuffType = popedomStuffType;
    }

    public String getReadContent() {
        return readContent;
    }

    public void setReadContent(String readContent) {
        this.readContent = readContent;
    }

    public String getReadTime() {
        return readTime;
    }

    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }
}
