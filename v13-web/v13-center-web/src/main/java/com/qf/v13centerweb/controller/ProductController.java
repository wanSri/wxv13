package com.qf.v13centerweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.qf.api.IProductDescService;
import com.qf.api.IProductService;
import com.qf.api.IProductTypeService;
import com.qf.api.SearchService;
import com.qf.common.constant.RabbitMQConst;
import com.qf.common.pojo.ResponseBean;
import com.qf.common.util.HttpClientUtils;
import com.qf.entity.TProduct;

import com.qf.entity.TProductDesc;
import com.qf.entity.TProductType;
import com.qf.pojo.ProductVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author: WangXi
 * @Date: 2019/6/11
 */

@Controller
@RequestMapping("product")
public class ProductController {

    @Reference
    public IProductService productService;

    @Reference
    public IProductDescService productDescService;

    @Reference
    public SearchService searchService;

    @Autowired
    private RabbitTemplate rabbitTemplate;



    @GetMapping("get/{pageIndex}/{pageSize}")
    public String getById(@PathVariable("pageIndex") Integer pageIndex,@PathVariable("pageSize") Integer pageSize,
                            Model model){
        PageInfo<TProduct> page = productService.page(pageIndex, pageSize);
        model.addAttribute("page",page);
        return "product/list";
    }


    @PostMapping("addProduct")
    public String addProduct(ProductVO productVO){
        Long insert = productService.insert(productVO);
        rabbitTemplate.convertAndSend(RabbitMQConst.PRODUCT_EXCHANGE,"solr.add",insert);
        //searchService.updateById(insert);
        HttpClientUtils.doGet("http://localhost:8084/iterm/createHtml/"+insert);
        return "redirect:/product/get/1/5";
    }


    @PostMapping("delete/{id}")
    @ResponseBody
    public ResponseBean deleteById(@PathVariable("id") Long id){
        ResponseBean responseBean=new ResponseBean();
        int i = productService.deleteByPrimaryKey(id);
        if(i>0){
            responseBean.setStatusCode("200");
            responseBean.setData("删除成功！");

        }else{
            responseBean.setStatusCode("404");
            responseBean.setData("删除失败！");
        }
        return responseBean;
    }

    @ResponseBody
    @RequestMapping("batchDelete")
    public ResponseBean batchDelete(Long[] ids){
        ResponseBean responseBean=new ResponseBean();
        int result = productService.batchDelete(ids);
        if(result>0){
            responseBean.setStatusCode("200");
            responseBean.setData("删除成功！");

        }else{
            responseBean.setStatusCode("404");
            responseBean.setData("删除失败！");
        }
        return responseBean;


    }
    @ResponseBody
    @RequestMapping("get/{id}")
    public ProductVO getById(@PathVariable("id") Long id){

        ProductVO productVO=new ProductVO();
        TProduct tProduct = productService.selectByPrimaryKey(id);
        TProductDesc productDesc=productDescService.selectByProductId(id);
        productVO.setProduct(tProduct);
        productVO.setProductDesc(productDesc.getProductDesc());
        return productVO;
    }


    @RequestMapping("updateProduct")
    public String updateProduct(ProductVO productVO){

        TProduct product = productVO.getProduct();
        String productDesc = productVO.getProductDesc();
        TProductDesc productDesc1=new TProductDesc();
        productDesc1.setProductId(product.getId());
        productDesc1.setProductDesc(productDesc);
        productService.updateByPrimaryKey(product);
        productDescService.updateByProductId(productDesc1);
        return "redirect:/product/get/1/5";

    }

}
