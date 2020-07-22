package com.Kotori.store.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.Kotori.store.ware.dao")
public class StoreWareApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreWareApplication.class);
    }
}
