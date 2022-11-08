package com.wind.kafka.repo;

import com.wind.kafka.beans.KafkaTopicInitializer;
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
 * @Date: 2022/11/07/15:28
 * @Description:
 */
@Component
public class DemoKafkaSend {

    private static final Logger logger = LoggerFactory.getLogger(DemoKafkaSend.class);


    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void demoSend(String normalMessage){
        logger.info("发送数据到kafka  {}",normalMessage);
        kafkaTemplate.send("demo.test",normalMessage);
    }

    public void demoSendCallback(String normalMessage){
        logger.info("发送数据到kafka  {}",normalMessage);
        kafkaTemplate.send("demo.test",normalMessage).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
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

    public void sendInTransaction(String normalMessage){
        kafkaTemplate.executeInTransaction(operations ->{
            operations.send("demo.test",normalMessage).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    logger.info("发送数据到kafka失败 error{} " , throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                    logger.info("发送数据到kafka成功");
                }
            });
            //throw new RuntimeException("事务异常测试");
            return true;
        });


    }

}
