package com.eleven.five.controller;

import com.eleven.five.service.ElevenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ElevenController {
    @Autowired
    private ElevenService elevenService;

    @GetMapping("insert")
    public void insertNumbers(){
           elevenService.insertNumbers();
    }
}
