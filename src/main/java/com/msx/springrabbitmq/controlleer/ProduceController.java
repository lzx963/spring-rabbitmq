package com.msx.springrabbitmq.controlleer;

import com.msx.springrabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发布确认
 **/
@Slf4j
@RestController
@RequestMapping("/produce")
public class ProduceController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //发消息
    @RequestMapping("/sendMsg/{msg}")
    public  void sendMessage(@PathVariable String msg){
        CorrelationData correlationData = new CorrelationData("1");

        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME,
                                        ConfirmConfig.CONFIRM_ROUTING_KEY,
                                        msg,correlationData);
        log.info("发送消息内容:{}",msg);
        CorrelationData correlationData2 = new CorrelationData("2");

          rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME,
                ConfirmConfig.CONFIRM_ROUTING_KEY+"2",
                msg+"key2",correlationData2);
        log.info("发送消息内容:{}",msg);
    }
}
