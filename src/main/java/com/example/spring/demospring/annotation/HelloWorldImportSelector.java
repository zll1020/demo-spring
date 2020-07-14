package com.example.spring.demospring.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Description: HelloWorld 模块 {@link ImportSelector} 实现
 * User: zhangll
 * Date: 2020-07-14
 * Time: 14:40
 */
public class HelloWorldImportSelector implements ImportSelector{

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"org.geekbang.thinking.in.spring.annotation.HelloWorldConfiguration"}; // 导入
    }

}
