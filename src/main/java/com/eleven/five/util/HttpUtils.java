package com.eleven.five.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.eleven.five.entity.ElevenRequest;
import com.eleven.five.entity.Myself;
import com.eleven.five.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaozhihong
 * 需要自己封装两个方法
 */
public class HttpUtils {

    static String loginUrl = "http://m.zh08823.com/tools/_ajax/login";
    static String loginName = "zzh1986";
    static String pwd = "z13653603146";

    static String payUrl = "http://m.zh08823.com/tools/_ajax/GD11X5/betSingle";


    public static String loginPost() {
        HttpRequest httpRequest = HttpUtil.createPost(loginUrl);
        User user = new User();
        user.setIsdefaultLogin(true);
        user.setLoginName(loginName);
        user.setLoginPwd(SecureUtil.md5(pwd));
        user.setValidCode("");
        user.setValidateDate(System.currentTimeMillis());
        String body = JSONUtil.toJsonStr(user);
        httpRequest.body(body);
        HttpResponse httpResponse = httpRequest.execute();
        String JSESSIONID = httpResponse.getCookie("JSESSIONID").getValue();
        String sto = httpResponse.getCookie("sto-id-20480").getValue();
        String cookie = "sto-id-20480=" + sto + "; JSESSIONID=" + JSESSIONID;

        return cookie;
    }

    /**
     *
     * @param period 20180812060
     * @param nums 这个结果是02 01&05 这种 注意格式化
     * @return
     */
    public static int pay(String period, List<String> nums) {
        HttpRequest httpRequest = HttpUtil.createPost(payUrl);
        String cookie = loginPost();
        httpRequest.cookie(cookie);
        ElevenRequest elevenRequest = new ElevenRequest();
        elevenRequest.setAccountId(161355);
        elevenRequest.setClientTime(System.currentTimeMillis());
        elevenRequest.setGameId("GD11X5");
        //TODO 期号 这个数字建议传入20180812060
        elevenRequest.setIssue(period);
        List<String> myselfList = new ArrayList<>();
        Myself myself = new Myself();
        //应该是选择1个号的
        myself.setMethodid("XX5011001001");
        //实际选择几个数字,跟codes 有关系
        myself.setNums(1);
        //double :钱数 0.00
        myself.setRebate("0.00");
        //TODO 选择的次数 倍数;
        myself.setTimes(1);
        //钱数 2代表2元,也跟codes 和 times 有关系
        myself.setMoney(2);
        //TODO 模式 暂定1,不太确定具体是干啥用的
        myself.setMode(1);
        //期号 :同 上面的Issue 20180812060
        myself.setIssueNo(period);
        //实际要买的数字,建议传入
        String codes = "";
        if (nums != null && nums.size() == 1) {
            codes = nums.get(0);
        }
        if (nums != null && nums.size() == 2) {
            codes = nums.get(0) + "&" + nums.get(1);
        }
        myself.setCodes(codes);
        myself.setPlayId(new ArrayList<>());
        myselfList.add(JSONUtil.toJsonStr(myself));
        elevenRequest.setItem(myselfList);
        httpRequest.body(JSONUtil.toJsonStr(elevenRequest));

        HttpResponse httpResponse = httpRequest.execute();
        if (httpResponse.getStatus() == 200) {
            return 200;
        } else {
            return 400;
        }
    }

    /*public static void main(String[] args) {
        ElevenRequest elevenRequest = new ElevenRequest();
        elevenRequest.setAccountId(161355);
        elevenRequest.setClientTime(System.currentTimeMillis());
        elevenRequest.setGameId("GD11X5");
        //TODO 期号 这个数字建议传入20180812060
        elevenRequest.setIssue("20180812060");
        List<String> myselfList = new ArrayList<>();
        Myself myself = new Myself();
        //应该是选择1个号的
        myself.setMethodid("XX5011001001");
        //实际选择几个数字,跟codes 有关系
        myself.setNums(1);
        //double :钱数 0.00
        myself.setRebate("0.00");
        //TODO 选择的次数 倍数;
        myself.setTimes(1);
        //钱数 2代表2元,也跟codes 和 times 有关系
        myself.setMoney(2);
        //TODO 模式 暂定1,不太确定具体是干啥用的
        myself.setMode(1);
        //期号 :同 上面的Issue 20180812060
        myself.setIssueNo("20180812060");
        //实际要买的数字,建议传入
        String codes = "01&02";
        myself.setCodes(codes);
        myself.setPlayId(new ArrayList<>());
        myselfList.add(JSONUtil.toJsonStr(myself));
        elevenRequest.setItem(myselfList);
        System.out.println(JSONUtil.toJsonStr(elevenRequest));
    }*/


}
