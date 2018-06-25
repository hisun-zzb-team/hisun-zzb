/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.e01z2.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Marco {854476391@qq.com}
 */
@Entity
@Table(name = "e01z2")
public class E01Z2 extends TenantEntity implements Serializable {
    private String id;
    private A38 a38;//外键，人员档案主健
    private String e01Z201;//收件日期
    private String e01Z204A;//来件单位名称
    private String e01Z204B;//来件单位代码
    private String e01Z207;//接收人
    private String e01Z211B;//材料类号
    private String e01Z211A;//材料类别名称
    private String e01Z217;//材料编号
    private String e01Z221A;//材料名称
    private String e01Z221B;//材料名称代码
    private Integer e01Z224;//页数
    private String e01Z227;//材料制成日期
    private String e01Z231;//备注
    private Integer e01Z234;//份数
    private String e01Z237;//材料处理标识 1待归档 2待处理
    private String e01Z237Content;//材料处理标识
    private String e01Z241;//零散材料序号
    private String e01Z244;//是否已受理标识
    private String e01Z244Content;//是否已受理标识
    private Integer e01Z214;// 材料序号

    @Basic
    @Column(name = "e01z244_content", nullable = true, length = 64)
    public String getE01Z244Content() {
        return e01Z244Content;
    }

    public void setE01Z244Content(String e01Z244Content) {
        this.e01Z244Content = e01Z244Content;
    }

    @Basic
    @Column(name = "e01z237_content", nullable = true, length = 64)
    public String getE01Z237Content() {
        return e01Z237Content;
    }

