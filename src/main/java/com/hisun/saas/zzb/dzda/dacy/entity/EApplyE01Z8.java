/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.entity;

import com.hisun.base.entity.TombstoneEntity;
import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.e01z4.entity.E01Z4;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author liuzj {279421824@qq.com}
 */
@Entity
@Table(name = "e_apply_e01z8")
    public class EApplyE01Z8  extends TenantEntity implements Serializable {
    private String id;//查阅记录id
    private A38 a38;//档案id
    private String a0101;//档案所属人姓名
    private String manageTenantId;//档案管理单位id ，即申请单位id
    private String sqcydazw;//申请查阅时填写的档案所属人单位职务
    private String manageTenantName;//档案管理单位名称，即申请单位名称
    private String e01Z801;//批准人姓名
    private String readContent;//查阅内容
    private String e01Z807Id;//查阅人id
    private String e01Z807Name;//查阅人姓名
    private String e01Z824B;//查阅人单位id
    private String e01Z824A;//查阅单位名称
    private String readTime;//查阅时长
    private String readDate;//开始查阅时间
    private String accreditDate;//授权查阅时间
    private String endReadDate;//结束查阅时间
    private String auditingState;//审核状态。0：待审；1：已审；2：拒绝授权 3:收回权限 4:结束阅档
    private String refuseReason;//拒绝原因
    private String readState;//查阅状态。0：未查阅；1：已查阅。审核通过后提醒用户查阅档案，查阅后即不再提醒
    private String accreditType;//授权类型。0：申请查阅授权；1：管理员主动授权查看记录
    private String popedomStuffType;//授权材料范围类型。0：授权所有档案材料；1：授权指定的档案材料
    private String applyRemark;//申请备注。申请阅档时由申请用户填写
    private String auditingRemark;//审核备注。管理员审核申请时填写
    private String isShowToA0101;//是否对申请用户可见。0:可见；1：不可见。申请用户删除此条信息时只是改变此状态。只有在管理员删除时才真正的删除
    private String alreadyReadTime;//已经查看的时长
    private String phone;//联系电话
    private String sqdwpzld;//申请单位批准领导
    private String isPrint;//是否能打印 0表示不打印 1表示打印
    private Serializable checkTime;//处理时间
    private String isDownload;//是否能下载图片 1表示能下载 0表示不能下载
    private String officePhone;//办公电话
    private String applyType;//申请产生的记录标示为0 管理员主动授权的为1（包括管理员再次授权的）
    private String parentApplyE01Z8Id;//再次授权的父id
    private String viewIpInfo;//当前浏览档案的ip
    private String applyFileName;//上传附件名称
    private String applyFilePath;//上传附件路径
    private String applyUserId;//申请查阅人id
    private String applyUserName;//申请查阅人名字
    private List<EA38Log> a38Logs;//
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
    @Column(name = "sqcydazw", nullable = true, length = 256)
    public String getSqcydazw() {
        return sqcydazw;
    }

    public void setSqcydazw(String sqcydazw) {
        this.sqcydazw = sqcydazw;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "a38_id")
    public A38 getA38() {
        return a38;
    }

    public void setA38(A38 a38) {
        this.a38 = a38;
    }
    @OneToMany(mappedBy = "applyE01Z8", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<EA38Log> getA38Logs() {
        return a38Logs;
    }

    public void setA38Logs(List<EA38Log> a38Logs) {
        this.a38Logs = a38Logs;
    }

    @Basic
    @Column(name = "a0101", nullable = true, length = 16)
    public String getA0101() {
        return a0101;
    }

    public void setA0101(String a0101) {
        this.a0101 = a0101;
    }

    @Basic
    @Column(name = "manage_tenant_id", nullable = true, length = 128)
    public String getManageTenantId() {
        return manageTenantId;
    }

    public void setManageTenantId(String manageTenantId) {
        this.manageTenantId = manageTenantId;
    }

    @Basic
    @Column(name = "manage_tenant_name", nullable = true, length = 128)
    public String getManageTenantName() {
        return manageTenantName;
    }

    public void setManageTenantName(String manageTenantName) {
        this.manageTenantName = manageTenantName;
    }

    @Basic
    @Column(name = "e01z801", nullable = true, length = 16)
    public String getE01Z801() {
        return e01Z801;
    }

    public void setE01Z801(String e01Z801) {
        this.e01Z801 = e01Z801;
    }

    @Basic
    @Column(name = "read_content", nullable = true, length = 512)
    public String getReadContent() {
        return readContent;
    }

    public void setReadContent(String readContent) {
        this.readContent = readContent;
    }

    @Basic
    @Column(name = "e01z807_id", nullable = true, length = 64)
    public String getE01Z807Id() {
        return e01Z807Id;
    }

    public void setE01Z807Id(String e01Z807Id) {
        this.e01Z807Id = e01Z807Id;
    }

    @Basic
    @Column(name = "e01z807_name", nullable = true, length = 1024)
    public String getE01Z807Name() {
        return e01Z807Name;
    }

    public void setE01Z807Name(String e01Z807Name) {
        this.e01Z807Name = e01Z807Name;
    }

    @Basic
    @Column(name = "e01z824b", nullable = true, length = 128)
    public String getE01Z824B() {
        return e01Z824B;
    }

    public void setE01Z824B(String e01Z824B) {
        this.e01Z824B = e01Z824B;
    }

    @Basic
    @Column(name = "e01z824a", nullable = true, length = 128)
    public String getE01Z824A() {
        return e01Z824A;
    }

    public void setE01Z824A(String e01Z824A) {
        this.e01Z824A = e01Z824A;
    }

    @Basic
    @Column(name = "read_time", nullable = true, length = 64)
    public String getReadTime() {
        return readTime;
    }

    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }

    @Basic
    @Column(name = "read_date", nullable = true, length = 64)
    public String getReadDate() {
        return readDate;
    }

    public void setReadDate(String readDate) {
        this.readDate = readDate;
    }

    @Basic
    @Column(name = "accredit_date", nullable = true, length = 64)
    public String getAccreditDate() {
        return accreditDate;
    }

    public void setAccreditDate(String accreditDate) {
        this.accreditDate = accreditDate;
    }

    @Basic
    @Column(name = "end_read_date", nullable = true, length = 64)
    public String getEndReadDate() {
        return endReadDate;
    }

    public void setEndReadDate(String endReadDate) {
        this.endReadDate = endReadDate;
    }

    @Basic
    @Column(name = "auditing_state", nullable = true, length = 1)
    public String getAuditingState() {
        return auditingState;
    }

    public void setAuditingState(String auditingState) {
        this.auditingState = auditingState;
    }

    @Basic
    @Column(name = "refuse_reason", nullable = true, length = 512)
    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    @Basic
    @Column(name = "read_state", nullable = true, length = 1)
    public String getReadState() {
        return readState;
    }

    public void setReadState(String readState) {
        this.readState = readState;
    }

    @Basic
    @Column(name = "accredit_type", nullable = true, length = 1)
    public String getAccreditType() {
        return accreditType;
    }

    public void setAccreditType(String accreditType) {
        this.accreditType = accreditType;
    }

    @Basic
    @Column(name = "popedom_stuff_type", nullable = true, length = 1)
    public String getPopedomStuffType() {
        return popedomStuffType;
    }

    public void setPopedomStuffType(String popedomStuffType) {
        this.popedomStuffType = popedomStuffType;
    }

    @Basic
    @Column(name = "apply_remark", nullable = true, length = 1024)
    public String getApplyRemark() {
        return applyRemark;
    }

    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark;
    }

    @Basic
    @Column(name = "auditing_remark", nullable = true, length = 1020)
    public String getAuditingRemark() {
        return auditingRemark;
    }

    public void setAuditingRemark(String auditingRemark) {
        this.auditingRemark = auditingRemark;
    }

    @Basic
    @Column(name = "is_show_to_a0101", nullable = true, length = 1)
    public String getIsShowToA0101() {
        return isShowToA0101;
    }

    public void setIsShowToA0101(String isShowToA0101) {
        this.isShowToA0101 = isShowToA0101;
    }

    @Basic
    @Column(name = "already_read_time", nullable = true, length = 64)
    public String getAlreadyReadTime() {
        return alreadyReadTime;
    }

    public void setAlreadyReadTime(String alreadyReadTime) {
        this.alreadyReadTime = alreadyReadTime;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 64)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "sqdwpzld", nullable = true, length = 64)
    public String getSqdwpzld() {
        return sqdwpzld;
    }

    public void setSqdwpzld(String sqdwpzld) {
        this.sqdwpzld = sqdwpzld;
    }

    @Basic
    @Column(name = "is_print", nullable = true, length = 1)
    public String getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(String isPrint) {
        this.isPrint = isPrint;
    }

    @Basic
    @Column(name = "check_time", nullable = true)
    public Serializable getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Serializable checkTime) {
        this.checkTime = checkTime;
    }

    @Basic
    @Column(name = "is_download", nullable = true, length = 1)
    public String getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(String isDownload) {
        this.isDownload = isDownload;
    }

    @Basic
    @Column(name = "office_phone", nullable = true, length = 128)
    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    @Basic
    @Column(name = "apply_type", nullable = true, length = 32)
    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    @Basic
    @Column(name = "parent_apply_e01z8_id", nullable = true, length = 64)
    public String getParentApplyE01Z8Id() {
        return parentApplyE01Z8Id;
    }

    public void setParentApplyE01Z8Id(String parentApplyE01Z8Id) {
        this.parentApplyE01Z8Id = parentApplyE01Z8Id;
    }

    @Basic
    @Column(name = "view_ip_info", nullable = true, length = 128)
    public String getViewIpInfo() {
        return viewIpInfo;
    }

    public void setViewIpInfo(String viewIpInfo) {
        this.viewIpInfo = viewIpInfo;
    }

    @Basic
    @Column(name = "apply_file_name", nullable = true, length = 512)
    public String getApplyFileName() {
        return applyFileName;
    }

    public void setApplyFileName(String applyFileName) {
        this.applyFileName = applyFileName;
    }

    @Basic
    @Column(name = "apply_file_path", nullable = true, length = 512)
    public String getApplyFilePath() {
        return applyFilePath;
    }

    public void setApplyFilePath(String applyFilePath) {
        this.applyFilePath = applyFilePath;
    }

    @Basic
    @Column(name = "apply_user_id", nullable = true, length = 64)
    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    @Basic
    @Column(name = "apply_user_name", nullable = true, length = 128)
    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EApplyE01Z8 that = (EApplyE01Z8) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (a0101 != null ? !a0101.equals(that.a0101) : that.a0101 != null) return false;
        if (manageTenantId != null ? !manageTenantId.equals(that.manageTenantId) : that.manageTenantId != null)
            return false;
        if (manageTenantName != null ? !manageTenantName.equals(that.manageTenantName) : that.manageTenantName != null)
            return false;
        if (e01Z801 != null ? !e01Z801.equals(that.e01Z801) : that.e01Z801 != null) return false;
        if (readContent != null ? !readContent.equals(that.readContent) : that.readContent != null) return false;
        if (e01Z807Id != null ? !e01Z807Id.equals(that.e01Z807Id) : that.e01Z807Id != null) return false;
        if (e01Z807Name != null ? !e01Z807Name.equals(that.e01Z807Name) : that.e01Z807Name != null) return false;
        if (e01Z824B != null ? !e01Z824B.equals(that.e01Z824B) : that.e01Z824B != null) return false;
        if (e01Z824A != null ? !e01Z824A.equals(that.e01Z824A) : that.e01Z824A != null) return false;
        if (readTime != null ? !readTime.equals(that.readTime) : that.readTime != null) return false;
        if (readDate != null ? !readDate.equals(that.readDate) : that.readDate != null) return false;
        if (accreditDate != null ? !accreditDate.equals(that.accreditDate) : that.accreditDate != null) return false;
        if (endReadDate != null ? !endReadDate.equals(that.endReadDate) : that.endReadDate != null) return false;
        if (auditingState != null ? !auditingState.equals(that.auditingState) : that.auditingState != null)
            return false;
        if (refuseReason != null ? !refuseReason.equals(that.refuseReason) : that.refuseReason != null) return false;
        if (readState != null ? !readState.equals(that.readState) : that.readState != null) return false;
        if (accreditType != null ? !accreditType.equals(that.accreditType) : that.accreditType != null) return false;
        if (popedomStuffType != null ? !popedomStuffType.equals(that.popedomStuffType) : that.popedomStuffType != null)
            return false;
        if (applyRemark != null ? !applyRemark.equals(that.applyRemark) : that.applyRemark != null) return false;
        if (auditingRemark != null ? !auditingRemark.equals(that.auditingRemark) : that.auditingRemark != null)
            return false;
        if (isShowToA0101 != null ? !isShowToA0101.equals(that.isShowToA0101) : that.isShowToA0101 != null)
            return false;
        if (alreadyReadTime != null ? !alreadyReadTime.equals(that.alreadyReadTime) : that.alreadyReadTime != null)
            return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (sqdwpzld != null ? !sqdwpzld.equals(that.sqdwpzld) : that.sqdwpzld != null) return false;
        if (isPrint != null ? !isPrint.equals(that.isPrint) : that.isPrint != null) return false;
        if (checkTime != null ? !checkTime.equals(that.checkTime) : that.checkTime != null) return false;
        if (isDownload != null ? !isDownload.equals(that.isDownload) : that.isDownload != null) return false;
        if (officePhone != null ? !officePhone.equals(that.officePhone) : that.officePhone != null) return false;
        if (applyType != null ? !applyType.equals(that.applyType) : that.applyType != null) return false;
        if (parentApplyE01Z8Id != null ? !parentApplyE01Z8Id.equals(that.parentApplyE01Z8Id) : that.parentApplyE01Z8Id != null)
            return false;
        if (viewIpInfo != null ? !viewIpInfo.equals(that.viewIpInfo) : that.viewIpInfo != null) return false;
        if (applyFileName != null ? !applyFileName.equals(that.applyFileName) : that.applyFileName != null)
            return false;
        if (applyFilePath != null ? !applyFilePath.equals(that.applyFilePath) : that.applyFilePath != null)
            return false;
        if (applyUserId != null ? !applyUserId.equals(that.applyUserId) : that.applyUserId != null) return false;
        if (applyUserName != null ? !applyUserName.equals(that.applyUserName) : that.applyUserName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (a0101 != null ? a0101.hashCode() : 0);
        result = 31 * result + (manageTenantId != null ? manageTenantId.hashCode() : 0);
        result = 31 * result + (manageTenantName != null ? manageTenantName.hashCode() : 0);
        result = 31 * result + (e01Z801 != null ? e01Z801.hashCode() : 0);
        result = 31 * result + (readContent != null ? readContent.hashCode() : 0);
        result = 31 * result + (e01Z807Id != null ? e01Z807Id.hashCode() : 0);
        result = 31 * result + (e01Z807Name != null ? e01Z807Name.hashCode() : 0);
        result = 31 * result + (e01Z824B != null ? e01Z824B.hashCode() : 0);
        result = 31 * result + (e01Z824A != null ? e01Z824A.hashCode() : 0);
        result = 31 * result + (readTime != null ? readTime.hashCode() : 0);
        result = 31 * result + (readDate != null ? readDate.hashCode() : 0);
        result = 31 * result + (accreditDate != null ? accreditDate.hashCode() : 0);
        result = 31 * result + (endReadDate != null ? endReadDate.hashCode() : 0);
        result = 31 * result + (auditingState != null ? auditingState.hashCode() : 0);
        result = 31 * result + (refuseReason != null ? refuseReason.hashCode() : 0);
        result = 31 * result + (readState != null ? readState.hashCode() : 0);
        result = 31 * result + (accreditType != null ? accreditType.hashCode() : 0);
        result = 31 * result + (popedomStuffType != null ? popedomStuffType.hashCode() : 0);
        result = 31 * result + (applyRemark != null ? applyRemark.hashCode() : 0);
        result = 31 * result + (auditingRemark != null ? auditingRemark.hashCode() : 0);
        result = 31 * result + (isShowToA0101 != null ? isShowToA0101.hashCode() : 0);
        result = 31 * result + (alreadyReadTime != null ? alreadyReadTime.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (sqdwpzld != null ? sqdwpzld.hashCode() : 0);
        result = 31 * result + (isPrint != null ? isPrint.hashCode() : 0);
        result = 31 * result + (checkTime != null ? checkTime.hashCode() : 0);
        result = 31 * result + (isDownload != null ? isDownload.hashCode() : 0);
        result = 31 * result + (officePhone != null ? officePhone.hashCode() : 0);
        result = 31 * result + (applyType != null ? applyType.hashCode() : 0);
        result = 31 * result + (parentApplyE01Z8Id != null ? parentApplyE01Z8Id.hashCode() : 0);
        result = 31 * result + (viewIpInfo != null ? viewIpInfo.hashCode() : 0);
        result = 31 * result + (applyFileName != null ? applyFileName.hashCode() : 0);
        result = 31 * result + (applyFilePath != null ? applyFilePath.hashCode() : 0);
        result = 31 * result + (applyUserId != null ? applyUserId.hashCode() : 0);
        result = 31 * result + (applyUserName != null ? applyUserName.hashCode() : 0);
        return result;
    }
}
