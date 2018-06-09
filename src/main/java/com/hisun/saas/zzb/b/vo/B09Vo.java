package com.hisun.saas.zzb.b.vo;

import com.hisun.saas.zzb.b.entity.B01;

/**
 * Created by 60514 on 2018/6/5.
 */
public class B09Vo {
    /** 主键 */
    private String b0900;

    /** 查询职务名称 */
    private String b0901A;

    /** 查询职务名称代码ZB08-2006/ZWMC */
    private String b0901B;

    /** 职务类别ZB42-2006/ZWLB */
    private String b0904;

    /** 职务类别名称 */
    private String b0904A;

    /** 职级ZB09-2006/ZWJB */
    private String b0907;

    /** 职务级别名称 */
    private String b0907A;

    /** 职数 */
    private Integer b0911;

    /** 现配职数 */
    private Integer b0914;

    /** 超编原因 */
    private String b0917;

    /** 缺编原因 */
    private String b0921;

    /** 职务排序 */
    private Integer bPx;

    /** 单位职务名称 */
    private String bDwzwmc;

    /** 职数管理名称 */
    private String bZsglmc;

    /** 是否存在兼任，1－存在，0－不存在 */
    private String bSfjr;

    /** 是否能存在包含，1－存在，0－不存在 */
    private String bSfbh;

    /** 是否占编制，1－占用，0－不占用 */
    private String bSfzb;

    /** 是否领导职务，1－是，0－否,默认1 */
    private String bSfldzw;
    /** 是否领导职务，1－是，0－否,默认1 */
    private String bSfldzwB;

    /** 是否班子成员，1－是，0－否  */
    private String bSfbzcy;

    /** 是否需要试用 */
    private String bSfsy;

    /** 试用时长 */
    private Integer bSysc;

    /** 试用时长单位,1－月，2－年 */
    private String bSyscdw;

    /** 退休年龄,格式(60,60) */
    private String bTxnl;

    /** 该职务是否存在多职级，1－是，0－否  */
    private String bSfdzj;

    /** 是否在本单位名册中显示此职务，1－是，0－否。默认为是 */
    private String bSfmcxs;

    /** 管理类别，单位管理类别:01－中央管理，02－省级党委管理，03－市级党委管理，04－县级党委管理，05－其他 */
    private String bGllbB;

    /** 管理类别说明，单位管理类别:01－中央管理，02－省级党委管理，03－市级党委管理，04－县级党委管理，05－其他 */
    private String bGllbA;

    /** 是否统计职数:0-不统计,1-统计 */
    private String bSftjzs;

    /** 编制性质ZB61-2006/BZXZ */
    private String bBzxzB;

    /** 所属编制性质名字 */
    private String bBzxzA;

    /** 排序参照职务id */
    private String bCzpxzwB;

    /** 排序参照职务名称 */
    private String bCzpxzwA;

    /** 职务状态1-在编，0-撤销，默认1 */
    private String bZwztB;

    /** 职务状态内容 */
    private String bZwztA;

    /** 计算是否领导职务*/
    private String b0127;
    private String b0127A;

    private String jrB0901a1;
    private String jrGlB09001;
    private Integer zs1;//职数
    private String jrB0901a2;
    private String jrGlB09002;
    private Integer zs2;//职数
    private int isZs2;
    private String jrB0901a3;
    private String jrGlB09003;
    private Integer zs3;//职数
    private int isZs3;
    private int isGd;//是否展示更多按钮

    private B01Vo b01Vo;

    private String updateData;

    public String getUpdateData() {
        return updateData;
    }

    public void setUpdateData(String updateData) {
        this.updateData = updateData;
    }

    public String getB0900() {
        return b0900;
    }

    public void setB0900(String b0900) {
        this.b0900 = b0900;
    }

    public String getB0901A() {
        return b0901A;
    }

    public void setB0901A(String b0901A) {
        this.b0901A = b0901A;
    }

    public String getB0901B() {
        return b0901B;
    }

    public void setB0901B(String b0901B) {
        this.b0901B = b0901B;
    }

    public String getB0904() {
        return b0904;
    }

    public void setB0904(String b0904) {
        this.b0904 = b0904;
    }

    public String getB0904A() {
        return b0904A;
    }

    public void setB0904A(String b0904A) {
        this.b0904A = b0904A;
    }

    public String getB0907() {
        return b0907;
    }

    public void setB0907(String b0907) {
        this.b0907 = b0907;
    }

    public String getB0907A() {
        return b0907A;
    }

    public void setB0907A(String b0907A) {
        this.b0907A = b0907A;
    }

    public Integer getB0911() {
        return b0911;
    }

    public void setB0911(Integer b0911) {
        this.b0911 = b0911;
    }

    public Integer getB0914() {
        return b0914;
    }

    public void setB0914(Integer b0914) {
        this.b0914 = b0914;
    }

    public String getB0917() {
        return b0917;
    }

    public void setB0917(String b0917) {
        this.b0917 = b0917;
    }

    public String getB0921() {
        return b0921;
    }

    public void setB0921(String b0921) {
        this.b0921 = b0921;
    }

    public Integer getbPx() {
        return bPx;
    }

    public void setbPx(Integer bPx) {
        this.bPx = bPx;
    }

    public String getbDwzwmc() {
        return bDwzwmc;
    }

    public void setbDwzwmc(String bDwzwmc) {
        this.bDwzwmc = bDwzwmc;
    }

