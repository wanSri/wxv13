package com.qf.api;

import com.qf.common.base.IBaseService;
import com.qf.entity.TProductType;

import java.util.List;

/**
 * @author: WangXi
 * @Date: 2019/6/14
 */
public interface IProductTypeService extends IBaseService<TProductType> {
    List<TProductType> queryByPid(Long id);
}
