package com.example.rabbit.worker;

import com.example.rabbit.model.User;
import com.example.rabbit.model.UserDTO;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "testQueuez")
public class Testqueuez {

    @RabbitHandler
    public void test(User user){
        System.out.println(">>>>>>>>>>>>>>>   "+user);
    }
}
