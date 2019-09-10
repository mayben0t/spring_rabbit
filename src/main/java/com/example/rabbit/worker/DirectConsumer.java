package com.example.rabbit.worker;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DirectConsumer {

    @RabbitListener(queues = {"directQueue"})
    public void handler(String message){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date())
        +":"+message);
    }
}
