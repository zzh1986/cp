package com.eleven.five.service;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.eleven.five.entity.Adjacent;
import com.eleven.five.entity.GroupEntity;
import com.eleven.five.entity.RepeatTimes;
import com.eleven.five.entity.TenRepeat;
import com.eleven.five.mapper.AdjacentMapper;
import com.eleven.five.mapper.RepeatTimesMapper;
import com.eleven.five.mapper.TenRepeatMapper;
import com.eleven.five.util.ArrayUtils;
import com.eleven.five.util.FiveUtil;
import com.eleven.five.util.GroupUtils;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhaozhihong
 */
@Service
public class GroupService {
    @Autowired
    private AdjacentMapper adjacentMapper;

    @Autowired
    private TenRepeatMapper tenRepeatMapper;

    @Autowired
    private RepeatTimesMapper repeatTimesMapper;

    public List<Object[]> getGroupResult(String date, String period) throws IOException {
        List<Object[]> oneOrTwoGroup = getOneOrTwoGroup(date, period, 6, 3);
        List<Object[]> oneOrTwoGroup1 = getOneOrTwoGroup(date, period, 5, 3);
        List<Object[]> oneOrTwoGroup2 = getOneOrTwoGroup(date, period, 4, 2);
        //List<Object[]> oneOrTwoGroup3 = getOneOrTwoGroup(date, period, 9, 4);
        //List<Object[]> oneOrTwoGroup4 = getOneOrTwoGroup(date, period, 2, 1);
        List<Object[]> result = new ArrayList<>();
        Object[] standard = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11"};
//        if(oneOrTwoGroup3!=null){
//            if(oneOrTwoGroup3.size()==2){
//                Object[] minus = ArrayUtils.minus(standard, ArrayUtils.union(oneOrTwoGroup3.get(0), oneOrTwoGroup3.get(1)));
//                if(minus.length==3){
//                    result.add(minus);
//                    return result;
//                }
//            }
//            if(oneOrTwoGroup3.size()==1){
//                Object[] minus = ArrayUtils.minus(standard, oneOrTwoGroup3.get(0));
//                result.add(minus);
//                return result;
//            }
//        }
        if (oneOrTwoGroup != null && oneOrTwoGroup.size() == 1 && oneOrTwoGroup1 != null && oneOrTwoGroup1.size() == 1 && oneOrTwoGroup2 != null && oneOrTwoGroup2.size() == 1) {
            return oneOrTwoGroup2;
        }
        if (oneOrTwoGroup1 != null && oneOrTwoGroup1.size() == 1 && oneOrTwoGroup2 != null && oneOrTwoGroup2.size() == 1) {
            Object[] minus = ArrayUtils.minus(standard, ArrayUtils.union(oneOrTwoGroup1.get(0), oneOrTwoGroup2.get(0)));
            result.add(minus);
            return result;
        }
        if (oneOrTwoGroup1 != null && oneOrTwoGroup1.size() == 2) {
            Object[] minus = ArrayUtils.minus(ArrayUtils.union(oneOrTwoGroup1.get(0), oneOrTwoGroup1.get(1)), ArrayUtils.intersect(oneOrTwoGroup1.get(0), oneOrTwoGroup1.get(1)));
            if (minus.length < 4) {
                result.add(minus);
            } else {
                minus = ArrayUtils.minus(standard, ArrayUtils.union(oneOrTwoGroup1.get(0), oneOrTwoGroup1.get(1)));
                if (minus.length < 4) {
                    result.add(minus);
                }
            }
            return result;
        }
        if (oneOrTwoGroup != null && oneOrTwoGroup.size() == 1 && oneOrTwoGroup2 != null && oneOrTwoGroup2.size() == 1) {
            if (ArrayUtils.union(oneOrTwoGroup.get(0), oneOrTwoGroup2.get(0)).length == oneOrTwoGroup.get(0).length) {
                Object[] minus = ArrayUtils.minus(oneOrTwoGroup.get(0), oneOrTwoGroup2.get(0));
                result.add(minus);
                return result;
            }
            Object[] minus = ArrayUtils.minus(standard, ArrayUtils.union(oneOrTwoGroup.get(0), oneOrTwoGroup2.get(0)));
            if (minus.length != 1 && minus.length < 5) {
                result.add(minus);
                return result;
            }
            return oneOrTwoGroup2;
        }
        if (oneOrTwoGroup1 != null && oneOrTwoGroup1.size() == 2) {
            Object[] minus = ArrayUtils.minus(ArrayUtils.union(oneOrTwoGroup1.get(0), oneOrTwoGroup1.get(1)), ArrayUtils.intersect(oneOrTwoGroup1.get(0), oneOrTwoGroup1.get(1)));
            result.add(minus);
            return result;
        }
        if (oneOrTwoGroup2 != null && oneOrTwoGroup2.size() == 2) {
            Object[] minus = ArrayUtils.minus(standard, ArrayUtils.minus(ArrayUtils.union(oneOrTwoGroup2.get(0), oneOrTwoGroup2.get(1)), ArrayUtils.intersect(oneOrTwoGroup2.get(0), oneOrTwoGroup2.get(1))));
            if (minus.length < 4) {
                result.add(minus);
                return result;
            }

        }
//        if(oneOrTwoGroup!=null&&oneOrTwoGroup.size()==1){
//            Object[] minus = ArrayUtils.minus(standard, oneOrTwoGroup.get(0));
//            result.add(minus);
//            return result;
//        }
        if (oneOrTwoGroup != null && oneOrTwoGroup.size() == 2) {
            Object[] minus = ArrayUtils.minus(ArrayUtils.union(oneOrTwoGroup.get(0), oneOrTwoGroup.get(1)), ArrayUtils.intersect(oneOrTwoGroup.get(0), oneOrTwoGroup.get(1)));
            if (minus.length < 4) {
                result.add(minus);
                return result;
            }

        }
        /*if (oneOrTwoGroup!=null&&oneOrTwoGroup2!=null){
            Object[] intersect = ArrayUtils.intersect(oneOrTwoGroup.get(0), oneOrTwoGroup2.get(0));
            result.add(intersect);
            return result;
        }*/

        return result;
    }

