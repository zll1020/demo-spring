package com.example.spring.demospring.bean.definition;

import com.example.spring.demospring.bean.factory.DefaultUserFactory;
import com.example.spring.demospring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description: 单体 Bean 注册实例
 * User: zhangll
 * Date: 2020-07-04
 * Time: 13:38
 */
public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {
        // 创建应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        // 创建一个外部 UserFactory 对象
        UserFactory userFactory = new DefaultUserFactory ();

        SingletonBeanRegistry singletonBeanRegistry = applicationContext.getBeanFactory();

        // 注册外部单例对象
        singletonBeanRegistry.registerSingleton("userFactory",userFactory);

        applicationContext.refresh();

        UserFactory userFactoryByLookup = applicationContext.getBean("userFactory",UserFactory.class);
        System.out.println("userFactory  == userFactoryByLookup : " + (userFactory == userFactoryByLookup));

        applicationContext.close();

    }




}
