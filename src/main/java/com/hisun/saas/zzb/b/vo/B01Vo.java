/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.b.vo;

import com.hisun.base.entity.TombstoneEntity;
import com.hisun.saas.zzb.b.entity.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author liuzj {279421824@qq.com}
 */
public class B01Vo  {
    /** 主键值 */
    private String b0100;

    /** 单位名称 */
    private String b0101;

    /** 单位简称 */
    private String b0104;

    /** 单位缩名 */
    private String b0107;

    /** 单位代码ZB02-2006/JGMC */
    private String b0111;

    /** 组织单位代码 */
    private String b0114;

    /** 单位所在地区ZB01-2006/GQMC */
    private String b0117;

    /** 单位隶属关系ZB87-2006/DWLS */
    private String b0124;

    /** 单位级别ZB03-1994/DWJB */
    private String b0127;

    /** 单位级别内容 */
    private String b0127A;

    /** 单位性质类别ZB04-2006/DWXZ */
    private String b0131;

    /** 单位性质类别内容 */
    private String b0131A;

    /** 单位所属行业GB/T4754-2002 */
    private String b0134;

    /** 单位工作职能 */
    private String b0137;

    /** 隶属单位名称 */
    private String b0144A;

    /** 隶属单位代码ZB02-2006/JGMC */
    private String b0144B;

    /** 领导班子主管单位名称 */
    private String b0151A;

    /** 领导班子主管单位代码ZB02-2006/JGMC */
    private String b0151B;

    /** 领导班子协管单位名称 */
    private String b0154A;

    /** 领导班子协管单位代码ZB02-2006/JGMC */
    private String b0154B;

    /** 归口管理单位名称 */
    private String b0157A;

    /** 归口管理单位代码ZB02-2006/JGMC */
    private String b0157B;

    /** 单位负责人 */
    private String b0161;

    /** 单位成立批准日期 */
    private String b0164;

    /** 单位成立批准文号 */
    private String b0167;

    /** 单位成立批准机关名称 */
    private String b0171A;

    /** 单位成立批准机关代码ZB02-2006/JGMC */
    private String b0171B;

    /** 有中共党员标识,1-有，0-无。默认1 */
    private String b0174;

    /** 有中共组织标识,0-无；1-党委；2-党总支部；3-党支部；4-党小组 */
    private String b0177;

    /** 单位撤销批准日期 */
    private String b0181;

    /** 单位撤销批准文号 */
    private String b0184;

    /** 单位撤销批准机关名称 */
    private String b0187A;

    /** 单位撤销批准机关代码ZB02-2006/JGMC */
    private String b0187B;

    /** 法人单位代码 */
    private String b0191;

    /** 法人单位标识，1-是，2-否，与b_sjlx同步 */
    private String b0194;

    /** 单位说明 */
    private String b0197;

    /** 单位状态，01－当前，02－撤销，02001-撤销单位,02002-撤销并入其他单位,03－取消级别 */
    private String bDwztB;

    /** 状态名称 */
    private String bDwztA;

    /** 是否为临时单位,0－否，1-是 */
    private String bSfls;

    /** 单位管理类别:01－中央管理，02－省级党委管理，03－市级党委管理，04－县级党委管理，05－其他 */
    private String bGllbB;

    /** 单位管理类别内容 */
    private String bGllbA;

    /** 是否统计职数，默认是-1，否-0 */
    private String bSftjzs;

    /** 该单位下最高正职职务级别 */
    private String bZgzzjbB;

    /** 该单位下最高正职职务名称 */
    private String bZgzzjbA;

    /** 单位最高副职级别 */
    private String bZgfzjbB;

    /** 单位最高副职级别名称 */
    private String bZgfzjbA;

    /** 上级机构 */
    private String parentId;

    /** 排序 */
    private Integer bPx;

    /** 查询编码 */
    private String bCxbm;

    /** 数据类型0-法人机构，1-内设机构，2-机构分组 */
    private String bSjlx;

    /** 计入公务员统计,默认是-1，0-否 */
    private String bSfgwytj;
    /**  备注*/
    private String remark;
    //新增 和修改标识
    private String B01Id;

    public String getB01Id() {
        return B01Id;
    }

