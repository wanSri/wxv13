package com.qf.api;

import com.qf.common.base.IBaseService;
import com.qf.entity.TProductDesc;

/**
 * @author: WangXi
 * @Date: 2019/6/15
 */
public interface IProductDescService extends IBaseService<TProductDesc> {

    TProductDesc selectByProductId(Long productId);

    int updateByProductId(TProductDesc record);

}
