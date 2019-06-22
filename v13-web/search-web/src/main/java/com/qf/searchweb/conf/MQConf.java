package com.qf.searchweb.conf;

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
public class MQConf {


    @Bean
    public Queue getQueueOne(){

        return new Queue(RabbitMQConst.SOLR_ADD_QUEUE);
    }


    @Bean
    public TopicExchange getExchange(){

        return new TopicExchange(RabbitMQConst.PRODUCT_EXCHANGE);
    }

    //将队列和交换机进行绑定
    @Bean
    public Binding bindingExchange1(Queue queue,TopicExchange topicExchange){
        return BindingBuilder.bind(getQueueOne()).to(getExchange()).with("solr.*");
    }


}
