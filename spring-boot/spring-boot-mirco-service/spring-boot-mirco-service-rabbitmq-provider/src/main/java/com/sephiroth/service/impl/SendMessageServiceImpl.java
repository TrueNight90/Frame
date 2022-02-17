package com.sephiroth.service.impl;

import com.sephiroth.service.api.ISendMessageService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@DubboService
public class SendMessageServiceImpl implements ISendMessageService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${queueName}")
    private String queueName;
    @Value("${directExchangeName}")
    private String directExchangeName;
    @Value("${routingKey}")
    private String routingKey;

    @Override
    public void send(Object o){
        rabbitTemplate.convertAndSend(directExchangeName,routingKey,o);
    }
}
