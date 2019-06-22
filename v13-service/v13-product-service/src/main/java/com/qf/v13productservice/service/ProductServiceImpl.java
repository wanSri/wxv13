package com.qf.v13productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.api.IProductService;
import com.qf.common.base.BaseServiceImpl;
import com.qf.common.base.IBaseDao;
import com.qf.entity.TProduct;
import com.qf.entity.TProductDesc;
import com.qf.mapper.TProductDescMapper;
import com.qf.mapper.TProductMapper;
import com.qf.pojo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: WangXi
 * @Date: 2019/6/11
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<TProduct> implements IProductService {

    @Autowired
    private TProductMapper productMapper;
    @Autowired
    private TProductDescMapper productDescMapper;
    @Override
    public IBaseDao<TProduct> getBaseDao() {
        return productMapper;
    }


    @Override
    public PageInfo<TProduct> page(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<TProduct> products = productMapper.queryAll();
        PageInfo<TProduct> pageInfo=new PageInfo<TProduct>(products,5);
        return pageInfo;
    }

    @Transactional
    @Override
    public Long insert(ProductVO productVO) {
        TProduct product=productVO.getProduct();
        product.setFlag(true);
        productMapper.insert(product);
        String productDesc = productVO.getProductDesc();

        TProductDesc tproductDesc=new TProductDesc();
        tproductDesc.setProductId(product.getId());
        tproductDesc.setProductDesc(productDesc);
        productDescMapper.insert(tproductDesc);

        return product.getId();
    }

    @Override
    public int batchDelete(Long[] ids) {
         return productMapper.updateFlagByIdIn(false,ids);
    }


    @Override
    public int deleteByPrimaryKey(Long id) {
        TProduct tProduct=new TProduct();
        tProduct.setId(id);
        tProduct.setFlag(false);
        int result = productMapper.updateByPrimaryKeySelective(tProduct);
        return result;
    }


}