    public String getbZsglmc() {
        return bZsglmc;
    }

    public void setbZsglmc(String bZsglmc) {
        this.bZsglmc = bZsglmc;
    }

    public String getbSfjr() {
        return bSfjr;
    }

    public void setbSfjr(String bSfjr) {
        this.bSfjr = bSfjr;
    }

    public String getbSfbh() {
        return bSfbh;
    }

    public void setbSfbh(String bSfbh) {
        this.bSfbh = bSfbh;
    }

    public String getbSfzb() {
        return bSfzb;
    }

    public void setbSfzb(String bSfzb) {
        this.bSfzb = bSfzb;
    }

    public String getbSfldzw() {
        return bSfldzw;
    }

    public void setbSfldzw(String bSfldzw) {
        this.bSfldzw = bSfldzw;
    }

    public String getbSfbzcy() {
        return bSfbzcy;
    }

    public void setbSfbzcy(String bSfbzcy) {
        this.bSfbzcy = bSfbzcy;
    }

    public String getbSfsy() {
        return bSfsy;
    }

    public void setbSfsy(String bSfsy) {
        this.bSfsy = bSfsy;
    }

    public Integer getbSysc() {
        return bSysc;
    }

    public void setbSysc(Integer bSysc) {
        this.bSysc = bSysc;
    }

    public String getbSyscdw() {
        return bSyscdw;
    }

    public void setbSyscdw(String bSyscdw) {
        this.bSyscdw = bSyscdw;
    }

    public String getbTxnl() {
        return bTxnl;
    }

    public void setbTxnl(String bTxnl) {
        this.bTxnl = bTxnl;
    }

    public String getbSfdzj() {
        return bSfdzj;
    }

    public void setbSfdzj(String bSfdzj) {
        this.bSfdzj = bSfdzj;
    }

    public String getbSfmcxs() {
        return bSfmcxs;
    }

    public void setbSfmcxs(String bSfmcxs) {
        this.bSfmcxs = bSfmcxs;
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

    public String getbBzxzB() {
        return bBzxzB;
    }

    public void setbBzxzB(String bBzxzB) {
        this.bBzxzB = bBzxzB;
    }

    public String getbBzxzA() {
        return bBzxzA;
    }

    public void setbBzxzA(String bBzxzA) {
        this.bBzxzA = bBzxzA;
    }

    public String getbCzpxzwB() {
        return bCzpxzwB;
    }

    public void setbCzpxzwB(String bCzpxzwB) {
        this.bCzpxzwB = bCzpxzwB;
    }

    public String getbCzpxzwA() {
        return bCzpxzwA;
    }

    public void setbCzpxzwA(String bCzpxzwA) {
        this.bCzpxzwA = bCzpxzwA;
    }

    public String getbZwztB() {
        return bZwztB;
    }

    public void setbZwztB(String bZwztB) {
        this.bZwztB = bZwztB;
    }

    public String getbZwztA() {
        return bZwztA;
    }

    public void setbZwztA(String bZwztA) {
        this.bZwztA = bZwztA;
    }

    public B01Vo getB01Vo() {
        return b01Vo;
    }

    public void setB01Vo(B01Vo b01Vo) {
        this.b01Vo = b01Vo;
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

    public String getbSfldzwB() {
        return bSfldzwB;
    }

    public void setbSfldzwB(String bSfldzwB) {
        this.bSfldzwB = bSfldzwB;
    }

    public String getJrB0901a1() {
        return jrB0901a1;
    }

    public void setJrB0901a1(String jrB0901a1) {
        this.jrB0901a1 = jrB0901a1;
    }

    public String getJrGlB09001() {
        return jrGlB09001;
    }

    public void setJrGlB09001(String jrGlB09001) {
        this.jrGlB09001 = jrGlB09001;
    }

    public Integer getZs1() {
        return zs1;
    }

    public void setZs1(Integer zs1) {
        this.zs1 = zs1;
    }

    public String getJrB0901a2() {
        return jrB0901a2;
    }

    public void setJrB0901a2(String jrB0901a2) {
        this.jrB0901a2 = jrB0901a2;
    }

    public String getJrGlB09002() {
        return jrGlB09002;
    }

    public void setJrGlB09002(String jrGlB09002) {
        this.jrGlB09002 = jrGlB09002;
    }

    public Integer getZs2() {
        return zs2;
    }

    public void setZs2(Integer zs2) {
        this.zs2 = zs2;
    }

    public int getIsZs2() {
        return isZs2;
    }

    public void setIsZs2(int isZs2) {
        this.isZs2 = isZs2;
    }

    public String getJrB0901a3() {
        return jrB0901a3;
    }

    public void setJrB0901a3(String jrB0901a3) {
        this.jrB0901a3 = jrB0901a3;
    }

    public String getJrGlB09003() {
        return jrGlB09003;
    }

    public void setJrGlB09003(String jrGlB09003) {
        this.jrGlB09003 = jrGlB09003;
    }

    public Integer getZs3() {
        return zs3;
    }

    public void setZs3(Integer zs3) {
        this.zs3 = zs3;
    }

    public int getIsZs3() {
        return isZs3;
    }

    public void setIsZs3(int isZs3) {
        this.isZs3 = isZs3;
    }

    public int getIsGd() {
        return isGd;
    }

    public void setIsGd(int isGd) {
        this.isGd = isGd;
    }
}
