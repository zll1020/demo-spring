package com.example.spring.demospring.metadata;

import com.example.spring.demospring.domain.User;
import com.example.spring.demospring.enums.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * Description: 基于 Java 注解的 YAML 外部化配置示例
 * User: zhangll
 * Date: 2020-07-11
 * Time: 17:17
 */
@PropertySource(
        name = "yamlPropertySource",
        value = "classpath:/META-INF/user.yaml",
        factory = YamlPropertySourceFactory.class)
public class AnnotatedYamlPropertySourceDemo {

    /**
     * user.name 是 Java Properties 默认存在，当前用户：zhangll，而非配置文件中定义"Lillian"
     * @param id
     * @param name
     * @return
     */
    @Bean
    public User user(@Value("${user.id}") Long id, @Value("${user.name}") String name, @Value("${user.city}") City city) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCity(city);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类作为 Configuration Class
        context.register(AnnotatedYamlPropertySourceDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();
        // 获取 Map YAML 对象
        // User{id=2, name='zhangll', city=BEIJING}
        User user = context.getBean("user", User.class);
        System.out.println(user);
        // 关闭 Spring 应用上下文
        context.close();
    }

}
