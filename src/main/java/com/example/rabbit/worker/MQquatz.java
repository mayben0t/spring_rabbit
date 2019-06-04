package com.example.rabbit.worker;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
//@Configurable
//@EnableScheduling
public class MQquatz {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Queue queue;

    @Scheduled(cron = "0/5 * * * * ?")
    public void sendMsg(){
        String s = new Date().toString();
//        System.out.println(queue.getName());
//        System.out.println("zzz");
        rabbitTemplate.convertAndSend("rabbit_tem",s);
    }
}
