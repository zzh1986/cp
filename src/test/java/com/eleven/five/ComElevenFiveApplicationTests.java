package com.eleven.five;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.XmlUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ComElevenFiveApplicationTests {


    public static void main(String[] args) throws IOException {




    }























    private static void getCPNums() throws IOException {
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
    }

    private static List<String> getLastTenDays() {
        DateTime date = DateUtil.date();
        List<String> dateList = new ArrayList<>();
        for (int i = 9; i < 10; i++) {
            DateTime dateTime = DateUtil.offsetDay(date, -i - 1);
            String format = DateUtil.format(dateTime, "yyyyMMdd");
            dateList.add(format);
        }
        return dateList;
    }
}
