package com.example.spring.demospring.dependency.injection;

import com.example.spring.demospring.bean.lifecycle.UserHolder;
import com.example.spring.demospring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * Description: 基于 Java 注解的依赖方法注入示例
 * User: zhangll
 * Date: 2020-07-08
 * Time: 10:46
 */

public class AnnotationDependencyMethodInjectionDemo {

    private UserHolder userHolder;

    private UserHolder userHolder1;

    @Autowired
    public void init(UserHolder userHolder){
        this.userHolder = userHolder;
    }

    @Resource
    public void init2(UserHolder userHolder2){
        this.userHolder1 = userHolder2;
    }

    @Bean
    public UserHolder userHolder(User user){
        return new UserHolder(user);
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类） -> Spring Bean
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找 AnnotationDependencyFieldInjectionDemo Bean
        AnnotationDependencyMethodInjectionDemo demo = applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);

        // @Autowired 字段关联
        UserHolder userHolder = demo.userHolder;
        System.out.println("userHolder：" + userHolder);
        System.out.println("userHolder1：" + demo.userHolder1);

        // true
        System.out.println(userHolder == demo.userHolder1);


        // 显示地关闭 Spring 应用上下文
        applicationContext.close();

    }

}
