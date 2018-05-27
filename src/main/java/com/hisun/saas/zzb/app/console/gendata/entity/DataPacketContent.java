/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gendata.entity;

import com.hisun.base.entity.BaseEntity;
import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zhouying on 2017/9/23.
 */
@Entity
@Table(name = "app_data_packet_content")
public class DataPacketContent extends TenantEntity implements Serializable {

    public static int SHPC_DATA=1;
    public static int GBTJ_DATA=2;
    public static int GBMC_DATA=3;
    public static int GBCX_DATA=4;
    public static int ZSCX_DATA=5;


    private String id;


    private String name;


    private String dataId;



    private int dataType;


    private int sort;


    private Gendata gendata;
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false, unique = true, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name = "name",length = 240)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "data_id",length = 32)
    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
    @Column(name = "data_type")
    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }
    @Column(name = "sort")
    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "data_packet_id")
    public Gendata getGendata() {
        return gendata;
    }

    public void setGendata(Gendata gendata) {
        this.gendata = gendata;
    }
}
