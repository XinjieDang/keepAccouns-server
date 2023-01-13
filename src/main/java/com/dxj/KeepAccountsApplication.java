package com.dxj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dxj.mapper")
public class KeepAccountsApplication {
    public static void main(String[] args) {
        SpringApplication.run(KeepAccountsApplication.class, args);
    }
}
