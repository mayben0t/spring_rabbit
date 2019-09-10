package com.example.rabbit.worker;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RabbitListener(queues = "directQueue")
public class DirectConsumer {

    @RabbitHandler
    public void handler(String message){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(new Date())
        +":"+message);
    }
}
