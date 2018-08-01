package com.eleven.five.service;

import com.eleven.five.entity.ElevenNumber;
import com.eleven.five.entity.TenTongJi;
import com.eleven.five.mapper.ElevenNumberMapper;
import com.eleven.five.mapper.TenTongJiMapper;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhaozhihong
 */
@Service
public class MaxSimularService {

    @Autowired
    private TenTongJiMapper tenTongJiMapper;

    @Autowired
    private ElevenNumberMapper elevenNumberMapper;

    public String findMaxSimularNextPeriod() {

        List<TenTongJi> tenTongJiList = tenTongJiMapper.findAll();
        if (tenTongJiList.size() != 1) {
            return "近10次统计异常";
        }
        //查询出目标对象
        TenTongJi tenTongJi = tenTongJiList.get(0);
        //查询出elevenNumber进行对应的比较
        List<ElevenNumber> elevenNumberList = elevenNumberMapper.findAll();
        if (elevenNumberList.size() == 0) {
            return "10天的数据还未查出,请先查出来!!";
        }
        int[] count = new int[elevenNumberList.size()];
        for (int i = 0; i < elevenNumberList.size(); i++) {
            for (int j = 0; j < 11; j++) {
                if (elevenNumberList.get(i).getOneNum().equals(tenTongJi.getOne())) {
                    elevenNumberList.get(i).setOneNum(-1);
                    count[i]++;
                    continue;
                }
                if (elevenNumberList.get(i).getTwoNum().equals(tenTongJi.getTwo())) {
                    elevenNumberList.get(i).setTwoNum(-1);
                    count[i]++;
                    continue;
                }
                if (elevenNumberList.get(i).getThreeNum().equals(tenTongJi.getThree())) {
                    elevenNumberList.get(i).setThreeNum(-1);
                    count[i]++;
                    continue;
                }
                if (elevenNumberList.get(i).getFourNum().equals(tenTongJi.getFour())) {
                    elevenNumberList.get(i).setFourNum(-1);
                    count[i]++;
                    continue;
                }
                if (elevenNumberList.get(i).getFiveNum().equals(tenTongJi.getFive())) {
                    elevenNumberList.get(i).setFiveNum(-1);
                    count[i]++;
                    continue;
                }
                if (elevenNumberList.get(i).getSixNum().equals(tenTongJi.getSix())) {
                    elevenNumberList.get(i).setSixNum(-1);
                    count[i]++;
                    continue;
                }
                if (elevenNumberList.get(i).getSevenNum().equals(tenTongJi.getSeven())) {
                    elevenNumberList.get(i).setSevenNum(-1);
                    count[i]++;
                    continue;
                }
                if (elevenNumberList.get(i).getEightNum().equals(tenTongJi.getEight())) {
                    elevenNumberList.get(i).setEightNum(-1);
                    count[i]++;
                    continue;
                }
                if (elevenNumberList.get(i).getNineNum().equals(tenTongJi.getNine())) {
                    elevenNumberList.get(i).setNineNum(-1);
                    count[i]++;
                    continue;
                }
                if (elevenNumberList.get(i).getTenNum().equals(tenTongJi.getTen())) {
                    elevenNumberList.get(i).setTenNum(-1);
                    count[i]++;
                    continue;
                }
                if (elevenNumberList.get(i).getElevenNum().equals(tenTongJi.getEleven())) {
                    elevenNumberList.get(i).setElevenNum(-1);
                    count[i]++;
                    continue;
                }
            }
        }
        int[] result = count.clone();
        Arrays.sort(count);
        int max = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i] == count[count.length - 1]) {
                max = i;
                break;
            }
        }
        //TODO 20180801继续;
//        for (int i = 0; i < max.length; i++) {
//            System.out.println(i);
//        }
        ElevenNumber elevenNumber = elevenNumberList.get(max);
        //eleven 用来记录 相似的一组数据,需要通过下一组来实现数据的相应结果
        int[] eleven = new int[11];
        eleven[0] = elevenNumber.getOneNum() == -1 ? 1 : 0;
        eleven[1] = elevenNumber.getTwoNum() == -1 ? 1 : 0;
        eleven[2] = elevenNumber.getThreeNum() == -1 ? 1 : 0;
        eleven[3] = elevenNumber.getFourNum() == -1 ? 1 : 0;
        eleven[4] = elevenNumber.getFiveNum() == -1 ? 1 : 0;
        eleven[5] = elevenNumber.getSixNum() == -1 ? 1 : 0;
        eleven[6] = elevenNumber.getSevenNum() == -1 ? 1 : 0;
        eleven[7] = elevenNumber.getEightNum() == -1 ? 1 : 0;
        eleven[8] = elevenNumber.getNineNum() == -1 ? 1 : 0;
        eleven[9] = elevenNumber.getTenNum() == -1 ? 1 : 0;
        eleven[10] = elevenNumber.getElevenNum() == -1 ? 1 : 0;
        if (max < elevenNumberList.size()) {
            ElevenNumber elevenNumberNext = elevenNumberList.get(max + 1);
            System.out.println(elevenNumberNext.getPeriod());
        }
        return Arrays.toString(eleven);

    }

    //TODO 方案二:需要模糊查询 可以现在service 做起来
    //两层for循环 可以实现,但是我不想用!!!
    public String getFourNumberSimular() {
        //1.查询出目标sort
        List<TenTongJi> tenTongJiList = tenTongJiMapper.findAll();
        if (tenTongJiList.size() != 1) {
            return "统计出现异常,需要先进行统计才可以";
        }
        TenTongJi tenTongJi = tenTongJiList.get(0);
        String str = "";
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0:
                    str =             tenTongJi.getSort().substring(0+i, 3+i) + "_______";
                    break;
                case 1:
                    str = "_"       + tenTongJi.getSort().substring(0+i, 3+i) + "______";
                    break;
                case 2:
                    str = "__"      + tenTongJi.getSort().substring(0+i, 3+i) + "_____";
                    break;
                case 3:
                    str = "___"     + tenTongJi.getSort().substring(0+i, 3+i) + "____";
                    break;
                case 4:
                    str = "____"    + tenTongJi.getSort().substring(0+i, 3+i) + "___";
                    break;
                case 5:
                    str = "_____"   + tenTongJi.getSort().substring(0+i, 3+i) + "__";
                    break;
                case 6:
                    str = "______"  + tenTongJi.getSort().substring(0+i, 3+i) + "_";
                    break;
                default:
                    str = "_______" + tenTongJi.getSort().substring(0+i, 3+i);
            }
            String period = elevenNumberMapper.findPeriodLikeFourSort(str);
            if(!StringUtils.isEmpty(period)){
                String result = String.valueOf(Integer.valueOf(period) + 1);
                System.out.println(result);
                return result;
            }
        }
        return "不好意思 没匹配到哦";
    }


}
