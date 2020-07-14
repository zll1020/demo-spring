package com.example.spring.demospring.event;

/**
 * Description: 自定义 Spring 事件
 * User: zhangll
 * Date: 2020-07-14
 * Time: 17:13
 */
public class MySpringEvent2 extends MySpringEvent {
    public MySpringEvent2(String message) {
        super(message);
    }

    @Override
    public String getSource() {
        return (String) super.getSource();
    }

    @Override
    public String getMessage() {
        return getSource();
    }
}
