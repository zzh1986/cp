package com.eleven.five.controller;

import com.eleven.five.service.TenTimesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-06-21
 */
@RestController
public class TenTimesController {

    @Autowired
    private TenTimesService tenTimesService;

    @Autowired
    private TenTongJiController tenTongJiController;

    private Logger logger = LoggerFactory.getLogger(TenTimesController.class);

    /**
     *
     * @param date------------> 20180620
     * @param period----------> 10-83
     * @return
     */
    @GetMapping("/saveTenTimes")
    public String saveTenTimes(String date,String period){

        try {
            logger.info("请求成功,保存数据开始" + date + "------>" + period);
            tenTimesService.saveTenTimes(date,period);
            tenTongJiController.saveTenTongJiNumber();
            return "数据保存成功";
        } catch (IOException e) {
            logger.error("数据保存异常" + date + "------->" + period);
            return "数据保存异常,请到控制台查看";
        }
    }

}