    /**
     * 倒序的哦
     *
     * @param date
     * @param period
     * @return
     * @throws IOException
     */
    private List<String[]> getTenTimes(String date, String period, int num) throws IOException {
        String url = "http://caipiao.163.com/award/gd11xuan5/" + date + ".html";
        Elements elements = Jsoup.connect(url).get().select("[data-period]");
        List<String> fiveList = new ArrayList<>();
        FiveUtil.getOneGroupNumber(fiveList, elements);

        Integer perNum = Integer.valueOf(period);
        if (perNum < num || perNum > 83) {
            return null;
        }
        //找出最近的10期数据 例如:perNum = 56 [47,56]
        List<String[]> stringList = new ArrayList<>();
        //此处改为从开始的爬取
        t1:
        for (int i = perNum - num + 1; i < perNum + 1; i++) {
            // 期号 18062042
            String iFormat = String.valueOf(i).length() < 2 ? "0" + i : String.valueOf(i);
            String dp = date.substring(2, date.length()) + iFormat;
            for (String s : fiveList) {
                if (s.contains(dp)) {
                    String[] number = s.substring(0, 15).split("[\\s]+");
                    stringList.add(number);
                    continue t1;
                }
            }
        }
        return stringList;
    }

    /**
     * 写一个方法,用于获取相应的参数,其中的参数有
     *
     * @param date   string
     * @param period String
     * @param n      int 每组几个数字
     * @param m      int 相同数字个数
     * @return list</> 一个集合 一个或两个数组
     */
    private List<Object[]> getOneOrTwoGroup(String date, String period, int n, int m) throws IOException {
        for (int z = 10; z > 0; z--) {
            //先准备目标数据 n(10)组
            List<String[]> tenTimes = getTenTimes(date, period, z);
            //准备好所有分类 allC  8组 出5个试试
            Object[] standard = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11"};
            List<GroupEntity> allC = GroupUtils.getAllC(standard, n, 2);
            List<Object[]> objectList = new ArrayList<>();
            t1:
            for (int i = 0; i < allC.size(); i++) {
                for (int j = 0; j < tenTimes.size(); j++) {
                    if (ArrayUtils.intersect(allC.get(i).getOne(), tenTimes.get(j)).length != m) {
                        continue t1;
                    }
                }
                //到这里说明全部都包括了
                Object[] one = allC.get(i).getOne();
                objectList.add(one);
            }
            if (objectList.size() == 2) {
                return objectList;
            } else if (objectList.size() == 1) {
                return objectList;
            }
        }
        return null;
    }


