/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbmc.vo;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMc;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouying on 2017/9/8.
 */
public class GbMcVo{

    private String id;
    private String mc;
    private int px;
    private int a01Count;
    private List<GbMcB01Vo> gbMcB01Vos;
    private int isMl;
    private String isMlValue;
    private String mb;//选择的模板
    private String mcb01id;//如果为无目录 则用来存储隐藏目录ID
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public List<GbMcB01Vo> getGbMcB01Vos() {
        return gbMcB01Vos;
    }

    public void setGbMcB01Vos(List<GbMcB01Vo> gbMcB01s) {
        this.gbMcB01Vos = gbMcB01Vos;
    }

    public int getA01Count() {
        return a01Count;
    }

    public void setA01Count(int a01Count) {
        this.a01Count = a01Count;
    }

    public String getMb() {
        return mb;
    }

    public void setMb(String mb) {
        this.mb = mb;
    }

    public int getIsMl() {
        return isMl;
    }

    public void setIsMl(int isMl) {
        this.isMl = isMl;
    }

    public String getMcb01id() {
        return mcb01id;
    }

    public void setMcb01id(String mcb01id) {
        this.mcb01id = mcb01id;
    }

    public String getIsMlValue() {
        if(this.isMl == GbMc.WML) {
            return "无目录";
        }else{
            return "有目录";
        }
    }

    public void setIsMlValue(String isMlValue) {
        this.isMlValue = isMlValue;
    }
}
