package com.wind.rabbitmq.repo;

import com.wind.rabbitmq.model.dto.MessageDto;
import com.wind.rabbitmq.utils.DESUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/26/14:31
 * @Description:
 */
@Repository
public class RabbitSendRepo {

    private static Logger logger = LoggerFactory.getLogger(RabbitSendRepo.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String send(String exchangeName, String bingKey, Object obj){
        MessageDto messageDto = new MessageDto();
        messageDto.setData(obj);
        String messageId = DESUtil.generateRandomKey(8);
        messageDto.setMessageId(messageId);
        messageDto.setCreateTime(System.currentTimeMillis());
//        rabbitTemplate.convertAndSend(exchangeName,bingKey,messageDto);

        //异步确认是否消息被接收
        CorrelationData cd1 = new CorrelationData();
        cd1.setId(messageId);
        //如有必要可以使用messagePostProcessor 添加ttl 等属性
        rabbitTemplate.convertAndSend(exchangeName,bingKey,messageDto,cd1);

        try {
            if(cd1.getFuture().get(10, TimeUnit.SECONDS).isAck()){
                logger.info("消息成功投递");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return messageDto.getMessageId();
    }


    @Deprecated
    public String sendTransction(String exchangeName, String bingKey, Object obj){
        rabbitTemplate.setChannelTransacted(true);
        MessageDto messageDto = new MessageDto();
        messageDto.setData(obj);
        String messageId = DESUtil.generateRandomKey(8);
        messageDto.setMessageId(messageId);
        messageDto.setCreateTime(System.currentTimeMillis());
        //如有必要可以使用messagePostProcessor 添加ttl 等属性
        rabbitTemplate.convertAndSend(exchangeName,bingKey,messageDto);
        throw new RuntimeException("事务异常测试");
        //return messageDto.getMessageId();
    }
}
