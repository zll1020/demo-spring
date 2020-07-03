package com.example.spring.demospring.ioc.dependency;

import com.example.spring.demospring.ioc.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * Description: 依赖注册
 * User: zhangll
 * Date: 2020-07-03
 * Time: 15:45
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {

        // 两个上下文
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        // 依赖来源一 自定义Bean
        //UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        UserRepository userRepository = applicationContext.getBean("userRepository", UserRepository.class);

        System.out.println(userRepository.getUsers());

        // 依赖来源二：依赖注入（內建依赖）
        // org.springframework.beans.factory.support.DefaultListableBeanFactory@aecb35a:
        // defining beans [user,superUser,objectFactory,userRepository]; root of factory hierarchy
        System.out.println(userRepository.getBeanFactory());

        ObjectFactory userFactory = userRepository.getObjectFactory();

        System.out.println(userFactory.getObject() == applicationContext); // true ？

        // 报错 No qualifying bean of type 'org.springframework.beans.factory.BeanFactory
        //System.out.println(beanFactory.getBean(BeanFactory.class));

        // 依赖来源三：容器內建 Bean
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println("获取 Environment 类型的 Bean：" + environment);
    }


    /**
     * 谁才是Spring IOC容器
     * @param userRepository
     * @param applicationContext
     */
    private static void whoIsIoCContainer(UserRepository userRepository, ApplicationContext applicationContext) {


        // ConfigurableApplicationContext <- ApplicationContext <- BeanFactory

        // ConfigurableApplicationContext#getBeanFactory()

        // 这个表达式为什么不会成立
        System.out.println(userRepository.getBeanFactory() == applicationContext);

        // ApplicationContext is BeanFactory
        // 实现上，使用继承的方式，实际内部还有组合的方式，有点像代理的实现方式
        // 实际上是两个对象 ApplicationContext 组合了BeanFactory，新增了许多企业级应用
        }
}
