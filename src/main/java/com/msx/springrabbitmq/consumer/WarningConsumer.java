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
 * @create: 2023-05-15 17:00:07
 **/
@Slf4j
@Component
public class WarningConsumer {

    //接收报警信息
    @RabbitListener(queues = ConfirmConfig.WARNING_QUEUE_NAME)
    public  void receiveWarningMessage(Message message){
        String msg = new String(message.getBody());
        log.info("警告：发现不可路由信息{}",msg );
    }
}
