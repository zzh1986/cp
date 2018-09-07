package com.eleven.five.controller;

import com.eleven.five.service.TenTongJiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-06-29
 */
@RestController
public class TenTongJiController {

    @Autowired
    private TenTongJiService tenTongJiService;

    private Logger logger = LoggerFactory.getLogger(TenTongJiController.class);

    @GetMapping("/saveTenTongJiNumber")
    public String saveTenTongJiNumber(){
        try {
            logger.info("十次统计开始.....");
            tenTongJiService.saveTenTongJiNumber();
            return "统计结束,数据正常,请到数据库查阅";
        }catch (Exception e){
            logger.error("统计数据异常,请处理异常信息");
            return "统计数据异常,请处理异常信息";
        }
    }

    @GetMapping("/getPercentFromTenDays")
    public String getPercentFromTenDays(){
        return tenTongJiService.getPercentFromTenDays();
    }
}
