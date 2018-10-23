package com.eleven.five;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.io.IOException;

/**
 * @author zhaozhihong
 */
public class DemoTest {
    public static void main(String[] args) throws IOException {
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
         /* Date date = new Date();
          System.out.println(date.getTime());*/

//         List<Integer> list = new ArrayList<>();
//         list.add(1);
//         list.add(2);
//         list.add(3);
//         list.add(4);
//        Object[] objects = list.toArray();
//        System.out.println(Arrays.toString(objects));
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String format = sdf.format(new Date());
//        System.out.println(format);

        /*DateTime parse = DateUtil.parse("2018-09-24");
        System.out.println(parse);*/

    /*    boolean b1=true;
        boolean b2 =false;
        System.out.println(b1^b2);*/
    /*double[] d = {0.1,0.2,0.5,0.3};
        Arrays.sort(d);
        System.out.println(Arrays.toString(d));*/

       /* Document doc = DocumentHelper.createDocument();
        Element head = DocumentHelper.createElement("Head");
        Element funcCode = head.addElement("funcCode");
        funcCode.setText("接口功能代码");
        Element transID = head.addElement("transID");
        transID.setText("消息流水号");
        Element userName = head.addElement("userName");
        userName.setText("身份信息核查系统分配的用户名");
        Element timeStamp = head.addElement("timeStamp");
        timeStamp.setText("时间戳");
        Element version = head.addElement("version");
        version.setText("版本号");
        Element authCode = head.addElement("authCode");
        authCode.setText("身份信息核查系统分配的认证码");
        Element expandPara = head.addElement("expandPara");
        expandPara.setText("保留字段");
        Element content = head.addElement("content");
        content.setText("加密后的消息体，即Base64 (DES(消息体))，具体请见4.2节");
        doc.setRootElement(head);
        doc.setXMLEncoding("GB2312");
        XMLWriter xmlWriter = new XMLWriter();
        xmlWriter.write(doc);*/

       /* Document doc = XmlUtil.createXml("Head");

        Element funcCode = doc.createElement("funcCode");
        funcCode.setTextContent("接口功能代码");
        Element transID = doc.createElement("transID");
        transID.setTextContent("消息流水号");
        Element userName = doc.createElement("userName");
        userName.setTextContent("身份信息核查系统分配的用户名");
        Element timeStamp = doc.createElement("timeStamp");
        timeStamp.setTextContent("时间戳");
        Element version = doc.createElement("version");
        version.setTextContent("版本号");
        Element authCode = doc.createElement("authCode");
        authCode.setTextContent("身份信息核查系统分配的认证码");
        Element expandPara = doc.createElement("expandPara");
        expandPara.setTextContent("保留字段");
        Element content = doc.createElement("content");
        content.setTextContent("加密后的消息体，即Base64 (DES(消息体))，具体请见4.2节");

        doc.setXmlStandalone(true);
        String s = XmlUtil.toStr(doc, "GB2312", true);
        System.out.println(s);*/


       /* String content = "test中文";
        DES des = SecureUtil.des();
        byte[] encrypt = des.encrypt(content);

        final Base64.Decoder decoder = Base64.getDecoder();
        final Base64.Encoder encoder = Base64.getEncoder();
        final byte[] textByte = new String(encrypt).getBytes("UTF-8");
//编码
        System.out.println(new String(encoder.encode(textByte)));
//解码*/
        String now = DateUtil.now();
        DateTime today = DateUtil.parse(now);
        DateTime dateTime = DateUtil.offsetDay(today, -50);
        System.out.println(dateTime.toString("yyyyMMdd"));



    }

}
