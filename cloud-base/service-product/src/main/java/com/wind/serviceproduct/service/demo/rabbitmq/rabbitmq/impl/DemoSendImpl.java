package com.wind.serviceproduct.service.demo.rabbitmq.rabbitmq.impl;

import com.wind.rabbitmq.service.RabbitSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wind.serviceproduct.service.demo.rabbitmq.rabbitmq.DemoSend;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/26/16:12
 * @Description:
 */
@Service
public class DemoSendImpl implements DemoSend {

    @Autowired
    private RabbitSendService rabbitSendService;


    @Override
    public String sendTest(String message) {
        return rabbitSendService.send("demo.test","demo.test",message);
    }
}
