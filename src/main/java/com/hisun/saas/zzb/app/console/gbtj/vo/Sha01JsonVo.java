package com.hisun.saas.zzb.app.console.gbtj.vo;

import java.util.List;

/**
 * Created by zhouying on 2017/9/8.
 */
public class Sha01JsonVo {

    private String title;
    private List<Sha01JsonDataVo> data;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Sha01JsonDataVo> getData() {
        return data;
    }

    public void setData(List<Sha01JsonDataVo> data) {
        this.data = data;
    }
}
