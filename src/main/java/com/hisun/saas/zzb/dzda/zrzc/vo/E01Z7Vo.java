/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.zrzc.vo;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author liuzj {279421824@qq.com}
 */
public class E01Z7Vo {
    private String id;
    private String a38Id;
    private String name;//'档案名称'
    private String nameContent;//档案id字符串
    private String e01Z701;//'转递日期'
    private String e01Z704A;//'转往单位名称'
    private String e01Z704B;//'转往单位Id'
    private Integer e01Z711;//'转出正本数'
    private Integer e01Z714;//'转出副本数'
    private String e01Z717;//'经办人'
    private String e01Z721;//'转递原因'
    private String e01Z724;//'回执人'
    private String e01Z727;//'回执日期'
    private String e01Z731;//'备注'
    private String filePath;//上传文件路径
    private String fileName; //上传文件名称

    public String getNameContent() {
        return nameContent;
    }

    public void setNameContent(String nameContent) {
        this.nameContent = nameContent;
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

    public String getA38Id() {
        return a38Id;
    }

    public void setA38Id(String a38Id) {
        this.a38Id = a38Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getE01Z701() {
        return e01Z701;
    }

    public void setE01Z701(String e01Z701) {
        this.e01Z701 = e01Z701;
    }

    public String getE01Z704A() {
        return e01Z704A;
    }

    public void setE01Z704A(String e01Z704A) {
        this.e01Z704A = e01Z704A;
    }

    public String getE01Z704B() {
        return e01Z704B;
    }

    public void setE01Z704B(String e01Z704B) {
        this.e01Z704B = e01Z704B;
    }

    public Integer getE01Z711() {
        return e01Z711;
    }

    public void setE01Z711(Integer e01Z711) {
        this.e01Z711 = e01Z711;
    }

    public Integer getE01Z714() {
        return e01Z714;
    }

    public void setE01Z714(Integer e01Z714) {
        this.e01Z714 = e01Z714;
    }

    public String getE01Z717() {
        return e01Z717;
    }

    public void setE01Z717(String e01Z717) {
        this.e01Z717 = e01Z717;
    }

    public String getE01Z721() {
        return e01Z721;
    }

    public void setE01Z721(String e01Z721) {
        this.e01Z721 = e01Z721;
    }

    public String getE01Z724() {
        return e01Z724;
    }

    public void setE01Z724(String e01Z724) {
        this.e01Z724 = e01Z724;
    }

    public String getE01Z727() {
        return e01Z727;
    }

    public void setE01Z727(String e01Z727) {
        this.e01Z727 = e01Z727;
    }

    public String getE01Z731() {
        return e01Z731;
    }

    public void setE01Z731(String e01Z731) {
        this.e01Z731 = e01Z731;
    }
}
