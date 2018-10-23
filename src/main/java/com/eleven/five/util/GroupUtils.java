package com.eleven.five.util;

import cn.hutool.core.convert.Convert;
import com.eleven.five.entity.GroupEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-09-03
 */
public class GroupUtils {


        /**
         * @param args
         */
        public static void main(String[] args) {
          String[] test = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11"};
            List<Object[]> allArrayfromCount = getAllArrayfromCount(test, 4);
            for (int i = 0; i <allArrayfromCount.size() ; i++) {
                System.out.println(Arrays.toString(allArrayfromCount.get(i)));
            }
            System.out.println(allArrayfromCount.size());
        }

    /**
     * 获取一个数组11个数的全排列组合
     * @param numbers
     * @return
     */
   public static List<GroupEntity> getAllC(Object[] numbers,int one_group,int two_group){
        List<GroupEntity> result = new ArrayList<>();
        ArrayList<Object[]> cmn = cmn(numbers, one_group);
        for (int i=0;i<cmn.size();i++){
            GroupEntity groupEntity = new GroupEntity();
            groupEntity.setOne(cmn.get(i));
            Object[] minus = ArrayUtils.minus(numbers, cmn.get(i));
            ArrayList<Object[]> cmn1 = cmn(minus, two_group);
            groupEntity.setTwo(cmn1);
            List<Object[]> three = new ArrayList<>();
            for(int j = 0;j<cmn1.size();j++){
                three.add(ArrayUtils.minus(numbers, ArrayUtils.union(cmn.get(i), cmn1.get(j))));
            }
            groupEntity.setThree(three);
            result.add(groupEntity);
        }
        return result;
    }
    /**
     * 获取一个数组11个数的全排列组合
     * @param numbers
     * @return
     */
    public static List<Object[]> getAllArrayfromCount(Object[] numbers,int one_group){
        ArrayList<Object[]> cmn = cmn(numbers, one_group);
        List<Object[]> result = new ArrayList<>();
        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < one_group; i++) {
            indexList.add(i);
        }
        List<Integer[]> arrys = new ArrayList<>();
        permutation(arrys, Convert.toIntArray(indexList.toArray()),0);
        //现在arrys 里面保存的就是下标的全排列
        for (int i = 0; i < cmn.size() ; i++) {
            for (int j = 0; j < arrys.size(); j++) {
                Object[] tmp = new Object[one_group];
                for (int k = 0; k < arrys.get(j).length; k++) {
                    tmp[k]=cmn.get(i)[arrys.get(j)[k]];
                }
                result.add(tmp);

            }
        }
        return result;
    }
    //该方法用于将ss所有组合保存的result中
    private static void permutation(List<Integer[]> result, Integer[] ss, int i){

        if(ss==null||i<0 ||i>ss.length){//1
            return;
        }

        if(i==ss.length-1){//2
            Integer[] clone = ss.clone();
            result.add(clone);
        }else{
            for(int j=i;j<ss.length;j++){//3
                Integer temp=ss[j];//交换前缀,使之产生下一个前缀
                ss[j]=ss[i];
                ss[i]=temp;
                permutation(result,ss,i+1);//4
                temp=ss[j]; //将前缀换回来,继续做上一个的前缀排列.//5
                ss[j]=ss[i];
                ss[i]=temp;
            }
        }
    }
    /**
     * 求一个数组的任意组合
     */
       public static ArrayList<Object[]> RandomC(Object[] source)
        {
            ArrayList<Object[]> result=new ArrayList<>();
            if(source.length==1)
            {
                result.add(source);
            }
            else
            {
                Object[] psource=new Object[source.length-1];
                for(int i=0;i<psource.length;i++)
                {
                    psource[i]=source[i];
                }
                result=RandomC(psource);
                //fn组合的长度
                int len=result.size();
                result.add((new Object[]{source[source.length-1]}));
                for(int i=0;i<len;i++)
                {
                    Object[] tmp=new Object[result.get(i).length+1];
                    for(int j=0;j<tmp.length-1;j++)
                    {
                        tmp[j]=result.get(i)[j];
                    }
                    tmp[tmp.length-1]=source[source.length-1];
                    result.add(tmp);
                }

            }
            return result;
        }

    /**
     * 求任意数组的n 的组合
     * @param source
     * @param n
     * @return
     */
       public static ArrayList<Object[]> cmn(Object[] source, int n)
        {
            ArrayList<Object[]> result=new ArrayList<Object[]>();
            if(n==1)
            {
                for(int i=0;i<source.length;i++)
                {
                    result.add(new Object[]{source[i]});

                }
            }
            else if(source.length==n)
            {
                result.add(source);
            }
            else
            {
                Object[] psource=new Object[source.length-1];
                for(int i=0;i<psource.length;i++)
                {
                    psource[i]=source[i];
                }
                result=cmn(psource,n);
                ArrayList<Object[]> tmp=cmn(psource,n-1);
                for(int i=0;i<tmp.size();i++)
                {
                    Object[] rs=new Object[n];
                    for(int j=0;j<n-1;j++)
                    {
                        rs[j]=tmp.get(i)[j];
                    }
                    rs[n-1]=source[source.length-1];
                    result.add(rs);
                }
            }
            return result;
        }

    }
