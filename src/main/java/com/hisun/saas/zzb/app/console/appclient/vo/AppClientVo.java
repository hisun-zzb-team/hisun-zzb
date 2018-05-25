package com.hisun.saas.zzb.app.console.appclient.vo;

import com.hisun.saas.zzb.app.console.appclient.entity.AppClient;

/**
 * Created by zhouying on 2017/9/23.
 */
public class AppClientVo {


    private String id;
    private String identification;//终端标识
    private int status;//1-正常,2-停用
    private String statusValue;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusValue() {
        if(this.status == AppClient.ON){
            return "正常";
        }else if(this.status == AppClient.OFF)
        {
            return "停用";
        }
        return "";
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }
}
