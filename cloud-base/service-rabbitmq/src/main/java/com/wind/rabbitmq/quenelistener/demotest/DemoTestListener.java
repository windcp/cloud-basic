package com.wind.rabbitmq.quenelistener.demotest;

import com.wind.rabbitmq.model.dto.MessageDto;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/27/11:41
 * @Description:
 */
public interface DemoTestListener {

    void listenResult(MessageDto messageDto);

}
