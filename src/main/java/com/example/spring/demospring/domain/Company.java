package com.example.spring.demospring.domain;

/**
 * Description: 公司
 * User: zhangll
 * Date: 2020-07-06
 * Time: 14:31
 */
public class Company {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }

}
