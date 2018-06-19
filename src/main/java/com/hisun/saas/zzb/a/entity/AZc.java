package com.hisun.saas.zzb.a.entity;

import com.hisun.base.entity.TombstoneEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "a_zc")
public class AZc  extends TombstoneEntity implements Serializable {
    /** 技术职称主键 */
    private String id;

    /** 人员基本信息主键 */
    private A01 a01;

    /** 技术职称名称字典代码 */
    private String aZcb;

    /** 技术职称名称 */
    private String aZca;

    /** 取得职称时间 */
    private String aQdsj;

    /** 职称级别字典代码 */
    private String aZcjbb;

    /** 职称级别字典内容 */
    private String aZcjba;

    /** 职称状态字典代码 */
    private String aZczta;

    /** 职称状态字典内容 */
    private String aZcztb;

    /** 职称说明 */
    private String aZcsm;

    /** 授予职称单位 */
    private String aSydw;

    /** 取得资格时间 */
    private String aQdzgsj;

    /** 评选机构 */
    private String aPxjg;

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false, length = 64)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "a_zcb", nullable = true, length = 128)
    public String getaZcb() {
        return aZcb;
    }

    public void setaZcb(String aZcb) {
        this.aZcb = aZcb;
    }

    @Basic
    @Column(name = "a_zca", nullable = true, length = 64)
    public String getaZca() {
        return aZca;
    }

    public void setaZca(String aZca) {
        this.aZca = aZca;
    }

    @Basic
    @Column(name = "a_qdsj", nullable = true, length = 8)
    public String getaQdsj() {
        return aQdsj;
    }

    public void setaQdsj(String aQdsj) {
        this.aQdsj = aQdsj;
    }

    @Basic
    @Column(name = "a_zcjbb", nullable = true, length = 128)
    public String getaZcjbb() {
        return aZcjbb;
    }

    public void setaZcjbb(String aZcjbb) {
        this.aZcjbb = aZcjbb;
    }

    @Basic
    @Column(name = "a_zcjba", nullable = true, length = 256)
    public String getaZcjba() {
        return aZcjba;
    }

    public void setaZcjba(String aZcjba) {
        this.aZcjba = aZcjba;
    }

    @Basic
    @Column(name = "a_zczta", nullable = true, length = 128)
    public String getaZczta() {
        return aZczta;
    }

    public void setaZczta(String aZczta) {
        this.aZczta = aZczta;
    }

    @Basic
    @Column(name = "a_zcztb", nullable = true, length = 256)
    public String getaZcztb() {
        return aZcztb;
    }

    public void setaZcztb(String aZcztb) {
        this.aZcztb = aZcztb;
    }

    @Basic
    @Column(name = "a_zcsm", nullable = true, length = 512)
    public String getaZcsm() {
        return aZcsm;
    }

    public void setaZcsm(String aZcsm) {
        this.aZcsm = aZcsm;
    }

    @Basic
    @Column(name = "a_sydw", nullable = true, length = 256)
    public String getaSydw() {
        return aSydw;
    }

    public void setaSydw(String aSydw) {
        this.aSydw = aSydw;
    }

    @Basic
    @Column(name = "a_qdzgsj", nullable = true, length = 8)
    public String getaQdzgsj() {
        return aQdzgsj;
    }

    public void setaQdzgsj(String aQdzgsj) {
        this.aQdzgsj = aQdzgsj;
    }

    @Basic
    @Column(name = "a_pxjg", nullable = true, length = 256)
    public String getaPxjg() {
        return aPxjg;
    }

    public void setaPxjg(String aPxjg) {
        this.aPxjg = aPxjg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AZc aZc = (AZc) o;
        return Objects.equals(id, aZc.id) &&
                Objects.equals(aZcb, aZc.aZcb) &&
                Objects.equals(aZca, aZc.aZca) &&
                Objects.equals(aQdsj, aZc.aQdsj) &&
                Objects.equals(aZcjbb, aZc.aZcjbb) &&
                Objects.equals(aZcjba, aZc.aZcjba) &&
                Objects.equals(aZczta, aZc.aZczta) &&
                Objects.equals(aZcztb, aZc.aZcztb) &&
                Objects.equals(aZcsm, aZc.aZcsm) &&
                Objects.equals(aSydw, aZc.aSydw) &&
                Objects.equals(aQdzgsj, aZc.aQdzgsj) &&
                Objects.equals(aPxjg, aZc.aPxjg);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, aZcb, aZca, aQdsj, aZcjbb, aZcjba, aZczta, aZcztb, aZcsm, aSydw, aQdzgsj, aPxjg);
    }
}
