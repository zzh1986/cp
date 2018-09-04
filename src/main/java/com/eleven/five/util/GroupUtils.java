package com.eleven.five.util;

import com.eleven.five.entity.GroupEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-09-03
 */
public class GroupUtils {


        /**
         * @param args
         */
       /* public static void main(String[] args) {
            // TODO Auto-generated method stub
            Object[] tmp={"01","02","03","04","05","06","07","08","09","10","11"};
//        ArrayList<Object[]> rs=RandomC(tmp);
//            ArrayList<Object[]> rs=cmn(tmp,5);
            List<GroupEntity> groupEntityList = getAllC(tmp,8,3);
//            System.out.println(rs.size());
            for (int i = 0;i<groupEntityList.size();i++){
                System.out.println(groupEntityList.get(i));
            }
        }*/

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
