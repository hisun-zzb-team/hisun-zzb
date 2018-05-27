package com.hisun.saas.zzb.app.console.appversion.vo;

import java.util.Date;

/**
 * Created by zhouying on 2017/11/23.
 */
public class AppVersionUpdateVo {

    private String name;
    private String md5;
    private String versionName;
    private String versionCode;
    private String url;
    private String description;
    private String contentLength;
    private Date updateDate;

    public AppVersionUpdateVo() {
        this.name = "";
        this.md5 = "";
        this.versionName = "";
        this.versionCode = "";
        this.url = "";
        this.description = "";
        this.contentLength = "";
        this.updateDate = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContentLength() {
        return contentLength;
    }

    public void setContentLength(String contentLength) {
        this.contentLength = contentLength;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
