package com.example.spring.demospring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * Description: 带有字符编码的 {@link FileSystemResource} 示例
 * @see FileSystemResource
 * @see EncodedResource
 * User: zhangll
 * Date: 2020-07-14
 * Time: 15:59
 */

public class EncodedFileSystemResourceDemo {

    public static void main(String[] args) throws IOException {
        String currentJavaFilePath = System.getProperty("user.dir") + "/src/main/java/com/example/spring/demospring/resource/EncodedFileSystemResourceDemo.java";
        File currentJavaFile = new File(currentJavaFilePath);
        // FileSystemResource => WritableResource => Resource
        FileSystemResource fileSystemResource = new FileSystemResource(currentJavaFilePath);
        EncodedResource encodedResource = new EncodedResource(fileSystemResource, "UTF-8");
        // 字符输入流
        // 字符输入流
        try (Reader reader = encodedResource.getReader()) {
            System.out.println(IOUtils.toString(reader));
        }
    }
}
