package com.wind.serviceopenfeign.intercepter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
public class GlobalFeignIntercepter implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
//        log.debug("全局feign拦截器");
        System.out.println("全局feign拦截器");
    }
}
