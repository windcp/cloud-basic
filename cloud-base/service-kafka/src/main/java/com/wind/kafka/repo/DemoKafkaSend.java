package com.wind.kafka.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/11/07/15:28
 * @Description:
 */
@Component
public class DemoKafkaSend {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void demoSend(String normalMessage){
        kafkaTemplate.send("demo-test",normalMessage);
    }

}
