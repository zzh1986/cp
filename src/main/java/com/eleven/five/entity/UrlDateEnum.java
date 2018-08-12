package com.eleven.five.entity;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-08-05
 */
public enum  UrlDateEnum {
    /**
     * 设置测试当天的时间,默认给个空 ,正确格式 如:"2018-08-05"
     * 保存数据时只用一次即可
     */
    //DATE_ENUM("2018-08-02"),
    DATE_ENUM("2018-08-12"),
    /**
     * 默认url是广东11选5的url
     */
    URL_ENUM("http://caipiao.163.com/award/gd11xuan5/"),
    /**
     * 好运11选5
     */
    URL_GOODLUCK("http://caipiao.163.com/award/hlj11xuan5/"),
    /**
     * 老11选5
     */
    URL_OLD("http://caipiao.163.com/award/jx11xuan5/"),
    /**
     *  重庆11选5
     */
    URL_CHONGQIN("http://caipiao.163.com/award/cq11xuan5/"),
    /**
     *  易乐11选5
     */
    URL_YILE("http://caipiao.163.com/award/zj11xuan5/"),

    /**
     * 360彩票网址
     */
    URL_360("http://caipiao.360.com/award/zj11xuan5/");
    private final String msg;

    UrlDateEnum( String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
