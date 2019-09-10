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

    @RabbitListener(queues = {"fanoutQueueA"})
    public void handlerA(String message){
        System.out.println("from fanoutA"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())
                +":"+message);
    }

    @RabbitListener(queues = {"fanoutQueueB"})
    public void handlerB(String message){
        System.out.println("from fanoutB"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())
                +":"+message);
    }

    @RabbitListener(queues = {"topicOrderKaitong"})
    public void handlerTopicA(String message){
        System.out.println("from topicOrderKaitong "+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())
                +":"+message);
    }

    @RabbitListener(queues = {"topicOrdertuike"})
    public void handlerTopicB(String message){
        System.out.println("from topicOrdertuike "+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())
                +":"+message);
    }

    @RabbitListener(queues = {"topicCourseKaitong"})
    public void handlerTopicC(String message){
        System.out.println("from topicCourseKaitong "+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())
                +":"+message);
    }
}
