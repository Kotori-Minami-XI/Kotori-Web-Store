package com.Kotori.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.Kotori.store.coupon.dao")
@EnableDiscoveryClient
public class StoreCouponApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreCouponApplication.class);
    }
}
