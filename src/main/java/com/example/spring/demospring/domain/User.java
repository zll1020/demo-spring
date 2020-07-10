package com.example.spring.demospring.domain;

import com.example.spring.demospring.enums.City;
import com.example.spring.demospring.ioc.annotation.Super;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Description: 用户类
 * User: zhangll
 * Date: 2020-07-03
 * Time: 11:26
 */
@Data
@Super
public class User {

    private Long id;

    private String name;

    private City city;

    /**
     * 当前 Bean 的名称
     */
    private transient String beanName;

    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Lillian");
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                '}';
    }

    @PostConstruct
    public void init() {
        System.out.println("User Bean [" + beanName + "] 初始化...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("User Bean [" + beanName + "] 销毁中...");
    }

}
