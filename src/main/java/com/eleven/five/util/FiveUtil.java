package com.eleven.five.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.eleven.five.entity.UrlDateEnum;
import com.eleven.five.service.ElevenService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhaozhihong
 * 彩票 工具类 用于获取数据保存到数据库
 */
public class FiveUtil {

    private static String BASE_URL = UrlDateEnum.URL_ENUM.getMsg();
    private static String TODAY = UrlDateEnum.DATE_ENUM.getMsg();

    private static Logger logger = LoggerFactory.getLogger(ElevenService.class);

    //生成过去的10天的 年-月-日 存到集合中
    public static List<String> getLastTenDays() {
        DateTime date = DateUtil.date();
        if (!StringUtils.isEmpty(TODAY)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date today = null;
            try {
                today = simpleDateFormat.parse(TODAY);
            } catch (ParseException e) {
                logger.error("日期格式化异常,请检查配置文件中日期的格式 要求是 四位数年份-两位数月份-两位数几号");

            }
            date = new DateTime(today);
        }
        List<String> dateList = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            DateTime dateTime = DateUtil.offsetDay(date, -i - 1);
            String format = DateUtil.format(dateTime, "yyyyMMdd");
            dateList.add(format);
        }
        return dateList;
    }

    //获取前10天的彩票数据保存到集合并返回
    public static List<String> getCPNums() throws IOException {
        //实现将一天的彩票信息存到集合中
        List<String> dateList = getLastTenDays();
        // List<String> dateList =  "20180602";
        List<String> fiveStr = new ArrayList<>();
        for (String date : dateList) {
            String url = BASE_URL + date + ".html";
            Elements elements = Jsoup.connect(url).get().select("[data-period]");
            getOneGroupNumber(fiveStr, elements);
        }
        return fiveStr;
    }

    public static void getOneGroupNumber(List<String> fiveStr, Elements elements) {
        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i % 21 * 4 + i / 21);
            String target = element.attr("data-period");
            String target1 = element.attr("data-award");
            String result = target1 + " " + target;
            if(StringUtils.isEmpty(target1)){
                continue;
            }
            /*if (result.length() < 15) {
                break;
            }*/
            fiveStr.add(result);
        }
    }

}
