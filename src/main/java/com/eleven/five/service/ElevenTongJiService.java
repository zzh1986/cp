package com.eleven.five.service;

import com.eleven.five.entity.ElevenTongJi;
import com.eleven.five.mapper.ElevenTongJiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-08-05
 */
@Service
public class ElevenTongJiService {

    @Autowired
    private ElevenTongJiMapper elevenTongJiMapper;

    public void saveElevenTongJiNumber() {
        // TODO 清空数据库
        elevenTongJiMapper.deleteAll();

        Integer one = elevenTongJiMapper.searchOneNumberFromEleven();
        Integer two = elevenTongJiMapper.searchTwoNumberFromEleven();
        Integer three = elevenTongJiMapper.searchThreeNumberFromEleven();
        Integer four = elevenTongJiMapper.searchFourNumberFromEleven();
        Integer five = elevenTongJiMapper.searchFiveNumberFromEleven();
        Integer six = elevenTongJiMapper.searchSixNumberFromEleven();
        Integer seven = elevenTongJiMapper.searchSevenNumberFromEleven();
        Integer eight = elevenTongJiMapper.searchEightNumberFromEleven();
        Integer nine = elevenTongJiMapper.searchNineNumberFromEleven();
        Integer ten = elevenTongJiMapper.searchTenNumberFromEleven();
        Integer eleven = elevenTongJiMapper.searchElevenNumberFromEleven();
        Integer[] elevens = new Integer[11];
        elevens[0] = one;
        elevens[1] = two;
        elevens[2] = three;
        elevens[3] = four;
        elevens[4] = five;
        elevens[5] = six;
        elevens[6] = seven;
        elevens[7] = eight;
        elevens[8] = nine;
        elevens[9] = ten;
        elevens[10] = eleven;
        // Arrays.sort(elevens);
        ElevenTongJi elevenTongJi = new ElevenTongJi();
        elevenTongJi.setId(null);
        elevenTongJi.setOne(one);
        elevenTongJi.setTwo(two);
        elevenTongJi.setThree(three);
        elevenTongJi.setFour(four);
        elevenTongJi.setFive(five);
        elevenTongJi.setSix(six);
        elevenTongJi.setSeven(seven);
        elevenTongJi.setEight(eight);
        elevenTongJi.setNine(nine);
        elevenTongJi.setTen(ten);
        elevenTongJi.setEleven(eleven);
        String sort = "" + elevens[0] + elevens[1] + elevens[2] + elevens[3] + elevens[4] + elevens[5] + elevens[6] + elevens[7] + elevens[8] + elevens[9] + elevens[10];
        elevenTongJi.setSort(sort);
        elevenTongJiMapper.save(elevenTongJi);
    }
}

