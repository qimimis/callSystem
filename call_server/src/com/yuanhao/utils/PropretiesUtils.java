package com.yuanhao.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropretiesUtils {

    public static Map<String, String> getProperties(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(is);
        Set<String> setKey = properties.stringPropertyNames();
        Set<Map.Entry<Object, Object>> set = properties.entrySet();
        Map<String,String> map  = new HashMap<>();
        for (Map.Entry<Object, Object> entry : set) {
            map.put((String) entry.getKey(), (String) entry.getValue());
        }
        is.close();
        return map;
    }

}
