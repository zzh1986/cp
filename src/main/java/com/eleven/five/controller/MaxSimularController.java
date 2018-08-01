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


    @GetMapping("/getFourNumberSimular")
    public String getFourNumberSimular() {
        String getFourNumberSimular = maxSimularService.getFourNumberSimular();
        return getFourNumberSimular ;
    }
}
