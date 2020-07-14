package com.example.spring.demospring.applicationcontext.lifecycle;

import org.springframework.context.Lifecycle;

/**
 * Description: 自定义 {@link Lifecycle} 实现
 * User: zhangll
 * Date: 2020-07-14
 * Time: 10:43
 */
public class MyLifecycle implements Lifecycle{

    private boolean running = false;


    @Override
    public void start() {
        running = true;
        System.out.println("MyLifecycle 启动...");
    }

    @Override
    public void stop() {
        running = false;
        System.out.println("MyLifecycle 停止...");
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
