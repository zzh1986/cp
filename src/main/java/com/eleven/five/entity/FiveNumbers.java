package com.eleven.five.entity;

import javax.persistence.*;

/**
 * 该5个数目的就是为了为选前三个数字做基础,需要用大量的数据进行统计和比较,从而得出较为接近的结果
 * @author zhaozhihong
 */
@Entity
@Table(name = "five_numbers")
public class FiveNumbers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "one")
    private String one;

    @Column(name = "two")
    private String two;

    @Column(name = "three")
    private String three;

    @Column(name = "four")
    private String four;

    @Column(name = "five")
    private String five;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public String getFive() {
        return five;
    }

    public void setFive(String five) {
        this.five = five;
    }

}
