//package com.wind.serviceorder.config;
//
//import com.netflix.loadbalancer.IRule;
//import com.netflix.loadbalancer.RandomRule;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created with IntelliJ IDEA.
// *
// * @Author: v_vwlkunli
// * @Date: 2022/10/12/16:30
// * @Description:
// */
//@Configuration
//public class RibbonRuleConfig {
//
//    /**
//     * 全局配置，指定负载均衡策略
//     * @return
//     */
//    @Bean
//    public IRule iRule(){//方法名一定叫iRule，遵循约定大于配置
//        return new RandomRule();//使用随机负载均衡策略
//        //return new NacosRule();//指定使用Nacos提供的负载均衡策略（优先调用统一集群的实例，基于随机权重）
//    }
//
//}
