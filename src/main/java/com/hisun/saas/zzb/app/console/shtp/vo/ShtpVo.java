package com.hisun.saas.zzb.app.console.shtp.vo;

import com.google.common.collect.Lists;
import com.hisun.saas.sys.tenant.tenant.vo.TenantEntityVo;
import com.hisun.saas.zzb.app.console.shpc.vo.ShpcVo;

import java.util.Date;
import java.util.List;

/**
 * Created by zhouying on 2017/9/15.
 */
public class ShtpVo extends TenantEntityVo{

    private String id;
    private ShpcVo shpcVo;
    private String shpcId;
    private String tpq_bh;
    private String tpr_id;
    private String tpr_xm;
    private Date tp_sj;
    private String tp_sj_str;
    private int tpqkCount;

    private List<ShtpsjVo> tpsjs;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTpq_bh() {
        return tpq_bh;
    }

    public void setTpq_bh(String tpq_bh) {
        this.tpq_bh = tpq_bh;
    }

    public String getTpr_id() {
        return tpr_id;
    }

    public void setTpr_id(String tpr_id) {
        this.tpr_id = tpr_id;
    }

    public String getTpr_xm() {
        return tpr_xm;
    }

    public void setTpr_xm(String tpr_xm) {
        this.tpr_xm = tpr_xm;
    }

    public ShpcVo getShpcVo() {
        return shpcVo;
    }

    public void setShpcVo(ShpcVo shpcVo) {
        this.shpcVo = shpcVo;
    }

    public int getTpqkCount() {
        return tpqkCount;
    }

    public void setTpqkCount(int tpqkCount) {
        this.tpqkCount = tpqkCount;
    }

    public String getShpcId() {
        return shpcId;
    }

    public void setShpcId(String shpcId) {
        this.shpcId = shpcId;
    }

    public Date getTp_sj() {
        return tp_sj;
    }

    public void setTp_sj(Date tp_sj) {
        this.tp_sj = tp_sj;
    }

    public String getTp_sj_str() {
        return tp_sj_str;
    }

    public void setTp_sj_str(String tp_sj_str) {
        this.tp_sj_str = tp_sj_str;
    }

    public List<ShtpsjVo> getTpsjs() {
        return tpsjs;
    }

    public void setTpsjs(List<ShtpsjVo> tpsjs) {
        this.tpsjs = tpsjs;
    }


}
