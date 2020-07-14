package com.example.spring.demospring.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: HelloWorld Configuration Class
 * User: zhangll
 * Date: 2020-07-14
 * Time: 11:18
 */
@Configuration
public class HelloWorldConfiguration {

    @Bean
    public String helloWorld() {
        return "Hello,World";
    }

}
