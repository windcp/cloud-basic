package com.wind.rabbitmq.quenelistener.demotest;

import com.alibaba.fastjson.JSON;
import com.wind.rabbitmq.model.dto.MessageDto;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

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

    public DemoTestReceiver(List<DemoTestListener> demoTestListenerList){
        this.demoTestListenerList.addAll(demoTestListenerList);
    }

    @RabbitHandler
    @RabbitListener(queues = "demo.test" , containerFactory="rabbitListenerContainerFactory")
    public void process(MessageDto message) {
        System.out.println("demoTest消费者收到消息  : " + message.toString());
        demoTestListenerList.forEach(demoTestListener -> {
            demoTestListener.listenResult(message);
        });
    }

}
