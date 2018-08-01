package com.eleven.five.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaozhihong
 * 彩票 工具类 用于获取数据保存到数据库
 *
 */
public class FiveUtil {

    //生成过去的10天的 年-月-日 存到集合中
    public static List<String> getLastTenDays() {
        DateTime date = DateUtil.date();
        List<String> dateList= new ArrayList<>(10);
        for(int i=0;i<10;i++){
            DateTime dateTime = DateUtil.offsetDay(date, -i-1);
            String format = DateUtil.format(dateTime, "yyyyMMdd");
            dateList.add(format);
        }
        return dateList;
    }
    //获取前10天的彩票数据保存到集合并返回
    public static List<String> getCPNums() throws IOException {
        //实现将一天的彩票信息存到集合中
        List<String> dateList =  getLastTenDays();
        // List<String> dateList =  "20180602";
        List<String> fiveStr=new ArrayList<>();
        for(String date : dateList){
            String url = "http://caipiao.163.com/award/gd11xuan5/"+date+".html";
            Elements elements = Jsoup.connect(url).get().select("[data-period]");
            for (int i=0;i<elements.size();i++) {
                Element element = elements.get(i % 21 * 4 + i / 21);
                String target = element.attr("data-period");
                String target1 = element.attr("data-award");
                String result = target1 + " " + target;
                if(result.length()<15){
                    break;
                }
                fiveStr.add(result);
            }
        }
        return fiveStr;
    }

}
