package com.eleven.five.entity;

import javax.persistence.*;

/**
 * @author zhaozhihong
 */
@Table(name = "change_numbers")
@Entity
public class ChangeNumbers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 次数为5的号码,且10组之内最远一组的号码内不含该数字 (温转热,考虑出)*/
    @Column(name = "five_times_number")
    private String fiveTimesNumber;

    /** 次数为5的号码,近3期的出现情况 */
    @Column(name = "five_times_happen")
    private String fiveTimesHappen;

    /** 次数为3的号码,且10组之内最远一组的号码内不含该数字 (冷转温,考虑出)*/
    @Column(name = "three_times_number")
    private String threeTimesNumber;

    /** 次数为3的号码,近3期的出现情况 */
    @Column(name = "three_times_happen")
    private String threeTimesHappen;

    /** 次数为4的号码且10组之内最远一组的号码内包含该数字 (温转冷,考虑不出)*/
    @Column(name = "four_times_number")
    private String fourTimesNumber;

    /** 次数为4的号码,近3期的出现情况 */
    @Column(name = "four_times_happen")
    private String fourTimesHappen;

    /** 次数为6的号码且10组内最远的一组的号码含有该数字 (热转温,考虑不出)*/
    @Column(name = "six_times_number")
    private String sixTimesNumber;

    /** 次数为6的号码,近3期的出现情况 */
    @Column(name = "six_times_happen")
    private String sixTimesHappen;

    /**当前期号*/
    @Column(name = "period")
    private String period;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFiveTimesNumber() {
        return fiveTimesNumber;
    }

    public void setFiveTimesNumber(String fiveTimesNumber) {
        this.fiveTimesNumber = fiveTimesNumber;
    }

    public String getFiveTimesHappen() {
        return fiveTimesHappen;
    }

    public void setFiveTimesHappen(String fiveTimesHappen) {
        this.fiveTimesHappen = fiveTimesHappen;
    }

    public String getThreeTimesNumber() {
        return threeTimesNumber;
    }

    public void setThreeTimesNumber(String threeTimesNumber) {
        this.threeTimesNumber = threeTimesNumber;
    }

    public String getThreeTimesHappen() {
        return threeTimesHappen;
    }

    public void setThreeTimesHappen(String threeTimesHappen) {
        this.threeTimesHappen = threeTimesHappen;
    }

    public String getFourTimesNumber() {
        return fourTimesNumber;
    }

    public void setFourTimesNumber(String fourTimesNumber) {
        this.fourTimesNumber = fourTimesNumber;
    }

    public String getFourTimesHappen() {
        return fourTimesHappen;
    }

    public void setFourTimesHappen(String fourTimesHappen) {
        this.fourTimesHappen = fourTimesHappen;
    }

    public String getSixTimesNumber() {
        return sixTimesNumber;
    }

    public void setSixTimesNumber(String sixTimesNumber) {
        this.sixTimesNumber = sixTimesNumber;
    }

    public String getSixTimesHappen() {
        return sixTimesHappen;
    }

    public void setSixTimesHappen(String sixTimesHappen) {
        this.sixTimesHappen = sixTimesHappen;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
