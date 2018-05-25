/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbmc.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name = "app_mc_a01_shgx")
public class GbMcA01shgx extends TenantEntity implements Serializable{



    private String id;

    private GbMcA01 gbMcA01;


    private String cw;

    private String xm;

    private String nl;

    private String zzmm;

    private String gzdwjzw;


    private int px;


    public GbMcA01shgx(){}

    public GbMcA01shgx(Map<String,String> ywJsonMap, int px){
        this.cw = ywJsonMap.get("call_"+px);
        this.xm =  ywJsonMap.get("name_"+px);
        this.nl = ywJsonMap.get("age_"+px);
        this.zzmm = ywJsonMap.get("polity_"+px);
        this.gzdwjzw = ywJsonMap.get("job_"+px);
        this.px = px;
    }
    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="id",nullable=false,unique=true,length=32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(optional=true,fetch = FetchType.LAZY)
    @JoinColumn(name="app_mc_a01_id")
    public GbMcA01 getGbMcA01() {
        return gbMcA01;
    }

    public void setGbMcA01(GbMcA01 gbMcA01) {
        this.gbMcA01 = gbMcA01;
    }
    @Column(name = "cw",length = 24)
    public String getCw() {
        return cw;
    }

    public void setCw(String cw) {
        this.cw = cw;
    }
    @Column(name = "xm",length = 24)
    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }
    @Column(name = "nl",length = 10)
    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }
    @Column(name = "zzmm",length = 24)
    public String getZzmm() {
        return zzmm;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm;
    }
    @Column(name = "gzdwjzw",length = 128)
    public String getGzdwjzw() {
        return gzdwjzw;
    }

    public void setGzdwjzw(String gzdwjzw) {
        this.gzdwjzw = gzdwjzw;
    }
    @Column(name = "shgx_px")
    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }


    public Map<String,String> toSqlFieldMap(){
        Map<String,String> fieldMap = new HashMap<String,String>();
        fieldMap.put("cw",cw);
        fieldMap.put("xm",xm);
        fieldMap.put("nl",nl);
        fieldMap.put("zzmm",zzmm);
        fieldMap.put("gzdwjzw",gzdwjzw);
        fieldMap.put("shgx_px",String.valueOf(px));
        return fieldMap;
    }
}
