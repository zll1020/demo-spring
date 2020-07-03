package com.example.spring.demospring.ioc.domain;

import com.example.spring.demospring.ioc.annotation.Super;
import lombok.Data;

/**
 * Description:
 * User: zhangll
 * Date: 2020-07-03
 * Time: 11:26
 */
@Data
@Super
public class User {

    private Long id;

    private String name;

}
