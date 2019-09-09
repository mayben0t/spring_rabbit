package com.example.rabbit.rabbitConfig;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean("rabbit_tem")
    public Queue getQueue(){
        return new Queue("rabbit_tem");
    }

    @Bean("testQueuez")
    public Queue getQueue2(){
        return new Queue("testQueuez");
    }
}
