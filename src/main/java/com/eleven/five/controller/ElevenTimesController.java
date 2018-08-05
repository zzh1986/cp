package com.eleven.five.controller;

import com.eleven.five.service.ElevenTimesService;
import com.eleven.five.service.ElevenTongJiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-08-05
 */
@RestController
public class ElevenTimesController {

    @Autowired
    private ElevenTimesService elevenTimesService;

    @Autowired
    private ElevenTongJiService elevenTongJiService;

    private Logger logger = LoggerFactory.getLogger(TenTimesController.class);

    @GetMapping("/saveElevenTimes")
    public String saveElevenTimes(String date,String period){

        try {
            logger.info("请求成功,保存数据开始" + date + "------>" + period);
            elevenTimesService.saveElevenTimes(date,period);
            elevenTongJiService.saveElevenTongJiNumber();
            return "数据保存成功";
        } catch (Exception e) {
            logger.error("数据保存异常" + date + "------->" + period);
            return "数据保存异常,请到控制台查看";
        }
    }


}
