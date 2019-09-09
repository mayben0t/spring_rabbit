package com.example.rabbit.rabbitConfig;

import com.google.common.collect.Maps;
import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class RabbitConfig {

    @Bean("rabbit_tem")
    public Queue getQueue(){
        return new Queue("rabbit_tem");
    }

    @Bean("testQueuez")
    public Queue getQueue2(){
        return new Queue("testQueuez");
    }


    @Bean("deadLetterExchange")
    public Exchange deadLetterExchangeGet(){
        return ExchangeBuilder.directExchange("deadLetterExchange").durable(true).build();
    }

    @Bean("deadLetterQueue")
    public Queue deadLetterQueueGet(@Qualifier("deadLetterExchange")Exchange exchange){
        Map<String,Object> args = Maps.newHashMap();
        args.put("x-dead-letter-exchange",exchange.getName());
        args.put("x-dead-letter-routing-key","KEY_D");
        return QueueBuilder.durable("deadLetterQueue").withArguments(args).build();
    }

    @Bean
    public Binding deadLetterBinding(){
        return new Binding("deadLetterQueue",Binding.DestinationType.QUEUE,"deadLetterExchange","DL_KEY",null);
    }

    @Bean
    public Binding redirectBinding(){
        return new Binding("testQueuez",Binding.DestinationType.QUEUE,"deadLetterExchange","KEY_D",null);
    }
}
