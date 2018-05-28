/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.zrzc.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Marco {854476391@qq.com}
 */
public class E01Z5ResVo implements Serializable{
    private String id;
    private String a38Id;
    private String name;//档案名称
    private Date e01Z501;//接收日期
    private String e01Z507A;//来件单位名称
    private String e01Z507B;//来件单位ID
    private Integer e01Z511;//正本卷数
    private Integer e01Z514;//副本卷数'
    private String e01Z517;//经办人'
    private String e01Z521;//审核人'
    private Date e01Z524;//审核日期'
    private String e01Z527;//案卷质量 0-不合格；1-合格；2-优秀
    private Date e01Z531;//回执日期
    private Date e01Z534;//入库日期
    private String e01Z537;//入库审批人
    private String e01Z541;//档案位置
    private String e01Z544;//备注
    private String filePath;//上传文件路径
    private String fileName; //上传文件名称
    private String sjly;//数据来源

    public String getA38Id() {
        return a38Id;
    }

    public void setA38Id(String a38Id) {
        this.a38Id = a38Id;
    }

    public Date getE01Z501() {
        return e01Z501;
    }

    public void setE01Z501(Date e01Z501) {
        this.e01Z501 = e01Z501;
    }

    public String getE01Z507A() {
        return e01Z507A;
    }

    public void setE01Z507A(String e01Z507A) {
        this.e01Z507A = e01Z507A;
    }

    public String getE01Z507B() {
        return e01Z507B;
    }

    public void setE01Z507B(String e01Z507B) {
        this.e01Z507B = e01Z507B;
    }

    public Integer getE01Z511() {
        return e01Z511;
    }

    public void setE01Z511(Integer e01Z511) {
        this.e01Z511 = e01Z511;
    }

    public Integer getE01Z514() {
        return e01Z514;
    }

    public void setE01Z514(Integer e01Z514) {
        this.e01Z514 = e01Z514;
    }

    public String getE01Z517() {
        return e01Z517;
    }

    public void setE01Z517(String e01Z517) {
        this.e01Z517 = e01Z517;
    }

    public String getE01Z521() {
        return e01Z521;
    }

    public void setE01Z521(String e01Z521) {
        this.e01Z521 = e01Z521;
    }

    public Date getE01Z524() {
        return e01Z524;
    }

    public void setE01Z524(Date e01Z524) {
        this.e01Z524 = e01Z524;
    }

    public String getE01Z527() {
        return e01Z527;
    }

    public void setE01Z527(String e01Z527) {
        this.e01Z527 = e01Z527;
    }

    public Date getE01Z531() {
        return e01Z531;
    }

    public void setE01Z531(Date e01Z531) {
        this.e01Z531 = e01Z531;
    }

    public Date getE01Z534() {
        return e01Z534;
    }

    public void setE01Z534(Date e01Z534) {
        this.e01Z534 = e01Z534;
    }

    public String getE01Z537() {
        return e01Z537;
    }

    public void setE01Z537(String e01Z537) {
        this.e01Z537 = e01Z537;
    }

    public String getE01Z541() {
        return e01Z541;
    }

    public void setE01Z541(String e01Z541) {
        this.e01Z541 = e01Z541;
    }

    public String getE01Z544() {
        return e01Z544;
    }

    public void setE01Z544(String e01Z544) {
        this.e01Z544 = e01Z544;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSjly() {
        return sjly;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }
}
