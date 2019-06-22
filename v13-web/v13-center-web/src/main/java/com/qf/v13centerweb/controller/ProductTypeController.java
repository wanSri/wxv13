package com.qf.v13centerweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.api.IProductTypeService;

import com.qf.entity.TProductType;
import com.qf.pojo.ProductTypeVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: WangXi
 * @Date: 2019/6/15
 */
@Controller
public class ProductTypeController {

    @Reference
    private IProductTypeService productTypeService;

    @ResponseBody
    @RequestMapping("query")
    public List<ProductTypeVo> queryAll(Long id){
        System.out.println("hello");
        if(id==null){
            id=0L;
        }
        System.out.println(id);
        List<TProductType> list = productTypeService.queryByPid(id);
        List<ProductTypeVo> list1=new ArrayList<>();
        for (TProductType productType : list) {
            ProductTypeVo productTypeVo=new ProductTypeVo();
            productTypeVo.setId(productType.getId());
            productTypeVo.setName(productType.getName());
            productTypeVo.setPid(productType.getPid());
            Long id1 = productType.getId();
            if(productTypeService.queryByPid(id1).size()>0){
                productTypeVo.setIsParent(true);
            }else {
                productTypeVo.setIsParent(false);
            }
            list1.add(productTypeVo);
        }
        return list1;
    }
}
