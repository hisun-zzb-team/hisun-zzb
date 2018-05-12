/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.mlcl.entity;

import com.hisun.base.entity.TombstoneEntity;
import com.hisun.saas.zzb.dzda.a32.entity.A32;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author liuzj {279421824@qq.com}
 */
@Entity
@Table(name = "e01z1")
public class E01Z1  extends TombstoneEntity implements Serializable {
    private String id;//材料主键
    private A38 a38;//外键，档案基本信息主键
    private String e01Z111;//材料名称
    private String e01Z111Remark;//材料名称备注
    private String e01Z117;//材料制成时间
    private Integer e01Z107;//扫描序号（单份材料的扫描序号）
    private String e01Z101B;//材料类别字典代码
    private String e01Z101A;//材料类别字典内容
    private Integer e01Z114;//材料页数
    private Integer smys;//扫描页数
    private Integer e01Z124;//材料份数
    private String e01Z207;//接收人姓名
    private String e01Z204;//材料来处
    private String e01Z201;//材料接收时间
    private String e01Z231;//备注
    private Integer yjztps;//已加载图片数
    private Integer e01Z104;//排序号
    private List<EImages> images;

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
    @JoinColumn(name = "a38_id")
    public A38 getA38() {
        return a38;
    }

    public void setA38(A38 a38) {
        this.a38 = a38;
    }

    @Basic
    @Column(name = "e01z111")
    public String getE01Z111() {
        return e01Z111;
    }

    public void setE01Z111(String e01Z111) {
        this.e01Z111 = e01Z111;
    }

    @Basic
    @Column(name = "e01z111_remark")
    public String getE01Z111Remark() {
        return e01Z111Remark;
    }

    public void setE01Z111Remark(String e01Z111Remark) {
        this.e01Z111Remark = e01Z111Remark;
    }

    @Basic
    @Column(name = "e01z117")
    public String getE01Z117() {
        return e01Z117;
    }

    public void setE01Z117(String e01Z117) {
        this.e01Z117 = e01Z117;
    }

    @Basic
    @Column(name = "e01z107")
    public Integer getE01Z107() {
        return e01Z107;
    }

    public void setE01Z107(Integer e01Z107) {
        this.e01Z107 = e01Z107;
    }

    @Basic
    @Column(name = "e01z101b")
    public String getE01Z101B() {
        return e01Z101B;
    }

    public void setE01Z101B(String e01Z101B) {
        this.e01Z101B = e01Z101B;
    }

    @Basic
    @Column(name = "e01z101a")
    public String getE01Z101A() {
        return e01Z101A;
    }

    public void setE01Z101A(String e01Z101A) {
        this.e01Z101A = e01Z101A;
    }

    @Basic
    @Column(name = "e01z114")
    public Integer getE01Z114() {
        return e01Z114;
    }

    public void setE01Z114(Integer e01Z114) {
        this.e01Z114 = e01Z114;
    }

    @Basic
    @Column(name = "smys")
    public Integer getSmys() {
        return smys;
    }

    public void setSmys(Integer smys) {
        this.smys = smys;
    }

    @Basic
    @Column(name = "e01z124")
    public Integer getE01Z124() {
        return e01Z124;
    }

    public void setE01Z124(Integer e01Z124) {
        this.e01Z124 = e01Z124;
    }

    @Basic
    @Column(name = "e01z207")
    public String getE01Z207() {
        return e01Z207;
    }

    public void setE01Z207(String e01Z207) {
        this.e01Z207 = e01Z207;
    }

    @Basic
    @Column(name = "e01z204")
    public String getE01Z204() {
        return e01Z204;
    }

    public void setE01Z204(String e01Z204) {
        this.e01Z204 = e01Z204;
    }

    @Basic
    @Column(name = "e01z201")
    public String getE01Z201() {
        return e01Z201;
    }

    public void setE01Z201(String e01Z201) {
        this.e01Z201 = e01Z201;
    }

    @Basic
    @Column(name = "e01z231")
    public String getE01Z231() {
        return e01Z231;
    }

    public void setE01Z231(String e01Z231) {
        this.e01Z231 = e01Z231;
    }

