package com.qf.v13searchservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.qf.mapper")
public class V13SearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(V13SearchServiceApplication.class, args);
    }

}
