package com.hisun.saas.zzb.dzda.e01z9.entity;

import com.hisun.saas.sys.tenant.base.entity.TenantEntity;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "e01z9")
public class E01Z9 extends TenantEntity implements Serializable {
    /** 借阅档案主键 */
    private String id;

    /** 档案id */
    private A38 a38;

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

    /** 借阅状态（0：申请借阅 1：未归还 2：已归还 3: 拒绝借阅） */
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

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "a38_id")
    public A38 getA38() {
        return a38;
    }

    public void setA38(A38 a38) {
        this.a38 = a38;
    }

    @Basic
    @Column(name = "a0101", nullable = true, length = 100)
    public String getA0101() {
        return a0101;
    }

    public void setA0101(String a0101) {
        this.a0101 = a0101;
    }

    @Basic
    @Column(name = "e01z9_damc", nullable = true, length = 100)
    public String getE01Z9Damc() {
        return e01Z9Damc;
    }

    public void setE01Z9Damc(String e01Z9Damc) {
        this.e01Z9Damc = e01Z9Damc;
    }

    @Basic
    @Column(name = "e01z901", nullable = true, length = 8)
    public String getE01Z901() {
        return e01Z901;
    }

    public void setE01Z901(String e01Z901) {
        this.e01Z901 = e01Z901;
    }

    @Basic
    @Column(name = "e01z904b", nullable = true, length = 128)
    public String getE01Z904B() {
        return e01Z904B;
    }

    public void setE01Z904B(String e01Z904B) {
        this.e01Z904B = e01Z904B;
    }

    @Basic
    @Column(name = "e01z904a", nullable = true, length = 256)
    public String getE01Z904A() {
        return e01Z904A;
    }

    public void setE01Z904A(String e01Z904A) {
        this.e01Z904A = e01Z904A;
    }

    @Basic
    @Column(name = "e01z907", nullable = true, length = 100)
    public String getE01Z907() {
        return e01Z907;
    }

    public void setE01Z907(String e01Z907) {
        this.e01Z907 = e01Z907;
    }

    @Basic
    @Column(name = "e01z911", nullable = true, length = 18)
    public String getE01Z911() {
        return e01Z911;
    }

    public void setE01Z911(String e01Z911) {
        this.e01Z911 = e01Z911;
    }

    @Basic
    @Column(name = "e01z9_jyzt", nullable = true, length = 1)
    public String getE01Z9Jyzt() {
        return e01Z9Jyzt;
    }

    public void setE01Z9Jyzt(String e01Z9Jyzt) {
        this.e01Z9Jyzt = e01Z9Jyzt;
    }

    @Basic
    @Column(name = "e01z914", nullable = true, length = 80)
    public String getE01Z914() {
        return e01Z914;
    }

    public void setE01Z914(String e01Z914) {
        this.e01Z914 = e01Z914;
    }

    @Basic
    @Column(name = "e01z917", nullable = true, length = 36)
    public String getE01Z917() {
        return e01Z917;
    }

    public void setE01Z917(String e01Z917) {
        this.e01Z917 = e01Z917;
    }

    @Basic
    @Column(name = "e01z927", nullable = true, length = 8)
    public String getE01Z927() {
        return e01Z927;
    }

    public void setE01Z927(String e01Z927) {
        this.e01Z927 = e01Z927;
    }

    @Basic
    @Column(name = "e01z931", nullable = true, length = 100)
    public String getE01Z931() {
        return e01Z931;
    }

    public void setE01Z931(String e01Z931) {
        this.e01Z931 = e01Z931;
    }

    @Basic
    @Column(name = "e01z934", nullable = true, length = 100)
    public String getE01Z934() {
        return e01Z934;
    }

    public void setE01Z934(String e01Z934) {
        this.e01Z934 = e01Z934;
    }

    @Basic
    @Column(name = "e01z941", nullable = true, length = 368)
    public String getE01Z941() {
        return e01Z941;
    }

    public void setE01Z941(String e01Z941) {
        this.e01Z941 = e01Z941;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        E01Z9 that = (E01Z9) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(a38, that.a38) &&
                Objects.equals(a0101, that.a0101) &&
                Objects.equals(e01Z9Damc, that.e01Z9Damc) &&
                Objects.equals(e01Z901, that.e01Z901) &&
                Objects.equals(e01Z904B, that.e01Z904B) &&
                Objects.equals(e01Z904A, that.e01Z904A) &&
                Objects.equals(e01Z907, that.e01Z907) &&
                Objects.equals(e01Z911, that.e01Z911) &&
                Objects.equals(e01Z9Jyzt, that.e01Z9Jyzt) &&
                Objects.equals(e01Z914, that.e01Z914) &&
                Objects.equals(e01Z917, that.e01Z917) &&
                Objects.equals(e01Z927, that.e01Z927) &&
                Objects.equals(e01Z931, that.e01Z931) &&
                Objects.equals(e01Z934, that.e01Z934) &&
                Objects.equals(e01Z941, that.e01Z941);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, a38, a0101, e01Z9Damc, e01Z901, e01Z904B, e01Z904A, e01Z907, e01Z911, e01Z9Jyzt, e01Z914, e01Z917, e01Z927, e01Z931, e01Z934, e01Z941);
    }
}