    @Basic
    @Column(name = "yjztps")
    public Integer getYjztps() {
        return yjztps;
    }

    public void setYjztps(Integer yjztps) {
        this.yjztps = yjztps;
    }

    @Basic
    @Column(name = "e01z104")
    public Integer getE01Z104() {
        return e01Z104;
    }

    public void setE01Z104(Integer e01Z104) {
        this.e01Z104 = e01Z104;
    }

    @OneToMany(mappedBy = "e01z1", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<EImages> getImages() {
        return images;
    }

    public void setImages(List<EImages> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        E01Z1 e01Z1 = (E01Z1) o;

        if (id != null ? !id.equals(e01Z1.id) : e01Z1.id != null) return false;
        if (e01Z111 != null ? !e01Z111.equals(e01Z1.e01Z111) : e01Z1.e01Z111 != null) return false;
        if (e01Z111Remark != null ? !e01Z111Remark.equals(e01Z1.e01Z111Remark) : e01Z1.e01Z111Remark != null)
            return false;
        if (e01Z117 != null ? !e01Z117.equals(e01Z1.e01Z117) : e01Z1.e01Z117 != null) return false;
        if (e01Z107 != null ? !e01Z107.equals(e01Z1.e01Z107) : e01Z1.e01Z107 != null) return false;
        if (e01Z101B != null ? !e01Z101B.equals(e01Z1.e01Z101B) : e01Z1.e01Z101B != null) return false;
        if (e01Z101A != null ? !e01Z101A.equals(e01Z1.e01Z101A) : e01Z1.e01Z101A != null) return false;
        if (e01Z114 != null ? !e01Z114.equals(e01Z1.e01Z114) : e01Z1.e01Z114 != null) return false;
        if (smys != null ? !smys.equals(e01Z1.smys) : e01Z1.smys != null) return false;
        if (e01Z124 != null ? !e01Z124.equals(e01Z1.e01Z124) : e01Z1.e01Z124 != null) return false;
        if (e01Z207 != null ? !e01Z207.equals(e01Z1.e01Z207) : e01Z1.e01Z207 != null) return false;
        if (e01Z204 != null ? !e01Z204.equals(e01Z1.e01Z204) : e01Z1.e01Z204 != null) return false;
        if (e01Z201 != null ? !e01Z201.equals(e01Z1.e01Z201) : e01Z1.e01Z201 != null) return false;
        if (e01Z231 != null ? !e01Z231.equals(e01Z1.e01Z231) : e01Z1.e01Z231 != null) return false;
        if (yjztps != null ? !yjztps.equals(e01Z1.yjztps) : e01Z1.yjztps != null) return false;
        if (e01Z104 != null ? !e01Z104.equals(e01Z1.e01Z104) : e01Z1.e01Z104 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (e01Z111 != null ? e01Z111.hashCode() : 0);
        result = 31 * result + (e01Z111Remark != null ? e01Z111Remark.hashCode() : 0);
        result = 31 * result + (e01Z117 != null ? e01Z117.hashCode() : 0);
        result = 31 * result + (e01Z107 != null ? e01Z107.hashCode() : 0);
        result = 31 * result + (e01Z101B != null ? e01Z101B.hashCode() : 0);
        result = 31 * result + (e01Z101A != null ? e01Z101A.hashCode() : 0);
        result = 31 * result + (e01Z114 != null ? e01Z114.hashCode() : 0);
        result = 31 * result + (smys != null ? smys.hashCode() : 0);
        result = 31 * result + (e01Z124 != null ? e01Z124.hashCode() : 0);
        result = 31 * result + (e01Z207 != null ? e01Z207.hashCode() : 0);
        result = 31 * result + (e01Z204 != null ? e01Z204.hashCode() : 0);
        result = 31 * result + (e01Z201 != null ? e01Z201.hashCode() : 0);
        result = 31 * result + (e01Z231 != null ? e01Z231.hashCode() : 0);
        result = 31 * result + (yjztps != null ? yjztps.hashCode() : 0);
        result = 31 * result + (e01Z104 != null ? e01Z104.hashCode() : 0);
        return result;
    }

}
