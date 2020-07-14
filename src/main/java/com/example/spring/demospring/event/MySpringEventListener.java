package com.example.spring.demospring.event;

import org.springframework.context.ApplicationListener;

/**
 * Description: 自定义 Spring 事件监听器
 * User: zhangll
 * Date: 2020-07-14
 * Time: 17:14
 */
public class MySpringEventListener implements ApplicationListener<MySpringEvent> {

    @Override
    public void onApplicationEvent(MySpringEvent event) {
        System.out.printf("[线程 ： %s] 监听到事件 : %s\n", Thread.currentThread().getName(), event);
    }
}
