package com.bmwapp.flat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FlatApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlatApplication.class, args);
    }
}