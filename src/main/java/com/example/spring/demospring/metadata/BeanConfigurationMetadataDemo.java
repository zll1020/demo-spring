package com.example.spring.demospring.metadata;

import com.example.spring.demospring.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

/**
 * Description: Bean 配置元信息示例
 * User: zhangll
 * Date: 2020-07-11
 * Time: 17:33
 */
public class BeanConfigurationMetadataDemo {

    public static void main(String[] args) {

        // BeanDefinition 的定义（声明）
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "zhangf");
        // 获取 AbstractBeanDefinition
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // 附加属性（不影响 Bean populate、initialize）
        beanDefinition.setAttribute("name", "Lillian");
        // 当前 BeanDefinition 来自于何方（辅助作用）
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
                    BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
                    if (BeanConfigurationMetadataDemo.class.equals(bd.getSource())) { // 通过 source 判断来
                        // 属性（存储）上下文
                        String name = (String) bd.getAttribute("name"); // Lillian
                        User user = (User) bean;
                        user.setName(name);
                    }
                }
                return bean;
            }
        });

        // 注册 User 的 BeanDefinition
        beanFactory.registerBeanDefinition("user", beanDefinition);

        User user = beanFactory.getBean("user", User.class);

        // User{id=null, name='Lillian', city=null}
        System.out.println(user);

    }
}
