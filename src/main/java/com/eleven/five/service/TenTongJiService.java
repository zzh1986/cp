package com.eleven.five.service;

import cn.hutool.core.util.NumberUtil;
import com.eleven.five.entity.Elevens;
import com.eleven.five.entity.TenTongJi;
import com.eleven.five.mapper.ElevenMapper;
import com.eleven.five.mapper.TenTongJiMapper;
import com.eleven.five.util.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-06-29
 */
@Service
public class TenTongJiService {
    @Autowired
    private TenTongJiMapper tenTongJiMapper;

    @Autowired
    private ElevenMapper elevenMapper;

    public void saveTenTongJiNumber() {
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
        //暂时需要测试把该排序放开下
        Arrays.sort(elevens);
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
        String sort = "" + elevens[0] + elevens[1] + elevens[2] + elevens[3] + elevens[4] + elevens[5]
                + elevens[6] + elevens[7] + elevens[8] + elevens[9] + elevens[10];
        tenTongJi.setSort(sort);
        tenTongJiMapper.save(tenTongJi);
    }

    public String getPercentFromTenDays() {
        String result = "前10天的出现概率分别为:(1,4,7,10) (2,5,8,11) (3,6,9),出现次数0,1,2,3,(4)的概率如下:";
        List<Elevens> elevensList = elevenMapper.findAll();
        Integer[] oneGroup = {1, 2, 3, 4};
        Integer[] twoGroup = {8, 9, 10, 11};
        Integer[] threeGroup = {5, 6, 7};
        Integer[][] elevenInt = new Integer[elevensList.size()][5];
        for (int i = 0; i < 10; i++) {
            int[] oneCount = new int[5];
            int[] twoCount = new int[5];
            int[] threeCount = new int[5];
            for (int j = 0; j < 84; j++) {
                Elevens eleven = elevensList.get(i * 84 + j);
                int index = 0;
                if (eleven.getOne() == 1) {
                    elevenInt[i * 84 + j][index++] = 1;
                }
                if (eleven.getTwo() == 1) {
                    elevenInt[i * 84 + j][index++] = 2;
                }
                if (eleven.getThree() == 1) {
                    elevenInt[i * 84 + j][index++] = 3;
                }
                if (eleven.getFour() == 1) {
                    elevenInt[i * 84 + j][index++] = 4;
                }
                if (eleven.getFive() == 1) {
                    elevenInt[i * 84 + j][index++] = 5;
                }
                if (eleven.getSix() == 1) {
                    elevenInt[i * 84 + j][index++] = 6;
                }
                if (eleven.getSeven() == 1) {
                    elevenInt[i * 84 + j][index++] = 7;
                }
                if (eleven.getEight() == 1) {
                    elevenInt[i * 84 + j][index++] = 8;
                }
                if (eleven.getNine() == 1) {
                    elevenInt[i * 84 + j][index++] = 9;
                }
                if (eleven.getTen() == 1) {
                    elevenInt[i * 84 + j][index++] = 10;
                }
                if (eleven.getEleven() == 1) {
                    elevenInt[i * 84 + j][index++] = 11;
                }


                switch (ArrayUtils.intersect(oneGroup, elevenInt[i * 84 + j]).length) {
                    case 0:
                        oneCount[0]++;
                        break;
                    case 1:
                        oneCount[1]++;
                        break;
                    case 2:
                        oneCount[2]++;
                        break;
                    case 3:
                        oneCount[3]++;
                        break;
                    default:
                        oneCount[4]++;
                }
                switch (ArrayUtils.intersect(twoGroup, elevenInt[i * 84 + j]).length) {
                    case 0:
                        twoCount[0]++;
                        break;
                    case 1:
                        twoCount[1]++;
                        break;
                    case 2:
                        twoCount[2]++;
                        break;
                    case 3:
                        twoCount[3]++;
                        break;
                    default:
                        twoCount[4]++;
                }
                switch (ArrayUtils.intersect(threeGroup, elevenInt[i * 84 + j]).length) {
                    case 0:
                        threeCount[0]++;
                        break;
                    case 1:
                        threeCount[1]++;
                        break;
                    case 2:
                        threeCount[2]++;
                        break;
                    case 3:
                        threeCount[3]++;
                        break;
                    default:
                        threeCount[4]++;
                }
            }
            double[] onePercent = new double[5];
            double[] twoPercent = new double[5];
            double[] threePercent = new double[4];
            onePercent[0] = 1.0 * oneCount[0] / 84;
            onePercent[1] = 1.0 * oneCount[1] / 84;
            onePercent[2] = 1.0 * oneCount[2] / 84;
            onePercent[3] = 1.0 * oneCount[3] / 84;
            onePercent[4] = 1.0 * oneCount[4] / 84;
            twoPercent[0] = 1.0 * twoCount[0] / 84;
            twoPercent[1] = 1.0 * twoCount[1] / 84;
            twoPercent[2] = 1.0 * twoCount[2] / 84;
            twoPercent[3] = 1.0 * twoCount[3] / 84;
            twoPercent[4] = 1.0 * twoCount[4] / 84;
            threePercent[0] = 1.0 * threeCount[0] / 84;
            threePercent[1] = 1.0 * threeCount[1] / 84;
            threePercent[2] = 1.0 * threeCount[2] / 84;
            threePercent[3] = 1.0 * threeCount[3] / 84;
            //统计每天出现的概率
            result += "\n";
            for (int m = 0; m < 5; m++) {
                result += "\t" + NumberUtil.roundStr(onePercent[m], 2);
            }
            for (int m = 0; m < 5; m++) {
                result += "\t\t" + NumberUtil.roundStr(twoPercent[m], 2);
            }
            for (int m = 0; m < 4; m++) {
                result += "\t\t" + NumberUtil.roundStr(threePercent[m], 2);
            }
        }
        return result;
    }
}

