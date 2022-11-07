package com.wind.kafka.model.bo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/11/07/11:38
 * @Description:
 */
@ConfigurationProperties(prefix = "spring.kafka")
@Data
@Component
public class KafkaModuleProperties {

    private List<KafkaTopicBo> topics;
}
