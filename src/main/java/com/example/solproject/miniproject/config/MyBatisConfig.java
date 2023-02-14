package com.example.solproject.miniproject.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.example.solproject.miniproject.mapper")
public class MyBatisConfig {
    
}
