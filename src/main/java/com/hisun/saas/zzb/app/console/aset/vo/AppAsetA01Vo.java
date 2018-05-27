package com.hisun.saas.zzb.app.console.aset.vo;

import com.hisun.util.DateUtil;
import com.hisun.util.StringUtils;
import org.jboss.aop.metadata.SimpleMetaData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zhouying on 2017/9/23.
 */
public class AppAsetA01Vo {

    private String id;
    private String xm;
    private String xb;
    private String csny;
    private String csnyStr;
    private String nl;
    private String gbrmspbCsnyAndNl;
    private String mz;
    private String zw;
    private String csd;
    private String jg;
    private String jkzk;

    private String cjgzsj;
    private String cjgzsjStr;
    private String rdsj;
    private String rdsjStr;
    private String qrzxl;
    private String qrzxw;
    private String gbrmspbQrzxlxw;
    private String qrzByyx;
    private String qrzZy;
    private String gbrmspbQrzByyxAndZy;
    private String zzxl;
    private String zzxw;
    private String gbrmspbZzxlxw;
    private String zzByyx;
    private String zzZy;
    private String gbrmspbZzByyxAndZy;


    private String zyjszw;
    private String zytc;

    private String xrzjsj;
    private String xrzjsjStr;
    private String xrzwsj;

    private String xrzw;
    private String nrzw;
    private String nmzw;
    private String filepath;
    private String file2ImgPath;
    private String gzjlStr;
    private String jcqkStr;
    private String khjgStr;

    private int a01Px;
    private String zpPath;
    private String dp;

    private String qrzxlxwjzy;
    private String zzxlxwjzy;
    private List<AppAsetA02Vo> appAsetA02Vos;
    private List<String> gzjlStrs;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getCsny() {
        return csny;
    }

    public void setCsny(String csny) {
        this.csny = csny;
    }

    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
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

    public String getJkzk() {
        return jkzk;
    }

