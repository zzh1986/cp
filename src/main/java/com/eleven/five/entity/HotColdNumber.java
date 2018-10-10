package com.eleven.five.entity;

import javax.persistence.*;

/**
 * @author zhaozhihong
 */
@Entity
@Table(name = "hot_cold_number")
public class HotColdNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "hot_number")
    private String hotNumber;

    @Column(name = "cold_number")
    private String coldNumber;

    @Column(name = "warm_number")
    private String warmNumber;

    @Column(name = "hot_right")
    private Integer hotRight;

    @Column(name = "cold_right")
    private Integer coldRight;

    @Column(name = "warm_right")
    private Integer warmRight;

    @Column(name = "hot_code")
    private String hotCode;

    @Column(name = "cold_code")
    private String coldCode;

    @Column(name = "warm_code")
    private String warmCode;

    @Column(name = "hot_reserve")
    private String hotReserve;

    @Column(name = "warm_reserve")
    private String warmReserve;

    @Column(name = "next_award")
    private String nextAward;

    @Column(name = "two_number")
    private String twoNumber;

    @Column(name = "another_two")
    private String anotherTwo;

    @Column(name = "period")
    private String period;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotNumber() {
        return hotNumber;
    }

    public void setHotNumber(String hotNumber) {
        this.hotNumber = hotNumber;
    }

    public String getColdNumber() {
        return coldNumber;
    }

    public void setColdNumber(String coldNumber) {
        this.coldNumber = coldNumber;
    }

    public String getWarmNumber() {
        return warmNumber;
    }

    public void setWarmNumber(String warmNumber) {
        this.warmNumber = warmNumber;
    }

    public Integer getHotRight() {
        return hotRight;
    }

    public void setHotRight(Integer hotRight) {
        this.hotRight = hotRight;
    }

    public Integer getColdRight() {
        return coldRight;
    }

    public void setColdRight(Integer coldRight) {
        this.coldRight = coldRight;
    }

    public Integer getWarmRight() {
        return warmRight;
    }

    public void setWarmRight(Integer warmRight) {
        this.warmRight = warmRight;
    }

    public String getHotCode() {
        return hotCode;
    }

    public void setHotCode(String hotCode) {
        this.hotCode = hotCode;
    }

    public String getColdCode() {
        return coldCode;
    }

    public void setColdCode(String coldCode) {
        this.coldCode = coldCode;
    }

    public String getWarmCode() {
        return warmCode;
    }

    public void setWarmCode(String warmCode) {
        this.warmCode = warmCode;
    }

    public String getHotReserve() {
        return hotReserve;
    }

    public void setHotReserve(String hotReserve) {
        this.hotReserve = hotReserve;
    }

    public String getWarmReserve() {
        return warmReserve;
    }

    public void setWarmReserve(String warmReserve) {
        this.warmReserve = warmReserve;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getNextAward() {
        return nextAward;
    }

    public void setNextAward(String nextAward) {
        this.nextAward = nextAward;
    }

    public String getTwoNumber() {
        return twoNumber;
    }

    public void setTwoNumber(String twoNumber) {
        this.twoNumber = twoNumber;
    }

    public String getAnotherTwo() {
        return anotherTwo;
    }

    public void setAnotherTwo(String anotherTwo) {
        this.anotherTwo = anotherTwo;
    }
}
