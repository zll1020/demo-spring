package com.example.spring.demospring.ioc.dependency;

import com.example.spring.demospring.ioc.annotation.Super;
import com.example.spring.demospring.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Description: 依赖查找
 * User: zhangll
 * Date: 2020-07-03
 * Time: 11:28
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");

        // 实时查找
        lookupInRealTime(beanFactory);

        // 延迟查找
        lookupInLazy(beanFactory);

        // 按照类型查找
        lookupByType(beanFactory);

        // 按照类型查找结合对象
        lookupCollectionByType(beanFactory);

        // 通过注解查找对象
        lookupByAnnotationType(beanFactory);

    }

    /**
     * 实时查找 按名称ch
     * @pam beanFactory
     */
    private static void lookupInRealTime(BeanFactory beanFactory) {

    }

    /**
     * 延迟查找
     * objectFactory Bean 没有生成新的bean，是与factoryBean的区别
     * @param beanFactory
     */
    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("objectFactory 实时查找：" + user);
    }

    /**
     * 按类型查找
     * @param beanFactory
     */
    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("实时查找：" + user);
    }

    /**
     * 按照类型查找结合对象
     * @param beanFactory
     */
    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有的 User 集合对象：" + users);
        }
    }

    /**
     * 通过注解查找对象
     * @param beanFactory
     */
    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找标注 @Super 所有的 User 集合对象：" + users);
        }
    }
}
