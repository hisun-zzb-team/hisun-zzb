package com.hisun.saas.zzb.app.console.gendata.vo;

import com.aspose.slides.Collections.Generic.List;
import com.hisun.saas.zzb.app.console.gbtj.vo.GbtjVo;
import com.hisun.saas.zzb.app.console.shpc.vo.ShpcVo;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by zhouying on 2017/9/16.
 */
public class DataPacketContentVo {

    private String id;

    private String name;

    private String dataId;


    private int dataType;

    private int sort;

    private String parent_id;

    private GendataVo gendataVo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public GendataVo getGendataVo() {
        return gendataVo;
    }

    public void setGendataVo(GendataVo gendataVo) {
        this.gendataVo = gendataVo;
    }
}
