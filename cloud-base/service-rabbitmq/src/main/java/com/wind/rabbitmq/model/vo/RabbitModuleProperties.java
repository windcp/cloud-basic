package com.wind.rabbitmq.model.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/26/14:56
 * @Description:
 */
@ConfigurationProperties(prefix = "spring.rabbitmq")
@Data
@Component
public class RabbitModuleProperties {

    private List<RabbitModuleInfo> modules;
}
