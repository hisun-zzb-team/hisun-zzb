/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.mlcl.entity;

import com.hisun.base.entity.TombstoneEntity;
import com.hisun.saas.zzb.dzda.a32.entity.A32;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author liuzj {279421824@qq.com}
 */
@Entity
@Table(name = "e_images")
public class EImages  extends TombstoneEntity implements Serializable {
    private String id;
    private E01Z1 e01z1;
    private String imgNo;
    private String imgFilePath;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "e01z1_id")
    public E01Z1 getE01z1() {
        return e01z1;
    }

    public void setE01z1(E01Z1 e01z1) {
        this.e01z1 = e01z1;
    }




    @Basic
    @Column(name = "img_no")
    public String getImgNo() {
        return imgNo;
    }

    public void setImgNo(String imgNo) {
        this.imgNo = imgNo;
    }

    @Basic
    @Column(name = "img_file_path")
    public String getImgFilePath() {
        return imgFilePath;
    }

    public void setImgFilePath(String imgFilePath) {
        this.imgFilePath = imgFilePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EImages eImages = (EImages) o;

        if (id != null ? !id.equals(eImages.id) : eImages.id != null) return false;
        if (imgNo != null ? !imgNo.equals(eImages.imgNo) : eImages.imgNo != null) return false;
        if (imgFilePath != null ? !imgFilePath.equals(eImages.imgFilePath) : eImages.imgFilePath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (imgNo != null ? imgNo.hashCode() : 0);
        result = 31 * result + (imgFilePath != null ? imgFilePath.hashCode() : 0);
        return result;
    }
}
