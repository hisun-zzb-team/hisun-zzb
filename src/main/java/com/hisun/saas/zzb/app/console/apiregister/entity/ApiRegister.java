/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.apiregister.entity;

import com.hisun.base.entity.TombstoneEntity;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/10/12.
 */
@Entity
@Table(name = "app_api_register")
public class ApiRegister extends TombstoneEntity implements Serializable{


    private String id;

    private String ip;

    private String port;

    private String context;

    private String uri;

    private String apiCode;

    private String requestMethod;
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false, unique = true, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name = "ip",nullable = false,length = 32)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    @Column(name = "port",nullable = false,length = 10)
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
    @Column(name = "context",nullable = true,length = 32)
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
    @Column(name = "uri",nullable = false,length = 128)
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    @Column(name = "api_code",nullable = false,length = 32)
    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }
    @Column(name = "request_method",nullable = false,length = 32)
    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }


    public String toSqliteInsertSql(){
        StringBuffer sb = new StringBuffer("");
        sb.append(" INSERT INTO ");
        sb.append(" app_api_register ");
        sb.append("(");
        sb.append("id");
        sb.append(",ip");
        sb.append(",port");
        sb.append(",context");
        sb.append(",uri");
        sb.append(",api_code");
        sb.append(",request_method");
        sb.append(")");
        sb.append(" VALUES");
        sb.append("(");
        sb.append("'"+ StringUtils.trimNull2Empty(id)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(ip)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(port)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(context)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(uri)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(apiCode)+"'");
        sb.append(",'"+ StringUtils.trimNull2Empty(requestMethod)+"'");
        sb.append(")");
        return sb.toString();
    }
}
