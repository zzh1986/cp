package com.eleven.five;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.*"})
@MapperScan({"com.eleven.five.mapper"})
@EnableJpaRepositories
public class ComElevenFiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComElevenFiveApplication.class, args);
    }
}
