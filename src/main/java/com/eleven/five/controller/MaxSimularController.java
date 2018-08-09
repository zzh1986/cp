package com.eleven.five.controller;

import com.eleven.five.service.MaxSimularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozhihong
 */
@RestController
public class MaxSimularController {

    @Autowired
    private MaxSimularService maxSimularService;

    /**
     * 查询相似度最高的数据的下一期,预测下期出现和不出现的数据返回
     *
     * @return
     */
    @GetMapping("/findMaxSimularNextPeriod")
    public String findMaxSimularNextPeriod() {
        String maxSimularNextPeriod = maxSimularService.findMaxSimularNextPeriod();
        return maxSimularNextPeriod == null ? "存在异常或者无匹配结果" : maxSimularNextPeriod;
    }

    /**
     * 10组数据连着4个相似数据
     * @return
     */
    //@GetMapping("/getFourNumberSimular")
    public String getFourNumberSimular() {
        String getFourNumberSimular = maxSimularService.getFourNumberSimular();
        return getFourNumberSimular ;
    }

    /**
     * 比较的sort相同的下一组数据 出结果
     */
    @GetMapping("/getHopeLastSimular")
    public String getHopeLastSimular() {
        String getHopeLastSimular = maxSimularService.getHopeLastSimular();
        return getHopeLastSimular ;
    }

    @GetMapping("/getTenMaxSimular")
    public String getTenMaxSimular(){
        return maxSimularService.getTenMaxSimular();
    }

    @GetMapping("/getThreeMaxSimular")
    public String getThreeMaxSimular(){
        return maxSimularService.getThreeMaxSimular();
    }

    /**
     * 验证对应的一天的结果 或者几天的结果 百分比结果
     * @param date   格式为: 20180807
     * @return
     */
    @GetMapping("/verification")
    public String getVerification(String date){
        return maxSimularService.getVerification(date);
    }

    @GetMapping("/chooseTwoNumber")
    public String chooseTwoNumber(){
        return maxSimularService.chooseTwoNumber();
    }
}
