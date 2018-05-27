/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbmc.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.util.ReflectionVoUtil;
import com.hisun.util.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/8.
 */
@Entity
@Table(name = "APP_MC_A01_GBRMSPB")
public class GbMcA01gbrmspb extends TenantEntity implements Serializable{


    private String id;

    private GbMcA01 gbMcA01;

    private String xm;

    private String xb;

    private String csny;

    private String nl;

    private String zppath;

    private String mz;

    private String jg;

    private String csd;



    private String rdsj;

    private String cjgzsj;

    private String jkzk;

    private String zyjszw;

    private String zytc;

    private String xlqrz;

    private String xwqrz;

    private String xlzz;



    private String xwzz;

    private String  qrz_byyx;

    private String zz_byyx;

    private String xrzw;

    private String nrzw;


    private String nmzw;


    private String rmly;

    private String cbdwyj;

    private String spjgyj;

    private String xzjgrmyj;

    private String filepath;

    private String file2imgPath;


    private String gzjlStr;

    private String jcqkStr;

    private String khjgStr;

    public GbMcA01gbrmspb() {

    }

    public GbMcA01gbrmspb(Map<String,String> ywJson) {

        this.xm = ywJson.get("name");
        this.xb = ywJson.get("sex");
        this.csny = ywJson.get("birthday");
        this.mz = ywJson.get("nation");
        this.jg = ywJson.get("nativeplace");
        this.csd = ywJson.get("birthplace");
        this.rdsj = ywJson.get("jointong");
        this.cjgzsj = ywJson.get("workdate");
        this.jkzk = ywJson.get("healthstate");
        this.zyjszw = ywJson.get("technology");
        this.zytc = ywJson.get("speciality");
        this.xlqrz = ywJson.get("degree");
        this.xlzz = ywJson.get("degreein");
        this.qrz_byyx = ywJson.get("school");
        this.zz_byyx = ywJson.get("schoolin");
        this.xrzw = ywJson.get("jobnow");
        this.nrzw = ywJson.get("jobnowin");
        this.nmzw = ywJson.get("jobremove");
        this.rmly = ywJson.get("reason");
        this.cbdwyj = ywJson.get("unit");
        this.spjgyj = ywJson.get("jgyj");
        this.xzjgrmyj = ywJson.get("xzjgyj");
        Object obj = ywJson.get("intro");
        if (obj instanceof String) {
            this.gzjlStr = ywJson.get("intro");
        }else{
            this.gzjlStr="";
        }
        this.jcqkStr = ywJson.get("awardpunish");
        this.khjgStr = ywJson.get("yearcheck");

    }
    @Id
    @GenericGenerator(name="generator",strategy="uuid.hex")
    @GeneratedValue(generator="generator")
    @Column(name="ID",nullable=false,unique=true,length=32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @ManyToOne(optional=true,fetch = FetchType.LAZY)
    @JoinColumn(name="app_mc_a01_id")
    public GbMcA01 getGbMcA01() {
        return gbMcA01;
    }

    public void setGbMcA01(GbMcA01 gbMcA01) {
        this.gbMcA01 = gbMcA01;
    }
    @Column(name = "xm",length = 20)
    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }
    @Column(name = "xb",length = 10)
    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }
    @Column(name = "csny",length = 24)
    public String getCsny() {
        return csny;
    }

    public void setCsny(String csny) {
        this.csny = csny;
    }
    @Column(name = "nl",length = 10)
    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }
    @Column(name = "zp_path",length = 128)
    public String getZppath() {
        return zppath;
    }

    public void setZppath(String zppath) {
        this.zppath = zppath;
    }
    @Column(name = "mz",length = 24)
    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }
    @Column(name = "jg",length = 24)
    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }
    @Column(name = "csd",length = 24)
    public String getCsd() {
        return csd;
    }

    public void setCsd(String csd) {
        this.csd = csd;
    }
    @Column(name = "rdsj",length = 10)
    public String getRdsj() {
        return rdsj;
    }

    public void setRdsj(String rdsj) {
        this.rdsj = rdsj;
    }
    @Column(name = "cjgzsj",length = 10)
    public String getCjgzsj() {
        return cjgzsj;
    }

    public void setCjgzsj(String cjgzsj) {
        this.cjgzsj = cjgzsj;
    }
    @Column(name = "jkzk",length = 24)
    public String getJkzk() {
        return jkzk;
    }

    public void setJkzk(String jkzk) {
        this.jkzk = jkzk;
    }
    @Column(name = "zyjszw",length = 60)
    public String getZyjszw() {
        return zyjszw;
    }

    public void setZyjszw(String zyjszw) {
        this.zyjszw = zyjszw;
    }
    @Column(name = "zytc",length = 60)
    public String getZytc() {
        return zytc;
    }

    public void setZytc(String zytc) {
        this.zytc = zytc;
    }
    @Column(name = "xl_qrz",length = 24)
    public String getXlqrz() {
        return xlqrz;
    }

    public void setXlqrz(String xlqrz) {
        this.xlqrz = xlqrz;
    }
    @Column(name = "xw_qrz",length = 24)
    public String getXwqrz() {
        return xwqrz;
    }

    public void setXwqrz(String xwqrz) {
        this.xwqrz = xwqrz;
    }
    @Column(name = "xl_zz",length = 24)
    public String getXlzz() {
        return xlzz;
    }

    public void setXlzz(String xlzz) {
        this.xlzz = xlzz;
    }
    @Column(name = "xw_zz",length = 24)
    public String getXwzz() {
        return xwzz;
    }

    public void setXwzz(String xwzz) {
        this.xwzz = xwzz;
    }
    @Column(name = "qrz_byyx",length = 128)
    public String getQrz_byyx() {
        return qrz_byyx;
    }

    public void setQrz_byyx(String qrz_byyx) {
        this.qrz_byyx = qrz_byyx;
    }
    @Column(name = "zz_byyx",length = 128)
    public String getZz_byyx() {
        return zz_byyx;
    }

    public void setZz_byyx(String zz_byyx) {
        this.zz_byyx = zz_byyx;
    }

    @Column(name = "xrzw",length = 128)
    public String getXrzw() {
        return xrzw;
    }

    public void setXrzw(String xrzw) {
        this.xrzw = xrzw;
    }
    @Column(name = "nrzw",length = 128)
    public String getNrzw() {
        return nrzw;
    }

    public void setNrzw(String nrzw) {
        this.nrzw = nrzw;
    }
    @Column(name = "rmly",length = 255)
    public String getRmly() {
        return rmly;
    }

    public void setRmly(String rmly) {
        this.rmly = rmly;
    }
    @Column(name = "cbdwyj",length = 255)
    public String getCbdwyj() {
        return cbdwyj;
    }

    public void setCbdwyj(String cbdwyj) {
        this.cbdwyj = cbdwyj;
    }
    @Column(name = "spjgyj",length = 24)
    public String getSpjgyj() {
        return spjgyj;
    }

    public void setSpjgyj(String spjgyj) {
        this.spjgyj = spjgyj;
    }
    @Column(name = "xzjgrmyj",length = 24)
    public String getXzjgrmyj() {
        return xzjgrmyj;
    }

    public void setXzjgrmyj(String xzjgrmyj) {
        this.xzjgrmyj = xzjgrmyj;
    }
    @Column(name = "nmzw",length = 128)
    public String getNmzw() {
        return nmzw;
    }

    public void setNmzw(String nmzw) {
        this.nmzw = nmzw;
    }
    @Column(name = "file_path",length = 128)
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath.replaceAll("\\\\", "\\\\\\\\");
    }

    @Column(name = "file2img_path",length = 128)
    public String getFile2imgPath() {
        return file2imgPath;
    }

    public void setFile2imgPath(String file2imgPath) {
        this.file2imgPath = file2imgPath.replaceAll("\\\\", "\\\\\\\\");
    }
    @Type(type="text")
    @Column(name = "gzjl_str")
    public String getGzjlStr() {
        return gzjlStr;
    }

    public void setGzjlStr(String gzjlStr) {
        this.gzjlStr = gzjlStr;
    }
    @Type(type="text")
    @Column(name = "jcqk_str")
    public String getJcqkStr() {
        return jcqkStr;
    }

    public void setJcqkStr(String jcqkStr) {
        this.jcqkStr = jcqkStr;
    }
    @Type(type="text")
    @Column(name = "khjg_str")
    public String getKhjgStr() {
        return khjgStr;
    }

    public void setKhjgStr(String khjgStr) {
        this.khjgStr = khjgStr;
    }

    public void addGzjl(GbMcA01gzjl gbMcA01gzjl){

    }





    public Map<String,String> toFieldMap(){
        Map<String,String> fieldMap = new HashMap<String,String>();
        fieldMap.put("xm",StringUtils.trimNull2Empty(this.xm));
        fieldMap.put("xb",this.xb);
        fieldMap.put("csny",this.csny);
        fieldMap.put("zp_path",this.zppath);
        fieldMap.put("mz",this.mz);
        fieldMap.put("jg",this.jg);
        fieldMap.put("csd",this.csd);
        fieldMap.put("rdsj",this.rdsj);
        fieldMap.put("cjgzsj",this.cjgzsj);
        fieldMap.put("jkzk",this.jkzk);
        fieldMap.put("zyjszw",this.zyjszw);
        fieldMap.put("zytc",this.zytc);
        fieldMap.put("xl_qrz",this.xlqrz);
        fieldMap.put("xw_qrz",this.xwqrz);
        fieldMap.put("xl_zz",this.xlzz);
        fieldMap.put("xw_zz",this.xwzz);
        fieldMap.put("qrz_byyx",this.qrz_byyx);
        fieldMap.put("zz_byyx",this.zz_byyx);
        fieldMap.put("xrzw",this.xrzw);
        fieldMap.put("nrzw",this.nrzw);
        fieldMap.put("nmzw",this.nmzw);
        fieldMap.put("rmly",this.rmly);
        fieldMap.put("cbdwyj",this.cbdwyj);
        fieldMap.put("spjgyj",this.spjgyj);
        fieldMap.put("xzjgrmyj",this.xzjgrmyj);
        fieldMap.put("gzjl_str",this.gzjlStr);
        fieldMap.put("jcqk_str",this.jcqkStr);
        fieldMap.put("khjg_str",this.khjgStr);
        return fieldMap;
    }

}
