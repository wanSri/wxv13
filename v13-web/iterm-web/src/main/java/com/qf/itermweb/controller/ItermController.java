package com.qf.itermweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.api.IProductService;
import com.qf.common.pojo.ResponseBean;
import com.qf.entity.TProduct;
import com.qf.itermweb.common.CreateHtmlTask;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author: WangXi
 * @Date: 2019/6/18
 */
@Controller
@RequestMapping("iterm")
public class ItermController {

    @Autowired
    private Configuration configuration;

    @Reference
    private IProductService productService;

    @ResponseBody
    @RequestMapping("test")
    public ResponseBean createHTML(){

        //1.获取模板对象
        try {
            Template template = configuration.getTemplate("hello.ftl");

            //2.设置模板数据
            Map<String,Object> map=new HashMap<>();
            map.put("username","word");

            //3.模板+数据，生成静态页面
            FileWriter writer = new FileWriter("F:\\workspace\\intelljWorkspace\\wxv13\\v13-web\\iterm-web\\src\\main\\resources" +
                    "\\templates\\1.html");

            template.process(map,writer);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseBean("400","获取模板对象失败");
        } catch (TemplateException e) {
            e.printStackTrace();
            return new ResponseBean("400","生成静态页面失败");
        }
        return new ResponseBean("200","生成静态页面成功");
    }

    @ResponseBody
    @RequestMapping("createHtml/{id}")
    public ResponseBean createHtml(@PathVariable Long id){

        return createHTMLById(id);
    }

    private ResponseBean createHTMLById(Long id) {
        TProduct product = productService.selectByPrimaryKey(id);
        HashMap<String,Object> map= new HashMap<>();
        map.put("product",product);

        //获取模板
        try {
            Template template = configuration.getTemplate("iterm.html");

            //设置静态页面存放位置
            String path = ResourceUtils.getURL("classpath:static").getPath();
            System.out.println(path);
            FileWriter writer = new FileWriter(path + File.separator + id + ".html");

            template.process(map,writer);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseBean("400","获取模板对象失败");
        } catch (TemplateException e) {
            e.printStackTrace();
            return new ResponseBean("400","生成静态页面失败");
        }
        return new ResponseBean("200","生成静态页面成功");
    }


    @ResponseBody
    @RequestMapping("batchCreate")
    public ResponseBean batchCreateHtml(@RequestBody List<Long> ids){

        //获取cpu的核心
        int i = Runtime.getRuntime().availableProcessors();

        //创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i, 2 * i, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));

        ArrayList<Future> list=new ArrayList<>(ids.size());
        for (Long id : ids) {
            list.add(threadPoolExecutor.submit(new CreateHtmlTask(id, productService, configuration)));
        }
        for (Future future : list) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return new ResponseBean("200","生成静态页成功");
    }
}
