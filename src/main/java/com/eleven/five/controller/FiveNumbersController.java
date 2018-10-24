package com.eleven.five.controller;

import com.eleven.five.service.FiveNumbersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author zhaozhihong
 */
@RestController
public class FiveNumbersController {

    @Autowired
    private FiveNumbersService fiveNumbersService;


    /**
     * 先传参数把过去几年的数据保存的数据库
     */
    @GetMapping("/saveFiveNumbers")
    public String saveFiveNumbers(Integer years) throws IOException {
        return fiveNumbersService.saveFiveNumbers(years);
    }
    /**
     * 查询前三组小于均值 150330 / 990 = 151 条数据的结果
     */
    @GetMapping("/findMinCountThreeNumbers")
    public List<String[]> findMinCountThreeNumbers(){
        return fiveNumbersService.findMinCountThreeNumbers();
    }

    /**
     * 查询 最近days天没出现过的前三个的组合
     */
    @GetMapping("/getSomeDaysNotAppearNumbers")
    public List<String[]> getSomeDaysNotAppearNumbers(Integer days) throws IOException {
        return fiveNumbersService.getSomeDaysNotAppearNumbers(days);
    }
    /**
     * 查询 当前组合的下一期组合中出现频率最高的三位数排列
     */
    @GetMapping("/getNextMaxPercentNumbers")
    public List<String[]> getNextMaxPercentNumbers(String date,String period) throws IOException {
        return fiveNumbersService.getNextMaxPercentNumbers(date,period);
    }

}
