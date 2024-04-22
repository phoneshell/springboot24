package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient //服务注册和发现
@SpringBootApplication
public class Main9527 {
    public static void main(String[] args) {
        SpringApplication.run(Main9527.class, args);
    }
}