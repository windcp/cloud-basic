package com.wind.serviceorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
//可配置多个  RibbonRuleConfig不能被@SpringBootApplication的@ComponentScan扫描到，所以把它放到上一层，否则就是全局配置的效果
//@RibbonClients(value = {
//        @RibbonClient(name = "service-stock",configuration = RibbonRuleConfig.class)
//})
public class ServiceOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }

}
