package com.Kotori.store.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.Kotori.store.order.dao")
public class StoreOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreOrderApplication.class);
    }
}
