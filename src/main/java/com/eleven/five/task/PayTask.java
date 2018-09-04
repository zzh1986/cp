package com.eleven.five.task;

import com.eleven.five.controller.ElevenController;
import com.eleven.five.controller.MaxSimularController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

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
}
