package com.crw.conf;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnExpression("${spring.activemq.enabled:false}")
@Configuration
public class MessageQueueConfig {

    @Bean
    public org.springframework.amqp.core.Queue helloQueueByRabbitMQ() {
        return new org.springframework.amqp.core.Queue("hello");
    }

    @Bean
    public javax.jms.Queue helloQueueByActiveMQ() {
        return new ActiveMQQueue("hello");
    }
}