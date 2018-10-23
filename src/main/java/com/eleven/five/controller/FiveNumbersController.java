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
}
