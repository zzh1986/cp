package com.eleven.five.service;

import com.eleven.five.entity.TenTimes;
import com.eleven.five.mapper.TenTimesMapper;
import com.eleven.five.util.Combine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaozhihong
 */
@Service
public class CombinationService {

    @Autowired
    private Combine<Integer> combine;

    @Autowired
    private TenTimesMapper tenTimesMapper;

    public List<List<String>> getCombination() {

        List<TenTimes> tenTimesList = tenTimesMapper.findAll();
        List<Integer> tenTimesInt = new ArrayList<>();

        if (tenTimesList.get(0).getOneTen() != 0) {
            tenTimesInt.add(1);
        }
        if (tenTimesList.get(0).getTwoTen() != 0) {
            tenTimesInt.add(2);
        }
        if (tenTimesList.get(0).getThreeTen() != 0) {
            tenTimesInt.add(3);
        }
        if (tenTimesList.get(0).getFourTen() != 0) {
            tenTimesInt.add(4);
        }
        if (tenTimesList.get(0).getFiveTen() != 0) {
            tenTimesInt.add(5);
        }
        if (tenTimesList.get(0).getSixTen() != 0) {
            tenTimesInt.add(6);
        }
        if (tenTimesList.get(0).getSevenTen() != 0) {
            tenTimesInt.add(7);
        }
        if (tenTimesList.get(0).getEightTen() != 0) {
            tenTimesInt.add(8);
        }
        if (tenTimesList.get(0).getNineTen() != 0) {
            tenTimesInt.add(9);
        }
        if (tenTimesList.get(0).getTenTen() != 0) {
            tenTimesInt.add(10);
        }
        if (tenTimesList.get(0).getElevenTen() != 0) {
            tenTimesInt.add(11);
        }
        List<List<Integer>> oneGroup = combine.combinerSelect(tenTimesInt, new ArrayList<Integer>(), tenTimesInt.size(), 2);

        List<Integer> tenTimesInt2 = new ArrayList<>();

        if (tenTimesList.get(9).getOneTen() != 0) {
            tenTimesInt.add(1);
        }
        if (tenTimesList.get(9).getTwoTen() != 0) {
            tenTimesInt.add(2);
        }
        if (tenTimesList.get(9).getThreeTen() != 0) {
            tenTimesInt.add(3);
        }
        if (tenTimesList.get(9).getFourTen() != 0) {
            tenTimesInt.add(4);
        }
        if (tenTimesList.get(9).getFiveTen() != 0) {
            tenTimesInt.add(5);
        }
        if (tenTimesList.get(9).getSixTen() != 0) {
            tenTimesInt.add(6);
        }
        if (tenTimesList.get(9).getSevenTen() != 0) {
            tenTimesInt.add(7);
        }
        if (tenTimesList.get(9).getEightTen() != 0) {
            tenTimesInt.add(8);
        }
        if (tenTimesList.get(9).getNineTen() != 0) {
            tenTimesInt.add(9);
        }
        if (tenTimesList.get(9).getTenTen() != 0) {
            tenTimesInt.add(10);
        }
        if (tenTimesList.get(9).getElevenTen() != 0) {
            tenTimesInt.add(11);
        }
        List<List<Integer>> twoGroup = combine.combinerSelect(tenTimesInt, new ArrayList<Integer>(), tenTimesInt2.size(), 2);


        return null;
    }
}
