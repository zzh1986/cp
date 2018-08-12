package com.eleven.five.entity;

import java.util.List;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-08-12
 */
public class ElevenRequest {

    /**
     * accountId : 161355
     * clientTime : 1534077219538
     * gameId : GD11X5
     * issue : 20180812070
     * item : ["{\"methodid\":\"XX5011001001\",\"nums\":2,\"rebate\":\"0.00\",\"times\":2,\"money\":8,\"playId\":[],\"mode\":1,\"issueNo\":\"20180812070\",\"codes\":\"02&11\"}"]
     */

    private Integer accountId;
    private Long clientTime;
    private String gameId;
    private String issue;
    private List<String> item;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Long getClientTime() {
        return clientTime;
    }

    public void setClientTime(Long clientTime) {
        this.clientTime = clientTime;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public List<String> getItem() {
        return item;
    }

    public void setItem(List<String> item) {
        this.item = item;
    }
}
