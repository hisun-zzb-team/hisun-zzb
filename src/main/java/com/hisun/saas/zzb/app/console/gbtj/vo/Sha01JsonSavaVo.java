package com.hisun.saas.zzb.app.console.gbtj.vo;

import java.util.List;

/**
 * Created by zhouying on 2017/9/8.
 */
public class Sha01JsonSavaVo {

    private String title;
    private List<Sha01JsonDataVo> jsonDataVos;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Sha01JsonDataVo> getJsonDataVos() {
        return jsonDataVos;
    }

    public void setJsonDataVos(List<Sha01JsonDataVo> jsonDataVos) {
        this.jsonDataVos = jsonDataVos;
    }
}
