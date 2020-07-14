package com.example.spring.demospring.resource.sun.net.www.protocol.x;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Description: X Handler 测试示例
 * User: zhangll
 * Date: 2020-07-14
 * Time: 14:59
 */
public class HandlerTest {

    public static void main(String[] args) throws IOException {
        URL url = new URL("x:///META-INF/resource-default.properties"); // 类似于 classpath:/META-INF/default.properties
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));
    }

}
