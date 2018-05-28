/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacx.vo;

import java.io.Serializable;

/**
 * @author Marco {854476391@qq.com}
 */
public class DzdaQueryInfoVo implements Serializable{
    private String id;
    private String queryName;//查询名称
    private String queryModel;//查询json字符串
    private String cxsy;//查询索引
    private String syxz;//查询限制
    private String queryType;//查询类型
    private String description;//查询描述
    private int px;//序号

    public String getCxsy() {
        return cxsy;
    }

    public void setCxsy(String cxsy) {
        this.cxsy = cxsy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public String getQueryModel() {
        return queryModel;
    }

    public void setQueryModel(String queryModel) {
        this.queryModel = queryModel;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getSyxz() {
        return syxz;
    }

    public void setSyxz(String syxz) {
        this.syxz = syxz;
    }
}
