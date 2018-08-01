package com.eleven.five.entity;

import javax.persistence.*;

@Table(name="ten_times")
@Entity
public class TenTimes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "one_ten")
    private Integer oneTen = 0;

    @Column(name = "two_ten")
    private Integer twoTen = 0;

    @Column(name = "three_ten")
    private Integer threeTen = 0;

    @Column(name = "four_ten")
    private Integer fourTen = 0;

    @Column(name = "five_ten")
    private Integer fiveTen = 0;

    @Column(name = "six_ten")
    private Integer sixTen = 0;

    @Column(name = "seven_ten")
    private Integer sevenTen = 0;

    @Column(name = "eight_ten")
    private Integer eightTen = 0;

    @Column(name = "nine_ten")
    private Integer nineTen = 0;

    @Column(name = "ten_ten")
    private Integer tenTen = 0;

    @Column(name = "eleven_ten")
    private Integer elevenTen = 0;

    @Column(name = "period")
    private String period;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOneTen() {
        return oneTen;
    }

    public void setOneTen(Integer oneTen) {
        this.oneTen = oneTen;
    }

    public Integer getTwoTen() {
        return twoTen;
    }

    public void setTwoTen(Integer twoTen) {
        this.twoTen = twoTen;
    }

    public Integer getThreeTen() {
        return threeTen;
    }

    public void setThreeTen(Integer threeTen) {
        this.threeTen = threeTen;
    }

    public Integer getFourTen() {
        return fourTen;
    }

    public void setFourTen(Integer fourTen) {
        this.fourTen = fourTen;
    }

    public Integer getFiveTen() {
        return fiveTen;
    }

    public void setFiveTen(Integer fiveTen) {
        this.fiveTen = fiveTen;
    }

    public Integer getSixTen() {
        return sixTen;
    }

    public void setSixTen(Integer sixTen) {
        this.sixTen = sixTen;
    }

    public Integer getSevenTen() {
        return sevenTen;
    }

    public void setSevenTen(Integer sevenTen) {
        this.sevenTen = sevenTen;
    }

    public Integer getEightTen() {
        return eightTen;
    }

    public void setEightTen(Integer eightTen) {
        this.eightTen = eightTen;
    }

    public Integer getNineTen() {
        return nineTen;
    }

    public void setNineTen(Integer nineTen) {
        this.nineTen = nineTen;
    }

    public Integer getTenTen() {
        return tenTen;
    }

    public void setTenTen(Integer tenTen) {
        this.tenTen = tenTen;
    }

    public Integer getElevenTen() {
        return elevenTen;
    }

    public void setElevenTen(Integer elevenTen) {
        this.elevenTen = elevenTen;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}