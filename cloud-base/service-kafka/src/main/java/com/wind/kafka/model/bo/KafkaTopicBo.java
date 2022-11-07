package com.wind.kafka.model.bo;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/11/07/11:31
 * @Description:
 */
@Data
public class KafkaTopicBo {

    /**
     *  topic名称
     */
    private String name;

    /**
     *  分区数
     */
    private  Integer numPartitions ;

    /**
     *  分区副本数
     */
    private Short replicationFactor;

}
