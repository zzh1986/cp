package com.eleven.five;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.eleven.five.entity.TenTimes;
import com.eleven.five.entity.UrlDateEnum;
import com.eleven.five.service.TenTimesService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author zhaozhihong
 */
public class DemoTest {
    public static void main(String[] args) throws ParseException, IOException {
//        String instead = "___________";
//        System.out.println(instead.substring(0,0)+"****"+instead.substring(0+4));
//        String s="22222";
//        String substring = s.substring(0, 5);
//        System.out.println(substring);

//        String s="222222";
//        StringBuffer s1 = new StringBuffer(s);
//        //String replace = s.replace(s.substring(0, 2), "_{5}");
//        System.out.println(s1.replace(0,3,"_"));
//        String a = "我是 f_static_000 的 f_static_001 aaa f_static_001";
//
//        a = a.replaceAll("f_static_\\d{3}", "#[face/png/$0]#");
//
//        System.out.println(a);

//        for (int i=0;i<5;i++){
//            switch (i){
//                case 1:System.out.println(i+"本次测试的结果哦");break;
//            }
//            System.out.println(i);
//        }

//        System.out.println((1^0) == 1?0:1);
//        System.out.println((0^0)==1 ?0:1);
//        System.out.println((1^1)==1 ?0:1);
//        System.out.println((0^1)==1 ?0:1);

//        DateTime dateTime = new DateTime();
//        System.out.println(dateTime);
//        DateTime date = DateUtil.date();
//        System.out.println(date);
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = sdf.parse("2018-08-05");
//        DateTime dateTime1 = new DateTime(date);
//        System.out.println(dateTime1);
//        DateTime dateTime = DateUtil.offsetDay(date, -1);
//        System.out.println(dateTime);
        // System.out.println("1234566".substring(3));

        /*Integer[][] tesy ={{1,2,3},{4,6}};
        for (int i=0;i<tesy.length;i++){
            for(int j=0;j<tesy[i].length;j++){
                System.out.println(tesy[i][j]);
            }
        }*/

//        String url = UrlDateEnum.URL_ENUM.getMsg() + 20180802 + ".html";
//
//        Elements elements = Jsoup.connect(url).get().select("[data-period="+ 180802 + (12 + 1)+"]" );
//        String award = elements.get(0).attr("data-award");
//        System.out.println(award);
       /* String url = "http://gd11x5.icaile.com/";
        Elements elements = Jsoup.connect(url).get().select(".chart-bg-qh");
        if(!elements.isEmpty()){
            for (Element element : elements){
                String period = element.text();
                Elements elements1 = element.siblingElements();
                String one = elements1.select(".dqhm").get(0).text();
                String two = elements1.select(".dqhm").get(1).text();
                String three = elements1.select(".dqhm").get(2).text();
                String four = elements1.select(".dqhm").get(3).text();
                String five = elements1.select(".chart-bg-kjhmo").get(0).text();
                System.out.println(one +two +three +four +five);
            }
        }
*/
        /*TenTimesService tenTimesService = new TenTimesService();
        List<TenTimes> tenTimesSencond = tenTimesService.getTenTimesSencond("20180809", "05");
        System.out.println(tenTimesSencond);*/

//        Locale locale = new Locale("zh","CN");
//        NumberFormat numberInstance = NumberFormat.getNumberInstance(locale);
//        NumberFormat numberInstance = NumberFormat.getPercentInstance();
//        double value = 0.97123123;
//        System.out.println(numberInstance.format(value));
          Date date = new Date();
          System.out.println(date.getTime());

    }

}
