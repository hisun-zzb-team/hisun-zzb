package com.hisun.saas.zzb.a.vo;

import com.hisun.saas.zzb.a.entity.A01;
import com.hisun.util.StringUtils;

public class A14Z2Vo {
    /** 奖励情况主键 */
    private String a14Z200;

    /** 人员基本信息主键 */
    private A01Vo a01Vo;

    /** 奖励类别:ZB19-2006/JCLB */
    private String a14Z201;

    /** 奖励名称字典内容/奖励名称显示内容 */
    private String a14Z204A;

    /** 奖励名称字典代码:ZB65-2006/JLDM及ZB66-2006/CHJZ */
    private String a14Z204B;

    /** 授予荣誉称号级别代码:ZB66-2006/CHJZ第1部分 */
    private String a14Z207;

    /** 授予荣誉称号级别字典内容/授予荣誉称号级别显示内容 */
    private String aA14Z207A;

    /** 奖励日期 */
    private String a14Z211;

    /** 奖励批准机关名称 */
    private String a14Z214A;

    /** 奖励批准机关代码：ZB02-2006/JGMC */
    private String a14Z214B;

    /** 批准奖励机关级别:ZB03-1994/DWJB */
    private String a14Z217;

    /** 奖励原因 */
    private String a14Z221;

    /** 奖励说明 */
    private String a14Z224;

    /** 奖励撤销日期 */
    private String a14Z227;

    /** 奖励撤消原因 */
    private String a14Z231;

    /** 奖励荣誉级别字典代码  */
    private String aJljbb;

    /** 奖励荣誉级别字典内容  */
    private String aJljba;

    private String jlsj;
    private String jlrymc;

    public String getA14Z200() {
        return a14Z200;
    }

    public void setA14Z200(String a14Z200) {
        this.a14Z200 = a14Z200;
    }

    public A01Vo getA01Vo() {
        return a01Vo;
    }

    public void setA01Vo(A01Vo a01Vo) {
        this.a01Vo = a01Vo;
    }

    public String getA14Z201() {
        return a14Z201;
    }

    public void setA14Z201(String a14Z201) {
        this.a14Z201 = a14Z201;
    }

    public String getA14Z204A() {
        return a14Z204A;
    }

    public void setA14Z204A(String a14Z204A) {
        this.a14Z204A = a14Z204A;
    }

    public String getA14Z204B() {
        return a14Z204B;
    }

    public void setA14Z204B(String a14Z204B) {
        this.a14Z204B = a14Z204B;
    }

    public String getA14Z207() {
        return a14Z207;
    }

    public void setA14Z207(String a14Z207) {
        this.a14Z207 = a14Z207;
    }

    public String getaA14Z207A() {
        return aA14Z207A;
    }

    public void setaA14Z207A(String aA14Z207A) {
        this.aA14Z207A = aA14Z207A;
    }

    public String getA14Z211() {
        return a14Z211;
    }

    public void setA14Z211(String a14Z211) {
        this.a14Z211 = a14Z211;
    }

    public String getA14Z214A() {
        return a14Z214A;
    }

    public void setA14Z214A(String a14Z214A) {
        this.a14Z214A = a14Z214A;
    }

    public String getA14Z214B() {
        return a14Z214B;
    }

    public void setA14Z214B(String a14Z214B) {
        this.a14Z214B = a14Z214B;
    }

    public String getA14Z217() {
        return a14Z217;
    }

    public void setA14Z217(String a14Z217) {
        this.a14Z217 = a14Z217;
    }

    public String getA14Z221() {
        return a14Z221;
    }

    public void setA14Z221(String a14Z221) {
        this.a14Z221 = a14Z221;
    }

    public String getA14Z224() {
        return a14Z224;
    }

    public void setA14Z224(String a14Z224) {
        this.a14Z224 = a14Z224;
    }

    public String getA14Z227() {
        return a14Z227;
    }

    public void setA14Z227(String a14Z227) {
        this.a14Z227 = a14Z227;
    }

    public String getA14Z231() {
        return a14Z231;
    }

    public void setA14Z231(String a14Z231) {
        this.a14Z231 = a14Z231;
    }

    public String getaJljbb() {
        return aJljbb;
    }

    public void setaJljbb(String aJljbb) {
        this.aJljbb = aJljbb;
    }

    public String getaJljba() {
        return aJljba;
    }

    public void setaJljba(String aJljba) {
        this.aJljba = aJljba;
    }

    public String getJlsj() {
        int flag = 0;
        if(StringUtils.isEmpty(a14Z211)){
            a14Z211="";
            flag++;
        }
        if(StringUtils.isEmpty(a14Z227)){
            a14Z227="";
            flag++;
        }
        return a14Z211+(flag==0?"-":"")+a14Z227;
    }

    public String getJlrymc() {
        int flag = 0;
        if(StringUtils.isEmpty(a14Z204A)){
            a14Z204A="";
            flag++;
        }
        if(StringUtils.isEmpty(aA14Z207A)){
            aA14Z207A="";
            flag++;
        }
        return a14Z204A+(flag==0?"-":"")+aA14Z207A;
    }
}
