package com.wind.rabbitmq.service;

import com.wind.rabbitmq.model.dto.MessageDto;
import com.wind.rabbitmq.utils.DESUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/26/14:31
 * @Description:
 */
@Service
public class RabbitSendService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String send(String exchangeName, String bingKey, Object obj){
        MessageDto messageDto = new MessageDto();
        messageDto.setData(obj);
        String messageId = DESUtil.generateRandomKey(8);
        messageDto.setMessageId(messageId);
        messageDto.setCreateTime(System.currentTimeMillis());
        rabbitTemplate.convertAndSend(exchangeName,bingKey,messageDto);
        return messageDto.getMessageId();
    }

}
