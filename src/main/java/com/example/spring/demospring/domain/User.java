package com.example.spring.demospring.domain;

import com.example.spring.demospring.enums.City;
import com.example.spring.demospring.ioc.annotation.Super;
import lombok.Data;

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

    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("小马哥");
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
}
