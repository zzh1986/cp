package com.eleven.five.util;

//求排列数组合数
public class ZuHe {
    //求排列数
    public static int A(int up, int bellow) {
        int result = 1;
        for (int i = up; i > 0; i--) {
            result *= bellow;
            bellow--;
        }
        return result;
    }

    //求组合数，这个也不需要了。定义式，不使用互补率
    public static int C2(int up, int below) {
//			int denominator=factorial(up);//分母up的阶乘
        //分母
        int denominator = A(up, up);//A(6,6)就是求6*5*4*3*2*1,也就是求6的阶乘
        //分子
        int numerator = A(up, below);//分子的排列数
        return numerator / denominator;

    }

    public static int C(int up, int below)//应用组合数的互补率简化计算量
    {
        int helf = below / 2;
        if (up > helf) {
            System.out.print(up + "---->");
            up = below - up;
            System.out.print(up + "\n");

        }
        int denominator = A(up, up);//A(6,6)就是求6*5*4*3*2*1,也就是求6的阶乘
        //分子
        int numerator = A(up, below);//分子的排列数
        return numerator / denominator;

    }

    public static void main(String[] args) {
        int i = 4;
        int t = 8;
        System.out.println("C(" + i + "," + t + ")=" + C(i, t));

    }
}
