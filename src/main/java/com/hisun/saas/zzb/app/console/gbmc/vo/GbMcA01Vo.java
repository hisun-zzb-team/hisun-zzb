/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbmc.vo;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.saas.zzb.app.console.shpc.vo.Sha01gbrmspbVo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouying on 2017/9/8.
 */
public class GbMcA01Vo {
    private String id;

    private GbMcB01Vo gbMcB01Vo;

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
    private List<GbMcA01gzjlVo> gzjlVos;
    private List<GbMcA01gbrmspbVo> gbrmspbVos;
    private int px = 0;
    private boolean isHavagbrmspbFile = false;//是否存在干部任免审批表材料
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GbMcB01Vo getGbMcB01Vo() {
        return gbMcB01Vo;
    }

    public void setGbMcB01Vo(GbMcB01Vo gbMcB01Vo) {
        this.gbMcB01Vo = gbMcB01Vo;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    public String getZw() {
        return zw;
    }

    public void setZw(String zw) {
        this.zw = zw;
    }

    public String getCsd() {
        return csd;
    }

    public void setCsd(String csd) {
        this.csd = csd;
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public String getCsny() {
        return csny;
    }

    public void setCsny(String csny) {
        this.csny = csny;
    }

    public String getCjgzsj() {
        return cjgzsj;
    }

    public void setCjgzsj(String cjgzsj) {
        this.cjgzsj = cjgzsj;
    }

    public String getRdsj() {
        return rdsj;
    }

    public void setRdsj(String rdsj) {
        this.rdsj = rdsj;
    }

    public String getQrzxlxwjzy() {
        return qrzxlxwjzy;
    }

    public void setQrzxlxwjzy(String qrzxlxwjzy) {
        this.qrzxlxwjzy = qrzxlxwjzy;
    }

    public String getZzxlxwjzy() {
        return zzxlxwjzy;
    }

    public void setZzxlxwjzy(String zzxlxwjzy) {
        this.zzxlxwjzy = zzxlxwjzy;
    }

    public String getZyjszw() {
        return zyjszw;
    }

    public void setZyjszw(String zyjszw) {
        this.zyjszw = zyjszw;
    }

    public String getXrzwsj() {
        return xrzwsj;
    }

    public void setXrzwsj(String xrzwsj) {
        this.xrzwsj = xrzwsj;
    }

    public String getXrzjsj() {
        return xrzjsj;
    }

    public void setXrzjsj(String xrzjsj) {
        this.xrzjsj = xrzjsj;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public List<GbMcA01gzjlVo> getGzjlVos() {
        return gzjlVos;
    }

    public void setGzjlVos(List<GbMcA01gzjlVo> gzjlVos) {
        this.gzjlVos = gzjlVos;
    }

    public List<GbMcA01gbrmspbVo> getGbrmspbVos() {
        return gbrmspbVos;
    }

    public void setGbrmspbVos(List<GbMcA01gbrmspbVo> gbrmspbVos) {
        this.gbrmspbVos = gbrmspbVos;
    }

    public boolean isHavagbrmspbFile() {
        return isHavagbrmspbFile;
    }

    public void setHavagbrmspbFile(boolean havagbrmspbFile) {
        isHavagbrmspbFile = havagbrmspbFile;
    }
}
