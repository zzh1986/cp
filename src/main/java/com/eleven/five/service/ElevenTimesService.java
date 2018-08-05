package com.eleven.five.service;

import com.eleven.five.entity.*;
import com.eleven.five.mapper.ElevenThreeTongJiMapper;
import com.eleven.five.mapper.ElevenTimesMapper;
import com.eleven.five.util.FiveUtil;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-08-05
 */
@Service
public class ElevenTimesService {

    @Autowired
    private ElevenTimesMapper elevenTimesMapper;

    @Autowired
    private ElevenThreeTongJiMapper elevenThreeTongJiMapper;

    private String BASE_URL = UrlDateEnum.URL_ENUM.getMsg();

        public void saveElevenTimes(String date, String period) throws IOException {
        //TODO 需要对数据库进行清空操作
        elevenTimesMapper.deleteAll();
        elevenThreeTongJiMapper.deleteAll();
        //================
        List<ElevenTimes> elevenTimesList = getElevenTimes(date, period);
        for (ElevenTimes elevenTimes : elevenTimesList) {
            elevenTimesMapper.save(elevenTimes);
        }
        // TODO 需要对结果进行处理
        int one = elevenTimesList.get(0).getOneTen() + elevenTimesList.get(10).getOneTen() + elevenTimesList.get(9).getOneTen();
        int two = elevenTimesList.get(0).getTwoTen() + elevenTimesList.get(10).getTwoTen() + elevenTimesList.get(9).getTwoTen();
        int three = elevenTimesList.get(0).getThreeTen() + elevenTimesList.get(10).getThreeTen() + elevenTimesList.get(9).getThreeTen();
        int four = elevenTimesList.get(0).getFourTen() + elevenTimesList.get(10).getFourTen() + elevenTimesList.get(9).getFourTen();
        int five = elevenTimesList.get(0).getFiveTen() + elevenTimesList.get(10).getFiveTen() + elevenTimesList.get(9).getFiveTen();
        int six = elevenTimesList.get(0).getSixTen() + elevenTimesList.get(10).getSixTen() + elevenTimesList.get(9).getSixTen();
        int seven = elevenTimesList.get(0).getSevenTen() + elevenTimesList.get(10).getSevenTen() + elevenTimesList.get(9).getSevenTen();
        int eight = elevenTimesList.get(0).getEightTen() + elevenTimesList.get(10).getEightTen() + elevenTimesList.get(9).getEightTen();
        int nine = elevenTimesList.get(0).getNineTen() + elevenTimesList.get(10).getNineTen() + elevenTimesList.get(9).getNineTen();
        int ten = elevenTimesList.get(0).getTenTen() + elevenTimesList.get(10).getTenTen() + elevenTimesList.get(9).getTenTen();
        int eleven = elevenTimesList.get(0).getElevenTen() + elevenTimesList.get(10).getElevenTen() + elevenTimesList.get(9).getElevenTen();
//        System.out.println("这个是第一组数据----------------------------------->"+tenTimesList.get(0).getPeriod());
//        System.out.println("这个是第二组数据----------------------------------->"+tenTimesList.get(9).getPeriod());
//        System.out.println("这个是第三组数据----------------------------------->"+tenTimesList.get(10).getPeriod());

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

        // 可在此处对11个数进行统计 范围为0-3
        int[] times = new int[4];
        for (int i = 0; i < elevens.length; i++) {
            times[elevens[i]]++;
        }
        // 此处需要实现的功能是,统计出出现1次和2次的数据,从而进行统计 最终需要选择
        List<Integer> oneTime = new ArrayList<>();
        List<Integer> twoTime = new ArrayList<>();
        for (int i = 0; i < times.length; i++) {
            if (times[i] == 1) oneTime.add(i);
            //TODO 这边有问题  重复的问题如何解决!!!!!
            if (times[i] == 2) twoTime.add(i);
        }
        System.out.println("==================================");
        if (oneTime.size() > 0) {
            for (int j = 0; j < oneTime.size(); j++) {
                System.out.print("出现一次" + oneTime.get(j) + "的数据为:");
                for (int i = 0; i < elevens.length; i++) {
                    if (elevens[i].equals(oneTime.get(j))) {
                        //  此处的i就是我需要的那个数
                        System.out.print(i + 1 + " ");
                    }

                }
            }
        }
        System.out.println("==================================");
        if (twoTime.size() > 0) {
            for (int j = 0; j < twoTime.size(); j++) {
                System.out.print("出现两次" + twoTime.get(j) + "的数据为:");
                for (int i = 0; i < elevens.length; i++) {
                    if (elevens[i].equals(twoTime.get(j))) {
                        //  此处的i就是我需要的那个数
                        System.out.print(i + 1 + " ");
                    }
                }
            }
        }
        System.out.println("==================================");
        Arrays.sort(elevens);
        String sort = "" + elevens[0] + elevens[1] + elevens[2] + elevens[3] + elevens[4] + elevens[5] + elevens[6] + elevens[7] + elevens[8] + elevens[9] + elevens[10];
        ElevenThreeTongJi elevenThreeTongJi = new ElevenThreeTongJi();
        elevenThreeTongJi.setId(null);
        elevenThreeTongJi.setOne(one);
        elevenThreeTongJi.setTwo(two);
        elevenThreeTongJi.setThree(three);
        elevenThreeTongJi.setFour(four);
        elevenThreeTongJi.setFive(five);
        elevenThreeTongJi.setSix(six);
        elevenThreeTongJi.setSeven(seven);
        elevenThreeTongJi.setEight(eight);
        elevenThreeTongJi.setNine(nine);
        elevenThreeTongJi.setTen(ten);
        elevenThreeTongJi.setEleven(eleven);
        elevenThreeTongJi.setPeriod(sort);
        elevenThreeTongJiMapper.save(elevenThreeTongJi);

    }

