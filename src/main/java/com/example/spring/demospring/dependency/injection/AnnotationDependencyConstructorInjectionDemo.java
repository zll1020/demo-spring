package com.example.spring.demospring.dependency.injection;

import com.example.spring.demospring.bean.lifecycle.UserHolder;
import com.example.spring.demospring.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Description: 基于 Java 注解的依赖 Constructor 注入示例
 * User: zhangll
 * Date: 2020-07-08
 * Time: 10:20
 */
public class AnnotationDependencyConstructorInjectionDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 不加入此行 报错 No qualifying bean of type 'com.example.spring.demospring.bean.lifecycle.UserHolder'
        applicationContext.register(AnnotationDependencySetterInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";;

        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean(UserHolder.class);

        System.out.println(userHolder);

        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user){
        return new UserHolder(user);
    }

}
