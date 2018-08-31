package com.eleven.five.controller;

import com.eleven.five.service.TenTimesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozhihong
 */
@RestController
public class MfileController {

    @Autowired
    private TenTimesService tenTimesService;

    @RequestMapping("/saveMFileOneToEleven")
    public String saveMFileOneToEleven(){
        try {
            return tenTimesService.saveMFileOneToEleven();
        }catch (Exception e){
            e.printStackTrace();
            return "文件存储有异常";
        }

    }
    @RequestMapping("/saveMFileRencentTen")
    public String saveMFileRencentTen(){
        try {
            return tenTimesService.saveMFileRencentTen();
        }catch (Exception e){
            e.printStackTrace();
            return "文件存储有异常";
        }

    }
}
