package com.crw.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableJms
public class HelloActiveMQConsumer {

    @JmsListener(destination = "hello")
    public void process(String hello) {
        log.info("ActiveMQ receiver : {}", hello);
    }
}
