package com.eleven.five.controller;

import com.eleven.five.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author zhaozhihong
 */
@RestController
public class GroupController {

     @Autowired
     private GroupService groupService;

     @RequestMapping("/getGroupResult")
     public List<Object[]> getGroupResult(String date, String period) throws IOException {
          return groupService.getGroupResult(date,period);
     }

}
