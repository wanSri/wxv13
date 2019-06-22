package com.qf.v13centerweb.conf;

import com.qf.common.constant.RabbitMQConst;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: WangXi
 * @Date: 2019/6/20
 */
@Configuration
public class MQConfig {


    @Bean
    public TopicExchange getExchange(){

        return new TopicExchange(RabbitMQConst.PRODUCT_EXCHANGE);
    }


}
