package com.eleven.five.controller;

import com.eleven.five.entity.HistoryFiveNumbers;
import com.eleven.five.service.NotAppareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author zhaozhihong
 */
@RestController
public class NotAppareController {

    @Autowired
    private NotAppareService notAppareService;

    @GetMapping("/getLongTimeNotexitNumber")
    public HistoryFiveNumbers getLongTimeNotexitNumber(String days) throws IOException {
        if(StringUtils.isEmpty(days)) return null;
        return notAppareService.getLongTimeNotexitNumber(Integer.valueOf(days));
    }

}
