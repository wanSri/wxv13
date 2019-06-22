package com.qf.indexweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.api.IProductTypeService;
import com.qf.entity.TProductType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author: WangXi
 * @Date: 2019/6/14
 */
@Controller
@RequestMapping("index")
public class ProductTypeController {

    @Reference
    private IProductTypeService productTypeService;

    @RequestMapping("allType")
    public String getAllType(Model model){
        List<TProductType> list = productTypeService.queryAll();
        model.addAttribute("list",list);
        return "home";
    }
}
