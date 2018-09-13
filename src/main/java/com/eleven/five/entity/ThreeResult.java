package com.eleven.five.entity;

import javax.persistence.*;

/**
 * @author zhaozhihong
 */
@Table(name="three_result")
@Entity
public class ThreeResult {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 0与1的总和
     */
    @Column(name = "zero_one")
    private Integer zeroOne;
    /**
     * 0与1的正确个数
     */
    @Column(name = "one_total")
    private Integer oneTotal;
    /**
     * 0与2的总和
     */
    @Column(name = "zero_two")
    private Integer zeroTwo;
    /**
     * 0与2的正确个数
     */
    @Column(name = "two_total")
    private Integer twoTotal;
    /**
     * 0与3的总和
     */
    @Column(name = "zero_three")
    private Integer zeroThree;
    /**
     * 0与3的正确个数
     */
    @Column(name = "three_total")
    private Integer threeTotal;
    /**
     * 1与2的总和
     */
    @Column(name = "one_two")
    private Integer oneTwo;
    /**
     * 1与2的正确个数
     */
    @Column(name = "four_total")
    private Integer fourTotal;
    /**
     * 1与3的总和
     */
    @Column(name = "one_three")
    private Integer oneThree;
    /**
     * 1与3的正确个数
     */
    @Column(name = "five_total")
    private Integer fiveTotal;
    /**
     * 2与3的总和
     */
    @Column(name = "two_three")
    private Integer twoThree;
    /**
     * 2与3的正确个数
     */
    @Column(name = "six_total")
    private Integer sixTotal;

    @Column(name = "period")
    private String period;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getZeroOne() {
        return zeroOne;
    }

    public void setZeroOne(Integer zeroOne) {
        this.zeroOne = zeroOne;
    }

    public Integer getOneTotal() {
        return oneTotal;
    }

    public void setOneTotal(Integer oneTotal) {
        this.oneTotal = oneTotal;
    }

    public Integer getZeroTwo() {
        return zeroTwo;
    }

    public void setZeroTwo(Integer zeroTwo) {
        this.zeroTwo = zeroTwo;
    }

    public Integer getTwoTotal() {
        return twoTotal;
    }

    public void setTwoTotal(Integer twoTotal) {
        this.twoTotal = twoTotal;
    }

    public Integer getZeroThree() {
        return zeroThree;
    }

    public void setZeroThree(Integer zeroThree) {
        this.zeroThree = zeroThree;
    }

    public Integer getThreeTotal() {
        return threeTotal;
    }

    public void setThreeTotal(Integer threeTotal) {
        this.threeTotal = threeTotal;
    }

    public Integer getOneTwo() {
        return oneTwo;
    }

    public void setOneTwo(Integer oneTwo) {
        this.oneTwo = oneTwo;
    }

    public Integer getFourTotal() {
        return fourTotal;
    }

    public void setFourTotal(Integer fourTotal) {
        this.fourTotal = fourTotal;
    }

    public Integer getOneThree() {
        return oneThree;
    }

    public void setOneThree(Integer oneThree) {
        this.oneThree = oneThree;
    }

    public Integer getFiveTotal() {
        return fiveTotal;
    }

    public void setFiveTotal(Integer fiveTotal) {
        this.fiveTotal = fiveTotal;
    }

    public Integer getTwoThree() {
        return twoThree;
    }

    public void setTwoThree(Integer twoThree) {
        this.twoThree = twoThree;
    }

    public Integer getSixTotal() {
        return sixTotal;
    }

    public void setSixTotal(Integer sixTotal) {
        this.sixTotal = sixTotal;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