    public void setJkzk(String jkzk) {
        this.jkzk = jkzk;
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

    public String getQrzxl() {
        return qrzxl;
    }

    public void setQrzxl(String qrzxl) {
        this.qrzxl = qrzxl;
    }

    public String getQrzxw() {
        return qrzxw;
    }

    public void setQrzxw(String qrzxw) {
        this.qrzxw = qrzxw;
    }

    public String getQrzByyx() {
        return qrzByyx;
    }

    public void setQrzByyx(String qrzByyx) {
        this.qrzByyx = qrzByyx;
    }

    public String getQrzZy() {
        return qrzZy;
    }

    public void setQrzZy(String qrzZy) {
        this.qrzZy = qrzZy;
    }

    public String getZzxl() {
        return zzxl;
    }

    public void setZzxl(String zzxl) {
        this.zzxl = zzxl;
    }

    public String getZzxw() {
        return zzxw;
    }

    public void setZzxw(String zzxw) {
        this.zzxw = zzxw;
    }

    public String getZzByyx() {
        return zzByyx;
    }

    public void setZzByyx(String zzByyx) {
        this.zzByyx = zzByyx;
    }

    public String getZzZy() {
        return zzZy;
    }

    public void setZzZy(String zzZy) {
        this.zzZy = zzZy;
    }

    public String getZyjszw() {
        return zyjszw;
    }

    public void setZyjszw(String zyjszw) {
        this.zyjszw = zyjszw;
    }

    public String getZytc() {
        return zytc;
    }

    public void setZytc(String zytc) {
        this.zytc = zytc;
    }

    public String getXrzjsj() {
        return xrzjsj;
    }

    public void setXrzjsj(String xrzjsj) {
        this.xrzjsj = xrzjsj;
    }

    public String getXrzwsj() {
        return xrzwsj;
    }

    public void setXrzwsj(String xrzwsj) {
        this.xrzwsj = xrzwsj;
    }

    public String getXrzw() {
        return xrzw;
    }

    public void setXrzw(String xrzw) {
        this.xrzw = xrzw;
    }

    public String getNrzw() {
        return nrzw;
    }

    public void setNrzw(String nrzw) {
        this.nrzw = nrzw;
    }

    public String getNmzw() {
        return nmzw;
    }

    public void setNmzw(String nmzw) {
        this.nmzw = nmzw;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFile2ImgPath() {
        return file2ImgPath;
    }

    public void setFile2ImgPath(String file2ImgPath) {
        this.file2ImgPath = file2ImgPath;
    }

    public String getGzjlStr() {
        return gzjlStr;
    }

    public void setGzjlStr(String gzjlStr) {
        this.gzjlStr = gzjlStr;
    }

    public String getJcqkStr() {
        return jcqkStr;
    }

    public void setJcqkStr(String jcqkStr) {
        this.jcqkStr = jcqkStr;
    }

    public String getKhjgStr() {
        return khjgStr;
    }

    public void setKhjgStr(String khjgStr) {
        this.khjgStr = khjgStr;
    }

    public int getA01Px() {
        return a01Px;
    }

    public void setA01Px(int a01Px) {
        this.a01Px = a01Px;
    }

    public String getZpPath() {
        return zpPath;
    }

    public void setZpPath(String zpPath) {
        this.zpPath = zpPath;
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

    public List<AppAsetA02Vo> getAppAsetA02Vos() {
        return appAsetA02Vos;
    }

    public void setAppAsetA02Vos(List<AppAsetA02Vo> appAsetA02Vos) {
        this.appAsetA02Vos = appAsetA02Vos;
    }

    public List<String> getGzjlStrs() {
        return gzjlStrs;
    }

    public void setGzjlStrs(List<String> gzjlStrs) {
        this.gzjlStrs = gzjlStrs;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getGbrmspbCsnyAndNl() {
        return DateUtil.covertPatternStringToOtherPatternString(this.csny,
                DateUtil.yyyyMM, DateUtil.yyyydotMM) + "\n" + "(" + this.nl + "岁)";
    }

    public void setGbrmspbCsnyAndNl(String gbrmspbCsnyAndNl) {
        this.gbrmspbCsnyAndNl = gbrmspbCsnyAndNl;
    }

    public String getCsnyStr() {
       return  DateUtil.covertPatternStringToOtherPatternString(this.csny,
                DateUtil.yyyyMM, DateUtil.yyyydotMM);
    }

    public void setCsnyStr(String csnyStr) {
        this.csnyStr = csnyStr;
    }

    public String getCjgzsjStr() {
        return DateUtil.covertPatternStringToOtherPatternString(this.cjgzsj,
                DateUtil.yyyyMM, DateUtil.yyyydotMM);
    }

    public void setCjgzsjStr(String cjgzsjStr) {
        this.cjgzsjStr = cjgzsjStr;
    }

    public String getRdsjStr() {
        return DateUtil.covertPatternStringToOtherPatternString(this.rdsj,
                DateUtil.yyyyMM, DateUtil.yyyydotMM);
    }

    public void setRdsjStr(String rdsjStr) {
        this.rdsjStr = rdsjStr;
    }

    public String getGbrmspbQrzxlxw() {

        return StringUtils.trimNull2Empty(this.qrzxl)
                + "\n" + StringUtils.trimNull2Empty(this.qrzxw);
    }

    public void setGbrmspbQrzxlxw(String gbrmspbQrzxlxw) {
        this.gbrmspbQrzxlxw = gbrmspbQrzxlxw;
    }

    public String getGbrmspbQrzByyxAndZy() {
        String s = "";
        if (StringUtils.isEmpty(this.qrzByyx) == false) {
            s += this.qrzByyx;
        }
        if (StringUtils.isEmpty(this.qrzZy) == false) {
            s += this.qrzZy + "专业";
        }
        return s;
    }

    public void setGbrmspbQrzByyxAndZy(String gbrmspbQrzByyxAndZy) {
        this.gbrmspbQrzByyxAndZy = gbrmspbQrzByyxAndZy;
    }

    public String getGbrmspbZzxlxw() {

        return StringUtils.trimNull2Empty(this.zzxl)
                + "\n" + StringUtils.trimNull2Empty(this.zzxw);
    }

    public void setGbrmspbZzxlxw(String gbrmspbZzxlxw) {
        this.gbrmspbZzxlxw = gbrmspbZzxlxw;
    }

    public String getGbrmspbZzByyxAndZy() {
        String s = "";
        if (StringUtils.isEmpty(this.zzByyx) == false) {
            s += this.zzByyx;
        }
        if (StringUtils.isEmpty(this.zzZy) == false) {
            s += this.zzZy + "专业";
        }
        return s;
    }

    public void setGbrmspbZzByyxAndZy(String gbrmspbZzByyxAndZy) {
        this.gbrmspbZzByyxAndZy = gbrmspbZzByyxAndZy;
    }

    public String getXrzjsjStr() {
        return  DateUtil.covertPatternStringToOtherPatternString(this.xrzjsj,
                DateUtil.yyyyMM, DateUtil.yyyydotMM);
    }

    public void setXrzjsjStr(String xrzjsjStr) {
        this.xrzjsjStr = xrzjsjStr;
    }
}
