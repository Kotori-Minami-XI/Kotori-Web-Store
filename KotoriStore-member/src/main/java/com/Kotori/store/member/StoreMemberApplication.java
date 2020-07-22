package com.Kotori.store.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.Kotori.store.Member.dao")
public class StoreMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreMemberApplication.class);
    }
}
