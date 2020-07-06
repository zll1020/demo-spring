package com.example.spring.demospring.bean.definition;

import com.example.spring.demospring.bean.factory.DefaultUserFactory;
import com.example.spring.demospring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Bean 初始化 Demo
 *
 * @author Lillian
 * @since
 */
@Configuration // Configuration Class
public class BeanInitializationDemo {

    /**
     * @PostConstruct : UserFactory 初始化中...
     * InitializingBean#afterPropertiesSet() : UserFactory 初始化中...
     * 自定义初始化方法 initUserFactory() : UserFactory 初始化中...
     * Spring 应用上下文已启动...
     * com.example.spring.demospring.bean.factory.DefaultUserFactory@1fe20588
     * Spring 应用上下文准备关闭...
     * 15:50:34.137 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@1f89ab83, started on Mon Jul 06 15:50:33 CST 2020
     * @PreDestroy : UserFactory 销毁中...
     * DisposableBean#destroy() : UserFactory 销毁中...
     * 自定义销毁方法 doDestroy() : UserFactory 销毁中...
     * Spring 应用上下文已关闭...
     * @param args
     */
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类）
        applicationContext.register(BeanInitializationDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();
        // 非延迟初始化在 Spring 应用上下文启动完成后，被初始化
        System.out.println("Spring 应用上下文已启动...");
        // 依赖查找 UserFactory
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);
        System.out.println("Spring 应用上下文准备关闭...");
        // 关闭 Spring 应用上下文
        applicationContext.close();
        System.out.println("Spring 应用上下文已关闭...");
    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    @Lazy(value = false)
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
