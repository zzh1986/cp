package com.eleven.five.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.eleven.five.controller.TenTimesController;
import com.eleven.five.entity.*;
import com.eleven.five.mapper.*;
import com.eleven.five.util.ArrayUtils;
import com.eleven.five.util.FiveUtil;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhaozhihong
 */
@Service
public class MaxSimularService {

    private Logger log = LoggerFactory.getLogger(MaxSimularService.class);

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
    private ElevenMapper elevenMapper;

    @Autowired
    private TenTimesController tenTimesController;

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


    /**
     * 这里通过按顺序比较,查询到下一组在进行对应的1或0的比对,然后选出对应的数字
     *
     * @return
     */
    public String getHopeLastSimular() {
        List<TenTongJi> tenTongjiList = tenTongJiMapper.findAll();
        if (tenTongjiList.size() != 1) {
            return "统计没数据有误!请重新统计!";
        }

        TenTongJi tenTongJi = tenTongjiList.get(0);
        String sort = tenTongJi.getSort();
        for (int i = 0; i < sort.length(); i++) {
            String sortStr = "%" + sort.substring(i);
            List<TenNumber> tenNumberList = tenNumberMapper.findPeriodLikeFourSort(sortStr);
            if (tenNumberList.size() > 0) {
                //已找到 可能多个...
                int index = 0;
                Integer[][] three = new Integer[tenNumberList.size()][11];
                Integer[][] integers = new Integer[tenNumberList.size()][11];
                for (TenNumber tenNumber : tenNumberList) {
//                    if (sort.length() == 12) {
//                        char[] chars = sort.substring(i, 11).toCharArray();
//                        integers[index] = Convert.toIntArray(chars);
//                        Arrays.copyOf(integers[index], integers[index].length + 1);
//                        integers[index][integers[index].length - 1] = 10;
//                    } else {
//                        char[] chars = sort.substring(i).toCharArray();
//                        integers[index] = Convert.toIntArray(chars);
//                    }

                    //TODO 逻辑有问题!!!!!! 应该查询下一组数据 在这组中是多少次来进行判断下一次的数据为出现的数据 逻辑上将只有5个数字
                    if (tenNumber.getPeriod().endsWith("84")) {
                        continue;
                    }
                    long nextPeriod = Long.valueOf(tenNumber.getPeriod()) + 1;
                    Elevens elevens = new Elevens();
                    elevens.setId(null);
                    elevens.setOne(null);
                    elevens.setTwo(null);
                    elevens.setThree(null);
                    elevens.setFour(null);
                    elevens.setFive(null);
                    elevens.setSix(null);
                    elevens.setSeven(null);
                    elevens.setEight(null);
                    elevens.setNine(null);
                    elevens.setTen(null);
                    elevens.setEleven(null);
                    elevens.setPeriod(String.valueOf(nextPeriod));
                    Example<Elevens> elevensExample = Example.of(elevens);
                    Elevens elevensTagert = elevenMapper.findOne(elevensExample).get();

                    integers[index][0] = elevensTagert.getOne() == 1 ? 1 : -11;
                    integers[index][1] = elevensTagert.getTwo() == 1 ? 2 : -11;
                    integers[index][2] = elevensTagert.getThree() == 1 ? 3 : -11;
                    integers[index][3] = elevensTagert.getFour() == 1 ? 4 : -11;
                    integers[index][4] = elevensTagert.getFive() == 1 ? 5 : -11;
                    integers[index][5] = elevensTagert.getSix() == 1 ? 6 : -11;
                    integers[index][6] = elevensTagert.getSeven() == 1 ? 7 : -11;
                    integers[index][7] = elevensTagert.getEight() == 1 ? 8 : -11;
                    integers[index][8] = elevensTagert.getNine() == 1 ? 9 : -11;
                    integers[index][9] = elevensTagert.getTen() == 1 ? 10 : -11;
                    integers[index][10] = elevensTagert.getEleven() == 1 ? 11 : -11;


                    /**三组数据的可以暂时先不考虑 试试
                     * */
                    ThreePeriod threePeriod = new ThreePeriod();
                    threePeriod.setId(null);
                    threePeriod.setOneNum(null);
                    threePeriod.setTwoNum(null);
                    threePeriod.setThreeNum(null);
                    threePeriod.setFourNum(null);
                    threePeriod.setFiveNum(null);
                    threePeriod.setSixNum(null);
                    threePeriod.setSevenNum(null);
                    threePeriod.setEightNum(null);
                    threePeriod.setNineNum(null);
                    threePeriod.setTenNum(null);
                    threePeriod.setElevenNum(null);
                    threePeriod.setPeriod(tenNumber.getPeriod());
                    Example<ThreePeriod> example = Example.of(threePeriod);
                    ThreePeriod threePeriodTagert = threePeriodMapper.findOne(example).get();
                    //在查询
                    TongJi tongJi = tongJiMapper.findAll().get(0);
                    //这个匹配不到直接赋值 -3

                    three[index][0] = tongJi.getOne().equals(threePeriodTagert.getOneNum()) ? 1 : -3;
                    three[index][1] = tongJi.getTwo().equals(threePeriodTagert.getTwoNum()) ? 2 : -3;
                    three[index][2] = tongJi.getThree().equals(threePeriodTagert.getThreeNum()) ? 3 : -3;
                    three[index][3] = tongJi.getFour().equals(threePeriodTagert.getFourNum()) ? 4 : -3;
                    three[index][4] = tongJi.getFive().equals(threePeriodTagert.getFiveNum()) ? 5 : -3;
                    three[index][5] = tongJi.getSix().equals(threePeriodTagert.getSixNum()) ? 6 : -3;
                    three[index][6] = tongJi.getSeven().equals(threePeriodTagert.getSevenNum()) ? 7 : -3;
                    three[index][7] = tongJi.getEight().equals(threePeriodTagert.getEightNum()) ? 8 : -3;
                    three[index][8] = tongJi.getNine().equals(threePeriodTagert.getNineNum()) ? 9 : -3;
                    three[index][9] = tongJi.getTen().equals(threePeriodTagert.getTenNum()) ? 10 : -3;
                    three[index][10] = tongJi.getEleven().equals(threePeriodTagert.getElevenNum()) ? 11 : -3;

                    index++;
                }

                if (three != null && integers != null && three.length != 0 && integers.length != 0) {
                    Object[][] result = new Object[three.length][];
                    for (int j = 0; j < three.length; j++) {
                        String[] threeStr = Convert.toStrArray(three[j]);
                        String[] elevenStr = Convert.toStrArray(integers[j]);
                        result[i] = ArrayUtils.intersect(threeStr, elevenStr);
                    }
                    return Arrays.toString(result);
                }
            }
        }
        return "实在是匹配不到合适的哦!";
    }

