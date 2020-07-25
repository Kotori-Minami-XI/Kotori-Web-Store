package com.Kotori.store.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.Kotori.store.product.dao")
public class StoreProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreProductApplication.class);
    }
}
