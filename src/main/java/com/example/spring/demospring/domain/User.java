package com.example.spring.demospring.domain;

import com.example.spring.demospring.enums.City;
import com.example.spring.demospring.ioc.annotation.Super;
import lombok.Data;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

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

    private City[] workCities;

    private List<City> lifeCities;

    private Resource configFileLocation;

    private Company company;

    private Properties context;

    private String contextAsText;

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
                ", workCities=" + Arrays.toString(workCities) +
                ", lifeCities=" + lifeCities +
                ", configFileLocation=" + configFileLocation +
                ", company=" + company +
                ", context=" + context +
                ", contextAsText='" + contextAsText + '\'' +
                ", beanName='" + beanName + '\'' +
                ", beanName='" + beanName + '\'' +
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
