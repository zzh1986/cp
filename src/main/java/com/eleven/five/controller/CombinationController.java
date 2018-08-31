package com.eleven.five.controller;

import com.eleven.five.service.CombinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhaozhihong
 */
@RestController
public class CombinationController {

    @Autowired
    private CombinationService combinationService;

    @RequestMapping("/getCombination")
    public List<List<String>> getCombination(){
        return combinationService.getCombination();
    }

}
