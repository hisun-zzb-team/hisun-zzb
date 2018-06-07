package com.hisun.saas.zzb.b.vo;

import com.hisun.saas.zzb.b.entity.B01;
import com.hisun.saas.zzb.b.entity.BFl;

/**
 * Created by 60514 on 2018/6/5.
 */
public class BFl2B01Vo {
    private Integer px;//排序
    private BFl bfl;//分类id
    private B01 b01;//机构id
    private String id;//主键

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPx() {
        return px;
    }

    public void setPx(Integer px) {
        this.px = px;
    }

    public BFl getBfl() {
        return bfl;
    }

    public void setBfl(BFl bfl) {
        this.bfl = bfl;
    }

    public B01 getB01() {
        return b01;
    }

    public void setB01(B01 b01) {
        this.b01 = b01;
    }

}
