package com.lihao.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void send() {

        rabbitTemplate.convertAndSend("DIRECT_EXCHANGE", "lihao.best", "a direct msg");
        rabbitTemplate.convertAndSend("TOPIC_EXCHANGE", "shanghai.lihao.teacher", "a direct msg shanghai");
        rabbitTemplate.convertAndSend("TOPIC_EXCHANGE", "wuhan.lihao.student", "adirect msg wuhan");

        rabbitTemplate.convertAndSend("FANOUT_EXCHANGE", "","a fanout msg");
    }

}
