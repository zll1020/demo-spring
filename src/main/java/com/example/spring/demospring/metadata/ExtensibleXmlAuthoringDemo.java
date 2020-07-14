package com.example.spring.demospring.metadata;

import com.example.spring.demospring.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Description: Spring XML 元素扩展示例
 * User: zhangll
 * Date: 2020-07-11
 * Time: 18:21
 */
public class ExtensibleXmlAuthoringDemo {

    public static void main(String[] args) {

        // 创建 IoC 底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 创建 XML 资源的 BeanDefinitionReader
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // 记载 XML 资源
        reader.loadBeanDefinitions("META-INF/users-context.xml");
        // 获取 User Bean 对象
        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }

}
