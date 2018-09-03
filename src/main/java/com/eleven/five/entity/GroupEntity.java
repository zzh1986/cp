package com.eleven.five.entity;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhaozhihong <zhao.zhihong@chinaott.net>
 * @date 2018-09-03
 */
public class GroupEntity {
    private Object[] one;
    private List<Object[]> two;
    private List<Object[]> three;

    @Override
    public String toString() {
        return "GroupEntity{" + "one=" + Arrays.toString(one) + ", two=" + two + ", three=" + three + '}';
    }

    public Object[] getOne() {
        return one;
    }

    public void setOne(Object[] one) {
        this.one = one;
    }

    public List<Object[]> getTwo() {
        return two;
    }

    public void setTwo(List<Object[]> two) {
        this.two = two;
    }

    public List<Object[]> getThree() {
        return three;
    }

    public void setThree(List<Object[]> three) {
        this.three = three;
    }
}
