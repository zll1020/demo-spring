package com.example.spring.demospring.dependency.injection.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

/**
 * Description: 自定义注解 （元标注 @Autowired）  快速实现
 * User: zhangll
 * Date: 2020-07-08
 * Time: 09:55
 */

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Autowired
public @interface MyAutowired {

    boolean required() default true;

}
