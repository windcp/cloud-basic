package com.wind.serviceopenfeign.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/17/10:53
 * @Description:
 */
@RestController
@RequestMapping("/config")
@RefreshScope //nacos config自动刷新
public class ConfigerController {

    @Value("${user.config.local:null}")
    private String info;     //该属性值是从nacos配置中心拉取到的配置

    /**
     * 新增订单
     * @return
     */
    @GetMapping("/getValue")
    public String getValue(){

        return "nacos-config value:" + info;
    }
}
