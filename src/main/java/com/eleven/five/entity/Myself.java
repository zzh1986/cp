package com.eleven.five.entity;

import java.util.List;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-08-12
 */
public class Myself {

    /**
     * methodid : XX5011001001
     * nums : 2
     * rebate : 0.00
     * times : 2
     * money : 8
     * playId : []
     * mode : 1
     * issueNo : 20180812070
     * codes : 02&11
     */

    private String methodid;
    private Integer nums;
    private String rebate;
    private Integer times;
    private Integer money;
    private Integer mode;
    private String issueNo;
    private String codes;
    private List<?> playId;

    public String getMethodid() {
        return methodid;
    }

    public void setMethodid(String methodid) {
        this.methodid = methodid;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public String getRebate() {
        return rebate;
    }

    public void setRebate(String rebate) {
        this.rebate = rebate;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public String getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(String issueNo) {
        this.issueNo = issueNo;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public List<?> getPlayId() {
        return playId;
    }

    public void setPlayId(List<?> playId) {
        this.playId = playId;
    }
}
