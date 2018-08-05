package com.eleven.five.service;

import com.eleven.five.entity.Elevens;
import com.eleven.five.mapper.ElevenMapper;
import com.eleven.five.util.FiveUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElevenService {

    @Autowired
    private ElevenMapper elevenMapper;

    @Autowired
    private TenNumberService tenNumberService;

    @Autowired
    private ElevenNumberService elevenNumberService;

    private Logger logger = LoggerFactory.getLogger(ElevenService.class);


    public void insertNumbers() {
        try {
            logger.info("开始保存.....");
            List<String> numsList = FiveUtil.getCPNums();
            // List<Five> fiveList = new ArrayList<>();
            saveFiveNumbers(numsList);
            logger.info("10天数据保存完毕!开始统计:每10期的次数!");
            tenNumberService.insertIntoTenNumbers();
            tenNumberService.insertIntoThreeTimes();
            elevenNumberService.insertIntoElevenNumbers();
            elevenNumberService.insertIntoElevenThreeTimes();
            logger.info("数据保存结束!请到数据库查询");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取数据异常", e);
        }


    }

    private void saveFiveNumbers(List<String> numsList) {
        elevenMapper.deleteAll();
        for (String nums : numsList) {
            String[] numberSplit = nums.split("[\\s]");
            Elevens elevens = new Elevens();
            for (String number : numberSplit) {
                switch (number) {
                    case "01":
                        elevens.setOne(1);
                        break;
                    case "02":
                        elevens.setTwo(1);
                        break;
                    case "03":
                        elevens.setThree(1);
                        break;
                    case "04":
                        elevens.setFour(1);
                        break;
                    case "05":
                        elevens.setFive(1);
                        break;
                    case "06":
                        elevens.setSix(1);
                        break;
                    case "07":
                        elevens.setSeven(1);
                        break;
                    case "08":
                        elevens.setEight(1);
                        break;
                    case "09":
                        elevens.setNine(1);
                        break;
                    case "10":
                        elevens.setTen(1);
                        break;
                    case "11":
                        elevens.setEleven(1);
                        break;
                    default:
                        elevens.setPeriod(number);
                        break;
                }
            }
            //TODO 保存 数据到数据库
            elevenMapper.save(elevens);
        }
    }

    public Integer searchOneNumberFromEleven(int offset, int limit) {
        return elevenMapper.searchOneNumberFromEleven(offset, limit);
    }


    public Integer searchTwoNumberFromEleven(int offset, int limit) {
        return elevenMapper.searchTwoNumberFromEleven(offset, limit);
    }

    public Integer searchThreeNumberFromEleven(int offset, int limit) {
        return elevenMapper.searchThreeNumberFromEleven(offset, limit);
    }

    public Integer searchFourNumberFromEleven(int offset, int limit) {
        return elevenMapper.searchFourNumberFromEleven(offset, limit);
    }

    public Integer searchFiveNumberFromEleven(int offset, int limit) {
        return elevenMapper.searchFiveNumberFromEleven(offset, limit);
    }

    public Integer searchSixNumberFromEleven(int offset, int limit) {
        return elevenMapper.searchSixNumberFromEleven(offset, limit);

    }

    public Integer searchSevenNumberFromEleven(int offset, int limit) {
        return elevenMapper.searchSevenNumberFromEleven(offset, limit);
    }

    public Integer searchEightNumberFromEleven(int offset, int limit) {
        return elevenMapper.searchEightNumberFromEleven(offset, limit);
    }

    public Integer searchNineNumberFromEleven(int offset, int limit) {
        return elevenMapper.searchNineNumberFromEleven(offset, limit);
    }

    public Integer searchTenNumberFromEleven(int offset, int limit) {
        return elevenMapper.searchTenNumberFromEleven(offset, limit);

    }

    public Integer searchElevenNumberFromEleven(int offset, int limit) {
        return elevenMapper.searchElevenNumberFromEleven(offset, limit);
    }

    public String searchByLatest(Integer latest) {
        return elevenMapper.searchByLatest(latest);
    }

    public Integer searchOneThreeTimesFromEleven(int i, int i1, int i2) {
        return elevenMapper.searchOneThreeTimesFromEleven(i,i1,i2);
    }

    public Integer searchTwoThreeTimesFromEleven(int i, int i1, int i2) {
        return elevenMapper.searchTwoThreeTimesFromEleven(i,i1,i2);
    }

    public Integer searchThreeThreeTimesFromEleven(int i, int i1, int i2) {
        return elevenMapper.searchThreeThreeTimesFromEleven(i,i1,i2);
    }

    public Integer searchFourThreeTimesFromEleven(int i, int i1, int i2) {
        return elevenMapper.searchFourThreeTimesFromEleven(i,i1,i2);
    }

    public Integer searchFiveThreeTimesFromEleven(int i, int i1, int i2) {
        return elevenMapper.searchFiveThreeTimesFromEleven(i,i1,i2);
    }

    public Integer searchSixThreeTimesFromEleven(int i, int i1, int i2) {
        return elevenMapper.searchSixThreeTimesFromEleven(i,i1,i2);
    }

    public Integer searchSevenThreeTimesFromEleven(int i, int i1, int i2) {
        return elevenMapper.searchSevenThreeTimesFromEleven(i,i1,i2);
    }

    public Integer searchEightThreeTimesFromEleven(int i, int i1, int i2) {
        return elevenMapper.searchEightThreeTimesFromEleven(i,i1,i2);
    }

    public Integer searchNineThreeTimesFromEleven(int i, int i1, int i2) {
        return elevenMapper.searchNineThreeTimesFromEleven(i,i1,i2);
    }

    public Integer searchTenThreeTimesFromEleven(int i, int i1, int i2) {
        return elevenMapper.searchTenThreeTimesFromEleven(i,i1,i2);
    }

    public Integer searchElevenThreeTimesFromEleven(int i, int i1, int i2) {
        return elevenMapper.searchElevenThreeTimesFromEleven(i,i1,i2);
    }

    public Integer SearchFirstId() {
        return elevenMapper.SearchFirstId();
    }

}