package com.wind.serviceproduct.service.demo.rabbitmq.product.impl;

import com.wind.rabbitmq.repo.RabbitSendRepo;
import com.wind.serviceproduct.service.demo.rabbitmq.product.DemoSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private RabbitSendRepo rabbitSendService;


    @Override
    public String sendTest(String message) {
        return rabbitSendService.send("demo.test","demo.test",message);
    }
}
