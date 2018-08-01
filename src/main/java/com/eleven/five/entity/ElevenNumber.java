package com.eleven.five.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="eleven_number")
public class ElevenNumber implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="one_num")
    private Integer oneNum;

    @Column(name="two_num")
    private Integer twoNum;

    @Column(name="three_num")
    private Integer threeNum;

    @Column(name="four_num")
    private Integer fourNum;

    @Column(name="five_num")
    private Integer fiveNum;

    @Column(name="six_num")
    private Integer sixNum;

    @Column(name="seven_num")
    private Integer sevenNum;

    @Column(name="eight_num")
    private Integer eightNum;

    @Column(name="nine_num")
    private Integer nineNum;

    @Column(name="ten_num")
    private Integer tenNum;

    @Column(name="eleven_num")
    private Integer elevenNum;

    @Column(name="sort")
    private String sort;

    @Column(name="period")
    private String period;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOneNum() {
        return oneNum;
    }

    public void setOneNum(Integer oneNum) {
        this.oneNum = oneNum;
    }

    public Integer getTwoNum() {
        return twoNum;
    }

    public void setTwoNum(Integer twoNum) {
        this.twoNum = twoNum;
    }

    public Integer getThreeNum() {
        return threeNum;
    }

    public void setThreeNum(Integer threeNum) {
        this.threeNum = threeNum;
    }

    public Integer getFourNum() {
        return fourNum;
    }

    public void setFourNum(Integer fourNum) {
        this.fourNum = fourNum;
    }

    public Integer getFiveNum() {
        return fiveNum;
    }

    public void setFiveNum(Integer fiveNum) {
        this.fiveNum = fiveNum;
    }

    public Integer getSixNum() {
        return sixNum;
    }

    public void setSixNum(Integer sixNum) {
        this.sixNum = sixNum;
    }

    public Integer getSevenNum() {
        return sevenNum;
    }

    public void setSevenNum(Integer sevenNum) {
        this.sevenNum = sevenNum;
    }

    public Integer getEightNum() {
        return eightNum;
    }

    public void setEightNum(Integer eightNum) {
        this.eightNum = eightNum;
    }

    public Integer getNineNum() {
        return nineNum;
    }

    public void setNineNum(Integer nineNum) {
        this.nineNum = nineNum;
    }

    public Integer getTenNum() {
        return tenNum;
    }

    public void setTenNum(Integer tenNum) {
        this.tenNum = tenNum;
    }

    public Integer getElevenNum() {
        return elevenNum;
    }

    public void setElevenNum(Integer elevenNum) {
        this.elevenNum = elevenNum;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}