package com.example.spring.demospring.metadata;

import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Description: "users.xsd" {@link NamespaceHandler} 实现
 * User: zhangll
 * Date: 2020-07-11
 * Time: 18:24
 */
public class UsersNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        // 将 "user" 元素注册对应的 BeanDefinitionParser 实现
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }

}
