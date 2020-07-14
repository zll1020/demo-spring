package com.example.spring.demospring.applicationcontext.lifecycle;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

/**
 * Description: Spring Shutdown Hook 线程示例
 * User: zhangll
 * Date: 2020-07-14
 * Time: 10:52
 */
public class SpringShutdownHookThreadDemo {

    public static void main(String[] args) throws IOException {
        GenericApplicationContext context = new GenericApplicationContext();

        context.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
            @Override
            public void onApplicationEvent(ContextClosedEvent event) {
                System.out.printf("[线程 %s] ContextClosedEvent 处理\n", Thread.currentThread().getName());
            }
        });

        // 刷新 Spring 应用上下文
        context.refresh();

        // 注册 Shutdown Hook
        context.registerShutdownHook();

        System.out.println("按任意键继续并且关闭 Spring 应用上下文");
        System.in.read();

        // 关闭 Spring 应用（同步）
        context.close();
    }

}
