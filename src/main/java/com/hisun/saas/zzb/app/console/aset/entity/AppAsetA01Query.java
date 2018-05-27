/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.aset.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.saas.zzb.app.console.zscx.entity.AppZscxZsA01;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouying on 2017/11/28.
 */
@Entity
@Table(name = "app_aset_a01_query")
public class AppAsetA01Query extends TenantEntity implements Serializable {
    public final static int DISPLAY =1;
    public final static int HIDDEN =0;

    private String id;

    private String queryName;

    private String queryCondition;

    private String queryJson;

    private int querySort;

    private int isDisplay=HIDDEN;
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false, unique = true, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name = "query_name")
    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "query_condition",columnDefinition = "TEXT",nullable = true)
    public String getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(String queryCondition) {
        this.queryCondition = queryCondition;
    }
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "query_json",columnDefinition = "TEXT",nullable = true)
    public String getQueryJson() {
        return queryJson;
    }

    public void setQueryJson(String queryJson) {
        this.queryJson = queryJson;
    }
    @Column(name = "query_sort")
    public int getQuerySort() {
        return querySort;
    }

    public void setQuerySort(int querySort) {
        this.querySort = querySort;
    }
    @Column(name = "is_display")
    public int getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(int isDisplay) {
        this.isDisplay = isDisplay;
    }
}
