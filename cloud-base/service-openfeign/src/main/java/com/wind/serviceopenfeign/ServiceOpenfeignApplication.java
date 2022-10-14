package com.wind.serviceopenfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServiceOpenfeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOpenfeignApplication.class, args);
    }

}
