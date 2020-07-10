package com.example.spring.demospring.dependency.injection;

import com.example.spring.demospring.bean.lifecycle.UserHolder;
import com.example.spring.demospring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * Description: 基于 Java 注解的依赖字段注入示例
 * User: zhangll
 * Date: 2020-07-08
 * Time: 11:02
 */
public class AnnotationDependencyFieldInjectionDemo {

    @Autowired
    private
//    static // @Autowired 会忽略掉静态字段
            UserHolder userHolder;

    @Resource
    private UserHolder userHolder2;

    @Bean
    public UserHolder userHolder(User user){
        return new UserHolder(user);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类） -> Spring Bean
        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        AnnotationDependencyFieldInjectionDemo demo = applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);

        System.out.println( "userHolder :" + demo.userHolder);
        System.out.println( "userHolder2 :" + demo.userHolder2);

        // true
        System.out.println(demo.userHolder == demo.userHolder2);
        applicationContext.close();

    }
}
