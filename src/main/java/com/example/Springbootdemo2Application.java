package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableCaching
@MapperScan(value = "com.example.dao")
//@EnableEurekaClient
public class Springbootdemo2Application {
    public static void main(String[] args) {
        SpringApplication.run(Springbootdemo2Application.class, args);
    }
}
