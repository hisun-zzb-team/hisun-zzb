package com.hisun.saas.zzb.app.console.bset.vo;

import com.hisun.saas.zzb.app.console.appclient.entity.AppClient;

/**
 * Created by zhouying on 2017/9/23.
 */
public class AppBsetB01Vo {


    private String id;
    private String b0101;
    private int px;
    private int dataType;//类型 0--机构 1--分类
    private String parentId;

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

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
