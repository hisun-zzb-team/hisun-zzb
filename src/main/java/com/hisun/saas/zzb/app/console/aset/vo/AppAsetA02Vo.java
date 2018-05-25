package com.hisun.saas.zzb.app.console.aset.vo;

import javax.persistence.Column;

/**
 * Created by zhouying on 2017/9/23.
 */
public class AppAsetA02Vo {
    private String id;
    private String b0101;
    private String zwmc;
    private String rzsj;//任职时间
    private int jtlPx;//集体类排列顺序
    private int px;//个人职务排序

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getB0101() {
        return b0101;
    }

    public void setB0101(String b0101) {
        this.b0101 = b0101;
    }

    public String getZwmc() {
        return zwmc;
    }

    public void setZwmc(String zwmc) {
        this.zwmc = zwmc;
    }

    public String getRzsj() {
        return rzsj;
    }

    public void setRzsj(String rzsj) {
        this.rzsj = rzsj;
    }

    public int getJtlPx() {
        return jtlPx;
    }

    public void setJtlPx(int jtlPx) {
        this.jtlPx = jtlPx;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }
}
