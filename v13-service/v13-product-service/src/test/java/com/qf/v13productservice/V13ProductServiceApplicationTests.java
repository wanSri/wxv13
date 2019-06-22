package com.qf.v13productservice;

import com.qf.entity.TProduct;
import com.qf.entity.TProductType;
import com.qf.mapper.TProductMapper;
import com.qf.mapper.TProductTypeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V13ProductServiceApplicationTests {

    @Autowired
    private TProductMapper productMapper;

    @Autowired
    private TProductTypeMapper productTypeMapper;
    @Test
    public void contextLoads() {

        TProduct tProduct = productMapper.selectByPrimaryKey(1L);
        System.out.println(tProduct.getName());
    }

    @Test
    public void getAllType(){
        List<TProductType> tProductTypes = productTypeMapper.queryAll();
        System.out.println(tProductTypes.size());
    }

}
