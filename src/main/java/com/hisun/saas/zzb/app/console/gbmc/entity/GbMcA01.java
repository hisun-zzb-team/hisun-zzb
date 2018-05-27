/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbmc.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name = "APP_MC_A01")
public class GbMcA01 extends TenantEntity implements Serializable {

    private String id;


    private GbMcB01 gbMcB01;


    private GbMc gbMc;


    private String xm;


    private String mz;


    private String zw;


    private String csd;


    private String jg;

    private String csny;

    private String cjgzsj;

    private String rdsj;

    private String qrzxlxwjzy;

    private String zzxlxwjzy;

    private String zyjszw;

    private String xrzwsj;

    private String xrzjsj;

    private int px = 0;

    private String zppath;



    private List<GbMcA01gzjl> gbMca01gzjls;


    private List<GbMcA01gbrmspb> gbMca01gbrmspbs;


    private List<GbMcA01shgx> gbMcA01shgxes;
    @Id
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false, unique = true, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "b01_id")
    public GbMcB01 getGbMcB01() {
        return gbMcB01;
    }

    public void setGbMcB01(GbMcB01 gbMcB01) {
        this.gbMcB01 = gbMcB01;
    }
    @Column(name = "xm", length = 10)
    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }
    @Column(name = "mz", length = 10)
    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }
    @Column(name = "zw", length = 255)
    public String getZw() {
        return zw;
    }

    public void setZw(String zw) {
        this.zw = zw;
    }
    @Column(name = "csd", length = 40)
    public String getCsd() {
        return csd;
    }

    public void setCsd(String csd) {
        this.csd = csd;
    }
    @Column(name = "jg", length = 20)
    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }
    @Column(name = "csny", length = 20)
    public String getCsny() {
        return csny;
    }

    public void setCsny(String csny) {
        this.csny = csny;
    }
    @Column(name = "cjgzsj", length = 20)
    public String getCjgzsj() {
        return cjgzsj;
    }

    public void setCjgzsj(String cjgzsj) {
        this.cjgzsj = cjgzsj;
    }
    @Column(name = "rdsj", length = 20)
    public String getRdsj() {
        return rdsj;
    }

    public void setRdsj(String rdsj) {
        this.rdsj = rdsj;
    }
    @Column(name = "qrzxlxwjzy", length = 100)
    public String getQrzxlxwjzy() {
        return qrzxlxwjzy;
    }

    public void setQrzxlxwjzy(String qrzxlxwjzy) {
        this.qrzxlxwjzy = qrzxlxwjzy;
    }
    @Column(name = "zzxlxwjzy", length = 100)
    public String getZzxlxwjzy() {
        return zzxlxwjzy;
    }

    public void setZzxlxwjzy(String zzxlxwjzy) {
        this.zzxlxwjzy = zzxlxwjzy;
    }
    @Column(name = "zyjszw", length = 100)
    public String getZyjszw() {
        return zyjszw;
    }

    public void setZyjszw(String zyjszw) {
        this.zyjszw = zyjszw;
    }
    @Column(name = "xrzwsj", length = 20)
    public String getXrzwsj() {
        return xrzwsj;
    }

    public void setXrzwsj(String xrzwsj) {
        this.xrzwsj = xrzwsj;
    }
    @Column(name = "xrzjsj", length = 40)
    public String getXrzjsj() {
        return xrzjsj;
    }

    public void setXrzjsj(String xrzjsj) {
        this.xrzjsj = xrzjsj;
    }
    @Column(name = "a01_PX")
    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }
    @Column(name = "ZP_PATH", length = 128)
    public String getZppath() {
        return zppath;
    }

    public void setZppath(String zppath) {
        this.zppath = zppath;
    }
    @OneToMany(mappedBy = "gbMcA01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OrderBy("gzjl_px asc")
    public List<GbMcA01gzjl> getGbMca01gzjls() {
        return gbMca01gzjls;
    }

    public void setGbMca01gzjls(List<GbMcA01gzjl> gbMca01gzjls) {
        this.gbMca01gzjls = gbMca01gzjls;
    }
    @OneToMany(mappedBy = "gbMcA01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    public List<GbMcA01gbrmspb> getGbMca01gbrmspbs() {
        return gbMca01gbrmspbs;
    }

    public void setGbMca01gbrmspbs(List<GbMcA01gbrmspb> gbMca01gbrmspbs) {
        this.gbMca01gbrmspbs = gbMca01gbrmspbs;
    }
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "mc_id")
    public GbMc getGbMc() {
        return gbMc;
    }

    public void setGbMc(GbMc gbMc) {
        this.gbMc = gbMc;
    }
    @OneToMany(mappedBy = "gbMcA01", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OrderBy("shgx_px asc")
    public List<GbMcA01shgx> getGbMcA01shgxes() {
        return gbMcA01shgxes;
    }

    public void setGbMcA01shgxes(List<GbMcA01shgx> gbMcA01shgxes) {
        this.gbMcA01shgxes = gbMcA01shgxes;
    }

    public void addGbrmspb(GbMcA01gbrmspb gbMcA01gbrmspb) {
        if (this.gbMca01gbrmspbs == null) {
            this.gbMca01gbrmspbs = new ArrayList<GbMcA01gbrmspb>();
        }
        gbMcA01gbrmspb.setGbMcA01(this);
        this.gbMca01gbrmspbs.add(gbMcA01gbrmspb);
    }

    public void addGzjl(GbMcA01gzjl gbMcA01gzjl) {
        if (this.gbMca01gzjls == null) {
            this.gbMca01gzjls = new ArrayList<GbMcA01gzjl>();
        }
        gbMcA01gzjl.setGbMcA01(this);
        this.gbMca01gzjls.add(gbMcA01gzjl);
    }

    public void addShgx(GbMcA01shgx gbMcA01shgx){
        if(this.gbMcA01shgxes==null){
            this.gbMcA01shgxes = new ArrayList<GbMcA01shgx>();
        }
        gbMcA01shgx.setGbMcA01(this);
        this.gbMcA01shgxes.add(gbMcA01shgx);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GbMcA01 gbMcA01 = (GbMcA01) o;

        if (px != gbMcA01.px) return false;
        if (id != null ? !id.equals(gbMcA01.id) : gbMcA01.id != null) return false;
        if (gbMcB01 != null ? !gbMcB01.equals(gbMcA01.gbMcB01) : gbMcA01.gbMcB01 != null) return false;
        if (gbMc != null ? !gbMc.equals(gbMcA01.gbMc) : gbMcA01.gbMc != null) return false;
        if (xm != null ? !xm.equals(gbMcA01.xm) : gbMcA01.xm != null) return false;
        if (mz != null ? !mz.equals(gbMcA01.mz) : gbMcA01.mz != null) return false;
        if (zw != null ? !zw.equals(gbMcA01.zw) : gbMcA01.zw != null) return false;
        if (csd != null ? !csd.equals(gbMcA01.csd) : gbMcA01.csd != null) return false;
        if (jg != null ? !jg.equals(gbMcA01.jg) : gbMcA01.jg != null) return false;
        if (csny != null ? !csny.equals(gbMcA01.csny) : gbMcA01.csny != null) return false;
        if (cjgzsj != null ? !cjgzsj.equals(gbMcA01.cjgzsj) : gbMcA01.cjgzsj != null) return false;
        if (rdsj != null ? !rdsj.equals(gbMcA01.rdsj) : gbMcA01.rdsj != null) return false;
        if (qrzxlxwjzy != null ? !qrzxlxwjzy.equals(gbMcA01.qrzxlxwjzy) : gbMcA01.qrzxlxwjzy != null) return false;
        if (zzxlxwjzy != null ? !zzxlxwjzy.equals(gbMcA01.zzxlxwjzy) : gbMcA01.zzxlxwjzy != null) return false;
        if (zyjszw != null ? !zyjszw.equals(gbMcA01.zyjszw) : gbMcA01.zyjszw != null) return false;
        if (xrzwsj != null ? !xrzwsj.equals(gbMcA01.xrzwsj) : gbMcA01.xrzwsj != null) return false;
        if (xrzjsj != null ? !xrzjsj.equals(gbMcA01.xrzjsj) : gbMcA01.xrzjsj != null) return false;
        if (zppath != null ? !zppath.equals(gbMcA01.zppath) : gbMcA01.zppath != null) return false;
        if (gbMca01gzjls != null ? !gbMca01gzjls.equals(gbMcA01.gbMca01gzjls) : gbMcA01.gbMca01gzjls != null)
            return false;
        if (gbMca01gbrmspbs != null ? !gbMca01gbrmspbs.equals(gbMcA01.gbMca01gbrmspbs) : gbMcA01.gbMca01gbrmspbs != null)
            return false;
        return gbMcA01shgxes != null ? gbMcA01shgxes.equals(gbMcA01.gbMcA01shgxes) : gbMcA01.gbMcA01shgxes == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (gbMcB01 != null ? gbMcB01.hashCode() : 0);
        result = 31 * result + (gbMc != null ? gbMc.hashCode() : 0);
        result = 31 * result + (xm != null ? xm.hashCode() : 0);
        result = 31 * result + (mz != null ? mz.hashCode() : 0);
        result = 31 * result + (zw != null ? zw.hashCode() : 0);
        result = 31 * result + (csd != null ? csd.hashCode() : 0);
        result = 31 * result + (jg != null ? jg.hashCode() : 0);
        result = 31 * result + (csny != null ? csny.hashCode() : 0);
        result = 31 * result + (cjgzsj != null ? cjgzsj.hashCode() : 0);
        result = 31 * result + (rdsj != null ? rdsj.hashCode() : 0);
        result = 31 * result + (qrzxlxwjzy != null ? qrzxlxwjzy.hashCode() : 0);
        result = 31 * result + (zzxlxwjzy != null ? zzxlxwjzy.hashCode() : 0);
        result = 31 * result + (zyjszw != null ? zyjszw.hashCode() : 0);
        result = 31 * result + (xrzwsj != null ? xrzwsj.hashCode() : 0);
        result = 31 * result + (xrzjsj != null ? xrzjsj.hashCode() : 0);
        result = 31 * result + px;
        result = 31 * result + (zppath != null ? zppath.hashCode() : 0);
        result = 31 * result + (gbMca01gzjls != null ? gbMca01gzjls.hashCode() : 0);
        result = 31 * result + (gbMca01gbrmspbs != null ? gbMca01gbrmspbs.hashCode() : 0);
        result = 31 * result + (gbMcA01shgxes != null ? gbMcA01shgxes.hashCode() : 0);
        return result;
    }
}
