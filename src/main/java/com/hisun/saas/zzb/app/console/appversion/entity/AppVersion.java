/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.appversion.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/11/22.
 */
@Entity
@Table(name = "app_version")
public class AppVersion extends TenantEntity implements Serializable {

    public static int ANDROID=1;
    public static int IOS=2;

    private String id;

    private int appType=ANDROID;//App类型,1-安卓,2-iOS

    private String appVersionName;//版本名称

    private String appVersionCode;//版本号build

    private String appName;//应用名称

    private String appCode;//应用代码

    private String appStorePath;//安装包存储路径

    private String appMd5;


    private String appSize;//大小(字节)

    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="ID",nullable=false,unique=true,length=32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "app_type")
    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    @Column(name = "app_version_name")
    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    @Column(name = "app_version_code")
    public String getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(String appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    @Column(name = "app_name")
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Column(name = "app_code")
    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    @Column(name = "app_store_path")
    public String getAppStorePath() {
        return appStorePath;
    }

    public void setAppStorePath(String appStorePath) {
        this.appStorePath = appStorePath;
    }

    @Column(name = "app_md5")
    public String getAppMd5() {
        return appMd5;
    }

    public void setAppMd5(String appMd5) {
        this.appMd5 = appMd5;
    }

    @Column(name = "app_size")
    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppVersion that = (AppVersion) o;

        if (appType != that.appType) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (appVersionName != null ? !appVersionName.equals(that.appVersionName) : that.appVersionName != null)
            return false;
        if (appVersionCode != null ? !appVersionCode.equals(that.appVersionCode) : that.appVersionCode != null)
            return false;
        if (appName != null ? !appName.equals(that.appName) : that.appName != null) return false;
        if (appCode != null ? !appCode.equals(that.appCode) : that.appCode != null) return false;
        if (appStorePath != null ? !appStorePath.equals(that.appStorePath) : that.appStorePath != null) return false;
        if (appMd5 != null ? !appMd5.equals(that.appMd5) : that.appMd5 != null) return false;
        if (appSize != null ? !appSize.equals(that.appSize) : that.appSize != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + appType;
        result = 31 * result + (appVersionName != null ? appVersionName.hashCode() : 0);
        result = 31 * result + (appVersionCode != null ? appVersionCode.hashCode() : 0);
        result = 31 * result + (appName != null ? appName.hashCode() : 0);
        result = 31 * result + (appCode != null ? appCode.hashCode() : 0);
        result = 31 * result + (appStorePath != null ? appStorePath.hashCode() : 0);
        result = 31 * result + (appMd5 != null ? appMd5.hashCode() : 0);
        result = 31 * result + (appSize != null ? appSize.hashCode() : 0);
        return result;
    }
}
