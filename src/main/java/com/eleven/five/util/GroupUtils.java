package com.eleven.five.util;

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
            // TODO Auto-generated method stub
            Object[] one = {3,4,5,7,9};
            Object[] two = {2,3,5,7,10};
            ArrayList<Object[]> oneCmn = cmn(one, 2);
            ArrayList<Object[]> twoCmn = cmn(two, 2);
            Object[] three = {1,4,7,10};
            Object[] four = {2,5,8,11};
            Object[] five = {3,6,9};
            Object[] six = {1,2,3,4};
            Object[] seven = {8,9,10,11};
            Object[] eight = {5,6,7};
            List<Object[]> result = new ArrayList<>();
            for (int i=0;i<oneCmn.size();i++){
                for (int j=0;j<twoCmn.size();j++){
                    Object[] union = ArrayUtils.union(oneCmn.get(i), twoCmn.get(j));
                    if(union.length==3&&ArrayUtils.intersect(union,three).length>=1
                            &&ArrayUtils.intersect(union,four).length>=1&&ArrayUtils.intersect(union,six).length>=1
                            &&ArrayUtils.intersect(union,seven).length>=1
                    ){
                        result.add(union);
                    }
                }
            }
            for (Object[] objects : result){
                System.out.println(Arrays.toString(objects));
            }
            System.out.println(result.size());
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
