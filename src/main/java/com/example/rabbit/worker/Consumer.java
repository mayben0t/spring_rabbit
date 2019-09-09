//package com.example.rabbit.worker;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@RabbitListener(queues = "rabbit_tem")
//public class Consumer {
//
//    @RabbitHandler
//    public void process(String msg){
//        System.out.println("recv"+msg );
//    }
//}
