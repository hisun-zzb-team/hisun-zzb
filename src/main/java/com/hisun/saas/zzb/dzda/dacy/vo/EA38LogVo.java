package com.hisun.saas.zzb.dzda.dacy.vo;

import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.dacy.entity.EA38LogDetail;
import com.hisun.saas.zzb.dzda.dacy.entity.EA38LogViewTime;
import com.hisun.saas.zzb.dzda.dacy.entity.EApplyE01Z8;

import java.util.Date;
import java.util.List;

/**
 * Created by 60514 on 2018/5/25.
 */
public class EA38LogVo {
    private String id;
    private Date cysj;//查阅时间
    private String cyrId;//查阅人id
    private String cyrName;//查阅人姓名
    private String a0101;//档案所属人姓名
    private String readTenantId;//查阅单位id
    private String readTenantName;//查阅单位
    private String viewTime;//查阅时长
    private Integer ydzt;//是否在阅档中 1表示阅档中 0或空表示不在阅档中
    private Date zzcysj;//如果为正在阅档 则需要记录其在阅档的时间 由查阅页面不停写入查阅的时间，用于计算剩余的查阅时长
    private String readContent;//查阅内容

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCysj() {
        return cysj;
    }

    public void setCysj(Date cysj) {
        this.cysj = cysj;
    }

    public String getCyrId() {
        return cyrId;
    }

    public void setCyrId(String cyrId) {
        this.cyrId = cyrId;
    }

    public String getCyrName() {
        return cyrName;
    }

    public void setCyrName(String cyrName) {
        this.cyrName = cyrName;
    }

    public String getA0101() {
        return a0101;
    }

    public void setA0101(String a0101) {
        this.a0101 = a0101;
    }


    public String getReadTenantId() {
        return readTenantId;
    }

    public void setReadTenantId(String readTenantId) {
        this.readTenantId = readTenantId;
    }

    public String getReadTenantName() {
        return readTenantName;
    }

    public void setReadTenantName(String readTenantName) {
        this.readTenantName = readTenantName;
    }

    public String getViewTime() {
        return viewTime;
    }

    public void setViewTime(String viewTime) {
        this.viewTime = viewTime;
    }

    public Integer getYdzt() {
        return ydzt;
    }

    public void setYdzt(Integer ydzt) {
        this.ydzt = ydzt;
    }

    public Date getZzcysj() {
        return zzcysj;
    }

    public void setZzcysj(Date zzcysj) {
        this.zzcysj = zzcysj;
    }

    public String getReadContent() {
        return readContent;
    }

    public void setReadContent(String readContent) {
        this.readContent = readContent;
    }
}
