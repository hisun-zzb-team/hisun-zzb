package com.hisun.saas.zzb.dzda.mlcl.vo;

import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.mlcl.entity.EImages;

import java.util.List;

/**
 * @author zhout {605144321@qq.com}
 */
public class E01Z1Vo {
    private String id;//材料主键
//    private A38 a38;//外键，档案基本信息主键
    private String e01Z111;//材料名称
    private String e01Z111Remark;//材料名称备注
    private String e01Z117;//材料制成时间
    //    private Integer e01Z107;//扫描序号（单份材料的扫描序号）
//    private String eCatalogTypeId;//材料类别主键
    private String e01Z101B;//材料类别字典代码
    private String e01Z101A;//材料类别字典内容
    private Integer e01Z114;//材料页数
    private Integer smys;//扫描页数
    private Integer e01Z124;//材料份数
    private String e01Z207;//接收人姓名
    private String e01Z204;//材料来处
    private String e01Z201;//材料接收时间
    private String e01Z231;//备注
    private Integer yjztps=0;//已加载图片数
    private Integer e01Z104;//排序号

    private String year;//用做excel导入时获取年份
    private String month;//用做excel导入时获取月份
    private String day;//用做excel导入时获取日期

    private String parentId;
    private String parentName;
    private Integer row;

    private String a0101;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public A38 getA38() {
//        return a38;
//    }
//
//    public void setA38(A38 a38) {
//        this.a38 = a38;
//    }

//    public String geteCatalogTypeId() {
//        return eCatalogTypeId;
//    }
//
//    public void seteCatalogTypeId(String eCatalogTypeId) {
//        this.eCatalogTypeId = eCatalogTypeId;
//    }

    public String getE01Z111() {
        return e01Z111;
    }

    public void setE01Z111(String e01Z111) {
        this.e01Z111 = e01Z111;
    }

    public String getE01Z111Remark() {
        return e01Z111Remark;
    }

    public void setE01Z111Remark(String e01Z111Remark) {
        this.e01Z111Remark = e01Z111Remark;
    }

    public String getE01Z117() {
        return e01Z117;
    }

    public void setE01Z117(String e01Z117) {
        this.e01Z117 = e01Z117;
    }

//    public Integer getE01Z107() {
//        return e01Z107;
//    }
//
//    public void setE01Z107(Integer e01Z107) {
//        this.e01Z107 = e01Z107;
//    }

    public String getE01Z101B() {
        return e01Z101B;
    }

    public void setE01Z101B(String e01Z101B) {
        this.e01Z101B = e01Z101B;
    }

    public String getE01Z101A() {
        return e01Z101A;
    }

    public void setE01Z101A(String e01Z101A) {
        this.e01Z101A = e01Z101A;
    }

    public Integer getE01Z114() {
        return e01Z114;
    }

    public void setE01Z114(Integer e01Z114) {
        this.e01Z114 = e01Z114;
    }

    public Integer getSmys() {
        return smys;
    }

    public void setSmys(Integer smys) {
        this.smys = smys;
    }

    public Integer getE01Z124() {
        return e01Z124;
    }

    public void setE01Z124(Integer e01Z124) {
        this.e01Z124 = e01Z124;
    }

    public String getE01Z207() {
        return e01Z207;
    }

    public void setE01Z207(String e01Z207) {
        this.e01Z207 = e01Z207;
    }

    public String getE01Z204() {
        return e01Z204;
    }

    public void setE01Z204(String e01Z204) {
        this.e01Z204 = e01Z204;
    }

    public String getE01Z201() {
        return e01Z201;
    }

    public void setE01Z201(String e01Z201) {
        this.e01Z201 = e01Z201;
    }

    public String getE01Z231() {
        return e01Z231;
    }

    public void setE01Z231(String e01Z231) {
        this.e01Z231 = e01Z231;
    }

    public Integer getYjztps() {
        return yjztps;
    }

    public void setYjztps(Integer yjztps) {
        this.yjztps = yjztps;
    }

    public Integer getE01Z104() {
        return e01Z104;
    }

    public void setE01Z104(Integer e01Z104) {
        this.e01Z104 = e01Z104;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public String getA0101() {
        return a0101;
    }

    public void setA0101(String a0101) {
        this.a0101 = a0101;
    }
}
