package com.eleven.five.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-09-24
 */
@Table(name = "repeat_times")
@Entity
public class RepeatTimes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="one_times")
    private Integer oneTimes;

    @Column(name="two_times")
    private Integer twoTimes;

    @Column(name="three_times")
    private Integer threeTimes;

    @Column(name="four_times")
    private Integer fourTimes;

    @Column(name="five_times")
    private Integer fiveTimes;

    @Column(name="six_times")
    private Integer sixTimes;

    @Column(name="seven_times")
    private Integer sevenTimes;

    @Column(name="eight_times")
    private Integer eightTimes;

    @Column(name="nine_times")
    private Integer nineTimes;

    @Column(name="ten_times")
    private Integer tenTimes;

    @Column(name="period")
    private String period;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOneTimes() {
        return oneTimes;
    }

    public void setOneTimes(Integer oneTimes) {
        this.oneTimes = oneTimes;
    }

    public Integer getTwoTimes() {
        return twoTimes;
    }

    public void setTwoTimes(Integer twoTimes) {
        this.twoTimes = twoTimes;
    }

    public Integer getThreeTimes() {
        return threeTimes;
    }

    public void setThreeTimes(Integer threeTimes) {
        this.threeTimes = threeTimes;
    }

    public Integer getFourTimes() {
        return fourTimes;
    }

    public void setFourTimes(Integer fourTimes) {
        this.fourTimes = fourTimes;
    }

    public Integer getFiveTimes() {
        return fiveTimes;
    }

    public void setFiveTimes(Integer fiveTimes) {
        this.fiveTimes = fiveTimes;
    }

    public Integer getSixTimes() {
        return sixTimes;
    }

    public void setSixTimes(Integer sixTimes) {
        this.sixTimes = sixTimes;
    }

    public Integer getSevenTimes() {
        return sevenTimes;
    }

    public void setSevenTimes(Integer sevenTimes) {
        this.sevenTimes = sevenTimes;
    }

    public Integer getEightTimes() {
        return eightTimes;
    }

    public void setEightTimes(Integer eightTimes) {
        this.eightTimes = eightTimes;
    }

    public Integer getNineTimes() {
        return nineTimes;
    }

    public void setNineTimes(Integer nineTimes) {
        this.nineTimes = nineTimes;
    }

    public Integer getTenTimes() {
        return tenTimes;
    }

    public void setTenTimes(Integer tenTimes) {
        this.tenTimes = tenTimes;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepeatTimes that = (RepeatTimes) o;
        return Objects.equals(oneTimes, that.oneTimes) && Objects.equals(twoTimes, that.twoTimes) && Objects.equals(threeTimes, that.threeTimes) && Objects.equals(fourTimes, that.fourTimes) && Objects.equals(fiveTimes, that.fiveTimes) && Objects.equals(sixTimes, that.sixTimes) && Objects.equals(sevenTimes, that.sevenTimes) && Objects.equals(eightTimes, that.eightTimes) && Objects.equals(nineTimes, that.nineTimes) && Objects.equals(tenTimes, that.tenTimes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(oneTimes, twoTimes, threeTimes, fourTimes, fiveTimes, sixTimes, sevenTimes, eightTimes, nineTimes, tenTimes);
    }
}
