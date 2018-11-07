package io.httpdoc.sample.user;

import io.httpdoc.core.annotation.Order;

import java.util.Date;

/**
 * 用户模型
 *
 * @author 杨昌沛 646742615@qq.com
 * 2018/11/5
 * @order 1
 */
public class User {
    /**
     * 用户ID
     *
     * @order 1
     */
    private Long id;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户性别
     *
     * @order 3
     */
    private Gender gender;
    /**
     * 出生日期
     *
     * @alias date
     * @order 4
     */
    private Date birthday;
    /**
     * 年龄
     *
     * @skip
     */
    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Order(2)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
