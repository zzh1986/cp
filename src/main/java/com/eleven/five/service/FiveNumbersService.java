package com.eleven.five.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.eleven.five.entity.FiveNumbers;
import com.eleven.five.mapper.FiveNumbersMapper;
import com.eleven.five.util.ArrayUtils;
import com.eleven.five.util.GroupUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhaozhihong
 */
@Service
public class FiveNumbersService {


    @Autowired
    private FiveNumbersMapper fiveNumbersMapper;

    @Autowired
    private GroupService groupService;


    public String saveFiveNumbers(Integer years) throws IOException {
        String now = DateUtil.now();
        DateTime today = DateUtil.parse(now);
        List<String[]> allNumbers = new ArrayList<>();

        //准备数据
        for (int i = 0 - years * 365; i <= 0; i++) {
            String date = DateUtil.offsetDay(today, i).toString("yyyyMMdd");
            List<String[]> tenTimes = groupService.getTenTimes(date, "84", 84);
            allNumbers.addAll(tenTimes);
        }

        //数据准备好了
        List<FiveNumbers> fiveNumbersList = new ArrayList<>();

        for (int i = 0; i < allNumbers.size(); i++) {
            FiveNumbers fiveNumbers = new FiveNumbers();
            fiveNumbers.setId(null);
            fiveNumbers.setOne(allNumbers.get(i)[0]);
            fiveNumbers.setTwo(allNumbers.get(i)[1]);
            fiveNumbers.setThree(allNumbers.get(i)[2]);
            fiveNumbers.setFour(allNumbers.get(i)[3]);
            fiveNumbers.setFive(allNumbers.get(i)[4]);
            fiveNumbersList.add(fiveNumbers);
        }

        fiveNumbersMapper.saveAll(fiveNumbersList);
        return "数据保存成功,请到数据库查看";


    }

    /**
     * 从表中统计出小数据进行返回
     *
     * @return
     */
    public List<String[]> findMinCountThreeNumbers() {
        String[] standard = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11"};
        List<Object[]> allGroup = GroupUtils.getAllArrayfromCount(standard, 3);
        List<String[]> result = new ArrayList<>();
        int[] countNum = new int[allGroup.size()];
        //只需要GroupEntity中的one进行查询
        for (int i = 0; i < allGroup.size(); i++) {
            //三个数据已经就绪
            String[] one = Convert.toStrArray(allGroup.get(i));
            int count = fiveNumbersMapper.findCountByThree(one[0], one[1], one[2]);
            countNum[i] = count;
            if (count >170 && count <180) {
                result.add(one);
            }

        }
        List<Integer> maxIndex = ArrayUtils.maxIndex(countNum);
        List<Integer> minIndex = ArrayUtils.minIndex(countNum);
        for (int i = 0; i < maxIndex.size(); i++) {
            System.out.println("出现频率最高的数据组合为:"+ Arrays.toString(allGroup.get(maxIndex.get(i))));
        }

        for (int i = 0; i < minIndex.size(); i++) {
            System.out.println("出现频率最低的数据组合为:"+Arrays.toString(allGroup.get(minIndex.get(i))));
        }
        return result;
    }
}
