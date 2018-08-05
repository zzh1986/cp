package com.eleven.five.service;

import com.eleven.five.entity.*;
import com.eleven.five.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author zhaozhihong
 */
@Service
public class MaxSimularService {

    @Autowired
    private TenTongJiMapper tenTongJiMapper;

    @Autowired
    private ElevenNumberMapper elevenNumberMapper;

    @Autowired
    private ThreePeriodMapper threePeriodMapper;

    @Autowired
    private TongJiMapper tongJiMapper;

    @Autowired
    private TenTimesMapper tenTimesMapper;

    public String findMaxSimularNextPeriod() {

        List<TenTongJi> tenTongJiList = tenTongJiMapper.findAll();
        if (tenTongJiList.size() != 1) {
            return "近10次统计异常";
        }
        //查询出目标对象
        TenTongJi tenTongJi = tenTongJiList.get(0);
        //查询出elevenNumber进行对应的比较
        List<ElevenNumber> elevenNumberList = elevenNumberMapper.findAll();
        if (elevenNumberList.size() == 0) {
            return "10天的数据还未查出,请先查出来!!";
        }
        int[] count = new int[elevenNumberList.size()];
        for (int i = 0; i < elevenNumberList.size(); i++) {
            for (int j = 0; j < 11; j++) {
                if (elevenNumberList.get(i).getOneNum().equals(tenTongJi.getOne())) {
                    elevenNumberList.get(i).setOneNum(-1);
                    count[i]++;
                    continue;
                }
                if (elevenNumberList.get(i).getTwoNum().equals(tenTongJi.getTwo())) {
                    elevenNumberList.get(i).setTwoNum(-1);
                    count[i]++;
                    continue;
                }
                if (elevenNumberList.get(i).getThreeNum().equals(tenTongJi.getThree())) {
                    elevenNumberList.get(i).setThreeNum(-1);
                    count[i]++;
                    continue;
                }
                if (elevenNumberList.get(i).getFourNum().equals(tenTongJi.getFour())) {
                    elevenNumberList.get(i).setFourNum(-1);
                    count[i]++;
                    continue;
                }
                if (elevenNumberList.get(i).getFiveNum().equals(tenTongJi.getFive())) {
                    elevenNumberList.get(i).setFiveNum(-1);
                    count[i]++;
                    continue;
                }
                if (elevenNumberList.get(i).getSixNum().equals(tenTongJi.getSix())) {
                    elevenNumberList.get(i).setSixNum(-1);
                    count[i]++;
                    continue;
                }
                if (elevenNumberList.get(i).getSevenNum().equals(tenTongJi.getSeven())) {
                    elevenNumberList.get(i).setSevenNum(-1);
                    count[i]++;
                    continue;
                }
                if (elevenNumberList.get(i).getEightNum().equals(tenTongJi.getEight())) {
                    elevenNumberList.get(i).setEightNum(-1);
                    count[i]++;
                    continue;
                }
                if (elevenNumberList.get(i).getNineNum().equals(tenTongJi.getNine())) {
                    elevenNumberList.get(i).setNineNum(-1);
                    count[i]++;
                    continue;
                }
                if (elevenNumberList.get(i).getTenNum().equals(tenTongJi.getTen())) {
                    elevenNumberList.get(i).setTenNum(-1);
                    count[i]++;
                    continue;
                }
                if (elevenNumberList.get(i).getElevenNum().equals(tenTongJi.getEleven())) {
                    elevenNumberList.get(i).setElevenNum(-1);
                    count[i]++;
                    continue;
                }
            }
        }
        int[] result = count.clone();
        Arrays.sort(count);
        int max = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i] == count[count.length - 1]) {
                max = i;
                break;
            }
        }
        //TODO 20180801继续;
