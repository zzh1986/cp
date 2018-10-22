package com.eleven.five.controller;

import com.eleven.five.entity.ChangeNumbers;
import com.eleven.five.service.ChangeNumbersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author zhaozhihong
 */
@RestController
public class ChangeNumbersController {


    @Autowired
    private ChangeNumbersService changeNumbersService;


    @GetMapping("/getChangeNumbers")
    public ChangeNumbers getChangeNumbers() throws IOException {
        return changeNumbersService.getChangeNumbers();
    }
}
