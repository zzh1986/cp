package com.eleven.five.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.eleven.five.entity.HistoryFiveNumbers;
import com.eleven.five.util.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaozhihong
 */
@Service
public class NotAppareService {

    @Autowired
    private GroupService groupService;

    public HistoryFiveNumbers getLongTimeNotexitNumber(Integer days) throws IOException {
        String now = DateUtil.now();
        DateTime today = DateUtil.parse(now);
        List<String[]> allNumbers = new ArrayList<>();
        for (int i = 0 - days; i <= 0; i++) {
            String date = DateUtil.offsetDay(today, i).toString("yyyyMMdd");
            List<String[]> tenTimes = groupService.getTenTimes(date, "84", 84);
            allNumbers.addAll(tenTimes);
        }
        System.out.println("数据准备结束,可进行统计");
        //TODO 第一类统计的是连号未出现的 这里是按顺序排列好的
        String[] oneGroup = {"01", "02", "03", "04", "05"};
        String[] twoGroup = {"02", "03", "04", "05", "06"};
        String[] threeGroup = {"03", "04", "05", "06", "07"};
        String[] fourGroup = {"04", "05", "06", "07", "08"};
        String[] fiveGroup = {"05", "06", "07", "08", "09"};
        String[] sixGroup = {"06", "07", "08", "09", "10"};
        String[] sevenGroup = {"07", "08", "09", "10", "11"};
        String[] eightGroup = {"08", "09", "10", "11", "01"};
        String[] nineGroup = {"09", "10", "11", "01", "02"};
        String[] tenGroup = {"10", "11", "01", "02", "03"};
        String[] elevenGroup = {"11", "01", "02", "03", "04"};
        List<String[]> standard = new ArrayList<>();
        standard.add(oneGroup);
        standard.add(twoGroup);
        standard.add(threeGroup);
        standard.add(fourGroup);
        standard.add(fiveGroup);
        standard.add(sixGroup);
        standard.add(sevenGroup);
        standard.add(eightGroup);
        standard.add(nineGroup);
        standard.add(tenGroup);
        standard.add(elevenGroup);
        int[] index = new int[11];
        //一共11个分组
        int[] currentMissDays = new int[11];
        //TODO 为了拼凑相关的数据 这里的内容只能拆开 按照 days 和 84 的方式进行统计
        for (int i = 0; i < days-1; i++) {
            for (int j = 0; j < 84; j++) {
                for (int k = 0; k < standard.size(); k++) {
                    if (ArrayUtils.intersect(allNumbers.get(j + i * 84), standard.get(k)).length == 5) {
                        index[k]++;
                        currentMissDays[k] = -1;
                    }

                }
            }
            for (int j = 0; j < 11; j++) {
                currentMissDays[j]++;
            }
        }
        DecimalFormat df = new DecimalFormat("0%");
        String[] percent = new String[11];
        for (int i = 0; i < 11; i++) {
            percent[i] = df.format(1.0 * index[i] / days);
        }
        HistoryFiveNumbers historyFiveNumbers = new HistoryFiveNumbers();
        historyFiveNumbers.setFiveList(standard);
        historyFiveNumbers.setCurrentMissDays(Convert.toIntArray(currentMissDays));
        historyFiveNumbers.setPercent(percent);
        historyFiveNumbers.setTotal(Convert.toIntArray(index));
        return historyFiveNumbers;
    }
}
