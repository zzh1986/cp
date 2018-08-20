package com.eleven.five.controller;

import com.eleven.five.service.ElevenService;
import com.eleven.five.task.PayTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElevenController {
    @Autowired
    private ElevenService elevenService;

    @Autowired
    private PayTask payTask;
    @GetMapping("/insert")
    public void insertNumbers(){
           elevenService.insertNumbers();
    }

    @RequestMapping("/chooseOne")
    public void chooseOne(){
        payTask.chooseOne();
    }
}
