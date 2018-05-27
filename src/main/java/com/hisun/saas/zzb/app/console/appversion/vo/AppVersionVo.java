package com.hisun.saas.zzb.app.console.appversion.vo;

import com.hisun.saas.zzb.app.console.appversion.entity.AppVersion;

/**
 * Created by zhouying on 2017/9/23.
 */
public class AppVersionVo {


    private String id;
    private int appType;//App类型,1-安卓,2-iOS
    private String appTypeValue;
    private String appVersionName;//版本名称
    private String appVersionCode;//版本号build
    private String appName;//应用名称
    private String appCode;//应用代码
    private String appStorePath;//安装包存储路径
    private String appMd5;
    private String appSize;//大小(字节)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public String getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(String appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppStorePath() {
        return appStorePath;
    }

    public void setAppStorePath(String appStorePath) {
        this.appStorePath = appStorePath;
    }

    public String getAppMd5() {
        return appMd5;
    }

    public void setAppMd5(String appMd5) {
        this.appMd5 = appMd5;
    }

    public String getAppSize() {
        return appSize;
    }

    public void setAppSize(String appSize) {
        this.appSize = appSize;
    }

    public String getAppTypeValue() {
        if(this.appType == AppVersion.ANDROID){
            return "安卓";
        }else if(this.appType == AppVersion.IOS){
            return "IOS";
        }
        return "";
    }

    public void setAppTypeValue(String appTypeValue) {
        this.appTypeValue = appTypeValue;
    }
}
