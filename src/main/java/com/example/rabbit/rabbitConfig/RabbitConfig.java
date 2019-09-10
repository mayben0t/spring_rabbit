package com.example.rabbit.rabbitConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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

    /**
     * topic model 搞三个队列   com.order.kaitong   com.order.tuike  com.course.kaitong 测试一下
     * com.order.#
     */
    @Bean("topicSimple")
    public TopicExchange topicExchangeGet(){
        return new TopicExchange("topicSimple");
    }

    @Bean
    public Queue topicOrderKaitong(){
        return new Queue("topicOrderKaitong");
    }

    @Bean
    public Queue topicOrdertuike(){
        return new Queue("topicOrdertuike");
    }

    @Bean
    public Queue topicCourseKaitong(){
        return new Queue("topicCourseKaitong");
    }

    @Bean
    Binding bindingTopicA(){
        return BindingBuilder.bind(topicOrderKaitong())
                .to(topicExchangeGet())
                .with("com.order.#");
    }

    @Bean
    Binding bindingTopicB(){
        return BindingBuilder.bind(topicOrdertuike())
                .to(topicExchangeGet())
                .with("com.order.tuike");
    }

    @Bean
    Binding bindingTopicC(){
        return BindingBuilder.bind(topicCourseKaitong())
                .to(topicExchangeGet())
                .with("com.#.kaitong");
    }
}

