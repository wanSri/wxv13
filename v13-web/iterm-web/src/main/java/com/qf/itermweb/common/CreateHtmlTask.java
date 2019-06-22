package com.qf.itermweb.common;

import com.qf.api.IProductService;
import com.qf.common.pojo.ResponseBean;
import com.qf.entity.TProduct;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * @author: WangXi
 * @Date: 2019/6/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHtmlTask implements Callable {

    private Long id;
    private IProductService productService;
    private Configuration configuration;


    @Override
    public Object call() throws Exception {
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
}
