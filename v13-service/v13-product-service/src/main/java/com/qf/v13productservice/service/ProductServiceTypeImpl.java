package com.qf.v13productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.api.IProductTypeService;
import com.qf.common.base.BaseServiceImpl;
import com.qf.common.base.IBaseDao;
import com.qf.common.base.IBaseService;
import com.qf.entity.TProductType;
import com.qf.mapper.TProductTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: WangXi
 * @Date: 2019/6/14
 */
@Service
public class ProductServiceTypeImpl extends BaseServiceImpl<TProductType> implements IProductTypeService {

    @Autowired
    private TProductTypeMapper productTypeMapper;

    @Override
    public IBaseDao<TProductType> getBaseDao() {
        return productTypeMapper;
    }

    @Override
    public List<TProductType> queryByPid(Long id) {
        return productTypeMapper.queryByPidAndFlag(id);
    }
}
