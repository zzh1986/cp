package com.eleven.five.entity;

import javax.persistence.*;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-10-21
 */
@Table(name = "four_group_count")
@Entity
public class FourGroupCount {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    /**第一组的正确百分数*/
    @Column(name = "one_group_percent")
    private String oneGroupPercent;
    /**第一组的最近连续不中次数*/
    @Column(name = "one_group_times")
    private Integer oneGroupTimes;
    /**第一组最后一次正确的期号*/
    @Column(name = "one_group_last_period")
    private String oneGroupLastPeriod;
    /**第二组的正确百分比*/
    @Column(name = "two_group_percent")
    private String twoGroupPercent;
    /**第二组的最近连续不中次数*/
    @Column(name = "two_group_times")
    private Integer twoGroupTimes;
    /**第二组最后一次正确的期号*/
    @Column(name = "two_group_last_period")
    private String twoGroupLastPeriod;
    /**第三组的正确百分比*/
    @Column(name = "three_group_percent")
    private String threeGroupPercent;
    /**第三组的最近连续不中次数*/
    @Column(name = "three_group_times")
    private Integer threeGroupTimes;
    /**第三组最近一次正确的期号*/
    @Column(name = "three_group_last_period")
    private String threeGroupLastPeriod;
    /**第四组正确百分比*/
    @Column(name = "four_group_percent")
    private String fourGroupPercent;
    /**第四组的最近连续不中次数*/
    @Column(name = "four_group_times")
    private Integer fourGroupTimes;
    /**最近一次第四组出现正确的期号*/
    @Column(name = "four_group_last_period")
    private String fourGroupLastPeriod;
    /**当前的期号*/
    @Column(name = "period")
    private String period;

    /**新增字段,获取最大连续不中数*/
    @Column(name = "one_group_max_times")
    private Integer oneGroupMaxTimes;

    @Column(name = "two_group_max_times")
    private Integer twoGroupMaxTimes;

    @Column(name = "three_group_max_times")
    private Integer threeGroupMaxTimes;

    @Column(name = "four_group_max_times")
    private Integer fourGroupMaxTimes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOneGroupPercent() {
        return oneGroupPercent;
    }

    public void setOneGroupPercent(String oneGroupPercent) {
        this.oneGroupPercent = oneGroupPercent;
    }

    public Integer getOneGroupTimes() {
        return oneGroupTimes;
    }

    public void setOneGroupTimes(Integer oneGroupTimes) {
        this.oneGroupTimes = oneGroupTimes;
    }

    public String getOneGroupLastPeriod() {
        return oneGroupLastPeriod;
    }

    public void setOneGroupLastPeriod(String oneGroupLastPeriod) {
        this.oneGroupLastPeriod = oneGroupLastPeriod;
    }

    public String getTwoGroupPercent() {
        return twoGroupPercent;
    }

    public void setTwoGroupPercent(String twoGroupPercent) {
        this.twoGroupPercent = twoGroupPercent;
    }

    public Integer getTwoGroupTimes() {
        return twoGroupTimes;
    }

    public void setTwoGroupTimes(Integer twoGroupTimes) {
        this.twoGroupTimes = twoGroupTimes;
    }

    public String getTwoGroupLastPeriod() {
        return twoGroupLastPeriod;
    }

    public void setTwoGroupLastPeriod(String twoGroupLastPeriod) {
        this.twoGroupLastPeriod = twoGroupLastPeriod;
    }

    public String getThreeGroupPercent() {
        return threeGroupPercent;
    }

    public void setThreeGroupPercent(String threeGroupPercent) {
        this.threeGroupPercent = threeGroupPercent;
    }

    public Integer getThreeGroupTimes() {
        return threeGroupTimes;
    }

    public void setThreeGroupTimes(Integer threeGroupTimes) {
        this.threeGroupTimes = threeGroupTimes;
    }

    public String getThreeGroupLastPeriod() {
        return threeGroupLastPeriod;
    }

    public void setThreeGroupLastPeriod(String threeGroupLastPeriod) {
        this.threeGroupLastPeriod = threeGroupLastPeriod;
    }

    public String getFourGroupPercent() {
        return fourGroupPercent;
    }

    public void setFourGroupPercent(String fourGroupPercent) {
        this.fourGroupPercent = fourGroupPercent;
    }

    public Integer getFourGroupTimes() {
        return fourGroupTimes;
    }

    public void setFourGroupTimes(Integer fourGroupTimes) {
        this.fourGroupTimes = fourGroupTimes;
    }

    public String getFourGroupLastPeriod() {
        return fourGroupLastPeriod;
    }

    public void setFourGroupLastPeriod(String fourGroupLastPeriod) {
        this.fourGroupLastPeriod = fourGroupLastPeriod;
    }

    public String getPeriod() {
        return period;
    }

    public Integer getOneGroupMaxTimes() {
        return oneGroupMaxTimes;
    }

    public void setOneGroupMaxTimes(Integer oneGroupMaxTimes) {
        this.oneGroupMaxTimes = oneGroupMaxTimes;
    }

    public Integer getTwoGroupMaxTimes() {
        return twoGroupMaxTimes;
    }

    public void setTwoGroupMaxTimes(Integer twoGroupMaxTimes) {
        this.twoGroupMaxTimes = twoGroupMaxTimes;
    }

    public Integer getThreeGroupMaxTimes() {
        return threeGroupMaxTimes;
    }

    public void setThreeGroupMaxTimes(Integer threeGroupMaxTimes) {
        this.threeGroupMaxTimes = threeGroupMaxTimes;
    }

    public Integer getFourGroupMaxTimes() {
        return fourGroupMaxTimes;
    }

    public void setFourGroupMaxTimes(Integer fourGroupMaxTimes) {
        this.fourGroupMaxTimes = fourGroupMaxTimes;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
