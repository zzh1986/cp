package com.eleven.five.controller;

import com.eleven.five.service.TenNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-06-19
 */
@RestController
public class TenNumberController {

    @Autowired
    private TenNumberService tenNumberService;

    @RequestMapping("/saveTenNumber")
    public String saveTenNumber(){
        try {
            tenNumberService.insertIntoTenNumbers();
            return "保存成功...请到数据库查阅";
        }catch (Exception e){
            e.printStackTrace();
            return "保存失败,请在控制台查阅失败信息..";
        }
    }
    @RequestMapping("/saveThreeTimes")
    public String saveThreeTimes(){
        try {
            tenNumberService.insertIntoThreeTimes();
            return "保存成功...请到数据库查阅";
        }catch (Exception e){
            e.printStackTrace();
            return "保存失败,请在控制台查阅失败信息..";
        }
    }

}
