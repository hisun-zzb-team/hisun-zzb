/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.dacy.entity;

import com.hisun.base.entity.TombstoneEntity;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author liuzj {279421824@qq.com}
 */
@Entity
@Table(name = "e_popedom_e01z1_relation")
public class EPopedomE01Z1Relation implements Serializable{
    private String id;
    private EApplyE01Z8 applyE01Z8;
    private E01Z1 e01z1;

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
    @JoinColumn(name = "apply_e01z8_id")
    public EApplyE01Z8 getApplyE01Z8() {
        return applyE01Z8;
    }

    public void setApplyE01Z8(EApplyE01Z8 applyE01Z8) {
        this.applyE01Z8 = applyE01Z8;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "e01z1_id")
    public E01Z1 getE01z1() {
        return e01z1;
    }

    public void setE01z1(E01Z1 e01z1) {
        this.e01z1 = e01z1;
    }

}