    /**
     * 查询sort的相同的数据的下一组,按照是否在sort,如果在sort中,则结果相同,否则结果不同
     *
     * @return
     */
    public String getTenMaxSimular() {
        List<TenTongJi> tenTongjiList = tenTongJiMapper.findAll();
        if (tenTongjiList.size() != 1) {
            return "统计没数据有误!请重新统计!";
        }

        TenTongJi tenTongJi = tenTongjiList.get(0);
        String sort = tenTongJi.getSort();
        Integer[][] integers = null;
        Map<Integer, String> map = new HashMap<>();
        //查询当前数据
        TenTimes tenTimes = tenTimesMapper.findTenTimesLatest();
        //保存当前数据的数组
        String[] eleven = getLatestTenTimes(tenTimes);

        for (int i = 0; i < sort.length(); i++) {
            String sortStr = "%" + sort.substring(i);
            List<TenNumber> tenNumberList = tenNumberMapper.findPeriodLikeFourSort(sortStr);
            if (tenNumberList.size() > 0) {
                //已找到 可能多个...
                int index = 0;
                integers = new Integer[tenNumberList.size()][11];
                for (TenNumber tenNumber : tenNumberList) {
                    if (tenNumber.getPeriod().endsWith("84")) {
                        continue;
                    }

                    long nextPeriod = Long.valueOf(tenNumber.getPeriod()) + 1;
                    Elevens elevens = new Elevens();
                    elevens.setId(null);
                    elevens.setOne(null);
                    elevens.setTwo(null);
                    elevens.setThree(null);
                    elevens.setFour(null);
                    elevens.setFive(null);
                    elevens.setSix(null);
                    elevens.setSeven(null);
                    elevens.setEight(null);
                    elevens.setNine(null);
                    elevens.setTen(null);
                    elevens.setEleven(null);
                    elevens.setPeriod(tenNumber.getPeriod());
                    Example<Elevens> elevensExample = Example.of(elevens);
                    Elevens elevensTagert = elevenMapper.findOne(elevensExample).get();

                    integers[index][0] = elevensTagert.getOne() == 1 && sort.substring(i).indexOf(String.valueOf(tenNumber.getOneNum())) != -1 ? 1 : -11;
                    integers[index][1] = elevensTagert.getTwo() == 1 && sort.substring(i).indexOf(String.valueOf(tenNumber.getTwoNum())) != -1 ? 2 : -11;
                    integers[index][2] = elevensTagert.getThree() == 1 && sort.substring(i).indexOf(String.valueOf(tenNumber.getThreeNum())) != -1 ? 3 : -11;
                    integers[index][3] = elevensTagert.getFour() == 1 && sort.substring(i).indexOf(String.valueOf(tenNumber.getFourNum())) != -1 ? 4 : -11;
                    integers[index][4] = elevensTagert.getFive() == 1 && sort.substring(i).indexOf(String.valueOf(tenNumber.getFiveNum())) != -1 ? 5 : -11;
                    integers[index][5] = elevensTagert.getSix() == 1 && sort.substring(i).indexOf(String.valueOf(tenNumber.getSixNum())) != -1 ? 6 : -11;
                    integers[index][6] = elevensTagert.getSeven() == 1 && sort.substring(i).indexOf(String.valueOf(tenNumber.getSevenNum())) != -1 ? 7 : -11;
                    integers[index][7] = elevensTagert.getEight() == 1 && sort.substring(i).indexOf(String.valueOf(tenNumber.getEightNum())) != -1 ? 8 : -11;
                    integers[index][8] = elevensTagert.getNine() == 1 && sort.substring(i).indexOf(String.valueOf(tenNumber.getNineNum())) != -1 ? 9 : -11;
                    integers[index][9] = elevensTagert.getTen() == 1 && sort.substring(i).indexOf(String.valueOf(tenNumber.getTenNum())) != -1 ? 10 : -11;
                    integers[index][10] = elevensTagert.getEleven() == 1 && sort.substring(i).indexOf(String.valueOf(tenNumber.getElevenNum())) != -1 ? 11 : -11;
                    //直接在这里取交集
                    String[] integerStr = Convert.toStrArray(integers[index]);
                    Object[] intersect = ArrayUtils.intersect(integerStr, eleven);
                    map.put(intersect.length, String.valueOf(nextPeriod));
                    index++;
                }
                break;
            }

        }
        if (integers == null) {
            return "没匹配到合适的数据";
        }
        Integer[] max = new Integer[map.size()];
        int asc = 0;
        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> next = iterator.next();
            max[asc++] = next.getKey();
        }
        Arrays.sort(max);
        String nextPeriod = map.get(max[max.length - 1]);
        Elevens eleven1 = new Elevens();
        eleven1.setId(null);
        eleven1.setOne(null);
        eleven1.setTwo(null);
        eleven1.setThree(null);
        eleven1.setFour(null);
        eleven1.setFive(null);
        eleven1.setSix(null);
        eleven1.setSeven(null);
        eleven1.setEight(null);
        eleven1.setNine(null);
        eleven1.setTen(null);
        eleven1.setEleven(null);
        eleven1.setPeriod(nextPeriod);
        Example<Elevens> elevensExample = Example.of(eleven1);
        Elevens elevens = elevenMapper.findOne(elevensExample).get();
        String target = "第" + (Long.valueOf(tenTimes.getPeriod()) + 1) + "期的目标数据为: [";
        target += elevens.getOne() == 1 ? "1," : "";
        target += elevens.getTwo() == 1 ? "2," : "";
        target += elevens.getThree() == 1 ? "3," : "";
        target += elevens.getFour() == 1 ? "4," : "";
        target += elevens.getFive() == 1 ? "5," : "";
        target += elevens.getSix() == 1 ? "6," : "";
        target += elevens.getSeven() == 1 ? "7," : "";
        target += elevens.getEight() == 1 ? "8," : "";
        target += elevens.getNine() == 1 ? "9," : "";
        target += elevens.getTen() == 1 ? "10," : "";
        target += elevens.getEleven() == 1 ? "11," : "";
        target += "];相似度为:" + max[max.length - 1] + ".";
        return target;
    }

    /**
     * 获取三组统计最大相似度的数据输出
     *
     * @return
     */
    public String getThreeMaxSimular() {
        List<TongJi> tongjiList = tongJiMapper.findAll();
        if (tongjiList.size() != 1) {
            return "统计没数据有误!请重新统计!";
        }

        TongJi tongJi = tongjiList.get(0);
        String sort = tongJi.getPeriod();
        Integer[][] integers = null;
        Map<Integer, String> map = new HashMap<>();
        //查询当前数据
        TenTimes tenTimes = tenTimesMapper.findTenTimesLatest();
        //保存当前数据的数组
        String[] eleven = getLatestTenTimes(tenTimes);

        for (int i = 0; i < sort.length(); i++) {
            String sortStr = "%" + sort.substring(i);
            List<ThreePeriod> threePeriodList = threePeriodMapper.findPeriodLikeFourSort(sortStr);
            if (threePeriodList.size() > 0) {
                //已找到 可能多个...
                int index = 0;
                integers = new Integer[threePeriodList.size()][11];
                for (ThreePeriod threePeriod : threePeriodList) {
                    if (threePeriod.getPeriod().endsWith("84")) {
                        continue;
                    }

                    long nextPeriod = Long.valueOf(threePeriod.getPeriod()) + 1;
                    Elevens elevens = new Elevens();
                    elevens.setId(null);
                    elevens.setOne(null);
                    elevens.setTwo(null);
                    elevens.setThree(null);
                    elevens.setFour(null);
                    elevens.setFive(null);
                    elevens.setSix(null);
                    elevens.setSeven(null);
                    elevens.setEight(null);
                    elevens.setNine(null);
                    elevens.setTen(null);
                    elevens.setEleven(null);
                    elevens.setPeriod(threePeriod.getPeriod());
                    Example<Elevens> elevensExample = Example.of(elevens);
                    Elevens elevensTagert = elevenMapper.findOne(elevensExample).get();

                    integers[index][0] = elevensTagert.getOne() == 1 && sort.substring(i).indexOf(String.valueOf(threePeriod.getOneNum())) != -1 ? 1 : -11;
                    integers[index][1] = elevensTagert.getTwo() == 1 && sort.substring(i).indexOf(String.valueOf(threePeriod.getTwoNum())) != -1 ? 2 : -11;
                    integers[index][2] = elevensTagert.getThree() == 1 && sort.substring(i).indexOf(String.valueOf(threePeriod.getThreeNum())) != -1 ? 3 : -11;
                    integers[index][3] = elevensTagert.getFour() == 1 && sort.substring(i).indexOf(String.valueOf(threePeriod.getFourNum())) != -1 ? 4 : -11;
                    integers[index][4] = elevensTagert.getFive() == 1 && sort.substring(i).indexOf(String.valueOf(threePeriod.getFiveNum())) != -1 ? 5 : -11;
                    integers[index][5] = elevensTagert.getSix() == 1 && sort.substring(i).indexOf(String.valueOf(threePeriod.getSixNum())) != -1 ? 6 : -11;
                    integers[index][6] = elevensTagert.getSeven() == 1 && sort.substring(i).indexOf(String.valueOf(threePeriod.getSevenNum())) != -1 ? 7 : -11;
                    integers[index][7] = elevensTagert.getEight() == 1 && sort.substring(i).indexOf(String.valueOf(threePeriod.getEightNum())) != -1 ? 8 : -11;
                    integers[index][8] = elevensTagert.getNine() == 1 && sort.substring(i).indexOf(String.valueOf(threePeriod.getNineNum())) != -1 ? 9 : -11;
                    integers[index][9] = elevensTagert.getTen() == 1 && sort.substring(i).indexOf(String.valueOf(threePeriod.getTenNum())) != -1 ? 10 : -11;
                    integers[index][10] = elevensTagert.getEleven() == 1 && sort.substring(i).indexOf(String.valueOf(threePeriod.getElevenNum())) != -1 ? 11 : -11;
                    //直接在这里取交集
                    String[] integerStr = Convert.toStrArray(integers[index]);
                    Object[] intersect = ArrayUtils.intersect(integerStr, eleven);
                    map.put(intersect.length, String.valueOf(nextPeriod));
                    index++;
                }
                break;
            }

        }
        if (integers == null) {
            return "没匹配到合适的数据";
        }
        Integer[] max = new Integer[map.size()];
        int asc = 0;
        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> next = iterator.next();
            max[asc++] = next.getKey();
        }
        Arrays.sort(max);
        String nextPeriod = map.get(max[max.length - 1]);
        Elevens eleven1 = new Elevens();
        eleven1.setId(null);
        eleven1.setOne(null);
        eleven1.setTwo(null);
        eleven1.setThree(null);
        eleven1.setFour(null);
        eleven1.setFive(null);
        eleven1.setSix(null);
        eleven1.setSeven(null);
        eleven1.setEight(null);
        eleven1.setNine(null);
        eleven1.setTen(null);
        eleven1.setEleven(null);
        eleven1.setPeriod(nextPeriod);
        Example<Elevens> elevensExample = Example.of(eleven1);
        Elevens elevens = elevenMapper.findOne(elevensExample).get();
        String target = "第" + (Long.valueOf(tenTimes.getPeriod()) + 1) + "期的目标数据为: [";
        target += elevens.getOne() == 1 ? "1," : "";
        target += elevens.getTwo() == 1 ? "2," : "";
        target += elevens.getThree() == 1 ? "3," : "";
        target += elevens.getFour() == 1 ? "4," : "";
        target += elevens.getFive() == 1 ? "5," : "";
        target += elevens.getSix() == 1 ? "6," : "";
        target += elevens.getSeven() == 1 ? "7," : "";
        target += elevens.getEight() == 1 ? "8," : "";
        target += elevens.getNine() == 1 ? "9," : "";
        target += elevens.getTen() == 1 ? "10," : "";
        target += elevens.getEleven() == 1 ? "11," : "";
        target += "];相似度为:" + max[max.length - 1] + ".";
        return target;


    }

    /**
     * 将获取到的最新的一期数据转成字符串数组
     *
     * @param tenTimes
     * @return
     */
    public String[] getLatestTenTimes(TenTimes tenTimes) {
        String[] eleven = new String[11];
        eleven[0] = String.valueOf(tenTimes.getOneTen() == 1 ? 1 : -1);
        eleven[1] = String.valueOf(tenTimes.getTwoTen() == 1 ? 2 : -1);
        eleven[2] = String.valueOf(tenTimes.getThreeTen() == 1 ? 3 : -1);
        eleven[3] = String.valueOf(tenTimes.getFourTen() == 1 ? 4 : -1);
        eleven[4] = String.valueOf(tenTimes.getFiveTen() == 1 ? 5 : -1);
        eleven[5] = String.valueOf(tenTimes.getSixTen() == 1 ? 6 : -1);
        eleven[6] = String.valueOf(tenTimes.getSevenTen() == 1 ? 7 : -1);
        eleven[7] = String.valueOf(tenTimes.getEightTen() == 1 ? 8 : -1);
        eleven[8] = String.valueOf(tenTimes.getNineTen() == 1 ? 9 : -1);
        eleven[9] = String.valueOf(tenTimes.getTenTen() == 1 ? 10 : -1);
        eleven[10] = String.valueOf(tenTimes.getElevenTen() == 1 ? 11 : -1);
        return eleven;
    }

    /**
     * 验证结果并返回
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String getVerification(String date) {
        int zeroFenZi = 0;
        int oneFenZi = 0;
        int oneFenZiIn = 0;
        int oneFenZiNotIn = 0;
        int twoFenZi = 0;
        int fenMu = 0;

        int threeZeroFenZi = 0;
        int threeOneFenZi = 0;
        int threeOneFenZiIn = 0;
        int threeOneFenZiNotIn = 0;
        int threeTwoFenZi = 0;
        int threeTwoFenZiZeroIn = 0;
        int threeTwoFenZiOneIn = 0;
        int threeTwoFenZiTwoIn = 0;
        int threeThreeFenZi = 0;
        int threeFenMu = 0;

        try {
            //少个save操作
            t1:
            for (int period = 10; period < 84; period++) {

                tenTimesController.saveTenTimes(date, String.valueOf(period));
                String tenMaxSimular = getTenMaxSimular();
                String threeMaxSimular = getThreeMaxSimular();
                String[] fiveFirst = tenMaxSimular.substring(tenMaxSimular.indexOf('[') + 1, tenMaxSimular.indexOf(']')).split(",");
                String[] fiveSecond = threeMaxSimular.substring(threeMaxSimular.indexOf('[') + 1, threeMaxSimular.indexOf(']')).split(",");
                //先出一个11个数字的数组
                String[] elevenStr = {"1","2","3","4","5","6","7","8","9","10","11"};
                Object[] result = ArrayUtils.intersect(fiveFirst, fiveSecond);
                Object[] firstMinus = ArrayUtils.minus(elevenStr, fiveFirst);
                Object[] secondMinus = ArrayUtils.minus(elevenStr, fiveSecond);
                Object[] buJi = ArrayUtils.intersect(firstMinus, secondMinus);

                TenTimes tenTimesLatest = tenTimesMapper.findTenTimesLatest();
                String[] latestTenTimes = getLatestTenTimes(tenTimesLatest);
                Integer[] latestIntTenTimes = Convert.toIntArray(latestTenTimes);
                if (result.length == 2) {
                    //TODO 需要进行再次爬取数据进行验证.
                    String url = UrlDateEnum.URL_ENUM.getMsg() + date + ".html";
                    Elements elements = Jsoup.connect(url).get().select("[data-period=" + date.substring(2) + (period + 1) + "]");
                    String award = elements.get(0).attr("data-award");
                    if (StringUtils.isEmpty(award) || award.length() < 10) {
                        break t1;
                    }
                    String[] awardArray = award.split("[\\s]+");
                    Integer[] awardInt = Convert.toIntArray(awardArray);
                    Integer[] resultInt = Convert.toIntArray(result);

                    if (ArrayUtils.intersect(resultInt, awardInt).length == 0) {
                        zeroFenZi++;
                    }
                    //只有一个数字中奖,如果中奖数字,只有两种情况,在里面(6)和不在里面(7),结果出了两个数字
                    if (ArrayUtils.intersect(resultInt, awardInt).length == 1) {
                        //只有一个数字中奖,如果中奖数字,只有两种情况,在里面(6)和不在里面(7)
                        if (ArrayUtils.intersect(resultInt, awardInt, latestIntTenTimes).length == 1) {
                            oneFenZiIn++;
                        }
                        if (ArrayUtils.intersect(resultInt, awardInt, latestIntTenTimes).length == 0) {
                            oneFenZiNotIn++;
                        }
                        oneFenZi++;
                    }
                    // 关联的一起改  ============ awardInt.length
                    if (ArrayUtils.intersect(resultInt, awardInt).length == 2) {
                        twoFenZi++;
                    }
                    fenMu++;
                }
                if (result.length == 3) {
                    //TODO 需要进行再次爬取数据进行验证.
                    String url = UrlDateEnum.URL_ENUM.getMsg() + date + ".html";

                    Elements elements = Jsoup.connect(url).get().select("[data-period=" + date.substring(2) + (period + 1) + "]");
                    String award = elements.get(0).attr("data-award");
                    if (StringUtils.isEmpty(award) || award.length() < 10) {
                        break t1;
                    }
                    String[] awardArray = award.split("[\\s]+");
                    Integer[] awardInt = Convert.toIntArray(awardArray);
                    Integer[] resultInt = Convert.toIntArray(result);

                    // 关联的一起改  ============ awardInt.length
                    if (ArrayUtils.union(resultInt, awardInt).length == awardInt.length) {
                        threeThreeFenZi++;
                    }
                    if (ArrayUtils.union(resultInt, awardInt).length == awardInt.length + 1) {
                        switch (ArrayUtils.intersect(resultInt, awardInt, latestIntTenTimes).length) {
                            case 0:
                                threeTwoFenZiZeroIn++;
                                break;
                            case 1:
                                threeTwoFenZiOneIn++;
                                break;
                            default:
                                threeTwoFenZiTwoIn++;
                        }
                        threeTwoFenZi++;
                    }
                    if (ArrayUtils.union(resultInt, awardInt).length == awardInt.length + 2) {
                        if (ArrayUtils.intersect(resultInt, awardInt, latestIntTenTimes).length == 1) {
                            threeOneFenZiIn++;
                        } else {
                            threeOneFenZiNotIn++;
                        }
                        threeOneFenZi++;
                    }
                    if (ArrayUtils.union(resultInt, awardInt).length == awardInt.length + 3) {
                        threeZeroFenZi++;
                    }
                    threeFenMu++;
                }
            }
        } catch (Exception e) {
            log.error("抓取下一期的数据异常!");
            e.printStackTrace();
        }
        final double twoPercent = twoFenZi * 1.0 / fenMu;
        final double onePercent = oneFenZi * 1.0 / fenMu;
        final double oneInPercent = oneFenZiIn * 1.0 / fenMu;
        final double oneNotInPercent = oneFenZiNotIn * 1.0 / fenMu;
        final double zeroPercent = zeroFenZi * 1.0 / fenMu;
        final double threeThreePercent = threeThreeFenZi * 1.0 / threeFenMu;
        final double threeTwoZeroPercent = threeTwoFenZiZeroIn * 1.0 / threeFenMu;
        final double threeTwoOnePercent = threeTwoFenZiOneIn * 1.0 / threeFenMu;
        final double threeTwoTwoPercent = threeTwoFenZiTwoIn * 1.0 / threeFenMu;
        final double threeTwoPercent = threeTwoFenZi * 1.0 / threeFenMu;
        final double threeOneInPercent = threeOneFenZiIn * 1.0 / threeFenMu;
        final double threeOneNotInPercent = threeOneFenZiNotIn * 1.0 / threeFenMu;
        final double threeOnePercent = threeOneFenZi * 1.0 / threeFenMu;
        final double threeZeroPercent = threeZeroFenZi * 1.0 / threeFenMu;

        NumberFormat pnf = NumberFormat.getPercentInstance();

        return date + "当天结果为2的成功率如下:\n 1.两次都出现的成功率为--"
                + pnf.format(twoPercent) + ";\n 2.一次出现的概率为--"
                + pnf.format(onePercent) + ":\n\t  2.1.一次出现在上次的概率为--"
                + pnf.format(oneInPercent) + ";\n\t  2.2一次没有出现在上次的概率为--"
                + pnf.format(oneNotInPercent) + ";\n 3.都没有出现在上次的概率为--"
                + pnf.format(zeroPercent) + ";\n 两次的情况总次数为:"
                + fenMu + "\n 当天结果为3的成功率如下:\n\t  3.1三次都出现在上次的成功率为--"
                + pnf.format(threeThreePercent) + ";\n\t  3.2三次中两次出现的概率为--"
                + pnf.format(threeTwoPercent) + ";\n\t  3.2.1三次中两次出现且不在上次的概率为--"
                + pnf.format(threeTwoZeroPercent) + ";\n\t\t 3.2.2三次中两次出现在一次在上次的概率为--"
                + pnf.format(threeTwoOnePercent) + ";\n\t\t  3.2.3三次中两次出现且都不在上次的概率为--"
                + pnf.format(threeTwoTwoPercent) + ";\n\t 3.3三次中一次出现的概率为--"
                + pnf.format(threeOnePercent) + ";\n\t\t  3.3.1三次中一次出现且在上次的概率为--"
                + pnf.format(threeOneInPercent) + ";\n\t\t  3.3.2三次中一次出现且不在上次的概率为--"
                + pnf.format(threeOneNotInPercent) + ";\n\t  3.4三次中没有出现在上次的概率为--"
                + pnf.format(threeZeroPercent) + ";\n 三次的情况总次数为:"
                + threeFenMu;
        /*if (result.length == 8) {
            //TODO 另一种用法
        }*/
    }

    public Map<String, Object> chooseTwoNumber() {
        try {
            //date 应该是 20180809
            Date today = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String date = sdf.format(today);
            String url = UrlDateEnum.URL_ENUM.getMsg() + date + ".html";
            Elements elements = Jsoup.connect(url).get().select("[data-period]");
            List<String> fiveList = new ArrayList<>();
            FiveUtil.getOneGroupNumber(fiveList, elements);
            int period = fiveList.size();
            tenTimesController.saveTenTimes(date, String.valueOf(period));
            String tenMaxSimular = getTenMaxSimular();
            String threeMaxSimular = getThreeMaxSimular();
            String[] fiveFirst = tenMaxSimular.substring(tenMaxSimular.indexOf('[') + 1, tenMaxSimular.indexOf(']')).split(",");
            String[] fiveSecond = threeMaxSimular.substring(threeMaxSimular.indexOf('[') + 1, threeMaxSimular.indexOf(']')).split(",");
            TenTimes tenTimesLatest = tenTimesMapper.findTenTimesLatest();
            String[] latestTenTimes = getLatestTenTimes(tenTimesLatest);
            Object[] intersect = ArrayUtils.intersect(fiveFirst, fiveSecond, latestTenTimes);
            String[] result = Convert.toStrArray(intersect);
            Map<String, Object> map = new HashMap<>();
            map.put("period", 20 + tenTimesLatest.getPeriod().substring(0,tenTimesLatest.getPeriod().length()-2)+"0"+String.valueOf(Long.valueOf(tenTimesLatest.getPeriod().substring(tenTimesLatest.getPeriod().length()-2)) + 1));
            List<String> numbers = new ArrayList<>();
            switch (result.length) {
                case 1:
                    numbers.add(result[0].length() == 1 ? ("0" + result[0]) : result[0]);
                    break;
                case 2:
                    numbers.add(result[0].length() == 1 ? ("0" + result[0]) : result[0]);
                    numbers.add(result[1].length() == 1 ? ("0" + result[1]) : result[1]);
                    break;
                default:
                    return null;
            }
            map.put("numbers", numbers);
            return map;
        } catch (Exception e) {
            log.error("数据爬取异常!!!");
            return null;
        }

    }

    public String getThreePercent(String date) {
        int zeroCount=0;
        int oneCount=0;
        int twoCount=0;
        int threeCount=0;
        int fourCount=0;

        int fourFenZi=0;
        int threeFenZi=0;
        int twoFenZi=0;
        int oneFenZi=0;
        int zeroBuFenZi=0;

        try {
            //少个save操作
            for (int period = 10; period < 84; period++) {

                tenTimesController.saveTenTimes(date, String.valueOf(period));
                //取统计中的4和5的所有数值放到集合中
                TenTongJi tenTongJi = tenTongJiMapper.findAll().get(0);
                List<Integer> tenTongJiFourList = new ArrayList<>();
                List<Integer> tenTongJiFiveList = new ArrayList<>();
                List<Integer> tenTongJiNumberList = new ArrayList<>();
                //+++++++++++++++++次数为4次的统计+++++++++++++++++++//
                if(tenTongJi.getOne()==4){
                    tenTongJiFourList.add(1);
                }
                if(tenTongJi.getTwo()==4){
                    tenTongJiFourList.add(2);
                }
                if(tenTongJi.getThree()==4){
                    tenTongJiFourList.add(3);
                }
                if(tenTongJi.getFour()==4){
                    tenTongJiFourList.add(4);
                }
                if(tenTongJi.getFive()==4){
                    tenTongJiFourList.add(5);
                }
                if(tenTongJi.getSix()==4){
                    tenTongJiFourList.add(6);
                }
                if(tenTongJi.getSeven()==4){
                    tenTongJiFourList.add(7);
                }
                if(tenTongJi.getEight()==4){
                    tenTongJiFourList.add(8);
                }
                if(tenTongJi.getNine()==4){
                    tenTongJiFourList.add(9);
                }
                if(tenTongJi.getTen()==4){
                    tenTongJiFourList.add(10);
                }
                if(tenTongJi.getEleven()==4){
                    tenTongJiFourList.add(11);
                }
                //+++++++++++++++++次数为5次的统计+++++++++++++++++++//
                if(tenTongJi.getOne()==5){
                    tenTongJiFiveList.add(1);
                }
                if(tenTongJi.getTwo()==5){
                    tenTongJiFiveList.add(2);
                }
                if(tenTongJi.getThree()==5){
                    tenTongJiFiveList.add(3);
                }
                if(tenTongJi.getFour()==5){
                    tenTongJiFiveList.add(4);
                }
                if(tenTongJi.getFive()==5){
                    tenTongJiFiveList.add(5);
                }
                if(tenTongJi.getSix()==5){
                    tenTongJiFiveList.add(6);
                }
                if(tenTongJi.getSeven()==5){
                    tenTongJiFiveList.add(7);
                }
                if(tenTongJi.getEight()==5){
                    tenTongJiFiveList.add(8);
                }
                if(tenTongJi.getNine()==5){
                    tenTongJiFiveList.add(9);
                }
                if(tenTongJi.getTen()==5){
                    tenTongJiFiveList.add(10);
                }
                if(tenTongJi.getEleven()==5){
                    tenTongJiFiveList.add(11);
                }
                //+++++++++++++++++++++结果集合的处理+++++++++++++++//
                if(tenTongJiFourList.size()>=5){
                    tenTongJiNumberList.addAll(tenTongJiFourList);
                }else if(tenTongJiFiveList.size()>=5){
                    tenTongJiNumberList.addAll(tenTongJiFiveList);
                }else{
                    tenTongJiNumberList.addAll(tenTongJiFourList);
                    tenTongJiNumberList.addAll(tenTongJiFiveList);
                }
                //取统计中的结果为1的放到集合中
                TongJi tongJi = tongJiMapper.findAll().get(0);
                List<Integer> tongJiList = new ArrayList<>();
                //+++++++++++++++++次数为5次的统计+++++++++++++++++++//
                if(tongJi.getOne()==1){
                    tongJiList.add(1);
                }
                if(tongJi.getTwo()==1){
                    tongJiList.add(2);
                }
                if(tongJi.getThree()==1){
                    tongJiList.add(3);
                }
                if(tongJi.getFour()==1){
                    tongJiList.add(4);
                }
                if(tongJi.getFive()==1){
                    tongJiList.add(5);
                }
                if(tongJi.getSix()==1){
                    tongJiList.add(6);
                }
                if(tongJi.getSeven()==1){
                    tongJiList.add(7);
                }
                if(tongJi.getEight()==1){
                    tongJiList.add(8);
                }
                if(tongJi.getNine()==1){
                    tongJiList.add(9);
                }
                if(tongJi.getTen()==1){
                    tongJiList.add(10);
                }
                if(tongJi.getEleven()==1){
                    tongJiList.add(11);
                }
                //将集合转化成数组,求交集 和与11 的补集
                //先出一个11个数字的数组
                String[] elevenStr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
                //先将集合转为数组

                String[] intersect = Convert.toStrArray(ArrayUtils.intersect(tenTongJiNumberList.toArray(), tongJiList.toArray()));
                //对结果进行分析和判断  可能有几种情况 0,1,2,3,4;最多应该集中在 2,3 上面
                //TODO 需要进行再次爬取数据进行验证.
                String url = UrlDateEnum.URL_ENUM.getMsg() + date + ".html";
                Elements elements = Jsoup.connect(url).get().select("[data-period=" + date.substring(2) + (period + 1) + "]");
                String award = elements.get(0).attr("data-award");
                String[] awardArray = award.split("[\\s]+");
                String[] awardStr = Convert.toStrArray(Convert.toIntArray(awardArray));
                if(intersect.length==4){
                    fourCount++;
                    if (ArrayUtils.intersect(intersect,awardStr).length>=2){
                        fourFenZi++;
                    }
                }
                if(intersect.length==3){
                    threeCount++;
                    if (ArrayUtils.intersect(intersect,awardStr).length>=2){
                        threeFenZi++;
                    }
                }
                if(intersect.length==2){
                    twoCount++;
                    if (ArrayUtils.intersect(intersect,awardStr).length>=1){
                        twoFenZi++;
                    }
                }
                if(intersect.length==1){
                    oneCount++;
                    if (ArrayUtils.intersect(intersect,awardStr).length>=1){
                        oneFenZi++;
                    }
                }
                if(intersect.length==0){
                    zeroCount++;
                    if(ArrayUtils.intersect(ArrayUtils.minus(elevenStr,Convert.toStrArray(ArrayUtils.union(tenTongJiNumberList.toArray(), tongJiList.toArray()))),awardArray).length==1){
                        zeroBuFenZi++;
                    }
                }

            }
            double fourPercent = fourFenZi * 1.0 / fourCount;
            double threePercent = threeFenZi * 1.0 / threeCount;
            double twoPercent = twoFenZi * 1.0 / twoCount;
            double onePercent = oneFenZi * 1.0 / oneCount;
            double zeroBuPercent = zeroBuFenZi * 1.0 / zeroCount;
            NumberFormat pnf = NumberFormat.getPercentInstance();

            return date + "当天结果次数及成功率如下:\n\t  1.四次都出现的次数为:"+fourCount+";成功率为--"
                    + pnf.format(fourPercent) + ";\n\t  2.三次出现的次数为:"+threeCount+";成功率为--"
                    + pnf.format(threePercent) + ":\n\t  3.两次出现的次数为:"+twoCount+";成功率为--"
                    + pnf.format(twoPercent) + ";\n\t  4.一次出现的次数为:"+oneCount+";成功率为--"
                    + pnf.format(onePercent) + ";\n\t 5.没有交集出现的次数--"+ zeroCount+";补集成功率为--"
                    + pnf.format(zeroBuPercent);
        } catch (Exception e) {
            log.error("抓取下一期的数据异常!");
            e.printStackTrace();
            return "抓取数据异常";
        }

    }

    public Map<String, Object> chooseTarget() {
        try {
            //date 应该是 20180809
            Date today = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String date = sdf.format(today);
            String url = UrlDateEnum.URL_ENUM.getMsg() + date + ".html";
            Elements elements = Jsoup.connect(url).get().select("[data-period]");
            List<String> fiveList = new ArrayList<>();
            FiveUtil.getOneGroupNumber(fiveList, elements);
            int period = fiveList.size();
            tenTimesController.saveTenTimes(date, String.valueOf(period));
            //取统计中的4和5的所有数值放到集合中
            TenTongJi tenTongJi = tenTongJiMapper.findAll().get(0);
            List<Integer> tenTongJiFourList = new ArrayList<>();
            List<Integer> tenTongJiFiveList = new ArrayList<>();
            List<Integer> tenTongJiNumberList = new ArrayList<>();
            //+++++++++++++++++次数为4次的统计+++++++++++++++++++//
            if(tenTongJi.getOne()==4){
                tenTongJiFourList.add(1);
            }
            if(tenTongJi.getTwo()==4){
                tenTongJiFourList.add(2);
            }
            if(tenTongJi.getThree()==4){
                tenTongJiFourList.add(3);
            }
            if(tenTongJi.getFour()==4){
                tenTongJiFourList.add(4);
            }
            if(tenTongJi.getFive()==4){
                tenTongJiFourList.add(5);
            }
            if(tenTongJi.getSix()==4){
                tenTongJiFourList.add(6);
            }
            if(tenTongJi.getSeven()==4){
                tenTongJiFourList.add(7);
            }
            if(tenTongJi.getEight()==4){
                tenTongJiFourList.add(8);
            }
            if(tenTongJi.getNine()==4){
                tenTongJiFourList.add(9);
            }
            if(tenTongJi.getTen()==4){
                tenTongJiFourList.add(10);
            }
            if(tenTongJi.getEleven()==4){
                tenTongJiFourList.add(11);
            }
            //+++++++++++++++++次数为5次的统计+++++++++++++++++++//
            if(tenTongJi.getOne()==5){
                tenTongJiFiveList.add(1);
            }
            if(tenTongJi.getTwo()==5){
                tenTongJiFiveList.add(2);
            }
            if(tenTongJi.getThree()==5){
                tenTongJiFiveList.add(3);
            }
            if(tenTongJi.getFour()==5){
                tenTongJiFiveList.add(4);
            }
            if(tenTongJi.getFive()==5){
                tenTongJiFiveList.add(5);
            }
            if(tenTongJi.getSix()==5){
                tenTongJiFiveList.add(6);
            }
            if(tenTongJi.getSeven()==5){
                tenTongJiFiveList.add(7);
            }
            if(tenTongJi.getEight()==5){
                tenTongJiFiveList.add(8);
            }
            if(tenTongJi.getNine()==5){
                tenTongJiFiveList.add(9);
            }
            if(tenTongJi.getTen()==5){
                tenTongJiFiveList.add(10);
            }
            if(tenTongJi.getEleven()==5){
                tenTongJiFiveList.add(11);
            }
            //+++++++++++++++++++++结果集合的处理+++++++++++++++//
            if(tenTongJiFourList.size()>=5){
                tenTongJiNumberList.addAll(tenTongJiFourList);
            }else if(tenTongJiFiveList.size()>=5){
                tenTongJiNumberList.addAll(tenTongJiFiveList);
            }else{
                tenTongJiNumberList.addAll(tenTongJiFourList);
                tenTongJiNumberList.addAll(tenTongJiFiveList);
            }
            //取统计中的结果为1的放到集合中
            TongJi tongJi = tongJiMapper.findAll().get(0);
            List<Integer> tongJiList = new ArrayList<>();
            //+++++++++++++++++次数为5次的统计+++++++++++++++++++//
            if(tongJi.getOne()==1){
                tongJiList.add(1);
            }
            if(tongJi.getTwo()==1){
                tongJiList.add(2);
            }
            if(tongJi.getThree()==1){
                tongJiList.add(3);
            }
            if(tongJi.getFour()==1){
                tongJiList.add(4);
            }
            if(tongJi.getFive()==1){
                tongJiList.add(5);
            }
            if(tongJi.getSix()==1){
                tongJiList.add(6);
            }
            if(tongJi.getSeven()==1){
                tongJiList.add(7);
            }
            if(tongJi.getEight()==1){
                tongJiList.add(8);
            }
            if(tongJi.getNine()==1){
                tongJiList.add(9);
            }
            if(tongJi.getTen()==1){
                tongJiList.add(10);
            }
            if(tongJi.getEleven()==1){
                tongJiList.add(11);
            }
            String[] elevens = {"1","2","3","4","5","6","7","8","9","10","11"};
            Object[] intersect = ArrayUtils.intersect(tenTongJiNumberList.toArray(), tongJiList.toArray());
            String[] result = Convert.toStrArray(intersect);
            List<String> numbers = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            TenTimes tenTimesLatest = tenTimesMapper.findTenTimesLatest();
            map.put("period", 20 + tenTimesLatest.getPeriod().substring(0,tenTimesLatest.getPeriod().length()-2)+"0"+String.valueOf(Long.valueOf(tenTimesLatest.getPeriod().substring(tenTimesLatest.getPeriod().length()-2)) + 1));
            if (result==null || result.length <= 1){
                if(tongJiList.size() <= 3 && tongJiList.size() != 1){
                    for (Integer i : tongJiList){
                        numbers.add(String.valueOf(i).length()==1?("0"+i):(""+i));
                    }
                    map.put("numbers",numbers);
                    return map;
                }
                return null;
            }
            if(result.length > 4){
                List<Integer> tongJiTwoList = new ArrayList<>();
                //+++++++++++++++++次数为5次的统计+++++++++++++++++++//
                if(tongJi.getOne()==2){
                    tongJiTwoList.add(1);
                }
                if(tongJi.getTwo()==2){
                    tongJiTwoList.add(2);
                }
                if(tongJi.getThree()==2){
                    tongJiTwoList.add(3);
                }
                if(tongJi.getFour()==2){
                    tongJiTwoList.add(4);
                }
                if(tongJi.getFive()==2){
                    tongJiTwoList.add(5);
                }
                if(tongJi.getSix()==2){
                    tongJiTwoList.add(6);
                }
                if(tongJi.getSeven()==2){
                    tongJiTwoList.add(7);
                }
                if(tongJi.getEight()==2){
                    tongJiTwoList.add(8);
                }
                if(tongJi.getNine()==2){
                    tongJiTwoList.add(9);
                }
                if(tongJi.getTen()==2){
                    tongJiTwoList.add(10);
                }
                if(tongJi.getEleven()==2){
                    tongJiTwoList.add(11);
                }
                Object[] minus = ArrayUtils.minus(elevens, Convert.toStrArray(tenTongJiNumberList.toArray()));
                String[] fourResult = Convert.toStrArray(ArrayUtils.intersect(minus, Convert.toStrArray(tongJiTwoList.toArray())));
                if (fourResult == null || fourResult.length<1){
                    return null;
                }
                for (String s : fourResult){
                    numbers.add(s.length()==1?("0"+s):s);
                }
                map.put("numbers",numbers);
                return map;
            }
            for (String s : result){
                numbers.add(s.length()==1?("0"+s):s);
            }
           map.put("numbers",numbers);
            return map;
        } catch (Exception e) {
            log.error("数据爬取异常!!!");
            return null;
        }

    }

    /**
     * 获取当前的余额的方法
     * 注意: 二哥的号
     */
    public void getUserBanlance() throws IOException {
        String balanceUrl = "https://m.zh08823.com/tools/_ajax//getUserBanlance";
        HttpRequest httpRequest = HttpUtil.createPost(balanceUrl);
        String cookie = loginPost();
        UserFind userFind = new UserFind();
        userFind.setUserName("zzh1986");
        String body = JSONUtil.toJsonStr(userFind);
        httpRequest.cookie(cookie);
        httpRequest.body(body);
        HttpResponse httpResponse = httpRequest.execute();
        String responseBodyStr = httpResponse.body();
        ResponseBody responseBody = JSONUtil.toBean(responseBodyStr, ResponseBody.class);
        double balance = responseBody.getData().getMoney();
        log.info("当前余额为:"+balance);
        if(balance>500){
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("shutdown -s -t 00");
        }
    }

    /**
     * 注意:二哥的号cookie
     * @return
     */
    public String loginPost() {

        HttpRequest requestLogOut = HttpUtil.createPost("https://m.zh08823.com/tools/_ajax//forgetPwdSeting");
        requestLogOut.execute();
        String loginUrl = "https://m.zh08823.com/tools/_ajax/login";
        String loginName = "mzg159";
        String pwd = "zg15934038";
        HttpRequest httpRequest = HttpUtil.createPost(loginUrl);
        User user = new User();
        user.setIsdefaultLogin(true);
        user.setLoginName(loginName);
        user.setLoginPwd(SecureUtil.md5(pwd));
        user.setValidCode("");
        user.setValidateDate(System.currentTimeMillis());
        String body = JSONUtil.toJsonStr(user);
        httpRequest.body(body);
        HttpResponse httpResponse = httpRequest.execute();
        //System.out.println(LocalDate.now()+httpResponse.body());
        //code  data message 三个参数 data 可以不管 code 必须是success 才算登录成功
        String JSESSIONID = httpResponse.getCookie("JSESSIONID").getValue();
        String cookie = "JSESSIONID=" + JSESSIONID;
        httpResponse.close();
        return cookie;
    }

}
