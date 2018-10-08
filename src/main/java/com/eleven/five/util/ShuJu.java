package com.eleven.five.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 该工具类用于判断数据的分组,统计分组的百分比
 * 特别说明:
 * 1.有几个特殊的数据需要说明: 质数中"01"按照质数进行统计;
 * 大小数据中"06"按照大数进行统计;
 *
 * @author zhaozhihong
 */
public class ShuJu {

    /**
     * 判断是否为奇数,是的话返回1,否的话返回0
     *
     * @param jiShu
     * @return
     */
    public static boolean isJiShu(String jiShu) {
        String[] jiShuStr = {"01", "03", "05", "07", "09", "11"};
        List<String> jiShuList = Arrays.asList(jiShuStr);
        if (jiShuList.contains(jiShu)) {
            return true;
        }
        return false;
    }

    /**
     * 统计n(10)期数据内不低于3个奇数的次数
     *
     * @param numberList
     * @return
     */
    public static int jiShuTimes(List<String[]> numberList) {
        int index = 0;
        String[] jiShuStr = {"01", "03", "05", "07", "09", "11"};
        for (int i = 0; i < numberList.size(); i++) {
            if (ArrayUtils.intersect(jiShuStr, numberList.get(i)).length >= 3) {
                index++;
            }
        }
        return index;
    }

    /**
     * 判断是否为质数,是的话返回1,否的话返回0
     *
     * @param zhiShu
     * @return
     */
    public static boolean isZhiShu(String zhiShu) {
        String[] zhiShuStr = {"01", "02", "03", "05", "07", "11"};
        List<String> zhiShuList = Arrays.asList(zhiShuStr);
        if (zhiShuList.contains(zhiShu)) {
            return true;
        }
        return false;
    }

    /**
     * 统计n(10)期数据内不低于3个大数据的次数
     *
     * @param numberList
     * @return
     */
    public static int zhiShuTimes(List<String[]> numberList) {
        int index = 0;
        String[] zhiShuStr = {"01", "02", "03", "05", "07", "11"};
        for (int i = 0; i < numberList.size(); i++) {
            if (ArrayUtils.intersect(zhiShuStr, numberList.get(i)).length >= 3) {
                index++;
            }
        }
        return index;
    }

    /**
     * 判断是否为质数,是的话返回1,否的话返回0
     *
     * @param daShu
     * @return
     */
    public static boolean isDaShu(String daShu) {
        String[] daShuStr = {"06", "07", "08", "09", "10", "11"};
        List<String> daShuList = Arrays.asList(daShuStr);
        if (daShuList.contains(daShu)) {
            return true;
        }
        return false;
    }

    /**
     * 统计n(10)期数据内不低于3个大数据的次数
     *
     * @param numberList
     * @return
     */
    public static int daShuTimes(List<String[]> numberList) {
        int index = 0;
        String[] daShuStr = {"06", "07", "08", "09", "10", "11"};
        for (int i = 0; i < numberList.size(); i++) {
            if (ArrayUtils.intersect(daShuStr, numberList.get(i)).length >= 3) {
                index++;
            }
        }
        return index;
    }

    /**
     * 统计n(10)期数据内不低于3个大数据的概率
     *
     * @param numberList
     * @return
     */
    public static double daShuPercent(List<String[]> numberList) {
        int index = 0;
        String[] daShuStr = {"06", "07", "08", "09", "10", "11"};
        for (int i = 0; i < numberList.size(); i++) {
            if (ArrayUtils.intersect(daShuStr, numberList.get(i)).length >= 3) {
                index++;
            }
        }
        double daShuPercent = index * 1.0 / numberList.size();
        return daShuPercent;
    }

    /**
     * 统计n(10)期数据内不低于3个大数据的概率
     *
     * @param numberList
     * @return
     */
    public static double zhiShuPercent(List<String[]> numberList) {
        int index = 0;
        String[] zhiShuStr = {"01", "02", "03", "05", "07", "11"};
        for (int i = 0; i < numberList.size(); i++) {
            if (ArrayUtils.intersect(zhiShuStr, numberList.get(i)).length >= 3) {
                index++;
            }
        }
        double zhiShuPercent = index * 1.0 / numberList.size();
        return zhiShuPercent;
    }

    /**
     * 统计n(10)期数据内不低于3个奇数的概率
     *
     * @param numberList
     * @return
     */
    public static double jiShuPercent(List<String[]> numberList) {
        int index = 0;
        String[] jiShuStr = {"01", "03", "05", "07", "09", "11"};
        for (int i = 0; i < numberList.size(); i++) {
            if (ArrayUtils.intersect(jiShuStr, numberList.get(i)).length >= 3) {
                index++;
            }
        }
        double jiShuPercent = index * 1.0 / numberList.size();
        return jiShuPercent;
    }

    /**
     * 统计温码的数据放入List<String> 并返回
     */
    public static List<String> getWenHao(List<String[]> numberList) {
        List<String> result = new ArrayList<>();
        String[] standard = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11"};
        int[] index = new int[11];
        for (int i = 0; i < numberList.size(); i++) {
            for (int j = 0; j < numberList.get(i).length; j++) {
                switch (numberList.get(i)[j]) {
                    case "01":
                        index[0]++;
                        break;
                    case "02":
                        index[1]++;
                        break;
                    case "03":
                        index[2]++;
                        break;
                    case "04":
                        index[3]++;
                        break;
                    case "05":
                        index[4]++;
                        break;
                    case "06":
                        index[5]++;
                        break;
                    case "07":
                        index[6]++;
                        break;
                    case "08":
                        index[7]++;
                        break;
                    case "09":
                        index[8]++;
                        break;
                    case "10":
                        index[9]++;
                        break;
                    default:
                        index[10]++;
                }
            }

        }
        for (int i = 0; i < index.length; i++) {
            if ( index[i] == (numberList.size() + 1) / 2 || index[i] == (numberList.size() - 1) / 2 ) {
                result.add(standard[i]);
            }
        }
        return result;
    }
}
