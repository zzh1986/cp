package com.eleven.five.task;

import com.eleven.five.controller.ElevenController;
import com.eleven.five.controller.MaxSimularController;
import com.eleven.five.entity.FourGroupCount;
import com.eleven.five.service.FourGroupCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-08-13
 */
@Component
@EnableScheduling
public class PayTask {

    @Autowired
    private ElevenController elevenController;

    @Autowired
    private MaxSimularController maxSimularController;

    @Autowired
    private FourGroupCountService fourGroupCountService;

//    @Scheduled(cron = "0 5/10 11-23 * * * ")
//    public void chooseOne(){
//        Map<String, Object> map = maxSimularController.chooseTwoNumber();
//        if(null == map){
//            return;
//        }else {
//            String period = (String)map.get("period");
//            List<String> numbers = (List<String>) map.get("numbers");
//            HttpUtils.pay(period, numbers);
//        }
//    }

 /*   @Scheduled(cron = "0 0/30 9 * * * ")
    public void saveHistoryNumber(){
        elevenController.insertNumbers();
    }*/

//    @Scheduled(cron = "*/1 * * * * * ")
//    public void getProperties1(){
//        System.out.println(HttpUtils.nianfen++);
//    }
//
//    @Scheduled(cron = "*/2 * * * * * ")
//    public void getProperties(){
//        System.out.println(HttpUtils.nianfen);
//    }

//    @Scheduled(cron = "0 5/10 11-23 * * * ")
//    public void chooseTarget(){
//        Map<String, Object> map = maxSimularController.chooseTarget();
//        if(null == map){
//            return;
//        }else {
//            String period = (String)map.get("period");
//            List<String> numbers = (List<String>) map.get("numbers");
//            HttpUtils.payTwo(period, numbers);
//        }
//    }

    /**
     * 查询当前余额的定时任务
     */
//    @Scheduled(cron = "0 3/10 11-23 * * * ")
//    public void getUserBanlance() throws IOException {
//        maxSimularController.getUserBanlance();
//    }
    /**
     * 写一个定时器去完成新表的统计
     */
    @Scheduled(cron = "0 3/10 11-23 * * * ")
    public void getUserBanlance() throws IOException {
        fourGroupCountService.saveFourGroupCount();
    }
}
