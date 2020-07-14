package com.example.spring.demospring.resource.sun.net.www.protocol.x;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * Description: X 协议 {@link URLStreamHandler} 实现
 * User: zhangll
 * Date: 2020-07-14
 * Time: 14:58
 */
public class Handler extends URLStreamHandler {
    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new XURLConnection(u);
    }
}
