package com.eleven.five.service;

import com.eleven.five.entity.TenTongJi;
import com.eleven.five.mapper.TenTongJiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-06-29
 */
@Service
public class TenTongJiService {
    @Autowired
    private TenTongJiMapper tenTongJiMapper;

    public void saveTenTongJiNumber(){
        // TODO 清空数据库
        tenTongJiMapper.deleteAll();

        Integer one = tenTongJiMapper.searchOneNumberFromEleven();
        Integer two = tenTongJiMapper.searchTwoNumberFromEleven();
        Integer three = tenTongJiMapper.searchThreeNumberFromEleven();
        Integer four = tenTongJiMapper.searchFourNumberFromEleven();
        Integer five = tenTongJiMapper.searchFiveNumberFromEleven();
        Integer six = tenTongJiMapper.searchSixNumberFromEleven();
        Integer seven = tenTongJiMapper.searchSevenNumberFromEleven();
        Integer eight = tenTongJiMapper.searchEightNumberFromEleven();
        Integer nine = tenTongJiMapper.searchNineNumberFromEleven();
        Integer ten = tenTongJiMapper.searchTenNumberFromEleven();
        Integer eleven = tenTongJiMapper.searchElevenNumberFromEleven();
        Integer[] elevens = new Integer[11];
        elevens[0]=one;
        elevens[1]=two;
        elevens[2]=three;
        elevens[3]=four;
        elevens[4]=five;
        elevens[5]=six;
        elevens[6]=seven;
        elevens[7]=eight;
        elevens[8]=nine;
        elevens[9]=ten;
        elevens[10]=eleven;
       // Arrays.sort(elevens);
        TenTongJi tenTongJi = new TenTongJi();
        tenTongJi.setId(null);
        tenTongJi.setOne(one);
        tenTongJi.setTwo(two);
        tenTongJi.setThree(three);
        tenTongJi.setFour(four);
        tenTongJi.setFive(five);
        tenTongJi.setSix(six);
        tenTongJi.setSeven(seven);
        tenTongJi.setEight(eight);
        tenTongJi.setNine(nine);
        tenTongJi.setTen(ten);
        tenTongJi.setEleven(eleven);
        String sort = "" +elevens[0]+elevens[1]+elevens[2]+elevens[3]+elevens[4]+elevens[5]
                +elevens[6]+elevens[7]+elevens[8]+elevens[9]+elevens[10];
        tenTongJi.setSort(sort);
        tenTongJiMapper.save(tenTongJi);
    }
}

