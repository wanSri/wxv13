package com.qf.api;

import com.github.pagehelper.PageInfo;
import com.qf.common.base.IBaseService;
import com.qf.entity.TProduct;
import com.qf.pojo.ProductVO;

/**
 * @author: WangXi
 * @Date: 2019/6/11
 */
public interface IProductService extends IBaseService<TProduct> {

    public PageInfo<TProduct> page(Integer pageIndex, Integer pageSize);

    Long insert(ProductVO productVO);

    int batchDelete(Long[] ids);
}
