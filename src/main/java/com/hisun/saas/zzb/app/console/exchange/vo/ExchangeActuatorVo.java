package com.hisun.saas.zzb.app.console.exchange.vo;

import com.hisun.base.vo.TombstoneVo;
import com.hisun.saas.zzb.app.console.appclient.entity.AppClient;
import com.hisun.saas.zzb.app.console.exchange.entity.ExchangeActuator;

import javax.persistence.Column;

/**
 * Created by zhouying on 2017/9/23.
 */
public class ExchangeActuatorVo extends TombstoneVo {


    private String id;
    private int sourceType;
    private String sourceTypeValue;
    private String ip;
    private String port;
    private String databaseName;
    private String userName;
    private String password;
    private int sort;
    private String actuatorName;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getSourceTypeValue() {
        if(this.sourceType== ExchangeActuator.source_gwyglxt){
            return "公务员管理系统(浙大网新)";
        }else if(this.sourceType== ExchangeActuator.source_zzzhywpt){
            return "组织综合业务平台(广州三零)";
        }else if(this.sourceType== ExchangeActuator.source_gbglxt){
            return "干部管理系统(长沙远望)";
        }
        return "";
    }

    public void setSourceTypeValue(String sourceTypeValue) {
        this.sourceTypeValue = sourceTypeValue;
    }

    public String getActuatorName() {
        return actuatorName;
    }

    public void setActuatorName(String actuatorName) {
        this.actuatorName = actuatorName;
    }
}
