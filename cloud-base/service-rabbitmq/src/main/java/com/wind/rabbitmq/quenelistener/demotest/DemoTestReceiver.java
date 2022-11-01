package com.wind.rabbitmq.quenelistener.demotest;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.wind.rabbitmq.model.dto.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/27/11:40
 * @Description:
 */
@Component
public class DemoTestReceiver {

    private static final List<DemoTestListener> demoTestListenerList = new ArrayList<>();

    private static Logger logger = LoggerFactory.getLogger(DemoTestReceiver.class);

    public DemoTestReceiver(List<DemoTestListener> demoTestListenerList){
        this.demoTestListenerList.addAll(demoTestListenerList);
    }


    @RabbitHandler
    @RabbitListener(queues = "demo.test"
            , containerFactory="myRabbitListenerContainerFactory"
            //, replyContentType = "application/json" //回复消息的消息体类型
            //, converterWinsContentType = "false" //设置向下兼容 使SimpleMessageConverter 设置有效不会被 replyContentType 覆盖
    )
    //@SendTo("demo.relpy")  // https://www.cnblogs.com/geekdc/p/13604995.html 返回消息到指定队列,这样生产者可以知道消息是否被消费 demo.relpy
    public void processMessage(@Payload MessageDto messageDto, @Headers Map<String,Object> headers,
                               @Header(AmqpHeaders.CONSUMER_QUEUE) String queue, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        logger.info("接收到消费消息 messageDto {}" ,messageDto);
        logger.info("接收到消费消息 headers {}" ,headers);
        //手动确认
        channel.basicAck(deliveryTag,false);
        ////异常，ture 重新入队,或者false,进入死信队列
        //channel.basicNack(deliveryTag,false,true);
        demoTestListenerList.forEach(demoTestListener -> {
            demoTestListener.listenResult(messageDto);
        });
    }

    @RabbitHandler
    @RabbitListener(queues = "batch.test"
            , containerFactory="myRabbitListenerContainerFactory")
    public void processBatchMessage(List<MessageDto> messageDtoList
                                ,Channel channel) throws IOException {
        logger.info("接收到消费消息 messageDtoList {}" ,messageDtoList);

        //手动确认
        ////异常，ture 重新入队,或者false,进入死信队列
        //channel.basicNack(deliveryTag,false,true );
//        demoTestListenerList.forEach(demoTestListener -> {
//            demoTestListener.listenResult(messageDto);
//        });
    }




}
