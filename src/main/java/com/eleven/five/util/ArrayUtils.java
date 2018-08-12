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
 * 数组工具类 求多个数组的交集、并集、差集等
 *
 * @author zzh
 */
public class ArrayUtils {

    /**
     * 并集 求两个字符串数组的并集，利用set的元素唯一性
     *
     * @param arr1 参数不定个数的方法
     * @return 并集
     */
    public static Object[] union(Object[]... arr1) {
        Set<Object> set = new HashSet<>();
        if (arr1 != null && arr1.length > 0) {
            for (Object[] arr : arr1) {
                if (arr != null && arr.length > 0) {
                    for (Object str : arr) {
                        set.add(str);
                    }
                }
            }
        }
        Object[] result = {};
        return set.toArray(result);
    }


    /**
     * 交集 求两个数组的交集
     *
     * @param arr1 数组1
     * @param arr2 数组2
     * @return 交集
     */
    private static Object[] intersect(Object[] arr1, Object[] arr2) {
        Map<Object, Boolean> map = new HashMap<>();
        LinkedList<Object> list = new LinkedList<>();
        if (arr1 != null) {
            for (Object str : arr1) {
                if (!map.containsKey(str)) {
                    map.put(str, Boolean.FALSE);
                }
            }
        }
        if (arr2 != null) {
            for (Object str : arr2) {
                if (map.containsKey(str)) {
                    map.put(str, Boolean.TRUE);
                }
            }
        }
        for (Entry<Object, Boolean> e : map.entrySet()) {
            if (e.getValue().equals(Boolean.TRUE)) {
                list.add(e.getKey());
            }
        }

        return list.toArray();
    }

    /**
     * 交集 求多个数组的交集
     *
     * @param arr1 可变参数的交集,至少两个参数,才有交集的意义
     * @return 交集
     */
    public static Object[] intersect(Object[]... arr1) {
        if (arr1 == null || arr1.length < 2) {
            return new Object[]{};
        }
        if (arr1.length == 2) {
            return intersect(arr1[0], arr1[1]);
        }
        return intersect(arr1[arr1.length - 1], intersect(Arrays.copyOf(arr1, arr1.length - 1)));
    }

    /**
     * 求多组数组的余集(差集)
     * 我觉得应该是并集 减去交集
     *
     * @param arr1
     * @return
     */
    public static Object[] minus(Object[]... arr1) {
        if (arr1 != null) {
            Object[] union = union(arr1);
            Object[] intersect = intersect(arr1);
            List<Object> unionList = Arrays.asList(union);
            List<Object> intersectList = Arrays.asList(intersect);
            ArrayList<Object> unionArrayList = new ArrayList<>(unionList);
            ArrayList<Object> intersectArrayList = new ArrayList<>(intersectList);
            unionArrayList.removeAll(intersectArrayList);
            return unionArrayList.toArray();
        }
        return null;
    }

    /**
     * 差集 求两个数组的差集
     *
     * @param arr1 数组1
     * @param arr2 数组2
     * @return 差集
     */
    private static Object[] minus(Object[] arr1, Object[] arr2) {
        LinkedList<Object> list = new LinkedList<Object>();
        LinkedList<Object> history = new LinkedList<Object>();
        Object[] longerArr = arr1;
        Object[] shorterArr = arr2;
        // 找出较长的数组来减较短的数组
        if (arr1 != null && arr2 != null) {
            if (arr1.length > arr2.length) {
                longerArr = arr2;
                shorterArr = arr1;
            }
        }
        if (longerArr != null) {
            for (Object str : longerArr) {
                if (!list.contains(str)) {
                    list.add(str);
                }
            }
        }
        if (shorterArr != null) {
            for (Object str : shorterArr) {
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

        Object[] result = {};
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

   /* public static void main(String[] args) {
        Integer[] str1 = {1, 2, 3, 4, 5, 0};
        Integer[] str2 = {1, 4, 5, 6};
        Integer[] str3 = {1, 7, 9, 10, 12};
        Integer[] str4 = {1, 7, 9, 10, 12, 19};
        Object[] minus = minus(str1, str2, str3, str4);
        System.out.println(Arrays.toString(minus));
    }*/

}
