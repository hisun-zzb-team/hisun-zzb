package com.hisun.saas.zzb.a.vo;

import com.hisun.util.StringUtils;

public class A15Vo {
    private String a1500;

    /** null */
    private A01Vo a01Vo;

    /** 考核类别字典代码:ZB17-2006/KHLB */
    private String a1501;

    /** 考核类别显示内容 */
    private String a1501A;

    /** 考核起始日期 */
    private String a1504;

    /** 考核截止日期 */
    private String a1505;

    /** 考核组织名称 */
    private String a1507;

    /** 考核意见 */
    private String a1511;

    /** 考核人姓名 */
    private String a1514;

    /** 考核结论类别字典代码:ZB18-2006/KHJL */
    private String a1517;

    /** 考核结论类别显示内容 */
    private String a1517A;

    /** 考核年度 */
    private String aKhnd;


    /** 考核时间  列表 */
    private String khsj;

    public String getA1500() {
        return a1500;
    }

    public void setA1500(String a1500) {
        this.a1500 = a1500;
    }

    public A01Vo getA01Vo() {
        return a01Vo;
    }

    public void setA01Vo(A01Vo a01Vo) {
        this.a01Vo = a01Vo;
    }

    public String getA1501() {
        return a1501;
    }

    public void setA1501(String a1501) {
        this.a1501 = a1501;
    }

    public String getA1501A() {
        return a1501A;
    }

    public void setA1501A(String a1501A) {
        this.a1501A = a1501A;
    }

    public String getA1504() {
        return a1504;
    }

    public void setA1504(String a1504) {
        this.a1504 = a1504;
    }

    public String getA1505() {
        return a1505;
    }

    public void setA1505(String a1505) {
        this.a1505 = a1505;
    }

    public String getA1507() {
        return a1507;
    }

    public void setA1507(String a1507) {
        this.a1507 = a1507;
    }

    public String getA1511() {
        return a1511;
    }

    public void setA1511(String a1511) {
        this.a1511 = a1511;
    }

    public String getA1514() {
        return a1514;
    }

    public void setA1514(String a1514) {
        this.a1514 = a1514;
    }

    public String getA1517() {
        return a1517;
    }

    public void setA1517(String a1517) {
        this.a1517 = a1517;
    }

    public String getA1517A() {
        return a1517A;
    }

    public void setA1517A(String a1517A) {
        this.a1517A = a1517A;
    }

    public String getaKhnd() {
        return aKhnd;
    }

    public void setaKhnd(String aKhnd) {
        this.aKhnd = aKhnd;
    }

    public String getKhsj() {
        if("1".equals(a1501)){
            if(StringUtils.isEmpty(aKhnd)){
                aKhnd="";
            }
            khsj=aKhnd;
        }else if("2".equals(a1501)){
            int flag = 0;
            if(StringUtils.isEmpty(a1504)){
                a1504="";
                flag++;
            }
            if(StringUtils.isEmpty(a1505)){
                a1505="";
                flag++;
            }
            khsj = a1504 + (flag==0?"-":"") + a1505;
        }
        return khsj;
    }
}
