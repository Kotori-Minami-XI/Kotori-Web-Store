package com.Kotori.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.Kotori.store.coupon.dao")
public class StoreCouponApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreCouponApplication.class);
    }
}
