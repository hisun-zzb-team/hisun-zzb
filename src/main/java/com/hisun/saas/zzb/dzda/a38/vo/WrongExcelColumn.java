package com.hisun.saas.zzb.dzda.a38.vo;

/**
 * Created by 60514 on 2018/5/29.
 */
public class WrongExcelColumn {
    private String wrongExcel;//出错表格
    private String lines;//出错行数
    private String reason;//出错原因

    public String getWrongExcel() {
        return wrongExcel;
    }

    public void setWrongExcel(String wrongExcel) {
        this.wrongExcel = wrongExcel;
    }

    public String getLines() {
        return lines;
    }

    public void setLines(String lines) {
        this.lines = lines;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
