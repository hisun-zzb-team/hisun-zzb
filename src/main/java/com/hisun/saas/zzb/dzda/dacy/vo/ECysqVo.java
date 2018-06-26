/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.vo;

import com.hisun.saas.zzb.dzda.dacy.entity.EApplyE01Z8;

/**
 * @author Marco {854476391@qq.com}
 */
public class ECysqVo {
    private String id;
    private String sqzt;//授权状态2：拒绝1：同意
    private String sqcysc;//授权查阅时长（分钟）
    private String sqr;//授权人
    private String sfyxxz;//是否允许下载0：否1：是
    private String sfyxdy;//是否允许打印0：否1：是
    private String sqcyml;//授权查阅目录
    private String sqclfw;//授权材料范围类型。0：授权所有档案材料；1：授权指定的档案材料
    private String sqbz;//授权备注
    private String e01z8Id;
    private String sqcymlIds;//部分授权目录 e01z1Id字符串 “，”隔开
    private String a38Id;//
    private EApplyE01Z8Vo eApplyE01Z8Vo;
    private String cysqr;//查阅申请人
    private String cydadxmc;//查阅档案对象姓名
    private String sqztContent;//授权状态

    public String getCysqr() {
        return cysqr;
    }

    public void setCysqr(String cysqr) {
        this.cysqr = cysqr;
    }

    public String getCydadxmc() {
        return cydadxmc;
    }

    public void setCydadxmc(String cydadxmc) {
        this.cydadxmc = cydadxmc;
    }

    public String getSqztContent() {
        return sqztContent;
    }

    public void setSqztContent(String sqztContent) {
        this.sqztContent = sqztContent;
    }

    public EApplyE01Z8Vo geteApplyE01Z8Vo() {
        return eApplyE01Z8Vo;
    }

    public void seteApplyE01Z8Vo(EApplyE01Z8Vo eApplyE01Z8Vo) {
        this.eApplyE01Z8Vo = eApplyE01Z8Vo;
    }

    public String getA38Id() {
        return a38Id;
    }

    public void setA38Id(String a38Id) {
        this.a38Id = a38Id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSqzt() {
        return sqzt;
    }

    public void setSqzt(String sqzt) {
        this.sqzt = sqzt;
    }

    public String getSqcysc() {
        return sqcysc;
    }

    public void setSqcysc(String sqcysc) {
        this.sqcysc = sqcysc;
    }

    public String getSqr() {
        return sqr;
    }

    public void setSqr(String sqr) {
        this.sqr = sqr;
    }

    public String getSfyxxz() {
        return sfyxxz;
    }

    public void setSfyxxz(String sfyxxz) {
        this.sfyxxz = sfyxxz;
    }

    public String getSfyxdy() {
        return sfyxdy;
    }

    public void setSfyxdy(String sfyxdy) {
        this.sfyxdy = sfyxdy;
    }

    public String getSqcyml() {
        return sqcyml;
    }

    public void setSqcyml(String sqcyml) {
        this.sqcyml = sqcyml;
    }

    public String getSqclfw() {
        return sqclfw;
    }

    public void setSqclfw(String sqclfw) {
        this.sqclfw = sqclfw;
    }

    public String getSqbz() {
        return sqbz;
    }

    public void setSqbz(String sqbz) {
        this.sqbz = sqbz;
    }

    public String getE01z8Id() {
        return e01z8Id;
    }

    public void setE01z8Id(String e01z8Id) {
        this.e01z8Id = e01z8Id;
    }

    public String getSqcymlIds() {
        return sqcymlIds;
    }

    public void setSqcymlIds(String sqcymlIds) {
        this.sqcymlIds = sqcymlIds;
    }
}
