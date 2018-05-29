/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.b.entity;

import com.hisun.base.entity.TombstoneEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author liuzj {279421824@qq.com}
 */
@Entity
@Table(name = "b01")
public class B01 extends TombstoneEntity  implements Serializable {
    private String b0100;
    private String b0101;
    private String b0104;
    private String b0107;
    private String b0111;
    private String b0114;
    private String b0117;
    private String b0124;
    private String b0127;
    private String b0127A;
    private String b0131;
    private String b0131A;
    private String b0134;
    private String b0137;
    private String b0144A;
    private String b0144B;
    private String b0151A;
    private String b0151B;
    private String b0154A;
    private String b0154B;
    private String b0157A;
    private String b0157B;
    private String b0161;
    private String b0164;
    private String b0167;
    private String b0171A;
    private String b0171B;
    private String b0174;
    private String b0177;
    private String b0181;
    private String b0184;
    private String b0187A;
    private String b0187B;
    private String b0191;
    private String b0194;
    private String b0197;
    private String bDwztB;
    private String bDwztA;
    private String bSfls;
    private String bGllbB;
    private String bGllbA;
    private String bSftjzs;
    private String bZgzzjbB;
    private String bZgzzjbA;
    private String bZgfzjbB;
    private String bZgfzjbA;
    private Integer bPx;
    private String bCxbm;
    private String bSjlx;
    private String bSfgwytj;

