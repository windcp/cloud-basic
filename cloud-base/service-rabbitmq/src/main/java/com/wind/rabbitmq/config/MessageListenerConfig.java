package com.wind.rabbitmq.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Address;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.SendRetryContextAccessor;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/27/17:14
 * @Description: 消息接受配置
 */
@Configuration
@EnableRabbit
public class MessageListenerConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;


    @Bean
    public SimpleRabbitListenerContainerFactory myRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
//        factory.setConcurrentConsumers(3);
//        factory.setMaxConcurrentConsumers(10);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        factory.setTaskExecutor();
        factory.setContainerCustomizer(container -> {
             simpleMessageListenerContainer(container);
        });

        //事务
        //factory.setTransactionManager();

        //factory.setConsumerTagStrategy(consumerTagStrategy());
        factory.setBatchListener(true); // configures a BatchMessageListenerAdapter
        //factory.setBatchSize(2); 未生效
        //factory.setConsumerBatchEnabled(true); //在消费者中启用离散消息的批处理

        //回复消息之前对回复消息进行代理修改
//        factory.setBeforeSendReplyPostProcessors(msg -> {
//            msg.getMessageProperties().setHeader("calledBean",
//                    msg.getMessageProperties().getTargetBean().getClass().getSimpleName());
//            msg.getMessageProperties().setHeader("calledMethod",
//                    msg.getMessageProperties().getTargetMethod().getName());
//            return msg;
//        });



        //factory.setRetryTemplate(retryTemplate);

//        factory.setReplyRecoveryCallback(ctx -> {
//            Message failed = SendRetryContextAccessor.getMessage(ctx);
//            Address replyTo = SendRetryContextAccessor.getAddress(ctx);
//            Throwable t = ctx.getLastThrowable();
//
//            return null;
//        });

        return factory;
    }


    //@Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(SimpleMessageListenerContainer container ) {
        //SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(3);
        container.setMaxConcurrentConsumers(10);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // RabbitMQ默认是自动确认，这里改为手动确认消息
        //设置一个队列
        //container.setQueueNames("batch.test");
        //如果同时设置多个如下： 前提是队列都是必须已经创建存在的
        //  container.setQueueNames("TestDirectQueue","TestDirectQueue2","TestDirectQueue3");
        //另一种设置队列的方法,如果使用这种情况,那么要设置多个,就使用addQueues
        //container.setQueues(new Queue("TestDirectQueue",true));
        //container.addQueues(new Queue("TestDirectQueue2",true));
        //container.addQueues(new Queue("TestDirectQueue3",true));
        //设置异步消费类  ChannelAwareMessageListener 实现类  ChannelAwareBatchMessageListener 实现类（批量）
        //container.setMessageListener(demoAckListener);

        //生成消费者标签的策略 ConsumerTagStrategy 实现类
        //container.setConsumerTagStrategy();

        //设置是否重回队列,使用rabbitmq自动重试并设置重试次数必须设置该属性，否则发生异常会一直重回队列，一直重试，直到成功为止
        //container.setDefaultRequeueRejected(false);

        //异常处理器 ErrorHandler 接口实现
        //container.setErrorHandler();



        return container;
    }


}
