/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a52.vo;

/**
 * @author Marco {854476391@qq.com}
 */
public class A52Vo {
    private String id;
    private String a38Id;//外键，人员档案主健
    private String a5204;//任职机构
    private String a5211;//职务名称
    private String a5227In;//任职时间
    private String a5227Out;//免职时间
    private String a0245;//任职文号
    private String a0267;//免职文号
    private Integer px;//职务变动排序

    public String getA0245() {
        return a0245;
    }

    public void setA0245(String a0245) {
        this.a0245 = a0245;
    }

    public String getA0267() {
        return a0267;
    }

    public void setA0267(String a0267) {
        this.a0267 = a0267;
    }

    public String getA38Id() {
        return a38Id;
    }

    public void setA38Id(String a38Id) {
        this.a38Id = a38Id;
    }

    public String getA5204() {
        return a5204;
    }

    public void setA5204(String a5204) {
        this.a5204 = a5204;
    }

    public String getA5211() {
        return a5211;
    }

    public void setA5211(String a5211) {
        this.a5211 = a5211;
    }

    public String getA5227In() {
        return a5227In;
    }

    public void setA5227In(String a5227In) {
        this.a5227In = a5227In;
    }

    public String getA5227Out() {
        return a5227Out;
    }

    public void setA5227Out(String a5227Out) {
        this.a5227Out = a5227Out;
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
}
