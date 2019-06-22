package com.qf.registerweb.config;

import com.qf.common.constant.RabbitMQConst;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: WangXi
 * @Date: 2019/6/21
 */
@Configuration
public class RabbitMQConf {

    @Bean
    public TopicExchange getTopExchange(){
        return new TopicExchange(RabbitMQConst.EMAIL_EXCHANGE);
    }

    @Bean
    public Queue getQueue(){
        return new Queue(RabbitMQConst.EMAIL_QUEUE);
    }

    @Bean
    public Binding bind(Queue queue,TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("email.*");
    }
}
