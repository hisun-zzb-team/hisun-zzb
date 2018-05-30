/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.e01z2.vo;

import java.io.Serializable;

/**
 * @author Marco {854476391@qq.com}
 */
public class E01z2Vo implements Serializable {
    private String id;
    private String a38Id;//外键，人员档案主健
    private String e01Z201;//收件日期
    private String e01Z204A;//来件单位名称
    private String e01Z204B;//来件单位代码
    private String e01Z207;//接收人
    private String e01Z211B;//材料类号
    private String e01Z211A;//材料类别名称
    private String e01Z217;//材料编号
    private String e01Z221A;//材料名称
    private String e01Z221B;//材料名称代码
    private Integer e01Z224;//页数
    private String e01Z227;//材料制成日期
    private String e01Z231;//备注
    private Integer e01Z234;//份数
    private String e01Z237;//材料处理标识
    private String e01Z237Content;//材料处理标识
    private String e01Z241;//零散材料序号
    private String e01Z244;//是否已受理标识
    private String e01Z244Content;//是否已受理标识
    private Integer e01Z214;// 材料序号
    private Integer row;//行数

    public String getE01Z237Content() {
        return e01Z237Content;
    }

    public void setE01Z237Content(String e01Z237Content) {
        this.e01Z237Content = e01Z237Content;
    }

    public String getE01Z244Content() {
        return e01Z244Content;
    }

    public void setE01Z244Content(String e01Z244Content) {
        this.e01Z244Content = e01Z244Content;
    }

    public String getA38Id() {
        return a38Id;
    }

    public void setA38Id(String a38Id) {
        this.a38Id = a38Id;
    }

    public String getE01Z201() {
        return e01Z201;
    }

    public void setE01Z201(String e01Z201) {
        this.e01Z201 = e01Z201;
    }

    public String getE01Z204A() {
        return e01Z204A;
    }

    public void setE01Z204A(String e01Z204A) {
        this.e01Z204A = e01Z204A;
    }

    public String getE01Z204B() {
        return e01Z204B;
    }

    public void setE01Z204B(String e01Z204B) {
        this.e01Z204B = e01Z204B;
    }

    public String getE01Z207() {
        return e01Z207;
    }

    public void setE01Z207(String e01Z207) {
        this.e01Z207 = e01Z207;
    }

    public String getE01Z211B() {
        return e01Z211B;
    }

    public void setE01Z211B(String e01Z211B) {
        this.e01Z211B = e01Z211B;
    }

    public String getE01Z211A() {
        return e01Z211A;
    }

    public void setE01Z211A(String e01Z211A) {
        this.e01Z211A = e01Z211A;
    }

    public Integer getE01Z214() {
        return e01Z214;
    }

    public void setE01Z214(Integer e01Z214) {
        this.e01Z214 = e01Z214;
    }

    public String getE01Z217() {
        return e01Z217;
    }

    public void setE01Z217(String e01Z217) {
        this.e01Z217 = e01Z217;
    }

    public String getE01Z221A() {
        return e01Z221A;
    }

    public void setE01Z221A(String e01Z221A) {
        this.e01Z221A = e01Z221A;
    }

    public String getE01Z221B() {
        return e01Z221B;
    }

    public void setE01Z221B(String e01Z221B) {
        this.e01Z221B = e01Z221B;
    }

    public Integer getE01Z224() {
        return e01Z224;
    }

    public void setE01Z224(Integer e01Z224) {
        this.e01Z224 = e01Z224;
    }

    public String getE01Z227() {
        return e01Z227;
    }

    public void setE01Z227(String e01Z227) {
        this.e01Z227 = e01Z227;
    }

    public String getE01Z231() {
        return e01Z231;
    }

    public void setE01Z231(String e01Z231) {
        this.e01Z231 = e01Z231;
    }

    public Integer getE01Z234() {
        return e01Z234;
    }

    public void setE01Z234(Integer e01Z234) {
        this.e01Z234 = e01Z234;
    }

    public String getE01Z237() {
        return e01Z237;
    }

    public void setE01Z237(String e01Z237) {
        this.e01Z237 = e01Z237;
    }

    public String getE01Z241() {
        return e01Z241;
    }

    public void setE01Z241(String e01Z241) {
        this.e01Z241 = e01Z241;
    }

    public String getE01Z244() {
        return e01Z244;
    }

    public void setE01Z244(String e01Z244) {
        this.e01Z244 = e01Z244;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }
}
