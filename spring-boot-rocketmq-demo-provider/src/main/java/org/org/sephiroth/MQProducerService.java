package org.org.sephiroth;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class MQProducerService {

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @RequestMapping("send")
    public void send(String s){
        rocketMQTemplate.convertAndSend("TopicTest",s);
    }

    public static void main(String[] args) {
        SpringApplication.run(MQProducerService.class);
    }
}
