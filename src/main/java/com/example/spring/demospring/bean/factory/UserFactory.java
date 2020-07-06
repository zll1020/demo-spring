package com.example.spring.demospring.bean.factory;


import com.example.spring.demospring.domain.User;

/**
 * {@link User} 工厂类
 *
 * @author lillian
 * @since
 */
public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }
}
