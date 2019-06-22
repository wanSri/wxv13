package com.qf.registerweb;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class RegisterWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterWebApplication.class, args);
    }

}
