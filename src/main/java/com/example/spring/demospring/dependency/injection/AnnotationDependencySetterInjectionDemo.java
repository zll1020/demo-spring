package com.example.spring.demospring.dependency.injection;

import com.example.spring.demospring.bean.lifecycle.UserHolder;
import com.example.spring.demospring.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Description: 基于 Java 注解的依赖 Setter 方法注入示例
 * User: zhangll
 * Date: 2020-07-08
 * Time: 10:13
 */
public class AnnotationDependencySetterInjectionDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

        annotationConfigApplicationContext.register(AnnotationDependencySetterInjectionDemo.class);

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(annotationConfigApplicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        xmlBeanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动应用上下文
        annotationConfigApplicationContext.refresh();

        // 依赖查找并且创建 Bean
        UserHolder userHolder = annotationConfigApplicationContext.getBean(UserHolder.class);

        System.out.println(userHolder);

        // 显示地关闭 Spring 应用上下文
        annotationConfigApplicationContext.close();


    }

    @Bean
    public UserHolder userHolder(User user) {
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }
}
