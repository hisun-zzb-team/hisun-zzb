/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.mlcl.entity;

import com.hisun.base.entity.TombstoneEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author liuzj {279421824@qq.com}
 */
@Entity
@Table(name = "e_list_row_count")
public class EListRowCount  extends TombstoneEntity implements Serializable {
    private String id;
    private String bigTypeCount;
    private String smallTypeCount;

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

    @Basic
    @Column(name = "big_type_count")
    public String getBigTypeCount() {
        return bigTypeCount;
    }

    public void setBigTypeCount(String bigTypeCount) {
        this.bigTypeCount = bigTypeCount;
    }

    @Basic
    @Column(name = "small_type_count")
    public String getSmallTypeCount() {
        return smallTypeCount;
    }

    public void setSmallTypeCount(String smallTypeCount) {
        this.smallTypeCount = smallTypeCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EListRowCount that = (EListRowCount) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (bigTypeCount != null ? !bigTypeCount.equals(that.bigTypeCount) : that.bigTypeCount != null) return false;
        if (smallTypeCount != null ? !smallTypeCount.equals(that.smallTypeCount) : that.smallTypeCount != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bigTypeCount != null ? bigTypeCount.hashCode() : 0);
        result = 31 * result + (smallTypeCount != null ? smallTypeCount.hashCode() : 0);
        return result;
    }
}
