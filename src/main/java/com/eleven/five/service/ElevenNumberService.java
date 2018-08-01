package com.eleven.five.service;

import com.eleven.five.entity.ElevenNumber;
import com.eleven.five.entity.ThreePeriod;
import com.eleven.five.mapper.ElevenNumberMapper;
import com.eleven.five.mapper.ThreePeriodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author zhaozhihong<zhao.zhihong@chinaott.net>
 * @date 2018-06-19
 */
@Service
public class ElevenNumberService {

    @Autowired
    private ElevenNumberMapper elevenNumberMapper;

    @Autowired
    private ElevenService elevenService;

    @Autowired
    private ThreePeriodMapper threePeriodMapper;

    /**
     * 初始化表elevenNumber,添加信息作为备用
     */
    public void insertIntoElevenNumbers() {
        elevenNumberMapper.deleteAll();
        //需要进行双层的循环插入
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 75; j++) {
                //查询相关的信息 limit(j+i*84,10)
                // 查询一次插入一条
                int offset = j + i * 84;
                int limit = 10;
                Integer oneNum = elevenService.searchOneNumberFromEleven(offset, limit);
                Integer twoNum = elevenService.searchTwoNumberFromEleven(offset, limit);
                Integer threeNum = elevenService.searchThreeNumberFromEleven(offset, limit);
                Integer fourNum = elevenService.searchFourNumberFromEleven(offset, limit);
                Integer fiveNum = elevenService.searchFiveNumberFromEleven(offset, limit);
                Integer sixNum = elevenService.searchSixNumberFromEleven(offset, limit);
                Integer sevenNum = elevenService.searchSevenNumberFromEleven(offset, limit);
                Integer eightNum = elevenService.searchEightNumberFromEleven(offset, limit);
                Integer nineNum = elevenService.searchNineNumberFromEleven(offset, limit);
                Integer tenNum = elevenService.searchTenNumberFromEleven(offset, limit);
                Integer elevenNum = elevenService.searchElevenNumberFromEleven(offset, limit);
                ElevenNumber elevenNumber = new ElevenNumber();
                elevenNumber.setId(null);
                elevenNumber.setOneNum(oneNum);
                elevenNumber.setTwoNum(twoNum);
                elevenNumber.setThreeNum(threeNum);
                elevenNumber.setFourNum(fourNum);
                elevenNumber.setFiveNum(fiveNum);
                elevenNumber.setSixNum(sixNum);
                elevenNumber.setSevenNum(sevenNum);
                elevenNumber.setEightNum(eightNum);
                elevenNumber.setNineNum(nineNum);
                elevenNumber.setTenNum(tenNum);
                elevenNumber.setElevenNum(elevenNum);
                //处理排序问题
                Integer[] sort = new Integer[11];
                sort[0] = oneNum;
                sort[1] = twoNum;
                sort[2] = threeNum;
                sort[3] = fourNum;
                sort[4] = fiveNum;
                sort[5] = sixNum;
                sort[6] = sevenNum;
                sort[7] = eightNum;
                sort[8] = nineNum;
                sort[9] = tenNum;
                sort[10] = elevenNum;
                //暂时将该排序方式注释掉采用非排序的方式进行统计
//                Arrays.sort(sort);
                elevenNumber.setSort("" + sort[0] + sort[1] + sort[2] + sort[3] + sort[4] + sort[5] + sort[6] + sort[7] + sort[8] + sort[9] + sort[10]);
                //再查询一个最后的一个period
                Integer latest = offset + limit - 1;
                String period = elevenService.searchByLatest(latest);
                elevenNumber.setPeriod(period);
                elevenNumberMapper.save(elevenNumber);
            }
        }
    }

    public void insertIntoThreeTimes() {
        //需要进行双层的循环插入
        Integer firstId = elevenService.SearchFirstId();
        firstId = firstId == 0 ? 1 : firstId;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 75; j++) {
                //查询相关的信息 limit(j+i*84,10)
                // 查询一次插入一条
                int offset = j + i * 84;
                int limit = 10;
                Integer oneNum = elevenService.searchOneThreeTimesFromEleven(offset + firstId -1 + 1, offset + firstId -1 + 2, offset + firstId -1 + 10);
                Integer twoNum = elevenService.searchTwoThreeTimesFromEleven(offset + firstId -1 + 1, offset + firstId -1 + 2, offset + firstId -1 + 10);
                Integer threeNum = elevenService.searchThreeThreeTimesFromEleven(offset + firstId -1 + 1, offset + firstId -1 + 2, offset + firstId -1 + 10);
                Integer fourNum = elevenService.searchFourThreeTimesFromEleven(offset + firstId -1 + 1, offset + firstId -1 + 2, offset + firstId -1 + 10);
                Integer fiveNum = elevenService.searchFiveThreeTimesFromEleven(offset + firstId -1 + 1, offset + firstId -1 + 2, offset + firstId -1 + 10);
                Integer sixNum = elevenService.searchSixThreeTimesFromEleven(offset + firstId -1 + 1, offset + firstId -1 + 2, offset + firstId -1 + 10);
                Integer sevenNum = elevenService.searchSevenThreeTimesFromEleven(offset + firstId -1 + 1, offset + firstId -1 + 2, offset + firstId -1 + 10);
                Integer eightNum = elevenService.searchEightThreeTimesFromEleven(offset + firstId -1 + 1, offset + firstId -1 + 2, offset + firstId -1 + 10);
                Integer nineNum = elevenService.searchNineThreeTimesFromEleven(offset + firstId -1 + 1, offset + firstId -1 + 2, offset + firstId -1 + 10);
                Integer tenNum = elevenService.searchTenThreeTimesFromEleven(offset + firstId -1 + 1, offset + firstId -1 + 2, offset + firstId -1 + 10);
                Integer elevenNum = elevenService.searchElevenThreeTimesFromEleven(offset + firstId -1 + 1, offset + firstId -1 + 2, offset + firstId -1 + 10);

                ThreePeriod threePeriod = new ThreePeriod();
                threePeriod.setId(null);
                threePeriod.setOneNum(oneNum == null ? 0 : oneNum);
                threePeriod.setTwoNum(twoNum == null ? 0 : twoNum);
                threePeriod.setThreeNum(threeNum == null ? 0 : threeNum);
                threePeriod.setFourNum(fourNum == null ? 0 : fourNum);
                threePeriod.setFiveNum(fiveNum == null ? 0 : fiveNum);
                threePeriod.setSixNum(sixNum == null ? 0 : sixNum);
                threePeriod.setSevenNum(sevenNum == null ? 0 : sevenNum);
                threePeriod.setEightNum(eightNum == null ? 0 : eightNum);
                threePeriod.setNineNum(nineNum == null ? 0 : nineNum);
                threePeriod.setTenNum  (tenNum == null ? 0 : tenNum);
                threePeriod.setElevenNum(elevenNum == null ? 0 : elevenNum);
                Integer[] sort = new Integer[11];
                sort[0] = oneNum;
                sort[1] = twoNum;
                sort[2] = threeNum;
                sort[3] = fourNum;
                sort[4] = fiveNum;
                sort[5] = sixNum;
                sort[6] = sevenNum;
                sort[7] = eightNum;
                sort[8] = nineNum;
                sort[9] = tenNum;
                sort[10] = elevenNum;
//                Arrays.sort(sort);
                threePeriod.setSortNum("" + sort[0] + sort[1] + sort[2] + sort[3] + sort[4] + sort[5] + sort[6] + sort[7] + sort[8] + sort[9] + sort[10]);
                //再查询一个最后的一个period
                Integer latest = offset + limit - 1;
                String period = elevenService.searchByLatest(latest);
                threePeriod.setPeriod(period);
                threePeriodMapper.save(threePeriod);
            }
        }
    }
}
