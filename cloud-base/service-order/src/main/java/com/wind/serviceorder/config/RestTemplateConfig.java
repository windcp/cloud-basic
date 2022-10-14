//package com.wind.serviceorder.config;
//
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
///**
// * Created with IntelliJ IDEA.
// *
// * @Author: v_vwlkunli
// * @Date: 2022/10/11/21:42
// * @Description:
// */
//@Configuration
//@Component
//public class RestTemplateConfig {
//
//    @Bean
//    @LoadBalanced //使RestTemplate请求支持负载均衡
//    public RestTemplate restTemplate(RestTemplateBuilder builder){
//        RestTemplate restTemplate = builder.build();
//        return restTemplate;
//    }
//}
