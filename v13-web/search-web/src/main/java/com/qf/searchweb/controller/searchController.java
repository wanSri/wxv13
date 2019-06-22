package com.qf.searchweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.api.SearchService;
import com.qf.common.pojo.ResponseBean;
import com.qf.entity.TProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * @author: WangXi
 * @Date: 2019/6/17
 */
@Controller
public class searchController {

    @Reference
    private SearchService searchService;

    @RequestMapping("searchByKeywords")
    public String searchByKeywords(String keywords, Model model){
        ResponseBean responseBean = searchService.searchByKeywords(keywords);

        model.addAttribute("data",responseBean);
        return "list";
    }
}