    public void setE01Z237Content(String e01Z237Content) {
        this.e01Z237Content = e01Z237Content;
    }

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false, length = 64)
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
    @Column(name = "e01z201", nullable = true, length = 64)
    public String getE01Z201() {
        return e01Z201;
    }

    public void setE01Z201(String e01Z201) {
        this.e01Z201 = e01Z201;
    }



    @Basic
    @Column(name = "e01z204a", nullable = true, length = 512)
    public String getE01Z204A() {
        return e01Z204A;
    }

    public void setE01Z204A(String e01Z204A) {
        this.e01Z204A = e01Z204A;
    }



    @Basic
    @Column(name = "e01z204b", nullable = true, length = 128)
    public String getE01Z204B() {
        return e01Z204B;
    }

    public void setE01Z204B(String e01Z204B) {
        this.e01Z204B = e01Z204B;
    }



    @Basic
    @Column(name = "e01z207", nullable = true, length = 64)
    public String getE01Z207() {
        return e01Z207;
    }

    public void setE01Z207(String e01Z207) {
        this.e01Z207 = e01Z207;
    }




    @Basic
    @Column(name = "e01z211a", nullable = true, length = 256)
    public String getE01Z211A() {
        return e01Z211A;
    }

    public void setE01Z211A(String e01Z211A) {
        this.e01Z211A = e01Z211A;
    }
    @Basic
    @Column(name = "e01z211b", nullable = true, length =32)
    public String getE01Z211B() {
        return e01Z211B;
    }

    public void setE01Z211B(String e01Z211B) {
        this.e01Z211B = e01Z211B;
    }

    @Basic
    @Column(name = "e01z217", nullable = true, length = 8)
    public String getE01Z217() {
        return e01Z217;
    }

    public void setE01Z217(String e01Z217) {
        this.e01Z217 = e01Z217;
    }




    @Basic
    @Column(name = "e01z221a", nullable = true, length = 512)
    public String getE01Z221A() {
        return e01Z221A;
    }

    public void setE01Z221A(String e01Z221A) {
        this.e01Z221A = e01Z221A;
    }



    @Basic
    @Column(name = "e01z221b", nullable = true, length = 512)
    public String getE01Z221B() {
        return e01Z221B;
    }

    public void setE01Z221B(String e01Z221B) {
        this.e01Z221B = e01Z221B;
    }



    @Basic
    @Column(name = "e01z224", nullable = true)
    public Integer getE01Z224() {
        return e01Z224;
    }

    public void setE01Z224(Integer e01Z224) {
        this.e01Z224 = e01Z224;
    }



    @Basic
    @Column(name = "e01z227", nullable = true, length = 64)
    public String getE01Z227() {
        return e01Z227;
    }

    public void setE01Z227(String e01Z227) {
        this.e01Z227 = e01Z227;
    }



    @Basic
    @Column(name = "e01z231", nullable = true, length = 512)
    public String getE01Z231() {
        return e01Z231;
    }

    public void setE01Z231(String e01Z231) {
        this.e01Z231 = e01Z231;
    }



    @Basic
    @Column(name = "e01z234", nullable = true)
    public Integer getE01Z234() {
        return e01Z234;
    }

    public void setE01Z234(Integer e01Z234) {
        this.e01Z234 = e01Z234;
    }



    @Basic
    @Column(name = "e01z237", nullable = true, length = 64)
    public String getE01Z237() {
        return e01Z237;
    }

    public void setE01Z237(String e01Z237) {
        this.e01Z237 = e01Z237;
    }



    @Basic
    @Column(name = "e01z241", nullable = true, length = 128)
    public String getE01Z241() {
        return e01Z241;
    }

    public void setE01Z241(String e01Z241) {
        this.e01Z241 = e01Z241;
    }



    @Basic
    @Column(name = "e01z244", nullable = true, length = 8)
    public String getE01Z244() {
        return e01Z244;
    }

    public void setE01Z244(String e01Z244) {
        this.e01Z244 = e01Z244;
    }



    @Basic
    @Column(name = "e01z214", nullable = true)
    public Integer getE01Z214() {
        return e01Z214;
    }

    public void setE01Z214(Integer e01Z214) {
        this.e01Z214 = e01Z214;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        E01Z2 e01Z2 = (E01Z2) o;

        if (id != null ? !id.equals(e01Z2.id) : e01Z2.id != null) return false;
        if (a38 != null ? !a38.equals(e01Z2.a38) : e01Z2.a38 != null) return false;
        if (e01Z201 != null ? !e01Z201.equals(e01Z2.e01Z201) : e01Z2.e01Z201 != null) return false;
        if (e01Z204A != null ? !e01Z204A.equals(e01Z2.e01Z204A) : e01Z2.e01Z204A != null) return false;
        if (e01Z204B != null ? !e01Z204B.equals(e01Z2.e01Z204B) : e01Z2.e01Z204B != null) return false;
        if (e01Z207 != null ? !e01Z207.equals(e01Z2.e01Z207) : e01Z2.e01Z207 != null) return false;
        if (e01Z217 != null ? !e01Z217.equals(e01Z2.e01Z217) : e01Z2.e01Z217 != null) return false;
        if (e01Z221A != null ? !e01Z221A.equals(e01Z2.e01Z221A) : e01Z2.e01Z221A != null) return false;
        if (e01Z221B != null ? !e01Z221B.equals(e01Z2.e01Z221B) : e01Z2.e01Z221B != null) return false;
        if (e01Z224 != null ? !e01Z224.equals(e01Z2.e01Z224) : e01Z2.e01Z224 != null) return false;
        if (e01Z227 != null ? !e01Z227.equals(e01Z2.e01Z227) : e01Z2.e01Z227 != null) return false;
        if (e01Z231 != null ? !e01Z231.equals(e01Z2.e01Z231) : e01Z2.e01Z231 != null) return false;
        if (e01Z234 != null ? !e01Z234.equals(e01Z2.e01Z234) : e01Z2.e01Z234 != null) return false;
        if (e01Z237 != null ? !e01Z237.equals(e01Z2.e01Z237) : e01Z2.e01Z237 != null) return false;
        if (e01Z241 != null ? !e01Z241.equals(e01Z2.e01Z241) : e01Z2.e01Z241 != null) return false;
        if (e01Z244 != null ? !e01Z244.equals(e01Z2.e01Z244) : e01Z2.e01Z244 != null) return false;
        if (e01Z214 != null ? !e01Z214.equals(e01Z2.e01Z214) : e01Z2.e01Z214 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (a38 != null ? a38.hashCode() : 0);
        result = 31 * result + (e01Z201 != null ? e01Z201.hashCode() : 0);
        result = 31 * result + (e01Z204A != null ? e01Z204A.hashCode() : 0);
        result = 31 * result + (e01Z204B != null ? e01Z204B.hashCode() : 0);
        result = 31 * result + (e01Z207 != null ? e01Z207.hashCode() : 0);
        result = 31 * result + (e01Z217 != null ? e01Z217.hashCode() : 0);
        result = 31 * result + (e01Z221A != null ? e01Z221A.hashCode() : 0);
        result = 31 * result + (e01Z221B != null ? e01Z221B.hashCode() : 0);
        result = 31 * result + (e01Z224 != null ? e01Z224.hashCode() : 0);
        result = 31 * result + (e01Z227 != null ? e01Z227.hashCode() : 0);
        result = 31 * result + (e01Z231 != null ? e01Z231.hashCode() : 0);
        result = 31 * result + (e01Z234 != null ? e01Z234.hashCode() : 0);
        result = 31 * result + (e01Z237 != null ? e01Z237.hashCode() : 0);
        result = 31 * result + (e01Z241 != null ? e01Z241.hashCode() : 0);
        result = 31 * result + (e01Z244 != null ? e01Z244.hashCode() : 0);
        result = 31 * result + (e01Z214 != null ? e01Z214.hashCode() : 0);
        return result;
    }
}
