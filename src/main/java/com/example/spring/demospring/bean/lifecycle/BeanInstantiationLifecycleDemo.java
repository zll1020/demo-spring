package com.example.spring.demospring.bean.lifecycle;

import com.example.spring.demospring.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化生命周期示例
 *
 * @author Lillian
 * @since
 */
public class BeanInstantiationLifecycleDemo {

    /**
     * 16:44:08.035 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.annotation.internalConfigurationAnnotationProcessor'
     * 16:44:08.087 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerProcessor'
     * 16:44:08.089 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerFactory'
     * 16:44:08.091 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.annotation.internalAutowiredAnnotationProcessor'
     * 16:44:08.092 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.annotation.internalCommonAnnotationProcessor'
     * 16:44:08.097 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'com.example.spring.demospring.bean.lifecycle.MyInstantiationAwareBeanPostProcessor#0'
     * 16:44:08.112 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'user'
     * 16:44:08.113 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'superUser'
     * 16:44:08.113 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'objectFactory'
     * 16:44:08.128 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'userHolder'
     * 16:44:08.131 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Autowiring by type from bean name 'userHolder' via constructor to bean named 'superUser'
     * initPostConstruct() = The user holder V4
     * afterPropertiesSet() = The user holder V5
     * init() = The user holder V6
     * afterSingletonsInstantiated() = The user holder V8
     * User{id=2, name='Lillian', city=null}
     * SuperUser{address='null'} User{id=null, name='null', city=null}
     * UserHolder{user=SuperUser{address='null'} User{id=null, name='null', city=null}, number=1, description='The user holder V8', beanName='userHolder'}
     * 16:44:08.162 [main] DEBUG org.springframework.context.support.ClassPathXmlApplicationContext - Closing org.springframework.context.support.ClassPathXmlApplicationContext@543c6f6d, started on Mon Jul 06 16:44:07 CST 2020
     * preDestroy() = The user holder V10
     * destroy() = The user holder V11
     * doDestroy() = The user holder V12
     * @param args
     */
    public static void main(String[] args) {
        executeBeanFactory();

        System.out.println("--------------------------------");

        executeApplicationContext();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 方法一：添加 BeanPostProcessor 实现 MyInstantiationAwareBeanPostProcessor
        // beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 方法二：将 MyInstantiationAwareBeanPostProcessor 作为 Bean 注册
        // 基于 XML 资源 BeanDefinitionReader 实现
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        int beanNumbers = beanDefinitionReader.loadBeanDefinitions(locations);
        System.out.println("已加载 BeanDefinition 数量：" + beanNumbers);
        // 通过 Bean Id 和类型进行依赖查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);

        // 构造器注入按照类型注入，resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }

    private static void executeApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        applicationContext.setConfigLocations(locations);
        // 启动应用上下文
        applicationContext.refresh();

        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);

        User superUser = applicationContext.getBean("superUser", User.class);
        System.out.println(superUser);

        // 构造器注入按照类型注入，resolveDependency
        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);

        // 关闭应用上下文
        applicationContext.close();

    }


}

