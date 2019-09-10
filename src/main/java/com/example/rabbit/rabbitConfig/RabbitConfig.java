package com.example.rabbit.rabbitConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {


    @Bean("directSimple")
    public DirectExchange directExchangeGet(){
        return new DirectExchange("directSimple");
    }

    @Bean("directQueue")
    public Queue directQueueGet(){
        return new Queue("directQueue");
    }

    @Bean
    Binding binding(){
        return BindingBuilder.bind(directQueueGet())
                .to(directExchangeGet())
                .with("123");
    }


    /**
     * fanout_model
     */
    @Bean("fanoutSimple")
    public FanoutExchange fanoutExchangeGet(){
        return new FanoutExchange("fanoutSimple");
    }

    @Bean("fanoutQueueA")
    public Queue fanoutQueueA(){
        return new Queue("fanoutQueueA");
    }

    @Bean("fanoutQueueB")
    public Queue fanoutQueueB(){
        return new Queue("fanoutQueueB");
    }

    @Bean
    public Binding bindingFanout(){
        return BindingBuilder.bind(fanoutQueueA())
                .to(fanoutExchangeGet());
    }

    @Bean
    public Binding bindingFanoutB(){
        return BindingBuilder.bind(fanoutQueueB())
                .to(fanoutExchangeGet());
    }
}

