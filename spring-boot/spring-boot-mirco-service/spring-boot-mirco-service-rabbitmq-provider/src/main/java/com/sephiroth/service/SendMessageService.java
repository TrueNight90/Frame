package com.sephiroth.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${queueName}")
    private String queueName;
    @Value("${directExchangeName}")
    private String directExchangeName;
    @Value("${routingKey}")
    private String routingKey;

    @GetMapping("/send")
    public void send(String s){
        rabbitTemplate.convertAndSend(directExchangeName,routingKey,s);
    }
}
