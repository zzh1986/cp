package com.eleven.five.entity;

import javax.persistence.*;

/**
 * @author zhaozhihong
 */
@Table(name = "number_group")
@Entity
public class NumberGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ji_group")
    private String jiGroup;

    @Column(name = "ou_group")
    private String ouGroup;

    @Column(name = "zhi_group")
    private String zhiGroup;

    @Column(name = "he_group")
    private String heGroup;

    @Column(name = "da_group")
    private String daGroup;

    @Column(name = "xiao_group")
    private String xiaoGroup;

    @Column(name = "ou_amount")
    private Integer ouAmount;

    @Column(name = "ji_amount")
    private Integer jiAmount;

    @Column(name = "zhi_amount")
    private Integer zhiAmount;

    @Column(name = "he_amount")
    private Integer heAmount;

    @Column(name = "da_amount")
    private Integer daAmount;

    @Column(name = "xiao_amount")
    private Integer xiaoAmount;

    @Column(name = "period")
    private String period;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJiGroup() {
        return jiGroup;
    }

    public void setJiGroup(String jiGroup) {
        this.jiGroup = jiGroup;
    }

    public String getOuGroup() {
        return ouGroup;
    }

    public void setOuGroup(String ouGroup) {
        this.ouGroup = ouGroup;
    }

    public String getZhiGroup() {
        return zhiGroup;
    }

    public void setZhiGroup(String zhiGroup) {
        this.zhiGroup = zhiGroup;
    }

    public String getHeGroup() {
        return heGroup;
    }

    public void setHeGroup(String heGroup) {
        this.heGroup = heGroup;
    }

    public String getDaGroup() {
        return daGroup;
    }

    public void setDaGroup(String daGroup) {
        this.daGroup = daGroup;
    }

    public String getXiaoGroup() {
        return xiaoGroup;
    }

    public void setXiaoGroup(String xiaoGroup) {
        this.xiaoGroup = xiaoGroup;
    }

    public Integer getOuAmount() {
        return ouAmount;
    }

    public void setOuAmount(Integer ouAmount) {
        this.ouAmount = ouAmount;
    }

    public Integer getJiAmount() {
        return jiAmount;
    }

    public void setJiAmount(Integer jiAmount) {
        this.jiAmount = jiAmount;
    }

    public Integer getZhiAmount() {
        return zhiAmount;
    }

    public void setZhiAmount(Integer zhiAmount) {
        this.zhiAmount = zhiAmount;
    }

    public Integer getHeAmount() {
        return heAmount;
    }

    public void setHeAmount(Integer heAmount) {
        this.heAmount = heAmount;
    }

    public Integer getDaAmount() {
        return daAmount;
    }

    public void setDaAmount(Integer daAmount) {
        this.daAmount = daAmount;
    }

    public Integer getXiaoAmount() {
        return xiaoAmount;
    }

    public void setXiaoAmount(Integer xiaoAmount) {
        this.xiaoAmount = xiaoAmount;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
