package com.Kotori.store.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.Kotori.store.Member.dao")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.Kotori.store.member.feign")
public class StoreMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreMemberApplication.class);
    }
}
