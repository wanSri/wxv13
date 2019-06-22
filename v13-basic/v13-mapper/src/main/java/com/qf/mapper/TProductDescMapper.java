package com.qf.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;


import com.qf.common.base.IBaseDao;
import com.qf.entity.TProductDesc;

/**
@author: WangXi
@Date: 2019/6/11
*/
public interface TProductDescMapper extends IBaseDao<TProductDesc> {

    TProductDesc selectByProductId(@Param("productId")Long productId);

    int updateByProductId(TProductDesc updated);



}