package com.example.rabbit.worker;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "testQueuez")
public class Testqueuez {

    @RabbitHandler
    public void test(String asd){
        System.out.println(">>>>>>>>>>>>>>>   "+asd);
    }
}
