package com.eleven.five.controller;

import com.eleven.five.service.HotColdNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author zhaozhihong
 */
@RestController
public class HotColdNumberController {

    @Autowired
    private HotColdNumberService hotColdNumberService;

    /**
     * 获取10期开始到输入期号的热号冷号的统计结果保存到数据库
     * @param date
     * @param period
     */
    @GetMapping("/getHotColdNumbers")
    public void getHotColdNumbers(String date,String period) throws IOException {
        hotColdNumberService.getHotColdNumbers(date,period);
    }
}
