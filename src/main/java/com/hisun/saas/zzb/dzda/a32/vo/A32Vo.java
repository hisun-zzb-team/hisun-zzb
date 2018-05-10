/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a32.vo;

import java.io.Serializable;

/**
 * @author Marco {854476391@qq.com}
 */
public class A32Vo implements Serializable{
    private String id;//工资变动id
    private String a38Id;//外键，人员档案主健
    private String gzbm;//工作部门
    private String zwmc;//职务名称
    private String a3224;//执行的职务工资标准
    private String a3234;//职务工资额
    private String a3207;//批准日期
    private String a3204;//批准机关
    private String a3211;//批准文电号
    private Integer px;//工资变动顺序号

    public String getA3204() {
        return a3204;
    }

    public void setA3204(String a3204) {
        this.a3204 = a3204;
    }

    public String getA3207() {
        return a3207;
    }

    public void setA3207(String a3207) {
        this.a3207 = a3207;
    }

    public String getA3211() {
        return a3211;
    }

    public void setA3211(String a3211) {
        this.a3211 = a3211;
    }

    public String getA3224() {
        return a3224;
    }

    public void setA3224(String a3224) {
        this.a3224 = a3224;
    }

    public String getA3234() {
        return a3234;
    }

    public void setA3234(String a3234) {
        this.a3234 = a3234;
    }

    public String getA38Id() {
        return a38Id;
    }

    public void setA38Id(String a38Id) {
        this.a38Id = a38Id;
    }

    public String getGzbm() {
        return gzbm;
    }

    public void setGzbm(String gzbm) {
        this.gzbm = gzbm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPx() {
        return px;
    }

    public void setPx(Integer px) {
        this.px = px;
    }

    public String getZwmc() {
        return zwmc;
    }

    public void setZwmc(String zwmc) {
        this.zwmc = zwmc;
    }
}