    public void setB01Id(String b01Id) {
        B01Id = b01Id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getB0100() {
        return b0100;
    }

    public void setB0100(String b0100) {
        this.b0100 = b0100;
    }

    public String getB0101() {
        return b0101;
    }

    public void setB0101(String b0101) {
        this.b0101 = b0101;
    }

    public String getB0104() {
        return b0104;
    }

    public void setB0104(String b0104) {
        this.b0104 = b0104;
    }

    public String getB0107() {
        return b0107;
    }

    public void setB0107(String b0107) {
        this.b0107 = b0107;
    }

    public String getB0111() {
        return b0111;
    }

    public void setB0111(String b0111) {
        this.b0111 = b0111;
    }

    public String getB0114() {
        return b0114;
    }

    public void setB0114(String b0114) {
        this.b0114 = b0114;
    }

    public String getB0117() {
        return b0117;
    }

    public void setB0117(String b0117) {
        this.b0117 = b0117;
    }

    public String getB0124() {
        return b0124;
    }

    public void setB0124(String b0124) {
        this.b0124 = b0124;
    }

    public String getB0127() {
        return b0127;
    }

    public void setB0127(String b0127) {
        this.b0127 = b0127;
    }

    public String getB0127A() {
        return b0127A;
    }

    public void setB0127A(String b0127A) {
        this.b0127A = b0127A;
    }

    public String getB0131() {
        return b0131;
    }

    public void setB0131(String b0131) {
        this.b0131 = b0131;
    }

    public String getB0131A() {
        return b0131A;
    }

    public void setB0131A(String b0131A) {
        this.b0131A = b0131A;
    }

    public String getB0134() {
        return b0134;
    }

    public void setB0134(String b0134) {
        this.b0134 = b0134;
    }

    public String getB0137() {
        return b0137;
    }

    public void setB0137(String b0137) {
        this.b0137 = b0137;
    }

    public String getB0144A() {
        return b0144A;
    }

    public void setB0144A(String b0144A) {
        this.b0144A = b0144A;
    }

    public String getB0144B() {
        return b0144B;
    }

    public void setB0144B(String b0144B) {
        this.b0144B = b0144B;
    }

    public String getB0151A() {
        return b0151A;
    }

    public void setB0151A(String b0151A) {
        this.b0151A = b0151A;
    }

    public String getB0151B() {
        return b0151B;
    }

    public void setB0151B(String b0151B) {
        this.b0151B = b0151B;
    }

    public String getB0154A() {
        return b0154A;
    }

    public void setB0154A(String b0154A) {
        this.b0154A = b0154A;
    }

    public String getB0154B() {
        return b0154B;
    }

    public void setB0154B(String b0154B) {
        this.b0154B = b0154B;
    }

    public String getB0157A() {
        return b0157A;
    }

    public void setB0157A(String b0157A) {
        this.b0157A = b0157A;
    }

    public String getB0157B() {
        return b0157B;
    }

    public void setB0157B(String b0157B) {
        this.b0157B = b0157B;
    }

    public String getB0161() {
        return b0161;
    }

    public void setB0161(String b0161) {
        this.b0161 = b0161;
    }

    public String getB0164() {
        return b0164;
    }

    public void setB0164(String b0164) {
        this.b0164 = b0164;
    }

    public String getB0167() {
        return b0167;
    }

    public void setB0167(String b0167) {
        this.b0167 = b0167;
    }

    public String getB0171A() {
        return b0171A;
    }

    public void setB0171A(String b0171A) {
        this.b0171A = b0171A;
    }

    public String getB0171B() {
        return b0171B;
    }

    public void setB0171B(String b0171B) {
        this.b0171B = b0171B;
    }

    public String getB0174() {
        return b0174;
    }

    public void setB0174(String b0174) {
        this.b0174 = b0174;
    }

    public String getB0177() {
        return b0177;
    }

    public void setB0177(String b0177) {
        this.b0177 = b0177;
    }

    public String getB0181() {
        return b0181;
    }

    public void setB0181(String b0181) {
        this.b0181 = b0181;
    }

    public String getB0184() {
        return b0184;
    }

    public void setB0184(String b0184) {
        this.b0184 = b0184;
    }

    public String getB0187A() {
        return b0187A;
    }

    public void setB0187A(String b0187A) {
        this.b0187A = b0187A;
    }

    public String getB0187B() {
        return b0187B;
    }

    public void setB0187B(String b0187B) {
        this.b0187B = b0187B;
    }

    public String getB0191() {
        return b0191;
    }

    public void setB0191(String b0191) {
        this.b0191 = b0191;
    }

    public String getB0194() {
        return b0194;
    }

    public void setB0194(String b0194) {
        this.b0194 = b0194;
    }

    public String getB0197() {
        return b0197;
    }

    public void setB0197(String b0197) {
        this.b0197 = b0197;
    }

    public String getbDwztB() {
        return bDwztB;
    }

    public void setbDwztB(String bDwztB) {
        this.bDwztB = bDwztB;
    }

    public String getbDwztA() {
        return bDwztA;
    }

    public void setbDwztA(String bDwztA) {
        this.bDwztA = bDwztA;
    }

    public String getbSfls() {
        return bSfls;
    }

    public void setbSfls(String bSfls) {
        this.bSfls = bSfls;
    }

    public String getbGllbB() {
        return bGllbB;
    }

    public void setbGllbB(String bGllbB) {
        this.bGllbB = bGllbB;
    }

    public String getbGllbA() {
        return bGllbA;
    }

    public void setbGllbA(String bGllbA) {
        this.bGllbA = bGllbA;
    }

    public String getbSftjzs() {
        return bSftjzs;
    }

    public void setbSftjzs(String bSftjzs) {
        this.bSftjzs = bSftjzs;
    }

    public String getbZgzzjbB() {
        return bZgzzjbB;
    }

    public void setbZgzzjbB(String bZgzzjbB) {
        this.bZgzzjbB = bZgzzjbB;
    }

    public String getbZgzzjbA() {
        return bZgzzjbA;
    }

    public void setbZgzzjbA(String bZgzzjbA) {
        this.bZgzzjbA = bZgzzjbA;
    }

    public String getbZgfzjbB() {
        return bZgfzjbB;
    }

    public void setbZgfzjbB(String bZgfzjbB) {
        this.bZgfzjbB = bZgfzjbB;
    }

    public String getbZgfzjbA() {
        return bZgfzjbA;
    }

    public void setbZgfzjbA(String bZgfzjbA) {
        this.bZgfzjbA = bZgfzjbA;
    }

    public Integer getbPx() {
        return bPx;
    }

    public void setbPx(Integer bPx) {
        this.bPx = bPx;
    }

    public String getbCxbm() {
        return bCxbm;
    }

    public void setbCxbm(String bCxbm) {
        this.bCxbm = bCxbm;
    }

    public String getbSjlx() {
        return bSjlx;
    }

    public void setbSjlx(String bSjlx) {
        this.bSjlx = bSjlx;
    }

    public String getbSfgwytj() {
        return bSfgwytj;
    }

    public void setbSfgwytj(String bSfgwytj) {
        this.bSfgwytj = bSfgwytj;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
