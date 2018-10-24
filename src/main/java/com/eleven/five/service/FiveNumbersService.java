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
        for (int i = 0 - years * 365-1; i < -1; i++) {
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
            if (count ==170) {
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

    public List<String[]> getSomeDaysNotAppearNumbers(Integer days) throws IOException {

        String now = DateUtil.now();
        DateTime today = DateUtil.parse(now);
        List<String[]> allNumbers = new ArrayList<>();

        //准备数据
        for (int i = 0 - days; i < 0; i++) {
            String date = DateUtil.offsetDay(today, i).toString("yyyyMMdd");
            List<String[]> tenTimes = groupService.getTenTimes(date, "84", 84);
            allNumbers.addAll(tenTimes);
        }
        //数据准备结束,对前三位进行统计
        String[] standard = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11"};
        List<Object[]> allArray = GroupUtils.getAllArrayfromCount(standard, 3);
        int[] index = new int[allArray.size()];
        t1:
        for (int i = 0; i < allNumbers.size(); i++) {
            for (int j = 0; j < allArray.size(); j++) {
                if( allNumbers.get(i)[0].equals(allArray.get(j)[0]) &&
                    allNumbers.get(i)[1].equals(allArray.get(j)[1]) &&
                    allNumbers.get(i)[2].equals(allArray.get(j)[2])){
                    index[j]++;
                    continue t1;
                }
            }
        }
        List<Integer> list = ArrayUtils.minIndex(index);
        List<String[]> result = new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            result.add(Convert.toStrArray(allArray.get(list.get(i))));
        }
        System.out.println(result.size());
        System.out.println("最小值"+index[list.get(0)]);
    return result;

    }

    public List<String[]> getNextMaxPercentNumbers(String date, String period) throws IOException {

        List<String[]> tenTimes = groupService.getTenTimes(date, period, 1);
        if(null != tenTimes && tenTimes.size()>0){
            String[] currentNumber = tenTimes.get(0);
            List<FiveNumbers> fiveNumbersList = fiveNumbersMapper.getListByThreeNumbers(currentNumber[0],currentNumber[1],currentNumber[2]);
            List<Integer> ids = new ArrayList<>();
            for (int i = 0; i < fiveNumbersList.size(); i++) {
                ids.add(fiveNumbersList.get(i).getId()+1);
            }
            List<FiveNumbers> allNext = fiveNumbersMapper.findAllById(ids);

            String[] standard = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11"};
            List<Object[]> allArray = GroupUtils.getAllArrayfromCount(standard, 3);
            int[] index = new int[allArray.size()];
            t1:
            for (int i = 0; i < allNext.size(); i++) {
                for (int j = 0; j < allArray.size(); j++) {
                    if( allNext.get(i).getOne().equals(allArray.get(j)[0]) &&
                        allNext.get(i).getTwo().equals(allArray.get(j)[1]) &&
                        allNext.get(i).getThree().equals(allArray.get(j)[2])){
                        index[j]++;
                        continue t1;
                    }
                }
            }
            //统计结束 找出最大的下标
            List<Integer> list = ArrayUtils.maxIndex(index);
            List<String[]> result = new ArrayList<>();
            for (int i = 0; i <list.size() ; i++) {
                result.add(Convert.toStrArray(allArray.get(list.get(i))));
            }
            //对结果进行统计
            int[] elevenNumberTimes = new int[11];
            for (int i = 0; i < result.size(); i++) {
                for (int j = 0; j < 3; j++) {
                    switch (result.get(i)[j]){
                        case "01": elevenNumberTimes[0]++;break;
                        case "02": elevenNumberTimes[1]++;break;
                        case "03": elevenNumberTimes[2]++;break;
                        case "04": elevenNumberTimes[3]++;break;
                        case "05": elevenNumberTimes[4]++;break;
                        case "06": elevenNumberTimes[5]++;break;
                        case "07": elevenNumberTimes[6]++;break;
                        case "08": elevenNumberTimes[7]++;break;
                        case "09": elevenNumberTimes[8]++;break;
                        case "10": elevenNumberTimes[9]++;break;
                        default:   elevenNumberTimes[10]++;
                    }
                }
            }
            List<Integer> sortIndex = ArrayUtils.sortIndex(elevenNumberTimes);
            List<String> sortList = new ArrayList<>();
            for (int i = 0; i < sortIndex.size(); i++) {
                if (elevenNumberTimes[sortIndex.get(i)]!=0){
                    sortList.add(standard[sortIndex.get(i)]);
                }
            }
            System.out.println(Arrays.toString(sortList.toArray())+"--------->"+sortList.size());
            return result;
        }
        return null;

    }
}
