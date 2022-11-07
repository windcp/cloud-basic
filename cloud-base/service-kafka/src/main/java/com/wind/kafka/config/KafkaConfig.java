package com.wind.kafka.config;

import com.wind.kafka.beans.KafkaTopicInitializer;
import com.wind.kafka.model.bo.KafkaModuleProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/11/07/14:36
 * @Description:
 */
@Configuration
public class KafkaConfig {

    /**
     * 动态创建topic初始化
     */
    @Bean
    @ConditionalOnMissingBean
    public KafkaTopicInitializer kafkaTopicInitializer(KafkaModuleProperties kafkaModuleProperties, GenericApplicationContext genericApplicationContext) {
        return new KafkaTopicInitializer(kafkaModuleProperties, genericApplicationContext);
    }
}
