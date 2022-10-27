package com.wind.serviceproduct.service.demo.rabbitmq.consume;

import com.wind.rabbitmq.model.dto.MessageDto;
import com.wind.rabbitmq.quenelistener.demotest.DemoTestListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/27/14:28
 * @Description:
 */
@Component
public class DemoListener implements DemoTestListener {

    @Override
    public void listenResult(MessageDto messageDto) {
        System.out.println("广播接受结果："+ messageDto.toString());
    }
}
