package com.eleven.five.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhaozhihong
 */
@Controller
public class IndexController {

    @RequestMapping("/{path}")
    public String index(@PathVariable("path") String path){
        return path;
    }
}
