package com.eleven.five.entity;

import java.util.List;

/**为了封装数据使用,可以不与数据库交互
 * @author zhaozhihong
 */

public class HistoryFiveNumbers {

    private List<String[]> fiveList;
    private Integer[] currentMissDays;
    private String[] percent;
    private Integer[] total;

    public Integer[] getTotal() {
        return total;
    }

    public void setTotal(Integer[] total) {
        this.total = total;
    }

    public List<String[]> getFiveList() {
        return fiveList;
    }

    public void setFiveList(List<String[]> fiveList) {
        this.fiveList = fiveList;
    }

    public Integer[] getCurrentMissDays() {
        return currentMissDays;
    }

    public void setCurrentMissDays(Integer[] currentMissDays) {
        this.currentMissDays = currentMissDays;
    }

    public String[] getPercent() {
        return percent;
    }

    public void setPercent(String[] percent) {
        this.percent = percent;
    }
}
