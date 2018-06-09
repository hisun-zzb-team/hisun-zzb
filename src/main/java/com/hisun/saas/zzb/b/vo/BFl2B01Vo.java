package com.hisun.saas.zzb.b.vo;

import com.hisun.saas.zzb.b.entity.B01;
import com.hisun.saas.zzb.b.entity.BFl;

/**
 * Created by 60514 on 2018/6/5.
 */
public class BFl2B01Vo {
    private Integer px;//排序
    private BFlVo bflVo;//分类
    private B01Vo b01Vo;//机构
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

    public BFlVo getBflVo() {
        return bflVo;
    }

    public void setBflVo(BFlVo bflVo) {
        this.bflVo = bflVo;
    }

    public B01Vo getB01Vo() {
        return b01Vo;
    }

    public void setB01Vo(B01Vo b01Vo) {
        this.b01Vo = b01Vo;
    }
}
