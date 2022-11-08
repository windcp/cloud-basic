package com.wind.kafka.config;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.BackOffExecution;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/11/08/16:17
 * @Description:
 */
@Configuration
public class KafkaConsumeConfig {

    @Autowired
    private KafkaProperties properties;

    // 消息过滤器
    @Bean
    //@ConditionalOnBean(ConcurrentKafkaListenerContainerFactoryConfigurer.class)
    public ConcurrentKafkaListenerContainerFactory myContainerFactory(ConsumerFactory consumerFactory,
                                                                      ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory);

        configurer.configure(factory,consumerFactory());

//        // 被过滤的消息将被丢弃
//        factory.setAckDiscarded(true);
//        // 消息过滤策略
//        factory.setRecordFilterStrategy(consumerRecord -> {
//            if (Integer.parseInt(consumerRecord.value().toString()) % 2 == 0) {
//                return false;
//            }
//            //返回true消息则被过滤
//            return true;
//        });
        return factory;
    }

    //获得创建消费者工厂
    public ConsumerFactory<Object, Object> consumerFactory() {
        KafkaProperties myKafkaProperties = JSON.parseObject(JSON.toJSONString(this.properties), KafkaProperties.class);
        //对模板 properties 进行定制化
        //....
        //例如：定制servers
        //myKafkaProperties.setBootstrapServers(myServers);
        myKafkaProperties.getConsumer().setMaxPollRecords(null);
        myKafkaProperties.getListener().setType(KafkaProperties.Listener.Type.SINGLE);
        return new DefaultKafkaConsumerFactory<>(myKafkaProperties.buildConsumerProperties());
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory containerFactory(ConsumerFactory consumerFactory,KafkaTemplate kafkaTemplate) {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        // 最大重试次数3次
        //死刑队列 https://blog.csdn.net/qq_37362891/article/details/113656416
        factory.setErrorHandler(new SeekToCurrentErrorHandler(new DeadLetterPublishingRecoverer(kafkaTemplate), new BackOff() {
            @Override
            public BackOffExecution start() {
                return new BackOffExecution() {
                    @Override
                    public long nextBackOff() {
                        return 3;
                    }
                };
            }
        }));
        return factory;
    }


}
