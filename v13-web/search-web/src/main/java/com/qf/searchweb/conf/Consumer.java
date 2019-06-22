package com.qf.searchweb.conf;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.api.SearchService;
import com.qf.common.constant.RabbitMQConst;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: WangXi
 * @Date: 2019/6/20
 */
@Component
public class Consumer {

    @Reference
    private SearchService searchService;

    @RabbitHandler
    @RabbitListener(queues = RabbitMQConst.SOLR_ADD_QUEUE)
    public void process(Long id){
        System.out.println(id);
        searchService.updateById(id);
    }
}
