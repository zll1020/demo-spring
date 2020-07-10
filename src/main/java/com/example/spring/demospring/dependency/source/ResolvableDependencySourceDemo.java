package com.example.spring.demospring.dependency.source;

import com.example.spring.demospring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * Description: ResolvableDependency 作为依赖来源
 * User: zhangll
 * Date: 2020-07-10
 * Time: 14:34
 */
public class ResolvableDependencySourceDemo {

    @Autowired
    private String value;

    @Autowired
    private String name;

    @Autowired
    private User user;

    @PostConstruct
    public void init() {
        // Hello,World
        System.out.println(value);
        // Hello,World
        System.out.println(name);
        // User{id=1, name='Lillian', city=null}
        System.out.println(user);
    }

    public static void main(String[] args) {

        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class（配置类） -> Spring Bean
        applicationContext.register(ResolvableDependencySourceDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            // 注册 Resolvable Dependency
            // 所有String 字段都赋值
            beanFactory.registerResolvableDependency(String.class, "Hello,World");
            beanFactory.registerResolvableDependency(User.class, User.createUser());

        });

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }

}
