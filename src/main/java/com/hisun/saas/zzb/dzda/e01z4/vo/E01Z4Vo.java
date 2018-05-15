/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.e01z4.vo;

import com.hisun.base.entity.TombstoneEntity;
import com.hisun.saas.zzb.dzda.a38.entity.A38;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author zhout {605144321@qq.com}
 */
public class E01Z4Vo {
    private String id;
    private String e01Z401;
    private String eCatalogTypeId;
    private String e01Z401B;
    private String e01Z401A;
    private Integer e01Z407;
    private String fileTime;
    private String fileTypeCode;
    private String fileTypeName;
    private Integer px;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getE01Z401() {
        return e01Z401;
    }

    public void setE01Z401(String e01Z401) {
        this.e01Z401 = e01Z401;
    }

    public String geteCatalogTypeId() {
        return eCatalogTypeId;
    }

    public void seteCatalogTypeId(String eCatalogTypeId) {
        this.eCatalogTypeId = eCatalogTypeId;
    }

    public String getE01Z401B() {
        return e01Z401B;
    }

    public void setE01Z401B(String e01Z401B) {
        this.e01Z401B = e01Z401B;
    }

    public String getE01Z401A() {
        return e01Z401A;
    }

    public void setE01Z401A(String e01Z401A) {
        this.e01Z401A = e01Z401A;
    }

    public Integer getE01Z407() {
        return e01Z407;
    }

    public void setE01Z407(Integer e01Z407) {
        this.e01Z407 = e01Z407;
    }

    public String getFileTime() {
        return fileTime;
    }

    public void setFileTime(String fileTime) {
        this.fileTime = fileTime;
    }

    public String getFileTypeCode() {
        return fileTypeCode;
    }

    public void setFileTypeCode(String fileTypeCode) {
        this.fileTypeCode = fileTypeCode;
    }

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    public Integer getPx() {
        return px;
    }

    public void setPx(Integer px) {
        this.px = px;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
