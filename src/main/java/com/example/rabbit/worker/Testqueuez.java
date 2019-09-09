package com.example.rabbit.worker;

import com.example.rabbit.model.User;
import com.example.rabbit.model.UserDTO;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Testqueuez {


//    @RabbitListener(queues = {"testQueuez"})
//    public void redirect(Message message, Channel channel)throws Exception{
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
//        System.out.println(">>>>>>> "+new String(message.getBody()));
//    }

    @RabbitListener(queues = "sunspring.dlx.queue")
    public void dlxListener(Message message,Channel channel) throws IOException {
        System.out.println(new String(message.getBody()));

        //对消息进行业务处理....
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

}