    private List<ElevenTimes> getElevenTimes(String date, String period) throws IOException {
        // String url = "http://caipiao.163.com/award/gd11xuan5/"+date+".html";  date=20180620
        String url = BASE_URL + date + ".html";
        Elements elements = Jsoup.connect(url).get().select("[data-period]");
        List<String> fiveList = new ArrayList<>();
//        for (int i = 0; i < elements.size(); i++) {
//            Element element = elements.get(i % 21 * 4 + i / 21);
//            String target = element.attr("data-period");
//            String target1 = element.attr("data-award");
//            String result = target1 + " " + target;
//            if (result.length() < 15) {
//                break;
//            }
//            fiveList.add(result);
//        }
        FiveUtil.getOneGroupNumber(fiveList, elements);
        //只统计11-83期之间的数据,其余均不需要统计
        Integer perNum = Integer.valueOf(period);
        if (perNum < 11 || perNum > 83) {
            return null;
        }
        //找出最近的11期数据 例如:perNum = 56 [47,56]
        List<ElevenTimes> elevenTimesList = new ArrayList<>();
        t1:
        for (int i = perNum; i > perNum - 11; i--) {
            // 期号 18062042
            String iFormat = String.valueOf(i).length() < 2 ? "0" + i : String.valueOf(i);
            String dp = date.substring(2, date.length()) + iFormat;
            for (String s : fiveList) {
                if (s.contains(dp)) {
                    String[] number = s.split("[\\s]+");
                    ElevenTimes elevenTimes = new ElevenTimes();
                    for (int j = 0; j < number.length; j++) {
                        switch (number[j]) {
                            case "01":
                                elevenTimes.setOneTen(1);
                                break;
                            case "02":
                                elevenTimes.setTwoTen(1);
                                break;
                            case "03":
                                elevenTimes.setThreeTen(1);
                                break;
                            case "04":
                                elevenTimes.setFourTen(1);
                                break;
                            case "05":
                                elevenTimes.setFiveTen(1);
                                break;
                            case "06":
                                elevenTimes.setSixTen(1);
                                break;
                            case "07":
                                elevenTimes.setSevenTen(1);
                                break;
                            case "08":
                                elevenTimes.setEightTen(1);
                                break;
                            case "09":
                                elevenTimes.setNineTen(1);
                                break;
                            case "10":
                                elevenTimes.setTenTen(1);
                                break;
                            case "11":
                                elevenTimes.setElevenTen(1);
                                break;
                            default:
                                elevenTimes.setPeriod(number[j]);
                        }
                    }
                    elevenTimesList.add(elevenTimes);
                    continue t1;
                }
            }
        }
        return elevenTimesList;
    }
}

