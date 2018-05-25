package com.hisun.saas.zzb.dzda.a38.vo;

import com.hisun.saas.zzb.dzda.a32.vo.A32Vo;
import com.hisun.saas.zzb.dzda.e01z2.entity.E01Z2;
import com.hisun.saas.zzb.dzda.e01z2.vo.E01z2Vo;
import com.hisun.saas.zzb.dzda.mlcl.vo.E01Z1ExcelVo;

import java.util.List;

/**
 * Created by 60514 on 2018/5/24.
 */
public class A38ExcelVo {
    private A38Vo jbxxA38Vo;
    private A38Vo zwbdA38Vo;
    private List<A32Vo> gzbdList;
    private List<E01z2Vo> e01z2Vos;
    private E01Z1ExcelVo e01Z1ExcelVo;

    public A38Vo getJbxxA38Vo() {
        return jbxxA38Vo;
    }

    public void setJbxxA38Vo(A38Vo jbxxA38Vo) {
        this.jbxxA38Vo = jbxxA38Vo;
    }

    public List<A32Vo> getGzbdList() {
        return gzbdList;
    }

    public void setGzbdList(List<A32Vo> gzbdList) {
        this.gzbdList = gzbdList;
    }

    public A38Vo getZwbdA38Vo() {
        return zwbdA38Vo;
    }

    public void setZwbdA38Vo(A38Vo zwbdA38Vo) {
        this.zwbdA38Vo = zwbdA38Vo;
    }

    public List<E01z2Vo> getE01z2Vos() {
        return e01z2Vos;
    }

    public void setE01z2Vos(List<E01z2Vo> e01z2Vos) {
        this.e01z2Vos = e01z2Vos;
    }

    public E01Z1ExcelVo getE01Z1ExcelVo() {
        return e01Z1ExcelVo;
    }

    public void setE01Z1ExcelVo(E01Z1ExcelVo e01Z1ExcelVo) {
        this.e01Z1ExcelVo = e01Z1ExcelVo;
    }
}
