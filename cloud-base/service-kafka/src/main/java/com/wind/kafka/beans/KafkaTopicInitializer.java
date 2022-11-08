package com.wind.kafka.beans;

import com.wind.kafka.model.bo.KafkaModuleProperties;
import com.wind.kafka.model.bo.KafkaTopicBo;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/11/07/11:43
 * @Description: kafka topic初始化器
 * 实现SmartInitializingSingleton的接口后，当所有单例 bean 都初始化完成以后，
 * Spring的IOC容器会回调该接口的 afterSingletonsInstantiated()方法。
 * 主要应用场合就是在所有单例 bean 创建完成之后，可以在该回调中做一些事情。
 */
public class KafkaTopicInitializer implements SmartInitializingSingleton {

    private static final Logger logger = LoggerFactory.getLogger(KafkaTopicInitializer.class);

    private KafkaModuleProperties kafkaModuleProperties;

    private GenericApplicationContext genericApplicationContext;

    public KafkaTopicInitializer(KafkaModuleProperties kafkaModuleProperties,GenericApplicationContext genericApplicationContext){
        this.kafkaModuleProperties = kafkaModuleProperties;
        this.genericApplicationContext = genericApplicationContext;
    }


    @Override
    public void afterSingletonsInstantiated() {
        logger.info("kafka 根据配置动态创建topic");
        declareKafkaModule();
    }

    private void declareKafkaModule(){
        List<KafkaTopicBo> topicBos = kafkaModuleProperties.getTopics();
        topicBos.forEach(topicBo ->{
            genericApplicationContext.registerBean(topicBo.getName(),NewTopic.class,topicBo.getName(),topicBo.getNumPartitions(),topicBo.getReplicationFactor());
            //KafkaAdminClient 这个也可以创建topic，可以研究下
        });
    }
}
