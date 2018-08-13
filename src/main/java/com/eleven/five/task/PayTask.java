package com.eleven.five.task;

import cn.hutool.core.date.DateUtil;
import com.eleven.five.controller.ElevenController;
import com.eleven.five.controller.MaxSimularController;
import com.eleven.five.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Scheduled(cron = "0 4/10 11-23 * * * ")
    public void chooseOne(){
        Map<String, Object> map = maxSimularController.chooseTwoNumber();
        if(null == map){
            return;
        }else {
            String period = (String)map.get("period");
            List<String> numbers = (List<String>) map.get("numbers");
            int status = HttpUtils.pay(period, numbers);
            if (status==300||status==200){
                return;
            }
            if (status==400){
                chooseOne();
            }
        }
    }

    @Scheduled(cron = "0 0/30 9 * * * ")
    public void saveHistoryNumber(){
        elevenController.insertNumbers();
    }

}
