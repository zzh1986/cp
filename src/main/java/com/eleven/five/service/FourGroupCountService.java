package com.eleven.five.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import com.eleven.five.entity.FourGroupCount;
import com.eleven.five.entity.NumberGroup;
import com.eleven.five.mapper.FourGroupCountMapper;
import com.eleven.five.util.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-10-21
 */
@Service
public class FourGroupCountService {

    @Autowired
    private FourGroupCountMapper fourGroupCountMapper;

    @Autowired
    private GroupService groupService;

    /**
     * 只对当天的数据进行统计,找出合适的数据
     *
     * @throws IOException
     */
    public void saveFourGroupCount() throws IOException {
        fourGroupCountMapper.deleteAll();
        String date = DateUtil.now().substring(0, 10).replaceAll("-", "");
        List<String[]> tenTimes = groupService.getTenTimes(date, "84", 84);
        //这里需要一个方法,获取固定一组数据的分组 然后跟下一组进行比较,暂时全部写在本类中 只统计当天的
        /**下面的各种数据主要为了对象的属性做准备,其中times代表当前最大连续不正确数,right代表一共的正确数,total代表一共出现分组的次数(有可能会没有分组)*/
        int oneTimes = 0;
        int oneRight = 0;
        int oneTotal = 0;
        int oneMax = 0;
        int twoTimes = 0;
        int twoRight = 0;
        int twoTotal = 0;
        int twoMax = 0;
        int threeTimes = 0;
        int threeRight = 0;
        int threeTotal = 0;
        int threeMax = 0;
        int fourTimes = 0;
        int fourRight = 0;
        int fourTotal = 0;
        int fourMax = 0;
        for (int i = 0; i < tenTimes.size() - 1; i++) {
            Map<String, List<String>> oneGroupJiOuNumber = getOneGroupJiOuNumber(tenTimes.get(i));
            Map<String, List<String>> threeSixNineFromCurrent = getThreeSixNineFromCurrent(tenTimes.get(i));
            String[] oneGroups = null;
            String[] twoGroups = null;
            String[] threeGroups = null;
            String[] fourGroups = null;
            if (oneGroupJiOuNumber.get("oneGroup") != null && oneGroupJiOuNumber.get("oneGroup").size() != 0) {
                oneGroups = Convert.toStrArray(oneGroupJiOuNumber.get("oneGroup").toArray());
            }
            if (oneGroupJiOuNumber.get("twoGroup") != null && oneGroupJiOuNumber.get("twoGroup").size() != 0) {
                twoGroups = Convert.toStrArray(oneGroupJiOuNumber.get("twoGroup").toArray());
            }
            if (threeSixNineFromCurrent.get("threeGroup") != null && threeSixNineFromCurrent.get("threeGroup").size() != 0) {
                threeGroups = Convert.toStrArray(threeSixNineFromCurrent.get("threeGroup").toArray());
            }
            if (threeSixNineFromCurrent.get("fourGroup") != null && threeSixNineFromCurrent.get("fourGroup").size() != 0) {
                fourGroups = Convert.toStrArray(threeSixNineFromCurrent.get("fourGroup").toArray());
            }
            if (oneGroups != null && oneGroups.length != 0) {
                oneTotal++;
                if (ArrayUtils.intersect(oneGroups, tenTimes.get(i + 1)).length != oneGroups.length) {
                    oneTimes++;
                } else {
                    if (oneMax < oneTimes) {
                        oneMax = oneTimes;
                    }
                    oneRight++;
                    oneTimes = 0;
                }
            }
            if (twoGroups != null && twoGroups.length != 0) {
                twoTotal++;
                if (ArrayUtils.intersect(twoGroups, tenTimes.get(i + 1)).length != twoGroups.length) {
                    twoTimes++;
                } else {
                    if (twoMax < twoTimes) {
                        twoMax = twoTimes;
                    }
                    twoRight++;
                    twoTimes = 0;
                }
            }
            if (threeGroups != null && threeGroups.length != 0) {
                threeTotal++;
                if (ArrayUtils.intersect(threeGroups, tenTimes.get(i + 1)).length != threeGroups.length) {
                    threeTimes++;
                } else {
                    if (threeMax < threeTimes) {
                        threeMax = threeTimes;
                    }
                    threeRight++;
                    threeTimes = 0;
                }
            }
            if (fourGroups != null && fourGroups.length != 0) {
                fourTotal++;
                if (ArrayUtils.intersect(fourGroups, tenTimes.get(i + 1)).length != fourGroups.length) {
                    fourTimes++;
                } else {
                    if (fourMax < fourTimes) {
                        fourMax = fourTimes;
                    }
                    fourRight++;
                    fourTimes = 0;
                }
            }
        }
        /**设置%形式表示正确率*/
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);

