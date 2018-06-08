package com.hisun.saas.zzb.b.vo;

import java.io.Serializable;

/**
 * Created by 60514 on 2018/6/7.
 */
public class BB09BhJrInfoVo implements Serializable {
    private String id;
    private B09Vo b09;//当前职务
    private B01Vo b01;//当前机构
    private int sfbdwzw;//是否本单位职务 1：是 0：否
    private String gl_b0900;//关联的职务id
    private String b0901a;//关联的职务名称
    private int lx;//类型 1：兼任 2：包含
    private int zs;//职数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSfbdwzw() {
        return sfbdwzw;
    }

    public void setSfbdwzw(int sfbdwzw) {
        this.sfbdwzw = sfbdwzw;
    }

    public String getGl_b0900() {
        return gl_b0900;
    }

    public void setGl_b0900(String gl_b0900) {
        this.gl_b0900 = gl_b0900;
    }

    public String getB0901a() {
        return b0901a;
    }

    public void setB0901a(String b0901a) {
        this.b0901a = b0901a;
    }

    public int getLx() {
        return lx;
    }

    public void setLx(int lx) {
        this.lx = lx;
    }

    public int getZs() {
        return zs;
    }

    public void setZs(int zs) {
        this.zs = zs;
    }
}
