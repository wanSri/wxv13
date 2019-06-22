package com.qf.v13userservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@MapperScan("com.qf.mapper")
@SpringBootApplication
public class V13UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(V13UserServiceApplication.class, args);
    }

}
