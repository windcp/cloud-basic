package com.wind.kafka.Listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/11/08/15:35
 * @Description:
 */
@Component
public class DemoKafkaBatchListener {

    Logger logger = LoggerFactory.getLogger(DemoKafkaListener.class);

    @KafkaListener(id = "demo-batch-consumer" ,groupId = "demo-batch-group",topics = {"demo.batch.test"})
    public void onMessage(List<ConsumerRecord<?, ?>> recordList){
        logger.info("消费批量 recordList{}",recordList);
    }
}
