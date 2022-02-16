package com.sephiroth.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue")
public class ReceiverService {

    @RabbitHandler
    public void process(String msg){
        System.out.println(msg);
    }
}
