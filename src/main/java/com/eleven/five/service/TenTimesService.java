package com.eleven.five.service;

import com.eleven.five.entity.TenTimes;
import com.eleven.five.entity.TongJi;
import com.eleven.five.mapper.TenTimesMapper;
import com.eleven.five.mapper.TongJiMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-06-21
 */
@Service
public class TenTimesService {

    @Autowired
    private TenTimesMapper tenTimesMapper;

    @Autowired
    private TongJiMapper tongJiMapper;

    public void saveTenTimes(String date, String period) throws IOException {
        //TODO 需要对数据库进行清空操作
        tenTimesMapper.deleteAll();
        tongJiMapper.deleteAll();
        //================
        List<TenTimes> tenTimesList = getTenTimes(date, period);
        for (TenTimes tenTimes : tenTimesList) {
            tenTimesMapper.save(tenTimes);
        }
        // TODO 需要对结果进行处理
        int one = tenTimesList.get(0).getOneTen() + tenTimesList.get(9).getOneTen() + tenTimesList.get(8).getOneTen();
        int two = tenTimesList.get(0).getTwoTen() + tenTimesList.get(9).getTwoTen() + tenTimesList.get(8).getTwoTen();
        int three = tenTimesList.get(0).getThreeTen() + tenTimesList.get(9).getThreeTen() + tenTimesList.get(8).getThreeTen();
        int four = tenTimesList.get(0).getFourTen() + tenTimesList.get(9).getFourTen() + tenTimesList.get(8).getFourTen();
        int five = tenTimesList.get(0).getFiveTen() + tenTimesList.get(9).getFiveTen() + tenTimesList.get(8).getFiveTen();
        int six = tenTimesList.get(0).getSixTen() + tenTimesList.get(9).getSixTen() + tenTimesList.get(8).getSixTen();
        int seven = tenTimesList.get(0).getSevenTen() + tenTimesList.get(9).getSevenTen() + tenTimesList.get(8).getSevenTen();
        int eight = tenTimesList.get(0).getEightTen() + tenTimesList.get(9).getEightTen() + tenTimesList.get(8).getEightTen();
        int nine = tenTimesList.get(0).getNineTen() + tenTimesList.get(9).getNineTen() + tenTimesList.get(8).getNineTen();
        int ten = tenTimesList.get(0).getTenTen() + tenTimesList.get(9).getTenTen() + tenTimesList.get(8).getTenTen();
        int eleven = tenTimesList.get(0).getElevenTen() + tenTimesList.get(9).getElevenTen() + tenTimesList.get(8).getElevenTen();

//        System.out.println("这个是第一组数据----------------------------------->"+tenTimesList.get(0).getPeriod());
//        System.out.println("这个是第二组数据----------------------------------->"+tenTimesList.get(8).getPeriod());
//        System.out.println("这个是第三组数据----------------------------------->"+tenTimesList.get(9).getPeriod());

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
            for (int j=0;j<twoTime.size();j++){
                System.out.print("出现两次"+twoTime.get(j)+"的数据为:");
                for (int i = 0; i < elevens.length; i++) {
                    if (elevens[i].equals(twoTime.get(j)) ) {
                        //  此处的i就是我需要的那个数
                        System.out.print(i + 1 + " ");
                    }
                }
            }
        }
        System.out.println("==================================");
        Arrays.sort(elevens);
        String sort = "" + elevens[0] + elevens[1] + elevens[2] + elevens[3] + elevens[4] + elevens[5] + elevens[6] + elevens[7] + elevens[8] + elevens[9] + elevens[10];
        TongJi tongJi = new TongJi();
        tongJi.setId(null);
        tongJi.setOne(one);
        tongJi.setTwo(two);
        tongJi.setThree(three);
        tongJi.setFour(four);
        tongJi.setFive(five);
        tongJi.setSix(six);
        tongJi.setSeven(seven);
        tongJi.setEight(eight);
        tongJi.setNine(nine);
        tongJi.setTen(ten);
        tongJi.setEleven(eleven);
        tongJi.setPeriod(sort);
        tongJiMapper.save(tongJi);

    }

    private List<TenTimes> getTenTimes(String date, String period) throws IOException {
        // String url = "http://caipiao.163.com/award/gd11xuan5/"+date+".html";  date=20180620
        String url = "http://caipiao.163.com/award/gd11xuan5/" + date + ".html";
        Elements elements = Jsoup.connect(url).get().select("[data-period]");
        List<String> fiveList = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i % 21 * 4 + i / 21);
            String target = element.attr("data-period");
            String target1 = element.attr("data-award");
            String result = target1 + " " + target;
            if (result.length() < 15) {
                break;
            }
            fiveList.add(result);
        }
        //只统计10-83期之间的数据,其余均不需要统计
        Integer perNum = Integer.valueOf(period);
        if (perNum < 10 || perNum > 83) {
            return null;
        }
        //找出最近的10期数据 例如:perNum = 56 [47,56]
        List<TenTimes> tenTimesList = new ArrayList<>();
        t1:
        for (int i = perNum; i > perNum - 10; i--) {
            // 期号 18062042
            String iFormat = String.valueOf(i).length() < 2 ? "0" + i : String.valueOf(i);
            String dp = date.substring(2, date.length()) + iFormat;
            for (String s : fiveList) {
                if (s.contains(dp)) {
                    String[] number = s.split("[\\s]+");
                    TenTimes tenTimes = new TenTimes();
                    for (int j = 0; j < number.length; j++) {
                        switch (number[j]) {
                            case "01":
                                tenTimes.setOneTen(1);
                                break;
                            case "02":
                                tenTimes.setTwoTen(1);
                                break;
                            case "03":
                                tenTimes.setThreeTen(1);
                                break;
                            case "04":
                                tenTimes.setFourTen(1);
                                break;
                            case "05":
                                tenTimes.setFiveTen(1);
                                break;
                            case "06":
                                tenTimes.setSixTen(1);
                                break;
                            case "07":
                                tenTimes.setSevenTen(1);
                                break;
                            case "08":
                                tenTimes.setEightTen(1);
                                break;
                            case "09":
                                tenTimes.setNineTen(1);
                                break;
                            case "10":
                                tenTimes.setTenTen(1);
                                break;
                            case "11":
                                tenTimes.setElevenTen(1);
                                break;
                            default:
                                tenTimes.setPeriod(number[j]);
                        }
                    }
                    tenTimesList.add(tenTimes);
                    continue t1;
                }
            }
        }
        return tenTimesList;
    }
}