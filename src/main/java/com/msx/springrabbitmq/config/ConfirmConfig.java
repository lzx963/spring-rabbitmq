package com.msx.springrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Program: spring-rabbitmq
 * @description:
 * @author: mengshixuan
 * @create: 2023-05-15 10:25:11
 **/
@Configuration
public class ConfirmConfig {

    //配置交换机
    public static final String CONFIRM_EXCHANGE_NAME = "confirm_exchange";
    //配置队列
    public static final String CONFIRM_QUEUE_NAME = "confirm_queue";
    //绑定Routingkey
    public static final String CONFIRM_ROUTING_KEY = "key1";
    //备份交换机
    public static final String BACKUP_EXCHANGE_NAME = "backup_exchange";
    //备份队列
    public static final String BACKUP_QUEUE_NAME = "backup_queue";
    //报警队列
    public static final String WARNING_QUEUE_NAME = "warning_queue";

    //声明交换机
    @Bean("confirmExchange")
    public DirectExchange  getDirectExchange(){
        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE_NAME).durable(true)
                .withArgument("alternate-exchange",BACKUP_EXCHANGE_NAME).build();
    }
    //声明队列
    @Bean("confirmQueue")
    public Queue confirmQueue(){
        return QueueBuilder.durable(CONFIRM_QUEUE_NAME).build();
    }

    //绑定
    @Bean
    public Binding queueBindingExchange(@Qualifier("confirmExchange")  DirectExchange confirmExchange,
                                            @Qualifier("confirmQueue") Queue confirmQueue){
        return BindingBuilder.bind(confirmQueue).to(confirmExchange).with(CONFIRM_ROUTING_KEY);
    }
    //--------------------------------------------------------------------
    //备份交换机声明
    @Bean("backupExchange")
    public FanoutExchange backupExchange(){
        return new FanoutExchange(BACKUP_EXCHANGE_NAME);
    }
    //备份队列声明
    @Bean("backupQueue")
    public Queue backupQueue(){
        return QueueBuilder.durable(BACKUP_QUEUE_NAME).build();
    }
    //警告队列声明
    @Bean("warningQueue")
    public Queue warningQueue(){
        return QueueBuilder.durable(WARNING_QUEUE_NAME).build();
    }
    //绑定
    @Bean
    public Binding backupQueueBindingBackupExchange(@Qualifier("backupQueue") Queue backupQueue,
                                      @Qualifier("backupExchange") FanoutExchange backupExchange){
        return BindingBuilder.bind(backupQueue).to(backupExchange);
    }
    //绑定
    @Bean
    public Binding warningQueueBindingBackupExchange(@Qualifier("warningQueue") Queue warningQueue,
                                                    @Qualifier("backupExchange") FanoutExchange backupExchange){
        return BindingBuilder.bind(warningQueue).to(backupExchange);
    }
}
