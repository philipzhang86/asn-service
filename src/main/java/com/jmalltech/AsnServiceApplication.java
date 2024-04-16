package com.jmalltech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan(basePackages = {"com.jmalltech.mapper"})
@EnableCaching
@SpringBootApplication
public class AsnServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AsnServiceApplication.class, args);
        System.out.println("Hello asn service!");
    }
}