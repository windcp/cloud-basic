package com.wind.serviceopenfeign.config;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/13/16:24
 * @Description:
 */

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局配置：当使用@Configuration 会将配置作用所有的服务提供方
 * 局部配置：如果只想针对某一个服务进行配置，就不要加@Configuration
 */
//@Configuration
//public class OpenFeignConfig {
//
//    @Bean
//    public Logger.Level feignLoggerLevel(){
//        return Logger.Level.FULL;
//    }
//}