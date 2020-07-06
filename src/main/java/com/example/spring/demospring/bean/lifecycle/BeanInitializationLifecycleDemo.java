package com.example.spring.demospring.bean.lifecycle;

import com.example.spring.demospring.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * Bean 初始化生命周期示例
 *
 * @author Lillian
 * @since
 */
public class BeanInitializationLifecycleDemo {

    /**
     * 已加载 BeanDefinition 数量：5
     * 17:14:23.944 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'user'
     * 17:14:23.966 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'superUser'
     * 17:14:23.967 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'objectFactory'
     * 17:14:24.018 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'com.example.spring.demospring.bean.lifecycle.MyInstantiationAwareBeanPostProcessor#0'
     * 17:14:24.025 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'userHolder'
     * 17:14:24.032 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Autowiring by type from bean name 'userHolder' via constructor to bean named 'superUser'
     * initPostConstruct() = The user holder V4
     * afterPropertiesSet() = The user holder V5
     * init() = The user holder V6
     * afterSingletonsInstantiated() = The user holder V8
     * User{id=2, name='Lillian', city=null}
     * SuperUser{address='null'} User{id=null, name='null', city=null}
     * UserHolder{user=SuperUser{address='null'} User{id=null, name='null', city=null}, number=1, description='The user holder V8', beanName='userHolder'}
     * @param args
     */
    public static void main(String[] args) {

        executeBeanFactory();

    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 添加 BeanPostProcessor 实现 MyInstantiationAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 添加 CommonAnnotationBeanPostProcessor 解决 @PostConstruct
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.println("已加载 BeanDefinition 数量：" + beanNumbers);
        // 显示地执行 preInstantiateSingletons()
        // SmartInitializingSingleton 通常在 Spring ApplicationContext 场景使用
        // preInstantiateSingletons 将已注册的 BeanDefinition 初始化成 Spring Bean
        beanFactory.preInstantiateSingletons();

        // 通过 Bean Id 和类型进行依赖查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);

        // 构造器注入按照类型注入，resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);

    }

}

