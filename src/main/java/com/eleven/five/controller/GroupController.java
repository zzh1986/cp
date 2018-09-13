package com.eleven.five.controller;

import com.eleven.five.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
     @RequestMapping("/getFiveNumbers")
     public Map<String,Object> getFiveNumbers(String date, String period) throws IOException {
          return groupService.getFiveNumbers(date,period);
     }

     @RequestMapping("/getSixList")
     public List<String> getSixList(String date, String period) throws IOException {
          return groupService.getSixList(date,period);
     }
     @RequestMapping("/getMostFrequently")
     public Object getMostFrequently(String date, String period) throws IOException {
          return groupService.getMostFrequently(date,period);
     }
     @RequestMapping("/getSixLists")
     public Object getSixLists(String date, String period) throws IOException {
          return groupService.getSixLists(date,period);
     }

     @RequestMapping("/getMaxSimularly")
     public Object getMaxSimularly(String date, String period) throws IOException {
          return groupService.getMaxSimularly(date,period);
     }
}
