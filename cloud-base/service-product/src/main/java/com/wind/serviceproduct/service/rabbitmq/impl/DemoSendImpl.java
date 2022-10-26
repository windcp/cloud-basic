package com.wind.serviceproduct.service.rabbitmq.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wind.serviceproduct.service.rabbitmq.DemoSend;

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
    private RabbitTemplate rabbitTemplate;

}
