/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.b.vo;

/**
 * @author Marco {854476391@qq.com}
 */
public class B01QueryModel {
    private String b0101Query;//机构名称
    private String parentIdQuery;//上级机构ID
    private String parentNameQuery;//上级机构名称
    private String b0127Query;//机构级别代码
    private String b0127AQuery;//机构级别内容
    private String bGllbBQuery;//机构管理类别代码
    private String bGllbAQuery;//机构管理类别内容

    public String getB0101Query() {
        return b0101Query;
    }

    public void setB0101Query(String b0101Query) {
        this.b0101Query = b0101Query;
    }

    public String getParentIdQuery() {
        return parentIdQuery;
    }

    public void setParentIdQuery(String parentIdQuery) {
        this.parentIdQuery = parentIdQuery;
    }

    public String getParentNameQuery() {
        return parentNameQuery;
    }

    public void setParentNameQuery(String parentNameQuery) {
        this.parentNameQuery = parentNameQuery;
    }

    public String getB0127Query() {
        return b0127Query;
    }

    public void setB0127Query(String b0127Query) {
        this.b0127Query = b0127Query;
    }

    public String getB0127AQuery() {
        return b0127AQuery;
    }

    public void setB0127AQuery(String b0127AQuery) {
        this.b0127AQuery = b0127AQuery;
    }

    public String getbGllbBQuery() {
        return bGllbBQuery;
    }

    public void setbGllbBQuery(String bGllbBQuery) {
        this.bGllbBQuery = bGllbBQuery;
    }

    public String getbGllbAQuery() {
        return bGllbAQuery;
    }

    public void setbGllbAQuery(String bGllbAQuery) {
        this.bGllbAQuery = bGllbAQuery;
    }
}
