package com.wind.rabbitmq.config;


import com.wind.rabbitmq.beans.RabbitModuleInitializer;
import com.wind.rabbitmq.model.vo.RabbitModuleProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.batch.BatchingStrategy;
import org.springframework.amqp.rabbit.batch.SimpleBatchingStrategy;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: v_vwlkunli
 * @Date: 2022/10/26/14:18
 * @Description: 消息发送配置
 */
@Configuration
public class RabbitmqConfig {


    private static Logger logger = LoggerFactory.getLogger(RabbitmqConfig.class);

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        //消息被交换机接收会被触发
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
                System.out.println("ConfirmCallback:     "+"确认情况："+ack);
                System.out.println("ConfirmCallback:     "+"原因："+cause);
            }
        });

        //消息未路由到队列触发
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                logger.info("ReturnedMessage {}",returnedMessage);
            }
        });


        return rabbitTemplate;
    }

    @Bean("batchQueueTaskScheduler")
    public TaskScheduler batchQueueTaskScheduler(){
        TaskScheduler taskScheduler=new ThreadPoolTaskScheduler();
        return taskScheduler;
    }

    @Bean
    public BatchingRabbitTemplate batchingRabbitTemplate(ConnectionFactory connectionFactory, @Qualifier("batchQueueTaskScheduler") TaskScheduler taskScheduler){
        //一次批量的数量
        int batchSize=10;
        // 缓存大小限制,单位字节，
        // simpleBatchingStrategy的策略，是判断message数量是否超过batchSize限制或者message的大小是否超过缓存限制，
        // 缓存限制，主要用于限制"组装后的一条消息的大小"
        // 如果主要通过数量来做批量("打包"成一条消息), 缓存设置大点
        // 详细逻辑请看simpleBatchingStrategy#addToBatch()
        int bufferLimit=1024; //1 K
        long timeout=3000;

        //注意，该策略只支持一个exchange/routingKey
        //A simple batching strategy that supports only one exchange/routingKey
        BatchingStrategy batchingStrategy=new SimpleBatchingStrategy(batchSize,bufferLimit,timeout);
        BatchingRabbitTemplate batchingRabbitTemplate =new BatchingRabbitTemplate(connectionFactory,batchingStrategy,taskScheduler);
        batchingRabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());


        return batchingRabbitTemplate;
    }

//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        return factory;
//    }

    /**
     * 动态创建队列、交换机初始化器
     */
    @Bean
    @ConditionalOnMissingBean
    public RabbitModuleInitializer rabbitModuleInitializer(AmqpAdmin amqpAdmin, RabbitModuleProperties rabbitModuleProperties) {
        return new RabbitModuleInitializer(amqpAdmin, rabbitModuleProperties);
    }


//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
//        connectionFactory.setUsername(username);
//        connectionFactory.setPassword(password);
//        connectionFactory.setVirtualHost("/");
//        //connectionFactory.setPublisherConfirms(true);
//        return connectionFactory;
//    }
//
//
//    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    //必须是prototype类型
//    public RabbitTemplate rabbitTemplate() {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory());
//        return template;
//    }

}