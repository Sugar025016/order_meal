package com.order_meal.order_meal.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.order_meal.order_meal.entity.FileData;


// 解決Entity無法使用@Value的問題
@Configuration
public class FilePathConfig {
    @Value("${filePath}")
    private String filePath;
        @PostConstruct
    public void init() {
        FileData.setImageGetUrl(filePath);
    }
}