    private List<B02> b02s;
    private List<B04> b04s;
    private List<B09> b09s;
    private List<B10> b10s;
    private List<BFl2B01> bFl2B01s;
    private B01 parentB01;
    private List<B01> childB01s;
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "b0100", nullable = false, unique = true, length = 32)
    public String getB0100() {
        return b0100;
    }
    @OneToMany(mappedBy = "b01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<B02> getB02s() {
        return b02s;
    }

    public void setB02s(List<B02> b02s) {
        this.b02s = b02s;
    }
    @OneToMany(mappedBy = "b01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<B04> getB04s() {
        return b04s;
    }

    public void setB04s(List<B04> b04s) {
        this.b04s = b04s;
    }
    @OneToMany(mappedBy = "b01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<B09> getB09s() {
        return b09s;
    }

    public void setB09s(List<B09> b09s) {
        this.b09s = b09s;
    }
    @OneToMany(mappedBy = "b01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<B10> getB10s() {
        return b10s;
    }

    public void setB10s(List<B10> b10s) {
        this.b10s = b10s;
    }
    @OneToMany(mappedBy = "b01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<BFl2B01> getbFl2B01s() {
        return bFl2B01s;
    }

    public void setbFl2B01s(List<BFl2B01> bFl2B01s) {
        this.bFl2B01s = bFl2B01s;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b_sjjg")
    public B01 getParentB01() {
        return parentB01;
    }

    public void setParentB01(B01 parentB01) {
        this.parentB01 = parentB01;
    }
    @OneToMany(mappedBy = "parentB01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<B01> getChildB01s() {
        return childB01s;
    }

    public void setChildB01s(List<B01> childB01s) {
        this.childB01s = childB01s;
    }

    public void setB0100(String b0100) {
        this.b0100 = b0100;
    }
    @Basic
    @javax.persistence.Column(name = "b0101", nullable = true, length = 512)
    public String getB0101() {
        return b0101;
    }

    public void setB0101(String b0101) {
        this.b0101 = b0101;
    }

    @Basic
    @javax.persistence.Column(name = "b0104", nullable = true, length = 512)
    public String getB0104() {
        return b0104;
    }

    public void setB0104(String b0104) {
        this.b0104 = b0104;
    }

    @Basic
    @javax.persistence.Column(name = "b0107", nullable = true, length = 6)
    public String getB0107() {
        return b0107;
    }
    public void setB0107(String b0107) {
        this.b0107 = b0107;
    }

    @Basic
    @javax.persistence.Column(name = "b0111", nullable = true, length = 24)
    public String getB0111() {
        return b0111;
    }
    public void setB0111(String b0111) {
        this.b0111 = b0111;
    }

    @Basic
    @javax.persistence.Column(name = "b0114", nullable = true, length = 12)
    public String getB0114() {
        return b0114;
    }
    public void setB0114(String b0114) {
        this.b0114 = b0114;
    }

    @Basic
    @javax.persistence.Column(name = "b0117", nullable = true, length = 6)
    public String getB0117() {
        return b0117;
    }

    public void setB0117(String b0117) {
        this.b0117 = b0117;
    }

    @Basic
    @javax.persistence.Column(name = "b0124", nullable = true, length = 2)
    public String getB0124() {
        return b0124;
    }

    public void setB0124(String b0124) {
        this.b0124 = b0124;
    }

    @Basic
    @javax.persistence.Column(name = "b0127", nullable = true, length = 128)
    public String getB0127() {
        return b0127;
    }

    public void setB0127(String b0127) {
        this.b0127 = b0127;
    }


    @Basic
    @javax.persistence.Column(name = "b0127a", nullable = true, length = 256)
    public String getB0127A() {
        return b0127A;
    }
    public void setB0127A(String b0127A) {
        this.b0127A = b0127A;
    }

    @Basic
    @javax.persistence.Column(name = "b0131", nullable = true, length = 128)
    public String getB0131() {
        return b0131;
    }

    public void setB0131(String b0131) {
        this.b0131 = b0131;
    }


    @Basic
    @javax.persistence.Column(name = "b0131a", nullable = true, length = 256)
    public String getB0131A() {
        return b0131A;
    }

    public void setB0131A(String b0131A) {
        this.b0131A = b0131A;
    }


    @Basic
    @javax.persistence.Column(name = "b0134", nullable = true, length = 5)
    public String getB0134() {
        return b0134;
    }

    public void setB0134(String b0134) {
        this.b0134 = b0134;
    }


    @Basic
    @javax.persistence.Column(name = "b0137", nullable = true, length = 4000)
    public String getB0137() {
        return b0137;
    }

    public void setB0137(String b0137) {
        this.b0137 = b0137;
    }


    @Basic
    @javax.persistence.Column(name = "b0144a", nullable = true, length = 240)
    public String getB0144A() {
        return b0144A;
    }

    public void setB0144A(String b0144A) {
        this.b0144A = b0144A;
    }


    @Basic
    @javax.persistence.Column(name = "b0144b", nullable = true, length = 64)
    public String getB0144B() {
        return b0144B;
    }

    public void setB0144B(String b0144B) {
        this.b0144B = b0144B;
    }


    @Basic
    @javax.persistence.Column(name = "b0151a", nullable = true, length = 240)
    public String getB0151A() {
        return b0151A;
    }

    public void setB0151A(String b0151A) {
        this.b0151A = b0151A;
    }


    @Basic
    @javax.persistence.Column(name = "b0151b", nullable = true, length = 30)
    public String getB0151B() {
        return b0151B;
    }

    public void setB0151B(String b0151B) {
        this.b0151B = b0151B;
    }


    @Basic
    @javax.persistence.Column(name = "b0154a", nullable = true, length = 60)
    public String getB0154A() {
        return b0154A;
    }

    public void setB0154A(String b0154A) {
        this.b0154A = b0154A;
    }


    @Basic
    @javax.persistence.Column(name = "b0154b", nullable = true, length = 15)
    public String getB0154B() {
        return b0154B;
    }

    public void setB0154B(String b0154B) {
        this.b0154B = b0154B;
    }


    @Basic
    @javax.persistence.Column(name = "b0157a", nullable = true, length = 60)
    public String getB0157A() {
        return b0157A;
    }

    public void setB0157A(String b0157A) {
        this.b0157A = b0157A;
    }


    @Basic
    @javax.persistence.Column(name = "b0157b", nullable = true, length = 15)
    public String getB0157B() {
        return b0157B;
    }

    public void setB0157B(String b0157B) {
        this.b0157B = b0157B;
    }


    @Basic
    @javax.persistence.Column(name = "b0161", nullable = true, length = 36)
    public String getB0161() {
        return b0161;
    }

    public void setB0161(String b0161) {
        this.b0161 = b0161;
    }


    @Basic
    @javax.persistence.Column(name = "b0164", nullable = true, length = 8)
    public String getB0164() {
        return b0164;
    }

    public void setB0164(String b0164) {
        this.b0164 = b0164;
    }


    @Basic
    @javax.persistence.Column(name = "b0167", nullable = true, length = 24)
    public String getB0167() {
        return b0167;
    }

    public void setB0167(String b0167) {
        this.b0167 = b0167;
    }


    @Basic
    @javax.persistence.Column(name = "b0171a", nullable = true, length = 60)
    public String getB0171A() {
        return b0171A;
    }

    public void setB0171A(String b0171A) {
        this.b0171A = b0171A;
    }


    @Basic
    @javax.persistence.Column(name = "b0171b", nullable = true, length = 15)
    public String getB0171B() {
        return b0171B;
    }

    public void setB0171B(String b0171B) {
        this.b0171B = b0171B;
    }


    @Basic
    @javax.persistence.Column(name = "b0174", nullable = true, length = 1)
    public String getB0174() {
        return b0174;
    }

    public void setB0174(String b0174) {
        this.b0174 = b0174;
    }


    @Basic
    @javax.persistence.Column(name = "b0177", nullable = true, length = 1)
    public String getB0177() {
        return b0177;
    }

    public void setB0177(String b0177) {
        this.b0177 = b0177;
    }


    @Basic
    @javax.persistence.Column(name = "b0181", nullable = true, length = 8)
    public String getB0181() {
        return b0181;
    }

    public void setB0181(String b0181) {
        this.b0181 = b0181;
    }


    @Basic
    @javax.persistence.Column(name = "b0184", nullable = true, length = 24)
    public String getB0184() {
        return b0184;
    }

    public void setB0184(String b0184) {
        this.b0184 = b0184;
    }


    @Basic
    @javax.persistence.Column(name = "b0187a", nullable = true, length = 240)
    public String getB0187A() {
        return b0187A;
    }

    public void setB0187A(String b0187A) {
        this.b0187A = b0187A;
    }


    @Basic
    @javax.persistence.Column(name = "b0187b", nullable = true, length = 64)
    public String getB0187B() {
        return b0187B;
    }

    public void setB0187B(String b0187B) {
        this.b0187B = b0187B;
    }


    @Basic
    @javax.persistence.Column(name = "b0191", nullable = true, length = 10)
    public String getB0191() {
        return b0191;
    }

    public void setB0191(String b0191) {
        this.b0191 = b0191;
    }


    @Basic
    @javax.persistence.Column(name = "b0194", nullable = true, length = 1)
    public String getB0194() {
        return b0194;
    }

    public void setB0194(String b0194) {
        this.b0194 = b0194;
    }


    @Basic
    @javax.persistence.Column(name = "b0197", nullable = true, length = 4000)
    public String getB0197() {
        return b0197;
    }

    public void setB0197(String b0197) {
        this.b0197 = b0197;
    }


    @Basic
    @javax.persistence.Column(name = "b_dwzt_b", nullable = true, length = 128)
    public String getbDwztB() {
        return bDwztB;
    }

    public void setbDwztB(String bDwztB) {
        this.bDwztB = bDwztB;
    }


    @Basic
    @javax.persistence.Column(name = "b_dwzt_a", nullable = true, length = 256)
    public String getbDwztA() {
        return bDwztA;
    }

    public void setbDwztA(String bDwztA) {
        this.bDwztA = bDwztA;
    }


    @Basic
    @javax.persistence.Column(name = "b_sfls", nullable = true, length = 1)
    public String getbSfls() {
        return bSfls;
    }

    public void setbSfls(String bSfls) {
        this.bSfls = bSfls;
    }


    @Basic
    @javax.persistence.Column(name = "b_gllb_b", nullable = true, length = 3)
    public String getbGllbB() {
        return bGllbB;
    }

    public void setbGllbB(String bGllbB) {
        this.bGllbB = bGllbB;
    }


    @Basic
    @javax.persistence.Column(name = "b_gllb_a", nullable = true, length = 20)
    public String getbGllbA() {
        return bGllbA;
    }

    public void setbGllbA(String bGllbA) {
        this.bGllbA = bGllbA;
    }


    @Basic
    @javax.persistence.Column(name = "b_sftjzs", nullable = true, length = 1)
    public String getbSftjzs() {
        return bSftjzs;
    }

    public void setbSftjzs(String bSftjzs) {
        this.bSftjzs = bSftjzs;
    }


    @Basic
    @javax.persistence.Column(name = "b_zgzzjb_b", nullable = true, length = 128)
    public String getbZgzzjbB() {
        return bZgzzjbB;
    }

    public void setbZgzzjbB(String bZgzzjbB) {
        this.bZgzzjbB = bZgzzjbB;
    }


    @Basic
    @javax.persistence.Column(name = "b_zgzzjb_a", nullable = true, length = 256)
    public String getbZgzzjbA() {
        return bZgzzjbA;
    }

    public void setbZgzzjbA(String bZgzzjbA) {
        this.bZgzzjbA = bZgzzjbA;
    }


    @Basic
    @javax.persistence.Column(name = "b_zgfzjb_b", nullable = true, length = 128)
    public String getbZgfzjbB() {
        return bZgfzjbB;
    }

    public void setbZgfzjbB(String bZgfzjbB) {
        this.bZgfzjbB = bZgfzjbB;
    }


    @Basic
    @javax.persistence.Column(name = "b_zgfzjb_a", nullable = true, length = 256)
    public String getbZgfzjbA() {
        return bZgfzjbA;
    }

    public void setbZgfzjbA(String bZgfzjbA) {
        this.bZgfzjbA = bZgfzjbA;
    }

    @Basic
    @javax.persistence.Column(name = "b_px", nullable = true, precision = 0)
    public Integer getbPx() {
        return bPx;
    }

    public void setbPx(Integer bPx) {
        this.bPx = bPx;
    }


    @Basic
    @javax.persistence.Column(name = "b_cxbm", nullable = true, length = 256)
    public String getbCxbm() {
        return bCxbm;
    }

    public void setbCxbm(String bCxbm) {
        this.bCxbm = bCxbm;
    }


    @Basic
    @javax.persistence.Column(name = "b_sjlx", nullable = true, length = 12)
    public String getbSjlx() {
        return bSjlx;
    }

    public void setbSjlx(String bSjlx) {
        this.bSjlx = bSjlx;
    }


    @Basic
    @javax.persistence.Column(name = "b_sfgwytj", nullable = true, length = 1)
    public String getbSfgwytj() {
        return bSfgwytj;
    }

    public void setbSfgwytj(String bSfgwytj) {
        this.bSfgwytj = bSfgwytj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        B01 b01 = (B01) o;

        if (b0100 != null ? !b0100.equals(b01.b0100) : b01.b0100 != null) return false;
        if (b0101 != null ? !b0101.equals(b01.b0101) : b01.b0101 != null) return false;
        if (b0104 != null ? !b0104.equals(b01.b0104) : b01.b0104 != null) return false;
        if (b0107 != null ? !b0107.equals(b01.b0107) : b01.b0107 != null) return false;
        if (b0111 != null ? !b0111.equals(b01.b0111) : b01.b0111 != null) return false;
        if (b0114 != null ? !b0114.equals(b01.b0114) : b01.b0114 != null) return false;
        if (b0117 != null ? !b0117.equals(b01.b0117) : b01.b0117 != null) return false;
        if (b0124 != null ? !b0124.equals(b01.b0124) : b01.b0124 != null) return false;
        if (b0127 != null ? !b0127.equals(b01.b0127) : b01.b0127 != null) return false;
        if (b0127A != null ? !b0127A.equals(b01.b0127A) : b01.b0127A != null) return false;
        if (b0131 != null ? !b0131.equals(b01.b0131) : b01.b0131 != null) return false;
        if (b0131A != null ? !b0131A.equals(b01.b0131A) : b01.b0131A != null) return false;
        if (b0134 != null ? !b0134.equals(b01.b0134) : b01.b0134 != null) return false;
        if (b0137 != null ? !b0137.equals(b01.b0137) : b01.b0137 != null) return false;
        if (b0144A != null ? !b0144A.equals(b01.b0144A) : b01.b0144A != null) return false;
        if (b0144B != null ? !b0144B.equals(b01.b0144B) : b01.b0144B != null) return false;
        if (b0151A != null ? !b0151A.equals(b01.b0151A) : b01.b0151A != null) return false;
        if (b0151B != null ? !b0151B.equals(b01.b0151B) : b01.b0151B != null) return false;
        if (b0154A != null ? !b0154A.equals(b01.b0154A) : b01.b0154A != null) return false;
        if (b0154B != null ? !b0154B.equals(b01.b0154B) : b01.b0154B != null) return false;
        if (b0157A != null ? !b0157A.equals(b01.b0157A) : b01.b0157A != null) return false;
        if (b0157B != null ? !b0157B.equals(b01.b0157B) : b01.b0157B != null) return false;
        if (b0161 != null ? !b0161.equals(b01.b0161) : b01.b0161 != null) return false;
        if (b0164 != null ? !b0164.equals(b01.b0164) : b01.b0164 != null) return false;
        if (b0167 != null ? !b0167.equals(b01.b0167) : b01.b0167 != null) return false;
        if (b0171A != null ? !b0171A.equals(b01.b0171A) : b01.b0171A != null) return false;
        if (b0171B != null ? !b0171B.equals(b01.b0171B) : b01.b0171B != null) return false;
        if (b0174 != null ? !b0174.equals(b01.b0174) : b01.b0174 != null) return false;
        if (b0177 != null ? !b0177.equals(b01.b0177) : b01.b0177 != null) return false;
        if (b0181 != null ? !b0181.equals(b01.b0181) : b01.b0181 != null) return false;
        if (b0184 != null ? !b0184.equals(b01.b0184) : b01.b0184 != null) return false;
        if (b0187A != null ? !b0187A.equals(b01.b0187A) : b01.b0187A != null) return false;
        if (b0187B != null ? !b0187B.equals(b01.b0187B) : b01.b0187B != null) return false;
        if (b0191 != null ? !b0191.equals(b01.b0191) : b01.b0191 != null) return false;
        if (b0194 != null ? !b0194.equals(b01.b0194) : b01.b0194 != null) return false;
        if (b0197 != null ? !b0197.equals(b01.b0197) : b01.b0197 != null) return false;
        if (bDwztB != null ? !bDwztB.equals(b01.bDwztB) : b01.bDwztB != null) return false;
        if (bDwztA != null ? !bDwztA.equals(b01.bDwztA) : b01.bDwztA != null) return false;
        if (bSfls != null ? !bSfls.equals(b01.bSfls) : b01.bSfls != null) return false;
        if (bGllbB != null ? !bGllbB.equals(b01.bGllbB) : b01.bGllbB != null) return false;
        if (bGllbA != null ? !bGllbA.equals(b01.bGllbA) : b01.bGllbA != null) return false;
        if (bSftjzs != null ? !bSftjzs.equals(b01.bSftjzs) : b01.bSftjzs != null) return false;
        if (bZgzzjbB != null ? !bZgzzjbB.equals(b01.bZgzzjbB) : b01.bZgzzjbB != null) return false;
        if (bZgzzjbA != null ? !bZgzzjbA.equals(b01.bZgzzjbA) : b01.bZgzzjbA != null) return false;
        if (bZgfzjbB != null ? !bZgfzjbB.equals(b01.bZgfzjbB) : b01.bZgfzjbB != null) return false;
        if (bZgfzjbA != null ? !bZgfzjbA.equals(b01.bZgfzjbA) : b01.bZgfzjbA != null) return false;
        if (bPx != null ? !bPx.equals(b01.bPx) : b01.bPx != null) return false;
        if (bCxbm != null ? !bCxbm.equals(b01.bCxbm) : b01.bCxbm != null) return false;
        if (bSjlx != null ? !bSjlx.equals(b01.bSjlx) : b01.bSjlx != null) return false;
        if (bSfgwytj != null ? !bSfgwytj.equals(b01.bSfgwytj) : b01.bSfgwytj != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = b0100 != null ? b0100.hashCode() : 0;
        result = 31 * result + (b0101 != null ? b0101.hashCode() : 0);
        result = 31 * result + (b0104 != null ? b0104.hashCode() : 0);
        result = 31 * result + (b0107 != null ? b0107.hashCode() : 0);
        result = 31 * result + (b0111 != null ? b0111.hashCode() : 0);
        result = 31 * result + (b0114 != null ? b0114.hashCode() : 0);
        result = 31 * result + (b0117 != null ? b0117.hashCode() : 0);
        result = 31 * result + (b0124 != null ? b0124.hashCode() : 0);
        result = 31 * result + (b0127 != null ? b0127.hashCode() : 0);
        result = 31 * result + (b0127A != null ? b0127A.hashCode() : 0);
        result = 31 * result + (b0131 != null ? b0131.hashCode() : 0);
        result = 31 * result + (b0131A != null ? b0131A.hashCode() : 0);
        result = 31 * result + (b0134 != null ? b0134.hashCode() : 0);
        result = 31 * result + (b0137 != null ? b0137.hashCode() : 0);
        result = 31 * result + (b0144A != null ? b0144A.hashCode() : 0);
        result = 31 * result + (b0144B != null ? b0144B.hashCode() : 0);
        result = 31 * result + (b0151A != null ? b0151A.hashCode() : 0);
        result = 31 * result + (b0151B != null ? b0151B.hashCode() : 0);
        result = 31 * result + (b0154A != null ? b0154A.hashCode() : 0);
        result = 31 * result + (b0154B != null ? b0154B.hashCode() : 0);
        result = 31 * result + (b0157A != null ? b0157A.hashCode() : 0);
        result = 31 * result + (b0157B != null ? b0157B.hashCode() : 0);
        result = 31 * result + (b0161 != null ? b0161.hashCode() : 0);
        result = 31 * result + (b0164 != null ? b0164.hashCode() : 0);
        result = 31 * result + (b0167 != null ? b0167.hashCode() : 0);
        result = 31 * result + (b0171A != null ? b0171A.hashCode() : 0);
        result = 31 * result + (b0171B != null ? b0171B.hashCode() : 0);
        result = 31 * result + (b0174 != null ? b0174.hashCode() : 0);
        result = 31 * result + (b0177 != null ? b0177.hashCode() : 0);
        result = 31 * result + (b0181 != null ? b0181.hashCode() : 0);
        result = 31 * result + (b0184 != null ? b0184.hashCode() : 0);
        result = 31 * result + (b0187A != null ? b0187A.hashCode() : 0);
        result = 31 * result + (b0187B != null ? b0187B.hashCode() : 0);
        result = 31 * result + (b0191 != null ? b0191.hashCode() : 0);
        result = 31 * result + (b0194 != null ? b0194.hashCode() : 0);
        result = 31 * result + (b0197 != null ? b0197.hashCode() : 0);
        result = 31 * result + (bDwztB != null ? bDwztB.hashCode() : 0);
        result = 31 * result + (bDwztA != null ? bDwztA.hashCode() : 0);
        result = 31 * result + (bSfls != null ? bSfls.hashCode() : 0);
        result = 31 * result + (bGllbB != null ? bGllbB.hashCode() : 0);
        result = 31 * result + (bGllbA != null ? bGllbA.hashCode() : 0);
        result = 31 * result + (bSftjzs != null ? bSftjzs.hashCode() : 0);
        result = 31 * result + (bZgzzjbB != null ? bZgzzjbB.hashCode() : 0);
        result = 31 * result + (bZgzzjbA != null ? bZgzzjbA.hashCode() : 0);
        result = 31 * result + (bZgfzjbB != null ? bZgfzjbB.hashCode() : 0);
        result = 31 * result + (bZgfzjbA != null ? bZgfzjbA.hashCode() : 0);
        result = 31 * result + (bPx != null ? bPx.hashCode() : 0);
        result = 31 * result + (bCxbm != null ? bCxbm.hashCode() : 0);
        result = 31 * result + (bSjlx != null ? bSjlx.hashCode() : 0);
        result = 31 * result + (bSfgwytj != null ? bSfgwytj.hashCode() : 0);
        return result;
    }
}
