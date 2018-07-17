package com.crw.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HelloActiveMQSender {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void send(String content) {
        log.info("ActiveMQ send : {}", content);
        this.jmsMessagingTemplate.convertAndSend("hello", content);
    }
}
