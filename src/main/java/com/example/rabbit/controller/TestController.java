package com.example.rabbit.controller;


import com.example.rabbit.model.User;
import org.checkerframework.checker.units.qual.A;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    @Qualifier("directSimple")
    private DirectExchange directExchange;
    @Autowired
    @Qualifier("fanoutSimple")
    private FanoutExchange fanoutExchange;
    @Autowired
    @Qualifier("topicSimple")
    private TopicExchange topicExchange;

    @GetMapping("/direct")
    public String directTest(){
        rabbitTemplate.convertAndSend(directExchange.getName(),"123","direct交换器の测试");
        return "success";
    }

    @GetMapping("/fanout")
    public String fanoutTest(){
        rabbitTemplate.convertAndSend(fanoutExchange.getName(),"其实也没用","fanout交换器の测试");
        return "ooook";
    }

    @GetMapping("/topic")
    public String topicToOrder(@RequestParam(value = "on",defaultValue = "1")Integer on){
        if(on.equals(1)) {
            rabbitTemplate.convertAndSend(topicExchange.getName(), "com.order.tuike", "topic交换器到order的の测试");
        }else {
            rabbitTemplate.convertAndSend(topicExchange.getName(), "com.course.kaitong", "topic交换器到开通の测试");
        }
        return "zz";
    }
}
