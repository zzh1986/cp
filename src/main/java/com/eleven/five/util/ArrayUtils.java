/**
 * <p>Copyright ® 中国证监会中央监管信息平台版权所有。</p>
 * 类名:ArrayUtil
 * 创建人:CKW    创建时间:2015-08-26
 */

package com.eleven.five.util;

import java.lang.reflect.Array;
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
//    public static Object[] minus(Object[]... arr1) {
//        if (arr1 != null) {
//            Object[] union = union(arr1);
//            Object[] intersect = intersect(arr1);
//            List<Object> unionList = Arrays.asList(union);
//            List<Object> intersectList = Arrays.asList(intersect);
//            ArrayList<Object> unionArrayList = new ArrayList<>(unionList);
//            ArrayList<Object> intersectArrayList = new ArrayList<>(intersectList);
//            unionArrayList.removeAll(intersectArrayList);
//            return unionArrayList.toArray();
//        }
//        return null;
//    }

    /**
     * 差集 求两个数组的差集
     *
     * @param arr1 数组1
     * @param arr2 数组2
     * @return 差集
     */
    //TODO 这里做一个自定义的差集
    public static Object[] minus(Object[] arr1, Object[] arr2) {
        Object[] intersect = intersect(arr1, arr2);
        List<Object> arr1List = Arrays.asList(arr1);
        List<Object> result = new ArrayList<>();
        if (null != intersect && intersect.length != 0) {
            List<Object> intersectList = Arrays.asList(intersect);
            for (int i = 0; i <arr1List.size() ; i++) {
                if(!intersectList.contains(arr1List.get(i))){
                    result.add(arr1List.get(i));
                }
            }
        }
        return result.toArray();
    }
    /*private static Object[] minus(Object[] arr1, Object[] arr2) {
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
    }*/

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

    /**
     * 统计数组中相同内容出现的次数
     *
     * @param args
     */

    public static Map<Object, Integer> getRepeatNum(Object[] args) {

        Map<Object, Integer> sameElement = new HashMap<>();
        for (int i = 0, k = args.length; i < k; i++) {
            Integer sum = sameElement.get(args[i]);
            sameElement.put(args[i], sum == null ? 1 : sum + 1);
        }
        return sameElement;
    }
    /**
     *  获取最大值的下标集合
     */
    public static List<Integer> maxIndex(int[] numGroup){
        int[] clone = numGroup.clone();
        Arrays.sort(clone);
        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < numGroup.length ; i++) {
            if (numGroup[i]==clone[clone.length-1]){
                index.add(i);
            }
        }
        return index;
    }

    /**
     *  获取最小值的下标集合
     */
    public static List<Integer> minIndex(int[] numGroup){
        int[] clone = numGroup.clone();
        Arrays.sort(clone);
        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < numGroup.length ; i++) {
            if (numGroup[i]==clone[0]){
                index.add(i);
            }
        }
        return index;
    }

    public static void main(String[] args) {
//        String[] num = {"[1,2,3]", "[4,5,6]", "[1,2,3]", "[4,5]", "[1,2,3]", "[4,5,6]", "[5,6,7]"};
//        Map<Object, Integer> repeatNum = getRepeatNum(num);
//        Integer[] intNum = {1, 2, 3, 1, 22, 1111, 1, 2, 33, 3, 4};
//        Map<Object, Integer> repeatIntNum = getRepeatNum(intNum);
//        /*for (Map.Entry<Object, Integer> entry : repeatNum.entrySet()){
//            System.out.println("出现的重复的内容"+entry.getKey()+"的次数是:"+entry.getValue());
//        }*/
//        for (Map.Entry<Object, Integer> entry : repeatIntNum.entrySet()) {
//            System.out.println("出现的重复的内容" + entry.getKey() + "的次数是:" + entry.getValue());
//        }
       /* Boolean[] booleans = {true,true,false};
        Boolean[] target = {true,true,true};
        System.out.println(intersect(booleans,target).length);*/

        /*Integer[] integers = {1,2,3,4,5,6,7,8,9,10,11};
        ArrayList<Object[]> cmn = GroupUtils.cmn(integers, 3);
        System.out.println(cmn.size());*/

        /*int[] test = {1,10,4,7,11,8,15,2,3,7,10,15,1};
        List<Integer> list = maxIndex(test);
        List<Integer> list1 = minIndex(test);
        System.out.println("最大值下标"+list);
        System.out.println("最小值下标"+list1);*/

        String[] s1 = {"01","02"};
        String[] s2 = {"03"};
        System.out.println(intersect(s1,s2).length);

    }
}
