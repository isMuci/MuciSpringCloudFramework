package com.muci.framework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.muci.auth.infra.basic.dao")
//@EnableFeignClients(basePackages = "com.muci")
@SpringBootApplication(scanBasePackages = "com.muci")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class);
    }
}
