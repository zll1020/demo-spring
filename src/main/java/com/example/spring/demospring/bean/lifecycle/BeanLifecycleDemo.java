package com.example.spring.demospring.bean.lifecycle;

import com.example.spring.demospring.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * TODO
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class BeanLifecycleDemo {

    /**
     * 已加载 BeanDefinition 数量：5
     * 17:15:03.996 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'user'
     * 17:15:04.021 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'superUser'
     * 17:15:04.022 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'objectFactory'
     * 17:15:04.066 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'com.example.spring.demospring.bean.lifecycle.MyInstantiationAwareBeanPostProcessor#0'
     * 17:15:04.071 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'userHolder'
     * 17:15:04.077 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Autowiring by type from bean name 'userHolder' via constructor to bean named 'superUser'
     * initPostConstruct() = The user holder V4
     * afterPropertiesSet() = The user holder V5
     * init() = The user holder V6
     * afterSingletonsInstantiated() = The user holder V8
     * User{id=2, name='Lillian', city=null}
     * SuperUser{address='null'} User{id=null, name='null', city=null}
     * UserHolder{user=SuperUser{address='null'} User{id=null, name='null', city=null}, number=1, description='The user holder V8', beanName='userHolder'}
     * postProcessBeforeDestruction() : The user holder V9
     * preDestroy() = The user holder V10
     * destroy() = The user holder V11
     * doDestroy() = The user holder V12
     * UserHolder{user=SuperUser{address='null'} User{id=null, name='null', city=null}, number=1, description='The user holder V12', beanName='userHolder'}
     * postProcessBeforeDestruction() : The user holder V9
     * preDestroy() = The user holder V10
     * destroy() = The user holder V11
     * doDestroy() = The user holder V12
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 添加 BeanPostProcessor 实现 MyInstantiationAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 添加 MyDestructionAwareBeanPostProcessor 执行销毁前回调
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        // 添加 CommonAnnotationBeanPostProcessor 解决 @PostConstruct @PreDestroy
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

        // 执行 Bean 销毁（容器内）
        beanFactory.destroyBean("userHolder", userHolder);
        // Bean 销毁并不意味着 Bean 垃圾回收了
        System.out.println(userHolder);

        // 销毁 BeanFactory 中的单例 Bean
        beanFactory.destroySingletons();
        // 强制 GC
        System.gc();
        // 等待一段时间
        Thread.sleep(1000L);
        // 强制 GC
        System.gc();
    }
}
