package com.eleven.five.service;

import com.eleven.five.entity.TenTimes;
import com.eleven.five.entity.TenTongJi;
import com.eleven.five.entity.TongJi;
import com.eleven.five.entity.UrlDateEnum;
import com.eleven.five.mapper.TenTimesMapper;
import com.eleven.five.mapper.TenTongJiMapper;
import com.eleven.five.mapper.TongJiMapper;
import com.eleven.five.util.FiveUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
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

    private static String BASE_URL = UrlDateEnum.URL_ENUM.getMsg();

    @Autowired
    private TenTimesMapper tenTimesMapper;

    @Autowired
    private TongJiMapper tongJiMapper;

    @Autowired
    private TenTongJiMapper tenTongJiMapper;

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

    /**
     * 从爱彩乐获取10组数据
     *
     * @param date
     * @param period
     * @return
     * @throws IOException
     */
    public List<TenTimes> getTenTimesSencond(String date, String period) throws IOException {
        if (Integer.valueOf(period) < 10 || Integer.valueOf(period) > 83) {
            return null;
        }
        String url = "http://gd11x5.icaile.com/";
        date = date.substring(2);
        Elements elements = Jsoup.connect(url).get().select(".chart-bg-qh");
        if (!elements.isEmpty() && elements.size() > 9) {
            List<TenTimes> tenTimesList = new ArrayList<>();
            for (Element element : elements) {
                Long periodtagert = Long.valueOf(element.text());
                //逻辑不对
                if (periodtagert > Long.valueOf(date) * 100 + Integer.valueOf(period) - 10 && periodtagert < Long.valueOf(date) * 100 + Integer.valueOf(period) + 1) {
                    Elements elements1 = element.siblingElements();
                    String one = elements1.select(".dqhm").get(0).text();
                    String two = elements1.select(".dqhm").get(1).text();
                    String three = elements1.select(".dqhm").get(2).text();
                    String four = elements1.select(".dqhm").get(3).text();
                    String five = elements1.select(".chart-bg-kjhmo").get(0).text();
                    TenTimes tenTimes = new TenTimes();
                    chooseTenTimes(tenTimes, one);
                    chooseTenTimes(tenTimes, two);
                    chooseTenTimes(tenTimes, three);
                    chooseTenTimes(tenTimes, four);
                    chooseTenTimes(tenTimes, five);
                    tenTimes.setPeriod(element.text());
                    tenTimesList.add(tenTimes);
                }

            }
            return tenTimesList;
        }
        return null;
    }

    private void chooseTenTimes(TenTimes tenTimes, String num) {
        switch (num) {
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
            default:
                tenTimes.setElevenTen(1);
        }
    }

    public String saveMFileOneToEleven() throws IOException {
        Integer[][] count = new Integer[11][];
        count[0] = tenTimesMapper.selectFirstColumn();
        count[1] = tenTimesMapper.selectSecondColumn();
        count[2] = tenTimesMapper.selectThirdColumn();
        count[3] = tenTimesMapper.selectForthColumn();
        count[4] = tenTimesMapper.selectFifthColumn();
        count[5] = tenTimesMapper.selectSixthColumn();
        count[6] = tenTimesMapper.selectSeventhColumn();
        count[7] = tenTimesMapper.selectEighthColumn();
        count[8] = tenTimesMapper.selectNinthColumn();
        count[9] = tenTimesMapper.selectTenthColumn();
        count[10] = tenTimesMapper.selectEleventhColumn();
        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\m文件\\target.m"));
        String target = "x=1:10;\n";
        for (int i = 0; i < count.length; i++) {

            target += "y" + (i + 1) + "=" + Arrays.toString(count[i]) + ";\n"
                    + "figure(" + (i + 1) + ");\n"
                    + "plot(x,y" + (i + 1) + ");\n";
        }
        TenTongJi tenTongJi = tenTongJiMapper.findAll().get(0);
        Integer[] count1 = new Integer[11];
        count1[0] = tenTongJi.getOne();
        count1[1] = tenTongJi.getTwo();
        count1[2] = tenTongJi.getThree();
        count1[3] = tenTongJi.getFour();
        count1[4] = tenTongJi.getFive();
        count1[5] = tenTongJi.getSix();
        count1[6] = tenTongJi.getSeven();
        count1[7] = tenTongJi.getEight();
        count1[8] = tenTongJi.getNine();
        count1[9] = tenTongJi.getTen();
        count1[10] = tenTongJi.getEleven();


        String target2 = "x1=1:11;\n"
                + "ytotal=" + Arrays.toString(count1) + ";\n"
                + "figure(12);\n"
                + "plot(x1,ytotal);";
        fos.write(target.getBytes());
        fos.write(target2.getBytes());
        fos.close();
        return "保存成功";
    }


    public String saveMFileRencentTen() throws IOException {
        List<TenTimes> tenTimesList = tenTimesMapper.findAll();
        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\m文件\\10.m"));
        String target = "x = 1:1:11;\n"
                + "y1 =  (x-" + (tenTimesList.get(0).getOneTen() == 0 ? 0 : 1) + ").*" +
                        "(x-" + (tenTimesList.get(0).getTwoTen() == 0 ? 0 : 2) + ").*" +
                        "(x-" + (tenTimesList.get(0).getThreeTen() == 0 ? 0 : 3) + ").*" +
                        "(x-" + (tenTimesList.get(0).getFourTen() == 0 ? 0 : 4) + ").*" +
                        "(x-" + (tenTimesList.get(0).getFiveTen() == 0 ? 0 : 5) + ").*" +
                        "(x-" + (tenTimesList.get(0).getSixTen() == 0 ? 0 : 6) + ").*" +
                        "(x-" + (tenTimesList.get(0).getSevenTen() == 0 ? 0 : 7) + ").*" +
                        "(x-" + (tenTimesList.get(0).getEightTen() == 0 ? 0 : 8) + ").*" +
                        "(x-" + (tenTimesList.get(0).getNineTen() == 0 ? 0 : 9) + ").*" +
                        "(x-" + (tenTimesList.get(0).getTenTen() == 0 ? 0 : 10) + ").*" +
                        "(x-" + (tenTimesList.get(0).getElevenTen() == 0 ? 0 : 11) + ");\n"
                + "y2 =  (x-" + (tenTimesList.get(1).getOneTen() == 0 ? 0 : 1) + ").*" +
                        "(x-" + (tenTimesList.get(1).getTwoTen() == 0 ? 0 : 2) + ").*" +
                        "(x-" + (tenTimesList.get(1).getThreeTen() == 0 ? 0 : 3) + ").*" +
                        "(x-" + (tenTimesList.get(1).getFourTen() == 0 ? 0 : 4) + ").*" +
                        "(x-" + (tenTimesList.get(1).getFiveTen() == 0 ? 0 : 5) + ").*" +
                        "(x-" + (tenTimesList.get(1).getSixTen() == 0 ? 0 : 6) + ").*" +
                        "(x-" + (tenTimesList.get(1).getSevenTen() == 0 ? 0 : 7) + ").*" +
                        "(x-" + (tenTimesList.get(1).getEightTen() == 0 ? 0 : 8) + ").*" +
                        "(x-" + (tenTimesList.get(1).getNineTen() == 0 ? 0 : 9) + ").*" +
                        "(x-" + (tenTimesList.get(1).getTenTen() == 0 ? 0 : 10) + ").*" +
                        "(x-" + (tenTimesList.get(1).getElevenTen() == 0 ? 0 : 11) + ");\n"
                + "y3 =  (x-" + (tenTimesList.get(2).getOneTen() == 0 ? 0 : 1) + ").*" +
                        "(x-" + (tenTimesList.get(2).getTwoTen() == 0 ? 0 : 2) + ").*" +
                        "(x-" + (tenTimesList.get(2).getThreeTen() == 0 ? 0 : 3) + ").*" +
                        "(x-" + (tenTimesList.get(2).getFourTen() == 0 ? 0 : 4) + ").*" +
                        "(x-" + (tenTimesList.get(2).getFiveTen() == 0 ? 0 : 5) + ").*" +
                        "(x-" + (tenTimesList.get(2).getSixTen() == 0 ? 0 : 6) + ").*" +
                        "(x-" + (tenTimesList.get(2).getSevenTen() == 0 ? 0 : 7) + ").*" +
                        "(x-" + (tenTimesList.get(2).getEightTen() == 0 ? 0 : 8) + ").*" +
                        "(x-" + (tenTimesList.get(2).getNineTen() == 0 ? 0 : 9) + ").*" +
                        "(x-" + (tenTimesList.get(2).getTenTen() == 0 ? 0 : 10) + ").*" +
                        "(x-" + (tenTimesList.get(2).getElevenTen() == 0 ? 0 : 11) + ");\n"
                + "y4 =  (x-" + (tenTimesList.get(3).getOneTen() == 0 ? 0 : 1) + ").*" +
                        "(x-" + (tenTimesList.get(3).getTwoTen() == 0 ? 0 : 2) + ").*" +
                        "(x-" + (tenTimesList.get(3).getThreeTen() == 0 ? 0 : 3) + ").*" +
                        "(x-" + (tenTimesList.get(3).getFourTen() == 0 ? 0 : 4) + ").*" +
                        "(x-" + (tenTimesList.get(3).getFiveTen() == 0 ? 0 : 5) + ").*" +
                        "(x-" + (tenTimesList.get(3).getSixTen() == 0 ? 0 : 6) + ").*" +
                        "(x-" + (tenTimesList.get(3).getSevenTen() == 0 ? 0 : 7) + ").*" +
                        "(x-" + (tenTimesList.get(3).getEightTen() == 0 ? 0 : 8) + ").*" +
                        "(x-" + (tenTimesList.get(3).getNineTen() == 0 ? 0 : 9) + ").*" +
                        "(x-" + (tenTimesList.get(3).getTenTen() == 0 ? 0 : 10) + ").*" +
                        "(x-" + (tenTimesList.get(3).getElevenTen() == 0 ? 0 : 11) + ");\n"
                + "y5 =  (x-" + (tenTimesList.get(4).getOneTen() == 0 ? 0 : 1) + ").*" +
                        "(x-" + (tenTimesList.get(4).getTwoTen() == 0 ? 0 : 2) + ").*" +
                        "(x-" + (tenTimesList.get(4).getThreeTen() == 0 ? 0 : 3) + ").*" +
                        "(x-" + (tenTimesList.get(4).getFourTen() == 0 ? 0 : 4) + ").*" +
                        "(x-" + (tenTimesList.get(4).getFiveTen() == 0 ? 0 : 5) + ").*" +
                        "(x-" + (tenTimesList.get(4).getSixTen() == 0 ? 0 : 6) + ").*" +
                        "(x-" + (tenTimesList.get(4).getSevenTen() == 0 ? 0 : 7) + ").*" +
                        "(x-" + (tenTimesList.get(4).getEightTen() == 0 ? 0 : 8) + ").*" +
                        "(x-" + (tenTimesList.get(4).getNineTen() == 0 ? 0 : 9) + ").*" +
                        "(x-" + (tenTimesList.get(4).getTenTen() == 0 ? 0 : 10) + ").*" +
                        "(x-" + (tenTimesList.get(4).getElevenTen() == 0 ? 0 : 11) + ");\n"
                + "y6 =  (x-" + (tenTimesList.get(5).getOneTen() == 0 ? 0 : 1) + ").*" +
                        "(x-" + (tenTimesList.get(5).getTwoTen() == 0 ? 0 : 2) + ").*" +
                        "(x-" + (tenTimesList.get(5).getThreeTen() == 0 ? 0 : 3) + ").*" +
                        "(x-" + (tenTimesList.get(5).getFourTen() == 0 ? 0 : 4) + ").*" +
                        "(x-" + (tenTimesList.get(5).getFiveTen() == 0 ? 0 : 5) + ").*" +
                        "(x-" + (tenTimesList.get(5).getSixTen() == 0 ? 0 : 6) + ").*" +
                        "(x-" + (tenTimesList.get(5).getSevenTen() == 0 ? 0 : 7) + ").*" +
                        "(x-" + (tenTimesList.get(5).getEightTen() == 0 ? 0 : 8) + ").*" +
                        "(x-" + (tenTimesList.get(5).getNineTen() == 0 ? 0 : 9) + ").*" +
                        "(x-" + (tenTimesList.get(5).getTenTen() == 0 ? 0 : 10) + ").*" +
                        "(x-" + (tenTimesList.get(5).getElevenTen() == 0 ? 0 : 11) + ");\n"
                + "y7 =  (x-" + (tenTimesList.get(6).getOneTen() == 0 ? 0 : 1) + ").*" +
                        "(x-" + (tenTimesList.get(6).getTwoTen() == 0 ? 0 : 2) + ").*" +
                        "(x-" + (tenTimesList.get(6).getThreeTen() == 0 ? 0 : 3) + ").*" +
                        "(x-" + (tenTimesList.get(6).getFourTen() == 0 ? 0 : 4) + ").*" +
                        "(x-" + (tenTimesList.get(6).getFiveTen() == 0 ? 0 : 5) + ").*" +
                        "(x-" + (tenTimesList.get(6).getSixTen() == 0 ? 0 : 6) + ").*" +
                        "(x-" + (tenTimesList.get(6).getSevenTen() == 0 ? 0 : 7) + ").*" +
                        "(x-" + (tenTimesList.get(6).getEightTen() == 0 ? 0 : 8) + ").*" +
                        "(x-" + (tenTimesList.get(6).getNineTen() == 0 ? 0 : 9) + ").*" +
                        "(x-" + (tenTimesList.get(6).getTenTen() == 0 ? 0 : 10) + ").*" +
                        "(x-" + (tenTimesList.get(6).getElevenTen() == 0 ? 0 : 11) + ");\n"
                + "y8 =  (x-" + (tenTimesList.get(7).getOneTen() == 0 ? 0 : 1) + ").*" +
                        "(x-" + (tenTimesList.get(7).getTwoTen() == 0 ? 0 : 2) + ").*" +
                        "(x-" + (tenTimesList.get(7).getThreeTen() == 0 ? 0 : 3) + ").*" +
                        "(x-" + (tenTimesList.get(7).getFourTen() == 0 ? 0 : 4) + ").*" +
                        "(x-" + (tenTimesList.get(7).getFiveTen() == 0 ? 0 : 5) + ").*" +
                        "(x-" + (tenTimesList.get(7).getSixTen() == 0 ? 0 : 6) + ").*" +
                        "(x-" + (tenTimesList.get(7).getSevenTen() == 0 ? 0 : 7) + ").*" +
                        "(x-" + (tenTimesList.get(7).getEightTen() == 0 ? 0 : 8) + ").*" +
                        "(x-" + (tenTimesList.get(7).getNineTen() == 0 ? 0 : 9) + ").*" +
                        "(x-" + (tenTimesList.get(7).getTenTen() == 0 ? 0 : 10) + ").*" +
                        "(x-" + (tenTimesList.get(7).getElevenTen() == 0 ? 0 : 11) + ");\n"
                + "y9 =  (x-" + (tenTimesList.get(8).getOneTen() == 0 ? 0 : 1) + ").*" +
                        "(x-" + (tenTimesList.get(8).getTwoTen() == 0 ? 0 : 2) + ").*" +
                        "(x-" + (tenTimesList.get(8).getThreeTen() == 0 ? 0 : 3) + ").*" +
                        "(x-" + (tenTimesList.get(8).getFourTen() == 0 ? 0 : 4) + ").*" +
                        "(x-" + (tenTimesList.get(8).getFiveTen() == 0 ? 0 : 5) + ").*" +
                        "(x-" + (tenTimesList.get(8).getSixTen() == 0 ? 0 : 6) + ").*" +
                        "(x-" + (tenTimesList.get(8).getSevenTen() == 0 ? 0 : 7) + ").*" +
                        "(x-" + (tenTimesList.get(8).getEightTen() == 0 ? 0 : 8) + ").*" +
                        "(x-" + (tenTimesList.get(8).getNineTen() == 0 ? 0 : 9) + ").*" +
                        "(x-" + (tenTimesList.get(8).getTenTen() == 0 ? 0 : 10) + ").*" +
                        "(x-" + (tenTimesList.get(8).getElevenTen() == 0 ? 0 : 11) + ");\n"
                + "y10 = (x-" + (tenTimesList.get(9).getOneTen() == 0 ? 0 : 1) + ").*" +
                        "(x-" + (tenTimesList.get(9).getTwoTen() == 0 ? 0 : 2) + ").*" +
                        "(x-" + (tenTimesList.get(9).getThreeTen() == 0 ? 0 : 3) + ").*" +
                        "(x-" + (tenTimesList.get(9).getFourTen() == 0 ? 0 : 4) + ").*" +
                        "(x-" + (tenTimesList.get(9).getFiveTen() == 0 ? 0 : 5) + ").*" +
                        "(x-" + (tenTimesList.get(9).getSixTen() == 0 ? 0 : 6) + ").*" +
                        "(x-" + (tenTimesList.get(9).getSevenTen() == 0 ? 0 : 7) + ").*" +
                        "(x-" + (tenTimesList.get(9).getEightTen() == 0 ? 0 : 8) + ").*" +
                        "(x-" + (tenTimesList.get(9).getNineTen() == 0 ? 0 : 9) + ").*" +
                        "(x-" + (tenTimesList.get(9).getTenTen() == 0 ? 0 : 10) + ").*" +
                        "(x-" + (tenTimesList.get(9).getElevenTen() == 0 ? 0 : 11) + ");\n"
                +       "plot(x,y1);\n" +
                        "hold on;\n"    +
                        "plot(x,y2);\n" +
                        "plot(x,y3);\n" +
                        "plot(x,y4);\n" +
                        "plot(x,y5);\n" +
                        "plot(x,y6);\n" +
                        "plot(x,y7);\n" +
                        "plot(x,y8);\n" +
                        "plot(x,y9);\n" +
                        "plot(x,y10);";
        target = target.replace(".*(x-0)", "");
        target = target.replace("(x-0).*","");
        System.out.println(target);
                fos.write(target.getBytes());
                fos.close();
        return "保存成功";









    }
}