package com.qf.v13productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.api.IProductDescService;
import com.qf.common.base.BaseServiceImpl;
import com.qf.common.base.IBaseDao;
import com.qf.entity.TProductDesc;
import com.qf.mapper.TProductDescMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: WangXi
 * @Date: 2019/6/15
 */
@Service
public class ProductDescServiceImpl extends BaseServiceImpl<TProductDesc> implements IProductDescService {

    @Autowired
    private TProductDescMapper productDescMapper;
    @Override
    public IBaseDao<TProductDesc> getBaseDao() {
        return productDescMapper;
    }

    @Override
    public TProductDesc selectByProductId(Long productId) {
        return productDescMapper.selectByProductId(productId);
    }

    @Override
    public int updateByProductId(TProductDesc record) {
        return productDescMapper.updateByProductId(record);
    }
}
