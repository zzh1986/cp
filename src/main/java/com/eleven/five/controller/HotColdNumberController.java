package com.eleven.five.controller;

import cn.hutool.core.date.DateUtil;
import com.eleven.five.entity.HotColdNumber;
import com.eleven.five.service.HotColdNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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
     */
    /*@GetMapping("/getHotColdNumbers")
    public List<HotColdNumber> getHotColdNumbers(String date, String period) throws IOException {
        return hotColdNumberService.getHotColdNumbers(date,period);
    }*/

    @GetMapping("/getHotColdNumbers")
    public List<HotColdNumber> getHotColdNumbers(Date date) throws IOException {
        String dateStr = DateUtil.format(date,"yyyyMMdd");
        String period = "84";
        return hotColdNumberService.getHotColdNumbers(dateStr,period);
    }
}
