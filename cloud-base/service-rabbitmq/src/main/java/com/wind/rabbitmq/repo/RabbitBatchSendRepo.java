package com.wind.rabbitmq.repo;

import com.wind.rabbitmq.model.dto.MessageDto;
import com.wind.rabbitmq.utils.DESUtil;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/31/11:07
 * @Description:
 */
@Repository
public class RabbitBatchSendRepo {

    @Autowired
    private BatchingRabbitTemplate batchingRabbitTemplate;


    public void sendBatch(String exchangeName, String bingKey, Object objectList){
        List<Object> objects = (List<Object>) objectList;
        Long currentMillis = System.currentTimeMillis();
        objects.forEach(obj ->{
            MessageDto messageDto = new MessageDto();
            messageDto.setData(obj);
            String messageId = DESUtil.generateRandomKey(8);
            messageDto.setMessageId(messageId);
            messageDto.setCreateTime(currentMillis);
            batchingRabbitTemplate.convertAndSend(exchangeName,bingKey,messageDto);
        });
    }

}
