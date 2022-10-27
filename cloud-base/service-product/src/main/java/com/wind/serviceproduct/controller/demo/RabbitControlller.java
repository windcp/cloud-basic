package com.wind.serviceproduct.controller.demo;

import com.wind.serviceproduct.service.demo.rabbitmq.rabbitmq.DemoSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/27/11:07
 * @Description:
 */
@RestController
@RequestMapping("/rabbitmqDemo")
public class RabbitControlller {

    @Autowired
    private DemoSend demoSend;

    @PostMapping("/sendTest")
    public String sendTest(@RequestBody String message){
        return demoSend.sendTest(message);
    }
}
