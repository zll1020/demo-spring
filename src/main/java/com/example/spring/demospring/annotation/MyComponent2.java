package com.example.spring.demospring.annotation;

import java.lang.annotation.*;

/**
 * Description: {@link MyComponent} "派生"注解
 * User: zhangll
 * Date: 2020-07-14
 * Time: 11:20
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent
public @interface MyComponent2 {
}
