/**
 * <p>Copyright ® 中国证监会中央监管信息平台版权所有。</p>
 * 类名:ArrayUtil
 * 创建人:CKW    创建时间:2015-08-26
 */

package com.eleven.five.util;

import cn.hutool.core.convert.Convert;

import java.util.*;
import java.util.Map.Entry;

/**
 * 数组工具类 求两个数组的交集、并集、差集等
 *
 * @author CKW
 */
public class ArrayUtils {

    /**
     * 并集 求两个字符串数组的并集，利用set的元素唯一性
     *
     * @param arr1 数组1
     * @param arr2 数组2
     * @return 并集
     */
    public static String[] union(Object[] arr1, Object[] arr2) {
        Set<String> set = new HashSet<String>();
        if (arr1 != null) {
            for (Object str : arr1) {
                set.add(str.toString());
            }
        }
        if (arr2 != null) {
            for (Object str : arr2) {
                set.add(str.toString());
            }
        }

        String[] result = {};
        return set.toArray(result);
    }

    /**
     * 交集 求两个数组的交集
     *
     * @param arr1 数组1
     * @param arr2 数组2
     * @return 交集
     */
    public static List<String> intersect(Object[] arr1, Object[] arr2) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        LinkedList<String> list = new LinkedList<String>();
        if (arr1 != null) {
            for (Object str : arr1) {
                if (!map.containsKey(str)) {
                    map.put(str.toString(), Boolean.FALSE);
                }
            }
        }
        if (arr2 != null) {
            for (Object str : arr2) {
                if (map.containsKey(str)) {
                    map.put(str.toString(), Boolean.TRUE);
                }
            }
        }
        for (Entry<String, Boolean> e : map.entrySet()) {
            if (e.getValue().equals(Boolean.TRUE)) {
                list.add(e.getKey());
            }
        }

        return list;
    }

    /**
     * 差集 求两个数组的差集
     *
     * @param arr1 数组1
     * @param arr2 数组2
     * @return 差集
     */
    public static String[] minus(String[] arr1, String[] arr2) {
        LinkedList<String> list = new LinkedList<String>();
        LinkedList<String> history = new LinkedList<String>();
        String[] longerArr = arr1;
        String[] shorterArr = arr2;
        // 找出较长的数组来减较短的数组
        if (arr1 != null && arr2 != null) {
            if (arr1.length > arr2.length) {
                longerArr = arr2;
                shorterArr = arr1;
            }
        }
        if (longerArr != null) {
            for (String str : longerArr) {
                if (!list.contains(str)) {
                    list.add(str);
                }
            }
        }
        if (shorterArr != null) {
            for (String str : shorterArr) {
                if (list.contains(str)) {
                    history.add(str);
                    list.remove(str);
                } else {
                    if (!history.contains(str)) {
                        list.add(str);
                    }
                }
            }
        }

        String[] result = {};
        return list.toArray(result);
    }

    /**
     * 数组转String
     *
     * @param list 数组
     * @return String
     */
    public static String listToString(List<String> list) {
        if (list == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean first = true;
        // 第一个前面不拼接","
        for (String string : list) {
            if (first) {
                first = false;
            } else {
                result.append(",");
            }
            result.append(string);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Integer[] str1 = {1, 2, 3, 4, 5};
        Integer[] str2 = {4, 5, 6};
        String[] string1 = Convert.toStrArray(str1);
        String[] string2 = Convert.toStrArray(str2);
        List<String> intersect = intersect(string1, string2);
        System.out.println(listToString(intersect));
    }

}
