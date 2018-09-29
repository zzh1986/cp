package com.eleven.five.controller;

import com.eleven.five.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
     /**
      * 获取重复数据的记录,分别记录到两个表中,
      */
     @RequestMapping("/saveAdjacentNumbers")
     public void saveAdjacentNumbers(String date,String period) throws IOException {
           groupService.saveAdjacentNumbers(date,period);
     }

    /**
     * 统计一天的0,1,2,3,4,5,出现的概率
     */
    @RequestMapping("/getOneDayPercent")
    public String getOneDayPercent(String date) throws IOException {
        return groupService.getOneDayPercent(date);
    }

     /**
      * 统计10期的1-11,出现的次数
      */
     @RequestMapping("/getOneToElevenNumber")
     public Map getOneToElevenNumber(String date,String period) throws IOException {
          return groupService.getOneToElevenNumber(date,period);
     }
    /**
     * 统计10期的1-11,出现的次数
     */
    @RequestMapping("/getMaxPercentFromTenNumber")
    public Map getMaxPercentFromTenNumber(String date,String period) throws IOException {
        return groupService.getMaxPercentFromTenNumber(date,period);
    }

    /**
     * 获取两个胆号
     * @return
     */
    @GetMapping("/getTwoNumbers")
    public List<Object[]> getTwoNumbers(String date,String period) throws IOException {
        return groupService.getTwoNumbers(date,period);
    }

    @GetMapping("/getRepeatTimes")
    public List<Integer> getRepeatTimes(String date,String period) throws IOException {
        return groupService.getRepeatTimes(date,period);
    }

    /**
     *
     */
    @GetMapping("/saveRepeatTimes")
    public String saveRepeatTimes(String date,String period) throws Exception {
        return groupService.saveRepeatTimes(date,period);
    }

    @GetMapping("/getNextExpectNumbers")
    public List<String[]> getNextExpectNumbers(String date,String period) throws Exception{
        return groupService.getNextExpectNumbers(date,period);
    }

    /**
     * 获取一天内出现最多的三位数的组合
     * @param date
     * @param period
     * @return
     */
    @GetMapping("/getOuShuFromTwo")
    public List<String[]> getOuShuFromTwo(String date,String period) throws IOException {
        return groupService.getOuShuFromTwo(date,period);
    }

    /**
     * 获取十期内偶数出现的次数统计 按顺序输出即可[2,4,6,8,10]
     * @param date
     * @param period
     * @return
     */
    @GetMapping("/getOneGroupFromThree")
    public List<String[]> getOneGroupFromThree(String date,String period) throws IOException {
        return groupService.getOneGroupFromThree(date,period);
    }
}
