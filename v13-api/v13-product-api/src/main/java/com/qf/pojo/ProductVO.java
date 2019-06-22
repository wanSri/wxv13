package com.qf.pojo;

import com.qf.entity.TProduct;
import lombok.Data;

import java.io.Serializable;


/**
 * @author: WangXi
 * @Date: 2019/6/12
 */
@Data
public class ProductVO implements Serializable {

    private TProduct product;

    private String productDesc;


}
