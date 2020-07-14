package com.example.spring.demospring.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Description: 自定义 Component "派生性"注解
 * User: zhangll
 * Date: 2020-07-14
 * Time: 11:19
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component // 元注解，实现 @Component "派生性"
public @interface MyComponent {
}
