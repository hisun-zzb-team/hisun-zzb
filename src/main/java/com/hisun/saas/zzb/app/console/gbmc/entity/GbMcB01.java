/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbmc.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name = "APP_MC_B01")
public class GbMcB01 extends TenantEntity implements Serializable {

    public static int DISPLAY=0;
    public static int HIDDEN=1;



    private String id;

    private GbMc gbMc;

    private String b0101;

    private int px;

    private int isDisplay=DISPLAY;


    private List<GbMcA01> gbMcA01s;
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false, unique = true, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "b0101", length = 255)
    public String getB0101() {
        return b0101;
    }

    public void setB0101(String b0101) {
        this.b0101 = b0101;
    }
    @Column(name = "px")
    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "mc_id")
    public GbMc getGbMc() {
        return gbMc;
    }

    public void setGbMc(GbMc gbMc) {
        this.gbMc = gbMc;
    }
    @OneToMany(mappedBy = "gbMcB01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<GbMcA01> getGbMcA01s() {
        return gbMcA01s;
    }

    public void setGbMcA01s(List<GbMcA01> gbMcA01s) {
        this.gbMcA01s = gbMcA01s;
    }
    @Column(name = "is_display")
    public int getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(int isDisplay) {
        this.isDisplay = isDisplay;
    }

}
