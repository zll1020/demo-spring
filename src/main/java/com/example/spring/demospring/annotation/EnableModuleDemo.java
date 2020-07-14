package com.example.spring.demospring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description: Enable 模块驱动示例
 * User: zhangll
 * Date: 2020-07-14
 * Time: 14:41
 */
public class EnableModuleDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        context.register(EnableModuleDemo.class);

        // 启动 Spring 应用上下文
        context.refresh();

        String helloWorld = context.getBean("helloWorld", String.class);

        System.out.println(helloWorld);

        // 关闭 Spring 应用上下文
        context.close();
    }

}
