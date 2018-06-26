package com.hisun.saas.zzb.dzda.e01z9.vo;

import com.hisun.saas.zzb.dzda.a38.vo.A38Vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class E01Z9Vo {
    /** 借阅档案主键 */
    private String id;

    /** 档案id */
    private A38Vo a38Vo;

    /** 档案名称 */
    private String a0101;

    /** 借阅档案名称 */
    private String e01Z9Damc;

    /** 借阅日期 */
    private String e01Z901;

    /** 借阅单位代码 */
    private String e01Z904B;

    /** 借阅单位名称 */
    private String e01Z904A;

    /** 借阅人 */
    private String e01Z907;

    /** 借阅人电话号码 */
    private String e01Z911;

    /** 借阅状态（0：待审核 1：已审核 2：已归还 3: 已拒绝） */
    private String e01Z9Jyzt;

    /** 借阅理由 */
    private String e01Z914;

    /** 批准人 */
    private String e01Z917;

    /** 归还日期 */
    private String e01Z927;

    /** 借阅经办人 */
    private String e01Z931;

    /** 归还经办人 */
    private String e01Z934;

    /** 备注 */
    private String e01Z941;

    /** 审核时间 */
    private String e01Z9Shsj;

    /** 是否删除 */
    private String isDel;

    /** 应还日期 */
    private String e01z9Yhsj;

    /** 是否逾期 0:未逾期，1:已逾期 */
    private int e01z9Yh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public A38Vo getA38Vo() {
        return a38Vo;
    }

    public void setA38Vo(A38Vo a38Vo) {
        this.a38Vo = a38Vo;
    }

    public String getA0101() {
        return a0101;
    }

    public void setA0101(String a0101) {
        this.a0101 = a0101;
    }

    public String getE01Z9Damc() {
        return e01Z9Damc;
    }

    public void setE01Z9Damc(String e01Z9Damc) {
        this.e01Z9Damc = e01Z9Damc;
    }

    public String getE01Z901() {
        return e01Z901;
    }

    public void setE01Z901(String e01Z901) {
        this.e01Z901 = e01Z901;
    }

    public String getE01Z904B() {
        return e01Z904B;
    }

    public void setE01Z904B(String e01Z904B) {
        this.e01Z904B = e01Z904B;
    }

    public String getE01Z904A() {
        return e01Z904A;
    }

    public void setE01Z904A(String e01Z904A) {
        this.e01Z904A = e01Z904A;
    }

    public String getE01Z907() {
        return e01Z907;
    }

    public void setE01Z907(String e01Z907) {
        this.e01Z907 = e01Z907;
    }

    public String getE01Z911() {
        return e01Z911;
    }

    public void setE01Z911(String e01Z911) {
        this.e01Z911 = e01Z911;
    }

    public String getE01Z9Jyzt() {
        return e01Z9Jyzt;
    }

    public void setE01Z9Jyzt(String e01Z9Jyzt) {
        this.e01Z9Jyzt = e01Z9Jyzt;
    }

    public String getE01Z914() {
        return e01Z914;
    }

    public void setE01Z914(String e01Z914) {
        this.e01Z914 = e01Z914;
    }

    public String getE01Z917() {
        return e01Z917;
    }

    public void setE01Z917(String e01Z917) {
        this.e01Z917 = e01Z917;
    }

    public String getE01Z927() {
        return e01Z927;
    }

    public void setE01Z927(String e01Z927) {
        this.e01Z927 = e01Z927;
    }

    public String getE01Z931() {
        return e01Z931;
    }

    public void setE01Z931(String e01Z931) {
        this.e01Z931 = e01Z931;
    }

    public String getE01Z934() {
        return e01Z934;
    }

    public void setE01Z934(String e01Z934) {
        this.e01Z934 = e01Z934;
    }

    public String getE01Z941() {
        return e01Z941;
    }

    public void setE01Z941(String e01Z941) {
        this.e01Z941 = e01Z941;
    }

    public String getE01Z9Shsj() {
        return e01Z9Shsj;
    }

    public void setE01Z9Shsj(String e01Z9Shsj) {
        this.e01Z9Shsj = e01Z9Shsj;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getE01z9Yhsj() {
        return e01z9Yhsj;
    }

    public void setE01z9Yhsj(String e01z9Yhsj) {
        this.e01z9Yhsj = e01z9Yhsj;
    }

    public int getE01z9Yh() {
        e01z9Yh=0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String nowDate = sdf.format(date);

        int e01z9YhsjNum = Integer.parseInt(e01z9Yhsj);
        int nowDateNum = Integer.parseInt(nowDate);

        if(e01z9YhsjNum<nowDateNum){
            e01z9Yh=1;
        }


        return e01z9Yh;
    }
}
