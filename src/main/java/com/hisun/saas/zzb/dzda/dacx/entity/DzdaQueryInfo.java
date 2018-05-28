/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacx.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Marco {854476391@qq.com}
 */
@Entity
@Table(name = "dzda_query_info")
public class DzdaQueryInfo extends TenantEntity implements Serializable {
    private String id;
    private String queryName;//查询名称
    private String queryModel;//查询json字符串
    private String cxsy;//查询索引
    private String syxz;//查询限制
    private String queryType;//查询类型
    private String description;//查询描述
    private int px;//序号
    private String queryStatus;//状态0正常 1临时

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false, unique = true, length = 40)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "query_name", nullable = true, length = 128)
    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }
    @Basic
    @Column(name = "query_status", nullable = false, length = 4)
    public String getQueryStatus() {
        return queryStatus;
    }

    public void setQueryStatus(String queryStatus) {
        this.queryStatus = queryStatus;
    }
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "query_model",columnDefinition = "TEXT",nullable = true)
    public String getQueryModel() {
        return queryModel;
    }

    public void setQueryModel(String queryModel) {
        this.queryModel = queryModel;
    }

    @Basic
    @Column(name = "cxsy", nullable = true, length = 200)
    public String getCxsy() {
        return cxsy;
    }

    public void setCxsy(String cxsy) {
        this.cxsy = cxsy;
    }

    @Basic
    @Column(name = "syxz", nullable = true, length = 200)
    public String getSyxz() {
        return syxz;
    }

    public void setSyxz(String syxz) {
        this.syxz = syxz;
    }

    @Basic
    @Column(name = "query_type", nullable = true, length = 40)
    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "px", nullable = false)
    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DzdaQueryInfo that = (DzdaQueryInfo) o;

        if (px != that.px) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (queryName != null ? !queryName.equals(that.queryName) : that.queryName != null) return false;
        if (queryModel != null ? !queryModel.equals(that.queryModel) : that.queryModel != null) return false;
        if (cxsy != null ? !cxsy.equals(that.cxsy) : that.cxsy != null) return false;
        if (syxz != null ? !syxz.equals(that.syxz) : that.syxz != null) return false;
        if (queryType != null ? !queryType.equals(that.queryType) : that.queryType != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (queryName != null ? queryName.hashCode() : 0);
        result = 31 * result + (queryModel != null ? queryModel.hashCode() : 0);
        result = 31 * result + (cxsy != null ? cxsy.hashCode() : 0);
        result = 31 * result + (syxz != null ? syxz.hashCode() : 0);
        result = 31 * result + (queryType != null ? queryType.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + px;
        return result;
    }
}
