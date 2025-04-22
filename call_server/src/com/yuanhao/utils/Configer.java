package com.yuanhao.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configer {
    private Properties properties = new Properties();

    public Configer(String fileName){

        try {
            InputStream inStream = new FileInputStream(fileName);
            properties.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getKey(String key){
        String value = properties.getProperty(key);
        return value;
    }
}
