package com.qf.registerweb;

import com.qf.common.constant.RabbitMQConst;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * @author: WangXi
 * @Date: 2019/6/21
 */
@Component
public class Consumer{

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromAddr}")
    private String from;

    @RabbitListener(queues = RabbitMQConst.EMAIL_QUEUE)
    @RabbitHandler
    public void process(Map<String,String> map){
        String email=map.get("email");
        String uuid=map.get("uuid");
        String s="<a href='www.baidu.com'>百度</a>";
        MimeMessage message=mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(email);
            helper.setSubject("一腔诗意喂了狗");
            helper.setText("<a href=\"http://localhost:9095/user/check?uuid="+uuid+"\">确认注册</a>",true);
            mailSender.send(message);
            System.out.println("发送邮件成功");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
