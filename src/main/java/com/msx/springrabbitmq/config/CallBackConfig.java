package com.msx.springrabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Program: spring-rabbitmq
 * @description:
 * @author: mengshixuan
 * @create: 2023-05-15 10:59:25
 **/
@Slf4j
@Configuration  //想用这个回调函数还需要在配置文件里面配置
public class CallBackConfig implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnsCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @PostConstruct  //初始化方法
    public void init(){
        //将当前类注入到这个接口里面
        rabbitTemplate.setConfirmCallback(this);
       rabbitTemplate.setReturnsCallback(this);
    }
    /**
     * prameter cor 保存回调消息的ID及其相关信息  ack 交换机是否收到消息返回true/false,  cause   成功为null失败则为失败消息
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause){
        String  id = correlationData != null ?correlationData.getId() :"";
        if (ack){
            log.info("交换机已经收到Id为:{}的信息",id  );
        }else{
            log.info("交换机为接收到ID为：{}的消息，接收消息失败：{}",id,cause);
        }
    }

//    @Override   //回退消息，将不可达到目的地的消息返回给生产者
//    public void returnedMessage(Message message,int replayCode,String replyText,String exchange,String routingKey){
//            log.error("消息{},被交换机{}给回退了，退回原因：{}，路由：{}",
//                            message.getBody(), exchange,replyText,routingKey );
//    }

    @Override
    public void returnedMessage(ReturnedMessage returned){
        log.error("消息{},被交换机{}给回退了，退回原因：{}，路由：{}",
                new String(returned.getMessage().getBody()), returned.getExchange(),returned.getReplyText(),returned.getRoutingKey() );
    }


}
