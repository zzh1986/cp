package com.eleven.five.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import com.eleven.five.entity.ChangeNumbers;
import com.eleven.five.mapper.ChangeNumbersMapper;
import com.eleven.five.util.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @author zhaozhihong
 */
@Service
public class ChangeNumbersService {


    @Autowired
    private ChangeNumbersMapper changeNumbersMapper;

    @Autowired
    private GroupService groupService;

    public ChangeNumbers getChangeNumbers() throws IOException {
        String date = DateUtil.now().substring(0, 10).replaceAll("-", "");
        List<String[]> tenTimes = groupService.getTenTimes(date, "84", 84);
        if (tenTimes.size() < 13) {
            return null;
        }

        String fiveTimesHappen = "[出现5:\t";
        String threeTimesHappen = "[出现3:\t";
        String fourTimesHappen = "[未出现4统计:\t";
        String sixTimesHappen = "[未出现6统计:\t";

        for (int i = tenTimes.size() - 13; i < tenTimes.size() - 10; i++) {
            //需要提供一个方法来获取四组数据,根据i值和tenTimes集合 来获取我的目标数据
            Map<String, String[]> map = getTargetNumber(i, tenTimes);
            String[] fives = map.get("five");
            String[] threes = map.get("three");
            String[] fours = map.get("four");
            String[] sixes = map.get("six");
            Object[] fiveRight = ArrayUtils.intersect(fives, tenTimes.get(i + 10));
            Object[] threeRight = ArrayUtils.intersect(threes, tenTimes.get(i + 10));
            Object[] fourRight = ArrayUtils.minus(fours, tenTimes.get(i + 10));
            Object[] sixRight = ArrayUtils.minus(sixes, tenTimes.get(i + 10));
            if (i == (tenTimes.size() - 11)){
                fiveTimesHappen += fives.length + "," + fiveRight.length +"]";
                threeTimesHappen += threes.length + "," + threeRight.length +"]";
                fourTimesHappen += fours.length + "," + fourRight.length +"]";
                sixTimesHappen += sixes.length + "," + sixRight.length +"]";
            }else{
                fiveTimesHappen += fives.length + "," + fiveRight.length +";"+"\t";
                threeTimesHappen += threes.length + "," + threeRight.length +";"+"\t";
                fourTimesHappen += fours.length + "," + fourRight.length +";"+"\t";
                sixTimesHappen += sixes.length + "," + sixRight.length +";"+"\t";
            }

        }

        ChangeNumbers changeNumbers = new ChangeNumbers();
        changeNumbers.setId(null);
        changeNumbers.setFiveTimesNumber(Arrays.toString(getTargetNumber(tenTimes.size()-10,tenTimes).get("five")));
        changeNumbers.setFiveTimesHappen(fiveTimesHappen);
        changeNumbers.setThreeTimesNumber(Arrays.toString(getTargetNumber(tenTimes.size()-10,tenTimes).get("three")));
        changeNumbers.setThreeTimesHappen(threeTimesHappen);
        changeNumbers.setFourTimesNumber(Arrays.toString(getTargetNumber(tenTimes.size()-10,tenTimes).get("four")));
        changeNumbers.setFourTimesHappen(fourTimesHappen);
        changeNumbers.setSixTimesNumber(Arrays.toString(getTargetNumber(tenTimes.size()-10,tenTimes).get("six")));
        changeNumbers.setSixTimesHappen(sixTimesHappen);
        changeNumbers.setPeriod(""+tenTimes.size());
        //TODO 可以考虑保存到数据库
        return changeNumbers;
    }

    /**
     * 该方法用于获取10期数据内 5次 3次 4次 6次的数据
     */
    private Map<String, String[]> getTargetNumber(int num, List<String[]> tenTimes) {
        Map<String, String[]> map = new HashMap<>();
        List<String> five = new ArrayList<>();
        List<String> three = new ArrayList<>();
        List<String> four = new ArrayList<>();
        List<String> six = new ArrayList<>();
        String[] standard = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11"};
        int[] index = new int[11];
        String[] leaveNumbers = tenTimes.get(num);
        for (int i = num; i < num + 10; i++) {
            //再统计一次相关的数据
            for (int j = 0; j < tenTimes.get(i).length; j++) {
                switch (tenTimes.get(i)[j]) {
                    case "01":
                        index[0]++;
                        break;
                    case "02":
                        index[1]++;
                        break;
                    case "03":
                        index[2]++;
                        break;
                    case "04":
                        index[3]++;
                        break;
                    case "05":
                        index[4]++;
                        break;
                    case "06":
                        index[5]++;
                        break;
                    case "07":
                        index[6]++;
                        break;
                    case "08":
                        index[7]++;
                        break;
                    case "09":
                        index[8]++;
                        break;
                    case "10":
                        index[9]++;
                        break;
                    default:
                        index[10]++;
                }
            }
        }
        //获取4组数据的List集合
        for (int i = 0; i < index.length; i++) {
            switch (index[i]) {
                case 5:
                    if (!isContainsObj(standard[i], leaveNumbers)) {
                        five.add(standard[i]);
                    }
                    ;
                    break;
                case 3:
                    if (!isContainsObj(standard[i], leaveNumbers)) {
                        three.add(standard[i]);
                    }
                    ;
                    break;
                case 4:
                    if (isContainsObj(standard[i], leaveNumbers)) {
                        four.add(standard[i]);
                    }
                    ;
                    break;
                case 6:
                    if (isContainsObj(standard[i], leaveNumbers)) {
                        six.add(standard[i]);
                    }
                    ;
                    break;
                default:
                    continue;
            }
        }
        //组装数据并返回
        map.put("five", Convert.toStrArray(five.toArray()));
        map.put("three", Convert.toStrArray(three.toArray()));
        map.put("four", Convert.toStrArray(four.toArray()));
        map.put("six", Convert.toStrArray(six.toArray()));

        return map;
    }

    /**
     * 该方法用于将判断一个对象是否在一个数组中
     */
    public Boolean isContainsObj(Object obj, Object[] objects) {
        Boolean result = false;
        if (null != objects && objects.length > 0) {
            List<Object> list = Arrays.asList(objects);
            if (list.contains(obj)) {
                result = true;
            }
        }
        return result;
    }
}
