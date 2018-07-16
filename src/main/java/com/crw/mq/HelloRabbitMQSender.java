package com.crw.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HelloRabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String content) {
        log.info("RabbitMQ send : {}", content);
        this.rabbitTemplate.convertAndSend("hello", content);
    }
}
