package com.wind.kafka.Listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/11/08/11:21
 * @Description:
 */
@Component
public class DemoKafkaListener {

    Logger logger = LoggerFactory.getLogger(DemoKafkaListener.class);

    @KafkaListener(topics = {"demo.test"})
    public void onMessage(ConsumerRecord<?, ?> record){
        logger.info("消费单个 record{}",record);
    }
}
