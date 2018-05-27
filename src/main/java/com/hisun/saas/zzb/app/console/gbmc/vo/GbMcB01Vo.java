/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbmc.vo;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouying on 2017/9/8.
 */
public class GbMcB01Vo{
    private String id;
    private GbMcVo gbMcVo;
    private String b0101;
    private int px;
    private List<GbMcA01Vo> gbMcA01Vos;
    private int a01Count;
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

    public GbMcVo getGbMcVo() {
        return gbMcVo;
    }

    public void setGbMcVo(GbMcVo gbMcVo) {
        this.gbMcVo = gbMcVo;
    }

    public List<GbMcA01Vo> getGbMcA01Vos() {
        return gbMcA01Vos;
    }

    public void setGbMcA01Vos(List<GbMcA01Vo> gbMcA01Vos) {
        this.gbMcA01Vos = gbMcA01Vos;
    }

    public int getA01Count() {
        return a01Count;
    }

    public void setA01Count(int a01Count) {
        this.a01Count = a01Count;
    }
}