//        for (int i = 0; i < max.length; i++) {
//            System.out.println(i);
//        }
        ElevenNumber elevenNumber = elevenNumberList.get(max);
        //eleven 用来记录 相似的一组数据,需要通过下一组来实现数据的相应结果
        int[] eleven = new int[11];
        eleven[0] = elevenNumber.getOneNum() == -1 ? 1 : 0;
        eleven[1] = elevenNumber.getTwoNum() == -1 ? 1 : 0;
        eleven[2] = elevenNumber.getThreeNum() == -1 ? 1 : 0;
        eleven[3] = elevenNumber.getFourNum() == -1 ? 1 : 0;
        eleven[4] = elevenNumber.getFiveNum() == -1 ? 1 : 0;
        eleven[5] = elevenNumber.getSixNum() == -1 ? 1 : 0;
        eleven[6] = elevenNumber.getSevenNum() == -1 ? 1 : 0;
        eleven[7] = elevenNumber.getEightNum() == -1 ? 1 : 0;
        eleven[8] = elevenNumber.getNineNum() == -1 ? 1 : 0;
        eleven[9] = elevenNumber.getTenNum() == -1 ? 1 : 0;
        eleven[10] = elevenNumber.getElevenNum() == -1 ? 1 : 0;
        if (max < elevenNumberList.size()) {
            ElevenNumber elevenNumberNext = elevenNumberList.get(max + 1);
            System.out.println(elevenNumberNext.getPeriod());
        }
        return Arrays.toString(eleven);

    }

    //TODO 方案二:需要模糊查询 可以现在service 做起来
    //两层for循环 可以实现,但是我不想用!!!
    public String getFourNumberSimular() {
        //1.查询出目标sort
        List<TenTongJi> tenTongJiList = tenTongJiMapper.findAll();
        if (tenTongJiList.size() != 1) {
            return "统计出现异常,需要先进行统计才可以";
        }
        TenTongJi tenTongJi = tenTongJiList.get(0);
        String str = "";
        int begin = -1;
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0:
                    str = tenTongJi.getSort().substring(0 + i, 4 + i) + "_______";
                    begin = i;
                    break;
                case 1:
                    str = "_" + tenTongJi.getSort().substring(0 + i, 4 + i) + "______";
                    begin = i;
                    break;
                case 2:
                    str = "__" + tenTongJi.getSort().substring(0 + i, 4 + i) + "_____";
                    begin = i;
                    break;
                case 3:
                    str = "___" + tenTongJi.getSort().substring(0 + i, 4 + i) + "____";
                    begin = i;
                    break;
                case 4:
                    str = "____" + tenTongJi.getSort().substring(0 + i, 4 + i) + "___";
                    begin = i;
                    break;
                case 5:
                    str = "_____" + tenTongJi.getSort().substring(0 + i, 4 + i) + "__";
                    begin = i;
                    break;
                case 6:
                    str = "______" + tenTongJi.getSort().substring(0 + i, 4 + i) + "_";
                    begin = i;
                    break;
                default:
                    str = "_______" + tenTongJi.getSort().substring(0 + i, 4 + i);
                    begin = i;
            }
            List<ElevenNumber> elevenNumberList = elevenNumberMapper.findPeriodLikeFourSort(str);
            String periodLatest = tenTimesMapper.findPeriodLatest();
            if (elevenNumberList.size() > 0) {
                String result = String.valueOf(Long.valueOf(elevenNumberList.get(0).getSort()) + 1);
                //TODO 里面需要做逻辑与运算的判断,看看应该怎样操作
                //首先两个对象每一位分别进行异或然后取反(用三目运算)得到的数据需要用三位得出的结果继续逻辑运算(算法待定)
                //1.先出第一组数据
                ElevenNumber elevenNumber = elevenNumberList.get(0);
                int[] oneXOR = new int[11];
                oneXOR[0] = (elevenNumber.getOneNum().equals(tenTongJi.getOne())) ? 1 : 0;
                oneXOR[1] = (elevenNumber.getTwoNum().equals(tenTongJi.getTwo())) ? 1 : 0;
                oneXOR[2] = (elevenNumber.getThreeNum().equals(tenTongJi.getThree())) ? 1 : 0;
                oneXOR[3] = (elevenNumber.getFourNum().equals(tenTongJi.getFour())) ? 1 : 0;
                oneXOR[4] = (elevenNumber.getFiveNum().equals(tenTongJi.getFive())) ? 1 : 0;
                oneXOR[5] = (elevenNumber.getSixNum().equals(tenTongJi.getSix())) ? 1 : 0;
                oneXOR[6] = (elevenNumber.getSevenNum().equals(tenTongJi.getSeven())) ? 1 : 0;
                oneXOR[7] = (elevenNumber.getEightNum().equals(tenTongJi.getEight())) ? 1 : 0;
                oneXOR[8] = (elevenNumber.getNineNum().equals(tenTongJi.getNine())) ? 1 : 0;
                oneXOR[9] = (elevenNumber.getTenNum().equals(tenTongJi.getTen())) ? 1 : 0;
                oneXOR[10] = (elevenNumber.getElevenNum().equals(tenTongJi.getEleven())) ? 1 : 0;

                int[] twoXOR = new int[11];
                ThreePeriod threePeriod1 = new ThreePeriod();
                threePeriod1.setPeriod(elevenNumberList.get(0).getPeriod());
                Example<ThreePeriod> example = Example.of(threePeriod1);
                ThreePeriod threePeriod = threePeriodMapper.findOne(example).get();
                TongJi tongJi = tongJiMapper.findAll().get(0);
                twoXOR[0] = (threePeriod.getOneNum().equals(tongJi.getOne())) ? 1 : 0;
                twoXOR[1] = (threePeriod.getTwoNum().equals(tongJi.getTwo())) ? 1 : 0;
                twoXOR[2] = (threePeriod.getThreeNum().equals(tongJi.getThree())) ? 1 : 0;
                twoXOR[3] = (threePeriod.getFourNum().equals(tongJi.getFour())) ? 1 : 0;
                twoXOR[4] = (threePeriod.getFiveNum().equals(tongJi.getFive())) ? 1 : 0;
                twoXOR[5] = (threePeriod.getSixNum().equals(tongJi.getSix())) ? 1 : 0;
                twoXOR[6] = (threePeriod.getSevenNum().equals(tongJi.getSeven())) ? 1 : 0;
                twoXOR[7] = (threePeriod.getEightNum().equals(tongJi.getEight())) ? 1 : 0;
                twoXOR[8] = (threePeriod.getNineNum().equals(tongJi.getNine())) ? 1 : 0;
                twoXOR[9] = (threePeriod.getTenNum().equals(tongJi.getTen())) ? 1 : 0;
                twoXOR[10] = (threePeriod.getElevenNum().equals(tongJi.getEleven())) ? 1 : 0;
                //TODO---> 1 这边做一下尝试性的测试.
                //No.1 试一下与操作
                List<Integer> target = new ArrayList<>();
                for (int j = 0; j < 11; j++) {
                    int no1 = (oneXOR[j] & twoXOR[j]) * (j + 1);
                    if (no1 != 0) {
                        target.add(no1);
                    }
                }
                //TODO---> 2
                //No.2 尝试另一种与操作
                int[] threeXOR = new int[11];
                TenTimes tenTimes =tenTimesMapper.findPeriodOldest();
                threeXOR[0]=tenTimes.getOneTen();
                threeXOR[1]=tenTimes.getTwoTen();
                threeXOR[2]=tenTimes.getThreeTen();
                threeXOR[3]=tenTimes.getFourTen();
                threeXOR[4]=tenTimes.getFiveTen();
                threeXOR[5]=tenTimes.getSixTen();
                threeXOR[6]=tenTimes.getSevenTen();
                threeXOR[7]=tenTimes.getEightTen();
                threeXOR[8]=tenTimes.getNineTen();
                threeXOR[9]=tenTimes.getTenTen();
                threeXOR[10]=tenTimes.getElevenTen();

                List<Integer> targetTwo = new ArrayList<>();
                for (int j = 0; j < 11; j++) {
                    int no2 = (oneXOR[j] & threeXOR[j]) * (j + 1);
                    if (no2 != 0) {
                        targetTwo.add(no2);
                    }
                }


                if (target.size() == 0) {
                    return "第"+ (Long.valueOf(periodLatest)+1) + "期,没有合适的号码,不太适合选择--->第二组:"+targetTwo.toString();
                } else if (target.size() == 2) {

                    return "第"+ (Long.valueOf(periodLatest)+1) + "期建议选择后面号码:" + target.toString()+"--->第二组:"+targetTwo.toString();
                } else if (target.size() == 1) {
                    return "第"+ (Long.valueOf(periodLatest)+1) + "期不建议选择这个号码:" + target.toString()+"--->第二组:"+targetTwo.toString();
                } else if (target.size() > 2) {
                    return "第"+ (Long.valueOf(periodLatest)+1) + "期请考虑还其他方案,因为号码太多:" + target.toString()+"--->第二组:"+targetTwo.toString();
                }


//                System.out.println();
//                System.out.println("===========================================");
//                System.out.println(result);
//                return result + "开始于:" + (begin + 1) + ";结束于:" + (begin + 4);
            }
        }
        return "不好意思 没匹配到哦";
    }


}