        FourGroupCount fourGroupCount = new FourGroupCount();
        fourGroupCount.setId(null);
        fourGroupCount.setOneGroupPercent(nf.format(1.0 * oneRight / oneTotal));
        fourGroupCount.setOneGroupTimes(oneTimes);
        fourGroupCount.setOneGroupLastPeriod("" + (tenTimes.size() - oneTimes));
        fourGroupCount.setTwoGroupPercent(nf.format(1.0 * twoRight / twoTotal));
        fourGroupCount.setTwoGroupTimes(twoTimes);
        fourGroupCount.setTwoGroupLastPeriod("" + (tenTimes.size() - twoTimes));
        fourGroupCount.setThreeGroupPercent(nf.format(1.0 * threeRight / threeTotal));
        fourGroupCount.setThreeGroupTimes(threeTimes);
        fourGroupCount.setThreeGroupLastPeriod("" + (tenTimes.size() - threeTimes));
        fourGroupCount.setFourGroupPercent(nf.format(1.0 * fourRight / fourTotal));
        fourGroupCount.setFourGroupTimes(fourTimes);
        fourGroupCount.setFourGroupLastPeriod("" + (tenTimes.size() - fourTimes));
        fourGroupCount.setPeriod("" + (tenTimes.size() + 1));
        fourGroupCount.setOneGroupMaxTimes(oneMax);
        fourGroupCount.setTwoGroupMaxTimes(twoMax);
        fourGroupCount.setThreeGroupMaxTimes(threeMax);
        fourGroupCount.setFourGroupMaxTimes(fourMax);
        fourGroupCountMapper.save(fourGroupCount);
    }

    /**
     * 获取第一组和第二组分组数据的方法
     *
     * @param numbers
     * @return
     */
    public Map<String, List<String>> getOneGroupJiOuNumber(String[] numbers) {
        NumberGroup numberGroup = getNumberGroupFromFiveNumbers(numbers);
        String[] numberGroupStr = new String[6];
        numberGroupStr[0] = numberGroup.getDaGroup();
        numberGroupStr[1] = numberGroup.getXiaoGroup();
        numberGroupStr[2] = numberGroup.getHeGroup();
        numberGroupStr[3] = numberGroup.getZhiGroup();
        numberGroupStr[4] = numberGroup.getJiGroup();
        numberGroupStr[5] = numberGroup.getOuGroup();

        List<String[]> oneGroup = new ArrayList<>();
        List<String[]> twoGroup = new ArrayList<>();
        //处理1和2的相关的数组
        for (int i = 0; i < numberGroupStr.length; i++) {
            if (numberGroupStr[i].length() == 4) {
                String[] tem = new String[1];
                tem[0] = numberGroupStr[i].substring(1, 3);
                oneGroup.add(tem);
            }
            if (numberGroupStr[i].length() == 8) {
                String oneNumber = numberGroupStr[i].substring(1, 3);
                String twoNumber = numberGroupStr[i].substring(5, 7);
                String[] tmp = new String[2];
                tmp[0] = oneNumber;
                tmp[1] = twoNumber;
                twoGroup.add(tmp);
            }
        }
        //两个数组均已经ok
        List<String> oneList = new ArrayList<>();
        List<String> twoList = new ArrayList<>();
        if (oneGroup.size() == 1 && twoGroup.size() != 2) {
            for (int i = 0; i < twoGroup.size(); i++) {
                Object[] minus = ArrayUtils.minus(twoGroup.get(i), oneGroup.get(0));
                if (minus.length == 1 || minus.length == 2) {
                    oneList.add((String) minus[0]);
                }
            }
            //循环结束已经出现了其中一组,然后在准备第二组
            for (int i = 0; i < twoGroup.size(); i++) {
                Object[] minus = ArrayUtils.minus(twoGroup.get(i), Convert.toStrArray(oneList.toArray()));
                if (minus.length == 1) {
                    twoList.add((String) minus[0]);
                }
            }
        } else if (oneGroup.size() == 2) {
            oneList.add(oneGroup.get(0)[0]);
            oneList.add(oneGroup.get(1)[0]);
            for (int i = 0; i < twoGroup.size(); i++) {
                if (ArrayUtils.minus(twoGroup.get(i), Convert.toStrArray(oneList.toArray())).length == 1) {
                    twoList.add((String) (ArrayUtils.minus(twoGroup.get(i), Convert.toStrArray(oneList.toArray())))[0]);
                }
            }
        }
        if (twoGroup.size() == 3) {
            //能到了这里 说明肯定没有1
            if (ArrayUtils.minus(twoGroup.get(0), twoGroup.get(1)).length == 1) {
                Object[] minus = ArrayUtils.minus(ArrayUtils.union(twoGroup.get(0), twoGroup.get(1)), ArrayUtils.intersect(twoGroup.get(0), twoGroup.get(1)));
                oneList = Arrays.asList(Convert.toStrArray(minus));
                twoList = Arrays.asList(Convert.toStrArray(ArrayUtils.minus(ArrayUtils.union(twoGroup.get(0), twoGroup.get(1), twoGroup.get(2)), minus)));
            }
            if (ArrayUtils.minus(twoGroup.get(1), twoGroup.get(2)).length == 1) {
                Object[] minus = ArrayUtils.minus(ArrayUtils.union(twoGroup.get(1), twoGroup.get(2)), ArrayUtils.intersect(twoGroup.get(1), twoGroup.get(2)));
                oneList = Arrays.asList(Convert.toStrArray(minus));
                twoList = Arrays.asList(Convert.toStrArray(ArrayUtils.minus(ArrayUtils.union(twoGroup.get(0), twoGroup.get(1), twoGroup.get(2)), minus)));
            }
            if (ArrayUtils.minus(twoGroup.get(0), twoGroup.get(2)).length == 1) {
                Object[] minus = ArrayUtils.minus(ArrayUtils.union(twoGroup.get(0), twoGroup.get(2)), ArrayUtils.intersect(twoGroup.get(0), twoGroup.get(2)));
                oneList = Arrays.asList(Convert.toStrArray(minus));
                twoList = Arrays.asList(Convert.toStrArray(ArrayUtils.minus(ArrayUtils.union(twoGroup.get(0), twoGroup.get(1), twoGroup.get(2)), minus)));
            }
        } else if (twoGroup.size() == 2) {
            oneList = Arrays.asList(Convert.toStrArray(ArrayUtils.intersect(twoGroup.get(0), twoGroup.get(1))));
            twoList = Arrays.asList(Convert.toStrArray(ArrayUtils.minus(ArrayUtils.union(twoGroup.get(0), twoGroup.get(1)), ArrayUtils.intersect(twoGroup.get(0), twoGroup.get(1)))));
        }
        Map<String, List<String>> map = new HashMap<>();
        map.put("oneGroup", oneList);
        map.put("twoGroup", twoList);
        return map;
    }

    public NumberGroup getNumberGroupFromFiveNumbers(String[] target) {
        String[] dashu = {"06", "07", "08", "09", "10", "11"};
        String[] xiaoshu = {"01", "02", "03", "04", "05"};
        String[] zhishu = {"01", "02", "03", "05", "07", "11"};
        String[] heshu = {"04", "06", "08", "09", "10"};
        String[] oushu = {"02", "04", "06", "08", "10",};
        String[] jishu = {"01", "03", "05", "07", "09", "11"};
        NumberGroup group = new NumberGroup();
        group.setId(null);
        group.setJiGroup(Arrays.toString(ArrayUtils.intersect(jishu, target)));
        group.setOuGroup(Arrays.toString(ArrayUtils.intersect(oushu, target)));
        group.setZhiGroup(Arrays.toString(ArrayUtils.intersect(zhishu, target)));
        group.setHeGroup(Arrays.toString(ArrayUtils.intersect(heshu, target)));
        group.setDaGroup(Arrays.toString(ArrayUtils.intersect(dashu, target)));
        group.setXiaoGroup(Arrays.toString(ArrayUtils.intersect(xiaoshu, target)));
        group.setOuAmount(ArrayUtils.intersect(oushu, target).length);
        group.setJiAmount(ArrayUtils.intersect(jishu, target).length);
        group.setZhiAmount(ArrayUtils.intersect(zhishu, target).length);
        group.setHeAmount(ArrayUtils.intersect(heshu, target).length);
        group.setDaAmount(ArrayUtils.intersect(dashu, target).length);
        group.setXiaoAmount(ArrayUtils.intersect(xiaoshu, target).length);
        return group;
    }

    /***
     * 获取第三组和第四组数据的方法
     */
    public Map<String, List<String>> getThreeSixNineFromCurrent(String[] numbers) throws IOException {
        String[] group = {"03", "06", "09"};
        Map<String, List<String>> map = new HashMap<>();
        Object[] intersect = ArrayUtils.intersect(numbers, group);
        if (intersect.length == 1) {
            map.put("threeGroup", Arrays.asList(Convert.toStrArray(intersect)));
            map.put("fourGroup", Arrays.asList(Convert.toStrArray(ArrayUtils.minus(group, numbers))));
        } else if (intersect.length == 2) {
            List<String> list1 = new ArrayList<>();
            list1.add((String) intersect[0]);
            map.put("threeGroup", list1);
            List<String> list2 = new ArrayList<>();
            list2.add((String) intersect[1]);
            map.put("fourGroup", list2);
        }
        return map;
    }
}
