package com.eleven.five.controller;

import com.eleven.five.service.ElevenNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-06-19
 */
@RestController
public class ElevenNumberController {

    @Autowired
    private ElevenNumberService elevenNumberService;

    @RequestMapping("/saveElevenNumber")
    public String saveElevenNumber(){
        try {
            elevenNumberService.insertIntoElevenNumbers();
            return "保存成功...请到数据库查阅";
        }catch (Exception e){
            e.printStackTrace();
            return "保存失败,请在控制台查阅失败信息..";
        }
    }
    @RequestMapping("/saveThreeTimes")
    public String saveThreeTimes(){
        try {
            elevenNumberService.insertIntoThreeTimes();
            return "保存成功...请到数据库查阅";
        }catch (Exception e){
            e.printStackTrace();
            return "保存失败,请在控制台查阅失败信息..";
        }
    }

}
