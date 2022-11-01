package com.wind.rabbitmq.event;

import com.wind.rabbitmq.quenelistener.demotest.DemoTestReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.listener.ListenerContainerConsumerFailedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/11/01/11:17
 * @Description:  rabbotma 异常事件接受中心
 */
@Component
public class ConsumerFailedEventContext implements ApplicationListener<ListenerContainerConsumerFailedEvent> {

    private static Logger logger = LoggerFactory.getLogger(ConsumerFailedEventContext.class);


    /**
     * @param listenerContainerConsumerFailedEvent
     * AsyncConsumerStartedEvent: 当消费者启动时。
     *
     * AsyncConsumerRestartedEvent:SimpleMessageListenerContainer仅当消费者在失败后重新启动时。
     *
     * AsyncConsumerTerminatedEvent: 当消费者正常停止时。
     *
     * AsyncConsumerStoppedEvent:SimpleMessageListenerContainer仅当消费者停止时。
     *
     * ConsumeOkEvent：consumeOk从代理接收到 a 时，包含队列名称和consumerTag
     *
     * ListenerContainerIdleEvent：请参阅检测空闲异步消费者。
     *
     * MissingQueueEvent：检测到丢失队列时。
     */
    @Override
    public void onApplicationEvent(ListenerContainerConsumerFailedEvent listenerContainerConsumerFailedEvent) {
        logger.warn("rabbotma 异常事件接受中心 event {}" , listenerContainerConsumerFailedEvent);
    }
}
