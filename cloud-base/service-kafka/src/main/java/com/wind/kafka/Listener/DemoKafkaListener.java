package com.wind.kafka.Listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
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


//    @KafkaListener(id = "consumer1",groupId = "felix-group",topicPartitions = {
//            @TopicPartition(topic = "topic1", partitions = { "0" }),
//            @TopicPartition(topic = "topic2", partitions = "0", partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "8"))
//    })
    @KafkaListener(id = "demo-consumer"
            ,groupId = "demo-group"
            ,topics = {"demo.test"}
            //,containerFactory = "myContainerFactory"
    )
    public void onMessage(ConsumerRecord<?, ?> record){
        logger.info("消费单个 record{}",record);
    }

    @KafkaListener(id = "demo-consumer"
            ,groupId = "demo-group"
            ,topics = {"demo.test2"}
            //,containerFactory = "myContainerFactory"
    )
    public void onMessage2(String msgData, Acknowledgment ack){
        logger.info("消费单个 msgData{}",msgData);
        //手动提交
        //enable.auto.commit参数设置成false。那么就是Spring来替为我们做人工提交，从而简化了人工提交的方式。
        //所以kafka和springboot结合中的enable.auto.commit为false为spring的人工提交模式。
        //enable.auto.commit为true是采用kafka的默认提交模式。
        ack.acknowledge();
    }
}
