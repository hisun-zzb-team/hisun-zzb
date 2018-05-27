/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gendata.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouying on 2017/9/23.
 */
@Entity
@Table(name = "app_data_packet")
public class Gendata extends TenantEntity implements Serializable {

    public static int IS_CURRENT=1;
    public static int IS_NOT_CURRENT=0;


    private String id;


    private String path;

  //是否当前数据包 0-不是数据包，1-当前数据包
    private int isCurrentPacket=IS_NOT_CURRENT;


    private String packetMd5;


    private String packetSize;


    private String packetName;


    private List<DataPacketContent> dataPacketContents;
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
    @Column(name = "path",length = 255)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    @Column(name = "is_current_packet")
    public int getIsCurrentPacket() {
        return isCurrentPacket;
    }

    public void setIsCurrentPacket(int isCurrentPacket) {
        this.isCurrentPacket = isCurrentPacket;
    }
    @Column(name = "packet_md5",length = 64)
    public String getPacketMd5() {
        return packetMd5;
    }

    public void setPacketMd5(String packetMd5) {
        this.packetMd5 = packetMd5;
    }
    @Column(name = "packet_size",length = 32)
    public String getPacketSize() {
        return packetSize;
    }

    public void setPacketSize(String packetSize) {
        this.packetSize = packetSize;
    }
    @Column(name = "packet_name",length = 32)
    public String getPacketName() {
        return packetName;
    }

    public void setPacketName(String packetName) {
        this.packetName = packetName;
    }

    @OneToMany(mappedBy="gendata",fetch= FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<DataPacketContent> getDataPacketContents() {
        return dataPacketContents;
    }

    public void setDataPacketContents(List<DataPacketContent> dataPacketContents) {
        this.dataPacketContents = dataPacketContents;
    }

    public void addDataPacketContent(DataPacketContent dataPacketContent){
        if(this.dataPacketContents==null){
            this.dataPacketContents = new ArrayList<DataPacketContent>();
        }
        dataPacketContent.setGendata(this);
        this.dataPacketContents.add(dataPacketContent);
    }
    @Transient
    public List<DataPacketContent> getShpcDataPacketContents() {
        if(this.dataPacketContents!=null){
            List<DataPacketContent> newDataPacketContents = new ArrayList<>();
            for(DataPacketContent dataPacketContent : this.dataPacketContents){
                if(dataPacketContent.getDataType()== DataPacketContent.SHPC_DATA){
                    newDataPacketContents.add(dataPacketContent);
                }
            }
            return newDataPacketContents;
        }
        return null;
    }
    @Transient
    public List<DataPacketContent> getGbcxDataPacketContents() {
        if(this.dataPacketContents!=null){
            List<DataPacketContent> newDataPacketContents = new ArrayList<>();
            for(DataPacketContent dataPacketContent : this.dataPacketContents){
                if(dataPacketContent.getDataType()== DataPacketContent.GBCX_DATA){
                    newDataPacketContents.add(dataPacketContent);
                }
            }
            return newDataPacketContents;
        }
        return null;
    }
    @Transient
    public List<DataPacketContent> getGbtjDataPacketContents() {
        if(this.dataPacketContents!=null){
            List<DataPacketContent> newDataPacketContents = new ArrayList<>();
            for(DataPacketContent dataPacketContent : this.dataPacketContents){
                if(dataPacketContent.getDataType()== DataPacketContent.GBTJ_DATA){
                    newDataPacketContents.add(dataPacketContent);
                }
            }
            return newDataPacketContents;
        }
        return null;
    }

    @Transient
    public List<DataPacketContent> getGbmcDataPacketContents() {
        if(this.dataPacketContents!=null){
            List<DataPacketContent> newDataPacketContents = new ArrayList<>();
            for(DataPacketContent dataPacketContent : this.dataPacketContents){
                if(dataPacketContent.getDataType()== DataPacketContent.GBMC_DATA){
                    newDataPacketContents.add(dataPacketContent);
                }
            }
            return newDataPacketContents;
        }
        return null;
    }


}
