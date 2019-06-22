package com.qf.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.qf.common.base.IBaseDao;
import com.qf.entity.TProductType;

/**
@author: WangXi
@Date: 2019/6/11
*/
public interface TProductTypeMapper extends IBaseDao<TProductType> {
    List<TProductType> queryByPidAndFlag(@Param("pid")Long pid);


}