package com.eleven.five.service;

import cn.hutool.core.convert.Convert;
import com.eleven.five.entity.GroupEntity;
import com.eleven.five.entity.ThreeResult;
import com.eleven.five.entity.UrlDateEnum;
import com.eleven.five.mapper.ThreeResultMapper;
import com.eleven.five.util.ArrayUtils;
import com.eleven.five.util.FiveUtil;
import com.eleven.five.util.GroupUtils;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @author zhaozhihong
 */
@Service
public class GroupService {


    @Autowired
    private ThreeResultMapper threeResultMapper;

    public List<Object[]> getGroupResult(String date, String period) throws IOException {
        List<Object[]> oneOrTwoGroup = getOneOrTwoGroup(date, period, 6, 3);
        List<Object[]> oneOrTwoGroup1 = getOneOrTwoGroup(date, period, 5, 3);
        List<Object[]> oneOrTwoGroup2 = getOneOrTwoGroup(date, period, 4, 2);
        //List<Object[]> oneOrTwoGroup3 = getOneOrTwoGroup(date, period, 9, 4);
        //List<Object[]> oneOrTwoGroup4 = getOneOrTwoGroup(date, period, 2, 1);
        List<Object[]> result = new ArrayList<>();
        Object[] standard={"01","02","03","04","05","06","07","08","09","10","11"};
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
        if(oneOrTwoGroup!=null&&oneOrTwoGroup.size()==1
                &&oneOrTwoGroup1!=null&&oneOrTwoGroup1.size()==1
                &&oneOrTwoGroup2!=null&&oneOrTwoGroup2.size()==1){
            return oneOrTwoGroup2;
        }
        if(oneOrTwoGroup1!=null&&oneOrTwoGroup1.size()==1
                &&oneOrTwoGroup2!=null&&oneOrTwoGroup2.size()==1){
            Object[] minus = ArrayUtils.minus(standard, ArrayUtils.union(oneOrTwoGroup1.get(0), oneOrTwoGroup2.get(0)));
            result.add(minus);
            return result;
        }
        if (oneOrTwoGroup1!=null&&oneOrTwoGroup1.size()==2){
            Object[] minus = ArrayUtils.minus(ArrayUtils.union(oneOrTwoGroup1.get(0), oneOrTwoGroup1.get(1)), ArrayUtils.intersect(oneOrTwoGroup1.get(0), oneOrTwoGroup1.get(1)));
            if(minus.length<4){
                result.add(minus);
            }else {
                minus = ArrayUtils.minus(standard, ArrayUtils.union(oneOrTwoGroup1.get(0), oneOrTwoGroup1.get(1)));
                if(minus.length<4){
                    result.add(minus);
                }
            }
                return result;
        }
        if(oneOrTwoGroup!=null&&oneOrTwoGroup.size()==1
                &&oneOrTwoGroup2!=null&&oneOrTwoGroup2.size()==1){
            if(ArrayUtils.union(oneOrTwoGroup.get(0),oneOrTwoGroup2.get(0)).length==oneOrTwoGroup.get(0).length){
                Object[] minus = ArrayUtils.minus(oneOrTwoGroup.get(0), oneOrTwoGroup2.get(0));
                result.add(minus);
                return result;
            }
            Object[] minus = ArrayUtils.minus(standard, ArrayUtils.union(oneOrTwoGroup.get(0), oneOrTwoGroup2.get(0)));
            if(minus.length!=1 && minus.length<5){
                result.add(minus);
                return result;
            }
            return oneOrTwoGroup2;
        }
        if(oneOrTwoGroup1!=null&&oneOrTwoGroup1.size()==2){
            Object[] minus = ArrayUtils.minus(ArrayUtils.union(oneOrTwoGroup1.get(0), oneOrTwoGroup1.get(1)), ArrayUtils.intersect(oneOrTwoGroup1.get(0), oneOrTwoGroup1.get(1)));
            result.add(minus);
            return result;
        }
        if(oneOrTwoGroup2!=null&&oneOrTwoGroup2.size()==2){
            Object[] minus = ArrayUtils.minus(standard,ArrayUtils.minus(ArrayUtils.union(oneOrTwoGroup2.get(0), oneOrTwoGroup2.get(1)), ArrayUtils.intersect(oneOrTwoGroup2.get(0), oneOrTwoGroup2.get(1))));
            if (minus.length<4){
                result.add(minus);
                return result;
            }

        }
//        if(oneOrTwoGroup!=null&&oneOrTwoGroup.size()==1){
//            Object[] minus = ArrayUtils.minus(standard, oneOrTwoGroup.get(0));
//            result.add(minus);
//            return result;
//        }
        if(oneOrTwoGroup!=null&&oneOrTwoGroup.size()==2){
            Object[] minus = ArrayUtils.minus(ArrayUtils.union(oneOrTwoGroup.get(0), oneOrTwoGroup.get(1)), ArrayUtils.intersect(oneOrTwoGroup.get(0), oneOrTwoGroup.get(1)));
            if(minus.length<4){
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
     * @param date
     * @param period
     * @return
     * @throws IOException
     */
    private List<String[]> getTenTimes(String date, String period,int num) throws IOException {
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
        t1:
        for (int i = perNum; i > perNum - num; i--) {
            // 期号 18062042
            String iFormat = String.valueOf(i).length() < 2 ? "0" + i : String.valueOf(i);
            String dp = date.substring(2, date.length()) + iFormat;
            for (String s : fiveList) {
                if (s.contains(dp)) {
                    String[] number = s.substring(0,15).split("[\\s]+");
                    stringList.add(number);
                    continue t1;
                }
            }
        }
        return stringList;
    }

    /**
     * 写一个方法,用于获取相应的参数,其中的参数有
     * @param  date string
     * @param   period String
     * @param  n  int 每组几个数字
     * @param  m  int 相同数字个数
     * @return list</> 一个集合 一个或两个数组
     */
    private List<Object[]> getOneOrTwoGroup(String date,String period,int n,int m) throws IOException {
        for(int z=10;z>0;z--){
            //先准备目标数据 n(10)组
            List<String[]> tenTimes = getTenTimes(date, period,z);
            //准备好所有分类 allC  8组 出5个试试
            Object[] standard={"01","02","03","04","05","06","07","08","09","10","11"};
            List<GroupEntity> allC = GroupUtils.getAllC(standard, n, 2);
            List<Object[]> objectList = new ArrayList<>();
            t1:
            for(int i=0;i<allC.size();i++){
                for (int j=0;j<tenTimes.size();j++){
                    if(ArrayUtils.intersect(allC.get(i).getOne(),tenTimes.get(j)).length!=m){
                        continue t1;
                    }
                }
                //到这里说明全部都包括了
                Object[] one = allC.get(i).getOne();
                objectList.add(one);
            }
            if(objectList.size()==2){
                return objectList;
            }else if(objectList.size()==1){
                return objectList;
            }
        }
        return null;
    }


    public Map<String,Object> getFiveNumbers(String date, String period) throws IOException {

        List<String[]> tenTimeList = getTenTimes(date, period,10);
        Integer[] count = {0,0,0,0,0,0,0,0,0,0,0};
        Integer[] beforeFive = {0,0,0,0,0,0,0,0,0,0,0};
        for (int i = 0; i < tenTimeList.size(); i++) {
            if(i<5){
                for (int j = 0; j <tenTimeList.get(i).length ; j++) {
                    int index = Integer.valueOf(tenTimeList.get(i)[j]) - 1;
                    count[index]++;
                }
            }else{
                for (int j = 0; j <tenTimeList.get(i).length ; j++) {
                    int index = Integer.valueOf(tenTimeList.get(i)[j]) - 1;
                    beforeFive[index]++;
                }
            }
        }
        Integer[] total = new Integer[11];
        for (int i = 0; i <total.length ; i++) {
            total[i]=count[i]+beforeFive[i];
        }
        Integer[] three = {0,0,0,0,0,0,0,0,0,0,0};
        for (int j = 0; j < 5 ; j++) {
            three[Integer.valueOf(tenTimeList.get(0)[j]) - 1]++;
            three[Integer.valueOf(tenTimeList.get(8)[j]) - 1]++;
            three[Integer.valueOf(tenTimeList.get(9)[j]) - 1]++;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("period",date+0+period);
        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < count.length; i++) {
            if(count[i]==3){
                numbers.add(String.valueOf(i+1).length()==1?("0"+(i+1)):(""+(i+1)));
            }
        }
        map.put("numbers",numbers);
        Integer[] ou = new Integer[11];
        for (int i = 0; i < ou.length; i++) {
            ou[i] = count[i]+beforeFive[i]+total[i]+three[i];
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < ou.length; i++) {
            if(ou[i]%2==0){
                result.add(i+1);
            }
        }

        //其中three 数组就保存的我要的东西
        Integer[][] threeGroup= new Integer[4][];
        List<Integer> tempInt0 = new ArrayList<>();
        List<Integer> tempInt1 = new ArrayList<>();
        List<Integer> tempInt2 = new ArrayList<>();
        List<Integer> tempInt3 = new ArrayList<>();
        for (int i = 0; i <three.length ; i++) {
            switch (three[i]){
                case 0: tempInt0.add(i+1);break;
                case 1: tempInt1.add(i+1);break;
                case 2: tempInt2.add(i+1);break;
                    default: tempInt3.add(i+1);
            }
        }
         threeGroup[0] = Convert.toIntArray(tempInt0.toArray());
         threeGroup[1] = Convert.toIntArray(tempInt1.toArray());
         threeGroup[2] = Convert.toIntArray(tempInt2.toArray());
         threeGroup[3] = Convert.toIntArray(tempInt3.toArray());
       /* //处理一下数组
        for (int i = 0; i < threeGroup.length ; i++) {
            for (int j = 0; j <threeGroup[i].length ; j++) {
                threeGroup[i][j]=threeGroup[i][j]==0?(-1*i-1):threeGroup[i][j];
            }
        }*/
        //获取下一组中奖号码
        String url = UrlDateEnum.URL_ENUM.getMsg() + date + ".html";
        Elements elements = Jsoup.connect(url).get().select("[data-period=" + date.substring(2) + (Integer.valueOf(period)+ 1) + "]");
        String award = elements.get(0).attr("data-award");
        Integer[] awardInt = Convert.toIntArray(award.split("[\\s]+"));


        ThreeResult threeResult = new ThreeResult();
        threeResult.setId(null);
        threeResult.setZeroOne(ArrayUtils.union(threeGroup[0],threeGroup[1]).length);
        threeResult.setOneTotal(ArrayUtils.intersect(ArrayUtils.union(threeGroup[0],threeGroup[1]),awardInt).length);
        threeResult.setZeroTwo(ArrayUtils.union(threeGroup[0],threeGroup[2]).length);
        threeResult.setTwoTotal(ArrayUtils.intersect(ArrayUtils.union(threeGroup[0],threeGroup[2]),awardInt).length);
        threeResult.setZeroThree(ArrayUtils.union(threeGroup[0],threeGroup[3]).length);
        threeResult.setThreeTotal(ArrayUtils.intersect(ArrayUtils.union(threeGroup[0],threeGroup[3]),awardInt).length);
        threeResult.setOneTwo(ArrayUtils.union(threeGroup[1],threeGroup[2]).length);
        threeResult.setFourTotal(ArrayUtils.intersect(ArrayUtils.union(threeGroup[1],threeGroup[2]),awardInt).length);
        threeResult.setOneThree(ArrayUtils.union(threeGroup[1],threeGroup[3]).length);
        threeResult.setFiveTotal(ArrayUtils.intersect(ArrayUtils.union(threeGroup[1],threeGroup[3]),awardInt).length);
        threeResult.setTwoThree(ArrayUtils.union(threeGroup[2],threeGroup[3]).length);
        threeResult.setSixTotal(ArrayUtils.intersect(ArrayUtils.union(threeGroup[2],threeGroup[3]),awardInt).length);
        threeResult.setPeriod(period);
        threeResultMapper.save(threeResult);

        System.out.println(Arrays.toString(count));
        System.out.println(Arrays.toString(beforeFive));
        System.out.println(Arrays.toString(total));
        System.out.println(Arrays.toString(three));
        System.out.println(result);
        System.out.println("================================");
        return map;
    }

    public String getOneDayResult(String date) throws IOException {
        threeResultMapper.deleteAll();
        for (int i = 10; i < 84; i++) {
             getFiveNumbers(date, String.valueOf(i));
        }
        return "统计结束,请到数据库查看";
    }
}
