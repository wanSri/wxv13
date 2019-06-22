package com.qf.mapper;
import java.util.Collection;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.qf.common.base.IBaseDao;
import com.qf.entity.TProduct;

/**
@author: WangXi
@Date: 2019/6/11
*/
public interface TProductMapper extends IBaseDao<TProduct> {

    int updateFlagByIdIn(@Param("updatedFlag")Boolean updatedFlag,@Param("idArray")Long[] idArray);



}