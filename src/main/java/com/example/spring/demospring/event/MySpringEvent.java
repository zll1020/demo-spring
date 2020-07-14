package com.example.spring.demospring.event;

import org.springframework.context.ApplicationEvent;

/**
 * Description: 自定义 Spring 事件
 * User: zhangll
 * Date: 2020-07-14
 * Time: 17:11
 */
public class MySpringEvent extends ApplicationEvent {

    public MySpringEvent(String message) {
        super(message);
    }

    @Override
    public String getSource() {
        return (String) super.getSource();
    }

    public String getMessage() {
        return getSource();
    }
}
