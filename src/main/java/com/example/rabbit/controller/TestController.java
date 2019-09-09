package com.example.rabbit.controller;


import com.example.rabbit.model.User;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    @Qualifier("testExchange")
    private DirectExchange directExchange;

    @GetMapping("/hi")
    public String test(){
        User user = new User(1,"test",22);

        rabbitTemplate.convertAndSend(directExchange.getName(),"",user);
        return "hi";
    }
}
