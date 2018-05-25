package com.hisun.saas.zzb.app.console.zscx.vo;

import java.util.List;

/**
 * Created by zhouying on 2017/9/23.
 */
public class AppZscxZsVo {

    private String id;
    private String zwmc;//职务名称
    private int xp;//现配
    private int sp;//实配
    private int cqb;//超缺编
    private String b0101;

    private String appZscxB01Id;
    private List<AppZscxZsA01Vo> appZscxZsA01Vos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZwmc() {
        return zwmc;
    }

    public void setZwmc(String zwmc) {
        this.zwmc = zwmc;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getSp() {
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public int getCqb() {
        return cqb;
    }

    public void setCqb(int cqb) {
        this.cqb = cqb;
    }

    public String getAppZscxB01Id() {
        return appZscxB01Id;
    }

    public void setAppZscxB01Id(String appZscxB01Id) {
        this.appZscxB01Id = appZscxB01Id;
    }

    public List<AppZscxZsA01Vo> getAppZscxZsA01Vos() {
        return appZscxZsA01Vos;
    }

    public void setAppZscxZsA01Vos(List<AppZscxZsA01Vo> appZscxZsA01Vos) {
        this.appZscxZsA01Vos = appZscxZsA01Vos;
    }

    public String getB0101() {
        return b0101;
    }

    public void setB0101(String b0101) {
        this.b0101 = b0101;
    }
}
