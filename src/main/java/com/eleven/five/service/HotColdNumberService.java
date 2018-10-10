package com.eleven.five.service;

import cn.hutool.core.convert.Convert;
import com.eleven.five.entity.HotColdNumber;
import com.eleven.five.mapper.HotColdNumberMapper;
import com.eleven.five.util.ArrayUtils;
import com.eleven.five.util.ShuJu;
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
public class HotColdNumberService {

    @Autowired
    private HotColdNumberMapper hotColdNumberMapper;

    @Autowired
    private GroupService groupService;

    public void getHotColdNumbers(String date, String period) throws IOException {
        //先清空表格
        hotColdNumberMapper.deleteAll();
        List<String[]> tenTimes = groupService.getTenTimes(date, period, Integer.valueOf(period));
        //获取到数据后需要进行相应的统计
        List<HotColdNumber> hostColdNumberList = new ArrayList<>();
        for (int i = 10; i < tenTimes.size(); i++) {
            List<String[]> tenGroup = new ArrayList<>();

            for (int j = i - 10; j < i; j++) {
                tenGroup.add(tenTimes.get(j));
            }
            //准备好一组tenGroup,需要按照这个数据进行相应数据的初始化
            String[] wenHao = Convert.toStrArray(ShuJu.getWenHao(tenGroup).toArray());
            String[] lenHao = Convert.toStrArray(ShuJu.getLenHao(tenGroup).toArray());
            String[] reHao = Convert.toStrArray(ShuJu.getReHao(tenGroup).toArray());

            //初始化对象的属性,然后进行赋值运算
            HotColdNumber hotColdNumber = new HotColdNumber();
            hotColdNumber.setId(null);
            hotColdNumber.setHotNumber(Arrays.toString(reHao));
            hotColdNumber.setColdNumber(Arrays.toString(lenHao));
            hotColdNumber.setWarmNumber(Arrays.toString(wenHao));
            hotColdNumber.setHotRight(ArrayUtils.intersect(reHao, tenTimes.get(i)).length);
            hotColdNumber.setColdRight(ArrayUtils.intersect(lenHao, tenTimes.get(i)).length);
            hotColdNumber.setWarmRight(ArrayUtils.intersect(wenHao, tenTimes.get(i)).length);
            hotColdNumber.setHotCode(Arrays.toString(ArrayUtils.intersect(reHao, tenTimes.get(i))));
            hotColdNumber.setColdCode(Arrays.toString(ArrayUtils.intersect(lenHao, tenTimes.get(i))));
            hotColdNumber.setWarmCode(Arrays.toString(ArrayUtils.intersect(wenHao, tenTimes.get(i))));
            hotColdNumber.setHotReserve(Arrays.toString(ArrayUtils.minus(reHao,tenTimes.get(i-1))));
            hotColdNumber.setWarmReserve(Arrays.toString(ArrayUtils.minus(wenHao,tenTimes.get(i-1))));
            hotColdNumber.setNextAward(Arrays.toString(tenTimes.get(i)));
            hotColdNumber.setPeriod(date + i);

            hostColdNumberList.add(hotColdNumber);
        }
        hotColdNumberMapper.saveAll(hostColdNumberList);
    }
}
