package com.wind.rabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/26/14:31
 * @Description:
 */
@Service
public class RabbitSendService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void test(){

    }
}