    public Map<String, Object> getFiveNumbers(String date, String period) throws IOException {
        List<String[]> tenTimeList = getTenTimes(date, period, 10);
        Integer[] count = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Integer[] beforeFive = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < tenTimeList.size(); i++) {
            if (i < 5) {
                for (int j = 0; j < tenTimeList.get(i).length; j++) {
                    int index = Integer.valueOf(tenTimeList.get(i)[j]) - 1;
                    count[index]++;
                }
            } else {
                for (int j = 0; j < tenTimeList.get(i).length; j++) {
                    int index = Integer.valueOf(tenTimeList.get(i)[j]) - 1;
                    beforeFive[index]++;
                }
            }
        }
        Integer[] total = new Integer[11];
        for (int i = 0; i < total.length; i++) {
            total[i] = count[i] + beforeFive[i];
        }
        Integer[] three = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int j = 0; j < 5; j++) {
            three[Integer.valueOf(tenTimeList.get(0)[j]) - 1]++;
            three[Integer.valueOf(tenTimeList.get(8)[j]) - 1]++;
            three[Integer.valueOf(tenTimeList.get(9)[j]) - 1]++;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("period", date + 0 + period);
        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 3) {
                numbers.add(String.valueOf(i + 1).length() == 1 ? ("0" + (i + 1)) : ("" + (i + 1)));
            }
        }
        map.put("numbers", numbers);
        Integer[] ou = new Integer[11];
        for (int i = 0; i < ou.length; i++) {
            ou[i] = count[i] + beforeFive[i] + total[i] + three[i];
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < ou.length; i++) {
            if (ou[i] % 2 == 0) {
                result.add(i + 1);
            }
        }
        System.out.println(Arrays.toString(count));
        System.out.println(Arrays.toString(beforeFive));
        System.out.println(Arrays.toString(total));
        System.out.println(Arrays.toString(three));
        System.out.println(result);
        System.out.println("================================");
        return map;
    }

    /**
     * @param date   日期
     * @param period 期号
     * @return
     * @throws IOException
     */
    public List<Integer> getSixNumber(String date, String period, List<Integer> list) throws IOException {
        List<String[]> tenTimeList = getTenTimes(date, period, 10);
        Integer[] three = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < list.size(); i++) {
                three[Integer.valueOf(tenTimeList.get(list.get(i))[j]) - 1]++;
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("period", date + 0 + period);
        List<String> numbers = new ArrayList<>();
        map.put("numbers", numbers);
        Integer[] ou = new Integer[11];
        for (int i = 0; i < ou.length; i++) {
            ou[i] = three[i];
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < ou.length; i++) {
            if (ou[i] % 2 == 0) {
                result.add(i + 1);
            }
        }
        return result;
    }

    /**
     * 田忌赛马
     *
     * @param date
     * @param period
     * @return
     * @throws IOException
     */
    public List<Integer> getTianJiSaiMa(String date, String period) throws IOException {
        List<String[]> tenTimeList = getTenTimes(date, period, 10);
        String[] oneGroup = tenTimeList.get(0);
        String[] twoGroup = tenTimeList.get(9);
        String[] threeGroup = tenTimeList.get(1);
        String[] fourGroup = tenTimeList.get(8);
        String[] all = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11"};
        //数据处理
        //1.处理前两组的交集并集补集
        Object[] oneIntersect = ArrayUtils.intersect(oneGroup, twoGroup);
        Object[] oneUnion = ArrayUtils.minus(ArrayUtils.union(oneGroup, twoGroup), oneIntersect);
        Object[] oneMinus = ArrayUtils.minus(all, ArrayUtils.union(oneGroup, twoGroup));

        Object[] twoIntersect = ArrayUtils.intersect(threeGroup, fourGroup);
        Object[] twoUnion = ArrayUtils.minus(ArrayUtils.union(threeGroup, fourGroup), twoIntersect);
        Object[] twoMinus = ArrayUtils.minus(all, ArrayUtils.union(threeGroup, fourGroup));


        Object[] union = ArrayUtils.union(ArrayUtils.intersect(oneIntersect, twoIntersect), ArrayUtils.minus(oneUnion, twoUnion), ArrayUtils.minus(oneMinus, twoMinus));
        List<Integer> resultList = Arrays.asList(Convert.toIntArray(union));
        return resultList;
    }

    /**
     * 用于将前一组数据的计算结果返回去
     *
     * @param date
     * @param period
     * @return
     */
    public List<String> getSixList(String date, String period) throws IOException {
        //获取下一期的中奖数据  18080138
        Integer[] nextAwardInt = getNextAwardNumber(date, period);
        Integer[] group = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayList<Object[]> cmn = GroupUtils.cmn(group, 3);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < cmn.size(); i++) {
            Integer[] objects = Convert.toIntArray(cmn.get(i));
            List<Integer> sixNumber = getSixNumber(date, period, Arrays.asList(objects));
            if (sixNumber.size() == 6 && ArrayUtils.intersect(nextAwardInt, sixNumber.toArray()).length >= 4) {
                result.add(Arrays.toString(objects));
            }
        }
        return result;
    }

    public Integer[] getNextAwardNumber(String date, String period) throws IOException {
        String url = "http://caipiao.163.com/award/gd11xuan5/" + date + ".html";
        Elements elements = Jsoup.connect(url).get().select("[data-period=" + date.substring(2) + (Integer.valueOf(period) + 1) + "]");
        String nextAward = elements.get(0).attr("data-award");
        return Convert.toIntArray(nextAward.split("\\s"));
    }

    public Object getMostFrequently(String date, String period) throws IOException {
        List result = new ArrayList();
        for (int i = Integer.valueOf(period) - 2; i < Integer.valueOf(period); i++) {
            List<String> sixList = getSixList(date, String.valueOf(i));
            result.addAll(sixList);
        }
        Map<Object, Integer> repeatNum = ArrayUtils.getRepeatNum(result.toArray());
        List<Integer> num = new ArrayList<>();
        for (Map.Entry<Object, Integer> entry : repeatNum.entrySet()) {
            num.add(entry.getValue());
        }
        Object[] objects = num.toArray();
        Arrays.sort(objects);
        Object key = getKey(repeatNum, objects[objects.length - 1]);
        System.out.println(key);
        return key;
    }

    public Object getSixLists(String date, String period) throws IOException {
        Integer[] group = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayList<Object[]> cmn = GroupUtils.cmn(group, 3);
        List result = new ArrayList<>();
        for (int i = 0; i < cmn.size(); i++) {
            Integer[] objects = Convert.toIntArray(cmn.get(i));
            List<Integer> sixNumber = getSixNumber(date, period, Arrays.asList(objects));
            if (sixNumber.size() == 6) {
                result.add(Arrays.toString(sixNumber.toArray()));
            }
        }
        return result;
    }

    /**
     * hashmap 通过value 获取key
     *
     * @param map
     * @param value
     * @return
     */
    public Object getKey(Map map, Object value) {
        List<Object> keyList = new ArrayList<>();
        for (Object key : map.keySet()) {
            if (map.get(key).equals(value)) {
                keyList.add(key);
            }
        }
        return keyList;
    }

    public List getMaxSimularly(String date, String period) throws IOException {
        Object mostFrequently = getMostFrequently(date, period);
        List<String> mostFrequently1 = (List) mostFrequently;
        List result = new ArrayList();
        if (mostFrequently1.size() != 0) {
            for (String s : mostFrequently1) {
                String[] split = s.substring(1, s.lastIndexOf("]")).split(",");
                Integer[] integers = Convert.toIntArray(split);
                List<Integer> sixNumber = getSixNumber(date, period, Arrays.asList(integers));
                result.add(Arrays.toString(sixNumber.toArray()));
            }
        }
        return result;
    }

    public void saveAdjacentNumbers(String date, String period) throws IOException {
        //每次先清空
        adjacentMapper.deleteAll();
        tenRepeatMapper.deleteAll();
        //爬取相应的数据 然后保存到数据库
        List<Object[]> oneDayNumbers = getOneDayNumbers(date, period);
        for (int i = 0; i < oneDayNumbers.size() - 1; i++) {
            Object[] intersect = ArrayUtils.intersect(oneDayNumbers.get(i), oneDayNumbers.get(i + 1));
            Adjacent adjacent = new Adjacent();
            adjacent.setId(null);
            adjacent.setRepeatNum(intersect.length);
            adjacent.setAwardNum(Arrays.toString(oneDayNumbers.get(i + 1)));
            adjacent.setPeriod(date + (String.valueOf(i + 2).length() == 1 ? ("0" + (i + 2)) : (i + 2)));
            adjacentMapper.save(adjacent);
        }
//        for (int i = 0; i < oneDayNumbers.size() - 9; i++) {
//            Object[] intersect = ArrayUtils.intersect(oneDayNumbers.get(i), oneDayNumbers.get(i + 9));
//            TenRepeat tenRepeat = new TenRepeat();
//            tenRepeat.setId(null);
//            tenRepeat.setRepeatNum(intersect.length);
//            tenRepeat.setAwardNum(Arrays.toString(oneDayNumbers.get(i+1)));
//            tenRepeat.setPeriod(date + (String.valueOf(i + 10).length() == 1 ? ("0" + (i + 10)) : (i + 10)));
//            tenRepeatMapper.save(tenRepeat);
//        }
        int m = 4;
        for (int i = 0; i < oneDayNumbers.size() - oneDayNumbers.size() / m; i++) {
            Object[] intersect = ArrayUtils.intersect(oneDayNumbers.get(i), oneDayNumbers.get(i + oneDayNumbers.size() / m));
            TenRepeat tenRepeat = new TenRepeat();
            tenRepeat.setId(null);
            tenRepeat.setRepeatNum(intersect.length);
            tenRepeat.setAwardNum(Arrays.toString(oneDayNumbers.get(i + 1)));
            tenRepeat.setPeriod(date + (String.valueOf(i + 1 + oneDayNumbers.size() / m).length() == 1 ? ("0" + (i + 1 + oneDayNumbers.size() / m)) : (i + 1 + oneDayNumbers.size() / m)));
            tenRepeatMapper.save(tenRepeat);
        }
    }

    private List<Object[]> getOneDayNumbers(String date, String period) throws IOException {
        //还是网易 网址爬虫  20180915
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if (StringUtils.isEmpty(date)) {
            date = sdf.format(new Date());
        }
        String url = "http://caipiao.163.com/award/gd11xuan5/" + date + ".html";
        Elements elements = Jsoup.connect(url).get().select("[data-period]");
        List<String> fiveList = new ArrayList<>();
        FiveUtil.getOneGroupNumber(fiveList, elements);
        List<Object[]> oneDayNumbers = new ArrayList<>();
        for (int i = 0; i < Integer.valueOf(period); i++) {
            String[] split = fiveList.get(i).substring(0, 14).split("\\s");
            oneDayNumbers.add(split);
        }
        return oneDayNumbers;
    }

    public String getOneDayPercent(String date) throws IOException {
//        saveAdjacentNumbers(date, "84");
        Integer[] repeatNumbers = tenRepeatMapper.findRepeatNumbers();
        // 用数组来进行统计
        Integer[] numbers = {0, 0, 0, 0, 0, 0};

        for (int i = 0; i < repeatNumbers.length; i++) {
            switch (repeatNumbers[i]) {
                case 0:
                    numbers[0]++;
                    break;
                case 1:
                    numbers[1]++;
                    break;
                case 2:
                    numbers[2]++;
                    break;
                case 3:
                    numbers[3]++;
                    break;
                case 4:
                    numbers[4]++;
                    break;
                default:
                    numbers[5]++;
                    break;
            }

        }
        int[] missing = new int[numbers[1] + 1];
        int index = 0;
        for (int i = 0; i < repeatNumbers.length; i++) {
            if (repeatNumbers[i] == 1) {
                index++;
            } else {
                missing[index]++;
            }
        }
        //TODO 总共 83个数据 需要分别统计每个的概率
        String[] adjacentPercent = new String[6];
        NumberFormat pnf = NumberFormat.getPercentInstance();
        for (int i = 0; i < adjacentPercent.length; i++) {
            adjacentPercent[i] = pnf.format(1.0 * numbers[i] / repeatNumbers.length);
        }

        //TODO 每 10组的概率待会再统计
        return Arrays.toString(adjacentPercent);

    }

    public Map getOneToElevenNumber(String date, String period) throws IOException {
        List<String[]> tenTimeList = getTenTimes(date, period, 10);
        int[] times = new int[11];
        for (int i = 0; i < tenTimeList.size(); i++) {
            for (int j = 0; j < tenTimeList.get(i).length; j++) {
                getTimes(tenTimeList, times, i, j);
            }

        }
        //统计3期号
        int[] threePeriod = new int[11];
        for (int i = 0; i < 5; i++) {
            getTimes(tenTimeList, threePeriod, 0, i);
            getTimes(tenTimeList, threePeriod, tenTimeList.size() - 2, i);
            getTimes(tenTimeList, threePeriod, tenTimeList.size() - 1, i);
        }
        //统计结束,开始进行分组
        Map<String, List<Integer>> map = new HashMap<>();
        List<Integer> small = new ArrayList<>();
        List<Integer> middle1 = new ArrayList<>();
        List<Integer> middle2 = new ArrayList<>();
        List<Integer> big = new ArrayList<>();
        for (int i = 0; i < times.length; i++) {
            if (times[i] < 4) {
                small.add(i + 1);
            } else if (times[i] > 5) {
                big.add(i + 1);
            } else if (times[i] == 4) {
                middle1.add(i + 1);
            } else {
                middle2.add(i + 1);
            }
        }

        List<Integer> three0 = new ArrayList<>();
        List<Integer> three1 = new ArrayList<>();
        List<Integer> three2 = new ArrayList<>();
        List<Integer> three3 = new ArrayList<>();
        for (int i = 0; i < threePeriod.length; i++) {
            switch (threePeriod[i]) {
                case 0:
                    three0.add(i + 1);
                    break;
                case 1:
                    three1.add(i + 1);
                    break;
                case 2:
                    three2.add(i + 1);
                    break;
                default:
                    three3.add(i + 1);
                    break;
            }
        }
        map.put("冷号:", small);
        map.put("温号4:", middle1);
        map.put("温号5:", middle2);
        map.put("热号:", big);
        map.put("0:", three0);
        map.put("1:", three1);
        map.put("2:", three2);
        map.put("3:", three3);
        return map;
    }

    private void getTimes(List<String[]> tenTimeList, int[] times, int i, int j) {
        switch (tenTimeList.get(i)[j]) {
            case "01":
                times[0]++;
                break;
            case "02":
                times[1]++;
                break;
            case "03":
                times[2]++;
                break;
            case "04":
                times[3]++;
                break;
            case "05":
                times[4]++;
                break;
            case "06":
                times[5]++;
                break;
            case "07":
                times[6]++;
                break;
            case "08":
                times[7]++;
                break;
            case "09":
                times[8]++;
                break;
            case "10":
                times[9]++;
                break;
            default:
                times[10]++;
                break;
        }
    }

    public Map getMaxPercentFromTenNumber(String date, String period) throws IOException {
        Integer[] ten = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayList<Object[]> cmn = GroupUtils.cmn(ten, 2);
        String[] eleven = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11"};
        Map<String, List<Double>> map = new HashMap<>();
        if (Integer.valueOf(period) < 15) {
            return null;
        }
        List<String[]> tenTimes = getTenTimes(date, String.valueOf(period), Integer.valueOf(period));
        for (int j = 0; j < cmn.size(); j++) {
            int minusIndex = 0;
            int minuxNotIn = 0;
            int minusElevenIndex = 0;
            int minusElevenNotIn = 0;
            for (int i = Integer.valueOf(period) - 10; i < Integer.valueOf(period); i++) {
                // 获取下一组数据
                // Integer[] awardNumber = getNextAwardNumber(date, String.valueOf(i));
                Integer[] awardNumber = Convert.toIntArray(tenTimes.get(i));
                Object[] objects = cmn.get(j);
                Object[] intersect = Convert.toIntArray(ArrayUtils.intersect(tenTimes.get(i - 10 + (Integer) objects[0]), tenTimes.get(i - 10 + (Integer) objects[1])));
                Object[] union = Convert.toIntArray(ArrayUtils.union(tenTimes.get(i - 10 + (Integer) objects[0]), tenTimes.get(i - 10 + (Integer) objects[1])));
                Object[] minus = Convert.toIntArray(ArrayUtils.minus(union, intersect));
                Object[] minusEleven = Convert.toIntArray(ArrayUtils.minus(eleven, minus));
                if (ArrayUtils.intersect(minus, awardNumber).length > minus.length / 2) {
                    minusIndex++;
                } else {
                    minuxNotIn++;
                }
                if (ArrayUtils.intersect(minusEleven, awardNumber).length > minus.length / 2) {
                    minusElevenIndex++;
                } else {
                    minusElevenNotIn++;
                }
            }
            List<Double> list = new ArrayList<>();
            list.add(minusIndex * 1.0 / (minusIndex + minuxNotIn));
            list.add(minusElevenIndex * 1.0 / (minusElevenIndex + minusElevenNotIn));
            map.put(Arrays.toString(cmn.get(j)), list);
        }
        return map;
    }

    /**
     * 保存10天的数据进行分析
     */
    void getAnalysisFromTenDay(String date) {
        //通过循环获取当前日期前10天的数据,并分析统计到数据库

//        for (int i = 0; i <  ; i++) {
//
//        }
    }

    /**
     * 获取两个胆码
     *
     * @param date
     * @param period
     * @return
     */
    public List<Object[]> getTwoNumbers(String date, String period) throws IOException {
        //从小到大的顺序咯
        List<String[]> tenTimes = getTenTimes(date, period, 10);
        //统计次数
        Map oneToElevenNumber = getOneToElevenNumber(date, period);
//        map.put("冷号:", small);
//        map.put("温号4:", middle1);
//        map.put("温号5:", middle2);
//        map.put("热号:", big);
//        map.put("0:", three0);
//        map.put("1:", three1);
//        map.put("2:", three2);
//        map.put("3:", three3);
        Object[] middle1 = ((List<Integer>) oneToElevenNumber.get("温号4:")).toArray();
        Object[] three1 = ((List<Integer>) oneToElevenNumber.get("1:")).toArray();
        Object[] middle2 = ((List<Integer>) oneToElevenNumber.get("温号5:")).toArray();
        Object[] three3 = ((List<Integer>) oneToElevenNumber.get("2:")).toArray();
        Object[] intersect1 = ArrayUtils.intersect(Convert.toIntArray(tenTimes.get(9)), middle1, three1);
        Object[] intersect2 = ArrayUtils.intersect(Convert.toIntArray(tenTimes.get(9)), middle2, three3);
        List<Object[]> target = new ArrayList<>();
        target.add(intersect1);
        target.add(intersect2);
        return target;
    }

    //如果有时间可以统计一下 与每一组出现的数字的交集个数
    public List<Integer> getRepeatTimes(String date, String period) throws IOException {
        List<String[]> tenTimes = getTenTimes(date, period, 10);
        Integer[] awardNumber = getNextAwardNumber(date, period);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < tenTimes.size(); i++) {
            list.add(ArrayUtils.intersect(awardNumber, Convert.toIntArray(tenTimes.get(i))).length);
        }
        return list;
    }

    public String saveRepeatTimes(String date, String period) throws Exception {
        //获取前10天的数据
        List<String> tenDateList = getLastTenDate(date);
        List<RepeatTimes> repeatTimesList = new ArrayList<>();
        for (int i = 0; i < tenDateList.size(); i++) {
            for (int j = 10; j < 84; j++) {
                List<Integer> repeatTimes = getRepeatTimes(tenDateList.get(i), String.valueOf(j));
                RepeatTimes repeatTimesNew = new RepeatTimes();
                repeatTimesNew.setId(null);
                repeatTimesNew.setOneTimes(repeatTimes.get(0));
                repeatTimesNew.setTwoTimes(repeatTimes.get(1));
                repeatTimesNew.setThreeTimes(repeatTimes.get(2));
                repeatTimesNew.setFourTimes(repeatTimes.get(3));
                repeatTimesNew.setFiveTimes(repeatTimes.get(4));
                repeatTimesNew.setSixTimes(repeatTimes.get(5));
                repeatTimesNew.setSevenTimes(repeatTimes.get(6));
                repeatTimesNew.setEightTimes(repeatTimes.get(7));
                repeatTimesNew.setNineTimes(repeatTimes.get(8));
                repeatTimesNew.setTenTimes(repeatTimes.get(9));
                repeatTimesNew.setPeriod(tenDateList.get(i) + String.valueOf(j));
                repeatTimesList.add(repeatTimesNew);
            }
        }
        repeatTimesMapper.saveAll(repeatTimesList);
        //暂时先定为保存成功
        return "保存成功!";
    }

    /**
     * 获取前10天
     *
     * @param date
     * @return
     * @throws ParseException
     */
    private List<String> getLastTenDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date dateStyle = sdf.parse(date);
        List<String> dateList = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            DateTime dateTime = DateUtil.offsetDay(dateStyle, -i);
            dateList.add(dateTime.toString("yyyyMMdd"));
        }
        return dateList;
    }
}