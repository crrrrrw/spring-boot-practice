package com.crw.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageQueueConfig {

    @Bean
    public org.springframework.amqp.core.Queue helloQueueByRabbitMQ() {
        return new org.springframework.amqp.core.Queue("hello");
    }

}