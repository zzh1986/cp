package com.eleven.five.entity;

import javax.persistence.*;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-09-16
 */
@Table(name = "ten_repeat")
@Entity
public class TenRepeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "repeat_num")
    private Integer repeatNum;

    @Column(name = "award_num")
    private String awardNum;

    @Column(name = "period")
    private String period;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRepeatNum() {
        return repeatNum;
    }

    public void setRepeatNum(Integer repeatNum) {
        this.repeatNum = repeatNum;
    }

    public String getAwardNum() {
        return awardNum;
    }

    public void setAwardNum(String awardNum) {
        this.awardNum = awardNum;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
