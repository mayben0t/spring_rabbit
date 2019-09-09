package com.example.rabbit.controller;


import com.example.rabbit.model.User;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
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

    @Autowired
    @Qualifier("deadLetterExchange")
    private Exchange deadLetterExchange;

    @GetMapping("/hi")
    public String test(){
        User user = new User(1,"test",22);

        rabbitTemplate.convertAndSend(directExchange.getName(),"",user);
        return "hi";
    }

    @GetMapping("/hic")
    public String testc(){
        User user = new User(1,"test",22);

        rabbitTemplate.convertAndSend(deadLetterExchange.getName(),"DL_KEY",user);
        return "hi";
    }
}
