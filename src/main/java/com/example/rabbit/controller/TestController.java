package com.example.rabbit.controller;


import com.example.rabbit.model.User;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
//    @Autowired
//    @Qualifier("testExchange")
//    private DirectExchange directExchange;

//    @Autowired
//    @Qualifier("deadLetterExchange")
//    private Exchange deadLetterExchange;

//    @GetMapping("/hi")
//    public String test(){
//        User user = new User(1,"test",22);
//
//        rabbitTemplate.convertAndSend(directExchange.getName(),"",user);
//        return "hi";
//    }

//    @GetMapping("/hic")
//    public String testc(){
//        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
//        MessagePostProcessor messagePostProcessor = message -> {
//            MessageProperties messageProperties = message.getMessageProperties();
//            messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
//            messageProperties.setContentEncoding("utf-8");
//            messageProperties.setExpiration("1000");
//            return message;
//        };
//
//
//        User user = new User(1,"test",22);
//
//        rabbitTemplate.convertAndSend(deadLetterExchange.getName(),"KEY_D"," 我们抬头看天空，星星还亮着几颗",messagePostProcessor);
//        return "hi";
//    }
    @Value("${sunspring.order.exchange}")
    private String orderExchange;

    @Value("${sunspring.order.queue}")
    private String orderQueue;

    @Value("${sunspring.order.routingKey}")
    private String orderRoutingKey;

    /**
     * 发送带有过期时间的消息
     */
    @GetMapping("/sendDlx")
    public void sendDlx() {

        rabbitTemplate.convertAndSend(orderExchange, orderRoutingKey,
                "wcnm", message -> {
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    message.getMessageProperties().setExpiration("10000");
                    return message;
                });
    }
}
