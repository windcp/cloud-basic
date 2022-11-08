package com.wind.kafka.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/11/08/14:13
 * @Description:
 */
@Component
public class DemoKafkaBatchSend {

    private static final Logger logger = LoggerFactory.getLogger(DemoKafkaBatchSend.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void demoBatchSend(String normalMessage){
        logger.info("发送数据到kafka  {}",normalMessage);
        kafkaTemplate.send("demo.batch.test",normalMessage);
    }

    public void demoBatchSendCallback(String normalMessage){
        logger.info("发送数据到kafka  {}",normalMessage);
        kafkaTemplate.send("demo.batch.test",normalMessage).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.info("发送数据到kafka失败");
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                logger.info("发送数据到kafka成功");

            }
        });
    }

}
