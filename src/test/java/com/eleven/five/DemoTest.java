package com.eleven.five;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhaozhihong
 */
public class DemoTest {
    public static void main(String[] args) throws ParseException {
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2018-08-05");
        DateTime dateTime1 = new DateTime(date);
        System.out.println(dateTime1);
        DateTime dateTime = DateUtil.offsetDay(date, -1);
        System.out.println(dateTime);
    }

}
