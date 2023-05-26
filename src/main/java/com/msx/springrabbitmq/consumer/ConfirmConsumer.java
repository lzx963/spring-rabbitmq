package com.msx.springrabbitmq.consumer;

import com.msx.springrabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Program: spring-rabbitmq
 * @description:
 * @author: mengshixuan
 * @create: 2023-05-15 10:46:29
 **/
@Slf4j
@Component
public class ConfirmConsumer {
    @RabbitListener(queues = ConfirmConfig.CONFIRM_QUEUE_NAME)
    public void receiveConfirmMessage(Message message){
        log.info("接收队列{}消息的内容:{}",message.getMessageProperties().getConsumerQueue(),new String(message.getBody()) );
    }
}
