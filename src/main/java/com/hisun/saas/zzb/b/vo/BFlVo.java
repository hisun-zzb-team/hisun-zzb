package com.hisun.saas.zzb.b.vo;

/**
 * Created by 60514 on 2018/6/4.
 */
public class BFlVo {
    private String id;//主键
    private String fl;//分类名称
    private Integer px;//排序
    private String pId;//主键

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public Integer getPx() {
        return px;
    }

    public void setPx(Integer px) {
        this.px = px;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }
}
