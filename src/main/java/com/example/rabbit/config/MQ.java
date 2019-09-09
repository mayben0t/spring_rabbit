package com.example.rabbit.config;

//import org.springframework.amqp.rabbit.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQ {


    @Bean("selfConn")
    public ConnectionFactory getConnectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setPort(5672);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate getRabbitTemplate(@Qualifier("selfConn") ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }

    @Bean("testExchange")
    public DirectExchange getTestExchange(){
        DirectExchange d =  new DirectExchange("testExchange");
        return d;
    }

//    @Bean("testQueuez")
//    public Queue getTestQueuez(){
//        return new Queue("testQueuez");
//    }

    @Bean
    Binding bindingExchangeMessage(@Qualifier("testQueuez")Queue queue,@Qualifier("testExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("");
    }
}