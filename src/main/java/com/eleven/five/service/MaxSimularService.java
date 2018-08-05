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
    private TenNumberMapper tenNumberMapper;

    @Autowired
    private ThreePeriodMapper threePeriodMapper;

    @Autowired
    private TongJiMapper tongJiMapper;

    @Autowired
    private TenTimesMapper tenTimesMapper;

    @Autowired
    private ElevenTongJiMapper elevenTongJiMapper;

    @Autowired
    private ElevenNumberMapper elevenNumberMapper;

    @Autowired
    private ElevenThreePeriodMapper elevenThreePeriodMapper;

    @Autowired
    private ElevenTimesMapper elevenTimesMapper;

    public String findMaxSimularNextPeriod() {

        List<TenTongJi> tenTongJiList = tenTongJiMapper.findAll();
        if (tenTongJiList.size() != 1) {
            return "近10次统计异常";
        }
        //查询出目标对象
        TenTongJi tenTongJi = tenTongJiList.get(0);
        //查询出elevenNumber进行对应的比较
        List<TenNumber> tenNumberList = tenNumberMapper.findAll();
        if (tenNumberList.size() == 0) {
            return "10天的数据还未查出,请先查出来!!";
        }
        int[] count = new int[tenNumberList.size()];
        for (int i = 0; i < tenNumberList.size(); i++) {
            for (int j = 0; j < 11; j++) {
                if (tenNumberList.get(i).getOneNum().equals(tenTongJi.getOne())) {
                    tenNumberList.get(i).setOneNum(-1);
                    count[i]++;
                    continue;
                }
                if (tenNumberList.get(i).getTwoNum().equals(tenTongJi.getTwo())) {
                    tenNumberList.get(i).setTwoNum(-1);
                    count[i]++;
                    continue;
                }
                if (tenNumberList.get(i).getThreeNum().equals(tenTongJi.getThree())) {
                    tenNumberList.get(i).setThreeNum(-1);
                    count[i]++;
                    continue;
                }
                if (tenNumberList.get(i).getFourNum().equals(tenTongJi.getFour())) {
                    tenNumberList.get(i).setFourNum(-1);
                    count[i]++;
                    continue;
                }
                if (tenNumberList.get(i).getFiveNum().equals(tenTongJi.getFive())) {
                    tenNumberList.get(i).setFiveNum(-1);
                    count[i]++;
                    continue;
                }
                if (tenNumberList.get(i).getSixNum().equals(tenTongJi.getSix())) {
                    tenNumberList.get(i).setSixNum(-1);
                    count[i]++;
                    continue;
                }
                if (tenNumberList.get(i).getSevenNum().equals(tenTongJi.getSeven())) {
                    tenNumberList.get(i).setSevenNum(-1);
                    count[i]++;
                    continue;
                }
                if (tenNumberList.get(i).getEightNum().equals(tenTongJi.getEight())) {
                    tenNumberList.get(i).setEightNum(-1);
                    count[i]++;
                    continue;
                }
                if (tenNumberList.get(i).getNineNum().equals(tenTongJi.getNine())) {
                    tenNumberList.get(i).setNineNum(-1);
                    count[i]++;
                    continue;
                }
                if (tenNumberList.get(i).getTenNum().equals(tenTongJi.getTen())) {
                    tenNumberList.get(i).setTenNum(-1);
                    count[i]++;
                    continue;
                }
                if (tenNumberList.get(i).getElevenNum().equals(tenTongJi.getEleven())) {
                    tenNumberList.get(i).setElevenNum(-1);
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
        TenNumber tenNumber = tenNumberList.get(max);
        //eleven 用来记录 相似的一组数据,需要通过下一组来实现数据的相应结果
        int[] eleven = new int[11];
        eleven[0] = tenNumber.getOneNum() == -1 ? 1 : 0;
        eleven[1] = tenNumber.getTwoNum() == -1 ? 1 : 0;
        eleven[2] = tenNumber.getThreeNum() == -1 ? 1 : 0;
        eleven[3] = tenNumber.getFourNum() == -1 ? 1 : 0;
        eleven[4] = tenNumber.getFiveNum() == -1 ? 1 : 0;
        eleven[5] = tenNumber.getSixNum() == -1 ? 1 : 0;
        eleven[6] = tenNumber.getSevenNum() == -1 ? 1 : 0;
        eleven[7] = tenNumber.getEightNum() == -1 ? 1 : 0;
        eleven[8] = tenNumber.getNineNum() == -1 ? 1 : 0;
        eleven[9] = tenNumber.getTenNum() == -1 ? 1 : 0;
        eleven[10] = tenNumber.getElevenNum() == -1 ? 1 : 0;
        if (max < tenNumberList.size()) {
            TenNumber tenNumberNext = tenNumberList.get(max + 1);
            System.out.println(tenNumberNext.getPeriod());
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
            List<TenNumber> tenNumberList = tenNumberMapper.findPeriodLikeFourSort(str);
            String periodLatest = tenTimesMapper.findPeriodLatest();
            if (tenNumberList.size() > 0) {
                //相同结果转成字符数组,实际上是Integer
                String result = String.valueOf(Long.valueOf(tenNumberList.get(0).getSort()) + 1);
                //TODO 里面需要做逻辑与运算的判断,看看应该怎样操作
                //首先两个对象每一位分别进行异或然后取反(用三目运算)得到的数据需要用三位得出的结果继续逻辑运算(算法待定)
                //1.先出第一组数据
                TenNumber tenNumber = tenNumberList.get(0);
                int[] oneXOR = new int[11];
                oneXOR[0] = (tenNumber.getOneNum().equals(tenTongJi.getOne())) ? 1 : 0;
                oneXOR[1] = (tenNumber.getTwoNum().equals(tenTongJi.getTwo())) ? 1 : 0;
                oneXOR[2] = (tenNumber.getThreeNum().equals(tenTongJi.getThree())) ? 1 : 0;
                oneXOR[3] = (tenNumber.getFourNum().equals(tenTongJi.getFour())) ? 1 : 0;
                oneXOR[4] = (tenNumber.getFiveNum().equals(tenTongJi.getFive())) ? 1 : 0;
                oneXOR[5] = (tenNumber.getSixNum().equals(tenTongJi.getSix())) ? 1 : 0;
                oneXOR[6] = (tenNumber.getSevenNum().equals(tenTongJi.getSeven())) ? 1 : 0;
                oneXOR[7] = (tenNumber.getEightNum().equals(tenTongJi.getEight())) ? 1 : 0;
                oneXOR[8] = (tenNumber.getNineNum().equals(tenTongJi.getNine())) ? 1 : 0;
                oneXOR[9] = (tenNumber.getTenNum().equals(tenTongJi.getTen())) ? 1 : 0;
                oneXOR[10] = (tenNumber.getElevenNum().equals(tenTongJi.getEleven())) ? 1 : 0;

                int[] twoXOR = new int[11];
                ThreePeriod threePeriod1 = new ThreePeriod();
                threePeriod1.setPeriod(tenNumberList.get(0).getPeriod());
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
                TenTimes tenTimes = tenTimesMapper.findPeriodOldest();
                threeXOR[0] = tenTimes.getOneTen();
                threeXOR[1] = tenTimes.getTwoTen();
                threeXOR[2] = tenTimes.getThreeTen();
                threeXOR[3] = tenTimes.getFourTen();
                threeXOR[4] = tenTimes.getFiveTen();
                threeXOR[5] = tenTimes.getSixTen();
                threeXOR[6] = tenTimes.getSevenTen();
                threeXOR[7] = tenTimes.getEightTen();
                threeXOR[8] = tenTimes.getNineTen();
                threeXOR[9] = tenTimes.getTenTen();
                threeXOR[10] = tenTimes.getElevenTen();

                List<Integer> targetTwo = new ArrayList<>();
                for (int j = 0; j < 11; j++) {
                    int no2 = (oneXOR[j] & threeXOR[j]) * (j + 1);
                    if (no2 != 0) {
                        targetTwo.add(no2);
                    }
                }


                if (target.size() == 0) {
                    return "第" + (Long.valueOf(periodLatest) + 1) + "期,没有合适的号码,不太适合选择--->第二组:" + targetTwo.toString();
                } else if (target.size() == 2) {
                    return "第" + (Long.valueOf(periodLatest) + 1) + "期建议选择后面号码:" + target.toString() + "--->第二组:" + targetTwo.toString();
                } else if (target.size() == 1) {
                    return "第" + (Long.valueOf(periodLatest) + 1) + "期不建议选择这个号码:" + target.toString() + "--->第二组:" + targetTwo.toString();
                } else if (target.size() > 2) {
                    return "第" + (Long.valueOf(periodLatest) + 1) + "期请考虑还其他方案,因为号码太多:" + target.toString() + "--->第二组:" + targetTwo.toString();
                }


                System.out.println();
                System.out.println("===========================================");
                System.out.println(result);
                return result + "开始于:" + (begin + 1) + ";结束于:" + (begin + 4);
            }
        }
        //TODO 这里进行11组数的判断,如果还没有,再考虑其他方案  暂时不实现
        return "不好意思 没匹配到哦";
    }


    public String getElevenFourNumberSimular() {
        //1.查询出目标sort
        List<ElevenTongJi> elevenTongJiList = elevenTongJiMapper.findAll();
        if (elevenTongJiList.size() != 1) {
            return "统计出现异常,需要先进行统计才可以";
        }
        ElevenTongJi elevenTongJi = elevenTongJiList.get(0);
        String str = "";
        int begin = -1;
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0:
                    str = elevenTongJi.getSort().substring(0 + i, 3 + i) + "________";
                    begin = i;
                    break;
                case 1:
                    str = "_" + elevenTongJi.getSort().substring(0 + i, 3 + i) + "_______";
                    begin = i;
                    break;
                case 2:
                    str = "__" + elevenTongJi.getSort().substring(0 + i, 3 + i) + "______";
                    begin = i;
                    break;
                case 3:
                    str = "___" + elevenTongJi.getSort().substring(0 + i, 3 + i) + "_____";
                    begin = i;
                    break;
                case 4:
                    str = "____" + elevenTongJi.getSort().substring(0 + i, 3 + i) + "____";
                    begin = i;
                    break;
                case 5:
                    str = "_____" + elevenTongJi.getSort().substring(0 + i, 3 + i) + "___";
                    begin = i;
                    break;
                case 6:
                    str = "______" + elevenTongJi.getSort().substring(0 + i, 3 + i) + "__";
                    begin = i;
                    break;
                case 7:
                    str = "_______" + elevenTongJi.getSort().substring(0 + i, 3 + i) + "_";
                    begin = i;
                default:
                    str = "________" + elevenTongJi.getSort().substring(0 + i, 3 + i);
                    begin = i;
            }
            List<ElevenNumber> elevenNumberList = elevenNumberMapper.findPeriodLikeFourSort(str);
            String periodLatest = elevenTimesMapper.findPeriodLatest();
            if (elevenNumberList.size() > 0) {
                String result = String.valueOf(Long.valueOf(elevenNumberList.get(0).getSort()) + 1);
                //TODO 里面需要做逻辑与运算的判断,看看应该怎样操作
                //首先两个对象每一位分别进行异或然后取反(用三目运算)得到的数据需要用三位得出的结果继续逻辑运算(算法待定)
                //1.先出第一组数据
                ElevenNumber elevenNumber = elevenNumberList.get(0);
                int[] oneXOR = new int[11];
                oneXOR[0] = (elevenNumber.getOneNum().equals(elevenTongJi.getOne())) ? 1 : 0;
                oneXOR[1] = (elevenNumber.getTwoNum().equals(elevenTongJi.getTwo())) ? 1 : 0;
                oneXOR[2] = (elevenNumber.getThreeNum().equals(elevenTongJi.getThree())) ? 1 : 0;
                oneXOR[3] = (elevenNumber.getFourNum().equals(elevenTongJi.getFour())) ? 1 : 0;
                oneXOR[4] = (elevenNumber.getFiveNum().equals(elevenTongJi.getFive())) ? 1 : 0;
                oneXOR[5] = (elevenNumber.getSixNum().equals(elevenTongJi.getSix())) ? 1 : 0;
                oneXOR[6] = (elevenNumber.getSevenNum().equals(elevenTongJi.getSeven())) ? 1 : 0;
                oneXOR[7] = (elevenNumber.getEightNum().equals(elevenTongJi.getEight())) ? 1 : 0;
                oneXOR[8] = (elevenNumber.getNineNum().equals(elevenTongJi.getNine())) ? 1 : 0;
                oneXOR[9] = (elevenNumber.getTenNum().equals(elevenTongJi.getTen())) ? 1 : 0;
                oneXOR[10] = (elevenNumber.getElevenNum().equals(elevenTongJi.getEleven())) ? 1 : 0;

                int[] twoXOR = new int[11];
                ElevenThreePeriod threePeriod1 = new ElevenThreePeriod();
                threePeriod1.setPeriod(elevenNumberList.get(0).getPeriod());
                Example<ElevenThreePeriod> example = Example.of(threePeriod1);
                ElevenThreePeriod elevenThreePeriod = elevenThreePeriodMapper.findOne(example).get();
                ElevenTongJi eleventongJi = elevenTongJiMapper.findAll().get(0);
                twoXOR[0] = (elevenThreePeriod.getOneNum().equals(eleventongJi.getOne())) ? 1 : 0;
                twoXOR[1] = (elevenThreePeriod.getTwoNum().equals(eleventongJi.getTwo())) ? 1 : 0;
                twoXOR[2] = (elevenThreePeriod.getThreeNum().equals(eleventongJi.getThree())) ? 1 : 0;
                twoXOR[3] = (elevenThreePeriod.getFourNum().equals(eleventongJi.getFour())) ? 1 : 0;
                twoXOR[4] = (elevenThreePeriod.getFiveNum().equals(eleventongJi.getFive())) ? 1 : 0;
                twoXOR[5] = (elevenThreePeriod.getSixNum().equals(eleventongJi.getSix())) ? 1 : 0;
                twoXOR[6] = (elevenThreePeriod.getSevenNum().equals(eleventongJi.getSeven())) ? 1 : 0;
                twoXOR[7] = (elevenThreePeriod.getEightNum().equals(eleventongJi.getEight())) ? 1 : 0;
                twoXOR[8] = (elevenThreePeriod.getNineNum().equals(eleventongJi.getNine())) ? 1 : 0;
                twoXOR[9] = (elevenThreePeriod.getTenNum().equals(eleventongJi.getTen())) ? 1 : 0;
                twoXOR[10] = (elevenThreePeriod.getElevenNum().equals(eleventongJi.getEleven())) ? 1 : 0;
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
                ElevenTimes elevenTimes = elevenTimesMapper.findPeriodOldest();
                threeXOR[0] = elevenTimes.getOneTen();
                threeXOR[1] = elevenTimes.getTwoTen();
                threeXOR[2] = elevenTimes.getThreeTen();
                threeXOR[3] = elevenTimes.getFourTen();
                threeXOR[4] = elevenTimes.getFiveTen();
                threeXOR[5] = elevenTimes.getSixTen();
                threeXOR[6] = elevenTimes.getSevenTen();
                threeXOR[7] = elevenTimes.getEightTen();
                threeXOR[8] = elevenTimes.getNineTen();
                threeXOR[9] = elevenTimes.getTenTen();
                threeXOR[10] = elevenTimes.getElevenTen();

                List<Integer> targetTwo = new ArrayList<>();
                for (int j = 0; j < 11; j++) {
                    int no2 = (oneXOR[j] & threeXOR[j]) * (j + 1);
                    if (no2 != 0) {
                        targetTwo.add(no2);
                    }
                }


                if (target.size() == 0) {
                    return "第" + (Long.valueOf(periodLatest) + 1) + "期,没有合适的号码,不太适合选择--->第二组:" + targetTwo.toString();
                } else if (target.size() == 2) {

                    return "第" + (Long.valueOf(periodLatest) + 1) + "期建议选择后面号码:" + target.toString() + "--->第二组:" + targetTwo.toString();
                } else if (target.size() == 1) {
                    return "第" + (Long.valueOf(periodLatest) + 1) + "期不建议选择这个号码:" + target.toString() + "--->第二组:" + targetTwo.toString();
                } else if (target.size() > 2) {
                    return "第" + (Long.valueOf(periodLatest) + 1) + "期请考虑还其他方案,因为号码太多:" + target.toString() + "--->第二组:" + targetTwo.toString();
                }


//                System.out.println();
//                System.out.println("===========================================");
//                System.out.println(result);
//                return result + "开始于:" + (begin + 1) + ";结束于:" + (begin + 4);
            }
        }
        //TODO 这里进行11组数的判断,如果还没有,再考虑其他方案  暂时不实现
        return "不好意思,11组的也没匹配到哦";
    }
}
