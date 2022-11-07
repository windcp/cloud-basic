package com.wind.serviceproduct.service.demo.kafka.product.impl;

import com.wind.serviceproduct.service.demo.kafka.product.DemoSendKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wind.kafka.repo.DemoKafkaSend;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/11/07/15:34
 * @Description:
 */
@Service
public class DemoSendKafkaImpl implements DemoSendKafka {

    @Autowired
    private DemoKafkaSend demoKafkaSend;

    @Override
    public void send(String message) {
        demoKafkaSend.demoSend(message);
    }
}
