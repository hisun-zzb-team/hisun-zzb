/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.a.vo;

/**
 * @author Marco {854476391@qq.com}
 */
public class QueryModel {
    private String a0101Query;//姓名
    private String parentIdQuery;//所属机构
    private String parentNameQuery;
    private String aGbztbQuery;//干部状态代码
    private String aGbztaQuery;//干部状态
    private String aSfyshQuery;//审核状态
    private String a0157BQuery;//管理单位代码
    private String a0157AQuery;//管理单位名称

    public String getA0101Query() {
        return a0101Query;
    }

    public void setA0101Query(String a0101Query) {
        this.a0101Query = a0101Query;
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

    public String getaGbztbQuery() {
        return aGbztbQuery;
    }

    public void setaGbztbQuery(String aGbztbQuery) {
        this.aGbztbQuery = aGbztbQuery;
    }

    public String getaGbztaQuery() {
        return aGbztaQuery;
    }

    public void setaGbztaQuery(String aGbztaQuery) {
        this.aGbztaQuery = aGbztaQuery;
    }

    public String getaSfyshQuery() {
        return aSfyshQuery;
    }

    public void setaSfyshQuery(String aSfyshQuery) {
        this.aSfyshQuery = aSfyshQuery;
    }

    public String getA0157BQuery() {
        return a0157BQuery;
    }

    public void setA0157BQuery(String a0157BQuery) {
        this.a0157BQuery = a0157BQuery;
    }

    public String getA0157AQuery() {
        return a0157AQuery;
    }

    public void setA0157AQuery(String a0157AQuery) {
        this.a0157AQuery = a0157AQuery;
    }
}
