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
import com.eleven.five.util.ShuJu;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
//        if (perNum < num || perNum > 83) {
//            return null;
//        }
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
        repeatTimesMapper.deleteAll();
        //获取前10天的数据
        List<String> tenDateList = getLastTenDate(date);
        List<RepeatTimes> repeatTimesList = new ArrayList<>();
        for (int i = 0; i < tenDateList.size(); i++) {
            List<String[]> tenTimes = getTenTimes(tenDateList.get(i), "84", 84);
            for (int j = 10; j < 84; j++) {
                int[] repeatTimes = new int[10];
                for (int k = 10; k > 0; k--) {
                    repeatTimes[10 - k] = ArrayUtils.intersect(tenTimes.get(j), tenTimes.get(j - k)).length;
                }
                RepeatTimes repeatTimesNew = new RepeatTimes();
                repeatTimesNew.setId(null);
                repeatTimesNew.setOneTimes(repeatTimes[0]);
                repeatTimesNew.setTwoTimes(repeatTimes[1]);
                repeatTimesNew.setThreeTimes(repeatTimes[2]);
                repeatTimesNew.setFourTimes(repeatTimes[3]);
                repeatTimesNew.setFiveTimes(repeatTimes[4]);
                repeatTimesNew.setSixTimes(repeatTimes[5]);
                repeatTimesNew.setSevenTimes(repeatTimes[6]);
                repeatTimesNew.setEightTimes(repeatTimes[7]);
                repeatTimesNew.setNineTimes(repeatTimes[8]);
                repeatTimesNew.setTenTimes(repeatTimes[9]);
                repeatTimesNew.setPeriod(tenDateList.get(i) + String.valueOf(j));
                repeatTimesList.add(repeatTimesNew);
            }

        }
        repeatTimesMapper.saveAll(repeatTimesList);
        //暂时先定为保存成功
        //前面的已经OK,下一步
        //获取本次的前11组数据才好


        return "保存成功!";
    }


    public List<String[]> getNextExpectNumbers(String date, String period) throws IOException {

        List<String[]> tenTimes = getTenTimes(date, period, 11);
        //获取交集对象
        Integer[] target = new Integer[10];
        for (int i = 0; i < tenTimes.size() - 1; i++) {
            target[i] = ArrayUtils.intersect(tenTimes.get(i), tenTimes.get(10)).length;
        }
        RepeatTimes repeatTimes1 = getRepeatTimesRandom(target);
        RepeatTimes repeatTimes2 = new RepeatTimes();
        String[] standard = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11"};
        ArrayList<Object[]> cmn = GroupUtils.cmn(standard, 5);
        List<String[]> resultList = new ArrayList<>();
        if (Integer.valueOf(repeatTimes1.getPeriod().substring(repeatTimes1.getPeriod().length() - 2)) < 83 && Integer.valueOf(repeatTimes1.getPeriod().substring(repeatTimes1.getPeriod().length() - 2)) > 10) {
            repeatTimes2.setPeriod(String.valueOf(Long.valueOf(repeatTimes1.getPeriod()) + 1));
            Example<RepeatTimes> of = Example.of(repeatTimes2);
            RepeatTimes repeatTimes3 = repeatTimesMapper.findOne(of).get();
            getOneResult(tenTimes, cmn, resultList, repeatTimes3);
        }

        return resultList;
    }

    public void getOneResult(List<String[]> tenTimes, ArrayList<Object[]> cmn, List<String[]> resultList, RepeatTimes repeatTimes3) {
        for (int i = 0; i < cmn.size(); i++) {
            if (ArrayUtils.intersect(cmn.get(i), tenTimes.get(1)).length == repeatTimes3.getOneTimes()
//            && ArrayUtils.intersect(cmn.get(i), tenTimes.get(2)).length == repeatTimes3.getTwoTimes()
//            && ArrayUtils.intersect(cmn.get(i), tenTimes.get(3)).length == repeatTimes3.getThreeTimes()
                    && ArrayUtils.intersect(cmn.get(i), tenTimes.get(4)).length == repeatTimes3.getFourTimes() && ArrayUtils.intersect(cmn.get(i), tenTimes.get(5)).length == repeatTimes3.getFiveTimes() && ArrayUtils.intersect(cmn.get(i), tenTimes.get(6)).length == repeatTimes3.getSixTimes() && ArrayUtils.intersect(cmn.get(i), tenTimes.get(7)).length == repeatTimes3.getSevenTimes()
//            && ArrayUtils.intersect(cmn.get(i), tenTimes.get(8)).length == repeatTimes3.getEightTimes()
//            && ArrayUtils.intersect(cmn.get(i), tenTimes.get(9)).length == repeatTimes3.getNineTimes()
                    && ArrayUtils.intersect(cmn.get(i), tenTimes.get(10)).length == repeatTimes3.getTenTimes()) {
                //目标出现
                resultList.add(Convert.toStrArray(cmn.get(i)));
                break;
            }
        }

    }

    private RepeatTimes getRepeatTimesRandom(Integer[] target) {
        RepeatTimes repeatTimes = new RepeatTimes();
        RepeatTimes repeatTimes1 = new RepeatTimes();
        t1:
        for (int i = 9; i >= 0; i--) {
            t2:
            for (int j = 0; j <= i; j++) {
                target[i - j] = null;
                repeatTimes.setOneTimes(target[0]);
                repeatTimes.setTwoTimes(target[1]);
                repeatTimes.setThreeTimes(target[2]);
                repeatTimes.setFourTimes(target[3]);
                repeatTimes.setFiveTimes(target[4]);
                repeatTimes.setSixTimes(target[5]);
                repeatTimes.setSevenTimes(target[6]);
                repeatTimes.setEightTimes(target[7]);
                repeatTimes.setNineTimes(target[8]);
                repeatTimes.setTenTimes(target[9]);
                Example<RepeatTimes> example = Example.of(repeatTimes);
                List<RepeatTimes> repeatTimesList = repeatTimesMapper.findAll(example);
                if (repeatTimesList.size() == 0) {
                    continue t2;
                } else {
                    for (int k = 0; k < repeatTimesList.size(); k++) {
                        if (!repeatTimesList.get(k).getPeriod().endsWith("83")) {
                            repeatTimes1 = repeatTimesList.get(k);
                            break t1;
                        }
                    }

                }
            }

        }
        return repeatTimes1;
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
        for (int i = 1; i < 181; i++) {
            DateTime dateTime = DateUtil.offsetDay(dateStyle, -i);
            dateList.add(dateTime.toString("yyyyMMdd"));
        }
        return dateList;
    }

    /**
     * 获取一天内最多的三位组合
     *
     * @param date
     * @param period
     * @return
     */
    public List<String[]> getOneGroupFromThree(String date, String period) throws IOException {
        List<String[]> tenTimes = getTenTimes(date, period, 80);
        String[] standard = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11"};
        ArrayList<Object[]> cmn = GroupUtils.cmn(standard, 3);
        int[] times = new int[cmn.size()];
        for (int i = 0; i < tenTimes.size(); i++) {
            for (int j = 0; j < cmn.size(); j++) {
                if (ArrayUtils.intersect(tenTimes.get(i), cmn.get(j)).length == 3) {
                    times[j]++;
                }
            }
        }
        //TODO 该出可修改获取最大最小值
        List<Integer> maxIndex = ArrayUtils.maxIndex(times);
        List<String[]> resultList = new ArrayList<>();
        for (int i = 0; i < maxIndex.size(); i++) {
            resultList.add(Convert.toStrArray(cmn.get(maxIndex.get(i))));
        }
        List<String> sortMaxAppear = getSortMaxAppear(resultList);
        System.out.println(sortMaxAppear);
        return resultList;
    }

    public List<String> getSortMaxAppear(List<String[]> resultList) {
        int[] standard = new int[11];
        for (int i = 0; i < resultList.size(); i++) {
            for (int j = 0; j < resultList.get(i).length; j++) {
                switch (resultList.get(i)[j]) {
                    case "01":
                        standard[0]++;
                        break;
                    case "02":
                        standard[1]++;
                        break;
                    case "03":
                        standard[2]++;
                        break;
                    case "04":
                        standard[3]++;
                        break;
                    case "05":
                        standard[4]++;
                        break;
                    case "06":
                        standard[5]++;
                        break;
                    case "07":
                        standard[6]++;
                        break;
                    case "08":
                        standard[7]++;
                        break;
                    case "09":
                        standard[8]++;
                        break;
                    case "10":
                        standard[9]++;
                        break;
                    default:
                        standard[10]++;
                        break;
                }
            }
        }
        int[] clone = standard.clone();
        Arrays.sort(clone);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < clone.length; i++) {
            for (int j = 0; j < standard.length; j++) {
                //TODO 与前面一起修改
//                if ( standard[j] == clone[i]) {
                if (clone[i] != 0 && standard[j] == clone[i]) {
                    result.add(String.valueOf(j + 1).length() == 1 ? ("0" + String.valueOf(j + 1)) : String.valueOf(j + 1));
                    standard[j] = -1;
                    break;
                }
            }

        }
        return result;
    }

    /**
     * 该方法即将开启历史的新篇章,也是我的精华之作
     *
     * @param date
     * @param period
     * @return
     * @throws IOException
     */
    public List<String[]> getOuShuFromTwo(String date, String period) throws IOException {
        List<String[]> tenTimes = getTenTimes(date, period, 14);
        String[] oushu = {"02", "04", "06", "08", "10"};
        String[] jishu = {"01", "03", "05", "07", "09", "11"};
        //需要统计连续出现的次数
        List<Integer> farGroup = new ArrayList<>();
        List<Integer> nearGroup = new ArrayList<>();

        List<Integer> ouFarGroup = new ArrayList<>();
        List<Integer> ouNearGroup = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            farGroup.add(ArrayUtils.intersect(tenTimes.get(i), tenTimes.get(i + 9)).length);
            ouFarGroup.add(ArrayUtils.intersect(oushu, ArrayUtils.intersect(tenTimes.get(i), tenTimes.get(i + 9))).length);
        }
        for (int i = 0; i < 13; i++) {
            nearGroup.add(ArrayUtils.intersect(tenTimes.get(i), tenTimes.get(i + 1)).length);

            ouNearGroup.add(ArrayUtils.intersect(oushu, ArrayUtils.intersect(tenTimes.get(i), tenTimes.get(i + 1))).length);
        }
        System.out.println(farGroup + "\t" + ouFarGroup);
        System.out.println(nearGroup + "\t" + ouNearGroup);

        Object[] ou = ArrayUtils.union(ArrayUtils.intersect(tenTimes.get(5), oushu), ArrayUtils.intersect(tenTimes.get(13), oushu));
        Object[] ji = ArrayUtils.union(ArrayUtils.intersect(tenTimes.get(5), jishu), ArrayUtils.intersect(tenTimes.get(13), jishu));
        List<String[]> result = new ArrayList<>();
        result.add(Convert.toStrArray(ou));
        return result;
    }

    /**
     * 统计10期数据内三组数据各自的占比
     *
     * @param date
     * @param period
     * @return
     */
    public Map<String, Double> getThreeGroupPercent(String date, String period) throws IOException {
        List<String[]> tenTimes = getTenTimes(date, period, 10);
        if (Integer.valueOf(period) < 10) {
            return null;
        }
        Map<String, Double> result = new HashMap<>();
        double daShuPercent = ShuJu.daShuPercent(tenTimes);
        double zhiShuPercent = ShuJu.zhiShuPercent(tenTimes);
        double jiShuPercent = ShuJu.jiShuPercent(tenTimes);
        result.put("大数概率", daShuPercent);
        result.put("质数概率", zhiShuPercent);
        result.put("奇数概率", jiShuPercent);
        return result;
    }


    /**
     * 看下温号和冷号 热号的选择
     * @param date
     * @param period
     * @return
     * @throws IOException
     */
    public Object[] getColdWarmNumber(String date,String period) throws IOException {
        List<String[]> tenTimes = getTenTimes(date, period,10);
        String[] wenHao = Convert.toStrArray(ShuJu.getWenHao(tenTimes).toArray());
        String[] lenHao = Convert.toStrArray(ShuJu.getLenHao(tenTimes).toArray());
        String[] reHao = Convert.toStrArray(ShuJu.getReHao(tenTimes).toArray());
        String[] awardNumber = getTenTimes(date, "" + (Integer.valueOf(period) + 1), 1).get(0);
        String[] oneGroup = {"01","04","07","10"};
        String[] twoGroup = {"02","05","08","11"};
        String[] threeGroup = {"03","06","09"};
        Object[] intersect1 = ArrayUtils.intersect(wenHao, oneGroup);
        Object[] intersect2 = ArrayUtils.intersect(wenHao, twoGroup);
        Object[] intersect3 = ArrayUtils.intersect(wenHao, threeGroup);
        int[] repeatTimes = new int[3];
        repeatTimes[0]=intersect1.length;
        repeatTimes[1]=intersect2.length;
        repeatTimes[2]=intersect3.length;
        List<Integer> maxList = ArrayUtils.maxIndex(repeatTimes);
        List<Integer> minList = ArrayUtils.minIndex(repeatTimes);
        Object[] result = null;
        if(maxList.size()==1){
            switch (maxList.get(0)){
                case 0: result=intersect1;break;
                case 1: result=intersect2;break;
                default: result=intersect3;break;
            }

        }else if(maxList.size()==2){
            switch (minList.get(0)){
                case 0: result=intersect3;break;
                case 1: result=intersect3;break;
                default: result=intersect1;break;
            }
        }else{
            result = intersect3;
        }
        return result;
    }


    //TODO 这里需要先进行相应的统计
    /**
     * 获取下一组数据的胆码和补码
     *
     * @param date
     * @param period
     * @return
     */
    public Map<String, List<String[]>> getNextProgram(String date, String period) throws IOException {
        List<String[]> tenTimes = getTenTimes(date, period, 10);
        if (Integer.valueOf(tenTimes.get(9)[0]) >= 10 || Integer.valueOf(tenTimes.get(9)[1]) >= 10) {
            return null;
        }
        Integer base = Integer.valueOf(tenTimes.get(9)[0]) * 10 + Integer.valueOf(tenTimes.get(9)[1]);
        Integer shang = base / 9;
        Integer yushu = base % 9;
        Integer he = shang + yushu;
        Integer cha = Math.abs(shang - yushu);
        Integer one = he > 11 ? shang : he;
        Integer two = he > 11 ? yushu : cha;
        //第一个数据和第二个数据已经算出,下一步去匹配
        String oneStr = String.valueOf(one).length() == 1 ? ("0" + one) : ("" + one);
        String twoStr = String.valueOf(two).length() == 1 ? ("0" + two) : ("" + two);
        //准备好温号
        List<String> wenHao = ShuJu.getWenHao(tenTimes);
        int[] wenHaoOneIndex = new int[wenHao.size()];
        for (int i = 0; i < wenHao.size(); i++) {
            if (ShuJu.isDaShu(oneStr) ^ ShuJu.isDaShu(wenHao.get(i))) {
                wenHaoOneIndex[i]++;
            }
            if (ShuJu.isJiShu(oneStr) ^ ShuJu.isJiShu(wenHao.get(i))) {
                wenHaoOneIndex[i]++;
            }
            if (ShuJu.isZhiShu(oneStr) ^ ShuJu.isZhiShu(wenHao.get(i))) {
                wenHaoOneIndex[i]++;
            }
        }

        int[] wenHaoTwoIndex = new int[wenHao.size()];
        for (int i = 0; i < wenHao.size(); i++) {
            if (ShuJu.isDaShu(twoStr) ^ ShuJu.isDaShu(wenHao.get(i))) {
                wenHaoTwoIndex[i]++;
            }
            if (ShuJu.isJiShu(twoStr) ^ ShuJu.isJiShu(wenHao.get(i))) {
                wenHaoTwoIndex[i]++;
            }
            if (ShuJu.isZhiShu(twoStr) ^ ShuJu.isZhiShu(wenHao.get(i))) {
                wenHaoTwoIndex[i]++;
            }
        }

        //需要找到权重最大的进行选择一个号即可
        String[] oneGroup = new String[2];
        oneGroup[0] = oneStr;
        //获取最大差异程度的温号
        oneGroup[1] = wenHao.get(ArrayUtils.maxIndex(wenHaoOneIndex).get(0));
        //TODO 这边还缺少下一组搭档的数据的选择,比如全奇数 还是全质数 还是全偶数 还是全合数 等等

        String[] twoGroup = new String[2];
        twoGroup[0] =twoStr;
        //获取最大差异程度的温号
        twoGroup[1] = wenHao.get(ArrayUtils.maxIndex(wenHaoTwoIndex).get(0));
        //TODO 这边也需要下一组搭档的数据选择,比如全偶数,全合数的出现 思路:可以根据第10,11期的走向来抉择,也可以考虑按照目前最大(或最小)的概率的走;
        //获取当前的概率:
        Map<String, Double> threeGroupPercent = getThreeGroupPercent(date, period);
        double[] current = new double[3];
        current[0] = threeGroupPercent.get("大数概率");
        current[1] = threeGroupPercent.get("质数概率");
        current[2] = threeGroupPercent.get("奇数概率");
        Arrays.sort(current);
        //缺少了最大两个值相同的情况的判断
        String keyMax = ((List<String>)getKey(threeGroupPercent, current[2])).get(0);
        String keymid = ((List<String>)getKey(threeGroupPercent, current[1])).get(0);
        String[] dashu = {"06", "07", "08", "09", "10", "11"};
        String[] zhishu = {"01", "02", "03", "05", "07", "11"};
        String[] jishu =  {"01", "03", "05", "07", "09", "11"};
        List<String[]> groupList = new ArrayList<>();
        //此处不做修改,也不做简化,直接将对应的数据放到集合中即可
        switch (keyMax){
            case "大数概率": groupList.add(Convert.toStrArray(ArrayUtils.minus(dashu,oneGroup)));break;
            case "质数概率": groupList.add(Convert.toStrArray(ArrayUtils.minus(zhishu,oneGroup)));break;
            default: groupList.add(Convert.toStrArray(ArrayUtils.minus(jishu,oneGroup)));
        }
        switch (keymid){
            case "大数概率": groupList.add(Convert.toStrArray(ArrayUtils.minus(dashu,oneGroup)));break;
            case "质数概率": groupList.add(Convert.toStrArray(ArrayUtils.minus(zhishu,oneGroup)));break;
            default: groupList.add(Convert.toStrArray(ArrayUtils.minus(jishu,oneGroup)));
        }
        Map<String,List<String[]>> map = new HashMap<>();
        map.put(Arrays.toString(oneGroup),groupList);
        map.put(Arrays.toString(twoGroup),groupList);
        return map;
    }
}