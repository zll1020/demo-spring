package com.example.spring.demospring.conversion;

import java.beans.PropertyEditor;

/**
 * Description: {@link PropertyEditor} 示例
 * User: zhangll
 * Date: 2020-07-17
 * Time: 16:46
 */
public class PropertyEditorDemo {

    public static void main(String[] args) {

        // 模拟 Spring Framework 操作
        // 有一段文本 name = lillian;
        String text = "name = lillian-conversion";

        PropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
        // 传递 String 类型的内容
        propertyEditor.setAsText(text);

        System.out.println(propertyEditor.getValue());

        System.out.println(propertyEditor.getAsText());
    }
}
