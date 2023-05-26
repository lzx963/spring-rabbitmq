package com.msx.springrabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


import java.io.IOException;
import com.rabbitmq.client.Channel;
import java.util.Date;

/**
 * @Program: spring-rabbitmq
 * @description:
 * @author: mengshixuan
 * @create: 2023-05-13 16:28:09
 **/
@Slf4j
@Component
public class DeadletterQueueConsumer {
    @RabbitListener(queues = {"QD"} )
    public void receiveD(Message message, Channel channel) throws IOException{
        String msg = new String(message.getBody());
        log.info("当前时间：{},收到死信队列信息{}", new Date().toString(), msg);
    }
}
