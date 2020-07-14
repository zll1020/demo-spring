package com.example.spring.demospring.metadata;

import com.example.spring.demospring.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * Description: 基于 Java 注解 Spring IoC 容器元信息配置示例
 * User: zhangll
 * Date: 2020-07-11
 * Time: 17:09
 */
// 将当前类作为 Configuration Class
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(User.class)
@PropertySource("classpath:/META-INF/user-bean-definitions.properties") // Java 8+ @Repeatable 支持
@PropertySource("classpath:/META-INF/user-bean-definitions.properties")
// @PropertySources(@PropertySource(...))
public class AnnotatedSpringIoCContainerMetadataConfigurationDemo {


    /**
     * user.name 是 Java Properties 默认存在，当前用户：zhangll，而非配置文件中定义"Lillian"
     * @param id
     * @param name
     * @return
     */
    @Bean
    public User configuredUser(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类作为 Configuration Class
        context.register(AnnotatedSpringIoCContainerMetadataConfigurationDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();
        // beanName 和 bean 映射
        Map<String, User> usersMap = context.getBeansOfType(User.class);

        /**
         * User Bean name : com.example.spring.demospring.domain.User , content : User{id=null, name='null', city=null}
         * User Bean name : configuredUser , content : User{id=1, name='zhangll', city=null}
         * User Bean name : user , content : User{id=1, name='Lillian', city=BEIJING}
         * User Bean name : superUser , content : SuperUser{address='北京'} User{id=1, name='Lillian', city=BEIJING}
         */
        for (Map.Entry<String, User> entry : usersMap.entrySet()) {
            System.out.printf("User Bean name : %s , content : %s \n", entry.getKey(), entry.getValue());
        }
        // 关闭 Spring 应用上下文
        context.close();

    }



}
