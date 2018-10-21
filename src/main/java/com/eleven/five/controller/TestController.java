package com.eleven.five.controller;

import com.eleven.five.service.FourGroupCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-10-21
 */
@RestController
public class TestController {
    @Autowired
    private FourGroupCountService fourGroupCountService;

    @GetMapping("/saveFourGroupCount")
    public void  saveFourGroupCount() throws IOException {
        fourGroupCountService.saveFourGroupCount();
    }
}
