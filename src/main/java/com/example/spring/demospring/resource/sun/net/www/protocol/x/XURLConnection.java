package com.example.spring.demospring.resource.sun.net.www.protocol.x;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Description: X {@link URLConnection} 实现
 * User: zhangll
 * Date: 2020-07-14
 * Time: 14:58
 */
public class XURLConnection extends URLConnection {

    private final ClassPathResource resource;

    /**
     * Constructs a URL connection to the specified URL. A connection to
     * the object referenced by the URL is not created.
     *
     * @param url the specified URL.
     */

    // URL = x:///META-INF/default.properties
    protected XURLConnection(URL url) {
        super(url);
        this.resource = new ClassPathResource(url.getPath());
    }

    @Override
    public void connect() throws IOException {

    }

    public InputStream getInputStream() throws IOException {
        return resource.getInputStream();
    }
}
