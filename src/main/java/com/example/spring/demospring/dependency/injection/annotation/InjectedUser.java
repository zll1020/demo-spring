package com.example.spring.demospring.dependency.injection.annotation;

import java.lang.annotation.*;

/**
 * Description: 自定义依赖注入注解
 * User: zhangll
 * Date: 2020-07-08
 * Time: 11:20
 */

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedUser {



}
